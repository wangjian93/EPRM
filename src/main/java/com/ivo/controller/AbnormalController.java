package com.ivo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONPObject;
import com.ivo.model.equipment.DepOfClass;
import com.ivo.model.equipment.EquipmentGroup;
import com.ivo.service.IEquipmentManageService;
import com.ivo.service.MailService;
import com.ivo.util.HttpRequest;
import org.codehaus.jackson.map.ObjectMapper;
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
	@Resource
	private MailService mailService;
	@Resource
	private IEquipmentManageService equipmentManageService;
	
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

	//历史记录和check中查询异常记录用，可以同时查出之前未完成的异常
	@RequestMapping("/getAbnormalData2.do")
	public void getAbnormalData2(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String yearS = request.getParameter("year");
		String monthS = request.getParameter("month");
		String dayS = request.getParameter("day");
		String equipmentGroupS = request.getParameter("equipmentGroup");
		String equipmentIDS = request.getParameter("equipmentID");
		String deptClassS = request.getParameter("deptClass");
		String ifCompleted = request.getParameter("ifCompleted");
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
		if(ifCompleted==null) {
			ifCompleted = "";
		}
		List<Abnormal> abnormalList = new ArrayList<Abnormal>();

		abnormalList = abnormalDao.queryAbnormal(
				year, month, day, deptClass, equipmentGroup, equipmentID, "1");

		//获取未完成的异常记录
		List<Abnormal> abnormalList2 = new ArrayList<Abnormal>();
		abnormalList2 = abnormalDao.queryAbnormal(
					0, 0, 0, deptClass, equipmentGroup, equipmentID, "0");
		abnormalList.addAll(abnormalList2);

		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(abnormalList);
		PrintWriter out = response.getWriter();
		out.println(jsonArray.toString());
	}
	
	@RequestMapping("/getAbnormalData.do")
	public void getAbnormalData(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String yearS = request.getParameter("year");
		String monthS = request.getParameter("month");
		String dayS = request.getParameter("day");
		String equipmentGroupS = request.getParameter("equipmentGroup");
		String equipmentIDS = request.getParameter("equipmentID");
		String deptClassS = request.getParameter("deptClass");
		String ifCompleted = request.getParameter("ifCompleted");
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
		if(ifCompleted==null) {
			ifCompleted = "";
		}
		List<Abnormal> abnormalList = new ArrayList<Abnormal>();
		
		abnormalList = abnormalDao.queryAbnormal(
				year, month, day, deptClass, equipmentGroup, equipmentID, ifCompleted);

		//获取未完成的异常记录
//		List<Abnormal> abnormalList2 = new ArrayList<Abnormal>();
//		abnormalList2 = abnormalDao.queryAbnormal(
//				0, 0, 0, deptClass, equipmentGroup, equipmentID, "0");
//		abnormalList.addAll(abnormalList2);

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

		/**发送邮件给工程师**/
		String param = "eid=";
		if(engineer.length()>8) {
			param += engineer.substring(0,8);
		}
		String em = HttpRequest.sendPost("http://10.20.2.10:8080/org/org/getEmployee", param);
		ObjectMapper mapper = new ObjectMapper();
		HashMap map = null;
		try{
			map = mapper.readValue(em,HashMap.class);
		} catch (IOException e){
			e.printStackTrace();
		}
		String mailAdress = (String) map.get("email");
		if(mailAdress!=null && !("").equals(mailAdress)) {
			StringBuffer mailStr = new StringBuffer();
			mailStr.append("<html lang=\"en\">");
			mailStr.append("<head><meta charset=\"UTF-8\"></head>");
			mailStr.append("<body>");
			mailStr.append("<h4>Dear All,</h4>");
			mailStr.append("<div style=\"margin-left:30px;\">");
			mailStr.append("<p>厂务设备妥善率管理系统中有设备异常，请登录系统确定解决方案");
			mailStr.append("<p>异常状况：</p>");
			mailStr.append("<div><table width=\"600\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
			mailStr.append("<tr>");
			mailStr.append("<th>日期</th>");
			mailStr.append("<th>课</th>");
			mailStr.append("<th>系统</th>");
			mailStr.append("<th>设备编号</th>");
			mailStr.append("<th>异常状况</th>");
			mailStr.append("<th>备注</th>");
			mailStr.append("<th>创建人</th>");
			mailStr.append("<th>工程师</th>");
			mailStr.append("</tr>");
			mailStr.append("<tr>");
			mailStr.append("<th>"+dates+"</th>");
			DepOfClass depOfClass = checkService.getDepOfClass(checkForm.getClass_fk());
			mailStr.append("<th>"+depOfClass.getClassName()+"</th>");
			EquipmentGroup equipmentGroup = checkService.getEuipmentGroup(checkForm.getEquipmentGroup_fk());
			mailStr.append("<th>"+equipmentGroup.getEquipmentGroupName()+"</th>");
			Equipment equipment = equipmentManageService.getEquipment(Integer.parseInt(equipmentID));
			mailStr.append("<th>"+equipment.getEquipmentName()+"</th>");
			mailStr.append("<th>"+sipecification+"</th>");
			mailStr.append("<th>"+memo+"</th>");
			mailStr.append("<th>"+employee.getEmployee_ID()+" "+employee.getEmployeeName()+"</th>");
			mailStr.append("<th>"+engineer+"</th>");
			mailStr.append("</tr>");
			mailService.sendHtmlMail("EPRM@ivo.com.cn",mailAdress,"常务设备妥善率管理系统有设备异常提醒",mailStr.toString());
		}
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
