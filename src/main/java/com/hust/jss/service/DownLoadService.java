package com.hust.jss.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 下载Service
 * 包括学生下载作业（题目），老师下载学生作业
 * 老师导出成绩等功能
 * @author tankai
 *
 */
public interface DownLoadService {
	
	/**
	 * 老师下载学生上交的作业（可能包含多个文件，打包下载）
	 * @param taskId 作业ID
	 * @param stuId 学生ID
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	void downloadResult(int taskId,String stuId,  HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	void downloadAllResult(int taskId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	/**
	 * 学生下载老师布置的作业（可能包含多个文件，打包下载）
	 * @param taskId 作业ID
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	void downloadTask(int taskId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}