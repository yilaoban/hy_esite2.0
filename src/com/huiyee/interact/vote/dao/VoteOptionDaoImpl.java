package com.huiyee.interact.vote.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.vote.model.VoteOption;

public class VoteOptionDaoImpl implements IVoteOptionDao{

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final String FIND_OPTION_BY_VOTEID="select * from es_interact_vote_option where voteid = ? and status != 'DEL'";
	@Override
	public List<VoteOption> findOptionsByVoteid(long voteid) {
		Object[] params={voteid};
		return jdbcTemplate.query(FIND_OPTION_BY_VOTEID,params, new VoteOptionRowmapper());
	}
	
	class VoteOptionRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			VoteOption o = new VoteOption();
			o.setId(rs.getLong("id"));
			o.setVoteid(rs.getLong("voteid"));
			o.setContent(rs.getString("content"));
			o.setPic(rs.getString("pic"));
			o.setCount(rs.getInt("count"));
			o.setCreatetime(rs.getTimestamp("createtime"));
			return o;
		}
		
	}
	
	
	@Override
	public int findUserOptionCount(long voteid, long optionid, long entityid, int type)
	{
		return jdbcTemplate.queryForInt("select count(id) from es_feature_interact_vote_record_option where optionid=? and uid=? and utype=?", new Object[]{optionid,entityid,type});
	}
}
