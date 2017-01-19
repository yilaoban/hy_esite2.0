package com.huiyee.interact.bbs.dao;

import java.util.List;

import com.huiyee.esite.dto.EsForum;
import com.huiyee.esite.dto.BBSAccount;
import com.huiyee.interact.bbs.model.BBS;
import com.huiyee.interact.bbs.model.BBSCategory;
import com.huiyee.interact.bbs.model.BBSContent;
import com.huiyee.interact.bbs.model.BBSForum;

public interface IBBSDao
{
	public BBS findBBS(long id);

	public List<BBSCategory> findCListByOwner(long owner, String typeName);

	public List<BBSForum> findListByCid(long bbsid, long accid);

	public long findBBSCateByTypeName(String typeName, long owner);

	public long addBBSCate(String typeName, long id);

	public long saveBBSForum(long cateid, String entityName, long forumer, String string);

	public BBSForum findForumById(long forumid);

	public BBSContent findData(String pid, int entitytype);
	
	public List<BBSAccount> findBBSAccount(long owner);

	public List<EsForum> findBbsForumByOwner(long owner);

	public int saveBBsAccount(BBSAccount bbsa);

	public int deleteBBsAccount(BBSAccount bbsa);
}
