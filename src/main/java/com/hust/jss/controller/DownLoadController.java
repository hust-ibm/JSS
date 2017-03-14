package com.hust.jss.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hust.jss.service.DownLoadService;

@Controller
@RequestMapping("/download")
public class DownLoadController {
	
	@Autowired
	private DownLoadService downLoadService;
	
	/**
	 * 学生下载老师布置的作业文件（打包成.zip文件）
	 * 文件名形如：实验三贝叶斯分类实验.zip
	 * @param taskId 作业ID
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/task/{taskId}", method=RequestMethod.GET)
	public void downloadTask(@PathVariable("taskId") int taskId,
			HttpServletRequest request,HttpServletResponse response){
		System.out.println("taskId: "+taskId);
        try {
            downLoadService.downloadTask(taskId,request,response);
        }catch (ServletException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
	
	/**
	 * 老师下载学生上交的作业文件（打包成.zip文件）
	 * 文件名形如：Task1_M201672888.zip
	 * @param taskId 作业ID
	 * @param stuId 学生ID
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/result/{taskId}/{stuId}", method=RequestMethod.GET)
	public void downloadResult(@PathVariable("taskId") int taskId,
			@PathVariable("stuId") String stuId,
			HttpServletRequest request,HttpServletResponse response){
		System.out.println("taskId: "+taskId);
		System.out.println("stuId: "+stuId);
        try {
            downLoadService.downloadResult(taskId,stuId,request,response);
        }catch (ServletException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

