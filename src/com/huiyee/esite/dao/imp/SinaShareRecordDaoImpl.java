package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.ISinaShareRecordDao;
import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.model.SinaShareRecord;
import com.huiyee.esite.model.SinaUser;
import com.huiyee.tfmodel.HyWbSrc;

public class SinaShareRecordDaoImpl extends AbstractDao implements ISinaShareRecordDao {

	@Override
	public List<SinaShareRecord> findRecordByFid(long fid, int page) {
		return getJdbcTemplate().query("select * from esite.es_feature_sina_share_record where shareid=? limit ?,?", new Object[] { fid, page * 1000, 1000 }, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SinaShareRecord sr = new SinaShareRecord();
				sr.setWbid(rs.getLong("wbid"));
				sr.setWbuid(rs.getLong("wbuid"));
				return sr;
			}

		});
	}

	@Override
	public void updateRecord(long id, HyWbSrc status) {
		getJdbcTemplate().update("update esite.es_feature_sina_share_record set repostsCount=?,commentsCount=? where wbid=?",
				new Object[] { status.getRepostsCount(), status.getCommentsCount(), id });
	}

	private static final String SAVE_SINA_SHARE_RECORD = "insert into esite.es_feature_sina_share_record(shareid,wbuid,wbid,content,imgPath,mimgPath,simgPath,createtime,terminal,ip,source) values(?,?,?,?,?,?,?,now(),?,?,?)";

	@Override
	public long saveSinaShareRecord(final long shareid,final HyWbSrc status,final String bimg,final String mimg,final String simg,final String terminal,final String ip,final String source) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_SINA_SHARE_RECORD,
						new String[] { "id" });
				ps.setLong(1, shareid);
				ps.setLong(2, Long.parseLong(status.getUser().getId()));
				ps.setLong(3, Long.parseLong(status.getId()));
				ps.setString(4, status.getText());
				ps.setString(5, bimg);
				ps.setString(6, mimg);
				ps.setString(7, simg);
				ps.setString(8, terminal);
				ps.setString(9, ip);
				ps.setString(10, source);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	private static final String FIND_SIZE = "select l.size from esite.es_feature_sina_share_list l where l.id = ?";
	private static final String FIND_WEIBO = "select * from  es_feature_sina_share_record record join es_feature_sina_user user on record.wbuid=user.wbuid where record.shareid= ? order by record.id desc limit ?";

	@Override
	public List<SinaChecklistRecord> findRecordByShareid(long shareid, long fid) {
		int size = 0;
		try {
			size = getJdbcTemplate().queryForInt(FIND_SIZE, new Object[] { fid });
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return getJdbcTemplate().query(FIND_WEIBO, new Object[] { shareid, size }, new SinaChecklistRecordRowmapper());
	}

	class SinaChecklistRecordRowmapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SinaChecklistRecord scr = new SinaChecklistRecord();
			SinaUser user = new SinaUser();
			user.setWbuid(rs.getLong("user.wbuid"));
			user.setNickname(rs.getString("user.nickname"));
			user.setImageurl(rs.getString("user.imageurl"));
			scr.setUser(user);
			scr.setContent(rs.getString("record.content"));
			scr.setImgPath(rs.getString("record.imgPath"));
			scr.setCreatetime(rs.getTimestamp("record.createtime"));
			scr.setAttitudesCount(rs.getInt("record.attitudesCount"));
			scr.setCommentsCount(rs.getInt("record.commentsCount"));
			scr.setRepostsCount(rs.getInt("record.repostsCount"));
			scr.setMimgPath(rs.getString("record.mimgPath"));
			scr.setSimgPath(rs.getString("record.simgPath"));
			return scr;
		}

	}

	private static final String FIND_EXPORT = "select record.*,user.*  from es_site_group  sg join es_site site on site.sitegroupid=sg.id join es_page page on page.siteid=site.id join es_feature_sina_share share on  share.pageid=page.id join es_feature_sina_share_record record on record.shareid=share.id left outer join es_feature_sina_user user on user.wbuid=record.wbuid where sg.id= ? and sg.ownerid=?";

	/**
	 * 导出用户新浪分享
	 */
	public List<SinaChecklistRecord> findExport(long sitegroupid, long ownerid) {
		return getJdbcTemplate().query(FIND_EXPORT, new Object[] { sitegroupid, ownerid }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SinaChecklistRecord scr = new SinaChecklistRecord();
				SinaUser user = new SinaUser();
				user.setWbuid(rs.getLong("user.wbuid"));
				user.setNickname(rs.getString("user.nickname"));
				user.setImageurl(rs.getString("user.imageurl"));
				scr.setUser(user);
				scr.setContent(rs.getString("record.content"));
				scr.setImgPath(rs.getString("record.imgPath"));
				scr.setCreatetime(rs.getTimestamp("record.createtime"));
				scr.setAttitudesCount(rs.getInt("record.attitudesCount"));
				scr.setCommentsCount(rs.getInt("record.commentsCount"));
				scr.setRepostsCount(rs.getInt("record.repostsCount"));
				scr.setWbid(rs.getLong("record.wbid"));
				return scr;
			}
		});
	}

}
