package com.huiyee.interact.exam.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.exam.dto.ExamRecordDto;
import com.huiyee.interact.exam.dto.ExamOptionDto;
import com.huiyee.interact.exam.dto.ExamQuestionDataDto;
import com.huiyee.interact.exam.mgr.IExamManager;
import com.huiyee.interact.exam.mgr.IExamQuestionMgr;
import com.opensymphony.xwork2.ActionContext;

public class ExamRecordDataAction extends InteractModelAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1211680738140330967L;
	private IExamManager examManager;
	private IExamQuestionMgr examQuestionMgr;
	private ExamRecordDto dto;
	private ExamOptionDto optiondto;
	private int pageId = 1;
	private long searchid;
	private int mtype = -1;
	private String nickname;
	private List<ExamQuestionDataDto> list;
	private String data;
	private String ordlists;
	private long mid = 10006;
	private String source1;

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
		dto = (ExamRecordDto) examManager.findExamRecordList(searchid, mtype, nickname, source1, pageId,this.getOwner());
		return SUCCESS;
	}

	public String getExamData()
	{
		list = examQuestionMgr.findExamData(searchid);
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
			rs = examManager.updateExamClean(searchid,account.getOwner().getId());
		}
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}

	public long getSearchid()
	{
		return searchid;
	}

	public void setSearchid(long searchid)
	{
		this.searchid = searchid;
	}

	public ExamRecordDto getDto()
	{
		return dto;
	}

	public void setExamManager(IExamManager examManager)
	{
		this.examManager = examManager;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public void setExamQuestionMgr(IExamQuestionMgr examQuestionMgr)
	{
		this.examQuestionMgr = examQuestionMgr;
	}

	public List<ExamQuestionDataDto> getList()
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

	public ExamOptionDto getOptiondto()
	{
		return optiondto;
	}

	public void setOptiondto(ExamOptionDto optiondto)
	{
		this.optiondto = optiondto;
	}
}
