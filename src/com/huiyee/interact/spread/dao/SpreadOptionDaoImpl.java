package com.huiyee.interact.spread.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.spread.model.SpreadModel;
import com.huiyee.interact.spread.model.SpreadOption;

public class SpreadOptionDaoImpl implements ISpreadOptionDao
{
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate(){
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<SpreadOption> findSpreadOption(long spreadid,int start,int size)
	{
		String sql="select id, spreadid, wbid,title, content, pic, createtime from es_interact_spread_option where spreadid=? and status!='DEL' limit ?,?";
		return getJdbcTemplate().query(sql, new Object[]{spreadid,start,size},new SpreadOptionMapper());
	}
	class SpreadOptionMapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			SpreadOption model=new SpreadOption();
			model.setId(rs.getLong("id"));
			model.setSpreadid(rs.getLong("spreadid"));
			model.setWbid(rs.getString("wbid"));
			model.setContent(rs.getString("content"));
			model.setTitle(rs.getString("title"));
			model.setPic(rs.getString("pic"));
			model.setCreatetime(rs.getTimestamp("createtime"));
			return model;
		}
	}
	
	@Override
	public int findSpreadOptionTotal(long spreadid)
	{
		String sql="select count(*) from es_interact_spread_option where spreadid=?";
		return getJdbcTemplate().queryForInt(sql,new Object[]{spreadid});
	}

	@Override
	public int deleteSpreadOption(long id)
	{
		String sql="update es_interact_spread_option set status='DEL' where id=?";
		return getJdbcTemplate().update(sql,new Object[]{id});
	}

	@Override
	public int saveSpreadOption(long spreadid,String wbid,String title,String content,String pic)
	{
		String sql="insert into es_interact_spread_option (spreadid,wbid,title,content,pic,createtime) values(?,?,?,?,?,now())";
		return getJdbcTemplate().update(sql,new Object[]{spreadid,wbid,title,content,pic});
	}

	@Override
	public int updateSpreadOption(String title,String content,String pic, long id)
	{
		String sql="update es_interact_spread_option set title=?,content=?,pic=? where id=?";
		return getJdbcTemplate().update(sql,new Object[]{title,content,pic,id});
	}

	@Override
	public SpreadModel fingSpreadType(long id)
	{
		String sql="select type from es_interact_spread where id=?";
		List<SpreadModel> list= getJdbcTemplate().query(sql, new Object[]{id},new SpreadTypeMapper());
		if(list.size()>0){
			SpreadModel sm=list.get(0);
			return sm;
		}
		return null;
	}
	class SpreadTypeMapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			SpreadModel model=new SpreadModel();
			model.setType(rs.getString("type"));
			return model;
		}
	}

	@Override
	public SpreadOption findOneSpreadOption(long id)
	{
		String sql="select id,spreadid,wbid,title,content,pic from es_interact_spread_option where id=?";
		List<SpreadOption> list= getJdbcTemplate().query(sql, new Object[]{id},new SpreadOptionMapper1());
		if(list.size()>0){
			SpreadOption sp=list.get(0);
			return sp;
		}
		return null;
	}
	class SpreadOptionMapper1 implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			SpreadOption model=new SpreadOption();
			model.setId(rs.getLong("id"));
			model.setSpreadid(rs.getLong("spreadid"));
			model.setWbid(rs.getString("wbid"));
			model.setTitle(rs.getString("title"));
			model.setContent(rs.getString("content"));
			model.setPic(rs.getString("pic"));
			return model;
		}
	}

	@Override
	public int updateSpreadWbid(String wbid,String url,long id)
	{
		String sql="update es_interact_spread_option set wbid=?,wburl=? where id=?";
		return getJdbcTemplate().update(sql,new Object[]{wbid,url,id});
	}

	@Override
	public SpreadOption findOneSpreadWbid(long id)
	{
		String sql="select id, wbid,wburl from es_interact_spread_option where spreadid=?";
		List<SpreadOption> list= getJdbcTemplate().query(sql, new Object[]{id},new SpreadOptionMapper2());
		if(list.size()>0){
			SpreadOption sp=list.get(0);
			return sp;
		}
		return null;
	}
	class SpreadOptionMapper2 implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			SpreadOption model=new SpreadOption();
			model.setId(rs.getLong("id"));
			model.setWbid(rs.getString("wbid"));
			model.setWburl(rs.getString("wburl"));
			return model;
		}
	}

	@Override
	public int saveSpreadWbid(String wbid,String url,long spreadid)
	{
		String sql="insert into es_interact_spread_option (spreadid,wburl,wbid) values(?,?,?) ";
		return getJdbcTemplate().update(sql,new Object[]{spreadid,url,wbid});
	}


}
