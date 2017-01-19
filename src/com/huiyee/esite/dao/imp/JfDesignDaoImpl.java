package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.IJfDesignDao;
import com.huiyee.esite.model.BalanceSet;
import com.huiyee.interact.checkin.model.Checkin;


public class JfDesignDaoImpl implements IJfDesignDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int savezfjfDesign(long owner,BalanceSet balanceSet)
	{
		String sql = "insert into es_hy_user_balance_set(owner,sharenum,ssharenum,sgznum,sclicknum) values(?,?,?,?,?) on duplicate key update sharenum=?,ssharenum=?,sgznum=?,sclicknum=?";
		Object[] params = {owner,balanceSet.getSharenum(),balanceSet.getSsharenum(),balanceSet.getSgznum(),balanceSet.getSclicknum(),balanceSet.getSharenum(),balanceSet.getSsharenum(),balanceSet.getSgznum(),balanceSet.getSclicknum()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int savesqjfDesign(long owner, BalanceSet balanceSet)
	{
		String sql = "insert into es_hy_user_balance_set(owner,topicnum,comnum,topnum,upnum,downum) values(?,?,?,?,?,?) on duplicate key update topicnum=?,comnum=?,topnum=?,upnum=?,downum=?";
		Object[] params = {owner,balanceSet.getTopicnum(),balanceSet.getComnum(),balanceSet.getTopnum(),balanceSet.getUpnum(),balanceSet.getDownum(),balanceSet.getTopicnum(),balanceSet.getComnum(),balanceSet.getTopnum(),balanceSet.getUpnum(),balanceSet.getDownum()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int savezsjfDesign(long owner, BalanceSet balanceSet)
	{
		String sql = "insert into es_hy_user_balance_set(owner,newnum) values(?,?) on duplicate key update newnum = ?";
		Object[] params = {owner,balanceSet.getNewnum(),balanceSet.getNewnum()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int saveqdjfDesign(long owner, Checkin checkin)
	{
		String sql = "insert into es_interact_checkin(owner,addnum,addaddnum,maxday) values(?,?,?,?) on duplicate key update addnum=?,addaddnum=?,maxday=? ";
		Object[] params ={owner,checkin.getAddnum(),checkin.getAddaddnum(),checkin.getMaxday(),checkin.getAddnum(),checkin.getAddaddnum(),checkin.getMaxday()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public BalanceSet findBalanceSetByOwner(long owner)
	{
		String sql = "select * from es_hy_user_balance_set where owner = ?";
		Object[] params = {owner};
		List<BalanceSet> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BalanceSet bs = new BalanceSet();
				bs.setId(rs.getInt("id"));
				bs.setNewnum(rs.getInt("newnum"));
				bs.setDownum(rs.getInt("downum"));
				bs.setComnum(rs.getInt("comnum"));
				bs.setSclicknum(rs.getInt("sclicknum"));
				bs.setSgznum(rs.getInt("sgznum"));
				bs.setSharenum(rs.getInt("sharenum"));
				bs.setSsharenum(rs.getInt("ssharenum"));
				bs.setTopicnum(rs.getInt("topicnum"));
				bs.setTopnum(rs.getInt("topnum"));
				bs.setUpnum(rs.getInt("upnum"));
				bs.setOcnum(rs.getInt("ocnum"));
				bs.setYynum(rs.getInt("yynum"));
				bs.setYypjnum(rs.getInt("yypjnum"));
				bs.setHyname(rs.getString("hyname"));
				bs.setHykimg(rs.getString("hykimg"));
				bs.setRmbrule(rs.getString("rmbrule"));
				bs.setPageset(rs.getString("pageset"));
				return bs;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int savexqjfDesign(long owner, BalanceSet balanceSet)
	{
		String sql = "insert into es_hy_user_balance_set(owner,ocnum) values(?,?) on duplicate key update ocnum = ?";
		Object[] params = {owner,balanceSet.getOcnum(),balanceSet.getOcnum()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int saveyyjfDesign(long owner, BalanceSet balanceSet)
	{
		String sql = "insert into es_hy_user_balance_set(owner,yynum) values(?,?) on duplicate key update yynum = ?";
		Object[] params = {owner,balanceSet.getYynum(),balanceSet.getYynum()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int savepjjfDesign(long owner, BalanceSet balanceSet) {
		String sql = "insert into es_hy_user_balance_set(owner,yypjnum) values(?,?) on duplicate key update yypjnum = ?";
		Object[] params = {owner,balanceSet.getYypjnum(),balanceSet.getYypjnum()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int saveczDesign(long owner, BalanceSet balanceSet) {
		String sql = "insert into es_hy_user_balance_set(owner,hykimg,rmbrule) values(?,?,?) on duplicate key update hykimg = ?,rmbrule=?";
		Object[] params = {owner,balanceSet.getHykimg(),balanceSet.getRmbrule(),balanceSet.getHykimg(),balanceSet.getRmbrule()};
		return jdbcTemplate.update(sql, params);
	}
	
	@Override
	public int updatePageSet(String json, long owner)
	{
		return jdbcTemplate.update("update es_hy_user_balance_set set pageset=? where owner=?", new Object[]{json,owner});
	}
	
	
}
