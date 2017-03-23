package com.hust.jss.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.hust.jss.entity.Result;
import com.hust.jss.service.ResultService;
import com.hust.jss.service.StudentService;
import com.hust.jss.service.TaskService;
import com.hust.jss.utils.Config;
import com.hust.jss.utils.UploadUtils;

@Controller
public class StudentController {
	
	@Autowired
	private ResultService resultService;
	@Autowired
	private TaskService taskService;
	
	//学生上交作业
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request,
			@RequestParam(value="taskid") int taskid,
			@RequestParam(value = "uploadfile", required = false) MultipartFile[] uploadfile)
	{
		String currentID=(String) request.getSession().getAttribute("id");
		String road=Config.task+taskid+"/"+currentID;
		UploadUtils up = new UploadUtils();
		if(up.uploadUtils(uploadfile, road))
		{
			//获得当前用户id
			System.out.println("$$$$$"+taskid);
			//根据作业ID，和用户ID。修改result表中的submit状态
			Result result = new Result();
			result.setStuId(currentID);
			result.setTaskId(taskid);
			result.setSubmit(true);
			try {
				resultService.updateResult(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return"redirect:/joblist";  //返回作业列表
		
	}
	
	
	@RequestMapping(value = "/checkTime",method = RequestMethod.POST)
	public void checkTime(@RequestBody JSONObject json,HttpServletResponse response){
		int flag = -1;
		Long curTime = System.currentTimeMillis();
		Long deadLine = 0l;
		Integer taskId = json.getInteger("taskId");
		try {
			System.out.println("taskid:"+taskId);
			Date expiry = taskService.findTaskByTaskId(taskId).getTaskExpiry();
			deadLine = expiry.getTime();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			flag = -1;
			e.printStackTrace();
		}
		System.out.println("curTime:"+curTime);
		System.out.println("deadLine:"+deadLine);
		if(curTime > deadLine){
			flag = 1;
		}else{
			flag = 0;
		}
		JSONObject reJson = new JSONObject();
		reJson.put("flag", flag);
		try {
			response.getWriter().print(reJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
