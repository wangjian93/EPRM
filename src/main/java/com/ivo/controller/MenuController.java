package com.ivo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ivo.model.equipment.CheckForm;
import com.ivo.model.equipment.DepOfClass;
import com.ivo.model.equipment.EquipmentGroup;
import com.ivo.service.ICheckService;
import com.ivo.service.IDepartmentService;
import com.ivo.service.IMenuService;
import com.ivo.util.CurrentUtil;

/**
 *@author wangjian
 *@time 2017年8月31日 - 上午10:37:01
 *@description:
 */
@Controller
public class MenuController {
	@Resource 
	private ICheckService checkService;
	@Resource  
    private IMenuService menuService;
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@RequestMapping("/initMenu.do")
	public void initMenu(HttpServletRequest request, HttpServletResponse response) throws IOException{
//		List<DepOfClass> depOfClassList = menuService.getDepOfClass();
//		Map<String,List<EquipmentGroup>> map = new HashMap<String,List<EquipmentGroup>>();
//		for(DepOfClass c : depOfClassList){
//			List<EquipmentGroup> egList = menuService.getEuipmentGroupByCalss(c.getClassID());
//			map.put(c.getClassName(), egList);
//		}
//		JSONObject json = new JSONObject();
//		json.putAll(map);
//		System.out.println(json);
//		
//		PrintWriter out = response.getWriter();
//        out.println(json.toString());
		List<DepOfClass> depOfClassList = menuService.getDepOfClass();
		Map<String,List<EquipmentGroup>> map = new HashMap<String,List<EquipmentGroup>>();
		//JSONArray jsonArray = new JSONArray();
		JSONObject json = new JSONObject();
		for(DepOfClass c : depOfClassList){
			List<EquipmentGroup> egList = menuService.getEuipmentGroupByCalss(c.getClassID());
			JSONArray jsonArray = new JSONArray();
			for(EquipmentGroup  e : egList){
				JSONObject jsonObject = new JSONObject();
				String trackingNumber = CurrentUtil.CurrentPerTracking(e.getEquipmentGroupID());
				CheckForm checkForm = checkService.getCheckForm(trackingNumber);
				float properRate = checkForm.getProperRate();
				jsonObject.put("class_fk",e.getClass_fk());
				jsonObject.put("equipmentGroupID", e.getEquipmentGroupID());
				jsonObject.put("equipmentGroupName", e.getEquipmentGroupName());
				jsonObject.put("properRate",properRate);
				jsonArray.add(jsonObject);
			}
			String[] classNames = c.getClassName().split("/");
			String className = classNames.length >1 ? classNames[1] : classNames[0];
			json.put(className , jsonArray);
		}
		System.out.println(json.toJSONString());
		PrintWriter out = response.getWriter();
        out.println(json.toJSONString());
	}
	@RequestMapping("/goCheck.do")
	public void geCheck(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String module = request.getParameter("module");
		String groupID = request.getParameter("groupID");
		String depClassID = request.getParameter("depClassID");
		request.setAttribute("module", module);
		request.setAttribute("groupID", groupID);
		request.setAttribute("depClassID", depClassID);
		if(module.equals("abnormal")){
			request.getRequestDispatcher("abnormalView.do").forward(request, response);
		}
		else if(module.equals("first")){
			request.getRequestDispatcher("firstView.do").forward(request, response);
		}else if(module.equals("now")){
			String trackingNumber = CurrentUtil.CurrentPerTracking(Integer.parseInt(groupID));
			request.setAttribute("trackingNumber", trackingNumber);
			request.getRequestDispatcher("checkFormView.do").forward(request, response);
		}else if(module.equals("history")){
			request.getRequestDispatcher("historyView.do").forward(request, response);
		}
		else if(module.equals("equipmentManage")){
			request.getRequestDispatcher("equipmentManageView.do").forward(request, response);
		}else if(module.equals("spec")){
			request.getRequestDispatcher("specView.do").forward(request, response);
		}
	}
}
