
package project.caidan.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.util.DateUtil;

public class Coupon implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6182196195336453151L;
	private ContentProduct product;
	private long id;
	private long pid;
	private int zdrmb;
	private int xjrmb;
	private int rmb;
	private Date endtime;
	private List<Long> caid;
	private List<CDWay> ways;
	private List<Long> wayid;

	public ContentProduct getProduct()
	{
		return product;
	}

	public void setProduct(ContentProduct product)
	{
		this.product = product;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getPid()
	{
		return pid;
	}

	public void setPid(long pid)
	{
		this.pid = pid;
	}

	public int getZdrmb()
	{
		return zdrmb;
	}

	public void setZdrmb(int zdrmb)
	{
		this.zdrmb = zdrmb;
	}

	public int getXjrmb()
	{
		return xjrmb;
	}

	public void setXjrmb(int xjrmb)
	{
		this.xjrmb = xjrmb;
	}

	public List<CDWay> getWays()
	{
		return ways;
	}

	public void setWays(List<CDWay> ways)
	{
		this.ways = ways;
	}

	public List<Long> getCaid()
	{
		return caid;
	}

	public void setCaid(List<Long> caid)
	{
		this.caid = caid;
	}

	public List<Long> getWayid()
	{
		return wayid;
	}

	public void setWayid(List<Long> wayid)
	{
		this.wayid = wayid;
	}

	public int getRmb()
	{
		return rmb;
	}

	public void setRmb(int rmb)
	{
		this.rmb = rmb;
	}

	public Date getEndtime()
	{
		return endtime;
	}

	public void setEndtime(String endtime)
	{
		if(StringUtils.isNotEmpty(endtime))
			this.endtime = DateUtil.getDateTime(endtime);
	}
}
