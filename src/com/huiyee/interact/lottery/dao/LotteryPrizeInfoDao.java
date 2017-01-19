package com.huiyee.interact.lottery.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.service.IMemCacheAble;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.interact.lottery.model.LotteryPrize;
import com.huiyee.interact.lottery.model.LotteryPrizeCode;

public class LotteryPrizeInfoDao implements IlotteryPrizeInfoDao,IMemCacheAble {
	private int cacheTime;
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<LotteryPrizeCode> findPrizeWinnnerInformation(long lid, long wbuid,int type,long pageid){
		String sql = "select lp.name,lp.jf,lp.link,lpc.code,lpc.password,r.status rstatus,r.createtime,r.nickname from es_interact_lottery_user_record r join es_interact_lottery_prize lp on r.lpid = lp.id left join es_interact_lottery_prize_code lpc on r.lpcid = lpc.id where r.lid = ? and r.wbuid = ? and r.type = ? and r.pageid = ? and r.status > 0 order by r.createtime desc";
		return jdbcTemplate.query(sql, new Object[]{lid,wbuid,type,pageid}, new LotteryPrizeInformationRowMapper());
	}
	class LotteryPrizeInformationRowMapper implements RowMapper
	{
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			LotteryPrizeCode lpc = new LotteryPrizeCode();
			lpc.setName(rs.getString("name"));
			lpc.setCode(rs.getString("code"));
			lpc.setPassword(rs.getString("password"));
			lpc.setStatus(rs.getString("rstatus"));
			lpc.setJf(rs.getInt("jf"));
			Date d = rs.getTimestamp("r.createtime");
			String timeStr = DateUtil.getDateTimeString(d);
			lpc.setTimeStr(timeStr);
			lpc.setNickname(rs.getString("r.nickname"));
			lpc.setLink(rs.getString("lp.link"));
			return lpc;
		}
	}
	
	public int getCacheTime()
	{
		return cacheTime;
	}
	public void setCacheTime(int cacheTime) {
		this.cacheTime = cacheTime;
	}
	
	@Override
	public LotteryPrize findPrizeNameById(long lpid)
	{
		List<LotteryPrize> list=jdbcTemplate.query("select * from es_interact_lottery_prize where id=?", new Object[]{lpid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				LotteryPrize lp=new LotteryPrize();
				lp.setPrice(rs.getInt("price"));
				lp.setName(rs.getString("name"));
				return lp;
			}
		});
		return list.size()>0?list.get(0):null;
	}

	@Override
	public List<LotteryPrizeCode> findPrizeWinnnerInformation(long lid, int type, long pageid, int start, int size) {
		String sql = "select lp.name,lp.jf,lp.link, lpc.code,lpc.password,r.status rstatus,r.createtime,r.nickname from es_interact_lottery_user_record r join es_interact_lottery_prize lp on r.lpid = lp.id left join es_interact_lottery_prize_code lpc on r.lpcid = lpc.id where r.lid = ? and r.type = ? and r.pageid = ? and r.status > 0 order by r.createtime desc limit ?,?";
		return jdbcTemplate.query(sql, new Object[]{lid,type,pageid,start,size}, new LotteryPrizeInformationRowMapper());
	}
}
