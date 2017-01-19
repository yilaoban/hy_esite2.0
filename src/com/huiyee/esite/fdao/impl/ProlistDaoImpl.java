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
import com.huiyee.esite.fdao.IProlistDao;
import com.huiyee.esite.model.Prolist;

public class ProlistDaoImpl extends AbstractDao implements IProlistDao {
	private static final String FIND_PROLIST_BY_FID = "select * from es_feature_prolist where prolist_listid = ? and status!='DEL' order by idx asc ";

	public List<Prolist> findProlistByfid(long fid) {
		return getJdbcTemplate().query(FIND_PROLIST_BY_FID, new Object[] { fid }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Prolist pl = new Prolist();
				pl.setId(rs.getLong("id"));
				pl.setTitle(rs.getString("title"));
				pl.setContent(rs.getString("content"));
				pl.setProlist_listid(rs.getLong("prolist_listid"));
				pl.setIdx(rs.getInt("idx"));
				return pl;
			}
		});
	}

	private static final String DELETE_BY_ID = "update es_feature_prolist set status='DEL' where id=?";

	public void deleteById(long id) {
		getJdbcTemplate().update(DELETE_BY_ID, new Object[] { id });
	}

	private static final String ADD_PROLIST = "insert into es_feature_prolist (title,prolist_listid,status,content,idx) value(?,?,'CMP',?,?)";

	public long addProlist(final String title, final long fid,final String content,final int idx) {
		// TODO Auto-generated method stubKeyHolder keyHolder = new
		// GeneratedKeyHolder();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(ADD_PROLIST, new String[] { "id" });
				int i = 1;
				ps.setString(i++, title);
				ps.setLong(i++, fid);
				ps.setString(i++, content);
				ps.setInt(i++, idx);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	private static final String UPDATR_PROLIST_TITLE = "update es_feature_prolist set title=?,content=?,status='CMP',idx=? where id= ? ";

	public void updateProlistTitle(String title, long id,String content,int idx) {
		getJdbcTemplate().update(UPDATR_PROLIST_TITLE, new Object[] { title,content,idx, id });
	}
}
