package com.ivo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ivo.dao.abnormalRecord.IAbnormalDao;
import com.ivo.model.abnormalRecord.Abnormal;
import com.ivo.model.equipment.CheckForm;
import com.ivo.model.equipment.Equipment;
import com.ivo.model.hr.Employee;
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
	
	@RequestMapping("/abnormalView.do")
	public ModelAndView abnormalView(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("abnormal");
		return mv;
	}
	
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
	
	@RequestMapping("/getAbnormalByID.do")
	public void getAbnormalByID(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int abnormalID = Integer.parseInt(request.getParameter("abnormalID"));
		Abnormal abnormal = abnormalDao.getAbnotmalByID(abnormalID);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("abnormal", abnormal);
		response.getWriter().print(jsonObject.toJSONString());
	}
	
//	/**用于报表**/
//	@RequestMapping("/getAbnormal.do")
	
	@RequestMapping("/getAbnormalData.do")
	public void getAbnormalData(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String yearS = request.getParameter("year");
		String monthS = request.getParameter("month");
		String dayS = request.getParameter("day");
		String equipmentGroupS = request.getParameter("equipmentGroup");
		String equipmentIDS = request.getParameter("equipmentID");
		String deptClassS = request.getParameter("deptClass");
		int year = 0;
		int month = 0;
		int day = 0;
		int equipmentGroup = 0;
		int equipmentID = 0;
		int deptClass = 0;
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
		if(deptClassS!=null && !deptClassS.equals("")){
			deptClass = Integer.parseInt(deptClassS);
		}
		List<Abnormal> abnormalList = new ArrayList<Abnormal>();
		
		abnormalList = abnormalDao.queryAbnormal(
				year, month, day, deptClass, equipmentGroup, equipmentID);
//		if(day==0){
//			if(equipmentID==0){
//				if(equipmentGroup==0){
//					abnormalList = (List<Abnormal>) abnormalDao.getAbnormalByMonth(year, month);
//				}else{
//					abnormalList = (List<Abnormal>) abnormalDao.getAbnormalMonth(year, month, equipmentGroup);
//				}
//			}else{
//				abnormalList = (List<Abnormal>) abnormalDao.getAbnormalEquipmentMonth(year, month, equipmentID);
//			}
//		}else{
//			if(equipmentID==0){
//				abnormalList = (List<Abnormal>) abnormalDao.getAbnormalDay(year, month, day, equipmentGroup);
//			}else{
//				abnormalList = (List<Abnormal>) abnormalDao.getAbnormalEquipmentDay(year, month, day, equipmentID);
//			}
//		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(abnormalList);
		PrintWriter out = response.getWriter();
		out.println(jsonArray.toString());
	}
	
	@RequestMapping("/submitAbnormal.do")
	public void submitAbnormal(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String trackingNumber = request.getParameter("trackingNumber");
		String dates = request.getParameter("dates");
		String sipecification = request.getParameter("sipecification");
		String solutions = request.getParameter("solutions");
		String expectedTime = request.getParameter("expectedTime");
		String actualTime = request.getParameter("actualTime");
		String ifCompleted = request.getParameter("ifCompleted");
		String memo = request.getParameter("memo");
		String equipmentID = request.getParameter("equipmentID");
		String engineer = request.getParameter("engineer");
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
		abnormal.setEngineer(engineer);
		Employee employee =  (Employee) request.getSession().getAttribute("LOGIN_USER");
		abnormal.setCreator(employee.getEmployee_ID());
		abnormal.setDateOfCreate(new Date());
		abnormal.setDeptClass(Integer.toString(checkForm.getClass_fk()));
		abnormalDao.saveAbnormal(abnormal);
		response.getWriter().print("{\"success\":\"true\"}");
	}
	
	@RequestMapping("/modifyAbnormal.do")
	public void modifyAbnormal(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String abnormalID = request.getParameter("abnormalID");
		String dates = request.getParameter("dates");
		String sipecification = request.getParameter("sipecification");
		String solutions = request.getParameter("solutions");
		String expectedTime = request.getParameter("expectedTime");
		String actualTime = request.getParameter("actualTime");
		String ifCompleted = request.getParameter("ifCompleted");
		String memo = request.getParameter("memo");
		String equipmentID = request.getParameter("equipmentID");
		String validFlag =  request.getParameter("validFlag");
		String engineer = request.getParameter("engineer");
		Employee employee = (Employee) request.getSession().getAttribute("LOGIN_USER");
		Abnormal abnormal = abnormalDao.getAbnotmalByID(Integer.parseInt(abnormalID));
		//判断删除或修改
		if(validFlag!=null && validFlag.equals("0")){
			if(employee.getEmployee_ID().equals(abnormal.getCreater()) 
					|| employee.getEmployee_ID().toUpperCase().equals("C0711009")){
				
				abnormal.setValidFlag(0);
				abnormal.setUpdater(employee.getEmployee_ID());
				abnormal.setDateOfUpdate(new Date());
			}else{
				response.getWriter().print("{\"success\":\"false\",\"message\":\"你没有权限删除\"}");
				return;
			}
		}else{
			abnormal.setDates(dates);
			abnormal.setSipecification(sipecification);
			abnormal.setSolutions(solutions);
			abnormal.setExpectedTime(expectedTime);
			abnormal.setActualTime(actualTime);		
			abnormal.setIfCompleted(ifCompleted);
			abnormal.setMemo(memo);
			abnormal.setEngineer(engineer);
			abnormal.setEquipmentID_fk(Integer.parseInt(equipmentID));
			abnormal.setUpdater(employee.getEmployee_ID());
			abnormal.setDateOfUpdate(new Date());
		}
		abnormalDao.updateAbnormal(abnormal);
		response.getWriter().print("{\"success\":\"true\"}");
	}
}
