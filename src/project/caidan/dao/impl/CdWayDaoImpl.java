
package project.caidan.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;

import project.caidan.dao.ICdWayDao;
import project.caidan.model.CDWay;
import project.caidan.model.CDWayCatalog;
import project.caidan.model.WayReport;

public class CdWayDaoImpl extends AbstractDao implements ICdWayDao
{

	@Override
	public List<CDWayCatalog> findCatalogs(long owner)
	{
		return getJdbcTemplate().query("select * from caidan.cd_channel_catalog where owner=?", new Object[]
		{ owner }, new CataLogRowMapper());
	}

	@Override
	public List<CDWayCatalog> findCatalogs(long owner, int start, int size)
	{
		return getJdbcTemplate().query("select * from caidan.cd_channel_catalog where owner=? order by id desc limit ?,?", new Object[]
		{ owner, start, size }, new CataLogRowMapper());
	}

	@Override
	public List<CDWay> findWays(long owner, long catid)
	{
		return getJdbcTemplate().query("select * from caidan.cd_channel where owner=? and caid=? ", new Object[]
		{ owner, catid }, new WayRowMapper());
	}

	@Override
	public CDWayCatalog findCatalogById(long id, long owner)
	{
		List<CDWayCatalog> list = getJdbcTemplate().query("select * from caidan.cd_channel_catalog where id=? and owner=?", new Object[]
		{ id, owner }, new CataLogRowMapper());
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public CDWay findWayByid(long wayid, long owner)
	{
		List<CDWay> list = getJdbcTemplate().query("select * from caidan.cd_channel where id=? and owner=?", new Object[]
		{ wayid, owner }, new WayRowMapper());
		return list.size() > 0 ? list.get(0) : null;
	}

	class CataLogRowMapper implements RowMapper
	{

		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			CDWayCatalog cc = new CDWayCatalog();
			cc.setId(rs.getLong("id"));
			cc.setName(rs.getString("name"));
			cc.setHydesc(rs.getString("hydesc"));
			cc.setCreatetime(rs.getTimestamp("createtime"));
			cc.setOwner(rs.getLong("owner"));
			return cc;
		}
	}

