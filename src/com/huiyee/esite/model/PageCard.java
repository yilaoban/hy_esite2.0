package com.huiyee.esite.model;

import java.util.List;

public class PageCard {
	private long id;
	private long pageid;
	private int position;
	private long cardid;
	private String cardname;
	private String cardsimg;
	private String cardbimg;
	private long relationid;
	private String bg;
	private String isshow;
	private String path;
	private String desc;
	private String css;
	private String type;
	private String eventName;
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	private List<TemplateBlock> tb;
	
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public List<TemplateBlock> getTb()
	{
		return tb;
	}
	public void setTb(List<TemplateBlock> tb)
	{
		this.tb = tb;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPageid() {
		return pageid;
	}
	public void setPageid(long pageid) {
		this.pageid = pageid;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public long getCardid() {
		return cardid;
	}
	public void setCardid(long cardid) {
		this.cardid = cardid;
	}
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public String getCardsimg() {
		return cardsimg;
	}
	public void setCardsimg(String cardsimg) {
		this.cardsimg = cardsimg;
	}
	public String getCardbimg() {
		return cardbimg;
	}
	public void setCardbimg(String cardbimg) {
		this.cardbimg = cardbimg;
	}
	public long getRelationid() {
		return relationid;
	}
	public void setRelationid(long relationid) {
		this.relationid = relationid;
	}
	public String getBg()
	{
		return bg;
	}
	public void setBg(String bg)
	{
		this.bg = bg;
	}
	public String getIsshow()
	{
		return isshow;
	}
	public void setIsshow(String isshow)
	{
		this.isshow = isshow;
	}
	public String getPath()
	{
		return path;
	}
	public void setPath(String path)
	{
		this.path = path;
	}
	public String getCss()
	{
		return css;
	}
	public void setCss(String css)
	{
		this.css = css;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
