package com.ivo.service;

import com.ivo.model.hr.Department;
import com.ivo.model.hr.Employee;

/**
 *@author wangjian
 *@time 2017年8月30日 - 下午3:37:08
 *@description:
 */
public interface IDepartmentService {
	public Department getDepartment(String id);
	public Department getDepartment(Employee employee);
}
