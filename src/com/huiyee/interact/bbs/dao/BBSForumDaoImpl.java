package com.huiyee.interact.bbs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.bbs.model.BBSForum;

public class BBSForumDaoImpl implements IBBSForumDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BBSForum> findForumsByCateid(long cateid) {
		String sql = "SELECT * FROM hybbs.bbs_forum WHERE cateid=? ORDER BY rank";
		Object[] params = { cateid };
		return jdbcTemplate.query(sql, params, new ForumRowMapper());
	}

	@SuppressWarnings("unchecked")
	@Override
	public BBSForum findForumById(long forumid) {
		String sql = "SELECT * FROM hybbs.bbs_forum WHERE id=?";
		Object[] params = { forumid };
		List<BBSForum> list = jdbcTemplate.query(sql, params, new ForumRowMapper());
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}

class ForumRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		BBSForum f = new BBSForum();
		f.setId(rs.getLong("id"));
		f.setCateid(rs.getLong("cateid"));
		f.setTitle(rs.getString("title"));
		f.setTopicCheck(rs.getString("topicCheck"));
		f.setCommentCheck(rs.getString("commentCheck"));
		f.setForumer(rs.getLong("forumer"));
		return f;
	}

}