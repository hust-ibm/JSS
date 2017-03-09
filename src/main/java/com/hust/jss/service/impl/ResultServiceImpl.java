package com.hust.jss.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hust.jss.dao.ResultDao;
import com.hust.jss.entity.Result;
import com.hust.jss.service.ResultService;

public class ResultServiceImpl implements ResultService {
	@Autowired
	private ResultDao resultDao;

	public int addResult(Result result) {
		// TODO Auto-generated method stub
		
		return resultDao.insertSelective(result);
	}

	public int deleteResultByStuId(String stuId) {
		// TODO Auto-generated method stub
		return resultDao.deleteByStuId(stuId);
	}

	public int deleteResultByTaskId(Integer taskId) {
		// TODO Auto-generated method stub
		return resultDao.deleteByTaskId(taskId);
	}

	public int deleteResultByResult(Result result) {
		// TODO Auto-generated method stub
		if(result.getStuId() ==null || result.getTaskId()==null)
			return 0;
		return resultDao.deleteByPrimaryKey(result);
	}

	public int updateResult(Result result) {
		// TODO Auto-generated method stub
		if(result.getStuId() ==null || result.getTaskId()==null)
			return 0;
		return resultDao.updateByPrimaryKeySelective(result);
	}

	public List<Result> findAllResult() {
		// TODO Auto-generated method stub
		List<Result> list = new ArrayList<Result>();
		list = resultDao.selectAll();
		return list;
	}

	public List<Result> findResultByStuId(String stuId) {
		// TODO Auto-generated method stub
		List<Result> list = new ArrayList<Result>();
		list = resultDao.selectByStuId(stuId);
		return list;
	}

	public List<Result> findResultByTaskId(Integer taskId) {
		// TODO Auto-generated method stub
		List<Result> list = new ArrayList<Result>();
		list = resultDao.selectByTaskId(taskId);
		return list;
	}

	public Result findResult(String stuId, Integer taskId) {
		// TODO Auto-generated method stub
		Result result = new Result();
		result.setStuId(stuId);
		result.setTaskId(taskId);
		return resultDao.selectByPrimaryKey(result);
	}

}
