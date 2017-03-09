package com.hust.jss.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hust.jss.entity.Task;

@Repository("taskService")
@Transactional
public interface TaskService {
	/**
	 * 添加作业
	 * @param task
	 * @return
	 */
	public int addTask(Task task);
	/**
	 * 根据作业id删除作业
	 * @param taskId
	 * @return
	 */
	public int deleteTaskByTaskId(Integer taskId);
	/**
	 * 删除所有作业
	 * @return
	 */
	public int deleteAll();
	/**
	 * 根据作业id更新作业规则路径信息
	 * @param taskId
	 * @param taskRule
	 * @return
	 */
	public int updateTaskRuleByTaskId(Integer taskId, String taskRule);
	/**
	 * 根据作业id更新作业截止时间
	 * @param taskId
	 * @param taskExpiry
	 * @return
	 */
	public int updatetaskExpiryByTaskId(Integer taskId, Date taskExpiry);
	/**
	 * 根据作业id更新作业最小大小
	 * @param taskId
	 * @param minsize
	 * @return
	 */
	public int updateMinsizeByTaskId(Integer taskId, Integer minsize);
	/**
	 * 根据作业id查找作业
	 * @param taskId
	 * @return
	 */
	public Task findTaskByTaskId(Integer taskId);
	/**
	 * 查找所有作业
	 * @return
	 */
	public List<Task> findAllTasks();
}
