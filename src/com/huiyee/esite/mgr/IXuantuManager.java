package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.model.AreaCity;
import com.huiyee.esite.model.AreaProvince;
import com.huiyee.weixin.dto.OrderInfoDto;
import com.huiyee.weixin.model.ShoppingCartRecord;

import net.sf.json.JSONObject;

public interface IXuantuManager {
	
	public int addCollection(long productid,long ownerid,long hyuid);
	
	public int checkCollection(long productid,long ownerid,long hyuid);
	
	public List myCollection(long ownerid, long hyuid,int start,int size);
	
	public int mytotal(long ownerid, long hyuid);
	
	public int removeCollection(long ownerid, long hyuid,long productid);
	
	public ShoppingCartRecord addShoppingCart(long productid,long hyuid,String status,long owner);
	
	public List findShoppingCartProductByHyuid(long hyuid,long owner,String type,int start,int size);
	
	public int delShopCartProduct(long shopCartid,long hyuid);
	
	public int savesubmit(long hyuid,long wxuid,long ownerid,String name,String telphone,String[] something) throws Exception;
	
}
