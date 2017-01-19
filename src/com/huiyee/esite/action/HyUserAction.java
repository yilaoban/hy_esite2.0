
package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.HuDetail;
import com.huiyee.esite.mgr.IHyUserMgr;
import com.huiyee.esite.mgr.IWeiXinMgr;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.ToolUtils;
import com.huiyee.interact.bbs.action.SMSSender;
import com.huiyee.interact.bbs.model.SMS;
import com.opensymphony.xwork2.ActionContext;

public class HyUserAction extends AbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jspname;
	private long pageid;
	private long pid;
	private IHyUserMgr hyUserMgr;
	private IWeiXinMgr weiXinMgr;
	private String username;
	private String password;
	private String telphone;
	private String code;
	private HuDetail huDetail;
	private String email;
	private String nickname;
	private String img;

	public String toLogin() throws Exception
	{
		//如果没有visituser，过不来，直接filter过滤了
		jspname = "/WEB-INF/interactpage/bbs/bbslogin.jsp";
		Sitegroup sg = this.pageCompose.findSitegroupByPageid(pid);
		if (sg != null && sg.getLoginpage() > 0)
		{
			pageid = sg.getLoginpage();
			return "rs";
		}
		return SUCCESS;
	}

	public String login() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = 0;// 0 登录失败 1登录成功
		String ip = ClientUserIp.getIpAddr(request);
		if (username == null || password == null)
		{
			return null;
		}
		HyUser hu = this.hyUserMgr.login(username, ToolUtils.getMD5Str(password), this.getOwner());
		if (hu != null)
		{
			result = 1;
			VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
			if (vu == null)
			{
				VisitUser vux = new VisitUser();
				vux.setOname(this.getOname());
				vux.setHyUser(hu);
				vux.setCd(2);
				ActionContext.getContext().getSession().put("visitUser", vux);
			} else
			{
				if (hu.getWxuid() > 0)
				{
					WxUser wxUser = weiXinMgr.getWxUserById(hu.getWxuid());
					vu.setWxuid(wxUser.getId());
					vu.setWxUser(wxUser);
				} else if (vu.getWxuid() > 0)
				{
					this.hyUserMgr.updateHyUserWxuidById(vu.getWxuid(), hu.getId());
				}
				vu.setHyUser(hu);
				if (vu.getCd() == -1)
				{
					vu.setCd(2);
				}
			}
		}
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	public String register() throws Exception
	{
		//如果没有visituser，过不来，直接filter过滤了
		jspname = "/WEB-INF/interactpage/bbs/bbslogin.jsp";
		Sitegroup sg = this.pageCompose.findSitegroupByPageid(pid);
		if (sg != null && sg.getLoginpage() > 0)
		{
			pageid = sg.getRegistpage();
			return "rs";
		}
		return SUCCESS;
	}

	public String doRegister() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		String sRand = (String) request.getSession().getAttribute("checkCode");
		if (vu != null)
		{
			if (sRand != null)
			{
				if (sRand.equalsIgnoreCase(code))
				{
					ActionContext.getContext().getSession().put("checkCode", null);
					String ip = ClientUserIp.getIpAddr(request);
					HyUser hu = this.hyUserMgr.saveHyUser(username,ToolUtils.getMD5Str(password),telphone,email,nickname,huDetail,img,this.getOwner(),ip,vu);
					if (hu != null)
					{
						vu.setHyUser(hu);
						if (vu.getCd() == -1)
						{
							vu.setCd(2);
						}
						out.print("Y");
					} else
					{
						out.print("N");
					}
				} else
				{
					out.print("验证码填写错误");
				}
			} else
			{
				out.print("验证码失效,请重新获取");
			}
		} else
		{
			out.print("请获取验证码");
		}
		out.flush();
		out.close();
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

	public String getCode() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		// out.print(result);
		String code = get();
		String message = "尊敬的用户，你的验证码是:" + code + "，请查收！【会易科技】";
		SMS sms = this.hyUserMgr.findPPSmsbyOwner(this.getOwner());
		String r = "0";
		if (sms != null)
		{
			if(StringUtils.isNotBlank(sms.getHydesc())){
				String hydesc = sms.getHydesc();
				message = hydesc.replace("${code}", code);
			}
			r = new SMSSender(sms.getUsername(), sms.getPassword(), (int) sms.getProductid()).sendSms(telphone, message);
		}
		// String r = new SMSSender().sendSms(bbsUser.getTelphone(), message);
		String[] arr = r.trim().split(",");
		String status = arr[0];
		if (Integer.valueOf(status) == 1)
		{// 发送成功
			ActionContext.getContext().getSession().put("checkCode", code);
			out.print("Y");
		} else if (Integer.valueOf(status) == 2)
		{
			out.print("获取验证码失败，请联系客服！");
		} else
		{
			out.print("N");
		}
		out.flush();
		out.close();
		return null;
	}

	public String get()
	{
		String str = "";
		Random rd = new Random();
		for (int i = 0; i < 6; i++)
		{
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
			//if (vu.getBbsUser() != null)
//			{
//				BbsUserLoginTime time = new BbsUserLoginTime();
//				time.setTime(new Date().getTime() - vu.getBbsUser().getLogintime());
//				time.setUid(vu.getBbsUser().getId());
//				time.setLogid(vu.getBbsUser().getLogid());
//				CacheUtil.addBbs(time);
//
//				BBSUserOnline online = bbsUserMgr.findBBSUserOnlineById(vu.getBbsUser().getId());
//				BBSUserActiveLevel level = bbsUserMgr.findBBSUseActiveLevel(vu.getBbsUser().getId());
//				if (level != null && online != null)
//				{
//					if (level.getRequired_hour() < online.getOnline_total())
//					{
//						// System.out.println("该提升等级了");
//						bbsUserMgr.updateBBSUserLevlebyId(vu.getBbsUser().getId());
//					}
//				}
//			}
			vu.setHyUser(null);
		}
		PrintWriter pw = response.getWriter();
		pw.print("Y");
		pw.flush();
		pw.close();
		return null;
	}

	
	public String getJspname()
	{
		return jspname;
	}

	
	public void setJspname(String jspname)
	{
		this.jspname = jspname;
	}

	
	public long getPageid()
	{
		return pageid;
	}

	
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	
	public long getPid()
	{
		return pid;
	}

	
	public void setPid(long pid)
	{
		this.pid = pid;
	}
	
	public void setHyUserMgr(IHyUserMgr hyUserMgr)
	{
		this.hyUserMgr = hyUserMgr;
	}

	
	public void setWeiXinMgr(IWeiXinMgr weiXinMgr)
	{
		this.weiXinMgr = weiXinMgr;
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

	
	public String getTelphone()
	{
		return telphone;
	}

	
	public void setTelphone(String telphone)
	{
		this.telphone = telphone;
	}

	
	public HuDetail getHuDetail()
	{
		return huDetail;
	}

	
	public void setHuDetail(HuDetail huDetail)
	{
		this.huDetail = huDetail;
	}

	
	public String getEmail()
	{
		return email;
	}

	
	public void setEmail(String email)
	{
		this.email = email;
	}

	
	public String getNickname()
	{
		return nickname;
	}

	
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	
	public String getImg()
	{
		return img;
	}

	
	public void setImg(String img)
	{
		this.img = img;
	}

	
	public void setCode(String code)
	{
		this.code = code;
	}

	
}
