package com.ivo.service;

import java.util.List;

import com.ivo.model.equipment.DepOfClass;
import com.ivo.model.equipment.Equipment;
import com.ivo.model.equipment.EquipmentGroup;

/**
 *@author wangjian
 *@time 2017年10月22日 - 下午1:26:08
 *@description:
 */
public interface IEquipmentManageService {
	public List<Equipment> getAllEquipment();
	public List<Equipment> getEquipmentByGroup(int equipemtnGroupID);
	public Equipment getEquipment(int equipmentID);
	public void saveEquipment(Equipment equpment);
	public void updateEquipment(Equipment equipment);
	public void deleteEquipment(Equipment equipment);
	
	public List<EquipmentGroup> getAllEquipmentGroup();
	public List<EquipmentGroup> getEquipmentGroupList(int depClassID);
	public EquipmentGroup getEquipmentGroup(int equipmentGroupID);
	public void saveEquipmentGroup(EquipmentGroup equipmentGroup);
	public void uqdateEquipmentGroup(EquipmentGroup equipmentGroup);
	public void deleteEqiupmentGroup(EquipmentGroup equipmentGroup);
	
	public List<DepOfClass> getAllDepClass();
	public DepOfClass getDepClass(int depClassID);
}
