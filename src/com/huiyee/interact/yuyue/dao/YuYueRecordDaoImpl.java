package com.huiyee.interact.yuyue.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.yuyue.model.YuYueSSTimeUsed;


public class YuYueRecordDaoImpl implements IYuYueRecordDao
{
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public long saveInteractAptRecord(long wxuid, AppointmentDataModel apt, int type,long pageid)
	{
//		String sql = "insert into esite.es_feature_interact_apt_record(pageid,entityid,aptid,name,gender,phone,type,createtime) values (?,?,?,?,?,?,?,now())";
//		return jdbcTemplate.update(sql,new Object[]{pageid, wxuid, apt.getAptid(), apt.getName(),  apt.getGender(), apt.getPhone(), type});
		String sql = "insert into esite.es_feature_interact_apt_record(code,pageid,entityid,aptid,name,age, gender,birthday,address,idcard,email,phone,date,time,type,f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14,f15,f16,f17,f18,f19,f20,ip,terminal,source,createtime,province,city,district,f21,f22,f23,f24,f25,f26,f27,f28,f29,f30) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,new Object[]
				{ apt.getCode(),pageid, wxuid, apt.getAptid(), apt.getName(), apt.getAge(), apt.getGender(), apt.getBirthday(), apt.getAddress(), apt.getIdcard(), apt.getEmail(),
						apt.getPhone(), apt.getDate(), apt.getTime(), type, apt.getF1(), apt.getF2(), apt.getF3(), apt.getF4(), apt.getF5(), apt.getF6(), apt.getF7(), apt.getF8(),
						apt.getF9(), apt.getF10(), apt.getF11(), apt.getF12(), apt.getF13(), apt.getF14(), apt.getF15(), apt.getF16(), apt.getF17(), apt.getF18(), apt.getF19(),
						apt.getF20(), apt.getIp(), apt.getTerminal(), apt.getSource(), apt.getProvince(), apt.getCity(), apt.getDistrict(), apt.getF21(), apt.getF22(), apt.getF23(), apt.getF24(),
						apt.getF25(), apt.getF26(), apt.getF27(), apt.getF28(), apt.getF29(), apt.getF30() });
	
	}

	@Override
	public int updateInteractAptRecord(long wxuid, AppointmentDataModel apt, int type, long id)
	{
//		String sql = "update es_feature_interact_apt_record set entityid = ?,aptid = ?,name = ?,gender = ?,phone = ?,type = ? where id = ?";
//		Object[] params = {wxuid,apt.getAptid(), apt.getName(),  apt.getGender(), apt.getPhone(), type,id};
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
	public YuYueSSTimeUsed findYuYueTimeUsed(int dateday,long stid, long ssid, long owner)
	{
		String sql = "select * from es_yu_yue_ss_time_used where dateday = ? and stid = ? and ssid = ? and owner = ?";
		Object[] params = {dateday,stid,ssid,owner};
		List<YuYueSSTimeUsed> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueSSTimeUsed u = new YuYueSSTimeUsed();
				u.setId(rs.getLong("id"));
				u.setUsed(rs.getInt("used"));
				return u;
			}
			
		});
		if(list!= null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public long saveYuYueTimeUsed(int dateday, long stid, long ssid, long owner)
	{
		String sql = "insert into es_yu_yue_ss_time_used (dateday,stid,ssid,used,owner) values(?,?,?,?,?)";
		Object[] params = {dateday,stid,ssid,1,owner};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateYuYueTimeUsedById(long id)
	{
		String sql = "update es_yu_yue_ss_time_used set used = used + 1 where id = ?";
		Object[] params = {id};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int saveYuYueRecord(long wxuid, String ip, long stid, Date yytime, String hydesc,String servicename,String sername,long serid,long serviceid,long catid,String catname,String tag1,String tag2)
	{
		String sql = "insert into es_yu_yue_record(wxuid,ip,stid,yytime,hydesc,createtime,servicename,sername,servicerid,serviceid,catid,catname,tag1,tag2) values(?,?,?,?,?,now(),?,?,?,?,?,?,?,?) on duplicate key update yytime = ?,hydesc=?";
		Object[] params = {wxuid,ip,stid,yytime,hydesc,servicename,sername,serid,serviceid,catid,catname,tag1,tag2,yytime,hydesc};
		return jdbcTemplate.update(sql, params);
	}
	
	
}
