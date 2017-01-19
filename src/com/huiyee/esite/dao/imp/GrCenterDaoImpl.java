package com.huiyee.esite.dao.imp;

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

import com.huiyee.esite.dao.IGrCenterDao;
import com.huiyee.esite.model.BalancePay;

public class GrCenterDaoImpl implements IGrCenterDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findTotalUnusedKQ(long hyuid, String status) {
		String sql = "select count(o.id) from es_pay_order o join es_pay_order_goods g on g.orderid = o.id where o.hyuid = ? and o.status =? and o.del_tag != 'Y' and o.fatherorderid > 0 and g.used = 'N'";
		Object[] params = {hyuid,status};
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public long saveBalancePay(final String ip,final int price,final long hyuid,final long owner,final long ruleid) {
		final String sql = "insert into es_hy_user_balance_pay(ip,price,hyuid,owner,createtime,ruleid) values(?,?,?,?,now(),?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i=1;
				ps.setString(i++, ip);
				ps.setInt(i++, price);
				ps.setLong(i++, hyuid);
				ps.setLong(i++, owner);
				ps.setLong(i++, ruleid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public int updateBalancePayById(long id, String mediaorder,long hyuid,long rid,int price) {
		String sql = "update es_hy_user_balance_pay set mediaorder = ?,status = 1,rid = ?,price=? where id = ? and hyuid = ?";
		Object[] params = {mediaorder,rid,price,id,hyuid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public BalancePay findBalancePayById(long payid) {
		String sql = "select status,hyuid,owner from es_hy_user_balance_pay where id = ?";
		Object[] params = {payid};
		List<BalancePay> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BalancePay pay = new BalancePay();
				pay.setStatus(rs.getInt("status"));
				pay.setHyuid(rs.getLong("hyuid"));
				pay.setOwner(rs.getLong("owner"));
				return pay;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	
}
