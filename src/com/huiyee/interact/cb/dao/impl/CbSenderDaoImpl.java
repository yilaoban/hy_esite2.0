
package com.huiyee.interact.cb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.cb.dao.ICbSenderDao;
import com.huiyee.interact.cb.model.CbSender;
import com.huiyee.interact.cb.model.SenderImpel;

public class CbSenderDaoImpl extends AbstractDao implements ICbSenderDao
{

	@Override
	public int findSenderRecordTotal(long cbid, int sendType, long owner)
	{
		String sql = "select count(*) from es_interact_cb c join es_feature_interact_apt_record r on c.aptid=r.aptid left outer join es_interact_cb_sender s on c.id=s.owner and s.recordid=r.id where c.id=? and c.owner=?";
		if (sendType == 1)
		{
			sql += " and s.id is null ";
		} else if (sendType == 2)
		{
			sql += " and s.status='CMP' ";
		} else if (sendType == 3)
		{
			sql += " and s.status='ERR' ";
		} else if (sendType == 4)
		{
			sql += " and s.status='FAL' ";
		}
		return getJdbcTemplate().queryForInt(sql, new Object[]
		{ cbid, owner });
	}

	@Override
	public List<CbSender> findSenderRecord(long cbid, int sendType, long owner, int start, int size)
	{
		String sql = "select * from es_interact_cb c join es_feature_interact_apt_record r on c.aptid=r.aptid left outer join es_interact_cb_sender s on c.id=s.owner and r.id=s.recordid where c.id=? and c.owner=?";
		// 0-全部 1-新增 2-已批准 3-已拒绝
		if (sendType == 1)
		{
			sql += " and s.id is null ";
		} else if (sendType == 2)
		{
			sql += " and s.status='CMP' ";
		} else if (sendType == 3)
		{
			sql += " and s.status='ERR' ";
		} else if (sendType == 4)
		{
			sql += " and s.status='FAL' ";
		}
		sql += " order by r.createtime desc limit ?,?";
		return getJdbcTemplate().query(sql, new Object[]
		{ cbid, owner, start, size }, new CBRowmapper());
	}

	@Override
	public CbSender findRecordById(long owner, long rid)
	{
		List<CbSender> list = getJdbcTemplate()
				.query("select * from es_interact_cb c join es_feature_interact_apt_record r on c.aptid=r.aptid left outer join es_interact_cb_sender s on c.id=s.owner and s.recordid=r.id where c.id=? and r.id=?",
						new Object[]
						{ owner, rid }, new CBRowmapper());
		return list.size() > 0 ? list.get(0) : null;
	}
	
	@Override
	public CbSender findRecordByUid(long owner, long uid)
	{
		List<CbSender> list = getJdbcTemplate()
				.query("select * from es_interact_cb c join es_feature_interact_apt_record r on c.aptid=r.aptid left outer join es_interact_cb_sender s on c.id=s.owner and s.recordid=r.id where c.id=? and r.entityid=?",
						new Object[]
						{ owner, uid }, new CBRowmapper());
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * 
	 * @author ldw
	 * 根据record表查找left outer join sender表
	 */
	class CBRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{

			CbSender sender = new CbSender();
			AppointmentDataModel apt = new AppointmentDataModel();
			apt.setId(rs.getLong("r.id"));
			apt.setAptid(rs.getLong("r.aptid"));
			apt.setWbuid(rs.getLong("r.entityid"));
			apt.setName(rs.getString("r.name"));
			apt.setAge(rs.getInt("r.age"));
			apt.setGender(rs.getString("r.gender"));
			apt.setBirthday(rs.getTimestamp("r.birthday"));
			apt.setAddress(rs.getString("r.address"));
			apt.setCreatetime(rs.getTimestamp("r.createtime"));
			apt.setIdcard(rs.getString("r.idcard"));
			apt.setEmail(rs.getString("r.email"));
			apt.setPhone(rs.getString("r.phone"));
			apt.setDate(rs.getTimestamp("r.date"));
			apt.setTime(rs.getTimestamp("r.time"));
			apt.setIp(rs.getString("r.ip"));
			apt.setF1(rs.getString("r.f1"));
			apt.setF2(rs.getString("r.f2"));
			apt.setF3(rs.getString("r.f3"));
			apt.setF4(rs.getString("r.f4"));
			apt.setF5(rs.getString("r.f5"));
			apt.setF6(rs.getString("r.f6"));
			apt.setF7(rs.getString("r.f7"));
			apt.setF8(rs.getString("r.f8"));
			apt.setF9(rs.getString("r.f9"));
			apt.setF10(rs.getString("r.f10"));
			apt.setF11(rs.getString("r.f11"));
			apt.setF12(rs.getString("r.f12"));
			apt.setF13(rs.getString("r.f13"));
			apt.setF14(rs.getString("r.f14"));
			apt.setF15(rs.getString("r.f15"));
			apt.setF16(rs.getString("r.f16"));
			apt.setF17(rs.getString("r.f17"));
			apt.setF18(rs.getString("r.f18"));
			apt.setF19(rs.getString("r.f19"));
			apt.setF20(rs.getString("r.f20"));
			apt.setF20(rs.getString("r.f20"));
			apt.setF21(rs.getString("r.f21"));
			apt.setF22(rs.getString("r.f22"));
			apt.setF23(rs.getString("r.f23"));
			apt.setF24(rs.getString("r.f24"));
			apt.setF25(rs.getString("r.f25"));
			apt.setF26(rs.getString("r.f26"));
			apt.setF27(rs.getString("r.f27"));
			apt.setF28(rs.getString("r.f28"));
			apt.setF29(rs.getString("r.f29"));
			apt.setF30(rs.getString("r.f30"));
			apt.setType(rs.getString("r.type"));
			apt.setProvince(rs.getString("r.province"));
			apt.setCity(rs.getString("r.city"));
			apt.setDistrict(rs.getString("r.district"));
			sender.setRecord(apt);
			sender.setHyuid(rs.getLong("s.hyuid"));
			sender.setAptid(rs.getLong("r.aptid"));
			sender.setWxuid(rs.getLong("s.wxuid"));
			sender.setRecordid(rs.getLong("r.id"));
			sender.setStatus(rs.getString("s.status"));
			sender.setOwner(rs.getLong("s.owner"));
			sender.setHbtotal(rs.getInt("s.hbtotal"));
			sender.setHbused(rs.getInt("s.hbused"));
			sender.setReason(rs.getString("s.reason"));
			return sender;
		}
	}

