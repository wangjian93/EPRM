package com.ivo.quartz;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivo.service.ICheckService;

/**
 *@author wangjian
 *@time 2017年9月25日 - 上午10:06:42
 *@description:每天创建当天的设备点检表
 */
public class CreateCurrentCheckForm {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
    private ICheckService checkService;
    
	public ICheckService getCheckService() {
		return checkService;
	}

	public void setCheckService(ICheckService checkService) {
		this.checkService = checkService;
	}

	public void execute() {
		System.out.println("开始定时器");
		checkService.createCurrentCheck();
		System.out.println("结束定时器");
	}
}