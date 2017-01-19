package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.Account;

public class EsbbsDto
{
	private List<BBSAccount> relationList;
	private List<EsForum> forumList;
	private List<Account> accountList;

	public List<BBSAccount> getRelationList()
	{
		return relationList;
	}

	public void setRelationList(List<BBSAccount> relationList)
	{
		this.relationList = relationList;
	}

	public List<EsForum> getForumList()
	{
		return forumList;
	}

	public void setForumList(List<EsForum> forumList)
	{
		this.forumList = forumList;
	}

	public List<Account> getAccountList()
	{
		return accountList;
	}

	public void setAccountList(List<Account> accountList)
	{
		this.accountList = accountList;
	}
}
