
package com.huiyee.interact.xc.mgr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.huiyee.esite.dao.IWxUserDao;
import com.huiyee.esite.model.SinaUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.interact.xc.dao.ISigninDao;
import com.huiyee.interact.xc.dao.IXcDao;
import com.huiyee.interact.xc.dao.InteractCommentDao;
import com.huiyee.interact.xc.model.Xc;
import com.huiyee.interact.xc.model.XcCommentRecord;
import com.huiyee.interact.xc.model.XcSendRecord;

public class InteractCommentMgrImpl implements InteractCommentMgr
{

	private InteractCommentDao commentDao;
	private IXcDao xcDao;
	private ISigninDao signinDao;
	private IWxUserDao wxUserDao;

	public void setWxUserDao(IWxUserDao wxUserDao)
	{
		this.wxUserDao = wxUserDao;
	}

	public IXcDao getXcDao()
	{
		return xcDao;
	}

	public void setXcDao(IXcDao xcDao)
	{
		this.xcDao = xcDao;
	}

	public ISigninDao getSigninDao()
	{
		return signinDao;
	}

	public void setSigninDao(ISigninDao signinDao)
	{
		this.signinDao = signinDao;
	}

	public InteractCommentDao getCommentDao()
	{
		return commentDao;
	}

	public void setCommentDao(InteractCommentDao commentDao)
	{
		this.commentDao = commentDao;
	}

	@Override
	public int saveComment(XcCommentRecord xcRecord)
	{
		return commentDao.saveComment(xcRecord);
	}

	@Override
	public List<XcCommentRecord> findCommentRecordList(long xcid, long pageid, long start, int size)
	{
		List<XcCommentRecord> list = new ArrayList<XcCommentRecord>();
		Xc xc = xcDao.getXcById(xcid);
		if ("Y".equals(xc.getNeedcomment()) && xc.getCommentconfig() != null)
		{
			if (xc.getCommentconfig().getChecked().equalsIgnoreCase("Y"))
			{
				list = commentDao.findCommentRecord1(xcid, start, size);
			} else
			{
				list = commentDao.findCommentRecord(xcid, start, size);
			}
		} else
		{
			// 搜所有评论 包括FLD的
			list = commentDao.findCommentRecord(xcid, start, size);
		}

		for (XcCommentRecord r : list)
		{
			XcSendRecord sd = signinDao.findsdRecord(r.getUid(), r.getUtype(), r.getXcid());
			if (r.getUtype() == 1)
			{
				WxUser u = commentDao.findWxUser(r.getUid());
				if (u != null)
				{
					if (sd == null || sd.getUsername() == null || "".equals(sd.getUsername()))
					{
						r.setNickname(u.getNickname());
					} else
					{
						r.setNickname(sd.getUsername());
					}
					if (StringUtils.isEmpty(u.getHeadimgurl()))
					{
						r.setHeadimgurl("http://img.huiyee.com/touxiang.png");
					} else
					{
						r.setHeadimgurl(u.getHeadimgurl());
					}
				}
			}
			if (r.getUtype() == 0)
			{
				SinaUser u = commentDao.findSinaUser(r.getUid());
				if (u != null)
				{
					if (sd == null || sd.getUsername() == null || "".equals(sd.getUsername()))
					{
						r.setNickname(u.getNickname());
					} else
					{
						r.setNickname(sd.getUsername());
					}
					if (StringUtils.isEmpty(u.getImageurl()))
					{
						r.setHeadimgurl("http://img.huiyee.com/touxiang.png");
					}
					r.setHeadimgurl(u.getImageurl());
				}
			}
			if (StringUtils.isEmpty(r.getHeadimgurl()))
			{
				r.setHeadimgurl("http://img.huiyee.com/touxiang.png");
			}
			if (StringUtils.isEmpty(r.getNickname()))
			{
				r.setNickname("匿名");
			}
		}
		return list;
	}

