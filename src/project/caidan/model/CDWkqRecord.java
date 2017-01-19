
package project.caidan.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.huiyee.esite.model.WxUser;

public class CDWkqRecord
{

	private long hyuid;
	private long pid;
	private WxUser wxUser;
	private Coupon coupon;
	private Date createtime;
	private String formatDate;

	public WxUser getWxUser()
	{
		return wxUser;
	}

	public void setWxUser(WxUser wxUser)
	{
		this.wxUser = wxUser;
	}

	public Coupon getCoupon()
	{
		return coupon;
	}

	public void setCoupon(Coupon coupon)
	{
		this.coupon = coupon;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		if(createtime!=null){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd HH.mm");
			this.formatDate=sdf.format(createtime);
		}
		this.createtime = createtime;
	}

	public long getHyuid()
	{
		return hyuid;
	}

	public void setHyuid(long hyuid)
	{
		this.hyuid = hyuid;
	}

	public long getPid()
	{
		return pid;
	}

	public void setPid(long pid)
	{
		this.pid = pid;
	}

}
