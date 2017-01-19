package com.huiyee.esite.model;

public class UserInfo
{
	private long id;
	private long siteid;
	private String email;
	private String phone;
	private String wbuid;
	private String nickname;
	private String url;
	private String biaoqian;
	private int fensishu;
	private int weiboshu;
	private String createtime;
	private int dcount;//»¥¶¯Êý
	private long userid;
	private String token;
	private long wbuid1;
	
	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getSiteid()
	{
		return siteid;
	}

	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(String wbuid)
	{
		this.wbuid = wbuid;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getBiaoqian()
	{
		return biaoqian;
	}

	public void setBiaoqian(String biaoqian)
	{
		this.biaoqian = biaoqian;
	}

	public int getFensishu()
	{
		return fensishu;
	}

	public void setFensishu(int fensishu)
	{
		this.fensishu = fensishu;
	}

	public int getWeiboshu()
	{
		return weiboshu;
	}

	public void setWeiboshu(int weiboshu)
	{
		this.weiboshu = weiboshu;
	}

	public String getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(String createtime)
	{
		this.createtime = createtime;
	}

    public int getDcount()
    {
        return dcount;
    }

    public void setDcount(int dcount)
    {
        this.dcount = dcount;
    }

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public long getWbuid1()
	{
		return wbuid1;
	}

	public void setWbuid1(long wbuid1)
	{
		this.wbuid1 = wbuid1;
	}

}
