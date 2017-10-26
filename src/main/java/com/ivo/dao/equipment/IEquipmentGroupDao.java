package com.ivo.dao.equipment;

import java.util.List;

import com.ivo.model.equipment.Equipment;
import com.ivo.model.equipment.EquipmentGroup;

/**
 *@author wangjian
 *@time 2017年8月31日 - 上午11:27:48
 *@description:
 */
public interface IEquipmentGroupDao {
	public EquipmentGroup getByID(int id);
	public List<EquipmentGroup> getAll();
	public List<EquipmentGroup> getByClass(int classID);
	public List<EquipmentGroup> getByName(String equipmentGroupName);
	
	public void saveEquipmentGroup(EquipmentGroup equipmentGroup);
	public void updateEquipmentGroup(EquipmentGroup equipmentGroup);
	public void deleteEquipmentGroup(EquipmentGroup equipmentGroup);
}
