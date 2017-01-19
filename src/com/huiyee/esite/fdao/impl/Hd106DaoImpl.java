package com.huiyee.esite.fdao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.IHd106Dao;
import com.huiyee.esite.fdao.impl.Hd113DaoImpl.HdModelRowmapper;
import com.huiyee.esite.model.HD106Model;
import com.huiyee.esite.model.HdModel;

public class Hd106DaoImpl extends AbstractDao implements IHd106Dao {

	private static final Long HDID = 106L;
	private static final String HDNAME="ÉÏ´«Í¼Æ¬";
	
	private static final String FIND_HD_MODEL="select uu.id hdfid, uu.name name,count(ur.id) total,max(ur.uploadtime) lasttime from esite.es_site s join esite.es_page p on p.siteid = s.id join esite.es_feature_dynamic_action_record r on r.pageid = p.id join esite.es_feature_user_upload_record ur on ur.id = r.entityid join esite.es_feature_user_upload uu on uu.id = ur.uploadid where s.sitegroupid = ? and r.action = ? group by uu.id";
	@Override
	public List<HdModel> findHdModel(long sitegroupid) {
		Object[] params={sitegroupid,HDID};
		return getJdbcTemplate().query(FIND_HD_MODEL, params, new HdModelRowmapper());
	}
	
	private static final String FIND_HD_MODEL_NAME="select uu.name name from esite.es_site s join esite.es_page p on p.siteid = s.id join esite.es_feature_dynamic_action_record r on r.pageid = p.id join esite.es_feature_user_upload_record ur on ur.id = r.entityid join esite.es_feature_user_upload uu on uu.id = ur.uploadid where s.sitegroupid = ? and r.action = ? and r.entityid=? ";
    @Override
    public HdModel findHdModelName(long sitegroupid,long entityid) {
        Object[] params={sitegroupid,HDID,entityid};
        List<HdModel> list=getJdbcTemplate().query(FIND_HD_MODEL_NAME, params, new HdModelNameRowmapper());
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

	private static final String FIND_HD_DETAIL="select i.wbuid,i.nickname,r.* from esite.es_feature_user_upload_record r join esite.es_feature_user_upload u on r.uploadid = u.id join esite.es_user_info i on r.uid = i.id where u.id = ? limit ?,?";
	@Override
	public List<HD106Model> findHdDetail(long hdfid, int start, int size) {
		Object[] params={hdfid,start,size};
		return getJdbcTemplate().query(FIND_HD_DETAIL, params, new HD106ModelRowmapper());
	}
	
	class HD106ModelRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			HD106Model m = new HD106Model();
			m.setWbuid(rs.getLong("wbuid"));
			m.setNickname(rs.getString("nickname"));
			m.setBimg(rs.getString("bigimg"));
			m.setMimg(rs.getString("midimg"));
			m.setSimg(rs.getString("smallimg"));
			m.setIp(rs.getString("ip"));
			m.setUploadtime(rs.getTimestamp("uploadtime"));
			m.setSource(rs.getString("source"));
			m.setTerminal(rs.getString("terminal"));
			return m;
		}
	}

	private static final String FIND_HD_DETAIL_TOTAL="select count(r.id) from esite.es_feature_user_upload_record r join esite.es_feature_user_upload u on r.uploadid = u.id join esite.es_user_info i on r.uid = i.id where u.id = ?";
	@Override
	public int findHdDetailTotal(long hdfid) {
		Object[] params={hdfid};
		return getJdbcTemplate().queryForInt(FIND_HD_DETAIL_TOTAL, params);
	}

}
