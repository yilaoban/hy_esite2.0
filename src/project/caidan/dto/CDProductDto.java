package project.caidan.dto;

import java.util.List;

import project.caidan.model.CDAccountCpz;
import project.caidan.model.CDProductRmb;
import project.caidan.model.CDRmb;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.ProductCode;
import com.huiyee.esite.model.WxUser;


public class CDProductDto implements IDto
{
	private List<CDProductRmb> list;
	private CDRmb cdRmb;
	private CDProductRmb cdProduct;
	private WxUser wxUser;
	private List<CDProductRmb> payOrderList;
	private List<CDAccountCpz> cpz;
	private int jf;
	
	private String hydesc;
	private int status;//1-成功；-1-商品不存在；-2-商品没有库存；-3-积分不足 
	private ProductCode code;
	
	public String getHydesc()
	{
		return hydesc;
	}


	
	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}


	
	public int getStatus()
	{
		return status;
	}


	
	public void setStatus(int status)
	{
		this.status = status;
	}


	public List<CDProductRmb> getPayOrderList()
	{
		return payOrderList;
	}

	
	public void setPayOrderList(List<CDProductRmb> payOrderList)
	{
		this.payOrderList = payOrderList;
	}

	public WxUser getWxUser()
	{
		return wxUser;
	}
	
	public void setWxUser(WxUser wxUser)
	{
		this.wxUser = wxUser;
	}


	public CDProductRmb getCdProduct()
	{
		return cdProduct;
	}

	
	public void setCdProduct(CDProductRmb cdProduct)
	{
		this.cdProduct = cdProduct;
	}

	public List<CDProductRmb> getList()
	{
		return list;
	}
	
	public void setList(List<CDProductRmb> list)
	{
		this.list = list;
	}
	
	public CDRmb getCdRmb()
	{
		return cdRmb;
	}
	
	public void setCdRmb(CDRmb cdRmb)
	{
		this.cdRmb = cdRmb;
	}


	
	public int getJf()
	{
		return jf;
	}


	
	public void setJf(int jf)
	{
		this.jf = jf;
	}



	
	public List<CDAccountCpz> getCpz()
	{
		return cpz;
	}



	
	public void setCpz(List<CDAccountCpz> cpz)
	{
		this.cpz = cpz;
	}



	
	public ProductCode getCode()
	{
		return code;
	}



	
	public void setCode(ProductCode code)
	{
		this.code = code;
	}
	
	
	
}
