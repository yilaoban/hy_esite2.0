package com.huiyee.interact.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.util.HyConfig;
import com.huiyee.interact.bbs.model.BBSForum;
import com.huiyee.interact.bbs.model.BBSTopic;

public class BBSTopicDaoImpl implements IBBSTopicDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BBSTopic> findTopicsByForumid(BBSForum forum, int start, int rows) {
		String sql = "SELECT t.*,hu.nickname huname,wu.nickname wuname, hu.img,wu.headimgurl,x.content,x.pic FROM hybbs.bbs_topic t LEFT JOIN hybbs.bbs_user u ON u.id=t.CREATER_UID left join esite.es_hy_user hu on u.hyuserid = hu.id left join esite.es_wx_user wu on hu.wxuid = wu.id LEFT JOIN hybbs.bbs_topic_text x ON x.topicid=t.id WHERE t.FORUM_ID=? and t.`STATUS` != 1 ";
		if ("Y".equals(forum.getTopicCheck())) {
			sql = sql + "AND t.checked ='CMP' ";
		}
		sql = sql + "ORDER BY t.top DESC,t.CREATE_TIME DESC LIMIT ?,?";
		Object[] params = { forum.getId(), start, rows };
		return jdbcTemplate.query(sql, params, new TopicRowMapper());
	}

	@SuppressWarnings("unchecked")
	@Override
	public BBSTopic findTopicById(long topicid) {
		String sql = "SELECT t.*,hu.nickname huname,wu.nickname wuname,hu.img,wu.headimgurl,x.content,x.pic FROM hybbs.bbs_topic t LEFT JOIN hybbs.bbs_user u ON u.id=t.CREATER_UID left join esite.es_hy_user hu on u.hyuserid = hu.id left join esite.es_wx_user wu on hu.wxuid = wu.id LEFT JOIN hybbs.bbs_topic_text x ON x.topicid=t.id WHERE t.id=?";
		Object[] params = { topicid };
		List<BBSTopic> list = jdbcTemplate.query(sql, params, new TopicRowMapper());
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BBSTopic findTopicByEntity(long entityid, long entype) {
		String sql = "SELECT t.*,hu.nickname huname,wu.nickname wuname,hu.img,wu.headimgurl,x.content,x.pic FROM hybbs.bbs_topic t LEFT JOIN hybbs.bbs_user u ON u.id=t.CREATER_UID left join esite.es_hy_user hu on u.hyuserid = hu.id left join esite.es_wx_user wu on hu.wxuid = wu.id LEFT JOIN hybbs.bbs_topic_text x ON x.topicid=t.id WHERE t.entityid=? AND t.entype=?";
		Object[] params = { entityid, entype };
		List<BBSTopic> list = jdbcTemplate.query(sql, params, new TopicRowMapper());
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	class TopicRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			BBSTopic t = new BBSTopic();
			t.setId(rs.getLong("t.id"));
			t.setTITLE(rs.getString("t.TITLE"));
			t.setCREATER_UID(rs.getLong("t.CREATER_UID"));
			t.setCREATE_TIME(rs.getTimestamp("t.CREATE_TIME"));
			t.setVIEW_COUNT(rs.getLong("t.VIEW_COUNT"));
			t.setREPLY_COUNT(rs.getInt("t.REPLY_COUNT"));
			t.setLAST_TIME(rs.getTimestamp("t.LAST_TIME"));
			t.setModerator_reply(rs.getInt("t.moderator_reply"));
			t.setUp(rs.getInt("up"));
			t.setDown(rs.getInt("down"));
			t.setSTATUS(rs.getInt("t.STATUS"));
			t.setEntityid(rs.getLong("entityid"));
			t.setEntype(rs.getInt("entype"));
			if(StringUtils.isNotBlank(rs.getString("huname"))){
				t.setCreater(rs.getString("huname"));
			}else{
				t.setCreater(rs.getString("wuname"));
			}
			if(StringUtils.isNotBlank(rs.getString("hu.img"))){
				t.setCreater_img(rs.getString("hu.img"));
			}else{
				t.setCreater_img(rs.getString("wu.headimgurl"));
			}
			t.setContent(rs.getString("content"));
			String pic = rs.getString("pic");
			t.setPic(pic);
			if (StringUtils.isNotBlank(pic)) {
				List<String> pic_list = new ArrayList<String>();
				for (String i : pic.split(",")) {
					pic_list.add(HyConfig.getImgDomain() + i);
				}
				t.setPic_list(pic_list);
			}
			t.setTop(rs.getInt("top"));
			t.setChecked(rs.getString("checked"));
			return t;
		}

	}

	@Override
	public int addTopicField(long topicid, String field) {
		String sql = "UPDATE hybbs.bbs_topic SET " + field + "=" + field + "+1 WHERE id=?";
		Object[] param = { topicid };
		return jdbcTemplate.update(sql, param);
	}

	@Override
	public long addTopic(final long forumid, final BBSTopic topic) {
		final String sql = "INSERT INTO hybbs.bbs_topic(FORUM_ID,CREATER_UID,TITLE,CREATE_TIME,checked) VALUES(?,?,?,now(),?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql, new String[] { "id" });
				int i = 1;
				ps.setLong(i++, forumid);
				ps.setLong(i++, topic.getCREATER_UID());
				ps.setString(i++, topic.getTITLE());
				ps.setString(i++, topic.getChecked());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public int addTopicText(long topicid, BBSTopic topic) {
		String sql = "INSERT INTO hybbs.bbs_topic_text(topicid,content,pic) VALUES(?,?,?)";
		Object[] params = { topicid, topic.getContent(), topic.getPic() };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int findTopicExist(long entityId, int typeCode) {
		return jdbcTemplate.queryForInt("select count(id) from hybbs.bbs_topic where entityid=? and entype=?", new Object[] { entityId, typeCode });
	}

	@Override
	public long saveBBSTopic(final long forumid, final long forumer, final String entityName, final long entityId, final int entityType) {
		final String sql = "insert into hybbs.bbs_topic (FORUM_ID,CREATER_UID,REPLYER_UID,TITLE,CREATE_TIME,LAST_TIME,TYPE_ID,entityid,entype,checked) values(?,?,?,?,now(),now(),0,?,?,'CMP')";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql, new String[] { "id" });
				int i = 1;
				ps.setLong(i++, forumid);
				ps.setLong(i++, forumer);
				ps.setLong(i++, forumer);
				ps.setString(i++, entityName);
				ps.setLong(i++, entityId);
				ps.setInt(i++, entityType);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;

	}

}
