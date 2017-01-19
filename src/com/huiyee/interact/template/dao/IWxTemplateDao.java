package com.huiyee.interact.template.dao;

import java.util.List;

import com.huiyee.interact.template.model.WxTemplate;

public interface IWxTemplateDao {

	public int getTemplateCount(long owner);

	public int getTemplateCount(long owner, String type, long entityid);

	public int getTemplateCount(long mpid, String template_id);

	public List<WxTemplate> getTemplateList(long owner, int start, int rows);

	public List<WxTemplate> getTemplateList(long owner, String type, long entityid, int start, int rows);

	public List<WxTemplate> getTemplateList(long owner, String type);

	public List<WxTemplate> getTemplateList(long owner, String type, long entityid);

	public WxTemplate getTemplate(long id);

	public WxTemplate getTemplate(long owner, long store_id);

	public int addTemplate(WxTemplate wt);

	public int[] addTemplate(List<WxTemplate> list);

	public int updateTemplate(WxTemplate wt);

	public int delTemplate(long id);

	public int delAllTemplate(long owner);

}
