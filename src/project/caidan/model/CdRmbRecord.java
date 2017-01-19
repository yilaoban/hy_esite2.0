
package project.caidan.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.huiyee.esite.model.WxUser;

import freemarker.template.SimpleDate;

public class CdRmbRecord
{

	private long id;
	private int total;
	private int num;
	private long wxuid;
	private String hydesc;
	private String source;
	private Date createtime;
	private WxUser wxuser;
	private String formatDate;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public int getNum()
	{
		return num;
	}

	public void setNum(int num)
	{
		this.num = num;
	}

	public long getWxuid()
	{
		return wxuid;
	}

	public void setWxuid(long wxuid)
	{
		this.wxuid = wxuid;
	}

	public String getHydesc()
	{
		return hydesc;
	}

	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
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

	public WxUser getWxuser()
	{
		return wxuser;
	}

	public void setWxuser(WxUser wxuser)
	{
		this.wxuser = wxuser;
	}

}
