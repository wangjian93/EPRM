package com.ivo.model.hr;
/**
 *@author wangjian
 *@time 2017年8月30日 - 上午9:32:32
 *@description:
 */
public class Department {
	private String department_ID;
	private String deptName;
	private String parent_FK;
	private String srcDept_FK;
	private String sucDept_FK;
	private String deptHead_FK;
	private String deptName_EN;
	private String deptName_S;
	private int deptLevel;
	private String deptPath;
	private String costCenter_FK;
	private int transparentFlag;
	private int collapseFlag;
	public String getDepartment_ID() {
		return department_ID;
	}
	public void setDepartment_ID(String department_ID) {
		this.department_ID = department_ID;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getParent_FK() {
		return parent_FK;
	}
	public void setParent_FK(String parent_FK) {
		this.parent_FK = parent_FK;
	}
	public String getSrcDept_FK() {
		return srcDept_FK;
	}
	public void setSrcDept_FK(String srcDept_FK) {
		this.srcDept_FK = srcDept_FK;
	}
	public String getSucDept_FK() {
		return sucDept_FK;
	}
	public void setSucDept_FK(String sucDept_FK) {
		this.sucDept_FK = sucDept_FK;
	}
	public String getDeptHead_FK() {
		return deptHead_FK;
	}
	public void setDeptHead_FK(String deptHead_FK) {
		this.deptHead_FK = deptHead_FK;
	}
	public String getDeptName_EN() {
		return deptName_EN;
	}
	public void setDeptName_EN(String deptName_EN) {
		this.deptName_EN = deptName_EN;
	}
	public String getDeptName_S() {
		return deptName_S;
	}
	public void setDeptName_S(String deptName_S) {
		this.deptName_S = deptName_S;
	}
	public int getDeptLevel() {
		return deptLevel;
	}
	public void setDeptLevel(int deptLevel) {
		this.deptLevel = deptLevel;
	}
	public String getDeptPath() {
		return deptPath;
	}
	public void setDeptPath(String deptPath) {
		this.deptPath = deptPath;
	}
	public String getCostCenter_FK() {
		return costCenter_FK;
	}
	public void setCostCenter_FK(String costCenter_FK) {
		this.costCenter_FK = costCenter_FK;
	}
	public int getTransparentFlag() {
		return transparentFlag;
	}
	public void setTransparentFlag(int transparentFlag) {
		this.transparentFlag = transparentFlag;
	}
	public int getCollapseFlag() {
		return collapseFlag;
	}
	public void setCollapseFlag(int collapseFlag) {
		this.collapseFlag = collapseFlag;
	}
}
