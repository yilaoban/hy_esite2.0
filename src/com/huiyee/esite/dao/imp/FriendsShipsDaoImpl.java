package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IFriendsShipsDao;
import com.huiyee.esite.model.SinaToken;

public class FriendsShipsDaoImpl extends AbstractDao implements IFriendsShipsDao
{
	@Override
	public SinaToken findToken(long pageid, long wbuid)
	{
		List<SinaToken> list = getJdbcTemplate().query("select * from es_user_info user join es_page page on page.siteid=user.siteid where user.wbuid=? and page.id=?", new Object[]
		{ wbuid, pageid }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				SinaToken token = new SinaToken();
				token.setToken(rs.getString("user.token"));
				return token;
			}
		});
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	public void addRecord(long pageid, long wbuid, long cid)
	{
		getJdbcTemplate().update("insert into es_feature_guanzhu_record (pageid,wbuid,cid,createtime) values (?,?,?,now())", new Object[]
		{ pageid, wbuid, cid });
	}
}
