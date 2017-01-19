
package com.huiyee.interact.offcheck.dao;

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
import com.huiyee.interact.offcheck.model.OffCheckSource;

@SuppressWarnings("unchecked")
public class OffCheckSourceDaoImpl extends AbstractDao implements IOffCheckSourceDao {

	private RowMapper rowMapper = new RowMapper() {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			OffCheckSource os = new OffCheckSource();
			os.setId(rs.getLong("id"));
			os.setOwner(rs.getLong("owner"));
			os.setOcid(rs.getLong("ocid"));
			os.setFpageid(rs.getLong("fpageid"));
			os.setSpageid(rs.getLong("spageid"));
			os.setApageid(rs.getLong("apageid"));
			os.setFensi(rs.getString("fensi"));
			os.setName(rs.getString("name"));
			os.setCreatetime(rs.getTimestamp("createtime"));
			os.setGzeid(rs.getLong("gzeid"));
			os.setType(rs.getInt("type"));
			os.setDzpageid(rs.getLong("dzpageid"));
			os.setDtpageid(rs.getLong("dtpageid"));
			os.setJfpageid(rs.getLong("jfpageid"));
			os.setRmbjf(rs.getInt("rmbjf") / 100);
			return os;
		}

	};

	@Override
	public int findSourceTotalByOwner(long owner) {
		return getJdbcTemplate().queryForInt("select count(*) from es_off_check_source where owner=?", new Object[] { owner });
	}

	@Override
	public List<OffCheckSource> findSourceByOwner(long owner, int start, int size) {
		return getJdbcTemplate().query("select * from es_off_check_source where owner=? order by id desc limit ?,?", new Object[] { owner, start, size }, rowMapper);
	}

	@Override
	public List<OffCheckSource> findSourceByOwner(long owner) {
		return getJdbcTemplate().query("select * from es_off_check_source where owner=? order by id desc", new Object[] { owner }, rowMapper);
	}

	@Override
	public OffCheckSource findOffCheckSourceById(long sourceid, long owner) {
		List<OffCheckSource> list = getJdbcTemplate().query("select * from es_off_check_source where id=?", new Object[] { sourceid }, rowMapper);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public long saveOffCheckSource(final OffCheckSource ofcSource) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(
						"insert into es_off_check_source (owner,ocid,fpageid,spageid,apageid,fensi,name,createtime,gzeid,type,dzpageid,dtpageid,jfpageid,rmbjf) values(?,?,?,?,?,?,?,now(),?,?,?,?,?,?)",
						new String[] { "id" });
				int i = 1;
				ps.setLong(i++, ofcSource.getOwner());
				ps.setLong(i++, ofcSource.getOcid());
				ps.setLong(i++, ofcSource.getFpageid());
				ps.setLong(i++, ofcSource.getSpageid());
				ps.setLong(i++, ofcSource.getApageid());
				ps.setString(i++, ofcSource.getFensi());
				ps.setString(i++, ofcSource.getName());
				ps.setLong(i++, ofcSource.getGzeid());
				ps.setInt(i++, ofcSource.getType());
				ps.setLong(i++, ofcSource.getDzpageid());
				ps.setLong(i++, ofcSource.getDtpageid());
				ps.setLong(i++, ofcSource.getJfpageid());
				ps.setInt(i++, ofcSource.getRmbjf() * 100);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	public int updateOffCheckSource(OffCheckSource ofcSource) {
		return getJdbcTemplate().update("update es_off_check_source set fpageid=?,spageid=?,apageid=?,fensi=?,name=?,gzeid=?,type=?,dzpageid=?,dtpageid=?,jfpageid=?,rmbjf=? where id=?",
				new Object[] { ofcSource.getFpageid(), ofcSource.getSpageid(), ofcSource.getApageid(), ofcSource.getFensi(), ofcSource.getName(), ofcSource.getGzeid(), ofcSource.getType(),
				ofcSource.getDzpageid(), ofcSource.getDtpageid(), ofcSource.getJfpageid(), ofcSource.getRmbjf() * 100, ofcSource.getId() });
	}
	@Override
	public long delSource(long sourceid, long owner) {
		return getJdbcTemplate().update("delete from es_off_check_source where id=? and owner=?", new Object[] { sourceid, owner });
	}
}
