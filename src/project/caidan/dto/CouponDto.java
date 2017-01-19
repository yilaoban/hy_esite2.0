
package project.caidan.dto;

import java.util.List;

import project.caidan.model.CDWay;
import project.caidan.model.CDWayCatalog;
import project.caidan.model.Coupon;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.ProductCode;

public class CouponDto implements IDto
{

	private List<Coupon> list;
	private Pager pager;
	private List<CDWay> ways;
	private List<CDWayCatalog> catalogs;
	private Coupon coupon;
	private List<ProductCode> codeList;

	public List<Coupon> getList()
	{
		return list;
	}

	public void setList(List<Coupon> list)
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

	public List<CDWay> getWays()
	{
		return ways;
	}

	public void setWays(List<CDWay> ways)
	{
		this.ways = ways;
	}

	public List<CDWayCatalog> getCatalogs()
	{
		return catalogs;
	}

	public void setCatalogs(List<CDWayCatalog> catalogs)
	{
		this.catalogs = catalogs;
	}

	public Coupon getCoupon()
	{
		return coupon;
	}

	public void setCoupon(Coupon coupon)
	{
		this.coupon = coupon;
	}

	
	public List<ProductCode> getCodeList()
	{
		return codeList;
	}

	
	public void setCodeList(List<ProductCode> codeList)
	{
		this.codeList = codeList;
	}
}
