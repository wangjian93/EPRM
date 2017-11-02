package com.ivo.dao.abnormalRecord;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ivo.model.abnormalRecord.Abnormal;

/**
 *@author wangjian
 *@time 2017年9月14日 - 上午9:14:53
 *@description:
 */
public interface IAbnormalDao {
	public Abnormal getAbnotmalByID(@Param("abnormalID")int abnormalID);
	public List<Abnormal> getAbnormalMonth(@Param("year")int year, @Param("month")int month, @Param("equipmentGroup")int equipmentGroup);
	public List<Abnormal> getAbnormalDay(@Param("year")int year, @Param("month")int month, @Param("day")int day, @Param("equipmentGroup")int equipmentGroup);
	public List<Abnormal> getAbnormalEquipmentMonth(@Param("year")int year, @Param("month")int month, @Param("equipmentID")int equipmentID);
	public List<Abnormal> getAbnormalEquipmentDay(@Param("year")int year,  @Param("month")int month, @Param("day")int day, @Param("equipmentID")int equipmentID);
	public void saveAbnormal(Abnormal abnormla);
	public void updateAbnormal(Abnormal abnormla);
}
