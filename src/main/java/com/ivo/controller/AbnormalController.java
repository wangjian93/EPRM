package com.ivo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.ivo.dao.abnormalRecord.IAbnormalDao;
import com.ivo.model.abnormalRecord.Abnormal;
import com.ivo.model.equipment.CheckForm;
import com.ivo.model.equipment.Equipment;
import com.ivo.service.ICheckService;
import com.ivo.service.IMenuService;

/**
 *@author wangjian
 *@time 2017年9月14日 - 下午5:06:41
 *@description:
 */
@Controller
public class AbnormalController {
	@Resource
	private IAbnormalDao abnormalDao;
	@Resource  
    private ICheckService checkService;
	@Resource  
    private IMenuService menuService;
	
	@RequestMapping("/getEquipmentByGroup")
	public void getEquipmentByGroup(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String equipmentGroup = request.getParameter("equipmentGroup");
		if(equipmentGroup!=null && !equipmentGroup.equals("")){
			List<Equipment> list = menuService.getEquipmentByGroup(Integer.parseInt(equipmentGroup));
			JSONArray jsonArray = new JSONArray();
			jsonArray.addAll(list);
			response.getWriter().print(jsonArray.toString());
		}
	}
	
	@RequestMapping("/getAbnormalData.do")
	public void getAbnormalData(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String yearS = request.getParameter("year");
		String monthS = request.getParameter("month");
		String dayS = request.getParameter("day");
		String equipmentGroupS = request.getParameter("equipmentGroup");
		String equipmentIDS = request.getParameter("equipmentID");
		int year = 0;
		int month = 0;
		int day = 0;
		int equipmentGroup = 0;
		int equipmentID = 0;
		if(yearS!=null && !yearS.equals("")){
			year = Integer.parseInt(yearS);
		}
		if(monthS!=null && !monthS.equals("")){
			month = Integer.parseInt(monthS);
		}
		if(dayS!=null && !dayS.equals("")){
			day = Integer.parseInt(dayS);
		}
		if(equipmentGroupS!=null && !equipmentGroupS.equals("")){
			equipmentGroup = Integer.parseInt(equipmentGroupS);
		}
		if(equipmentIDS!=null && !equipmentIDS.equals("")){
			equipmentID = Integer.parseInt(equipmentIDS);
		}
		List<Abnormal> abnormalList = new ArrayList<Abnormal>();
		if(day==0){
			if(equipmentID==0){
				abnormalList = (List<Abnormal>) abnormalDao.getAbnormalMonth(year, month, equipmentGroup);
			}else{
				abnormalList = (List<Abnormal>) abnormalDao.getAbnormalEquipmentMonth(year, month, equipmentID);
			}
		}else{
			if(equipmentID==0){
				abnormalList = (List<Abnormal>) abnormalDao.getAbnormalDay(year, month, day, equipmentGroup);
			}else{
				abnormalList = (List<Abnormal>) abnormalDao.getAbnormalEquipmentDay(year, month, day, equipmentID);
			}
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(abnormalList);
		PrintWriter out = response.getWriter();
		out.println(jsonArray.toString());
	}
	
	@RequestMapping("/submitAbnormal.do")
	public void submitAbnormal(HttpServletRequest request, HttpServletResponse response) throws IOException{
		System.out.println("ddd");
		String trackingNumber = request.getParameter("trackingNumber");
		String dates = request.getParameter("dates");
		String sipecification = request.getParameter("sipecification");
		String solutions = request.getParameter("solutions");
		String expectedTime = request.getParameter("expectedTime");
		String actualTime = request.getParameter("actualTime");
		String ifCompleted = request.getParameter("ifCompleted");
		String memo = request.getParameter("memo");
		String equipmentID = request.getParameter("equipmentID");
		CheckForm checkForm = checkService.getCheckForm(trackingNumber);
		Abnormal abnormal = new Abnormal();
		abnormal.setYear(checkForm.getYear());
		abnormal.setMonth(checkForm.getMonth());
		abnormal.setDay(checkForm.getDay());
		abnormal.setEquipmentGroup_fk(checkForm.getEquipmentGroup_fk());
		abnormal.setEquipmentID_fk(Integer.parseInt(equipmentID));
		abnormal.setCheckForm_fk(checkForm.getTrackingNumber());
		abnormal.setDates(dates);
		abnormal.setSipecification(sipecification);
		abnormal.setSolutions(solutions);
		abnormal.setExpectedTime(expectedTime);
		abnormal.setActualTime(actualTime);		
		abnormal.setIfCompleted(ifCompleted);
		abnormal.setMemo(memo);
		abnormalDao.saveAbnormal(abnormal);
		response.getWriter().print("{\"success\":\"true\"}");
	}
}
