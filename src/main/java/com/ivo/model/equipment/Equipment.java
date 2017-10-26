package com.ivo.model.equipment;

import com.ivo.core.Model;

/**
 *@author wangjian
 *@time 2017年8月31日 - 上午11:21:19
 *@description:
 */
public class Equipment extends Model {
	private int equipmentID;
	private String equipmentName;
	private int equipmentGroup_fk;
	public int getEquipmentID() {
		return equipmentID;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public int getEquipmentGroup_fk() {
		return equipmentGroup_fk;
	}
	public void setEquipmentGroup_fk(int equipmentGroup_fk) {
		this.equipmentGroup_fk = equipmentGroup_fk;
	}
	public void setEquipmentID(int equipmentID) {
		this.equipmentID = equipmentID;
	}
	
}
