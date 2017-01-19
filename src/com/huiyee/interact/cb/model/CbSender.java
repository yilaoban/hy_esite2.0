package com.huiyee.interact.cb.model;

import java.io.Serializable;
import java.util.Date;

import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.appointment.model.AppointmentDataModel;

public class CbSender implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AppointmentDataModel record;
	private long id;
	private long hyuid;
	private long wxuid;
	private long aptid;
	private long recordid;
	private String status;
	private long owner;
	private int hbtotal;
	private int hbused;
	private String reason;
	private Date lastUpTime;
	private long fatherid;
	private WxUser wxUser;
	private int jf;
	
	public int getJf() {
		return jf;
	}

	public void setJf(int jf) {
		this.jf = jf;
	}

	public WxUser getWxUser() {
		return wxUser;
	}

	public void setWxUser(WxUser wxUser) {
		this.wxUser = wxUser;
	}

	public AppointmentDataModel getRecord()
	{
		return record;
	}

	public void setRecord(AppointmentDataModel record)
	{
		this.record = record;
	}

	public long getWxuid()
	{
		return wxuid;
	}

	public void setWxuid(long wxuid)
	{
		this.wxuid = wxuid;
	}

	public long getAptid()
	{
		return aptid;
	}

	public void setAptid(long aptid)
	{
		this.aptid = aptid;
	}

	public long getRecordid()
	{
		return recordid;
	}

	public void setRecordid(long recordid)
	{
		this.recordid = recordid;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public long getOwner()
	{
		return owner;
	}

	public void setOwner(long owner)
	{
		this.owner = owner;
	}

	public int getHbtotal()
	{
		return hbtotal;
	}

	public void setHbtotal(int hbtotal)
	{
		this.hbtotal = hbtotal;
	}

	public int getHbused()
	{
		return hbused;
	}

	public void setHbused(int hbused)
	{
		this.hbused = hbused;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public Date getLastUpTime()
	{
		return lastUpTime;
	}

	public void setLastUpTime(Date lastUpTime)
	{
		this.lastUpTime = lastUpTime;
	}

	public String getReason()
	{
		return reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}

	public long getHyuid() {
		return hyuid;
	}

	public void setHyuid(long hyuid) {
		this.hyuid = hyuid;
	}

	public long getFatherid() {
		return fatherid;
	}

	public void setFatherid(long fatherid) {
		this.fatherid = fatherid;
	}
	
}
