package com.huiyee.esite.fdao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.IHd117Dao;
import com.huiyee.esite.model.HD117Model;
import com.huiyee.esite.model.Hd117QueryObject;
import com.huiyee.esite.model.HdModel;

public class Hd117DaoImpl extends AbstractDao implements IHd117Dao{

	private static final Long HDID = 117L;
	private static final String HDNAME="ת������";
	private static final String FIND_HD_MODEL="select ss.id hdfid, ss.name,count(r.id) total,max(r.createtime) lasttime from esite.es_site s join esite.es_page p on p.siteid = s.id join esite.es_feature_dynamic_action_record hd on hd.pageid = p.id join esite.es_feature_sina_forward_record r on hd.entityid = r.id join esite.es_feature_sina_share ss on r.shareid = ss.id where s.sitegroupid = ? and action = ? group by ss.id order by ss.id desc";
	@Override
	public List<HdModel> findHdModel(long sitegroupid) {
		Object[] params={sitegroupid,HDID};
		return getJdbcTemplate().query(FIND_HD_MODEL, params, new HdModelRowmapper());
	}
	private static final String FIND_HD_MODEL_NAME="select ss.id hdfid, ss.name,count(r.id) total,max(r.createtime) lasttime from esite.es_site s join esite.es_page p on p.siteid = s.id join esite.es_feature_dynamic_action_record hd on hd.pageid = p.id join esite.es_feature_sina_forward_record r on hd.entityid = r.id join esite.es_feature_sina_share ss on r.shareid = ss.id where s.sitegroupid = ? and action = ? and hd.entityid=?";
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

	private static final String FIND_HD_DETAIL="select u.imageurl, u.nickname,r.* from esite.es_feature_sina_forward_record r join esite.es_feature_sina_share s on r.shareid = s.id join esite.es_feature_sina_user u on r.wbuid = u.wbuid where s.id = ? order by r.id desc limit ?,?";
	@Override
	public List<HD117Model> findHdDetail(long hdfid, int start, int size) {
		Object[] params={hdfid,start,size};
		return getJdbcTemplate().query(FIND_HD_DETAIL, params, new HD117ModelRowmapper());
	}
	
	class HD117ModelRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			HD117Model m = new HD117Model();
			m.setWbuid(rs.getLong("wbuid"));
			m.setNickname(rs.getString("nickname"));
			m.setImageurl(rs.getString("imageurl"));
			m.setContent(rs.getString("content"));
			m.setRepostsCount(rs.getInt("repostsCount"));
			m.setCommentsCount(rs.getInt("commentsCount"));
			m.setAttitudesCount(rs.getInt("attitudesCount"));
			m.setTerminal(rs.getString("terminal"));
			m.setWbid(rs.getLong("wbid"));
			m.setMid(rs.getLong("mid"));
			m.setCreatetime(rs.getTimestamp("createtime"));
			return m;
		}
		
	}
	

	private static final String FIND_HD_DETAIL_TOTAL="select count(*) from esite.es_feature_sina_forward_record r join esite.es_feature_sina_share s on r.shareid = s.id join esite.es_feature_sina_user u on r.wbuid = u.wbuid where s.id = ?";
	@Override
	public int findHdDetailTotal(long hdfid) {
		Object[] params={hdfid};
		return getJdbcTemplate().queryForInt(FIND_HD_DETAIL_TOTAL, params);
	}
	
	@Override
	public List<HD117Model> findHdDetail(long hdfid,
			Hd117QueryObject queryObject, int start, int size) {
		String sql = "select u.imageurl, u.nickname,r.* from esite.es_feature_sina_forward_record r join esite.es_feature_sina_share s on r.shareid = s.id join esite.es_feature_sina_user u on r.wbuid = u.wbuid where s.id = "+hdfid;
		if(!StringUtils.isEmpty(queryObject.getStarttime())){
			sql+=" and r.createtime >= '"+ queryObject.getStarttime()+"'";
		}
		if(!StringUtils.isEmpty(queryObject.getEndtime())){
			sql+=" and r.createtime <= '"+ queryObject.getEndtime()+"'";
		}
		if(!StringUtils.isEmpty(queryObject.getStatus())){
			sql+=" and r.status = '"+queryObject.getStatus()+"'";
		}
		if(!StringUtils.isEmpty(queryObject.getTerminal())){
			sql+=" and r.terminal = '"+queryObject.getTerminal()+"'";
		}
		sql+=" order by r.id desc limit "+start+","+size;
		return getJdbcTemplate().query(sql, new HD117ModelRowmapper());
	}
	
	@Override
	public int findHdDetailTotal(long hdfid, Hd117QueryObject queryObject) {
		String sql = "select count(*) from esite.es_feature_sina_forward_record r join esite.es_feature_sina_share s on r.shareid = s.id join esite.es_feature_sina_user u on r.wbuid = u.wbuid where s.id = "+hdfid;
		if(!StringUtils.isEmpty(queryObject.getStarttime())){
			sql+=" and r.createtime >= '"+ queryObject.getStarttime()+"'";
		}
		if(!StringUtils.isEmpty(queryObject.getEndtime())){
			sql+=" and r.createtime <= '"+ queryObject.getEndtime()+"'";
		}
		if(!StringUtils.isEmpty(queryObject.getStatus())){
			sql+=" and r.status = '"+queryObject.getStatus()+"'";
		}
		if(!StringUtils.isEmpty(queryObject.getTerminal())){
			sql+=" and r.terminal = '"+queryObject.getTerminal()+"'";
		}
		return getJdbcTemplate().queryForInt(sql);
	}

}
