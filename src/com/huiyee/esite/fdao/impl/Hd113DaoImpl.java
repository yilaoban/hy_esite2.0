package com.huiyee.esite.fdao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.IHd113Dao;
import com.huiyee.esite.model.HD113Model;
import com.huiyee.esite.model.Hd113QueryObject;
import com.huiyee.esite.model.HdModel;

public class Hd113DaoImpl extends AbstractDao implements IHd113Dao{

	private static final Long HDID = 113L;
	private static final String HDNAME="–¬¿À∑÷œÌ";
	private static final String FIND_HD_MODEL="select ss.id hdfid, ss.name,count(r.id) total,max(r.createtime) lasttime from esite.es_site s join esite.es_page p on p.siteid = s.id join esite.es_feature_dynamic_action_record hd on hd.pageid = p.id join esite.es_feature_sina_share_record r on hd.entityid = r.id join esite.es_feature_sina_share ss on r.shareid = ss.id where s.sitegroupid = ? and action = ? group by ss.id order by ss.id desc";
	@Override
	public List<HdModel> findHdModel(long sitegroupid) {
		Object[] params={sitegroupid,HDID};
		return getJdbcTemplate().query(FIND_HD_MODEL, params, new HdModelRowmapper());
	}
	private static final String FIND_HD_MODEL_NAME="select ss.id hdfid, ss.name,count(r.id) total,max(r.createtime) lasttime from esite.es_site s join esite.es_page p on p.siteid = s.id join esite.es_feature_dynamic_action_record hd on hd.pageid = p.id join esite.es_feature_sina_share_record r on hd.entityid = r.id join esite.es_feature_sina_share ss on r.shareid = ss.id where s.sitegroupid = ? and action = ? and hd.entityid=?";
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

	private static final String FIND_HD_DETAIL="select u.imageurl, u.nickname,r.* from esite.es_feature_sina_share_record r join esite.es_feature_sina_share s on r.shareid = s.id join esite.es_feature_sina_user u on r.wbuid = u.wbuid where s.id = ? limit ?,?";
	@Override
	public List<HD113Model> findHdDetail(long hdfid, int start, int size) {
		Object[] params={hdfid,start,size};
		return getJdbcTemplate().query(FIND_HD_DETAIL, params, new HD113ModelRowmapper());
	}
	
	class HD113ModelRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			HD113Model m = new HD113Model();
			m.setWbuid(rs.getLong("wbuid"));
			m.setNickname(rs.getString("nickname"));
			m.setImageurl(rs.getString("imageurl"));
			m.setContent(rs.getString("content"));
			m.setBimg(rs.getString("imgPath"));
			m.setMimg(rs.getString("mimgPath"));
			m.setSimg(rs.getString("mimgPath"));
			m.setCreatetime(rs.getTimestamp("createtime"));
			m.setRepostsCount(rs.getInt("repostsCount"));
			m.setCommentsCount(rs.getInt("commentsCount"));
			m.setAttitudesCount(rs.getInt("attitudesCount"));
			m.setTerminal(rs.getString("terminal"));
			m.setWbid(rs.getLong("wbid"));
			return m;
		}
		
	}
	

	private static final String FIND_HD_DETAIL_TOTAL="select count(*) from esite.es_feature_sina_share_record r join esite.es_feature_sina_share s on r.shareid = s.id join esite.es_feature_sina_user u on r.wbuid = u.wbuid where s.id = ?";
	@Override
	public int findHdDetailTotal(long hdfid) {
		Object[] params={hdfid};
		return getJdbcTemplate().queryForInt(FIND_HD_DETAIL_TOTAL, params);
	}
	
	@Override
	public List<HD113Model> findHdDetail(long hdfid,
			Hd113QueryObject queryObject, int start, int size) {
		String sql = "select u.imageurl, u.nickname,r.* from esite.es_feature_sina_share_record r join esite.es_feature_sina_share s on r.shareid = s.id join esite.es_feature_sina_user u on r.wbuid = u.wbuid where s.id = "+hdfid;
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
		sql+=" limit "+start+","+size;
		return getJdbcTemplate().query(sql, new HD113ModelRowmapper());
	}
	
	@Override
	public int findHdDetailTotal(long hdfid, Hd113QueryObject queryObject) {
		String sql = "select count(*) from esite.es_feature_sina_share_record r join esite.es_feature_sina_share s on r.shareid = s.id join esite.es_feature_sina_user u on r.wbuid = u.wbuid where s.id = "+hdfid;
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
