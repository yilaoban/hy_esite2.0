package com.huiyee.interact.exam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OptionIdx implements Serializable{

	private int idx;
	private int count;
	public int getIdx()
	{
		return idx;
	}
	public void setIdx(int idx)
	{
		this.idx = idx;
	}
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	
	public Map<Integer,Integer> getMaps(){
		Map<Integer,Integer> maps = new HashMap<Integer,Integer>();
		maps.put(this.idx, this.count);
		return maps;
	}
}
