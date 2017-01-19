package com.huiyee.esite.fdao;

import java.util.List;
import com.huiyee.esite.model.SinaShare;

public interface ISinaShareDao {

    public long addFeatureSinaShare(long pageid);

    public SinaShare findFeatureSinaShareById(long id);

    public int updateFeatureSinaShare(SinaShare share);

    public List<SinaShare> findSinaShareListByPageId(long pageid);

}
