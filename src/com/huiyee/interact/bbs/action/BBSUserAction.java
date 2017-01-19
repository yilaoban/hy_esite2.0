package com.huiyee.interact.bbs.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.BbsUserLoginTime;
import com.huiyee.esite.mgr.IWeiXinMgr;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.CacheUtil;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.ToolUtils;
import com.huiyee.interact.bbs.mgr.IBBSUserMgr;
import com.huiyee.interact.bbs.model.BBSUser;
import com.huiyee.interact.bbs.model.BBSUserActiveLevel;
import com.huiyee.interact.bbs.model.BBSUserOnline;
import com.huiyee.interact.bbs.model.SMS;
import com.opensymphony.xwork2.ActionContext;

public class BBSUserAction extends AbstractBBSAction
{

	private static final long serialVersionUID = -6689784831592797521L;
	private BBSUser bbsUser;
	private String source;
	
	public String getSource()
	{
		return source;
	}

	
	public void setSource(String source)
	{
		this.source = source;
	}

//	private IBBSUserMgr bbsUserMgr;
	private IWeiXinMgr weiXinMgr;
	private String username;
	private String password;
	private long cateid;
	private String code;
	private String jspname;
	private String bbserr;
	private int regtype; //注册方式  0：手机验证 1：图片验证码验证
	private long pageid;
	private long pid;
	
	public void setPid(long pid)
	{
		this.pid = pid;
	}


	public long getPageid()
	{
		return pageid;
	}
	
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public String toLogin() throws Exception{
		jspname="/WEB-INF/interactpage/bbs/bbslogin.jsp";
		if(this.getForumid() > 0){
			Page	pa = bbsUserMgr.findJspnameByForumid(this.getForumid());
			if(pa!=null&&pa.getId()>0){
				pageid=pa.getId();
				return "rs";
			}
		}
		return SUCCESS;
	}
	
	public String login() throws Exception
	{
//		HttpServletRequest request = ServletActionContext.getRequest();
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("application/html;charset=utf-8");
//		response.setHeader("Cache-Control", "no-cache");
//		PrintWriter out = response.getWriter();
//		int result = 0;// 0 登录失败 1登录成功
//		String ip = ClientUserIp.getIpAddr(request);
//		if(username==null || password==null)
//		{
//			return null;
//		}
//		bbsUser = this.hyUserMgr.loginBBSUser(username, ToolUtils.getMD5Str(password), this.getOwner(), ip);
//		if (bbsUser != null)
//		{
//			result = 1;
//			VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
//			if (vu == null)
//			{
//				VisitUser vux = new VisitUser();
//				vux.setOname(this.getOname());
//				HyUser hyUser = this.hyUserMgr.findHyUserById(bbsUser.getHyuserid());
//				vux.setBbsUser(bbsUser);
//				vux.setHyUser(hyUser);
//				vux.setCd(2);
//				
//				ActionContext.getContext().getSession().put("visitUser", vux);
//			}
//			else if (vu != null)
//			{
//				HyUser hyUser = this.hyUserMgr.findHyUserById(bbsUser.getHyuserid());
//				if(hyUser != null){
//					if(hyUser.getWbuid() !=0 && vu.getWbuid() > 0){
//						this.hyUserMgr.updateHyUserWbuidById(vu.getWbuid(),bbsUser.getHyuserid());
//					}
//					if(hyUser.getWxuid() !=0 && vu.getWxuid() > 0){
//						this.hyUserMgr.updateHyUserWxuidById(vu.getWxuid(),bbsUser.getHyuserid());
//						WxUser wxUser = weiXinMgr.getWxUserById(vu.getWxuid());
//						vu.setWxuid(wxUser.getId());
//						vu.setWxUser(wxUser);
//					}
//				}
//				vu.setBbsUser(bbsUser);
//				vu.setHyUser(hyUser);
//				if(vu.getCd()==-1)
//				{
//				vu.setCd(2);
//				}
//			}
//		}
//		out.print(result);
//		out.flush();
//		out.close();
		return null;
	}

	/**
	 * 注册
	 * 
	 * @return
	 * @throws Exception
	 */
	public String register() throws Exception
	{
		jspname="/WEB-INF/interactpage/bbs/bbsregister.jsp";
		if(this.getForumid() > 0){
			  Page	pa = bbsUserMgr.findRegJspnameByForumid(this.getForumid());;
				if(pa!=null&&pa.getId()>0){
					pageid=pa.getId();
					return "rs";
				}
			}
		return SUCCESS;
	}

