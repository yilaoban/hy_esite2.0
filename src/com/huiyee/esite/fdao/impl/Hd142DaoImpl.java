package com.huiyee.esite.fdao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.IHd142Dao;
import com.huiyee.esite.model.SecurityCodeModel;

public class Hd142DaoImpl extends AbstractDao implements IHd142Dao{

	private static final String SAVE_SECURITY_CODE_CHECK="insert into es_feature_security_code_check(pageid,createtime) values (?,now())";
	@Override
	public long saveSecurityCodeCheck(final long pageid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
                PreparedStatement ps = arg0.prepareStatement(SAVE_SECURITY_CODE_CHECK, new String[] { "id" });
                int i = 1;
                ps.setLong(i++, pageid);
                return ps;
            }
        }, keyHolder);
        long id = keyHolder.getKey().intValue();
        return id;
	}
	
	private static final String FIND_SERCURITY_CODE_CHECKED_LIST="select cr.*,wu.openid from es_feature_security_code_check cc join es_feature_security_code_record cr on cc.pageid = cr.pageid left outer join es_wx_user wu on cr.entityid = wu.id where cc.id = ? order by cr.id desc";
	@Override
	public List<SecurityCodeModel> findSecurityCodeCheckedList(long fid) {
		Object[] params={fid};
		return getJdbcTemplate().query(FIND_SERCURITY_CODE_CHECKED_LIST, params, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				SecurityCodeModel scm = new SecurityCodeModel();
				scm.setId(rs.getLong("id"));
				scm.setPageid(rs.getLong("pageid"));
				scm.setEntityid(rs.getLong("entityid"));
				scm.setType(rs.getString("type"));
				scm.setCode1(rs.getString("code1"));
				scm.setCode2(rs.getString("code2"));
				scm.setCode3(rs.getString("code3"));
				scm.setCode4(rs.getString("code4"));
				scm.setPhone(rs.getString("phone"));
				scm.setAddress(rs.getString("address"));
				scm.setStatus(rs.getString("status"));
				scm.setPayed(rs.getInt("payed"));
				scm.setCreatetime(rs.getTimestamp("createtime"));
				scm.setUpdatetime(rs.getTimestamp("updatetime"));
				scm.setOpenid(rs.getString("openid"));
				return scm;
			}
			
		});
	}
	
	private static final String FIND_SECURITYCODE_RECORD="select * from es_feature_security_code_record where pageid = ? and entityid = ? and type = ?";
	@Override
	public SecurityCodeModel findSecurityCodeRecord(long pageid,long entityid, int type) {
		Object[] params={pageid,entityid,type};
		List<SecurityCodeModel> list = getJdbcTemplate().query(FIND_SECURITYCODE_RECORD, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				SecurityCodeModel scm = new SecurityCodeModel();
				scm.setId(rs.getLong("id"));
				scm.setPageid(rs.getLong("pageid"));
				scm.setEntityid(rs.getLong("entityid"));
				scm.setType(rs.getString("type"));
				scm.setCode1(rs.getString("code1"));
				scm.setCode2(rs.getString("code2"));
				scm.setCode3(rs.getString("code3"));
				scm.setCode4(rs.getString("code4"));
				scm.setPhone(rs.getString("phone"));
				scm.setAddress(rs.getString("address"));
				scm.setStatus(rs.getString("status"));
				scm.setPayed(rs.getInt("payed"));
				scm.setCreatetime(rs.getTimestamp("createtime"));
				scm.setUpdatetime(rs.getTimestamp("updatetime"));
				return scm;
			}
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<SecurityCodeModel> findSecurityCodeRecord(long pageId, String startTime, String endTime)
	{	
		List<Object> params=new ArrayList<Object>();
		String sql="select * from esite.es_feature_security_code_record where pageid=? ";
		params.add(pageId);
		if(startTime!=null){
			sql+=" and createtime>? ";
			params.add(startTime);
		}
		if(endTime!=null){
			sql+=" and createtime<? ";
			params.add(endTime);
		}
		return getJdbcTemplate().query(sql, params.toArray(), new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				SecurityCodeModel scm = new SecurityCodeModel();
				scm.setId(rs.getLong("id"));
				scm.setPageid(rs.getLong("pageid"));
				scm.setEntityid(rs.getLong("entityid"));
				scm.setType(rs.getString("type"));
				scm.setCode1(rs.getString("code1"));
				scm.setCode2(rs.getString("code2"));
				scm.setCode3(rs.getString("code3"));
				scm.setCode4(rs.getString("code4"));
				scm.setPhone(rs.getString("phone"));
				scm.setAddress(rs.getString("address"));
				scm.setStatus(rs.getString("status"));
				scm.setPayed(rs.getInt("payed"));
				scm.setCreatetime(rs.getTimestamp("createtime"));
				scm.setUpdatetime(rs.getTimestamp("updatetime"));
				return scm;
			}
		});
	}
	
}
