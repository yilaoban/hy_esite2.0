package com.huiyee.interact.setting.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.setting.dao.IHyUserLevelCodeDao;
import com.huiyee.interact.setting.model.HyUserLevelCode;

@SuppressWarnings("unchecked")
public class HyUserLevelCodeDaoImpl implements IHyUserLevelCodeDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private RowMapper rowMapper = new RowMapper() {
		@Override
		public Object mapRow(ResultSet rs, int index) throws SQLException {
			HyUserLevelCode code = new HyUserLevelCode();
			code.setId(rs.getLong("id"));
			code.setOwner(rs.getLong("owner"));
			code.setLevelid(rs.getLong("levelid"));
			code.setCode(rs.getString("code"));
			code.setStatus(rs.getInt("status"));
			code.setUpdatetime(rs.getTimestamp("updatetime"));
			return code;
		}
	};

	@Override
	public int findCodeCount(HyUserLevelCode code) {
		String sql = "SELECT count(*) FROM esite.es_hy_user_level_code WHERE levelid=?";
		List<Object> args = new ArrayList<Object>();
		args.add(code.getLevelid());
		if (code.getStatus() != null) {
			sql += " AND status=?";
			args.add(code.getStatus());
		}
		if (StringUtils.isNotBlank(code.getCode())) {
			sql += " AND code LIKE ?";
			args.add("%" + code.getCode() + "%");
		}
		return jdbcTemplate.queryForInt(sql, args.toArray());
	}

	@Override
	public List<HyUserLevelCode> findCodeList(HyUserLevelCode code, int start, int rows) {
		String sql = "SELECT * FROM esite.es_hy_user_level_code WHERE levelid=?";
		List<Object> args = new ArrayList<Object>();
		args.add(code.getLevelid());
		if (code.getStatus() != null) {
			sql += " AND status=?";
			args.add(code.getStatus());
		}
		if (StringUtils.isNotBlank(code.getCode())) {
			sql += " AND code LIKE ?";
			args.add("%" + code.getCode() + "%");
		}
		sql += " ORDER BY updatetime DESC limit ?,?";
		args.add(start);
		args.add(rows);
		return jdbcTemplate.query(sql, args.toArray(), rowMapper);
	}

	@Override
	public HyUserLevelCode findCode(long owner, String code) {
		String sql = "SELECT * FROM esite.es_hy_user_level_code WHERE owner=? and code=?";
		Object[] params = { owner, code };
		List<HyUserLevelCode> list = jdbcTemplate.query(sql, params, rowMapper);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int[] addCode(final long owner, final long levelid, final List<String> codes) {
		String sql = "INSERT IGNORE INTO esite.es_hy_user_level_code(owner,levelid,code,updatetime) VALUES(?,?,?,now())";
		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				String code = codes.get(index);
				ps.setLong(1, owner);
				ps.setLong(2, levelid);
				ps.setString(3, code);
			}

			@Override
			public int getBatchSize() {
				return codes.size();
			}

		});
	}

	@Override
	public int updateStatus(long id) {
		String sql = "UPDATE esite.es_hy_user_level_code SET status=1,updatetime=now() WHERE id=?";
		Object[] args = { id };
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int updateCode(HyUserLevelCode code) {
		// check if had this code
		String sql = "SELECT COUNT(*) FROM esite.es_hy_user_level_code WHERE levelid=? AND code=?";
		Object[] args = { code.getLevelid(), code.getCode() };
		int res = jdbcTemplate.queryForInt(sql, args);
		if (res > 0) {
			return -1;
		}

		sql = "UPDATE esite.es_hy_user_level_code set code=?,status=0 WHERE id=?";
		args = new Object[] { code.getCode(), code.getId() };
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int delCode(long id) {
		String sql = "DELETE FROM esite.es_hy_user_level_code WHERE id=?";
		Object[] args = { id };
		return jdbcTemplate.update(sql, args);
	}

}
