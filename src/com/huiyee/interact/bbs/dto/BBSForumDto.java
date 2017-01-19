package com.huiyee.interact.bbs.dto;

import java.util.List;

import com.huiyee.interact.bbs.model.BBSForum;
import com.huiyee.interact.bbs.model.BBSTopic;

public class BBSForumDto implements IDto {

	private BBSForum forum;
	private List<BBSTopic> topics;

	public BBSForum getForum() {
		return forum;
	}

	public void setForum(BBSForum forum) {
		this.forum = forum;
	}

	public List<BBSTopic> getTopics() {
		return topics;
	}

	public void setTopics(List<BBSTopic> topics) {
		this.topics = topics;
	}

}
