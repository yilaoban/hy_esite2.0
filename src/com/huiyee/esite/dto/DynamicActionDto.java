package com.huiyee.esite.dto;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.huiyee.esite.model.Emotions;
import com.huiyee.esite.model.EmotionsCategory;
import com.huiyee.esite.model.UserUploadRecord;

public class DynamicActionDto implements IDto{

	private long fppid;
	private File img;
	private String imgFileName;
	private String imgContentType;
	private Map<Long,Integer> zanList;
	
	private UserUploadRecord userUploadRecord;
	
	private long pageid;
	private String source;
	private String ip;
	private String terminal;
	private long contractid;
	private String email;
	
	private List<EmotionsCategory> emotionsCategory;
	private List<Emotions> emotions;
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

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

	public long getFppid() {
		return fppid;
	}

	public void setFppid(long fppid) {
		this.fppid = fppid;
	}

	public UserUploadRecord getUserUploadRecord() {
		return userUploadRecord;
	}

	public void setUserUploadRecord(UserUploadRecord userUploadRecord) {
		this.userUploadRecord = userUploadRecord;
	}

	public Map<Long, Integer> getZanList() {
		return zanList;
	}

	public void setZanList(Map<Long, Integer> zanList) {
		this.zanList = zanList;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public List<EmotionsCategory> getEmotionsCategory() {
		return emotionsCategory;
	}

	public void setEmotionsCategory(List<EmotionsCategory> emotionsCategory) {
		this.emotionsCategory = emotionsCategory;
	}

	public List<Emotions> getEmotions() {
		return emotions;
	}

	public void setEmotions(List<Emotions> emotions) {
		this.emotions = emotions;
	}

    public long getContractid() {
        return contractid;
    }

    public void setContractid(long contractid) {
        this.contractid = contractid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
