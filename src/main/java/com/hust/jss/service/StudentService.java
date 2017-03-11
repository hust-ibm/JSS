package com.hust.jss.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hust.jss.entity.Student;


public interface StudentService {
	/**
	 * 批量插入学生信息（最好是完整的学生信息）
	 * @param list
	 * @return
	 */
	public int addStuList(List<Student> list) throws Exception;
	/**
	 * 插入单个学生信息
	 * @param stu
	 * @return
	 */
	public int addStu(Student stu) throws Exception;
	/**
	 * 根据学生id删除学生信息
	 * @param stuId
	 * @return
	 */
	public int deleteStuByStuId(String stuId) throws Exception;
	/**
	 * 根据id删除list中所有学生信息
	 * @return
	 */
	public int deleteAll(List<String> list) throws Exception;
	/**
	 * 根据学生学号修改密码
	 * @param stuId
	 * @param password
	 * @return
	 */
	public int updatePasswordByStuId(String stuId ,String password) throws Exception;
	/**
	 * 根据学生学号修改名字
	 * @param stuId
	 * @param name
	 * @return
	 */
	public int updateStuNameByStuId(String stuId,String name) throws Exception;
	/**
	 * 根据学生对象修改学生信息
	 * @param stu 必须包含学生学号
	 * @return
	 */
	public int updateStudentInfo(Student stu) throws Exception;
	/**
	 * 根据学号获取学生密码
	 * @param stuId
	 * @return
	 * @throws Exception 
	 */
	public String findStudentPwdByStuId(String stuId) throws Exception;
	/**
	 * 根据学号获取学生信息
	 * @param stuId
	 * @return
	 */
	public Student findStudentInfoByStuId(String stuId) throws Exception;
	/**
	 * 获取所有学生信息 
	 * @return
	 */
	public List<Student> findAllStudent() throws Exception;
	/**
	 * 判断登录是否成功，成功返回1，否则返回0,id不存在返回-1
	 * @param stuId
	 * @param pwd
	 * @return
	 */
	public int login(String stuId,String pwd);
}
