package com.ivo.model.abnormalRecord;

import java.util.Date;

import com.ivo.core.Model;

/**
 *@author wangjian
 *@time 2017年9月14日 - 上午9:05:19
 *@description:设备异常记录
 */
public class Abnormal extends Model {
	private long id;
	private int year;
	private int month;
	private int day;
	private int equipmentGroup_fk;
	private int equipmentID_fk;
	private String checkForm_fk;
	private String equipmentName;
	private String dates;
	private String sipecification;
	private String solutions;
	private String expectedTime;
	private String actualTime;
	private String ifCompleted;
	private String memo;
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
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getEquipmentGroup_fk() {
		return equipmentGroup_fk;
	}
	public void setEquipmentGroup_fk(int equipmentGroup_fk) {
		this.equipmentGroup_fk = equipmentGroup_fk;
	}
	public int getEquipmentID_fk() {
		return equipmentID_fk;
	}
	public void setEquipmentID_fk(int equipmentID_fk) {
		this.equipmentID_fk = equipmentID_fk;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public String getSipecification() {
		return sipecification;
	}
	public void setSipecification(String sipecification) {
		this.sipecification = sipecification;
	}
	public String getSolutions() {
		return solutions;
	}
	public void setSolutions(String solutions) {
		this.solutions = solutions;
	}
	public String getExpectedTime() {
		return expectedTime;
	}
	public void setExpectedTime(String expectedTime) {
		this.expectedTime = expectedTime;
	}
	public String getActualTime() {
		return actualTime;
	}
	public void setActualTime(String actualTime) {
		this.actualTime = actualTime;
	}
	public String getIfCompleted() {
		return ifCompleted;
	}
	public void setIfCompleted(String ifCompleted) {
		this.ifCompleted = ifCompleted;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getCheckForm_fk() {
		return checkForm_fk;
	}
	public void setCheckForm_fk(String checkForm_fk) {
		this.checkForm_fk = checkForm_fk;
	}
	
}