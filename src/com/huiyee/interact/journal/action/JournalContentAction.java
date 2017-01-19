package com.huiyee.interact.journal.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.journal.dto.JournalDto;
import com.huiyee.interact.journal.mgr.IJournalMgr;
import com.huiyee.interact.journal.util.FileUploadService;

public class JournalContentAction extends InteractModelAction{

	private static final long serialVersionUID = 2764910201890730259L;
	
	private IJournalMgr journalMgr;
	private int pageId=1;
	private long jid;
	private long id;
	private JournalDto dto;
	private long mid = 10001;
	private String result;
	private File bimg;
	private String bimgFileName;
	private String bimgContentType;
	private String oldbimg;
	private File simg;
	private String simgFileName;
	private String simgContentType;
	private String oldsimg;
	private File sharepic;
	private String sharepicFileName;
	private String sharepicContentType;
	private String sharepicimg;
	
	@Override
	public String execute() throws Exception {
		dto = (JournalDto) journalMgr.findJournalContentList(jid,pageId);
		return SUCCESS;
	}

	public String toJournalContentDesign() throws Exception {
		return SUCCESS;
	}
	
	private String createFileName(String fileName,String type) {
		int index = fileName.lastIndexOf('.');
		// ≈–∂œ…œ¥´Œƒº˛√˚ «∑Ò”–¿©’π√˚
		if (index != -1) {
			return FileUploadService.getNow()+type+ fileName.substring(index);
		}
		return FileUploadService.getNow()+type;
	}
	
	public String saveJournalContent() throws Exception {
		Account account =(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		if(simg != null){
			String rename = createFileName(simgFileName,"_m");
			String repath = FileUploadService.getFilePath(IInteractConstants.TYPE_INTERACT, ownerid, mid+"");
			String resaveResult = FileUploadService.saveFile(simg,repath,rename);
			if(resaveResult == null){
				result="Õº∆¨±£¥Ê ß∞‹";
				return SUCCESS;
			}
			dto.getJournal().setSimg(resaveResult);
		}
		if(bimg != null){
			String rename = createFileName(bimgFileName,"_m");
			String repath = FileUploadService.getFilePath(IInteractConstants.TYPE_INTERACT, ownerid, mid+"");
			String resaveResult = FileUploadService.saveFile(bimg,repath,rename);
			if(resaveResult == null){
				result="Õº∆¨±£¥Ê ß∞‹";
				return SUCCESS;
			}
			dto.getJournal().setBimg(resaveResult);
		}
		long resultid = journalMgr.saveJournalContent(jid,dto);
		if(resultid > 0){
			result = "Y";
		}
		return SUCCESS;
	}
	
	public String toUpdateJournalContent() throws Exception {
		dto = (JournalDto) journalMgr.findJournalContentById(id);
		return SUCCESS;
	}
	
	public String updateJournalContent() throws Exception {
		Account account =(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		if(simg != null){
			String rename = createFileName(simgFileName,"_m");
			String repath = FileUploadService.getFilePath(IInteractConstants.TYPE_INTERACT, ownerid, mid+"");
			String resaveResult = FileUploadService.saveFile(simg,repath,rename);
			if(resaveResult == null){
				result="Õº∆¨±£¥Ê ß∞‹";
				return SUCCESS;
			}
			dto.getJournal().setSimg(resaveResult);
		}
		if(simg == null){
			dto.getJournal().setSimg(oldsimg);
		}
		if(bimg != null){
			String rename = createFileName(bimgFileName,"_m");
			String repath = FileUploadService.getFilePath(IInteractConstants.TYPE_INTERACT, ownerid, mid+"");
			String resaveResult = FileUploadService.saveFile(bimg,repath,rename);
			if(resaveResult == null){
				result="Õº∆¨±£¥Ê ß∞‹";
				return SUCCESS;
			}
			dto.getJournal().setBimg(resaveResult);
		}
		if(bimg == null){
			dto.getJournal().setBimg(oldbimg);
		}
		long len = journalMgr.updateJournalContent(id,dto);
		if(len == 1){
			result="Y";
		}
		return SUCCESS;
	}
	
	public String toUpdateJournalShareContent() throws Exception {
		dto = (JournalDto) journalMgr.findJournalContentById(id);
		return SUCCESS;
	}
	
	public String updateJournalShareContent() throws Exception {
		Account account =(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		if(sharepic != null){
			String rename = createFileName(sharepicFileName,"_m");
			String repath = FileUploadService.getFilePath(IInteractConstants.TYPE_INTERACT, ownerid, mid+"");
			String resaveResult = FileUploadService.saveFile(sharepic,repath,rename);
			if(resaveResult == null){
				result="Õº∆¨±£¥Ê ß∞‹";
				return SUCCESS;
			}
			dto.getJournal().setSharepic(resaveResult);
		}
		if(sharepic == null){
			dto.getJournal().setSharepic(sharepicimg);
		}
		long len = journalMgr.updateJournalShareContent(id,dto);
		if(len == 1){
			result="Y";
		}
		return SUCCESS;
	}
	
	
	public String deleteJournalContent()throws Exception{
		int len = journalMgr.deleteJournalContent(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(len);
		out.flush();
		out.close();
		return null;
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

	public long getJid()
	{
		return jid;
	}

	public void setJid(long jid)
	{
		this.jid = jid;
	}

	public IJournalMgr getJournalMgr()
	{
		return journalMgr;
	}

	public void setJournalMgr(IJournalMgr journalMgr)
	{
		this.journalMgr = journalMgr;
	}

	public JournalDto getDto()
	{
		return dto;
	}

	public void setDto(JournalDto dto)
	{
		this.dto = dto;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public File getBimg()
	{
		return bimg;
	}

	public void setBimg(File bimg)
	{
		this.bimg = bimg;
	}

	public String getBimgFileName()
	{
		return bimgFileName;
	}

	public void setBimgFileName(String bimgFileName)
	{
		this.bimgFileName = bimgFileName;
	}

	public String getBimgContentType()
	{
		return bimgContentType;
	}

	public void setBimgContentType(String bimgContentType)
	{
		this.bimgContentType = bimgContentType;
	}

	public String getOldbimg()
	{
		return oldbimg;
	}

	public void setOldbimg(String oldbimg)
	{
		this.oldbimg = oldbimg;
	}

	public File getSimg()
	{
		return simg;
	}

	public void setSimg(File simg)
	{
		this.simg = simg;
	}

	public String getSimgFileName()
	{
		return simgFileName;
	}

	public void setSimgFileName(String simgFileName)
	{
		this.simgFileName = simgFileName;
	}

	public String getSimgContentType()
	{
		return simgContentType;
	}

	public void setSimgContentType(String simgContentType)
	{
		this.simgContentType = simgContentType;
	}

	public String getOldsimg()
	{
		return oldsimg;
	}

	public void setOldsimg(String oldsimg)
	{
		this.oldsimg = oldsimg;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public File getSharepic()
	{
		return sharepic;
	}

	public void setSharepic(File sharepic)
	{
		this.sharepic = sharepic;
	}

	public String getSharepicFileName()
	{
		return sharepicFileName;
	}

	public void setSharepicFileName(String sharepicFileName)
	{
		this.sharepicFileName = sharepicFileName;
	}

	public String getSharepicContentType()
	{
		return sharepicContentType;
	}

	public void setSharepicContentType(String sharepicContentType)
	{
		this.sharepicContentType = sharepicContentType;
	}

	public String getSharepicimg()
	{
		return sharepicimg;
	}

	public void setSharepicimg(String sharepicimg)
	{
		this.sharepicimg = sharepicimg;
	}
	
}
