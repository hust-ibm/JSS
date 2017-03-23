package automaticRating;

import java.io.File;

import com.hust.jss.utils.Config;

public class GetResultOfTask2 {
	private int ansjResult;
	private int tfidfResult;
	private int distanceResult;
	private String taskPath;

	public GetResultOfTask2(){
		ansjResult = 85;
		tfidfResult = 70;
		distanceResult = 70;	
		taskPath = Config.task;
	}
	
	//自动判断Ansj小实验成绩
	private void checkAnsj(){
		File source = new File(""); // 源文件
		File target = new File(""); // 结果文件
		File target_add = new File(""); //附加的结果文件
		File userwords = new File(""); //用户自定义词典
		File stopwords = new File(""); //停用词表
				
	}
	
	//自动判断TfiDf小实验成绩
	private void checkTfidf(){
		
	}
	
	//自动判断相似度计算小实验成绩
	
	private void checkDistance(){
		
	}
	
	/**
	 * 根据作业和学生信息给学生自动评分
	 * @param taskId 作业id
	 * @param stuId 学生id
	 */	
	public int getTotalResult(Integer taskId,String stuId){
		taskPath = taskPath+taskId+"\\"+stuId+"\\";
		checkAnsj();
		return (int)(ansjResult*0.4+tfidfResult*0.3+distanceResult*0.3);
	}
}
