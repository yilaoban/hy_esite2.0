package project.caidan.mgr;

import project.caidan.model.CDAccountDl;

import com.huiyee.esite.dto.IDto;


public interface ICaiDanTeamMgr
{
	public IDto findUserIdentityByWxUid(long wxuid,String type,long owner);
	
	public IDto findTeamBusiness(long wxuid,String type,long owner);
	
	public IDto channelCheck(long wxuid,String type,long owner,String status);
	
	public IDto doCheckPre(long wxuid,String type,long owner,long id);
	
	public IDto updatedoCheck(long wxuid,String type,long owner,long id,long clid,String status);
	
	public IDto saveForm(long wxuid,String type,CDAccountDl dl);
	
	public IDto findSubUser(long wxuid,String type,CDAccountDl dl);
	
	public IDto findSubUserInfo(long wxuid,String type,CDAccountDl dl);
}
