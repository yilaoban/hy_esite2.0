package com.huiyee.esite.mgr.imp;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dao.ICommunityUserDao;
import com.huiyee.esite.dao.IHyUserDao;
import com.huiyee.esite.dto.CommunityDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.mgr.ICommunityUserMgr;
import com.huiyee.esite.model.BBSJfLevel;
import com.huiyee.esite.model.BBSJfRule;
import com.huiyee.esite.model.HyUser;
import com.huiyee.interact.bbs.dao.IBBSUserDao;
import com.huiyee.interact.bbs.model.BBSUser;

public class CommunityUserMgrImpl implements ICommunityUserMgr
{
	private ICommunityUserDao communityUserDao;
	private IHyUserDao hyUserDao;
	private IBBSUserDao bbsUserDao;
	
	public void setBbsUserDao(IBBSUserDao bbsUserDao)
	{
		this.bbsUserDao = bbsUserDao;
	}

	public void setHyUserDao(IHyUserDao hyUserDao)
	{
		this.hyUserDao = hyUserDao;
	}

	public void setCommunityUserDao(ICommunityUserDao communityUserDao)
	{
		this.communityUserDao = communityUserDao;
	}

	@Override
	public int saveBBSUserJfRule(int action, int jifen,long owner)
	{
		BBSJfRule jfRule = communityUserDao.findBBSUSErJfRuleByAction(action,owner);
		if(jfRule != null){
			return 0;
		}else{
			int res = communityUserDao.saveBBSUserJfRule(action, jifen,owner);
			return res;
		}
	}

	@Override
	public IDto findBBSUserJfRuleList(long owner)
	{
		CommunityDto dto = new CommunityDto();
		List<BBSJfRule> list = communityUserDao.findBBSUserJfRuleList(owner);
		if(list != null && list.size()>0){
			dto.setBbsJfRuleList(list);
		}
		return dto;
	}

	@Override
	public IDto findBBSUSErJfRuleById(long ruleid)
	{
		CommunityDto dto = new CommunityDto();
		BBSJfRule jfRule = communityUserDao.findBBSUSErJfRuleById(ruleid);
		dto.setJfRule(jfRule);
		return dto;
	}

	@Override
	public int updateJfRuleById(long ruleid, int jifen)
	{
		return communityUserDao.updateJfRuleById(ruleid, jifen);
	}

	@Override
	public int deleteJfRule(long ruleid)
	{
		return communityUserDao.deleteJfRule(ruleid);
	}

	@Override
	public IDto findBBSUserJflevelList(long owner)
	{
		CommunityDto dto = new CommunityDto();
		List<BBSJfLevel> list = communityUserDao.findBBSUserJflevelList(owner);
		if(list != null && list.size()>0){
			dto.setBbsJfLevelList(list);
		}
		return dto;
	}

	@Override
	public int saveBBSUserJfLevel(String level_name, long require_jf, long owner)
	{
		List<BBSJfLevel> jfLevel = communityUserDao.findBBSUserJflevelList(owner);
		if(jfLevel != null && jfLevel.size()>0){
			for(int i=0;i<jfLevel.size();i++){
				if(jfLevel.get(i).getRequire_jf() > require_jf){
					return 0;
				}
			}
		}
		int	res = communityUserDao.saveBBSUserJfLevel(level_name, require_jf,owner,jfLevel.size()+1);
		return res;
	}

	@Override
	public IDto findBBSUserJfLevelById(long levelid)
	{
		CommunityDto dto = new CommunityDto();
		BBSJfLevel jfLevel = communityUserDao.findBBSUserJfLevelById(levelid);
		dto.setJfLevel(jfLevel);
		return dto;
	}

	@Override
	public int updateBBSUserJfLevel(String level_name, long require_jf, long levelid,long owner)
	{
		return communityUserDao.updateBBSUserJfLevel(level_name, require_jf, levelid);
	}

	@Override
	public int deleteJfLevel(long levelid)
	{
		return communityUserDao.deleteJfLevel(levelid);
	}

