package com.huiyee.weixin.model.template;

/**
 * 会员消费提醒
 */
public class HYXFTX extends BaseTemplate {

	private String first;// 标题
	private String remark;// 备注
	private String keyword1;// 会员卡号
	private String keyword2;// 店铺名称
	private String keyword3;// 消费金额

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getKeyword1() {
		return keyword1;
	}

	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}

	public String getKeyword2() {
		return keyword2;
	}

	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}

	public String getKeyword3() {
		return keyword3;
	}

	public void setKeyword3(String keyword3) {
		this.keyword3 = keyword3;
	}

}
