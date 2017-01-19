package com.huiyee.esite.model;

import java.util.Date;


public class UserBalance
{
	private long id;
	private long hyuid;
	private int total;
	private int used;
	private int preused;
	private Date lastaddtime;
	private Date lastusetime;
	private String nickname;
	private int remain;//Ê£Óà»ý·Ö
	private String img;
	private int rmbtotal;
	private int rmbused;
	private int rmbpreused;
	private Date rmblastaddtime;
	private Date rmblastusetime;
	private HyUser hyuser;
	
	public int getRemain()
	{
		return remain;
	}


	
	public void setRemain(int remain)
	{
		this.remain = remain;
	}


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
	
	public long getHyuid()
	{
		return hyuid;
	}
	
	public void setHyuid(long hyuid)
	{
		this.hyuid = hyuid;
	}
	
	public int getTotal()
	{
		return total;
	}
	
	public void setTotal(int total)
	{
		this.total = total;
	}
	
	public int getUsed()
	{
		return used;
	}
	
	public void setUsed(int used)
	{
		this.used = used;
	}
	
	public int getPreused()
	{
		return preused;
	}
	
	public void setPreused(int preused)
	{
		this.preused = preused;
	}
	
	public Date getLastaddtime()
	{
		return lastaddtime;
	}
	
	public void setLastaddtime(Date lastaddtime)
	{
		this.lastaddtime = lastaddtime;
	}
	
	public Date getLastusetime()
	{
		return lastusetime;
	}
	
	public void setLastusetime(Date lastusetime)
	{
		this.lastusetime = lastusetime;
	}



	
	public String getImg()
	{
		return img;
	}



	
	public void setImg(String img)
	{
		this.img = img;
	}



	public int getRmbtotal() {
		return rmbtotal;
	}



	public void setRmbtotal(int rmbtotal) {
		this.rmbtotal = rmbtotal;
	}



	public int getRmbused() {
		return rmbused;
	}



	public void setRmbused(int rmbused) {
		this.rmbused = rmbused;
	}



	public int getRmbpreused() {
		return rmbpreused;
	}



	public void setRmbpreused(int rmbpreused) {
		this.rmbpreused = rmbpreused;
	}



	public Date getRmblastaddtime() {
		return rmblastaddtime;
	}



	public void setRmblastaddtime(Date rmblastaddtime) {
		this.rmblastaddtime = rmblastaddtime;
	}



	public Date getRmblastusetime() {
		return rmblastusetime;
	}



	public void setRmblastusetime(Date rmblastusetime) {
		this.rmblastusetime = rmblastusetime;
	}



	
	public HyUser getHyuser()
	{
		return hyuser;
	}



	
	public void setHyuser(HyUser hyuser)
	{
		this.hyuser = hyuser;
	}
	
	
	
}
