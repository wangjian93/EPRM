package com.ivo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ivo.dao.equipment.ICheckDataDetailDao;
import com.ivo.dao.equipment.ICheckFormDao;
import com.ivo.dao.equipment.ICheckFormMonthDao;
import com.ivo.dao.equipment.IDepOfClassDao;
import com.ivo.dao.equipment.IEquipmentDao;
import com.ivo.dao.equipment.IEquipmentGroupDao;
import com.ivo.model.equipment.CheckDataDetail;
import com.ivo.model.equipment.CheckForm;
import com.ivo.model.equipment.CheckFormItem;
import com.ivo.model.equipment.CheckFormMonth;
import com.ivo.model.equipment.DepOfClass;
import com.ivo.model.equipment.Equipment;
import com.ivo.model.equipment.EquipmentGroup;
import com.ivo.service.ICheckService;
import com.ivo.util.CurrentUtil;

/**
 *@author wangjian
 *@time 2017年9月5日 - 上午11:32:26
 *@description:
 */
@Service("checkService")
public class CheckServiceImpl implements ICheckService{
	@Resource
	private ICheckFormDao checkFormDao;
	@Resource 
	private ICheckDataDetailDao checkDataDetailDao;
	@Resource
	private IEquipmentGroupDao equipmentGroupDao;
	@Resource
	private IDepOfClassDao depOfClassDao;
	@Resource
	private IEquipmentDao equipmentDao;
	@Resource
	private ICheckFormMonthDao checkFormMonthDao;
	
	@Override
	public CheckForm getCheckForm(String trackingNumber) {
		return checkFormDao.getCheckForm(trackingNumber);
	}

	@Override
	public CheckDataDetail getCheckDataDetail(int year, int month,
			int equipmentID) {
		return checkDataDetailDao.getCheckData(year, month, equipmentID);
	}

	@Override
	public List<CheckDataDetail> getCheckDataGroup(int year, int month,
			int equipmentGroup) {	
		return checkDataDetailDao.getCheckDataGroup(year, month, equipmentGroup);
	}

	@Override
	public CheckForm setCheckForm(String trackingNumber) {
		CheckForm checkForm = getCheckForm(trackingNumber);
		if(checkForm!=null){
			List<CheckDataDetail> checkDataDetail = getCheckDataGroup(checkForm.getYear(),
					checkForm.getMonth(), checkForm.getEquipmentGroup_fk());
			List<CheckFormItem> checkFormItem = new ArrayList<CheckFormItem>();
			for(CheckDataDetail d : checkDataDetail){
				CheckFormItem checkItem = new CheckFormItem();
				checkItem.setEquipmentID(d.getEquipmentID_fk());
				checkItem.setEquipmentName(d.getEquipmentName());
				checkItem.setCheckResult(d.getCurrentDay(checkForm.getDay()));
				checkFormItem.add(checkItem);
			}
			checkForm.setCheckFormItem(checkFormItem);
			return checkForm;
		}else{
			return null;
		}
	}
	
	@Override
	public int saveChekForm(CheckForm checkForm) {
		return checkFormDao.saveCheckForm(checkForm);
	}

	@Override
	public void saveCheckDataDetail(CheckDataDetail checkDataDetail) {
		checkDataDetailDao.saveCheckDataDetail(checkDataDetail);
	}

	@Override
	public void updateCheckForm(CheckForm checkForm) {
		checkFormDao.updateCheckForm(checkForm);
	}

	@Override
	public void updateCheckFormDetail(CheckDataDetail checkDataDetail) {
		checkDataDetailDao.updateCheckDataDetail(checkDataDetail);
	}
	
	

