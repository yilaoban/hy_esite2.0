package com.huiyee.esite.fdao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.IHd111Dao;
import com.huiyee.esite.model.HD111Model;
import com.huiyee.esite.model.HdModel;

public class Hd111DaoImpl extends AbstractDao implements IHd111Dao{
	
	private static final Long HDID=111L;
	private static final String HDNAME="–¬¿À ⁄»®";
	
	private static final String FIND_HD_MODEL="select s.id hdfid, s.name name,count(ap.id) total,max(ap.createtime) lasttime from esite.es_site s join esite.es_page p on p.siteid = s.id join esite.es_feature_dynamic_action_record r on r.pageid = p.id join esite.es_feature_sina_user_app ap on r.entityid = ap.id where s.sitegroupid = ? and r.action = ?";
	@Override
	public List<HdModel> findHdModel(long sitegroupid) {
		Object[] params={sitegroupid,HDID};
		return getJdbcTemplate().query(FIND_HD_MODEL, params, new HdModelRowmapper());
	}
	
	private static final String FIND_HD_MODEL_NAME="select s.id hdfid, s.name name,count(ap.id) total,max(ap.createtime) lasttime from esite.es_site s join esite.es_page p on p.siteid = s.id join esite.es_feature_dynamic_action_record r on r.pageid = p.id join esite.es_feature_sina_user_app ap on r.entityid = ap.id join es_sina_app sa on ap.appid = sa.id where s.sitegroupid = ? and r.action = ? and r.entityid=?";
    @Override
    public HdModel findHdModelName(long sitegroupid,long entityid) {
        Object[] params={sitegroupid,HDID,entityid};
        List<HdModel> list= getJdbcTemplate().query(FIND_HD_MODEL_NAME, params, new HdModelNameRowmapper());
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
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
	class HdModelNameRowmapper implements RowMapper{
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            HdModel hm = new HdModel();
            hm.setName(rs.getString("name"));
            return hm;
        }
    }

	private static final String FIND_HD_DETAIL="select u.wbuid,u.nickname,s.name,ua.createtime ,ua.updatetime ,ua.ip,ua.terminal from esite.es_feature_sina_user_app ua join esite.es_feature_sina_user u on ua.wbuid = u.wbuid join esite.es_site s on ua.siteid = s.id where s.id = ? order by ua.updatetime desc limit ?,?";
	@Override
	public List<HD111Model> findHdDetail(long hdfid, int start, int size) {
		Object[] params={hdfid,start,size};
		return getJdbcTemplate().query(FIND_HD_DETAIL, params, new HD111ModelRowmapper());
	}
	
	class HD111ModelRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			HD111Model m = new HD111Model();
			m.setWbuid(rs.getLong("wbuid"));
			m.setNickname(rs.getString("nickname"));
			m.setSitename(rs.getString("name"));
			m.setCreatetime(rs.getTimestamp("createtime"));
			m.setUpdatetime(rs.getTimestamp("updatetime"));
			m.setIp(rs.getString("ip"));
			m.setTerminal(rs.getString("terminal"));
			return m;
		}
		
	}
	

	private static final String FIND_HD_DETAIL_TOTAL="select count(ua.id) from esite.es_feature_sina_user_app ua join esite.es_feature_sina_user u on ua.wbuid = u.wbuid join esite.es_site s on ua.siteid = s.id where s.id = ?";
	@Override
	public int findHdDetailTotal(long hdfid) {
		Object[] params={hdfid};
		return getJdbcTemplate().queryForInt(FIND_HD_DETAIL_TOTAL, params);
	}
}
