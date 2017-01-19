package com.huiyee.esite.dao;

import java.util.List;
import java.util.Map;

import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.ContentTab;
import com.huiyee.esite.model.ProductCode;
import com.huiyee.interact.lottery.model.LotteryPrizeCode;

public interface IContentProductDao {

	public List<ContentProduct> findProductByCcid(long ccid, long owner, int start, int size, String name);
	
	public List<ContentProduct> findProductByCcid(long ccid,long ownerid);

	public long saveProduct(ContentProduct product);

	public ContentProduct findProductById(long contentId);
	
	public ContentProduct findBeforeProductById(ContentProduct product);
	
	public ContentProduct findNextProductById(ContentProduct product);
	
	public void updateUsed(long id,int quantity);

	public int updateProduct(ContentProduct product, long ownerid);

	public int updateProduct(long contentId, String contentDel, long ownerid);

	public List<ContentProduct> findProductByOwner(long ownerId);

	public int findProductTotal(long ccid, long ownerid, String name);
	
	public int findMaxIndx(long catid);
	
	public Map findIndx(long id);
	
	public int deleteIndx(int idx,long catid);
	
	public int updateProductIdx(ContentProduct currnet);

	public void updateProductPost(long entityid, long topicid);
	public void updateProductByFatherid(long fatherid, long ownerid, ContentProduct cp);

	public List<ContentProduct> findProductBySubtype(long owner, String subtype);
	
	public ContentProduct findEasyProductById(long id,long owner);
	
	public int addCouponsCode(List<ProductCode> list,long id);

	public int findCodeTotal(long pid, String code);

	public List<ProductCode> findCodeList(long pid, String code, int i, int j);

	public ProductCode findCodeByPid(long id);

	public void updateProductCodeUsed(ProductCode pcode);
	
	public int saveCode(long pid,String code,String password,long total);
	
	public long addProductLevel(long productid,String id, String name);
	
	public long updateProductVip(long vip,long productid);
	
	public Map<Long,Object> findProductLevel(long productid);

	public Map<String, String> findProductTabs(long contentId, long owner);

	public int updateTabIndex(long pid, String tabid, String json);
	
}
