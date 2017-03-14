package com.hust.jss.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hust.jss.entity.Result;
import com.hust.jss.entity.Task;
import com.hust.jss.service.ResultService;
import com.hust.jss.service.TaskService;

@Controller
public class TumController {
	@Autowired
	private TaskService taskService;
	@Autowired
	private ResultService resultService;
	@RequestMapping("/addjob")
	public String tumAddjob() {
		return "/addjob";
	}
	@RequestMapping("/addStudent")
	public String tumAddStudent() {
		return "/addStudent";
	}
	@RequestMapping("/exportResult")
	public String tumExportResult(Model model) {
		List<Task> taskList = new ArrayList<Task>();
		try {
			taskList = taskService.findAllTasks();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("taskList", taskList);
		return "/exportResult";
	}
	@RequestMapping("/joblist")
	public String tumJoblist(HttpServletRequest request,Model model){
		String stuName = (String) request.getSession().getAttribute("stuName");
		String stuId = "";
		//验证当前用户是否还在线
		if(stuName == null)
			return "redirect:/";
		else
			stuId =  (String) request.getSession().getAttribute("id");
		List<Task> taskList = new ArrayList<Task>();
		List<Result> resultList = new ArrayList<Result>();
		try {
			taskList = taskService.findAllTasks();
			for (Task task : taskList) {
				Result result = new Result();
				result = resultService.findResult(stuId, task.getTaskId());
				resultList.add(result);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("taskList", taskList);
		model.addAttribute("resultList", resultList);
		return "/joblist";
	}
	@RequestMapping("/managejob")
	public String tumManagejob(Model model){
		List<Task> taskList = new ArrayList<Task>();
		try {
			taskList = taskService.findAllTasks();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("taskList", taskList);
		return "/managejob";
	}
	
	@RequestMapping(value = "/pass")
	public String pass(){
		return "/pass";
	}
	@RequestMapping("/personResult")
	public String tumpersonResult(HttpServletRequest request,Model model){
		String stuName = (String) request.getSession().getAttribute("stuName");
		String stuId = "";
		//验证当前用户是否还在线
		if(stuName == null)
			return "redirect:/";
		else
			stuId =  (String) request.getSession().getAttribute("id");
		List<Task> taskList = new ArrayList<Task>();
		List<Result> resultList = new ArrayList<Result>();
		try {
			taskList = taskService.findAllTasks();
			for (Task task : taskList) {
				Result result = new Result();
				result = resultService.findResult(stuId, task.getTaskId());
				resultList.add(result);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("taskList", taskList);
		model.addAttribute("resultList", resultList);
		return "/personResult";
	}	
	
	@RequestMapping("/queryResult")
	public String tumQueryResult(Model model){
		List<Task> list = new ArrayList<Task>();
		try {
			list = taskService.findAllTasks();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			list = null;
			e.printStackTrace();
		}
		model.addAttribute("taskList", list);
		return "/queryResult";
	}
	
}
