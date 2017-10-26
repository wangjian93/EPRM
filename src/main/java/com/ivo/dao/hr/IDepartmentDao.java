package com.ivo.dao.hr;

import com.ivo.model.hr.Department;
import com.ivo.model.hr.Employee;

/**
 *@author wangjian
 *@time 2017年8月30日 - 下午1:51:09
 *@description:
 */
public interface IDepartmentDao {
	public Department getByID(String id);
	public Department getByEmployee(Employee employee);
}
