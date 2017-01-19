package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.WxUser;
import com.huiyee.weixin.model.Wkq;

public interface IWeiKaQuanDao {

	public int saveWkqShopOwner(long wxuid, long owner);

	public int findTotalContentProduct(long owner);

	public List<ContentProduct> findContentProductList(long owner, int start, int size);

	public int findTotalPayOrderListByProductid(long productid);

	public List<OrderGoods> findPayOrderListByProductid(long productid, int start, int size);

	public OrderGoods findPayOrderGoods(long id);

	public int saveWKQ(long pageid, long owner);

	public int findTotalWKQShopOwnerByOwner(long owner);

	public List<WxUser> findWKQShopOwnerByOwner(long owner, int start, int size);

	public int delShopOwner(long wxuid, long owner);

	public Wkq findWKQByOwner(long owner);

}
