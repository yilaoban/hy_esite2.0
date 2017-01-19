
package com.huiyee.esite.mgr.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IAccountHideDao;
import com.huiyee.esite.dao.IContentCategoryDao;
import com.huiyee.esite.dao.IContentFormDao;
import com.huiyee.esite.dao.IContentNewDao;
import com.huiyee.esite.dao.IContentPictureDao;
import com.huiyee.esite.dao.IContentProductDao;
import com.huiyee.esite.dao.IContentVideoDao;
import com.huiyee.esite.fdao.IHd149Dao;
import com.huiyee.esite.mgr.IContentManager;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.ContentPicture;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.ContentTab;
import com.huiyee.esite.model.ContentVideo;
import com.huiyee.esite.model.OwnerContentType;
import com.huiyee.interact.bbs.model.BBSContent;
import com.opensymphony.xwork2.ActionContext;

public class ContentManagerImpl implements IContentManager
{

	private IContentCategoryDao contentCategoryDao;
	private IContentProductDao contentProductDao;
	private IContentPictureDao contentPictureDao;
	private IContentNewDao contentNewDao;
	private IContentVideoDao contentVideoDao;
	private IContentFormDao contentFormDao;
	private IAccountHideDao accountHideDao;
	private IHd149Dao hd149Dao;

	public void setHd149Dao(IHd149Dao hd149Dao)
	{
		this.hd149Dao = hd149Dao;
	}

	public void setAccountHideDao(IAccountHideDao accountHideDao)
	{
		this.accountHideDao = accountHideDao;
	}

	public void setContentFormDao(IContentFormDao contentFormDao)
	{
		this.contentFormDao = contentFormDao;
	}

	public void setContentNewDao(IContentNewDao contentNewDao)
	{
		this.contentNewDao = contentNewDao;
	}

	public void setContentVideoDao(IContentVideoDao contentVideoDao)
	{
		this.contentVideoDao = contentVideoDao;
	}

	public void setContentPictureDao(IContentPictureDao contentPictureDao)
	{
		this.contentPictureDao = contentPictureDao;
	}

	public void setContentCategoryDao(IContentCategoryDao contentCategoryDao)
	{
		this.contentCategoryDao = contentCategoryDao;
	}

	public void setContentProductDao(IContentProductDao contentProductDao)
	{
		this.contentProductDao = contentProductDao;
	}

	public List<ContentCategory> findNextCategory(long ccid, long owner)
	{
		if (ccid == -1)
		{
			return contentCategoryDao.findContentCategoryWithTypeAndNoFathercatid("T", owner);
		} else if (ccid == -2)
		{
			return contentCategoryDao.findContentCategoryWithTypeAndNoFathercatid("P", owner);
		} else if (ccid == -3)
		{
			return contentCategoryDao.findContentCategoryWithTypeAndNoFathercatid("V", owner);
		} else if (ccid == -4)
		{
			return contentCategoryDao.findContentCategoryWithTypeAndNoFathercatid("N", owner);
		} else if (ccid != 0)
		{
			return contentCategoryDao.findNextCategoryByCcid(ccid, owner);
		}
		return null;
	}

	public String findContactCategoryType(long typeid, long owner)
	{
		return contentCategoryDao.findContentCategoryType(typeid, owner);
	}

	@Override
	public List<ContentProduct> findProductByCcid(long ccid, long owner, int start, int size, String name)
	{
		return contentProductDao.findProductByCcid(ccid, owner, start, size, name);
	}

	@Override
	public List<ContentProduct> findProductByCcid(long ccid, long ownerid)
	{
		return contentProductDao.findProductByCcid(ccid, ownerid);
	}

	public List<ContentCategory> findContentCategory(long ccid)
	{
		return contentCategoryDao.findContentCategory(ccid);
	}

	@Override
	public ContentCategory findContentCategoryById(long ccid)
	{
		return contentCategoryDao.findContentCategoryById(ccid);
	}

	@Override
	public List<ContentCategory> findContactCategoryByOwnerAndType(long owner, String type)
	{
		return contentCategoryDao.findProductCategory(owner, type);
	}

	@Override
	public long saveProduct(ContentProduct product)
	{
		return contentProductDao.saveProduct(product);
	}

