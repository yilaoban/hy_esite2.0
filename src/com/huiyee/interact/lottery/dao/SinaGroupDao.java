package com.huiyee.interact.lottery.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.lottery.model.SinaGroup;

public class SinaGroupDao implements ISinaGroupDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private static final String FIND_WBGROUPLIST_BY_OWNER = "select g.* from yibo.wb_sina_company_weibo cw join yibo.wb_sina_group g on cw.wbuid = g.wbuid where cw.owner = ?";
	@Override
	public List<SinaGroup> findWbGroupListByOwner(long owner){
		Object[] params={owner};
		return jdbcTemplate.query(FIND_WBGROUPLIST_BY_OWNER, params, new SinaGroupRowMapper());
	}
	
	class SinaGroupRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			SinaGroup l = new SinaGroup();
			l.setId(rs.getLong("id"));
			l.setWbuid(rs.getLong("wbuid"));
			l.setGroupName(rs.getString("groupName"));
			l.setType(rs.getString("type"));
			l.setUserNum(rs.getInt("userNum"));
			return l;
		}
	}

	@Override
	public int findWbuidInGroup(long gid, long owner,long wbuid)
	{
		return jdbcTemplate.queryForInt("select count(id) from yibouser.wb_sina_group_"+owner+" where wbuid=? and groupid=?",new Object[]{wbuid,gid});
	}
}
