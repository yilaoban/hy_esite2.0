package com.huiyee.esite.fdao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.IHd131Dao;
import com.huiyee.esite.model.HdModel;

public class Hd131DaoImpl extends AbstractDao implements IHd131Dao{

	private static final String FIND_HD_MODEL_NAME="select ia.title from es_site_group g join es_site s on g.id = s.sitegroupid join es_page p on p.siteid = s.id join es_feature_interact_auction fa on p.id=fa.pageid join es_interact_auction ia on fa.auctionid=ia.id where  fa.id=? and g.id=?";
    @Override
    public HdModel findHdModelName(long sitegroupid,long entityid) {
        Object[] params={entityid,sitegroupid};
        List<HdModel> list= getJdbcTemplate().query(FIND_HD_MODEL_NAME, params, new HdModelNameRowmapper());
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
    
	class HdModelNameRowmapper implements RowMapper{
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            HdModel hm = new HdModel();
            hm.setName(rs.getString("ia.title"));
            return hm;
        }
    }
}
