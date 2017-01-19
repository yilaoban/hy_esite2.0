package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.IMbDao;
import com.huiyee.esite.model.Mb;
import com.huiyee.esite.model.Tag;


public class MbDaoImpl implements IMbDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Mb> findMbList(String type, long tagid)
	{
		List<Object> params = new ArrayList<Object>();
		String sql = "";
		params.add(type);
		if(tagid > 0){
			sql = "select * from es_mb m left join es_mb_tag t on m.id = t.mbid where m.type = ? and m.status != 'DEL' and t.tagid = ? order by m.idx asc";
			params.add(tagid);
		}else{
			sql = "select * from es_mb m where m.type = ? and m.status != 'DEL' order by m.idx asc";
		}
		return jdbcTemplate.query(sql, params.toArray(), new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Mb mb = new Mb();
				mb.setId(rs.getLong("m.id"));
				mb.setTitle(rs.getString("m.title"));
				mb.setShortdesc(rs.getString("m.shortdesc"));
				mb.setImg(rs.getString("m.img"));
				mb.setType(rs.getString("m.type"));
				mb.setLink(rs.getString("m.link"));
				mb.setCreatetime(rs.getTimestamp("m.createtime"));
				return mb;
			}
			
		});
	}

	@Override
	public List<Tag> findTagList(String type)
	{
		String sql = "select t.*,count(*) count from es_tag t join es_mb_tag t1 on t1.tagid = t.id where t.type = ? group by t1.tagid";
		Object[] params = {type};
		return jdbcTemplate.query(sql, params,new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Tag t = new Tag();
				t.setName(rs.getString("t.name"));
				t.setId(rs.getLong("t.id"));
				t.setType(rs.getString("t.type"));
				t.setCount(rs.getInt("count"));
				return t;
			}
		});
	}

	private static final String SAVE_MB="insert into es_mb(title,shortdesc,img,link,type,createtime,ownerid) values(?,?,?,?,?,now(),?)";
	@Override
	public long saveMb(final Mb mb)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(SAVE_MB, new String[]
				{ "id" });
				ps.setString(1, mb.getTitle());
				ps.setString(2, mb.getShortdesc());
				ps.setString(3, mb.getImg());
				ps.setString(4, mb.getLink());
				ps.setString(5, mb.getType());
				ps.setLong(6, mb.getOwnerid());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	private static final String FIND_TAG="select id from es_tag where name=? and type=?";
	@Override
	public long findTag(String tag,String type)
	{
		Object[] param={tag,type};
		try
		{
			return jdbcTemplate.queryForLong(FIND_TAG,param);
		} catch (DataAccessException e)
		{
			return 0;
		}
	}

	private static final String SAVE_TAG="insert into es_tag(name,type) values(?,?)";
	@Override
	public long saveTag(final String tag,final String type)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(SAVE_TAG, new String[]
				{ "id" });
				ps.setString(1, tag);
				ps.setString(2, type);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	private static final String SAVE_MB_PAGE="insert into es_mb_page(mbid,pageid) values(?,?)";
	@Override
	public int saveMbPage(long mbid, long pageid)
	{
		Object[] params={mbid,pageid};
		return jdbcTemplate.update(SAVE_MB_PAGE, params);
	}

	private static final String SAVE_MB_TAG="insert into es_mb_tag(mbid,tagid) values(?,?)";
	@Override
	public int saveMbTag(long mbid, long tagid)
	{
		Object[] params={mbid,tagid};
		return jdbcTemplate.update(SAVE_MB_TAG, params);
	}

	@Override
	public int findTotalMb(String type)
	{
		String sql = "select count(id) from es_mb m where m.type = ? and m.status != 'DEL'";
		Object[] params = {type};
		return jdbcTemplate.queryForInt(sql, params);
	}

	private static final String FIND_PAGE_TAG="select count(mt.id) from es_tag g join es_mb_tag mt on g.id = mt.tagid where mt.mbid = ? and g.name=?";
	@Override
	public int findMbTag(long mbid, String tag)
	{
		Object[] params={mbid,tag};
		return jdbcTemplate.queryForInt(FIND_PAGE_TAG,params);
	}
	
	
}
