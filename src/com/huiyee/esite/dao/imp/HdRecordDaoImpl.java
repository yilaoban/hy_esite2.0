
package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IHdRecordDao;
import com.huiyee.esite.model.AppointmentRecord;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.HdRecord;
import com.huiyee.esite.model.ZanDetail;
import com.huiyee.interact.appointment.model.AppointmentDataModel;

public class HdRecordDaoImpl extends AbstractDao implements IHdRecordDao
{

	private static final String FIND_HDRECORD = "select r.*,p.siteid siteid from esite.es_feature_dynamic_action_record r join esite.es_page p on r.pageid = p.id join esite.es_site s on p.siteid = s.id join esite.es_site_group g on s.sitegroupid = g.id where g.id = ?";

	@Override
	public List<HdRecord> findHdRecordBySitegroupid(long sitegroupid)
	{
		Object[] params =
		{ sitegroupid };
		return getJdbcTemplate().query(FIND_HDRECORD, params, new HdReocrdRowmapper());
	}

	class HdReocrdRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			HdRecord dr = new HdRecord();
			dr.setId(rs.getLong("id"));
			dr.setWbuid(rs.getLong("wbuid"));
			dr.setPageid(rs.getLong("pageid"));
			dr.setAction(rs.getLong("action"));
			dr.setEntityid(rs.getLong("entityid"));
			dr.setSource(rs.getString("source"));
			dr.setTerminal(rs.getString("terminal"));
			dr.setIp(rs.getString("ip"));
			dr.setSiteid(rs.getLong("siteid"));
			return dr;
		}

	}

	private static final String FIND_HDTYPE_BY_SITEGROUPID = "select action from esite.es_site s join esite.es_page p on s.id =p.siteid join esite.es_feature_dynamic_action_record r on p.id = r.pageid where s.sitegroupid = ? group by r.action";

	@Override
	public List<Long> findHdTypeBySitegroupid(long sitegroupid)
	{
		Object[] params =
		{ sitegroupid };
		try
		{
			return getJdbcTemplate().queryForList(FIND_HDTYPE_BY_SITEGROUPID, params, Long.class);
		} catch (DataAccessException e)
		{
			return null;
		}
	}

	private static final String FIND_HD_RECORD_NO_PROCESS = "select r.*,s.sitegroupid,t.cid,u.fensishu from esite.es_feature_dynamic_action_record r join esite.es_page p on r.pageid = p.id join esite.es_site s on p.siteid = s.id left join es_user_info u on (r.wbuid=u.wbuid and u.siteid=s.id) join esite.es_sina_token t on s.id =t.siteid where r.fansprocessstatus ='N' and t.type='Z' and u.status='NOR' limit ?";

	@Override
	public List<HdRecord> findHdRecordNoProcess(int size)
	{
		Object[] params =
		{ size };
		return getJdbcTemplate().query(FIND_HD_RECORD_NO_PROCESS, params, new HdReocrdRowmapper2());
	}

	class HdReocrdRowmapper2 implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			HdRecord dr = new HdRecord();
			dr.setId(rs.getLong("id"));
			dr.setWbuid(rs.getLong("wbuid"));
			dr.setPageid(rs.getLong("pageid"));
			dr.setAction(rs.getLong("action"));
			dr.setEntityid(rs.getLong("entityid"));
			dr.setSource(rs.getString("source"));
			dr.setTerminal(rs.getString("terminal"));
			dr.setIp(rs.getString("ip"));
			dr.setSitegroupid(rs.getLong("sitegroupid"));
			dr.setCid(rs.getLong("cid"));
			dr.setFansnum(rs.getInt("fensishu"));
			return dr;
		}

	}

	private static final String UPDATE_HDRECORD_FANS_STATUS = "update esite.es_feature_dynamic_action_record set fansprocessstatus = ? where id = ?";

	@Override
	public int updateHdReocrdFansStatus(long id, String type)
	{
		Object[] params =
		{ type, id };
		return getJdbcTemplate().update(UPDATE_HDRECORD_FANS_STATUS, params);
	}

	@Override
	public int findHdReportNumTotal(long sgid)
	{
		String sql = "select sum(count) from es_report_hd_anaylse where siteid=?";
		return getJdbcTemplate().queryForInt(sql, new Object[]
		{ sgid });
	}

	private static final String FIND_HD_MODEL_LIST = "select * from esite.es_report_hd_anaylse where siteid=? order by count desc limit 10";

	@Override
	public List<HdModel> findHdModelListBySgid(long sgid)
	{
		Object[] params =
		{ sgid };
		return getJdbcTemplate().query(FIND_HD_MODEL_LIST, params, new HdModelRowmapper());
	}

	class HdModelRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			HdModel model = new HdModel();
			model.setHdfid(rs.getLong("hdfid"));
			model.setHdid(rs.getLong("hdid"));
			model.setLasttime(rs.getTimestamp("lasthdtime"));
			model.setTotal(rs.getInt("count"));
			model.setType(rs.getString("hdtype"));
			model.setName(rs.getString("hdmodel"));
			return model;
		}
	}

	@Override
	public int updateUserInfoStatus(long wbuid)
	{
		String sql = "update es_user_info set status='err' where wbuid=?";
		Object[] params =
		{ wbuid };
		return getJdbcTemplate().update(sql, params);
	}

	@Override
	public List<ZanDetail> findZanDetail(long productid, int start, int size)
	{
		String sql = "select su.nickname,a.createtime from es_feature_product_zan a left join es_feature_prolist_product p on a.zanproid=p.id  left join es_feature_prolist pl on p.prolistid=pl.id  left join es_feature_prolist_list ll on ll.id=pl.prolist_listid left join es_page page on page.id=ll.pageid  left join es_site s on s.id=page.siteid join es_user_info u on a.uid=u.id left join es_sina_user su on u.wbuid = su.wbuid  where p.productid=? order by a.createtime desc limit ?,?";
		Object[] params =
		{ productid, start, size };
		return getJdbcTemplate().query(sql, params, new ZanDetailRowmapper());
	}

	class ZanDetailRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			ZanDetail detail = new ZanDetail();
			detail.setNickname(rs.getString("nickname"));
			detail.setCreatetime(rs.getTimestamp("createtime"));
			return detail;
		}
	}

	@Override
	public int findZanDetailTotal(long productid)
	{
		String sql = "select count(a.id) from es_feature_product_zan a left join es_feature_prolist_product p on a.zanproid=p.id  left join es_feature_prolist pl on p.prolistid=pl.id  left join es_feature_prolist_list ll on ll.id=pl.prolist_listid left join es_page page on page.id=ll.pageid  left join es_site s on s.id=page.siteid join es_user_info u on a.uid=u.id left join es_sina_user su on u.wbuid = su.wbuid  where p.productid=? ";
		return getJdbcTemplate().queryForInt(sql, new Object[]
		{ productid });
	}

	@Override
	public AppointmentRecord findAptRecord(long recordid)
	{
		List<AppointmentRecord> list = getJdbcTemplate().query("select * from es_feature_interact_apt_record where id=?", new Object[]
		{ recordid }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AppointmentRecord model = new AppointmentRecord();
				model.setId(rs.getLong("id"));
				model.setAptid(rs.getLong("aptid"));
				model.setWbuid(rs.getLong("entityid"));
				model.setName(rs.getString("name"));
				model.setAge(rs.getInt("age"));
				model.setGender(rs.getString("gender"));
				model.setBirthday(rs.getTimestamp("birthday"));
				model.setAddress(rs.getString("address"));
				model.setIdcard(rs.getString("idcard"));
				model.setEmail(rs.getString("email"));
				model.setPhone(rs.getString("phone"));
				model.setDate(rs.getTimestamp("date"));
				model.setTime(rs.getTimestamp("time"));
				model.setF1(rs.getString("f1"));
				model.setF2(rs.getString("f2"));
				model.setF3(rs.getString("f3"));
				model.setF4(rs.getString("f4"));
				model.setF5(rs.getString("f5"));
				model.setF6(rs.getString("f6"));
				model.setF7(rs.getString("f7"));
				model.setF8(rs.getString("f8"));
				model.setF9(rs.getString("f9"));
				model.setF10(rs.getString("f10"));
				model.setF11(rs.getString("f11"));
				model.setF12(rs.getString("f12"));
				model.setF13(rs.getString("f13"));
				model.setF14(rs.getString("f14"));
				model.setF15(rs.getString("f15"));
				model.setF16(rs.getString("f16"));
				model.setF17(rs.getString("f17"));
				model.setF18(rs.getString("f18"));
				model.setF19(rs.getString("f19"));
				model.setF20(rs.getString("f20"));
				model.setF21(rs.getString("f21"));
				model.setF22(rs.getString("f22"));
				model.setF23(rs.getString("f23"));
				model.setF24(rs.getString("f24"));
				model.setF25(rs.getString("f25"));
				model.setF26(rs.getString("f26"));
				model.setF27(rs.getString("f27"));
				model.setF28(rs.getString("f28"));
				model.setF29(rs.getString("f29"));
				model.setF30(rs.getString("f30"));
				return model;
			}
		});

		return list.size()>0?list.get(0):null;
	}

	@Override
	public AppointmentRecord findAptRecord(long aptid, long wbuid, int type)
	{
		List<AppointmentRecord> list = getJdbcTemplate().query("select * from es_feature_interact_apt_record where aptid=? and entityid=? and type=? order by id desc limit 1", new Object[]
		{ aptid,wbuid,type }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AppointmentRecord model = new AppointmentRecord();
				model.setId(rs.getLong("id"));
				model.setAptid(rs.getLong("aptid"));
				model.setWbuid(rs.getLong("entityid"));
				model.setName(rs.getString("name"));
				model.setAge(rs.getInt("age"));
				model.setGender(rs.getString("gender"));
				model.setBirthday(rs.getTimestamp("birthday"));
				model.setAddress(rs.getString("address"));
				model.setIdcard(rs.getString("idcard"));
				model.setEmail(rs.getString("email"));
				model.setPhone(rs.getString("phone"));
				model.setDate(rs.getTimestamp("date"));
				model.setTime(rs.getTimestamp("time"));
				model.setF1(rs.getString("f1"));
				model.setF2(rs.getString("f2"));
				model.setF3(rs.getString("f3"));
				model.setF4(rs.getString("f4"));
				model.setF5(rs.getString("f5"));
				model.setF6(rs.getString("f6"));
				model.setF7(rs.getString("f7"));
				model.setF8(rs.getString("f8"));
				model.setF9(rs.getString("f9"));
				model.setF10(rs.getString("f10"));
				model.setF11(rs.getString("f11"));
				model.setF12(rs.getString("f12"));
				model.setF13(rs.getString("f13"));
				model.setF14(rs.getString("f14"));
				model.setF15(rs.getString("f15"));
				model.setF16(rs.getString("f16"));
				model.setF17(rs.getString("f17"));
				model.setF18(rs.getString("f18"));
				model.setF19(rs.getString("f19"));
				model.setF20(rs.getString("f20"));
				model.setF21(rs.getString("f21"));
				model.setF22(rs.getString("f22"));
				model.setF23(rs.getString("f23"));
				model.setF24(rs.getString("f24"));
				model.setF25(rs.getString("f25"));
				model.setF26(rs.getString("f26"));
				model.setF27(rs.getString("f27"));
				model.setF28(rs.getString("f28"));
				model.setF29(rs.getString("f29"));
				model.setF30(rs.getString("f30"));
				return model;
			}
		});

		return list.size()>0?list.get(0):null;
	}
}
