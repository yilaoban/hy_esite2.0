package com.huiyee.interact.lottery.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.model.OwnerContentType;
import com.huiyee.interact.lottery.model.LotteryLose;
import com.huiyee.interact.lottery.model.LotteryPrize;

public class LotteryPrizeDao implements ILotteryPrizeDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<LotteryPrize> findLotteryNPrize(long lid)
	{
		return jdbcTemplate.query("select * from es_interact_lottery_prize where lid=? and hydefault='N' and total>used and status='E'", new Object[]
		{ lid }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				LotteryPrize lp = new LotteryPrize();
				lp.setId(rs.getLong("id"));
				lp.setDetail(rs.getString("detail"));
				lp.setHydefault(rs.getString("hydefault"));
				lp.setImg(rs.getString("img"));
				lp.setLid(rs.getLong("lid"));
				lp.setLocation(rs.getString("location"));
				lp.setName(rs.getString("name"));
				lp.setPrice(rs.getInt("price"));
				lp.setStatus(rs.getString("status"));
				lp.setTotal(rs.getInt("total"));
				lp.setType(rs.getString("type"));
				lp.setUsed(rs.getInt("used"));
				lp.setJf(rs.getInt("jf"));
				lp.setHprice(rs.getInt("hprice"));
				lp.setHbgz(rs.getInt("hbgz"));
				lp.setHbkey(rs.getString("hbkey"));
				lp.setProductid(rs.getLong("productid"));
				lp.setLink(rs.getString("link"));
				lp.setPositionid(rs.getInt("positionid"));
				return lp;
			}

		});

	}
	
	@Override
	public List<LotteryPrize> findLotteryYPrize(long lid)
	{
		return jdbcTemplate.query("select * from es_interact_lottery_prize where lid=? and hydefault='Y' and total>used  and status='E'", new Object[]
		{ lid}, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				LotteryPrize lp = new LotteryPrize();
				lp.setId(rs.getLong("id"));
				lp.setDetail(rs.getString("detail"));
				lp.setHydefault(rs.getString("hydefault"));
				lp.setImg(rs.getString("img"));
				lp.setLid(rs.getLong("lid"));
				lp.setLocation(rs.getString("location"));
				lp.setName(rs.getString("name"));
				lp.setPrice(rs.getInt("price"));
				lp.setStatus(rs.getString("status"));
				lp.setTotal(rs.getInt("total"));
				lp.setType(rs.getString("type"));
				lp.setUsed(rs.getInt("used"));
				lp.setJf(rs.getInt("jf"));
				lp.setHprice(rs.getInt("hprice"));
				lp.setHbgz(rs.getInt("hbgz"));
				lp.setHbkey(rs.getString("hbkey"));
				lp.setProductid(rs.getLong("productid"));
				lp.setLink(rs.getString("link"));
				lp.setPositionid(rs.getInt("positionid"));
				return lp;
			}

		});

	}

	@Override
	public void updateUsed(long lpid)
	{
		jdbcTemplate.update("update es_interact_lottery_prize set used=used+1 where id=? and total>used", new Object[]
		{ lpid });
	}

	@Override
	public List<LotteryPrize> findPrizeList(int start, int size, long lid)
	{
		List<LotteryPrize> list = new ArrayList<LotteryPrize>();
		list = jdbcTemplate.query("select * from es_interact_lottery_prize pr where lid=? and status!='D' order by id desc limit ?,?", new Object[]
		{ lid, start, size }, new MyRowMapper());
		return list;
	}

	class MyRowMapper implements RowMapper
	{
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			LotteryPrize pr = new LotteryPrize();
			pr.setId(rs.getLong("id"));
			pr.setLid(rs.getLong("lid"));
			pr.setName(rs.getString("name"));
			pr.setImg(rs.getString("img"));
			pr.setTotal(rs.getInt("total"));
			pr.setUsed(rs.getInt("used"));
			pr.setPrice(rs.getInt("price"));
			pr.setStatus(rs.getString("status"));
			pr.setType(rs.getString("type"));
			pr.setHydefault(rs.getString("hydefault"));
			pr.setLocation(rs.getString("location"));
			pr.setJf(rs.getInt("jf"));
			pr.setHprice(rs.getInt("hprice"));
			pr.setWishing(rs.getString("wishing"));
			pr.setAct_name(rs.getString("act_name"));
			pr.setRemark(rs.getString("remark"));
			pr.setHbgz(rs.getInt("hbgz"));
			pr.setHbkey(rs.getString("hbkey"));
			pr.setProductid(rs.getLong("productid"));
			pr.setLink(rs.getString("link"));
			pr.setPositionid(rs.getInt("positionid"));
			return pr;
		}
	}

	@Override
	public int findLotteryprizeTotal(long lid)
	{
		return jdbcTemplate.queryForInt("select count(id) from es_interact_lottery_prize where lid=? and status!='D'", new Object[]
		{ lid });
	}

	@Override
	public long saveprice(LotteryPrize lp)
	{
		String sql = "insert into es_interact_lottery_prize (lid,name,total,price,img,type,hydefault,jf,hprice,wishing,act_name,remark,hbgz,hbkey,productid,link,positionid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[]
		{ lp.getLid(), lp.getName(), lp.getTotal(), lp.getPrice(), lp.getImg(), lp.getType(), lp.getHydefault(),lp.getJf(),lp.getHprice(),lp.getWishing(),lp.getAct_name(),lp.getRemark(),lp.getHbgz(),lp.getHbkey(),lp.getProductid(),lp.getLink(),lp.getPositionid()});
	}

	@Override
	public long delprice(long id)
	{
		String sql = "update  es_interact_lottery_prize set status='D' where id =?";
		return jdbcTemplate.update(sql, new Object[]
		{ id });
	}

	@Override
	public long updateprice(LotteryPrize lp)
	{
		String sql = "update es_interact_lottery_prize set name=?,total=?,price=?,img=?,type=?,hydefault=?,jf=?,hprice=?,wishing=?,act_name=?,remark=?,hbgz=?,hbkey=?,productid=?,link=? where id =? ";
		return jdbcTemplate.update(sql, new Object[]
		{ lp.getName(), lp.getTotal(), lp.getPrice(), lp.getImg(), lp.getType(), lp.getHydefault(),lp.getJf(),lp.getHprice(),lp.getWishing(),lp.getAct_name(),lp.getRemark(),lp.getHbgz(),lp.getHbkey(),lp.getProductid(),lp.getLink(), lp.getId() });
	}

	@Override
	public LotteryPrize findPrizeByLpid(long id)
	{
		List<LotteryPrize> list = jdbcTemplate.query("select * from es_interact_lottery_prize where id=?", new Object[]
		{ id }, new MyRowMapper());
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public void updateTotal(int count, long id)
	{
		String sql = "update es_interact_lottery_prize set total=? where id =? and type='C'";
		jdbcTemplate.update(sql, new Object[]
		{ count, id });
	}
	
	@Override
	public List<LotteryPrize> findLotteryTotalPrizeById(long lid)
	{
		String sql = "select * from es_interact_lottery_prize where lid = ? and status != 'D' and (type = 'M' or type = 'H')";
		Object[] params = {lid};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				LotteryPrize lp = new LotteryPrize();
				lp.setTotal(rs.getInt("total"));
				lp.setHprice(rs.getInt("hprice"));
				return lp;
			}
			
		});
	}
	
	
	@Override
	public List<LotteryPrize> findPrizeOrdByPrice(long lid)
	{
		return jdbcTemplate.query("select * from es_interact_lottery_prize where lid=? ", new  Object[]{lid}, new MyRowMapper());
	}

	@Override
	public LotteryLose findLose(long uid, int type, long lid)
	{
			List<LotteryLose> ls=jdbcTemplate.query("select id,num from es_interact_lottery_lose where uid =? and type=? and lid=?",new Object[]{ uid, type,lid },new RowMapper(){

				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException
				{
					LotteryLose ll=new LotteryLose();
					ll.setId(rs.getLong("id"));
					ll.setNum(rs.getInt("num"));
					return ll;
				}
				
			});
			if(ls!=null&&ls.size()>0)
			{
				return ls.get(0);
			}
			return null;
		
	}

	@Override
	public void updateLose(long id)
	{
		String sql = "update es_interact_lottery_lose set num=num+1 where id=?";
		jdbcTemplate.update(sql, new Object[]
		{ id });
	}

	@Override
	public void updateWinLose(long id)
	{
		String sql = "update es_interact_lottery_lose set num=0 where id=?";
		jdbcTemplate.update(sql, new Object[]
		{ id });
	}

	@Override
	public void addLose(long uid, int type, long lid)
	{
		String sql = "insert into es_interact_lottery_lose (uid,type,lid,num) values (?,?,?,1)";
		jdbcTemplate.update(sql, new Object[]
		{ uid,type,lid });
	}

	@Override
	public List<LotteryPrize> findLotteryPrize(long lid)
	{
		return jdbcTemplate.query("select * from es_interact_lottery_prize where lid=? and status='E' order by positionid asc ", new Object[]{ lid }, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				LotteryPrize lp = new LotteryPrize();
				lp.setId(rs.getLong("id"));
				lp.setHydefault(rs.getString("hydefault"));
				lp.setImg(rs.getString("img"));
				lp.setLid(rs.getLong("lid"));
				lp.setLocation(rs.getString("location"));
				lp.setName(rs.getString("name"));
				lp.setPrice(rs.getInt("price"));
				lp.setStatus(rs.getString("status"));
				lp.setTotal(rs.getInt("total"));
				lp.setType(rs.getString("type"));
				lp.setUsed(rs.getInt("used"));
				lp.setJf(rs.getInt("jf"));
				lp.setHprice(rs.getInt("hprice"));
				lp.setHbgz(rs.getInt("hbgz"));
				lp.setHbkey(rs.getString("hbkey"));
				lp.setProductid(rs.getLong("productid"));
				lp.setLink(rs.getString("link"));
				lp.setPositionid(rs.getInt("positionid"));
				lp.setStyle(rs.getString("style"));
				return lp;
			}

		});
	}

	@Override
	public int updateLotteryPrize(final List<LotteryPrize> prizes)
	{
		int[] result = jdbcTemplate.batchUpdate("update es_interact_lottery_prize set name = ?,img=?,total=?,style=?,type=?,jf=?,hprice=?,hydefault=? where id = ?", new BatchPreparedStatementSetter() {
			@Override
			public int getBatchSize() {
				return prizes.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				int i = 1;
				LotteryPrize lp = prizes.get(index);
				lp.process();
				ps.setString(i++, lp.getName());
				ps.setString(i++, lp.getImg());
				ps.setInt(i++, lp.getTotal());
				ps.setString(i++, lp.getStyle());
				ps.setString(i++, lp.getType());
				ps.setInt(i++, lp.getJf());
				ps.setInt(i++, lp.getHprice());
				ps.setString(i++, lp.getHydefault());
				ps.setLong(i++, lp.getId());
			}
		});
		return result.length;
	}
}
