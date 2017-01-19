package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IUserDao;
import com.huiyee.esite.model.SinaUser;
import com.huiyee.esite.model.UserInfo;
import com.huiyee.esite.util.DateUtil;

public class UserDaoImpl extends AbstractDao implements IUserDao
{

	@Override
	public long findUidByViewer(long viewer, long siteid)
	{
		List<Long> ls = getJdbcTemplate().query("select id from es_user_info where siteid=? and wbuid=?", new Object[]
		{ siteid, viewer }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("id");
			}

		});
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}
		return 0;
	}

	@Override
	public long updateUidByViewer(final long viewer, final long siteid)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();

		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement("insert into es_user_info(wbuid,siteid) values(?,?)", new String[]
				{ "id" });
				ps.setLong(1, viewer);
				ps.setLong(2, siteid);
				return ps;
			}
		}, keyHolder);

		long id = keyHolder.getKey().longValue();
		return id;
	}

	@Override
	public List<SinaUser> findNoNickname()
	{
		return getJdbcTemplate().query("select wbuid from esite.es_sina_user where wbuid is not null  and nickname is null limit 0,1000", new SinaUserRowMapper());
	}

	@Override
	public void updateNicknameByid(long wbuid, String screenName, int fansnum, int weibonum,String gender)
	{
		getJdbcTemplate().update("insert into esite.es_sina_user (wbuid,nickname,fensishu,weiboshu,gender,createtime) values (?,?,?,?,?,now()) ON DUPLICATE KEY UPDATE nickname=?,fensishu=?,weiboshu=?,gender=?", new Object[]
		{ wbuid,screenName, fansnum, weibonum,gender,screenName, fansnum, weibonum,gender});
	}
	
	@Override
    public UserInfo findUserInfo(long siteid,String wbuid)
    {
        String sql="select * from esite.es_sina_user  where wbuid=? ";
        List<UserInfo> list= getJdbcTemplate().query(sql,new Object[]{wbuid},new UserInfoRowMapper());
        if(list!=null&&list.size()>0){
        	return list.get(0);
        }
        return null;
    }

	private static final String SAVE_SITE_USER="insert into esite.es_user_info(siteid,wbuid,token,tokenendtime,createtime)values(?,?,?,?,now()) on duplicate key update token = ? ,tokenendtime = ?";
	@Override
	public int saveSiteUser(long viewer, long siteid, String token,
			Date tokenendtime) {
		Object[] params={siteid,viewer,token,tokenendtime,token,tokenendtime};
		return getJdbcTemplate().update(SAVE_SITE_USER, params);
	}
	
	private static final String UPDATE_USER_INFO="update es_user_info set nickname=? where id=?";
	@Override
	public int updateUserinfo(long id,String nickname) {
		Object[] params={nickname,id};
		return getJdbcTemplate().update(UPDATE_USER_INFO, params);
	}

	private static final String SAVE_USER_WBUID="insert ignore into esite.es_sina_user(wbuid,info)values(?,?)";
	@Override
	public int saveUserWbuid(long wbuid, String info) {
		Object[] params={wbuid,info};
		return getJdbcTemplate().update(SAVE_USER_WBUID, params);
	}

}

class UserInfoRowMapper implements RowMapper
{

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException
	{
		UserInfo userinfo = new UserInfo();
//		userinfo.setId(rs.getLong("id"));
//		userinfo.setSiteid(rs.getLong("siteid"));
//		userinfo.setEmail(rs.getString("email"));
//		userinfo.setPhone(rs.getString("phone"));
		userinfo.setWbuid(rs.getString("wbuid"));
		userinfo.setNickname(rs.getString("nickname"));
		userinfo.setUrl(rs.getString("url"));
		userinfo.setBiaoqian(rs.getString("biaoqian"));
		userinfo.setFensishu(rs.getInt("fensishu"));
		userinfo.setWeiboshu(rs.getInt("weiboshu"));
		userinfo.setCreatetime(DateUtil.getDateTimeString(rs.getTimestamp("createtime")));
		return userinfo;
	}

}
class SinaUserRowMapper implements RowMapper
{

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException
	{
		SinaUser user = new SinaUser();
		user.setWbuid(rs.getLong("wbuid"));
		return user;
	}

}
