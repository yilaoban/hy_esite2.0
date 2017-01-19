
package com.huiyee.esite.model;

import java.util.Date;

import com.huiyee.weixin.model.PayAddress;

public class OrderGoods
{

	private long id;
	private long orderid;
	private long pid;
	private long pcid;
	private String productname;
	private String productsubtype;
	private String productsimg;
	private ProductCode pcode;
	private Date createtime;
	private int price;
	private int type;
	private String uuid;
	private int num;
	private long paid;
	private String used;
	private long shoppingcartid;
	
	private String status;
	
	private PayApt payApt;
	
	public PayApt getPayApt()
	{
		return payApt;
	}



	
	public void setPayApt(PayApt payApt)
	{
		this.payApt = payApt;
	}



	public String getStatus()
	{
		return status;
	}


	
	public void setStatus(String status)
	{
		this.status = status;
	}


	public String getProductsimg()
	{
		return productsimg;
	}

	
	public void setProductsimg(String productsimg)
	{
		this.productsimg = productsimg;
	}

	
	public long getPaid()
	{
		return paid;
	}

	
	public void setPaid(long paid)
	{
		this.paid = paid;
	}

	
	public String getUsed()
	{
		return used;
	}

	
	public void setUsed(String used)
	{
		this.used = used;
	}

	
	public long getShoppingcartid()
	{
		return shoppingcartid;
	}

	
	public void setShoppingcartid(long shoppingcartid)
	{
		this.shoppingcartid = shoppingcartid;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getOrderid()
	{
		return orderid;
	}

	public void setOrderid(long orderid)
	{
		this.orderid = orderid;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public int getNum()
	{
		return num;
	}

	public void setNum(int num)
	{
		this.num = num;
	}

	public long getPid()
	{
		return pid;
	}

	public void setPid(long pid)
	{
		this.pid = pid;
	}

	public long getPcid()
	{
		return pcid;
	}

	public void setPcid(long pcid)
	{
		this.pcid = pcid;
	}

	public ProductCode getPcode()
	{
		return pcode;
	}

	public void setPcode(ProductCode pcode)
	{
		this.pcode = pcode;
	}

	public String getProductname()
	{
		return productname;
	}

	public void setProductname(String productname)
	{
		this.productname = productname;
	}

	public String getProductsubtype()
	{
		return productsubtype;
	}

	public void setProductsubtype(String productsubtype)
	{
		this.productsubtype = productsubtype;
	}

}
