
package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;

public class ContentProduct implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7585372363173363887L;
	private long id;
	private long owner;
	private long catid;
	private String catname;
	private String name;
	private String simgurl;
	private String bimgurl;
	private String linkurl;
	private double price;
	private double salesprice;
	private int jifen;
	private int salesjifen;
	private String detail;
	private String status;
	private Date createtime;
	private Date updatetime;
	private String tag;
	private int idx;
	private int length;
	private String fatie;
	private long topicid;
	private String type = "N";// N-内容管理产品 W-微商城产品 J-积分产品
	private String subtype = "S";// S-实物 C-优惠券 K-卡券
	private int total;
	private int used;
	private int shownum;//展示的已经使用的数量
	private ContentProduct before;
	private ContentProduct next;
	
	private int maxjf;
	private long fatherid;
	private int personlimit;
	private int vip;
	private Map<Long,Object> plevelMap;//会员价
	
	//拓展字段
	private String f1;
	private String f2;
	private String f3;
	private String f4;
	private String f5;
	
	
	public String getF1()
	{
		return f1;
	}

	
	public void setF1(String f1)
	{
		this.f1 = f1;
	}

	
	public String getF2()
	{
		return f2;
	}

	
	public void setF2(String f2)
	{
		this.f2 = f2;
	}

	
	public String getF3()
	{
		return f3;
	}

	
	public void setF3(String f3)
	{
		this.f3 = f3;
	}

	
	public String getF4()
	{
		return f4;
	}

	
	public void setF4(String f4)
	{
		this.f4 = f4;
	}

	
	public String getF5()
	{
		return f5;
	}

	
	public void setF5(String f5)
	{
		this.f5 = f5;
	}

	public Map<Long, Object> getPlevelMap()
	{
		return plevelMap;
	}
	
	public void setPlevelMap(Map<Long, Object> plevelMap)
	{
		this.plevelMap = plevelMap;
	}


	public int getVip() {
		return vip;
	}


	public void setVip(int vip) {
		this.vip = vip;
	}


	public static final String PRODUCT_SUBTYPE_K="K";
	
	
	public int getPersonlimit()
	{
		return personlimit;
	}

	
	public void setPersonlimit(int personlimit)
	{
		this.personlimit = personlimit;
	}

	public ContentProduct getBefore()
	{
		return before;
	}

	public void setBefore(ContentProduct before)
	{
		this.before = before;
	}

	public ContentProduct getNext()
	{
		return next;
	}

	public void setNext(ContentProduct next)
	{
		this.next = next;
	}

	public int getTotal()
	{
		return total;
	}

	public int getUsed()
	{
		return used;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public void setUsed(int used)
	{
		this.used = used;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public long getOwner()
	{
		return owner;
	}

	public void setOwner(long owner)
	{
		this.owner = owner;
	}

	public long getCatid()
	{
		return catid;
	}

	public void setCatid(long catid)
	{
		this.catid = catid;
	}

	public String getCatname()
	{
		return catname;
	}

	public void setCatname(String catname)
	{
		this.catname = catname;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSimgurl()
	{
		return simgurl;
	}

	public void setSimgurl(String simgurl)
	{
		this.simgurl = simgurl;
	}

	public String getBimgurl()
	{
		return bimgurl;
	}

	public void setBimgurl(String bimgurl)
	{
		this.bimgurl = bimgurl;
	}

	public String getLinkurl()
	{
		return linkurl;
	}

	public void setLinkurl(String linkurl)
	{
		this.linkurl = linkurl;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
		this.jifen = (int) price;
	}

	public String getDetail()
	{
		return detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public Date getUpdatetime()
	{
		return updatetime;
	}

	public void setUpdatetime(Date updatetime)
	{
		this.updatetime = updatetime;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public long getWbid()
	{
		return new Double(price).longValue();
	}

	public String getTag()
	{
		return tag;
	}

	public void setTag(String tag)
	{
		this.tag = tag;
	}

	public int getIdx()
	{
		return idx;
	}

	public void setIdx(int idx)
	{
		this.idx = idx;
	}

	public double getSalesprice()
	{
		return salesprice;
	}

	public void setSalesprice(double salesprice)
	{
		this.salesprice = salesprice;
		this.salesjifen = (int) salesprice;
	}

	public int getLength()
	{
		return length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public String getFatie()
	{
		return fatie;
	}

	public void setFatie(String fatie)
	{
		this.fatie = fatie;
	}

	public long getTopicid()
	{
		return topicid;
	}

	public void setTopicid(long topicid)
	{
		this.topicid = topicid;
	}

	public String getSubtype()
	{
		return subtype;
	}

	public void setSubtype(String subtype)
	{
		this.subtype = subtype;
	}

	public int getMaxjf()
	{
		return maxjf;
	}

	public void setMaxjf(int maxjf)
	{
		this.maxjf = maxjf;
	}

	public int getJifen()
	{
		return jifen;
	}

	public void setJifen(int jifen)
	{
		this.jifen = jifen;
	}

	public int getSalesjifen()
	{
		return salesjifen;
	}

	public void setSalesjifen(int salesjifen)
	{
		this.salesjifen = salesjifen;
	}

	public long getFatherid()
	{
		return fatherid;
	}

	public void setFatherid(long fatherid)
	{
		this.fatherid = fatherid;
	}

	
	public int getShownum()
	{
		return shownum;
	}

	
	public void setShownum(int shownum)
	{
		this.shownum = shownum;
	}
	
	public JSONObject getF1Json(){
		if(this.f1 != null){
			return JSONObject.fromObject(this.f1);
		}
		return new JSONObject();
	}
	
	public JSONObject getF2Json(){
		if(this.f3 != null){
			return JSONObject.fromObject(this.f2);
		}
		return new JSONObject();
	}
	public JSONObject getF3Json(){
		if(this.f3 != null){
			return JSONObject.fromObject(this.f3);
		}
		return new JSONObject();
	}
	public JSONObject getF4Json(){
		if(this.f4 != null){
			return JSONObject.fromObject(this.f4);
		}
		return new JSONObject();
	}
	public JSONObject getF5Json(){
		if(this.f5 != null){
			return JSONObject.fromObject(this.f5);
		}
		return new JSONObject();
	}

}
