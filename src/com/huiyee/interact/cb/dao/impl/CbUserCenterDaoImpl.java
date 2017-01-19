package com.huiyee.interact.cb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.cb.dao.ICbUserCenterDao;
import com.huiyee.interact.cb.model.CbHbRecord;
import com.huiyee.interact.cb.model.CbSender;
import com.huiyee.interact.cb.model.InteractCb;

public class CbUserCenterDaoImpl implements ICbUserCenterDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public CbSender findCbSenderByHyuid(long hyuid) {
		String sql = "select * from es_interact_cb_sender s where s.hyuid=? ";
		Object[] params = {hyuid};
		List<CbSender> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				CbSender sender = new CbSender();
				sender.setId(rs.getLong("s.id"));
				sender.setHyuid(rs.getLong("s.hyuid"));
				sender.setWxuid(rs.getLong("s.wxuid"));
				sender.setAptid(rs.getLong("s.aptid"));
				sender.setRecordid(rs.getLong("s.recordid"));
				sender.setStatus(rs.getString("s.status"));
				sender.setOwner(rs.getLong("s.owner"));
				sender.setHbtotal(rs.getInt("s.hbtotal"));
				sender.setHbused(rs.getInt("s.hbused"));
				return sender;
			}
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<CbHbRecord> findHbCbSendByHyuid(long hyuid) {
		String sql = "select r.createtime from crm.crm_wx_hongbao_cb_send r join es_hy_user u on r.hyuid = u.id where r.hyuid = ? group by date(r.createtime) order by r.createtime desc ";
		Object[] params = {hyuid};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				CbHbRecord r = new CbHbRecord();
				r.setCreatetime(rs.getTimestamp("r.createtime"));
				return r;
			}
			
		});
	}
	
	@Override
	public List<CbHbRecord> findHbCbSendByHyuidAndTime(long hyuid,Date createtime){
		String sql = "select * from crm.crm_wx_hongbao_cb_send r join es_hy_user u on r.hyuid = u.id where r.hyuid = ? and date(r.createtime) = date(?) order by r.createtime desc ";
		Object[] params = {hyuid,createtime};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				CbHbRecord r = new CbHbRecord();
				r.setId(rs.getLong("r.id"));
				r.setNum(rs.getInt("r.rmb"));
				r.setSenderName(rs.getString("u.nickname"));
				r.setCreatetime(rs.getTimestamp("r.createtime"));
				r.setStatus(rs.getInt("r.status"));
				return r;
			}
			
		});
	}

	@Override
	public List<CbSender> findMySender(long hyuid) {
		String sql = "select * from es_interact_cb_sender s join es_hy_user u on s.hyuid=u.id left join es_wx_user wx on s.wxuid = wx.id where s.fatherid = ? order by s.id desc";
		Object[] params = {hyuid};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				CbSender sender = new CbSender();
				sender.setId(rs.getLong("s.id"));
				sender.setHyuid(rs.getLong("s.hyuid"));
				sender.setWxuid(rs.getLong("s.wxuid"));
				sender.setAptid(rs.getLong("s.aptid"));
				sender.setRecordid(rs.getLong("s.recordid"));
				sender.setStatus(rs.getString("s.status"));
				sender.setOwner(rs.getLong("s.owner"));
				sender.setHbtotal(rs.getInt("s.hbtotal"));
				sender.setHbused(rs.getInt("s.hbused"));
				AppointmentDataModel record = new AppointmentDataModel();
				record.setName(rs.getString("u.nickname"));
				sender.setRecord(record);
				WxUser wxUser = new WxUser();
				wxUser.setHeadimgurl(rs.getString("wx.headimgurl"));
				wxUser.setNickname(rs.getString("wx.nickname"));
				sender.setWxUser(wxUser);
				return sender;
			}
			
		});
	}
	
	@Override
	public List<CbSender> findSenderlist(long owner) {
		String sql = "select * from es_interact_cb_sender s join es_hy_user u on s.hyuid=u.id left join es_wx_user wx on s.wxuid = wx.id where s.owner = ? order by s.hbtotal desc limit 10";
		Object[] params = {owner};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				CbSender sender = new CbSender();
				sender.setId(rs.getLong("s.id"));
				sender.setHyuid(rs.getLong("s.hyuid"));
				sender.setWxuid(rs.getLong("s.wxuid"));
				sender.setAptid(rs.getLong("s.aptid"));
				sender.setRecordid(rs.getLong("s.recordid"));
				sender.setStatus(rs.getString("s.status"));
				sender.setOwner(rs.getLong("s.owner"));
				sender.setHbtotal(rs.getInt("s.hbtotal"));
				sender.setHbused(rs.getInt("s.hbused"));
				AppointmentDataModel m = new AppointmentDataModel();
				m.setName(rs.getString("u.nickname"));
				WxUser wxUser = new WxUser();
				wxUser.setHeadimgurl(rs.getString("wx.headimgurl"));
				wxUser.setNickname(rs.getString("wx.nickname"));
				sender.setWxUser(wxUser);
				sender.setRecord(m);
				return sender;
			}
			
		});
	}

	@Override
	public InteractCb findCbByOwner(long owner) {
		String sql = "select * from es_interact_cb where owner = ?";
		Object[] params = {owner};
		List<InteractCb> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				InteractCb cb = new InteractCb();
				cb.setId(rs.getLong("id"));
				cb.setTitle(rs.getString("title"));
				cb.setOwner(rs.getLong("owner"));
				cb.setAptid(rs.getLong("aptid"));
				cb.setSiteid(rs.getLong("siteid"));
				cb.setSitegroupid(rs.getLong("sitegroupid"));
				cb.setCreatetime(rs.getTimestamp("createtime"));
				cb.setWishing(rs.getString("wishing"));
				cb.setAct_name(rs.getString("act_name"));
				cb.setRemark(rs.getString("remark"));
				cb.setSpageid(rs.getLong("spageid"));
				cb.setNpageid(rs.getLong("npageid"));
				cb.setRpageid(rs.getLong("rpageid"));
				cb.setYpageid(rs.getLong("ypageid"));
				cb.setPizhun(rs.getInt("pizhun"));
				return cb;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int saveCbHongbaoSend(long owner, long hyuid, String openid, long mpid, int rmb) {
		String sql = "insert into crm.crm_wx_hongbao_cb_send(owner, hyuid, openid, mpid, rmb,createtime) values(?,?,?,?,?,now())";
		Object[] params = {owner, hyuid, openid, mpid, rmb};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int saveCbSender(long hyuid, long wxuid, long aptid, long recordid, String status, long owner,long fatherid) {
		String sql = "insert into es_interact_cb_sender(hyuid, wxuid, aptid, recordid, status, owner, fatherid) values(?,?,?,?,?,?,?)";
		Object[] params = {hyuid, wxuid, aptid, recordid, status, owner, fatherid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public AppointmentDataModel findAptRecordByWxuid(long aptid, long uid, int cd) {
		String sql = "select * from es_feature_interact_apt_record where aptid = ? and entityid=? and type=?";
		Object[] params = { aptid, uid, cd};
		List<AppointmentDataModel> list = jdbcTemplate.query(sql, params, new AppointmentRecordRowmapper());
		if(list != null && list.size()>0){
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
			return model;
		}

	}

	@Override
	public int updateInteractAptRecord(long wxuid, AppointmentDataModel apt, int type, long id) {
		return jdbcTemplate.update(
				"update esite.es_feature_interact_apt_record  set code=?,pageid=?,entityid=?,aptid=?,name=?,age=?, gender=?,birthday=?,address=?,idcard=?,email=?,phone=?,date=?,time=?,type=?,f1=?,f2=?,f3=?,f4=?,f5=?,f6=?,f7=?,f8=?,f9=?,f10=?,f11=?,f12=?,f13=?,f14=?,f15=?,f16=?,f17=?,f18=?,f19=?,f20=?,ip=?,terminal=?,source=?,createtime=now(),province=?,city=?,district=?,f21=?,f22=?,f23=?,f24=?,f25=?,f26=?,f27=?,f28=?,f29=?,f30=? where id=?",
				new Object[]
				{ apt.getCode(),apt.getPageid(), wxuid, apt.getAptid(), apt.getName(), apt.getAge(), apt.getGender(), apt.getBirthday(), apt.getAddress(), apt.getIdcard(), apt.getEmail(),
						apt.getPhone(), apt.getDate(), apt.getTime(), type, apt.getF1(), apt.getF2(), apt.getF3(), apt.getF4(), apt.getF5(), apt.getF6(), apt.getF7(), apt.getF8(),
						apt.getF9(), apt.getF10(), apt.getF11(), apt.getF12(), apt.getF13(), apt.getF14(), apt.getF15(), apt.getF16(), apt.getF17(), apt.getF18(), apt.getF19(),
						apt.getF20(), apt.getIp(), apt.getTerminal(), apt.getSource(), apt.getProvince(), apt.getCity(), apt.getDistrict(), apt.getF21(), apt.getF22(), apt.getF23(), apt.getF24(),
						apt.getF25(), apt.getF26(), apt.getF27(), apt.getF28(), apt.getF29(), apt.getF30(),id });
		
	}

	@Override
	public long saveInteractAptRecord(final long wxuid,final AppointmentDataModel apt,final int type) {
		final String sql = "insert into esite.es_feature_interact_apt_record(code,pageid,entityid,aptid,name,age, gender,birthday,address,idcard,email,phone,date,time,type,f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14,f15,f16,f17,f18,f19,f20,ip,terminal,source,createtime,province,city,district,f21,f22,f23,f24,f25,f26,f27,f28,f29,f30) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?,?,?,?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql,
						new String[] { "id" });
				int i=1;
				ps.setString(i++, apt.getCode());
				ps.setLong(i++, apt.getPageid());
				ps.setLong(i++,wxuid );
				ps.setLong(i++, apt.getAptid());
				ps.setString(i++, apt.getName());
				ps.setInt(i++,  apt.getAge());
				ps.setString(i++, apt.getGender());
				if(apt.getBirthday() != null){
					ps.setTimestamp(i++, new Timestamp(apt.getBirthday().getTime()));
				}else{
					ps.setTimestamp(i++, null);
				}
				ps.setString(i++, apt.getAddress());
				ps.setString(i++, apt.getIdcard());
				ps.setString(i++, apt.getEmail());
				ps.setString(i++, apt.getPhone());
				if(apt.getDate() != null){
					ps.setTimestamp(i++, new Timestamp(apt.getDate().getTime()));
				}else{
					ps.setTimestamp(i++, null);
				}
				if(apt.getTime() != null){
					ps.setTimestamp(i++, new Timestamp(apt.getTime().getTime()));
				}else{
					ps.setTimestamp(i++, null);
				}
				ps.setInt(i++, type);
				ps.setString(i++, apt.getF1());
				ps.setString(i++, apt.getF2());
				ps.setString(i++, apt.getF3());
				ps.setString(i++, apt.getF4());
				ps.setString(i++, apt.getF5());
				ps.setString(i++, apt.getF6());
				ps.setString(i++, apt.getF7());
				ps.setString(i++, apt.getF8());
				ps.setString(i++, apt.getF9());
				ps.setString(i++, apt.getF10());
				ps.setString(i++, apt.getF11());
				ps.setString(i++, apt.getF12());
				ps.setString(i++, apt.getF13());
				ps.setString(i++, apt.getF14());
				ps.setString(i++, apt.getF15());
				ps.setString(i++, apt.getF16());
				ps.setString(i++, apt.getF17());
				ps.setString(i++, apt.getF18());
				ps.setString(i++, apt.getF19());
				ps.setString(i++, apt.getF20());
				ps.setString(i++, apt.getIp());
				ps.setString(i++, apt.getTerminal());
				ps.setString(i++, apt.getSource());
				ps.setString(i++, apt.getProvince());
				ps.setString(i++, apt.getCity());
				ps.setString(i++, apt.getDistrict());
				ps.setString(i++, apt.getF21());
				ps.setString(i++, apt.getF22());
				ps.setString(i++, apt.getF23());
				ps.setString(i++, apt.getF24());
				ps.setString(i++, apt.getF25());
				ps.setString(i++, apt.getF26());
				ps.setString(i++, apt.getF27());
				ps.setString(i++, apt.getF28());
				ps.setString(i++, apt.getF29());
				ps.setString(i++, apt.getF30());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public void updateCbUsed(long hyuid, int rmb)
	{
		String sql = "update es_interact_cb_sender set hbused = hbused + ? where hyuid = ?";
		Object[] params = {rmb,hyuid};
		 jdbcTemplate.update(sql, params);
	}
	
}