	@Override
	public int updateCbSenderSub(CbSender sender, String status, String reason, long owner)
	{
		return getJdbcTemplate().update(
				"insert into es_interact_cb_sender (hyuid,wxuid,aptid,recordid,status,owner,reason)values(?,?,?,?,?,?,?) on duplicate key update status=?,reason=?", new Object[]
				{  sender.getHyuid(),sender.getRecord().getWbuid(), sender.getAptid(), sender.getRecord().getId(), status, owner, reason, status, reason });
	}

	@Override
	public CbSender findRecordById(long sender)
	{
		List<CbSender> list = getJdbcTemplate().query("select * from es_interact_cb_sender s  join es_feature_interact_apt_record r on s.recordid=r.id  where s.id=?", new Object[]
		{ sender }, new CBRowmapper1());
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public CbSender findSenderByUId(long owner, long wxuid)
	{
		List<CbSender> list = getJdbcTemplate().query("select * from es_interact_cb_sender s  join es_feature_interact_apt_record r on s.recordid=r.id  where owner=? and wxuid=?",
				new Object[]
				{ owner, wxuid }, new CBRowmapper1());
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public List<CbSender> findSenderByTime(long cbid, String endtime, int start, int size)
	{
		return getJdbcTemplate().query(
				"select max(endtime) endtime,sender from es_interact_cb_jl_record  where cbid=?  group by sender having  endtime <? order by endtime asc limit ?,?", new Object[]
				{ cbid, endtime, start, size }, new RowMapper()
				{

					public Object mapRow(ResultSet rs, int arg1) throws SQLException
					{
						CbSender sender = new CbSender();
						sender.setId(rs.getLong("sender"));
						sender.setLastUpTime(rs.getTimestamp("endtime"));
						return sender;
					}
				});
	}

	@Override
	public void addHbtotal(SenderImpel si, int subPrice)
	{
		getJdbcTemplate().update("update es_interact_cb_sender set hbtotal=hbtotal+? where id=? and status='CMP'", new Object[]
		{ subPrice, si.getSender() });
	}

	/**
	 * 
	 * @author ldw
	 *根据sender表查找 join record表
	 */
	class CBRowmapper1 implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{

			CbSender sender = new CbSender();
			AppointmentDataModel apt = new AppointmentDataModel();
			apt.setId(rs.getLong("r.id"));
			apt.setAptid(rs.getLong("r.aptid"));
			apt.setWbuid(rs.getLong("r.entityid"));
			apt.setName(rs.getString("r.name"));
			apt.setAge(rs.getInt("r.age"));
			apt.setGender(rs.getString("r.gender"));
			apt.setBirthday(rs.getTimestamp("r.birthday"));
			apt.setAddress(rs.getString("r.address"));
			apt.setCreatetime(rs.getTimestamp("r.createtime"));
			apt.setIdcard(rs.getString("r.idcard"));
			apt.setEmail(rs.getString("r.email"));
			apt.setPhone(rs.getString("r.phone"));
			apt.setDate(rs.getTimestamp("r.date"));
			apt.setTime(rs.getTimestamp("r.time"));
			apt.setIp(rs.getString("r.ip"));
			apt.setF1(rs.getString("r.f1"));
			apt.setF2(rs.getString("r.f2"));
			apt.setF3(rs.getString("r.f3"));
			apt.setF4(rs.getString("r.f4"));
			apt.setF5(rs.getString("r.f5"));
			apt.setF6(rs.getString("r.f6"));
			apt.setF7(rs.getString("r.f7"));
			apt.setF8(rs.getString("r.f8"));
			apt.setF9(rs.getString("r.f9"));
			apt.setF10(rs.getString("r.f10"));
			apt.setF11(rs.getString("r.f11"));
			apt.setF12(rs.getString("r.f12"));
			apt.setF13(rs.getString("r.f13"));
			apt.setF14(rs.getString("r.f14"));
			apt.setF15(rs.getString("r.f15"));
			apt.setF16(rs.getString("r.f16"));
			apt.setF17(rs.getString("r.f17"));
			apt.setF18(rs.getString("r.f18"));
			apt.setF19(rs.getString("r.f19"));
			apt.setF20(rs.getString("r.f20"));
			apt.setF20(rs.getString("r.f20"));
			apt.setF21(rs.getString("r.f21"));
			apt.setF22(rs.getString("r.f22"));
			apt.setF23(rs.getString("r.f23"));
			apt.setF24(rs.getString("r.f24"));
			apt.setF25(rs.getString("r.f25"));
			apt.setF26(rs.getString("r.f26"));
			apt.setF27(rs.getString("r.f27"));
			apt.setF28(rs.getString("r.f28"));
			apt.setF29(rs.getString("r.f29"));
			apt.setF30(rs.getString("r.f30"));
			apt.setType(rs.getString("r.type"));
			apt.setProvince(rs.getString("r.province"));
			apt.setCity(rs.getString("r.city"));
			apt.setDistrict(rs.getString("r.district"));
			sender.setRecord(apt);
//			sender.setCbid(rs.getLong("s.cbid"));
			sender.setAptid(rs.getLong("s.aptid"));
			sender.setWxuid(rs.getLong("s.wxuid"));
			sender.setRecordid(rs.getLong("s.recordid"));
			sender.setStatus(rs.getString("s.status"));
			sender.setOwner(rs.getLong("s.owner"));
			sender.setHbtotal(rs.getInt("s.hbtotal"));
			sender.setHbused(rs.getInt("s.hbused"));
			sender.setId(rs.getLong("s.id"));
			return sender;
		}
	}

	@Override
	public int findSenderTotal(long owner, String name)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(s.id) from es_interact_cb_sender s join es_hy_user u on s.hyuid=u.id where s.owner=? and s.status='CMP'";
		list.add(owner);
		if (StringUtils.isNotEmpty(name))
		{
			sql += " and u.nickname like ?";
			list.add("%" + name + "%");
		}
		return getJdbcTemplate().queryForInt(sql, list.toArray());
	}

