package com.huiyee.interact.renqi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.huiyee.interact.renqi.model.FUidJf;
import com.huiyee.interact.renqi.model.RenQiData;
import com.huiyee.interact.renqi.model.RenQiDetail;

public class RenQiRecordDao implements IRenQiRecordDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addRqDuidDraw(long rqid, long duid, long fuid, int utype, int addjf, String ip, String terminal, String source)
	{
		jdbcTemplate.update("insert ignore into esite.es_interact_rq_duid_draw (duid,utype,rqid,fuid,addjf,createtime,ip,terminal,source) value (?,?,?,?,?,now(),?,?,?)", new Object[]
		{ duid, utype, rqid, fuid, addjf, ip, terminal, source });
	}

	@Override
	public void updateRqFuidJfTotal(long rqid, long fuid, int utype, int addjf)
	{
		jdbcTemplate.update("insert into esite.es_interact_rq_fuid_jf (utype,rqid,fuid,totalnum,createtime) value (?,?,?,?,now()) on duplicate key update totalnum=totalnum+?", new Object[]
		{ utype, rqid, fuid, addjf, addjf });
	}

	@Override
	public void updateRqFuidJfUsed(long rqid, long fuid, int utype, int addjf)
	{
		jdbcTemplate.update("insert into esite.es_interact_rq_fuid_jf (utype,rqid,fuid,usednum,lunum,createtime) value (?,?,?,?,1,now()) on duplicate key update usednum=usednum+?,lunum=lunum+1", new Object[]
		{ utype, rqid, fuid, addjf, addjf });
	}

	@Override
	public FUidJf findFuidJf(long rqid, long fuid, int utype)
	{
		List<FUidJf> ls = jdbcTemplate.query("select * from esite.es_interact_rq_fuid_jf where rqid=? and fuid=? and utype=?", new Object[]
		{ rqid, fuid, utype }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				FUidJf aj = new FUidJf();
				aj.setFuid(rs.getLong("fuid"));
				aj.setId(rs.getLong("id"));
				aj.setLunum(rs.getInt("lunum"));
				aj.setRqid(rs.getLong("rqid"));
				aj.setTotalnum(rs.getInt("totalnum"));
				aj.setUsednum(rs.getInt("usednum"));
				aj.setUtype(rs.getInt("utype"));
				return aj;
			}

		});
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}
		return null;
	}

	@Override
	public void addLotteryUserTotal(long lid, long fuid, int utype, int addjf)
	{
		jdbcTemplate.update("insert into esite.es_interact_lottery_user (wbuid,lid,type,totalnum) value (?,?,?,?) on duplicate key update totalnum=totalnum+?", new Object[]
		{ fuid, lid, utype, addjf, addjf });
	}

	@Override
	public int findDuidDraw(long rqid, long duid, long fuid, int utype)
	{
		return jdbcTemplate.queryForInt("select count(id) from esite.es_interact_rq_duid_draw where rqid=? and duid=? and fuid=? and utype=?", new Object[]
		{ rqid, duid, fuid, utype });
	}

	@Override
	public void addRqFuidJf(long rqid, long fuid, int utype)
	{
		jdbcTemplate.update("insert ignore into esite.es_interact_rq_fuid_jf (utype,rqid,fuid,createtime) value (?,?,?,now()) ", new Object[]
		{ utype, rqid, fuid });
	}

	@Override
	public void addFuidShare(long rqid, long fuid, int utype, String content, String ip, String terminal, String source)
	{
		jdbcTemplate.update("insert into esite.es_interact_rq_fuid_share (utype,rqid,fuid,content,createtime,ip,terminal,source) value (?,?,?,?,now(),?,?,?)", new Object[]
		{ utype, rqid, fuid, content, ip, terminal, source });
	}

	@Override
	public int findJoinTotal(long rqid, int utype)
	{
		return jdbcTemplate.queryForInt("select count(*) from es_interact_rq_fuid_jf where rqid=? and utype=?", new Object[]
		{ rqid, utype });
	}

	@Override
	public List<RenQiData> findRecordList(long rqid, int utype, int start, int size)
	{
		List<RenQiData> list = jdbcTemplate
				.query(
						"select *,sum(addjf) sumjf  from es_interact_rq_fuid_jf share join es_interact_rq rq on share.rqid=rq.id join es_interact_lottery lottery on rq.lotteryid=lottery.id left outer join es_interact_rq_duid_draw draw on draw.fuid=share.fuid where share.rqid=? and share.utype=? group by share.fuid  limit ?,?",
						new Object[]
						{ rqid, utype, start, size }, new RowMapper()
						{
							@Override
							public Object mapRow(ResultSet rs, int arg1) throws SQLException
							{
								RenQiData data = new RenQiData();
								data.setFuid(rs.getLong("share.fuid"));
								data.setUtype(rs.getInt("utype"));
								data.setAddjf(rs.getInt("sumjf"));
								data.setCnum(rs.getInt("rq.cnum"));
								data.setLid(rs.getLong("rq.lotteryid"));
								data.setRqid(rs.getLong("share.rqid"));
								data.setCreatetime(rs.getTimestamp("share.createtime"));
								return data;
							}
						});
		return list;
	}

	@Override
	public int findFuidTotal(long fuid, long rqid)
	{
		return jdbcTemplate.queryForInt("select count(*) from es_interact_rq_duid_draw where fuid=? and rqid=?", new Object[]
		{ fuid, rqid });
	}

	@Override
	public List<RenQiDetail> findFuidDetail(long fuid, long rqid, int start, int size)
	{
		List<RenQiDetail> list = jdbcTemplate.query("select * from es_interact_rq_duid_draw where fuid=? and rqid=? order by id desc limit ?,?", new Object[]
		{ fuid, rqid, start, size }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				RenQiDetail rq = new RenQiDetail();
				rq.setWbuid(rs.getLong("duid"));
				rq.setUtype(rs.getInt("utype"));
				rq.setCreatetime(rs.getTimestamp("createtime"));
				rq.setAddjf(rs.getInt("addjf"));
				rq.setIp(rs.getString("ip"));
				return rq;
			}
		});
		return list;
	}

	@Override
	public String findFristIp(long rqid, long fuid)
	{
		List<String> list = jdbcTemplate.query("select ip from es_interact_rq_fuid_share where rqid=? and fuid=? order by id asc limit 1", new Object[]
		{ rqid, fuid }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getString("ip");
			}
		});
		if(list.size()>0)
			return list.get(0);
		return null;
	}
}
