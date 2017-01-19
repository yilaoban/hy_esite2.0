package com.huiyee.weixin.dto;

import java.util.Date;
import java.util.List;

import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.AreaCity;
import com.huiyee.esite.model.AreaProvince;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.PayRecord;
import com.huiyee.weixin.model.PayAddress;
import com.huiyee.weixin.model.PayOrder;
import com.huiyee.weixin.model.ShoppingCartRecord;
import com.huiyee.weixin.model.Wkq;


public class OrderInfoDto
{
	
	private ContentProduct product;
	private List<PayAddress> payAddressList;
	private PayAddress address;
	private int totalPrice;
	private int quantity;
	private long payOrderid;
	private long payrecordid;
	private List<AreaProvince> provinceList;
	private List<AreaCity> cityList;
	private String hydesc;
	private int status;//1-成功；-1-商品不存在；-2-商品没有库存；-3-积分不足
	private List<ContentProduct> productList;
	private List<PayOrder> payOrderList;
	private List<Long> payOrderidList;
	private Pager pager;
	private int totalJf; //用户的积分
	private Wkq wkq;
	
	private Date createtime;
	
	private List<ShoppingCartRecord> shopCartList;
	private PayOrder payOrder;
	private PayRecord record;
	private int dfkCount;
	private int kqCount;
	private int needAddress;//是否需要填写地址
	
	public int getNeedAddress() {
		return needAddress;
	}


	public void setNeedAddress(int needAddress) {
		this.needAddress = needAddress;
	}





	public int getDfkCount()
	{
		return dfkCount;
	}




	
	public void setDfkCount(int dfkCount)
	{
		this.dfkCount = dfkCount;
	}




	
	public int getKqCount()
	{
		return kqCount;
	}




	
	public void setKqCount(int kqCount)
	{
		this.kqCount = kqCount;
	}




	public PayRecord getRecord()
	{
		return record;
	}



	
	public void setRecord(PayRecord record)
	{
		this.record = record;
	}



	public Wkq getWkq()
	{
		return wkq;
	}


	
	public void setWkq(Wkq wkq)
	{
		this.wkq = wkq;
	}


	public List<ShoppingCartRecord> getShopCartList()
	{
		return shopCartList;
	}

	
	public void setShopCartList(List<ShoppingCartRecord> shopCartList)
	{
		this.shopCartList = shopCartList;
	}

	public List<ContentProduct> getProductList()
	{
		return productList;
	}
	
	public void setProductList(List<ContentProduct> productList)
	{
		this.productList = productList;
	}



	public String getHydesc()
	{
		return hydesc;
	}


	
	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}


	
	public int getStatus()
	{
		return status;
	}


	
	public void setStatus(int status)
	{
		this.status = status;
	}


	public List<AreaCity> getCityList()
	{
		return cityList;
	}

	
	public void setCityList(List<AreaCity> cityList)
	{
		this.cityList = cityList;
	}

	public ContentProduct getProduct()
	{
		return product;
	}

	public void setProduct(ContentProduct product)
	{
		this.product = product;
	}

	public List<PayAddress> getPayAddressList()
	{
		return payAddressList;
	}
	
	public void setPayAddressList(List<PayAddress> payAddressList)
	{
		this.payAddressList = payAddressList;
	}

	public PayAddress getAddress()
	{
		return address;
	}

	public void setAddress(PayAddress address)
	{
		this.address = address;
	}

	public int getTotalPrice()
	{
		return totalPrice;
	}

	
	public void setTotalPrice(int totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	public int getQuantity()
	{
		return quantity;
	}

	
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	
	public long getPayOrderid()
	{
		return payOrderid;
	}

	
	public void setPayOrderid(long payOrderid)
	{
		this.payOrderid = payOrderid;
	}

	
	public List<AreaProvince> getProvinceList()
	{
		return provinceList;
	}

	
	public void setProvinceList(List<AreaProvince> provinceList)
	{
		this.provinceList = provinceList;
	}

	public long getPayrecordid()
	{
		return payrecordid;
	}

	
	public void setPayrecordid(long payrecordid)
	{
		this.payrecordid = payrecordid;
	}

	
	public List<PayOrder> getPayOrderList()
	{
		return payOrderList;
	}

	
	public void setPayOrderList(List<PayOrder> payOrderList)
	{
		this.payOrderList = payOrderList;
	}

	
	public List<Long> getPayOrderidList()
	{
		return payOrderidList;
	}

	
	public void setPayOrderidList(List<Long> payOrderidList)
	{
		this.payOrderidList = payOrderidList;
	}

	
	public Pager getPager()
	{
		return pager;
	}

	
	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	
	public int getTotalJf()
	{
		return totalJf;
	}

	
	public void setTotalJf(int totalJf)
	{
		this.totalJf = totalJf;
	}

	
	public Date getCreatetime()
	{
		return createtime;
	}

	
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public PayOrder getPayOrder()
	{
		return payOrder;
	}

	public void setPayOrder(PayOrder payOrder)
	{
		this.payOrder = payOrder;
	}
	
}
