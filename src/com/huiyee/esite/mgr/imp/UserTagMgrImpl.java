
package com.huiyee.esite.mgr.imp;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.huiyee.esite.dao.IContentCategoryDao;
import com.huiyee.esite.dao.IUserTagDao;
import com.huiyee.esite.mgr.IUserTagMgr;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.UserPkvTag;
import com.huiyee.esite.model.UserTag;

public class UserTagMgrImpl implements IUserTagMgr
{

	private IUserTagDao userTagDao;
	private IContentCategoryDao contentCategoryDao;

	public void setUserTagDao(IUserTagDao userTagDao)
	{
		this.userTagDao = userTagDao;
	}

	public void setContentCategoryDao(IContentCategoryDao contentCategoryDao)
	{
		this.contentCategoryDao = contentCategoryDao;
	}

	@Override
	public UserPkvTag findContentTag(long ccid, long pid)
	{
		ContentCategory cc = contentCategoryDao.findContentCategoryById(ccid);
		String k_s = "";
		String v_s = pid + "";
		String type = cc.getType();
		if ("N".equals(type))
		{
			k_s = "n";
		} else if ("T".equals(type))
		{
			k_s = "ctt";
		} else if ("P".equals(type))
		{
			k_s = "cpp";
		} else if ("V".equals(type))
		{
			k_s = "cvv";
		}
		UserPkvTag tag = userTagDao.findUserPkvTag(k_s, v_s);
		if (tag != null)
		{
			for (int i = 1; i < 6; i++)
			{
				try
				{
					Method getIdMethod = tag.getClass().getMethod("getTg" + i);
					Object value = getIdMethod.invoke(tag);
					if (value != null)
					{
						long tagid = (Long) value;
						UserTag ut = userTagDao.findUserTagById(tagid);
						Method method = tag.getClass().getDeclaredMethod("setTag" + i, UserTag.class);
						// 调用set方法
						method.invoke(tag, ut);
					}
				} catch (Exception e)
				{
					e.printStackTrace();
				}

			}
		}
		return tag;
	}
	
	
	
	@Override
	public UserPkvTag findContentTag(String type, long pid)
	{
		String k_s = "";
		String v_s = pid + "";
		if ("N".equals(type))
		{
			k_s = "n";
		} else if ("T".equals(type))
		{
			k_s = "ctt";
		} else if ("P".equals(type))
		{
			k_s = "cpp";
		} else if ("V".equals(type))
		{
			k_s = "cvv";
		}
		UserPkvTag tag = userTagDao.findUserPkvTag(k_s, v_s);
		if (tag != null)
		{
			for (int i = 1; i < 6; i++)
			{
				try
				{
					Method getIdMethod = tag.getClass().getMethod("getTg" + i);
					Object value = getIdMethod.invoke(tag);
					if (value != null)
					{
						long tagid = (Long) value;
						UserTag ut = userTagDao.findUserTagById(tagid);
						Method method = tag.getClass().getDeclaredMethod("setTag" + i, UserTag.class);
						// 调用set方法
						method.invoke(tag, ut);
					}
				} catch (Exception e)
				{
					e.printStackTrace();
				}

			}
		}
		return tag;
	}
	
	
	@Override
	public UserPkvTag findPageTag(long pageid)
	{
		UserPkvTag tag = userTagDao.findUserPkvTag(pageid);
		if (tag != null)
		{
			for (int i = 1; i < 6; i++)
			{
				try
				{
					Method getIdMethod = tag.getClass().getMethod("getTg" + i);
					Object value = getIdMethod.invoke(tag);
					if (value != null)
					{
						long tagid = (Long) value;
						UserTag ut = userTagDao.findUserTagById(tagid);
						Method method = tag.getClass().getDeclaredMethod("setTag" + i, UserTag.class);
						// 调用set方法
						method.invoke(tag, ut);
					}
				} catch (Exception e)
				{
					e.printStackTrace();
				}

			}
		}
		return tag;
	}
	

	public void updateContentTag(long owner, long ccid, long pid, JSONObject tags)
	{
		UserPkvTag t = new UserPkvTag();
		ContentCategory cc = contentCategoryDao.findContentCategoryById(ccid);
		String type = cc.getType();
		String k_s = "";
		if ("N".equals(type))
		{
			k_s = "n";
		} else if ("T".equals(type))
		{
			k_s = "ctt";
		} else if ("P".equals(type))
		{
			k_s = "cpp";
		} else if ("V".equals(type))
		{
			k_s = "cvv";
		}
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
	
	@Override
	public void updatePageTag(long owner, long pageid, JSONObject tags)
	{
		UserPkvTag t = new UserPkvTag();
		t.setPage(pageid);
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
		if(t.getTg1()>0||t.getTg2()>0||t.getTg3()>0||t.getTg4()>0||t.getTg5()>0){
			userTagDao.savePkvTag(t);
		}

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
	public List<UserTag> findOwnerTag(long owner)
	{
		return userTagDao.findOwnerTag(owner);
	}
}
