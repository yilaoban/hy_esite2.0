package com.huiyee.interact.xc.service;

import java.util.List;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.xc.model.XcRecord;

public interface IXcService {

	public int start(long xcid);

	public int process(long xcid, VisitUser vu, String ip, String device);

	public List<XcRecord> getResult(long xcid);

	public void saveRecords(long xcid);

	public List<WxUser> getWxUser(List<XcRecord> records);

	public void removeCache(long xcid);

}
