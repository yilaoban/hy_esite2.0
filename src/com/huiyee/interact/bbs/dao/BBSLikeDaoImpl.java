package com.huiyee.interact.bbs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.bbs.model.BBSLike;

public class BBSLikeDaoImpl implements IBBSLikeDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BBSLike> findLike(BBSLike like) {
		String sql = "SELECT * FROM hybbs.bbs_like WHERE entityid=? AND userid=? AND type=?";
		Object[] params = { like.getEntityid(), like.getUserid(), like.getType() };
		return jdbcTemplate.query(sql, params, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BBSLike like = new BBSLike();
				like.setId(rs.getLong("id"));
				like.setEntityid(rs.getLong("entityid"));
				like.setUserid(rs.getLong("userid"));
				like.setType(rs.getString("type"));
				like.setCreatetime(rs.getTimestamp("createtime"));
				return like;
			}

		});
	}

	@Override
	public int addLike(BBSLike like) {
		String sql = "INSERT IGNORE INTO hybbs.bbs_like(entityid,userid,type,createtime,atype) VALUES(?,?,?,now(),?)";
		Object[] params = { like.getEntityid(), like.getUserid(), like.getType(),like.getAtype() };
		return jdbcTemplate.update(sql, params);
	}

}
