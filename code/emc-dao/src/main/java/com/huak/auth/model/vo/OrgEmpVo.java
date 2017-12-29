package com.huak.auth.model.vo;

import java.io.Serializable;

public class OrgEmpVo implements Serializable{

    private static final long serialVersionUID = 2382154610287694928L;
    private String orgId;
	private String empId;
	private String empName;
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
}
