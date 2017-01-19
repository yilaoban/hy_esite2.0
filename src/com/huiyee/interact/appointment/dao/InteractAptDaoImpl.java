
package com.huiyee.interact.appointment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.bdmap.dto.BDAddress;
import com.huiyee.esite.model.AppointmentRecord;
import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.appointment.model.AptCode;
import com.huiyee.interact.appointment.model.AptMapping;
import com.huiyee.interact.appointment.model.OrderMappingModel;
import com.huiyee.interact.lottery.model.Lottery;

public class InteractAptDaoImpl implements IInteractAptDao
{

	private JdbcTemplate jdbcTemplate;

	@Override
	public List<AppointmentModel> findOrderMesById(long ownerid, long omid, int start, int size)
	{
		String sql = "select id,ownerid,title,starttime,endtime,daylimit,totallimit,coded from es_interact_apt where status !='DEL' and ownerid =? and omid=? order by id desc limit ?,?";
		Object[] params =
		{ ownerid, omid, start, size };
		return getJdbcTemplate().query(sql, params, new OrderMesMapper());
	}

	class OrderMesMapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException
		{
			AppointmentModel order = new AppointmentModel();
			order.setId(rs.getLong("id"));
			order.setTitle(rs.getString("title"));
			order.setStarttimeDate(rs.getTimestamp("starttime"));
			order.setEndtimeDate(rs.getTimestamp("endtime"));
			order.setTotallimit(rs.getInt("totallimit"));
			order.setCoded(rs.getInt("coded"));
			return order;
		}
	}

	private static final String SAVE_CUSTOM_COMMENT_REPORT = "insert into esite.es_feature_interact_apt_record(code,pageid,entityid,aptid,name,age, gender,birthday,address,idcard,email,phone,date,time,type,f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14,f15,f16,f17,f18,f19,f20,ip,terminal,source,createtime,province,city,district,f21,f22,f23,f24,f25,f26,f27,f28,f29,f30) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?,?,?,?,?,?,?,?,?,?)";

	@Override
	public void saveCustomCommentRepotr(final long entityid, final AppointmentRecord apt, final String ip, final String terminal, final String source, final int type)
	{

		getJdbcTemplate().update(
				SAVE_CUSTOM_COMMENT_REPORT,
				new Object[]
				{ apt.getCode(),apt.getPageid(), entityid, apt.getAptid(), apt.getName(), apt.getAge(), apt.getGender(), apt.getBirthday(), apt.getAddress(), apt.getIdcard(), apt.getEmail(),
						apt.getPhone(), apt.getDate(), apt.getTime(), type, apt.getF1(), apt.getF2(), apt.getF3(), apt.getF4(), apt.getF5(), apt.getF6(), apt.getF7(), apt.getF8(),
						apt.getF9(), apt.getF10(), apt.getF11(), apt.getF12(), apt.getF13(), apt.getF14(), apt.getF15(), apt.getF16(), apt.getF17(), apt.getF18(), apt.getF19(),
						apt.getF20(), ip, terminal, source, apt.getProvince(), apt.getCity(), apt.getDistrict(), apt.getF21(), apt.getF22(), apt.getF23(), apt.getF24(),
						apt.getF25(), apt.getF26(), apt.getF27(), apt.getF28(), apt.getF29(), apt.getF30() });
	}

	private static final String SAVE_CUSTOM_COMMENT_REPORTXY = "insert into esite.es_feature_interact_apt_record(code,pageid,entityid,aptid,name,age, gender,birthday,province,city,district,address,idcard,email,phone,date,time,type,f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14,f15,f16,f17,f18,f19,f20,ip,terminal,source,createtime,f21,f22,f23,f24,f25,f26,f27,f28,f29,f30) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?,?,?,?,?,?,?)";

	@Override
	public void saveCustomCommentRepotrXY(final long entityid, final AppointmentRecord apt, final String ip, final String terminal, final String source, final int type,
			BDAddress bda)
	{
		getJdbcTemplate().update(
				SAVE_CUSTOM_COMMENT_REPORTXY,
				new Object[]
				{ apt.getCode(),apt.getPageid(), entityid, apt.getAptid(), apt.getName(), apt.getAge(), apt.getGender(), apt.getBirthday(), bda.getProvince(), bda.getCity(), bda.getDistrict(),
						bda.getFormatted_address(), apt.getIdcard(), apt.getEmail(), apt.getPhone(), apt.getDate(), apt.getTime(), type, apt.getF1(), apt.getF2(), apt.getF3(),
						apt.getF4(), apt.getF5(), apt.getF6(), apt.getF7(), apt.getF8(), apt.getF9(), apt.getF10(), apt.getF11(), apt.getF12(), apt.getF13(), apt.getF14(),
						apt.getF15(), apt.getF16(), apt.getF17(), apt.getF18(), apt.getF19(), apt.getF20(), ip, terminal, source, apt.getF21(), apt.getF22(), apt.getF23(),
						apt.getF24(), apt.getF25(), apt.getF26(), apt.getF27(), apt.getF28(), apt.getF29(), apt.getF30() });
	}

	@Override
	public int findOrderMesTotal(long ownerid, long omid)
	{
		String sql = "select count(*) from es_interact_apt where ownerid = ? and status !='DEL' and omid=? ";
		return getJdbcTemplate().queryForInt(sql, new Object[]
		{ ownerid, omid });
	}

	@Override
	public long addOrderMes(final AppointmentModel apt, final long owerid, final long mid, final long omid)
	{
		final String sql = "insert into es_interact_apt(mid,ownerid,title,content,starttime,endtime,daylimit,totallimit,islottery,lotteryid,lotterychance,balance,createtime,maxlotterychance,startnote,endnote,omid,status) values(?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, mid);
				ps.setLong(i++, owerid);
				ps.setString(i++, apt.getTitle());
				ps.setString(i++, apt.getContent());
				if (apt.getStarttime() != null && !"".equals(apt.getStarttime()))
				{
					ps.setTimestamp(i++, new Timestamp(apt.getStarttime().getTime()));
				} else
				{
					ps.setTimestamp(i++, null);
				}
				if (apt.getEndtime() != null && !"".equals(apt.getEndtime()))
				{
					ps.setTimestamp(i++, new Timestamp(apt.getEndtime().getTime()));
				} else
				{
					ps.setTimestamp(i++, null);
				}
				ps.setInt(i++, apt.getDaylimit());
				ps.setInt(i++, apt.getTotallimit());
				ps.setString(i++, apt.getIslottery());
				ps.setLong(i++, apt.getLotteryid());
				ps.setInt(i++, apt.getLotterychance());
				ps.setInt(i++, apt.getBalance());
				ps.setInt(i++, apt.getMaxlotterychance());
				ps.setString(i++, apt.getStartnote());
				ps.setString(i++, apt.getEndnote());
				ps.setLong(i++, omid);
				if(apt.getTotallimit()==1){
					ps.setString(i++, "UPD");//当只能提交一次时,状态改为UPD 表示申请记录可重复更新
				}else{
					ps.setString(i++, "EDT");//当只能提交一次时,状态改为UPD 表示申请记录可重复更新
				}
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public AppointmentModel findOrderMes(long id)
	{
		String sql = "select * from es_interact_apt where id=? ";
		List<AppointmentModel> list = getJdbcTemplate().query(sql, new Object[]
		{ id }, new AptOrderMesMapper());
		if (list.size() > 0)
		{
			AppointmentModel md = list.get(0);
			return md;
		}
		return null;

	}

	class AptOrderMesMapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException
		{
			AppointmentModel order = new AppointmentModel();
			order.setId(rs.getLong("id"));
			order.setTitle(rs.getString("title"));
			order.setDaylimit(rs.getInt("daylimit"));
			order.setTotallimit(rs.getInt("totallimit"));
			order.setStatus(rs.getString("status"));
			order.setContent(rs.getString("content"));
			order.setStarttimeDate(rs.getTimestamp("starttime"));
			order.setEndtimeDate(rs.getTimestamp("endtime"));
			order.setIslottery(rs.getString("islottery"));
			order.setLotteryid(rs.getLong("lotteryid"));
			order.setLotterychance(rs.getInt("lotterychance"));
			order.setBalance(rs.getInt("balance"));
			order.setMaxlotterychance(rs.getInt("maxlotterychance"));
			order.setStartnote(rs.getString("startnote"));
			order.setEndnote(rs.getString("endnote"));
			order.setCoded(rs.getInt("coded"));
			return order;
		}
	}

	@Override
	public int updateOrderMes(AppointmentModel apt, long id)
	{
		String sql = "update es_interact_apt set title=?,content=?,daylimit=?,totallimit=?,endtime=?,starttime=?,islottery=?,lotteryid=?,lotterychance=?,balance=?,maxlotterychance = ?,startnote=?,endnote=?,status=? where id=?";
		Object[] params =
		{ apt.getTitle(), apt.getContent(), apt.getDaylimit(), apt.getTotallimit(), apt.getEndtime(), apt.getStarttime(), apt.getIslottery(), apt.getLotteryid(),
				apt.getLotterychance(), apt.getBalance(), apt.getMaxlotterychance(), apt.getStartnote(), apt.getEndnote(),apt.getTotallimit()==1?"UPD":"EDT", id };
		return getJdbcTemplate().update(sql, params);
	}

	@Override
	public int deleteOrderMes(long id)
	{
		String sql = "update es_interact_apt set status='DEL' where id=?";
		return getJdbcTemplate().update(sql, new Object[]
		{ id });
	}

	@Override
	public int addName(long aptid, String name, String mapping, String coltype, String stype, String defaultvalue, String isshow, int row, String tag, String req)
	{

		String sql = "insert into es_interact_apt_mapping(aptid,name,mapping,coltype,stype,defaultvalue,isshow,row,tag,req) values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params =
		{ aptid, name, mapping, coltype, stype, defaultvalue, isshow, row, tag, req };
		return getJdbcTemplate().update(sql, params);

	}

	@Override
	public List<OrderMappingModel> findMappingPre(long aptid)
	{
		String sql = "select id, name, aptid,mapping,coltype,stype,defaultvalue,isshow,row,tag,req from es_interact_apt_mapping where aptid=? and isshow !='N' order by row ";
		return getJdbcTemplate().query(sql, new Object[]
		{ aptid }, new UpdateMappingMesMapper());
	}
	
	@Override
	public List<OrderMappingModel> findUserDefine(long aptid)
	{
		String sql = "select id, name, aptid,mapping,coltype,stype,defaultvalue,isshow,row,tag,req from es_interact_apt_mapping where aptid=? and tag!='Y' ";
		return getJdbcTemplate().query(sql, new Object[]
		{ aptid }, new UpdateMappingMesMapper());
	}


	@Override
	public List<OrderMappingModel> findMappingPreNew(long aptid)
	{
		String sql = "select id, name, aptid,mapping,coltype,stype,defaultvalue,isshow,row,tag,req from es_interact_apt_mapping where aptid=? order by id ";
		return getJdbcTemplate().query(sql, new Object[]
		{ aptid }, new UpdateMappingMesMapper());
	}

	class UpdateMappingMesMapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException
		{
			OrderMappingModel model = new OrderMappingModel();
			model.setId(rs.getLong("id"));
			model.setAptid(rs.getLong("aptid"));
			model.setDefaultvalue(rs.getString("defaultvalue"));
			model.setIsshow(rs.getString("isshow"));
			model.setRow(rs.getInt("row"));
			model.setName(rs.getString("name"));
			model.setMapping(rs.getString("mapping"));
			model.setColtype(rs.getString("coltype"));
			model.setStype(rs.getString("stype"));
			model.setTag(rs.getString("tag"));
			model.setReq(rs.getString("req"));
			return model;
		}
	}

	@Override
	public int updateMappingMes(long id, String name, String defaultvalue, String isshow, int row, String tag, String req)
	{
		String sql = "update es_interact_apt_mapping set name=?, defaultvalue=?,isshow=?,row=?,tag=?,req=? where id=?";
		Object[] params =
		{ name, defaultvalue, isshow, row, tag, req, id };
		return getJdbcTemplate().update(sql, params);
	}

	private static final String FIND_APT_MAPPING = "select mapping form esite.es_interact_apt_mapping where aptid = ? and tag = 'N'";

	@Override
	public List<String> findAptMapping(long aptid)
	{
		try
		{
			return getJdbcTemplate().queryForList(FIND_APT_MAPPING, String.class);
		} catch (Exception e)
		{
			return new ArrayList<String>();
		}
	}

	private static final String SAVE_APT_MAPPING = "insert into esite.es_interact_apt_mapping(aptid,mapping,tag)values(?,?,?)";

	@Override
	public long saveAptMapping(final long aptid, String mapping, String tag)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(SAVE_APT_MAPPING, new String[]
				{ "id" });
				ps.setLong(1, aptid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public int deleteMapping(long aptid)
	{
		String sql = "delete from es_interact_apt_mapping where aptid=?";
		return getJdbcTemplate().update(sql, new Object[]
		{ aptid });
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate()
	{
		return jdbcTemplate;
	}

	@Override
	public List<AppointmentDataModel> checkOrderRecord(long aptid, long ownerid, int type, int start, int size)
	{
		String sql = null;
		Object[] param = null;
		/*if (type == 0)
		{
			sql = "select * from es_feature_interact_apt_record a join es_interact_apt e on a.aptid = e.id left join es_sina_user wb on a.entityid = wb.wbuid left join es_hy_user u on a.entityid = u.wbuid and u.owner=? where a.aptid = ? and a.type=0 order by a.createtime desc limit ?,?";
		} else if (type == 1)
		{
			sql = "select * from es_feature_interact_apt_record a join es_interact_apt e on a.aptid = e.id left join es_wx_user wx on a.entityid = wx.id left join es_hy_user u on a.entityid = u.wxuid and u.owner=? where a.aptid = ? and a.type=1 order by a.createtime desc limit ?,?";
		} else if (type == -1)
		{
			sql = "select *,a.name nickname1 from es_feature_interact_apt_record a join es_interact_apt e on a.aptid = e.id left join es_hy_user u on a.entityid = u.id and u.owner=? where a.aptid = ? and a.type =-1 order by a.createtime desc limit ?,?";
		} else if (type == 2)
		{
			sql = "select *,wb.nickname nickname1,wx.nickname wxnickname from es_feature_interact_apt_record a join es_interact_apt e on a.aptid = e.id left join es_hy_user u on a.entityid = u.id and u.owner=? left join es_sina_user wb on u.wbuid = wb.wbuid left join es_wx_user wx on u.wxuid = wx.id where a.aptid = ? and a.type =2 order by a.createtime desc limit ?,?";
		}*/
		sql = "select * from es_feature_interact_apt_record a where a.aptid = ? and a.type =? order by a.createtime desc limit ?,?";
		param = new Object[]
		{aptid,type, start, size };
		return getJdbcTemplate().query(sql, param, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AppointmentDataModel model = new AppointmentDataModel();
				model.setId(rs.getLong("id"));
				model.setAptid(rs.getLong("aptid"));
				model.setWbuid(rs.getLong("entityid"));
				model.setName(rs.getString("name"));
				model.setAge(rs.getInt("age"));
				model.setGender(rs.getString("gender"));
				model.setBirthday(rs.getTimestamp("birthday"));
				model.setAddress(rs.getString("address"));
				model.setCreatetime(rs.getTimestamp("createtime"));
				model.setIdcard(rs.getString("idcard"));
				model.setEmail(rs.getString("email"));
				model.setPhone(rs.getString("phone"));
				model.setDate(rs.getTimestamp("date"));
				model.setTime(rs.getTimestamp("time"));
				model.setIp(rs.getString("ip"));
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
				model.setNickname(rs.getString("name"));
				model.setProvince(rs.getString("province"));
				model.setCity(rs.getString("city"));
				model.setDistrict(rs.getString("district"));
				model.setCode(rs.getString("code"));
				return model;
			}
		});
	}

	@Override
	public int findAptTotal(long aptid, long ownerid, int type)
	{
		String sql = null;
		Object[] param = null;
		/*if (type == 0)
		{
			sql = "select count(*) from es_feature_interact_apt_record a join es_interact_apt e on a.aptid = e.id left join es_sina_user wb on a.entityid = wb.wbuid left join es_hy_user u on a.entityid = u.wbuid and u.owner=? where a.aptid = ? and a.type=0 ";
		} else if (type == 1)
		{
			sql = "select count(*) from es_feature_interact_apt_record a join es_interact_apt e on a.aptid = e.id left join es_wx_user wx on a.entityid = wx.id left join es_hy_user u on a.entityid = u.wxuid and u.owner=? where a.aptid = ? and a.type=1 ";
		} else if (type == -1)
		{
			sql = "select count(*) from es_feature_interact_apt_record a join es_interact_apt e on a.aptid = e.id left join es_hy_user u on a.entityid = u.id and u.owner=? where a.aptid = ? and a.type =-1 ";
		} else if (type == 2)
		{
			sql = "select count(*) from es_feature_interact_apt_record a join es_interact_apt e on a.aptid = e.id left join es_hy_user u on a.entityid = u.id and u.owner=? left join es_sina_user wb on u.wbuid = wb.wbuid left join es_wx_user wx on u.wxuid = wx.id where a.aptid = ? and a.type =2";
		}*/
		sql = "select count(*) from es_feature_interact_apt_record a where a.aptid = ? and a.type =?";
		param = new Object[]
		{ aptid,type };
		return getJdbcTemplate().queryForInt(sql, param);
	}

	private static final String FIND_APPOINTMENT_DATA_MODEL_BY_ID = "select r.* from es_feature_interact_apt_record r join es_interact_apt apt on r.aptid = apt.id  where r.id = ? and apt.ownerid = ?";

	@Override
	public AppointmentDataModel findAppointmentDataModelById(long rid, long ownerid)
	{
		Object[] params =
		{ rid, ownerid };
		List<AppointmentDataModel> list = getJdbcTemplate().query(FIND_APPOINTMENT_DATA_MODEL_BY_ID, params, new AppointmentRecordRowmapper());
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	class AppointmentRecordRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			AppointmentDataModel model = new AppointmentDataModel();
			model.setId(rs.getLong("id"));
			model.setAptid(rs.getLong("aptid"));
			model.setWbuid(rs.getLong("entityid"));
			model.setName(rs.getString("name"));
			model.setAge(rs.getInt("age"));
			model.setGender(rs.getString("gender"));
			model.setBirthday(rs.getTimestamp("birthday"));
			model.setCity(rs.getString("city"));
			model.setProvince(rs.getString("province"));
			model.setDistrict(rs.getString("district"));
			model.setAddress(rs.getString("address"));
			model.setIdcard(rs.getString("idcard"));
			model.setEmail(rs.getString("email"));
			model.setPhone(rs.getString("phone"));
			model.setDate(rs.getTimestamp("date"));
			model.setTime(rs.getTimestamp("time"));
			model.setType(rs.getString("type"));
			model.setSource(rs.getString("source"));
			model.setTerminal(rs.getString("terminal"));
			model.setIp(rs.getString("ip"));
			model.setCreatetime(rs.getTimestamp("createtime"));
			model.setCode(rs.getString("code"));
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

	}

	class AppointmentMappingModelRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			OrderMappingModel m = new OrderMappingModel();
			m.setId(rs.getLong("id"));
			m.setAptid(rs.getLong("aptid"));
			m.setName(rs.getString("name"));
			m.setMapping(rs.getString("mapping"));
			m.setIsshow(rs.getString("isshow"));
			return m;
		}

	}

	class LotteryMapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException
		{
			Lottery lottery = new Lottery();
			lottery.setId(rs.getLong("id"));
			lottery.setName(rs.getString("name"));
			lottery.setType(rs.getString("type"));
			return lottery;
		}
	}

	private static final String FIND_LOTTERY_BY_TYPE = "select es.id,es.name,es.type from esite.es_interact_lottery es where es.owner = ? and type = ? and status !='D' order by es.id desc";

	@Override
	public List<Lottery> findLotteryByType(long ownerid, String type)
	{
		Object[] params =
		{ ownerid, type };
		return getJdbcTemplate().query(FIND_LOTTERY_BY_TYPE, params, new LotteryMapper());
	}

	private static final String FIND_LTTERY_BY_ID = "select es.id,es.name,es.type from esite.es_interact_lottery es where id = ?";

	@Override
	public Lottery findLotteryById(long lotteryid)
	{
		Object[] params =
		{ lotteryid };
		List<Lottery> list = getJdbcTemplate().query(FIND_LTTERY_BY_ID, params, new LotteryMapper());
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	private static final String UPDATE_RULETYPE_BY_LOTTERYID = "update es_interact_lottery set ruletype = 'G' where id = ?";

	@Override
	public int updateRuletypeByLotteryid(long lotteryid)
	{
		Object[] params =
		{ lotteryid };
		return getJdbcTemplate().update(UPDATE_RULETYPE_BY_LOTTERYID, params);
	}

	private static final String FIND_SHOW_CLOUM = "select * from es_interact_apt_mapping where aptid=? and dataShow>0 order by  dataShow asc limit ?";

	@Override
	public List<AptMapping> findShowColum(long aptid, int limit)
	{
		return getJdbcTemplate().query(FIND_SHOW_CLOUM, new Object[]
		{ aptid, limit }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AptMapping am = new AptMapping();
				am.setName(rs.getString("name"));
				am.setMapping(rs.getString("mapping"));
				am.setStype(rs.getString("stype"));
				return am;
			}
		});
	}

	@Override
	public long addAppointment(final long mid, final long ownerid, final String title)
	{
		final String sql = "insert into es_interact_apt(mid,ownerid,title,createtime) values(?,?,?,now())";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				ps.setLong(1, mid);
				ps.setLong(2, ownerid);
				ps.setString(3, title);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public List<AptMapping> findUsedMapping(long aptid, long ownerid)
	{
		return getJdbcTemplate().query("select * from es_interact_apt_mapping where aptid=? and isshow='Y' order by id asc", new Object[]
		{ aptid }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AptMapping am = new AptMapping();
				am.setName(rs.getString("name"));
				am.setMapping(rs.getString("mapping"));
				am.setDataShow(rs.getInt("dataShow"));
				return am;
			}
		});
	}

	@Override
	public int updataDataShow(final long aptid, final List<String> list)
	{
		int[] result = getJdbcTemplate().batchUpdate("update  es_interact_apt_mapping set dataShow=? where aptid=? and mapping=?", new BatchPreparedStatementSetter()
		{

			@Override
			public int getBatchSize()
			{
				return list.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException
			{
				int i = 1;
				String mapping = list.get(index);
				ps.setInt(i++, list.indexOf(mapping) + 1);
				ps.setLong(i++, aptid);
				ps.setString(i++, mapping);
			}
		});
		return result.length;
	}

	@Override
	public void updateDataShowClean(long aptid)
	{
		getJdbcTemplate().update("update esite.es_interact_apt_mapping set dataShow=0 where aptid=?", new Object[]
		{ aptid });
	}

	@Override
	public int findUserJoinTotal(long aptid, long entity, int userTypeWb)
	{
		return getJdbcTemplate().queryForInt("select count(*) from esite.es_feature_interact_apt_record where entityid=? and aptid=? and type=?", new Object[]
		{ entity, aptid, userTypeWb });
	}

	private static final String FIND_APT_RECORD = "select * from es_feature_interact_apt_record where aptid = ? and pageid = ? order by id desc limit ?,? ";

	@Override
	public List<AppointmentRecord> findAptRecord(long aptid, long pageid, int start, int size)
	{
		Object[] params =
		{ aptid, pageid, start, size };
		return getJdbcTemplate().query(FIND_APT_RECORD, params, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AppointmentDataModel model = new AppointmentDataModel();
				model.setId(rs.getLong("id"));
				model.setAptid(rs.getLong("aptid"));
				model.setWbuid(rs.getLong("entityid"));
				model.setName(rs.getString("name"));
				model.setAge(rs.getInt("age"));
				model.setGender(rs.getString("gender"));
				model.setBirthday(rs.getTimestamp("birthday"));
				model.setAddress(rs.getString("address"));
				model.setCreatetime(rs.getTimestamp("createtime"));
				model.setIdcard(rs.getString("idcard"));
				model.setEmail(rs.getString("email"));
				model.setPhone(rs.getString("phone"));
				model.setDate(rs.getTimestamp("date"));
				model.setTime(rs.getTimestamp("time"));
				model.setIp(rs.getString("ip"));
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
				return model;
			}
		});
	}

	private static final String FIND_APT_RECORD_BY_USER = "select * from es_feature_interact_apt_record where aptid = ? and pageid = ? and entityid=? and type=? order by id desc limit ?,? ";

	@Override
	public List<AppointmentRecord> findAptRecordByUser(long aptid, long pageid, long uid, int cd, int start, int size)
	{
		Object[] params =
		{ aptid, pageid, uid, cd, start, size };
		return getJdbcTemplate().query(FIND_APT_RECORD_BY_USER, params, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AppointmentDataModel model = new AppointmentDataModel();
				model.setId(rs.getLong("id"));
				model.setAptid(rs.getLong("aptid"));
				model.setWbuid(rs.getLong("entityid"));
				model.setName(rs.getString("name"));
				model.setAge(rs.getInt("age"));
				model.setGender(rs.getString("gender"));
				model.setBirthday(rs.getTimestamp("birthday"));
				model.setAddress(rs.getString("address"));
				model.setCreatetime(rs.getTimestamp("createtime"));
				model.setIdcard(rs.getString("idcard"));
				model.setEmail(rs.getString("email"));
				model.setPhone(rs.getString("phone"));
				model.setDate(rs.getTimestamp("date"));
				model.setTime(rs.getTimestamp("time"));
				model.setIp(rs.getString("ip"));
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
				return model;
			}
		});
	}

	@Override
	public int updateAptClean(long aptid, long owner)
	{
		String[] sql =
		{ "delete from es_interact_user_data where featureid=118 and hdid=" + aptid,
				"delete r from  es_feature_interact_apt_record  r  join es_interact_apt s on s.id=r.aptid where r.aptid=" + aptid + " and s.ownerid=" + owner, };
		int[] rs = getJdbcTemplate().batchUpdate(sql);
		int result = 0;
		for (int i : rs)
		{
			result = result + i;
		}
		return result;
	}

	private static final String FIND_ALL_CLOUM = "select * from es_interact_apt_mapping where aptid=? and isshow='Y'  order by datashow=0,datashow asc";

	@Override
	public List<AptMapping> findAllColum(long aptid)
	{
		return getJdbcTemplate().query(FIND_ALL_CLOUM, new Object[]
		{ aptid }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AptMapping am = new AptMapping();
				am.setName(rs.getString("name"));
				am.setMapping(rs.getString("mapping"));
				am.setStype(rs.getString("stype"));
				return am;
			}
		});
	}

	@Override
	public List<AppointmentDataModel> findExportData(long aptid, long owmer, int type, String starttime, String endtime)
	{
		String sql = null;
		List<Object> param = new ArrayList<Object>();
		/*if (type == 0)
		{
			sql = "select *,wb.nickname nickname1 from es_feature_interact_apt_record a join es_interact_apt e on a.aptid = e.id left join es_sina_user wb on a.entityid = wb.wbuid left join es_hy_user u on a.entityid = u.wbuid and u.owner=? where a.aptid = ? and a.type=0 ";
		} else if (type == 1)
		{
			sql = "select *,wx.nickname nickname1 from es_feature_interact_apt_record a join es_interact_apt e on a.aptid = e.id left join es_wx_user wx on a.entityid = wx.id left join es_hy_user u on a.entityid = u.wxuid and u.owner=? where a.aptid = ? and a.type=1 ";
		} else if (type == -1)
		{
			sql = "select *,a.name nickname1 from es_feature_interact_apt_record a join es_interact_apt e on a.aptid = e.id left join es_hy_user u on a.entityid = u.id and u.owner=? where a.aptid = ? and a.type =-1 ";
		} else if (type == 2)
		{
			sql = "select *,wb.nickname nickname1,wx.nickname wxnickname from es_feature_interact_apt_record a join es_interact_apt e on a.aptid = e.id left join es_hy_user u on a.entityid = u.id and u.owner=? left join es_sina_user wb on u.wbuid = wb.wbuid left join es_wx_user wx on u.wxuid = wx.id where a.aptid = ? and a.type =2 ";
		}*/
		sql = "select * from es_feature_interact_apt_record a where a.aptid = ? and a.type =? ";
		param.add(aptid);
		param.add(type);
		if (starttime != null && starttime.trim().length() > 0)
		{
			sql += " and a.createtime>? ";
			param.add(starttime);
		}
		if (endtime != null && endtime.trim().length() > 0)
		{
			sql += " and a.createtime<? ";
			param.add(endtime);
		}
		sql += " order by a.createtime desc";
		return getJdbcTemplate().query(sql, param.toArray(), new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AppointmentDataModel model = new AppointmentDataModel();
				model.setId(rs.getLong("id"));
				model.setAptid(rs.getLong("aptid"));
				model.setWbuid(rs.getLong("entityid"));
				model.setName(rs.getString("name"));
				model.setAge(rs.getInt("age"));
				model.setGender(rs.getString("gender"));
				model.setBirthday(rs.getTimestamp("birthday"));
				model.setAddress(rs.getString("address"));
				model.setCreatetime(rs.getTimestamp("createtime"));
				model.setIdcard(rs.getString("idcard"));
				model.setEmail(rs.getString("email"));
				model.setPhone(rs.getString("phone"));
				model.setDate(rs.getTimestamp("date"));
				model.setTime(rs.getTimestamp("time"));
				model.setIp(rs.getString("ip"));
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
				int type = rs.getInt("a.type");
				model.setProvince(rs.getString("province"));
				model.setCity(rs.getString("city"));
				model.setDistrict(rs.getString("district"));
				model.setCode(rs.getString("code"));
				return model;
			}
		});
	}

	@Override
	public int delAptRecord(long rid)
	{
		String sql = "delete from esite.es_feature_interact_apt_record where id = ?";
		Object[] params =
		{ rid };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public long saveSimpleApt(final long ownerid, final String aptname)
	{
		final String sql = "insert into es_interact_apt(mid,ownerid,title,starttime,endtime,createtime) values(?,?,?,now(),DATE_ADD(now(),INTERVAL 1 MONTH),now())";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i = 1;
				ps.setInt(i++, 10000);
				ps.setLong(i++, ownerid);
				ps.setString(i++, aptname);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public int updateCoded(long id, int i)
	{
		return getJdbcTemplate().update("update es_interact_apt set coded=? where id=?", new Object[]
		{ i, id });
	}

	@Override
	public int findAptCodeTotal(long aptid, String code, int status)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(id) from es_interact_apt_code where aptid=? ";
		list.add(aptid);
		if (code != null && code.trim().length() > 0)
		{
			sql += " and code like ?";
			list.add("%" + code + "%");
		}
		if (status != -1)
		{
			sql += " and status = ?";
			list.add(status);
		}
		return getJdbcTemplate().queryForInt(sql, list.toArray());
	}

	@Override
	public List<AptCode> findAptCodeList(long aptid, String code, int status, int start, int size)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from es_interact_apt_code where aptid=? ";
		list.add(aptid);
		if (code != null && code.trim().length() > 0)
		{
			sql += " and code like ?";
			list.add("%" + code + "%");
		}
		if (status != -1)
		{
			sql += " and status = ?";
			list.add(status);
		}
		sql += " limit ?,?";
		list.add(start);
		list.add(size);
		return getJdbcTemplate().query(sql, list.toArray(), new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AptCode code = new AptCode();
				code.setAptid(rs.getLong("aptid"));
				code.setCode(rs.getString("code"));
				code.setStatus(rs.getInt("status"));
				return code;
			}
		});
	}

	@Override
	public int saveAptCode(final long aptid, final List<String> insertList)
	{
		int[] i = jdbcTemplate.batchUpdate("insert ignore into es_interact_apt_code(aptid,code) values(?,?)", new BatchPreparedStatementSetter()
		{

			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException
			{
				int i = 1;
				ps.setLong(i++, aptid);
				ps.setString(i++, insertList.get(index));
			}

			@Override
			public int getBatchSize()
			{
				return insertList.size();
			}
		});
		return i.length;
	}

	@Override
	public int findCode(String code, long aptid,int coded)
	{
		if(coded==1){
			return getJdbcTemplate().queryForInt("select count(id) from esite.es_interact_apt_code where aptid=? and code=? and status=0",new Object[]{aptid,code});
		}else{
			return getJdbcTemplate().queryForInt("select count(id) from esite.es_interact_apt_code where aptid=? and code=?",new Object[]{aptid,code});
			
		}
	}

	@Override
	public int updateCode(String code, long aptid)
	{
		return	getJdbcTemplate().update("update esite.es_interact_apt_code set status=1 where aptid=? and code=?",new Object[]{aptid,code});
	}
	
	
	@Override
	public int delAptRecordByuid(long aptid, long uid)
	{
		return jdbcTemplate.update("delete from es_feature_interact_apt_record where aptid=? and entityid=? and type=1", new Object[]{aptid,uid});
	}
	
	@Override
	public long findRecordIdByUid(long id, long uid)
	{
		List<Long> list=getJdbcTemplate().query("select id from es_feature_interact_apt_record where aptid=? and entityid=? and type=1", new Object[]{id,uid}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("id");
			}
		});
		return  list.size()>0?list.get(0):0;
	}
	
	@Override
	public AppointmentDataModel findAptRecordByUser(long id, long uid, int type)
	{
		Object[] params ={ id, uid, type};
		List<AppointmentDataModel> list= getJdbcTemplate().query("select * from es_feature_interact_apt_record where aptid=? and entityid=? and type=? order by id desc", params, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AppointmentDataModel model = new AppointmentDataModel();
				model.setId(rs.getLong("id"));
				model.setAptid(rs.getLong("aptid"));
				model.setWbuid(rs.getLong("entityid"));
				model.setName(rs.getString("name"));
				model.setAge(rs.getInt("age"));
				model.setGender(rs.getString("gender"));
				model.setBirthday(rs.getTimestamp("birthday"));
				model.setAddress(rs.getString("address"));
				model.setCreatetime(rs.getTimestamp("createtime"));
				model.setIdcard(rs.getString("idcard"));
				model.setEmail(rs.getString("email"));
				model.setPhone(rs.getString("phone"));
				model.setDate(rs.getTimestamp("date"));
				model.setTime(rs.getTimestamp("time"));
				model.setIp(rs.getString("ip"));
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
				return model;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	
	@Override
	public void updateAptRecord(long uid, AppointmentRecord apt, String ip, String terminal, String source, int type)
	{

		getJdbcTemplate().update(
				"update esite.es_feature_interact_apt_record  set code=?,pageid=?,entityid=?,aptid=?,name=?,age=?, gender=?,birthday=?,address=?,idcard=?,email=?,phone=?,date=?,time=?,type=?,f1=?,f2=?,f3=?,f4=?,f5=?,f6=?,f7=?,f8=?,f9=?,f10=?,f11=?,f12=?,f13=?,f14=?,f15=?,f16=?,f17=?,f18=?,f19=?,f20=?,ip=?,terminal=?,source=?,createtime=now(),province=?,city=?,district=?,f21=?,f22=?,f23=?,f24=?,f25=?,f26=?,f27=?,f28=?,f29=?,f30=? where id=?",
				new Object[]
				{ apt.getCode(),apt.getPageid(), uid, apt.getAptid(), apt.getName(), apt.getAge(), apt.getGender(), apt.getBirthday(), apt.getAddress(), apt.getIdcard(), apt.getEmail(),
						apt.getPhone(), apt.getDate(), apt.getTime(), type, apt.getF1(), apt.getF2(), apt.getF3(), apt.getF4(), apt.getF5(), apt.getF6(), apt.getF7(), apt.getF8(),
						apt.getF9(), apt.getF10(), apt.getF11(), apt.getF12(), apt.getF13(), apt.getF14(), apt.getF15(), apt.getF16(), apt.getF17(), apt.getF18(), apt.getF19(),
						apt.getF20(), ip, terminal, source, apt.getProvince(), apt.getCity(), apt.getDistrict(), apt.getF21(), apt.getF22(), apt.getF23(), apt.getF24(),
						apt.getF25(), apt.getF26(), apt.getF27(), apt.getF28(), apt.getF29(), apt.getF30(),apt.getId() });
	}
	
	
	private static final String FIND_APPOINTMENT_DATA_MODEL_BY_WXUID = "select r.* from es_feature_interact_apt_record r join es_interact_apt apt on r.aptid = apt.id  where r.entityid = ? and r.type=1 and apt.ownerid = ? and apt.id=?";
	@Override
	public AppointmentDataModel findAppointmentDataByWxuid(long wxuid, long aptid, long ownerid)
	{
		Object[] params =
		{ wxuid, ownerid ,aptid };
		List<AppointmentDataModel> list = getJdbcTemplate().query(FIND_APPOINTMENT_DATA_MODEL_BY_WXUID, params, new AppointmentRecordRowmapper());
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public int updateAptRecord(AppointmentDataModel apt)
	{
		return getJdbcTemplate().update(
						"update esite.es_feature_interact_apt_record  set code=?,name=?,age=?, gender=?,birthday=?,address=?,idcard=?,email=?,phone=?,date=?,time=?,f1=?,f2=?,f3=?,f4=?,f5=?,f6=?,f7=?,f8=?,f9=?,f10=?,f11=?,f12=?,f13=?,f14=?,f15=?,f16=?,f17=?,f18=?,f19=?,f20=?,province=?,city=?,district=?,f21=?,f22=?,f23=?,f24=?,f25=?,f26=?,f27=?,f28=?,f29=?,f30=? where id=?",
						new Object[]
						{ apt.getCode(), apt.getName(), apt.getAge(), apt.getGender(), apt.getBirthday(), apt.getAddress(), apt.getIdcard(), apt.getEmail(),
								apt.getPhone(), apt.getDate(), apt.getTime(), apt.getF1(), apt.getF2(), apt.getF3(), apt.getF4(), apt.getF5(), apt.getF6(), apt.getF7(), apt.getF8(),
								apt.getF9(), apt.getF10(), apt.getF11(), apt.getF12(), apt.getF13(), apt.getF14(), apt.getF15(), apt.getF16(), apt.getF17(), apt.getF18(), apt.getF19(),
								apt.getF20(), apt.getProvince(), apt.getCity(), apt.getDistrict(), apt.getF21(), apt.getF22(), apt.getF23(), apt.getF24(),
								apt.getF25(), apt.getF26(), apt.getF27(), apt.getF28(), apt.getF29(), apt.getF30(),apt.getId() });
			
	}

}
