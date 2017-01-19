package com.huiyee.tfmodel.param;

import java.util.Date;

import com.huiyee.tfmodel.TfConstants;

public class TfWeiBoMonitorDto implements java.io.Serializable
{ 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String wbid;//监控的微博id
	private String type;//F-转发,C-评论,A-赞
	private String startRow;//关键字开始的index
	private String endRow;//结束的index
	private Date starttime;
	private Date endtime;
	private int verified=TfConstants.NOT_REQUIRED;//暂时用不到
	private int verifiedType=TfConstants.NOT_REQUIRED;//四位整数,千位代表普通用户,百位代表达人,十位代表个人认证,个位代表企业认证
	private int province=TfConstants.NOT_REQUIRED;//省份编码
	private int city=TfConstants.NOT_REQUIRED;//城市编码
	private int minF=TfConstants.NOT_REQUIRED;//粉丝数最小值
	private int maxF=TfConstants.NOT_REQUIRED;//粉丝数最大值
	private int gender=TfConstants.NOT_REQUIRED;//1:男;2:女
	private long pageSize=100;
	public String getWbid()
	{
		return wbid;
	}
	public void setWbid(String wbid)
	{
		this.wbid = wbid;
	}
	public String getStartRow()
	{
		return startRow;
	}
	public void setStartRow(String startRow)
	{
		this.startRow = startRow;
	}
	public int getVerified()
	{
		return verified;
	}
	public void setVerified(int verified)
	{
		this.verified = verified;
	}
	public int getVerifiedType()
	{
		return verifiedType;
	}
	public void setVerifiedType(int verifiedType)
	{
		this.verifiedType = verifiedType;
	}
	public int getProvince()
	{
		return province;
	}
	public void setProvince(int province)
	{
		this.province = province;
	}
	public int getCity()
	{
		return city;
	}
	public void setCity(int city)
	{
		this.city = city;
	}
	public int getMinF()
	{
		return minF;
	}
	public void setMinF(int minF)
	{
		this.minF = minF;
	}
	public int getMaxF()
	{
		return maxF;
	}
	public void setMaxF(int maxF)
	{
		this.maxF = maxF;
	}
	public int getGender()
	{
		return gender;
	}
	public void setGender(int gender)
	{
		this.gender = gender;
	}
	public long getPageSize()
	{
		return pageSize;
	}
	public void setPageSize(long pageSize)
	{
		this.pageSize = pageSize;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getEndRow()
	{
		return endRow;
	}
	public void setEndRow(String endRow)
	{
		this.endRow = endRow;
	}
	public Date getStarttime()
	{
		return starttime;
	}
	public void setStarttime(Date starttime)
	{
		this.starttime = starttime;
	}
	public Date getEndtime()
	{
		return endtime;
	}
	public void setEndtime(Date endtime)
	{
		this.endtime = endtime;
	}
}
