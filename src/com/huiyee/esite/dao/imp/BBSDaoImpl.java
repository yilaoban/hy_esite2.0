package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.groovy.GJson;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.IBBSDao;
import com.huiyee.esite.model.BBSCategory;
import com.huiyee.esite.model.BBSForum;
import com.huiyee.esite.model.Page;
import com.huiyee.interact.bbs.model.BBSUser;

public class BBSDaoImpl implements IBBSDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<BBSCategory> findForumByOwnerid(long ownerid)
	{
		String sql = "select cat.* from hybbs.bbs_category cat where cat.owner = ? and (cat.name = '新闻' or cat.name='图片' or cat.name='视频' or cat.name='产品')";
		Object[] params = {ownerid};
		List<BBSCategory> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSCategory c = new BBSCategory();
				c.setId(rs.getLong("cat.id"));
				c.setName(rs.getString("cat.name"));
				c.setOwner(rs.getLong("cat.owner"));
				return c;
			}
			
		});
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public int saveBBSCategoryByOwner(long ownerid,String name,int rank)
	{
		String sql = "insert into hybbs.bbs_category(name,owner,rank) values (?,?,?)";
		Object[] params = {name,ownerid,rank};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public BBSCategory findCatNameByCatid(long catid)
	{
		String sql = "select * from hybbs.bbs_category cat where id = ?";
		Object[] params = {catid};
		List<BBSCategory> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSCategory c = new BBSCategory();
				c.setId(rs.getLong("id"));
				c.setName(rs.getString("name"));
				return c;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public long saveBBSForum(final String title,final long catid)
	{
		final String sql = "insert into hybbs.bbs_forum(title,cateid,createtime) values(?,?,now()) ";
//		Object[] params = {title,catid};
//		return jdbcTemplate.update(sql, params);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql,
						new String[] { "id" });
				ps.setString(1, title);
				ps.setLong(2, catid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}

	@Override
	public List<BBSForum> findForumListByOwnerid(long ownerid,long accid,int start,int size)
	{
		String sql = "select * from hybbs.bbs_category c join hybbs.bbs_forum f on c.id = f.cateid join hybbs.bbs_forum_account a on a.forumid = f.id where c.owner = ? and a.accid = ? and f.status != 'DEL' order by f.createtime desc limit ?,?";
		Object[] params = {ownerid,accid,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSForum f = new BBSForum();
				f.setId(rs.getLong("f.id"));
				f.setCateid(rs.getLong("f.cateid"));
				f.setTitle(rs.getString("f.title"));
				f.setCreatetime(rs.getTimestamp("f.createtime"));
				f.setTopicnum(rs.getInt("f.topicnum"));
				f.setCatname(rs.getString("c.name"));
				return f;
			}
			
		});
	}

	@Override
	public int updateForumName(BBSForum forum)
	{
		String sql = "update hybbs.bbs_forum f set f.title = ? where f.id = ?";
		Object[] params = {forum.getTitle(),forum.getId()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public List<BBSCategory> findBBSCategoryByOwnerid(long ownerid)
	{
		String sql = "select * from hybbs.bbs_category cat where cat.owner = ?";
		Object[] params = {ownerid};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSCategory c = new BBSCategory();
				c.setId(rs.getLong("id"));
				c.setName(rs.getString("name"));
				return c;
			}
			
		});
	}

	@Override
	public int findTotlaForumByOwnerid(long ownerid,long accid)
	{
		String sql = "select count(*) from hybbs.bbs_category c join hybbs.bbs_forum f on c.id = f.cateid join hybbs.bbs_forum_account a on f.id = a.forumid where c.owner = ? and a.accid = ? and f.status != 'DEL'";
		Object[] params = {ownerid,accid};
		return jdbcTemplate.queryForInt(sql,params);
	}

	@Override
	public int updateBBSForumByForumid(BBSForum forum)
	{
		String sql = "update hybbs.bbs_forum f set f.forumer = ?,f.forumname=? where f.id = ?";
		Object[] params = {forum.getForumer(),forum.getForumname(),forum.getId()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateBBSForumPermissionByForumid(BBSForum forum)
	{
		String sql = "update hybbs.bbs_forum f set f.visittype = ?,f.ovisittype=?,f.topicCheck=?,f.commentCheck = ?,f.loginpageid=?,f.registerpageid=? where f.id = ?";
		Object[] params = {forum.getVisittype(),forum.getOvisittype(),forum.getTopicCheck(),forum.getCommentCheck(),forum.getLoginpageid(),forum.getRegisterpageid(),forum.getId()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public BBSForum findBBSForumById(long id)
	{
		String sql = "select * from hybbs.bbs_forum f left join hybbs.bbs_user u on f.forumer = u.id left join esite.es_hy_user hu on u.hyuserid = hu.id where f.id = ? and f.status != 'DEL'";
		Object[] params = {id};
		List<BBSForum> list = jdbcTemplate.query(sql,params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSForum f = new BBSForum();
				f.setId(rs.getLong("f.id"));
				f.setCateid(rs.getLong("f.cateid"));
				f.setTitle(rs.getString("f.title"));
				f.setCreatetime(rs.getTimestamp("f.createtime"));
				f.setTopicnum(rs.getInt("f.topicnum"));
				f.setForumer(rs.getLong("f.forumer"));
				f.setForumname(rs.getString("f.forumname"));
				f.setVisittype(rs.getInt("f.visittype"));
				f.setTopicCheck(rs.getString("f.topicCheck"));
				f.setCommentCheck(rs.getString("f.commentCheck"));
				f.setNickname(rs.getString("hu.nickname"));
				f.setRegisterpageid(rs.getLong("f.registerpageid"));
				f.setLoginpageid(rs.getLong("f.loginpageid"));
				f.setImg(rs.getString("hu.img"));
				f.setOvisittype(rs.getInt("f.ovisittype"));
				return f;
			}
			
		});
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public long saveBBSUser(final String ip,final long owner,final long hyuserid)
	{
		final String sql = "insert into hybbs.bbs_user(createtime,createip,owner,hyuserid) values(now(),?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql,
						new String[] { "id" });
				ps.setString(1, ip);
				ps.setLong(2, owner);
				ps.setLong(3, hyuserid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}

	@Override
	public int updateBBSUserById(long forumer, String nickname,String img)
	{
		String sql = "update hybbs.bbs_user u set u.nickname = ?,u.img = ? where u.id = ?";
		Object[] params = {nickname,img,forumer};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int saveBBSForumAccount(long forumid, long accid, long owner)
	{
		String sql = "insert into hybbs.bbs_forum_account(owner,accid,forumid) values(?,?,?) ";
		Object[] params = {owner,accid,forumid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public List<Page> findPageidByOwnerid(long ownerid) {
		String sql = "select p.id,p.name,g.groupName from es_site s join es_page p on s.id = p.siteid join es_site_group g on s.sitegroupid = g.id where s.ownerid = ? and p.isonline = 'Y' and (p.relationid is null and p.contextid is null) and p.status != 'DEL' and g.type = 'B' order by p.createtime desc limit 20";
		Object[] params = {ownerid};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Page p = new Page();
				p.setId(rs.getLong("p.id"));
				p.setName(rs.getString("p.name"));
				p.setSitename(rs.getString("g.groupName"));
				return p;
			}
			
		});
	}

	@Override
	public int delforum(long forumid) {
		String sql = "update hybbs.bbs_forum set status = 'DEL' where id = ?";
		Object[] params = {forumid};
		return jdbcTemplate.update(sql, params);
	}
	
	@Override
	public List<Long> findEntityByType(long owner, int type) {
		String sql="select * from hybbs.bbs_topic t  join hybbs.bbs_forum f on t.FORUM_ID=f.id join hybbs.bbs_category c on f.cateid=c.id where c.owner=? and t.entype=?";
		return jdbcTemplate.query(sql, new Object[]{owner,type}, new RowMapper(){
			public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
				return arg0.getLong("t.entityid");
			}
		});
	}

	@Override
	public int findForumByOwneridAndTitle(String title, long cateid, long ownerid) {
		String sql = "select count(*) from hybbs.bbs_category c join hybbs.bbs_forum f on c.id = f.cateid where f.title = ? and c.owner = ? and f.cateid = ? and f.status != 'DEL'";
		Object[] params = {title,ownerid,cateid};
		return jdbcTemplate.queryForInt(sql, params);
	}
	@Override
	public List<String> findSource(long topicid) {
		
		String sql="select t.id topicid,f.id forumid,c.id cateid from hybbs.bbs_topic t join hybbs.bbs_forum f on t.FORUM_ID=f.id join hybbs.bbs_category c on f.cateid=c.id where t.id=?";
		List<List<String>> list=jdbcTemplate.query(sql, new Object[]{topicid}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				List<String> list=new ArrayList<String>();
				list.add(rs.getString("cateid"));
				list.add(rs.getString("forumid"));
				list.add(rs.getString("topicid"));
				return list;
			}
		});
		if(list.size()>0&&list.get(0).size()==3){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public long findBBSCateByTypeName(String typeName, long owner)
	{
		List<Long> list = jdbcTemplate.query("select id from hybbs.bbs_category where name=? and owner=?", new Object[]{ typeName, owner }, new RowMapper()
		{
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("id");
			}
		});
		return list.size() > 0 ? list.get(0) : 0;
	}

	private static final String SAVE_BBS_CATEGORY = "insert into hybbs.bbs_category (name,owner,rank) values(?,?,0)";
	@Override
	public long addBBSCate(final String typeName,final long ownerid)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(SAVE_BBS_CATEGORY, new String[]
				{ "id" });
				int i = 1;
				ps.setString(i++, typeName);
				ps.setLong(i++, ownerid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public long saveHyUser(final long owner,final String nickname,final String img)
	{
		KeyHolder keyholder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement("insert into esite.es_hy_user (owner,nickname,img) values(?,?,?)", new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, owner);
				ps.setString(i++, nickname);
				ps.setString(i++, img);
				return ps;
			}

		}, keyholder);
		return keyholder.getKey().longValue();
}

	@Override
	public BBSUser findBBSUserById(long id)
	{
		String sql = "select * from hybbs.bbs_user where id = ?";
		Object[] params = {id};
		List<BBSUser> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSUser bbsUser = new BBSUser();
				bbsUser.setId(rs.getLong("id"));
				bbsUser.setHyuserid(rs.getLong("hyuserid"));
				return bbsUser;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateHyUserById(long hyuserid, String nickname, String img)
	{
		String sql = "update esite.es_hy_user u set u.nickname = ?,u.img = ? where u.id = ?";
		Object[] params = {nickname,img,hyuserid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateBBSUserHyUser(long hyuserid, long id)
	{
		String sql = "update hybbs.bbs_user set hyuserid = ? where id = ?";
		Object[] params = {hyuserid,id};
		return jdbcTemplate.update(sql, params);
	}
	
}
