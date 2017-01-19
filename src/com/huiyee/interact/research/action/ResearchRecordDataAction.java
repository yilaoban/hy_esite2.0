package com.huiyee.interact.research.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.solr.HylogSolrServer;
import com.huiyee.interact.research.dto.ResearceRecordDto;
import com.huiyee.interact.research.dto.ResearchOptionDto;
import com.huiyee.interact.research.dto.ResearchQuestionDataDto;
import com.huiyee.interact.research.mgr.IResearchManager;
import com.huiyee.interact.research.mgr.IResearchQuestionMgr;
import com.opensymphony.xwork2.ActionContext;

public class ResearchRecordDataAction extends InteractModelAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1211680738140330967L;
	private IResearchManager researchManager;
	private IResearchQuestionMgr researchQuestionMgr;
	private ResearceRecordDto dto;
	private ResearchOptionDto optiondto;
	private int pageId = 1;
	private long searchid;
	private int mtype = -1;
	private String nickname;
	private List<ResearchQuestionDataDto> list;
	private String data;
	private String ordlists;
	private long mid = 10006;
	private String source1;
	private HylogSolrServer hylogSolrServer;
	private int gz_i;//1·ÛË¿  0 ·Ç·ÛË¿ -1È«²¿
	private List<List<Object>> areaList;
	
	public String getSource1()
	{
		return source1;
	}

	public void setSource1(String source1)
	{
		this.source1 = source1;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public String execute() throws Exception
	{
		/*if (mtype == -1)
		{
			mtype = 0;
		}*/
		dto = (ResearceRecordDto) researchManager.findResearchRecordList(searchid, mtype, nickname, source1, pageId,this.getOwner());
		return SUCCESS;
	}

	public String getResearchData()
	{
		list = researchQuestionMgr.findResearchData(searchid);
		Gson gson = new Gson();
		data = gson.toJson(list);
		mtype = 3;
		return SUCCESS;
	}
	
	public String dateClean() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int rs = 0;
		if (account != null && searchid > 0)
		{
			rs = researchManager.updateResearchClean(searchid,account.getOwner().getId());
		}
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}
	
	public String researchMoveToGroup() throws Exception{
		areaList = hylogSolrServer.findHudongUserArea("wx", "h","d", searchid, gz_i);
		return SUCCESS;
	}

	public long getSearchid()
	{
		return searchid;
	}

	public void setSearchid(long searchid)
	{
		this.searchid = searchid;
	}

	public ResearceRecordDto getDto()
	{
		return dto;
	}

	public void setResearchManager(IResearchManager researchManager)
	{
		this.researchManager = researchManager;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public void setResearchQuestionMgr(IResearchQuestionMgr researchQuestionMgr)
	{
		this.researchQuestionMgr = researchQuestionMgr;
	}

	public List<ResearchQuestionDataDto> getList()
	{
		return list;
	}

	public String getData()
	{
		return data;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public int getMtype()
	{
		return mtype;
	}

	public void setMtype(int mtype)
	{
		this.mtype = mtype;
	}

	public String getOrdlists()
	{
		return ordlists;
	}

	public void setOrdlists(String ordlists)
	{
		this.ordlists = ordlists;
	}

	public ResearchOptionDto getOptiondto()
	{
		return optiondto;
	}

	public void setOptiondto(ResearchOptionDto optiondto)
	{
		this.optiondto = optiondto;
	}
	
	public int getGz_i()
	{
		return gz_i;
	}

	
	public void setGz_i(int gz_i)
	{
		this.gz_i = gz_i;
	}

	
	public List<List<Object>> getAreaList()
	{
		return areaList;
	}

	
	public void setAreaList(List<List<Object>> areaList)
	{
		this.areaList = areaList;
	}

	
	public void setHylogSolrServer(HylogSolrServer hylogSolrServer)
	{
		this.hylogSolrServer = hylogSolrServer;
	}
	
}
