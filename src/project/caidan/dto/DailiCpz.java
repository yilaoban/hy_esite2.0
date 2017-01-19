
package project.caidan.dto;

public class DailiCpz
{

	private long wxuid;// 彩票站
	private long clid;// 渠道代理
	private long fawxuid;// 县级代理
	
	public long getWxuid()
	{
		return wxuid;
	}
	
	public long getClid()
	{
		return clid;
	}
	
	public long getFawxuid()
	{
		return fawxuid;
	}
	
	public void setWxuid(long wxuid)
	{
		this.wxuid = wxuid;
	}
	
	public void setClid(long clid)
	{
		this.clid = clid;
	}
	
	public void setFawxuid(long fawxuid)
	{
		this.fawxuid = fawxuid;
	}
}
