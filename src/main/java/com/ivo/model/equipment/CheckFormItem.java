package com.ivo.model.equipment;
/**
 *@author wangjian
 *@time 2017年9月5日 - 上午10:52:28
 *@description:
 */
public class CheckFormItem {
	private int equipmentID;
	private String checkResult;
	private String equipmentName;
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public int getEquipmentID() {
		return equipmentID;
	}
	public void setEquipmentID(int equipmentID) {
		this.equipmentID = equipmentID;
	}
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	
}
