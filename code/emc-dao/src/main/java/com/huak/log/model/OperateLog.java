package com.huak.log.model;

import java.io.Serializable;

public class OperateLog implements Serializable{
    private static final long serialVersionUID = 6534831654677190775L;
    /**
     *主键
     * @preserve 声明此方法不被JOC混淆
     */
    private String id;

    /**
     *操作名称
     * @preserve 声明此方法不被JOC混淆
     */
    private String optName;

    /**
     *操作类型 0：增 1：删 2：改 3：查
     * @preserve 声明此方法不被JOC混淆
     */
    private String optType;

    /**
     *操作模块名称
     * @preserve 声明此方法不被JOC混淆
     */
    private String optKey;

    /**
     *操作类名称
     * @preserve 声明此方法不被JOC混淆
     */
    private String className;

    /**
     *操作方法名称
     * @preserve 声明此方法不被JOC混淆
     */
    private String methodName;

    /**
     *客户机ip
     * @preserve 声明此方法不被JOC混淆
     */
    private String remoteIp;

    /**
     *客户机名称
     * @preserve 声明此方法不被JOC混淆
     */
    private String remoteName;

    /**
     *客户机端口
     * @preserve 声明此方法不被JOC混淆
     */
    private String remotePort;

    /**
     *请求资源
     * @preserve 声明此方法不被JOC混淆
     */
    private String reqUri;

    /**
     *请求地址
     * @preserve 声明此方法不被JOC混淆
     */
    private String reqUrl;

    /**
     *创建时间
     * @preserve 声明此方法不被JOC混淆
     */
    private String createTime;

    /**
     *创建者
     * @preserve 声明此方法不被JOC混淆
     */
    private String creator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName == null ? null : optName.trim();
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType == null ? null : optType.trim();
    }

    public String getOptKey() {
        return optKey;
    }

    public void setOptKey(String optKey) {
        this.optKey = optKey == null ? null : optKey.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp == null ? null : remoteIp.trim();
    }

    public String getRemoteName() {
        return remoteName;
    }

    public void setRemoteName(String remoteName) {
        this.remoteName = remoteName == null ? null : remoteName.trim();
    }

    public String getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(String remotePort) {
        this.remotePort = remotePort == null ? null : remotePort.trim();
    }

    public String getReqUri() {
        return reqUri;
    }

    public void setReqUri(String reqUri) {
        this.reqUri = reqUri == null ? null : reqUri.trim();
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl == null ? null : reqUrl.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }
}