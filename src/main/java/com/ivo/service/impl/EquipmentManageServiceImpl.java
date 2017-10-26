package com.ivo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ivo.dao.equipment.IDepOfClassDao;
import com.ivo.dao.equipment.IEquipmentDao;
import com.ivo.dao.equipment.IEquipmentGroupDao;
import com.ivo.model.equipment.DepOfClass;
import com.ivo.model.equipment.Equipment;
import com.ivo.model.equipment.EquipmentGroup;
import com.ivo.model.hr.Employee;
import com.ivo.service.IEmployeeService;
import com.ivo.service.IEquipmentManageService;

/**
 *@author wangjian
 *@time 2017年10月22日 - 下午1:40:32
 *@description:
 */
@Service("equipmentService")
public class EquipmentManageServiceImpl implements IEquipmentManageService {
	@Resource  
	   private IEquipmentDao equipmentDao;
	@Resource  
	   private IEquipmentGroupDao equipmentGroupDao;
	@Resource
		private IDepOfClassDao depClassDao;
	
	@Override
	public List<Equipment> getAllEquipment() {
		return equipmentDao.getAll();
	}

	@Override
	public List<Equipment> getEquipmentByGroup(int equipemtnGroupID) {
		return equipmentDao.getByEquipmentGroup(equipemtnGroupID);
	}

	@Override
	public Equipment getEquipment(int equipmentID) {
		return equipmentDao.getByID(equipmentID);
	}

	@Override
	public void saveEquipment(Equipment equipment) {
		equipmentDao.saveEquipment(equipment);
	}

	@Override
	public void updateEquipment(Equipment equipment) {
		equipmentDao.updateEquipment(equipment);
	}

	@Override
	public void deleteEquipment(Equipment equipment) {
		equipmentDao.deleteEquipment(equipment);
	}

	@Override
	public List<EquipmentGroup> getAllEquipmentGroup() {
		return equipmentGroupDao.getAll();
	}

	@Override
	public List<EquipmentGroup> getEquipmentGroupList(int depClassID) {
		return equipmentGroupDao.getByClass(depClassID);
	}

	@Override
	public EquipmentGroup getEquipmentGroup(int equipmentGroupID) {
		return equipmentGroupDao.getByID(equipmentGroupID);
	}

	@Override
	public void saveEquipmentGroup(EquipmentGroup equipmentGroup) {
		equipmentGroupDao.saveEquipmentGroup(equipmentGroup);
	}

	@Override
	public void uqdateEquipmentGroup(EquipmentGroup equipmentGroup) {
		equipmentGroupDao.updateEquipmentGroup(equipmentGroup);
	}

	@Override
	public void deleteEqiupmentGroup(EquipmentGroup equipmentGroup) {
		equipmentGroupDao.deleteEquipmentGroup(equipmentGroup);
	}

	@Override
	public List<DepOfClass> getAllDepClass() {
		return depClassDao.getAll();
	}

	@Override
	public DepOfClass getDepClass(int depClassID) {
		return depClassDao.getByID(depClassID);
	}

}
