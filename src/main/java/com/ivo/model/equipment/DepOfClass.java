package com.ivo.model.equipment;

import com.ivo.core.Model;

/**
 *@author wangjian
 *@time 2017年8月31日 - 上午11:07:48
 *@description:
 */
public class DepOfClass{
	private int classID;
	private String className;
	private int department_fk;
	public int getClassID() {
		return classID;
	}
	public void setClassID(int classID) {
		this.classID = classID;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getDepartment_fk() {
		return department_fk;
	}
	public void setDepartment_fk(int department_fk) {
		this.department_fk = department_fk;
	}
}
