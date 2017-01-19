package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.ISinaShareDao;
import com.huiyee.esite.model.SinaShare;

public class SinaShareDaoImpl extends AbstractDao implements ISinaShareDao {

	@Override
	public List<SinaShare> findTask()
	{
		return getJdbcTemplate().query("select * from esite.es_feature_sina_share where status='N' or status='I'", new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				SinaShare ss=new SinaShare();
				ss.setId(rs.getLong("id"));
				ss.setName(rs.getString("name"));
				return ss;
			}
			
		});
	}

	@Override
	public void updateStatus(long id, String status)
	{
      getJdbcTemplate().update("update esite.es_feature_sina_share set status=?,lastupdatetime=now() where id=?", new Object[]{status,id});		
	}

	private static final String UPDATE_SHARE_COUNT="update esite.es_feature_sina_share set sharecount = sharecount + 1 where id = ?";
	@Override
	public int updateShareCount(long shareid) {
		Object[] params={shareid};
		return getJdbcTemplate().update(UPDATE_SHARE_COUNT,params);
	}
	
	private static final String FIND_SINA_SHARE_BY_SITEGROUP="select share.*  from es_site_group  sg join es_site site on site.sitegroupid=sg.id join es_page page on page.siteid=site.id join es_feature_sina_share share on share.pageid=page.id where sg.id=? and sg.ownerid=?";
	@Override
	public List<SinaShare> findSinaShareBySiteGroup(long sitegroupid, long owner) {
		return getJdbcTemplate().query(FIND_SINA_SHARE_BY_SITEGROUP, new Object[]{sitegroupid,owner}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SinaShare ss=new SinaShare();
				ss.setId(rs.getLong("id"));
				ss.setName(rs.getString("name"));
				ss.setLastupdatetime(rs.getTimestamp("lastupdatetime"));
				ss.setStatus(rs.getString("status"));
				ss.setPageid(rs.getLong("pageid"));
				ss.setSharecount(rs.getInt("sharecount"));
				return ss;
			}
		});
	}

	private static final String FIND_SINA_SHARE_TOTAL = "select sharecount from esite.es_feature_sina_share where id = ? ";
	@Override
	public int findSinaShareTotal(long shareid) {
		Object[] parasm={shareid};
		return getJdbcTemplate().queryForInt(FIND_SINA_SHARE_TOTAL, parasm);
	}

	private static final String FIND_SINA_SHARE="select * from esite.es_feature_sina_share where id = ?";
	@Override
	public SinaShare findSinaShareById(long shareid) {
		Object[] params={shareid};
		List<SinaShare> list = getJdbcTemplate().query(FIND_SINA_SHARE,params, new SinaShareRowmapper());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	class SinaShareRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SinaShare ss=new SinaShare();
			ss.setId(rs.getLong("id"));
			ss.setName(rs.getString("name"));
			ss.setLastupdatetime(rs.getTimestamp("lastupdatetime"));
			ss.setStatus(rs.getString("status"));
			ss.setPageid(rs.getLong("pageid"));
			ss.setSharecount(rs.getInt("sharecount"));
			ss.setSoauthurl(rs.getString("soauthurl"));
			ss.setCoauthurl(rs.getString("coauthurl"));
			ss.setOldurl(rs.getString("oldurl"));
			return ss;
		}
		
	}


}
