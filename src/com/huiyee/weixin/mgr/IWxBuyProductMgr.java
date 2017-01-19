package com.huiyee.weixin.mgr;

import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.MarketingSet;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.PayApt;
import com.huiyee.esite.model.PayRecord;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.weixin.dto.OrderInfoDto;
import com.huiyee.weixin.model.PayAddress;
import com.huiyee.weixin.model.PayOrder;
import com.huiyee.weixin.model.ShoppingCartRecord;


public interface IWxBuyProductMgr
{
	public OrderInfoDto findProductAndAddressInfo(long[] productid,long aid,long hyuid,long ownerid,long levelid);
	
	public OrderGoods findPayOrderGoodsById(long id);
	
	public void updateConfirmFreeOrder(long productid,String ip,VisitUser vu,String desc,long owner,String starturl);
	
	public PayOrder payOrderInfo(long payOrderid );
	
	public ShoppingCartRecord addShoppingCart(long productid,long hyuid,long paid,String status,long owner);
	
	public long addToShoppingCart(long productid,long hyuid,String status,PayApt apt);
	
	public int delShopCartProduct(long shopCartid,long hyuid);
	
	public OrderInfoDto findShoppingCartProductByHyuid(long hyuid,long aid,long owner,String type,long levelid);
	
	public int findShoppingCartProductNumByHyuid(long hyuid);
	
	public int updateQuantity(long shopCartid,long hyuid,int num,long productid);
	
	public int updateUseJf(long productid,long hyuid,int usejf);
	
	public PayOrder findPayOrderById(long payOrderid);
	
	public int updatepayOrderStatus(long out_trade_no,String prestatus,String status,long owner,String url,int fee);
	
	public int updatePayorderStatusByFid(long out_trade_no,String status);
	
	public int updatepayOrderStatus(long out_trade_no,String prestatus,String status,PayRecord payRecord);
	
	public long findPayHome(long owner);
	
	public long findPayJfHome(long owner);
	
	public long findPayJfUserPage(long owner);
	
	public long savePayRecord(String mediaorder,String ip );
	
	public long savePayRecord(PayRecord payRecord);
	
	public PayRecord findPayRecordById(long id);
	
	public int savePayAddress(long hyuid,PayAddress address);
	
	public int updateWkqYzStatus(long wxuid,String uuid,long pogid,long poid,String status,String onameurl,int ptype,long owner);
	
	public String findWkqOrder(String uuid,long id);
	
	public PayAddress findPayAddressById(long aid);
	
	public int updatePayAddress(long aid,long hyuid, PayAddress address);
	
	public int delPayAddress(long aid);
	
	public int updatePayRecord(long payrecordid,String mediaorder, int status,int price);
	
	public OrderInfoDto findOrderList(long hyuid,String status,int pageId,int type);
	
	public int delPayOrder(long payOrderid);
	
	public OrderInfoDto findAddressList(long aid,long hyuid);
	
	public long saveOrder(VisitUser vu,long[] id,long aid,int usejf,String ip,long owner);
	
	public OrderInfoDto savePayMoney(long payOrderid,long hyuid,long owner,VisitUser vu,String url,String ip);
	
	public OrderInfoDto findKqOrderList(long hyuid,String status,int pageId,int type);
	
	public OrderInfoDto findKqOrderList(long hyuid,String status,int pageId);
	
	public int updatePayOrder(long payOrderid,long hyuid);

	public MarketingSet findPayShop(long owner);

	public MarketingSet findJfPayShop(long owner);
	
	public OrderInfoDto findPayOrderInfoById(long id);
	
	public boolean findPersonLimitNum(ContentProduct product,long hyuid,int num);
	
}
