package com.huiyee.interact.xc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.interact.xc.model.HdEntity;

public class XcfeatureDaoImpl extends AbstractDao implements IXcfeatureDao
{

	private static final String ADD_XC_FEATURE = "insert into es_interact_xc_feature (xcid,featureid,entityid,pages) values (?,?,?,?)";

	@Override
	public long addXcfeature(final long xcid, final int featureid, final long entityid,final String pages)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(ADD_XC_FEATURE, new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, xcid);
				ps.setInt(i++, featureid);
				ps.setLong(i++, entityid);
				ps.setString(i++, pages);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public List<HdEntity> findXcEntitys(long xcid)
	{
		return getJdbcTemplate().query("select * from es_interact_xc_feature where xcid=? order by id", new Object[]
		{ xcid }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				HdEntity e = new HdEntity();
				e.setId(rs.getLong("id"));
				e.setEntityid(rs.getLong("entityid"));
				e.setFeatureid(rs.getInt("featureid"));
				return e;
			}
		});
	}

	@Override
	public int deletfeature(int featureid, long entityid)
	{
		return getJdbcTemplate().update("delete from es_interact_xc_feature where featureid=? and entityid=?", new Object[]
		{ featureid, entityid });
	}

	private static final String FIND_VOTE_VALUE = "select * from es_interact_vote where id=?";

	@Override
	public HdEntity findVoteValue(long entityid)
	{
		List<HdEntity> list = getJdbcTemplate().query(FIND_VOTE_VALUE, new Object[]
		{ entityid }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				HdEntity h = new HdEntity();
				h.setTitle(rs.getString("title"));
				return h;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	private static final String FIND_LOTTERY_VALUE = "select * from es_interact_lottery where id=?";

	@Override
	public HdEntity findLotteryValue(long entityid)
	{
		List<HdEntity> list = getJdbcTemplate().query(FIND_LOTTERY_VALUE, new Object[]
		{ entityid }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				HdEntity h = new HdEntity();
				h.setTitle(rs.getString("name"));
				h.setVotetype(rs.getString("type"));
				return h;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	private static final String FIND_GROUP_BY_TYPE = "select * from es_site_group where istemplate='Y' and status!='DEL'";

	@Override
	public List<Sitegroup> findGroupByType()
	{
		return getJdbcTemplate().query(FIND_GROUP_BY_TYPE, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Sitegroup s = new Sitegroup();
				s.setId(rs.getLong("id"));
				s.setGroupname(rs.getString("groupname"));
				s.setType(rs.getString("type"));
				return s;
			}
		});
	}

	@Override
	public int updatehdstart(long hdid, long ownerid)
	{
		return getJdbcTemplate().update("update es_interact_lottery l set starttime=now(),endtime=DATE_ADD(now(),INTERVAL 1 DAY) where id=? and owner=?", new Object[]
		{ hdid, ownerid });
	}
	
	@Override
	public int updatehdEnd(long hdid, long ownerid)
	{
		return getJdbcTemplate().update("update es_interact_lottery l set starttime=now(),endtime=now() where id=? and owner=?", new Object[]
		{ hdid, ownerid });
	}
	
	@Override
	public String findXcfeaturePages(long xcid, int interactid, long hdid)
	{
		List<String> pages=getJdbcTemplate().query("select pages from es_interact_xc_feature where xcid=? and featureid=? and entityid=?", new Object[]{xcid,interactid,hdid},new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getString("pages");
			}
		});
		return pages.size()>0?pages.get(0):null;
	}
	
	
	@Override
	public int updateLotteryClean(long lid)
	{
		String[] sql={
				"delete s from  es_interact_lottery_user_record  r left outer join es_interact_lottery_user_sub s on s.lurid=r.id where r.lid="+lid,	
				"delete from es_interact_lottery_user_record where lid="+lid,
				"delete from es_interact_lottery_user_date where lid="+lid,
				"delete from es_interact_lottery_user where lid="+lid,
				"delete from es_interact_lottery_user_chance_record where lid="+lid,
				"update es_interact_lottery_prize set used=0 where lid="+lid,
				"delete from es_interact_lottery_lose where lid="+lid
			};
			int[] rs=getJdbcTemplate().batchUpdate(sql);
			int sum=0;
			for (int i : rs)
			{
				sum=sum+i;
			}
			return sum;
	}
	
	@Override
	public int updateVoteClean(long voteid)
	{
		String[] sql={
				"delete o from  es_feature_interact_vote_record  r left outer join es_feature_interact_vote_record_option o on o.recordid=r.id where voteid="+voteid,	
				"delete from es_feature_interact_vote_record where voteid="+voteid,
				"update es_interact_vote_option set count=0 where voteid="+voteid,
				"delete from es_interact_user_data where featureid=123 and hdid="+voteid,
			};
		int[] rs=getJdbcTemplate().batchUpdate(sql);
		int sum=0;
		for (int i : rs)
		{
			sum=sum+i;
		}
		return sum;
	}
}
