package com.hust.jss.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hust.jss.entity.Task;
import com.hust.jss.service.TaskService;

@Controller
public class TumController {
	@Autowired
	private TaskService taskService;
	
	@RequestMapping("/addjob")
	public String tumAddjob() {
		return "/addjob";
	}
	@RequestMapping("/addStudent")
	public String tumAddStudent() {
		return "/addStudent";
	}
	@RequestMapping("/exportResult")
	public String tumExportResult() {
		return "/exportResult";
	}
	@RequestMapping("/joblist")
	public String tumJoblist(){
		return "/joblist";
	}
	@RequestMapping("/managejob")
	public String tumManagejob(){
		return "/managejob";
	}
	
	@RequestMapping(value = "/pass")
	public String pass(){
		return "/pass";
	}
	@RequestMapping("/personResult")
	public String tumpersonResult(){
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
