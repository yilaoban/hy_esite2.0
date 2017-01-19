package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.ISiteActionDao;
import com.huiyee.esite.model.SiteCount;
import com.huiyee.esite.util.DateUtil;

public class SiteActionDaoImpl extends AbstractDao implements ISiteActionDao
{

	@Override
	public List<SiteCount> findSiteActionBySiteid(long sitegid,long owner)
	{
		// select type,sum(total) from action,site where sitegroupid=12111 group
		// by type
		return getJdbcTemplate().query("select esa.type,sum(esa.visitTotal) visitTotal,now() updatetime from esite.es_site_action  esa,esite.es_site site where esa.siteid=site.id and   siteid=? and ownerid=? group by type", new Object[]
		{ sitegid,owner }, new SiteCountRowMapper());
	}
	
	@Override
    public List<SiteCount> findSiteCountBySite(long siteid,long owner)
    {
        // select type,sum(total) from action,site where sitegroupid=12111 group
        // by type
        return getJdbcTemplate().query("select esa.type,sum(esa.visitTotal) visitTotal,now() updatetime from esite.es_site_action  esa,esite.es_site site where esa.siteid=site.id and site.id=? and ownerid=? group by type", new Object[]
        { siteid,owner }, new SiteCountRowMapper());
    }

}

class SiteCountRowMapper implements RowMapper
{

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException
	{
		SiteCount sc = new SiteCount();
		sc.setVisteNum(rs.getLong("visitTotal"));
		sc.setType(rs.getString("type").equalsIgnoreCase("A") ? "ÊÇ" : "·ñ");
		sc.setUpdateTime(DateUtil.getDateString(rs.getTimestamp("updatetime")));
		return sc;
	}
}
