package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.ContentProduct;
import com.huiyee.weixin.model.ShoppingCartRecord;

import net.sf.json.JSONObject;

public interface IXuantuDao {

	public int addCollection(long productid, long ownerid, long hyuid);
	
	public int checkCollection(long productid, long ownerid, long hyuid);
	
	public List myCollection(long ownerid, long hyuid,int start,int size);
	
	public int mytotal(long ownerid, long hyuid);
	
	public int removeCollection(long ownerid, long hyuid, long productid);
	
	public ContentProduct findEasyProductById(long id,long owner);
	
	public ShoppingCartRecord findShoppingCartProduct(long productid, long hyuid,String status);
	
	public int updatePayShoppingCartNum(long productid, long hyuid);
	
	public long savePayShoppingCart(long productid, long hyuid,String status);
	
	public List findShoppingCartProductByHyuid(long hyuid, long owner, String type, int start, int size);
	
	public int delShopCartProduct(long shopCartid, long hyuid);
	
	public int delShopCart(long hyuid);
}
