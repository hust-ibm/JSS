package com.hust.jss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hust.jss.dao.ResultDao;

import com.hust.jss.entity.Result;
import com.hust.jss.service.TestService;

@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {

	@Autowired
	private ResultDao resultDao;
	
	
	public List<Result> findAllByTaskId(Integer taskId){
		
		return resultDao.selectByTaskId(taskId);
	}
}
