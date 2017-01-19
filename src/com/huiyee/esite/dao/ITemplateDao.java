package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.Block2page;
import com.huiyee.esite.model.BlockContext;
import com.huiyee.esite.model.CardBackGround;
import com.huiyee.esite.model.CardBolck;
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

public interface ITemplateDao {

	public List<TemplateCategory> findTemplateCategory();
	
	public List<TemplateModel> findTemplateModelList(long categoryid,String type);
	
	public int saveOwnerTemplate(long ownerid, long templateid);
	
	public List<MyTempalte> findMyTemplateByOwnerid(long ownerid);
	
	public MyTempalte findOneTemplate(long id);
	
	public int updateNameTemplate(long id,String name);
	
	public int updateTemplate(long id,String style);
	
	public int deleteTemplate(long id);
	
	public int findCardCount(long categoryid,String type);
	
	public List<TemplateCard> findCardList(long categoryid, String type);
	
	public List<TemplateCard> findCardList(long categoryid, String type,String ptype,int start,int size);
	
	public List<TemplateCard> findCardList(long categoryid, String type,String ptype);
	
	public long findCardCategoryid(long pageid);
	
	public long savePageCard(long pageid,long cardid);
	
	public long saveNewPageCard(PageCard pagecard);
	
	public List<CardBolck> findCardBlock(long cardid);
	
	public long savePageBlockRelation(long pcid,long cbid,String json,long pfid,String display);
	
	public List<Block2page> findBlock2pageByPageid(long pageid);
	
	public PageBlockRelation findPageBlockRelationById(long id);
	
	public int updatePageBlockRelation(long id, String json);
	
	public int updatePageBlockRelation(long relationid,long pfid);
	
	public TemplateCard findCardByPcid(long pcid);
	
	public String findCssByPcid(long pcid);
	
	public TemplateCard findCardByPcid2(long pcid);
	
	public List<TemplateBlock> findBlocksByPcid(long cardid);
	
	public List<TemplateBlock> findBlocksByPcid2(long cardid);
	
	public List<PageBlockRelation> findPageBlockRelationBycardid(long cardid);
	
	public TemplateBlock findTemplateBlockByRelationid(long relationid);
	
	public PageBlockRelation findPageBlockRelationByRelationid(long relationid);
	
	public int saveRelationJson(long relationid,String json);
	
	public List<PageCard> findTemplateCardByPageid(long pageid);
	
	public List<PageCard> findTemplateCardByPageid2(long pageid);
	
	public List<Page> findTemplateCardByRelationidAndContextid(long relationid,long contextid);
	
	public List<Page> findChildPageByPageid(long pageid);
	
	public int updateCardPosition(long cardid, long position);
	
	public int deleteCard(long pcid);
	
	public int deletePageBlockRelation(long pcid);
	
	public int updateCardName(long pcid, String name);
	
	public List<PageBlockRelation> findPageBlockRelationBycardid1(long pcid);
	
	public List<BlockContext> findesBlockContext(long relationid);
	
	public long saveNewPage(long relationid,long siteid,String stype,long contextid,String name);
	
	public Page findSiteidByPageid(long pageid);
	
	public List<Page> findResultPageByPageid(long pageid);
	
	public List<Page> findResultSuccessPageByPageid(long pageid);
	
	public Page findPageByRelationidAndContextid(long relationid,long contextid);
	
	public BlockContext findBlockContextid(long blockid,String type);
	
	public long findPageidByPcid(long pcid);
	
	public long findRelationidByPageid(long pageid);
	
	public List<TemplateBlock> findRelationidByPcid(long pcid);
	
	public long findPageidByRelationid(long relationid);
	
	public Page findPageByPageid(long pageid,long ownerid);
	
	public PageHtml findHtmlByPageid(long pageid,String type);
	
	public List<PageCard> findPageCardAllByPageid(long pageid);
	
	public int deletePageCard2(long pageid);
	
	public int deletePageBolckRelation2(long pcid);
	
	public int savePageCard2(long id,long pageid,long cid,int position,String name,String bg,String isshow,String css,String eventName);
	
	public int savePageBolckRelation2(long id,long pcid,long cbid,String json,long pfid,String display);
	
	public int updatePageJspName(String jspname,String jspstyle,long pageid);
	
	public List<Block2page> findAnotherPageidByPageid(long pageid);
	
	public int saveBlock2Page(long relationid,long pageid,long fapageid,int type);
	
	public int deleteBlock2Page(long relationid);
	
	public ContentNew findNewsByNid(long id);
	
	public int savePageCardStyle(long cardid,String bg);
	
	public int savePageCardCss(long cardid,String css);
	
	public CardBackGround findBgByCardid(long cardid);
	
	public PageCard findcssBgByCardid(long cardid);
	
	public int findCardCount(long categoryid, String type,String ptype);
	
	public String findPageStype(long pageid);
	
	public List<PageTemplate> findPageTemplateList();
	
	public String findJsonByRelationid(long relationid);
	
	public int saveJsonByRelationid(long relationid,String json);
	
	public String findSubTypeByPageid(long pageid);
	
	public List<TemplateCard> findTemplateCardList(long categoryid, String subtype);
	
	public List<TemplateBlock> findZujian();
	
	public long addCardBlock(long cardid,long bid,String vjson);
	
	public TemplateBlock findTemplateBlockByBid(long bid);
	
	public int updateEvent(long pcid, String eventName);
	
	public long addCopyCbid(long cbid);

	public List<PageBlockRelation> findPageBlockRelationByPageId(long pageid);
	
	public PageCard findPageBlockById(long pcid);
	
	public ContentProduct findProductByNid(long id);
	
	public int savePageBolckRelation2(long pcid);

	public long findCbidByPcidBid(long pcid, long bid);
	
}
