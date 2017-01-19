package com.huiyee.esite.mgr.imp;

import java.util.List;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.dao.ITemplateDao;
import com.huiyee.esite.dto.TemplateSiteDto;
import com.huiyee.esite.mgr.ITemplateManager;
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
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.JsonUtil;

public class TemplateManagerImpl implements ITemplateManager {
	private Map<Long, com.huiyee.esite.fmgr.IFeatureManager> managers;
	private ITemplateDao templateDao;
	private VelocityEngine velocityEngine;
	private IPageDao pageDao;

	public void setPageDao(IPageDao pageDao)
	{
		this.pageDao = pageDao;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public Map<Long, com.huiyee.esite.fmgr.IFeatureManager> getManagers() {
		return managers;
	}

	public void setManagers(
			Map<Long, com.huiyee.esite.fmgr.IFeatureManager> managers) {
		this.managers = managers;
	}

	@Override
	public List<TemplateCategory> findTemplateCategory() {
		return templateDao.findTemplateCategory();
	}

	public void setTemplateDao(ITemplateDao templateDao) {
		this.templateDao = templateDao;
	}

	@Override
	public List<TemplateModel> findTemplateModelList(long categoryid,
			String type) {
		return templateDao.findTemplateModelList(categoryid, type);
	}

	@Override
	public int saveOwnerTemplate(long ownerid, List<Long> templates) {
		for (Long tempid : templates) {
			templateDao.saveOwnerTemplate(ownerid, tempid);
		}
		return 1;
	}

	@Override
	public List<MyTempalte> findMyTemplateByOwnerid(long ownerid) {
		return templateDao.findMyTemplateByOwnerid(ownerid);
	}

	@Override
	public int deleteTemplate(long id) {
		return templateDao.deleteTemplate(id);
	}

	@Override
	public MyTempalte findOneTemplate(long id) {
		return templateDao.findOneTemplate(id);
	}

	@Override
	public int updateNameTemplate(long id, String name) {
		return templateDao.updateNameTemplate(id, name);
	}

	@Override
	public int updateTemplate(long id, String style) {
		return templateDao.updateTemplate(id, style);
	}

	@Override
	public int findCardCount(long categoryid, String type) {
		return templateDao.findCardCount(categoryid, type);
	}

	@Override
	public List<TemplateCard> findCardList(long categoryid, String type) {
		return templateDao.findCardList(categoryid, type);
	}

	@Override
	public List<TemplateCard> findCardList(long categoryid, String type,
			String ptype) {
		return templateDao.findCardList(categoryid, type, ptype);
	}

	@Override
	public long findCardCategoryid(long pageid) {
		return templateDao.findCardCategoryid(pageid);
	}

	@Override
	public long updateCopyCard(long pageid, long cardid) {
		// page - card
		long pcid = templateDao.savePageCard(pageid, cardid);
		// copy block
		List<CardBolck> list = templateDao.findCardBlock(cardid);
		Page p = templateDao.findSiteidByPageid(pageid);
		long siteid = p.getSiteid();
		if (list != null && list.size() > 0) {
			for (CardBolck cb : list) {
				long relationid = templateDao.savePageBlockRelation(pcid,
						cb.getId(), cb.getJson(), cb.getFeatureid(),"Y");
				if (cb.getFeatureid() > 0) {
					//互动卡片
					if(managers.get(cb.getFeatureid()) != null){
						long pfid = managers.get(cb.getFeatureid()).add(pageid,
								cb.getFeatureid(), null);
						templateDao.updatePageBlockRelation(relationid, pfid);
						List<BlockContext> blockContextList = templateDao
								.findesBlockContext(relationid);
						if (blockContextList != null && blockContextList.size() > 0) {
							for (int i = 0; i < blockContextList.size(); i++) {
								long rpageid = templateDao.saveNewPage(relationid,
										siteid, p.getStype(),
										blockContextList.get(i).getId(),
										blockContextList.get(i).getContext());
								templateDao.saveBlock2Page(relationid, rpageid,
										pageid, 1);
								if (blockContextList.get(i).getCardid() > 0) {
									updateCopyCard(rpageid, blockContextList.get(i)
											.getCardid());
								}
							}
						}
					}
				}
			}
		}
		return pcid;
	}

	@Override
	public TemplateCard findCardByPcid(long pcid) {
		TemplateCard tc = templateDao.findCardByPcid(pcid);
		if (tc != null) {
			tc.setBlocks(templateDao.findBlocksByPcid(pcid));
		}
		return tc;
	}

	@Override
	public String findCssByPcid(long pcid) {
		return templateDao.findCssByPcid(pcid);
	}

	@Override
	public TemplateCard findCardByPcid2(long pcid) {
		TemplateCard tc = templateDao.findCardByPcid2(pcid);
		if (tc != null) {
			tc.setBlocks(templateDao.findBlocksByPcid2(pcid));
		}
		return tc;
	}

	@Override
	public TemplateBlock findTemplateBlockByRelationid(long relationid) {
		return templateDao.findTemplateBlockByRelationid(relationid);
	}

	@Override
	public PageBlockRelation findPageBlockRelationByRelationid(long relationid) {
		return templateDao.findPageBlockRelationByRelationid(relationid);
	}

	@Override
	public int saveRelationJson(long relationid, String json) {
		JSONObject j = JSONObject.fromObject(json);
		JSONArray jo = null;
		templateDao.deleteBlock2Page(relationid);
		long fapageid = templateDao.findPageidByRelationid(relationid);
		try {
			jo = j.getJSONArray("list");
		} catch (Exception e) {
		}
		if (jo == null) {
			long gld = 0;
			try {
				gld = j.getLong("gld");
			} catch (Exception e) {
			}
			if (gld > 0) {
				templateDao.saveBlock2Page(relationid, gld, fapageid, 0);
			}
		} else {
			JSONArray ja = JSONArray.fromObject(jo);
			for (int i = 0; i < ja.size(); i++) {
				JSONObject jo1 = (JSONObject) ja.get(i);
				long gld = 0;
				String link = null;
				try {
					// gld = jo1.getLong("gld");
					link = jo1.getString("link");
					if (link.contains("/user/show/")) {
						String pageid = link.substring(
								link.lastIndexOf("/") + 1,
								link.lastIndexOf("."));
						gld = Long.parseLong(pageid);
					}
				} catch (Exception e) {
				}
				if (gld > 0) {
					templateDao.saveBlock2Page(relationid, gld, fapageid, 0);
				}
			}
		}
		return templateDao.saveRelationJson(relationid, j.toString());
	}

	@Override
	public List<PageCard> findTemplateCardByPageid(long pageid) {
		return templateDao.findTemplateCardByPageid(pageid);
	}

	@Override
	public List<PageCard> findTemplateCardByPageid2(long pageid) {
		return templateDao.findTemplateCardByPageid2(pageid);
	}

	@Override
	public List<PageCard> findCardListByPageid(long pageid) {
		List<PageCard> cardList = templateDao.findTemplateCardByPageid(pageid);
		List<Page> childPageList = templateDao.findChildPageByPageid(pageid);// 根据Pageid找到所有子页面
		if (childPageList != null && childPageList.size() > 0) {
			for (int i = 0; i < childPageList.size(); i++) {
				List<Page> pageList = templateDao
						.findTemplateCardByRelationidAndContextid(childPageList
								.get(i).getRelationid(), childPageList.get(i)
								.getContextid());
				if (pageList != null && pageList.size() > 0) {
					for (int k = 0; k < pageList.size(); k++) {// 查找子页面的卡片
						List<PageCard> childCardList = templateDao
								.findTemplateCardByPageid(pageList.get(k)
										.getId());
						if (childCardList != null && childCardList.size() > 0) {
							for (int j = 0; j < childCardList.size(); j++) {
								cardList.add(childCardList.get(j));
							}
						}
					}
				}
			}
		}
		return cardList;
	}

	@Override
	public int updateCardPosition(String cardMoveStr) {
		cardMoveStr = cardMoveStr.substring(0, cardMoveStr.length() - 1);
		String[] cards = cardMoveStr.split(";");
		for (int i = 0; i < cards.length; i++) {
			String[] pos = cards[i].split(",");
			int cardid = Integer.parseInt(pos[0]);
			int position = Integer.parseInt(pos[1]);
			templateDao.updateCardPosition(cardid, position);
		}
		return 1;
	}

	@Override
	public int deleteCard(long pcid, long pageid) {
		templateDao.deletePageBlockRelation(pcid);
		templateDao.deleteCard(pcid);
		List<PageCard> list = templateDao.findTemplateCardByPageid(pageid);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				templateDao.updateCardPosition(list.get(i).getId(), i + 1);
			}
		}else{
			String str = pageDao.findPageBg(pageid);
			JSONObject jo;
			try{
				jo = JSONObject.fromObject(str);
			} catch (Exception e){
				jo=new JSONObject();
			} 
			jo.remove("pageheight");
			pageDao.savePageBg(pageid, jo.toString());
		}
		return 1;
	}

	@Override
	public int updateCardName(long pcid, String name) {
		return templateDao.updateCardName(pcid, name);
	}

	@Override
	public long saveNewPageCard(PageCard pagecard) {
		return templateDao.saveNewPageCard(pagecard);
	}

	@Override
	public long savePageBlockRelation(long pcid, long cbid, String json,
			long pfid,String display) {
		return templateDao.savePageBlockRelation(pcid, cbid, json, pfid,display);
	}

	@Override
	public List<Block2page> findBlock2pageByPageid(long pageid) {
		return templateDao.findBlock2pageByPageid(pageid);
	}

	@Override
	public PageBlockRelation findPageBlockRelationById(long id) {
		return templateDao.findPageBlockRelationById(id);
	}

	@Override
	public int updatePageBlockRelation(long id, String json) {
		return templateDao.updatePageBlockRelation(id, json);
	}

	@Override
	public List<PageBlockRelation> findPageBlockRelationBycardid1(long pcid) {
		return templateDao.findPageBlockRelationBycardid1(pcid);
	}

	@Override
	public List<Page> findSuccessPageByPageid(long pageid) {
		return templateDao.findResultSuccessPageByPageid(pageid);
	}

	@Override
	public List<Page> findFailPageByPageid(long pageid) {
		return templateDao.findResultPageByPageid(pageid);
	}

	@Override
	public List<BlockContext> findesBlockContext(long relationid) {
		return templateDao.findesBlockContext(relationid);
	}

	@Override
	public Page findPageByRelationidAndContextid(long relationid, long contextid) {
		return templateDao.findPageByRelationidAndContextid(relationid,
				contextid);
	}

	@Override
	public BlockContext findBlockContextid(long blockid, String type) {
		return templateDao.findBlockContextid(blockid, type);
	}

	@Override
	public long findPageidByPcid(long pcid) {
		return templateDao.findPageidByPcid(pcid);
	}

	@Override
	public long findRelationidByPageid(long pageid) {
		return templateDao.findRelationidByPageid(pageid);
	}

	@Override
	public List<TemplateBlock> findRelationidByPcid(long pcid) {
		return templateDao.findRelationidByPcid(pcid);
	}

	@Override
	public long findPageidByRelationid(long relationid) {
		return templateDao.findPageidByRelationid(relationid);
	}

	@Override
	public Page findPageByPageid(long pageid,long ownerid) {
		return templateDao.findPageByPageid(pageid,ownerid);
	}

	@Override
	public PageHtml findHtmlByPageid(long pageid, String type) {
		return templateDao.findHtmlByPageid(pageid, type);
	}

	@Override
	public TemplateSiteDto findTemplateSiteIndex(long category, String type,
			int pageId) {
		TemplateSiteDto dto = new TemplateSiteDto();
		List<TemplateCategory> catetgorylist = templateDao
				.findTemplateCategory();
		if (catetgorylist != null && catetgorylist.size() > 0) {
			if (category == 0) {
				category = catetgorylist.get(0).getId();
				int start = (pageId - 1) * IPageConstants.CATEGORY_LIMIT;
				List<TemplateCard> cardlist = templateDao.findCardList(
						category, null, "N", start,
						IPageConstants.CATEGORY_LIMIT);
				dto.setCategoryList(catetgorylist);
				dto.setCardlist(cardlist);
			} else {
				int start = (pageId - 1) * IPageConstants.CATEGORY_LIMIT;
				List<TemplateCard> cardlist = templateDao.findCardList(
						category, type, "N", start,
						IPageConstants.CATEGORY_LIMIT);
				dto.setCategoryList(catetgorylist);
				dto.setCardlist(cardlist);
			}
			int total = templateDao.findCardCount(category, type, "N");
			dto.setTotal(total);
		}
		return dto;
	}

	@Override
	public int deletePageBolckRelation2(long pcid) {
		return templateDao.deletePageBolckRelation2(pcid);
	}

	@Override
	public int deletePageCard2(long pageid) {
		return templateDao.deletePageCard2(pageid);
	}

	@Override
	public List<PageCard> findPageCardAllByPageid(long pageid) {
		return templateDao.findPageCardAllByPageid(pageid);
	}

	@Override
	public int savePageBolckRelation2(long id, long pcid, long cbid,
			String json, long pfid, String display) {
		return templateDao.savePageBolckRelation2(id, pcid, cbid, json, pfid,
				display);
	}

	@Override
	public int savePageCard2(long id, long pageid, long cid, int position,
			String name, String bg, String isshow, String css,String eventName) {
		return templateDao.savePageCard2(id, pageid, cid, position, name, bg,
				isshow, css,eventName);
	}

	@Override
	public int updatePageJspName(String jspname, String jspstyle, long pageid) {
		return templateDao.updatePageJspName(jspname, jspstyle, pageid);
	}

	@Override
	public List<Block2page> findAnotherPageidByPageid(long pageid) {
		return templateDao.findAnotherPageidByPageid(pageid);
	}

	@Override
	public ContentNew findNewsByNid(long id) {
		return templateDao.findNewsByNid(id);
	}

	@Override
	public int savePageCardStyle(long cardid, String bg) {
		return templateDao.savePageCardStyle(cardid, bg);
	}

	@Override
	public int savePageCardCss(long cardid, String css) {
		return templateDao.savePageCardCss(cardid, css);
	}

	@Override
	public CardBackGround findBgByCardid(long cardid) {
		return templateDao.findBgByCardid(cardid);
	}

	@Override
	public PageCard findcssBgByCardid(long cardid) {
		return templateDao.findcssBgByCardid(cardid);
	}

	@Override
	public String findPageStype(long pageid) {
		return templateDao.findPageStype(pageid);
	}

	@Override
	public List<PageTemplate> findPageTemplateList() {
		return templateDao.findPageTemplateList();
	}

	@Override
	public int savePostionStyle(long relationid, String style) {
		String json = templateDao.findJsonByRelationid(relationid);
		if (json != null) {
			JSONObject jo = JSONObject.fromObject(json);
			jo.element("hyct", style);
			return templateDao.saveJsonByRelationid(relationid, jo.toString());
		}
		return 0;
	}

	@Override
	public String findSubTypeByPageid(long pageid) {
		return templateDao.findSubTypeByPageid(pageid);
	}

	@Override
	public List<TemplateCard> findTemplateCardList(long categoryid,
			String subtype) {
		return templateDao.findTemplateCardList(categoryid, subtype);
	}

	@Override
	public List<TemplateBlock> findZujian() {
		return templateDao.findZujian();
	}

	@Override
	public String addZujian(long pcid,long bid) {
		TemplateBlock tb = templateDao.findTemplateBlockByBid(bid);
		long cbid = templateDao.findCbidByPcidBid(pcid,bid);
		if(cbid==0){
			cbid = templateDao.addCardBlock(pcid,bid,tb.getVjson());
		}
		long pcbid = templateDao.savePageBlockRelation(pcid, cbid, tb.getVjson(), 0,"Y");
		Map map = JsonUtil.parseJSON2Map(tb.getVjson());
		map.put("pcbid", pcbid);
		map.put("isRun", true);
		return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/WEB-INF/velocity/zujian/"+tb.getName(),"UTF-8", map);
	}

	@Override
	public int updateEvent(long pcid, String eventName) {
		return templateDao.updateEvent(pcid, eventName);
	}

	@Override
	public long addCopyCbid(long cbid){
		return templateDao.addCopyCbid(cbid);
	}

	@Override
	public ContentProduct findProductByNid(long id){
		return templateDao.findProductByNid(id);
	}

	@Override
	public int savePageBolckRelation2(long pcid)
	{
		templateDao.deletePageBolckRelation2(pcid);
		return templateDao.savePageBolckRelation2(pcid);
	}

}
