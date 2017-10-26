package com.ivo.dao.equipment;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ivo.model.equipment.CheckDataDetail;

/**
 *@author wangjian
 *@time 2017年9月7日 - 下午2:45:01
 *@description:
 */
public interface ICheckDataDetailDao {
	public CheckDataDetail getCheckData(@Param("year")int year, @Param("month")int month, @Param("equipmentID")int equipmentID);
	public List<CheckDataDetail> getCheckDataGroup(@Param("year")int year, @Param("month")int month, @Param("equipmentGroup")int equipmentGroup);
	public void saveCheckDataDetail(CheckDataDetail checkDataDetail);
	public void updateCheckDataDetail(CheckDataDetail checkDataDetail);
}
