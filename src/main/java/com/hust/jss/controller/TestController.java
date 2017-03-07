package com.hust.jss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hust.jss.entity.Admin;
import com.hust.jss.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestService testService;
	
	@RequestMapping("/")
	public ModelAndView adminlist(){
		ModelAndView mv = new ModelAndView();
		
		List<Admin> adminList = testService.findAll();
		
		mv.addObject("adminList",adminList);
		mv.setViewName("index");
		
		return mv;
	}
	
}