	@Override
	public IDto findBBSUserInfoList(long owner,int pageId)
	{
		CommunityDto dto = new CommunityDto();
		int total = communityUserDao.findBBSUserInfoTotal(owner);
		int start = (pageId - 1) * IInteractConstants.INTERACT_BBS_LIMIT;
		if(total > 0){
			List<BBSUser> bbsUserList = communityUserDao.findBBSUserInfoList(owner,start,IInteractConstants.INTERACT_BBS_LIMIT);
			if(bbsUserList != null && bbsUserList.size()>0){
				for(int i=0;i<bbsUserList.size();i++){
					int toptotal = communityUserDao.findTopTotalByUid(bbsUserList.get(i).getId());
					int uptotal = communityUserDao.findCountByUid(bbsUserList.get(i).getId(),0);
					int downtotal = communityUserDao.findCountByUid(bbsUserList.get(i).getId(),1);
					bbsUserList.get(i).setToptotal(toptotal);
					bbsUserList.get(i).setUptotal(uptotal);
					bbsUserList.get(i).setDowntotal(downtotal);
				}
			}
			dto.setBbsUserList(bbsUserList);
		}
		Pager pager = new Pager(pageId, total, IInteractConstants.INTERACT_BBS_LIMIT);
		dto.setPager(pager);
		return dto;
	}

	@Override
	public IDto findBBSUserInfoListByNicknameAndLevelid(long owner, int pageId, String nickname, long levelid)
	{
		CommunityDto dto = new CommunityDto();
		int total = communityUserDao.findBBSUserInfoListByNicknameAndLevelidTotal(owner,nickname,levelid);
		int start = (pageId - 1) * IInteractConstants.INTERACT_BBS_LIMIT;
		if(total > 0){
			List<BBSUser> bbsUserList = communityUserDao.findBBSUserInfoListByNicknameAndLevelidTotal(owner,nickname,levelid,start,IInteractConstants.INTERACT_BBS_LIMIT);
			if(bbsUserList != null && bbsUserList.size()>0){
				for(int i=0;i<bbsUserList.size();i++){
					int toptotal = communityUserDao.findTopTotalByUid(bbsUserList.get(i).getId());
					int uptotal = communityUserDao.findCountByUid(bbsUserList.get(i).getId(),0);
					int downtotal = communityUserDao.findCountByUid(bbsUserList.get(i).getId(),1);
					bbsUserList.get(i).setToptotal(toptotal);
					bbsUserList.get(i).setUptotal(uptotal);
					bbsUserList.get(i).setDowntotal(downtotal);
				}
			}
			dto.setBbsUserList(bbsUserList);
		}
		Pager pager = new Pager(pageId, total, IInteractConstants.INTERACT_BBS_LIMIT);
		dto.setPager(pager);
		return dto;
	}

	@Override
	public IDto findBBSUserBalck(long owner, int pageId)
	{
		CommunityDto dto = new CommunityDto();
		List<BBSJfLevel> jfLevel = communityUserDao.findBBSUserJflevelList(owner);
		dto.setBbsJfLevelList(jfLevel);
		int total = communityUserDao.findBBSUserBalck(owner);
		int start = (pageId - 1) * IInteractConstants.INTERACT_BBS_LIMIT;
		if(total > 0){
			List<BBSUser> bbsUserList = communityUserDao.findBBSUserBalckList(owner,start,IInteractConstants.INTERACT_BBS_LIMIT);
			if(bbsUserList != null && bbsUserList.size()>0){
				for(int i=0;i<bbsUserList.size();i++){
					int toptotal = communityUserDao.findTopTotalByUid(bbsUserList.get(i).getId());
					int uptotal = communityUserDao.findCountByUid(bbsUserList.get(i).getId(),0);
					int downtotal = communityUserDao.findCountByUid(bbsUserList.get(i).getId(),1);
					bbsUserList.get(i).setToptotal(toptotal);
					bbsUserList.get(i).setUptotal(uptotal);
					bbsUserList.get(i).setDowntotal(downtotal);
				}
			}
			dto.setBbsUserList(bbsUserList);
		}
		Pager pager = new Pager(pageId, total, IInteractConstants.INTERACT_BBS_LIMIT);
		dto.setPager(pager);
		return dto;
	}

	@Override
	public int addUserBalck(long uid,String isbalck)
	{
		return communityUserDao.addUserBalck(uid,isbalck);
	}

	@Override
	public int saveBBSUser(String username, String password, BBSUser bs, long owner, String ip)
	{
		HyUser hu = hyUserDao.findHyuserByUP(username, password, owner);
		if (hu == null){
			if (StringUtils.isBlank(bs.getNickname())){
				bs.setNickname(username);
			}
			if(StringUtils.isBlank(bs.getImg())){
				bs.setImg("http://img.huiyee.com/mr.jpg");
			}
			long hyuid = hyUserDao.saveHyUser(username, password, owner,bs);
			bs.setHyuserid(hyuid);
			bs.setOwner(owner);
			bs.setCreatetime(new Date());
			long bsid = bbsUserDao.saveBBSUser(bs, ip);
			bs.setId(bsid);
			int res = bbsUserDao.saveBBSUserOnline(bsid);
			return res;
		}else{
			return -1;
		}
	}
	
	
}
