package com.huiyee.interact.research.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.research.dto.ResearchQuestionDto;
import com.huiyee.interact.research.mgr.IResearchQuestionMgr;
import com.huiyee.interact.research.model.Pager;
import com.huiyee.interact.research.model.ResearchQuestionListModel;
import com.huiyee.interact.research.model.ResearchQuestionModel;
import com.huiyee.interact.research.model.ResearchQuestionOptionModel;
import com.huiyee.interact.research.model.ResearchQuestionOptionVO;

public class ResearchQuestionAction extends InteractModelAction
{

	private static final long serialVersionUID = 2367157772097376717L;
	private IResearchQuestionMgr researchQuestionMgr;
	private int pageId=1;
	private ResearchQuestionDto dto;
	private long searchid;
	private long id;
	private long questionid;
	private long resultid;
	private long optionsid;
	private String result;
	private String type;
	private ResearchQuestionModel rqmodel;
	private ResearchQuestionListModel rqlmodel;
	private ResearchQuestionOptionModel rqomodel;
	private long mid = 10006;
	private long target;
	private int idx;
	
    
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public ResearchQuestionListModel getRqlmodel()
	{
		return rqlmodel;
	}

	public void setRqlmodel(ResearchQuestionListModel rqlmodel)
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

	public IResearchQuestionMgr getResearchQuestionMgr()
	{
		return researchQuestionMgr;
	}

	public void setResearchQuestionMgr(IResearchQuestionMgr researchQuestionMgr)
	{
		this.researchQuestionMgr = researchQuestionMgr;
	}

	public ResearchQuestionDto getDto()
	{
		return dto;
	}

	public void setDto(ResearchQuestionDto dto)
	{
		this.dto = dto;
	}

	public String execute()throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		int start=(pageId - 1) * IInteractConstants.VOTE_LIMIT;
		int total=researchQuestionMgr.findResearchQuestionTotal(searchid);
		dto=new ResearchQuestionDto();
		dto.setList(researchQuestionMgr.findResearchQuestionList(searchid,start,IInteractConstants.VOTE_LIMIT));
		Pager pager=new Pager(pageId, total, IInteractConstants.VOTE_LIMIT);
		dto.setPager(pager);
		return SUCCESS;
	}
	
	public String delResearchQuestion()throws Exception{
		int len=researchQuestionMgr.delResearchQuestion(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(len);
		out.flush();
		out.close();
		return null;
	}
	
	public String updateResearchQuestion()throws Exception{
		resultid=researchQuestionMgr.updateResearchQuestion(id, rqlmodel, questionid, rqomodel);
		if(resultid>0){
			result="Y";
		}
		return SUCCESS;
	}
	
	public String saveResearchQuestion()throws Exception{
		resultid=researchQuestionMgr.saveResearchQuestion(rqlmodel, rqomodel);
		if(resultid>0){
			result="Y";
		}
		return SUCCESS;
	}

	public String findOneResearchQuestion()throws Exception{
		dto=new ResearchQuestionDto();
		dto.setList(researchQuestionMgr.findOneResearchQuestion(id));
		rqmodel=researchQuestionMgr.findTypeIds(id);
		return SUCCESS;
	}
	
//	public String fileUpload()throws Exception{
//		int i=researchQuestionMgr.saveResearchQuestion(rqlmodel, rqomodel);
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("text/html;charset=utf-8");
//		response.setHeader("Cache-Control", "no-cache");
//		PrintWriter out = response.getWriter();
//		out.write(i);
//		out.flush();
//		out.close();
//		return null;
//	}
	
	public String upResearchQuestion()throws Exception{
		int result = researchQuestionMgr.updateUpResearchQuestion(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	public String downResearchQuestion()throws Exception{
		int result = researchQuestionMgr.updateDownResearchQuestion(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	public String deleteResearchOptions()throws Exception{
		int result=researchQuestionMgr.deleteResearchOptions(id);
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
		dto=new ResearchQuestionDto();
		dto.setRqo(researchQuestionMgr.findoptionbyqid(questionid));
		dto.setList(researchQuestionMgr.findquestionbyqid(questionid,searchid,idx));
		return SUCCESS;
	}
	
	public String inTarget()throws Exception{
		int result=researchQuestionMgr.updateinTarget(id,target);
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
		ResearchQuestionOptionVO vo=researchQuestionMgr.selisTar(id);
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

	public ResearchQuestionModel getRqmodel()
	{
		return rqmodel;
	}

	public void setRqmodel(ResearchQuestionModel rqmodel)
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

	public ResearchQuestionOptionModel getRqomodel()
	{
		return rqomodel;
	}

	public void setRqomodel(ResearchQuestionOptionModel rqomodel)
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
