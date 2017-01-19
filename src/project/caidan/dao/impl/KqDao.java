
package project.caidan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import project.caidan.dao.IKqDao;
import project.caidan.dto.DailiCpz;
import project.caidan.dto.ProductRMB;

public class KqDao implements IKqDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public DailiCpz findChannelByWxuid(long wxuid)
	{
		List<DailiCpz> ls=jdbcTemplate.query("select * from caidan.cd_account_cpz where wxuid=?", new Object[]{wxuid}, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				DailiCpz dc=new DailiCpz();
				dc.setClid(rs.getLong("clid"));
				dc.setFawxuid(rs.getLong("fawxuid"));
				dc.setWxuid(rs.getLong("wxuid"));
				return dc;
			}
			
		});
		if(ls!=null&&ls.size()>0){
			return ls.get(0);
		}
		return null;
	}

	@Override
	public int findProductChannel(long pid, long clid)
	{
		return jdbcTemplate.queryForInt("select count(id) from caidan.cd_product_channel where pid=? and clid=?",new Object[]{pid,clid});
	}

	@Override
	public ProductRMB findPRMB(long pid)
	{
		List<ProductRMB> ls=jdbcTemplate.query("select * from caidan.cd_product_rmb where pid=?", new Object[]{pid}, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ProductRMB dc=new ProductRMB();
				dc.setXjrmb(rs.getInt("xjrmb"));
				dc.setZdrmb(rs.getInt("zdrmb"));
				dc.setPid(rs.getLong("pid"));
				dc.setRmb(rs.getInt("rmb"));
				dc.setEndtime(rs.getTimestamp("endtime"));
				return dc;
			}
			
		});
		if(ls!=null&&ls.size()>0){
			return ls.get(0);
		}
		return null;
	}

	@Override
	public void updateRMB(long wxuid, int rmb)
	{
		Object[] params = {wxuid,rmb,rmb};
		String sql = "INSERT INTO caidan.cd_rmb (wxuid,total) VALUES(?,?) ON DUPLICATE KEY UPDATE total=total+?";
		 jdbcTemplate.update(sql, params);
	}

	@Override
	public int findRMB(long wxuid)
	{
		try
		{
			return jdbcTemplate.queryForInt("select total from caidan.cd_rmb where wxuid=?",new Object[]{wxuid});
		} catch (DataAccessException e)
		{
		}
		return 0;
	}

	@Override
	public void updateRMBRecord(long wxuid, int rmb, String desc, int total, String source)
	{
		Object[] params = {total,rmb,wxuid,desc,source};
		String sql = "INSERT INTO caidan.cd_rmb_record (total,num,wxuid,hydesc,source,createtime) VALUES(?,?,?,?,?,now())";
		 jdbcTemplate.update(sql, params);
	}


}
