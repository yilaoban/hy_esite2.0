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
import com.huiyee.esite.fdao.IHd140Dao;
import com.huiyee.esite.fdao.impl.Hd126DaoImpl.FeatureRowmapper;
import com.huiyee.esite.model.JournalModel;
import com.huiyee.esite.model.Module;

public class Hd140DaoImpl extends AbstractDao implements IHd140Dao{

	private static final String SAVE_FEATRUE_INTERACT = "insert into esite.es_feature_interact_journal (pageid,createtime) values(?,now())";
	@Override
	public long saveFeatureInteractJournal(final long pageid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
                PreparedStatement ps = arg0.prepareStatement(SAVE_FEATRUE_INTERACT, new String[] { "id" });
                int i = 1;
                ps.setLong(i++, pageid);
                return ps;
            }
        }, keyHolder);
        long id = keyHolder.getKey().intValue();
        return id;
	}
	
	class FeatureJournalRowmapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			JournalModel jm = new JournalModel();
			jm.setId(rs.getLong("id"));
			jm.setTitle(rs.getString("title"));
			return jm;
		}
	}
	
	private static final String FIND_INTERACT_JOURNAL_BY_OWNER = "select a.id,a.title from esite.es_interact_journal a where a.ownerid = ? and a.status != 'DEL'";
	@Override
	public List<JournalModel> findInteractJournalByOwner(long ownerid) {
		Object[] params = { ownerid };
		return getJdbcTemplate().query(FIND_INTERACT_JOURNAL_BY_OWNER, params, new FeatureJournalRowmapper());
	}
	
	private static final String FIND_JID_BY_FID = "select a.jid from esite.es_feature_interact_journal a where id = ? ";
	@Override
	public Module findJidByFid(long fid) {
		List<Module> list = getJdbcTemplate().query(FIND_JID_BY_FID, new Object[] { fid }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Module m = new Module();
				m.setId(rs.getLong("jid"));
				return m;
			}
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	private static final String UPDATE_FEATURE_INTERACT_JOURNAL = "update esite.es_feature_interact_journal a set a.jid = ? where a.id = ? ";
	@Override
	public int updateFeatureIneractJournal(long jid, long fid) {
		Object[] params = { jid,fid};
		return getJdbcTemplate().update(UPDATE_FEATURE_INTERACT_JOURNAL, params);
	}
	
	class JournalMapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			JournalModel model=new JournalModel();
			model.setId(rs.getLong("id"));
			model.setTitle(rs.getString("title"));
			model.setIsshare(rs.getString("isshare"));
			model.setBalance(rs.getInt("balance"));
			model.setCreatetime(rs.getTimestamp("createtime"));
			return model;
		}
	}
	
	private static final String FIND_INTERACT_JOURNAL_BY_ID = "select s.* from esite.es_feature_interact_journal a join esite.es_interact_journal s on a.jid= s.id where a.id = ?";
	@Override
	public JournalModel findFeatureInteractJournalById(long fid) {
		Object[] params = { fid };
		List<JournalModel> list = getJdbcTemplate().query(FIND_INTERACT_JOURNAL_BY_ID, params, new JournalMapper());
		if(list !=null && list.size()>0){
	 		return list.get(0);
	 	}
		return null;
	}
	
}
