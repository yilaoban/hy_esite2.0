package com.huiyee.esite.mgr;

import java.util.List;
import java.util.Map;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.ContentPicture;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.ContentTab;
import com.huiyee.esite.model.OwnerContentType;
import com.huiyee.esite.model.ContentVideo;
import com.huiyee.interact.bbs.model.BBSContent;

public interface IContentManager {

	public List<ContentCategory> findNextCategory(long ccid, long id);

	public String findContactCategoryType(long type, long owner);

	public List<ContentProduct> findProductByCcid(long ccid, long owner, int i, int productLimit, String entityName);
	
	public List<ContentProduct> findProductByCcid(long ccid,long ownerid);

	public List<ContentCategory> findContentCategory(long ccid);

	public List<ContentCategory> findContactCategoryByOwnerAndType(long id, String contentProducy);

	public long saveProduct(ContentProduct product);

	//无限遍历category直至找到所有父节点
	public List<List<ContentCategory>> findContentCategoryLevel(long ccid, long owner);

	public ContentProduct findProductById(long contentId);

	public int updateProduct(ContentProduct product, long ownerid);

	public int updateProduct(long contentId, String contentDel, long ownerid);

	public long savePicture(ContentPicture picture);

	public List<ContentPicture> findPictureByCcid(long ccid, long owner, int i, int contentLimit, String entityName);
	
	public List<ContentPicture> findPictureByCcid(long ccid,long ownerid);

	public ContentPicture findPictureById(long contentId, long id);
	
	public ContentPicture findPictureById(long contentId);

	public int updatePicture(ContentPicture picture);

	public int updatePicture(long contentId, long owner, String contentDel);

	public long saveNew(ContentNew cn);

	public List<ContentNew> findNewByCcid(long ccid, long owner, int i, int contentLimit, String entityName);
	
	public List<ContentNew> findNewByCcid(long ccid,long ownerid);

	public ContentNew findNewById(long contentId, long id);
	
	public ContentNew findNewsById(long contentId);

	public int updateNew(ContentNew cn);

	public int updateNew(long contentId, long id, String status);

	public long saveVideo(ContentVideo video);

	public List<ContentVideo> findVideoByCcid(long ccid, long owner, int i, int contentLimit, String entityName);

	public ContentVideo findVideoById(long contentId, long id);
	
	public ContentVideo findVideoById(long contentId);

	public int updateVideo(ContentVideo video);

	public int updateVideo(long contentId, String status, long id);

	public List<List<ContentCategory>> findContentCategoryWithType(long ccid, long owner);

	public long delCategory(long ccid);

	public int addCategory(long owner, ContentCategory cc);

	public List<ContentCategory> findTopContentCategory(String contentProducy, long id);

	public List<ContentProduct> findOwnerProduct(long id);

	public int findProductTotal(long ccid, long owner, String entityName);

	public int findPictureTotal(long ccid, long owner, String entityName);

	public int findNewsTotal(long ccid, long owner, String entityName);

	public int findVideoTotal(long ccid, long owner, String entityName);

	public List<ContentNew> findNewsByOwner(long owner);

	public List<CategoryTree> findTreeByType(String type, long ccid, long owner, long typeid);

	public ContentCategory findContentCategoryById(long ccid);
	
	public int updateContentIdx(long id, long ccid, String cctype, int moveUp);
	
	public int updateCategoryName(long id,ContentCategory cc);

	public List<OwnerContentType> findTypeList(long owner);

	public long findDefaultCcid(long typeid, long owner);

	public int addContentType(String ccname, long id, String type);

	public int deleteContentType(long typeid, long id);

	public List<OwnerContentType> findConfigType(Account account);

	public int updateTypeSub(List<OwnerContentType> subList, long id);

	public List<OwnerContentType> findContentType(long id);
	
	public List<BBSContent> findBBSProduct(long catid, int entype);

	public List<BBSContent> findBBSPicture(long catid, int entype);

	public List<BBSContent> findBBSVideo(long catid, int entype);

	public List<BBSContent> findBBSNews(long catid, int entype);
	
	public String findBread(long catid); 
	
	public int saveBread(long catid,String json);

	/**
	 * 内容复制/移动到别的目录
	 * @param ids
	 * @param targetCcid
	 * @param copyOrcut
	 * @return
	 */
	public int updateContentMoveOrCut(String ids, long targetCcid, int copyOrcut);

	/**
	 * 批量删除
	 * @param ids
	 * @param targetCcid
	 * @return
	 */
	public int updateContentBatchDel(String ids, long targetCcid);

	/**
	 * 新闻的置顶/取消置顶
	 * @param account
	 * @param contentid
	 * @param topType
	 * @return
	 */
	public int updateContentToTop(Account account, long contentid, int topType);

	/**
	 * 根据status获取目录中新闻
	 * @param ccid
	 * @param ownerid
	 * @param start
	 * @param size
	 * @param statusCmp
	 * @return
	 */
	public List<ContentNew> findNewsByCcid(long ccid, long ownerid, int start, int size, String status);

	public List<ContentCategory> findBangDingCategory(long ownerid);

	public List<ContentCategory> findBangDingShopCategory(long id, String productWsc);
	
	public List<ContentCategory> findCategoryByFatherCcid(long catid, long owner);
	
	public long addProductLevel(long productid,String id, String name);
	
	public long updateProductVip(long productid);

	public Map<String, String> findProductTabs(long contentId, long owner);

	public int updateTabIndex(ContentTab tab);
}
