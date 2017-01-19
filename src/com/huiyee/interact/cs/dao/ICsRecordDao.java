package com.huiyee.interact.cs.dao;

import java.util.List;

import com.huiyee.interact.cs.model.CsData;


public interface ICsRecordDao
{
	 public long addCsFuidDraw(long csid,long fuid,int utype,String jcon, String ip,String terminal,String source);

	public int findTotal(long csid, int type);

	public List<CsData> findList(long csid, int type, int i, int interactCsDataLimit);	
}
