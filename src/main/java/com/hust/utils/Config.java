package com.hust.utils;

import java.io.File;

public class Config {
	//项目跟路径
//	public static String bacePath = System.getProperty("user.dir");
	//停用词文件路径
	public static String stopWordPath = Config.class.getResource("/defaultDic/stopwords.txt").getPath();
	//用户词表的存放路径
	public static String userWordsPath = Config.class.getResource("/defaultDic/userwords.txt").getPath();
	//Canopy聚类结果文件路径
	public static String CANOPY_RESULT_PATH = "result/cluster/canopy/";
	//KMeans聚类结果文件路径
	public static String KMEANS_RESULT_PATH = "result/cluster/kmeans/";
	
}
