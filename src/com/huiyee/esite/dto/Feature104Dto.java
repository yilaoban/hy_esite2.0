package com.huiyee.esite.dto;

import java.io.File;
import java.io.Serializable;

import com.huiyee.esite.model.Notice;

public class Feature104Dto implements IDto,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Notice notice;
	private File img;
	private String imgFileName;
	private String imgContentType;

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getImgContentType() {
		return imgContentType;
	}

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}
}
