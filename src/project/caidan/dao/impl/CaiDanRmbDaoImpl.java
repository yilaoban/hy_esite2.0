
package project.caidan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import project.caidan.dao.ICaiDanRmbDao;
import project.caidan.model.CDRmb;
import project.caidan.model.CDRmbGet;

public class CaiDanRmbDaoImpl implements ICaiDanRmbDao
{

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findTotalRmbGet(String starttime,String endtime)
	{
		List<Object> param=new ArrayList<Object>();
		String sql = "select count(id) from caidan.cd_rmb_get";
		if(StringUtils.isNotEmpty(starttime)){
			sql+=" where createtime>=? ";
			param.add(starttime);
		}
		if(StringUtils.isNotEmpty(endtime)){
			if(param.size()==0){
				sql+=" where createtime<=? ";
			}else{
				sql+=" and createtime<=? ";
			}
			param.add(endtime);
		}
		return jdbcTemplate.queryForInt(sql,param.toArray());
	}

	@Override
	public List<CDRmbGet> findRmbGetList(String starttime,String endtime,int start, int size)
	{
		List<Object> param=new ArrayList<Object>();
		String sql = "select r.*,u.nickname from caidan.cd_rmb_get r left join esite.es_wx_user u on r.wxuid = u.id ";
		if(StringUtils.isNotEmpty(starttime)){
			sql+=" where createtime>=? ";
			param.add(starttime);
		}
		if(StringUtils.isNotEmpty(endtime)){
			if(param.size()==0){
				sql+=" where createtime<=? ";
			}else{
				sql+=" and createtime<=? ";
			}
			param.add(endtime);
		}
		sql+=" order by r.id desc limit ?,?";
		param.add(start);
		param.add(size);
		return jdbcTemplate.query(sql, param.toArray(), new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDRmbGet rmb = new CDRmbGet();
				rmb.setId(rs.getLong("r.id"));
				rmb.setRmb(rs.getInt("r.rmb"));
				rmb.setWxuid(rs.getLong("r.wxuid"));
				rmb.setCreatetime(rs.getTimestamp("r.createtime"));
				rmb.setStatus(rs.getString("r.status"));
				rmb.setErrreason(rs.getString("r.errmsg"));
				rmb.setNickname(rs.getString("u.nickname"));
				rmb.setMch_billno(rs.getString("mch_billno"));
				rmb.setSend_listid(rs.getString("send_listid"));
				return rmb;
			}

		});
	}

	@Override
	public int updateRmbGetById(CDRmbGet rmbGet)
	{
		String sql = "update caidan.cd_rmb_get set status = 'SND' where id = ?";
		Object[] params =
		{ rmbGet.getId() };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public CDRmb findCDRmbByWxuid(long wxuid)
	{
		String sql = "select * from caidan.cd_rmb where wxuid = ?";
		Object[] params =
		{ wxuid };
		List<CDRmb> list = jdbcTemplate.query(sql, params, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDRmb rmb = new CDRmb();
				rmb.setId(rs.getLong("id"));
				rmb.setTotal(rs.getInt("total"));
				rmb.setUsed(rs.getInt("used"));
				rmb.setWxuid(rs.getLong("wxuid"));
				return rmb;
			}

		});
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public int addRmbGet(long wxuid, float rmb)
	{
		return jdbcTemplate.update("insert into caidan.cd_rmb_get(rmb,wxuid,createtime)values(?,?,now())", new Object[]
		{ rmb * 100, wxuid });
	}
	
	@Override
	public List<CDRmbGet> findRmbGetList(long wxuid, int start, int size)
	{
		String sql = "select r.*,u.nickname from caidan.cd_rmb_get r left join esite.es_wx_user u on r.wxuid = u.id where r.wxuid=? order by r.id desc limit ?,?";
		Object[] params ={ wxuid,start, size };
		return jdbcTemplate.query(sql, params, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDRmbGet rmb = new CDRmbGet();
				rmb.setId(rs.getLong("r.id"));
				rmb.setRmb(rs.getInt("r.rmb"));
				rmb.setWxuid(rs.getLong("r.wxuid"));
				rmb.setCreatetime(rs.getTimestamp("r.createtime"));
				rmb.setStatus(rs.getString("r.status"));
				rmb.setErrreason(rs.getString("r.errmsg"));
				rmb.setNickname(rs.getString("u.nickname"));
				return rmb;
			}

		});
	}

	@Override
	public void updateRmbUsed(long wxuid, int used)
	{
		String sql = "update caidan.cd_rmb set used = used+? where wxuid = ?";
		Object[] params =
		{ used,wxuid};
		jdbcTemplate.update(sql, params);
		
	}

	@Override
	public int updateRmbGetStatusById(CDRmbGet rmbGet)
	{
		String sql = "update caidan.cd_rmb_get set status = 'SNN' where id = ?";
		Object[] params = { rmbGet.getId() };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public CDRmbGet findCDRmbGetById(long id)
	{
		String sql = "select * from caidan.cd_rmb_get where id = ?";
		Object[] params = { id };
		List<CDRmbGet> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDRmbGet rmb = new CDRmbGet();
				rmb.setId(rs.getLong("id"));
				rmb.setRmb(rs.getInt("rmb"));
				rmb.setWxuid(rs.getLong("wxuid"));
				return rmb;
			}
			
		});
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int saveCdRmbget(long wxuid, float rmb)
	{
		String sql = "insert into caidan.cd_rmb_get(rmb,wxuid,createtime,status,type,jf)values(?,?,now(),?,?,?)";
		return jdbcTemplate.update(sql, new Object[]{ rmb * 100, wxuid,"SND",1,rmb * 100});
	}
	
	@Override
	public List<CDRmbGet> findRmbGetList(String starttime, String endtime)
	{
		List<Object> param=new ArrayList<Object>();
		String sql = "select r.*,u.nickname from caidan.cd_rmb_get r left join esite.es_wx_user u on r.wxuid = u.id ";
		if(StringUtils.isNotEmpty(starttime)){
			sql+=" where createtime>=? ";
			param.add(starttime);
		}
		if(StringUtils.isNotEmpty(endtime)){
			if(param.size()==0){
				sql+=" where createtime<=? ";
			}else{
				sql+=" and createtime<=? ";
			}
			param.add(endtime);
		}
		sql+=" order by r.id desc";
		return jdbcTemplate.query(sql, param.toArray(), new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDRmbGet rmb = new CDRmbGet();
				rmb.setId(rs.getLong("r.id"));
				rmb.setRmb(rs.getInt("r.rmb"));
				rmb.setWxuid(rs.getLong("r.wxuid"));
				rmb.setCreatetime(rs.getTimestamp("r.createtime"));
				rmb.setStatus(rs.getString("r.status"));
				rmb.setErrreason(rs.getString("r.errmsg"));
				rmb.setNickname(rs.getString("u.nickname"));
				rmb.setMch_billno(rs.getString("mch_billno"));
				rmb.setSend_listid(rs.getString("send_listid"));
				return rmb;
			}

		});
	}

}
