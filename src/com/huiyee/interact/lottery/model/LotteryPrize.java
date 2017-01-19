package com.huiyee.interact.lottery.model;

import java.io.Serializable;

public class LotteryPrize implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1916887998240087552L;
	private long id;
	private long lid;
	private String name;
	private String img;
	private String detail;
	private int total;
	private int used;
	private int price;
	private String status;
	private String type = "J";// J-ji fen;S-shi wu;C-code;M-红包券 ;H-微信红包
	private String hydefault;
	private String location;
	private int jf;
	private int hprice;// 红包金额
	private String wishing;// 祝福语
	private String act_name;// 活动名称
	private String remark;// 备注
	private int hbgz;//是否关注发送红包，0-否，1-是
	private String hbkey;//红包口令
	private long productid;//自提商品id
	private String link;
	private int positionid;
	private String style;
	private int value;
	
	
	public int getValue()
	{
		if("J".equals(this.type) || "S".equals(this.type)){
			this.value = this.jf;
		}
		if("M".equals(this.type)){
			 this.value = this.hprice;
		}
		return value;
	}

	
	public void setValue(int value)
	{
		this.value = value;
	}

	public int getPositionid()
	{
		return positionid;
	}

	public void setPositionid(int positionid)
	{
		this.positionid = positionid;
	}

	public String getStyle()
	{
		return style;
	}

	public void setStyle(String style)
	{
		this.style = style;
	}

	public long getProductid()
	{
		return productid;
	}

	public void setProductid(long productid)
	{
		this.productid = productid;
	}


	public int getHbgz()
	{
		return hbgz;
	}

	
	public void setHbgz(int hbgz)
	{
		this.hbgz = hbgz;
	}

	
	public String getHbkey()
	{
		return hbkey;
	}

	
	public void setHbkey(String hbkey)
	{
		this.hbkey = hbkey;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getLid() {
		return lid;
	}

	public void setLid(long lid) {
		this.lid = lid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHydefault() {
		return hydefault;
	}

	public void setHydefault(String hydefault) {
		this.hydefault = hydefault;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getJf() {
		return jf;
	}

	public void setJf(int jf) {
		this.jf = jf;
	}

	public int getHprice() {
		return hprice;
	}

	public void setHprice(int hprice) {
		this.hprice = hprice;
	}

	public String getWishing() {
		return wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getLink()
	{
		return link;
	}
	
	public void setLink(String link)
	{
		this.link = link;
	}
	
	public void process(){
		if("J".equals(this.type) || "S".equals(this.type)){
			this.jf = this.value;
		}
		if("M".equals(this.type)){
			this.hprice = this.value;
		}
	}
}
