package com.hust.jss.service;

import java.util.List;


import com.hust.jss.entity.Result;


public interface TestService {

	List<Result> findAllByTaskId(Integer taskId);

}