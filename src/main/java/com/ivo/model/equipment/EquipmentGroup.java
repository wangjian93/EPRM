package com.ivo.model.equipment;

import java.util.List;

import com.ivo.core.Model;

/**
 *@author wangjian
 *@time 2017年8月31日 - 上午11:18:39
 *@description:
 */
public class EquipmentGroup extends Model {
	private int equipmentGroupID;
	private String equipmentGroupName;
	private int class_fk;
	public int getEquipmentGroupID() {
		return equipmentGroupID;
	}
	public void setEquipmentGroupID(int equipmentGroupID) {
		this.equipmentGroupID = equipmentGroupID;
	}
	public String getEquipmentGroupName() {
		return equipmentGroupName;
	}
	public void setEquipmentGroupName(String equipmentGroupName) {
		this.equipmentGroupName = equipmentGroupName;
	}
	public int getClass_fk() {
		return class_fk;
	}
	public void setClass_fk(int class_fk) {
		this.class_fk = class_fk;
	}
}
