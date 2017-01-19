
package project.caidan.model;

import java.util.Date;
import java.util.List;

public class CDAccountDl
{

	private long id;
	private long wxuid;
	private long clid;
	private String name;
	private String province;
	private String city;
	private String telphone;
	private String hydesc;
	private String dpname;
	private String status;
	private long fawxuid;

	private List<CDAccountCpz> cpzList;
	private int todayNum;// 今日兑换金额
	private int total;// 累积兑换金额

	private String nickname;
	private String headimgurl;
	private Date createtime;

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getHeadimgurl()
	{
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl)
	{
		this.headimgurl = headimgurl;
	}

	public int getTodayNum()
	{
		return todayNum;
	}

	public void setTodayNum(int todayNum)
	{
		this.todayNum = todayNum;
	}

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public List<CDAccountCpz> getCpzList()
	{
		return cpzList;
	}

	public void setCpzList(List<CDAccountCpz> cpzList)
	{
		this.cpzList = cpzList;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getWxuid()
	{
		return wxuid;
	}

	public void setWxuid(long wxuid)
	{
		this.wxuid = wxuid;
	}

	public long getClid()
	{
		return clid;
	}

	public void setClid(long clid)
	{
		this.clid = clid;
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

	public String getTelphone()
	{
		return telphone;
	}

	public void setTelphone(String telphone)
	{
		this.telphone = telphone;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public long getFawxuid()
	{
		return fawxuid;
	}

	public void setFawxuid(long fawxuid)
	{
		this.fawxuid = fawxuid;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	
	public String getHydesc()
	{
		return hydesc;
	}

	
	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}

	
	public String getDpname()
	{
		return dpname;
	}

	
	public void setDpname(String dpname)
	{
		this.dpname = dpname;
	}

}
