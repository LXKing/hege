package com.huak.web.system;

import com.huak.home.workorder.WorkOrderInfoService;
import com.huak.home.workorder.WorkOrderRecordService;
import com.huak.workorder.model.WorkOrderInfo;
import com.huak.workorder.vo.WorkOrderRecordA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
@RequestMapping("/work/order/record")
public class WorkOrderRecordController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private WorkOrderInfoService workOrderInfoService;
    @Resource
    private WorkOrderRecordService workOrderRecordService;


    @RequestMapping(method = RequestMethod.GET)
    public String page(HttpServletRequest request, Model model,@RequestBody WorkOrderInfo info) {
        logger.info("打开工单管理页");

        List<WorkOrderRecordA> list  =  workOrderRecordService.selectAllRecord(info.getCode());
        model.addAttribute("list",list);
        return "system/workorder/list";
    }

}
