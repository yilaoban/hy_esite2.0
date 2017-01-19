package com.huiyee.esite.fdao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.IHd129Dao;
import com.huiyee.esite.model.HdModel;

public class Hd129DaoImpl extends AbstractDao implements IHd129Dao {
	private static final Long HDID = 129L;
	private static final String HDNAME="Еїба";
	private static final String FIND_HD_MODEL_NAME="select v.title from esite.es_site_group sg join esite.es_site es on sg.id = es.sitegroupid join esite.es_page p on p.siteid = es.id join esite.es_feature_interact_research_record r on r.pageid = p.id join esite.es_interact_research v on v.id = r.searchid where sg.id = ? and r.id = ?";
	@Override
	public HdModel findHdModelName(long sitegroupid, long entityid)	{
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
