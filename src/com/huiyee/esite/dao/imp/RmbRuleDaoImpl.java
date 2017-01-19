package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.IRmbRuleDao;
import com.huiyee.esite.model.BalanceRule;
import com.huiyee.esite.util.Arith;

public class RmbRuleDaoImpl implements IRmbRuleDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<BalanceRule> findBalanceRuleListByOwner(long owner) {
		String sql = "select * from es_hy_user_balance_rule where owner = ?";
		Object[] params = {owner};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BalanceRule r = new BalanceRule();
				r.setId(rs.getLong("id"));
				r.setOwner(rs.getLong("owner"));
				r.setRmb(rs.getInt("rmb"));
				r.setGetrmb(rs.getInt("getrmb"));
				r.setGetjf(rs.getInt("getjf"));
				int total = rs.getInt("rmb") + rs.getInt("getrmb");
				if(total > 0){
					double bilu =  Arith.div(rs.getInt("rmb"),total,2);
					r.setBilu( Arith.mul(bilu,10));
				}
				return r;
			}
			
		});
	}
	
	@Override
	public int delRmbRule(long ruleid,long owner) {
		String sql = "delete from es_hy_user_balance_rule where id = ? and owner = ?";
		Object[] params = {ruleid,owner};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int saveRmbRule(BalanceRule rule, long owner) {
		String sql = "insert into es_hy_user_balance_rule(owner,rmb,getrmb,getjf) values(?,?,?,?)";
		Object[] params = {owner,rule.getRmb(),rule.getGetrmb(),rule.getGetjf()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public BalanceRule findRuleById(long ruleid,long owner) {
		String sql = "select * from es_hy_user_balance_rule where id = ? and owner = ?";
		Object[] params = {ruleid,owner};
		List<BalanceRule> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BalanceRule r = new BalanceRule();
				r.setId(rs.getLong("id"));
				r.setOwner(rs.getLong("owner"));
				r.setRmb(rs.getInt("rmb"));
				r.setGetrmb(rs.getInt("getrmb"));
				r.setGetjf(rs.getInt("getjf"));
				return r;
			}
		});
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateRmbRule(BalanceRule rule,long owner) {
		String sql = "update es_hy_user_balance_rule set rmb = ?,getrmb=?,getjf=? where id = ? and owner = ?";
		Object[] params = {rule.getRmb(),rule.getGetrmb(),rule.getGetjf(),rule.getId(),owner};
		return jdbcTemplate.update(sql, params);
	}
	
}
