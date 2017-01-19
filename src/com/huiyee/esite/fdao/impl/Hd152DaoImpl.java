
package com.huiyee.esite.fdao.impl;

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
import com.huiyee.esite.fdao.IHd152Dao;
import com.huiyee.esite.fdao.impl.Hd118DaoImpl.FeatureInteractAptMappingRowmapper;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.appointment.model.OrderMappingModel;
import com.huiyee.interact.cb.model.InteractCb;

public class Hd152DaoImpl extends AbstractDao implements IHd152Dao
{

	private static final String SAVE_FEATRUE_INTERACT = "insert into esite.es_feature_cb (pageid,createtime) values(?,now())";

	@Override
	public long saveFeatureInteract(final long pageid)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(SAVE_FEATRUE_INTERACT, new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, pageid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public InteractCb findCbByFid(long fid)
	{
		List<InteractCb> list = getJdbcTemplate().query("select * from  esite.es_feature_cb f join es_interact_cb c on f.cbid=c.id where f.id=?", new Object[]
		{ fid }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				InteractCb cb = new InteractCb();
				cb.setId(rs.getLong("c.id"));
				return cb;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public List<InteractCb> findCblist(long ownerid)
	{
		return getJdbcTemplate().query("select * from es_interact_cb where owner=?", new Object[]
		{ ownerid }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				InteractCb cb = new InteractCb();
				cb.setId(rs.getLong("id"));
				cb.setTitle(rs.getString("title"));
				return cb;
			}
		});
	}

	private static final String FIND_APT_BY_CBID = "select a.* from esite.es_interact_apt a join es_interact_cb c on a.id=c.aptid where c.id = ? ";

	@Override
	public AppointmentModel findAptByCbid(long id)
	{
		List<AppointmentModel> list = getJdbcTemplate().query(FIND_APT_BY_CBID, new Object[]
		{ FIND_APT_BY_CBID }, new FeatureRowmapper());
		return list.size() > 0 ? list.get(0) : null;
	}

	private static final String FIND_INTERACT_APT_MAPPING_BY_ID = "select s.* from esite.es_interact_apt a join esite.es_interact_apt_mapping s on a.id= s.aptid where a.id = ? and s.isshow = 'Y' order by s.row";

	@Override
	public List<OrderMappingModel> findInteractAptMappingById(long id)
	{
		Object[] params =
		{ id };
		return getJdbcTemplate().query(FIND_INTERACT_APT_MAPPING_BY_ID, params, new FeatureInteractAptMappingRowmapper());
	}

	class FeatureRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			AppointmentModel apt = new AppointmentModel();
			apt.setId(rs.getLong("id"));
			apt.setOwnerid(rs.getLong("ownerid"));
			apt.setTitle(rs.getString("title"));
			apt.setContent(rs.getString("content"));
			apt.setStarttimeDate(rs.getTimestamp("starttime"));
			apt.setEndtimeDate(rs.getTimestamp("endtime"));
			apt.setTotallimit(rs.getInt("totallimit"));
			apt.setStatus(rs.getString("status"));
			apt.setIslottery(rs.getString("islottery"));
			apt.setLotteryid(rs.getLong("lotteryid"));
			apt.setLotterychance(rs.getInt("lotterychance"));
			apt.setBalance(rs.getInt("balance"));
			apt.setMaxlotterychance(rs.getInt("maxlotterychance"));
			apt.setStartnote(rs.getString("startnote"));
			apt.setEndnote(rs.getString("endnote"));
			return apt;
		}

	}

	class FeatureInteractAptMappingRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			OrderMappingModel apt = new OrderMappingModel();
			apt.setAptid(rs.getLong("aptid"));
			apt.setName(rs.getString("name"));
			apt.setMapping(rs.getString("mapping"));
			apt.setColtype(rs.getString("coltype"));
			apt.setStype(rs.getString("stype"));
			apt.setDefaultvalue(rs.getString("defaultvalue"));
			apt.setIsshow(rs.getString("isshow"));
			apt.setRow(rs.getInt("row"));
			apt.setTag(rs.getString("tag"));
			apt.setReq(rs.getString("req"));
			return apt;
		}

	}

	@Override
	public int updateFeatureCbid(long cbid, long fid)
	{
		return getJdbcTemplate().update("update es_feature_cb set cbid=? where id=?", new Object[]
		{ cbid, fid });
	}
}
