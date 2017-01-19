package com.huiyee.interact.bbs.dao;

import java.util.List;

import com.huiyee.interact.bbs.model.BBSLike;

public interface IBBSLikeDao {

	public List<BBSLike> findLike(BBSLike like);

	public int addLike(BBSLike like);

}
