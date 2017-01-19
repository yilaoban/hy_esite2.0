package com.huiyee.esite.fdao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.IHd103Dao;
import com.huiyee.esite.fdao.impl.Hd113DaoImpl.HdModelRowmapper;
import com.huiyee.esite.model.HD103Model;
import com.huiyee.esite.model.HdModel;

public class Hd103DaoImpl extends AbstractDao implements IHd103Dao{

	private static final Long HDID = 103L;
	private static final String HDNAME="до";
	
	private static final String FIND_HD_MODEL_LIST="select pl.id hdfid, p.name name ,count(r.id) total, max(z.createtime) lasttime from esite.es_site s join esite.es_page p on s.id= p.siteid join esite.es_feature_dynamic_action_record r on r.pageid = p.id join esite.es_feature_product_zan z on r.entityid = z.id join esite.es_feature_prolist_product pp on z.zanproid = pp.id join esite.es_feature_prolist fp on pp.prolistid = fp.id join esite.es_feature_prolist_list pl on fp.prolist_listid = pl.id where s.sitegroupid = ? and r.action = ? group by pl.id";
	@Override
	public List<HdModel> findHdModelList(long sitegroupid) {
		Object[] params={sitegroupid,HDID};
		return getJdbcTemplate().query(FIND_HD_MODEL_LIST, params, new HdModelRowmapper());
	}
	
	private static final String FIND_HD_MODEL="select  pl.name name  from esite.es_site s join esite.es_page p on s.id= p.siteid join esite.es_feature_dynamic_action_record r on r.pageid = p.id join esite.es_feature_product_zan z on r.entityid = z.id join esite.es_feature_prolist_product pp on z.zanproid = pp.id join esite.es_feature_prolist fp on pp.prolistid = fp.id join esite.es_feature_prolist_list pl on fp.prolist_listid = pl.id where s.sitegroupid = ? and r.action = ? and r.entityid=?";
    @Override
    public HdModel findHdModel(long sitegroupid,long entityid) {
        Object[] params={sitegroupid,HDID,entityid};
        List<HdModel> list= getJdbcTemplate().query(FIND_HD_MODEL, params, new HdModelNameRowmapper());
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

	private static final String FIND_HD_DETAIL_TOTAL="select count(distinct pp.productid) from esite.es_feature_prolist fp join esite.es_feature_prolist_product pp on fp.id = pp.prolistid join esite.es_feature_product_zan z on pp.id=z.zanproid join esite.es_content_product cp on pp.productid = cp.id where fp.prolist_listid = ?";
	@Override
	public int findHdDetailTotal(long hdfid) {
		Object[] params={hdfid};
		return getJdbcTemplate().queryForInt(FIND_HD_DETAIL_TOTAL,params);
	}
	
	private static final String FIND_HD_DETAIL="select pp.productid,cp.name,count(z.uid) total,max(z.createtime) lasttime from esite.es_feature_prolist fp join esite.es_feature_prolist_product pp on fp.id = pp.prolistid join esite.es_feature_product_zan z on pp.id=z.zanproid join esite.es_content_product cp on pp.productid = cp.id where fp.prolist_listid = ? group by pp.productid order by total desc limit ?,?";
	@Override
	public List<HD103Model> findHdDetail(long hdfid, int start, int size) {
		Object[] params={hdfid,start,size};
		return getJdbcTemplate().query(FIND_HD_DETAIL, params, new Hd103ModelRowmapper());
	}
	
	class Hd103ModelRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			HD103Model m = new HD103Model();
			m.setProductid(rs.getLong("productid"));
			m.setProductname(rs.getString("name"));
			m.setTotal(rs.getInt("total"));
			m.setLasttime(rs.getTimestamp("lasttime"));
			return m;
		}
		
	}
}
