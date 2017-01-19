package com.huiyee.esite.dao.imp;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.huiyee.esite.dao.IVisitLogDao;
import com.huiyee.esite.dto.VisitLogDto;
import com.huiyee.esite.dto.VisitPageTime;
import com.huiyee.interact.lottery.util.JsonStringUtil;

public class VisitLogDao implements IVisitLogDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addLogs(final List<VisitLogDto> vls)
	{
		if (vls != null && vls.size() > 0)
		{
			String sql = "insert esite.es_visitlog (vlog,tid) values (?,?)";
			BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter()
			{
				public int getBatchSize()
				{
					return vls.size();
				}

				public void setValues(PreparedStatement ps, int index) throws SQLException
				{
					ps.setString(1, JsonStringUtil.Obj2String(vls.get(index)));
					ps.setLong(2, vls.get(index).getCreatetime());
				}
			};
			jdbcTemplate.batchUpdate(sql, setter);
		}
	}

	@Override
	public void addVpts(final List<VisitPageTime> vls)
	{
		if (vls != null && vls.size() > 0)
		{
			String sql = "insert esite.es_visitpage_time (tid,cutid,pageid,cookie) values (?,?,?,?)";
			BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter()
			{
				public int getBatchSize()
				{
					return vls.size();
				}

				public void setValues(PreparedStatement ps, int index) throws SQLException
				{
					ps.setLong(1,vls.get(index).getTid() );
					ps.setLong(2, vls.get(index).getCutid());
					ps.setLong(3, vls.get(index).getPageid());
					ps.setString(4, vls.get(index).getCookie());
				}
			};
			jdbcTemplate.batchUpdate(sql, setter);
		}
	}
}
