package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.WxUser;
import com.huiyee.weixin.model.PayOrder;


public class WeiKaQuanDto implements IDto 
{
	private Pager pager;
	private List<WxUser> userList;
	private List<ContentProduct> productList;
	private List<OrderGoods> orderGoodsList;

	public List<OrderGoods> getOrderGoodsList()
	{
		return orderGoodsList;
	}



	
	public void setOrderGoodsList(List<OrderGoods> orderGoodsList)
	{
		this.orderGoodsList = orderGoodsList;
	}



	public List<ContentProduct> getProductList()
	{
		return productList;
	}


	
	public void setProductList(List<ContentProduct> productList)
	{
		this.productList = productList;
	}


	public Pager getPager()
	{
		return pager;
	}

	
	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public List<WxUser> getUserList()
	{
		return userList;
	}
	
	public void setUserList(List<WxUser> userList)
	{
		this.userList = userList;
	}
	
	
}
