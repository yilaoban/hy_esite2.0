package com.huiyee.interact.cb.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.interact.cb.dao.IHbRecordDao;
import com.huiyee.interact.cb.model.CbHbRecord;
import com.huiyee.interact.cb.model.CbSender;


public class HbRecordDaoImpl implements IHbRecordDao
{
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<CbHbRecord> findHbRecordList(long sender, int start, int size)
	{
		String sql = "select r.* from es_interact_cb_hb_record r where r.sender = ? limit ?,?";
		Object[] params = {sender,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CbHbRecord r = new CbHbRecord();
				r.setId(rs.getLong("r.id"));
				int num = rs.getInt("r.num");
				double money = div(num,100);
				r.setMoney(money);
				r.setNum(rs.getInt("r.num"));
				r.setCreatetime(rs.getTimestamp("r.createtime"));
				return r;
			}
			
		});
	}
	
	 public static double div(double v1,double v2){   
	        return div(v1,v2,2);   
	 }  
	
	 public static double div(double v1,double v2,int scale){   
        if(scale<0){   
            throw new IllegalArgumentException(   
            "The scale must be a positive integer or zero");   
        }   
        BigDecimal b1 = new BigDecimal(Double.toString(v1));   
        BigDecimal b2 = new BigDecimal(Double.toString(v2));   
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();   
	 }
	 
	@Override
	public int findTotalHbRecordList(long sender)
	{
		String sql = "select count(r.id) from es_interact_cb_hb_record r where r.sender = ?";
		Object[] params = {sender};
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public CbSender findHbSender(long sender)
	{
		String sql = "select * from es_interact_cb_sender where id = ?";
		Object[] params = {sender};
		List<CbSender> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CbSender s = new CbSender();
				s.setId(rs.getLong("id"));
				s.setHbtotal(rs.getInt("hbtotal"));
				s.setHbused(rs.getInt("hbused"));
				return s;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public int findTotalByCbid(long owner, long sender, String name, String starttime, String endtime)
	{
		List<Object> list=new ArrayList<Object>();
		String sql="select count(*) from crm.crm_wx_hongbao_cb_send r join es_hy_user u on r.hyuid=u.id where u.owner=?";
		list.add(owner);
		if(sender!=0){
			sql+=" and r.hyuid=? ";
			list.add(sender);
		}else if(StringUtils.isNotEmpty(name)){
			sql+=" and u.nickname like ? ";
			list.add("%"+name+"%");
		}
		if(starttime!=null&&starttime.trim().length()>0){
			sql+=" and r.createtime>? ";
			list.add(starttime);
		}
		if(endtime!=null&&endtime.trim().length()>0){
			sql+=" and r.createtime<? ";
			list.add(endtime);
		}
		return jdbcTemplate.queryForInt(sql, list.toArray());
	}
	
	
	@Override
	public List<CbHbRecord> findHbRecord(long cbid, long sender, String name, String starttime, String endtime, int start, int size)
	{
		List<Object> list=new ArrayList<Object>();
		String sql="select * from crm.crm_wx_hongbao_cb_send r join es_hy_user u on r.hyuid=u.id where u.owner=?";
		list.add(cbid);
		if(sender!=0){
			sql+=" and r.hyuid=? ";
			list.add(sender);
		}else if(StringUtils.isNotEmpty(name)){
			sql+=" and u.nickname like ? ";
			list.add("%"+name+"%");
		}
		if(starttime!=null&&starttime.trim().length()>0){
			sql+=" and r.createtime>? ";
			list.add(starttime);
		}
		if(endtime!=null&&endtime.trim().length()>0){
			sql+=" and r.createtime<? ";
			list.add(endtime);
		}
		sql+=" order by r.createtime desc limit ?,?";
		list.add(start);
		list.add(size);
		return jdbcTemplate.query(sql, list.toArray(), new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CbHbRecord r = new CbHbRecord();
				r.setId(rs.getLong("r.id"));
				r.setNum(rs.getInt("r.rmb"));
				r.setCreatetime(rs.getTimestamp("r.createtime"));
				r.setStatus(rs.getInt("r.status"));
				r.setSenderName(rs.getString("u.nickname"));
				r.setHyuid(rs.getLong("r.hyuid"));
				return r;
			}
		});
	}


	@Override
	public int updateCbSendCheck(long id, int status) {
		String sql = "update crm.crm_wx_hongbao_cb_send set status = ? where id = ?";
		Object[] params = {status,id};
		return jdbcTemplate.update(sql, params);
	}
}
