package project.caidan.dto;

import java.util.Date;


public class ProductRMB
{
 private long pid;
 private int zdrmb;
 private int xjrmb;
 private int rmb;
 private Date endtime;



public Date getEndtime()
{
	return endtime;
}



public void setEndtime(Date endtime)
{
	this.endtime = endtime;
}


public int getRmb()
{
	return rmb;
}


public void setRmb(int rmb)
{
	this.rmb = rmb;
}

public long getPid()
{
	return pid;
}

public int getZdrmb()
{
	return zdrmb;
}

public int getXjrmb()
{
	return xjrmb;
}

public void setPid(long pid)
{
	this.pid = pid;
}

public void setZdrmb(int zdrmb)
{
	this.zdrmb = zdrmb;
}

public void setXjrmb(int xjrmb)
{
	this.xjrmb = xjrmb;
}
}
