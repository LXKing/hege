package com.huak.web.home.component;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.home.component.ComponentService;
import com.huak.home.type.ToolVO;
import com.huak.web.home.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
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
@RequestMapping("/energyConsumption")
public class EnergyConsumptionController  extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ComponentService componentService;

    /**
     *组件 能耗明细
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public String energyDetail(ToolVO toolVO, HttpServletRequest request) {
        logger.info("组件-能耗明细加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);

            Map<String,Object> map =  componentService.energyDetail(params);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());

            }

        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("组件-能耗明细加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

}
