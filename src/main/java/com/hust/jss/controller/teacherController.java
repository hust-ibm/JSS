package com.hust.jss.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.hust.jss.entity.Result;
import com.hust.jss.entity.Student;
import com.hust.jss.service.ResultService;
import com.hust.jss.service.StudentService;

@Controller
public class teacherController {
	@Autowired
	private ResultService resultService;
	@Autowired
	private StudentService studentService;
	@RequestMapping("/query")
	public String queryResult(Integer taskId,Integer curPage,Model model){
		Integer pageSize = 30;
		Integer total = 0;
		try {
			total = resultService.findResultByTaskId(taskId).size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("flag", false);
			return "/queryResult";
		}
		Integer totalPage = (total%pageSize)==0?total/pageSize:(total/pageSize)+1;
		if(curPage == null || curPage <= 1)
			curPage = 1;
		if(curPage > totalPage)
			curPage = totalPage;
		List<Result> list = new ArrayList<Result>();
		List<String> stuNameList = new ArrayList<String>();
		try {
			list = resultService.findResultByTaskId(taskId,(curPage-1)*pageSize, pageSize);
			for (Result result : list) {
				String stuId = result.getStuId();
				Student stu = new Student();
				stu = studentService.findStudentInfoByStuId(stuId);
				stuNameList.add(stu.getStuName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("resultList", list);
		model.addAttribute("stuNameList", stuNameList);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("curPage", curPage);
		return "/queryResult";
	}
}
