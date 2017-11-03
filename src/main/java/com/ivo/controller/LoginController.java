package com.ivo.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ivo.model.hr.Department;
import com.ivo.model.hr.Employee;
import com.ivo.service.IDepartmentService;
import com.ivo.service.IEmployeeService;
import com.ivo.util.HttpRequest;

/**
 *@author wangjian
 *@time 2017年8月29日 - 下午2:58:08
 *@description:
 */
@Controller  
public class LoginController {
	@Resource  
    private IDepartmentService departmentService;
	@Resource  
    private IEmployeeService employeeService;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response){
		String username = request.getParameter("username").toUpperCase();
		String password = request.getParameter("password");
		logger.info("Log --> :login.do 开始登陆用户"+username);
		Employee employee = null;
		try{
			employee = employeeService.getEmployee(username);
		}catch(Exception e){
//			防止客户端一段时间不访问,第一次访问数据库自动断开连接,刷新可以正常连接
//			再向数据库发送请求
			employee = employeeService.getEmployee(username);
			e.printStackTrace();
		}
		if(employee==null){
			ModelAndView mv = new ModelAndView("login");
			mv.addObject("msg", "用户不存在");
			logger.info("Log --> :登录失败..");
			return mv;
		}else{
			String param = "eid="+username+"&password="+password;
//			String result = HttpRequest.sendPost("http://myivo.ivo.com.cn/org/org/verify", param);
			String result = "true";
			if(result.equals("false")){
				ModelAndView mv = new ModelAndView("login");
				mv.addObject("msg", "密码不正确");
				logger.info("Log --> :login.do 登录失败..");
				return mv;
			}else{
				ModelAndView mv = new ModelAndView("test");
				Department department = departmentService.getDepartment(employee);
				HttpSession session = request.getSession();
				session.setAttribute("LOGIN_USER",employee);
				session.setAttribute("userID", employee.getEmployee_ID());
				session.setAttribute("userName",employee.getEmployeeName());
				session.setAttribute("department", department.getDeptName());
				logger.info("Log --> :login.do 登录成功..");
				return mv;
			}
		}
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		session.removeAttribute("LOGIN_USER");
		session.invalidate();
		ModelAndView mv = new ModelAndView("login");
		logger.info("Log --> :logout.do 退出登录..");
		return mv;
	}
	
	@RequestMapping("/loginView")
	public ModelAndView loginView(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
}
