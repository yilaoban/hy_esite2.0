
package com.huiyee.interact.cb.model;

import java.io.Serializable;

public class HbConfig implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9084214394363040770L;
	private String wishing;// 祝福语
	private String remark;// 备注
	private String act_name;// 活动名称

	public String getWishing()
	{
		return wishing;
	}

	public void setWishing(String wishing)
	{
		this.wishing = wishing;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getAct_name()
	{
		return act_name;
	}

	public void setAct_name(String act_name)
	{
		this.act_name = act_name;
	}

}
