package com.hust.jss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hust.jss.entity.Result;
import com.hust.jss.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestService testService;
	
	@RequestMapping("/")
	public ModelAndView adminlist(){
		ModelAndView mv = new ModelAndView();
		
		List<Result> resultList = testService.findAllByTaskId(1);
		
		mv.addObject("resultList",resultList);
		mv.setViewName("index");
		
		return mv;
	}
	
}
