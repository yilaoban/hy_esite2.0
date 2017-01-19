package com.huiyee.interact.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dto.EsForum;
import com.huiyee.esite.dto.BBSAccount;
import com.huiyee.interact.bbs.model.BBS;
import com.huiyee.interact.bbs.model.BBSCategory;
import com.huiyee.interact.bbs.model.BBSContent;
import com.huiyee.interact.bbs.model.BBSForum;

public class BBSDao implements IBBSDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public BBS findBBS(long id)
	{
		List<BBS> ls = jdbcTemplate.query("select * from hybbs.bbs_forum where id=?", new Object[]
		{ id }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBS bs = new BBS();
				bs.setId(rs.getLong("id"));
				bs.setVisittype(rs.getInt("visittype"));
				bs.setOvisittype(rs.getInt("ovisittype"));
				return bs;
			}

		});
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}
		return null;
	}

	@Override
	public List<BBSCategory> findCListByOwner(long owner, String typeName)
	{
		return jdbcTemplate.query("select * from hybbs.bbs_category where owner=? and name=?", new Object[]
		{ owner, typeName }, new RowMapper()
		{
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSCategory c = new BBSCategory();
				c.setBbsid(rs.getLong("id"));
				c.setOwner(rs.getLong("owner"));
				return c;
			}
		});
	}

	@Override
	public List<BBSForum> findListByCid(long bbsid,long accid)
	{
		return jdbcTemplate.query("select * from hybbs.bbs_forum f join hybbs.bbs_forum_account a on f.id=a.forumid where cateid=? and a.accid=? and f.status!='DEL'", new Object[]
		{ bbsid,accid }, new RowMapper()
		{
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSForum f = new BBSForum();
				f.setId(rs.getLong("id"));
				f.setTitle(rs.getString("title"));
				f.setCreatetime(rs.getTimestamp("createtime"));
				return f;
			}
		});
	}

	@Override
	public long findBBSCateByTypeName(String typeName, long owner)
	{
		List<Long> list = jdbcTemplate.query("select id from hybbs.bbs_category where name=? and owner=?", new Object[]
		{ typeName, owner }, new RowMapper()
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
	public long addBBSCate(final String typeName, final long owner)
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
				ps.setLong(i++, owner);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	private static final String SAVE_BBS_FORUM = "insert into hybbs.bbs_forum (cateid,title,createtime,forumer,forumname) values(?,?,now(),?,?)";

	@Override
	public long saveBBSForum(final long cateid, final String entityName, final long forumer, final String forumname)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(SAVE_BBS_FORUM, new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, cateid);
				ps.setString(i++, entityName);
				ps.setLong(i++, forumer);
				ps.setString(i++, forumname);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public BBSForum findForumById(long forumid)
	{
		List<BBSForum> list = jdbcTemplate.query("select * from hybbs.bbs_forum where id=?", new Object[]
		{ forumid }, new RowMapper()
		{
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSForum f = new BBSForum();
				f.setId(rs.getLong("id"));
				f.setTitle(rs.getString("title"));
				f.setCreatetime(rs.getTimestamp("createtime"));
				f.setForumer(rs.getLong("forumer"));
				return f;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	private static final String FIND_DATA = "select * from hybbs.bbs_topic where entityid=? and entype=?";

	@Override
	public BBSContent findData(String pid, int entityType)
	{
		List<BBSContent> list = jdbcTemplate.query(FIND_DATA, new Object[]
		{ pid, entityType }, new RowMapper()
		{
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSContent bbsc = new BBSContent();
				bbsc.setPid(rs.getLong("entityid"));
				bbsc.setCai(rs.getInt("down"));
				bbsc.setZan(rs.getInt("up"));
				bbsc.setComment(rs.getInt("REPLY_COUNT"));
				bbsc.setForumid(rs.getLong("FORUM_ID"));
				bbsc.setTopicid(rs.getLong("id"));
				return bbsc;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}
	
	@Override
	public List<BBSAccount> findBBSAccount(long owner)
	{
		return jdbcTemplate.query("select * from hybbs.bbs_forum_account r join hybbs.bbs_forum f on r.forumid=f.id join esite.es_owner_account a on a.id=r.accid where r.owner=?", new Object[]{owner}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSAccount a = new BBSAccount();
				a.setAccid(rs.getLong("r.accid"));
				a.setOwner(rs.getLong("r.owner"));
				a.setId(rs.getLong("r.id"));
				a.setAccname(rs.getString("a.username"));
				a.setForumid(rs.getLong("r.forumid"));
				a.setForumname(rs.getString("f.title"));
				return a;
			}
		});
	}
	
	@Override
	public List<EsForum> findBbsForumByOwner(long owner)
	{
		return jdbcTemplate.query("select * from hybbs.bbs_forum f join hybbs.bbs_category c on f.cateid=c.id where c.owner=?", new Object[]{owner}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				EsForum f=new EsForum();
				f.setId(rs.getLong("f.id"));
				f.setName(rs.getString("f.title"));
				return f;
			}
		});
	}
	
	
	@Override
	public int deleteBBsAccount(BBSAccount bbsa)
	{
		return jdbcTemplate.update("delete from hybbs.bbs_forum_account where id=?", new Object[]{bbsa.getId()});
	}
	
	@Override
	public int saveBBsAccount(BBSAccount bbsa)
	{
		return jdbcTemplate.update("insert ignore into hybbs.bbs_forum_account (owner,accid,forumid) values(?,?,?)", new Object[]{bbsa.getOwner(),bbsa.getAccid(),bbsa.getForumid()});
	}

}
