package com.huiyee.interact.lottery.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.lottery.model.LotteryPrizeCode;

public class LotteryPrizeCodeDao implements ILotteryPrizeCodeDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public LotteryPrizeCode findOneCode(long lpid,int rm)
	{
		List<LotteryPrizeCode> ls = jdbcTemplate.query("select * from es_interact_lottery_prize_code where lpid=? and used='N' limit ?,1", new Object[]
		{ lpid,rm }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				LotteryPrizeCode lpc = new LotteryPrizeCode();
				lpc.setCode(rs.getString("code"));
				lpc.setId(rs.getLong("id"));
				lpc.setPassword(rs.getString("password"));
				return lpc;
			}

		});
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}
		return null;
	}

	@Override
	public void updateOneCode(long lpcid)
	{
		jdbcTemplate.update("update es_interact_lottery_prize_code set used='Y' where id=?", new Object[]
		{ lpcid });
	}

	/**
	 * ÃÌº””≈ª›»Ø∫≈
	 * 
	 * @param list
	 * @return
	 */
	private final static String ADD_COUPONSCODE = "insert into es_interact_lottery_prize_code(lpid,code,password) values(?,?,?)";

	public int addCouponsCode(final List<LotteryPrizeCode> list)
	{
		String sql = ADD_COUPONSCODE;
		int[] rs = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter()
		{
			public int getBatchSize()
			{
				return list.size();
			}

			public void setValues(PreparedStatement ps, int index) throws SQLException
			{
				LotteryPrizeCode c = list.get(index);
				int i = 1;
				ps.setLong(i++, c.getLpid());
				ps.setString(i++, c.getCode());
				ps.setString(i++, c.getPassword());
			}
		});
		if (rs.length > 0)
		{
			return rs.length;
		}
		return 0;
	}

	@Override
	public List<LotteryPrizeCode> findLotteryCodeListByLpid(long lpid, int start, int size)
	{
		String sql = "select * from es_interact_lottery_prize_code c join es_interact_lottery_prize lp on c.lpid= lp.id where lp.id = ? limit ?,?";

		return jdbcTemplate.query(sql, new Object[]
		{ lpid, start, size }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				LotteryPrizeCode lpc = new LotteryPrizeCode();
				lpc.setId(rs.getLong("id"));
				lpc.setCode(rs.getString("code"));
				lpc.setPassword(rs.getString("password"));
				lpc.setUsed(rs.getString("used"));
				return lpc;
			}
		});
	}

	@Override
	public int findLotteryPrizeCodeTotal(long lpid)
	{
		String sql = "select count(c.id) from es_interact_lottery_prize_code c join es_interact_lottery_prize lp on c.lpid= lp.id where lp.id = ? ";
		return jdbcTemplate.queryForInt(sql, new Object[]
		{ lpid });
	}

	@Override
	public int findCodeTotal(long id)
	{
		String sql = "select count(*) from es_interact_lottery_prize_code where lpid = ? ";
		return jdbcTemplate.queryForInt(sql, new Object[]
		{ id });
	}
	
	@Override
	public LotteryPrizeCode findLotteryCodeByLpcid(long lpcid)
	{
		List<LotteryPrizeCode> list=jdbcTemplate.query("select * from es_interact_lottery_prize_code where id=?", new Object[]{lpcid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				LotteryPrizeCode lpc = new LotteryPrizeCode();
				lpc.setId(rs.getLong("id"));
				lpc.setCode(rs.getString("code"));
				lpc.setPassword(rs.getString("password"));
				lpc.setUsed(rs.getString("used"));
				return lpc;
			}
		});
		if(list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public List<LotteryPrizeCode> findLotteryCodeByLpidAndCode(long lpid, String code)
	{
		String sql = "select * from es_interact_lottery_prize_code c join es_interact_lottery_prize lp on c.lpid= lp.id where lp.id = ? and c.code = ?";
		return jdbcTemplate.query(sql, new Object[]{ lpid,code }, new RowMapper()
  		{
  			@Override
  			public Object mapRow(ResultSet rs, int arg1) throws SQLException
  			{
  				LotteryPrizeCode lpc = new LotteryPrizeCode();
  				lpc.setId(rs.getLong("id"));
  				lpc.setCode(rs.getString("code"));
  				lpc.setPassword(rs.getString("password"));
  				lpc.setUsed(rs.getString("used"));
  				return lpc;
  			}
  		});
	}

}
