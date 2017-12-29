package com.huak.web.home.health;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huak.auth.model.User;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.health.model.HealthScoreRecord;
import com.huak.health.model.PollingMessage;
import com.huak.health.type.PollingType;
import com.huak.health.vo.IndexDataA;
import com.huak.health.vo.IndexTempA;
import com.huak.health.vo.WorkWarnVo;
import com.huak.home.component.HealthScoreRecordService;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import com.huak.tools.HealthItem;
import com.huak.tools.Item;
import com.huak.web.home.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.home.health<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/22<BR>
 * Description:   健康指数  <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/health")
public class HealthController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static Map<String,Object> queueMap = new HashMap<>();

    private final static long TIMEOUT = 30000l;//超时时间

    private static final String COM_ID = "comId";
    private static final String ORG = "orgId";

    private static final String Q = "Q";
    private static final String F = "F";
    private static final String KEY = "key";

    @Resource
    private HealthScoreRecordService healthService;

    @RequestMapping(method = RequestMethod.GET)
    public String page(Model model, HttpServletRequest request) {
        logger.info("跳转健康指数页面");
        //Map param = paramsPackageOrg(model., request);
        Map<String, Object> params = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Constants.SESSION_KEY);
        Org org = (Org) session.getAttribute(Constants.SESSION_ORG_KEY);
        params.put("userid",user.getId());
        params.put("orgId",org.getId());
        HealthScoreRecord h= healthService.getRecordById(params);
        if(h==null){
            model.addAttribute(Constants.FLAG,false);
        }else {
            model.addAttribute(Constants.FLAG,true);
            model.addAttribute("score",String.valueOf(h.getScore()).substring(0,String.valueOf(h.getScore()).lastIndexOf(".")));
            model.addAttribute("time",h.getCreateTime().substring(0,h.getCreateTime().length()-2));
        }
        String key = UUIDGenerator.getUUID();
        Queue<PollingMessage> pollingMessageQueue = new ConcurrentLinkedQueue<>();//消息队列
       // model.addAttribute("tools",param);
        queueMap.put(key+Q,pollingMessageQueue);
        queueMap.put(key+F,false);
        model.addAttribute("healthItem", JSONArray.toJSONString(HealthItem.HEALTH_ITEM));
        model.addAttribute(KEY,key);

        return "health/inspect";
    }

    @RequestMapping(value = "/testing", method = RequestMethod.POST)
    @ResponseBody
    public void testing(HttpServletRequest request, HttpServletResponse response, @RequestBody List<JSONObject> items) {
        int idx = items.size()-1;
        JSONObject keyObject = items.get(idx);
        String key = keyObject.getString(KEY);
        items.remove(idx);

        Queue<PollingMessage> pollingMessageQueue = (Queue<PollingMessage>) queueMap.get(key+Q);

        pollingMessageQueue.clear();
        //业务数据入队列
        List<Item> list = JSONObject.parseArray(items.toString(), Item.class);
        healthIndex(request, list, pollingMessageQueue);

        queueMap.put(key+F,true);
    }

    /**
     * 开启长连接
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/polling", method = RequestMethod.GET)
    public
    @ResponseBody
    DeferredResult<JSONObject> polling(HttpServletRequest request, HttpServletResponse response,String key) throws InterruptedException {
        Thread.sleep(300);
        DeferredResult<JSONObject> result = new DeferredResult<>(TIMEOUT, null);  //设置超时,超时返回null
        boolean over = (boolean) queueMap.get(key+F);
        Queue<PollingMessage> pollingMessageQueue = (Queue<PollingMessage>) queueMap.get(key+Q);

        final JSONObject jo = new JSONObject();
        if (over && pollingMessageQueue.isEmpty()) {
            //消息接收完毕，返回结束标识
            jo.put(PollingType.END.getKey(), PollingType.END.getDes());
            result.setResult(jo);
            queueMap.remove(key+Q);
            queueMap.remove(key+F);
        } else {
            //队列取值
            final PollingMessage pollingMessage = pollingMessageQueue.poll();
            if (pollingMessage != null) {
                jo.put(pollingMessage.getKey(), pollingMessage.getValue());
            }

            result.setResult(jo);
            //结束后操作
            result.onCompletion(new Runnable() {
                @Override
                public void run() {
                    System.out.println(jo.toJSONString());
                }
            });
        }
        return result;

    }

    public void healthIndex(HttpServletRequest request, List<Item> items,Queue<PollingMessage> pollingMessageQueue) {

        Map<String, Object> params = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org) session.getAttribute(Constants.SESSION_ORG_KEY);

        params.put(COM_ID, company.getId());
        params.put(ORG, org.getId());

        for (int i = 0; i < items.size(); i++) {

            params.put("name", items.get(i).getTitle());

            if ("JJYX".equals(items.get(i).getParentName())) {
                List<IndexDataA> listj = healthService.getIndexData(params);
                //经济运行业务数据放入队列
                int count = 0;
                for (int n = 0; n < listj.size(); n++) {
                    if (Double.valueOf(listj.get(n).getDh()) > listj.get(n).getIndustry()) {
                        count++;
                    }
                    String s1 = listj.get(n).getUnitName() + listj.get(n).getName() + listj.get(n).getDh() + listj.get(n).getUnitMeter();
                    pollingMessageQueue.offer(new PollingMessage(PollingType.MSG.getKey(), s1));
                }
                pollingMessageQueue.offer(new PollingMessage(PollingType.NUM.getKey(), count));
            }
            if ("SWBJ".equals(items.get(i).getParentName())) {
                List<IndexTempA> listm = healthService.getIndexTemp(params);
                //室温报警业务数据放入队列

                List<PollingMessage> listp = new ArrayList<PollingMessage>();
                int count = 0;
                for (int m = 0; m < listm.size(); m++) {
                    if (Double.valueOf(listm.get(m).getTemp()) < listm.get(m).getMinTemp()
                            || Double.valueOf(listm.get(m).getTemp()) > listm.get(m).getMaxTemp()) {
                        count++;
                    }
                    String s1 = listm.get(m).getStationName() + listm.get(m).getCommunityName() + listm.get(m).getRoomCode() + "室温" + listm.get(m).getTemp() + "℃";
                    pollingMessageQueue.offer(new PollingMessage(PollingType.MSG.getKey(), s1));
                }
                pollingMessageQueue.offer(new PollingMessage(PollingType.NUM.getKey(), count));
            }
            if ("GKYX".equals(items.get(i).getParentName())) {
                //工况运行业务数据放入队列
                params.put("alarmName",items.get(i).getTitle());
                List<WorkWarnVo> list = healthService.getWorkWarnInfo(params);
                int count = 0;
                for (int j = 0; j <list.size() ; j++) {
                    count++;
                    String s1=list.get(j).getAlarmName()+"-"+list.get(j).getAlarmLevel()+"-"+list.get(j).getModel()+"-"+list.get(j).getStartTime();
                    pollingMessageQueue.offer(new PollingMessage(PollingType.MSG.getKey(), s1));
                }
                pollingMessageQueue.offer(new PollingMessage(PollingType.NUM.getKey(), count));
            }
            if ("FWQK".equals(items.get(i).getParentName())) {
                //业务数据放入队列
                pollingMessageQueue.offer(new PollingMessage(PollingType.NUM.getKey(), 0));
            }
        }
    }
}
