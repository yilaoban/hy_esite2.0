package com.huiyee.bdmap.dao;

import java.util.List;

import com.huiyee.bdmap.dto.BDLBSDto;
import com.huiyee.bdmap.dto.BDLBSRs;
import com.huiyee.bdmap.dto.BDPoint;

public interface IBDLBSDao
{
	public BDLBSRs saveLBS(BDPoint bdp,String tags,String key);

	public BDLBSRs updateLBS(long id, BDPoint bdp,String tags);
	
	/**
	 * 
	 * @param x 精度
	 * @param y 纬度
	 * @param tags 标签
	 * @param ra  方圆半径数单位米
	 * @return
	 */
	public List<BDLBSDto> findLBS(String x,String y,String tags,int ra,int pagesize);
	
	
	public BDLBSRs deleteLBS(long id);
}
