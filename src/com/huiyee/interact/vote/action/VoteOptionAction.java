package com.huiyee.interact.vote.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.interact.vote.dto.VoteOptionDto;
import com.huiyee.interact.vote.mgr.IVoteMgr;
import com.huiyee.interact.vote.model.Pager;
import com.huiyee.interact.vote.model.VoteOption;
import com.huiyee.interact.vote.model.VoteOptionModel;
import com.huiyee.interact.vote.util.FileUploadService;
import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class VoteOptionAction extends InteractModelAction{

	
	private static final long serialVersionUID = 5676277206747346960L;
	
	private IVoteMgr voteMgr;
	private VoteOptionDto dto;
	private long resultid;
	private String result;
	private long id;
	private long voteid;
	private VoteOptionModel vom;
	private VoteOption vo;
	private int pageId=1;
	private long mid=10002;
	private String type;
	private String vediotype;
	private File uploadvedio;
	private String uploadvedioFileName;
	private String uploadvedioContentType;
	private String vediourl;//编辑
	private long optionid;
	private int oldIdx;
	private int newIdx;
	private String[] tags;
	
	public String[] getTags()
	{
		return tags;
	}
	
	public void setTags(String[] tags)
	{
		this.tags = tags;
	}

	public File getUploadvedio()
	{
		return uploadvedio;
	}

	public void setUploadvedio(File uploadvedio)
	{
		this.uploadvedio = uploadvedio;
	}

	public String getUploadvedioFileName()
	{
		return uploadvedioFileName;
	}

	public void setUploadvedioFileName(String uploadvedioFileName)
	{
		this.uploadvedioFileName = uploadvedioFileName;
	}

	public String getUploadvedioContentType()
	{
		return uploadvedioContentType;
	}

	public void setUploadvedioContentType(String uploadvedioContentType)
	{
		this.uploadvedioContentType = uploadvedioContentType;
	}

	public String getVediotype()
	{
		return vediotype;
	}

	public void setVediotype(String vediotype)
	{
		this.vediotype = vediotype;
	}

	public IVoteMgr getVoteMgr()
	{
		return voteMgr;
	}

	public void setVoteMgr(IVoteMgr voteMgr)
	{
		this.voteMgr = voteMgr;
	}

	

	public VoteOptionDto getDto()
	{
		return dto;
	}

	public void setDto(VoteOptionDto dto)
	{
		this.dto = dto;
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


	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getVoteid()
	{
		return voteid;
	}

	public void setVoteid(long voteid)
	{
		this.voteid = voteid;
	}

	public VoteOptionModel getVom()
	{
		return vom;
	}

	public void setVom(VoteOptionModel vom)
	{
		this.vom = vom;
	}

	@Override
	public String execute() throws Exception
	{
		return SUCCESS;
	}
	
	public String findVoteOptionList()throws Exception{
		int start=(pageId-1)*IInteractConstants.VOTE_LIMIT;
		int total=voteMgr.findVoteOptionTotal(voteid);
		dto=new VoteOptionDto();
		dto.setList(voteMgr.searchVoteOptionList(voteid,start,IInteractConstants.VOTE_LIMIT));
		Pager pager=new Pager(pageId,total,IInteractConstants.VOTE_LIMIT);
		dto.setPager(pager);
		return SUCCESS;
	}
	
	public String updateContent() throws Exception{
		int result = voteMgr.updateContnetIdx(optionid,voteid,oldIdx,newIdx);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	
	public String saveVoteOption()throws Exception{
		if(uploadvedio!= null && uploadvedio.length() > (10*1024*1024)){
			result= "文件大小超过10M!" ;
			return "failed";
		}
		vom.setTag(JSONArray.fromObject(tags).toString());
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String vediopath = FileUploadService.getFilePath(IPageConstants.TYPE_INTERACT, account.getOwner().getId(), IPageConstants.CONTENT_VIDEO_FILEFATH);
		if ("U".equals(vediotype) && uploadvedio != null) {
			String simgfileName = FileUploadService.createFileName(uploadvedioFileName);
			String result1 = FileUploadService.saveFile(uploadvedio, vediopath, simgfileName);
			result1 = HyConfig.getImgDomain() + result1;
			vom.setVediourl(result1);
		}
		if(vom != null && vom.getContent()!= null){
			vom.setContent(vom.getContent().replace("<br>", "").replace("<br/>", ""));
		}
		resultid=voteMgr.saveVoteContent(vom, voteid);
		if(resultid>0){
			result="Y";
		}
		return SUCCESS;
	}
	
	public String updateVoteOption()throws Exception{
		if(uploadvedio!=null && uploadvedio.length() > (10*1024*1024)){
			result= "文件大小超过10M!" ;
			return "failed";
		}
		vom.setTag(JSONArray.fromObject(tags).toString());
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String vediopath = FileUploadService.getFilePath(IPageConstants.TYPE_INTERACT, account.getOwner().getId(), IPageConstants.CONTENT_VIDEO_FILEFATH);
		if ("U".equals(vediotype) && uploadvedio != null) {
			String simgfileName = FileUploadService.createFileName(uploadvedioFileName);
			String result1 = FileUploadService.saveFile(uploadvedio, vediopath, simgfileName);
			result1 = HyConfig.getImgDomain() + result1;
			vom.setVediourl(result1);
		}else if(!"U".equals(vediotype) && !"W".equals(vediotype)){
			vom.setVediourl(vediourl);
		}
		if(vom != null && vom.getContent()!= null){
			vom.setContent(vom.getContent().replace("<br>", "").replace("<br/>", ""));
		}
		resultid=voteMgr.updateVoteOption(id, vom, voteid);
		if(resultid>0){
			result="Y";
		}
		return SUCCESS;
	}
	
	public String delVoteOption()throws Exception{
		int len=voteMgr.delVoteOption(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(len);
		out.flush();
		out.close();
		return null;
		
	}
	
	public String findOneOption()throws Exception{
		vo=voteMgr.findOneOption(id);
		return SUCCESS;
	}

	public VoteOption getVo()
	{
		return vo;
	}

	public void setVo(VoteOption vo)
	{
		this.vo = vo;
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

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getVediourl()
	{
		return vediourl;
	}

	public void setVediourl(String vediourl)
	{
		this.vediourl = vediourl;
	}
	
	public long getOptionid() {
		return optionid;
	}

	public void setOptionid(long optionid) {
		this.optionid = optionid;
	}

	public int getOldIdx() {
		return oldIdx;
	}

	public void setOldIdx(int oldIdx) {
		this.oldIdx = oldIdx;
	}

	public int getNewIdx() {
		return newIdx;
	}

	public void setNewIdx(int newIdx) {
		this.newIdx = newIdx;
	}


}
