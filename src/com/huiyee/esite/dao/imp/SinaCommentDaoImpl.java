package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.ISinaCommentDao;

public class SinaCommentDaoImpl extends AbstractDao implements ISinaCommentDao{
	
	private static final String UPDATE_SHARE_COUNT="update esite.es_feature_sina_share set sharecount = sharecount + 1 where id = ?";
	@Override
	public int updateShareCount(long shareid) {
		Object[] params={shareid};
		return getJdbcTemplate().update(UPDATE_SHARE_COUNT,params);
	}
	
	private static final String SAVE_SINA_COMMENT="insert into esite.es_feature_sina_comment_record(shareid,wbuid,wbid,fatherwbid,content,terminal,ip,createtime,source) values (?,?,?,?,?,?,?,now(),?)";
	@Override
	public long saveSinaCommentDao(final long shareid,final long wbuid,final long fatherwbid,
		final long wbid,final String content,final String terminal,final String ip,final String source) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_SINA_COMMENT,
						new String[] { "id" });
				ps.setLong(1, shareid);
				ps.setLong(2, wbuid);
				ps.setLong(3, wbid);
				ps.setLong(4, fatherwbid);
				ps.setString(5, content);
				ps.setString(6, terminal);
				ps.setString(7, ip);
				ps.setString(8, source);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
}