	@Override
	public List<CbSender> findSenderByCbid(long owner, String name, int start, int size)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from es_interact_cb_sender s join es_hy_user u on s.hyuid=u.id where s.owner=? ";
		list.add(owner);
		if (StringUtils.isNotEmpty(name))
		{
			sql += " and u.nickname like ?";
			list.add("%" + name + "%");
		}
		sql += " order by hbtotal desc limit ?,?";
		list.add(start);
		list.add(size);
		return getJdbcTemplate().query(sql, list.toArray(), new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				CbSender sender = new CbSender();
				sender.setAptid(rs.getLong("s.aptid"));
				sender.setHyuid(rs.getLong("s.hyuid"));
				sender.setWxuid(rs.getLong("s.wxuid"));
				sender.setRecordid(rs.getLong("s.recordid"));
				sender.setStatus(rs.getString("s.status"));
				sender.setOwner(rs.getLong("s.owner"));
				sender.setHbtotal(rs.getInt("s.hbtotal"));
				sender.setHbused(rs.getInt("s.hbused"));
				sender.setId(rs.getLong("s.id"));
				AppointmentDataModel apt = new AppointmentDataModel();
				apt.setName(rs.getString("u.nickname"));
				sender.setRecord(apt);
				return sender;
			}
			
		});
	}

	@Override
	public void delSender(long owner, long uid)
	{
		getJdbcTemplate().update("delete from es_interact_cb_sender where owner=? and wxuid=? ", new Object[]
		{ owner, uid });

	}

	@Override
	public int findSender(long hyuid)
	{
		return getJdbcTemplate().queryForInt("select count(id) from es_interact_cb_sender where hyuid=? and status='CMP'", new Object[]{hyuid});
	}

	@Override
	public void updateCbSenderUsed(long id,int status)
	{
		String sql = "update crm.crm_wx_hongbao_cb_send s,esite.es_interact_cb_sender d set s.status = ?,d.hbused=d.hbused-s.rmb where s.id = ? and s.hyuid=d.hyuid";
		Object[] params = {status,id};
		getJdbcTemplate().update(sql, params);
	}
}
