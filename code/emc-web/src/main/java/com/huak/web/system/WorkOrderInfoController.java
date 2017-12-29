package com.huak.web.system;

import com.alibaba.fastjson.JSONObject;
import com.huak.auth.UserService;
import com.huak.auth.model.Employee;
import com.huak.auth.model.Role;
import com.huak.auth.model.User;
import com.huak.common.Constants;
import com.huak.common.StringUtils;
import com.huak.common.page.Page;
import com.huak.home.workorder.WorkOrderInfoService;
import com.huak.home.workorder.WorkOrderRecordService;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import com.huak.workorder.model.WorkOrderInfo;
import com.huak.workorder.type.WorkOrderStatus;
import com.huak.workorder.vo.WorkOrderInfoDetail;
import com.huak.workorder.vo.WorkOrderInfoRel;
import com.huak.workorder.vo.WorkOrderRecordA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
* ProjectName:emc<BR>
* File name:  com.huak.web.system<BR>
* Author:  Administrator  <BR>
* Project:emc    <BR>
* Version: v 1.0      <BR>
* Date: 2017-10-26<BR>
* Description: 工单信息    <BR>
* Function List:  <BR>
*/
@Controller
@RequestMapping("/work/order/info")
public class WorkOrderInfoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private WorkOrderInfoService workOrderInfoService;
    @Resource
    private WorkOrderRecordService workOrderRecordService;
    @Resource
    private UserService userService;

    @Value("${work.order.creator}")
    private String creator;
    @Value("${work.order.monitor}")
    private String monitor;
    @Value("${work.order.takor}")
    private String takor;
    @Value("${work.order.url}")
    private String workAppUrl;


    private static final String COMPANY = "company";
    private static final String ROLE = "role";
    private static final String EMPLOYEE = "employee";

    @RequestMapping(method = RequestMethod.GET)
    public String page(HttpServletRequest request, Model model) {
        logger.info("打开工单管理页");
        HttpSession session = request.getSession();
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        Employee employee = (Employee) session.getAttribute(Constants.SESSION_EMPLOYEE_KEY);
        model.addAttribute(COMPANY, company);
        model.addAttribute(EMPLOYEE, employee);
        User user = (User)session.getAttribute(Constants.SESSION_KEY);
        Role role=userService.getRole(user.getId());
            model.addAttribute("employee_id",employee.getId());
            if(creator.equals(role.getId())){
                model.addAttribute("roleType",1);
            }else if(monitor.equals(role.getId())){
                model.addAttribute("roleType",2);
            }else if(takor.equals(role.getId())){
                model.addAttribute("roleType",3);
            }
        return "system/workorder/list";
    }

    @RequestMapping(value="/list",method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap,HttpServletRequest request, Page page) {
        logger.info("工单分页查询");
        for (String s : paramsMap.keySet()) {
            System.out.println(s+"------------------------------"+paramsMap.get(s));
        }

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Constants.SESSION_KEY);
        Employee employee = (Employee) session.getAttribute(Constants.SESSION_EMPLOYEE_KEY);
        paramsMap.put("employee_id",employee.getId());
        Role role=userService.getRole(user.getId());
        JSONObject jo = new JSONObject();
        try {
            if(creator.equals(role.getId())){
                jo.put(Constants.LIST, workOrderInfoService.selectWorkOrderInfoByCreator(paramsMap, page));
            }else if(monitor.equals(role.getId())){
                jo.put(Constants.LIST, workOrderInfoService.selectWorkOrderInfoByMonitor(paramsMap, page));
            }else if(takor.equals(role.getId())){
                jo.put(Constants.LIST, workOrderInfoService.selectWorkOrderInfoByTakor(paramsMap, page));
            }
        } catch (Exception e) {
            logger.error("工单分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/send",method = RequestMethod.GET)
    @ResponseBody
    public String sendOrder(HttpServletRequest request,Model model,
                            @RequestBody WorkOrderInfo info){
        logger.info("班长发送工单到接单员");
        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);
        WorkOrderInfo workOrderInfo = new WorkOrderInfo();
        int i =workOrderInfoService.sendABorCRecord(workOrderInfo);
        if(i>0){
            jo.put(Constants.FLAG,true);
            jo.put(Constants.MSG,"操作成功");
        }else {
            jo.put(Constants.FLAG,false);
            jo.put(Constants.MSG,"操作失败");
        }
        return jo.toJSONString();
    }


    @RequestMapping(value = "/back",method = RequestMethod.GET)
    @ResponseBody
    public String backOrder(HttpServletRequest request,Model model,
                            @RequestBody WorkOrderInfo info){
        logger.info("接单员退回工单到派单员");
        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);
        WorkOrderInfo workOrderInfo = new WorkOrderInfo();
        int i = workOrderInfoService.backARecord(workOrderInfo);
        if(i>0){
            jo.put(Constants.FLAG,true);
            jo.put(Constants.MSG,"操作成功");
        }else {
            jo.put(Constants.FLAG,false);
            jo.put(Constants.MSG,"操作失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/finish",method = RequestMethod.GET)
    @ResponseBody
    public String finishOrder(HttpServletRequest request,Model model,
                              @RequestBody WorkOrderInfo info){
        logger.info("接单员端确认并且完成");
        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);
        WorkOrderInfo workOrderInfo = new WorkOrderInfo();
        int i  = workOrderInfoService.finishCRecord(workOrderInfo);
        if(i>0){
            jo.put(Constants.FLAG,true);
            jo.put(Constants.MSG,"操作成功");
        }else {
            jo.put(Constants.FLAG,false);
            jo.put(Constants.MSG,"操作失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/confirm",method = RequestMethod.GET)
    @ResponseBody
    public String confirmOrder(HttpServletRequest request,Model model,
                               @RequestBody WorkOrderInfo info){
        logger.info("接端确认并且完成等待派单员确认完成且派单员确认完成");
        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);
        WorkOrderInfo workOrderInfo = new WorkOrderInfo();
        int i = workOrderInfoService.confirmACRecord(workOrderInfo);
        if(i>0){
            jo.put(Constants.FLAG,true);
            jo.put(Constants.MSG,"操作成功");
        }else {
            jo.put(Constants.FLAG,false);
            jo.put(Constants.MSG,"操作失败");
        }
        return jo.toJSONString();
    }




    @RequestMapping(value = "/detail/{type}/{code}",method = RequestMethod.GET)
    public String detailPage(HttpServletRequest request, Model model,@PathVariable("code") String code,@PathVariable("type") Integer type) {
        logger.info("打开工单详情页");
        //根据code查询工单信息
        WorkOrderInfoDetail detail = workOrderInfoService.getWorkInfoByCode(code);

        model.addAttribute("detail", detail);
        model.addAttribute("roleType", type);

        return "system/workorder/detail";
    }

    @RequestMapping(value = "/record/{code}", method = RequestMethod.POST)
    @ResponseBody
    public String getRecord(@PathVariable("code") String code) {
        logger.info("查询工单操作记录");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            //根据code查询工单操作记录
            List<WorkOrderRecordA> records = workOrderRecordService.selectAllRecord(code);
            jo.put(Constants.LIST,records);
            jo.put(Constants.FLAG, true);
        } catch (Exception e) {
            logger.error("查询工单操作记录异常" + e.getMessage());
            jo.put(Constants.MSG, "查询工单操作记录失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/rel/{code}", method = RequestMethod.POST)
    @ResponseBody
    public String getRel(@PathVariable("code") String code) {
        logger.info("查询工单关联记录");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            List<WorkOrderInfoRel> rels = null;
            //根据code查询关联工单信息列表
            String pCode = workOrderInfoService.selectByCode(code);
            if(!StringUtils.isEmpty(pCode)){
                rels = workOrderInfoService.selectWorkRelByCode(pCode);
            }
            jo.put(Constants.LIST, rels);
            jo.put(Constants.FLAG, true);
        } catch (Exception e) {
            logger.error("查询工单关联记录异常" + e.getMessage());
            jo.put(Constants.MSG, "查询工单关联记录失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/close", method = RequestMethod.POST)
    @ResponseBody
    public String close(WorkOrderInfo workOrderInfo, HttpServletRequest request) {
        logger.info("关闭工单");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            //查询工单当前信息
            workOrderInfo = workOrderInfoService.selectByPrimaryKey(workOrderInfo.getId());
            if(WorkOrderStatus.B212.getKey() == workOrderInfo.getStatus()){
                workOrderInfoService.closeAB(workOrderInfo);
            }else if(WorkOrderStatus.C312.getKey() == workOrderInfo.getStatus()){
                workOrderInfoService.closeABC(workOrderInfo);
            }else if(WorkOrderStatus.C321.getKey() == workOrderInfo.getStatus()){
                workOrderInfoService.closeAC(workOrderInfo);
            }else{
                throw new IllegalArgumentException("没有此流程");
            }

            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "关闭工单成功");
        } catch (Exception e) {
            logger.error("关闭工单异常" + e.getMessage());
            jo.put(Constants.MSG, "关闭工单失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    @ResponseBody
    public String confirm(WorkOrderInfo workOrderInfo, HttpServletRequest request) {
        logger.info("确认工单");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            //查询工单当前信息
            workOrderInfo = workOrderInfoService.selectByPrimaryKey(workOrderInfo.getId());
            if(WorkOrderStatus.B213.getKey() == workOrderInfo.getStatus()){
                workOrderInfoService.confirmAB(workOrderInfo);
            }else if(WorkOrderStatus.C311.getKey() == workOrderInfo.getStatus()){
                workOrderInfoService.confirmACRecord(workOrderInfo);
            }else if(WorkOrderStatus.C323.getKey() == workOrderInfo.getStatus()){
                workOrderInfoService.confirmAC(workOrderInfo);
            }else{
                throw new IllegalArgumentException("没有此流程");
            }

            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "确认工单成功");
        } catch (Exception e) {
            logger.error("确认工单异常" + e.getMessage());
            jo.put(Constants.MSG, "确认工单失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/reset/{id}",method = RequestMethod.GET)
    public String resetPage(@PathVariable("id") String id, HttpServletRequest request, Model model) {
        logger.info("打开重新派送工单页面");
        //旧工单信息
        WorkOrderInfo workOrderInfo = workOrderInfoService.selectByPrimaryKey(id);
        //员工列表
        Map<String,Object> paramsMap = new HashMap<String,Object>();
        paramsMap.put("monitor",monitor);
        paramsMap.put("receiver",takor);
        paramsMap.put("comId",workOrderInfo.getComid());
        List<Map<String,Object>> employees = workOrderInfoService.getEmployeeAndRole(paramsMap);
        //员工列表
        model.addAttribute("employees",employees);
        model.addAttribute("workOrder",workOrderInfo);
        return "system/workorder/reset";
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ResponseBody
    public String reset(WorkOrderInfo workOrderInfo, HttpServletRequest request,String roleId) {
        logger.info("重新派送工单");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            //老单
            WorkOrderInfo oldOrder = workOrderInfoService.selectByPrimaryKey(workOrderInfo.getId());
            if(WorkOrderStatus.C321.getKey() == oldOrder.getStatus()){
                workOrderInfoService.resetBackAC(oldOrder,workOrderInfo.getTakor());
            }else if(WorkOrderStatus.C323.getKey() == oldOrder.getStatus()){
                workOrderInfoService.resetFinishAC(oldOrder,workOrderInfo.getTakor());
            }else if(WorkOrderStatus.B213.getKey() == oldOrder.getStatus()){
                workOrderInfoService.resetFinishAB(oldOrder,workOrderInfo.getTakor());
            }else if(WorkOrderStatus.B212.getKey() == oldOrder.getStatus()){
                workOrderInfoService.resetBackAB(oldOrder,workOrderInfo.getTakor());
            }else if(WorkOrderStatus.C311.getKey() == oldOrder.getStatus()){
                workOrderInfoService.confirmACRecord(oldOrder);
            }else if(WorkOrderStatus.C312.getKey() == oldOrder.getStatus()){
                workOrderInfoService.confirmACRecord(oldOrder);
            }else{
                throw new IllegalArgumentException("没有此流程");
            }

            //新单
            WorkOrderInfo newOrder = new WorkOrderInfo();
            newOrder.setCode(oldOrder.getCode());//添加关联标记
            newOrder.setType(workOrderInfo.getType());
            newOrder.setName(workOrderInfo.getName());
            newOrder.setContent(workOrderInfo.getContent());
            newOrder.setStartTime(workOrderInfo.getStartTime());
            newOrder.setFinishTime(workOrderInfo.getFinishTime());
            newOrder.setCreator(oldOrder.getCreator());
            newOrder.setComid(oldOrder.getComid());
            if(monitor.equals(roleId)){//班长
                newOrder.setMonitor(workOrderInfo.getTakor());
                workOrderInfoService.saveAndSendABorC(newOrder,workAppUrl);
            }else if(takor.equals(roleId)){//接单员
                newOrder.setTakor(workOrderInfo.getTakor());
                workOrderInfoService.saveAndSendAC(newOrder,workAppUrl);
            }

            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "重新派送工单成功");
        } catch (Exception e) {
            logger.error("重新派送工单异常" + e.getMessage());
            jo.put(Constants.MSG, "重新派送工单失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String addPage1(HttpServletRequest request,Model model,
                           @RequestParam("code")  String code,
                           @RequestParam("mid")  String mid,
                           @RequestParam("reid")  String reid){
        logger.info("派单并编辑当前订单");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("monitor",monitor);
        map1.put("receiver",takor);
        map1.put("comId",company.getId());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("monitor",monitor);
        map.put("receiver",takor);
        map.put("comId",company.getId());
        if(mid!=null&&mid!=""){
            map.put("id",mid);
        }
        if(reid!=null&&reid!=""){
            map.put("id",reid);
        }
        List<Map<String,Object>> list = workOrderInfoService.getEmployee(map1);

        String listemp =workOrderInfoService.getEmployeeById(map);

        WorkOrderInfoDetail detail = workOrderInfoService.getWorkInfoByCode(code);

        model.addAttribute("list",list);
        model.addAttribute("listemp",listemp);
        model.addAttribute("detail",detail);
        return "system/workorder/edit";
    }
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addPage1(HttpServletRequest request,Model model){
        logger.info("添加工单");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("monitor",monitor);
        map.put("receiver",takor);
        map.put("comId",company.getId());
        List<Map<String,Object>> list = workOrderInfoService.getEmployee(map);
        model.addAttribute("list",list);
        return "system/workorder/add";
    }
    @RequestMapping(value = "/do",method = RequestMethod.GET)
    public String doWork(HttpServletRequest request,Model model,
                         @RequestParam("code")  String code,
                         @RequestParam("mid")  String mid,
                         @RequestParam("reid")  String reid){
        logger.info("跳转到处理订单");
        model.addAttribute("code", code);
        model.addAttribute("mid", mid);
        model.addAttribute("reid", reid);
        return "system/workorder/doorder";
    }
    @RequestMapping(value = "/tui",method = RequestMethod.GET)
    public String tuiWork(HttpServletRequest request,Model model,
                          @RequestParam("code")  String code,
                          @RequestParam("mid")  String mid,
                          @RequestParam("reid")  String reid){
        logger.info("跳转到处理订单");
        model.addAttribute("code", code);
        model.addAttribute("mid", mid);
        model.addAttribute("reid", reid);
        return "system/workorder/tuiorder";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(WorkOrderInfo workOrderInfo, HttpServletRequest request,
                      @RequestParam  String urlType) {
        logger.info("添加工单");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        String[]  ss = workOrderInfo.getTakor().split(",");
        if(urlType.equals("0")){
            try {
                HttpSession session = request.getSession();
                Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
                Employee emp = (Employee)session.getAttribute(Constants.SESSION_EMPLOYEE_KEY);
                workOrderInfo.setComid(company.getId());
                workOrderInfo.setCreator(emp.getId());
                if("1".equals(workOrderInfo.getType().toString())){
                    workOrderInfo.setStartTime(null);
                }
                if(monitor.equals(ss[1])){
                    //班长
                    workOrderInfo.setMonitor(ss[0]);
                    workOrderInfo.setTakor(null);
                }
                if(takor.equals(ss[1])){
                    //接单员
                    workOrderInfo.setTakor(ss[0]);
                    workOrderInfo.setMonitor(null);
                }
                workOrderInfoService.saveA(workOrderInfo);
                jo.put(Constants.FLAG, true);
                jo.put(Constants.MSG, "添加工单成功");
            } catch (Exception e) {
                logger.error("添加工单异常" + e.getMessage());
                jo.put(Constants.MSG, "添加工单失败");
            }
        }else if(urlType.equals("1")){
            try {
                HttpSession session = request.getSession();
                Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
                Employee emp = (Employee)session.getAttribute(Constants.SESSION_EMPLOYEE_KEY);
                workOrderInfo.setComid(company.getId());
                workOrderInfo.setCreator(emp.getId());
                if("1".equals(workOrderInfo.getType().toString())){
                    workOrderInfo.setStartTime(null);
                }

                if(monitor.equals(ss[1])){
                    //班长
                    workOrderInfo.setMonitor(ss[0]);
                    workOrderInfo.setTakor(null);
                    workOrderInfoService.saveAndSendABorC(workOrderInfo,workAppUrl);
                }
                if(takor.equals(ss[1])){
                    //接单员
                    workOrderInfo.setTakor(ss[0]);
                    workOrderInfo.setMonitor(null);
                    workOrderInfoService.saveAndSendAC(workOrderInfo,workAppUrl);
                }
                jo.put(Constants.FLAG, true);
                jo.put(Constants.MSG, "添加且发送工单成功");
            } catch (Exception e) {
                logger.error("添加且发送工单异常" + e.getMessage());
                jo.put(Constants.MSG, "添加且发送工单失败");
            }
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/edit/send", method = RequestMethod.POST)
    @ResponseBody
    public String editAndSend(WorkOrderInfo workOrderInfo, HttpServletRequest request) {
        logger.info("编辑工单并派送工单");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        String[]  ss = workOrderInfo.getTakor().split(",");

        try {
            if("1".equals(workOrderInfo.getType().toString())){
                workOrderInfo.setStartTime(null);
            }
            if(monitor.equals(ss[1])){
                //班长
                workOrderInfo.setMonitor(ss[0]);
                workOrderInfo.setTakor(null);
                workOrderInfoService.sendABorC(workOrderInfo,workAppUrl);
            }
            if(takor.equals(ss[1])){
                //接单员
                workOrderInfo.setTakor(ss[0]);
                workOrderInfo.setMonitor(null);
                workOrderInfoService.sendAC(workOrderInfo,workAppUrl);
            }

            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "派送工单成功");
        } catch (Exception e) {
            logger.error("派送工单异常" + e.getMessage());
            jo.put(Constants.MSG, "派送工单失败");
        }

        return jo.toJSONString();
    }

    @RequestMapping(value = "/received",method = RequestMethod.GET)
    @ResponseBody
    public String received(HttpServletRequest request,Model model,
                           @RequestParam("code")  String code,
                           @RequestParam("mid")  String mid,
                           @RequestParam("reid")  String reid){
        logger.info("接收当前订单");
        JSONObject jo = new JSONObject();
        WorkOrderInfo info = workOrderInfoService.selectOneByCode(code);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("monitor",monitor);
        map.put("receiver",takor);
        if(mid!=null&&mid!=""){
            map.put("id",mid);
        }
        if(reid!=null&&reid!=""){
            map.put("id",reid);
        }
        String eid =workOrderInfoService.getEmployeeById(map);
        String[]  ss = eid.split(",");
        try {
            if(monitor.equals(ss[1])){
                //班长接单
                workOrderInfoService.takingB(info);
            }
            if(takor.equals(ss[1])){
                //接单员接单
                workOrderInfoService.takingC(info);
            }
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "接收工单成功");
        } catch (Exception e) {
            logger.error("接收工单异常" + e.getMessage());
            jo.put(Constants.MSG, "接收工单失败");
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/done/order",method = RequestMethod.POST)
    @ResponseBody
    public String doingOrder(HttpServletRequest request,Model model,
                             @RequestParam("code")  String code,
                             @RequestParam("mid")  String mid,
                             @RequestParam("reid")  String reid,
                             @RequestParam("finishReason")  String finishReason) {
        logger.info("处理完成当前订单");
        JSONObject jo = new JSONObject();
        WorkOrderInfo info = workOrderInfoService.selectOneByCode(code);
        info.setFinishReason(finishReason);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("monitor", monitor);
        map.put("receiver", takor);
        if (mid != null && mid != "") {
            map.put("id", mid);
        }
        if (reid != null && reid != "") {
            map.put("id", reid);
        }
        String eid = workOrderInfoService.getEmployeeById(map);
        String[] ss = eid.split(",");
        try {
            if (monitor.equals(ss[1])) {
                //班长接单
                workOrderInfoService.finishB(info);
            }
            if (takor.equals(ss[1])) {
                //接单员接单
                workOrderInfoService.finishC(info);
            }
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "处理完成工单成功");
        } catch (Exception e) {
            logger.error("处理完成工单异常" + e.getMessage());
            jo.put(Constants.MSG, "处理完成工单失败");
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/tui/order",method = RequestMethod.POST)
    @ResponseBody
    public String tuiOrder(HttpServletRequest request,Model model,
                           @RequestParam("code")  String code,
                           @RequestParam("mid")  String mid,
                           @RequestParam("reid")  String reid,
                           @RequestParam("reason")  String reason) {
        logger.info("退当前订单");
        JSONObject jo = new JSONObject();
        WorkOrderInfo info = workOrderInfoService.selectOneByCode(code);
        info.setReason(reason);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("monitor", monitor);
        map.put("receiver", takor);
        if (mid != null && mid != "") {
            map.put("id", mid);
        }
        if (reid != null && reid != "") {
            map.put("id", reid);
        }
        String eid = workOrderInfoService.getEmployeeById(map);
        String[] ss = eid.split(",");
        try {
            if (monitor.equals(ss[1])) {
                //班长接单
                workOrderInfoService.backB(info);
            }
            if (takor.equals(ss[1])) {
                //接单员接单
                workOrderInfoService.backC(info);
            }
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "退单成功");
        } catch (Exception e) {
            logger.error("退单异常" + e.getMessage());
            jo.put(Constants.MSG, "退单失败");
        }
        return jo.toJSONString();
    }
}
