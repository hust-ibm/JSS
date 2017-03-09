package com.hust.jss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hust.jss.dao.StudentDao;
import com.hust.jss.entity.Student;
import com.hust.jss.service.StudentService;
import com.hust.jss.utils.StudentUtils;

public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;

	public int addStuList(List<Student> list) {
		// TODO Auto-generated method stub
		if (list.get(0).getStuPwd() == null)
			list = StudentUtils.initialStudentPassword(list);
		return studentDao.insertBatch(list);
	}

	public int addStu(Student stu) {
		// TODO Auto-generated method stub
		if (stu.getStuPwd() == null)
			stu = StudentUtils.resetStudentPassword(stu);
		return studentDao.insert(stu);
	}

	public int deleteStuByStuId(String stuId) {
		// TODO Auto-generated method stub
		return studentDao.deleteByStuId(stuId);
	}

	public int deleteAll(List<String> stuIdList) {
		// TODO Auto-generated method stub
		return studentDao.deleteBatch(stuIdList);
	}

	public int updatePasswordByStuId(String stuId, String pwd) {
		// TODO Auto-generated method stub
		Student stu = new Student();
		stu.setStuId(stuId);
		stu.setStuPwd(pwd);
		return studentDao.updateByStuIdSelective(stu);
	}

	public int updateStuNameByStuId(String stuId, String name) {
		// TODO Auto-generated method stub
		Student stu = new Student();
		stu.setStuId(stuId);
		stu.setStuName(name);
		return studentDao.updateByStuIdSelective(stu);
	}

	public int updateStudentInfo(Student stu) {
		// TODO Auto-generated method stub
		return studentDao.updateByStuId(stu);
	}

	public String findStudentPwdByStuId(String stuId) {
		// TODO Auto-generated method stub
		return studentDao.selectByStuId(stuId).getStuPwd();
	}

	public Student findStudentInfoByStuId(String stuId) {
		// TODO Auto-generated method stub
		return studentDao.selectByStuId(stuId);
	}

	public List<Student> findAllStudent() {
		// TODO Auto-generated method stub
		return studentDao.selectAllStu();
	}

	public int login(String stuId, String pwd) {
		// TODO Auto-generated method stub
		if (pwd == findStudentPwdByStuId(stuId))
			return 1;
		else
			return 0;
	}

}
