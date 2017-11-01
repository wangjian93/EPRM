package com.ivo.dao.equipment;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ivo.model.equipment.Spec;

/**
 *@author wangjian
 *@time 2017年10月12日 - 下午2:23:28
 *@description:
 */
public interface ISpecDao {
	public List<Spec> getAllSpec();
	public List<Spec> getSpecByMonth(@Param("year")int year, @Param("month")int month);
	public Spec getSpecByEquipmentGroup(@Param("year")int year, @Param("month")int month, @Param("equipmentGroup")int equipmentGroup);
 	public Spec getSpec(int specID);
	public void saveSpec(Spec spec);
	public void updateSpec(Spec spec);
	
}
