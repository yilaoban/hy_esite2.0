package com.huiyee.interact.bbs.model;

import java.io.Serializable;
import java.util.Date;

public class BBSMessage implements Serializable
{
	private static final long serialVersionUID = 4604816403473599023L;
	private long id;
	private long sender;
	private long receiver;
	private String content;
	private Date create_time;
	private int is_sys;
	private int msg_type;
	private int is_read;
	private String sendername;
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getSender()
	{
		return sender;
	}
	public void setSender(long sender)
	{
		this.sender = sender;
	}
	public long getReceiver()
	{
		return receiver;
	}
	public void setReceiver(long receiver)
	{
		this.receiver = receiver;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public Date getCreate_time()
	{
		return create_time;
	}
	public void setCreate_time(Date create_time)
	{
		this.create_time = create_time;
	}
	public int getIs_sys()
	{
		return is_sys;
	}
	public void setIs_sys(int is_sys)
	{
		this.is_sys = is_sys;
	}
	public int getMsg_type()
	{
		return msg_type;
	}
	public void setMsg_type(int msg_type)
	{
		this.msg_type = msg_type;
	}
	public int getIs_read()
	{
		return is_read;
	}
	public void setIs_read(int is_read)
	{
		this.is_read = is_read;
	}
	public String getSendername()
	{
		return sendername;
	}
	public void setSendername(String sendername)
	{
		this.sendername = sendername;
	}
	
	
}
