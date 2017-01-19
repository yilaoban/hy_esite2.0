package com.huiyee.bdmap.dto;

public class BDPoint
{
	private String name;
	private BDLocation location;
	private String address;
	private String street_id;
	private String uid;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public BDLocation getLocation()
	{
		return location;
	}

	public void setLocation(BDLocation location)
	{
		this.location = location;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getStreet_id()
	{
		return street_id;
	}

	public void setStreet_id(String street_id)
	{
		this.street_id = street_id;
	}

	public String getUid()
	{
		return uid;
	}

	public void setUid(String uid)
	{
		this.uid = uid;
	}
}
