package com.huiyee.interact.cs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.interact.cs.dto.JContent;
import com.huiyee.interact.cs.model.CsData;
import com.huiyee.interact.cs.util.JsonStringUtil;

public class CsRecordDao implements ICsRecordDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public long addCsFuidDraw(final long csid, final long fuid, final int utype, final String jcon, final String ip, final String terminal, final String source)
	{
		KeyHolder keyholder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement("insert  into esite.es_interact_cs_fuid_draw (csid,fuid,utype,jcontent,createtime,ip,terminal,source) values(?,?,?,?,now(),?,?,?)", new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, csid);
				ps.setLong(i++, fuid);
				ps.setLong(i++, utype);
				ps.setString(i++, jcon);
				ps.setString(i++, ip);
				ps.setString(i++, terminal);
				ps.setString(i++, source);
				return ps;
			}

		}, keyholder);
		return keyholder.getKey().longValue();
	}

	@Override
	public int findTotal(long csid, int type)
	{
		return jdbcTemplate.queryForInt("select count(*) from es_interact_cs_fuid_draw where csid=? and utype=?", new Object[]
		{ csid, type });
	}

	@Override
	public List<CsData> findList(long csid, int type, int start, int size)
	{
		List<CsData> list = jdbcTemplate.query("select * from es_interact_cs_fuid_draw where csid=? and utype=? order by id desc limit ?,?", new Object[]
		{ csid, type, start, size }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				CsData c = new CsData();
				c.setId(rs.getLong("id"));
				c.setCsid(rs.getLong("csid"));
				c.setFuid(rs.getLong("fuid"));
				c.setUtype(rs.getInt("utype"));
				String jcontent = rs.getString("jcontent");
				c.setJcontent((JContent) JsonStringUtil.String2Obj(jcontent, JContent.class));
				c.setIp(rs.getString("ip"));
				c.setTerminal(rs.getString("terminal"));
				c.setSource(rs.getString("source"));
				c.setCreatetime(rs.getTimestamp("createtime"));
				return c;
			}
		});
		return list;
	}
}
