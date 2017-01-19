
package com.huiyee.interact.bbs.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.interact.bbs.model.BBS;
import com.huiyee.interact.bbs.model.BBSUser;
import com.opensymphony.xwork2.ActionContext;

public abstract class AbstractBBSUserAction extends AbstractBBSAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean beforeExcute() throws Exception
	{
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);
		System.out.println("过来评论：fid:" + this.getForumid() + "cd:" + vu.getCd() + ",uid+" + vu.getUid());
		BBS bs = this.bbsMgr.findBBS(this.getForumid());
		if (bs == null)
		{
			this.setBbserr("err:没找到版块,请检查链接地址");
			return false;
		}

		if (vu.getCd() == 1)// 微信环境
		{
			if (bs.getVisittype() == 1)
			{
				if (vu.getWxUser() == null)
				{
					this.setBbserr("err:请把页面发布到微信");
					return false;
				} else if (vu.getBbsUser() == null)
				{
					BBSUser bsu = this.bbsUserMgr.findBBSUserByHyuid(vu.getHyUser().getId());
					if (bsu == null)
					{
						bsu = this.bbsUserMgr.saveBBSUser(vu.getHyUser().getId(), this.getOwner(), ip);
					}
					vu.setBbsUser(bsu);
					return true;
				}
			} else if (bs.getVisittype() == 2)
			{
				if (vu.getWxUser() == null)
				{
					this.setBbserr("err:请把页面发布到微信");
					return false;
				} else if (vu.getWxUser().getSubscribe() <= 0)
				{
					this.setBbserr("err:请您先关注" + vu.getWxUser().getAppnickname());
					return false;
				} else if (vu.getBbsUser() == null)
				{
					BBSUser bsu = this.bbsUserMgr.findBBSUserByHyuid(vu.getHyUser().getId());
					if (bsu == null)
					{
						bsu = this.bbsUserMgr.saveBBSUser(vu.getHyUser().getId(), this.getOwner(), ip);
					}
					vu.setBbsUser(bsu);
					return true;
				}
			} else if (bs.getVisittype() == 3)
			{
				if (vu.getHyUser() == null || vu.getHyUser().getUsername() == null || vu.getHyUser().getPassword() == null)
				{
					String rdu = this.getOnameUrl() + "/user/bbs/toLogin.action?forumid=" + this.getForumid();
					this.setBbserr("redirect:" + rdu);
					return false;
				} else if (vu.getBbsUser() == null)
				{
					BBSUser bsu = this.bbsUserMgr.findBBSUserByHyuid(vu.getHyUser().getId());
					if (bsu == null)
					{
						bsu = this.bbsUserMgr.saveBBSUser(vu.getHyUser().getId(), this.getOwner(), ip);
					}
					vu.setBbsUser(bsu);
					return true;
				}
			}
		} else
		// 其他环境
		{
			if (bs.getOvisittype() == 1)
			{
				if (vu.getHyUser() == null || vu.getHyUser().getUsername() == null || vu.getHyUser().getPassword() == null)
				{
					String rdu = this.getOnameUrl() + "/user/bbs/toLogin.action?forumid=" + this.getForumid();
					this.setBbserr("redirect:" + rdu);
					return false;
				} else if (vu.getBbsUser() == null)
				{
					BBSUser bsu = this.bbsUserMgr.findBBSUserByHyuid(vu.getHyUser().getId());
					if (bsu == null)
					{
						bsu = this.bbsUserMgr.saveBBSUser(vu.getHyUser().getId(), this.getOwner(), ip);
					}
					vu.setBbsUser(bsu);
					return true;
				}
			} else if (bs.getOvisittype() == 2)
			{
				BBSUser bu = new BBSUser();
				bu.setOwner(this.getOwner());
				HyUser hu = new HyUser();
				hu.setNickname("匿名");
				vu.setHyUser(hu);
				vu.setBbsUser(bu);
				return true;
			}
		}

		if (vu.getBbsUser() == null)
		{
			this.setBbserr("请检查权限设置!");
			return false;
		}

		if (vu.getBbsUser().getOwner() != this.getOwner())
		{
			this.setBbserr("请不要串改网页地址！");
			return false;
		}

		return true;
	}

}
