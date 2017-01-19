package com.huiyee.interact.bbs.dto;

import java.util.List;

import com.huiyee.interact.bbs.model.BBSComment;
import com.huiyee.interact.bbs.model.BBSTopic;

public class BBSCommentDto {

	private BBSTopic topic;
	private List<BBSComment> comments;
	private String head_img;

	public BBSTopic getTopic() {
		return topic;
	}

	public void setTopic(BBSTopic topic) {
		this.topic = topic;
	}

	public List<BBSComment> getComments() {
		return comments;
	}

	public void setComments(List<BBSComment> comments) {
		this.comments = comments;
	}

	public String getHead_img() {
		return head_img;
	}

	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}

}
