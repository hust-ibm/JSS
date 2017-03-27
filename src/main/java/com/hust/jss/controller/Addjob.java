package com.hust.jss.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.hust.jss.entity.Result;
import com.hust.jss.entity.Student;
import com.hust.jss.entity.Task;
import com.hust.jss.service.ResultService;
import com.hust.jss.service.StudentService;
import com.hust.jss.service.TaskService;
import com.hust.jss.utils.AutoCheckThread;
import com.hust.jss.utils.Config;
import com.hust.jss.utils.UploadUtils;

/**
 * 实现老师布置作业
 * 
 * @author
 *
 */

@Controller
public class Addjob {

	@Autowired
	private TaskService taskservice;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ResultService resultService;

	@RequestMapping(value = "/uploadtask", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, @RequestParam(value = "taskname") String taskName,
			@RequestParam(value = "datetime") String datetime,
			@RequestParam(value = "uploadfile", required = false) MultipartFile[] uploadfile) {
		String road = Config.title + taskName;
		UploadUtils up = new UploadUtils();
		if (up.uploadUtils(uploadfile, road)) {
			System.out.println("hahahah");
			Task task = new Task();
			task.setTaskName(taskName);
			task.setTaskPath(road);
			// 日期转换
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				Date date = sdf.parse(datetime);
				System.out.println("%%%%%" + date);
				task.setTaskExpiry(date);

			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// 把作业记录添加到数据库中
			try {
				taskservice.addTask(task);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 在result中添加记录，作业和所有的学生
			// 获取当前的所有学生ID
			List<Student> students = new ArrayList<Student>();
			try {
				students = studentService.findAllStudent();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 获取作业ID
			try {
				task = taskservice.findTaskByTaskName(taskName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (Student student : students) {

				Result result = new Result();
				result.setStuId(student.getStuId());
				result.setTaskId(task.getTaskId());
				try {
					resultService.addResult(result);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		// 开起线程到规定时间后开启自动评分
		Task t = null;
		while(t == null) {
			try {
				t = taskservice.findTaskByTaskName(taskName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				t = null;
				e.printStackTrace();
			}
		}
		new Thread(new AutoCheckThread(taskName)).start();
		return "redirect:/managejob";

	}

	@RequestMapping("/taskIsExist")
	public void taskIsExist(String taskname, HttpServletResponse response) {
		String flag = "true";
		List<Task> tasks = new ArrayList<Task>();
		// 存放所有的作业
		try {
			tasks = taskservice.findAllTasks();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Task task : tasks) {
			if (task.getTaskName().equals(taskname)) {
				flag = "false";
			}
		}
		JSONObject json = new JSONObject();
		json.put("getdata", flag);
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
