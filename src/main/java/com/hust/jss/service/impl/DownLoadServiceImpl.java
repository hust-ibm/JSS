package com.hust.jss.service.impl;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hust.jss.dao.ResultDao;
import com.hust.jss.dao.TaskDao;
import com.hust.jss.entity.Task;
import com.hust.jss.service.DownLoadService;
import com.hust.jss.utils.Config;
import com.hust.jss.utils.DownloadUtils;

@Service("downLoadService")
public class DownLoadServiceImpl implements DownLoadService {

	@Autowired
	private ResultDao resultDao;
	
	@Autowired
	private TaskDao taskDao;
		
	@Override
	public void downloadTask(int taskId,
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		//根据文件id在数据库中获取作业
		Task task = taskDao.selectByTaskId(taskId);
		//获取作业文件名（带扩展名）
        String filePath = task.getTaskPath();
        
        //作业的文件存放路径
        String basePath = Config.task; // "E:\\jss\\task\\";
        
        //所有作业的压缩文件的临时存放路径
        String zipPath = Config.tmpDir;//"E:\\jss\\tmp\\";
        
        System.out.println(basePath+filePath);
        
        String zipfileName = task.getTaskName() + ".zip";//
        //"Task" + task.getTaskId()
        
        File file = new File(zipPath+zipfileName);
        
        if(!file.exists()){
        	System.out.println(file.exists());
        	DownloadUtils.fileToZip(basePath, zipPath, zipfileName);
        	
        }
        
        DownloadUtils.download(zipPath, zipfileName, request, response);
        
//        DownloadUtils.download(basePath, "实验四.docx", request, response);
     
	}

	@Override
	public void downloadResult(int taskId,String stuId, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		 //作业的文件存放路径
        String basePath = Config.result; //"E:\\jss\\result\\";
     
        //所有作业的压缩文件的临时存放路径
        String zipPath = Config.tmpDir;//"E:\\jss\\tmp\\";
                
        File resultDir = new File(basePath+taskId+"\\"+stuId);
        if(!resultDir.exists()){
        	System.out.println("作业文件不存在");
        	return;
        }
        
        String zipfileName = "Task" + taskId + "_" + stuId + ".zip";
        File file = new File(zipPath+zipfileName);
        
        if(!file.exists()){
        	System.out.println(file.exists());
        	DownloadUtils.fileToZip(basePath+taskId+"\\"+stuId, zipPath, zipfileName);
        	
        }
        
        DownloadUtils.download(zipPath, zipfileName, request, response);
	}



	@Override
	public void downloadAllResult(int taskId, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
}
