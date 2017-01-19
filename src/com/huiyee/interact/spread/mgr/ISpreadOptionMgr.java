package com.huiyee.interact.spread.mgr;

import java.util.List;

import com.huiyee.interact.spread.dto.IDto;
import com.huiyee.interact.spread.model.SpreadOption;

public interface ISpreadOptionMgr
{

	public IDto findSpreadOption(long spreadid,int start,int size);
	
	public int findSpreadOptionTotal(long spreadid);
	
	public int saveSpreadOption(SpreadOption sp);
	
	public int updateSpreadOption(SpreadOption sp);
	
	public int deleteSpreadOption(long id);
	
	public SpreadOption findOneSpreadOption(long id);
	
	public SpreadOption findOneSpreadWbid(long id);
	
	public int saveSpreadWbid(SpreadOption sp,long spreadid);
	
	public int updateSpreadWbid(SpreadOption sp,long id);
}
