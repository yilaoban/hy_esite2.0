package project.caidan.model;

import java.util.Date;

import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.util.DateUtil;


public class CDProductRmb
{
	private long id;
	private long pid;
	private int zdrmb;
	private int xjrmb;
	private int rmb;
	private Date endtime;
	private String endtimeStr;
	
	private ContentProduct product;
//	private PayOrder payOrder;
	private OrderGoods goods;
	
	public ContentProduct getProduct()
	{
		return product;
	}

	public void setProduct(ContentProduct product)
	{
		this.product = product;
	}


	public OrderGoods getGoods()
	{
		return goods;
	}

	
	public void setGoods(OrderGoods goods)
	{
		this.goods = goods;
	}

	public Date getEndtime()
	{
		return endtime;
	}

	public void setEndtime(Date endtime)
	{
		if(endtime != null){
			endtimeStr = DateUtil.getDateString(endtime);
		}
		this.endtime = endtime;
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
	
	public int getRmb()
	{
		return rmb;
	}
	
	public void setRmb(int rmb)
	{
		this.rmb = rmb;
	}


	
	public String getEndtimeStr()
	{
		return endtimeStr;
	}


	
	public void setEndtimeStr(String endtimeStr)
	{
		this.endtimeStr = endtimeStr;
	}
	
	
	
}
