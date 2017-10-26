package com.ivo.model.equipment;

import java.util.List;

import com.ivo.core.Model;

/**
 *@author wangjian
 *@time 2017年9月5日 - 上午10:52:00
 *@description:
 */
public class CheckForm extends Model{
	private long checkFormID;
	private String trackingNumber;
	private int equipmentGroup_fk;
	private int class_fk;
	private int year;
	private int month;
	private int day;
	private float properRate;
	private List<CheckFormItem> checkFormItem;
	public List<CheckFormItem> getCheckFormItem() {
		return checkFormItem;
	}
	public void setCheckFormItem(List<CheckFormItem> checkFormItem) {
		this.checkFormItem = checkFormItem;
	}
	public long getCheckFormID() {
		return checkFormID;
	}
	public void setCheckFormID(long checkFormID) {
		this.checkFormID = checkFormID;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public int getEquipmentGroup_fk() {
		return equipmentGroup_fk;
	}
	public void setEquipmentGroup_fk(int equipmentGroup_fk) {
		this.equipmentGroup_fk = equipmentGroup_fk;
	}
	public int getClass_fk() {
		return class_fk;
	}
	public void setClass_fk(int class_fk) {
		this.class_fk = class_fk;
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
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public float getProperRate() {
		return properRate;
	}
	public void setProperRate(float properRate) {
		this.properRate = properRate;
	}
	
}
