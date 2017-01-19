package com.huiyee.esite.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IInteractModelDao;
import com.huiyee.esite.model.InteractModel;

public class InteractModelDaoImpl extends AbstractDao implements IInteractModelDao
{

	@Override
	public List<InteractModel> findInteractModel()
	{
		return getJdbcTemplate().query("select * from esite.es_interact_model where status!='DEL'", new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				InteractModel i = new InteractModel();
				i.setId(rs.getLong("id"));
				i.setName(rs.getString("name"));
				return i;
			}
		});
	}

	private static final String FIND_INTERACT_MODEL = "select * from esite.es_owner_interact i join esite.es_interact_model m on i.interactid=m.id where i.status!='DEL' and m.status!='DEL' and i.ownerid=? order by i.id desc";

	@Override
	public List<InteractModel> findAllInteractModel(long owner)
	{
		return getJdbcTemplate().query(FIND_INTERACT_MODEL, new Object[]
		{ owner }, new InteractModelRowmapper());
	}

	private static final String FIND_INTERACT_MODEL_CONFIGD = "select * from  esite.es_owner_interact i join  esite.es_interact_model m on m.id=i.interactid where m.status != 'DEL' and i.ownerid=? and i.status='CMP' order by i.id asc";

	@Override
	public List<InteractModel> findInteractModel(long owner)
	{
		return getJdbcTemplate().query(FIND_INTERACT_MODEL_CONFIGD, new Object[]
		{ owner }, new InteractModelRowmapper());
	}

	class InteractModelRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			InteractModel i = new InteractModel();
			i.setId(rs.getLong("m.id"));
			i.setName(rs.getString("m.name"));
			long omid = rs.getLong("i.id");
			i.setUsed(omid > 0 ? true : false);
			i.setOmid(omid);
			i.setTitle(rs.getString("i.title"));
			i.setStatus(rs.getString("i.status"));
			return i;
		}

	}

	@Override
	public void removeInteract(long owner)
	{
		getJdbcTemplate().update("update es_owner_interact set status='REG' where ownerid=?", new Object[]
		{ owner });
	}


	@Override
	public int addOwnerInteract(String title, long mid, long owner)
	{
		return getJdbcTemplate().update("insert into esite.es_owner_interact (ownerid,interactid,title,status) values(?,?,?,'CMP')", new Object[]
		{ owner, mid, title });
	}

	@Override
	public int deleteOwnerInteract(long lid, long ownerid)
	{
		return getJdbcTemplate().update("update  esite.es_owner_interact set status='DEL' where id=? and ownerid=?", new Object[]
		{ lid, ownerid });
	}

	@Override
	public int updateOwnerInteract(long lid, String title, String status, long ownerid)
	{
		return getJdbcTemplate().update("update  esite.es_owner_interact set title=?,status=? where id=? and ownerid=?", new Object[]
		{ title, status, lid, ownerid });
	}

	@Override
	public int findTotalByMid(long mid, long owner)
	{
		return getJdbcTemplate().queryForInt("select count(*) from  esite.es_owner_interact where interactid=? and ownerid=? and status!='DEL'", new Object[]
		{ mid, owner });
	}

	@Override
	public InteractModel findInteractModelById(long omid)
	{
		List<InteractModel> list = getJdbcTemplate().query("select * from esite.es_owner_interact where id=?", new Object[]
		{ omid }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				InteractModel model = new InteractModel();
				model.setTitle(rs.getString("title"));
				return model;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}
	
	@Override
	public int updateOwnerInteractStatus(long lid, long owner, String status) {
		return getJdbcTemplate().update("update  esite.es_owner_interact set status=? where id=? and ownerid=?", new  Object[]{status,lid,owner});
	}
	
	@Override
	public int updateOwnerInteractTitle(long lid, long owner, String title) {
		return getJdbcTemplate().update("update  esite.es_owner_interact set title=? where id=? and ownerid=?", new  Object[]{title,lid,owner});
	}
	
	@Override
	public int findCountByTitle(String title, long owner) {
		return getJdbcTemplate().queryForInt("select count(*) from esite.es_owner_interact where ownerid=? and title=? and status!='DEL'", new Object[]{owner,title});
	}

}
