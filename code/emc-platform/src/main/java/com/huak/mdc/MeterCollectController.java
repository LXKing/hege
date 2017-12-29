package com.huak.mdc;

import com.alibaba.fastjson.JSONObject;
import com.huak.auth.model.User;
import com.huak.common.*;
import com.huak.common.page.Page;
import com.huak.common.utils.MultipartFileParam;
import com.huak.common.utils.MultipartFileUploadUtil;
import com.huak.mdc.model.MeterCollect;
import com.huak.mdc.model.RecordChange;
import com.huak.mdc.model.RecordPrestore;
import com.huak.prst.ChangeService;
import com.huak.prst.PrestoreService;
import com.huak.sys.model.EnergyType;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * 计量仪器管理
 * Created by MR-BIN on 2017/8/28.
 */
@RequestMapping("/meterCollect")
@Controller
public class MeterCollectController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MeterCollectService meterCollectService;

    @Resource
    private ChangeService changeService;
    @Resource
    private PrestoreService prestoreService;
    private static AtomicLong counter = new AtomicLong(0L);
    private static String UPLOAD_TEMP_DIR = "/usr/software/upload/sysmagent";
    private static String UPLOAD_TEMP_DIR1 = "D:\\workSp\\code\\upload";

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String listPage(Model model) {
        logger.info("转至系统计量器具列表页");
        return "/sys/mdc/list";
    }

    /**
     * 跳转至计量器具导入界面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/uploadPage", method = RequestMethod.GET)
    public String toUpload(Model model) {
        logger.info("转至系统计量器具导入界面");
        return "/sys/mdc/upload";
    }

    /**
     * 后台-计量器具导入
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
                File confFile = new File(UPLOAD_TEMP_DIR1, param.getFileName() + ".conf");
                File tmpDir = new File(UPLOAD_TEMP_DIR1);
                File tmpFile = new File(UPLOAD_TEMP_DIR1, tempFileName);
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
                    obj = this.getMap(UPLOAD_TEMP_DIR1 + "\\" + param.getFileName());
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
            logger.error("后台-计量器具导入异常:" + e);
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

            File file = new File(UPLOAD_TEMP_DIR1);
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
    private Map<String, Object> getMap(String path) {
        Map<String,String> cellMap = new LinkedHashMap<>();
        cellMap.put("CODE", "代码");
        cellMap.put("SERIAL_NO", "出厂编号");
        cellMap.put("NAME", "名称");
        cellMap.put("UNIT_ID", "单位名称");
        cellMap.put("ENERGY_TYPE_ID", "能源类型");
        cellMap.put("ISREAL", "实虚表");
        cellMap.put("ISTOTAL", "是否总表");
        cellMap.put("COEF", "系数");
        cellMap.put("UNIT_TYPE", "单位类型");
        cellMap.put("ISAUTO", "采集");
        cellMap.put("TAG", "点表");
        cellMap.put("FORMULA", "公式");
        cellMap.put("ISPRESTORE", "预存");
        cellMap.put("ISDELETE", "删除标识");
        cellMap.put("DEPICT", "描述");
        cellMap.put("COM_ID", "所属公司");
        List<Map<String, Object>> tempdata = meterCollectService.selectByMaps(new HashMap<String, Object>());
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
            MeterCollect meterCollect = null;
            List<MeterCollect> list = new ArrayList<>();
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
                        meterCollect = (MeterCollect) FileParseUtil.digitData(xssfRow,cellMap, MeterCollect.class);
                        list.add(meterCollect);
                    } catch (Exception e) {
                        message.append("第" + (k) + "行数据有问题：新增失败");
                        message.append(",");
                        result.put("flag", "2");
                        break;
                    }

                }
            }
            for (int m = 0; m < list.size(); m++) {
                MeterCollect data = list.get(m);
                if (null != data.getIsauto()) {
                    if (data.getIsauto().equals("自动采集")) {
                        data.setIsauto((byte) 0);
                    }
                    if (data.getIsauto().equals("手工")) {
                        data.setIsauto((byte) 1);
                    }
                }
                if (null != data.getIsprestore()) {
                    if (data.getIsprestore().equals("不是")) {
                        data.setIsprestore((byte) 0);
                    }
                    if (data.getIsprestore().equals("是")) {
                        data.setIsprestore((byte) 1);
                    }
                }
                if (null != data.getIsreal()) {
                    if (data.getIsreal().equals("否")) {
                        data.setIsreal((byte) 0);
                    }
                    if (data.getIsreal().equals("单位总表")) {
                        data.setIsreal((byte) 1);
                    }
                    if (data.getIsreal().equals("系统总表")) {
                        data.setIsreal((byte) 2);
                    }
                }
                if (null != data.getIsdelete()) {
                    if (data.getIsdelete().equals("软删除标识")) {
                        data.setIsdelete((byte) 0);
                    }
                    if (data.getIsdelete().equals("未删除")) {
                        data.setIsdelete((byte) 1);
                    }
                }
                if (null != data.getUnitType()) {
                    if (data.getUnitType().equals("热源")) {
                        data.setUnitType((byte) 1);
                    }
                    if (data.getUnitType().equals("一次网")) {
                        data.setUnitType((byte) 2);
                    }
                    if (data.getUnitType().equals("换热站")) {
                        data.setUnitType((byte) 3);
                    }
                    if (data.getUnitType().equals("二次线")) {
                        data.setUnitType((byte) 4);
                    }
                    if (data.getUnitType().equals("用户")) {
                        data.setUnitType((byte) 5);
                    }
                }
                boolean index = false;
                String codeFlag = data.getCode() + "-" + data.getComId();
                for (Map f : tempdata) {
                    if (f.containsValue(codeFlag)) {
                        index = true;
                        break;
                    }
                }
                if (!index) {
                    data.setId(UUIDGenerator.getUUID());
                    try {
                        meterCollectService.insert(data);
                        Map<String, Object> meter = new HashMap<>();
                        meter.put("code", codeFlag);
                        tempdata.add(meter);

                    } catch (Exception e) {
                        message.append("第" + (m + 1) + "行数据有问题：新增失败");
                        message.append(",");
                        result.put("flag", "2");
                    }
                } else {
                    try {
                        meterCollectService.updateByPrimaryKeySelective(data);
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
            logger.info("后台-计量器具导入出错" + e);
        } finally {
            if (null != io) {
                try {
                    io.close();
                } catch (IOException e) {
                    logger.info("后台-计量器具导入出错" + e);
                }
            }
        }
        result.put("message", message);
        return result;
    }

    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("计量器具列表页分页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, meterCollectService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("计量器具列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     * 计量仪器导出
     *
     * @param paramsMap
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, Object> paramsMap, HttpServletResponse response) throws IOException {
        logger.info("后台-导出计量采集表EXCEL");
        String workBookName = "计量采集表";//文件名
        HSSFWorkbook wb = null;
        OutputStream out = null;
        try {
            List<Map<String, Object>> data = meterCollectService.exportExcel(paramsMap);
            wb = CommonExcelExport.excelExport(Constants.CELL_NAME, data);
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
            logger.error("后台-导出计量采集表EXCEL异常" + e.getMessage());
        } finally {
            if (null != out) {
                out.close();
            }
        }
    }

    @RequestMapping(value = "/add/{comId}", method = RequestMethod.GET)
    public String addPage(Model model, @PathVariable("comId") String comId) {
        logger.info("计量器具新增页面");
        String code=null;
        code = meterCollectService.getGeneralCode(comId);
        if(code==null||code==""){
            code="A00001";
        }
        List<EnergyType> list  = meterCollectService.getEnergyType();
        String s = code.substring(1, code.length());
        String newCode = String.format("%0" + 5 + "d", Integer.parseInt(s) + 1);
        model.addAttribute("code", "A" + newCode);
        model.addAttribute("energy",list);
        return "/sys/mdc/add";
    }

    @RequestMapping(value = "/addvalue", method = RequestMethod.POST)
    @ResponseBody
    public String add(MeterCollect meterCollect, HttpServletRequest request) {
        logger.info("添加计量采集信息日志");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
           //给预存字段设置  默认值0 不预存
            meterCollect.setIsprestore(Byte.valueOf("0"));

            meterCollect.setId(UUIDGenerator.getUUID());
            meterCollectService.insert(meterCollect);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加计量采集成功");
        } catch (Exception e) {
            logger.error("添加计量采集异常" + e.getMessage());
            jo.put(Constants.MSG, "添加计量采集失败");
        }
        return jo.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/check/code", method = RequestMethod.POST)
    public String checkNodeCode(@RequestParam String code,
                                @RequestParam String comId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", code);
        map.put("comId", comId);
        JSONObject jo = new JSONObject();
        boolean flag = meterCollectService.checkCode(map);
        if (flag) {
            jo.put(Constants.FLAG, false);
        } else {
            jo.put(Constants.FLAG, true);
        }
        return jo.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/check/name", method = RequestMethod.POST)
    public String checkName(@RequestParam String name,
                            @RequestParam String unitId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("unitId", unitId);
        JSONObject jo = new JSONObject();
        boolean flag = meterCollectService.checkName(map);
        if (flag) {
            jo.put(Constants.FLAG, false);
        } else {
            jo.put(Constants.FLAG, true);
        }
        return jo.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/check/serialno", method = RequestMethod.POST)
    public String checkSerialSo(@RequestParam String serialNo
    ) {

        JSONObject jo = new JSONObject();
        boolean flag = meterCollectService.checkNo(serialNo);
        if (flag) {
            jo.put(Constants.FLAG, false);
        } else {
            jo.put(Constants.FLAG, true);
        }
        return jo.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/unit", method = RequestMethod.POST)
    public String getUnitList(@RequestParam String unitType,@RequestParam String comId) {

        JSONObject jo = new JSONObject();
        Map<String, Object> params = new HashMap<>();
        params.put("unitType", unitType);
        params.put("comId", comId);
        List<Map<String, Object>> list = meterCollectService.getUnitInfo(params);
        jo.put(Constants.LIST, list);
        return jo.toJSONString();
    }

    @RequestMapping(value = "/edit/{id}/{comId}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") String id,@PathVariable("comId") String comId,HttpServletRequest request) {
        logger.info("跳转修改热源页");
        try {

            MeterCollect mec = meterCollectService.selectByPrimaryKey(id);

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Constants.SESSION_KEY);
            Map<String, Object> params = new HashMap<>();
            params.put("unitType", mec.getUnitType().toString());
            params.put("comId",comId);
            List<Map<String, Object>> list = meterCollectService.getUnitInfo(params);
            List<EnergyType> listE  = meterCollectService.getEnergyType();
            model.addAttribute("energy",listE);
            model.addAttribute("mec", mec);
            model.addAttribute("uList", list);
        } catch (Exception e) {
            logger.error("跳转修改页异常" + e.getMessage());
        }
        return "/sys/mdc/edit";
    }

    /**
     * 计量仪器-预存
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/prestore/{id}", method = RequestMethod.GET)
    public String prestore(Model model, @PathVariable("id") String id) {
        logger.info("跳转计量仪器-预存页");
        try {
            MeterCollect mec = meterCollectService.selectByPrimaryKey(id);
            Map<String, Object> params = new HashMap<>();
            params.put("unitType", mec.getUnitType().toString());
            List<Map<String, Object>> list = meterCollectService.getUnitInfo(params);
            model.addAttribute("mec", mec);
            model.addAttribute("uList", list);
        } catch (Exception e) {
            logger.error("跳转计量仪器-预存页异常" + e.getMessage());
        }
        return "/sys/mdc/prestore";
    }

    /**
     * 计量仪器-换表
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/change/{id}", method = RequestMethod.GET)
    public String change(Model model, @PathVariable("id") String id) {
        logger.info("跳转计量仪器-换表页");
        try {
            MeterCollect mec = meterCollectService.selectByPrimaryKey(id);
            Map<String, Object> params = new HashMap<>();
            params.put("unitType", mec.getUnitType().toString());
            List<Map<String, Object>> list = meterCollectService.getUnitInfo(params);
            model.addAttribute("mec", mec);
            model.addAttribute("uList", list);
        } catch (Exception e) {
            logger.error("跳转计量仪器-换表页异常" + e.getMessage());
        }
        return "/sys/mdc/change";
    }

    /**
     * 计量仪器-换表-计算能耗
     *
     * @return
     */
    @RequestMapping(value = "/change", method = RequestMethod.POST)
    @ResponseBody
    public String change(RecordChange recordChange, HttpServletRequest request) {
        logger.info("计量仪器-换表-计算能耗开始");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_KEY);
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            recordChange.setCrestor(user.getId());
            int i = changeService.insert1(recordChange);
            jo.put(Constants.FLAG, i > 0);
            jo.put(Constants.MSG, "换表成功");
        } catch (Exception e) {
            logger.error("计量仪器-换表-计算能耗异常" + e.getMessage());
            jo.put(Constants.MSG, "换表失败");
        }
        return jo.toJSONString();
    }

    /**
     * 计量仪器-预存-计算能耗
     *
     * @return
     */
    @RequestMapping(value = "/prestore", method = RequestMethod.POST)
    @ResponseBody
    public String presotre(RecordPrestore recordChange, HttpServletRequest request) {
        logger.info("计量仪器-预存-计算能耗开始");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_KEY);
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            recordChange.setCrestor(user.getId());
            int i = prestoreService.insert(recordChange);
            jo.put(Constants.FLAG, i > 0);
            jo.put(Constants.MSG, "预存成功");
        } catch (Exception e) {
            logger.error("计量仪器-预存-计算能耗异常" + e.getMessage());
            jo.put(Constants.MSG, "预存失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/editvalue", method = RequestMethod.POST)
    @ResponseBody
    public String editValue(MeterCollect meterCollect, HttpServletRequest request) {
        logger.info("修改计量器具");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            int i = meterCollectService.updateByPrimaryKeySelective(meterCollect);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "修改计量器具成功");
        } catch (Exception e) {
            logger.error("修改计量器具异常" + e.getMessage());
            jo.put(Constants.MSG, "修改计量器具失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteMeter(@PathVariable("id") String id) {
        logger.info("删除计量器具");
        MeterCollect meterCollect = new MeterCollect();
        meterCollect.setIsdelete(Byte.valueOf("1"));
        meterCollect.setId(id);
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            meterCollectService.updateByPrimaryKeySelective(meterCollect);

            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除成功");
        } catch (Exception e) {
            logger.error("删除异常" + e.getMessage());
            jo.put(Constants.MSG, "删除失败");
        }
        return jo.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/check/formula", method = RequestMethod.POST)
    public String checkFormula(@RequestParam String formula
    ) {
        JSONObject jo = new JSONObject();
        List<Object> arr = new ArrayList<Object>();
        List<String> list = StringUtils.paresCodes(formula);
        for (int i = 0; i < list.size(); i++) {
            boolean flag = meterCollectService.getFormulaByIsReal(list.get(i));
            arr.add(flag);
        }
        boolean result = arr.contains(true);
        jo.put("result", result);
        return jo.toJSONString();
    }
}
