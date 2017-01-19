package com.huiyee.weixin.dao;

import java.util.List;

import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.MarketingSet;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.PayApt;
import com.huiyee.esite.model.PayRecord;
import com.huiyee.weixin.dto.WkqCmp;
import com.huiyee.weixin.model.PayAddress;
import com.huiyee.weixin.model.PayOrder;
import com.huiyee.weixin.model.PayOrderTemplate;
import com.huiyee.weixin.model.ProductLevel;
import com.huiyee.weixin.model.ShoppingCartRecord;


public interface IWxBuyProductDao
{
	public List<PayAddress> findPayAddressListByHyUid(long hyuid);
	
	public PayAddress findPayAddressById(long aid);
	
	public PayAddress findDefaultPayAddress(long hyuid);
	
	public long savePayOrder(long hyuid,long wxuid,long owner,int type,String ip,String status);
	
	public int updatepayOrderStatus(long out_trade_no,String prestatus,String status);
	
	public long savePayRecord(String mediaorder, String ip);
	
	public int savePayAddress(long hyuid, PayAddress address);
	
	public int updatePayAddress(long aid, PayAddress address);
	
	public int delPayAddress(long aid);
	
    public long findPayHome(long owner);
	
	public long findPayJfHome(long owner);
	
	public long findPayJfUserPage(long owner);
	
	public int updateWkqYzStatus(String uuid, long id,String status);
	
	public WkqCmp findWkqCmp(long poid);
	
	public String findWkqOrder(String uuid,long id);
	
	public PayOrder findPayOrderById(long payOrderid);
	
	public PayOrderTemplate findPayOrderNameById(long payOrderid);
	
	public PayOrderTemplate findPayOrderNameByFid(long payOrderid);
	
	public ShoppingCartRecord findShoppingCartProduct(long productid, long hyuid,long paid,String status);
	
	public int updateShoppingCartProductNum(long shopCartid, long hyuid,int num);
	
	public long savePayShoppingCart(long productid, long hyuid,long paid,String status);
	
	public int updatePayShoppingCartNum(long productid, long hyuid,long paid);
	
	public List<ShoppingCartRecord> findShoppingCartProductByHyuid(long hyuid,long owner,String type);
	
	public ContentProduct findShoppingCartByProductid(long hyuid,long productid);
	
	public int updateShoppingCartStatus(long shopCartid, long hyuid);
	
	public int updatePayRecord(long payrecordid, String mediaorder, int status,int price);
	
	public int findTotalOrderList(long hyuid,String status,int type);
	
	public int findTotalKQOrderList(long hyuid,String status,int type);
	
	public int finfTotalUnusedKQOrderList(long hyuid,String status,int type);
	
	public List<PayOrder> findOrderList(long hyuid,String status,int type,int start,int size);
	
	public List<PayOrder> findKQOrderList(long hyuid,String status,int type,int start,int size);
	
	public List<PayOrder> findKQOrderList(long hyuid,String status,int start,int size);
	
	public int updateShoppingCartJf(long productid, long hyuid,int usejf);
	
	public int findShoppingCartProductNumByHyuid(long hyuid);
	
	public PayRecord findPayRecordById(long id);
	
	public int delPayOrder(long payOrderid);
	
	public void updateWkqRecord(long wxuid, long pogid);
	
	public PayApt findPayAptById(long id);
	
	public long savePayOrderGoods(long payOrderid,long productid,String productname,String productSubtype,String productsimg,int saleprice,int type,String uuid,int quantity,long paid,long shoppingcartid);

	public void removeOtherAddressDefault(long hyuid);
	
	public ShoppingCartRecord findShoppingProductById(long id,long hyuid);
	
	public long saveNewOrder(long hyuid,long wxuid,long owner,int type,long addressid,String ip);
	
	public long saveChildOrder(long hyuid,long wxuid,long owner,int type,long addressid,String ip,long fathderOrderid,String status);

	public List<OrderGoods> findPayOrderGoods(long payOrderid);
	
	public OrderGoods findPayOrderGoodsById(long id);
	
	public long savePayRecord(PayRecord payRecord);
	
	public int updatePayorderStatusById(long orderid,String status);
	
	public int updatePayorderStatusByFid(long out_trade_no,String status);
	
	public int updateOrderPriceById(long orderid,int totalprice,int discountprice,int realprice);
	
	public int updateUseJf(long orderid,long hyuid,int usejf);
	
	public int deleteShoppingCart(long id,long hyuid);
	
	public int updatePayorderByFid(long payOrderid);
	
	public long savePayApt(PayApt apt );
	
	public long findChildOrder(long payOrderid);
	
	public PayOrder findChildOrderById(long payOrderid);
	
	public MarketingSet findPayShopByOwner(long owner);

	public MarketingSet findPayJfShopByOwner(long owner);
	
	public PayRecord findPayRecordByPoid(long payOrderid);
	
	/**
	 * 记录用户购买商品数量
	 * @param orderid
	 * @return
	 */
	public int updateAddPersonBuyProduct(long orderid);
	
	/**
	 * 取消订单回复商品数量
	 * @param orderid
	 * @return
	 */
	public int updateCancelPersonBuyProduct(long orderid);

	public void updateOrderPcode(long rs, long id);
	
	public ProductLevel findProductLevel(long productid,long levelid);
	
	public int findBuyProductNumByProductid(long productid,long hyuid);
}
