package com.huiyee.interact.bbs.mgr;

import java.util.List;

import com.huiyee.esite.dto.EsForum;
import com.huiyee.esite.dto.BBSAccount;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.bbs.model.BBS;
import com.huiyee.interact.bbs.model.BBSContent;
import com.huiyee.interact.bbs.model.BBSForum;

public interface IBBSMgr
{
public BBS findBBS(long id);

public List<BBSForum> findListByOwner(long id, long accid, String type);

public int saveTopicForContent(long entityId,String entityType,String entityName , long forumid,Account account);

/**
 * 
 * @param entityId
 * @param entityType
 * @param entityName
 * @param account
 * @return 2:有多个forum 1:成功 0:失败
 */
public int saveTopicForContent(long entityId,String entityType,String entityName, Account account);

/**
 * ajax获取踩 顶 评论
 * @param pids
 * @param entityType
 * @return
 */
public List<BBSContent> findData(List<String> pids, String entityType);

public List<BBSAccount> findBBSAccount(long owner);

public List<EsForum> findBbsForumByOwner(long owner);

public int updateBbsa(BBSAccount bbsa);

public int saveBathTopic(String bathPid, String entityType, long forumid, Account account);
}
