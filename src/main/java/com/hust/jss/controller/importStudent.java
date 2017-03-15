package com.hust.jss.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hust.jss.entity.Student;
import com.hust.jss.service.StudentService;
import com.hust.jss.service.TaskService;
import com.hust.jss.utils.Config;
import com.hust.jss.utils.UploadUtils;
import com.hust.jss.utils.readExcel;

@Controller
public class importStudent {
	
	@Autowired
	private StudentService studentservice;
	private StudentService taskservice;
	
	@RequestMapping(value="/importStudent",method = RequestMethod.POST)
	public String importStudent(@RequestParam(value = "uploadfile", required = false) MultipartFile[] uploadfile)
	{
		System.out.println("***********");
		String road=Config.title;
		UploadUtils up = new UploadUtils();
		readExcel rExcel=new readExcel();
		List<String> filename = new ArrayList<String>();
		filename = up.upload(uploadfile, road);
		for (String string : filename) { //遍历filename中所有的文件，内容存到result中
			List<List<Map<String, String>>> result = new ArrayList<List<Map<String,String>>>();//对应excel文件
			try {
				String s = road+"/"+string;
				result = rExcel.readExcelWithTitle(road+string);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (List<Map<String, String>> list : result) {
				for (Map<String, String> map : list) 
				{
					
					Student student = new Student();
					student.setStuId(map.get("学号"));
					student.setStuPwd(map.get("学号"));
					student.setStuName(map.get("姓名"));
					try {
						studentservice.addStu(student);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
				}
			}
		}
		return "redirect:/managejob";
		
	}
	

}
