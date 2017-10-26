package com.ivo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ivo.dao.hr.IDepartmentDao;
import com.ivo.dao.hr.IEmployeeDao;
import com.ivo.model.hr.Department;
import com.ivo.model.hr.Employee;
import com.ivo.service.IDepartmentService;

/**
 *@author wangjian
 *@time 2017年8月30日 - 下午3:39:06
 *@description:
 */
@Service("departmentService")
public class DepartmentServiceImpl implements IDepartmentService {
	@Resource  
	   private IDepartmentDao departmentDao;
	
	@Override
	public Department getDepartment(String id) {
		return departmentDao.getByID(id);
	}

	@Override
	public Department getDepartment(Employee employee) {
		return departmentDao.getByEmployee(employee);
	}

}
