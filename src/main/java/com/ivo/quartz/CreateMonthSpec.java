package com.ivo.quartz;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivo.dao.equipment.IEquipmentGroupDao;
import com.ivo.model.equipment.EquipmentGroup;
import com.ivo.model.equipment.Spec;
import com.ivo.service.ISpecService;
import com.ivo.util.CurrentUtil;

/**
 *@author wangjian
 *@time 2017年11月1日 - 下午3:16:37
 *@description:每月同步各设备系统的Spec值
 */
public class CreateMonthSpec {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private ISpecService specService;
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public ISpecService getSpecService() {
		return specService;
	}

	public void setSpecService(ISpecService specService) {
		this.specService = specService;
	}

	public void execute() {
		System.out.println("开始定时器");
		specService.updateCurrentMonthSpec();
		System.out.println("结束定时器");
	}
	
	
}
