package com.huiyee.esite.fdao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.ISinaShareListNoCheckDao;
import com.huiyee.esite.model.SinaCheckList;
import com.huiyee.esite.model.SinaNoCheckList;

public class SinaShareListNoCheckDaoImpl extends AbstractDao implements ISinaShareListNoCheckDao {

	private static final String SAVE_SHARE_LIST = "insert into es_feature_sina_share_list (name,pageid) values (now(),?)";

	public long save(final long pageid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_SHARE_LIST, new String[] { "id" });
				int i = 1;
				ps.setLong(i++, pageid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	private static final String FIND_SHAREID = "select * from es_feature_sina_share_list where id= ? ";

	public SinaNoCheckList findShareid(long fid) {
		List<SinaNoCheckList> list = getJdbcTemplate().query(FIND_SHAREID, new Object[] { fid }, new MyRowMapper());
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	class MyRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			SinaNoCheckList scl = new SinaNoCheckList();
			scl.setId(rs.getLong("id"));
			scl.setPageid(rs.getLong("pageid"));
			scl.setShareid(rs.getLong("shareid"));
			scl.setSize(rs.getInt("size"));
			scl.setName(rs.getString("name"));
			return scl;
		}
	}

	private static final String UPDATE_SNL = "update es_feature_sina_share_list set name=?,size=?,shareid=? where id=? ";

	public void updateSnl(SinaNoCheckList snl) {
		getJdbcTemplate().update(UPDATE_SNL, new Object[] { snl.getName(), snl.getSize(), snl.getShareid(),snl.getId() });
	}
}
