package project.caidan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import project.caidan.dao.ICaiDanLotteryDao;
import project.caidan.model.CDLottery;
import project.caidan.model.CDSet;

import com.huiyee.interact.lottery.model.Lottery;


public class CaiDanLotteryDaoImpl implements ICaiDanLotteryDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<CDSet> findCdSetByType(String type)
	{
		String sql = "select * from caidan.cd_set where type = ?";
		Object[] params = {type};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDSet set = new CDSet();
				set.setId(rs.getLong("id"));
				set.setEnid(rs.getLong("enid"));
				set.setImg(rs.getString("img"));
				set.setType(rs.getString("type"));
				return set;
			}
			
		});
	}

	@Override
	public long saveCdLottery(long lid, String img, long enid, int idx)
	{
		String sql = "insert into caidan.cd_lottery(lid,img,pageid,idx) values(?,?,?,?)";
		Object[] params = {lid,img,enid,idx};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int findMaxIdx(long owner, String type)
	{
		String sql = "select max(cd.idx) from esite.es_interact_lottery l join caidan.cd_lottery cd on l.id = cd.lid  where l.owner=? and l.status != 'D'";
		Object[] params = {owner};
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<Lottery> findAllLottery(int start, int size, long owner, String type, long omid)
	{
		return jdbcTemplate.query("select * from esite.es_interact_lottery l join caidan.cd_lottery cd on l.id = cd.lid where l.owner=? and l.status!='D' and l.omid=? order by cd.idx desc limit ?,?", new Object[]{ owner, omid, start, size }, new MyLotteryRowMapper());
	}
	
	@Override
	public List<Lottery> findAllLottery( long owner,long omid)
	{
		return jdbcTemplate.query("select * from esite.es_interact_lottery l join caidan.cd_lottery cd on l.id = cd.lid where l.owner=? and l.status!='D' and l.omid=? order by cd.idx desc", new Object[]{ owner, omid }, new MyLotteryRowMapper());
	}

	@Override
	public int findLotteryTotal(long owner, String type, long omid)
	{
		return jdbcTemplate.queryForInt("select count(l.id) from esite.es_interact_lottery l join caidan.cd_lottery cd on l.id = cd.lid where l.owner=? and l.status!='D' and l.omid=?", new Object[]{ owner, omid });
	}
	
	class MyLotteryRowMapper implements RowMapper
	{
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			Lottery l = new Lottery();
			l.setId(rs.getLong("l.id"));
			l.setName(rs.getString("l.name"));
			l.setStarttimeDate(rs.getTimestamp("l.starttime"));
			l.setEndtimeDate(rs.getTimestamp("l.endtime"));
			l.setOwner(rs.getLong("l.owner"));
			l.setStatus(rs.getString("l.status"));
			l.setType(rs.getString("l.type"));
			l.setZjl(rs.getInt("l.zjl"));
			l.setDetail(rs.getString("l.detail"));
			l.setEndimg(rs.getString("l.endimg"));
			l.setUserName(rs.getString("l.userName"));
			l.setUserNameValue(rs.getString("l.userNameValue"));
			l.setUserPhone(rs.getString("l.userPhone"));
			l.setUserPhoneValue(rs.getString("l.userPhoneValue"));
			l.setUserEmail(rs.getString("l.userEmail"));
			l.setUserEmailValue(rs.getString("l.userEmailValue"));
			l.setUserLocation(rs.getString("l.userLocation"));
			l.setUserLocationValue(rs.getString("l.userLocationValue"));
			l.setRuletype(rs.getString("l.ruletype"));
			l.setUsertype(rs.getString("l.usertype"));
			l.setAssignuser(rs.getString("l.assignuser"));
			l.setUsertotal(rs.getInt("l.usertotal"));
			l.setUserzjtotal(rs.getInt("l.userzjtotal"));
			l.setUserdaynum(rs.getInt("l.userdaynum"));
			l.setStartnote(rs.getString("l.startnote"));
			l.setEndnote(rs.getString("l.endnote"));
			l.setGzeid(rs.getLong("l.gzeid"));
			
			l.setImg(rs.getString("cd.img"));
			l.setPageid(rs.getLong("cd.pageid"));
			return l;
		}
	}
	
	@Override
	public int updateCdLottery(long lid, String img, long enid)
	{
		String sql = "update caidan.cd_lottery set img = ?,pageid = ? where lid = ?";
		Object[] params = {img,enid,lid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public CDLottery findCdLotteryById(long lid)
	{
		String sql = "select * from caidan.cd_lottery where lid = ?";
		Object[] params = {lid};
		List<CDLottery> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDLottery l = new CDLottery();
				l.setId(rs.getLong("id"));
				l.setImg(rs.getString("img"));
				l.setLid(rs.getLong("lid"));
				l.setPageid(rs.getLong("pageid"));
				l.setIdx(rs.getInt("idx"));
				return l;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Lottery findLotteryById(long lid, long owner, String type)
	{
		String sql = "select * from esite.es_interact_lottery l join caidan.cd_lottery cd on l.id = cd.lid where l.owner=? and l.status!='D' and l.omid=0 and l.id = ?";
		Object[] params = {owner,lid};
		List<Lottery> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Lottery l = new Lottery();
				l.setId(rs.getLong("l.id"));
				l.setIdx(rs.getInt("cd.idx"));
				return l;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public Lottery findFrontLottery(long lid, long owner, String type)
	{
		String sql = "select * from esite.es_interact_lottery l join caidan.cd_lottery cd on l.id = cd.lid where cd.idx >(select cdl.idx from caidan.cd_lottery cdl where cdl.lid = ?) and l.owner=? and l.status!='D' and l.omid=0 order by cd.idx asc limit 1";
		Object[] params = {lid,owner};
		List<Lottery> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Lottery l = new Lottery();
				l.setId(rs.getLong("l.id"));
				l.setIdx(rs.getInt("cd.idx"));
				return l;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public int updateLotteryIdx(long lid, long idx)
	{
		String sql = "update caidan.cd_lottery set idx = ? where lid = ?";
		Object[] params = {idx,lid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public Lottery findNextLottery(long lid, long owner, String type)
	{
		String sql = "select * from esite.es_interact_lottery l join caidan.cd_lottery cd on l.id = cd.lid where cd.idx < (select cdl.idx from caidan.cd_lottery cdl where cdl.lid = ?) and l.owner=? and l.status!='D' and l.omid=0 order by cd.idx desc limit 1";
		Object[] params = {lid,owner};
		List<Lottery> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Lottery l = new Lottery();
				l.setId(rs.getLong("l.id"));
				l.setIdx(rs.getInt("cd.idx"));
				return l;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		
		return null;
	}
	
}
