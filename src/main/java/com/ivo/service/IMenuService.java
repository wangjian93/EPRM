package com.ivo.service;

import java.util.List;

import com.ivo.model.equipment.DepOfClass;
import com.ivo.model.equipment.Equipment;
import com.ivo.model.equipment.EquipmentGroup;

/**
 *@author wangjian
 *@time 2017年8月31日 - 下午1:25:44
 *@description:
 */
public interface IMenuService {
	public DepOfClass getDepOfClass(int id);
	public List<DepOfClass> getDepOfClass();
	public EquipmentGroup getEuipmentGroup(int id);
	public List<EquipmentGroup> getEuipmentGroup();
	public List<EquipmentGroup> getEuipmentGroupByCalss(int classID);
	public List<EquipmentGroup> getEuipmentGroup(String equipmentGroupName);
	public Equipment getEquipment(int id);
	public List<Equipment> getEquipment();
	public List<Equipment> getEquipmentByGroup(int equipmentGroup);
	public List<Equipment> getEquipment(String equipmentName);
}
