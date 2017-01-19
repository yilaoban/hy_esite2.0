package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.dto.TemplateSiteDto;
import com.huiyee.esite.model.Block2page;
import com.huiyee.esite.model.BlockContext;
import com.huiyee.esite.model.CardBackGround;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.MyTempalte;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageCard;
import com.huiyee.esite.model.PageHtml;
import com.huiyee.esite.model.PageTemplate;
import com.huiyee.esite.model.TemplateBlock;
import com.huiyee.esite.model.TemplateCard;
import com.huiyee.esite.model.TemplateCategory;
import com.huiyee.esite.model.TemplateModel;

public interface ITemplateManager {

	public List<TemplateCategory> findTemplateCategory();
	
	public List<TemplateModel> findTemplateModelList(long categoryid,String type);
	
	public int saveOwnerTemplate(long ownerid, List<Long> templates);
	
	public List<MyTempalte> findMyTemplateByOwnerid(long ownerid);
	
	public MyTempalte findOneTemplate(long id);
	
	public int updateNameTemplate(long id,String name);
	
	public int updateTemplate(long id,String style);
	
	public int deleteTemplate(long id);
	
	public int findCardCount(long categoryid,String type);
	
	public List<TemplateCard> findCardList(long categoryid,String type);
	
	public List<TemplateCard> findCardList(long categoryid,String type,String ptype);
	
	public long findCardCategoryid(long pageid);
	
	public long updateCopyCard(long pageid, long cardid);
	
	public TemplateCard findCardByPcid(long relationid);
	
	public String findCssByPcid(long relationid);
	
	public TemplateCard findCardByPcid2(long relationid);
	
	public TemplateBlock findTemplateBlockByRelationid(long relationid);
	
	public PageBlockRelation findPageBlockRelationByRelationid(long relationid);
	
	public int saveRelationJson(long relationid,String json);
	
	public List<PageCard> findTemplateCardByPageid(long pageid);
	
	public List<PageCard> findTemplateCardByPageid2(long pageid);
	
	public List<Page> findSuccessPageByPageid(long pageid);
	
	public List<Page> findFailPageByPageid(long pageid);
	
	public List<PageCard> findCardListByPageid(long pageid);
	
	public int updateCardPosition(String cardMoveStr);
	
	public int deleteCard(long pcid,long pageid);
	
	public int updateCardName(long pcid, String name);
	
	public long saveNewPageCard(PageCard pagecard);
	
	public List<PageBlockRelation> findPageBlockRelationBycardid1(long pcid);
	
	public long savePageBlockRelation(long pcid,long cbid,String json,long pfid,String display);
	
	public List<Block2page> findBlock2pageByPageid(long pageid);
	
	public PageBlockRelation findPageBlockRelationById(long id);
	
	public int updatePageBlockRelation(long id, String json);
	
	public List<BlockContext> findesBlockContext(long relationid);
	
	public BlockContext findBlockContextid(long blockid,String type);
	
	public Page findPageByRelationidAndContextid(long relationid,long contextid);
	
	public long findPageidByPcid(long pcid);
	
	public long findRelationidByPageid(long pageid);
	
	public List<TemplateBlock> findRelationidByPcid(long pcid);
	
	public long findPageidByRelationid(long relationid);
	
	public Page findPageByPageid(long pageid,long ownerid);
	
	public PageHtml findHtmlByPageid(long pageid,String type);
	
	public TemplateSiteDto  findTemplateSiteIndex(long category,String type,int pageId);
	
	public List<PageCard> findPageCardAllByPageid(long pageid);
	
	public int deletePageCard2(long pageid);
	
	public int deletePageBolckRelation2(long pcid);
	
	public int savePageBolckRelation2(long pcid);
	
	public int savePageCard2(long id,long pageid,long cid,int position,String name,String bg,String isshow,String css,String eventName);
	
	public int savePageBolckRelation2(long id,long pcid,long cbid,String json,long pfid,String display);
	
	public int updatePageJspName(String jspname,String jspstyle,long pageid);
	
	public List<Block2page> findAnotherPageidByPageid(long pageid);
	
	public ContentNew findNewsByNid(long id);
	
	public ContentProduct findProductByNid(long id);
	
	public int savePageCardStyle(long cardid,String bg);
	
	public int savePageCardCss(long cardid,String css);
	
	public CardBackGround findBgByCardid(long cardid);
	
	public PageCard findcssBgByCardid(long cardid);
	
	public String findPageStype(long pageid);
	
	public List<PageTemplate> findPageTemplateList();
	
	public int savePostionStyle(long relationid,String style);
	
	public String findSubTypeByPageid(long pageid);
	
	public List<TemplateCard> findTemplateCardList(long categoryid, String subtype);
	
	public List<TemplateBlock> findZujian();
	
	public String addZujian(long pcid,long bid);
	
	public long addCopyCbid(long cbid);
	
	public int updateEvent(long pcid,String eventName);
	
}
