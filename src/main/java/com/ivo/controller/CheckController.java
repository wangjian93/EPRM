package com.ivo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.ivo.model.equipment.CheckDataDetail;
import com.ivo.model.equipment.CheckForm;
import com.ivo.model.equipment.CheckFormMonth;
import com.ivo.model.equipment.DepOfClass;
import com.ivo.model.equipment.Equipment;
import com.ivo.model.equipment.EquipmentGroup;
import com.ivo.service.ICheckService;

/**
 *@author wangjian
 *@time 2017年9月5日 - 上午9:12:10
 *@description:
 */
@Controller
public class CheckController {
	@Resource  
    private ICheckService checkService;
	
	@RequestMapping("/checkFormView.do")
	public ModelAndView checkFormView(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String trackingNumber = request.getParameter("trackingNumber");
		if(trackingNumber==null) trackingNumber = (String) request.getAttribute("trackingNumber");
		ModelAndView mv = new ModelAndView("check");
		CheckForm checkForm = checkService.getCheckForm(trackingNumber);
		if(checkForm!=null){
			int year = checkForm.getYear();
			int month = checkForm.getMonth();
			int day = checkForm.getDay();
			EquipmentGroup equipmentGroup = checkService.getEuipmentGroup(checkForm.getEquipmentGroup_fk());
			DepOfClass depOfClass = checkService.getDepOfClass(checkForm.getClass_fk());
			mv.addObject("year",year);
			mv.addObject("month",month);
			mv.addObject("day",day);
			mv.addObject("equipmentGroup",checkForm.getEquipmentGroup_fk());
			if(depOfClass!=null)
			mv.addObject("depOfClassName",depOfClass.getClassName());
			if(equipmentGroup!=null)
			mv.addObject("equipmentGroupName", equipmentGroup.getEquipmentGroupName());
		}
		
		mv.addObject("trackingNumber", trackingNumber);
		return mv;
	}
	@RequestMapping("/getCheckForm.do")
	public void getCheckForm(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String trackingNumber = request.getParameter("trackingNumber");
		CheckForm checkForm = checkService.setCheckForm(trackingNumber);
		JSONArray json = new JSONArray();
		json.add(checkForm);
		System.out.println(json.toJSONString());
		PrintWriter out = response.getWriter();
		out.println(json.toJSONString());
	}
	
	@RequestMapping("/saveCheckForm.do")
	public void saveCheckForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String trackingNumber = request.getParameter("trackingNumber");
		String properRate = request.getParameter("properRate");
		CheckForm checkForm = checkService.getCheckForm(trackingNumber);
		float p = Float.parseFloat(properRate);
		System.out.println(properRate);
		System.out.println(p);
		checkForm.setProperRate(p);
		int year = checkForm.getYear();
		int month = checkForm.getMonth();
		int day = checkForm.getDay();
		
		Enumeration<String> enu=request.getParameterNames();  
		while(enu.hasMoreElements()){
			String paraName=(String)enu.nextElement(); 
			if(!paraName.equals("trackingNumber") && !paraName.equals("properRate")){
				System.out.println(paraName+"###: "+request.getParameter(paraName));
				String check = request.getParameter(paraName);
				CheckDataDetail checkDataDetail = checkService.getCheckDataDetail(year, month, Integer.parseInt(paraName));
				if(checkDataDetail!=null){
					checkDataDetail.SetCurrentDay(day, check);
					checkService.updateCheckFormDetail(checkDataDetail);
				}
			}
		}
		CheckFormMonth checkFormMonth = checkService.getCheckFormMonthByEquipmentGroupID(checkForm.getEquipmentGroup_fk(), year, month);
		if(checkFormMonth==null){
			checkFormMonth = new CheckFormMonth();
			checkFormMonth.setDepOfclassID(checkForm.getClass_fk());
			checkFormMonth.setEquipmentGroupID(checkForm.getEquipmentGroup_fk());
			checkFormMonth.setYear(year);
			checkFormMonth.setMonth(month);
			checkFormMonth.SetCurrentDay(checkForm.getDay(), checkForm.getProperRate());
			checkService.saveCheckFormMonth(checkFormMonth);
		}else{
			checkFormMonth.SetCurrentDay(checkForm.getDay(), checkForm.getProperRate());
			checkService.updateCheckFormMonth(checkFormMonth);
		}
		checkService.updateCheckForm(checkForm);
		//request.setAttribute("trackingNumber", "DAR170908001");
		request.getRequestDispatcher("firstView.do").forward(request, response);
	}
	
	@RequestMapping("/createCurrentCheck.do")
	public void createCurrentCheck(HttpServletRequest request, HttpServletResponse response){
		checkService.createCurrentCheck();
	}
}
