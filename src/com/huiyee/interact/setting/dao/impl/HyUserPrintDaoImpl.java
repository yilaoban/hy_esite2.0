package com.huiyee.interact.setting.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.setting.dao.IHyUserPrintDao;
import com.huiyee.interact.setting.model.HyUserPrint;

@SuppressWarnings("unchecked")
public class HyUserPrintDaoImpl implements IHyUserPrintDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public HyUserPrint findPrint(long owner) {
		String sql = "SELECT * FROM esite.es_hy_user_print WHERE owner=?";
		Object[] args = { owner };
		List<HyUserPrint> list = jdbcTemplate.query(sql, args, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				HyUserPrint p = new HyUserPrint();
				p.setId(rs.getLong("id"));
				p.setOwner(rs.getLong("owner"));
				p.setVm(rs.getString("vm"));
				p.setName(rs.getString("name"));
				p.setLogo(rs.getString("logo"));
				p.setUrl(rs.getString("url"));
				return p;
			}

		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int addPrint(HyUserPrint print) {
		String sql = "INSERT INTO esite.es_hy_user_print(owner,vm,name) VALUES(?,?,?) ON DUPLICATE KEY UPDATE vm=?,name=?";
		Object[] args = { print.getOwner(), print.getVm(), print.getName(), print.getVm(), print.getName() };
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int updatePrint(HyUserPrint print) {
		String sql = "UPDATE esite.es_hy_user_print SET logo=?,url=? WHERE id=?";
		Object[] args = { print.getLogo(), print.getUrl(), print.getId() };
		return jdbcTemplate.update(sql, args);
	}

}
