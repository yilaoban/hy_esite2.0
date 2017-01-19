package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.ContentPicture;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.ContentVideo;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageCard;
import com.huiyee.esite.model.PageHtml;
import com.huiyee.esite.model.SiteSource;
import com.huiyee.esite.model.TemplateBlock;
import com.huiyee.esite.model.TemplateCard;
import com.huiyee.esite.model.TemplateCategory;
import com.huiyee.interact.bbs.model.BBSContent;
import com.huiyee.interact.exam.model.ExamResult;

import net.sf.json.JSONObject;

public class CardDto implements IDto{

	private List<TemplateCard> cards;
	private List<TemplateCard> fcards;
	private List<TemplateCard> scards;
	private List<TemplateCard> ccards;
	private List<TemplateCard> d1cards;
	private List<TemplateCard> d2cards;
	private List<TemplateCard> d4cards;
	private List<TemplateCard> d5cards;
	private List<TemplateCard> d6cards;
	private List<TemplateCard> d7cards;
	private List<TemplateCard> qcards;
	private List<TemplateCard> kcards;
	private List<TemplateCategory> categorylist;
	private TemplateCard tc;
	private TemplateBlock tb;
	private PageBlockRelation pbr;
	private List<PageCard> pc;
	private List<PageCard> checkedpc;
	private List<Page> pages;
	private long pageid;
	private Page page;
	private String bg;
	private String flag;
	private String stype;
	private String jspstyle;
	private PageHtml pageHtml;
	private JSONObject css;
	private String cardcss;
	private JSONObject js;
	private long xcid;
	private String subtype;
	private List<TemplateCard> soncards;
	private String isAddCard;//用于判断是否能继续添加卡片
	private List<SiteSource> sourceList;
	
	private ContentNew news;
	private ContentPicture picture;
	private List<BBSContent> list;
	private ExamResult examResult; 
	private long fatherpage;
	private ContentProduct product;
	private ContentVideo video;
	private boolean buyProduct;//用于判断是否能购买商品
	
	public boolean isBuyProduct() {
		return buyProduct;
	}


	public void setBuyProduct(boolean buyProduct) {
		this.buyProduct = buyProduct;
	}


	public ContentVideo getVideo()
	{
		return video;
	}

	
	public void setVideo(ContentVideo video)
	{
		this.video = video;
	}

	public ContentProduct getProduct()
	{
		return product;
	}
	
	public void setProduct(ContentProduct product)
	{
		this.product = product;
	}

	public long getFatherpage()
	{
		return fatherpage;
	}
	
	public void setFatherpage(long fatherpage)
	{
		this.fatherpage = fatherpage;
	}

	public ExamResult getExamResult()
	{
		return examResult;
	}
	
	public void setExamResult(ExamResult examResult)
	{
		this.examResult = examResult;
	}


	public ContentPicture getPicture()
	{
		return picture;
	}

	
	public void setPicture(ContentPicture picture)
	{
		this.picture = picture;
	}

	public List<SiteSource> getSourceList()
	{
		return sourceList;
	}
	
