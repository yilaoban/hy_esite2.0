
package project.caidan.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import project.caidan.dao.IAccountTypeDao;
import project.caidan.model.CDAccountCpz;
import project.caidan.model.CDAccountDl;
import project.caidan.model.CDAccoutType;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.model.Account;

public class AccountTypeDaoImpl extends AbstractDao implements IAccountTypeDao
{

	private static final String FIND_ACCOUNT_TYPE = "select type from caidan.cd_account_type where acid = ? order by id desc limit 0,1";

	@Override
	public String findAccountType(long accountid)
	{
		Object[] param =
		{ accountid };
		try
		{
			return (String) getJdbcTemplate().queryForObject(FIND_ACCOUNT_TYPE, param, String.class);
		} catch (DataAccessException e)
		{
			return null;
		}
	}

	@Override
	public List<Account> findAccountForWay(long owner)
	{
		return getJdbcTemplate().query("select a.* from caidan.cd_account_type t join es_owner_account a on t.acid=a.id where t.type='PRZ' and a.owner=?", new Object[]
		{ owner }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Account account = new Account();
				account.setId(rs.getLong("id"));
				account.setPassword(rs.getString("password"));
				account.setUsername(rs.getString("username"));
				return account;
			}
		});
	}

	@Override
	public int findAccountTotal(long id, String type)
	{
		return getJdbcTemplate().queryForInt("select count(*) from caidan.cd_account_type t join es_owner_account a on t.acid=a.id where t.type=? and a.owner=?", new Object[]{type,id});
	}
	
	@Override
	public List<CDAccoutType> findAccountsByType(long owner, String type, int start, int size)
	{
		return getJdbcTemplate().query("select * from caidan.cd_account_type t join es_owner_account a on t.acid=a.id where t.type=? and a.owner=? order by t.id desc limit ?,?",
				new Object[]
				{ type, owner, start, size }, new RowMapper()
				{

					public Object mapRow(ResultSet rs, int arg1) throws SQLException
					{
						CDAccoutType account = new CDAccoutType();
						account.setId(rs.getLong("a.id"));
						account.setPassword(rs.getString("a.password"));
						account.setUsername(rs.getString("a.username"));
						account.setCreatetime(rs.getTimestamp("a.createtime"));
						account.setWxuid(rs.getLong("t.wxuid"));
						account.setFullname(rs.getString("a.fullname"));
						account.setPhone(rs.getString("a.phone"));
						account.setEmail(rs.getString("a.email"));
						account.setTitle(rs.getString("a.title"));
						return account;
					}
				});
	}

	private static final String ADD_PASSPORT_ACCOUNT_ = "insert into es_owner_account(username,password,fullname,phone,email,title,owner,createtime,del_tag)values(?,?,?,?,?,?,?,now(),'N')";

	public long saveAccount(final long owner, final CDAccoutType act,final String password)
	{

		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(ADD_PASSPORT_ACCOUNT_, new String[]
				{ "id" });
				int i=1;
				ps.setString(i++, act.getUsername());
				ps.setString(i++, password);
				ps.setString(i++, act.getFullname());
				ps.setString(i++, act.getPhone());
				ps.setString(i++, act.getEmail());
				ps.setString(i++, act.getTitle());
				ps.setLong(i++, owner);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
	
	@Override
	public void saveAccountType(long acid, String type)
	{
		getJdbcTemplate().update("insert into caidan.cd_account_type (acid,type) values(?,?)", new Object[]{acid,type});
	}
	
	@Override
	public int findAccountByName(long id, String username)
	{
		return getJdbcTemplate().queryForInt("select count(*) from es_owner_account where username=? and owner=? and del_tag!='Y'", new Object[]{username,id});
	}
	
	@Override
	public CDAccoutType findByAcid(long accountid, long owner)
	{
		List<CDAccoutType> list=getJdbcTemplate().query("select * from caidan.cd_account_type t join es_owner_account a on t.acid=a.id where t.acid=? and a.owner=? and a.del_tag!='Y'", new Object[]{accountid,owner}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDAccoutType account = new CDAccoutType();
				account.setWxuid(rs.getLong("t.wxuid"));
				account.setUsername(rs.getString("a.username"));
				account.setFullname(rs.getString("a.fullname"));
				account.setPhone(rs.getString("a.phone"));
				account.setId(rs.getLong("a.id"));
				account.setEmail(rs.getString("a.email"));
				account.setTitle(rs.getString("a.title"));
				return account;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public CDAccoutType findByClid(long wayid, long owner)
	{
		List<CDAccoutType> list=getJdbcTemplate().query("select * from caidan.cd_channel a join caidan.cd_account_type t on a.acid=t.acid where a.id=? and a.owner=?", new Object[]{wayid,owner}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDAccoutType account = new CDAccoutType();
				account.setWxuid(rs.getLong("t.wxuid"));
				return account;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public int updateAccountWxuid(long accountid, long wxuid)
	{
		return getJdbcTemplate().update("update caidan.cd_account_type set wxuid=? where acid=?", new Object[]{wxuid,accountid});
	}
	
	@Override
	public int updateAtWxuid(long acid)
	{
		return getJdbcTemplate().update("update caidan.cd_account_type set wxuid=null where acid=?", new Object[]{acid});
	}
	
	@Override
	public int removeAccount(long acid)
	{
		return getJdbcTemplate().update("update es_owner_account set del_tag='Y' where id=?", new Object[]{acid});
	}
	
	@Override
	public int removeAccType(long acid)
	{
		return getJdbcTemplate().update("delete from caidan.cd_account_type where acid=?", new Object[]{acid});
	}
	
	@Override
	public CDAccoutType findAtByWxuid(long wxuid)
	{
		List<CDAccoutType> list=getJdbcTemplate().query("select * from caidan.cd_account_type t join es_owner_account a on t.acid=a.id where t.wxuid=?  and a.del_tag!='Y'", new Object[]{wxuid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDAccoutType account = new CDAccoutType();
				account.setWxuid(rs.getLong("t.wxuid"));
				return account;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public CDAccountCpz findCpzByWxuid(long wxuid, String status)
	{
		List<CDAccountCpz> list=getJdbcTemplate().query("select * from caidan.cd_account_cpz where wxuid=? and status=?", new Object[]{wxuid,status}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDAccountCpz cpz=new CDAccountCpz();
				cpz.setId(rs.getLong("id"));
				cpz.setFawxuid(rs.getLong("fawxuid"));
				cpz.setName(rs.getString("name"));
				return cpz;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public CDAccountDl findDlByWxuid(long wxuid, String status)
	{
		List<CDAccountDl> list=getJdbcTemplate().query("select * from caidan.cd_account_dl where wxuid=? and status=?", new Object[]{wxuid,status}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDAccountDl dl=new CDAccountDl();
				dl.setId(rs.getLong("id"));
				dl.setName(rs.getString("name"));
				return dl;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public int updatePwd(long acid, String md5Str)
	{
		return getJdbcTemplate().update("update esite.es_owner_account set password=? where id=?", new Object[]{md5Str,acid});
	}
	
	@Override
	public long upadte(long owner, CDAccoutType act)
	{
		return getJdbcTemplate().update("update esite.es_owner_account set username=?,fullname=?,phone=?,email=?,title=? where id=? and owner=?", new Object[]{act.getUsername(),act.getFullname(),act.getPhone(),act.getEmail(),act.getTitle(),act.getId(),owner});
	}
	
	@Override
	public List<CDAccountCpz> findCpzsByPid(long pid,int start,int size)
	{
		return getJdbcTemplate().query("select c.* from caidan.cd_product_channel p join caidan.cd_account_cpz c on p.clid=c.clid where p.pid=? order by c.id desc limit ?,?", new Object[]{pid,start,size}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDAccountCpz cpz=new CDAccountCpz();
				cpz.setId(rs.getLong("id"));
				cpz.setDpname(rs.getString("dpname"));
				cpz.setProvince(rs.getString("province"));
				cpz.setCity(rs.getString("city"));
				cpz.setHydesc(rs.getString("hydesc"));
				return cpz;
			}
		});
	}
	
}
