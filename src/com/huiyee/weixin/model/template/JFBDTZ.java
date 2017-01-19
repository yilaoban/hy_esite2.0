package com.huiyee.weixin.model.template;

/**
 * 积分变动通知
 */
public class JFBDTZ extends BaseTemplate {

	private String first;// 标题
	private String remark;// 备注

	private String FieldName;
	private String Account;
	private String change;
	private String CreditChange;
	private String CreditTotal;

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

	public String getFieldName() {
		return FieldName;
	}

	public void setFieldName(String fieldName) {
		FieldName = fieldName;
	}

	public String getAccount() {
		return Account;
	}

	public void setAccount(String account) {
		Account = account;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getCreditChange() {
		return CreditChange;
	}

	public void setCreditChange(String creditChange) {
		CreditChange = creditChange;
	}

	public String getCreditTotal() {
		return CreditTotal;
	}

	public void setCreditTotal(String creditTotal) {
		CreditTotal = creditTotal;
	}

}
