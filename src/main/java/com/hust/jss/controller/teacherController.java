package com.hust.jss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.alibaba.fastjson.JSONObject;
import com.hust.jss.entity.Result;
import com.hust.jss.entity.Student;
import com.hust.jss.entity.Task;
import com.hust.jss.service.ResultService;
import com.hust.jss.service.StudentService;
import com.hust.jss.service.TaskService;

@Controller
public class teacherController {
	@Autowired
	private ResultService resultService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private TaskService taskService;
	@RequestMapping("/query")
	public String queryResult(Integer taskId,Integer curPage,Model model){		
		Integer pageSize = 30;
		Integer total = 0;
		try {
			total = resultService.findResultByTaskId(taskId).size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("flag", false);
			System.out.println("error!!!!!"+taskId);
			return "/queryResult";
		}
		Integer totalPage = (total%pageSize)==0?total/pageSize:(total/pageSize)+1;
		if(curPage == null || curPage <= 1)
			curPage = 1;
		if(curPage > totalPage)
			curPage = totalPage;
		System.out.println("curOpage"+curPage);
		List<Result> list = new ArrayList<Result>();
		List<String> stuNameList = new ArrayList<String>();
		System.out.println("taskId"+taskId);
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
		List<Task> taskList = new ArrayList<Task>();
		try {
			taskList = taskService.findAllTasks();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			list = null;
			e.printStackTrace();
		}
		model.addAttribute("taskList", taskList);
		model.addAttribute("resultList", list);
		model.addAttribute("stuNameList", stuNameList);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("curPage", curPage);
		model.addAttribute("taskId", taskId);
		return "/queryResult";
	}
	@RequestMapping(value ="/updateResult",method = RequestMethod.POST)
	public void updateResult(@RequestBody JSONObject json,HttpServletResponse response){
		String stuId = json.getString("stuId");
		Integer taskId = json.getInteger("taskId");
		Integer score = json.getInteger("score");
		Result result = new Result();
		result.setStuId(stuId);
		result.setTaskId(taskId);
		result.setScore(score);
		int flag = 0;
		try {
			flag = resultService.updateResult(result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			flag = 0;
			e.printStackTrace();
		}
		try {
			response.getWriter().print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block			
			e.printStackTrace();
		}
	}
	
	
	
}
