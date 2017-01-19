package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.ISinaForwardDao;

public class SinaForwardDaoImpl extends AbstractDao implements ISinaForwardDao {

	private static final String UPDATE_SHARE_COUNT="update esite.es_feature_sina_share set sharecount = sharecount + 1 where id = ?";
	@Override
	public int updateShareCount(long shareid) {
		Object[] params={shareid};
		return getJdbcTemplate().update(UPDATE_SHARE_COUNT,params);
	}
	
	private static final String SAVE_SINA_FORWARD="insert into esite.es_feature_sina_forward_record(shareid,wbuid,wbid,mid,content,terminal,createtime,ip,source) values (?,?,?,?,?,?,now(),?,?)";
	@Override
	public long saveSinaForwardDao(final long shareid,final long wbuid,final long wbid,
		final long mid,final String content,final String terminal,final String ip,final String source) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_SINA_FORWARD,
						new String[] { "id" });
				ps.setLong(1, shareid);
				ps.setLong(2, wbuid);
				ps.setLong(3, wbid);
				ps.setLong(4, mid);
				ps.setString(5, content);
				ps.setString(6, terminal);
				ps.setString(7, ip);
				ps.setString(8,source);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

}
