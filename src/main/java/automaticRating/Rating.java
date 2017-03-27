package automaticRating;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hust.jss.entity.Result;
import com.hust.jss.entity.Task;
import com.hust.jss.service.ResultService;
import com.hust.jss.service.TaskService;
import com.hust.jss.utils.Config;
/**
 * 使用方法--用作业名去new对象，然后获取该作业的deadline,超过deadline的时间后，手动调用startRating开始评分
 * @author Jack
 *
 */
public class Rating {
	//存放作业名
	private String taskName;
	//存放作业id
	private Integer taskId;
	//存放学生文件路径
	private List<String> fileList;
	//TaskService
	@Autowired
	private TaskService taskService;
	//ResultService
	@Autowired
	private ResultService resultService;
	
	public Rating(String taskName){
		this.taskName = taskName;		
		List<String> fileList = new ArrayList<String>();
		init();
	}
	
	private void init(){
		Task task = new Task();
		try {
			task = taskService.findTaskByTaskName(taskName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			task.setTaskId(1);
		}
		this.taskId = task.getTaskId();
		String path = Config.task+taskId;
		File taskFiles = new File(path);
		if(taskFiles.isDirectory()){
			File[] files = taskFiles.listFiles();
			for (File file : files) {
				fileList.add(file.getName());
			}
		}else{
			System.out.println(path+"目录下没有作业存在！");
		}
	}
	
	public Date getDeadline(){
		Task task = new Task();
		try {
			task = taskService.findTaskByTaskName(taskName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return task.getTaskExpiry();
	}
	/*
	 * 已知作业id--taskId,
	 * 学生学号集合--fileList(存放着该作业下所有以提交作业的学生的学号)
	 */
	public void startRating(){
		switch (taskName) {
		case "爬虫":	
		{
			//实验一自动评分
		}
			break;
		case "数据预处理":	
		{
			for (String string : fileList) {
				GetResultOfTask2 task2 = new GetResultOfTask2();
				int result = task2.getTotalResult(taskId, string);
				Result rs = new Result();
				rs.setTaskId(taskId);
				rs.setStuId(string);
				rs.setScore(result);
				try {
					resultService.updateResult(rs);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("评分失败！");
					e.printStackTrace();
				}
			}
		}
			break;
		case "聚类":	
		{
			//实验三自动评分
		}
			break;
		case "朴素贝叶斯分类":	
		{
			//实验四自动评分
		}
			break;
		default:
			System.out.println("自动评分失败");
			break;
		}
	}
	
	
}
