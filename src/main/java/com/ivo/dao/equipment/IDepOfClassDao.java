package com.ivo.dao.equipment;

import java.util.List;

import com.ivo.model.equipment.DepOfClass;

/**
 *@author wangjian
 *@time 2017年8月31日 - 上午11:26:13
 *@description:
 */
public interface IDepOfClassDao {
	public DepOfClass getByID(int id);
	public List<DepOfClass> getAll();
}
