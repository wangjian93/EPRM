package com.ivo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ivo.model.equipment.CheckDataDetail;
import com.ivo.model.equipment.CheckForm;
import com.ivo.model.equipment.CheckFormItem;
import com.ivo.model.equipment.CheckFormMonth;
import com.ivo.model.equipment.DepOfClass;
import com.ivo.model.equipment.EquipmentGroup;

/**
 *@author wangjian
 *@time 2017年9月5日 - 上午11:09:15
 *@description:
 */
public interface ICheckService {
	public DepOfClass getDepOfClass(int classID);
	public EquipmentGroup getEuipmentGroup(int equipmentGroupID);
	
	public CheckForm getCheckForm(String trackingNumber);
	public List<CheckForm> getCheckFormMonth(int year, int month, int equipmentGroup);
	public CheckDataDetail getCheckDataDetail(int year, int month, int equipmentID);
	public List<CheckDataDetail> getCheckDataGroup(int year, int month, int equipmentGroup);
	
	public CheckFormMonth getCheckFormMonthByEquipmentGroupID(int equipmentGroupID, int year, int month);
	public List<CheckFormMonth> getCheckFormMonthByClassID(int depOfClassID, int year, int month);
	public List<CheckFormMonth> getCheckFormMonthALL(int year, int month);
	public void saveCheckFormMonth(CheckFormMonth checkFormMonth);
	public void updateCheckFormMonth(CheckFormMonth checkFormMonth);
	
	public CheckForm setCheckForm(String trackingNumber);
	
	public int saveChekForm(CheckForm checkForm);
	public void saveCheckDataDetail(CheckDataDetail checkDataDetail);
	
	public void updateCheckForm(CheckForm checkForm);
	public void updateCheckFormDetail(CheckDataDetail checkDataDetail);
	
	public void createCurrentCheck();
}
