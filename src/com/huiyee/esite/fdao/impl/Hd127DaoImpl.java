package com.huiyee.esite.fdao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.IHd127Dao;
import com.huiyee.esite.model.HdModel;

public class Hd127DaoImpl extends AbstractDao implements IHd127Dao {

	private static final Long HDID = 127L;
	private static final String HDNAME="…Í«Î";
	private static final String FIND_HD_MODEL="select c.id hdfid, c.name,count(r.id) total,max(r.createtime) lasttime from esite.es_site s join esite.es_page p on p.siteid = s.id join esite.es_feature_dynamic_action_record hd on hd.pageid = p.id join esite.es_feature_interact_apt_record r on hd.entityid = r.id left join es_feature_interact_apt c on c.id=r.aptid  where s.sitegroupid = ? and action = ? group by c.id order by c.id desc";
	@Override
	public List<HdModel> findHdModel(long sitegroupid){
		Object[] params={sitegroupid,HDID};
		return getJdbcTemplate().query(FIND_HD_MODEL, params, new HdModelRowmapper());
	}
	
	class HdModelRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			HdModel hm = new HdModel();
			hm.setName(rs.getString("name"));
			hm.setTotal(rs.getInt("total"));
			hm.setLasttime(rs.getTimestamp("lasttime"));
			hm.setHdfid(rs.getLong("hdfid"));
			hm.setType(HDNAME);
			hm.setHdid(HDID);
			return hm;
		}
	}

	private static final String FIND_HD_DETAIL_TOTAL="select count(r.id) from esite.es_feature_interact_apt_record r join esite.es_feature_interact_apt c on r.aptid = c.id  where c.id = ?";
	@Override
	public int findHdDetailTotal(long hdfid) {
		Object[] params={hdfid};
		return getJdbcTemplate().queryForInt(FIND_HD_DETAIL_TOTAL, params);
	}
	
	private static final String FIND_HD_MODEL_NAME="select apt.title from esite.es_site_group sg join esite.es_site es on sg.id = es.sitegroupid join esite.es_page p on p.siteid = es.id join esite.es_feature_interact_apt_record r on r.pageid = p.id join esite.es_interact_apt apt on apt.id = r.aptid where sg.id = ? and r.id = ?";
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
