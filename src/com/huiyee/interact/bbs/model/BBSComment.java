package com.huiyee.interact.bbs.model;

import java.io.Serializable;
import java.util.Date;

public class BBSComment implements Serializable {

	private static final long serialVersionUID = 0L;

	private long id;
	private long TOPIC_ID;
	private long EDITER_ID;
	private long CREATER_ID;
	private Date CREATE_TIME;
	private String POSTER_IP;
	private Date EDIT_TIME;
	private String EDITER_IP;
	private int EDIT_COUNT;
	private int INDEX_COUNT;
	private int STATUS;
	private int IS_HIDDEN;
	private int TYPE_ID;
	private int ANONYMOUS;
	private int ZAN;

	private int total;
	private String creater;
	private String creater_img;
	private String content;
	private boolean liked;
	private String checked;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTOPIC_ID() {
		return TOPIC_ID;
	}

	public void setTOPIC_ID(long topic_id) {
		TOPIC_ID = topic_id;
	}

	public long getEDITER_ID() {
		return EDITER_ID;
	}

	public void setEDITER_ID(long editer_id) {
		EDITER_ID = editer_id;
	}

	public long getCREATER_ID() {
		return CREATER_ID;
	}

	public void setCREATER_ID(long creater_id) {
		CREATER_ID = creater_id;
	}

	public Date getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(Date create_time) {
		CREATE_TIME = create_time;
	}

	public String getPOSTER_IP() {
		return POSTER_IP;
	}

	public void setPOSTER_IP(String poster_ip) {
		POSTER_IP = poster_ip;
	}

	public Date getEDIT_TIME() {
		return EDIT_TIME;
	}

	public void setEDIT_TIME(Date edit_time) {
		EDIT_TIME = edit_time;
	}

	public String getEDITER_IP() {
		return EDITER_IP;
	}

	public void setEDITER_IP(String editer_ip) {
		EDITER_IP = editer_ip;
	}

	public int getEDIT_COUNT() {
		return EDIT_COUNT;
	}

	public void setEDIT_COUNT(int edit_count) {
		EDIT_COUNT = edit_count;
	}

	public int getINDEX_COUNT() {
		return INDEX_COUNT;
	}

	public void setINDEX_COUNT(int index_count) {
		INDEX_COUNT = index_count;
	}

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int status) {
		STATUS = status;
	}

	public int getIS_HIDDEN() {
		return IS_HIDDEN;
	}

	public void setIS_HIDDEN(int is_hidden) {
		IS_HIDDEN = is_hidden;
	}

	public int getTYPE_ID() {
		return TYPE_ID;
	}

	public void setTYPE_ID(int type_id) {
		TYPE_ID = type_id;
	}

	public int getANONYMOUS() {
		return ANONYMOUS;
	}

	public void setANONYMOUS(int anonymous) {
		ANONYMOUS = anonymous;
	}

	public int getZAN() {
		return ZAN;
	}

	public void setZAN(int zAN) {
		ZAN = zAN;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreater_img() {
		return creater_img;
	}

	public void setCreater_img(String creater_img) {
		this.creater_img = creater_img;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

}
