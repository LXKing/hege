package com.huak.sys;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.sys.model.EnergyProject;

@Controller
@RequestMapping("/energy/project")
public class EnergyProjectController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private EnergyProjectService energyProjectService;
    
    /**
     * 获取采暖季下拉框html
     * @param orgId
     * @return
     */
    @RequestMapping(value="/getSeasonSelectHtml",method=RequestMethod.GET)
    @ResponseBody
    public String getSeasonSelectHtml(@RequestParam Map<String, Object> paramsMap){
    	logger.info("获取采暖季下拉框html");
    	JSONObject result = new JSONObject();
		String htmlStr = energyProjectService.getSeasonSelectHtml(paramsMap,null);
		result.put("html", htmlStr);
		return result.toJSONString();
    }

    /**
     * 跳转到能耗计划列表页面
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage(Model model) {
        logger.info("转至系统能耗计划列表页");
        return "/sys/energy/project/list";
    }

    /**
     * 分页查询
     * @param paramsMap
     * @param page
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("能耗计划列表页分页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, energyProjectService.list(paramsMap, page));
        } catch (Exception e) {
            logger.error("能耗计划列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     * 跳转到新增能耗计划页面
     * @param paramsMap
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(@RequestParam Map<String, Object> paramsMap,Model model) {
    	model.addAttribute("seasonHtml", energyProjectService.getSeasonSelectHtml(paramsMap, null));
        return "/sys/energy/project/add";
    }

    /**
     * 新增能耗计划
     * @param energyProject
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(EnergyProject energyProject, HttpServletRequest request) {
        logger.info("添加能耗计划");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
        	energyProject.setId(UUIDGenerator.getUUID());
            int r = energyProjectService.save(energyProject);
            if(r>0){
            	jo.put(Constants.FLAG, true);
            	jo.put(Constants.MSG, "添加能耗计划成功");
            }else{
            	jo.put(Constants.MSG, "添加能耗计划失败");
            }
        } catch (Exception e) {
            logger.error("添加能耗计划异常" + e.getMessage());
            jo.put(Constants.MSG, "添加能耗计划失败");
        }
        return jo.toJSONString();
    }

    /**
     * 跳转到修改能耗计划页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") String id,@RequestParam Map<String,Object> paramsMap) {
        logger.info("跳转修改能耗计划页");
        try {
        	EnergyProject ep = energyProjectService.get(id);
        	if(null!=ep){
        		model.addAttribute("energyProject", energyProjectService.get(id));
        		model.addAttribute("seasonHtml", energyProjectService.getSeasonSelectHtml(paramsMap, ep.getSeasonId()));
        	}
        } catch (Exception e) {
            logger.error("跳转修改能耗计划页异常" + e.getMessage());
        }
        return "/sys/energy/project/edit";
    }

    /**
     * 修改能耗计划
     * @param energyProject
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(EnergyProject energyProject) {
        logger.info("修改能耗计划");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
        	int r = energyProjectService.edit(energyProject);
        	if(r>0){
        		jo.put(Constants.FLAG, true);
        		jo.put(Constants.MSG, "修改能耗计划成功");
        	}else{
        		jo.put(Constants.MSG, "修改能耗计划失败");
        	}
        } catch (Exception e) {
            logger.error("修改能耗计划异常" + e.getMessage());
            jo.put(Constants.MSG, "修改能耗计划失败");
        }
        return jo.toJSONString();
    }

    /**
     * 删除能耗计划
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String deleteEnergyType(@PathVariable("id") String id) {
        logger.info("删除能耗计划");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
        	int r = energyProjectService.remove(id);
        	if(r>0){
        		jo.put(Constants.FLAG, true);
        		jo.put(Constants.MSG, "删除能耗计划成功");
        	}else{
        		jo.put(Constants.MSG, "删除能耗计划失败");
        	}
        } catch (Exception e) {
            logger.error("删除能耗计划异常" + e.getMessage());
            jo.put(Constants.MSG, "删除能耗计划失败");
        }
        return jo.toJSONString();
    }

    /**
     * 导出能耗计划
     * @param paramsMap
     * @param response
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, Object> paramsMap, HttpServletResponse response) {
        logger.info("导出能耗计划列表EXCEL");
        String workBookName = "能耗计划列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
        cellName.put("ORG_NAME", "所属组织机构");
        cellName.put("SEASON_NAME", "所属采暖季");
        cellName.put("NUM", "能耗计划");
        List<Map<String, Object>> cellValues = null;//列值
        OutputStream out = null;
        try {
            cellValues = energyProjectService.export(paramsMap);
            HSSFWorkbook wb = CommonExcelExport.excelExport(cellName, cellValues);
            //response输出流导出excel
            String mimetype = "application/vnd.ms-excel";
            response.setContentType(mimetype);
            response.setCharacterEncoding("UTF-8");
            String fileName = workBookName + ".xls";
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("导出能耗计划列表EXCEL异常" + e.getMessage());
        }
    }

    @RequestMapping(value = "/hasUnique", method = RequestMethod.POST)
    @ResponseBody
    public String hasUnique(@RequestParam Map<String, Object> paramsMap) {
        logger.info("能耗计划唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            Long num = energyProjectService.hasUnique(paramsMap);
            if (num == 0) {
                jo.put(Constants.FLAG, true);
            }
        } catch (Exception e) {
            logger.error("能耗计划唯一性校验异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

}
