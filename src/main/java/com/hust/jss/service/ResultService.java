package com.hust.jss.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hust.jss.entity.Result;

public interface ResultService {
	/**
	 * 插入成绩
	 * @param result
	 * @return
	 */
	public int addResult(Result result) throws Exception;
	/**
	 * 根据学生id删除该学生所有成绩
	 * @param stuId
	 * @return
	 */
	public int deleteResultByStuId(String stuId) throws Exception;
	/**
	 * 根据作业id删除该作业所有人的成绩
	 * @param taskId
	 * @return
	 */
	public int deleteResultByTaskId(Integer taskId) throws Exception;
	/**
	 * 根据Result对象删除该作业成绩记录
	 * @param result 必须包含stuId,taskId
	 * @return
	 */
	public int deleteResultByResult(Result result) throws Exception;
	/**
	 * 根据Result修改该作业成绩
	 * @param result 必须包含stuId，taskId，以及修改后的其他信息
	 * @return
	 */
	public int updateResult(Result result) throws Exception;
	/**
	 * 查询所有人的所有作业成绩
	 * @return
	 */
	public List<Result> findAllResult() throws Exception;
	/**
	 * 根据学生学号查询该学生的所有作业成绩
	 * @param stuId
	 * @return
	 */
	public List<Result> findResultByStuId(String stuId) throws Exception;
	/**
	 * 根据作业号查询该作业所有学生的成绩
	 * @param taskId
	 * @return
	 */
	public List<Result> findResultByTaskId(Integer taskId) throws Exception;
	/**
	 * 根据学生学号和作业号查询该学生该作业的成绩
	 * @param stuId
	 * @param taskId
	 * @return
	 */
	public Result findResult(String stuId,Integer taskId) throws Exception;
}
