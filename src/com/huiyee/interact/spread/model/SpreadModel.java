package com.huiyee.interact.spread.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.model.SuperHdModel;

public class SpreadModel extends SuperHdModel implements Serializable{

	private static final long serialVersionUID = 5268553478126512237L;
	
	private long ownerid;
	private String title;
	private String content;
	private int sharelimit;
	private String repic;
	private Date createtime;
	private Date updatetime;
	private String type;
	private String status;
	private int htype;
	
	public SpreadModel(){
		setFeatureid(IInteractConstants.INTERACT_SPREAD);
	}
	
	private List<SpreadOption> options;

	public int getHtype()
	{
		return htype;
	}
	public void setHtype(int htype)
	{
		this.htype = htype;
	}
	public long getOwnerid()
	{
		return ownerid;
	}
	public void setOwnerid(long ownerid)
	{
		this.ownerid = ownerid;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public int getSharelimit()
	{
		return sharelimit;
	}
	public void setSharelimit(int sharelimit)
	{
		this.sharelimit = sharelimit;
	}
	public String getRepic()
	{
		return repic;
	}
	public void setRepic(String repic)
	{
		this.repic = repic;
	}
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	public Date getUpdatetime()
	{
		return updatetime;
	}
	public void setUpdatetime(Date updatetime)
	{
		this.updatetime = updatetime;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public List<SpreadOption> getOptions() {
		return options;
	}
	public void setOptions(List<SpreadOption> options) {
		this.options = options;
	}
	
}
