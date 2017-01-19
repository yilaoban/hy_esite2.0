package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.compose.IPageCompose;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.dto.CommunityDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.BBSForum;
import com.huiyee.esite.service.Permission;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.interact.bbs.action.AbstractBBSAction;
import com.opensymphony.xwork2.ActionContext;

public class CommunityManagerAction extends AbstractBBSAction
{
	private static final long serialVersionUID = 4006723993094569082L;
	private CommunityDto dto;
	private IPageCompose pageCompose;
	private int lightType = 1;
	private int type = 1;
	private String entity;
	private List<Integer> catList;
	private BBSForum forum;
	private int pageId = 1;
	private String title;
	private long cateid;
	private String cateType;
	
	@Override
	@Permission(module=IPrivilegeConstants.MODULE_APP_微社区,privilege=IPrivilegeConstants.PERMISSION_R)
	public String execute() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		long ownerid = account.getOwner().getId();
		entity = account.getOwner().getEntity();
		dto = (CommunityDto) pageCompose.findForumByOwnerid(ownerid,pageId,account.getId());
		return SUCCESS;
	}
	
	/**
	 * 开启社区
	 * @return
	 * @throws Exception
	 */
	@Permission(module=IPrivilegeConstants.MODULE_APP_微社区,privilege=IPrivilegeConstants.PERMISSION_R_and_X)
	public String createCategory() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "";
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		long ownerid = account.getOwner().getId();
		result = pageCompose.saveBBSCategoryByOwner(ownerid);
		out.print(result);
		out.flush();
		out.close();
		return null;
		
	}

	/**
	 * 创建高级板块
	 * @return
	 * @throws Exception
	 */
	public String createHignFroum() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		long ownerid = account.getOwner().getId();
		entity = account.getOwner().getEntity();
		dto = (CommunityDto) pageCompose.findForumByOwnerid(ownerid,pageId,account.getId());
		return SUCCESS;
	}
	
	/**
	 * 保存高级板块
	 * @return
	 * @throws Exception
	 */
	public String saveForum() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "";
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		long ownerid = account.getOwner().getId();
		result = pageCompose.saveForumNew(title, cateType,ownerid,account.getId());
		out.print(result);
		out.flush();
		out.close();
		return null;
		
	}
	
	/**
	 * 创建板块
	 * @return
	 * @throws Exception
	 */
	public String createForum() throws Exception{
		/*Account account = (Account) ActionContext.getContext().getSession().get("account");
		long ownerid = account.getOwner().getId();
		dto = (CommunityDto) pageCompose.findForumByOwnerid(ownerid);*/
		return SUCCESS;
	}
	
	/**
	 * 保存普通板块
	 */
	
	public String saveNormalForum() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		int res = pageCompose.saveNormalForum(forum);
		return SUCCESS;
	}
	
	/**
	 * 删除板块
	 */
	public String delforum() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		int res = pageCompose.delforum(this.getForumid());
		if(res > 0){
			result = "Y";
		}
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 修改板块名称
	 * @return
	 * @throws Exception
	 */
	public String updateForumName() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		long ownerid = account.getOwner().getId();
		int res = pageCompose.updateForumName(forum,ownerid,cateid);
		if(res > 0){
			result = "Y";
		}
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	public String baseSetting() throws Exception{
		dto = (CommunityDto) pageCompose.findBBSForumById(this.getForumid());
		return SUCCESS;
	}
	
	/**
	 * 话题管理   判断是否进行过基础设置
	 * @return
	 * @throws Exception
	 */
	public String topicManager() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		dto = (CommunityDto) pageCompose.findBBSForumById(this.getForumid());
		if(dto.getBbsForum().getForumer() == 0){
			result = "Y";
		}else{
			result = dto.getBbsForum().getForumer() + "";
		}
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 保存基础设置
	 * @return
	 * @throws Exception
	 */
	public String saveBaseSetting() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
        String ip = ClientUserIp.getIpAddr(request);
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		long ownerid = account.getOwner().getId();
		int res = pageCompose.updateBBSForumByForumid(forum,ip,ownerid); //插入bbsUsr记录 并保存设置
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(res));
		out.flush();
		out.close();
		return null;
	}
	
	public String permissionSetting() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		long ownerid = account.getOwner().getId();
		dto = (CommunityDto) pageCompose.findBBSForumByIdAndOwnerid(this.getForumid(),ownerid);
		return SUCCESS;
	}

	/**
	 * 保存权限设置
	 */
	public String savePermissionSetting() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		int res = pageCompose.updateBBSForumPermissionByForumid(forum);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(res));
		out.flush();
		out.close();
		return null;
	}
	
	public CommunityDto getDto()
	{
		return dto;
	}

	public void setDto(CommunityDto dto)
	{
		this.dto = dto;
	}

	public int getLightType()
	{
		return lightType;
	}

	public void setLightType(int lightType)
	{
		this.lightType = lightType;
	}


	public int getType()
	{
		return type;
	}


	public void setType(int type)
	{
		this.type = type;
	}

	public void setPageCompose(IPageCompose pageCompose)
	{
		this.pageCompose = pageCompose;
	}

	public String getEntity()
	{
		return entity;
	}

	public void setEntity(String entity)
	{
		this.entity = entity;
	}

	public List<Integer> getCatList()
	{
		return catList;
	}

	public void setCatList(List<Integer> catList)
	{
		this.catList = catList;
	}

	public BBSForum getForum()
	{
		return forum;
	}

	public void setForum(BBSForum forum)
	{
		this.forum = forum;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getCateid() {
		return cateid;
	}

	public void setCateid(long cateid) {
		this.cateid = cateid;
	}

	
	public String getCateType()
	{
		return cateType;
	}

	
	public void setCateType(String cateType)
	{
		this.cateType = cateType;
	}
	
}
