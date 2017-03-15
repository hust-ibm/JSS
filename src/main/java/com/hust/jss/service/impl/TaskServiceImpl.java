package com.hust.jss.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hust.jss.dao.TaskDao;
import com.hust.jss.entity.Task;
import com.hust.jss.service.TaskService;

@Service("taskService")
@Transactional
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskDao taskDao;

	public int addTask(Task task) {
		// TODO Auto-generated method stub
		return taskDao.insert(task);
	}

	public int deleteTaskByTaskId(Integer taskId) {
		// TODO Auto-generated method stub
		return taskDao.deleteByTaskId(taskId);
	}

	// 暂未实现 
	public int deleteAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateTaskRuleByTaskId(Integer taskId, String taskRule) {
		// TODO Auto-generated method stub
		Task task = new Task();
		task.setTaskId(taskId);
		task.setTaskRule(taskRule);
		return taskDao.updateByTaskIdSelective(task);
	}

	public int updatetaskExpiryByTaskId(Integer taskId, Date taskExpiry) {
		// TODO Auto-generated method stub
		Task task = new Task();
		task.setTaskId(taskId);
		task.setTaskExpiry(taskExpiry);
		return taskDao.updateByTaskIdSelective(task);
	}

	public int updateMinsizeByTaskId(Integer taskId, Integer minsize) {
		Task task = new Task();
		task.setTaskId(taskId);
		task.setTaskMinsize(minsize);
		return taskDao.updateByTaskIdSelective(task);
	}

	public Task findTaskByTaskId(Integer taskId) {
		// TODO Auto-generated method stub
		return taskDao.selectByTaskId(taskId);
	}

	public List<Task> findAllTasks() {
		// TODO Auto-generated method stub
		return taskDao.selectAllTask();
	}

	@Override
	public Task findTaskByTaskName(String taskName) throws Exception {
		// TODO Auto-generated method stub
		return taskDao.selectByTaskName(taskName);
	}

}
