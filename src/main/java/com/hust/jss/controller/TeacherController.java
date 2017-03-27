package com.hust.jss.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.hust.jss.utils.FileUtils;
import com.hust.jss.utils.UploadUtils;

@Controller
public class TeacherController {
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
	
	
	@RequestMapping("/updatetask")
	public String updateTask(HttpServletRequest request,
			@RequestParam(value="taskid") Integer taskId,
			@RequestParam(value="taskname") String taskName,
			@RequestParam(value="datetime") String datetime,
			@RequestParam(value = "uploadfile", required = false) MultipartFile[] uploadfile){
		
		Task task = new Task();
		String oldUrl = "";
		String newUrl = "";
		String oldName = "";
		boolean flag = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			task = taskService.findTaskByTaskId(taskId);
			oldName = task.getTaskName();
			oldUrl = Config.title+task.getTaskName();
			newUrl = Config.title+taskName;
			task.setTaskName(taskName);
			task.setTaskExpiry(sdf.parse(datetime));
			taskService.updateTask(task);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File oldDir = new File(oldUrl); 
		if(uploadfile[0].getSize() == 0){//未选择上传文件,只修改文件夹名字
			flag = oldDir.renameTo(new File(newUrl));
			if(flag)
				System.out.println("文件名修改成功");
		}else{//选择上传文件，删除原来的文件，新建文件并写入上传的文件
			FileUtils fu= new FileUtils();
			fu.deleteFile(oldDir);
			UploadUtils up = new UploadUtils();
			up.uploadUtils(uploadfile, newUrl);
		}		
		Task t = null;
		while(t == null) {
			try {
				t = taskService.findTaskByTaskName(taskName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				t = null;
				e.printStackTrace();
			}
		}
		
		ThreadGroup group = Thread.currentThread().getThreadGroup();
		Thread thread = null;
		String threadName = oldName;
		while(group != null) {
            Thread[] threads = new Thread[(int)(group.activeCount() * 1.2)];
            int count = group.enumerate(threads, true);
            for(int i = 0; i < count; i++) {
            	System.out.println("线程名字："+threads[i].getName());
                if(threadName == threads[i].getName()) {
                	thread = threads[i];
                    break;
                }
            }
            group = group.getParent();
        }
		if(thread != null){
			thread.interrupt();
		}else{
			System.out.println("找不到线程！！！！");
		}
		new Thread(new AutoCheckThread(taskName)).start();
		
		return "redirect:/managejob"; 
	}
	
	
}
