package project.caidan.model;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CDRmbGet
{
	private long id;
	private int rmb;
	private long wxuid;
	private Date createtime;
	private String status;
	private String errreason;
	
	private String nickname;
	private String formatDate;
	private String mch_billno;
	private String send_listid;
	
	public String getNickname()
	{
		return nickname;
	}
	
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public int getRmb()
	{
		return rmb;
	}
	
	public void setRmb(int rmb)
	{
		this.rmb = rmb;
	}
	
	public long getWxuid()
	{
		return wxuid;
	}
	
	public void setWxuid(long wxuid)
	{
		this.wxuid = wxuid;
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
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public String getErrreason()
	{
		return errreason;
	}
	
	public void setErrreason(String errreason)
	{
		this.errreason = errreason;
	}

	
	public String getMch_billno()
	{
		return mch_billno;
	}

	
	public void setMch_billno(String mch_billno)
	{
		this.mch_billno = mch_billno;
	}

	
	public String getSend_listid()
	{
		return send_listid;
	}

	
	public void setSend_listid(String send_listid)
	{
		this.send_listid = send_listid;
	}
	
	
}
