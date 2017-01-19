package com.huiyee.weixin.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.weixin.dao.IPayShopOwnerDao;
import com.huiyee.weixin.model.Wkq;


public class PayShopOwnerDao implements IPayShopOwnerDao
{
private JdbcTemplate jdbcTemplate;

public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
{
	this.jdbcTemplate = jdbcTemplate;
}

@Override
public Wkq findWkq(long owner,int type)
{
	String sql = "";
	if(type==1){
		sql = "select * from es_pay_shop where owner=?";
	}else{
		sql = "select * from es_pay_jf_shop where owner=?";
	}
	List<Wkq> ls=jdbcTemplate.query(sql, new Object[]{owner}, new RowMapper(){

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			Wkq w=new Wkq();
			w.setFpage(rs.getLong("fpage"));
			w.setId(rs.getLong("id"));
			w.setOwner(rs.getLong("owner"));
			w.setSpage(rs.getLong("spage"));
			w.setYzpage(rs.getLong("yzpage"));
			w.setBili(rs.getInt("bili"));
			return w;
		}
		
	});
	if(ls!=null&&ls.size()>0){
		return ls.get(0);
	}
	return null;
}

}
