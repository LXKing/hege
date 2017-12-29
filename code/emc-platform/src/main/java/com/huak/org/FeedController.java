package com.huak.org;

import com.alibaba.fastjson.JSONObject;
import com.huak.auth.model.Role;
import com.huak.base.dao.DateDao;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.org.model.Feed;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.org<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/16<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/feed")
public class FeedController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private FeedService feedService;

    @Resource
    private OncenetService oncenetService;

    @Resource
    private SecondnetService secondnetService;
    /**
     * 转至系统热源列表页
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage() {
        logger.info("转至系统热源列表页");
        return "/org/feed/list";
    }

    /**
     * 热源列表查询
     * @param paramsMap
     * @param page
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("热源列表页分页查询");
        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, feedService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("热力站列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }


    /**
     * 跳转到热源添加页面
     * @param pOrgId
     * @param comId
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/add/{pOrgId}/{comId}", method = RequestMethod.GET)
    public String addPage(@PathVariable("pOrgId") String pOrgId,@PathVariable("comId") String comId,ModelMap modelMap) {
        try {
            Feed obj = new Feed();
            obj.setOrgId(Long.parseLong(pOrgId));
            obj.setComId(comId);
            Map<String,Object> params = new HashMap<>();
            params.put("comId",comId);
            modelMap.put("oncenet",oncenetService.selectNetAll(params));
            modelMap.put(Constants.OBJECT,obj);
        } catch (Exception e) {
            logger.error("跳转到热源编辑页出错！");
        }
        return "/org/feed/add";
    }

    /**
     * 跳转到热源修改页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}/{orgId}/{comId}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") String id, @PathVariable("orgId") String orgId
            , @PathVariable("comId") String comId) {
        logger.info("跳转修改热源页");
        try {
            Feed feed = feedService.selectByPrimaryKey(id);
            Map<String,Object> params = new HashMap<>();
            params.put("orgId",orgId);
            params.put("comId",comId);
            model.addAttribute("oncenet",oncenetService.selectNetAll(params));
            model.addAttribute("secondnet",secondnetService.selectLineAll(params));
            model.addAttribute(Constants.OBJECT, feed);
        } catch (Exception e) {
            logger.error("跳转修改热源页异常" + e.getMessage());
        }
        return "/org/feed/edit";
    }

    /**
     * 热源数据保存
     * @param feed
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(Feed feed, HttpServletRequest request) {
        logger.info("添加热源");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            // TODO 添加session，创建者
            HttpSession session = request.getSession();
            feed.setId(UUIDGenerator.getUUID());
            feedService.insertSelective(feed);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加热源成功");
        } catch (Exception e) {
            logger.error("添加热源异常" + e.getMessage());
            jo.put(Constants.MSG, "添加热源失败");
        }
        return jo.toJSONString();
    }

    /**
     * 热源数据修改
     * @param feed
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String editValue(Feed feed) {
        logger.info("修改热源");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            int i = feedService.updateByPrimaryKeySelective(feed);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "修改热源成功");
        } catch (Exception e) {
            logger.error("修改热源异常" + e.getMessage());
            jo.put(Constants.MSG, "修改热源失败");
        }
        return jo.toJSONString();
    }

    /**
     * 热源修改
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteFeed(@PathVariable("id") String id) {
        logger.info("删除热源");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            feedService.deleteByPrimaryKey(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除热源成功");
        } catch (Exception e) {
            logger.error("删除热源异常" + e.getMessage());
            jo.put(Constants.MSG, "删除热源失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, Object> paramsMap, HttpServletResponse response) {
        logger.info("导出热源列表EXCEL");
        String workBookName = "热源列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)

        cellName.put("FEED_NAME", "热源名称");
        cellName.put("FEED_CODE", "热源编号");
        cellName.put("FEED_TYPE_NAME", "热源性质");
        cellName.put("HEAT_TYPE_NAME", "供热类型");
        cellName.put("INSTALL_CAPACITY", "装机容量(MW)");
        cellName.put("HEAT_CAPACITY", "供热能力(m²)");
        cellName.put("STEAMTURBINE_NUM", "汽机数量");
        cellName.put("ADDR", "详细地址");
        cellName.put("BOILER_NUM", "锅炉数量");
        List<Map<String, Object>> cellValues = null;//列值
        OutputStream out = null;
        try {
            cellValues = feedService.exportFeeds(paramsMap);
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
            logger.error("导出热力站列表EXCEL异常" + e.getMessage());
        }
    }

    /**
     *名称唯一性校验
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public String checkUnique(@RequestParam Map<String, Object> paramsMap) {
        logger.info("热源唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            List<Map<String,Object>> data= feedService.selectFeedByMap(paramsMap);
            if (data.size() == 0) {
                jo.put(Constants.FLAG, true);
            }else {
                jo.put(Constants.FLAG, false);
            }
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("热源唯一性校验异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
}
