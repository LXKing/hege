package com.huak.org;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.model.Community;

/**
 * 小区controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/community")
public class CommunityController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommunityService communityService;
	
	/**
	 * 跳转到小区页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String listPage(Model model){
		logger.info("跳转到小区列表页面");
		model.addAttribute("com", communityService.getCompanySelectHtmlStr(null));
		return "/org/community/list";
	}
	
	/**
	 * 查询小区分页列表信息
	 * @param params
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.PATCH)
	@ResponseBody
	public String list(@RequestParam Map<String,String> params,Page page){
		logger.info("查询小区信息");
		JSONObject result = new JSONObject();
		//查询小区信息
		PageResult<Community> list = communityService.list(params,page);
		result.put(Constants.LIST, list);
		logger.info("查询小区信息："+list);
		return result.toJSONString();
	}
	
	/**
	 * 跳转到添加小区信息页面
	 * @param model
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value="/add/{companyId}",method=RequestMethod.GET)
	public String addPage(Model model,@PathVariable String companyId){
		logger.info("跳转到添加小区页面");
		model.addAttribute("com", communityService.getCompanySelectHtmlStr(companyId));
		return "/org/community/add";
	}
	
	/**
	 * 添加小区信息
	 * @param community
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public String add(Community community){
		logger.info("添加小区信息");
		JSONObject result = new JSONObject();
		try{
			result.put(Constants.FLAG, false);
			int save = communityService.save(community);
			if(save<=0){
				result.put(Constants.MSG, "添加小区信息失败");
	        }else{
	        	result.put(Constants.FLAG, true);
	        	result.put(Constants.MSG, "添加小区信息成功");
	        }
		}catch(Exception e){
			logger.error("添加小区信息异常" + e.getMessage());
			result.put(Constants.MSG, "添加小区信息异常");
		}
		return result.toJSONString();
	}
	
	/**
	 * 跳转到修改小区信息页面
	 * @param model
	 * @param communityId
	 * @return
	 */
	@RequestMapping(value="/edit/{communityId}",method=RequestMethod.GET)
	public String editPage(Model model,@PathVariable String communityId){
		logger.info("跳转到修改小区页面");
		Community community = communityService.get(communityId);
		if(community!=null){
			model.addAttribute("community", community);
			model.addAttribute("com", communityService.getCompanySelectHtmlStr(community.getComId()));
		}
		return "/org/community/edit";
	}
	
	/**
	 * 修改小区信息
	 * @param community
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public String edit(Community community){
		logger.info("修改小区信息，小区id:"+community.getId());
		JSONObject result = new JSONObject();
		try{
			result.put(Constants.FLAG, false);
			int edit = communityService.edit(community);
			if(edit<=0){
				result.put(Constants.MSG, "修改小区信息失败");
	        }else{
	        	result.put(Constants.FLAG, true);
	        	result.put(Constants.MSG, "修改小区信息成功");
	        }
		}catch(Exception e){
			logger.error("修改小区信息异常" + e.getMessage());
			result.put(Constants.MSG, "修改小区信息异常");
		}
		return result.toJSONString();
	}
	
	/**
	 * 删除小区信息
	 * @param communityId
	 * @return
	 */
	@RequestMapping(value="/delete/{communityId}",method=RequestMethod.POST)
	@ResponseBody
	public String delete(@PathVariable String communityId){
		logger.info("删除小区信息，小区id："+communityId);
		JSONObject result = new JSONObject();
		try{
			result.put(Constants.FLAG, false);
			int delete = communityService.delete(communityId);
			if(delete<=0){
				result.put(Constants.MSG, "删除小区信息失败");
	        }else{
	        	result.put(Constants.FLAG, true);
	        	result.put(Constants.MSG, "删除小区信息成功");
	        }
		}catch(Exception e){
			logger.error("删除小区信息异常" + e.getMessage());
			result.put(Constants.MSG, "删除小区信息异常");
		}
		return result.toJSONString();
	}
	
	/**
	 * 导出小区信息
	 * @param param
	 * @param response
	 */
	@RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, String> param, HttpServletResponse response) {
        logger.info("导出小区列表EXCEL");
        try {
        	JSONObject jo = new JSONObject();
	        jo.put(Constants.FLAG, false);
	        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
	        cellName.put("community_name", "小区名称");
	        cellName.put("com_name", "所属公司");
	        cellName.put("org_name", "所属机构");
	        List<Map<String, Object>> cellValues = communityService.exportCommunity(param);
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
                String fileName = "小区列表.xls";
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
                wb.write(out);
                out.flush();
            }
        } catch (Exception e) {
            logger.error("导出小区列表EXCEL异常" + e.getMessage());
        }
    }
	

}
