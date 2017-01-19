package com.huiyee.esite.dao;

import java.util.List;

public interface IAccountHideDao {

	/**
	 * 查看需隱藏的模塊
	 * @param ownerid
	 * @param accountid
	 * @param type L：右侧栏目 I:互动  C:内容分类
	 * @return
	 */
	public List<Long> findHidIds(long ownerid,long accountid,String type);
	
}
