package project.caidan.dao;

import java.util.List;

import com.huiyee.esite.model.ContentProduct;

import net.sf.json.JSONObject;
import project.caidan.model.CDWkqRecord;
import project.caidan.model.Coupon;


public interface ICouponDao
{

	public int findTotal(long ownerid);

	public List<Coupon> findList(long ownerid, int i, int j);

	public void saveCoupon(Coupon coupon);

	public Coupon findCouponByPid(long pid, long owner);

	public void updateCoupon(Coupon coupon, long id);

	public List<CDWkqRecord> findWkqRecord(long wxuid, int start, int size);

	public ContentProduct findProductByCodeid(long codeid, long owner);

	public int findCodeUsed(long codeid);

	public int delCouponCode(long codeid);

	public int updateCodeClean(long productid);

}
