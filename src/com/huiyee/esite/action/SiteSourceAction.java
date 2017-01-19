package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.SourceDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.PageSource;
import com.huiyee.esite.model.SiteSource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SiteSourceAction extends AbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4420226269133059926L;
	
	private List<SiteSource> sourceList;
	private long pageid;
	private List<Long> sources;
	private List<PageSource> pageSource;
	private SourceDto dto;
	private long psid;
	private String json;
	private String top;
	private String left;
	private String right;
	private String bottom;
	private long siteid;
	private Map<String, SiteSource> map;
	private long sourceid;
	private SiteSource ss;
	private long xcid;
	
	public long getXcid()
	{
		return xcid;
	}


	
	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}


	public String getRight()
	{
		return right;
	}

	
	public void setRight(String right)
	{
		this.right = right;
	}

	
	public String getBottom()
	{
		return bottom;
	}
	
	public void setBottom(String bottom)
	{
		this.bottom = bottom;
	}

	public long getSourceid()
	{
		return sourceid;
	}
	
	public void setSourceid(long sourceid)
	{
		this.sourceid = sourceid;
	}

	public SiteSource getSs()
	{
		return ss;
	}
	
	public void setSs(SiteSource ss)
	{
		this.ss = ss;
	}

	public long getSiteid()
	{
		return siteid;
	}
	
	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	
	public Map<String, SiteSource> getMap()
	{
		return map;
	}

	public void setMap(Map<String, SiteSource> map)
	{
		this.map = map;
	}


	public String getTop()
	{
		return top;
	}

	
	public void setTop(String top)
	{
		this.top = top;
	}

	
	public String getLeft()
	{
		return left;
	}

	
	public void setLeft(String left)
	{
		this.left = left;
	}
	
	public String getJson()
	{
		return json;
	}
	
	public void setJson(String json)
	{
		this.json = json;
	}

	public long getPsid()
	{
		return psid;
	}
	
	public void setPsid(long psid)
	{
		this.psid = psid;
	}

	public SourceDto getDto()
	{
		return dto;
	}
	
	public void setDto(SourceDto dto)
	{
		this.dto = dto;
	}

	public List<PageSource> getPageSource()
	{
		return pageSource;
	}
	
	public void setPageSource(List<PageSource> pageSource)
	{
		this.pageSource = pageSource;
	}

	public long getPageid()
	{
		return pageid;
	}
	
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public void setSources(List<Long> sources)
	{
		this.sources = sources;
	}

	public List<SiteSource> getSourceList()
	{
		return sourceList;
	}

	public void setSourceList(List<SiteSource> sourceList)
	{
		this.sourceList = sourceList;
	}
	
	@Override
	public String execute() throws Exception
	{
		return SUCCESS;
	}

	public String sourceset() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		sourceList = pageCompose.findSiteSourceByOwner(account.getOwner(),pageid);
		return SUCCESS;
	}
	
	public String sourcesave() throws Exception{
		pageCompose.updatePageSource(pageid, sources);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(1);
		pw.flush();
		pw.close();
		return null;
	}

	public String sourceList() throws Exception{
		pageSource = pageCompose.findPageSourceListByPageid(pageid);
		return SUCCESS;
	}
	
	public String edit_source() throws Exception{
		dto = pageCompose.findPageSourceById(psid,pageid);
		String json = dto.getVjson();
		try{
			JSONObject.fromObject(json);
			return "S";
		} catch (Exception e){
		}
		try{
			JSONArray.fromObject(json);
			return "C";
		} catch (Exception e){
		}
		return null;
	}
	
	public String edit_source_save() throws Exception{
		int result = pageCompose.savePageSourceEdit(psid, json);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
		return null;
	}

	public String edit_source_save_c() throws Exception{
		int result = pageCompose.savePageSourceEditC(psid, json,top,left,right,bottom);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
		return null;
	}
	
	public String source_manager() throws Exception{
		map = pageCompose.findSiteSource(siteid);
		return SUCCESS;
	}
	
	public String source_edit() throws Exception{
		ss= pageCompose.findSiteSourceById(sourceid);
		return SUCCESS;
	}
	
	public int getLightType(){
		return 3;
	}
	
	public String find_source_page() throws Exception{
		int result = pageCompose.findSourcePage(sourceid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
		return null;
	}
	
	public String del_source() throws Exception{
		int result = pageCompose.delSiteSource(sourceid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
		return null;
	}
	
}
