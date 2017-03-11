package com.hust.jss.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.hust.jss.entity.Student;
import com.hust.jss.entity.Teacher;
import com.hust.jss.service.StudentService;
import com.hust.jss.service.TeacherService;

@Controller
public class LoginController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;

	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public String studentLogin(Student stu, HttpServletRequest request) {
		Student student = new Student();
		try {
			student = studentService.findStudentInfoByStuId(stu.getStuId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("id", student.getStuId());
		request.getSession().setAttribute("name", student.getStuName());
		String name = (String) request.getSession().getAttribute("name");
		System.out.println(name);
		return "/student";
	}

	@RequestMapping(value = "/teacher", method = RequestMethod.POST)
	public String teacherLogin(Student stu, HttpServletRequest request) {
		Teacher teacher = new Teacher();
		System.out.println(stu.getStuId());
		try {
			teacher = teacherService.findTeacherByTeaId(stu.getStuId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("id", teacher.getTeaId());
		request.getSession().setAttribute("name", teacher.getTeaName());
		return "/teacher";
	}

	@RequestMapping(value = "/checkpwd", method = RequestMethod.POST, params = "type=student")
	public void checkStuPwd(@RequestBody JSONObject json, HttpServletResponse response) {
		JSONObject reJson = new JSONObject();
		String id = json.getString("id");
		String pwd = json.getString("pwd");
		int flag = studentService.login(id, pwd);
		System.out.println("flag" + flag);
		try {
			reJson.put("flag", flag);
			response.getWriter().print(reJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/checkpwd", method = RequestMethod.POST, params = "type=teacher")
	public void checkTeaPwd(@RequestBody JSONObject json, HttpServletResponse response) {
		JSONObject reJson = new JSONObject();
		String id = json.getString("id");
		String pwd = json.getString("pwd");
		int flag = teacherService.login(id, pwd);
		System.out.println("flag" + flag);
		try {
			reJson.put("flag", flag);
			response.getWriter().print(reJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
