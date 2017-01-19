
package com.huiyee.bdmap.dto;

import net.sf.json.JSONObject;

public class BDAddress
{

	private int status;

	private String city;
	private String country;
	private int country_code;
	private String direction;
	private String distance;
	private String district;
	private String province;
	private String street;
	private String street_number;

	private String business;
	private int cityCode;
	private String formatted_address;

	public BDAddress()
	{
		super();
	}

	public BDAddress(JSONObject json)
	{
		super();
		try
		{
			if (json != null)
			{
				if (json.containsKey("status"))
				{
					status = json.getInt("status");
				}
				if (json.containsKey("result"))
				{
					JSONObject result = json.getJSONObject("result");
					if (result.containsKey("addressComponent"))
					{
						JSONObject addressComponent = result.getJSONObject("addressComponent");
						if (addressComponent.containsKey("country"))
						{
							country = addressComponent.getString("country");
						}
						if (addressComponent.containsKey("province"))
						{
							province = addressComponent.getString("province");
						}
						if (addressComponent.containsKey("city"))
						{
							city = addressComponent.getString("city");
						}
						if (addressComponent.containsKey("district"))
						{
							district = addressComponent.getString("district");
						}
						if (addressComponent.containsKey("street"))
						{
							street = addressComponent.getString("street");
						}
						if (addressComponent.containsKey("street_number"))
						{
							street_number = addressComponent.getString("street_number");
						}
						if (addressComponent.containsKey("country_code"))
						{
							country_code = addressComponent.getInt("country_code");
						}
						if (addressComponent.containsKey("direction"))
						{
							direction = addressComponent.getString("direction");
						}
						if (addressComponent.containsKey("distance"))
						{
							distance = addressComponent.getString("distance");
						}
					}

					if (result.containsKey("business"))
					{
						business = result.getString("business");
					}
					if (result.containsKey("cityCode"))
					{
						cityCode = result.getInt("cityCode");
					}
					if (result.containsKey("formatted_address"))
					{
						formatted_address = result.getString("formatted_address");
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public int getCountry_code()
	{
		return country_code;
	}

	public void setCountry_code(int country_code)
	{
		this.country_code = country_code;
	}

	public String getDirection()
	{
		return direction;
	}

	public void setDirection(String direction)
	{
		this.direction = direction;
	}

	public String getDistance()
	{
		return distance;
	}

	public void setDistance(String distance)
	{
		this.distance = distance;
	}

	public String getDistrict()
	{
		return district;
	}

	public void setDistrict(String district)
	{
		this.district = district;
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince(String province)
	{
		this.province = province;
	}

	public String getStreet()
	{
		return street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public String getStreet_number()
	{
		return street_number;
	}

	public void setStreet_number(String street_number)
	{
		this.street_number = street_number;
	}

	public String getBusiness()
	{
		return business;
	}

	public void setBusiness(String business)
	{
		this.business = business;
	}

	public int getCityCode()
	{
		return cityCode;
	}

	public void setCityCode(int cityCode)
	{
		this.cityCode = cityCode;
	}

	public String getFormatted_address()
	{
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address)
	{
		this.formatted_address = formatted_address;
	}

}
