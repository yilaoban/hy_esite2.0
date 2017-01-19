package com.huiyee.interact.yuyue.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.yuyue.model.YuYueCatalog;


public class YuYueFormDaoImpl implements IYuYueFormDao
{
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public AppointmentDataModel findAptRecordByWxuid(long aptid, long uid, int cd)
	{
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

	@Override
	public List<YuYueCatalog> findYuYueCatalog(long owner,String type)
	{
		String sql = "select * from es_yu_yue_catalog c join es_yu_yue y on c.yyid = y.id where y.owner = ? and c.type = ? order by c.idx desc";
		Object[] params = {owner,type};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueCatalog c = new YuYueCatalog();
				c.setId(rs.getLong("c.id"));
				c.setName(rs.getString("c.name"));
				c.setHydesc(rs.getString("c.hydesc"));
				c.setHyldesc(rs.getString("c.hyldesc"));
				c.setYyid(rs.getLong("c.yyid"));
				c.setImg(rs.getString("c.img"));
				c.setSpageid(rs.getLong("c.spageid"));
				c.setXpageid(rs.getLong("c.xpageid"));
				return c;
			}
			
		});
	}

}
