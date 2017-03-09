package com.hust.jss.utils;

import java.util.List;

import com.hust.jss.entity.Teacher;

public class TeacherUtils {
	public static Teacher resetTeacherPassword(Teacher teacher){
		teacher.setTeaPwd(teacher.getTeaId());
		return teacher;
	}
	
	public static List<Teacher> initialTeacherPassword(List<Teacher> list){
		for (Teacher teacher : list) {
			teacher.setTeaPwd(teacher.getTeaId());
		}
		return list;
	}
}
