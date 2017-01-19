package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IAccountDao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Owner;
import com.huiyee.esite.model.OwnerSetting;
import com.huiyee.esite.model.OwnerPrivilege;

public class AccountDaoImpl extends AbstractDao implements IAccountDao
{

	public static final String FIND_ACCOUNT_LIST_BY_OWNER = "select owner.id ownerid,owner.entity,owner.sup,account.id accountid,account.fullname,account.password,account.username,owner.endtime endtime from es_owner owner join es_owner_account account on owner.id = account.owner where owner.entity = ? and account.username = ?  and account.password = ? and account.del_tag='N'";

	@Override
	public Account findAccountListByOwner(String ownerName, String accountName, String password)
	{
		Object[] params =
		{ ownerName, accountName, password };
		List<Account> list = getJdbcTemplate().query(FIND_ACCOUNT_LIST_BY_OWNER, params, new AccountRowmapper());
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	public static final String FIND_ACCOUNT_BY_OWNERID_AND_ACCOUNTID = "select owner.id ownerid,owner.entity,owner.sup,account.id accountid,account.fullname,account.password,account.username,owner.endtime endtime from es_owner owner join es_owner_account account on owner.id = account.owner where owner.id = ? and account.id = ? and account.del_tag='N'";

	@Override
	public Account findAccountByOwnerIdandAccountId(long ownerId, long accountId)
	{
		Object[] params =
		{ ownerId, accountId };
		List<Account> list = getJdbcTemplate().query(FIND_ACCOUNT_BY_OWNERID_AND_ACCOUNTID, params, new AccountRowmapper());
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	class AccountRowmapper implements RowMapper
	{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Account account = new Account();
			account.setId(rs.getLong("accountid"));
			account.setPassword(rs.getString("password"));
			account.getOwner().setId(rs.getLong("ownerid"));
			account.getOwner().setEntity(rs.getString("entity"));
			account.setUsername(rs.getString("username"));
			account.getOwner().setEndtime(rs.getTimestamp("endtime"));
			account.getOwner().setSup(rs.getString("sup"));
			return account;
		}
	}

	private static final String ADD_login_log = "insert into es_login_log (logintime,ip,accountid,ownerid,logintype) values(now(),?,?,?,?)";

	@Override
	public long addLoginLog(final String ip, final long accountid, final long ownerid, final String logintype)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(ADD_login_log, new String[]
				{ "id" });
				ps.setString(1, ip);
				ps.setLong(2, accountid);
				ps.setLong(3, ownerid);
				ps.setString(4, logintype);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	private static final String ADD_PASSPORT_ACCOUNT_ = "insert into es_owner_account(id,username,owner,createtime,del_tag)values(?,?,?,now(),'N')";

	@Override
	public long addAccount(final long ownerId, final long accountId, final String accountname)
	{

		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(ADD_PASSPORT_ACCOUNT_, new String[]
				{ "id" });
				ps.setLong(1, accountId);
				ps.setString(2, accountname);
				ps.setLong(3, ownerId);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	private static final String FIND_ACCOUNT = "select count(id) from esite.es_owner_account where id = ? and username= ? and owner=? and del_tag <> 'Y'";

	@Override
	public int findAccount(long ownerId, long accountId, String accountname)
	{
		Object[] params =
		{ accountId, accountname, ownerId };
		return getJdbcTemplate().queryForInt(FIND_ACCOUNT, params);
	}

	private static final String FIND_OWNER = "select * from es_owner where id = ?";

	@Override
	public Owner findOwner(long owner)
	{
		Object[] params =
		{ owner };
		List<Owner> list = getJdbcTemplate().query(FIND_OWNER, params, new OwnerRowmapper());
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	private static final String FIND_ACCOUNT_ID = "select id from esite.es_owner_account where id = ? and username= ? and owner=? ";

	@Override
	public long findAccountId(long ownerId, long accountId, String accountname)
	{
		Object[] params =
		{ accountId, accountname, ownerId };
		List<Long> list = getJdbcTemplate().query(FIND_ACCOUNT_ID, params, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("id");
			}
		});
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return 0;
	}

	class OwnerRowmapper implements RowMapper
	{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Owner owner = new Owner();
			owner.setId(rs.getLong("id"));
			owner.setEntity(rs.getString("entity"));
			return owner;
		}
	}

	@Override
	public OwnerSetting findOwnerSetting(long owner)
	{
		List<OwnerSetting> list = getJdbcTemplate().query("select * from esite.es_owner_setting where ownerid=?", new Object[]
		{ owner }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				OwnerSetting os = new OwnerSetting();
				os.setOdomain(rs.getString("odomain"));
				os.setOwnerid(rs.getLong("ownerid"));
				os.setWxappid(rs.getString("wxappid"));
				os.setWxsecret(rs.getString("wxsecret"));
				return os;
			}
		});

		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public int updateOwnerSetting(OwnerSetting set)
	{
		return getJdbcTemplate().update("insert into es_owner_setting (ownerid,odomain,wxappid,wxsecret) values(?,?,?,?) on duplicate key update odomain=?,wxappid=?,wxsecret=?", new Object[]
		{ set.getOwnerid(), set.getOdomain(), set.getWxappid(), set.getWxsecret(), set.getOdomain(), set.getWxappid(), set.getWxsecret()});
	}

	private static final String FIND_OWNER_PRIVILEGE="select * from es_owner_privilege op join es_privilege p on op.pid = p.id where op.ownerid = ? ";
	@Override
	public List<OwnerPrivilege> findOwnerPrivilege(long ownerid)
	{
		Object[] param={ownerid};
		return getJdbcTemplate().query(FIND_OWNER_PRIVILEGE, param, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				OwnerPrivilege op = new OwnerPrivilege();
				op.setId(rs.getLong("op.id"));
				op.setOwnerid(rs.getLong("op.ownerid"));
				op.setPid(rs.getLong("op.pid"));
				op.setModule(rs.getString("p.module"));
				op.setLevel(rs.getInt("op.level"));
				op.setCreatetime(rs.getTimestamp("op.createtime"));
				op.setStarttime(rs.getTimestamp("op.starttime"));
				op.setEndtime(rs.getTimestamp("op.endtime"));
				op.setStatus(rs.getString("op.status"));
				return op;
			}
			
		});
	}

	private static final String FIND_OWNER_PRIVILEGE_BY_APPID="select * from es_owner_privilege where ownerid = ? and pid = ?";
	@Override
	public OwnerPrivilege findOwnerPrivilege(long ownerid, long appid)
	{
		Object[] param={ownerid,appid};
		List<OwnerPrivilege> list = getJdbcTemplate().query(FIND_OWNER_PRIVILEGE_BY_APPID, param, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				OwnerPrivilege op = new OwnerPrivilege();
				op.setId(rs.getLong("id"));
				op.setOwnerid(rs.getLong("ownerid"));
				op.setPid(rs.getLong("pid"));
				op.setLevel(rs.getInt("level"));
				op.setCreatetime(rs.getTimestamp("createtime"));
				op.setStarttime(rs.getTimestamp("starttime"));
				op.setEndtime(rs.getTime("endtime"));
				op.setStatus(rs.getString("status"));
				return op;
			}
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	private static final String SAVE_OWNER_PRIVIELEGE="insert into es_owner_privilege(ownerid,pid,createtime,starttime,status,level) values (?,?,now(),now(),?,?)";
	@Override
	public int saveOwnerPrivilege(long ownerid, long appid,String status, int level)
	{
		Object[] param={ownerid,appid,status,level};
		return getJdbcTemplate().update(SAVE_OWNER_PRIVIELEGE, param);
	}
	
}
