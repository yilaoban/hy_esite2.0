
package com.huiyee.esite.dao;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.MarketingOrder;
import com.huiyee.esite.model.MarketingSet;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.ProductCode;
import com.huiyee.esite.model.UserLevel;

public interface IEbproductDao
{

	public List<CategoryTree> findCategory(long fatherCatid, String type, String subtype, long owner);

	public List<ContentProduct> findListByccid(long ccid, long owner, int start, int size);
	
	public List<UserLevel> findpriceByowner(long owner,long pid);
	
	public List<UserLevel> findLevelNameByowner(long owner);
	
	public ContentProduct findListBypid(long pid);

	public int findListTotal(long ccid, long owner);

	public int findCodeTotal(long pid, String code, String status);

	public List<ProductCode> findCodeList(long pid, String code, String status, int i, int j);

	public int saveProductCode(long pid, List<String> insertList);

	public int findOrderTotal(long id, JSONObject sift, int subtype,Date startTime,Date endTime);

	public int updateOrderExpress(long orderid, String express);

	public MarketingSet findwdsSetting(long owner);

	public MarketingSet findjifenSetting(long owner);

	public int updateHomepage(long id, MarketingSet marketingSet);

	public int updateJifenHomepage(long id, MarketingSet marketingSet);

	public List<MarketingOrder> findOrderList(long owner, JSONObject sift, int subtype, int start, int size, Date startTime, Date endTime);

	public List<OrderGoods> findOrderGoodsByOrid(long id);

	public ProductCode findCodeById(long pcid);

	public int updateJifenToPrice(long id, int jftoprice);

	public List<OrderGoods> findOrderGoodsByfoid(long id);

}
