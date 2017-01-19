package com.huiyee.interact.setting.model;

public class HyUserDz {

	private long id;
	private long owner;
	private long hyuid;
	private long wxuid;
	private String openid;
	private String nickname;
	private int sex;
	private String headimgurl;
	private int t1;// 收营员
	private int t2;// 预约收到提醒
	private int t3;// 服务评价
	private int t4;// 商城订单通知
	private int t5;// 消费成功通知
	private int t6;// 充值通知
	private int t7;// 权限

	private String type;
	private int type_val;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOwner() {
		return owner;
	}

	public void setOwner(long owner) {
		this.owner = owner;
	}

	public long getHyuid() {
		return hyuid;
	}

	public void setHyuid(long hyuid) {
		this.hyuid = hyuid;
	}

	public long getWxuid() {
		return wxuid;
	}

	public void setWxuid(long wxuid) {
		this.wxuid = wxuid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public int getT1() {
		return t1;
	}

	public void setT1(int t1) {
		this.t1 = t1;
	}

	public int getT2() {
		return t2;
	}

	public void setT2(int t2) {
		this.t2 = t2;
	}

	public int getT3() {
		return t3;
	}

	public void setT3(int t3) {
		this.t3 = t3;
	}

	public int getT4() {
		return t4;
	}

	public void setT4(int t4) {
		this.t4 = t4;
	}

	public int getT5() {
		return t5;
	}

	public void setT5(int t5) {
		this.t5 = t5;
	}

	public int getT6() {
		return t6;
	}

	public void setT6(int t6) {
		this.t6 = t6;
	}

	public int getT7() {
		return t7;
	}

	public void setT7(int t7) {
		this.t7 = t7;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getType_val() {
		return type_val;
	}

	public void setType_val(int type_val) {
		this.type_val = type_val;
	}

}
