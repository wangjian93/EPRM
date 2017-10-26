package com.ivo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ivo.model.equipment.CheckFormMonth;
import com.ivo.model.equipment.DepOfClass;
import com.ivo.model.equipment.EquipmentGroup;
import com.ivo.service.ICheckService;
import com.ivo.service.IMenuService;

/**
 *@author wangjian
 *@time 2017年10月10日 - 下午1:42:43
 *@description:
 */
@Controller
public class ReportController {
	@Resource 
	private ICheckService checkService;
	@Resource  
    private IMenuService menuService;
	@RequestMapping("/getReportData.do")
	public void getReportData(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		
		String deptClass = request.getParameter("deptClass");
		String equipmentGroup = request.getParameter("equipmentGroup");
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		JSONArray jsonArray = new JSONArray();
		if(equipmentGroup==null || equipmentGroup.equals("")){
			if(deptClass==null || deptClass.equals("")){
				List<DepOfClass> deptClassList = menuService.getDepOfClass();
				for(DepOfClass  dc : deptClassList){
					List<CheckFormMonth> checkFormMonthList = checkService.getCheckFormMonthByClassID(dc.getClassID(), year, month);
					JSONObject jsonObject = new JSONObject();
					String[] className = dc.getClassName().split("/");
					jsonObject.put("className",className[0]);			
					jsonObject.put("checkFormMonth", checkFormMonthList);
					jsonList.add(jsonObject);
				}
			}else{
				DepOfClass  dc = menuService.getDepOfClass(Integer.parseInt(deptClass));
				List<CheckFormMonth> checkFormMonthList = checkService.getCheckFormMonthByClassID(dc.getClassID(), year, month);
				JSONObject jsonObject = new JSONObject();
				String[] className = dc.getClassName().split("/");
				jsonObject.put("className",className[0]);			
				jsonObject.put("checkFormMonth", checkFormMonthList);
				jsonList.add(jsonObject);
			}
		}else{
			DepOfClass  dc = menuService.getDepOfClass(Integer.parseInt(deptClass));
			List<CheckFormMonth> checkFormMonthList = new ArrayList<CheckFormMonth>();
			CheckFormMonth c = checkService.getCheckFormMonthByEquipmentGroupID(Integer.parseInt(equipmentGroup), year, month);	
			checkFormMonthList.add(c);
			JSONObject jsonObject = new JSONObject();
			String[] className = dc.getClassName().split("/");
			jsonObject.put("className",className[0]);			
			jsonObject.put("checkFormMonth", checkFormMonthList);
			jsonList.add(jsonObject);
			
		}
		jsonArray.addAll(jsonList);
		response.getWriter().print(jsonArray.toJSONString());
	}
	
	@RequestMapping("/getEquipmentGroupByDepClass.do")
	public void getEquipmentGroupByDepClass(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String  deptClassID = request.getParameter("deptClassID");
		List<EquipmentGroup> list = menuService.getEuipmentGroupByCalss(Integer.parseInt(deptClassID));
		JSONArray josnArray = new JSONArray();
		josnArray.addAll(list);
		response.getWriter().print(josnArray.toString());
	}
}
