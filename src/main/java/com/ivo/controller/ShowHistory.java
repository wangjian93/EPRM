package com.ivo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ivo.model.equipment.CheckDataDetail;
import com.ivo.model.equipment.CheckForm;
import com.ivo.model.equipment.CheckFormMonth;
import com.ivo.model.equipment.DepOfClass;
import com.ivo.model.equipment.EquipmentGroup;
import com.ivo.service.ICheckService;
import com.ivo.service.IMenuService;
import com.ivo.service.ISpecService;
import com.ivo.util.CurrentUtil;

/**
 *@author wangjian
 *@time 2017年9月18日 - 上午8:21:59
 *@description:
 */
@Controller
public class ShowHistory {
	@Resource 
	private ICheckService checkService;
	@Resource
	private IMenuService menuService;
	@Resource
	private ISpecService specService;
	
	@RequestMapping("/historyView.do")
	public ModelAndView showHistory(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("history");
		String depClassID = request.getParameter("depClassID");
		String equipmentGroupID = request.getParameter("equipmentGroup");
		String yearS = request.getParameter("year");
		String monthS = request.getParameter("month");
		int year;
		int month;
		if(yearS!=null && !yearS.equals("")){
			year = Integer.parseInt(yearS);
		}else{
			year = CurrentUtil.CurrentYear();
		}
		if(monthS!=null && !monthS.equals("")){
			month = Integer.parseInt(monthS);
		}else{
			month = CurrentUtil.CurrentMonth();;
		}

		DepOfClass depOfClass = new DepOfClass();
		if(depClassID!=null && !depClassID.equals("")){
			depOfClass = menuService.getDepOfClass(Integer.parseInt(depClassID));
		}
		mv.addObject("depOfClassName", depOfClass.getClassName());
		mv.addObject("equipmentGroupID", equipmentGroupID);
		mv.addObject("year", year);
		mv.addObject("month",month);
		List<EquipmentGroup> equipmentGroupList = menuService.getEuipmentGroupByCalss(Integer.parseInt(depClassID));
		mv.addObject("equipmentGroupList", equipmentGroupList);
		return mv;
	}
	
	@RequestMapping("/getCheckDataDetail")
	public void getCheckDataDetail(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String yearS = request.getParameter("year");
		String monthS = request.getParameter("month");
		String equipmentGroupS = request.getParameter("equipmentGroup");
		int year=0;
		int month=0;
		int equipmentGroup=0;
		if(yearS!=null && !yearS.equals(""))  year = Integer.parseInt(yearS);
		if(monthS!=null && !monthS.equals("")) month = Integer.parseInt(monthS);
		if(equipmentGroupS!=null && !equipmentGroupS.equals("")) equipmentGroup = Integer.parseInt(equipmentGroupS);
		List<CheckDataDetail> checkDataDetaiList = checkService.getCheckDataGroup(year, month, equipmentGroup);
		CheckFormMonth checkFormMonth = checkService.getCheckFormMonthByEquipmentGroupID(equipmentGroup, year, month);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("checkDetail", checkDataDetaiList);
		if(checkFormMonth==null) checkFormMonth = new CheckFormMonth();
		jsonObject.put("checkFormMonth", checkFormMonth);
//		/Spec spec = specService.getSpecByMonth(year, month);
		
		PrintWriter out = response.getWriter();
		out.println(jsonObject.toString());
		
	}
}
