package com.huiyee.individual.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.individual.model.SecurityCodeRecord;

public class SecurityCodeRecordDaoImpl implements ISecurityCodeRecordDao
{

	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int updateSecurityCodeRecordPayed(long id,int payed)
	{
		String sql="update es_feature_security_code_record set payed=? where id=?";
		return getJdbcTemplate().update(sql,new Object[]{payed,id});
	}

	@Override
	public int updateSecurityCodeRecordStatus(long id,String status)
	{
		String sql="update es_feature_security_code_record set status=? where id=?";
		return getJdbcTemplate().update(sql,new Object[]{status,id});
	}

	public JdbcTemplate getJdbcTemplate()
	{
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int saveUser(long pageid, long entityid, int type, String code1, String code2, String code3, String code4, String phone, String address)
	{
		String sql = "insert into es_feature_security_code_record(pageid,entityid,type,code1,code2,code3,code4,phone,address,createtime) values(?,?,?,?,?,?,?,?,?,now())";
		return jdbcTemplate.update(sql, new Object[]{pageid, entityid, type, code1, code2, code3, code4, phone, address});
	}
	
	@Override
	public SecurityCodeRecord findbyUser(long entityid,int type)
	{
		String sql = "select * from es_feature_security_code_record  where entityid=? and type=?";
		List<SecurityCodeRecord> list = jdbcTemplate.query(sql, new Object[]{ entityid,type }, new ScrRowmapper());
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}
	
	class ScrRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			SecurityCodeRecord scr = new SecurityCodeRecord();
			scr.setId(rs.getLong("id"));
			scr.setType(rs.getInt("type"));
			scr.setCode1(rs.getString("code1"));
			scr.setCode2(rs.getString("code2"));
			scr.setCode3(rs.getString("code3"));
			scr.setCode4(rs.getString("code4"));
			scr.setPhone(rs.getString("phone"));
			scr.setAddress(rs.getString("address"));
			scr.setStatus(rs.getString("status"));
			scr.setEntityid(rs.getLong("entityid"));
			scr.setCreatetime(rs.getTimestamp("createtime"));
			scr.setUpdatetime(rs.getTimestamp("updatetime"));
			scr.setPayed(rs.getInt("payed"));
			return scr;
		}

	}

	@Override
	public int updateUser(String code1, String code2, String code3, String code4,long entityid,String phone,String address)
	{
		String sql = "update  es_feature_security_code_record set code1=?,code2=?,code3=?,code4=?,phone=?,address=?,status='EDT' where entityid=?";
		return jdbcTemplate.update(sql, new Object[]{ code1,code2,code3,code4,phone,address,entityid });
	}

	@Override
	public int updateUser(String code1, String code2,long entityid,int num,SecurityCodeRecord scr,String phone,String address)
	{	
		String sql;
		if(num == 2 && scr.getCode2() !=null && scr.getCode4()==null){
			System.out.println("有记录都替换34");
			sql = "update  es_feature_security_code_record set code3=?,code4=?,phone=?,address=?,status='EDT' where entityid=?";
		}else{
			System.out.println("有记录都替换12");
			sql = "update  es_feature_security_code_record set code1=?,code2=?,phone=?,address=?,status='EDT' where entityid=?";
		}
		return jdbcTemplate.update(sql, new Object[]{ code1,code2,phone,address,entityid });
	}
	
	@Override  
	public List<SecurityCodeRecord> findInfo(String edt,String cmp,String fld, String starttime, String endtime,String submitstarttime,String submitendtime)
	{
		if(edt==null && cmp==null && fld==null && "".equals(starttime) && "".equals(endtime) && "".equals(submitstarttime) && "".equals(submitendtime)){
			String sql="select * from es_feature_security_code_record  order by updatetime desc ";
			return getJdbcTemplate().query(sql, new ScrRowmapper());
		}
		if(edt==null && cmp==null && fld==null){
			String sql="select * from es_feature_security_code_record where (createtime>? and createtime<?) or (updatetime>? and updatetime<?) order by updatetime desc ";
			return getJdbcTemplate().query(sql, new Object[]{submitstarttime,submitendtime,starttime,endtime},new ScrRowmapper());
		}
		if("".equals(starttime) && "".equals(endtime) && "".equals(submitstarttime) && "".equals(submitendtime)){
			String sql="select * from es_feature_security_code_record where status=? or status=? or status=?";
			return getJdbcTemplate().query(sql, new Object[]{edt,cmp,fld},new ScrRowmapper());
		}
		if("".equals(starttime) && "".equals(endtime)){
			String sql="select * from es_feature_security_code_record where (createtime>? and createtime<?)  and (status=? or status=? or status=?) order by updatetime desc";
			return getJdbcTemplate().query(sql, new Object[]{submitstarttime,submitendtime,edt,cmp,fld},new ScrRowmapper());
		}
		if("".equals(submitstarttime) && "".equals(submitendtime)){
			String sql="select * from es_feature_security_code_record where (updatetime>? and updatetime<?)  and (status=? or status=? or status=?) order by updatetime desc";
			return getJdbcTemplate().query(sql, new Object[]{starttime,endtime,edt,cmp,fld},new ScrRowmapper());
		}
		else{
			String sql="select * from es_feature_security_code_record where ((createtime>? and createtime<?) or (updatetime>? and updatetime<?)) and (status=? or status=? or status=?) order by updatetime desc  ";
			return getJdbcTemplate().query(sql, new Object[]{submitstarttime,submitendtime,starttime,endtime,edt,cmp,fld},new ScrRowmapper());
		}
	}

}
