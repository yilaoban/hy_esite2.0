package project.caidan.mgr;

import java.util.List;

import project.caidan.model.CDAccountCpz;
import project.caidan.model.CDProductRmb;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.ProductCode;
import com.huiyee.esite.model.VisitUser;


public interface ICaiDanProductMgr
{
	public IDto findProductList(long owner,long hyuid,int size);
	
	public IDto findCdProductById(long owner,long hyuid);
	
	public IDto findWxUser(long wxuid);
	
	public IDto findOrderList(long hyuid,String status,int size,int type );
	
	public IDto findCdWxUser(long hyuid);
	
	public IDto findCdOrderById(long hyuid,String status,String type,long id );
	
	public IDto savePayMoney(long payOrderid,long hyuid,long owner,VisitUser vu,String url,String ip);
	
	public int saveCdRmbget(long wxuid,long hyuid,float rmb);

	public List<CDAccountCpz> findCouponArea(long pid, int pageId, int size);

	public CDProductRmb findProductById(long id);

	public int findProductCodeLess(long productid);
	
	public int findRemianJf(long hyuid,int jf);

	public ProductCode findProductCode(long payOrderid);

	public CDProductRmb findProductByProductid(long productid);
	
}
