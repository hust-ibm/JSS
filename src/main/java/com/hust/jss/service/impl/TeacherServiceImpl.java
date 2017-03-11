package com.hust.jss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hust.jss.dao.TeacherDao;
import com.hust.jss.entity.Teacher;
import com.hust.jss.service.TeacherService;
import com.hust.jss.utils.TeacherUtils;

@Service("teacherService")
@Transactional
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherDao teacherDao;

	public int addTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		if (teacher.getTeaPwd() == null)
			teacher = TeacherUtils.resetTeacherPassword(teacher);
		return teacherDao.insert(teacher);
	}

	public int deleteTeacherByTeaId(String teaId) {
		// TODO Auto-generated method stub
		return teacherDao.deleteByTeaId(teaId);
	}

	// 未实现 
	public int deleteAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateTeacherNameByTeaId(String teaId, String name) {
		// TODO Auto-generated method stub
		Teacher teacher = new Teacher();
		teacher.setTeaId(teaId);
		teacher.setTeaName(name);
		return teacherDao.updateByTeaIdSelective(teacher);
	}

	public int updatePasswordByTeaId(String teaId, String pwd){
		// TODO Auto-generated method stub
		Teacher teacher = new Teacher();
		teacher.setTeaId(teaId);
		teacher.setTeaPwd(pwd);
		return teacherDao.updateByTeaIdSelective(teacher);
	}

	public Teacher findTeacherByTeaId(String teaId) throws Exception{
		// TODO Auto-generated method stub
		return teacherDao.selectByTeaId(teaId);
	}

	public List<Teacher> findAll() {
		// TODO Auto-generated method stub
		return teacherDao.selectAllTeacher();
	}

	public int login(String teaId, String pwd) {
		// TODO Auto-generated method stub
		try {
			if (pwd.equals(findPwdByTeaId(teaId)))
				return 1;
			else
				return 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return -1;
		}
	}

	public String findPwdByTeaId(String teaId) throws Exception{
		// TODO Auto-generated method stub
		return teacherDao.selectByTeaId(teaId).getTeaPwd();
	}

}
