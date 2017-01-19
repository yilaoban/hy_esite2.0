package com.huiyee.esite.mgr;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Page;

public interface IWeiKaQuanMgr {

	public int saveWkqShopOwner(long wxuid, long owner);

	public IDto findContentProductList(long owner, int pageId);

	public IDto findPayOrderListByProductid(long productid, int pageId);

	public IDto findPayOrderGoods(long id);

	public int saveWKQ(long pageid, long owner);

	public IDto findWKQShopOwnerByOwner(long owner, int pageId);

	public int delShopOwner(long wxuid, long owner);

	public Page findWKQByOwner(long owner);

}
