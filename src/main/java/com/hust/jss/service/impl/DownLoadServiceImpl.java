package com.hust.jss.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hust.jss.dao.ResultDao;
import com.hust.jss.dao.StudentDao;
import com.hust.jss.dao.TaskDao;
import com.hust.jss.entity.Result;
import com.hust.jss.entity.Student;
import com.hust.jss.entity.Task;
import com.hust.jss.service.DownLoadService;
import com.hust.jss.utils.Config;
import com.hust.jss.utils.DownloadUtils;
import com.hust.jss.utils.ExcelUtil;

@Service("downLoadService")
public class DownLoadServiceImpl implements DownLoadService {

	@Autowired
	private ResultDao resultDao;
	
	@Autowired
	private TaskDao taskDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Override
	public void downloadTask(int taskId,
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		//根据文件id在数据库中获取作业
		Task task = taskDao.selectByTaskId(taskId);
		//获取作业文件名（带扩展名）
        String filePath = task.getTaskPath();
        
        String zipfileName = task.getTaskName() + ".zip";//
        //"Task" + task.getTaskId()
        
        //作业的文件存放路径
        String basePath = Config.title; // "E:\\jss\\task\\";
        
        //所有作业的压缩文件的临时存放路径
        String zipPath = Config.tmpDir;//"E:\\jss\\tmp\\";
        
        System.out.println(basePath+filePath);
        
        File taskDir = new File(basePath+task.getTaskName());
        if(!taskDir.exists()){
        	System.out.println("作业文件不存在");
        	response.setCharacterEncoding("UTF-8");
            response.getWriter().write("<html><body><h2>Sorry!不存在该作业文件!</h2></body></html>");
        	return;
        }
       
        
        File file = new File(zipPath+zipfileName);
        //判断临时目录有没有该作业，有的话删除
        if(file.exists()){
        	System.out.println("文件存在？"+file.exists());
        	file.delete();
        }
        DownloadUtils.fileToZip(basePath+task.getTaskName(), zipPath, zipfileName);
        DownloadUtils.download(zipPath, zipfileName, request, response);
	}

	@Override
	public void downloadResult(int taskId,String stuId, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		 //作业的文件存放路径
        String basePath = Config.task; //"E:\\jss\\result\\";
     
        //所有作业的压缩文件的临时存放路径
        String zipPath = Config.tmpDir;//"E:\\jss\\tmp\\";
                
        File resultDir = new File(basePath+taskId+"\\"+stuId);
        if(!resultDir.exists()){
        	System.out.println("作业文件不存在");
        	response.setCharacterEncoding("UTF-8");
            response.getWriter().write("<html><body><h2>Sorry!不存在该作业文件!</h2></body></html>");
        	return;
        }
        
        Task task = taskDao.selectByTaskId(taskId);
        
        String zipfileName = task.getTaskName() + "_" + stuId + ".zip";
        File file = new File(zipPath+zipfileName);
        //判断临时目录有没有该作业，有的话删除
        if(file.exists()){
        	System.out.println("文件存在？"+file.exists());
        	file.delete();        	
        }
        DownloadUtils.fileToZip(basePath+taskId+"\\"+stuId, zipPath, zipfileName);
        DownloadUtils.download(zipPath, zipfileName, request, response);
	}



	@Override
	public void downloadAllResults(List<Integer> taskIdList, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//所有学生的集合
		List<Student> stuList = new ArrayList<Student>();

		//存放作业名的列表
		List<String> tasknameList = new ArrayList<String>();
		//学生-作业（多个）分数 Map
		Map<Student,List<Integer>> stuScoreMap = new HashMap<>();
			
		//取第一个ID查询作业结果为了获取学生ID
		stuList = studentDao.selectAllStu();
		//遍历学生
		for(Student s : stuList){
			String stuId = s.getStuId();
			//分数列表
			List<Integer> scoreList = new ArrayList<Integer>();
			for (int taskId : taskIdList) {
				Result r = new Result();
				r.setStuId(stuId);
				r.setTaskId(taskId);
				if(stuList.indexOf(s) == 0){
					tasknameList.add(taskDao.selectByTaskId(taskId).getTaskName());
				}
				Integer score = 0;
				if(resultDao.selectByPrimaryKey(r) != null){
					score = resultDao.selectByPrimaryKey(r).getScore();
				}
				scoreList.add(Integer.valueOf(score));
//				System.out.println(score);
			}
			
			stuScoreMap.put(s, scoreList);
		}
		
		String excelPath = Config.tmpDir;//"F:\\upload\\tmp\\";
		String excelName = "学生成绩.xls";
		
		//创建成绩excel表
		toExcel(tasknameList,stuScoreMap,excelPath+excelName);
		
		//下载excel文件
		DownloadUtils.download(excelPath, excelName, request, response);
	}
	
	 public void toExcel(List<String> tasknameList ,Map<Student,List<Integer>> stuScoreMap , String path) {
	 	
		int colSize = tasknameList.size();
        System.out.println("colSize:"+colSize);

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("学生成绩");

        //存放表格数据的二维数组
        Object[][] value = new Object[stuScoreMap.size() + 1][2+colSize];
        
        //表头数据存入value数组---表格第一行
        value[0][0] = "学号";
        value[0][1] = "姓名";
        for (int m = 2; m < colSize + 2; m++) {
            value[0][m] = tasknameList.get(m-2);
            System.out.println("表头："+value[0][m]);
        }
        
        //从表格第二行开始，遍历map，将学生及作业分数保存到value数组
        int i = 1;//行数i
        Iterator iter = stuScoreMap.entrySet().iterator();
	     while (iter.hasNext()) {
		     Entry entry = (Entry) iter.next();
		     Student s = (Student) entry.getKey();
		     List<Integer> scoreList = (List) entry.getValue();
		     System.out.println(scoreList.size());
		     value[i][0] = s.getStuId();
		     value[i][1] = s.getStuName();
		     
		     for(int j = 0 ; j < scoreList.size() ; j++){
		    	 value[i][j+2] = scoreList.get(j);
		    	 
		     }
		     //行数i自增
		     i++;
	    }
        //把数据写入到Excel
        ExcelUtil.writeArrayToExcel(wb, sheet, stuScoreMap.size() + 1, colSize+2, value);

        ExcelUtil.writeWorkbook(wb, path);
//	     ExcelUtil.writeToExcel(path, stuScoreMap.size() + 1, colSize+2, value);
    }
}
