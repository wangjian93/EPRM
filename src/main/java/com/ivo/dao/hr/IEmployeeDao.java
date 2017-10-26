package com.ivo.dao.hr;

import com.ivo.model.hr.Employee;

/**
 *@author wangjian
 *@time 2017年8月30日 - 下午1:47:34
 *@description:
 */
public interface IEmployeeDao {
	public Employee getByID(String id);
}
