package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.SinaCheckList;
import com.huiyee.esite.model.SinaShare;

public interface ISinaShareListCheckDao {

    public long saveCheckList(long pageid);

    public SinaCheckList findShareid(long fid);

    public void updateShareId(long fid, long shareid);

}
