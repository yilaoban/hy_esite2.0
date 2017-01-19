package com.huiyee.interact.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.bbs.SensitiveWordFilter;
import com.huiyee.interact.bbs.model.BBSComment;
import com.huiyee.interact.bbs.model.BBSUser;
import com.opensymphony.xwork2.ActionContext;

public class BBSCommentDaoImpl implements IBBSCommentDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BBSComment> findCommentsByTopicid(long topicid,String commentCheck, int start, int rows)
	{
		String sql = "SELECT p.*,hu.nickname huname,wu.nickname wxname, hu.img,wu.headimgurl ,t.POST_CONTENT,(SELECT COUNT(1) FROM hybbs.bbs_post WHERE TOPIC_ID=?) total FROM hybbs.bbs_post p LEFT JOIN hybbs.bbs_user u ON u.id=p.CREATER_ID left join esite.es_hy_user hu on u.hyuserid = hu.id left join esite.es_wx_user wu on hu.wxuid = wu.id LEFT JOIN hybbs.bbs_post_text t ON t.postid=p.id WHERE p.TOPIC_ID=? AND p.STATUS!=1 ";
		if ("Y".equals(commentCheck)) {
			sql = sql + " AND p.checked ='CMP' ";
		}
		sql = sql + " ORDER BY p.INDEX_COUNT LIMIT ?,?";
		Object[] params = { topicid, topicid, start, rows };
		return jdbcTemplate.query(sql, params, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{

				BBSComment c = new BBSComment();
				c.setId(rs.getLong("id"));
				c.setTOPIC_ID(rs.getLong("TOPIC_ID"));
				c.setCREATER_ID(rs.getLong("CREATER_ID"));
				c.setCREATE_TIME(rs.getTimestamp("CREATE_TIME"));
				c.setINDEX_COUNT(rs.getInt("INDEX_COUNT"));
				c.setZAN(rs.getInt("ZAN"));

				c.setTotal(rs.getInt("total"));
				if(StringUtils.isNotBlank(rs.getString("huname"))){
					c.setCreater(rs.getString("huname"));
				}else{
					c.setCreater(rs.getString("wxname"));
				}
				if(StringUtils.isNotBlank(rs.getString("hu.img"))){
					c.setCreater_img(rs.getString("hu.img"));
				}else{
					c.setCreater_img(rs.getString("wu.headimgurl"));
				}
				c.setContent(rs.getString("POST_CONTENT"));

				VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
				if (vu != null)
				{
					BBSUser bu = vu.getBbsUser();
					if (bu != null)
					{
						Object[] p =
						{ rs.getLong("id"), bu.getId(), "C" };
						int count = jdbcTemplate.queryForInt("SELECT count(1) FROM hybbs.bbs_like WHERE entityid=? AND userid=? AND type=?", p);
						c.setLiked(count > 0);
					}
				}
				return c;
			}
		});
	}

	@Override
	public long addComment(final long topicid, final BBSComment comment)
	{
		final int index = jdbcTemplate.queryForInt("SELECT COUNT(1) FROM hybbs.bbs_post WHERE TOPIC_ID=" + topicid) + 1;
		final String sql = "INSERT INTO hybbs.bbs_post(TOPIC_ID,CREATER_ID,CREATE_TIME,POSTER_IP,INDEX_COUNT,ANONYMOUS,checked) VALUES(?,?,now(),?,?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException
			{
				PreparedStatement ps = conn.prepareStatement(sql, new String[]
				{ "id" });
				ps.setLong(1, topicid);
				ps.setLong(2, comment.getCREATER_ID());
				ps.setString(3, comment.getPOSTER_IP());
				ps.setInt(4, index);
				ps.setInt(5, comment.getANONYMOUS());
				ps.setString(6, comment.getChecked());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int addCommentText(long commentid, BBSComment comment)
	{
		if (SensitiveWordFilter.sensitiveWordMap == null)
		{
			List<String> keyWords = jdbcTemplate.query("SELECT word FROM hybbs.bbs_sensitive_word", new RowMapper()
			{
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException
				{
					return rs.getString("word");
				}
			});
			SensitiveWordFilter.addSensitiveWordToHashMap(keyWords);
		}
		String content = comment.getContent();
		content = SensitiveWordFilter.replaceSensitiveWord(content, SensitiveWordFilter.minMatchTYpe, "*");

		String sql = "INSERT INTO hybbs.bbs_post_text(postid,POST_CONTENT) VALUES(?,?)";
		Object[] params =
		{ commentid, content };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int addCommentField(long commentid, String field)
	{
		String sql = "UPDATE hybbs.bbs_post SET " + field + "=" + field + "+1 WHERE id=?";
		Object[] params =
		{ commentid };
		return jdbcTemplate.update(sql, params);
	}

}
