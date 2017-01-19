package com.huiyee.interact.template.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.mgr.IWeiXinMgr;
import com.huiyee.esite.mgr.IWxUserMgr;
import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.template.dto.WxTemplateDto;
import com.huiyee.interact.template.mgr.IWxTemplateIndustryMgr;
import com.huiyee.interact.template.mgr.IWxTemplateJobMgr;
import com.huiyee.interact.template.mgr.IWxTemplateMgr;
import com.huiyee.interact.template.mgr.IWxTemplateStoreMgr;
import com.huiyee.interact.template.model.WxTemplate;
import com.huiyee.interact.template.model.WxTemplateIndustry;
import com.huiyee.interact.template.model.WxTemplateJob;
import com.huiyee.interact.template.model.WxTemplateStore;
import com.huiyee.interact.template.service.IWeixinTemplateService;
import com.huiyee.weixin.model.WxMp;
import com.huiyee.weixin.model.message.ErrMsg;

import edu.emory.mathcs.backport.java.util.Arrays;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WxTemplateAction extends AbstractAction {

	private static final long serialVersionUID = 2630332050152558513L;
	private IWeiXinMgr weiXinMgr;
	private IWeixinTemplateService weixinTemplateService;
	private IWxTemplateMgr wxTemplateMgr;
	private IWxTemplateIndustryMgr wxTemplateIndustryMgr;
	private IWxTemplateStoreMgr wxTemplateStoreMgr;
	private IWxTemplateJobMgr wxTemplateJobMgr;
	private IWxUserMgr wxUserMgr;

	private int pageId = 1;
	private WxTemplateDto dto;
	private WxTemplate template;
	private WxTemplateStore store;

	@Override
	public String execute() throws Exception {
		int rows = 10;
		int start = (pageId - 1) * rows;
		int total = wxTemplateMgr.getTemplateCount(this.getOwner());
		List<WxTemplate> list = wxTemplateMgr.getTemplateList(this.getOwner(), start, rows);
		for (WxTemplate wt : list) {
			wt.setStore(wxTemplateStoreMgr.getStore(wt.getStore_id()));
		}
		dto = new WxTemplateDto();
		dto.setList(list);
		dto.setPager(new Pager(pageId, total, rows));

		// get industry
		WxMp mp = weiXinMgr.updateFindWxMp(this.getOwner(), 0);
		if (mp != null) {
			String access_token = mp.getAu_access_token();
			if (mp.getAppsecret() != null) {
				access_token = mp.getAccess_token();
			}
			JSONObject json = weixinTemplateService.getIndustry(access_token);
			if (json != null && !json.containsKey("errcode")) {
				StringBuffer sb = new StringBuffer();
				JSONObject primary_industry = json.getJSONObject("primary_industry");
				sb.append(primary_industry.getString("first_class")).append("-");
				sb.append(primary_industry.getString("second_class")).append(",");
				JSONObject secondary_industry = json.getJSONObject("secondary_industry");
				sb.append(secondary_industry.getString("first_class")).append("-");
				sb.append(secondary_industry.getString("second_class"));
				dto.setIndustry(sb.toString());
			}
		}
		this.setLightType(1);
		return SUCCESS;
	}

	public String syncTemplate() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			WxMp mp = weiXinMgr.updateFindWxMp(this.getOwner(), 0);
			if (mp == null) {
				return null;
			}
			String access_token = mp.getAu_access_token();
			if (mp.getAppsecret() != null) {
				access_token = mp.getAccess_token();
			}
			JSONObject json = weixinTemplateService.getTemplate(access_token);
			if (json == null || json.containsKey("errcode")) {
				out.print(json);
				return null;
			}

			long mp_id = mp.getId();
			List<WxTemplate> list = new ArrayList<WxTemplate>();
			JSONArray template_list = json.getJSONArray("template_list");
			for (int i = 0; i < template_list.size(); i++) {
				JSONObject template = template_list.getJSONObject(i);

				WxTemplate tpl = new WxTemplate();
				tpl.setMpid(mp.getId());
				tpl.setTemplate_id(template.getString("template_id"));
				// tpl.setFirst_class(template.getString("primary_industry"));
				// tpl.setSecond_class(template.getString("deputy_industry"));
				// tpl.setContent(template.getString("content"));
				// tpl.setExample(template.getString("example").replaceAll("\\n",
				// "<br/>"));
				list.add(tpl);
			}

			wxTemplateMgr.delAllTemplate(mp_id);
			wxTemplateMgr.addTemplate(list);

			json.put("errcode", 0);
			json.put("errmsg", "ok");
			out.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String editTemplate() throws Exception {
		if (template == null) {
			return null;
		}

		String type = template.getType();
		long entityid = template.getEntityid();
		String remark = template.getRemark();
		long store_id = template.getStore_id();
		boolean showRemark = template.isShowRemark();
		boolean showUrl = template.isShowUrl();
		boolean showSend = template.isShowSend();
		if (template.getId() > 0) {
			template = wxTemplateMgr.getTemplate(template.getId());
			template.setType(type);
			template.setRemark(remark);
			template.setEntityid(entityid);
			template.setStore_id(store_id);
			template.setShowRemark(showRemark);
			template.setShowUrl(showUrl);
			template.setShowSend(showSend);
		}
		WxTemplateStore wts = wxTemplateStoreMgr.getStore(store_id);
		template.setStore(wts);

		return SUCCESS;
	}

	public String saveTemplate() throws Exception {
		if (template == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			long store_id = template.getStore_id();
			// check if support this template
			WxTemplateStore wts = wxTemplateStoreMgr.getStore(store_id);
			if (wts == null) {
				out.print("{\"errcode\":-2,\"errmsg\":\"暂不支持该模板\"}");
				return null;
			}

			WxMp mp = weiXinMgr.updateFindWxMp(this.getOwner(), 0);
			if (mp == null) {
				return null;
			}
			long mpid = mp.getId();
			template.setOwner(this.getOwner());
			template.setMpid(mpid);

			// check if added same kind of template
			WxTemplate wt = wxTemplateMgr.getTemplate(mpid, store_id);
			if (wt != null) {
				template.setTemplate_id(wt.getTemplate_id());
			} else {
				String access_token = mp.getAu_access_token();
				if (mp.getAppsecret() != null) {
					access_token = mp.getAccess_token();
				}
				ErrMsg errmsg = weixinTemplateService.addTemplate(access_token, wts.getCode());
				if (errmsg == null || errmsg.getErrcode() != 0) {
					out.print(new Gson().toJson(errmsg));
					return null;
				}
				template.setTemplate_id(errmsg.getTemplate_id());
			}

			// update
			if (template.getId() > 0) {
				int res = wxTemplateMgr.updateTemplate(template);
				if (res == 0) {
					out.print("{\"errcode\":-1,\"errmsg\":\"DB save failed-updateTemplate\"}");
				} else {
					out.print("{\"errcode\":0,\"errmsg\":\"ok\"}");
				}
			}
			// add
			else {
				int res = wxTemplateMgr.addTemplate(template);
				if (res == 0) {
					out.print("{\"errcode\":-1,\"errmsg\":\"DB save failed-addTemplate\"}");
				} else {
					out.print("{\"errcode\":0,\"errmsg\":\"ok\"}");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;

	}

	public String delTemplate() throws Exception {
		if (template == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			WxMp mp = weiXinMgr.updateFindWxMp(this.getOwner(), 0);
			if (mp == null) {
				return null;
			}
			int count = wxTemplateMgr.getTemplateCount(mp.getId(), template.getTemplate_id());
			if (count == 1) {
				String access_token = mp.getAu_access_token();
				if (mp.getAppsecret() != null) {
					access_token = mp.getAccess_token();
				}
				ErrMsg errmsg = weixinTemplateService.delTemplate(access_token, template.getTemplate_id());
				if (errmsg != null && errmsg.getErrcode() == 0) {
					int res = wxTemplateMgr.delTemplate(template.getId());
					if (res == 0) {
						errmsg.setErrcode(-1);
						errmsg.setErrmsg("DB save failed");
					}
				}
				out.print(new Gson().toJson(errmsg));
			} else {
				int res = wxTemplateMgr.delTemplate(template.getId());
				if (res == 0) {
					out.print("{\"errcode\":-1,\"errmsg\":\"DB save failed\"}");
				} else {
					out.print("{\"errcode\":0,\"errmsg\":\"ok\"}");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String store() throws Exception {
		int rows = 10;
		int start = (pageId - 1) * rows;
		int total = wxTemplateStoreMgr.getStoreCount();
		List<WxTemplateStore> list = wxTemplateStoreMgr.getStoreList(start, rows);

		dto = new WxTemplateDto();
		dto.setStore_list(list);
		dto.setPager(new Pager(pageId, total, rows));

		// get industry
		WxMp mp = weiXinMgr.updateFindWxMp(this.getOwner(), 0);
		if (mp == null) {
			return null;
		}
		String access_token = mp.getAu_access_token();
		if (mp.getAppsecret() != null) {
			access_token = mp.getAccess_token();
		}
		JSONObject json = weixinTemplateService.getIndustry(access_token);
		if (json != null && !json.containsKey("errcode")) {
			StringBuffer sb = new StringBuffer();
			JSONObject primary_industry = json.getJSONObject("primary_industry");
			sb.append(primary_industry.getString("first_class")).append("-");
			sb.append(primary_industry.getString("second_class")).append(",");
			JSONObject secondary_industry = json.getJSONObject("secondary_industry");
			sb.append(secondary_industry.getString("first_class")).append("-");
			sb.append(secondary_industry.getString("second_class"));
			dto.setIndustry(sb.toString());
		}

		this.setLightType(2);
		return SUCCESS;
	}

	public String industry() throws Exception {
		if (dto == null) {
			return null;
		}

		// 默认行业设置
		String primary_first_class = "IT科技";
		String primary_second_class = "互联网/电子商务";
		String secondary_first_class = "IT科技";
		String secondary_second_class = "IT软件与服务";

		String[] industry = dto.getIndustry().split(",");
		if (industry.length == 2) {
			String[] primary = industry[0].split("-");
			primary_first_class = primary[0];
			primary_second_class = primary[1];
			String[] secondary = industry[1].split("-");
			secondary_first_class = secondary[0];
			secondary_second_class = secondary[1];
		}

		// 第一行业选中
		List<WxTemplateIndustry> primary_first_list = new ArrayList<WxTemplateIndustry>();
		List<WxTemplateIndustry> secondary_first_list = new ArrayList<WxTemplateIndustry>();
		List<WxTemplateIndustry> first_list = wxTemplateIndustryMgr.getFirst_class();
		for (WxTemplateIndustry wi : first_list) {
			WxTemplateIndustry primary_first_clone = wi.clone();
			if (primary_first_clone.getFirst_class().equals(primary_first_class)) {
				primary_first_clone.setFirst_selected(true);
			}
			primary_first_list.add(primary_first_clone);

			WxTemplateIndustry secondary_first_clone = wi.clone();
			if (secondary_first_clone.getFirst_class().equals(secondary_first_class)) {
				secondary_first_clone.setFirst_selected(true);
			}
			secondary_first_list.add(wi.clone());
		}
		// 第二行业选中
		List<WxTemplateIndustry> primary_second_list = wxTemplateIndustryMgr.getSecond_class(primary_first_class);
		for (WxTemplateIndustry wi : primary_second_list) {
			if (wi.getSecond_class().equals(primary_second_class)) {
				wi.setSecond_selected(true);
				break;
			}
		}
		List<WxTemplateIndustry> secondary_second_list = wxTemplateIndustryMgr.getSecond_class(secondary_first_class);
		for (WxTemplateIndustry wi : secondary_second_list) {
			if (wi.getSecond_class().equals(secondary_second_class)) {
				wi.setSecond_selected(true);
				break;
			}
		}

		dto = new WxTemplateDto();
		dto.setPrimary_first_list(primary_first_list);
		dto.setPrimary_second_list(primary_second_list);
		dto.setSecondary_first_list(secondary_first_list);
		dto.setSecondary_second_list(secondary_second_list);
		return SUCCESS;
	}

	public String saveIndustry() throws Exception {
		if (dto == null || dto.getIndustry_id1() == 0 || dto.getIndustry_id2() == 0) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			WxMp mp = weiXinMgr.updateFindWxMp(this.getOwner(), 0);
			if (mp == null) {
				return null;
			}
			String access_token = mp.getAu_access_token();
			if (mp.getAppsecret() != null) {
				access_token = mp.getAccess_token();
			}
			ErrMsg errMsg = weixinTemplateService.setIndustry(access_token, dto.getIndustry_id1(), dto.getIndustry_id2());
			if (errMsg != null && errMsg.getErrcode() == 0) {
				// 清空所有模板
				wxTemplateMgr.delAllTemplate(this.getOwner());
			}
			out.print(new Gson().toJson(errMsg));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String industrySecond() throws Exception {
		if (dto == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			List<WxTemplateIndustry> list = wxTemplateIndustryMgr.getSecond_class(dto.getIndustry());
			out.print(new Gson().toJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String record() throws Exception {
		WxMp mp = weiXinMgr.updateFindWxMp(this.getOwner(), 0);
		if (mp == null || template == null) {
			return null;
		}

		long mpid = mp.getId();
		int rows = 10;
		int start = (pageId - 1) * rows;
		int total = 0;
		List<WxTemplateJob> list = new ArrayList<WxTemplateJob>();

		long entityid = template.getEntityid();
		String type = template.getType();
		if (type != null && type.contains("-")) {
			@SuppressWarnings("unchecked")
			List<String> types = Arrays.asList(type.split("-"));
			total = wxTemplateJobMgr.getJobCount(mpid, types, entityid);
			list = wxTemplateJobMgr.getJobList(mpid, types, entityid, start, rows);
		} else {
			total = wxTemplateJobMgr.getJobCount(mpid, type, entityid);
			list = wxTemplateJobMgr.getJobList(mpid, type, entityid, start, rows);
		}

		for (WxTemplateJob wtj : list) {
			WxUser user = wxUserMgr.findWxUserByOpenid(wtj.getTouser());
			wtj.setUser(user);
		}
		dto = new WxTemplateDto();
		dto.setJob_list(list);
		dto.setPager(new Pager(pageId, total, rows));

		return SUCCESS;
	}

	public void setWeiXinMgr(IWeiXinMgr weiXinMgr) {
		this.weiXinMgr = weiXinMgr;
	}

	public void setWeixinTemplateService(IWeixinTemplateService weixinTemplateService) {
		this.weixinTemplateService = weixinTemplateService;
	}

	public void setWxTemplateMgr(IWxTemplateMgr wxTemplateMgr) {
		this.wxTemplateMgr = wxTemplateMgr;
	}

	public void setWxTemplateIndustryMgr(IWxTemplateIndustryMgr wxTemplateIndustryMgr) {
		this.wxTemplateIndustryMgr = wxTemplateIndustryMgr;
	}

	public void setWxTemplateStoreMgr(IWxTemplateStoreMgr wxTemplateStoreMgr) {
		this.wxTemplateStoreMgr = wxTemplateStoreMgr;
	}

	public void setWxTemplateJobMgr(IWxTemplateJobMgr wxTemplateJobMgr) {
		this.wxTemplateJobMgr = wxTemplateJobMgr;
	}

	public void setWxUserMgr(IWxUserMgr wxUserMgr) {
		this.wxUserMgr = wxUserMgr;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public WxTemplateDto getDto() {
		return dto;
	}

	public void setDto(WxTemplateDto dto) {
		this.dto = dto;
	}

	public WxTemplate getTemplate() {
		return template;
	}

	public void setTemplate(WxTemplate template) {
		this.template = template;
	}

	public WxTemplateStore getStore() {
		return store;
	}

	public void setStore(WxTemplateStore store) {
		this.store = store;
	}

}
