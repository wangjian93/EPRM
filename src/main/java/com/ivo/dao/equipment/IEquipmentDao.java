package com.ivo.dao.equipment;

import java.util.List;

import com.ivo.model.equipment.Equipment;

/**
 *@author wangjian
 *@time 2017年8月31日 - 上午11:27:17
 *@description:
 */
public interface IEquipmentDao {
	public Equipment getByID(int id);
	public List<Equipment> getAll();
	public List<Equipment> getByEquipmentGroup(int equipmentGroup);
	public List<Equipment> getByName(String equipmentName);
	public void saveEquipment(Equipment equipment);
	public void updateEquipment(Equipment equipment);
	public void deleteEquipment(Equipment equipment);
}
