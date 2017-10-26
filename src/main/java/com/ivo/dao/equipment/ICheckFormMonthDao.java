package com.ivo.dao.equipment;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ivo.model.equipment.CheckFormMonth;

/**
 *@author wangjian
 *@time 2017年9月21日 - 下午1:35:09
 *@description:
 */
public interface ICheckFormMonthDao {
	public CheckFormMonth getCheckFormMonthByEquipmentGroupID(@Param("equipmentGroupID")int equipmentGroupID, @Param("year")int year, @Param("month")int month);
	public List<CheckFormMonth> getCheckFormMonthByClassID(@Param("depOfClassID")int depOfClassID, @Param("year")int year, @Param("month")int month);
	public List<CheckFormMonth> getCheckFormMonthALL(@Param("year")int year, @Param("month")int month);
	public void saveCheckFormMonth(CheckFormMonth checkFormMonth);
	public void updateCheckFormMonth(CheckFormMonth checkFormMonth);
}
