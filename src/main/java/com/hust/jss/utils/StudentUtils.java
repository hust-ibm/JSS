package com.hust.jss.utils;

import java.util.List;

import com.hust.jss.entity.Student;

public class StudentUtils {
	public static Student resetStudentPassword(Student stu){
		stu.setStuPwd(stu.getStuId());
		return stu;
	}
	
	public static List<Student> initialStudentPassword(List<Student> list){
		for (Student stu : list) {
			stu.setStuPwd(stu.getStuId());
		}
		return list;
	}
}