	@Override
	public List<List<ContentCategory>> findContentCategoryLevel(long ccid, long owner)
	{
		List<List<ContentCategory>> list = new ArrayList<List<ContentCategory>>();
		Stack<List<ContentCategory>> stack = new Stack<List<ContentCategory>>();
		ContentCategory cc = contentCategoryDao.findContentCategoryById(ccid);
		stack = UpContent(cc, stack, owner);
		while (!stack.empty())
		{
			list.add(stack.pop());
		}
		List<ContentCategory> next = contentCategoryDao.findNextCategoryByCcid(ccid, owner);
		if (next.size() > 0)
		{
			Collections.sort(next);
			list.add(next);
		}
		return list;
	}

	private Stack<List<ContentCategory>> UpContent(ContentCategory cc, Stack<List<ContentCategory>> stack, long owner)
	{
		List<ContentCategory> prev = new ArrayList<ContentCategory>();
		Set<ContentCategory> set = new HashSet<ContentCategory>();
		cc.setUse("Y");
		set.add(cc);
		if (cc.getFartherCatId() > 0)
		{
			set.addAll(contentCategoryDao.findContentCategory(cc.getId()));
		} else
		{
			set.addAll(contentCategoryDao.findContentCategoryWithTypeAndNoFathercatid(cc.getType(), owner));
		}
		prev.addAll(set);
		Collections.sort(prev);
		if (prev.size() > 0)
		{
			stack.push(prev);
			if (cc.getFartherCatId() > 0)
			{
				ContentCategory prcc = contentCategoryDao.findContentCategoryById(cc.getFartherCatId());
				UpContent(prcc, stack, owner);
			} else
			{

			}
		}
		return stack;
	}

	public ContentProduct findProductById(long contentId)
	{
		ContentProduct cp = contentProductDao.findProductById(contentId);
		if (cp != null)
		{
			Map<Long,Object> pLevelMap = contentProductDao.findProductLevel(cp.getId());
			cp.setBefore(contentProductDao.findBeforeProductById(cp));
			cp.setNext(contentProductDao.findNextProductById(cp));
			cp.setPlevelMap(pLevelMap);
		}
		return cp;
	}

	public int updateProduct(ContentProduct product, long ownerid)
	{
		ContentProduct cp = contentProductDao.findProductById(product.getId());
		int rs = 0;
		if (cp.getFatherid() == 0)
		{
			rs = contentProductDao.updateProduct(product, ownerid);
			contentProductDao.updateProductByFatherid(product.getId(), ownerid, product);
		} else
		{
			contentProductDao.updateProductByFatherid(cp.getFatherid(), ownerid, product);
			product.setId(cp.getFatherid());
			rs = contentProductDao.updateProduct(product, ownerid);
		}
		return rs;
	}

	public int updateProduct(long contentId, String contentDel, long ownerid)
	{
		if ("DEL".equals(contentDel))
		{
			Map map = contentProductDao.findIndx(contentId);
			long catid = (Long) map.get("catid");
			int idx = (Integer) map.get("idx");
			contentProductDao.deleteIndx(idx, catid);
		}
		return contentProductDao.updateProduct(contentId, contentDel, ownerid);
	}

	public long savePicture(ContentPicture picture)
	{
		return contentPictureDao.savePicture(picture);
	}

	public List<ContentPicture> findPictureByCcid(long ccid, long ownerid, int start, int size, String name)
	{
		return contentPictureDao.findPictureByCcid(ccid, ownerid, start, size, name);
	}

	public List<ContentPicture> findPictureByCcid(long ccid, long ownerid)
	{
		return contentPictureDao.findPictureByCcid(ccid, ownerid);
	}

	public ContentPicture findPictureById(long contentId, long id)
	{
		return contentPictureDao.findPictureById(contentId, id);
	}

	public ContentPicture findPictureById(long contentId)
	{
		ContentPicture cp = contentPictureDao.findPictureById(contentId);
		if (cp != null)
		{
			cp.setBefore(contentNewDao.findBeforePicture(cp));
			cp.setNext(contentNewDao.findNextPicture(cp));
		}
		return cp;
	}

