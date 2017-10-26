package com.ivo.model.hr;
/**
 *@author wangjian
 *@time 2017年8月30日 - 上午9:33:50
 *@description:
 */
public class Employee {
	private String employee_ID;
	private String employeeName;
	private String employeeName_EN;
	private String department_FK;
	private String emailAddress;
	private String password;
	private String gender_FK;
	private String visibleFlag;
	private short validFlag;
	public String getEmployee_ID() {
		return employee_ID;
	}
	public void setEmployee_ID(String employee_ID) {
		this.employee_ID = employee_ID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeName_EN() {
		return employeeName_EN;
	}
	public void setEmployeeName_EN(String employeeName_EN) {
		this.employeeName_EN = employeeName_EN;
	}
	public String getDepartment_FK() {
		return department_FK;
	}
	public void setDepartment_FK(String department_FK) {
		this.department_FK = department_FK;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender_FK() {
		return gender_FK;
	}
	public void setGender_FK(String gender_FK) {
		this.gender_FK = gender_FK;
	}
	public String getVisibleFlag() {
		return visibleFlag;
	}
	public void setVisibleFlag(String visibleFlag) {
		this.visibleFlag = visibleFlag;
	}
	public short getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(short validFlag) {
		this.validFlag = validFlag;
	}
	
}
