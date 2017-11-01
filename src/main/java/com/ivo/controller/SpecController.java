package com.ivo.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ivo.model.equipment.Spec;
import com.ivo.service.ISpecService;
import com.ivo.util.CurrentUtil;

/**
 *@author wangjian
 *@time 2017年10月22日 - 下午1:22:08
 *@description:
 */
@Controller
public class SpecController {
	@Resource
	private ISpecService specService;
	
	@RequestMapping("/specView.do")
	public ModelAndView specView(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("spec");
		return mv;
	}
	
	@RequestMapping("getSpec.do")
	public void getSpec(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int year = CurrentUtil.CurrentYear();
		int month = CurrentUtil.CurrentMonth();
		List<Spec> specList = specService.getSpecByMonth(year, month);
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(specList);
		response.getWriter().print(jsonArray.toJSONString());
	}
	
	@RequestMapping("updateSpec.do")
	public void updateSpec(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String equipmentGroupID = request.getParameter("equipmentGroupID");
		String depClassID = request.getParameter("depClassID");
		String specValue = request.getParameter("spec");
		int year = CurrentUtil.CurrentYear();
		int month = CurrentUtil.CurrentMonth();
		Spec spec = specService.getSpecByEquipmentGroup(year, month, Integer.parseInt(equipmentGroupID));
		spec.setSpec(Float.parseFloat(specValue));
		specService.updateSpec(spec);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sueccess", true);
		response.getWriter().print(jsonObject.toJSONString());
	}
}
