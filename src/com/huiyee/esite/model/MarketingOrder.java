
package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.huiyee.weixin.model.PayAddress;

public class MarketingOrder implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5059914533718447735L;
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
	private long payrecordid;
	private String expressid;
	private int del_tag;
	private String status;
	private String ip;
	private List<String> imgs;
	private PayAddress address;
	private int nameTag;// 1-显示hyusername 2-显示微信nickname 3显示微博nickname
	private HyUser hyuser;
	private WxUser wxuser;
	private SinaUser sinauser;
	private List<OrderGoods> orderGoods;//订单里物流商品的列表
	private List<OrderGoods> kqGoods;//订单关联的子订单(即卡券订单)的卡券商品的列表
	private int subtype;

	public List<String> getImgs()
	{
		return imgs;
	}

	public void setImgs(List<String> imgs)
	{
		this.imgs = imgs;
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

	public long getOwnerid()
	{
		return ownerid;
	}

	public void setOwnerid(long ownerid)
	{
		this.ownerid = ownerid;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
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

	public long getPayrecordid()
	{
		return payrecordid;
	}

	public void setPayrecordid(long payrecordid)
	{
		this.payrecordid = payrecordid;
	}

	public int getDel_tag()
	{
		return del_tag;
	}

	public void setDel_tag(int del_tag)
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

	public PayAddress getAddress()
	{
		return address;
	}

	public void setAddress(PayAddress address)
	{
		this.address = address;
	}

	public int getNameTag()
	{
		return nameTag;
	}

	public void setNameTag(int nameTag)
	{
		this.nameTag = nameTag;
	}

	public HyUser getHyuser()
	{
		return hyuser;
	}

	public void setHyuser(HyUser hyuser)
	{
		this.hyuser = hyuser;
	}

	public WxUser getWxuser()
	{
		return wxuser;
	}

	public void setWxuser(WxUser wxuser)
	{
		this.wxuser = wxuser;
	}

	public SinaUser getSinauser()
	{
		return sinauser;
	}

	public void setSinauser(SinaUser sinauser)
	{
		this.sinauser = sinauser;
	}

	public List<OrderGoods> getOrderGoods()
	{
		return orderGoods;
	}

	public void setOrderGoods(List<OrderGoods> orderGoods)
	{
		this.orderGoods = orderGoods;
	}

	public String getExpressid()
	{
		return expressid;
	}

	public void setExpressid(String expressid)
	{
		this.expressid = expressid;
	}

	public int getSubtype()
	{
		return subtype;
	}

	public void setSubtype(int subtype)
	{
		this.subtype = subtype;
	}

	public List<OrderGoods> getKqGoods()
	{
		return kqGoods;
	}

	public void setKqGoods(List<OrderGoods> kqGoods)
	{
		this.kqGoods = kqGoods;
	}

}
