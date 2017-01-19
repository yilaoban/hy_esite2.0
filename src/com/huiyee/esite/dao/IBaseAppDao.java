
package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.interact.cb.model.InteractCb;

public interface IBaseAppDao
{

	public int findFormTotal(long owner, long accid);

	public List<InteractCb> findCbList(long ownerid);

}
