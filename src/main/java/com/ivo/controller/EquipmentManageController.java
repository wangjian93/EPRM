package com.ivo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ivo.model.equipment.DepOfClass;
import com.ivo.model.equipment.Equipment;
import com.ivo.model.equipment.EquipmentGroup;
import com.ivo.service.IEquipmentManageService;

/**
 *@author wangjian
 *@time 2017年10月22日 - 下午1:21:52
 *@description:设备管理
 */
@Controller
public class EquipmentManageController {
	@Resource  
    private IEquipmentManageService equipmentManageService;
	
	@RequestMapping("/equipmentManageView.do")
	public ModelAndView goFirst(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("equipmentManage");
		return mv;
	}
	
	@RequestMapping("/getEquipment.do")
	public void getEquipment(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String depClassID = request.getParameter("depClassID");
		String equipmentID = request.getParameter("equipmentID");
		String equipmentGroupID = request.getParameter("equipmentGroupID");
		
		if(equipmentGroupID!=null && !equipmentGroupID.equals("")){
			EquipmentGroup equipmentGroup = equipmentManageService.getEquipmentGroup(Integer.parseInt(equipmentGroupID));
			if(equipmentGroup!=null) depClassID  = Integer.toString(equipmentGroup.getClass_fk());
			else depClassID="0";
		}
		
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		int total = 0;
		List<DepOfClass> depClassList = new ArrayList<DepOfClass>();
		if(depClassID!=null && !depClassID.equals("")){
			DepOfClass depClass = equipmentManageService.getDepClass(Integer.parseInt(depClassID));
			if(depClass!=null) depClassList.add(depClass);
		}else{
			depClassList = equipmentManageService.getAllDepClass();
		}
		
		List<EquipmentGroup> equipmentGroupList = new ArrayList<EquipmentGroup>();
		for(DepOfClass depClass : depClassList){
			if(equipmentGroupID!=null && !equipmentGroupID.equals("")){
				EquipmentGroup equipmentGroup = equipmentManageService.getEquipmentGroup(Integer.parseInt(equipmentGroupID));
				if(equipmentGroup!=null) equipmentGroupList.add(equipmentGroup);
			} else {
				equipmentGroupList = equipmentManageService.getEquipmentGroupList(depClass.getClassID());
			}
			
			JSONArray jsonEA = new JSONArray();
			for(EquipmentGroup equipmentGroup : equipmentGroupList){
				List<Equipment> equipmentList = equipmentManageService.getEquipmentByGroup(equipmentGroup.getEquipmentGroupID());
				JSONArray jsonE = new JSONArray();
				jsonE.addAll(equipmentList);
				
				JSONObject jsonEG = new JSONObject();
				jsonEG.put("equipmentGroupID", equipmentGroup.getEquipmentGroupID());
				jsonEG.put("equipmentGroupName", equipmentGroup.getEquipmentGroupName());
				jsonEG.put("equipment", jsonE);
				
				jsonEA.add(jsonEG);
				total += jsonE.size();
			}
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("depClassID", depClass.getClassID());
			jsonObject.put("depClassName", depClass.getClassName());
			jsonObject.put("equipmentGroup", jsonEA);
			
			jsonArray.add(jsonObject);
		}
		
		json.put("total", total);
		json.put("depCalss", jsonArray);
		response.getWriter().print(json.toJSONString());
	}
	
	@RequestMapping("/getTree.do")
	public void getTree(HttpServletRequest request, HttpServletResponse response) throws IOException{
		List<DepOfClass> depClassList = equipmentManageService.getAllDepClass();
		JSONArray jsonArray = new JSONArray();
		for(DepOfClass depClass : depClassList){
			List<EquipmentGroup> equipmentGroupList = equipmentManageService.getEquipmentGroupList(depClass.getClassID());
			JSONArray jsonArray1 = new JSONArray();
			for(EquipmentGroup equipmentGroup : equipmentGroupList){
				JSONObject jsonObject1 = new JSONObject();
				jsonObject1.put("id", equipmentGroup.getEquipmentGroupID());
				jsonObject1.put("text", equipmentGroup.getEquipmentGroupName());
				jsonObject1.put("population", null);
				jsonObject1.put("flagUrl", null);
				jsonObject1.put("checked", false);
				
				jsonArray1.add(jsonObject1);
			}
			String[] depClassName = depClass.getClassName().split("/");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", depClass.getClassID());
			jsonObject.put("text", depClassName.length>1 ? depClassName[1] : depClassName[0]);
			jsonObject.put("population", null);
			jsonObject.put("flagUrl", null);
			jsonObject.put("checked", false);
			jsonObject.put("selectable",false);
			jsonObject.put("expanded", false);
			jsonObject.put("nodes", jsonArray1);	
			jsonArray.add(jsonObject);
		}

		response.getWriter().println(jsonArray.toString());
	}
	
	@RequestMapping("/deleteEquipment.do")
	public void deleteEquipment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String equipmentID = request.getParameter("equipmentID");
		Equipment equipment = equipmentManageService.getEquipment(Integer.parseInt(equipmentID));
		if(equipment!=null)
		equipmentManageService.deleteEquipment(equipment);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sueccess", true);
		response.getWriter().print(jsonObject.toJSONString());
	}
	
	@RequestMapping("/addEquipment.do")
	public void addEquipment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String equipmentGroupID = request.getParameter("equipmentGroupID");
		String depClassID = request.getParameter("depClassID");
		String equipmentName = request.getParameter("equipmentName");
		Equipment equipment = new Equipment();
		equipment.setEquipmentGroup_fk(Integer.parseInt(equipmentGroupID));
		equipment.setEquipmentName(equipmentName);
		equipment.setValidFlag(1);
		equipmentManageService.saveEquipment(equipment);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sueccess", true);
		response.getWriter().print(jsonObject.toJSONString());
	}
	
	@RequestMapping("/getEquipmentGroupAndDepClass.do")
	public void getEquipmentGroupAndDepClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String equipmentGroupID = request.getParameter("equipmentGroupID");
		EquipmentGroup equipmentGroup = equipmentManageService.getEquipmentGroup(Integer.parseInt(equipmentGroupID));
		DepOfClass depClass = equipmentManageService.getDepClass(equipmentGroup.getClass_fk());
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("equipmentGroupID", equipmentGroup.getEquipmentGroupID());
		jsonObject.put("equipmentGroupName", equipmentGroup.getEquipmentGroupName());
		jsonObject.put("depClassID", depClass.getClassID());
		jsonObject.put("depClassName", depClass.getClassName());
		response.getWriter().print(jsonObject.toJSONString());
	}
}
