package com.huiyee.esite.mgr;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.MarketingEbDto;
import com.huiyee.esite.dto.MarketingOrderDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.MarketingSet;


public interface IMarketingEbMgr
{

	public IDto findProductList(long ccid, long owner, int pageId, String subtype);
	
	public IDto findLevelbyOwner(long owner,long pid);

	public IDto findProductCodeList(long pid, int pageId, String code, String status);

	public int saveProductCode(long pid, List<String> insertList);

	public IDto findOrderList(int pageId, Account account, JSONObject sift, String subtype,Date startTime,Date endTime );

	public int updateOrderExpress(long orderid, String express);

	public MarketingSet findSetting(Account account, String subtype);

	public int updateHomepage(Account account, long sgid, String subtype);

	public int updateJifenToPrice(Account account, int jftoprice, String subtype);

}
