package com.hust.jss.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hust.jss.entity.Student;
import com.hust.jss.entity.Teacher;
import com.hust.jss.service.StudentService;
import com.hust.jss.service.TeacherService;

/**
 * 修改密码Controller。包括修改学生密码、老师密码。
 * @author Chan
 *
 */
@Controller
public class PasswordController {

	@Autowired
	StudentService studentService;
	@Autowired
	TeacherService teacherService;
	
	@RequestMapping(value = "/modifyStuPassword", method = RequestMethod.POST)
	public String modifyStuPassword(HttpServletRequest request){
		String currentID=(String) request.getSession().getAttribute("id");
		try {
			Student stu = studentService.findStudentInfoByStuId(currentID);
			if(!stu.getStuPwd().equals(request.getParameter("oldPwd"))){
				return "/modifyPwdFailed";
			}
			studentService.updatePasswordByStuId(currentID, request.getParameter("newPwd"));
			
		} catch (Exception e) {
			e.printStackTrace();
			return "/modifyPwdFailed";
		}
		return "/modifyPwdSuccess";
	}
	
	@RequestMapping(value = "/modifyTeaPassword", method = RequestMethod.POST)
	public String modifyTeaPassword(HttpServletRequest request){
		String currentID=(String) request.getSession().getAttribute("id");
		try {
			Teacher tea = teacherService.findTeacherByTeaId(currentID);
			if(!tea.getTeaPwd().equals(request.getParameter("oldPwd"))){
				return "/modifyPwdFailed";
			}
			teacherService.updatePasswordByTeaId(currentID, request.getParameter("newPwd"));
			
		} catch (Exception e) {
			e.printStackTrace();
			return "/modifyPwdFailed";
		}
		return "/modifyPwdSuccess";
	}
}
