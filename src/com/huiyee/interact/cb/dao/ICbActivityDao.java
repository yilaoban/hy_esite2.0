package com.huiyee.interact.cb.dao;

import java.util.List;

import com.huiyee.esite.model.CbActivity;
import com.huiyee.interact.cb.model.InteractCb;
import com.huiyee.interact.cb.model.WxHbCheck;

public interface ICbActivityDao {

	public List<CbActivity> findActivityList(long owner, int start, int rows);

	public CbActivity findActivity(long id);

	public InteractCb findCb(long owner);

	public WxHbCheck findCbHbCheck(long mpid, long aid);

}
