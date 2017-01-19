package com.huiyee.interact.bbs.mgr;

import java.util.List;

import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.interact.bbs.dao.IBBSLikeDao;
import com.huiyee.interact.bbs.model.BBSLike;

public class BBSLikeMgrImpl extends AbstractMgr implements IBBSLikeMgr {
	private IBBSLikeDao bbsLikeDao;

	public void setBbsLikeDao(IBBSLikeDao bbsLikeDao) {
		this.bbsLikeDao = bbsLikeDao;
	}

	@Override
	public int addLike(BBSLike like,long hyuid,long owner) {
		String stype="UPP";
		if(like.getAtype()==1)
		{
			stype="DOW";
		}
		this.updateHyUserBalance(hyuid, stype, owner, like.getEntityid());
		return bbsLikeDao.addLike(like);
	}

	@Override
	public List<BBSLike> findLike(BBSLike like) {
		return bbsLikeDao.findLike(like);
	}

}
