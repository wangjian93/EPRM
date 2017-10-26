package com.ivo.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ivo.dao.abnormalRecord.IAbnormalDao;
import com.ivo.dao.equipment.ICheckFormMonthDao;
import com.ivo.model.abnormalRecord.Abnormal;
import com.ivo.model.equipment.CheckFormMonth;

/**
 *@author wangjian
 *@time 2017年9月14日 - 下午1:34:09
 *@description:
 */
public class test {
	public static void main(String[] args){
		ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ICheckFormMonthDao checkFormMonthDao = (ICheckFormMonthDao) factory.getBean("personDao");
		CheckFormMonth checkFormMonth = new CheckFormMonth();
		checkFormMonth.setDepOfclassID(2);
		checkFormMonth.setEquipmentGroupID(1);
		checkFormMonth.setYear(2017);
		checkFormMonth.setMonth(9);
		checkFormMonth.setDay1((float)0.78);
		checkFormMonthDao.saveCheckFormMonth(checkFormMonth);
//		CheckFormMonth checkFormMonth = checkFormMonthDao.getCheckFormMonthByEquipmentGroupID(1, 2017, 9);
//		checkFormMonth.setDay1((float)0.44); 
//		checkFormMonthDao.updateCheckFormMonth(checkFormMonth);
	}
}
