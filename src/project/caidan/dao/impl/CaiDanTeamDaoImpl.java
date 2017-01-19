package project.caidan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import project.caidan.dao.ICaiDanTeamDao;
import project.caidan.model.CDAccountCpz;
import project.caidan.model.CDAccountDl;
import project.caidan.model.CDAccoutType;
import project.caidan.model.CDChannel;


public class CaiDanTeamDaoImpl implements ICaiDanTeamDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public CDAccoutType findAccountTypeByWxuid(long wxuid,String type)
	{
		String sql = "select * from caidan.cd_account_type where wxuid = ? and type = ?";
		Object[] params = {wxuid,type};
		List<CDAccoutType> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDAccoutType ac = new CDAccoutType();
				ac.setAcid(rs.getLong("acid"));
				ac.setId(rs.getLong("id"));
				ac.setType(rs.getString("type"));
				ac.setWxuid(rs.getLong("wxuid"));
				return ac;
			}
		});
		if(list != null && list.size()> 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<CDChannel> findChannelByAcid(long acid,long owner)
	{
		String sql = "select * from caidan.cd_channel where acid = ? and owner = ?";
		Object[] pamras = {acid,owner};
		return jdbcTemplate.query(sql, pamras, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDChannel ch = new CDChannel();
				ch.setId(rs.getLong("id"));
				ch.setName(rs.getString("name"));
				ch.setAcid(rs.getLong("acid"));
				ch.setCaid(rs.getLong("caid"));
				ch.setCreatetime(rs.getTimestamp("createtime"));
				ch.setHydesc(rs.getString("hydesc"));
				ch.setOwner(rs.getLong("owner"));
				return ch;
			}
			
		});
	}

	@Override
	public CDAccountDl findAccountDlByWxuid(long wxuid)
	{
		String sql = "select * from caidan.cd_account_dl where wxuid = ?";
		Object[] params = {wxuid};
		List<CDAccountDl> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDAccountDl dl = new CDAccountDl();
				dl.setCity(rs.getString("city"));
				dl.setClid(rs.getLong("clid"));
				dl.setFawxuid(rs.getLong("fawxuid"));
				dl.setId(rs.getLong("id"));
				dl.setName(rs.getString("name"));
				dl.setProvince(rs.getString("province"));
				dl.setStatus(rs.getString("status"));
				dl.setTelphone(rs.getString("telphone"));
				dl.setWxuid(rs.getLong("wxuid"));
				return dl;
			}
		});
		if(list != null && list.size()> 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<CDAccountDl> findAccountDlListByWxuid(long wxuid,String status)
	{
		String sql = "select dl.*,u.nickname,u.headimgurl from caidan.cd_account_dl dl left join esite.es_wx_user u on dl.wxuid = u.id where dl.fawxuid = ? and dl.status = ?";
		Object[] params = {wxuid,status};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDAccountDl dl = new CDAccountDl();
				dl.setCity(rs.getString("dl.city"));
				dl.setClid(rs.getLong("dl.clid"));
				dl.setFawxuid(rs.getLong("dl.fawxuid"));
				dl.setId(rs.getLong("dl.id"));
				dl.setName(rs.getString("dl.name"));
				dl.setProvince(rs.getString("dl.province"));
				dl.setStatus(rs.getString("dl.status"));
				dl.setTelphone(rs.getString("dl.telphone"));
				dl.setWxuid(rs.getLong("dl.wxuid"));
				dl.setNickname(rs.getString("u.nickname"));
				dl.setHeadimgurl(rs.getString("u.headimgurl"));
				return dl;
			}
			
		});
	}

	@Override
	public List<CDAccountCpz> findAccountCpzByWxuid(long wxuid,String status)
	{
		String sql = "select cpz.*,u.nickname,u.headimgurl from caidan.cd_account_cpz cpz left join esite.es_wx_user u on cpz.wxuid = u.id where cpz.fawxuid = ? and cpz.status = ?";
		Object[] params = {wxuid,status};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDAccountCpz dl = new CDAccountCpz();
				dl.setCity(rs.getString("cpz.city"));
				dl.setClid(rs.getLong("cpz.clid"));
				dl.setFawxuid(rs.getLong("cpz.fawxuid"));
				dl.setId(rs.getLong("cpz.id"));
				dl.setName(rs.getString("cpz.name"));
				dl.setProvince(rs.getString("cpz.province"));
				dl.setStatus(rs.getString("cpz.status"));
				dl.setTelphone(rs.getString("cpz.telphone"));
				dl.setWxuid(rs.getLong("cpz.wxuid"));
				dl.setNickname(rs.getString("u.nickname"));
				dl.setHeadimgurl(rs.getString("u.headimgurl"));
				return dl;
			}
			
		});
	}

	@Override
	public CDAccountDl findAccountDlById(long id)
	{
		String sql = "select dl.*,u.nickname,u.headimgurl from caidan.cd_account_dl dl left join esite.es_wx_user u on dl.wxuid = u.id where dl.id = ?";
		Object[] params = {id};
		List<CDAccountDl> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDAccountDl dl = new CDAccountDl();
				dl.setCity(rs.getString("dl.city"));
				dl.setClid(rs.getLong("dl.clid"));
				dl.setFawxuid(rs.getLong("dl.fawxuid"));
				dl.setId(rs.getLong("dl.id"));
				dl.setName(rs.getString("dl.name"));
				dl.setProvince(rs.getString("dl.province"));
				dl.setStatus(rs.getString("dl.status"));
				dl.setTelphone(rs.getString("dl.telphone"));
				dl.setWxuid(rs.getLong("dl.wxuid"));
				dl.setNickname(rs.getString("u.nickname"));
				dl.setHeadimgurl(rs.getString("u.headimgurl"));
				return dl;
			}
		});
		if(list != null && list.size()> 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public CDAccountCpz findAccountCpzById(long id)
	{
		String sql = "select cpz.*,u.nickname,u.headimgurl from caidan.cd_account_cpz cpz left join esite.es_wx_user u on cpz.wxuid = u.id where cpz.id = ? ";
		Object[] params = {id};
		List<CDAccountCpz> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDAccountCpz dl = new CDAccountCpz();
				dl.setCity(rs.getString("cpz.city"));
				dl.setClid(rs.getLong("cpz.clid"));
				dl.setFawxuid(rs.getLong("cpz.fawxuid"));
				dl.setId(rs.getLong("cpz.id"));
				dl.setName(rs.getString("cpz.name"));
				dl.setProvince(rs.getString("cpz.province"));
				dl.setStatus(rs.getString("cpz.status"));
				dl.setTelphone(rs.getString("cpz.telphone"));
				dl.setWxuid(rs.getLong("cpz.wxuid"));
				dl.setNickname(rs.getString("u.nickname"));
				dl.setHeadimgurl(rs.getString("u.headimgurl"));
				dl.setHydesc(rs.getString("cpz.hydesc"));
				dl.setDpname(rs.getString("cpz.dpname"));
				return dl;
			}
		});
		if(list != null && list.size()> 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public CDChannel findChannelById(long id, long owner)
	{
		String sql = "select * from caidan.cd_channel where id = ? and owner = ?";
		Object[] pamras = {id,owner};
		List<CDChannel> list = jdbcTemplate.query(sql, pamras, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDChannel ch = new CDChannel();
				ch.setId(rs.getLong("id"));
				ch.setName(rs.getString("name"));
				ch.setAcid(rs.getLong("acid"));
				ch.setCaid(rs.getLong("caid"));
				ch.setCreatetime(rs.getTimestamp("createtime"));
				ch.setHydesc(rs.getString("hydesc"));
				ch.setOwner(rs.getLong("owner"));
				return ch;
			}
			
		});
		if(list != null && list.size()> 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateAccountCpzById(long id, long clid,String status)
	{
		String sql = "update caidan.cd_account_cpz set status = ?,clid=? where id = ?";
		Object[] params = {status,clid,id};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateAccountDlById(long id, long clid,String status)
	{
		String sql = "update caidan.cd_account_dl set status = ?,clid=? where id = ?";
		Object[] params = {status,clid,id};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int saveAccountDl(CDAccountDl dl)
	{
		String sql = "insert into caidan.cd_account_dl(wxuid,name,province,city,telphone,fawxuid,createtime) values(?,?,?,?,?,?,now())";
		Object[] params = {dl.getWxuid(),dl.getName(),dl.getProvince(),dl.getCity(),dl.getTelphone(),dl.getFawxuid()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int saveAccountCpz(CDAccountDl dl)
	{
		String sql = "insert into caidan.cd_account_cpz(wxuid,name,province,city,telphone,fawxuid,createtime,hydesc,dpname) values(?,?,?,?,?,?,now(),?,?)";
		Object[] params = {dl.getWxuid(),dl.getName(),dl.getProvince(),dl.getCity(),dl.getTelphone(),dl.getFawxuid(),dl.getHydesc(),dl.getDpname()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public CDAccountCpz findAccountCpzByWxuid(long wxuid)
	{
		String sql = "select * from caidan.cd_account_cpz where wxuid = ?";
		Object[] params = {wxuid};
		List<CDAccountCpz> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDAccountCpz dl = new CDAccountCpz();
				dl.setCity(rs.getString("city"));
				dl.setClid(rs.getLong("clid"));
				dl.setFawxuid(rs.getLong("fawxuid"));
				dl.setId(rs.getLong("id"));
				dl.setName(rs.getString("name"));
				dl.setProvince(rs.getString("province"));
				dl.setStatus(rs.getString("status"));
				dl.setTelphone(rs.getString("telphone"));
				dl.setWxuid(rs.getLong("wxuid"));
				return dl;
			}
		});
		if(list != null && list.size()> 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public CDAccountDl findAccountDlByWxuidAndFwxuid(long wxuid, long fawxuid)
	{
		String sql = "select * from caidan.cd_account_dl where wxuid = ? and fawxuid = ?";
		Object[] params = {wxuid,fawxuid};
		List<CDAccountDl> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDAccountDl dl = new CDAccountDl();
				dl.setCity(rs.getString("city"));
				dl.setClid(rs.getLong("clid"));
				dl.setFawxuid(rs.getLong("fawxuid"));
				dl.setId(rs.getLong("id"));
				dl.setName(rs.getString("name"));
				dl.setProvince(rs.getString("province"));
				dl.setStatus(rs.getString("status"));
				dl.setTelphone(rs.getString("telphone"));
				dl.setWxuid(rs.getLong("wxuid"));
				return dl;
			}
		});
		if(list != null && list.size()> 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public CDAccountCpz findAccountCpzByWxuidAndFwxuid(long wxuid, long fawxuid)
	{
		String sql = "select * from caidan.cd_account_cpz where wxuid = ? and fawxuid = ?";
		Object[] params = {wxuid,fawxuid};
		List<CDAccountCpz> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDAccountCpz dl = new CDAccountCpz();
				dl.setCity(rs.getString("city"));
				dl.setClid(rs.getLong("clid"));
				dl.setFawxuid(rs.getLong("fawxuid"));
				dl.setId(rs.getLong("id"));
				dl.setName(rs.getString("name"));
				dl.setProvince(rs.getString("province"));
				dl.setStatus(rs.getString("status"));
				dl.setTelphone(rs.getString("telphone"));
				dl.setWxuid(rs.getLong("wxuid"));
				return dl;
			}
		});
		if(list != null && list.size()> 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateAccountDlByWxuidAndFwxuid(CDAccountDl dl)
	{
		String sql = "update caidan.cd_account_dl set name=?,province=?,city=?,telphone=? where wxuid = ? and fawxuid = ?";
		Object[] params = {dl.getName(),dl.getProvince(),dl.getCity(),dl.getTelphone(),dl.getWxuid(),dl.getFawxuid()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateAccountCpzByWxuidAndFwxuid(CDAccountDl dl)
	{
		String sql = "update caidan.cd_account_cpz set name=?,province=?,city=?,telphone=?,hydesc=?,dpname=? where wxuid = ? and fawxuid = ?";
		Object[] params = {dl.getName(),dl.getProvince(),dl.getCity(),dl.getTelphone(),dl.getHydesc(),dl.getDpname(),dl.getWxuid(),dl.getFawxuid()};
		return jdbcTemplate.update(sql, params);
	}
	
	
	@Override
	public List<CDAccountDl> findDlByFaWxuid(long wxuid, long wayid, String status, String starttime, String endtime)
	{
		List<Object> list=new ArrayList<Object>();
		String sql="select * from caidan.cd_account_dl where fawxuid=? and status=? and clid=?";
		list.add(wxuid);
		list.add(status);
		list.add(wayid);
		if(starttime!=null&&starttime.trim().length()>0){
			sql+=" and createtime>=? ";
			list.add(starttime.trim());
		}
		if(endtime!=null&&endtime.trim().length()>0){
			sql+=" and createtime<=? ";
			list.add(endtime.trim());
		}
		
		return jdbcTemplate.query(sql, list.toArray(), new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDAccountDl dl = new CDAccountDl();
				dl.setCity(rs.getString("city"));
				dl.setClid(rs.getLong("clid"));
				dl.setFawxuid(rs.getLong("fawxuid"));
				dl.setId(rs.getLong("id"));
				dl.setName(rs.getString("name"));
				dl.setProvince(rs.getString("province"));
				dl.setStatus(rs.getString("status"));
				dl.setTelphone(rs.getString("telphone"));
				dl.setWxuid(rs.getLong("wxuid"));
				dl.setCreatetime(rs.getTimestamp("createtime"));
				return dl;
			}
		});
	}
	
	@Override
	public List<CDAccountCpz> findCpzByFather(long wxuid, long wayid, String starttime, String endtime, int i, int reportCpzLimit)
	{
		List<Object> list=new ArrayList<Object>();
		String sql="select * from caidan.cd_account_cpz where fawxuid=? and clid=? ";
		list.add(wxuid);
		list.add(wayid);
		if(starttime!=null&&starttime.trim().length()>0){
			sql+=" and createtime>=? ";
			list.add(starttime.trim());
		}
		if(endtime!=null&&endtime.trim().length()>0){
			sql+=" and createtime<=? ";
			list.add(endtime.trim());
		}
		return jdbcTemplate.query(sql, list.toArray(), new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDAccountCpz dl = new CDAccountCpz();
				dl.setCity(rs.getString("city"));
				dl.setClid(rs.getLong("clid"));
				dl.setFawxuid(rs.getLong("fawxuid"));
				dl.setId(rs.getLong("id"));
				dl.setName(rs.getString("name"));
				dl.setProvince(rs.getString("province"));
				dl.setStatus(rs.getString("status"));
				dl.setTelphone(rs.getString("telphone"));
				dl.setWxuid(rs.getLong("wxuid"));
				dl.setCreatetime(rs.getTimestamp("createtime"));
				return dl;
			}
		});
	}
	
	
	@Override
	public int findCpzTotalByFather(long wxuid, long wayid, String starttime, String endtime)
	{
		List<Object> list=new ArrayList<Object>();
		String sql="select count(*) from caidan.cd_account_cpz where fawxuid=? and clid=? ";
		list.add(wxuid);
		list.add(wayid);
		if(starttime!=null&&starttime.trim().length()>0){
			sql+=" and createtime>=? ";
			list.add(starttime.trim());
		}
		if(endtime!=null&&endtime.trim().length()>0){
			sql+=" and createtime<=? ";
			list.add(endtime.trim());
		}
		return jdbcTemplate.queryForInt(sql, list.toArray());
	}

	
}
