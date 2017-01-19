package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8409679236086544030L;
	private long id;
	private String title;
	private String content;
	private String imgurl;
	private Date countdownTime;
	private int countdownWeekday;
	private int hour;
	private int minute;
	private int second;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public int getCountdownWeekday() {
		return countdownWeekday;
	}
	public void setCountdownWeekday(int countdownWeekday) {
		this.countdownWeekday = countdownWeekday;
	}
	public Date getCountdownTime() {
		return countdownTime;
	}
	public void setCountdownTime(Date countdownTime) {
		this.countdownTime = countdownTime;
		if(countdownTime!= null){
			hour=countdownTime.getHours();
			minute=countdownTime.getMinutes();
			second=countdownTime.getSeconds();			
		}
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
}
