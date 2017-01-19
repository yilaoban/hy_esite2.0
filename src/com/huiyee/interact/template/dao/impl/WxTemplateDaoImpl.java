package com.huiyee.interact.template.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.template.dao.IWxTemplateDao;
import com.huiyee.interact.template.model.WxTemplate;

@SuppressWarnings("unchecked")
public class WxTemplateDaoImpl implements IWxTemplateDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private RowMapper templateRowMapper() {
		return new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxTemplate wt = new WxTemplate();
				wt.setId(rs.getLong("id"));
				wt.setOwner(rs.getLong("owner"));
				wt.setType(rs.getString("type"));
				wt.setEntityid(rs.getLong("entityid"));
				wt.setRemark(rs.getString("remark"));

				wt.setStore_id(rs.getLong("store_id"));
				wt.setMpid(rs.getLong("mpid"));
				wt.setTemplate_id(rs.getString("template_id"));
				wt.setData(rs.getString("data"));
				wt.setUrl(rs.getString("url"));
				wt.setDelay(rs.getInt("delay"));
				Date sendtime = rs.getTimestamp("sendtime");
				if (sendtime != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					wt.setSendtime(sdf.format(sendtime));
				}
				wt.setUpdatetime(rs.getTimestamp("updatetime"));
				return wt;
			}

		};
	}

	@Override
	public int getTemplateCount(long owner) {
		String sql = "SELECT COUNT(*) FROM crm.crm_wx_template WHERE owner=?";
		Object[] param = { owner };
		return jdbcTemplate.queryForInt(sql, param);
	}

	@Override
	public int getTemplateCount(long owner, String type, long entityid) {
		String sql = "SELECT COUNT(*) FROM crm.crm_wx_template WHERE owner=? AND type=? AND entityid=?";
		Object[] param = { owner, type, entityid };
		return jdbcTemplate.queryForInt(sql, param);
	}

	@Override
	public int getTemplateCount(long mpid, String template_id) {
		String sql = "SELECT COUNT(*) FROM crm.crm_wx_template WHERE mpid=? AND template_id=?";
		Object[] param = { mpid, template_id };
		return jdbcTemplate.queryForInt(sql, param);
	}

	@Override
	public List<WxTemplate> getTemplateList(long owner, int start, int rows) {
		String sql = "SELECT * FROM crm.crm_wx_template WHERE owner=? LIMIT ?,?";
		Object[] param = { owner, start, rows };
		return jdbcTemplate.query(sql, param, templateRowMapper());
	}

	@Override
	public List<WxTemplate> getTemplateList(long owner, String type, long entityid, int start, int rows) {
		String sql = "SELECT * FROM crm.crm_wx_template WHERE owner=? AND type=? AND entityid=? LIMIT ?,?";
		Object[] param = { owner, type, entityid, start, rows };
		return jdbcTemplate.query(sql, param, templateRowMapper());
	}

	@Override
	public List<WxTemplate> getTemplateList(long owner, String type) {
		String sql = "SELECT * FROM crm.crm_wx_template WHERE owner=? AND type=?";
		Object[] param = { owner, type };
		return jdbcTemplate.query(sql, param, templateRowMapper());
	}

	@Override
	public List<WxTemplate> getTemplateList(long owner, String type, long entityid) {
		String sql = "SELECT * FROM crm.crm_wx_template WHERE owner=? AND type=? AND entityid=?";
		Object[] param = { owner, type, entityid };
		return jdbcTemplate.query(sql, param, templateRowMapper());
	}

	@Override
	public WxTemplate getTemplate(long id) {
		String sql = "SELECT * FROM crm.crm_wx_template WHERE id=?";
		Object[] param = { id };
		List<WxTemplate> list = jdbcTemplate.query(sql, param, templateRowMapper());
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public WxTemplate getTemplate(long mpid, long store_id) {
		String sql = "SELECT * FROM crm.crm_wx_template WHERE mpid=? AND store_id=?";
		Object[] param = { mpid, store_id };
		List<WxTemplate> list = jdbcTemplate.query(sql, param, templateRowMapper());
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int addTemplate(WxTemplate wt) {
		String sql = "INSERT INTO crm.crm_wx_template(owner,type,entityid,remark,store_id,mpid,template_id,data,url,delay,sendtime,updatetime,dtype) VALUES(?,?,?,?,?,?,?,?,?,?,?,now(),?)";
		Object[] params = { wt.getOwner(), wt.getType(), wt.getEntityid(), wt.getRemark(), wt.getStore_id(), wt.getMpid(), wt.getTemplate_id(), wt.getData(), wt.getUrl(), wt.getDelay(),
				wt.getSendtime(),wt.getDtype() };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int[] addTemplate(final List<WxTemplate> list) {
		String sql = "INSERT INTO crm.crm_wx_template(owner,type,entityid,remark,store_id,mpid,template_id,data,url,delay,sendtime,updatetime) VALUES(?,?,?,?,?,?,?,?,?,?,?,now())";
		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public int getBatchSize() {
				return list.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				WxTemplate tpl = list.get(index);
				int i = 1;
				ps.setLong(i++, tpl.getOwner());
				ps.setString(i++, tpl.getType());
				ps.setLong(i++, tpl.getEntityid());
				ps.setString(i++, tpl.getRemark());
				ps.setLong(i++, tpl.getStore_id());
				ps.setLong(i++, tpl.getMpid());
				ps.setString(i++, tpl.getTemplate_id());
				ps.setString(i++, tpl.getData());
				ps.setString(i++, tpl.getUrl());
				ps.setInt(i++, tpl.getDelay());
				ps.setString(i++, tpl.getSendtime());
			}

		});
	}

	@Override
	public int updateTemplate(WxTemplate wt) {
		String sql = "UPDATE crm.crm_wx_template SET type=?,entityid=?,remark=?,store_id=?,mpid=?,data=?,url=?,delay=?,sendtime=?,updatetime=now(),`status`='NTP',dtype=? WHERE id=?";
		Object[] params = { wt.getType(), wt.getEntityid(), wt.getRemark(), wt.getStore_id(), wt.getMpid(), wt.getData(), wt.getUrl(), wt.getDelay(), wt.getSendtime(),wt.getDtype(),wt.getId() };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int delTemplate(long id) {
		String sql = "DELETE FROM crm.crm_wx_template WHERE id=?";
		Object[] params = { id };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int delAllTemplate(long mp_id) {
		String sql = "DELETE FROM crm.crm_wx_template WHERE owner=?";
		Object[] params = { mp_id };
		return jdbcTemplate.update(sql, params);
	}

}
