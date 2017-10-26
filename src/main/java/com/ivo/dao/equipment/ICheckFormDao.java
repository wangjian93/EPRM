package com.ivo.dao.equipment;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ivo.model.equipment.CheckForm;
import com.ivo.model.equipment.CheckFormItem;

/**
 *@author wangjian
 *@time 2017年9月5日 - 上午11:34:24
 *@description:
 */
public interface ICheckFormDao {
	public List<CheckForm> getCheckFormMonth(@Param("year")int year, @Param("month")int month, @Param("equipmentGroup")int equipmentGroup);
	public CheckForm getCheckForm(String trackingNumber);
	public int saveCheckForm(CheckForm checkForm);
	public void updateCheckForm(CheckForm checkForm);
}
