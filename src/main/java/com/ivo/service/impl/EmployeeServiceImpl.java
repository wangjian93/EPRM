package com.ivo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ivo.dao.hr.IEmployeeDao;
import com.ivo.model.hr.Employee;
import com.ivo.service.IEmployeeService;

/**
 *@author wangjian
 *@time 2017年8月30日 - 下午2:15:28
 *@description:
 */
@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService {
	@Resource  
	   private IEmployeeDao employeeDao;
	
	@Override
	public Employee getEmployee(String id) {
		// TODO Auto-generated method stub
		return employeeDao.getByID(id);
	}
	
}
