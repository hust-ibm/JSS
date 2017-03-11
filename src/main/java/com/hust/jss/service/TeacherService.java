package com.hust.jss.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hust.jss.entity.Teacher;


public interface TeacherService {
	/**
	 * 添加教师
	 * 
	 * @param teacher
	 * @return
	 */
	public int addTeacher(Teacher teacher) throws Exception;

	/**
	 * 根据id删除教师
	 * 
	 * @param teaId
	 * @return
	 */
	public int deleteTeacherByTeaId(String teaId) throws Exception;

	/**
	 * 删除所有教师
	 * 
	 * @return
	 */
	public int deleteAll() throws Exception;

	/**
	 * 根据id更新name
	 * 
	 * @param teaId
	 * @param name
	 * @return
	 */
	public int updateTeacherNameByTeaId(String teaId, String name) throws Exception;

	/**
	 * 根据id更新密码
	 * 
	 * @param teaId
	 * @param pwd
	 * @return
	 * @throws Exception 
	 */
	public int updatePasswordByTeaId(String teaId, String pwd) throws Exception;

	/**
	 * 根据id找教师
	 * 
	 * @param teaId
	 * @return
	 * @throws Exception 
	 */
	public Teacher findTeacherByTeaId(String teaId) throws Exception;

	/**
	 * 查询所有教师
	 * 
	 * @return
	 */
	public List<Teacher> findAll() throws Exception;

	/**
	 * 根据id查密码
	 * 
	 * @param teaId
	 * @return
	 * @throws Exception 
	 */
	public String findPwdByTeaId(String teaId) throws Exception;

	/**
	 * 教师登录，成功返回1，否则返回0
	 * 
	 * @param teaId
	 * @param pwd
	 * @return
	 */
	public int login(String teaId, String pwd);
}
