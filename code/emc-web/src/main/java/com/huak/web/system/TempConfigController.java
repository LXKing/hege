package com.huak.web.system;

import com.alibaba.fastjson.JSONObject;
import com.huak.auth.model.User;
import com.huak.common.*;
import com.huak.common.page.Page;
import com.huak.common.utils.MultipartFileParam;
import com.huak.common.utils.MultipartFileUploadUtil;
import com.huak.health.AlarmConfigService;
import com.huak.health.AlarmConfigTempService;
import com.huak.health.model.AlarmConfigTemp;
import com.huak.home.workorder.WorkOrderInfoService;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import com.huak.web.home.BaseController;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.system<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/8<BR>
 * Description: 室温配置    <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/temp/config")
public class TempConfigController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${upload.file.dir}")
    private String UPLOAD_FILE_DIR;

    private static AtomicLong counter = new AtomicLong(0L);
    private static final String COMPANY = "company";
    private static final String ORG = "org";
    @Resource
    private AlarmConfigService alarmConfigService;
    @Resource
    private AlarmConfigTempService alarmConfigTempService;
    @Resource
    private WorkOrderInfoService workOrderInfoService;
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public String page(HttpServletRequest request,Model model){
        logger.info("打开室温配置页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);

        model.addAttribute(COMPANY,company);
        model.addAttribute(ORG,org);
        return "system/temp/list";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addPage(HttpServletRequest request,Model model){
        logger.info("打开室温添加配置页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);

        model.addAttribute(COMPANY,company);
        model.addAttribute(ORG,org);
        return "system/temp/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(AlarmConfigTemp alarmConfigTemp, HttpServletRequest request) {
        logger.info("添加室温配置");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);

        try {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute(Constants.SESSION_KEY);
            alarmConfigTemp.setId(UUIDGenerator.getUUID());
            alarmConfigTempService.insertSelective(alarmConfigTemp);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加室温配置成功");
        } catch (Exception e) {
            logger.error("添加室温配置异常" + e.getMessage());
            jo.put(Constants.MSG, "添加室温配置失败");
        }
            return jo.toJSONString();
    }

    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("温度配置列表分页查询");
        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, alarmConfigTempService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("室温配置列表分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String editPage(HttpServletRequest request,Model model,@PathVariable("id") String id){
        logger.info("打开修改室温配置页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);

        AlarmConfigTemp alarmConfigTemp = alarmConfigTempService.selectUpdateMap(id);

        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);

        model.addAttribute(ORG,org);
        model.addAttribute("company",company);
        model.addAttribute("alarmConfig",alarmConfigTemp);
        return "system/temp/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ResponseBody
    public String edit(AlarmConfigTemp alarmConfig) {
        logger.info("修改室温配置");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            alarmConfigTempService.updateByPrimaryKeySelective(alarmConfig);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "修改室温配置成功");
        } catch (Exception e) {
            logger.error("修改室温配置异常" + e.getMessage());
            jo.put(Constants.MSG, "修改室温配置失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id) {
        logger.info("删除室温配置");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            alarmConfigTempService.deleteByPrimaryKey(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除室温配置成功");
        } catch (Exception e) {
            logger.error("删除室温配置异常" + e.getMessage());
            jo.put(Constants.MSG, "删除室温配置失败");
        }
        return jo.toJSONString();
    }

    /**
     * 打开导入页
     * @param model
     * @return
     */
    @RequestMapping(value = "/upload/page", method = RequestMethod.GET)
    public String uploadPage(Model model) {
        logger.info(" 前台-安全与后台-室温报警导入跳转页");
        model.addAttribute("uploadUrl","/temp/config/upload");
        return "/system/upload";
    }
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, Object> paramsMap, HttpServletResponse response) {
        logger.info("导出室温列表EXCEL");
        String workBookName = "室温列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)

        cellName.put("ID", "主键(存在即修改)");
        cellName.put("COM_ID", "公司主键");
        cellName.put("UNIT_ID", "单位主键");
        cellName.put("MAXTEMP", "最高温度(℃)");
        cellName.put("MINTEMP", "最低温度(℃)");
        cellName.put("ISENABLE", "是否启用(是否启用0-启用 1-停用)");
        cellName.put("UNIT_TYPE", "单位类型1-源2-网3-站4-线5-户");
        cellName.put("UNITNAME", "单位名称");


        List<Map<String, Object>> cellValues = null;//列值
        OutputStream out = null;
        try {
            cellValues = alarmConfigTempService.exportTempConfig(paramsMap);
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
            logger.error("导出记录列表EXCEL异常" + e.getMessage());
        }
    }

    /**
     * 前台-安全与后台-工况报警-导入
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "upload", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String add(HttpServletRequest request, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, true);
        String prefix = "req_count:" + counter.incrementAndGet() + ":";
        MultipartFileParam param = null;
        Map<String, Object> obj = null;
        RandomAccessFile accessTmpFile = null;
        RandomAccessFile accessConfFile = null;
        //使用 工具类解析相关参数，工具类代码见下面
        try {
            param = MultipartFileUploadUtil.parse(request);
            //这个必须与前端设定的值一致
            long chunkSize = 512 * 1024;
            if (param.isMultipart()) {
                String tempFileName = param.getFileName();
                File confFile = new File(UPLOAD_FILE_DIR, param.getFileName() + ".conf");
                File tmpDir = new File(UPLOAD_FILE_DIR);
                File tmpFile = new File(UPLOAD_FILE_DIR, tempFileName);
                if (!tmpDir.exists()) {
                    tmpDir.mkdirs();
                }
                accessTmpFile = new RandomAccessFile(tmpFile, "rw");
                accessConfFile = new RandomAccessFile(confFile, "rw");
                long offset = chunkSize * param.getChunk();
                //定位到该分片的偏移量
                accessTmpFile.seek(offset);
                //写入该分片数据
                accessTmpFile.write(param.getFileItem().get());

                //把该分段标记为 true 表示完成
                System.out.println(prefix + "set part " + param.getChunk() + " complete");
                accessConfFile.setLength(param.getChunks());
                accessConfFile.seek(param.getChunk());
                accessConfFile.write(Byte.MAX_VALUE);

                //completeList 检查是否全部完成,如果数组里是否全部都是(全部分片都成功上传)
                byte[] completeList = FileUtils.readFileToByteArray(confFile);
                byte isComplete = Byte.MAX_VALUE;
                for (int i = 0; i < completeList.length && isComplete == Byte.MAX_VALUE; i++) {
                    //与运算, 如果有部分没有完成则 isComplete 不是 Byte.MAX_VALUE
                    isComplete = (byte) (isComplete & completeList[i]);
                }
                if (isComplete == Byte.MAX_VALUE) {
                    obj = this.importData(UPLOAD_FILE_DIR + "\\" + param.getFileName());
                }

            }
/*            if (null != accessTmpFile) {
                accessTmpFile.close();
            }
            if (null != accessConfFile) {
                accessConfFile.close();
            }*/
        } catch (Exception e) {
            jo.put(Constants.FLAG, false);
            logger.error("前台-安全与后台-采集表管理-导入异常:" + e);
        } finally {
            if (null != accessConfFile) {
                try {
                    accessConfFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (null != accessTmpFile) {
                        try {
                            accessTmpFile.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            File file = new File(UPLOAD_FILE_DIR);
            if (file.exists()) {
                System.out.println("删除excel");
                File[] files = file.listFiles();
                for (File files1 : files) {
                    files1.delete();
                }
            }
        }
        jo.put("message", obj);
        return jo.toJSONString();

    }

    //获取数据
    private Map<String, Object> importData(String path) {
        Map<String,String> cellMap = new LinkedHashMap<>();
        cellMap.put("ID", "主键(存在即修改)");
        cellMap.put("COM_ID", "公司主键");
        cellMap.put("UNIT_ID", "单位主键");

        cellMap.put("MAXTEMP", "最高温度(℃)");
        cellMap.put("MINTEMP", "最低温度(℃)");
        cellMap.put("ISENABLE", "是否启用(是否启用0-启用 1-停用)");
        cellMap.put("UNIT_TYPE", "单位类型1-源2-网3-站4-线5-户");

        System.out.println("-------------------------path:" + path + "-------------------------------");
        String prefix = "req_count:" + counter.incrementAndGet() + ":";
        System.out.println(prefix + "start !!!");
        FileInputStream io = null;
        InputStream io1 = null;
        Map<String, Object> result = new HashMap<>();
        result.put(Constants.FLAG, "1");
        StringBuffer message = new StringBuffer();
        try {
            Sheet hssFSheet = null;
            Workbook hssFWorkBook = null;
            Row xssfRow = null;
            AlarmConfigTemp alarmConfig = null;
            List<AlarmConfigTemp> list = new ArrayList<>();
            try {
                io = new FileInputStream(path);
                hssFWorkBook = new XSSFWorkbook(io);
            } catch (Exception e) {
                io1 = new FileInputStream(path);
                hssFWorkBook = new HSSFWorkbook(io1);
            }
            for (int i = 0; i < hssFWorkBook.getNumberOfSheets(); i++) {
                hssFSheet = hssFWorkBook.getSheetAt(i);
                for (int k = 1; k < hssFSheet.getPhysicalNumberOfRows(); k++) {
                    xssfRow = hssFSheet.getRow(k);
                    try {
                        alarmConfig = (AlarmConfigTemp) FileParseUtil.digitData(xssfRow, cellMap, AlarmConfigTemp.class);
                        list.add(alarmConfig);
                    } catch (Exception e) {
                        message.append("第" + (k) + "行数据有问题：新增失败");
                        message.append(",");
                        result.put("flag", "2");
                        //e.printStackTrace();
                        break;
                    }

                }
            }
            for (int m = 0; m < list.size(); m++) {
                AlarmConfigTemp data = list.get(m);
                if(StringUtils.isEmpty(data.getId())){
                    data.setId(UUIDGenerator.getUUID());
                    try {
                        alarmConfigTempService.insert(data);
                    } catch (Exception e) {
                        message.append("第" + (m + 1) + "行数据有问题：新增失败");
                        message.append(",");
                        result.put("flag", "2");
                    }
                }else{
                    try {
                        alarmConfigTempService.updateByPrimaryKeySelective(data);
                    } catch (Exception e) {
                        message.append("第" + (m + 1) + "行数据有问题：更新失败");
                        message.append(",");
                        result.put("flag", "2");
                    }
                }

            }
            io.close();
        } catch (Exception e) {
            result.put("flag", "2");
            message.append("系统错误！");
            logger.info("前台-安全与后台-采集表管理-导入出错" + e);
        } finally {
            if (null != io) {
                try {
                    io.close();
                } catch (IOException e) {
                    logger.info("前台-安全与后台-采集表管理-导入出错" + e);
                }
            }
        }
        result.put("message", message);
        return result;
    }

    @RequestMapping(value = "/check/name", method = RequestMethod.POST)
    @ResponseBody
    public String checkUnitName(@RequestParam Map<String, Object> paramsMap) {
        logger.info("用能单位唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            Long num = alarmConfigTempService.checkUnitName(paramsMap);
            if (num == 0) {
                jo.put(Constants.FLAG, true);
            }
        } catch (Exception e) {
            logger.error("用能单位唯一性校验异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
}