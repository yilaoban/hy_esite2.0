package project.caidan.dto;

import java.util.List;

import project.caidan.model.CDRmbGet;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;


public class CaiDanRmbDto implements IDto
{
	private List<CDRmbGet> rmbGetList;
	private Pager pager;
	
	public List<CDRmbGet> getRmbGetList()
	{
		return rmbGetList;
	}
	
	public void setRmbGetList(List<CDRmbGet> rmbGetList)
	{
		this.rmbGetList = rmbGetList;
	}
	
	public Pager getPager()
	{
		return pager;
	}
	
	public void setPager(Pager pager)
	{
		this.pager = pager;
	}
	
}
