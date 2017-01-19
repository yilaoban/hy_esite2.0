package com.huiyee.interact.appointment.dto;

import java.util.List;

import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.appointment.model.OrderMappingModel;

public class AddOrderDto implements IDto {

	private List<OrderMappingModel> list;
	private AppointmentModel am;
	private String type;
	private long zlotteryid;
	private long llotteryid;
	private long glotteryid;
	private long ylotteryid;
	
	public long getYlotteryid()
	{
		return ylotteryid;
	}

	public void setYlotteryid(long ylotteryid)
	{
		this.ylotteryid = ylotteryid;
	}

	public AppointmentModel getAm() {
		return am;
	}

	public void setAm(AppointmentModel am) {
		this.am = am;
	}

	public List<OrderMappingModel> getList() {
		return list;
	}

	public void setList(List<OrderMappingModel> list) {
		this.list = list;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public long getZlotteryid()
	{
		return zlotteryid;
	}

	public void setZlotteryid(long zlotteryid)
	{
		this.zlotteryid = zlotteryid;
	}

	public long getLlotteryid()
	{
		return llotteryid;
	}

	public void setLlotteryid(long llotteryid)
	{
		this.llotteryid = llotteryid;
	}

	public long getGlotteryid()
	{
		return glotteryid;
	}

	public void setGlotteryid(long glotteryid)
	{
		this.glotteryid = glotteryid;
	}

}
