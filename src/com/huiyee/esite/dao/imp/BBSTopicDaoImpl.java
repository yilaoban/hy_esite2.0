package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.IBBSTopicDao;
import com.huiyee.esite.model.BBSForum;
import com.huiyee.esite.model.BBSJfRule;
import com.huiyee.interact.bbs.model.BBSTopic;
import com.huiyee.interact.bbs.model.BBSTopicText;

public class BBSTopicDaoImpl implements IBBSTopicDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override//1
	public int findTopicCountByForumid(long forumid,long owner)
	{
		String sql = "select count(*) from hybbs.bbs_forum f join hybbs.bbs_topic t on f.id = t.FORUM_ID join hybbs.bbs_user u on f.forumer = u.id left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where f.id = ? and t.STATUS != 1 and f.status != 'DEL'";
		Object[] params = {owner,forumid};
		return jdbcTemplate.queryForInt(sql,params);
	}
	
	@Override//9
	public int findTopicCountByForumid(long forumid,int index,long owner)
	{
		String sql = "";
		if(index == 0){
			sql = "select count(*) from hybbs.bbs_forum f join hybbs.bbs_topic t on f.id = t.FORUM_ID join hybbs.bbs_user u on f.forumer = u.id left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where f.id = ? and t.STATUS != 1 and f.status != 'DEL'";
		}else if(index == 1){
			sql = "select count(*) from hybbs.bbs_forum f join hybbs.bbs_topic t on f.id = t.FORUM_ID join hybbs.bbs_user u on f.forumer = u.id left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where f.id = ? and t.STATUS != 1 and f.status != 'DEL' order by t.REPLY_COUNT desc";
		}else if(index == 2){
			sql = "select count(*) from hybbs.bbs_forum f join hybbs.bbs_topic t on f.id = t.FORUM_ID join hybbs.bbs_user u on f.forumer = u.id left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where f.id = ? and t.STATUS != 1 and f.status != 'DEL' order by t.REPLY_COUNT";
		}else if(index == 3){
			sql = "select count(*) from hybbs.bbs_forum f join hybbs.bbs_topic t on f.id = t.FORUM_ID join hybbs.bbs_user u on f.forumer = u.id left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where f.id = ? and t.STATUS != 1 and f.status != 'DEL' order by t.up desc";
		}else if(index == 4){
			sql = "select count(*) from hybbs.bbs_forum f join hybbs.bbs_topic t on f.id = t.FORUM_ID join hybbs.bbs_user u on f.forumer = u.id left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where f.id = ? and t.STATUS != 1 and f.status != 'DEL' order by t.up";
		}else if(index == 5){
			sql = "select count(*) from hybbs.bbs_forum f join hybbs.bbs_topic t on f.id = t.FORUM_ID join hybbs.bbs_user u on f.forumer = u.id left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where f.id = ? and t.STATUS != 1 and f.status != 'DEL' order by t.down desc";
		}else if(index == 6){
			sql = "select count(*) from hybbs.bbs_forum f join hybbs.bbs_topic t on f.id = t.FORUM_ID join hybbs.bbs_user u on f.forumer = u.id left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where f.id = ? and t.STATUS != 1 and f.status != 'DEL' order by t.down";
		}
		Object[] params = {owner,forumid};
		return jdbcTemplate.queryForInt(sql,params);
	}
	
	@Override//2
	public List<BBSTopic> findTopicListByForumid(long forumid,long owner,int start,int size)
	{
		String sql = "select hu.nickname,wu.nickname wxname,l.level_name,t.*,f.title forumname from hybbs.bbs_forum f join hybbs.bbs_topic t on f.id = t.FORUM_ID join hybbs.bbs_user u on t.CREATER_UID = u.id left join esite.es_hy_user hu on u.hyuserid = hu.id left join esite.es_wx_user wu on hu.wxuid = wu.id left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where f.id = ? and t.STATUS != 1 and f.status != 'DEL' order by t.CREATE_TIME desc limit ?,?";
		Object[] params = {owner,forumid,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSTopic t = new BBSTopic();
				t.setId(rs.getLong("t.id"));
				t.setCREATER_UID(rs.getLong("t.CREATER_UID"));
				t.setTITLE(rs.getString("t.TITLE"));
				if(StringUtils.isNotBlank(rs.getString("hu.nickname"))){
					t.setCreater(rs.getString("hu.nickname"));
				}else{
					t.setCreater(rs.getString("wxname"));
				}
				t.setLevel_name(rs.getString("l.level_name"));
				t.setChecked(rs.getString("t.checked"));
				t.setCREATE_TIME(rs.getTimestamp("t.CREATE_TIME"));
				t.setREPLY_COUNT(rs.getInt("t.REPLY_COUNT"));
				t.setUp(rs.getInt("t.up"));
				t.setDown(rs.getInt("t.down"));
				t.setTop(rs.getInt("t.top"));
				t.setEntityid(rs.getLong("t.entityid"));
				t.setForumname(rs.getString("forumname"));
				return t;
			}
			
		});
	}
	
	@Override
	public long saveBBSTopic(final BBSTopic topic)
	{
		final String sql = "insert into hybbs.bbs_topic(FORUM_ID,CREATER_UID,REPLYER_UID,TITLE,CREATE_TIME,LAST_TIME,TYPE_ID,checked) values(?,?,?,?,now(),now(),?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql,
						new String[] { "id" });
				int i = 1;
				ps.setLong(i++, topic.getFORUM_ID());
				ps.setLong(i++, topic.getCREATER_UID());
				ps.setLong(i++, topic.getCREATER_UID());
				ps.setString(i++, topic.getTITLE());
				ps.setInt(i++, topic.getTYPE_ID());
				ps.setString(i++, topic.getChecked());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public long saveBBSTopicText(final long topicid,final BBSTopicText topicText)
	{
		final String sql = "insert into hybbs.bbs_topic_text(topicid,content) values(?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql,
						new String[] { "id" });
				int i = 1;
				ps.setLong(i++, topicid);
				ps.setString(i++, topicText.getContent());
