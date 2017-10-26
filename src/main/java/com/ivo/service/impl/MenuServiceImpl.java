package com.ivo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ivo.dao.equipment.IDepOfClassDao;
import com.ivo.dao.equipment.IEquipmentDao;
import com.ivo.dao.equipment.IEquipmentGroupDao;
import com.ivo.dao.hr.IEmployeeDao;
import com.ivo.model.equipment.DepOfClass;
import com.ivo.model.equipment.Equipment;
import com.ivo.model.equipment.EquipmentGroup;
import com.ivo.service.IMenuService;

/**
 *@author wangjian
 *@time 2017年8月31日 - 下午1:26:39
 *@description:
 */
@Service("menuService")
public class MenuServiceImpl implements IMenuService{
	@Resource  
	   private IDepOfClassDao depOfClassDao;
	@Resource  
	   private IEquipmentDao equipmentDao;
	@Resource  
	   private IEquipmentGroupDao equipmentGroup;

	@Override
	public DepOfClass getDepOfClass(int id) {
		return depOfClassDao.getByID(id);
	}

	@Override
	public List<DepOfClass> getDepOfClass() {
		return depOfClassDao.getAll();
	}

	@Override
	public EquipmentGroup getEuipmentGroup(int id) {
		return equipmentGroup.getByID(id);
	}

	@Override
	public List<EquipmentGroup> getEuipmentGroup() {
		return equipmentGroup.getAll();
	}

	@Override
	public List<EquipmentGroup> getEuipmentGroupByCalss(int classID) {
		return equipmentGroup.getByClass(classID);
	}

	@Override
	public List<EquipmentGroup> getEuipmentGroup(String equipmentGroupName) {
		return equipmentGroup.getByName(equipmentGroupName);
	}

	@Override
	public Equipment getEquipment(int id) {
		return equipmentDao.getByID(id);
	}

	@Override
	public List<Equipment> getEquipment() {
		return equipmentDao.getAll();
	}

	@Override
	public List<Equipment> getEquipmentByGroup(int equipmentGroup) {
		return equipmentDao.getByEquipmentGroup(equipmentGroup);
	}

	@Override
	public List<Equipment> getEquipment(String equipmentName) {
		return equipmentDao.getByName(equipmentName);
	}
	
}
