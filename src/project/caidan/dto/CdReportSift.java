
package project.caidan.dto;

import java.io.Serializable;

public class CdReportSift implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2370157028361697173L;
	private String starttime;
	private String endtime;
	private long catid;
	private String accounttype;
	private String wayname;

	public String getStarttime()
	{
		return starttime;
	}

	public void setStarttime(String starttime)
	{
		this.starttime = starttime;
	}

	public String getEndtime()
	{
		return endtime;
	}

	public void setEndtime(String endtime)
	{
		this.endtime = endtime;
	}

	public long getCatid()
	{
		return catid;
	}

	public void setCatid(long catid)
	{
		this.catid = catid;
	}

	public String getAccounttype()
	{
		return accounttype;
	}

	public void setAccounttype(String accounttype)
	{
		this.accounttype = accounttype;
	}

	public String getWayname()
	{
		return wayname;
	}

	public void setWayname(String wayname)
	{
		this.wayname = wayname;
	}

}