	public String doRegister() throws Exception
	{
//		HttpServletRequest request = ServletActionContext.getRequest();
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("application/html;charset=utf-8");
//		response.setHeader("Cache-Control", "no-cache");
//		PrintWriter out = response.getWriter();
//		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
//		if(regtype == 1){//判断是否是图片验证码
//			String sRand = (String) request.getSession().getAttribute("checkCode");
//			if (vu == null){
//				VisitUser vux = new VisitUser();
//				vux.setOname(this.getOname());
//				vux.setCode(sRand);
//				ActionContext.getContext().getSession().put("visitUser", vux);
//			}else{
//				vu.setCode(sRand);
//			}
//		}
//		vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
//		if(vu != null){
//			if(vu.getCode() != null){
//				if(vu.getCode().equalsIgnoreCase(code)){
//					vu.setCode(null);
//					String ip = ClientUserIp.getIpAddr(request);
//					bbsUser = this.hyUserMgr.saveBBSUser(username, ToolUtils.getMD5Str(password), bbsUser, this.getOwner(), ip,vu);
//					if (bbsUser != null){
//						HyUser hyUser = new HyUser();
//						hyUser.setId(bbsUser.getHyuserid());
//						hyUser.setUsername(username);
//						hyUser.setPassword(ToolUtils.getMD5Str(password));
//						hyUser.setOwner(this.getOwner());
//						vu.setBbsUser(bbsUser);
//						vu.setHyUser(hyUser);
//						if(vu.getCd()==-1)
//						{
//						  vu.setCd(2);
//						}
//						out.print("Y");
//					}else{
//						out.print("N");
//					}
//				} else{
//					out.print("验证码填写错误");
//				}
//			}else{
//				out.print("验证码失效,请重新获取");
//			}
//		}else{
//			out.print("请获取验证码");
//		}
//		out.flush();
//		out.close();
		return null;
	}

	public String findBBSUserByName() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = this.hyUserMgr.findBBSUserByName(username, this.getOwner());
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	public String getCode() throws Exception{
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("application/html;charset=utf-8");
//		response.setHeader("Cache-Control", "no-cache");
//		PrintWriter out = response.getWriter();
////		out.print(result);
//		String code = get();
//		String message = "尊敬的用户，你的注册验证码是:"+code+"，请查收！【会易科技】";
//		SMS sms = this.hyUserMgr.findPPSmsbyOwner(this.getOwner());
//		String r = "0";
//		if(sms != null){
//			r = new SMSSender(sms.getUsername(),sms.getPassword(),(int)sms.getProductid()).sendSms(bbsUser.getTelphone(), message);
//		}
////		String r = new SMSSender().sendSms(bbsUser.getTelphone(), message);
//		String[] arr = r.trim().split(",");
//		String status = arr[0];
//		if(Integer.valueOf(status) == 1){//发送成功
//			VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
//			if (vu == null)
//			{
//				VisitUser vux = new VisitUser();
//				vux.setOname(this.getOname());
//				vux.setCode(code);
//				ActionContext.getContext().getSession().put("visitUser", vux);
//			}else
//			{
//				vu.setCode(code);
//			}
//			out.print("Y");
//		}else if(Integer.valueOf(status) == 2){
//			out.print("获取验证码失败，请联系客服！");
//		}else{
//			out.print("N");
//		}
//		out.flush();
//		out.close();
		return null;
	}

	public String get(){
		String str = "";
		Random rd = new Random();
		for (int i = 0; i < 6; i++){
			int itmp = rd.nextInt(10);
			str += Integer.valueOf(itmp);
		}
		return str;
	}

	/**
	 * 退出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String bbsexit() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");

		if (!HyConfig.isRun())
		{
			VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
			if (vu != null && vu.getBbsUser() != null)
			{   
				BbsUserLoginTime time = new BbsUserLoginTime();
				time.setTime(new Date().getTime() - vu.getBbsUser().getLogintime());
				time.setUid(vu.getBbsUser().getId());
				time.setLogid(vu.getBbsUser().getLogid());
				CacheUtil.addBbs(time);

				BBSUserOnline online = bbsUserMgr.findBBSUserOnlineById(vu.getBbsUser().getId());
				BBSUserActiveLevel level = bbsUserMgr.findBBSUseActiveLevel(vu.getBbsUser().getId());
				if (level != null && online != null)
				{
					if (level.getRequired_hour() < online.getOnline_total())
					{
						// System.out.println("该提升等级了");
						bbsUserMgr.updateBBSUserLevlebyId(vu.getBbsUser().getId());
					}
				}
			}
			vu.setBbsUser(null);
			vu.setHyUser(null);
		}
		PrintWriter pw = response.getWriter();
		pw.print("Y");
		pw.flush();
		pw.close();
		return null;
	}

	public BBSUser getBbsUser()
	{
		return bbsUser;
	}

	public void setBbsUser(BBSUser bbsUser)
	{
		this.bbsUser = bbsUser;
	}

	public IBBSUserMgr getBbsUserMgr()
	{
		return bbsUserMgr;
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

	public long getCateid()
	{
		return cateid;
	}

	public void setCateid(long cateid)
	{
		this.cateid = cateid;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public void setWeiXinMgr(IWeiXinMgr weiXinMgr)
	{
		this.weiXinMgr = weiXinMgr;
	}

	public String getJspname() {
		return jspname;
	}

	public void setJspname(String jspname) {
		this.jspname = jspname;
	}

	public String getBbserr() {
		return bbserr;
	}

	public void setBbserr(String bbserr) {
		this.bbserr = bbserr;
	}

	public int getRegtype() {
		return regtype;
	}

	public void setRegtype(int regtype) {
		this.regtype = regtype;
	}

}
