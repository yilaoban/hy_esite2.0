package project.chuanmc.dao.impl;

import org.springframework.dao.DataAccessException;

import project.chuanmc.dao.IAccountTypeDao;

import com.huiyee.esite.dao.AbstractDao;

public class AccountTypeDaoImpl extends AbstractDao implements IAccountTypeDao{

	private static final String FIND_ACCOUNT_TYPE="select type from caidan.cd_account_type where acid = ? order by id desc limit 0,1";
	@Override
	public String findAccountType(long accountid)
	{
		Object[] param={accountid};
		try
		{
			return (String) getJdbcTemplate().queryForObject(FIND_ACCOUNT_TYPE, param, String.class);
		} catch (DataAccessException e)
		{
			return null;
		}
	}
	
}
