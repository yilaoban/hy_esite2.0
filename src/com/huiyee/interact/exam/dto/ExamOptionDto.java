
package com.huiyee.interact.exam.dto;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.huiyee.interact.exam.model.ExamQuestionOption;
import com.huiyee.interact.exam.model.ExamRecordAnswer;

public class ExamOptionDto implements IDto
{

	private List<List<Object>> examdata;
	private List<List<Object>> orddata;
	private List<ExamQuestionOption> list;
	private List<ExamRecordAnswer> answer;
	private int total;
	private String ordStr;
	private List<Map<Integer, Integer>> lists;
	private List<OrdData> ordLists;
	private String ordListsStr;

	public List<List<Object>> getExamdata()
	{
		return examdata;
	}

	public void setExamdata(List<List<Object>> examdata)
	{
		this.examdata = examdata;
	}

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public List<ExamQuestionOption> getList()
	{
		return list;
	}

	public void setList(List<ExamQuestionOption> list)
	{
		this.list = list;
	}

	public List<ExamRecordAnswer> getAnswer()
	{
		return answer;
	}

	public void setAnswer(List<ExamRecordAnswer> answer)
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

	public String getOrdListsStr()
	{
		return this.ordListsStr;
	}

	public void setOrdListsStr(String ordListsStr)
	{
		this.ordListsStr = ordListsStr;
	}

}
