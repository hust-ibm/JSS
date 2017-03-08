package com.hust.jss.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hust.jss.entity.Task;

@Repository("taskDao")
public interface TaskDao {
	
	/**
	 * 插入一条作业记录
	 * @param task
	 * @return 成功插入结果数
	 */
	int insert(Task task);

	/**
	 * 插入一条作业记录
	 * 插入时判断属性值是否为空
	 * @param task
	 * @return
	 */
	int insertSelective(Task task);

	/**
	 * 通过taskId删除作业记录
	 * @param taskId
	 * @return
	 */
	int deleteByTaskId(Integer taskId);

	/**
	 * 通过taskId查询作业
	 * @param taskId
	 * @return
	 */
	Task selectByTaskId(Integer taskId);

	/**
	 * 查询所有作业
	 * @return
	 */
	List<Task> selectAllTask();
	
	/**
	 * 更新作业
	 * 更新时检查属性值非空
	 * @param task
	 * @return
	 */
	int updateByTaskIdSelective(Task task);

	/**
	 * 更新作业
	 * @param task
	 * @return
	 */
	int updateByTaskId(Task task);
}