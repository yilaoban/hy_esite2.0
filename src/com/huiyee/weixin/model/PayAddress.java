
package com.huiyee.weixin.model;

public class PayAddress
{

	private long id;
	private long hyuid;
	private String name;
	private String province;
	private String city;
	private String address;
	private String telphone;
	private String isdefault;

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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince(String province)
	{
		this.province = province;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getTelphone()
	{
		return telphone;
	}

	public void setTelphone(String telphone)
	{
		this.telphone = telphone;
	}

	public String getIsdefault()
	{
		return isdefault;
	}

	public void setIsdefault(String isdefault)
	{
		this.isdefault = isdefault;
	}

}