//				ps.setString(i++, topicText.getImg());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public int updateBBSUsrTopicnum(long userid)
	{
		String sql = "update hybbs.bbs_user set topicnum = topicnum + 1 where id = ?";
		Object[] params = {userid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public long updateBBSTopicStatus(long id, String status)
	{
		String sql = "update hybbs.bbs_topic t set t.checked = ? where t.id = ?";
		Object[] params ={ status, id };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateBBSTopicStatus(long topicid)
	{
		String sql = "update hybbs.bbs_topic t set t.STATUS = 1 where t.id = ?";
		Object[] params ={ topicid };
		return jdbcTemplate.update(sql, params);
	}
	
	@Override
	public BBSTopic findTopicbytopicid(long topicid,long owner)
	{
		String sql = "select t.*,hu.nickname creater,wu.nickname wxname,wu.headimgurl,tt.content,su.createtime regtime,su.topicnum,su.replynum,l.level_name,hu.img,f.title forumname,tt.pic from hybbs.bbs_topic t join hybbs.bbs_forum f on t.FORUM_ID = f.id join hybbs.bbs_topic_text tt on t.id = tt.topicid join hybbs.bbs_user su on t.CREATER_UID = su.id left join esite.es_hy_user hu on su.hyuserid = hu.id left join esite.es_wx_user wu on hu.wxuid = wu.id left join hybbs.bbs_user_jf_level l on (su.jf_level_id = l.level_id and l.owner = ?) where t.id = ?";
		Object[] params = {owner,topicid};
		List<BBSTopic> list = jdbcTemplate.query(sql, params, new BBSTopicRowMapper());
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	class BBSTopicRowMapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			BBSTopic t = new BBSTopic();
			t.setId(rs.getLong("t.id"));
			t.setUp(rs.getInt("t.up"));
			t.setDown(rs.getInt("t.down"));
			t.setTYPE_ID(rs.getInt("t.TYPE_ID"));
			t.setTITLE(rs.getString("t.TITLE"));
			t.setCREATER_UID(rs.getLong("t.CREATER_UID"));
			t.setCREATE_TIME(rs.getTimestamp("t.CREATE_TIME"));
			t.setVIEW_COUNT(rs.getLong("t.VIEW_COUNT"));
			t.setREPLY_COUNT(rs.getInt("t.REPLY_COUNT"));
			t.setLAST_TIME(rs.getTimestamp("t.LAST_TIME"));
			t.setModerator_reply(rs.getInt("t.moderator_reply"));
			if(StringUtils.isNotBlank(rs.getString("creater"))){
				t.setCreater(rs.getString("creater"));
			}else{
				t.setCreater(rs.getString("wxname"));
			}
			t.setContent(rs.getString("tt.content"));
			t.setCreatetime(rs.getTimestamp("regtime"));
			t.setTopicnum(rs.getInt("su.topicnum"));
			t.setReplynum(rs.getInt("su.replynum"));
			t.setLevel_name(rs.getString("l.level_name"));
			if(StringUtils.isNotBlank(rs.getString("hu.img"))){
				t.setImg(rs.getString("hu.img"));
			}else{
				t.setImg(rs.getString("wu.headimgurl"));
			}
			t.setForumname(rs.getString("forumname"));
			String pic = rs.getString("tt.pic");
			if(StringUtils.isNotBlank(pic)){
				String[] str = pic.split(",");
				List<String> pic_list = Arrays.asList(str);
				t.setPic_list(pic_list);
			}
			return t;
		}
		
	}

	@Override
	public List<BBSTopic> findBBSPostbyTopicid(long topicid,long owner,int start,int size)
	{
		String sql = "select t.*,hu.nickname creater,wu.nickname wxname,wu.headimgurl,tt.POST_CONTENT content,su.createtime regtime,su.topicnum,su.replynum,l.level_name,hu.img from hybbs.bbs_post t join hybbs.bbs_post_text tt on t.id = tt.postid left join hybbs.bbs_user su on t.CREATER_ID = su.id left join esite.es_hy_user hu on su.hyuserid = hu.id left join esite.es_wx_user wu on hu.wxuid = wu.id  left join hybbs.bbs_user_jf_level l on (su.jf_level_id = l.level_id and l.owner = ?) where t.TOPIC_ID = ? and t.STATUS != 1 order by t.INDEX_COUNT limit ?,?";
		Object[] params = {owner,topicid,start,size};
		return jdbcTemplate.query(sql, params, new BBSTopic1RowMapper());
	}
	
	class BBSTopic1RowMapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			BBSTopic t = new BBSTopic();
			t.setPostid(rs.getLong("t.id"));
			t.setINDEX_COUNT(rs.getInt("t.INDEX_COUNT"));
			t.setTYPE_ID(rs.getInt("t.TYPE_ID"));
			t.setCREATER_UID(rs.getLong("t.CREATER_ID"));
			t.setCREATE_TIME(rs.getTimestamp("t.CREATE_TIME"));
			if(StringUtils.isNotBlank(rs.getString("creater"))){
				t.setCreater(rs.getString("creater"));
			}else{
				t.setCreater(rs.getString("wxname"));
			}
			t.setContent(rs.getString("tt.content"));
			t.setCreatetime(rs.getTimestamp("regtime"));
			t.setTopicnum(rs.getInt("su.topicnum"));
			t.setReplynum(rs.getInt("su.replynum"));
			t.setLevel_name(rs.getString("l.level_name"));
			if(StringUtils.isNotBlank(rs.getString("hu.img"))){
				t.setImg(rs.getString("hu.img"));
			}else{
				t.setImg(rs.getString("wu.headimgurl"));
			}
			t.setChecked(rs.getString("t.checked"));
			return t;
		}
		
	}
	
	
	@Override
	public long saveBBSPost(final BBSTopic topic,final String ip)
	{
		final int indexCount = findIdexCount(topic.getId()) + 1;
		final String sql = "insert into hybbs.bbs_post(TOPIC_ID,CREATER_ID,CREATE_TIME,POSTER_IP,INDEX_COUNT,TYPE_ID,checked) values(?,?,now(),?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql,
						new String[] { "id" });
				int i = 1;
				ps.setLong(i++, topic.getId());
				ps.setLong(i++, topic.getCREATER_UID());
				ps.setString(i++, ip);
				ps.setInt(i++, indexCount);
				ps.setInt(i++, topic.getTYPE_ID());
				ps.setString(i++, topic.getChecked());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
	
	public int findIdexCount(long topicid){
		String sql = "select count(*) from hybbs.bbs_post where TOPIC_ID = ? ";
		Object[] params = {topicid};
		return jdbcTemplate.queryForInt(sql,params);
	}

	@Override
	public long saveBBSPostext(final long postid,final BBSTopic topic)
	{
		final String sql = "insert into hybbs.bbs_post_text(postid,POST_TITLE,POST_CONTENT) values(?,?,?)";
		Object[] params = {postid,topic.getTITLE(),topic.getContent()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateBBSTopic(BBSTopic topic,long postid)
	{
		String sql = "update hybbs.bbs_topic set LAST_POST_ID = ?,REPLYER_UID = ?,LAST_TIME=now(),REPLY_COUNT = REPLY_COUNT + 1 where id = ? ";
		Object[] params = {postid,topic.getCREATER_UID(),topic.getId()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateBBSUserReplynum(long userid)
	{
		String sql = "update hybbs.bbs_user set replynum = replynum + 1 where id = ? ";
		Object[] params = {userid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateBBSTopicTop(long topicid, int top)
	{
		String sql = "update hybbs.bbs_topic set top = ? where id = ?";
		Object[] params = {top,topicid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateForumTopicnum(long forumid)
	{
		String sql = "update hybbs.bbs_forum set topicnum = topicnum + 1 where id = ?";
		Object[] params = {forumid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateForumpostnum(long forumid)
	{
		String sql = "update hybbs.bbs_forum set postnum = postnum + 1 where id = ?";
		Object[] params = {forumid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateBBSTopicDown(long topicid)
	{
		String sql = "update hybbs.bbs_topic set down = down + 1 where id = ?";
		Object[] params = {topicid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateBBSTopicUp(long topicid)
	{
		String sql = "update hybbs.bbs_topic set up = up + 1 where id = ?";
		Object[] params = {topicid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int findBBSPostCountbyTopicid(long topicid,long owner)
	{
		String sql = "select count(*) from hybbs.bbs_post t join hybbs.bbs_post_text tt on t.id = tt.postid left join hybbs.bbs_user su on t.CREATER_ID = su.id left join hybbs.bbs_user_jf_level l on (su.jf_level_id = l.level_id and l.owner = ?)  where t.TOPIC_ID = ? and t.STATUS != 1 order by t.INDEX_COUNT";
		Object[] params = {owner,topicid};
		return jdbcTemplate.queryForInt(sql,params);
	}

	@Override
	public BBSJfRule findBBSUserJfRuleByAction(long owner, int action)
	{
		String sql = "select * from hybbs.bbs_user_jf_rule where owner = ? and action = ?";
		Object[] params = {owner,action};
		List<BBSJfRule> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSJfRule r = new BBSJfRule();
				r.setId(rs.getLong("id"));
				r.setAction(rs.getInt("action"));
				r.setJifen(rs.getInt("jifen"));
				return r;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateBBUserJf(int jifen, long forumer)
	{
		String sql = "update hybbs.bbs_user set jf = jf + ? where id = ?";
		Object[] params = {jifen,forumer};
		return jdbcTemplate.update(sql,params);
	}

	@Override//16
	public List<BBSTopic> findTopicListByForumid(long forumid, int index, long owner,int start, int size)
	{	String sql = "";
		if(index == 0){
			sql = "select u.nickname,l.level_name,t.* from hybbs.bbs_forum f join hybbs.bbs_topic t on f.id = t.FORUM_ID join hybbs.bbs_user u on f.forumer = u.id left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where f.id = ? and t.STATUS != 1 and f.status != 'DEL' limit ?,?";
		}else if(index == 1){
			sql = "select u.nickname,l.level_name,t.* from hybbs.bbs_forum f join hybbs.bbs_topic t on f.id = t.FORUM_ID join hybbs.bbs_user u on f.forumer = u.id left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where f.id = ? and t.STATUS != 1 and f.status != 'DEL' order by t.REPLY_COUNT desc limit ?,? ";
		}else if(index == 2){
			sql = "select u.nickname,l.level_name,t.* from hybbs.bbs_forum f join hybbs.bbs_topic t on f.id = t.FORUM_ID join hybbs.bbs_user u on f.forumer = u.id left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where f.id = ? and t.STATUS != 1 and f.status != 'DEL' order by t.REPLY_COUNT limit ?,? ";
		}else if(index == 3){
			sql = "select u.nickname,l.level_name,t.* from hybbs.bbs_forum f join hybbs.bbs_topic t on f.id = t.FORUM_ID join hybbs.bbs_user u on f.forumer = u.id left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where f.id = ? and t.STATUS != 1 and f.status != 'DEL' order by t.up desc limit ?,? ";
		}else if(index == 4){
			sql = "select u.nickname,l.level_name,t.* from hybbs.bbs_forum f join hybbs.bbs_topic t on f.id = t.FORUM_ID join hybbs.bbs_user u on f.forumer = u.id left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where f.id = ? and t.STATUS != 1 and f.status != 'DEL' order by t.up limit ?,? ";
		}else if(index == 5){
			sql = "select u.nickname,l.level_name,t.* from hybbs.bbs_forum f join hybbs.bbs_topic t on f.id = t.FORUM_ID join hybbs.bbs_user u on f.forumer = u.id left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where f.id = ? and t.STATUS != 1 and f.status != 'DEL' order by t.down desc limit ?,? ";
		}else if(index == 6){
			sql = "select u.nickname,l.level_name,t.* from hybbs.bbs_forum f join hybbs.bbs_topic t on f.id = t.FORUM_ID join hybbs.bbs_user u on f.forumer = u.id left join hybbs.bbs_user_jf_level l on (u.jf_level_id = l.level_id and l.owner = ?) where f.id = ? and t.STATUS != 1 and f.status != 'DEL' order by t.down limit ?,? ";
		}
		Object[] params = {owner,forumid,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSTopic t = new BBSTopic();
				t.setId(rs.getLong("t.id"));
				t.setCREATER_UID(rs.getLong("t.CREATER_UID"));
				t.setTITLE(rs.getString("t.TITLE"));
				t.setCreater(rs.getString("u.nickname"));
				t.setLevel_name(rs.getString("l.level_name"));
				t.setChecked(rs.getString("t.checked"));
				t.setCREATE_TIME(rs.getTimestamp("t.CREATE_TIME"));
				t.setREPLY_COUNT(rs.getInt("t.REPLY_COUNT"));
				t.setUp(rs.getInt("t.up"));
				t.setDown(rs.getInt("t.down"));
				t.setTop(rs.getInt("t.top"));
				return t;
			}
			
		});
	}

	@Override
	public BBSTopic findBBSTopic(long topicid) {
		String sql = "select t.*,u.hyuserid from hybbs.bbs_topic t ,hybbs.bbs_user u where t.id = ? and t.STATUS != 1 and t.CREATER_UID=u.id";
		Object[] params = {topicid};
		List<BBSTopic> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BBSTopic b = new BBSTopic();
				b.setId(rs.getLong("id"));
				b.setEntityid(rs.getLong("entityid"));
				b.setEntype(rs.getInt("entype"));
				b.setCREATER_UID(rs.getLong("hyuserid"));
				return b;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public BBSTopic findTopicbytopicidAndOwner(long topicid, long owner,int entype) {
		String sql = "";
		if(entype == 0){//0-图文;1-产品;2-新闻;3-视频
			sql = "select t.*,hu.nickname creater,wu.nickname wxname,wu.headimgurl,tt.imgurl topcontent,su.createtime regtime,su.topicnum,su.replynum,l.level_name,hu.img,f.title forumname from hybbs.bbs_topic t join hybbs.bbs_forum f on t.FORUM_ID = f.id join esite.es_content_pic tt on t.entityid = tt.id join hybbs.bbs_user su on t.CREATER_UID = su.id left join esite.es_hy_user hu on su.hyuserid = hu.id left join esite.es_wx_user wu on hu.wxuid = wu.id left join hybbs.bbs_user_jf_level l on (su.jf_level_id = l.level_id and l.owner = ?) where t.id = ?";
		}else if(entype == 1){
			sql = "select t.*,hu.nickname creater,wu.nickname wxname,wu.headimgurl,tt.simgurl topcontent,su.createtime regtime,su.topicnum,su.replynum,l.level_name,hu.img,f.title forumname from hybbs.bbs_topic t join hybbs.bbs_forum f on t.FORUM_ID = f.id join esite.es_content_product tt on t.entityid = tt.id join hybbs.bbs_user su on t.CREATER_UID = su.id left join esite.es_hy_user hu on su.hyuserid = hu.id left join esite.es_wx_user wu on hu.wxuid = wu.id left join hybbs.bbs_user_jf_level l on (su.jf_level_id = l.level_id and l.owner = ?) where t.id = ?";
		}else if(entype == 2){
			sql = "select t.*,hu.nickname creater,wu.nickname wxname,wu.headimgurl,tt.content topcontent,tt.islink,tt.linkurl,su.createtime regtime,su.topicnum,su.replynum,l.level_name,hu.img,f.title forumname from hybbs.bbs_topic t join hybbs.bbs_forum f on t.FORUM_ID = f.id join esite.es_content_news tt on t.entityid = tt.id join hybbs.bbs_user su on t.CREATER_UID = su.id left join esite.es_hy_user hu on su.hyuserid = hu.id left join esite.es_wx_user wu on hu.wxuid = wu.id left join hybbs.bbs_user_jf_level l on (su.jf_level_id = l.level_id and l.owner = ?) where t.id = ?";
		}else if(entype == 3){
			sql = "select t.*,hu.nickname creater,wu.nickname wxname,wu.headimgurl,tt.picurl topcontent,su.createtime regtime,su.topicnum,su.replynum,l.level_name,hu.img,f.title forumname from hybbs.bbs_topic t join hybbs.bbs_forum f on t.FORUM_ID = f.id join esite.es_content_video tt on t.entityid = tt.id join hybbs.bbs_user su on t.CREATER_UID = su.id left join esite.es_hy_user hu on su.hyuserid = hu.id left join esite.es_wx_user wu on hu.wxuid = wu.id left join hybbs.bbs_user_jf_level l on (su.jf_level_id = l.level_id and l.owner = ?) where t.id = ?";
		}
		Object[] params = {owner,topicid};
		List<BBSTopic> list = null;
		if(entype == 2){
			 list = jdbcTemplate.query(sql, params, new myRowMapper());
		}else{
			list = jdbcTemplate.query(sql, params, new RowMapper(){
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					BBSTopic t = new BBSTopic();
					t.setId(rs.getLong("t.id"));
					t.setUp(rs.getInt("t.up"));
					t.setDown(rs.getInt("t.down"));
					t.setTYPE_ID(rs.getInt("t.TYPE_ID"));
					t.setTITLE(rs.getString("t.TITLE"));
					t.setCREATER_UID(rs.getLong("t.CREATER_UID"));
					t.setCREATE_TIME(rs.getTimestamp("t.CREATE_TIME"));
					t.setVIEW_COUNT(rs.getLong("t.VIEW_COUNT"));
					t.setREPLY_COUNT(rs.getInt("t.REPLY_COUNT"));
					t.setLAST_TIME(rs.getTimestamp("t.LAST_TIME"));
					t.setModerator_reply(rs.getInt("t.moderator_reply"));
					if(StringUtils.isNotBlank(rs.getString("creater"))){
						t.setCreater(rs.getString("creater"));
					}else{
						t.setCreater(rs.getString("wxname"));
					}
					if(StringUtils.isNotBlank(rs.getString("hu.img"))){
						t.setImg(rs.getString("hu.img"));
					}else{
						t.setImg(rs.getString("wu.headimgurl"));
					}
					t.setContent(rs.getString("topcontent"));
					t.setCreatetime(rs.getTimestamp("regtime"));
					t.setTopicnum(rs.getInt("su.topicnum"));
					t.setReplynum(rs.getInt("su.replynum"));
					t.setLevel_name(rs.getString("l.level_name"));
					t.setEntityid(rs.getLong("t.entityid"));
					t.setEntype(rs.getInt("t.entype"));
					t.setForumname(rs.getString("forumname"));
					return t;
				}
				
			});
		}
		
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	class myRowMapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			BBSTopic t = new BBSTopic();
			t.setId(rs.getLong("t.id"));
			t.setUp(rs.getInt("t.up"));
			t.setDown(rs.getInt("t.down"));
			t.setTYPE_ID(rs.getInt("t.TYPE_ID"));
			t.setTITLE(rs.getString("t.TITLE"));
			t.setCREATER_UID(rs.getLong("t.CREATER_UID"));
			t.setCREATE_TIME(rs.getTimestamp("t.CREATE_TIME"));
			t.setVIEW_COUNT(rs.getLong("t.VIEW_COUNT"));
			t.setREPLY_COUNT(rs.getInt("t.REPLY_COUNT"));
			t.setLAST_TIME(rs.getTimestamp("t.LAST_TIME"));
			t.setModerator_reply(rs.getInt("t.moderator_reply"));
			t.setContent(rs.getString("topcontent"));
			t.setCreatetime(rs.getTimestamp("regtime"));
			t.setTopicnum(rs.getInt("su.topicnum"));
			t.setReplynum(rs.getInt("su.replynum"));
			t.setLevel_name(rs.getString("l.level_name"));
			t.setEntype(rs.getInt("t.entype"));
			t.setIslink(rs.getString("tt.islink"));
			t.setLinkurl(rs.getString("tt.linkurl"));
			if(StringUtils.isNotBlank(rs.getString("creater"))){
				t.setCreater(rs.getString("creater"));
			}else{
				t.setCreater(rs.getString("wxname"));
			}
			if(StringUtils.isNotBlank(rs.getString("hu.img"))){
				t.setImg(rs.getString("hu.img"));
			}else{
				t.setImg(rs.getString("wu.headimgurl"));
			}
			t.setForumname(rs.getString("forumname"));
			return t;
		}
	}

	@Override
	public int updateBBSTopicById(BBSTopic topic) {
		String sql = "update hybbs.bbs_topic set TITLE = ? where id = ?";
		Object[] params = {topic.getTITLE(),topic.getId()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateBBSTopicContentById(long topicid, BBSTopicText topicText) {
		String sql = "update hybbs.bbs_topic_text set content = ? where topicid = ?";
		Object[] params = {topicText.getContent(),topicid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int delReplyTopic(long postid) {
		String sql = "update hybbs.bbs_post set STATUS = 1 where id = ?";
		Object[] params = {postid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateBBSTopicReplyCount(long topicid) {
		String sql = "update hybbs.bbs_topic t set t.REPLY_COUNT = t.REPLY_COUNT - 1 where t.id = ?";
		Object[] params ={ topicid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public BBSForum findBBSForumById(long forumid)
	{
		String sql = "select * from hybbs.bbs_forum where id = ?";
		Object[] params ={ forumid};
		List<BBSForum> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSForum f = new BBSForum();
				f.setTitle(rs.getString("title"));
				f.setTopicCheck(rs.getString("topicCheck"));
				return f;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public BBSForum findForumById(long forumid)
	{
		String sql = "SELECT * FROM hybbs.bbs_forum WHERE id=?";
		Object[] params = { forumid };
		List<BBSForum> list = jdbcTemplate.query(sql, params, new ForumRowMapper());
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
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
			return f;
		}

	}

	@Override
	public int updateBBSPostChecked(long postid, String checked)
	{
		String sql = "update hybbs.bbs_post p set p.checked = ? where id = ?";
		Object[] params = { checked, postid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateBBSForumTopicnum(long forumid)
	{
		String sql = "update hybbs.bbs_forum set topicnum = topicnum - 1 where id = ?";
		Object[] params = { forumid};
		return jdbcTemplate.update(sql, params);
	}
}
