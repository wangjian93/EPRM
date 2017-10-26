package com.ivo.core;

import java.util.Date;

/**
 *@author wangjian
 *@time 2017年8月31日 - 上午10:32:19
 *@description:
 */
public class Model extends ModelAtom {
	
	private static final long serialVersionUID = 5156760158181527518L;
	private String creater = "sys";
	private String updater = "sys";
	private Date dateOfCreate = new Date();
	private Date dateOfUpdate = new Date();
	
	public Model() {}
	
	public String getCreater() {
		return creater;
	}
	public void setCreator(String creator) {
		this.creater = creator;
	}

	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	
	public Date getDateOfCreate() {
		return dateOfCreate;
	}
	public void setDateOfCreate(Date dateOfCreate) {
		this.dateOfCreate = dateOfCreate;
	}
	
	public Date getDateOfUpdate() {
		return dateOfUpdate;
	}
	public void setDateOfUpdate(Date dateOfUpdate) {
		this.dateOfUpdate = dateOfUpdate;
	}
	
}
