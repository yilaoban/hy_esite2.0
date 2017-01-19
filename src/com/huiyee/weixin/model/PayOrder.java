package com.huiyee.weixin.model;

import java.util.Date;
import java.util.List;

import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.util.DateUtil;


public class PayOrder
{
	private long id;
	private long hyuid;
	private long wxuid;
	private long ownerid;
	private Date createtime;
	private long addressid;
	private Date sendtime;
	private int totalprice;
	private int discountprice;
	private int realprice;
	private String expressid;
	private String del_tag;
	private String status;
	private String ip;
	private int subtype;
	private long fatherorderid;
	private int usejf;
	private int goodscount;
	
	private List<OrderGoods> goods;
	private String subject;
	private String createtimeStr;
	
	private PayOrder childOrder;
	public PayOrder getChildOrder()
	{
		return childOrder;
	}

	
	public void setChildOrder(PayOrder childOrder)
	{
		this.childOrder = childOrder;
	}

	public String getSubject()
	{
		return subject;
	}
	
	public int getUsejf()
	{
		return usejf;
	}


	
	public void setUsejf(int usejf)
	{
		this.usejf = usejf;
	}


	
	public int getGoodscount()
	{
		return goodscount;
	}


	
	public void setGoodscount(int goodscount)
	{
		this.goodscount = goodscount;
	}


	public void setSubject(String subject)
	{
		this.subject = subject;
	}


	public long getOwnerid()
	{
		return ownerid;
	}

	
	public void setOwnerid(long ownerid)
	{
		this.ownerid = ownerid;
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
	
	public long getWxuid()
	{
		return wxuid;
	}
	
	public void setWxuid(long wxuid)
	{
		this.wxuid = wxuid;
	}
	
	public Date getCreatetime()
	{
		return createtime;
	}
	
	public void setCreatetime(Date createtime)
	{
		if(createtime != null){
			createtimeStr = DateUtil.getDateString6(createtime);
		}
		this.createtime = createtime;
	}
	
	public long getAddressid()
	{
		return addressid;
	}
	
	public void setAddressid(long addressid)
	{
		this.addressid = addressid;
	}
	
	public Date getSendtime()
	{
		return sendtime;
	}
	
	public void setSendtime(Date sendtime)
	{
		this.sendtime = sendtime;
	}
	
	public int getTotalprice()
	{
		return totalprice;
	}
	
	public void setTotalprice(int totalprice)
	{
		this.totalprice = totalprice;
	}
	
	public int getDiscountprice()
	{
		return discountprice;
	}
	
	public void setDiscountprice(int discountprice)
	{
		this.discountprice = discountprice;
	}
	
	public int getRealprice()
	{
		return realprice;
	}
	
	public void setRealprice(int realprice)
	{
		this.realprice = realprice;
	}
	
	public String getExpressid()
	{
		return expressid;
	}
	
	public void setExpressid(String expressid)
	{
		this.expressid = expressid;
	}
	
	public String getDel_tag()
	{
		return del_tag;
	}
	
	public void setDel_tag(String del_tag)
	{
		this.del_tag = del_tag;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public String getIp()
	{
		return ip;
	}
	
	public void setIp(String ip)
	{
		this.ip = ip;
	}
	
	
	public int getSubtype()
	{
		return subtype;
	}
	
	public void setSubtype(int subtype)
	{
		this.subtype = subtype;
	}


	public long getFatherorderid()
	{
		return fatherorderid;
	}
	
	public void setFatherorderid(long fatherorderid)
	{
		this.fatherorderid = fatherorderid;
	}


	
	public List<OrderGoods> getGoods()
	{
		return goods;
	}


	
	public void setGoods(List<OrderGoods> goods)
	{
		this.goods = goods;
	}


	
	public String getCreatetimeStr()
	{
		return createtimeStr;
	}


	
	public void setCreatetimeStr(String createtimeStr)
	{
		this.createtimeStr = createtimeStr;
	}
	
//	private long productid;
//	private long pcodeid;
//	private int price;
//	private int type;
//	private String uuid;
//	private int num;
//	
//	private String name;
//	private String simgurl;
//	private String detail;
//	
//	private int usejf;
//	private int dkprice;
//	private String ptype;//商品类型  J-积分产品，W-微商产品
	
	
	

	
	
}
