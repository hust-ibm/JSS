package com.hust.jss.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hust.jss.entity.Task;


public interface TaskService {
	/**
	 * 添加作业
	 * @param task
	 * @return
	 */
	public int addTask(Task task) throws Exception;
	/**
	 * 根据作业id删除作业
	 * @param taskId
	 * @return
	 */
	public int deleteTaskByTaskId(Integer taskId) throws Exception;
	/**
	 * 删除所有作业
	 * @return
	 */
	public int deleteAll() throws Exception;
	/**
	 * 根据作业id更新作业规则路径信息
	 * @param taskId
	 * @param taskRule
	 * @return
	 */
	public int updateTaskRuleByTaskId(Integer taskId, String taskRule) throws Exception;
	/**
	 * 根据所给对象选择更新task
	 * @param task
	 * @return
	 * @throws Exception
	 */
	public int updateTask(Task task)throws Exception;
	/**
	 * 根据作业id更新作业截止时间
	 * @param taskId
	 * @param taskExpiry
	 * @return
	 */
	public int updatetaskExpiryByTaskId(Integer taskId, Date taskExpiry) throws Exception;
	/**
	 * 根据作业id更新作业最小大小 
	 * @param taskId
	 * @param minsize
	 * @return
	 */
	public int updateMinsizeByTaskId(Integer taskId, Integer minsize) throws Exception;
	/**
	 * 根据作业id查找作业
	 * @param taskId
	 * @return
	 */
	public Task findTaskByTaskId(Integer taskId) throws Exception;
	
	/**
	 * 根据作业name查找作业
	 * @param taskName
	 * @return
	 */
	public Task findTaskByTaskName(String taskName) throws Exception;
	
	/**
	 * 查找所有作业
	 * @return
	 */
	public List<Task> findAllTasks() throws Exception;
}
