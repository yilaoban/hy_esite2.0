package com.huiyee.interact.bbs.mgr;

import java.util.List;

import com.huiyee.interact.bbs.model.BBSLike;

public interface IBBSLikeMgr {

	public List<BBSLike> findLike(BBSLike like);

	public int addLike(BBSLike like,long hyuid,long owner);

}
