package project.caidan.dto;

import java.util.List;

import project.caidan.model.CDAccountCpz;
import project.caidan.model.CDAccountDl;
import project.caidan.model.CDChannel;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.WxUser;


public class CaiDanTeamDto implements IDto
{
	private List<CDChannel> chList;
	private List<CDAccountDl> dlList;
	private CDAccountDl dl;
	private WxUser wxUser;
	private int identity; //1:渠道经理 2：代理 3：彩票站
	private CDAccountCpz cpz;
	private CDChannel channel;
	
	private int res;
	
	public int getRes()
	{
		return res;
	}



	
	public void setRes(int res)
	{
		this.res = res;
	}



	public CDChannel getChannel()
	{
		return channel;
	}


	
	public void setChannel(CDChannel channel)
	{
		this.channel = channel;
	}


	public CDAccountCpz getCpz()
	{
		return cpz;
	}

	
	public void setCpz(CDAccountCpz cpz)
	{
		this.cpz = cpz;
	}

	public int getIdentity()
	{
		return identity;
	}
	
	public void setIdentity(int identity)
	{
		this.identity = identity;
	}


	public List<CDAccountDl> getDlList()
	{
		return dlList;
	}

	
	public void setDlList(List<CDAccountDl> dlList)
	{
		this.dlList = dlList;
	}

	public WxUser getWxUser()
	{
		return wxUser;
	}

	public void setWxUser(WxUser wxUser)
	{
		this.wxUser = wxUser;
	}


	public CDAccountDl getDl()
	{
		return dl;
	}

	
	public void setDl(CDAccountDl dl)
	{
		this.dl = dl;
	}

	public List<CDChannel> getChList()
	{
		return chList;
	}
	
	public void setChList(List<CDChannel> chList)
	{
		this.chList = chList;
	}

	
	
}
