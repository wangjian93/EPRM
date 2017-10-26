package com.ivo.model.equipment;
/**
 *@author wangjian
 *@time 2017年10月12日 - 上午8:28:41
 *@description:
 */
public class Spec {
	private long id;
	private int year;
	private int month;
	private int equipmentGroup;
	private String equipmentGroupName;
	private int depClassID;
	private String depClassName;
	private float spec;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getEquipmentGroup() {
		return equipmentGroup;
	}
	public void setEquipmentGroup(int equipmentGroup) {
		this.equipmentGroup = equipmentGroup;
	}
	public float getSpec() {
		return spec;
	}
	public void setSpec(float spec) {
		this.spec = spec;
	}
	public String getEquipmentGroupName() {
		return equipmentGroupName;
	}
	public void setEquipmentGroupName(String equipmentGroupName) {
		this.equipmentGroupName = equipmentGroupName;
	}
	public int getDepClassID() {
		return depClassID;
	}
	public void setDepClassID(int depClassID) {
		this.depClassID = depClassID;
	}
	public String getDepClassName() {
		return depClassName;
	}
	public void setDepClassName(String depClassName) {
		this.depClassName = depClassName;
	}
	
}
