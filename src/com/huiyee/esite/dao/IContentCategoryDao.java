package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.OwnerContentType;

public interface IContentCategoryDao {

	public List<ContentCategory> findContentCategoryWithTypeAndNoFathercatid(String string, long owner);

	public List<ContentCategory> findNextCategoryByCcid(long ccid, long owner);

	public List<CategoryTree> findNextTreeByCcid(long ccid, long ownerid);
	
	public String findContentCategoryType(long ccid, long owner);

	public List<ContentCategory> findContentCategory(long ccid);

	public List<ContentCategory> findProductCategory(long owner, String type);

	public ContentCategory findContentCategoryById(long ccid);

	public int delCategory(long ccid);

	public long addCategory(long owner, ContentCategory cc);

	public int findContentCategoryByName(String ccname, long owner, long ccid, long typeid);

	public long findFatherCategoryByCcid(long ccid);

	public List<CategoryTree> findTreeByType(String type, long ownerid, long typeid);
	
	public int updateCategoryName(long id,ContentCategory cc);

	public List<OwnerContentType> findTypeList(long owner);

	public long findDefaultCcid(long typeid, long owner);

	public long addContentType(String ccname, long owner, String type);

	public int deleteContentType(long typeid, long id);

	public List<OwnerContentType> findAllContentType(long id);

	public List<OwnerContentType> findContentType(long owner);

	public void addOwnerType(long typeid, long owner, String string);

	public void delCategoryByTypeid(long typeid, long id);

	public int addOwnerType(List<OwnerContentType> updateList, long owner);

	public void updateCategoryName(List<OwnerContentType> renameList);
	
	public String findBread(long catid); 
	
	public int saveBread(long catid,String json);

	/**
	 * 所有pageid不为0的目录
	 * @param ownerid
	 * @return
	 */
	public List<ContentCategory> findCcHasPageId(long ownerid);

	/**
	 * 查找content_category 的pageid
	 * @param ccid
	 * @param owner
	 * @return
	 */
	public long findPageidByNewsid(long nid, long owner);

	public List<ContentCategory> findBangDingCategory(long ownerid);

	public List<ContentCategory> findBangDingShopCategory(long ownerid, String productWsc);
	
	public List<ContentCategory> findCategoryByFatherCcid(long catid, long owner);

	public ContentCategory findByOwner(long id, String contentNew);
}
