
package com.huiyee.interact.exam.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.exam.dto.ExamResultDto;
import com.huiyee.interact.exam.mgr.IExamManager;
import com.huiyee.interact.exam.model.ExamResult;
import com.opensymphony.xwork2.ActionContext;

/**
 * 评测结果
 * 
 * @author ldw
 * 
 */
public class ExamResultAction extends InteractModelAction
{

	private ExamResult examResult;
	private ExamResultDto dto;
	private IExamManager examManager;
	private long examid;
	private long resultid;

	@Override
	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (ExamResultDto) examManager.findExamResultList(examid, account);
		return SUCCESS;
	}

	public String resultSub() throws Exception
	{

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String rs = "N";
		if (examResult.getStart() == 0 && examResult.getEnd() == 0)
		{
			rs = "上限与下限不能同时为空!";
		} else if (examResult.getStart() > examResult.getEnd())
		{
			rs = "得分下限必须小于等于上限!!";
		} else
		{
			long r = examManager.addExamResult(examResult, account);
			if (r > 0)
			{
				rs = "Y";
			}
		}
		out.print(new Gson().toJson(rs));
		out.flush();
		out.close();
		return null;
	}

	public String findExamResult() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		ExamResult rs = examManager.findExamResult(resultid, account.getOwner().getId());
		out.print(new Gson().toJson(rs));
		out.flush();
		out.close();
		return null;

	}

	public String delResult() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String rs = "N";
		int r = examManager.delExamResult(resultid, account);
		if (r > 0)
		{
			rs = "Y";
		}
		out.print(new Gson().toJson(rs));
		out.flush();
		out.close();
		return null;
	}

	public String updateResult() throws Exception
	{

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String rs = "N";
		if (examResult.getStart() == 0 && examResult.getEnd() == 0)
		{
			rs = "上限与下限不能同时为空!";
		} else if (examResult.getStart() > examResult.getEnd())
		{
			rs = "得分下限必须小于等于上限!!";
		} else
		{
			int r = examManager.updateExamResult(examResult, account);
			if (r > 0)
			{
				rs = "Y";
			}
		}
		out.print(new Gson().toJson(rs));
		out.flush();
		out.close();
		return null;
	}

	public ExamResult getExamResult()
	{
		return examResult;
	}

	public void setExamResult(ExamResult examResult)
	{
		this.examResult = examResult;
	}

	public ExamResultDto getDto()
	{
		return dto;
	}

	public void setDto(ExamResultDto dto)
	{
		this.dto = dto;
	}

	public long getExamid()
	{
		return examid;
	}

	public void setExamid(long examid)
	{
		this.examid = examid;
	}

	public void setExamManager(IExamManager examManager)
	{
		this.examManager = examManager;
	}

	public long getResultid()
	{
		return resultid;
	}

	public void setResultid(long resultid)
	{
		this.resultid = resultid;
	}
}