	public int updatePicture(ContentPicture picture)
	{
		ContentPicture pic = contentPictureDao.findPictureById(picture.getId());
		int rs = 0;
		if (pic.getFatherid() == 0)
		{
			rs = contentPictureDao.updatePicture(picture);
			contentPictureDao.updatePictureByFatherid(picture.getId(), picture);
		} else
		{
			contentPictureDao.updatePictureByFatherid(pic.getFatherid(), picture);
			picture.setId(pic.getFatherid());
			rs = contentPictureDao.updatePicture(picture);
		}
		return rs;
	}

	public int updatePicture(long contentId, long owner, String contentDel)
	{
		if ("DEL".equals(contentDel))
		{
			Map map = contentPictureDao.findIndx(contentId);
			long catid = (Long) map.get("catid");
			int idx = (Integer) map.get("idx");
			contentPictureDao.deleteIndx(idx, catid);
		}
		return contentPictureDao.updatePicture(contentId, owner, contentDel);
	}

	public long saveNew(ContentNew cn)
	{
		return contentNewDao.saveNew(cn);
	}

	public List<ContentNew> findNewByCcid(long ccid, long owner, int start, int size, String name)
	{
		return contentNewDao.findNewByCcid(ccid, owner, start, size, name);
	}

	@Override
	public List<ContentNew> findNewsByCcid(long ccid, long ownerid, int start, int size, String status)
	{
		return contentNewDao.findNewsByCcid(ccid, ownerid, start, size, status);
	}

	public List<ContentNew> findNewByCcid(long ccid, long ownerid)
	{
		return contentNewDao.findNewByCcid(ccid, ownerid);
	}

	public ContentNew findNewById(long contentId, long owner)
	{
		return contentNewDao.findNewById(contentId, owner);
	}

	public ContentNew findNewsById(long contentId)
	{
		ContentNew news = contentNewDao.findNewsById(contentId);
		if (news != null)
		{
			news.setBefore(contentNewDao.findBeforeNews(news));
			news.setNext(contentNewDao.findNextNews(news));
		}
		return news;
	}

	public int updateNew(ContentNew cn)
	{
		ContentNew cp = contentNewDao.findNewsById(cn.getId());
		int rs = 0;
		if (cp.getFatherid() == 0)
		{
			rs = contentNewDao.updateNew(cn);
			contentNewDao.updateNewsByFatherid(cn.getId(), cn);
		} else
		{
			contentNewDao.updateNewsByFatherid(cp.getFatherid(), cn);
			cn.setId(cp.getFatherid());
			rs = contentNewDao.updateNew(cn);
		}

		return contentNewDao.updateNew(cn);
	}

	public int updateNew(long contentId, long owner, String status)
	{
		if ("DEL".equals(status))
		{
			Map map = contentNewDao.findIndx(contentId);
			long catid = (Long) map.get("catid");
			int idx = (Integer) map.get("idx");
			contentNewDao.deleteIndx(idx, catid);
		}
		return contentNewDao.updateNew(contentId, owner, status);
	}

	public long saveVideo(ContentVideo video)
	{
		return contentVideoDao.saveVideo(video);
	}

	public List<ContentVideo> findVideoByCcid(long ccid, long owner, int start, int size, String name)
	{
		return contentVideoDao.findVideoByCcid(ccid, owner, start, size, name);
	}

	public ContentVideo findVideoById(long contentId, long owner)
	{
		return contentVideoDao.findVideoById(contentId, owner);
	}

	public ContentVideo findVideoById(long contentId)
	{
		ContentVideo video = contentVideoDao.findVideoById(contentId);
		if (video != null)
		{
			video.setBefore(contentVideoDao.findBeforeVideoById(video));
			video.setNext(contentVideoDao.findNextVideoById(video));
		}
		return video;
	}

	public int updateVideo(ContentVideo video)
	{
		ContentVideo v = contentVideoDao.findVideoById(video.getId());
		int rs = 0;
		if (v.getFatherid() == 0)
		{
			rs = contentVideoDao.updateVideo(video);
			contentVideoDao.updateVideoByFatherid(video.getId(), video);
		} else
		{
			contentVideoDao.updateVideoByFatherid(v.getFatherid(), video);
			video.setId(v.getFatherid());
			rs = contentVideoDao.updateVideo(video);
		}

		return rs;
	}

