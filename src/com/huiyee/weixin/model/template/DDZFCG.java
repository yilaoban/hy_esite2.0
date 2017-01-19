package com.huiyee.weixin.model.template;

/**
 * 订单支付成功
 */
public class DDZFCG extends BaseTemplate {

	private String first;// 标题
	private String remark;// 备注
	private String orderMoneySum;
	private String orderProductName;

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

	public String getOrderMoneySum() {
		return orderMoneySum;
	}

	public void setOrderMoneySum(String orderMoneySum) {
		this.orderMoneySum = orderMoneySum;
	}

	public String getOrderProductName() {
		return orderProductName;
	}

	public void setOrderProductName(String orderProductName) {
		this.orderProductName = orderProductName;
	}

}
