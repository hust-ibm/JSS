package com.hust.jss.entity;

public class Teacher {
	//	教师ID
    private String teaId;
    //教师姓名
    private String teaName;
    //教师登录密码 
    private String teaPwd;

    public String getTeaId() {
        return teaId;
    }

    public void setTeaId(String teaId) {
        this.teaId = teaId == null ? null : teaId.trim();
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName == null ? null : teaName.trim();
    }

    public String getTeaPwd() {
        return teaPwd;
    }

    public void setTeaPwd(String teaPwd) {
        this.teaPwd = teaPwd == null ? null : teaPwd.trim();
    }
}