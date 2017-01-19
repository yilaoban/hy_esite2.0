package com.huiyee.interact.exam.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.exam.dto.ExamQuestionDto;
import com.huiyee.interact.exam.mgr.IExamQuestionMgr;
import com.huiyee.interact.exam.model.Pager;
import com.huiyee.interact.exam.model.ExamQuestionListModel;
import com.huiyee.interact.exam.model.ExamQuestionModel;
import com.huiyee.interact.exam.model.ExamQuestionOptionModel;
import com.huiyee.interact.exam.model.ExamQuestionOptionVO;

public class ExamQuestionAction extends InteractModelAction
{

	private static final long serialVersionUID = 2367157772097376717L;
	private IExamQuestionMgr examQuestionMgr;
	private int pageId=1;
	private ExamQuestionDto dto;
	private long searchid;
	private long id;
	private long questionid;
	private long resultid;
	private long optionsid;
	private String result;
	private String type;
	private ExamQuestionModel rqmodel;
	private ExamQuestionListModel rqlmodel;
	private ExamQuestionOptionModel rqomodel;
	private long mid = 10006;
	private long target;
	private int idx;
	
    
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public ExamQuestionListModel getRqlmodel()
	{
		return rqlmodel;
	}

	public void setRqlmodel(ExamQuestionListModel rqlmodel)
	{
		this.rqlmodel = rqlmodel;
	}

	public long getResultid()
	{
		return resultid;
	}

	public void setResultid(long resultid)
	{
		this.resultid = resultid;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public IExamQuestionMgr getExamQuestionMgr()
	{
		return examQuestionMgr;
	}

	public void setExamQuestionMgr(IExamQuestionMgr examQuestionMgr)
	{
		this.examQuestionMgr = examQuestionMgr;
	}

	public ExamQuestionDto getDto()
	{
		return dto;
	}

	public void setDto(ExamQuestionDto dto)
	{
		this.dto = dto;
	}

	public String execute()throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		int start=(pageId - 1) * IInteractConstants.VOTE_LIMIT;
		int total=examQuestionMgr.findExamQuestionTotal(searchid);
		dto=new ExamQuestionDto();
		dto.setList(examQuestionMgr.findExamQuestionList(searchid,start,IInteractConstants.VOTE_LIMIT));
		Pager pager=new Pager(pageId, total, IInteractConstants.VOTE_LIMIT);
		dto.setPager(pager);
		return SUCCESS;
	}
	
	public String delExamQuestion()throws Exception{
		int len=examQuestionMgr.delExamQuestion(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(len);
		out.flush();
		out.close();
		return null;
	}
	
	public String updateExamQuestion()throws Exception{
		resultid=examQuestionMgr.updateExamQuestion(id, rqlmodel, questionid, rqomodel);
		if(resultid>0){
			result="Y";
		}
		return SUCCESS;
	}
	
	public String saveExamQuestion()throws Exception{
		resultid=examQuestionMgr.saveExamQuestion(rqlmodel, rqomodel);
		if(resultid>0){
			result="Y";
		}
		return SUCCESS;
	}

	public String findOneExamQuestion()throws Exception{
		dto=new ExamQuestionDto();
		dto.setList(examQuestionMgr.findOneExamQuestion(id));
		rqmodel=examQuestionMgr.findTypeIds(id);
		return SUCCESS;
	}
	
//	public String fileUpload()throws Exception{
//		int i=examQuestionMgr.saveExamQuestion(rqlmodel, rqomodel);
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("text/html;charset=utf-8");
//		response.setHeader("Cache-Control", "no-cache");
//		PrintWriter out = response.getWriter();
//		out.write(i);
//		out.flush();
//		out.close();
//		return null;
//	}
	
	public String upExamQuestion()throws Exception{
		int result = examQuestionMgr.updateUpExamQuestion(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	public String downExamQuestion()throws Exception{
		int result = examQuestionMgr.updateDownExamQuestion(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	public String deleteExamOptions()throws Exception{
		int result=examQuestionMgr.deleteExamOptions(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	public String createNext()throws Exception{
		dto=new ExamQuestionDto();
		dto.setRqo(examQuestionMgr.findoptionbyqid(questionid));
		dto.setList(examQuestionMgr.findquestionbyqid(questionid,searchid,idx));
		return SUCCESS;
	}
	
	public String inTarget()throws Exception{
		int result=examQuestionMgr.updateinTarget(id,target);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	public String selisTar()throws Exception{
		ExamQuestionOptionVO vo=examQuestionMgr.selisTar(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		if(vo == null){
			out.print(0);
		}else{
			out.print(vo.getTitle());
		}
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

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public ExamQuestionModel getRqmodel()
	{
		return rqmodel;
	}

	public void setRqmodel(ExamQuestionModel rqmodel)
	{
		this.rqmodel = rqmodel;
	}

	public long getQuestionid()
	{
		return questionid;
	}

	public void setQuestionid(long questionid)
	{
		this.questionid = questionid;
	}

	public ExamQuestionOptionModel getRqomodel()
	{
		return rqomodel;
	}

	public void setRqomodel(ExamQuestionOptionModel rqomodel)
	{
		this.rqomodel = rqomodel;
	}

	public long getOptionsid()
	{
		return optionsid;
	}

	public void setOptionsid(long optionsid)
	{
		this.optionsid = optionsid;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public long getTarget()
	{
		return target;
	}

	public void setTarget(long target)
	{
		this.target = target;
	}

	
	
	
}