	public int updateVideo(long contentId, String status, long owner)
	{
		if ("DEL".equals(status))
		{
			Map map = contentVideoDao.findIndx(contentId);
			long catid = (Long) map.get("catid");
			int idx = (Integer) map.get("idx");
			contentVideoDao.deleteIndx(idx, catid);
		}
		return contentVideoDao.updateVideo(contentId, status, owner);
	}

	@Override
	public List<List<ContentCategory>> findContentCategoryWithType(long ccid, long owner)
	{
		List<List<ContentCategory>> toplist = new ArrayList<List<ContentCategory>>();
		List<ContentCategory> list = new ArrayList<ContentCategory>();
		if (ccid == -1)
		{
			list = contentCategoryDao.findContentCategoryWithTypeAndNoFathercatid("T", owner);
		} else if (ccid == -2)
		{
			list = contentCategoryDao.findContentCategoryWithTypeAndNoFathercatid("P", owner);
		} else if (ccid == -3)
		{
			list = contentCategoryDao.findContentCategoryWithTypeAndNoFathercatid("V", owner);
		} else if (ccid == -4)
		{
			list = contentCategoryDao.findContentCategoryWithTypeAndNoFathercatid("N", owner);
		}
		Collections.sort(list);
		toplist.add(list);
		return toplist;
	}

	public long delCategory(long ccid)
	{
		int a = contentCategoryDao.delCategory(ccid);
		if (a > 0)
		{
			long rs = contentCategoryDao.findFatherCategoryByCcid(ccid);
			return rs;
		}
		return 0;
	}

	public int addCategory(long owner, ContentCategory cc)
	{
		int exist = contentCategoryDao.findContentCategoryByName(cc.getName(), owner, cc.getFartherCatId(), cc.getTypeid());
		if (exist > 0)
		{
			return 2;
		}
		long rs = contentCategoryDao.addCategory(owner, cc);
		if (IPageConstants.CONTENT_FORM.equals(cc.getType()) && rs > 0)
		{
			long formid = contentFormDao.addForm(owner, cc.getName(), rs);
			contentFormDao.addFormMapping(formid);
		}
		return rs > 0 ? 1 : 0;
	}

	public List<ContentCategory> findTopContentCategory(String contentProducy, long id)
	{
		return contentCategoryDao.findContentCategoryWithTypeAndNoFathercatid(contentProducy, id);
	}

	@Override
	public List<ContentProduct> findOwnerProduct(long id)
	{
		return contentProductDao.findProductByOwner(id);
	}

	public int findProductTotal(long ccid, long ownerid, String name)
	{
		return contentProductDao.findProductTotal(ccid, ownerid, name);
	}

	public int findPictureTotal(long ccid, long ownerid, String name)
	{
		return contentPictureDao.findPictureTotal(ccid, ownerid, name);
	}

	public int findNewsTotal(long ccid, long owner, String name)
	{
		return contentNewDao.findNewsTotal(ccid, owner, name);
	}

	@Override
	public int findVideoTotal(long ccid, long owner, String name)
	{
		return contentVideoDao.findVideoTotal(ccid, owner, name);
	}

	@Override
	public List<ContentNew> findNewsByOwner(long owner)
	{
		return contentNewDao.findNewsByOwner(owner);
	}

	public List<CategoryTree> findTreeByType(String type, long ccid, long ownerid, long typeid)
	{
		List<Long> used = findUsedCategory(ccid);
		// 获取一级目录
		List<CategoryTree> list = contentCategoryDao.findTreeByType(type, ownerid, typeid);
		list = findTreeByCatid(list, new ArrayList<CategoryTree>(), ownerid);

		List<CategoryTree> result = new ArrayList<CategoryTree>();
		// 目录打开路径
		for (CategoryTree categoryTree : list)
		{
			if (used.contains(categoryTree.getId()))
			{
				categoryTree.setOpen(true);
			}
			if (ccid == categoryTree.getId())
			{
				Map<String, String> map = new HashMap<String, String>();
				map.put("background", "#FFE6B0");
				categoryTree.setFont(map);
			}
			result.add(categoryTree);
		}
		// 当前选中目录
		for (CategoryTree categoryTree : result)
		{
			if (ccid == categoryTree.getId())
			{
				Map<String, String> map = new HashMap<String, String>();
				map.put("background", "#FFE6B0");
				categoryTree.setFont(map);
				categoryTree.setOpen(true);
			}
		}
		return result;
	}

