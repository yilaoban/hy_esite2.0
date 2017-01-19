package com.huiyee.interact.template.dao;

import java.util.List;

import com.huiyee.interact.template.model.WxTemplateJob;

public interface IWxTemplateJobDao {

	public int getJobCount(long mpid);

	public int getJobCount(long mpid, String type, long entityid);

	public int getJobCount(long mpid, List<String> types, long entityid);

	public List<WxTemplateJob> getJobList(long mpid, int start, int rows);

	public List<WxTemplateJob> getJobList(long mpid, String type, long entityid, int start, int rows);

	public List<WxTemplateJob> getJobList(long mpid, List<String> types, long entityid, int start, int rows);

	public int addTemplateJob(WxTemplateJob job);

}
