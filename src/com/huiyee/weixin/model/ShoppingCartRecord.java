package com.huiyee.weixin.model;

import java.util.Date;

import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.PayApt;

public class ShoppingCartRecord{
	
	private long id;
	private long hyuid;
	private int num;
	private String status;
	private Date createtime;
	private long paid;
	private long productid;
	private ContentProduct product;
	private PayApt payApt;
	private String type;
	
	public String getType()
	{
		return type;
	}


	
	public void setType(String type)
	{
		this.type = type;
	}


	public PayApt getPayApt()
	{
		return payApt;
	}

	
	public void setPayApt(PayApt payApt)
	{
		this.payApt = payApt;
	}

	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getHyuid()
	{
		return hyuid;
	}
	
	public void setHyuid(long hyuid)
	{
		this.hyuid = hyuid;
	}
	
	public int getNum()
	{
		return num;
	}
	
	public void setNum(int num)
	{
		this.num = num;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public Date getCreatetime()
	{
		return createtime;
	}
	
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	
	public long getPaid()
	{
		return paid;
	}
	
	public void setPaid(long paid)
	{
		this.paid = paid;
	}
	
	public long getProductid()
	{
		return productid;
	}
	
	public void setProductid(long productid)
	{
		this.productid = productid;
	}
	
	public ContentProduct getProduct()
	{
		return product;
	}
	
	public void setProduct(ContentProduct product)
	{
		this.product = product;
	}
	
	
}