	private List<CategoryTree> findTreeByCatid(List<CategoryTree> list, ArrayList<CategoryTree> arrayList, long ownerid)
	{
		arrayList.addAll(list);
		for (CategoryTree categoryTree : list)
		{
			List<CategoryTree> rs = contentCategoryDao.findNextTreeByCcid(categoryTree.getId(), ownerid);
			if (rs.size() > 0)
			{
				findTreeByCatid(rs, arrayList, ownerid);
			} else
			{
				continue;
			}
		}
		return arrayList;
	}

	private List<Long> findUsedCategory(long ccid)
	{
		List<Long> usedid = new ArrayList<Long>();
		usedid.add(ccid);
		long id = 0;
		while ((id = contentCategoryDao.findFatherCategoryByCcid(ccid)) > 0)
		{
			usedid.add(id);
			ccid = id;
		}
		return usedid;
	}

	@Override
	public int updateContentIdx(long contentId, long ccid, String cctype, int moveUp)
	{
		if ("T".equals(cctype))
		{
			ContentProduct currnet = contentProductDao.findProductById(contentId);
			ContentProduct other = null;
			if (moveUp > 0)
			{
				other = contentProductDao.findBeforeProductById(currnet);
			} else
			{
				other = contentProductDao.findNextProductById(currnet);
			}
			if (currnet != null && other != null)
			{
				int idx = currnet.getIdx();
				currnet.setIdx(other.getIdx());
				other.setIdx(idx);
				int rs1 = contentProductDao.updateProductIdx(currnet);
				int rs2 = contentProductDao.updateProductIdx(other);
				return rs1 + rs2;
			}
		} else if ("P".equals(cctype))
		{
			ContentPicture currnet = contentPictureDao.findPictureById(contentId);
			ContentPicture other = null;
			if (moveUp > 0)
			{
				other = contentNewDao.findBeforePicture(currnet);
			} else
			{
				other = contentNewDao.findNextPicture(currnet);
			}
			if (currnet != null && other != null)
			{
				int idx = currnet.getIdx();
				currnet.setIdx(other.getIdx());
				other.setIdx(idx);
				int rs1 = contentPictureDao.updatePicIdx(currnet);
				int rs2 = contentPictureDao.updatePicIdx(other);
				return rs1 + rs2;
			}
		} else if ("N".equals(cctype))
		{
			ContentNew currnet = contentNewDao.findNewsById(contentId);
			ContentNew other = null;
			if (moveUp > 0)
			{
				other = contentNewDao.findBeforeNews(currnet);
			} else
			{
				other = contentNewDao.findNextNews(currnet);
			}
			if (currnet != null && other != null)
			{
				int idx = currnet.getIdx();
				currnet.setIdx(other.getIdx());
				other.setIdx(idx);
				int rs1 = contentNewDao.updateNewsIdx(currnet);
				int rs2 = contentNewDao.updateNewsIdx(other);
				return rs1 + rs2;
			}
		} else if ("V".equals(cctype))
		{
			ContentVideo currnet = contentVideoDao.findVideoById(contentId);
			ContentVideo other = null;
			if (moveUp > 0)
			{
				other = contentVideoDao.findBeforeVideoById(currnet);
			} else
			{
				other = contentVideoDao.findNextVideoById(currnet);
			}
			if (currnet != null && other != null)
			{
				int idx = currnet.getIdx();
				currnet.setIdx(other.getIdx());
				other.setIdx(idx);
				int rs1 = contentVideoDao.updateVideoIdx(currnet);
				int rs2 = contentVideoDao.updateVideoIdx(other);
				return rs1 + rs2;
			}
		}
		return 0;
	}

	@Override
	public int updateCategoryName(long id, ContentCategory cc)
	{
		return contentCategoryDao.updateCategoryName(id, cc);
	}

	@Override
	public List<OwnerContentType> findTypeList(long owner)
	{
		return contentCategoryDao.findTypeList(owner);
	}

	@Override
	public long findDefaultCcid(long typeid, long owner)
	{
		return contentCategoryDao.findDefaultCcid(typeid, owner);
	}

