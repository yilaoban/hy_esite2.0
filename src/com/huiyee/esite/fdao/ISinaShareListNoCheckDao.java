package com.huiyee.esite.fdao;

import com.huiyee.esite.model.SinaCheckList;
import com.huiyee.esite.model.SinaNoCheckList;

public interface ISinaShareListNoCheckDao {

	public long save(long pageid);

	public SinaNoCheckList findShareid(long fid);

	public void updateSnl(SinaNoCheckList snl);

}
