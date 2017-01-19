
package com.huiyee.esite.fmgr.imp;

import java.util.List;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.huiyee.esite.dao.ISiteDao;
import com.huiyee.esite.dao.ITemplateDao;
import com.huiyee.esite.dto.Feature153Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show153Dto;
import com.huiyee.esite.fdao.IHd153Dao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.BlockContext;
import com.huiyee.esite.model.HdCard;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageRelation;
import com.huiyee.interact.exam.model.ExamModel;
import com.huiyee.interact.exam.model.ExamQuestionModel;

public class Feature153ManagerImpl extends AbstractFeatureManager
{

	private IHd153Dao hd153Dao;
	private ISiteDao siteDao;
	private ITemplateDao templateDao;

	
	public void setTemplateDao(ITemplateDao templateDao)
	{
		this.templateDao = templateDao;
	}

	public void setSiteDao(ISiteDao siteDao)
	{
		this.siteDao = siteDao;
	}

	public void setHd153Dao(IHd153Dao hd153Dao)
	{
		this.hd153Dao = hd153Dao;
	}

	@Override
	public long add(long pageid, long featureid, String featurename)
	{
		long fid = hd153Dao.saveFeatureInteractExam(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid, featurename, "N");
	}

	@Override
	public IDto config(long fid, Account account)
	{
		Feature153Dto dto = new Feature153Dto();
		List<ExamModel> researchList = hd153Dao.findInteractExamByOwner(account.getOwner().getId());
		dto.setExamid(hd153Dao.findExamidByFid(fid).getId());
		dto.setExamList(researchList);
		return dto;
	}

	@Override
	public String configSub(long featureid, IDto dto, Account account)
	{
		String result = "N";
		Feature153Dto d = (Feature153Dto) dto;
		List<ExamModel> research = hd153Dao.findInteractExamByOwner(account.getOwner().getId());
		d.setExamList(research);
		int res = hd153Dao.updateFeatureIneractExam(d.getExamid(), d.getFid());
		if (res == 1)
		{
			result = "Y";
		}
		return result;
	}

	@Override
	public IDto config(long fid)
	{
		Show153Dto d = new Show153Dto();
		ExamModel rm = hd153Dao.findFeatureInteractExamById(fid);
		List<ExamQuestionModel> list = hd153Dao.findQuestionsByExamid(rm.getId());
		for (ExamQuestionModel q : list)
		{
			q.setOptions(hd153Dao.findOptionsByQuestionid(q.getId()));
		}
		rm.setQuestions(list);
		d.setExamModel(rm);
		return d;
	}

	@Override
	public IDto configNew(long fid, Account account, long relationid)
	{
		long pageid = hd153Dao.findExamidByFid(fid).getPageid();
		long siteid = siteDao.findSiteidByPageid(pageid);
		List<Page> pageList = siteDao.findPageListBySiteId(siteid);
		PageBlockRelation pbr = hd153Dao.findPageBlockRelationByRelationid(relationid);
		String str = pbr.getJson();
		JSONObject jo = JSONObject.fromObject(str);
		String hd = jo.get("obj").toString();
		String type = "N";
		String url = null;
		String urlShow = null;
		String urlPre = null;
		long toPageid = 0;
		try
		{
			jo = JSONObject.fromObject(hd);
			hd = jo.get("redirect").toString();
			jo = JSONObject.fromObject(hd);
			type = jo.getString("type");
			url = jo.getString("url");
			urlShow = jo.getString("urlShow");
			urlPre = jo.getString("urlPre");
			toPageid = jo.getLong("toPageid");
		} catch (Exception e)
		{
			// e.printStackTrace();
		}
		Feature153Dto dto = (Feature153Dto) config(fid, account);
		dto.setType(type);
		dto.setUrl(url);
		dto.setUrlPre(urlPre);
		dto.setUrlShow(urlShow);
		dto.setPageList(pageList);
		dto.setToPageid(toPageid);
		return dto;
	}

	@Override
	public String configSubNew(long featureid, IDto dto, Account account)
	{
		Feature153Dto d = (Feature153Dto) dto;
		configSub(featureid, dto, account);
		HdCard hc = new HdCard();
		hc.setFid(d.getFid());
		hc.setId(d.getExamid());
		hc.setFeatureid(featureid);
		hc.setRedirect(d.getRedirect());
		
		/**
		 * 获取详情页的pageid
		 */
		long relationid=d.getRelationid();
		
		List<BlockContext> list = templateDao.findesBlockContext(relationid);
		long contextid = 0;
		if (list != null && list.size() > 0) {
			contextid = list.get(0).getId();
		}
		Page page = templateDao.findPageByRelationidAndContextid(relationid, contextid);
		hc.setPageid(page.getId());
		
		Gson gson = new Gson();
		String str1 = gson.toJson(hc);
		PageBlockRelation pbr = hd153Dao.findPageBlockRelationByRelationid(d.getRelationid());
		String str = pbr.getJson();
		JSONObject jo = JSONObject.fromObject(str);
		jo.element("obj", str1);
		hd153Dao.updatePageBlockRelationByRelationid(d.getRelationid(), jo.toString());
		return "Y";
	}
}
