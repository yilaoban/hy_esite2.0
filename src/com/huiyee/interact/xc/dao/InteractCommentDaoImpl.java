
package com.huiyee.interact.xc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.google.gson.Gson;
import com.huiyee.esite.model.SinaUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.xc.model.CheckinConfig;
import com.huiyee.interact.xc.model.CommentConfig;
import com.huiyee.interact.xc.model.Xc;
import com.huiyee.interact.xc.model.XcCommentRecord;

public class InteractCommentDaoImpl implements InteractCommentDao
{

	private JdbcTemplate jdbcTemplate;
	Gson gson = new Gson();

	public JdbcTemplate getJdbcTemplate()
	{
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final String SAVE_COMMENT = "insert into es_interact_xc_comment_record(pageid,xcid,uid,utype,content,img,createtime,ip,terminal,source,status)values(?,?,?,?,?,?,now(),?,?,?,?)";

	@Override
	public int saveComment(XcCommentRecord xcRecord)
	{
		Object[] params =
		{ xcRecord.getPageid(), xcRecord.getXcid(), xcRecord.getUid(), xcRecord.getUtype(), xcRecord.getContent(), xcRecord.getImg(), xcRecord.getIp(), xcRecord.getTerminal(),
				xcRecord.getSource(), xcRecord.getStatus() };
		return getJdbcTemplate().update(SAVE_COMMENT, params);
	}

	@Override
	public List<XcCommentRecord> findCommentRecordList(long xcid, long pageid, long start, int size)
	{
		String sql = "";
		Object[] params;
		if (start == 0)
		{
			params = new Object[]
			{ xcid, pageid, size };
			sql = "select * from (select * from es_interact_xc_comment_record where xcid = ? and pageid=? order by id desc limit ? ) t order by t.id asc";
		} else
		{
			params = new Object[]
			{ xcid, pageid, start, size };
			sql = "select * from es_interact_xc_comment_record where xcid=? and pageid=? and id > ? order by id asc limit ?";
		}
		return getJdbcTemplate().query(sql, params, new CommentRecordMapper());
	}

	class CommentRecordMapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException
		{
			XcCommentRecord crm = new XcCommentRecord();
			crm.setId(rs.getLong("id"));
			crm.setXcid(rs.getLong("xcid"));
			crm.setPageid(rs.getLong("pageid"));
			crm.setUid(rs.getLong("uid"));
			crm.setUtype(rs.getInt("utype"));
			crm.setContent(rs.getString("content"));
			crm.setImg(rs.getString("img"));
			crm.setStatus(rs.getString("status"));
			crm.setCreatetime(rs.getTimestamp("createtime"));
			return crm;
		}
	}

	@Override
	public int commentRecordTotal(long commentid)
	{
		String sql = "select count(*) from es_interact_xc_comment_record where xcid=?";
		return getJdbcTemplate().queryForInt(sql, new Object[]
		{ commentid });
	}

	@Override
	public List<XcCommentRecord> findCommentRecord(long xcid, long start, int size)
	{
		String sql = "";
		Object[] params;
		if (start == 0)
		{
			params = new Object[]
			{ xcid, size };
			sql = "select * from (select * from es_interact_xc_comment_record where xcid = ? order by id desc limit ? ) t order by t.id asc";
		} else
		{
			params = new Object[]
			{ xcid, start, size };
			sql = "select * from es_interact_xc_comment_record where xcid = ? and id > ? order by id asc limit ?";
		}
		return jdbcTemplate.query(sql, params, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				XcCommentRecord crm = new XcCommentRecord();
				crm.setId(rs.getLong("id"));
				crm.setXcid(rs.getLong("xcid"));
				crm.setPageid(rs.getLong("pageid"));
				crm.setUid(rs.getLong("uid"));
				crm.setUtype(rs.getInt("utype"));
				crm.setContent(rs.getString("content"));
				crm.setImg(rs.getString("img"));
				crm.setStatus(rs.getString("status"));
				crm.setCreatetime(rs.getTimestamp("createtime"));
				return crm;
			}
		});
	}

	private static final String FIND_WX_USER = "select * from es_wx_user where id = ?";

	@Override
	public WxUser findWxUser(long wxuid)
	{
		Object[] params =
		{ wxuid };
		List<WxUser> list = jdbcTemplate.query(FIND_WX_USER, params, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				WxUser u = new WxUser();
				u.setNickname(rs.getString("nickname"));
				u.setHeadimgurl(rs.getString("headimgurl"));
				return u;
			}

		});
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	private static final String FIND_SINA_USER = "select * from es_sina_user where wbuid = ?";

	@Override
	public SinaUser findSinaUser(long wbuid)
	{
		Object[] params =
		{ wbuid };
		List<SinaUser> list = jdbcTemplate.query(FIND_SINA_USER, params, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				SinaUser u = new SinaUser();
				u.setNickname(rs.getString("nickname"));
				u.setImageurl(rs.getString("url"));
				return u;
			}

		});
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<XcCommentRecord> findCommentRecord1(long xcid, long start, int size)
	{
		String sql = "";
		Object[] params;
		if (start == 0)
		{
			params = new Object[]
			{ xcid, size };
			sql = "select * from (select * from es_interact_xc_comment_record where xcid = ? and status = 'CMP' order by id desc limit ? ) t order by t.id asc";
		} else
		{
			params = new Object[]
			{ xcid, start, size };
			sql = "select * from es_interact_xc_comment_record where xcid = ? and id > ? and status = 'CMP' order by id asc limit ?";
		}
		return getJdbcTemplate().query(sql, params, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				XcCommentRecord crm = new XcCommentRecord();
				crm.setId(rs.getLong("id"));
				crm.setXcid(rs.getLong("xcid"));
				crm.setPageid(rs.getLong("pageid"));
				crm.setUid(rs.getLong("uid"));
				crm.setUtype(rs.getInt("utype"));
				crm.setContent(rs.getString("content"));
				crm.setImg(rs.getString("img"));
				crm.setStatus(rs.getString("status"));
				crm.setCreatetime(rs.getTimestamp("createtime"));
				return crm;
			}
		});
	}

	@Override
	public int findCommenterTotal(long xcid)
	{
		String sql = "select count(*) from es_interact_xc_comment_record where xcid=?";
		return getJdbcTemplate().queryForInt(sql, new Object[]
		{ xcid });
	}

	@Override
	public int findCommenterTotal1(long xcid)
	{
		String sql = "select count(*) from es_interact_xc_comment_record where xcid=? and status='CMP'";
		return getJdbcTemplate().queryForInt(sql, new Object[]
		{ xcid });
	}

	@Override
	public int findUserCommentNum(long uid, int utype, long xcid)
	{
		String sql = "select count(id) from es_interact_xc_comment_record where uid=? and utype=? and xcid=?";
		return getJdbcTemplate().queryForInt(sql, new Object[]
		{ uid, utype, xcid });
	}

	@Override
	public List<Long> findCommentUser(int size)
	{
		String sql = "select uid from es_interact_xc_comment_record where xcid=1 group by uid order by rand() limit ?";
		return getJdbcTemplate().queryForList(sql, new Object[]
		{ size }, Long.class);
	}

	@Override
	public XcCommentRecord findLastComment(Long wxuid)
	{
		String sql = "select * from es_interact_xc_comment_record where xcid = 1 and uid =? and utype=1 order by id desc limit 1";
		Object[] params = new Object[]
		{ wxuid };
		List<XcCommentRecord> list = getJdbcTemplate().query(sql, params, new CommentRecordMapper());
		return list.size() > 0 ? list.get(0) : null;
	}
}