	@Override
	public List<XcCommentRecord> findCommentRecord(long xcid, long start, int size)
	{
		List<XcCommentRecord> list = commentDao.findCommentRecord(xcid, start, size);
		for (XcCommentRecord r : list)
		{
			XcSendRecord sd = signinDao.findsdRecord(r.getUid(), r.getUtype(), r.getXcid());
			if (r.getUtype() == 1)
			{
				WxUser u = commentDao.findWxUser(r.getUid());
				if (u != null)
				{
					if (sd == null || sd.getUsername() == null || "".equals(sd.getUsername()))
					{
						r.setNickname(u.getNickname());
					} else
					{
						r.setNickname(sd.getUsername());
					}
					r.setHeadimgurl(u.getHeadimgurl());
				}
			}
			if (r.getUtype() == 0)
			{
				SinaUser u = commentDao.findSinaUser(r.getUid());
				if (u != null)
				{
					if (sd == null || sd.getUsername() == null || "".equals(sd.getUsername()))
					{
						r.setNickname(u.getNickname());
					} else
					{
						r.setNickname(sd.getUsername());
					}
					r.setHeadimgurl(u.getImageurl());
				}
			}
		}
		return list;
	}

	@Override
	public List<XcCommentRecord> findCommentRecord1(long xcid, long start, int size)
	{
		List<XcCommentRecord> list = commentDao.findCommentRecord1(xcid, start, size);
		for (XcCommentRecord r : list)
		{
			XcSendRecord sd = signinDao.findsdRecord(r.getUid(), r.getUtype(), r.getXcid());
			if (r.getUtype() == 1)
			{
				WxUser u = commentDao.findWxUser(r.getUid());
				if (u != null)
				{
					if (sd == null || sd.getUsername() == null || "".equals(sd.getUsername()))
					{
						r.setNickname(u.getNickname());
					} else
					{
						r.setNickname(sd.getUsername());
					}
					r.setHeadimgurl(u.getHeadimgurl());
				}
			}
			if (r.getUtype() == 0)
			{
				SinaUser u = commentDao.findSinaUser(r.getUid());
				if (u != null)
				{
					if (sd == null || sd.getUsername() == null || "".equals(sd.getUsername()))
					{
						r.setNickname(u.getNickname());
					} else
					{
						r.setNickname(sd.getUsername());
					}
					r.setHeadimgurl(u.getImageurl());
				}
			}
		}
		return list;
	}

	@Override
	public int findCommenterTotal(long xcid)
	{
		return commentDao.findCommenterTotal(xcid);
	}

	@Override
	public int findCommenterTotal1(long xcid)
	{
		return commentDao.findCommenterTotal1(xcid);
	}

	@Override
	public int findUserCommentNum(long uid, int utype, long xcid)
	{
		return commentDao.findUserCommentNum(uid, utype, xcid);
	}

	@Override
	/**
	 * 银票微现场使用 从吐槽的人中取出size个
	 */
	public List<XcCommentRecord> findCommentWinner(int size)
	{
		List<Long> wxids = commentDao.findCommentUser(size);
		List<XcCommentRecord> list = new ArrayList<XcCommentRecord>();
		System.out.println("评论抽奖start====="+DateUtil.getDateTimeString(new Date()));
		for (Long wxuid : wxids)
		{
			XcCommentRecord record = new XcCommentRecord();
			record = commentDao.findLastComment(wxuid);
			if (record != null)
			{
				WxUser user = wxUserDao.findWxUserByid(wxuid);
				if (user != null)
				{
					record.setNickname(user.getNickname());
					record.setHeadimgurl(user.getHeadimgurl());
					list.add(record);
					System.out.print(user.getNickname()+"  ");
				}
			}
		}
		System.out.println();
		System.out.println("评论抽奖end====="+DateUtil.getDateTimeString(new Date()));
		return list;
	}
}
