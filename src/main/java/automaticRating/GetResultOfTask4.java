package automaticRating;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hust.jss.automaticrating.utils.ExcelReader;

/**
 * 对实验四(分类实验)进行自动评分
 * 
 * @author Chan
 *
 */
public class GetResultOfTask4 {
	// 三部分的分数、权重分别为0.25,0.5,0.25
	double weight1 = 0.25f;
	double weight2 = 0.50f;
	double weight3 = 0.25f;

	// 大数据与招聘的先验概率
	final double priorPROfBigData = 0.5307470078415187;
	final double priorPROfRecruit = 0.4692529921584812;
	// 每个单词在某类下的条件概率
	private static HashMap<String, double[]> conditionalPROfWord = new HashMap<String, double[]>();

	// 读取先验概率
	static {
		try {
			File file = new File(GetResultOfTask4.class.getResource("/bayesdata/bayesdata.txt").getPath());
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				String[] wordsinfo = line.split("\t");
				conditionalPROfWord.put(wordsinfo[0],
						new double[] { Double.valueOf(wordsinfo[1]), Double.valueOf(wordsinfo[2]) });
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			System.out.println("读取文件出错");
		}
	}

	public double judge(String filePath){
		double part1=0.0,part2=0.0,part3=0.0;
		if(checkPath(filePath)){
			part1 = judgePart1(filePath);
			part2 = judgePart2(filePath);
			part3 = judgePart3(filePath);
		}
		
		return part1*weight1+part2*weight2+part3*weight3;
	}
	
	private boolean checkPath(String filePath) {
		File classifyDir = new File(filePath);

		File[] checkDir = new File[3];
		File[] subClassifyDir = classifyDir.listFiles();
		for (int i = 0; i < subClassifyDir.length; i++) {
			if (!subClassifyDir[i].isDirectory()) {
				return false;
			}
			if (subClassifyDir[i].getName().equals("分类结果")) {
				checkDir[0] = subClassifyDir[i];
			} else if (subClassifyDir[i].getName().equals("训练集")) {
				checkDir[1] = subClassifyDir[i];
			} else if (subClassifyDir[i].getName().equals("训练器输出")) {
				checkDir[2] = subClassifyDir[i];
			}
		}
		boolean sub1 = checkSubPath(checkDir[0], "大数据", "招聘");
		boolean sub2 = checkSubPath(checkDir[1], "大数据", "招聘");
		boolean sub3 = checkSubPath(checkDir[2], "训练集条件概率.txt", "训练集先验概率.txt");

		return sub1 && sub2 && sub3;
	}

	private boolean checkSubPath(File file, String filename1, String filename2) {
		File[] files = file.listFiles();
		if (files.length != 2) {
			return false;
		}
		int checkfile = 0;
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().contains(filename1)) {
				checkfile++;
			} else if (files[i].getName().contains(filename2)) {
				checkfile++;
			}
		}
		if (checkfile != 2) {
			return false;
		}
		return true;
	}

	// part1判分:检查训练集爬取是否符合要求。
	private double judgePart1(String filePath) {
		double part1 = 0.0;
		File dir = new File(filePath + "/训练集");
		int[] excelRows = new int[2];
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			excelRows[i] = ExcelReader.readRows(files[i].getAbsolutePath());
		}

		for (int row : excelRows) {
			if (row < 500) {
				part1 += 30;
			} else if (row <= 850) {
				part1 += 32.5;
			} else if (row <= 1500) {
				part1 += 50;
			} else {
				part1 = +42.5;
			}
		}

		return part1;
	}

	// part2判分：检查训练器输出情况
	private double judgePart2(String filePath) {
		double part2 = 0;
		File dir = new File(filePath + "/训练集");
		int[] excelRows = new int[2];
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			excelRows[i] = ExcelReader.readRows(files[i].getAbsolutePath());
		}

		double[] correctPriorPR = new double[2];
		for (int i = 0; i < correctPriorPR.length; i++) {
			correctPriorPR[i] = (double) excelRows[i] / (excelRows[0] + excelRows[1]);
		}
		Arrays.sort(correctPriorPR);

		double[] stuPriorPR = new double[2];
		try {
			File filePriorPR = new File(filePath + "/训练器输出/训练集先验概率.txt");
			FileReader fr = new FileReader(filePriorPR);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			int row = 0;
			while (row < 2 && (line = br.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				String[] wordsinfo = line.split("\t");
				stuPriorPR[row++] = Double.valueOf(wordsinfo[1]);
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			System.out.println("读取训练集先验概率.txt出错。");
		}
		Arrays.sort(stuPriorPR);

		double stuSumPriorPR = stuPriorPR[0] + stuPriorPR[1];
		if (stuSumPriorPR <= 1.1 && stuSumPriorPR >= 0.9) {
			double p = Math.abs(correctPriorPR[0] - stuPriorPR[0]);
			if (p <= 1e-10) {
				part2 = 95 + Math.random() * 5.0;
			} else if (p <= 0.00005) {
				part2 = 90 + Math.random() * 5.0;
			} else if (p <= 0.0001) {
				part2 = 85 + Math.random() * 5.0;
			} else if (p <= 0.0005) {
				part2 = 80 + Math.random() * 5.0;
			} else if (p <= 0.001) {
				part2 = 75 + Math.random() * 5.0;
			} else {
				part2 = 50 + Math.random() * 10.0;
			}
		} else {
			part2 = 30 + Math.random() * 5.0;
		}

		return part2;
	}

	// part3判分：检查分类结果正确率
	private double judgePart3(String filePath) {
		double part3 = 0.0;
		File dir = new File(filePath + "/分类结果");
		// 随机选取一个文件进行评分
		int index = (int) (Math.random() * 2);
		File file = dir.listFiles()[index];
		List<List<String>> content = ExcelReader.read(file.getAbsolutePath());

		// 将分类结果存储于result中,只存储个数
		int[] result = new int[2];
		/**
		 * 利用bayes处理content，将结果存储于result中
		 */
		nativebayes(index, content, result);
		Arrays.sort(result);

		if(Math.abs(result[0]+result[1]-content.size()) < 50){
			int p = (int) Math.abs(result[1] - content.size());
			if (p <= 25) {
				part3 = 95 + Math.random() * 5.0;
			} else if (p <= 75) {
				part3 = 90 + Math.random() * 5.0;
			} else if (p <= 150) {
				part3 = 85 + Math.random() * 5.0;
			} else if (p <= 300) {
				part3 = 75 + Math.random() * 5.0;
			} else {
				part3 = 60 + Math.random() * 5.0;
			}
		}else{
			part3 = 0;
		}

		return part3;
	}

	// 利用bayes处理content，将结果存储于result中
	private void nativebayes(int index, List<List<String>> content, int[] result) {
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetResultOfTask4 g = new GetResultOfTask4();
		System.out.println(g.judge("C:\\Users\\Chan\\Desktop/classify"));
	}

}
