package com.ivo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ivo.dao.equipment.ISpecDao;
import com.ivo.model.equipment.Spec;

/**
 *@author wangjian
 *@time 2017年10月12日 - 下午2:49:45
 *@description:
 */
public interface ISpecService{
	public List<Spec> getAllSpec();
	public List<Spec> getSpecByMonth(int year, int month);
	public Spec getSpecByEquipmentGroup(int year, int month, int equipmentGroup);
 	public Spec getSpec(int specID);
	public void saveSpec(Spec spec);
	public void updateSpec(Spec spec);
	public void createCurrentMonthSpec();
	public void updateCurrentMonthSpec();
}
