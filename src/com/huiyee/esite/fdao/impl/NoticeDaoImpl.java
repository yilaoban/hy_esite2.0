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
import com.huiyee.esite.fdao.INoticeDao;
import com.huiyee.esite.model.Notice;

public class NoticeDaoImpl extends AbstractDao implements INoticeDao {

	private static final String FIND_NOTICE_BY_ID="select * from esite.es_feature_notice where id = ?";
	@Override
	public Notice findNoticeById(long id) {
		Object[] params={id};
		List<Notice> list = getJdbcTemplate().query(FIND_NOTICE_BY_ID, params, new NoticeRowmapper());
		if(list.size() >0 ){
			return list.get(0);
		}
		return null;
	}
	
	class NoticeRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Notice n = new Notice();
			n.setId(rs.getLong("id"));
			n.setTitle(rs.getString("title"));
			n.setContent(rs.getString("content"));
			n.setImgurl(rs.getString("imgurl"));
			n.setCountdownTime(rs.getTimestamp("countdowntime"));
			n.setCountdownWeekday(rs.getInt("countdownweekday"));
			return n;
		}
		
	}

	private static final String UPDATE_NOTICE="update esite.es_feature_notice set title = ?,content = ? ,imgurl = ?,CountdownTime = ?,CountdownWeekday = ? where id = ?";
	@Override
	public int updateNotice(Notice notice) {
		Object[] params = {
				notice.getTitle(),
				notice.getContent(),
				notice.getImgurl(),
				notice.getHour() + ":" + notice.getMinute() + ":"
						+ notice.getSecond(), notice.getCountdownWeekday(),
				notice.getId() };
		return getJdbcTemplate().update(UPDATE_NOTICE, params);
	}
	
	private static final String ADD_NOTICE="insert into esite.es_feature_notice(createtime) values(now())";
	@Override
	public long addNotice() {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(ADD_NOTICE, new String[] { "id" });
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
	
}
