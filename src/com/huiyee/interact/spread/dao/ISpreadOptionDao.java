package com.huiyee.interact.spread.dao;

import java.util.List;

import com.huiyee.interact.spread.model.SpreadModel;
import com.huiyee.interact.spread.model.SpreadOption;

public interface ISpreadOptionDao
{

	public List<SpreadOption> findSpreadOption(long spreadid,int start,int size);
	
	public int findSpreadOptionTotal(long spreadid);
	
	public int saveSpreadOption(long spreadid,String wbid,String title,String content,String pic);
	
	public int updateSpreadOption(String title,String content,String pic,long id);
	
	public int deleteSpreadOption(long id);
	
	public SpreadModel fingSpreadType(long id);
	
	public SpreadOption findOneSpreadOption(long id);
	
	public SpreadOption findOneSpreadWbid(long id);
	
	public int saveSpreadWbid(String wbid,String url, long spreadid);
	
	public int updateSpreadWbid(String wbid,String url, long id);
	
	
}
