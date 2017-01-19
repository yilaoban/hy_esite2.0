package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IPageFeatureDao;
import com.huiyee.esite.model.PageFeature;

public class PageFeatureDaoImpl extends AbstractDao implements IPageFeatureDao {

	private static final String FIND_MAX_INX="select max(idx) from esite.es_page_feature where pageid = ?";
	private int findMaxInx(long pageid){
		return getJdbcTemplate().queryForInt(FIND_MAX_INX, new Object[]{pageid});
	}
	
	private static final String FIND_INX_BY_PFID="select pageid,idx from esite.es_page_feature where id = ?";
	private Map findInx(long pfid){
		try {
			return getJdbcTemplate().queryForMap(FIND_INX_BY_PFID, new Object[]{pfid});
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	private static final String ADD_INX_PAGEID="update esite.es_page_feature set idx=idx+1 where idx > ? and pageid = ?";
	private int addInx(long idx,long pageid){
		return getJdbcTemplate().update(ADD_INX_PAGEID, new Object[]{idx,pageid});
	}
	private static final String UPDATE_NAME="update esite.es_page_feature set name=? where id= ?";
    public int updateName(long pfid,String name){
        return getJdbcTemplate().update(UPDATE_NAME, new Object[]{name,pfid});
    }
	
	private static final String DELETE_INX_PAGEID="update esite.es_page_feature set idx=idx-1 where idx > ? and pageid = ?";
	private int deleteInx(long idx,long pageid){
		return getJdbcTemplate().update(DELETE_INX_PAGEID, new Object[]{idx,pageid});
	}
	
	private static final String ADD_PAGE_FEATURE="insert into esite.es_page_feature(pageid,featureid,fid,status,createtime,updatetime,idx,name,needuser) values(?,?,?,'EDT',now(),now(),?,?,?)";
	@Override
	public long addPageFeature(final long pageid,final long featureid,final long fid,final String featurename,final String needUser) {
		final int idx = findMaxInx(pageid);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(ADD_PAGE_FEATURE,
						new String[] { "id" });
				int i = 1;
				ps.setLong(i++, pageid);
				ps.setLong(i++, featureid);
				ps.setLong(i++,fid );
				ps.setLong(i++,idx+1 );
				ps.setString(i++, featurename);
				ps.setString(i++, needUser);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
		
	}
	
	private static final String DELETE_PAGE_FEATURE="delete from esite.es_page_feature where id = ?";
	@Override
	public int deletePageFeature(long pfid) {
		Object[] params={pfid};
		Map map = findInx(pfid);
		int result = getJdbcTemplate().update(DELETE_PAGE_FEATURE,params);
		deleteInx((Integer)map.get("idx"),(Long)map.get("pageid"));
		return result;
	}
	
	private static final String UPDATE_PAGE_FEATURE_UP="update esite.es_page_feature set idx = idx + 1 where pageid = ? and idx = ?";
	private static final String UP_PAGE_FEATURE="update esite.es_page_feature set idx = idx -1 where id = ?";
	@Override
	public int updateUpPageFeature(long pfid) {
		Object[] params={pfid};
		Map map = findInx(pfid);
		getJdbcTemplate().update(UPDATE_PAGE_FEATURE_UP,new Object[]{(Long)map.get("pageid"),(Integer)map.get("idx")-1});
		int result = getJdbcTemplate().update(UP_PAGE_FEATURE,params);
		return result;
	}
	
	private static final String UPDATE_PAGE_FEATURE_DOWN="update esite.es_page_feature set idx = idx - 1 where pageid = ? and idx = ?";
	private static final String DOWN_PAGE_FEATURE="update esite.es_page_feature set idx = idx + 1 where id = ?";
	@Override
	public int updateDownPageFeature(long pfid) {
		Object[] params={pfid};
		Map map = findInx(pfid);
		getJdbcTemplate().update(UPDATE_PAGE_FEATURE_DOWN,new Object[]{(Long)map.get("pageid"),(Integer)map.get("idx")+1});
		int result = getJdbcTemplate().update(DOWN_PAGE_FEATURE,params);
		return result;
	}
	
	private static final String FIND_OWNERID_BY_FID="select ownerid from esite.es_page_feature pf join esite.es_page p on pf.pageid=p.id join esite.es_site s on p.siteid = s.id where pf.pageid= ? and pf.fid = ?;";
	@Override
	public long findOwneridByPageidAndFid(long pageid,long fid) {
		Object[] params={pageid,fid};
		try {
			return getJdbcTemplate().queryForLong(FIND_OWNERID_BY_FID, params);
		} catch (DataAccessException e) {
			return 0;
		}
	}
	
	@Override
	public long addPageFeature(final long pageid,final long featureid,final long fid) {
		final String sql="insert into esite.es_page_feature(pageid,featureid,fid,status,createtime,updatetime,idx) values(?,?,?,'EDT',now(),now(),?)";
		final int idx = findMaxInx(pageid);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql,
						new String[] { "id" });
				int i = 1;
				ps.setLong(i++, pageid);
				ps.setLong(i++, featureid);
				ps.setLong(i++,fid );
				ps.setLong(i++,idx+1 );
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
		
	}

	private static final String FIND_PAGE_FEATURE="select * from es_page_feature where id = ?";
	@Override
	public PageFeature findPageFeature(long pfid){
		Object[] param={pfid};
		List<PageFeature> list = getJdbcTemplate().query(FIND_PAGE_FEATURE, param,new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException{
				PageFeature pf = new PageFeature();
				pf.setId(rs.getLong("id"));
				pf.setPageid(rs.getLong("pageid"));
				pf.setFeatureid(rs.getLong("featureid"));
				pf.setFid(rs.getLong("fid"));
				return pf;
			}
			
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<Long> findPfidByPageId(long np, int featureid)
	{
		return getJdbcTemplate().query("select id from es_page_feature where pageid=? and featureid=?",new Object[]{np,featureid},new RowMapper(){
			@Override
			public Object mapRow(ResultSet arg0, int arg1) throws SQLException
			{
				return arg0.getLong("id");
			}
		});
	}
	
}