	@Override
	public int addContentType(String ccname, long owner, String type)
	{
		long typeid = contentCategoryDao.addContentType(ccname, owner, type);
		contentCategoryDao.addOwnerType(typeid, owner, "CMP");
		ContentCategory cc = new ContentCategory();
		cc.setName(ccname);
		cc.setType(type);
		cc.setTypeid(typeid);
		this.addCategory(owner, cc);
		return 1;
	}

	@Override
	public int deleteContentType(long typeid, long id)
	{
		int rs = contentCategoryDao.deleteContentType(typeid, id);
		if (rs == 1)
		{
			contentCategoryDao.delCategoryByTypeid(typeid, id);
		}
		return rs;
	}

	@Override
	public int updateTypeSub(List<OwnerContentType> subList, long owner)
	{

		List<OwnerContentType> insertList = new ArrayList<OwnerContentType>();
		List<OwnerContentType> renameList = new ArrayList<OwnerContentType>();
		for (OwnerContentType ct : subList)
		{
			if (ct != null)
			{
				if ("CMP".equals(ct.getStatus()))
				{
					insertList.add(ct);
				}
				if (ct.getId() > 4 && StringUtils.isNotEmpty(ct.getName()))
				{
					renameList.add(ct);
				}
			}
		}
		if (renameList.size() > 0)
		{
			contentCategoryDao.updateCategoryName(renameList);
		}
		return contentCategoryDao.addOwnerType(insertList, owner);
	}

	@Override
	public List<OwnerContentType> findConfigType(Account account)
	{
		List<OwnerContentType> list = contentCategoryDao.findAllContentType(account.getOwner().getId());
		return updateTypeForAccount(list);
	}

	@Override
	public List<OwnerContentType> findContentType(long owner)
	{
		List<OwnerContentType> list = contentCategoryDao.findContentType(owner);
		return updateTypeForAccount(list);
	}

	/**
	 * 控制不同的account显示不同contenttype
	 * 
	 * @param list
	 * @return
	 */
	private List<OwnerContentType> updateTypeForAccount(List<OwnerContentType> list)
	{
		List<OwnerContentType> result = new ArrayList<OwnerContentType>();
		result.addAll(list);
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		List<Long> ids = accountHideDao.findHidIds(account.getOwner().getId(), account.getId(), IPageConstants.ACCOUNT_HIDE_CONTENTTYPE);
		if (ids.size() > 0 && list.size() > 0)
		{
			for (OwnerContentType t : list)
			{
				if (ids.contains(t.getId()))
				{
					result.remove(t);
				}
			}
		}
		return result;
	}

	@Override
	public List<BBSContent> findBBSProduct(long catid, int entype)
	{
		return hd149Dao.findBBSProduct(catid, entype);
	}

	@Override
	public List<BBSContent> findBBSPicture(long catid, int entype)
	{
		return hd149Dao.findBBSPicture(catid, entype);
	}

	@Override
	public List<BBSContent> findBBSVideo(long catid, int entype)
	{
		return hd149Dao.findBBSVideo(catid, entype);
	}

	@Override
	public List<BBSContent> findBBSNews(long catid, int entype)
	{
		return hd149Dao.findBBSNews(catid, entype);
	}

	@Override
	public String findBread(long catid)
	{
		return contentCategoryDao.findBread(catid);
	}

	@Override
	public int saveBread(long catid, String json)
	{
		return contentCategoryDao.saveBread(catid, json);
	}

