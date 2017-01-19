package com.huiyee.interact.yuyue.model;


public class YuYueService
{
	private long id;
	private String name;
	private String hydesc;
	private String hyldesc;
	private long yyid;
	private long caid;
	private int idx;
	private String img;
	private String simg;
	
	private String[] ids;
	
	public String[] getIds()
	{
		return ids;
	}

	
	public void setIds(String[] ids)
	{
		this.ids = ids;
	}


	public String getImg()
	{
		return img;
	}

	
	public void setImg(String img)
	{
		this.img = img;
	}

	
	public String getSimg()
	{
		return simg;
	}

	
	public void setSimg(String simg)
	{
		this.simg = simg;
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

	
	public int getIdx()
	{
		return idx;
	}

	
	public void setIdx(int idx)
	{
		this.idx = idx;
	}
	
	
}
