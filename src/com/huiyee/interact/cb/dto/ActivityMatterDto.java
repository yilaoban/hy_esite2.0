package com.huiyee.interact.cb.dto;

import java.util.List;

import com.huiyee.esite.model.CbActivity;
import com.huiyee.weixin.model.WxPageShow;


public class ActivityMatterDto extends CbRsDto
{
	private List<CbActivity> cbActivityList;
	
	private List<WxPageShow> wxPageShowList;
	private WxPageShow wxPageShow;
	
	private int clicknum;//昨日
	private int zhuanfanum;
	private int guanzhunum;
	private int hudongnum;
	
	private int sclicknum;//7天
	private int szhuanfanum;
	private int sguanzhunum;
	private int shudongnum;
	
	private int tclicknum;//总数
	private int tzhuanfanum;
	private int tguanzhunum;
	private int thudongnum;
	
	private long aid;
	private long pageid;//合伙人报告页面
	public List<CbActivity> getCbActivityList()
	{
		return cbActivityList;
	}
	
	public List<WxPageShow> getWxPageShowList()
	{
		return wxPageShowList;
	}


	
	public void setWxPageShowList(List<WxPageShow> wxPageShowList)
	{
		this.wxPageShowList = wxPageShowList;
	}


	public void setCbActivityList(List<CbActivity> cbActivityList)
	{
		this.cbActivityList = cbActivityList;
	}


	public WxPageShow getWxPageShow()
	{
		return wxPageShow;
	}

	public void setWxPageShow(WxPageShow wxPageShow)
	{
		this.wxPageShow = wxPageShow;
	}

	
	public int getClicknum()
	{
		return clicknum;
	}

	
	public void setClicknum(int clicknum)
	{
		this.clicknum = clicknum;
	}

	
	public int getZhuanfanum()
	{
		return zhuanfanum;
	}

	
	public void setZhuanfanum(int zhuanfanum)
	{
		this.zhuanfanum = zhuanfanum;
	}

	
	public int getGuanzhunum()
	{
		return guanzhunum;
	}

	
	public void setGuanzhunum(int guanzhunum)
	{
		this.guanzhunum = guanzhunum;
	}

	
	public int getHudongnum()
	{
		return hudongnum;
	}

	
	public void setHudongnum(int hudongnum)
	{
		this.hudongnum = hudongnum;
	}

	
	public int getSclicknum()
	{
		return sclicknum;
	}

	
	public void setSclicknum(int sclicknum)
	{
		this.sclicknum = sclicknum;
	}

	
	public int getSzhuanfanum()
	{
		return szhuanfanum;
	}

	
	public void setSzhuanfanum(int szhuanfanum)
	{
		this.szhuanfanum = szhuanfanum;
	}

	
	public int getSguanzhunum()
	{
		return sguanzhunum;
	}

	
	public void setSguanzhunum(int sguanzhunum)
	{
		this.sguanzhunum = sguanzhunum;
	}

	
	public int getShudongnum()
	{
		return shudongnum;
	}

	
	public void setShudongnum(int shudongnum)
	{
		this.shudongnum = shudongnum;
	}

	
	public int getTclicknum()
	{
		return tclicknum;
	}

	
	public void setTclicknum(int tclicknum)
	{
		this.tclicknum = tclicknum;
	}

	
	public int getTzhuanfanum()
	{
		return tzhuanfanum;
	}

	
	public void setTzhuanfanum(int tzhuanfanum)
	{
		this.tzhuanfanum = tzhuanfanum;
	}

	
	public int getTguanzhunum()
	{
		return tguanzhunum;
	}

	
	public void setTguanzhunum(int tguanzhunum)
	{
		this.tguanzhunum = tguanzhunum;
	}

	
	public int getThudongnum()
	{
		return thudongnum;
	}

	
	public void setThudongnum(int thudongnum)
	{
		this.thudongnum = thudongnum;
	}

	
	public long getPageid()
	{
		return pageid;
	}

	
	
	public long getAid()
	{
		return aid;
	}

	
	public void setAid(long aid)
	{
		this.aid = aid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
	
}
