package com.hust.jss.utils;

import java.util.Date;

import automaticRating.Rating;

public class AutoCheckThread implements Runnable{
	private String name;
	
	public AutoCheckThread(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Rating rate = new Rating(name);
		Date deadline = rate.getDeadline();
		Long curTime = System.currentTimeMillis();
		while(curTime < deadline.getTime()){
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			curTime = System.currentTimeMillis();
		}
		rate.startRating();
	}

}