	@Override
	public int updateContentMoveOrCut(String ids, long targetCcid, int copyOrcut)
	{
		ContentCategory cc = contentCategoryDao.findContentCategoryById(targetCcid);
		String[] id = ids.split(",");
		for (int i = id.length - 1; i >= 0; i--)
		{

			String pid = id[i].trim();
			if (pid.length() > 0)
			{
				long contentid = Long.parseLong(pid);
				if ("N".equals(cc.getType()))
				{
					ContentNew cn = contentNewDao.findNewsById(contentid);
					cn.setCatid(targetCcid);
					if (copyOrcut == 0)
					{
						cn.setFatherid(0);
					} else
					{
						if (cn.getFatherid() == 0)
							cn.setFatherid(cn.getId());
					}
					contentNewDao.saveNew(cn);
					if (copyOrcut == 1)
					{
						updateNew(cn.getId(), cc.getOwnerid(), IPageConstants.CONTENT_DEL);
					}
				} else if ("P".equals(cc.getType()))
				{
					ContentPicture picture = contentPictureDao.findPictureById(contentid);
					picture.setCatid(targetCcid);
					if (copyOrcut == 0)
					{
						picture.setFatherid(0);
					} else
					{
						if (picture.getFatherid() == 0)
							picture.setFatherid(picture.getId());
					}
					contentPictureDao.savePicture(picture);
					if (copyOrcut == 1)
					{
						updatePicture(picture.getId(), cc.getOwnerid(), IPageConstants.CONTENT_DEL);
					}
				} else if ("V".equals(cc.getType()))
				{
					ContentVideo cv = contentVideoDao.findVideoById(contentid, cc.getOwnerid());
					cv.setCatid(targetCcid);
					if (copyOrcut == 0)
					{
						cv.setFatherid(0);
					} else
					{
						if (cv.getFatherid() == 0)
							cv.setFatherid(cv.getId());
					}
					contentVideoDao.saveVideo(cv);
					if (copyOrcut == 1)
					{
						updateVideo(cv.getId(), IPageConstants.CONTENT_DEL, cc.getOwnerid());
					}
				} else if ("T".equals(cc.getType()))
				{
					ContentProduct cp = contentProductDao.findProductById(contentid);
					cp.setCatid(targetCcid);
					if (copyOrcut == 0)
					{
						cp.setFatherid(0);
					} else
					{
						if (cp.getFatherid() == 0)
							cp.setFatherid(cp.getId());
					}
					contentProductDao.saveProduct(cp);
					if (copyOrcut == 1)
					{
						updateProduct(cp.getId(), IPageConstants.CONTENT_DEL, cc.getOwnerid());
					}
				}
			}

		}
		return 1;
	}

	@Override
	public int updateContentBatchDel(String ids, long targetCcid)
	{
		ContentCategory cc = contentCategoryDao.findContentCategoryById(targetCcid);
		String[] id = ids.split(",");
		for (String pid : id)
		{
			pid = pid.trim();
			if (pid.length() > 0)
			{
				long contentid = Long.parseLong(pid);
				if ("N".equals(cc.getType()))
				{
					updateNew(contentid, cc.getOwnerid(), IPageConstants.CONTENT_DEL);
				} else if ("P".equals(cc.getType()))
				{
					updatePicture(contentid, cc.getOwnerid(), IPageConstants.CONTENT_DEL);
				} else if ("V".equals(cc.getType()))
				{
					updateVideo(contentid, IPageConstants.CONTENT_DEL, cc.getOwnerid());
				} else if ("T".equals(cc.getType()))
				{
					updateProduct(contentid, IPageConstants.CONTENT_DEL, cc.getOwnerid());
				}
			}
		}
		return 1;
	}

	@Override
	public int updateContentToTop(Account account, long contentid, int topType)
	{
		return contentNewDao.updateContentToTop(contentid, account.getOwner().getId(), topType);
	}

	@Override
	public List<ContentCategory> findBangDingCategory(long ownerid)
	{
		return contentCategoryDao.findBangDingCategory(ownerid);
	}

	@Override
	public List<ContentCategory> findBangDingShopCategory(long ownerid, String productWsc)
	{
		return contentCategoryDao.findBangDingShopCategory(ownerid, productWsc);
	}

	@Override
	public List<ContentCategory> findCategoryByFatherCcid(long catid, long owner)
	{
		return contentCategoryDao.findCategoryByFatherCcid(catid, owner);
	}

	@Override
	public long addProductLevel(long productid,String ids, String names)
	{
		String[] id = ids.split(",");
		String[] name = names.split(",");
		int num= 0;
		for(int i=0;i<id.length;i++){
			contentProductDao.addProductLevel(productid,id[i],name[i]);
			num++;
		}
		if(num > 0){
			contentProductDao.updateProductVip(1,productid);
		}
		return num;
	}

	@Override
	public long updateProductVip(long productid)
	{
		return contentProductDao.updateProductVip(0,productid);
	}
	
	@Override
	public Map<String, String> findProductTabs(long contentId, long owner)
	{
		return contentProductDao.findProductTabs(contentId,owner);
	}
	
	@Override
	public int updateTabIndex(ContentTab tab)
	{
		String json=new Gson().toJson(tab);
		return contentProductDao.updateTabIndex(tab.getPid(),tab.getId(),json);
	}
}
