package automaticRating;

import java.util.List;
import com.hust.jss.utils.readExcel;

/**获取爬虫excel的成绩
 * 
 * @author qin
 *
 */
public class GetResultOfCrawlExcel {
	
	private double scaleOfRow=0.25;
	private double scaleOfCell=0.25;
	private double scaleOfValide=0.5;
	
	public int getResult(String filepath)
	{
		List<List<String>> excel = readExcel.read(filepath); //读取excel
		int row ,cell=0;
		row=excel.size();  //获取行数
	/*	for (List<String> list : excel) {
			cell = list.size() + cell; //计算每行的列数之和
		}
		cell = cell/row; */
		List<String> list = excel.get(0);
		cell = list.size(); //获取列数
		
		int result,type;
		type = getExcelType(filepath);
		result=(int) (setResultOfCell(cell, type)*scaleOfCell+setResultOfRow(row)*scaleOfRow+setResultOfValide(excel)*scaleOfValide);
		
		return result;
		
	}
	//判断Excel是招聘网站还是大数据类型的网站,是大数据网站为0、是招聘网站是1
	private int getExcelType(String filepath)
	{
		int result = 0;
		String[] sourceStrArray = filepath.split("\\\\");
		String type = sourceStrArray[sourceStrArray.length];
		if(type.indexOf("51job")>=0|type.indexOf("lagou")>=0|type.indexOf("zhaopin")>=0|type.indexOf("liepin")>=0|type.indexOf("ganji")>=0)
		{
			result = 1;
		}
		return result;
		
	}
	
	//通过列数判断分数
	private int setResultOfCell(int cell, int type) 
	{
		int result=0;
		if (type==1)//招聘类网站，要求10列
		{
			result = 60+(cell/10)*40;
			
		} 
		else 
		{
			result = 60+(cell/3)*40;
		}
		return 0;
		
	}
	//根据行数得出分数
	private int setResultOfRow(int row) 
	{
		int result=0;
		if (row>1000)
		{
			result = 100;
		}
		else {
			result=row/10;
		}
		return result;
		
	}

	//如果每条数据有一个字段为空则为无效数据，每10个无效数据扣一分
	private int setResultOfValide(List<List<String>> excel)
	{
		int invalide = 0;
		for (List<String> list : excel) 
		{
			for (String string : list) {
				if (string.equals(" ")) 
				{
					invalide++;
				}
			}
		}
		return 100-invalide/10;
		
	}
	

}
