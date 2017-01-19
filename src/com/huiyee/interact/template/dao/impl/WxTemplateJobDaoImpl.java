package com.huiyee.interact.template.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.huiyee.interact.template.dao.IWxTemplateJobDao;
import com.huiyee.interact.template.model.WxTemplateJob;

@SuppressWarnings("unchecked")
public class WxTemplateJobDaoImpl implements IWxTemplateJobDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int getJobCount(long mpid) {
		String sql = "SELECT count(*) FROM crm.crm_wx_template_job WHERE mpid=?";
		Object[] params = { mpid };
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public int getJobCount(long mpid, String type, long entityid) {
		String sql = "SELECT count(*) FROM crm.crm_wx_template_job WHERE mpid=? AND type=? AND entityid=?";
		Object[] params = { mpid, type, entityid };
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public int getJobCount(long mpid, List<String> types, long entityid) {
		String sql = "SELECT COUNT(*) FROM crm.crm_wx_template_job WHERE mpid=:mpid AND type IN (:types) AND entityid=:entityid";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mpid", mpid);
		param.put("types", types);
		param.put("entityid", entityid);
		return new NamedParameterJdbcTemplate(jdbcTemplate).queryForInt(sql, param);
	}

	private RowMapper rowMapper = new RowMapper() {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			WxTemplateJob wtj = new WxTemplateJob();
			wtj.setId(rs.getLong("id"));
			wtj.setMpid(rs.getLong("mpid"));
			wtj.setType(rs.getString("type"));
			wtj.setEntityid(rs.getLong("entityid"));
			wtj.setRemark(rs.getString("remark"));
			wtj.setMsgid(rs.getLong("msgid"));
			wtj.setTouser(rs.getString("touser"));
			wtj.setTemplate_id(rs.getString("template_id"));
			wtj.setUrl(rs.getString("url"));
			wtj.setData(rs.getString("data"));
			wtj.setCreatetime(rs.getTimestamp("createtime"));
			wtj.setErrcode(rs.getInt("errcode"));
			wtj.setErrmsg(rs.getString("errmsg"));
			wtj.setSent(rs.getString("sent"));
			wtj.setSendtime(rs.getTimestamp("sendtime"));
			wtj.setOpened(rs.getString("opened"));
			wtj.setOpentime(rs.getTimestamp("opentime"));
			return wtj;
		}

	};

	@Override
	public List<WxTemplateJob> getJobList(long mpid, int start, int rows) {
		String sql = "SELECT * FROM crm.crm_wx_template_job WHERE mpid=? ORDER BY sendtime desc LIMIT ?,?";
		Object[] params = { mpid, start, rows };
		return jdbcTemplate.query(sql, params, rowMapper);
	}

	@Override
	public List<WxTemplateJob> getJobList(long mpid, String type, long entityid, int start, int rows) {
		String sql = "SELECT * FROM crm.crm_wx_template_job WHERE mpid=? AND type=? AND entityid=? ORDER BY sendtime desc LIMIT ?,?";
		Object[] params = { mpid, type, entityid, start, rows };
		return jdbcTemplate.query(sql, params, rowMapper);
	}

	@Override
	public List<WxTemplateJob> getJobList(long mpid, List<String> types, long entityid, int start, int rows) {
		String sql = "SELECT * FROM crm.crm_wx_template_job WHERE mpid=:mpid AND type IN (:types) AND entityid=:entityid ORDER BY sendtime desc LIMIT :start,:rows";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mpid", mpid);
		param.put("types", types);
		param.put("entityid", entityid);
		param.put("start", start);
		param.put("rows", rows);
		return new NamedParameterJdbcTemplate(jdbcTemplate).query(sql, param, rowMapper);
	}

	@Override
	public int addTemplateJob(WxTemplateJob job) {
		String sql = "INSERT INTO crm.crm_wx_template_job(mpid,type,entityid,remark,touser,template_id,url,data,createtime,hyuid,wxuid) VALUES(?,?,?,?,?,?,?,?,now(),?,?)";
		Object[] params = { job.getMpid(), job.getType(), job.getEntityid(), job.getRemark(), job.getTouser(), job.getTemplate_id(), job.getUrl(), job.getData(),job.getHyuid(),job.getWxuid() };
		return jdbcTemplate.update(sql, params);
	}

}
