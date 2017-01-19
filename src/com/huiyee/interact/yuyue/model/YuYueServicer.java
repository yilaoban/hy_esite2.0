
package com.huiyee.interact.yuyue.model;

import java.util.List;

import com.huiyee.interact.servicerpj.model.ServicerPjWd;

public class YuYueServicer
{

	private long id;
	private String name;
	private String hydesc;
	private String hyldesc;
	private long yyid;
	private long caid;
	private String img;
	private String simg;
	private long ssid;
	private List<YuYueTag> tagList;

	private List<String> tagname;
	private int idx;
	private int top;// ÷√∂•=1 Œ¥÷√∂•=0
	private int type;

	private String[] ids;
	private long owner;
	private int oidx;
	private int otop;
	
	private int leveltotal;
	private int pjtotal;
	private List<ServicerPjWd> wdList;

	public String[] getIds()
	{
		return ids;
	}

	public void setIds(String[] ids)
	{
		this.ids = ids;
	}

	public List<String> getTagname()
	{
		return tagname;
	}

	public void setTagname(List<String> tagname)
	{
		this.tagname = tagname;
	}

	public List<YuYueTag> getTagList()
	{
		return tagList;
	}

	public void setTagList(List<YuYueTag> tagList)
	{
		this.tagList = tagList;
	}

	public long getSsid()
	{
		return ssid;
	}

	public void setSsid(long ssid)
	{
		this.ssid = ssid;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getHydesc()
	{
		return hydesc;
	}

	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}

	public String getHyldesc()
	{
		return hyldesc;
	}

	public void setHyldesc(String hyldesc)
	{
		this.hyldesc = hyldesc;
	}

	public long getYyid()
	{
		return yyid;
	}

	public void setYyid(long yyid)
	{
		this.yyid = yyid;
	}

	public long getCaid()
	{
		return caid;
	}

	public void setCaid(long caid)
	{
		this.caid = caid;
	}

	public String getImg()
	{
		return img;
	}

	public void setImg(String img)
	{
		this.img = img;
	}

	public int getIdx()
	{
		return idx;
	}

	public void setIdx(int idx)
	{
		this.idx = idx;
	}

	public String getSimg()
	{
		return simg;
	}

	public void setSimg(String simg)
	{
		this.simg = simg;
	}

	public int getTop()
	{
		return top;
	}

	public void setTop(int top)
	{
		this.top = top;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
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

	public int getOidx()
	{
		return oidx;
	}

	public void setOidx(int oidx)
	{
		this.oidx = oidx;
	}

	public int getOtop()
	{
		return otop;
	}

	public void setOtop(int otop)
	{
		this.otop = otop;
	}

	public int getLeveltotal() {
		return leveltotal;
	}

	public void setLeveltotal(int leveltotal) {
		this.leveltotal = leveltotal;
	}

	public int getPjtotal() {
		return pjtotal;
	}

	public void setPjtotal(int pjtotal) {
		this.pjtotal = pjtotal;
	}

	public List<ServicerPjWd> getWdList() {
		return wdList;
	}

	public void setWdList(List<ServicerPjWd> wdList) {
		this.wdList = wdList;
	}

}
