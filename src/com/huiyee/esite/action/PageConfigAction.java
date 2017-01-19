
package com.huiyee.esite.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.DaohangDto;
import com.huiyee.esite.dto.PageAddressDto;
import com.huiyee.esite.dto.PageConfigDto;
import com.huiyee.esite.dto.PageTemplateDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageAddress;
import com.huiyee.esite.model.PageTemplate;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.service.FileUploadService;

public class PageConfigAction extends AbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8601870727170841257L;
	private long sitegroupid;
	private PageConfigDto dto;
	private List<Site> list;
	private long siteid;
	private String stype;
	private long pageid;
	private String wxurl;
	private String nickname;
	private PageAddressDto pdto;
	private String name;
	private String source;
	private String weixin;
	private String address;
	private String result;
	private Page page;
	private long weixinid;
	private String sup;
	private String templatename;
	private File pic;
	private String picFileName;
	private String picContentType;
	private PageTemplateDto ptdto;
	private int authed;
	private String type;
	private long xcid;// 微现场点过来的会带此参数
	private DaohangDto dh;
	/**
	 * 微社区=1 合伙人=2 微积分=3 微电商=4 积分商城=5 线下签到=6 微预约=7 潜客管理=8 用户分析=9 站内搜索=10 微投放=11
	 */
	private int appId;

	public String getName()
	{
		return name;
	}

	public DaohangDto getDh()
	{
		return dh;
	}

	public void setDh(DaohangDto dh)
	{
		this.dh = dh;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public String getWeixin()
	{
		return weixin;
	}

	public void setWeixin(String weixin)
	{
		this.weixin = weixin;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (PageConfigDto) pageCompose.composePageconfig(sitegroupid, account);

		return SUCCESS;
	}

	public String pageConfigNew() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		sup = account.getOwner().getSup();
		dto = (PageConfigDto) pageCompose.composePageconfigNew(siteid, account);
		if (xcid != 0)
		{
			dh = pageCompose.composeDhByXcid(xcid);
		}
		return SUCCESS;
	}

	public String pageConfigIndex() throws Exception
	{
		return SUCCESS;
	}
	
	public String pageConfigApp() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (PageConfigDto) pageCompose.composePageconfigNew(siteid, account);
		return SUCCESS;
	}

	// 上线 连带子页面一起上线
	public String pageOnline() throws Exception
	{
		int len = pageCompose.updateOnline(pageid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(len);
		out.flush();
		out.close();
		return null;
	}

	// 下线
	public String pageOffline() throws Exception
	{
		int len = pageCompose.updateOffline(pageid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(len);
		out.flush();
		out.close();
		return null;
	}

	// 更新数据 连带子页面一起更新
	public String updateData() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int len;
		try
		{
			len = pageCompose.updateData(pageid);
		} catch (Exception e)
		{
			e.printStackTrace();
			len = -1;
		}
		out.print(len);
		out.flush();
		out.close();
		return null;
	}

	// 全部上线
	public String pageOnlineAll() throws Exception
	{
		int len = pageCompose.updateOnlineAll(siteid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(len);
		out.flush();
		out.close();
		return null;
	}

	// 全部上线
	public String pageOfflineAll() throws Exception
	{
		int len = pageCompose.updateOfflineAll(siteid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(len);
		out.flush();
		out.close();
		return null;
	}

	// 全部更新
	public String updateDataAll() throws Exception
	{
		int len = pageCompose.updateDataAll(siteid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(len);
		out.flush();
		out.close();
		return null;
	}

	public String showAddresslist() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		pdto = (PageAddressDto) pageCompose.findAddressList(account, pageid);
		return SUCCESS;
	}

	public String savePageAddress() throws Exception
	{
		PageAddress pa = new PageAddress();
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		pa.setPageid(pageid);
		pa.setName(name);
		pa.setSource(source);
		pa.setWeixin(weixin);
		int len = pageCompose.savePageAddress(pa, account);
		if (len == 1)
		{
			result = "Y";
		}
		return SUCCESS;
	}

	public String findSourceExit() throws Exception
	{
		String str = source.toLowerCase();
		int aaa = pageCompose.findSourceExit(pageid, str);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(aaa);
		out.flush();
		out.close();
		return null;
	}

	public String findPageSubWeixin() throws Exception
	{
		page = pageCompose.findPageSubweixin(pageid);
		return SUCCESS;
	}

	public String sharePre() throws Exception
	{
		return SUCCESS;
	}

	private String createFileName(String fileName, String type)
	{
		int index = fileName.lastIndexOf('.');
		// 判断上传文件名是否有扩展名
		if (index != -1)
		{
			return FileUploadService.getNow() + type + fileName.substring(index);
		}
		return FileUploadService.getNow() + type;
	}

	public String shareSub() throws Exception
	{
		PageTemplate pt = new PageTemplate();
		pt.setPageid(pageid);
		pt.setName(templatename);
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		if (pic != null)
		{
			String name = createFileName(picFileName, "_m");
			String path = FileUploadService.getFilePath(IPageConstants.TYPE_PAGETEMP, ownerid, "");
			String saveResult = FileUploadService.saveFile(pic, path, name);
			if (saveResult == null)
			{
				result = "图片保存失败";
				return SUCCESS;
			}
			pt.setImg(saveResult);
		}
		int len = pageCompose.savePageTemplate(pt);
		if (len > 0)
		{
			result = "Y";
		}
		return SUCCESS;
	}

	public String chooseTemplatePage() throws Exception
	{
		ptdto = (PageTemplateDto) pageCompose.findPageTemplate();
		return SUCCESS;
	}

	public PageConfigDto getDto()
	{
		return dto;
	}

	public long getSiteid()
	{
		return siteid;
	}

	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	public void setDto(PageConfigDto dto)
	{
		this.dto = dto;
	}

	public List<Site> getList()
	{
		return list;
	}

	public void setList(List<Site> list)
	{
		this.list = list;
	}

	public long getSitegroupid()
	{
		return sitegroupid;
	}

	public void setSitegroupid(long sitegroupid)
	{
		this.sitegroupid = sitegroupid;
	}

	public String getStype()
	{
		return stype;
	}

	public void setStype(String stype)
	{
		this.stype = stype;
	}

	public String getWxurl()
	{
		return wxurl;
	}

	public void setWxurl(String wxurl)
	{
		this.wxurl = wxurl;
	}

	public PageAddressDto getPdto()
	{
		return pdto;
	}

	public void setPdto(PageAddressDto pdto)
	{
		this.pdto = pdto;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public Page getPage()
	{
		return page;
	}

	public void setPage(Page page)
	{
		this.page = page;
	}

	public long getWeixinid()
	{
		return weixinid;
	}

	public void setWeixinid(long weixinid)
	{
		this.weixinid = weixinid;
	}

	public String getSup()
	{
		return sup;
	}

	public void setSup(String sup)
	{
		this.sup = sup;
	}

	public String getTemplatename()
	{
		return templatename;
	}

	public void setTemplatename(String templatename)
	{
		this.templatename = templatename;
	}

	public File getPic()
	{
		return pic;
	}

	public void setPic(File pic)
	{
		this.pic = pic;
	}

	public String getPicFileName()
	{
		return picFileName;
	}

	public void setPicFileName(String picFileName)
	{
		this.picFileName = picFileName;
	}

	public String getPicContentType()
	{
		return picContentType;
	}

	public void setPicContentType(String picContentType)
	{
		this.picContentType = picContentType;
	}

	public PageTemplateDto getPtdto()
	{
		return ptdto;
	}

	public void setPtdto(PageTemplateDto ptdto)
	{
		this.ptdto = ptdto;
	}

	public int getAuthed()
	{
		return authed;
	}

	public void setAuthed(int authed)
	{
		this.authed = authed;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public long getXcid()
	{
		return xcid;
	}

	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}

	public int getAppId()
	{
		return appId;
	}

	public void setAppId(int appId)
	{
		this.appId = appId;
	}

}
