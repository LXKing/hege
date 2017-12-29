package com.huak.web.home.component;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.home.component.ComponentService;
import com.huak.home.type.RoomVO;
import com.huak.home.type.ToolVO;
import com.huak.web.home.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/9/26.
 */
@Controller
@RequestMapping("/roomtemperature")
public class QualifiedRateTrendController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ComponentService componentService;
    /**
     * 室温散点组件
     * @param request
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public String roomtemperature(ToolVO toolVO,HttpServletRequest request,RoomVO roomVO) {
        logger.info("组件-室温散点加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);

        try {
            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);
            params.put("min",roomVO.getMin());
            params.put("max",roomVO.getMax());

            Map<String,Object> map =  componentService.roomTemperature(params);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("组件-室温散点加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
}
