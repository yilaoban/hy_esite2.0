package com.huiyee.esite.dto;

import java.util.List;

import org.apache.solr.common.SolrDocument;

import com.huiyee.esite.model.UserBalance;
import com.huiyee.interact.checkin.dto.Pager;


public class JfUserDto implements IDto
{
	public List<UserBalance> userBalanceList;
	private Pager pager;
	private List<SolrDocument> ulist;
	private String nickname;
	private String img;
	private int jf;
	
	public int getJf()
	{
		return jf;
	}


	
	public void setJf(int jf)
	{
		this.jf = jf;
	}


	public String getNickname()
	{
		return nickname;
	}

	
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	
	public String getImg()
	{
		return img;
	}

	
	public void setImg(String img)
	{
		this.img = img;
	}

	public List<SolrDocument> getUlist()
	{
		return ulist;
	}

	public void setUlist(List<SolrDocument> ulist)
	{
		this.ulist = ulist;
	}

	public List<UserBalance> getUserBalanceList()
	{
		return userBalanceList;
	}
	
	public void setUserBalanceList(List<UserBalance> userBalanceList)
	{
		this.userBalanceList = userBalanceList;
	}
	
	public Pager getPager()
	{
		return pager;
	}
	
	public void setPager(Pager pager)
	{
		this.pager = pager;
	}
	
	
}
