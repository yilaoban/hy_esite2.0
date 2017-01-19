package com.huiyee.esite.dto.showdto;

import java.io.Serializable;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.CsFuidDraw;
import com.huiyee.interact.cs.model.Cs;

public class Show145Dto implements IDto, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2855246922232521768L;
	
	private Cs cs;
	private CsFuidDraw csFuidDraw;
	private long source;
	private JSONArray ja;
	private JSONObject jb;
	
	public JSONObject getJb()
	{
		return jb;
	}

	public void setJb(JSONObject jb)
	{
		this.jb = jb;
	}

	public JSONArray getJa()
	{
		return ja;
	}

	public void setJa(JSONArray ja)
	{
		this.ja = ja;
	}

	public long getSource()
	{
		return source;
	}

	public void setSource(long source)
	{
		this.source = source;
	}

	public Cs getCs()
	{
		return cs;
	}

	public void setCs(Cs cs)
	{
		this.cs = cs;
	}

	public CsFuidDraw getCsFuidDraw()
	{
		return csFuidDraw;
	}

	public void setCsFuidDraw(CsFuidDraw csFuidDraw)
	{
		this.csFuidDraw = csFuidDraw;
	}
	
}
