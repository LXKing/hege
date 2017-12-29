package com.huak.log;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.page.Page;

@Controller
@RequestMapping("/log")
public class LoggerController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OperateLogService loggerService;
	
	/**
     * 跳转到列表页面
     * @param modelMap
     * @return
     */
	@EMCLog(key="日志管理",name="跳转到日志列表页面",type=Constants.OPT_TYPE_SELECT)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage(ModelMap modelMap) {
        logger.info("转至系统日志列表页");
        return "/log/list";
    }

    /**
     * 根据条件查询列表信息
     * @param paramsMap
     * @param page
     * @return
     */
	@EMCLog(key="日志管理",name="根据条件查询日志列表信息",type=Constants.OPT_TYPE_SELECT)
    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, String> paramsMap, Page page) {
        logger.info("用户列表页分页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, loggerService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("用户列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
    
    /**
     * 导出业务日志信息
     * @param paramsMap
     * @param response
     */
	@EMCLog(key="日志管理",name="导出日志信息到Excel",type=Constants.OPT_TYPE_SELECT)
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, String> param, HttpServletResponse response) {
        logger.info("导出业务日志EXCEL");
        try {
        	
        	JSONObject jo = new JSONObject();
	        jo.put(Constants.FLAG, false);
	        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
	        cellName.put("id", "日志主键");
	        cellName.put("opt_name", "操作名称");
	        cellName.put("opt_type", "操作类型");
	        cellName.put("opt_key", "操作模块名称");
	        cellName.put("class_name", "操作类名称");
	        cellName.put("method_name", "操作方法名称");
	        cellName.put("remote_ip", "客户机ip");
	        cellName.put("remote_name", "客户机名称");
	        cellName.put("remote_port", "客户机端口");
	        cellName.put("req_uri", "请求资源");
	        cellName.put("req_url", "请求地址");
	        cellName.put("creator", "创建者");
	        cellName.put("create_time", "创建时间");
	        List<Map<String, Object>> cellValues = loggerService.exportLog(param);
            if(cellValues==null){
            	jo.put(Constants.MSG, "导出失败");
            }else if(cellValues.size()==0){
            	jo.put(Constants.MSG, "没有数据要导出");
            }else{
            	jo.put(Constants.MSG, "导出失败");
            	HSSFWorkbook wb = CommonExcelExport.excelExport(cellName, cellValues);
            	OutputStream out = response.getOutputStream();
            	//输出Excel文件  
            	String mimetype = "application/vnd.ms-excel";
                response.setContentType(mimetype);
                response.setCharacterEncoding("UTF-8");
                String fileName = "operateLogInfo.xls";
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
                wb.write(out);
                out.flush();
            }
        } catch (Exception e) {
            logger.error("导出业务日志列表EXCEL异常" + e.getMessage());
        }
    }

}
