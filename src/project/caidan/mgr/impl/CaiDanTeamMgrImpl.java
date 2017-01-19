package project.caidan.mgr.impl;

import java.util.List;

import project.caidan.dao.ICaiDanRmbDao;
import project.caidan.dao.ICaiDanTeamDao;
import project.caidan.dao.ICdRmbRecordDao;
import project.caidan.dto.CaiDanTeamDto;
import project.caidan.mgr.ICaiDanTeamMgr;
import project.caidan.model.CDAccountCpz;
import project.caidan.model.CDAccountDl;
import project.caidan.model.CDAccoutType;
import project.caidan.model.CDChannel;
import project.caidan.model.CDRmb;

import com.huiyee.esite.dto.IDto;


public class CaiDanTeamMgrImpl implements ICaiDanTeamMgr
{
	private ICaiDanTeamDao cdTeamDao;
	private ICdRmbRecordDao rmbRecordDao;
	private ICaiDanRmbDao cdRmbDao;
	
	public void setCdRmbDao(ICaiDanRmbDao cdRmbDao)
	{
		this.cdRmbDao = cdRmbDao;
	}

	public void setRmbRecordDao(ICdRmbRecordDao rmbRecordDao)
	{
		this.rmbRecordDao = rmbRecordDao;
	}

	public void setCdTeamDao(ICaiDanTeamDao cdTeamDao)
	{
		this.cdTeamDao = cdTeamDao;
	}

	@Override
	public IDto findUserIdentityByWxUid(long wxuid,String type,long owner)
	{
		CaiDanTeamDto dto = new CaiDanTeamDto();
		CDAccoutType ac = cdTeamDao.findAccountTypeByWxuid(wxuid,type);
		if(ac != null){
			dto.setIdentity(1);
			List<CDChannel> chList = cdTeamDao.findChannelByAcid(ac.getAcid(),owner);//渠道列表
			dto.setChList(chList);
			
			List<CDAccountDl> dlList = cdTeamDao.findAccountDlListByWxuid(wxuid,"Y");//代理列表
			if(dlList != null && dlList.size() > 0){
				for(int i=0;i<dlList.size();i++){
					List<CDAccountCpz> cpzList = cdTeamDao.findAccountCpzByWxuid(dlList.get(i).getWxuid(),"Y");
					if(cpzList.size() > 0){
						dlList.get(i).setCpzList(cpzList);
					}
				}
				dto.setDlList(dlList);
			}
		}else{//代理
			CDAccountDl dl = cdTeamDao.findAccountDlByWxuid(wxuid);
			if(dl != null){
				dto.setIdentity(2);
				List<CDAccountCpz> cpzList = cdTeamDao.findAccountCpzByWxuid(dl.getWxuid(),"Y");
				if(cpzList.size() > 0){
					dl.setCpzList(cpzList);
				}
				dto.setDl(dl);
			}else{
				//彩票站
				dto.setIdentity(3);
			}
		}
		return dto;
	}

	@Override
	public IDto findTeamBusiness(long wxuid, String type, long owner)
	{
		CaiDanTeamDto dto = new CaiDanTeamDto();
		CDAccoutType ac = cdTeamDao.findAccountTypeByWxuid(wxuid,type);
		if(ac != null){
			dto.setIdentity(1);
			List<CDAccountDl> dlList = cdTeamDao.findAccountDlListByWxuid(wxuid,"Y");//代理列表
			if(dlList != null && dlList.size() > 0){
				for(int i=0;i<dlList.size();i++){
					int todayNum = rmbRecordDao.findRmbRecordByWxuid(dlList.get(i).getWxuid());
					dlList.get(i).setTodayNum(todayNum);
					CDRmb cdRmb = cdRmbDao.findCDRmbByWxuid(dlList.get(i).getWxuid());
					if(cdRmb != null){
						dlList.get(i).setTotal(cdRmb.getTotal());
					}
				}
				dto.setDlList(dlList);
			}
		}else{//代理
			CDAccountDl dl = cdTeamDao.findAccountDlByWxuid(wxuid);
			if(dl != null){
				dto.setIdentity(2);
				List<CDAccountCpz> cpzList = cdTeamDao.findAccountCpzByWxuid(dl.getWxuid(),"Y");
				if(cpzList.size() > 0){
					for(int i=0;i<cpzList.size();i++){
						int todayNum = rmbRecordDao.findRmbRecordByWxuid(cpzList.get(i).getWxuid());
						cpzList.get(i).setTodayNum(todayNum);
						CDRmb cdRmb = cdRmbDao.findCDRmbByWxuid(cpzList.get(i).getWxuid());
						if(cdRmb != null){
							cpzList.get(i).setTotal(cdRmb.getTotal());
						}
					}
					dl.setCpzList(cpzList);
				}
				dto.setDl(dl);
			}else{
				//彩票站
				dto.setIdentity(3);
			}
		}
		return dto;
	}

	@Override
	public IDto channelCheck(long wxuid, String type, long owner,String status)
	{
		CaiDanTeamDto dto = new CaiDanTeamDto();
		CDAccoutType ac = cdTeamDao.findAccountTypeByWxuid(wxuid,type);
		if(ac != null){
			dto.setIdentity(1);
			List<CDAccountDl> dlList = cdTeamDao.findAccountDlListByWxuid(wxuid,status);//审批代理列表
			if(dlList != null && dlList.size() > 0){
				dto.setDlList(dlList);
			}
		}else{//代理
			CDAccountDl dl = cdTeamDao.findAccountDlByWxuid(wxuid);
			if(dl != null){
				dto.setIdentity(2);
				List<CDAccountCpz> cpzList = cdTeamDao.findAccountCpzByWxuid(dl.getWxuid(),status);//审批彩票站列表
				if(cpzList.size() > 0){
					dl.setCpzList(cpzList);
				}
				dto.setDl(dl);
			}else{
				//彩票站
				dto.setIdentity(3);
			}
		}
		return dto;
	}

