package com.huiyee.esite.fdao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.IHd128Dao;
import com.huiyee.esite.model.HdModel;

public class Hd128DaoImpl extends AbstractDao implements IHd128Dao {
	private static final Long HDID = 128L;
	private static final String HDNAME="ͶƱ";
	private static final String FIND_HD_MODEL_NAME="select v.title from esite.es_site_group sg join esite.es_site es on sg.id = es.sitegroupid join esite.es_page p on p.siteid = es.id join esite.es_feature_interact_vote_record r on r.pageid = p.id join esite.es_interact_vote v on v.id = r.voteid where sg.id = ? and r.id = ?";
	@Override
	public HdModel findHdModelName(long sitegroupid, long entityid) {
        Object[] params={sitegroupid,entityid};
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
            hm.setName(rs.getString("title"));
            return hm;
        }
    }
}
