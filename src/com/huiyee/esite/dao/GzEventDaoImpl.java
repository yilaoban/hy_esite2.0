
package com.huiyee.esite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.model.WxGzEvent;
import com.huiyee.interact.xc.model.KeywordMsg;

/**
 * Œ¢œ÷≥°…Ë÷√crm_wx_keyword_message
 * 
 * @author ldw
 * 
 */
public class GzEventDaoImpl extends AbstractDao implements IGzEventDao
{

	@Override
	public KeywordMsg findMsgById(long id)
	{
		List<KeywordMsg> list = getJdbcTemplate().query("select * from crm.crm_wx_keyword_message where id=?", new Object[]
		{ id }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				KeywordMsg km = new KeywordMsg();
				km.setId(rs.getLong("id"));
				km.setDescription(rs.getString("description"));
				km.setKeyid(rs.getLong("keyid"));
				km.setPic_url(rs.getString("pic_url"));
				km.setTitle(rs.getString("title"));
				km.setUrl(rs.getString("url"));
				return km;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public long addKeywordsMsg(final KeywordMsg msg)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement("insert into crm.crm_wx_keyword_message (title,description,pic_url,url) values(?,?,?,?)", new String[]
				{ "id" });
				ps.setString(1, msg.getTitle());
				ps.setString(2, msg.getDescription());
				ps.setString(3, msg.getPic_url());
				ps.setString(4, msg.getUrl());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public int updateKeywordsMsg(KeywordMsg msg)
	{
		return getJdbcTemplate().update("update crm.crm_wx_keyword_message set title=?,description=?,pic_url=?,url=? where id=?", new Object[]
		{ msg.getTitle(), msg.getDescription(), msg.getPic_url(), msg.getUrl(), msg.getId() });
	}

	@Override
	public int addSysKeywords(final String openid, final long msgid, final long recordid,final int type)
	{
		return getJdbcTemplate().update("replace into crm.crm_system_keywords (openid,hbgz,type,enid,recordid,status) values(?,1,?,?,?,0)", new Object[]
		{ openid, type,msgid, recordid });
	}

	@Override
	public long addGzEvent(final String furl, final long msgid, final long owner)
	{

		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement("insert into esite.es_wx_gz_event (owner,link,msgid) values(?,?,?)", new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, owner);
				ps.setString(i++, furl);
				ps.setLong(i++, msgid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;

	}

	@Override
	public int updateGzEvent(long id, String furl, long msgid, long owner)
	{
		return getJdbcTemplate().update("update esite.es_wx_gz_event set link=?,msgid=? where owner=? and id=?", new Object[]
		{ furl, msgid, owner, id });
	}

	@Override
	public WxGzEvent findGzEvent(long id)
	{
		List<WxGzEvent> list = getJdbcTemplate().query(
				"select * from esite.es_wx_gz_event e left outer join crm.crm_wx_keyword_message m on e.msgid=m.id where e.id=? ", new Object[]
				{ id }, new RowMapper()
				{

					@Override
					public Object mapRow(ResultSet rs, int arg1) throws SQLException
					{
						WxGzEvent e = new WxGzEvent();
						e.setId(rs.getLong("e.id"));
						e.setLink(rs.getString("e.link"));
						long msgid = rs.getLong("e.msgid");
						if (msgid > 0)
						{
							KeywordMsg km = new KeywordMsg();
							km.setId(rs.getLong("m.id"));
							km.setDescription(rs.getString("m.description"));
							km.setKeyid(rs.getLong("m.keyid"));
							km.setPic_url(rs.getString("m.pic_url"));
							km.setTitle(rs.getString("m.title"));
							km.setUrl(rs.getString("m.url"));

							e.setMsg(km);
						}
						return e;
					}
				});
		return list.size() > 0 ? list.get(0) : null;
	}
}
