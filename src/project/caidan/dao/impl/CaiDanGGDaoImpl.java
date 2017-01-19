package project.caidan.dao.impl;

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

import project.caidan.dao.ICaiDanGGDao;
import project.caidan.model.TfGG;

import com.huiyee.interact.ad.model.Adgg;


public class CaiDanGGDaoImpl implements ICaiDanGGDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public long saveTfGG(final long ggid,final long owner,final int idx,final int type)
	{
		final String sql = "insert into caidan.cd_tf_gg(ggid,idx,owner,type) values(?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{

			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, ggid);
				ps.setInt(i++, idx);
				ps.setLong(i++, owner);
				ps.setInt(i++, type);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public int findGGMaxIdx(long owner,int type)
	{
		return jdbcTemplate.queryForInt("select max(idx) from caidan.cd_tf_gg where owner=? and type=?", new Object[]{owner,type});
	}

	@Override
	public long findGGByGGid(long ggid, long owner, int type)
	{
		String sql = "select id from caidan.cd_tf_gg where ggid = ? and owner = ? and type=?";
		Object[] params = {ggid,owner, type};
		List<Long> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("id");
			}
		});
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return 0;
	}

	@Override
	public List<Long> findGGidsByOwner(long owner)
	{
		String sql = "select ggid from caidan.cd_tf_gg where owner = ?";
		Object[] params = {owner};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("ggid");
			}
		});
		
	}

	@Override
	public List<Adgg> findtfGGsByOwner(long owner,int type,int start,int size)
	{
		String sql = "select * from caidan.cd_tf_gg tg join esite.es_ad_gg gg on tg.ggid = gg.id where tg.owner = ? and tg.type=? order by tg.idx desc limit ?,?";
		Object[] params = {owner,type,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Adgg adgg = new Adgg();
				adgg.setId(rs.getLong("tg.id"));
				adgg.setTitle(rs.getString("gg.title"));
				adgg.setHydesc(rs.getString("gg.hydesc"));
				adgg.setContent(rs.getString("gg.content"));
				adgg.setOwner(rs.getLong("gg.owner"));
				adgg.setImg(rs.getString("gg.img"));
				adgg.setUrl(rs.getString("gg.url"));
				adgg.setStarttime(rs.getTimestamp("gg.starttime"));
				adgg.setEndtime(rs.getTimestamp("gg.endtime"));
				return adgg;
			}
			
			
		});
	}
	
	@Override
	public List<Adgg> findtfGGsByOwner(long owner,int type){
		String sql = "select * from caidan.cd_tf_gg tg join esite.es_ad_gg gg on tg.ggid = gg.id where tg.owner = ? and tg.type = ? and (gg.starttime is null or gg.starttime <= now()) and (gg.endtime is null or gg.endtime >= now()) order by tg.idx desc";
		Object[] params = {owner,type};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Adgg adgg = new Adgg();
				adgg.setId(rs.getLong("tg.id"));
				adgg.setTitle(rs.getString("gg.title"));
				adgg.setHydesc(rs.getString("gg.hydesc"));
				adgg.setContent(rs.getString("gg.content"));
				adgg.setOwner(rs.getLong("gg.owner"));
				adgg.setImg(rs.getString("gg.img"));
				adgg.setUrl(rs.getString("gg.url"));
				adgg.setStarttime(rs.getTimestamp("gg.starttime"));
				adgg.setEndtime(rs.getTimestamp("gg.endtime"));
				return adgg;
			}
			
			
		});
	}

	@Override
	public int findTotalTfGGSByOwenr(long owner,int type)
	{
		String sql = "select count(tg.id) from caidan.cd_tf_gg tg join esite.es_ad_gg gg on tg.ggid = gg.id where tg.owner = ? and tg.type=?";
		Object[] params = {owner,type};
		return jdbcTemplate.queryForInt(sql,params);
	}

	@Override
	public TfGG findGGById(long id, long owenr)
	{
		String sql = "select * from caidan.cd_tf_gg where id = ? and owner = ?";
		Object[] params = {id,owenr};
		List<TfGG> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				TfGG gg = new TfGG();
				gg.setId(rs.getLong("id"));
				gg.setIdx(rs.getInt("idx"));
				gg.setGgid(rs.getLong("ggid"));
				gg.setType(rs.getInt("type"));
				return gg;
			}
			
		});
		if(list != null && list.size() >0){
			return list.get(0);
		}
		return null; 
	}

	@Override
	public TfGG findFrontGG(long owner,long idx, int type)
	{
		String sql = "select * from caidan.cd_tf_gg gg where gg.idx>? and gg.owner = ? and gg.type=? order by gg.idx asc limit 1";
		Object[] params = {idx,owner,type};
		List<TfGG> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				TfGG gg = new TfGG();
				gg.setId(rs.getLong("gg.id"));
				gg.setIdx(rs.getInt("gg.idx"));
				gg.setGgid(rs.getLong("gg.ggid"));
				return gg;
			}
			
		});
		if(list != null && list.size() >0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateGGIdx(long id, int idx)
	{
		String sql = "update caidan.cd_tf_gg set idx = ? where id = ?";
		return jdbcTemplate.update(sql, new Object[]{idx,id});
	}

	@Override
	public TfGG findNextGG(long owner,long idx, int type)
	{
		String sql = "select * from caidan.cd_tf_gg gg where gg.idx<? and gg.owner = ? and gg.type=? order by gg.idx desc limit 1";
		Object[] params = {idx,owner,type};
		List<TfGG> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				TfGG gg = new TfGG();
				gg.setId(rs.getLong("gg.id"));
				gg.setIdx(rs.getInt("gg.idx"));
				gg.setGgid(rs.getLong("gg.ggid"));
				return gg;
			}
			
		});
		if(list != null && list.size() >0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public long delTfGGById(long id, long owner)
	{
		String sql = "delete from caidan.cd_tf_gg where id = ? and owner = ?";
		Object[] params = {id,owner};
		return jdbcTemplate.update(sql, params);
	}
	
	@Override
	public long delTfGGByGGId(long ggid, long owner)
	{
		String sql = "delete from caidan.cd_tf_gg where ggid = ? and owner = ?";
		Object[] params = {ggid,owner};
		return jdbcTemplate.update(sql, params);
	}
	
}
