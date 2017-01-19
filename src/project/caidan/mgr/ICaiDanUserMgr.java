package project.caidan.mgr;

import java.util.List;

import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.OrderGoods;

public interface ICaiDanUserMgr {

	public int findHyUserCount(long owner, HyUser filter);

	public List<HyUser> findHyUserList(long owner, HyUser hyUser);
	
	public List<HyUser> findHyUserList(long owner, HyUser filter, int start, int rows);

	public int findOrderGoodsCount(long hyuid);

	public List<OrderGoods> findOrderGoodsList(long hyuid, int start, int rows);

	public HyUser findHyUser(long owner, long wxuid);

	public int updateTelByWxuid(long owner, long wxuid, String telphone);


}
