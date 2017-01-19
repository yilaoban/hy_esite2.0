package com.huiyee.interact.spread.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.spread.dto.SpreadOptionDto;
import com.huiyee.interact.spread.mgr.ISpreadOptionMgr;
import com.huiyee.interact.spread.model.Pager;
import com.huiyee.interact.spread.model.SpreadModel;
import com.huiyee.interact.spread.model.SpreadOption;
import com.huiyee.interact.vote.util.FileUploadService;

public class SpreadOptionAction extends InteractModelAction
{

	private static final long serialVersionUID = 3737697247082240572L;
	private ISpreadOptionMgr spreadOptionMgr;
	private SpreadOptionDto dto;
	private SpreadOption sp;
	private SpreadModel sm;
	private long spreadid;
	private long id;
	private File img;
	private String imgFileName;
	private String imgContentType;
	private long mid=10010;
	private String result;
	private long resultid;
	private int pageId=1;
	
	public File getImg()
	{
		return img;
	}
	public void setImg(File img)
	{
		this.img = img;
	}
	public String getImgFileName()
	{
		return imgFileName;
	}
	public void setImgFileName(String imgFileName)
	{
		this.imgFileName = imgFileName;
	}
	public String getImgContentType()
	{
		return imgContentType;
	}
	public void setImgContentType(String imgContentType)
	{
		this.imgContentType = imgContentType;
	}
	public SpreadOptionDto getDto()
	{
		return dto;
	}
	public void setDto(SpreadOptionDto dto)
	{
		this.dto = dto;
	}
	public SpreadOption getSp()
	{
		return sp;
	}
	public void setSp(SpreadOption sp)
	{
		this.sp = sp;
	}
	public ISpreadOptionMgr getSpreadOptionMgr()
	{
		return spreadOptionMgr;
	}
	public void setSpreadOptionMgr(ISpreadOptionMgr spreadOptionMgr)
	{
		this.spreadOptionMgr = spreadOptionMgr;
	}
	
	public String execute()throws Exception{
		Account account =(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		int start=(pageId-1)*IInteractConstants.SPREADOPTION_LIMIT ;
		int total=spreadOptionMgr.findSpreadOptionTotal(id);
		dto=(SpreadOptionDto) spreadOptionMgr.findSpreadOption(id,start,IInteractConstants.SPREADOPTION_LIMIT);
		Pager pager=new Pager(pageId,total,IInteractConstants.SPREADOPTION_LIMIT);
		dto.setPager(pager);
		return SUCCESS;
	}
	
	private String createFileName(String fileName,String type) {
		int index = fileName.lastIndexOf('.');
		// 判断上传文件名是否有扩展名
		if (index != -1) {
			return FileUploadService.getNow()+type+ fileName.substring(index);
		}
		return FileUploadService.getNow()+type;
	}
	
	public String saveSpreadOption()throws Exception{
		Account account =(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		if(img!=null){
			String name = createFileName(imgFileName,"_m");
			String path = FileUploadService.getFilePath(IInteractConstants.TYPE_INTERACT, ownerid, mid+"");
			String saveResult = FileUploadService.saveFile(img,path,name);
			if(saveResult == null){
				result="图片保存失败";
				return SUCCESS;
			}
			sp.setPic(saveResult);
		}
		resultid=spreadOptionMgr.saveSpreadOption(sp);
		if(resultid>0){
			result="Y";
		}
		return SUCCESS;
	}
	
	public String findOneSpreadOption()throws Exception{
		sp=spreadOptionMgr.findOneSpreadOption(spreadid);
		return SUCCESS;
	}
	
	public String updateSpreadOption()throws Exception{
		Account account =(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		if(img!=null){
			String name = createFileName(imgFileName,"_m");
			String path = FileUploadService.getFilePath(IInteractConstants.TYPE_INTERACT, ownerid, mid+"");
			String saveResult = FileUploadService.saveFile(img,path,name);
			if(saveResult == null){
				result="图片保存失败";
				return SUCCESS;
			}
			sp.setPic(saveResult);
		}
		resultid=spreadOptionMgr.updateSpreadOption(sp);
		if(resultid>0){
			result="Y";
		}
		return SUCCESS;
	}
	
	public String deleteSpreadOption()throws Exception{
		int len=spreadOptionMgr.deleteSpreadOption(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(len);
		out.flush();
		out.close();
		return null;
	}
	
	public String updateSpreadWbid()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();  
		String indexid=request.getParameter("id");
		if("".equals(indexid)){
			resultid=spreadOptionMgr.saveSpreadWbid(sp, spreadid);
		}else{
			resultid=spreadOptionMgr.updateSpreadWbid(sp, id);
		}
		if(resultid>0){
			result="Y";
			return SUCCESS;
		}else{
			result="链接错误,请检查所输入的链接";
			return ERROR;
		}
	}
	
	public SpreadModel getSm()
	{
		return sm;
	}
	public void setSm(SpreadModel sm)
	{
		this.sm = sm;
	}
	public long getSpreadid()
	{
		return spreadid;
	}
	public void setSpreadid(long spreadid)
	{
		this.spreadid = spreadid;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getResult()
	{
		return result;
	}
	public void setResult(String result)
	{
		this.result = result;
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
