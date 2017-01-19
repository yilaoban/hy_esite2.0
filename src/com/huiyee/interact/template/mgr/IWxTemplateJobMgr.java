package com.huiyee.interact.template.mgr;

import java.util.List;

import com.huiyee.interact.template.model.WxTemplateJob;

public interface IWxTemplateJobMgr {

	public int getJobCount(long mpid);

	public int getJobCount(long mpid, String type, long entityid);

	public int getJobCount(long mpid, List<String> types, long entityid);

	public List<WxTemplateJob> getJobList(long mpid, int start, int rows);

	public List<WxTemplateJob> getJobList(long mpid, String type, long entityid, int start, int rows);

	public List<WxTemplateJob> getJobList(long mpid, List<String> types, long entityid, int start, int rows);

}
