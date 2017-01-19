
package project.caidan.dto;

import java.util.List;

import project.caidan.model.CDAccoutType;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;

public class AccountDto implements IDto
{

	private Pager pager;
	private List<CDAccoutType> list;
	private CDAccoutType act;

	public List<CDAccoutType> getList()
	{
		return list;
	}

	public void setList(List<CDAccoutType> list)
	{
		this.list = list;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public CDAccoutType getAct()
	{
		return act;
	}

	public void setAct(CDAccoutType act)
	{
		this.act = act;
	}

}
