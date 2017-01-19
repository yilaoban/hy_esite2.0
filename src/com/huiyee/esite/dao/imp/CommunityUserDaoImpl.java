package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.ICommunityUserDao;
import com.huiyee.esite.model.BBSJfLevel;
import com.huiyee.esite.model.BBSJfRule;
import com.huiyee.interact.bbs.model.BBSTopic;
import com.huiyee.interact.bbs.model.BBSUser;

public class CommunityUserDaoImpl implements ICommunityUserDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int saveBBSUserJfRule(int action, int jifen,long owner)
	{
		String sql = "insert into hybbs.bbs_user_jf_rule(action,jifen,owner) values(?,?,?)";
		Object[] params = {action,jifen,owner};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public List<BBSJfRule> findBBSUserJfRuleList(long owner)
	{
		String sql = "select * from hybbs.bbs_user_jf_rule where owner = ?";
		Object[] params = {owner};
		return jdbcTemplate.query(sql, params, new jfRuleRowMapper());
	}

	class jfRuleRowMapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			BBSJfRule r =new BBSJfRule();
			r.setAction(rs.getInt("action"));
			r.setId(rs.getLong("id"));
			r.setJifen(rs.getInt("jifen"));
			r.setOwner(rs.getLong("owner"));
			return r;
		}
	}
	
	@Override
	public BBSJfRule findBBSUSErJfRuleByAction(int action, long owner)
	{
		String sql = "select * from hybbs.bbs_user_jf_rule where owner = ? and action = ?";
		Object[] params = {owner,action};
		List<BBSJfRule> list = jdbcTemplate.query(sql, params, new jfRuleRowMapper());
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public BBSJfRule findBBSUSErJfRuleById(long ruleid)
	{
		String sql = "select * from hybbs.bbs_user_jf_rule where id = ?";
		Object[] params = {ruleid};
		List<BBSJfRule> list = jdbcTemplate.query(sql, params, new jfRuleRowMapper());
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateJfRuleById(long ruleid, int jifen)
	{
		String sql = "update hybbs.bbs_user_jf_rule set jifen = ? where id = ?";
		Object[] params = {jifen,ruleid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int deleteJfRule(long ruleid)
	{
		String sql = "delete from hybbs.bbs_user_jf_rule where id = ?";
		Object[] params = {ruleid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public List<BBSJfLevel> findBBSUserJflevelList(long owner)
	{
		String sql = "select * from hybbs.bbs_user_jf_level where owner = ?";
		Object[] params = {owner};
		return jdbcTemplate.query(sql, params, new JfLevelRowMapper());
	}
	
	class JfLevelRowMapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			BBSJfLevel r =new BBSJfLevel();
			r.setId(rs.getLong("id"));
			r.setOwner(rs.getLong("owner"));
			r.setLevel_name(rs.getString("level_name"));
			r.setRequire_jf(rs.getLong("require_jf"));
			return r;
		}
	}

	@Override
	public int saveBBSUserJfLevel(String level_name, long require_jf, long owner,int level_id)
	{
		String sql = "insert into hybbs.bbs_user_jf_level(level_name,require_jf,owner,level_id) values(?,?,?,?)";
		Object[] params = {level_name,require_jf,owner,level_id};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public BBSJfLevel findBBSUserJfLevelById(long levelid)
	{
		String sql = "select * from hybbs.bbs_user_jf_level where id = ?";
		Object[] params = {levelid};
		List<BBSJfLevel> list = jdbcTemplate.query(sql, params, new JfLevelRowMapper());
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateBBSUserJfLevel(String level_name, long require_jf, long levelid)
	{
		String sql = "update hybbs.bbs_user_jf_level set level_name=?,require_jf=? where id = ?";
		Object[] params = {level_name,require_jf,levelid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int deleteJfLevel(long levelid)
	{
		String sql = "delete from hybbs.bbs_user_jf_level where id = ?";
		Object[] params = {levelid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public List<BBSUser> findBBSUserInfoList(long owner,int start,int size)
	{
		String sql ="select * from esite.es_hy_user hu left join hybbs.bbs_user u on u.hyuserid = hu.id left join esite.es_hy_user_balance b on b.hyuid = u.hyuserid  left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where u.owner = ? order by u.createtime desc limit ?,?";
		Object[] params = {owner,owner,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSUser u = new BBSUser();
				u.setId(rs.getLong("u.id"));
				u.setUsername(rs.getString("hu.username"));
				u.setNickname(rs.getString("hu.nickname"));
				u.setLevel_name(rs.getString("l.level_name"));
				int total = rs.getInt("b.total");
				int used = rs.getInt("b.used");
				int jf = total - used;
				u.setJf(jf);
				u.setTopicnum(rs.getInt("u.topicnum"));
				u.setReplynum(rs.getInt("u.replynum"));
				u.setCreatetime(rs.getTimestamp("u.createtime"));
				u.setIsbalck(rs.getString("u.isbalck"));
				return u;
			}
			
		});
	}

	@Override
	public List<BBSTopic> fingBBSTopicByUid(long uid)
	{
		String sql = "select * from hybbs.bbs_topic t where t.status != 1 and t.CREATER_UID = ?";
		Object[] params = {uid};
		return jdbcTemplate.query(sql, params, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSTopic s = new BBSTopic();
				s.setUp(rs.getInt("up"));
				s.setDown(rs.getInt("down"));
				s.setTop(rs.getInt("top"));
				return s;
			}
			
		});
	}
	
	@Override
	public int findTopTotalByUid(long uid)
	{
		String sql = "select count(id) from hybbs.bbs_topic t where t.status != 1 and t.CREATER_UID = ? and t.top = 1";
		Object[] params = {uid};
		return jdbcTemplate.queryForInt(sql, params);
	}
	

	@Override
	public int findBBSUserInfoTotal(long owner)
	{
		String sql ="select count(*) from esite.es_hy_user hu left join hybbs.bbs_user u on hu.id = u.hyuserid left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where u.owner = ?";
		Object[] params = {owner,owner};
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public int findBBSUserInfoListByNicknameAndLevelidTotal(long owner, String nickname, long levelid)
	{
		String sql = "select count(*) from esite.es_hy_user hu left join hybbs.bbs_user u on u.hyuserid = hu.id left join esite.es_hy_user_balance b on b.hyuid = u.hyuserid left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where u.owner = ?";
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(owner);
		paramsList.add(owner);
		if(StringUtils.isNotBlank(nickname)){
			nickname = "%" + nickname + "%";
			sql = sql + " and hu.nickname like ? ";
			paramsList.add(nickname);
		}
		if(levelid > 0){
			sql = sql +" and l.id = ?";
			paramsList.add(levelid);
		}
		Object[] params = paramsList.toArray();
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<BBSUser> findBBSUserInfoListByNicknameAndLevelidTotal(long owner, String nickname, long levelid, int start, int size)
	{
		String sql = "select * from hybbs.bbs_user u right join esite.es_hy_user hu on u.hyuserid = hu.id left join esite.es_hy_user_balance b on b.hyuid = u.hyuserid left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where u.owner = ?";
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(owner);paramsList.add(owner);
		if(StringUtils.isNotBlank(nickname)){
			nickname = "%" + nickname + "%";
			sql = sql + " and hu.nickname like ? ";
			paramsList.add(nickname);
		}
		if(levelid > 0){
			sql = sql +" and l.id = ?";
			paramsList.add(levelid);
		}
		sql = sql + " order by u.createtime desc limit ?,?";
		paramsList.add(start);
		paramsList.add(size);
		Object[] params = paramsList.toArray();
		return jdbcTemplate.query(sql, params,new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSUser u = new BBSUser();
				u.setId(rs.getLong("u.id"));
				u.setUsername(rs.getString("hu.username"));
				u.setNickname(rs.getString("hu.nickname"));
				u.setLevel_name(rs.getString("l.level_name"));
				int total = rs.getInt("b.total");
				int used = rs.getInt("b.used");
				int jf = total - used;
				u.setJf(jf);
				u.setTopicnum(rs.getInt("u.topicnum"));
				u.setReplynum(rs.getInt("u.replynum"));
				u.setCreatetime(rs.getTimestamp("u.createtime"));
				u.setIsbalck(rs.getString("u.isbalck"));
				return u;
			}
			
		});
	}

	@Override
	public int findBBSUserBalck(long owner)
	{
		String sql ="select count(*) from hybbs.bbs_user u left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where u.owner = ? and u.isbalck = 'Y'";
		Object[] params = {owner,owner};
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<BBSUser> findBBSUserBalckList(long owner, int start, int size)
	{
		String sql ="select * from hybbs.bbs_user u left join esite.es_hy_user hu on u.hyuserid = hu.id left join esite.es_hy_user_balance b on b.hyuid = u.hyuserid left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where u.owner = ? and u.isbalck = 'Y' order by u.createtime desc limit ?,?";
		Object[] params = {owner,owner,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSUser u = new BBSUser();
				u.setId(rs.getLong("u.id"));
				u.setUsername(rs.getString("hu.username"));
				u.setNickname(rs.getString("hu.nickname"));
				u.setLevel_name(rs.getString("l.level_name"));
				int total = rs.getInt("b.total");
				int used = rs.getInt("b.used");
				int jf = total - used;
				u.setJf(jf);
				u.setTopicnum(rs.getInt("u.topicnum"));
				u.setReplynum(rs.getInt("u.replynum"));
				u.setCreatetime(rs.getTimestamp("u.createtime"));
				u.setIsbalck(rs.getString("u.isbalck"));
				return u;
			}
			
		});
	}

	@Override
	public int addUserBalck(long uid,String isbalck)
	{
		String sql ="update hybbs.bbs_user set isbalck = ? where id = ?";
		Object[] params = {isbalck,uid};
		return jdbcTemplate.update(sql,params);
	}

	@Override
	public int findCountByUid(long uid, int atype)
	{
		String sql = "select count(id) from hybbs.bbs_like where userid = ? and type = 'T' and atype = ?";
		Object[] params = {uid,atype};
		return jdbcTemplate.queryForInt(sql,params);
	}

	
	
}
