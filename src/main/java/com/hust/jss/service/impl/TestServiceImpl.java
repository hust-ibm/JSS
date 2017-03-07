package com.hust.jss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hust.jss.dao.AdminDao;
import com.hust.jss.entity.Admin;
import com.hust.jss.service.TestService;

@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {

	@Autowired
	private AdminDao adminDao;
	
	/* (non-Javadoc)
	 * @see com.hust.jss.service.impl.TestService#findAll()
	 */
	public List<Admin> findAll(){
		
		return adminDao.findAll();
	}
}
