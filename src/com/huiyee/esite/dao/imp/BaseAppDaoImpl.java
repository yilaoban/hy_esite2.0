
package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IBaseAppDao;
import com.huiyee.interact.cb.model.InteractCb;

public class BaseAppDaoImpl extends AbstractDao implements IBaseAppDao
{

	@Override
	public int findFormTotal(long ownerid, long accid)
	{
		String sql = "select count(*) from hybbs.bbs_category c join hybbs.bbs_forum f on c.id = f.cateid join hybbs.bbs_forum_account a on f.id = a.forumid where c.owner = ? and a.accid = ? and f.status != 'DEL'";
		Object[] params =
		{ ownerid, accid };
		return getJdbcTemplate().queryForInt(sql, params);
	}

	@Override
	public List<InteractCb> findCbList(long ownerid)
	{
		return getJdbcTemplate().query("select c.* from es_interact_cb c join es_site_group g on c.sitegroupid=g.id where c.owner=? and g.status!='DEL'", new Object[]
		{ ownerid }, new RowMapper()
		{
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				InteractCb cb=new InteractCb();
				cb.setTitle(rs.getString("title"));
				cb.setId(rs.getLong("id"));
				cb.setCreatetime(rs.getTimestamp("createtime"));
				return cb;
			}
		});
	}
}
