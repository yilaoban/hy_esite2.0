package com.huiyee.interact.cs.action;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.service.FileUploadService;
import com.huiyee.interact.cs.dto.CsDto;
import com.huiyee.interact.cs.dto.JContent;
import com.huiyee.interact.cs.mgr.ICsMgr;
import com.huiyee.interact.cs.model.ChuanSan;
import com.huiyee.interact.cs.util.JsonStringUtil;
import com.opensymphony.xwork2.ActionContext;

public class CsAction extends InteractModelAction
{
	private int pageId = 1;
	private CsDto dto;
	private ICsMgr csMgr;
	private long id;
	private String title;
	private String startTime;
	private String endTime;
	private String startNote;
	private String endNote;
	private int utype;
	private String content;
	private String link;
	private ChuanSan cs;
	private List<JContent> jlist;

	private long jid;
	private File img;
	private String imgFileName;
	private String imgContentType;
	private String name;
	private String starttime;
	private String sign;// 署名
	private long mid=10013;

	@Override
	public String execute() throws Exception
	{

		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = (CsDto) csMgr.findList(ownerid, pageId, IPageConstants.OWNER_INTERACT_SHOW);
		return SUCCESS;
	}

	public String add() throws Exception
	{
		return SUCCESS;
	}

	public String addSub() throws Exception
	{
		addCheck();
		if (this.getFieldErrors().size() > 0)
		{
			return "fail";
		}
		else
		{
			ChuanSan rq = new ChuanSan();
			rq.setTitle(title);
			SimpleDateFormat dateFormatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			rq.setStarttime(startTime);
			rq.setEndtime(endTime);
			rq.setStartnote(startNote);
			rq.setEndnote(endNote);
			rq.setLink(link);
			rq.setUtype(utype);
			rq.setContent(content);
			rq.setLink(link);

			Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
			long ownerid = account.getOwner().getId();
			long id = csMgr.saveChuanSan(rq, ownerid, IPageConstants.OWNER_INTERACT_SHOW);
			return SUCCESS;
		}
	}

	public String edit() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		cs = csMgr.findCsById(id, ownerid);
		return SUCCESS;
	}

	public String editSub() throws Exception
	{
		addCheck();
		if (this.getFieldErrors().size() > 0)
		{
			Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
			long ownerid = account.getOwner().getId();
			cs = csMgr.findCsById(id, ownerid);
			return "fail";
		}
		else
		{
			ChuanSan rq = new ChuanSan();
			rq.setTitle(title);
			SimpleDateFormat dateFormatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			rq.setStarttime(startTime);
			rq.setEndtime(endTime);
			rq.setStartnote(startNote);
			rq.setEndnote(endNote);
			rq.setLink(link);
			rq.setUtype(utype);
			rq.setContent(content);
			rq.setLink(link);

			Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
			long ownerid = account.getOwner().getId();
			int result = csMgr.updateCs(rq, id, ownerid);
			return SUCCESS;
		}
	}

	public String content() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		cs = csMgr.findCsById(id, ownerid);
		jlist = JsonStringUtil.String2List(cs.getJcontent(), JContent.class);
		return SUCCESS;
	}

	public String addContentIndex() throws Exception
	{
		return SUCCESS;
	}

	public String addContentSub() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		JContent j = new JContent();
		if (img != null)
		{
			String simgfileName = FileUploadService.createFileName(imgFileName);
			String path = FileUploadService.getFilePath(IPageConstants.TYPE_INTERACT, ownerid, "10013");
			String saveResult = FileUploadService.saveFile(img, path, simgfileName);
			if (saveResult == null)
			{
				this.addFieldError("error", "上传图片出错");
				return "failed";
			}
			j.setImg(saveResult);

		}
		j.setJid(new Date().getTime());
		j.setName(name);
		j.setStarttime(starttime);
		j.setSign(sign);
		j.setContent(content);
		cs = csMgr.findCsById(id, ownerid);
		List<JContent> old = JsonStringUtil.String2List(cs.getJcontent(), JContent.class);
		if (old == null)
		{
			old = new ArrayList<JContent>();
		}
		old.add(j);
		String jsonStr = JsonStringUtil.Obj2String(old);
		csMgr.updateCsJcontent(id, ownerid, jsonStr);
		return SUCCESS;
	}

	public String delContent() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = csMgr.deleteContent(id, jid, account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	private void addCheck()
	{
		if (StringUtils.isEmpty(title))
		{
			this.addFieldError("err1", "标题不能为空");
		}
		if (StringUtils.isEmpty(startTime))
		{
			this.addFieldError("err2", "开始时间不能为空");
		}
		if (StringUtils.isEmpty(endTime))
		{
			this.addFieldError("err3", "结束时间不能为空");
		}
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getStartTime()
	{
		return startTime;
	}

	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	public String getEndTime()
	{
		return endTime;
	}

	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	public String getStartNote()
	{
		return startNote;
	}

	public void setStartNote(String startNote)
	{
		this.startNote = startNote;
	}

	public String getEndNote()
	{
		return endNote;
	}

	public void setEndNote(String endNote)
	{
		this.endNote = endNote;
	}

	public int getUtype()
	{
		return utype;
	}

	public void setUtype(int utype)
	{
		this.utype = utype;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public CsDto getDto()
	{
		return dto;
	}

	public void setDto(CsDto dto)
	{
		this.dto = dto;
	}

	public void setCsMgr(ICsMgr csMgr)
	{
		this.csMgr = csMgr;
	}

	public ChuanSan getCs()
	{
		return cs;
	}

	public void setCs(ChuanSan cs)
	{
		this.cs = cs;
	}

	public List<JContent> getJlist()
	{
		return jlist;
	}

	public void setJlist(List<JContent> jlist)
	{
		this.jlist = jlist;
	}

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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getStarttime()
	{
		return starttime;
	}

	public void setStarttime(String starttime)
	{
		this.starttime = starttime;
	}

	public String getSign()
	{
		return sign;
	}

	public void setSign(String sign)
	{
		this.sign = sign;
	}

	public long getJid()
	{
		return jid;
	}

	public void setJid(long jid)
	{
		this.jid = jid;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

}
