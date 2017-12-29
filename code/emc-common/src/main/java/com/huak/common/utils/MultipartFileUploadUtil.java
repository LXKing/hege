package com.huak.common.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:eccp<BR>
 * File name:  com.huak.common.utils<BR>
 * Author:  Bin  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/2/9<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class MultipartFileUploadUtil {
    /**
     * 在HttpServletRequest中获取分段上传文件请求的信息
     *
     * @param request
     * @return
     */
    public static MultipartFileParam parse(HttpServletRequest request) throws Exception {
        MultipartFileParam param = new MultipartFileParam();
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        param.setMultipart(isMultipart);
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 得到所有的表单域，它们目前都被当作FileItem
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                if (fileItem.getFieldName().equals("id")) {
                    param.setId(fileItem.getString());
                } else if (fileItem.getFieldName().equals("name")) {
                    param.setFileName(new String(fileItem.getString("UTF-8")));
                } else if (fileItem.getFieldName().equals("chunks")) {
                    param.setChunks(NumberUtils.toInt(fileItem.getString("UTF-8")));
                } else if (fileItem.getFieldName().equals("chunk")) {
                    param.setChunk(NumberUtils.toInt(fileItem.getString("UTF-8")));
                } else if (fileItem.getFieldName().equals("file")) {
                    param.setFileItem(fileItem);
                    param.setSize(fileItem.getSize());
                } else {
                    param.getParam().put(fileItem.getFieldName(), fileItem.getString());
                }
            }
        }
        return param;
    }
}
