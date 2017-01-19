package com.huiyee.interact.research.dto;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.huiyee.interact.research.model.ResearchQuestionOption;
import com.huiyee.interact.research.model.ResearchRecordAnswer;
public class ResearchOptionDto implements IDto {
    private List<List<Object>>  researchdata;
    private List<List<Object>>  orddata;
    private List<ResearchQuestionOption> list;
    private List<ResearchRecordAnswer> answer;
    private int total;
    private String ordStr;
    private List<Map<Integer,Integer>> lists;
    private List<OrdData> ordLists;
    private String ordListsStr;
    public List<List<Object>> getResearchdata()
	{
		return researchdata;
	}

	public void setResearchdata(List<List<Object>> researchdata)
	{
		this.researchdata = researchdata;
	}

	public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

	public List<ResearchQuestionOption> getList()
	{
		return list;
	}

	public void setList(List<ResearchQuestionOption> list)
	{
		this.list = list;
	}

	public List<ResearchRecordAnswer> getAnswer()
	{
		return answer;
	}

	public void setAnswer(List<ResearchRecordAnswer> answer)
	{
		this.answer = answer;
	}

	public List<List<Object>> getOrddata()
	{
		return orddata;
	}

	public void setOrddata(List<List<Object>> orddata)
	{
		this.orddata = orddata;
	}

	public List<Map<Integer, Integer>> getLists()
	{
		return lists;
	}

	public void setLists(List<Map<Integer, Integer>> lists)
	{
		this.lists = lists;
	}

	public List<OrdData> getOrdLists()
	{
		return ordLists;
	}

	public void setOrdLists(List<OrdData> ordLists)
	{
		this.ordLists = ordLists;
		Gson gson = new Gson();
		this.ordListsStr = gson.toJson(this.ordLists);
	}

	public String getOrdStr()
	{
		return ordStr;
	}

	public void setOrdStr(String ordStr)
	{
		this.ordStr = ordStr;
	}

	public String getOrdListsStr(){
		return this.ordListsStr;
	}

	public void setOrdListsStr(String ordListsStr)
	{
		this.ordListsStr = ordListsStr;
	}




}
