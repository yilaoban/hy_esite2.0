package com.huiyee.esite.fdao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.IHd133Dao;
import com.huiyee.esite.model.HdModel;

public class Hd133DaoImpl extends AbstractDao implements IHd133Dao{

	private static final String FIND_HD_MODEL_NAME="select t.nickname from es_site_group g join es_site s on g.id = s.sitegroupid join es_page p on p.siteid = s.id join es_interact_checkin_record r on p.id= r.pageid join es_sina_token t on (r.ownerwbuid = t.cid and s.id = t.siteid) where g.id =? and r.id = ?";
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
            hm.setName(rs.getString("t.nickname"));
            return hm; 
        }
    }
	
	
}
