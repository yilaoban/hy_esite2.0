
package com.huiyee.interact.ad.model;


public class AdWay
{

	private AdMedia media;
	private Adwd wd;
	private Adwd qwd;
	private Adgg gg;
	private long pageid;
	private long wdid;
	private long qwdid;
	private long mid;
	private long id;
	private String url;
	private String fsurl;
	private String type;
	private int num;
	
	private String idStr;
	
	public String getType()
	{
		return type;
	}

	
	public void setType(String type)
	{
		this.type = type;
	}

	
	public int getNum()
	{
		return num;
	}

	
	public void setNum(int num)
	{
		this.num = num;
	}

	public AdMedia getMedia()
	{
		return media;
	}

	public void setMedia(AdMedia media)
	{
		this.media = media;
	}

	public Adwd getWd()
	{
		return wd;
	}

	public void setWd(Adwd wd)
	{
		this.wd = wd;
	}

	public long getWdid()
	{
		return wdid;
	}

	public void setWdid(long wdid)
	{
		this.wdid = wdid;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public Adwd getQwd()
	{
		return qwd;
	}

	public void setQwd(Adwd qwd)
	{
		this.qwd = qwd;
	}

	public long getQwdid()
	{
		return qwdid;
	}

	public void setQwdid(long qwdid)
	{
		this.qwdid = qwdid;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		if(id > 0){
			idStr = String.format("%07d", id);
		}
		this.id = id;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	
	public String getUrl()
	{
		return url;
	}

	
	public void setUrl(String url)
	{
		this.url = url;
	}

	
	public String getFsurl()
	{
		return fsurl;
	}

	
	public void setFsurl(String fsurl)
	{
		this.fsurl = fsurl;
	}
	
	
	public String getIdStr()
	{
		return idStr;
	}


	
	public void setIdStr(String idStr)
	{
		this.idStr = idStr;
	}


	
	public Adgg getGg()
	{
		return gg;
	}


	
	public void setGg(Adgg gg)
	{
		this.gg = gg;
	}

	
}
