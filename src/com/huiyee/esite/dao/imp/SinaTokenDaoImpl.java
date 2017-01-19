package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.ISinaTokenDao;
import com.huiyee.esite.model.SinaApp;
import com.huiyee.esite.model.SinaToken;
import com.huiyee.tfmodel.HyWbUser;

public class SinaTokenDaoImpl extends AbstractDao implements ISinaTokenDao
{

	@Override
	public long findPageIdForPageShow(long cid, long appid)
	{
		List<Long> ls = getJdbcTemplate().query("select pageid from es_sina_token where cid=? and appid=?", new Object[]
		{ cid, appid }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("pageid");
			}

		});
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}
		return 0;
	}

	private static final String FIND_SINA_APP = "select * from esite.es_sina_app where status!='DEL'";

	@Override
	public List<SinaApp> findSinaApp()
	{
		return getJdbcTemplate().query(FIND_SINA_APP, new SinaAppRowmapper());
	}

	class SinaAppRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			SinaApp sa = new SinaApp();
			sa.setId(rs.getLong("id"));
			sa.setAppkey(rs.getLong("appkey"));
			sa.setAppsecret(rs.getString("appsecret"));
			sa.setCreatetime(rs.getTimestamp("createtime"));
			sa.setName(rs.getString("name"));
			sa.setOauthByCodeUrl(rs.getString("oauthByCodeUrl"));
			sa.setRefurl(rs.getString("refurl"));
			sa.setToken(rs.getString("token"));
			sa.setStatus(rs.getString("status"));
			return sa;
		}

	}

	private static final String ADD_SINA_TOKEN = "insert into esite.es_sina_token(appid,siteid,ownerid,createtime,updatetime)values(?,?,?,now(),now())";

	@Override
	public int addSinaToken(long siteid, long appid,long ownerid)
	{
		Object[] params =
		{ appid, siteid ,ownerid};
		return getJdbcTemplate().update(ADD_SINA_TOKEN, params);
	}
	
	
	@Override
	public int updatePublishSinaToken(long pageid,long siteid, long appid,long cid,long ownerid,String token,Date tokenendtime,String nickname)
	{
		String sql = "insert into esite.es_sina_token(token,tokenendtime,ownerid,appid,cid,createtime,updatetime,siteid,nickname,pageid)values(?,?,?,?,?,now(),now(),?,?,?) ON DUPLICATE KEY UPDATE appid=?,cid=?,token=?,tokenendtime=?,updatetime=now(),siteid=?,pageid=?,nickname=?";
		Object[] params =
		{token,tokenendtime,ownerid, appid, cid,siteid ,nickname,pageid,appid,cid,token,tokenendtime,siteid,pageid,nickname};
		return getJdbcTemplate().update(sql, params);
	}

	@Override
	public SinaApp findSinaAppById(Long appid)
	{
		List<SinaApp> ls = getJdbcTemplate().query("select * from esite.es_sina_app where id=?", new Object[]
		{ appid }, new SinaAppRowmapper());
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}
		return null;
	}

	@Override
	public void updateSinaToken(HyWbUser user, long appid, String token, Date tokenendtime, long siteid)
	{
		Object[] params =
		{ user.getId(), token, tokenendtime, user.getName(), siteid};
		getJdbcTemplate().update("update esite.es_sina_token set cid = ? ,token = ?, tokenendtime = ?,updatetime = now(),nickname = ? where siteid = ? and type='Z'", params);
//		getJdbcTemplate().update("insert into esite.es_sina_token (appid,cid,token,tokenendtime,createtime,updatetime,nickname,siteid) values (?,?,?,?,now(),now(),?,?) on duplicate key update nickname=?,token=?,tokenendtime=?,updatetime=now()", params);

	}
	
	private static final String UPDATE_CANCEL_SINATOKEN_CID="update es_sina_token set cid = null , token = null where appid = ? and cid = ?";
	@Override
	public int updateCancelSinaTokenCid(long appid,long cid){
		Object[] params={appid,cid};
		return getJdbcTemplate().update(UPDATE_CANCEL_SINATOKEN_CID,params);
	}
	@Override
	public int updatePageSubSina(long id,String subsina){
		String sql="update es_page set subsina=? where id= ?";
		Object[] params={subsina,id};
		return getJdbcTemplate().update(sql,params);
	}

	@Override
	public long findAppidBySiteid(long siteid)
	{
		List<Long> ls = getJdbcTemplate().query("select appid from es_sina_token where siteid=? and type='Z'", new Object[]
		{ siteid }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("appid");
			}

		});
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}
		return 0;
	}

	@Override
	public SinaToken findTokenBySiteid(long siteid)
	{
		List<SinaToken> ls = getJdbcTemplate().query("select * from es_sina_token where siteid=? and type = 'Z'", new Object[]
		{ siteid }, new SinaTokenRowMapper());
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}
		return null;
	}
	
	@Override
	public SinaToken findSinaTokenByAppidAndCid(long appid,long cid)
	{
		List<SinaToken> ls = getJdbcTemplate().query("select * from es_sina_token where appid=? and cid=? ", new Object[]
		{ appid,cid }, new SinaTokenRowMapper());
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}
		return null;
	}

	@Override
	public SinaToken findLastNewToken()
	{
		List<SinaToken> sinatokenlist = getJdbcTemplate().query("select * from es_sina_token where token is not null  and tokenendtime is not null and tokenendtime>now()  order by tokenendtime", new SinaTokenRowMapper());
		if (sinatokenlist != null && sinatokenlist.size() > 0)
			return sinatokenlist.get((int) (Math.random() * sinatokenlist.size()));
		return null;
	}

	private static final String FIND_SITE_WBUID="select siteid,cid from es_sina_token where cid is not null  and token is not null and tokenendtime is not null";
	@Override
	public Map<Long, Long> findSiteWbuid() {
		return getJdbcTemplate().queryForMap(FIND_SITE_WBUID);
	}

	private static final String FIND_RANDOM_TOKEN_FORM_SINA_APP="select id,token,tokenendtime from es_user_info where  tokenendtime > now() ORDER BY rand() limit 1";
	@Override
	public SinaToken findRandomSinaAppToken() {
		List<SinaToken> list = getJdbcTemplate().query(FIND_RANDOM_TOKEN_FORM_SINA_APP, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				SinaToken st = new SinaToken();
				st.setToken(rs.getString("token"));
				st.setTokenendtime(rs.getTimestamp("tokenendtime"));
				return st;
			}
			
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<SinaToken> findCidByOwner(long owner,int start,int size)
	{
		return getJdbcTemplate().query("select * from es_sina_token where ownerid=?  and cid is not null order by id desc limit ?,? ", new Object[]{owner,start,size}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				SinaToken st=new SinaToken();
				st.setCid(rs.getLong("cid"));
				st.setNickname(rs.getString("nickname"));
				return st;
			}
		});
	}

	@Override
	public int findCidTotal(long owner){
		String sql = "select count(*) from es_sina_token where ownerid=?  and cid is not null order by id desc";
		return getJdbcTemplate().queryForInt(sql,new Object[]{owner});
	}
	
	private static final String FIND_APP_SEC="select appsecret from esite.es_sina_app where id = ?";
	@Override
	public String findAppSecrectByAppid(long appid) {
		try {
			return (String) getJdbcTemplate().queryForObject(FIND_APP_SEC, new Object[]{appid}, String.class);
		} catch (DataAccessException e) {
			return null;
		}
	}


	@Override
	public SinaToken findSinaTokenCidByPageid(long pageid,long appid)
	{
		String sql="select * from es_sina_token where pageid=? and appid=? and type = 'Z'";
		List<SinaToken> list=getJdbcTemplate().query(sql, new Object[]{pageid,appid},new SinaTokenRowMapper());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}


	@Override
	public int findSinaTokenIsExit(long pageid)
	{
		String sql="select count(id) from es_sina_token where pageid=?";
		return getJdbcTemplate().queryForInt(sql,new Object[]{pageid});
	}


	@Override
	public SinaToken findSinaToken(long pageid)
	{
		String sql="select * from es_sina_token where pageid=?";
		List<SinaToken> list=getJdbcTemplate().query(sql, new Object[]{pageid},new SinaTokenRowMapper());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}


	@Override
	public int updateSinaTokenByAppicCid(long appid, long cid,long pageid)
	{
		String sql="update es_sina_token set pageid=? where appid=? and cid=?";
		return getJdbcTemplate().update(sql,new Object[]{pageid,appid,cid});
	}


	@Override
	public int updateSinaTokenByPageid(long pageid)
	{
		String sql="update es_sina_token set pageid=null where pageid=?";
		return getJdbcTemplate().update(sql,new Object[]{pageid});
	}


	@Override
	public int updateAppidCidByPageid(long appid, long cid, long pageid)
	{
		String sql="update es_sina_token set appid=?,cid=? where pageid=?";
		return getJdbcTemplate().update(sql,new Object[]{appid,cid,pageid});
	}

}

class SinaTokenRowMapper implements RowMapper
{
	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException
	{
		SinaToken st = new SinaToken();
		st.setAppid(rs.getLong("appid"));
		st.setCid(rs.getLong("cid"));
		st.setCreatetime(rs.getTimestamp("createtime"));
		st.setNickname(rs.getString("nickname"));
		st.setSiteid(rs.getLong("siteid"));
		st.setToken(rs.getString("token"));
		st.setTokenendtime(rs.getTimestamp("tokenendtime"));
		st.setUpdatetime(rs.getTimestamp("updatetime"));
		st.setPageid(rs.getLong("pageid"));
		return st;
	}

}
