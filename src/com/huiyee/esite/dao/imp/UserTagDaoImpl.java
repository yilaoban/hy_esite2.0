
package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IUserTagDao;
import com.huiyee.esite.model.UserPkvTag;
import com.huiyee.esite.model.UserTag;

public class UserTagDaoImpl extends AbstractDao implements IUserTagDao
{

	@Override
	public List<UserTag> findOwnerTag(long owner)
	{
		return getJdbcTemplate().query("select * from es_user_tag where owner=? and del_tag!='Y'", new Object[]
		{ owner }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				UserTag u = new UserTag();
				u.setId(rs.getLong("id"));
				u.setName(rs.getString("name"));
				u.setOwner(rs.getLong("owner"));
				u.setCreatetime(rs.getTimestamp("createtime"));
				return u;
			}
		});
	}

	@Override
	public UserPkvTag findUserPkvTag(String k_s, String v_s)
	{
		List<UserPkvTag> list = getJdbcTemplate().query("select * from es_user_pkv_tag where k_s=? and v_s=?", new Object[]
		{ k_s, v_s }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				UserPkvTag t = new UserPkvTag();
				t.setId(rs.getLong("id"));
				t.setK_s(rs.getString("k_s"));
				t.setV_s(rs.getString("v_s"));
				t.setTg1(rs.getLong("tg1"));
				t.setTg2(rs.getLong("tg2"));
				t.setTg3(rs.getLong("tg3"));
				t.setTg4(rs.getLong("tg4"));
				t.setTg5(rs.getLong("tg5"));
				return t;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public UserTag findUserTagById(long tagid)
	{
		List<UserTag> list = getJdbcTemplate().query("select * from es_user_tag where id=?", new Object[]
		{ tagid }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				UserTag u = new UserTag();
				u.setId(rs.getLong("id"));
				u.setName(rs.getString("name"));
				u.setOwner(rs.getLong("owner"));
				u.setCreatetime(rs.getTimestamp("createtime"));
				return u;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public long findTagByName(long owner, String name)
	{
		List<Long> list = getJdbcTemplate().query("select id from es_user_tag where name=? and owner=? and del_tag!='Y'", new Object[]
		{ name, owner }, new RowMapper()
		{

			public Object mapRow(ResultSet arg0, int arg1) throws SQLException
			{
				return arg0.getLong("id");
			}
		});
		return list.size() > 0 ? list.get(0) : 0;
	}

	@Override
	public long saveTag(final long owner, final String name)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement("insert into es_user_tag (owner,name,createtime) values(?,?,now())", new String[]
				{ "id" });
				ps.setLong(1, owner);
				ps.setString(2, name);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public void savePkvTag(UserPkvTag t)
	{
		String sql = "";
		Object[] param = null;
		if (t.getPage() > 0)
		{
			sql = "replace into es_user_pkv_tag (page,tg1,tg2,tg3,tg4,tg5) values(?,?,?,?,?,?)";
			param = new Object[]
			{ t.getPage(), t.getTg1() == 0 ? null : t.getTg1(), t.getTg2() == 0 ? null : t.getTg2(), t.getTg3() == 0 ? null : t.getTg3(), t.getTg4() == 0 ? null : t.getTg4(),
					t.getTg5() == 0 ? null : t.getTg5() };
		} else if (StringUtils.isNotEmpty(t.getK_s()) && StringUtils.isNotEmpty(t.getV_s()))
		{
			sql = "replace into es_user_pkv_tag (k_s,v_s,tg1,tg2,tg3,tg4,tg5) values(?,?,?,?,?,?,?)";
			param = new Object[]
			{ t.getK_s(), t.getV_s(), t.getTg1() == 0 ? null : t.getTg1(), t.getTg2() == 0 ? null : t.getTg2(), t.getTg3() == 0 ? null : t.getTg3(),
					t.getTg4() == 0 ? null : t.getTg4(), t.getTg5() == 0 ? null : t.getTg5() };
		} else
		{
			System.out.println("±Í«©…Ë÷√“Ï≥£");
			return;
		}
		getJdbcTemplate().update(sql, param);
	}

	@Override
	public UserPkvTag findUserPkvTag(long pageid)
	{
		List<UserPkvTag> list = getJdbcTemplate().query("select * from es_user_pkv_tag where page=?", new Object[]
		{ pageid }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				UserPkvTag t = new UserPkvTag();
				t.setId(rs.getLong("id"));
				t.setK_s(rs.getString("k_s"));
				t.setV_s(rs.getString("v_s"));
				t.setTg1(rs.getLong("tg1"));
				t.setTg2(rs.getLong("tg2"));
				t.setTg3(rs.getLong("tg3"));
				t.setTg4(rs.getLong("tg4"));
				t.setTg5(rs.getLong("tg5"));
				return t;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}
}
