
package project.caidan.model;

import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.ContentNew;

public class CDNewsDto implements IDto
{

	private List<CDNews> list;
	private Pager pager;

	public List<CDNews> getList()
	{
		return list;
	}

	public void setList(List<CDNews> list)
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
}
