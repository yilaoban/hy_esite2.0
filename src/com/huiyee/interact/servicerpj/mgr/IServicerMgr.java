
package com.huiyee.interact.servicerpj.mgr;

import java.util.List;

import com.huiyee.interact.yuyue.model.YuYueServicer;

public interface IServicerMgr {

	public int findServicerTotal(long owner);

	public List<YuYueServicer> findServicerList(long owner, int start, int rows);

}
