
package project.caidan.dto;

import java.util.List;

import project.caidan.model.CDWay;
import project.caidan.model.CDWayCatalog;
import project.caidan.model.Coupon;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.Account;

public class WayDto implements IDto
{

	private List<CDWayCatalog> catalogs;
	private Pager pager;
	private List<CDWay> ways;

	public List<CDWayCatalog> getCatalogs()
	{
		return catalogs;
	}

	public void setCatalogs(List<CDWayCatalog> catalogs)
	{
		this.catalogs = catalogs;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public List<CDWay> getWays()
	{
		return ways;
	}

	public void setWays(List<CDWay> ways)
	{
		this.ways = ways;
	}

}