	@Override
	public void createCurrentCheck() {
		int year = CurrentUtil.CurrentYear();
		int month = CurrentUtil.CurrentMonth();
		int day = CurrentUtil.CurrentDay();
		List<DepOfClass> depOfClassList = depOfClassDao.getAll();
		for(DepOfClass depOfClass : depOfClassList){
			List<EquipmentGroup> equipmentGroupList = equipmentGroupDao.getByClass(depOfClass.getClassID());
			for(EquipmentGroup equipmentGroup : equipmentGroupList){
				String trackingNumber = CurrentUtil.CurrentTracking(equipmentGroup.getEquipmentGroupID());
				CheckForm checkForm = checkFormDao.getCheckForm(trackingNumber);
				/**前一天的CheckFrom**/
				String perTrackingNumber = CurrentUtil.CurrentPerTracking(equipmentGroup.getEquipmentGroupID());
				System.out.println(perTrackingNumber);
				CheckForm pertheckForm = checkFormDao.getCheckForm(perTrackingNumber);
				if(checkForm==null){
					checkForm = new CheckForm();
					checkForm.setTrackingNumber(trackingNumber);
					checkForm.setClass_fk(depOfClass.getClassID());
					checkForm.setEquipmentGroup_fk(equipmentGroup.getEquipmentGroupID());
					checkForm.setYear(year);
					checkForm.setMonth(month);
					checkForm.setDay(day);
					checkForm.setUpdater("sys");
					checkForm.setCreator("sys");
					checkForm.setDateOfCreate(new Date());
					checkForm.setDateOfUpdate(new Date());
					/**设置妥善率和前一天的相同**/
					if(pertheckForm!=null)
						checkForm.setProperRate(pertheckForm.getProperRate());
					checkFormDao.saveCheckForm(checkForm);
					
					/**同步checkFormmoNTH**/
					CheckFormMonth checkFormMonth = checkFormMonthDao.getCheckFormMonthByEquipmentGroupID(
							checkForm.getEquipmentGroup_fk(), checkForm.getYear(), checkForm.getMonth());
					if(checkFormMonth==null){
						checkFormMonth = new CheckFormMonth();
						checkFormMonth.setDepOfclassID(checkForm.getClass_fk());
						checkFormMonth.setEquipmentGroupID(checkForm.getEquipmentGroup_fk());
						checkFormMonth.setYear(checkForm.getYear());
						checkFormMonth.setMonth(checkForm.getMonth());
						checkFormMonth.SetCurrentDay(checkForm.getDay(), checkForm.getProperRate());
						checkFormMonthDao.saveCheckFormMonth(checkFormMonth);
					}else{
						checkFormMonth.SetCurrentDay(checkForm.getDay(), checkForm.getProperRate());
						checkFormMonthDao.updateCheckFormMonth(checkFormMonth);
					}
					
					/**同步CheckDataDetail**/
					List<Equipment> equipmentList = equipmentDao.getByEquipmentGroup(equipmentGroup.getEquipmentGroupID());
					for(Equipment equipment : equipmentList){
						CheckDataDetail checkDataDetail = checkDataDetailDao.getCheckData(year, month, equipment.getEquipmentID());
						if(checkDataDetail==null){
							int yearPer = CurrentUtil.CurrentPerYear();
							int monthPer = CurrentUtil.CurrentPerMonth();
							int dayPer = CurrentUtil.CurrentPerDay();
							CheckDataDetail perCheckDataDetail = checkDataDetailDao.getCheckData(yearPer, monthPer, equipment.getEquipmentID());
							
							checkDataDetail = new CheckDataDetail();
							checkDataDetail.setYear(year);
							checkDataDetail.setMonth(month);
							checkDataDetail.setEquipmentGroup_fk(equipmentGroup.getEquipmentGroupID());
							checkDataDetail.setEquipmentID_fk(equipment.getEquipmentID());
							if(perCheckDataDetail!=null)
								checkDataDetail.SetCurrentDay(day, perCheckDataDetail.getCurrentDay(dayPer));
							checkDataDetailDao.saveCheckDataDetail(checkDataDetail);
						}else{
							int yearPer = CurrentUtil.CurrentPerYear();
							int monthPer = CurrentUtil.CurrentPerMonth();
							int dayPer = CurrentUtil.CurrentPerDay();
							CheckDataDetail perCheckDataDetail = checkDataDetailDao.getCheckData(yearPer, monthPer, equipment.getEquipmentID());
							checkDataDetail.getCurrentDay(dayPer);
							if(perCheckDataDetail!=null)
							checkDataDetail.SetCurrentDay(day, perCheckDataDetail.getCurrentDay(dayPer));
							checkDataDetailDao.updateCheckDataDetail(checkDataDetail);
						}
					}
					
					
				}else{
					
					List<Equipment> equipmentList = equipmentDao.getByEquipmentGroup(equipmentGroup.getEquipmentGroupID());
					for(Equipment equipment : equipmentList){
						CheckDataDetail checkDataDetail = checkDataDetailDao.getCheckData(year, month, equipment.getEquipmentID());
						if(checkDataDetail==null){
							checkDataDetail = new CheckDataDetail();
							checkDataDetail.setYear(year);
							checkDataDetail.setMonth(month);
							checkDataDetail.setEquipmentGroup_fk(equipmentGroup.getEquipmentGroupID());
							checkDataDetail.setEquipmentID_fk(equipment.getEquipmentID());
							checkDataDetailDao.saveCheckDataDetail(checkDataDetail);
						}else{
							int yearPer = CurrentUtil.CurrentPerYear();
							int monthPer = CurrentUtil.CurrentPerMonth();
							int dayPer = CurrentUtil.CurrentPerDay();
							CheckDataDetail perCheckDataDetail = checkDataDetailDao.getCheckData(yearPer, monthPer, equipment.getEquipmentID());
							checkDataDetail.getCurrentDay(dayPer);
							if(perCheckDataDetail!=null) {
								checkDataDetail.SetCurrentDay(day, perCheckDataDetail.getCurrentDay(dayPer));
							}
							checkDataDetailDao.updateCheckDataDetail(checkDataDetail);
						}
					}
				}
			}
		}		
	}

	@Override
	public DepOfClass getDepOfClass(int classID) {
		return depOfClassDao.getByID(classID);
	}

	@Override
	public EquipmentGroup getEuipmentGroup(int equipmentGroupID) {
		return equipmentGroupDao.getByID(equipmentGroupID);
	}

	@Override
	public List<CheckForm> getCheckFormMonth(int year, int month,
			int equipmentGroup) {
		return checkFormDao.getCheckFormMonth(year, month, equipmentGroup);
	}

	@Override
	public CheckFormMonth getCheckFormMonthByEquipmentGroupID(
			int equipmentGroupID, int year, int month) {
		return checkFormMonthDao.getCheckFormMonthByEquipmentGroupID(equipmentGroupID, year, month);
	}

	@Override
	public void saveCheckFormMonth(CheckFormMonth checkFormMonth) {
		checkFormMonthDao.saveCheckFormMonth(checkFormMonth);
	}

	@Override
	public void updateCheckFormMonth(CheckFormMonth checkFormMonth) {
		checkFormMonthDao.updateCheckFormMonth(checkFormMonth);
	}

	@Override
	public List<CheckFormMonth> getCheckFormMonthByClassID(int depOfclassID,
			int year, int month) {
		return checkFormMonthDao.getCheckFormMonthByClassID(depOfclassID, year, month);
	}

	@Override
	public List<CheckFormMonth> getCheckFormMonthALL(int year, int month) {
		return checkFormMonthDao.getCheckFormMonthALL(year, month);
	} 
	
}
