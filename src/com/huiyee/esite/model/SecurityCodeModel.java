package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

import com.huiyee.esite.util.DateUtil;

public class SecurityCodeModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private long pageid;
	private long entityid;
	private String type;
	private String code1;
	private String code2;
	private String code3;
	private String code4;
	private String phone;
	private String address;
	private String status;
	private int payed;
	private Date createtime;
	private Date updatetime;
	private String openid;
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
	public long getEntityid() {
		return entityid;
	}
	public void setEntityid(long entityid) {
		this.entityid = entityid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode1() {
		return code1;
	}
	public void setCode1(String code1) {
		this.code1 = code1;
	}
	public String getCode2() {
		return code2;
	}
	public void setCode2(String code2) {
		this.code2 = code2;
	}
	public String getCode3() {
		return code3;
	}
	public void setCode3(String code3) {
		this.code3 = code3;
	}
	public String getCode4() {
		return code4;
	}
	public void setCode4(String code4) {
		this.code4 = code4;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public int getPayed() {
		return payed;
	}
	public void setPayed(int payed) {
		this.payed = payed;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	@Override
	public String toString()
	{
		//Œ¢–≈ID,∑¿Œ±¬Î1,∑¿Œ±¬Î2,∑¿Œ±¬Î3,∑¿Œ±¬Î4, ÷ª˙,µÿ÷∑,Ã·Ωª ±º‰
		return this.entityid + "," + cvsStr(this.code1) + "\t," + cvsStr(this.code2) + "\t," + cvsStr(this.code3) + "\t," + cvsStr(this.code4) + "\t," + cvsStr(this.phone) + "," + cvsStr(this.address) + "," + DateUtil.getDateTimeString(this.createtime);
	}
	private String cvsStr(String str)
	{
		if (str != null)
		{
			if (str.indexOf(",") != -1)
			{
				return "\"" + str + "\"";
			}
			else
			{
				return str;
			}
		}
		else
		{
			return "";
		}
	}
}
