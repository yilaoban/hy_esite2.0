
package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.ISinaUserDao;
import com.huiyee.esite.model.SinaUser;
import com.huiyee.esite.model.UserInfo;
import com.huiyee.esite.service.IMemCacheAble;
import com.huiyee.tfmodel.HyWbUser;

public class SinaUserDaoImpl extends AbstractDao implements ISinaUserDao, IMemCacheAble
{

	private int cacheTime;

	private static final String SAVE_SINA_USER = "insert into esite.es_feature_sina_user(wbuid,nickname,gender,followersCount,friendsCount,statusesCount,createtime,updatetime,imageurl) values(?,?,?,?,?,?,now(),now(),?) on duplicate key update nickname = ? , gender = ? ,followersCount = ?, friendsCount = ?, statusesCount = ?,imageurl = ?";

	@Override
	public int saveSinaUser(HyWbUser user)
	{
		Object[] params =
		{ user.getId(), user.getName(), user.getGender(), user.getFollowersCount(), user.getFriendsCount(), user.getStatusesCount(), user.getProfileImageUrl(), user.getName(),
				user.getGender(), user.getFollowersCount(), user.getFriendsCount(), user.getStatusesCount(), user.getProfileImageUrl() };
		return getJdbcTemplate().update(SAVE_SINA_USER, params);
	}

	private static final String UPDATE_SINA_USER_APP = "update esite.es_feature_sina_user_app set token = ? ,tokenendtime = ?,ip = ?,terminal = ? where id = ?";

	@Override
	public long updateSinaUserApp(long id, String token, Date tokenendtime, String ip, String terminal)
	{
		Object[] params =
		{ token, tokenendtime, ip, terminal, id };
		return getJdbcTemplate().update(UPDATE_SINA_USER_APP, params);
	}

	private static final String SAVE_SINA_USER_APP = "insert into esite.es_feature_sina_user_app(wbuid,appid,token,tokenendtime,createtime,updatetime,ip,terminal,siteid) values(?,?,?,?,now(),now(),?,?,?) on duplicate key update token = ?,tokenendtime = ?,ip = ? ,terminal = ?";

	@Override
	public long saveSinaUserApp(final long wbuid, final long appid, final String token, final Date tokenendtime, final String ip, final String terminal, final long siteid)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(SAVE_SINA_USER_APP, new String[]
				{ "id" });
				ps.setLong(1, wbuid);
				ps.setLong(2, appid);
				ps.setString(3, token);
				ps.setTimestamp(4, new Timestamp(tokenendtime.getTime()));
				ps.setString(5, ip);
				ps.setString(6, terminal);
				ps.setLong(7, siteid);
				ps.setString(8, token);
				ps.setTimestamp(9, new Timestamp(tokenendtime.getTime()));
				ps.setString(10, ip);
				ps.setString(11, terminal);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	private static final String FIND_TOKEN_BY_WBUID = "select su.token from esite.es_page p join esite.es_sina_token st on (p.siteid = st.siteid and st.type='Z') join esite.es_feature_sina_user_app su on su.siteid = st.siteid where p.id = ? and su.wbuid = ?";

	@Override
	public String findTokenByWbuid(long wbuid, long pageid)
	{
		Object[] params =
		{ pageid, wbuid };
		try
		{
			return (String) getJdbcTemplate().queryForObject(FIND_TOKEN_BY_WBUID, params, String.class);
		} catch (DataAccessException e)
		{
			return null;
		}
	}

	private static final String FIND_TOKEN_BY_UID_AND_SITEID = "select if(f.tokenendtime>now(),'Y','N') from esite.es_user_info u join esite.es_feature_sina_user_app f on (u.wbuid = f.wbuid and u.siteid = f.siteid) where u.id = ? and u.siteid = ?";

	@Override
	public String findTokenByUidAndSiteid(long uid, long siteid)
	{
		Object[] params =
		{ uid, siteid };
		try
		{
			return (String) getJdbcTemplate().queryForObject(FIND_TOKEN_BY_UID_AND_SITEID, params, String.class);
		} catch (DataAccessException e)
		{
			return null;
		}
	}

	private static final String FIND_WBUID_BU_UID = "select wbuid from esite.es_user_info where id = ?";

	@Override
	public long findWbuidByUid(long uid)
	{
		Object[] params =
		{ uid };
		try
		{
			return getJdbcTemplate().queryForLong(FIND_WBUID_BU_UID, params);
		} catch (DataAccessException e)
		{
			return 0;
		}
	}

	public int getCacheTime()
	{
		return cacheTime;
	}

	public void setCacheTime(int cacheTime)
	{
		this.cacheTime = cacheTime;
	}

	private static final String FIND_SINA_USER_APP = "select id from esite.es_feature_sina_user_app where wbuid = ? and siteid = ? and appid = ?";

	@Override
	public long findUserApp(long wbuid, long siteid, long appid)
	{
		Object[] params =
		{ wbuid, siteid, appid };
		try
		{
			return (Long) getJdbcTemplate().queryForObject(FIND_SINA_USER_APP, params, Long.class);
		} catch (DataAccessException e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public UserInfo findUserByUid(long uid)
	{
		String sql = "select wbuid,token from esite.es_user_info where id = ?";
		List<UserInfo> list = getJdbcTemplate().query(sql, new Object[]
		{ uid }, new UserInfoMapper());
		if (list.size() > 0)
		{
			UserInfo info = list.get(0);
			return info;
		}
		return null;
	}

	class UserInfoMapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			UserInfo info = new UserInfo();
			info.setWbuid1(rs.getLong("wbuid"));
			info.setToken(rs.getString("token"));
			return info;
		}
	}

	@Override
	public String findNickNameById(long wbuid)
	{
		List<String> list = getJdbcTemplate().query("select nickname from es_sina_user where wbuid=?", new Object[]
		{ wbuid }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getString("nickname");
			}
		});
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public SinaUser findSinaUserByid(long wbuid)
	{
		List<SinaUser> list = getJdbcTemplate().query("select * from es_sina_user where id=?", new Object[]
		{ wbuid }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				SinaUser u = new SinaUser();
				u.setNickname(rs.getString("nickname"));
				u.setImageurl(rs.getString("url"));
				return u;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}
}
