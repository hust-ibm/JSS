package com.hust.jss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tum")
public class TumController {
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
	
}
