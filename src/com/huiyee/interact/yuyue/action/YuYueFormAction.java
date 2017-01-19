package com.huiyee.interact.yuyue.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.yuyue.dto.YuYueFromDto;
import com.huiyee.interact.yuyue.mgr.IYuYueFormMgr;
import com.opensymphony.xwork2.ActionContext;


public class YuYueFormAction extends AbstractAction
{
	private static final long serialVersionUID = -2417889816672869287L;
	private IYuYueFormMgr yuYueFormMgr;
	private YuYueFromDto dto;
	private long catid;
	private long serid;
	private long serviceid;
	private int pageId = 1;
	private int size;
    private String type="NOR";
	
	public void setType(String type)
	{
		this.type = type;
	}

	public void setYuYueFormMgr(IYuYueFormMgr yuYueFormMgr)
	{
		this.yuYueFormMgr = yuYueFormMgr;
	}

	@Override
	public String execute() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser visit = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(visit != null && visit.getWxUser() != null){
			long wxuid = visit.getWxUser().getId();
			dto = (YuYueFromDto) yuYueFormMgr.findAptRecordByWxuid(wxuid,this.getOwner());
		}
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	public String index() throws Exception{
		return SUCCESS;
	}
	
	/**
	 * 查询科室
	 * @return
	 * @throws Exception
	 */
	public String findCatelog() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		dto = (YuYueFromDto) yuYueFormMgr.findYuYueCatalog(this.getOwner(),type);
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 根据科室id查询科室信息
	 * @return
	 * @throws Exception
	 */
	public String findYuYueCatalogById() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		dto = (YuYueFromDto) yuYueFormMgr.findYuYueCatalogById(catid,this.getOwner());
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	/**
	 * 根据服务人员id查询其信息
	 */
	public String findYuYueServicerById() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		dto = (YuYueFromDto) yuYueFormMgr.findYuYueServicerById(serid,this.getOwner(),catid);
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 根据科室id查询科室下的服务人员
	 * @return
	 * @throws Exception
	 */
	public String findYuYueServicerListBycatid() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		dto = (YuYueFromDto) yuYueFormMgr.findYuYueServicerListBycatid(catid,this.getOwner(),pageId,size);
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 根据科室id查询科室下的服务列表（首页）
	 * @return
	 * @throws Exception
	 */
	public String findYuYueService() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		dto = (YuYueFromDto) yuYueFormMgr.findYuYueServiceListBycatid(catid,this.getOwner(),pageId,size);
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 服务详情
	 * @return
	 * @throws Exception
	 */
	public String findYuYueServiceById() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		dto = (YuYueFromDto) yuYueFormMgr.findYuYueServiceById(catid,this.getOwner(),serviceid);
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 根据服务id查询服务人员
	 */
	public String findServicerListByService() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		dto = (YuYueFromDto) yuYueFormMgr.findServicerListByService(catid,this.getOwner(),serviceid);
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 根据服务人员id查询服务列表
	 * @return
	 * @throws Exception
	 */
	public String findServiceListBySerid() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		dto = (YuYueFromDto) yuYueFormMgr.findServiceListBySerid(catid,serid);
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	public YuYueFromDto getDto()
	{
		return dto;
	}
	
	public void setDto(YuYueFromDto dto)
	{
		this.dto = dto;
	}

	public long getCatid()
	{
		return catid;
	}

	public void setCatid(long catid)
	{
		this.catid = catid;
	}

	
	public long getSerid()
	{
		return serid;
	}

	
	public void setSerid(long serid)
	{
		this.serid = serid;
	}

	
	public long getServiceid()
	{
		return serviceid;
	}

	
	public void setServiceid(long serviceid)
	{
		this.serviceid = serviceid;
	}

	
	public int getPageId()
	{
		return pageId;
	}

	
	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	
	public int getSize()
	{
		return size;
	}

	
	public void setSize(int size)
	{
		this.size = size;
	}
	
}	