	public void setSourceList(List<SiteSource> sourceList)
	{
		this.sourceList = sourceList;
	}
	public List<TemplateCard> getQcards() {
		return qcards;
	}
	public void setQcards(List<TemplateCard> qcards) {
		this.qcards = qcards;
	}
	public String getIsAddCard() {
		return isAddCard;
	}
	public void setIsAddCard(String isAddCard) {
		this.isAddCard = isAddCard;
	}
	public JSONObject getJs()
	{
		return js;
	}
	public void setJs(JSONObject js)
	{
		this.js = js;
	}
	public JSONObject getCss()
	{
		return css;
	}
	public void setCss(JSONObject css)
	{
		this.css = css;
	}
	public PageHtml getPageHtml()
	{
		return pageHtml;
	}
	public void setPageHtml(PageHtml pageHtml)
	{
		this.pageHtml = pageHtml;
	}
	public long getPageid()
	{
		return pageid;
	}
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
	public List<Page> getPages()
	{
		return pages;
	}
	public void setPages(List<Page> pages)
	{
		this.pages = pages;
	}
	public List<PageCard> getCheckedpc()
	{
		return checkedpc;
	}
	public void setCheckedpc(List<PageCard> checkedpc)
	{
		this.checkedpc = checkedpc;
	}
	public TemplateCard getTc() {
		return tc;
	}
	public void setTc(TemplateCard tc) {
		this.tc = tc;
	}
	public TemplateBlock getTb() {
		return tb;
	}
	public void setTb(TemplateBlock tb) {
		this.tb = tb;
	}
	public PageBlockRelation getPbr() {
		return pbr;
	}
	public void setPbr(PageBlockRelation pbr) {
		this.pbr = pbr;
	}
	public List<PageCard> getPc() {
		return pc;
	}
	public void setPc(List<PageCard> pc) {
		this.pc = pc;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getBg()
	{
		return bg;
	}
	public void setBg(String bg)
	{
		this.bg = bg;
	}
	public String getFlag()
	{
		return flag;
	}
	public void setFlag(String flag)
	{
		this.flag = flag;
	}
	public List<TemplateCard> getFcards()
	{
		return fcards;
	}
	public void setFcards(List<TemplateCard> fcards)
	{
		this.fcards = fcards;
	}
	public List<TemplateCard> getScards()
	{
		return scards;
	}
	public void setScards(List<TemplateCard> scards)
	{
		this.scards = scards;
	}
	public List<TemplateCard> getCcards()
	{
		return ccards;
	}
	public void setCcards(List<TemplateCard> ccards)
	{
		this.ccards = ccards;
	}
	public List<TemplateCard> getCards()
	{
		return cards;
	}
	public void setCards(List<TemplateCard> cards)
	{
		this.cards = cards;
	}
	public List<TemplateCategory> getCategorylist()
	{
		return categorylist;
	}
	public void setCategorylist(List<TemplateCategory> categorylist)
	{
		this.categorylist = categorylist;
	}
	public String getStype()
	{
		return stype;
	}
	public void setStype(String stype)
	{
		this.stype = stype;
	}
	public String getJspstyle() {
		return jspstyle;
	}
	public void setJspstyle(String jspstyle) {
		this.jspstyle = jspstyle;
	}
	public long getXcid()
	{
		return xcid;
	}
	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}
	public String getSubtype()
	{
		return subtype;
	}
	public void setSubtype(String subtype)
	{
		this.subtype = subtype;
	}
	public List<TemplateCard> getSoncards()
	{
		return soncards;
	}
	public void setSoncards(List<TemplateCard> soncards)
	{
		this.soncards = soncards;
	}
	public String getCardcss()
	{
		return cardcss;
	}
	public void setCardcss(String cardcss)
	{
		this.cardcss = cardcss;
	}
	public List<TemplateCard> getKcards() {
		return kcards;
	}
	public void setKcards(List<TemplateCard> kcards) {
		this.kcards = kcards;
	}

	
	public ContentNew getNews()
	{
		return news;
	}

	
	public void setNews(ContentNew news)
	{
		this.news = news;
	}

	
	public List<BBSContent> getList()
	{
		return list;
	}

	
	public void setList(List<BBSContent> list)
	{
		this.list = list;
	}




	
	public List<TemplateCard> getD1cards()
	{
		return d1cards;
	}




	
	public void setD1cards(List<TemplateCard> d1cards)
	{
		this.d1cards = d1cards;
	}



	
	public List<TemplateCard> getD4cards()
	{
		return d4cards;
	}




	
	public void setD4cards(List<TemplateCard> d4cards)
	{
		this.d4cards = d4cards;
	}




	
	public List<TemplateCard> getD5cards()
	{
		return d5cards;
	}




	
	public void setD5cards(List<TemplateCard> d5cards)
	{
		this.d5cards = d5cards;
	}




	
	public List<TemplateCard> getD6cards()
	{
		return d6cards;
	}




	
	public void setD6cards(List<TemplateCard> d6cards)
	{
		this.d6cards = d6cards;
	}




	
	public List<TemplateCard> getD7cards()
	{
		return d7cards;
	}




	
	public void setD7cards(List<TemplateCard> d7cards)
	{
		this.d7cards = d7cards;
	}




	
	public List<TemplateCard> getD2cards()
	{
		return d2cards;
	}




	
	public void setD2cards(List<TemplateCard> d2cards)
	{
		this.d2cards = d2cards;
	}
	
}
