package com.huiyee.interact.research.dto;

import java.util.List;

public class ResearchSubDto implements IDto {
	
	private long researchid;
	private long wbuid;
	private List<String> xz_List;//questionid,optionid
	private List<Long> tk_qid;
	private List<String> tk_answer;
	private List<String> df_list;
	private String danxuanStr;
	private long pageid;
	private String answerStr;//表单提交的字符串
	
	public long getPageid()
	{
		return pageid;
	}
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public String getDanxuanStr()
	{
		return danxuanStr;
	}
	public void setDanxuanStr(String danxuanStr)
	{
		this.danxuanStr = danxuanStr;
	}
	public long getResearchid()
	{
		return researchid;
	}
	public void setResearchid(long researchid)
	{
		this.researchid = researchid;
	}
	public long getWbuid()
	{
		return wbuid;
	}
	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}
	public List<String> getXz_List()
	{
		return xz_List;
	}
	public void setXz_List(List<String> xz_List)
	{
		this.xz_List = xz_List;
	}
	public List<Long> getTk_qid()
	{
		return tk_qid;
	}
	public void setTk_qid(List<Long> tk_qid)
	{
		this.tk_qid = tk_qid;
	}
	public List<String> getTk_answer()
	{
		return tk_answer;
	}
	public void setTk_answer(List<String> tk_answer)
	{
		this.tk_answer = tk_answer;
	}
	public List<String> getDf_list()
	{
		return df_list;
	}
	public void setDf_list(List<String> df_list)
	{
		this.df_list = df_list;
	}
	public String getAnswerStr() {
		return answerStr;
	}
	public void setAnswerStr(String answerStr) {
		this.answerStr = answerStr;
	}
	
}
