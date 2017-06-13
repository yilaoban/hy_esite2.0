package com.huiyee.esite.action;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.huiyee.esite.compose.IFeatureCompose;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.CardDto;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.CacheUtil;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.esite.util.HyConfig;
import com.opensymphony.xwork2.ActionContext;

public class PageShowAction2 extends AbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6677318762427944074L;
	private IFeatureCompose featureCompose;
	private long pageid;
	private long featureid;
	private long fid;
	private Map<String, Object> result;
	private String jspname;
	private String userAgent;
	private long wbuid;
	private long hyuid;
	private long cid;
	private String style;
	private String source = "ldp";
	private CardDto dto;
	private String html = "";
	private Page page;
	private int cd;// 用来识别当前环境的;0-默认环境,1-微博,2-微信
	private String method="p";
	private String link;
	private String kv="";
	
	public String getKv()
	{
		return kv;
	}

	public void setKv(String kv)
	{
		this.kv = kv;
	}

	public String getLink()
	{
		return link;
	}


	
	public void setLink(String link)
	{
		this.link = link;
	}


	public String getMethod()
	{
		return method;
	}

	
	public void setMethod(String method)
	{
		this.method = method;
	}

	public long getFeatureid()
	{
		return featureid;
	}

	public void setFeatureid(long featureid)
	{
		this.featureid = featureid;
	}

	public long getFid()
	{
		return fid;
	}

	public void setFid(long fid)
	{
		this.fid = fid;
	}

	// 查看
	public String show() throws Exception
	{
		if (HyConfig.isRun()){
			return preview();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		String userA = request.getHeader("user-agent");
		userAgent = HttpRequestDeviceUtils.getMediaDevice(userA);
		page = pageCompose.findPageByid(pageid);
		if (page == null)
		{
			jspname = "nopage.jsp";
			return SUCCESS;
		}
		if (page.getIsonline().equals("N"))
		{
			jspname = "notonline.jsp";
			return SUCCESS;
		}
		if (page.getStatus().equals("DEL"))
		{
			jspname = "delpage.jsp";
			return SUCCESS;
		}
		//VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
//		if (vu != null)
//		{
//			source = vu.getSource();
//			cd = vu.getCd();
//			kv=vu.getKv();
//		}
		if (page.getType().equals("C"))
		{//卡片类型
			dto = CacheUtil.pgds.get(pageid);
			if (dto == null)
			{
				dto = (CardDto) pageCompose.composeTemplateCardByPageid2(pageid);// 需要走缓存
				if (page.getStype().equalsIgnoreCase("W") || page.getStype().equalsIgnoreCase("D"))
				{
					dto.setXcid(pageCompose.findXcIdBySiteid(page.getSiteid()));
				}
				if(dto!=null)
				CacheUtil.pgds.put(pageid, dto);
			}
		}
		else if (page.getType().equals("G"))
		{//个性化
			result = pageCompose.show(pageid);
			if (result != null)
			{
				result.put("page", page);
			}
		} else if (page.getType().equals("H"))
		{//混合类型
			result = featureCompose.show(pageid,page.getType(),source);
			ServletActionContext.getRequest().getSession().setAttribute("hy_page_dto", result);
			//下面是卡片要的信息
			dto = CacheUtil.pgds.get(pageid);
			if (dto == null)
			{
				dto = (CardDto) pageCompose.composeTemplateCardByPageid2(pageid);// 需要走缓存
				if (page.getStype().equalsIgnoreCase("W") || page.getStype().equalsIgnoreCase("D"))
				{
					dto.setXcid(pageCompose.findXcIdBySiteid(page.getSiteid()));
				}
				if(dto!=null)
				CacheUtil.pgds.put(pageid, dto);
			}
		} 
		else
		{
			jspname = "notypepage.jsp";
			return SUCCESS;
		}
		jspname = page.getJspname();
		List<String> sources = pageCompose.findPageSourceByPageid(pageid,false,IPageConstants.OWNER_SOURCE_LEVEL_PAGE);
		if(sources != null && sources.size() > 0){
			for(String str : sources){
				html += str;
			}
		}
		return SUCCESS;
	}

	// 预览
	public String preview() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		// 后台预览 出现提示页面
		String p = (String) request.getSession().getAttribute("preview");
		if (p == null && !"e".equalsIgnoreCase(method))
		{
			request.getRequestDispatcher("/WEB-INF/pagechip/suggest.jsp").forward(request, response);
			return SUCCESS;
		}

		String userA = request.getHeader("user-agent");
		userAgent = HttpRequestDeviceUtils.getMediaDevice(userA);
		page = pageCompose.findPageByid(pageid);
		if (page == null)
		{
			jspname = "nopage.jsp";
			return SUCCESS;
		}
		if (page.getStatus().equals("DEL"))
		{
			jspname = "delpage.jsp";
			return SUCCESS;
		}
		if (page.getType().equals("C"))
		{
			dto = (CardDto) pageCompose.composeTemplateCardByPageid(pageid);// 后台不需要走缓存
		}
		else if (page.getType().equals("N"))
		{
			html = pageCompose.findHtmlByPage(pageid, "E");
		}
		else if (page.getType().equals("G"))
		{
			result = featureCompose.show(pageid,page.getType(),source);
		} else if (page.getType().equals("H"))
		{
			result = featureCompose.show(pageid,page.getType(),source);
			ServletActionContext.getRequest().getSession().setAttribute("hy_page_dto", result);
			//下面是卡片要的信息
			dto = (CardDto) pageCompose.composeTemplateCardByPageid(pageid);// 后台不需要走缓存
		} 
		else
		{
			jspname = "notypepage.jsp";
			return SUCCESS;
		}
		jspname = page.getJspname();
		List<String> sources = pageCompose.findPageSourceByPageid(pageid,false,IPageConstants.OWNER_SOURCE_LEVEL_PAGE);
		if(sources != null && sources.size() > 0){
			for(String str : sources){
				html += str;
			}
		}
		return SUCCESS;
	}
	
	public String look() throws Exception{
		return SUCCESS;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public Map<String, Object> getResult()
	{
		return result;
	}

	public String getJspname()
	{
		return jspname;
	}

	public String getUserAgent()
	{
		return userAgent;
	}

	public long getHyuid()
	{
		return hyuid;
	}

	public void setHyuid(long hyuid)
	{
		this.hyuid = hyuid;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public String getStyle()
	{
		return style;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public long getCid()
	{
		return cid;
	}

	public void setCid(long cid)
	{
		this.cid = cid;
	}

	public CardDto getDto()
	{
		return dto;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public String getHtml()
	{
		return html;
	}

	public Page getPage()
	{
		return page;
	}

	public void setPage(Page page)
	{
		this.page = page;
	}

	public void setFeatureCompose(IFeatureCompose featureCompose)
	{
		this.featureCompose = featureCompose;
	}

	public String getSource()
	{
		return source;
	}

	public int getCd()
	{
		return cd;
	}

	public void setCd(int cd)
	{
		this.cd = cd;
	}

}
