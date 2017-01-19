package project.caidan.mgr;

import java.util.List;

import net.sf.json.JSONObject;
import project.caidan.model.CDWay;
import project.caidan.model.CDWayCatalog;
import project.caidan.model.CDWkqRecord;
import project.caidan.model.Coupon;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.ProductCode;


public interface ICouponMgr
{

	public IDto findCouponList(Account account, int pageid);

	public List<CDWayCatalog> findWayCatalogs(Account account);

	public long saveCoupon(Account account, Coupon coupon, ContentProduct product, JSONObject tagJson);

	public List<CDWay> findWayByCatalog(Account account, long catalog);

	public IDto findCouponByid(long pid, Account account);

	public int updateCoupon(Account account, Coupon coupon, ContentProduct product, JSONObject tagJson);

	public List<CDWkqRecord> findWkqRecord(long wxuid, long owner, int pageId, int size);
	
	public int addPrizeCode(List<ProductCode> list, long id);
	
	public IDto findProductCodeList(long pid, int pageId, String code);
	
	public int saveCode(long pid,String code,String password,long total);

	public int delCouponCode(long id, long owner);

	public int updateCodeClean(long productid, long owner);

}