	class WayRowMapper implements RowMapper
	{

		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			CDWay way = new CDWay();
			way.setId(rs.getLong("id"));
			way.setName(rs.getString("name"));
			way.setHydesc(rs.getString("hydesc"));
			way.setCaid(rs.getLong("caid"));
			way.setCreatetime(rs.getTimestamp("createtime"));
			way.setAcid(rs.getLong("acid"));
			way.setRealname(rs.getString("realname"));
			way.setTelphone(rs.getString("telphone"));
			way.setEmail(rs.getString("email"));
			way.setAddress(rs.getString("address"));
			return way;
		}
	}

	@Override
	public int findCatalogsTotal(long owner)
	{
		return getJdbcTemplate().queryForInt("select count(id) from caidan.cd_channel_catalog where owner=?", new Object[]
		{ owner });
	}

	@Override
	public int saveCatalog(CDWayCatalog catalog)
	{
		return getJdbcTemplate().update("insert into caidan.cd_channel_catalog (name,hydesc,createtime,owner) values(?,?,now(),?)", new Object[]
		{ catalog.getName(), catalog.getHydesc(), catalog.getOwner() });
	}

	@Override
	public int updateCatalog(CDWayCatalog catalog, long owner)
	{
		return getJdbcTemplate().update("update caidan.cd_channel_catalog set name=?,hydesc=? where id=? and owner=?", new Object[]
		{ catalog.getName(), catalog.getHydesc(), catalog.getId(), owner });
	}

	@Override
	public int deleteCatalog(long caid, long owner)
	{
		getJdbcTemplate().update("delete from caidan.cd_channel where caid=? and owner=?", new Object[]
		{ caid, owner });
		return getJdbcTemplate().update("delete from caidan.cd_channel_catalog where id=? and owner=?", new Object[]
		{ caid, owner });
	}

	@Override
	public List<CDWay> findWays(long owner, long caid, int start, int size)
	{
		return getJdbcTemplate().query("select * from caidan.cd_channel where caid=? and owner=? order by id desc limit ?,?", new Object[]
		{ caid, owner, start, size }, new WayRowMapper());
	}

	@Override
	public int findWaysTotal(long owner, long caid)
	{
		return getJdbcTemplate().queryForInt("select count(id) from caidan.cd_channel where caid=? and owner=?", new Object[]
		{ caid, owner });
	}

	@Override
	public int saveWay(CDWay way)
	{
		return getJdbcTemplate().update("insert into caidan.cd_channel (name,hydesc,createtime,owner,caid,acid,realname,email,telphone,address) values(?,?,now(),?,?,?,?,?,?,?)", new Object[]
		{ way.getName(), way.getHydesc(), way.getOwner(), way.getCaid(), way.getAcid(),way.getRealname(),way.getEmail(),way.getTelphone(),way.getAddress() });
	}

	@Override
	public int updateWay(CDWay way)
	{
		return getJdbcTemplate().update("update caidan.cd_channel set name=?,hydesc=?,acid=?,realname=?,telphone=?,email=?,address=? where id=? and owner=?", new Object[]
		{ way.getName(), way.getHydesc(), way.getAcid(),way.getRealname(),way.getTelphone(),way.getEmail(),way.getAddress(), way.getId(), way.getOwner() });
	}

	@Override
	public int deleteWay(long wayid, long owner)
	{
		return getJdbcTemplate().update("delete from caidan.cd_channel where id=? and owner=?", new Object[]
		{ wayid, owner });
	}

	@Override
	public List<CDWay> findWaysByPid(long pid)
	{
		return getJdbcTemplate().query("select c.* from caidan.cd_product_channel r join caidan.cd_channel c on r.clid=c.id where r.pid=? order by id asc", new Object[]
		{ pid }, new WayRowMapper());
	}

	@Override
	public void updateProductWayClean(long id)
	{
		getJdbcTemplate().update("delete from caidan.cd_product_channel where pid=?", new Object[]{id});
	}
	
	@Override
	public void updateProductWay(final long pid, final List<JSONObject> wayid)
	{
		getJdbcTemplate().batchUpdate("insert ignore into caidan.cd_product_channel(pid,clid,cname,ccname) values(?,?,?,?)", new BatchPreparedStatementSetter()
		{

			public void setValues(PreparedStatement ps, int index) throws SQLException
			{
				int i = 1;
				JSONObject jo=wayid.get(index);
				ps.setLong(i++, pid);
				ps.setLong(i++, jo.getLong("wayid"));
				ps.setString(i++, jo.getString("cname"));
				ps.setString(i++, jo.getString("ccname"));
			}

			@Override
			public int getBatchSize()
			{
				return wayid.size();
			}
		});
	}
	
	@Override
	public int findWaysTotal(long owner)
	{
		return getJdbcTemplate().queryForInt("select count(*) from caidan.cd_channel where owner=?", new Object[]{owner});
	}
	
	@Override
	public List<WayReport> findWayReport(long owner,int start,int size)
	{
		return getJdbcTemplate().query("select * from  caidan.cd_channel where owner=? order by caid desc,id desc limit ?,?", new Object[]{owner,start,size}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				WayReport report=new WayReport();
				CDWay way = new CDWay();
				way.setId(rs.getLong("id"));
				way.setName(rs.getString("name"));
				way.setHydesc(rs.getString("hydesc"));
				way.setCaid(rs.getLong("caid"));
				way.setCreatetime(rs.getTimestamp("createtime"));
				way.setAcid(rs.getLong("acid"));
				report.setWay(way);
				return report;
			}
		});
	}
	
	@Override
	public Map findWayCatByid(long wayid)
	{
		return getJdbcTemplate().queryForMap("select a.name cname,b.id ccname from caidan.cd_channel a join caidan.cd_channel_catalog b on a.caid=b.id where a.id=?", new Object[]{wayid});
	}
}
