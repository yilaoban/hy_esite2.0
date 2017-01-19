
package project.caidan.model;

import java.io.Serializable;
import java.util.Date;

import com.huiyee.esite.model.WxUser;

/**
 * ÏûÍö²ÊÆ±¿¨È¯µÄcpz-dl-way
 * 
 * @author ldw
 * 
 */
public class CdWkqRmb implements Serializable
{

	private Date createtime;
	private CDWay way;
	private CDAccountDl dl;
	private CDAccountCpz cpz;
	private WxUser sj;
	private WxUser wxuser;
	private CDProductRmb rmb;
	private long cpzid;
	private long pogid;
	private long pid;
	private long wayid;

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public CDWay getWay()
	{
		return way;
	}

	public void setWay(CDWay way)
	{
		this.way = way;
	}

	public CDAccountDl getDl()
	{
		return dl;
	}

	public void setDl(CDAccountDl dl)
	{
		this.dl = dl;
	}

	public CDAccountCpz getCpz()
	{
		return cpz;
	}

	public void setCpz(CDAccountCpz cpz)
	{
		this.cpz = cpz;
	}

	public WxUser getWxuser()
	{
		return wxuser;
	}

	public void setWxuser(WxUser wxuser)
	{
		this.wxuser = wxuser;
	}

	public CDProductRmb getRmb()
	{
		return rmb;
	}

	public void setRmb(CDProductRmb rmb)
	{
		this.rmb = rmb;
	}

	public WxUser getSj()
	{
		return sj;
	}

	public void setSj(WxUser sj)
	{
		this.sj = sj;
	}

	public long getCpzid()
	{
		return cpzid;
	}

	public void setCpzid(long cpzid)
	{
		this.cpzid = cpzid;
	}

	public long getPogid()
	{
		return pogid;
	}

	public void setPogid(long pogid)
	{
		this.pogid = pogid;
	}

	
	public long getPid()
	{
		return pid;
	}

	
	public void setPid(long pid)
	{
		this.pid = pid;
	}

	
	public long getWayid()
	{
		return wayid;
	}

	
	public void setWayid(long wayid)
	{
		this.wayid = wayid;
	}

}
