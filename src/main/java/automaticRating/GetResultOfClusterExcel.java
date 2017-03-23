package automaticRating;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import com.hust.jss.automaticrating.utils.zipUtils;
import com.hust.jss.utils.Config;

/**
 * 获取聚类实验学生提交的文件，
 * 计算得出学生的成绩
 * @author tankai
 *
 */
public class GetResultOfClusterExcel {

	private String stuId;
	
	private Integer taskId;
	
	
	private Integer score = 60;
	
	private String basePath;
	
	
	public GetResultOfClusterExcel(String stuId, Integer taskId){
		this.stuId = stuId;
		this.taskId = taskId;
		this.basePath = Config.task + taskId + "\\" + stuId;
	}
	public int getScore(){
		
		return score;
	}
	
	private void getBasicScore(){
		File dataExcel = new File(basePath + "\\" + "原始数据.xls");
		File demoZip = new File(basePath + "\\" + "KMeans.zip");
		File resultZip = new File(basePath + "\\" + "kmeansresult.zip");
		if(dataExcel.exists()){
			
		}else{
			score--;
		}
		if(!demoZip.exists()){
			score--;
		}
		if(resultZip.exists()){
			try {
				zipUtils.unzip(resultZip,basePath + "\\" + "kmeansresult");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			score--;
		}	
		
	}
	
	
	
}
