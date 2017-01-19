package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.dto.CommunityDto;
import com.huiyee.esite.mgr.ICommunityUserMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.BBSJfRule;
import com.huiyee.esite.service.Permission;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.ToolUtils;
import com.huiyee.interact.bbs.action.AbstractBBSAction;
import com.huiyee.interact.bbs.model.BBSUser;
import com.opensymphony.xwork2.ActionContext;

public class CommunityUserManagerAction extends AbstractBBSAction
{
	private static final long serialVersionUID = 141423943046431579L;
	private CommunityDto dto;
	private ICommunityUserMgr communityUserMgr;
	private int lightType = 2;
	private String entity;
	private int type;
	private int action;
	private int jifen;
	private long ruleid;
	private String level_name;
	private long require_jf;
	private long levelid;
	private int pageId = 1;
	private String nickname;
	private long uid;
	private String isbalck;
	
	private String username;
	private String password;
	private BBSUser bbsUser;
	
	public void setCommunityUserMgr(ICommunityUserMgr communityUserMgr)
	{
		this.communityUserMgr = communityUserMgr;
	}

	@Override
	public String execute() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		long owner = account.getOwner().getId();
		dto = (CommunityDto) communityUserMgr.findBBSUserJfRuleList(owner);
		return SUCCESS;
	}
	
	/**
	 * 设置积分规则
	 */
	public String addJifenRule() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		return SUCCESS;
	}
	/**
	 * 设置积分
	 * @return
	 * @throws Exception
	 */
	public String saveRule() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		long owner = account.getOwner().getId();
		int res = communityUserMgr.saveBBSUserJfRule(action,jifen,owner);
		if(res > 0){
			result = "Y";
		}
		out.write(result);
		out.flush();
		out.close();
		return null;
	}
	
	
	/**
	 * 编辑积分设置
	 * @return
	 */
	public String editJfRule() throws Exception{
		dto = (CommunityDto) communityUserMgr.findBBSUSErJfRuleById(ruleid);
		return SUCCESS;
	}
	
	/**
	 * 修改积分规则
	 * @return
	 */
	public String updateJfRule() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		int res = communityUserMgr.updateJfRuleById(ruleid,jifen);
		return SUCCESS;
	}
	
	/**
	 * 删除积分规则
	 * @return
	 * @throws Exception
	 */
	public String deleteJfRule() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		int res = communityUserMgr.deleteJfRule(ruleid);
		if(res > 0){
			result = "Y";
		}
		out.write(result);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 会员等级分配页
	 * @return
	 * @throws Exception
	 */
	public String userLevel() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		long owner = account.getOwner().getId();
		dto = (CommunityDto) communityUserMgr.findBBSUserJflevelList(owner);
		return SUCCESS;
	}
	
	/**
	 * 等级分配
	 * @return
	 * @throws Exception
	 */
	public String addLevelRule() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		return SUCCESS;
	}
	/**
	 * 保存等级设置
	 * @return
	 * @throws Exception
	 */
	public String saveLevel() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		long owner = account.getOwner().getId();
		int res = communityUserMgr.saveBBSUserJfLevel(level_name,require_jf,owner);
		if(res > 0){
			result = "Y";
		}
		out.write(result);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 编辑等级
	 * @return
	 * @throws Exception
	 */
	public String editJfLevel() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		dto = (CommunityDto) communityUserMgr.findBBSUserJfLevelById(levelid);
		return SUCCESS;
	}
	
	/**
	 * 修改等级设置
	 * @return
	 * @throws Exception
	 */
	public String updateLevel() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		long owner = account.getOwner().getId();
		int res = communityUserMgr.updateBBSUserJfLevel(level_name,require_jf,levelid,owner);
		if(res > 0){
			result = "Y";
		}
		out.write(result);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 删除积分等级
	 * @return
	 * @throws Exception
	 */
	public String deleteJfLevel() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		int res = communityUserMgr.deleteJfLevel(levelid);
		if(res > 0){
			result = "Y";
		}
		out.write(result);
		out.flush();
		out.close();
		return null;
	}
	
	@Permission(module=IPrivilegeConstants.MODULE_APP_微社区,privilege=IPrivilegeConstants.PERMISSION_R)
	public String userInfo() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		long owner = account.getOwner().getId();
		dto = (CommunityDto) communityUserMgr.findBBSUserInfoList(owner,pageId);
		return SUCCESS;
	}
	
	/**
	 * 查询用户列表
	 * @return
	 * @throws Exception
	 */
	public String userSearch() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		long owner = account.getOwner().getId();
		dto = (CommunityDto) communityUserMgr.findBBSUserInfoListByNicknameAndLevelid(owner,pageId,nickname,levelid);
		return SUCCESS;
	}
	/**
	 * 黑名单
	 * @return
	 */
	public String userBalck() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		entity = account.getOwner().getEntity();
		long owner = account.getOwner().getId();
		dto = (CommunityDto) communityUserMgr.findBBSUserBalck(owner,pageId);
		return SUCCESS;
	}
	
	/**
	 * 加入黑名单
	 * @return
	 */
	public String addUserBalck() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		int res = communityUserMgr.addUserBalck(uid,isbalck);
		if(res > 0){
			result = "Y";
		}
		out.write(result);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 添加会员
	 * @return
	 */
	public String addUser() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);
		
		int res = communityUserMgr.saveBBSUser(username, ToolUtils.getMD5Str(password), bbsUser, this.getOwner(), ip);
		out.print(res);
		out.flush();
		out.close();
		return null;
	}
	
	public String addUserPage() throws Exception{
		return SUCCESS;
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

	public String getEntity()
	{
		return entity;
	}

	public void setEntity(String entity)
	{
		this.entity = entity;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public int getAction()
	{
		return action;
	}

	public void setAction(int action)
	{
		this.action = action;
	}

	public int getJifen()
	{
		return jifen;
	}

	public void setJifen(int jifen)
	{
		this.jifen = jifen;
	}

	public long getRuleid()
	{
		return ruleid;
	}

	public void setRuleid(long ruleid)
	{
		this.ruleid = ruleid;
	}

	public String getLevel_name()
	{
		return level_name;
	}

	public void setLevel_name(String level_name)
	{
		this.level_name = level_name;
	}

	public long getRequire_jf()
	{
		return require_jf;
	}

	public void setRequire_jf(long require_jf)
	{
		this.require_jf = require_jf;
	}

	public long getLevelid()
	{
		return levelid;
	}

	public void setLevelid(long levelid)
	{
		this.levelid = levelid;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public long getUid()
	{
		return uid;
	}

	public void setUid(long uid)
	{
		this.uid = uid;
	}

	public String getIsbalck() {
		return isbalck;
	}

	public void setIsbalck(String isbalck) {
		this.isbalck = isbalck;
	}

	
	public String getUsername()
	{
		return username;
	}

	
	public void setUsername(String username)
	{
		this.username = username;
	}

	
	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public BBSUser getBbsUser()
	{
		return bbsUser;
	}

	public void setBbsUser(BBSUser bbsUser)
	{
		this.bbsUser = bbsUser;
	}
	
}
