package com.huiyee.interact.bbs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.bbs.model.BBSTopic;
import com.huiyee.interact.bbs.model.BBSUser;

public class BBSUserCenterDaoImpl implements IBBSUserCenterDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public BBSUser findUserInfoByUserid(long userid)
	{
		String sql = "select * from hybbs.bbs_user su join hybbs.bbs_user_active_level l on su.level_id = l.id where su.id = ?";
		Object[] params = {userid};
		List<BBSUser> list = jdbcTemplate.query(sql, params, new MyRowMapper());
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	class MyRowMapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			BBSUser su = new BBSUser();
			su.setId(rs.getLong("su.id"));
			su.setEmail(rs.getString("su.email"));
			su.setCreatetime(rs.getTimestamp("su.createtime"));
			su.setTopicnum(rs.getInt("su.topicnum"));
			su.setReplynum(rs.getInt("su.replynum"));
			su.setLevel_name(rs.getString("l.level_name"));
			return su;
		}
	}

	@Override
	public List<BBSTopic> findMyTopic(long userid)
	{
		String sql = "select t.id,t.TITLE,c.name,u.username,t.CREATE_TIME from hybbs.bbs_topic t join hybbs.bbs_forum f on t.FORUM_ID = f.id join hybbs.bbs_category c on f.cateid = c.id join hybbs.bbs_user su on t.REPLYER_UID = su.id  where t.CREATER_UID = ?";
		Object[] parmas = {userid};
		return jdbcTemplate.query(sql, parmas, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSTopic t = new BBSTopic();
				t.setId(rs.getLong("t.id"));
				t.setTITLE(rs.getString("t.TITLE"));
				t.setCREATE_TIME(rs.getTimestamp("t.CREATE_TIME"));
				t.setReplyer(rs.getString("su.nickname"));
				t.setForumname(rs.getString("c.name"));
				return t;
			}
			
		});
	}

	@Override
	public List<BBSTopic> bbsMyReply(long userid)
	{
		String sql = "select t.id,t.title,t.LAST_TIME,u.username,c.name from hybbs.bbs_post p join hybbs.bbs_topic t on p.TOPIC_ID = t.id join hybbs.bbs_forum f on t.FORUM_ID = f.id join hybbs.bbs_category c on f.cateid = c.id join hybbs.bbs_user su on t.REPLYER_UID = su.id where p.CREATER_ID = ? group by p.TOPIC_ID";
		Object[] parmas = {userid};
		return jdbcTemplate.query(sql, parmas, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSTopic t = new BBSTopic();
				t.setId(rs.getLong("t.id"));
				t.setTITLE(rs.getString("t.TITLE"));
				t.setLAST_TIME(rs.getTimestamp("t.LAST_TIME"));
				t.setReplyer(rs.getString("su.nickname"));
				t.setForumname(rs.getString("c.name"));
				return t;
			}
			
		});
	}
}
