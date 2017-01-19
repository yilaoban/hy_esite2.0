
package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.mgr.IHyUserMgr;
import com.huiyee.esite.mgr.IJfDesignMgr;
import com.huiyee.esite.model.BalanceSet;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * vip
 * 
 * @author ldw
 * 
 */
public class UserVipAction extends AbstractAction
{

	private IHyUserMgr hyUserMgr;
	private IJfDesignMgr jfDesignMgr;
	private HyUser hyUser;
	private String telphone;
	private String code;
	private String username;
	private String birthday;
	private int gender;
	private long pageid;
	private String solrtype;
	private String solrstype;

	public void setHyUserMgr(IHyUserMgr hyUserMgr)
	{
		this.hyUserMgr = hyUserMgr;
	}

	public String vipInfo() throws Exception
	{
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu.getCd() == 1)
		{
			hyUser = hyUserMgr.findHyUserBywxuid(vu.getWxuid(), this.getOwner());
		}
		return SUCCESS;
	}

	public void vipInfoAjax() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu.getCd() == 1)
		{
			hyUser = hyUserMgr.findHyUserBywxuid(vu.getWxuid(), this.getOwner());
		}
		Gson gson = new Gson();
		out.print(gson.toJson(hyUser));
		out.flush();
		out.close();
	}

	public void submit() throws Exception
	{

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		JSONObject rs = new JSONObject();
		rs.put("status", 0);
		if (vu != null && vu.getCd() == 1)
		{
			HyUser old = hyUserMgr.findHyUserBywxuid(vu.getWxuid(), this.getOwner());
			if (old.getTelphone()==null||!old.getTelphone().equals(telphone))
			{
				String sRand = (String) request.getSession().getAttribute("checkCode");
				if (sRand == null)
				{
					rs.put("msg", "验证码失效,请重新获取");
				} else if (!sRand.equalsIgnoreCase(code))
				{
					rs.put("msg", "验证码填写错误");
				} else
				{
					HyUser hyUser = new HyUser();
					hyUser.setWxuid(vu.getWxuid());
					hyUser.setTelphone(telphone);
					hyUser.setUsername(username);
					hyUser.setGender(gender);
					hyUser.setBirthday(DateUtil.getDate(birthday));
					hyUser.setOwner(this.getOwner());
					int line = hyUserMgr.updateVipInfo(hyUser);
					if (line>0)
					{
						rs.put("status", 1);
						BalanceSet balanceSet = jfDesignMgr.findBalanceSetByOwner(this.getOwner());
						long rmcid = 0;
						try
						{
							String pageset = balanceSet.getPageset();
							JSONObject jo = JSONObject.fromObject(pageset);
							rmcid = jo.getLong("rmcid");
						} catch (Exception e)
						{
							System.out.println("owner:" + this.getOwner() + "not find the rmcid from pageset:" + balanceSet.getPageset());
						}
						if (rmcid > 0)
						{
							String url = "/" + this.getOname() + "/user/wxshow/" + rmcid + "/wxn/" + pageid + ".html";
							rs.put("url", url);
						}

					}

				}

			} else
			{
				HyUser hyUser = new HyUser();
				hyUser.setWxuid(vu.getWxuid());
				hyUser.setTelphone(telphone);
				hyUser.setUsername(username);
				hyUser.setGender(gender);
				hyUser.setBirthday(DateUtil.getDate(birthday));
				hyUser.setOwner(this.getOwner());
				int line = hyUserMgr.updateVipInfo(hyUser);
				if (line == 1)
				{
					rs.put("status", 1);
					BalanceSet balanceSet = jfDesignMgr.findBalanceSetByOwner(this.getOwner());
					long rmcid = 0;
					try
					{
						String pageset = balanceSet.getPageset();
						JSONObject jo = JSONObject.fromObject(pageset);
						rmcid = jo.getLong("rmcid");
					} catch (Exception e)
					{
						System.out.println("owner:" + this.getOwner() + "not find the rmcid from pageset:" + balanceSet.getPageset());
					}
					if (rmcid > 0)
					{
						String url = "/" + this.getOname() + "/user/wxshow/" + rmcid + "/wxn/" + pageid + ".html";
						rs.put("url", url);
					}

				}
			}
		} else
		{
			out.print("需要微信环境");
		}
		out.print(rs.toString());
		out.flush();
		out.close();

	}
	public String vipRmbRecord() throws Exception{
		return SUCCESS;
	}

	public HyUser getHyUser()
	{
		return hyUser;
	}

	public void setHyUser(HyUser hyUser)
	{
		this.hyUser = hyUser;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public String getTelphone()
	{
		return telphone;
	}

	public void setTelphone(String telphone)
	{
		this.telphone = telphone;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getBirthday()
	{
		return birthday;
	}

	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}

	public int getGender()
	{
		return gender;
	}

	public void setGender(int gender)
	{
		this.gender = gender;
	}

	public void setJfDesignMgr(IJfDesignMgr jfDesignMgr)
	{
		this.jfDesignMgr = jfDesignMgr;
	}

	public String getSolrtype()
	{
		return solrtype;
	}

	public void setSolrtype(String solrtype)
	{
		this.solrtype = solrtype;
	}

	public String getSolrstype()
	{
		return solrstype;
	}

	public void setSolrstype(String solrstype)
	{
		this.solrstype = solrstype;
	}
}
