package com.huiyee.interact.auction.model;

import java.io.Serializable;
import java.util.Date;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.model.SuperHdModel;

public class Auction extends SuperHdModel implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4611535769844957687L;
	private String title;
	private long owner;
	private Date proendTime;
	private int startbalance;
	private int addbalance;
	private String simg;
	private String url;
	private String description;
	private int addsecond;
	private int lastsecond;
	private int currentmax;
	private String status;
	private long urid;
	
	private String userName;
	private String userPhone;
	private String userEmail;
	private String userLocation;
	
	public Auction(){
		setFeatureid(IInteractConstants.INTERACT_AUCTION);
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public long getOwner()
	{
		return owner;
	}

	public void setOwner(long owner)
	{
		this.owner = owner;
	}

	public Date getProendTime()
	{
		return proendTime;
	}

	public void setProendTime(Date proendTime)
	{
		this.proendTime = proendTime;
	}

	public int getStartbalance()
	{
		return startbalance;
	}

	public void setStartbalance(int startbalance)
	{
		this.startbalance = startbalance;
	}

	public int getAddbalance()
	{
		return addbalance;
	}

	public void setAddbalance(int addbalance)
	{
		this.addbalance = addbalance;
	}

	public String getSimg()
	{
		return simg;
	}

	public void setSimg(String simg)
	{
		this.simg = simg;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getAddsecond()
	{
		return addsecond;
	}

	public void setAddsecond(int addsecond)
	{
		this.addsecond = addsecond;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public long getUrid()
	{
		return urid;
	}

	public void setUrid(long urid)
	{
		this.urid = urid;
	}

	public int getLastsecond()
	{
		return lastsecond;
	}

	public void setLastsecond(int lastsecond)
	{
		this.lastsecond = lastsecond;
	}

	public int getCurrentmax()
	{
		return currentmax;
	}

	public void setCurrentmax(int currentmax)
	{
		this.currentmax = currentmax;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserPhone()
	{
		return userPhone;
	}

	public void setUserPhone(String userPhone)
	{
		this.userPhone = userPhone;
	}

	public String getUserEmail()
	{
		return userEmail;
	}

	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}

	public String getUserLocation()
	{
		return userLocation;
	}

	public void setUserLocation(String userLocation)
	{
		this.userLocation = userLocation;
	}

}
