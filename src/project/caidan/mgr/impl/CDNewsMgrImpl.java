
package project.caidan.mgr.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import project.caidan.dao.ICaiDanNewsDao;
import project.caidan.mgr.ICaiDanNewsMgr;
import project.caidan.model.CDNews;
import project.caidan.model.CDNewsDto;

import com.google.gson.Gson;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IContentCategoryDao;
import com.huiyee.esite.dao.IContentNewDao;
import com.huiyee.esite.dao.IUserTagDao;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.UserPkvTag;

public class CDNewsMgrImpl implements ICaiDanNewsMgr
{

	private ICaiDanNewsDao newsDao;
	private IContentNewDao contentNewDao;
	private IUserTagDao userTagDao;
	private IContentCategoryDao contentCategoryDao;

	public void setContentNewDao(IContentNewDao contentNewDao)
	{
		this.contentNewDao = contentNewDao;
	}

	public void setUserTagDao(IUserTagDao userTagDao)
	{
		this.userTagDao = userTagDao;
	}

	public void setNewsDao(ICaiDanNewsDao newsDao)
	{
		this.newsDao = newsDao;
	}

	public void setContentCategoryDao(IContentCategoryDao contentCategoryDao)
	{
		this.contentCategoryDao = contentCategoryDao;
	}

	@Override
	public IDto findList(Account account, String accountType, int pageId)
	{
		CDNewsDto dto = new CDNewsDto();
		long owner = account.getOwner().getId();
		int total = newsDao.findTotal(owner, accountType);
		if (total > 0)
		{
			List<CDNews> list = newsDao.findList(owner, accountType, (pageId - 1) * 20, 20);
			dto.setList(list);
			dto.setPager(new Pager(pageId, total, 20));
		}
		return dto;
	}

	@Override
	public long saveNews(ContentNew news, JSONObject tags, Account account, String accountType)
	{
		long ownerid = account.getOwner().getId();
		long catid = 0;
		ContentCategory cc = contentCategoryDao.findByOwner(ownerid, IPageConstants.CONTENT_NEW);
		if (cc == null)
		{
			ContentCategory simple = new ContentCategory();
			simple.setOwnerid(ownerid);
			simple.setName("菜单目录");
			simple.setType(IPageConstants.CONTENT_NEW);
			catid = contentCategoryDao.addCategory(ownerid, simple);
		} else
		{
			catid = cc.getId();
		}
		news.setCatid(catid);
		long pid = contentNewDao.savecdNews(news);
		updateTags(account.getOwner().getId(), pid, tags);
		newsDao.saveNews(ownerid, accountType, pid);
		return pid;
	}

	@Override
	public ContentNew findNewById(long contentid, long id)
	{
		return contentNewDao.findNewById(contentid, id);
	}

	@Override
	public int updateNews(ContentNew news, JSONObject tags, Account account, String accountType)
	{
		updateTags(account.getOwner().getId(), news.getId(), tags);
		return contentNewDao.updateNew(news);
	}

	@Override
	public int deletecdNews(long contentid, long owner)
	{
		return contentNewDao.updateNew(contentid, owner, IPageConstants.CONTENT_DEL);
	}

	private void updateTags(long owner, long pid, JSONObject tags)
	{
		UserPkvTag t = new UserPkvTag();
		String k_s = "n";

		t.setK_s(k_s);
		t.setV_s(pid + "");

		if (tags.has("tag1"))
		{
			t.setTg1(updateTg(owner, tags.getJSONArray("tag1")));
		}

		if (tags.has("tag2"))
		{
			t.setTg2(updateTg(owner, tags.getJSONArray("tag2")));
		}

		if (tags.has("tag3"))
		{
			t.setTg3(updateTg(owner, tags.getJSONArray("tag3")));
		}

		if (tags.has("tag4"))
		{
			t.setTg4(updateTg(owner, tags.getJSONArray("tag4")));
		}
		if (tags.has("tag5"))
		{
			t.setTg5(updateTg(owner, tags.getJSONArray("tag5")));
		}
		userTagDao.savePkvTag(t);

	}

	private long updateTg(long owner, JSONArray ja)
	{
		if (ja != null && ja.size() > 0)
		{
			String name = ja.getString(0);
			if (name.trim().length() > 0)
			{
				long id = userTagDao.findTagByName(owner, name);
				if (id == 0)
				{
					id = userTagDao.saveTag(owner, name);
				}
				return id;
			}

		}
		return 0;
	}

	@Override
	public int updateNewsUp(long contentid, long owner)
	{
		CDNews current = newsDao.findcdNews(contentid, owner);
		CDNews front = newsDao.findFrontcdNews(current.getIdx(), current.getType(), owner);
		if (current != null && front != null)
		{
			int currentIdx = current.getIdx();
			current.setIdx(front.getIdx());
			front.setIdx(currentIdx);
			int rs1 = newsDao.updatecdNews(current);
			int rs2 = newsDao.updatecdNews(front);
			return rs1 + rs2;
		}
		return 0;
	}

	@Override
	public int updateNewsDown(long contentid, long owner)
	{
		CDNews current = newsDao.findcdNews(contentid, owner);
		CDNews next = newsDao.findNextcdNews(current.getIdx(), current.getType(), owner);
		if (current != null && next != null)
		{
			int currentIdx = current.getIdx();
			current.setIdx(next.getIdx());
			next.setIdx(currentIdx);
			int rs1 = newsDao.updatecdNews(current);
			int rs2 = newsDao.updatecdNews(next);
			return rs1 + rs2;
		}
		return 0;
	}

	@Override
	public List<CDNews> findListForCaiMin(long owner, int pageid, int size)
	{
		return newsDao.findForCaiMinPage(owner, (pageid - 1) * size, size);
	}

	@Override
	public List<CDNews> findListForStation(long owner, String accountLevel, int pageid, int size)
	{
		// ALZ-渠道总监；PRZ-渠道经理；ADM-系统管理员;YYR-运维经理
		int level = 1;
		if ("SJ".equals(accountLevel))
		{
			level = 2;
		}
		return newsDao.findForStation(owner, level, (pageid - 1) * size, size);
	}
}
