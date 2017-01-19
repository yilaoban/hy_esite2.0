package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.common.SolrDocument;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.dto.GroupDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.mgr.IMoveToGroupMgr;
import com.huiyee.esite.mgr.IWeiXinMgr;
import com.huiyee.esite.mgr.IWxUserMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.WxGroup;
import com.huiyee.esite.model.WxUser;
import com.huiyee.weixin.model.WxMp;

public class GroupAction extends AbstractAction {

	private static final long serialVersionUID = -7048643342731449412L;
	private GroupDto dto;
	private int pageId = 1;
	private IWxUserMgr wxUserMgr;
	private IWeiXinMgr weiXinMgr;
	private long mpid;
	private IMoveToGroupMgr wxGroupMgr;
	private List<WxGroup> wxGroupList;

	public String user_log() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		if (account == null || dto == null) {
			return null;
		}
		long ownerid = account.getOwner().getId();
		dto.setOwner(ownerid);

		int rows = 10;
		int start = (pageId - 1) * rows;
		GroupCommand gc = null; 
//		gc = hylogSolr.groupByMid(dto, start, rows);
		if (gc != null) {
			List<SolrDocument> docs = new ArrayList<SolrDocument>();
			int total = gc.getNGroups();
			List<Group> groups = gc.getValues();
			for (Group group : groups) {
				SolrDocument doc = group.getResult().get(0);
				String openid = (String) doc.getFieldValue("mid");
				String nickname = "ÄäÃû";
				String sex = "Î´Öª";
				WxUser user = wxUserMgr.findWxUserByOpenid(openid);
				if (user != null) {
					if (user.getNickname() != null) {
						nickname = user.getNickname();
					} else {
						nickname = nickname + "_" + user.getId();
					}
					if (user.getSex() == 1) {
						sex = "ÄÐ";
					} else if (user.getSex() == 2) {
						sex = "Å®";
					}
				}
				doc.setField("nickname", nickname);
				doc.setField("sex", sex);
				docs.add(doc);
			}
			dto.setDocs(docs);
			dto.setPager(new Pager(pageId, total, rows));
		}
		return SUCCESS;
	}

	public String move_log() throws Exception {
		if (dto == null) {
			return null;
		}
		Map<String, Integer> gzMap = new HashMap<String, Integer>();
		Map<String, Integer> areaMap = new HashMap<String, Integer>();
		int rows = 10;
		int start = 0;
		int size = 1;
		while (size > 0) {
			GroupCommand gc = null; 
//			gc = hylogSolr.groupByMid(dto, start, rows);
			if (gc == null) {
				break;
			}
			List<Group> groups = gc.getValues();
			for (Group group : groups) {
				SolrDocument doc = group.getResult().get(0);
				String gz = "·Ç·ÛË¿";
				if (doc.containsKey("gz_i") && (Integer) doc.getFieldValue("gz_i") == 1) {
					gz = "·ÛË¿";
				}
				String area = (String) doc.getFieldValue("area");

				addToMap(gzMap, gz);
				addToMap(areaMap, area);
			}
			start += rows;
			size = groups.size();
		}
		List<List<Object>> fansList = new ArrayList<List<Object>>();
		addToList(fansList, gzMap);
		dto.setFansList(fansList);

		List<List<Object>> areaList = new ArrayList<List<Object>>();
		addToList(areaList, areaMap);
		dto.setAreaList(areaList);

		WxMp woa = weiXinMgr.updateFindWxMp(dto.getOwner(), 0);
		if (woa != null) {
			mpid = woa.getId();
			wxGroupList = wxGroupMgr.findWxGroupList(mpid);
		}
		return SUCCESS;
	}

	public String job() throws Exception {
		if (dto == null || mpid == 0) {
			return null;
		}
		if (dto.getGroupid() == 0) {
			long groupid = wxGroupMgr.saveCrmWxGroup(mpid, dto.getGroupname());
			dto.setGroupid(groupid);
		}
		String jobrule = new Gson().toJson(dto);
		int res = wxGroupMgr.saveWxGroupJob(mpid, dto.getGroupname(), jobrule, "MCR");

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(res);
		out.flush();
		out.close();
		return null;
	}

	private void addToMap(Map<String, Integer> map, String key) {
		Integer value = map.get(key);
		if (value == null) {
			value = 1;
		} else {
			value++;
		}
		map.put(key, value);
	}

	private void addToList(List<List<Object>> list, Map<String, Integer> map) {
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();

			List<Object> obj = new ArrayList<Object>();
			obj.add("'" + key + "'");
			obj.add(value);
			list.add(obj);
		}
	}

	
	public void setWeiXinMgr(IWeiXinMgr weiXinMgr)
	{
		this.weiXinMgr = weiXinMgr;
	}

	
	public void setWxGroupMgr(IMoveToGroupMgr wxGroupMgr)
	{
		this.wxGroupMgr = wxGroupMgr;
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

	public GroupDto getDto() {
		return dto;
	}

	public void setDto(GroupDto dto) {
		this.dto = dto;
	}

	public List<WxGroup> getWxGroupList() {
		return wxGroupList;
	}

	public void setWxGroupList(List<WxGroup> wxGroupList) {
		this.wxGroupList = wxGroupList;
	}

	
	public long getMpid()
	{
		return mpid;
	}

	
	public void setMpid(long mpid)
	{
		this.mpid = mpid;
	}

}