	@Override
	public IDto doCheckPre(long wxuid, String type, long owner, long id)
	{
		CaiDanTeamDto dto = new CaiDanTeamDto();
		CDAccoutType ac = cdTeamDao.findAccountTypeByWxuid(wxuid,type);
		if(ac != null){
			dto.setIdentity(1);
			List<CDChannel> chList = cdTeamDao.findChannelByAcid(ac.getAcid(),owner);//渠道列表
			dto.setChList(chList);
			CDAccountDl dl = cdTeamDao.findAccountDlById(id);
			dto.setDl(dl);
		}else{//代理
			CDAccountDl dl = cdTeamDao.findAccountDlByWxuid(wxuid);
			if(dl != null){
				dto.setIdentity(2);
				CDChannel channel = cdTeamDao.findChannelById(dl.getClid(), owner);
				dto.setChannel(channel);
				CDAccountCpz cpz = cdTeamDao.findAccountCpzById(id);
				dto.setCpz(cpz);
				dto.setDl(dl);
			}else{
				//彩票站
				dto.setIdentity(3);
			}
		}
		return dto;
	}

	@Override
	public IDto updatedoCheck(long wxuid, String type, long owner, long id, long clid,String status)
	{
		CaiDanTeamDto dto = new CaiDanTeamDto();
		CDAccoutType ac = cdTeamDao.findAccountTypeByWxuid(wxuid,type);
		if(ac != null){
			dto.setIdentity(1);
			int res = cdTeamDao.updateAccountDlById(id,clid,status);
			dto.setRes(res);
		}else{//代理
			CDAccountDl dl = cdTeamDao.findAccountDlByWxuid(wxuid);
			if(dl != null){
				dto.setIdentity(2);
				int res = cdTeamDao.updateAccountCpzById(id,clid,status);
				dto.setRes(res);
			}else{
				//彩票站
				dto.setIdentity(3);
			}
		}
		return dto;
	}

	@Override
	public IDto saveForm(long wxuid, String type,CDAccountDl accountdl)
	{
		CaiDanTeamDto dto = new CaiDanTeamDto();
		accountdl.setWxuid(wxuid);
		CDAccoutType ac = cdTeamDao.findAccountTypeByWxuid(accountdl.getFawxuid(),type);
		if(ac != null){
			dto.setIdentity(1);
			CDAccountDl dl = cdTeamDao.findAccountDlByWxuidAndFwxuid(wxuid, accountdl.getFawxuid());
			if(dl != null){
				int res = cdTeamDao.updateAccountDlByWxuidAndFwxuid(accountdl);
				dto.setRes(res);
			}else{
				int res = cdTeamDao.saveAccountDl(accountdl);
				dto.setRes(res);
			}
		}else{//代理
			CDAccountDl dl = cdTeamDao.findAccountDlByWxuid(accountdl.getFawxuid());
			if(dl != null){
				dto.setIdentity(2);
				CDAccountCpz cpz = cdTeamDao.findAccountCpzByWxuidAndFwxuid(wxuid, accountdl.getFawxuid());
				if(cpz != null){
					int res = cdTeamDao.updateAccountCpzByWxuidAndFwxuid(accountdl);
					dto.setRes(res);
				}else{
					int res = cdTeamDao.saveAccountCpz(accountdl);
					dto.setRes(res);
				}
			}
		}
		return dto;
	}

	@Override
	public IDto findSubUser(long wxuid, String type, CDAccountDl accountdl)
	{
		CaiDanTeamDto dto = new CaiDanTeamDto();
		CDAccoutType account = cdTeamDao.findAccountTypeByWxuid(wxuid,type);//查询申请人角色
		if(account != null){//是省级代理 
			dto.setRes(2); 
			return dto;
		}else{
			CDAccountDl dl = cdTeamDao.findAccountDlByWxuid(wxuid);//查询申请人是否是代理角色
			if(dl != null){//申请人是代理角色  去查看上级的角色
				CDAccoutType ac = cdTeamDao.findAccountTypeByWxuid(accountdl.getFawxuid(),type);//查询上级角色
				if(ac == null){//上级角色不是省级 不能申请
					dto.setRes(3); 
				}
			}
			return dto;
		}
	}

	@Override
	public IDto findSubUserInfo(long wxuid, String type, CDAccountDl accountdl)
	{
		CaiDanTeamDto dto = new CaiDanTeamDto();
		accountdl.setWxuid(wxuid);
		CDAccoutType ac = cdTeamDao.findAccountTypeByWxuid(accountdl.getFawxuid(),type);
		if(ac != null){
			dto.setIdentity(1);
			CDAccountDl dl = cdTeamDao.findAccountDlByWxuidAndFwxuid(wxuid, accountdl.getFawxuid());
			if(dl != null){
				dto.setDl(dl);
			}
		}else{//代理
			CDAccountDl dl = cdTeamDao.findAccountDlByWxuid(accountdl.getFawxuid());
			if(dl != null){
				dto.setIdentity(2);
				CDAccountCpz cpz = cdTeamDao.findAccountCpzByWxuidAndFwxuid(wxuid,accountdl.getFawxuid());
				if(cpz != null){
					dto.setCpz(cpz);
				}
			}
		}
		return dto;
	}
	
	
}
