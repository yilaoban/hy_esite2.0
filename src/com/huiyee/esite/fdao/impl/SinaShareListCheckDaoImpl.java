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
import com.huiyee.esite.fdao.ISinaShareListCheckDao;
import com.huiyee.esite.model.SinaCheckList;
import com.huiyee.esite.model.SinaShare;

public class SinaShareListCheckDaoImpl extends AbstractDao implements ISinaShareListCheckDao {

    private static final String SAVE_CHECK_LIST = "insert into es_feature_sina_share_check_list (pageid,createtime,status) values (?,now(),'CMP')";

    public long saveCheckList(final long pageid)
    {
	KeyHolder keyHolder = new GeneratedKeyHolder();
	getJdbcTemplate().update(new PreparedStatementCreator()
	{
	    @Override
	    public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
	    {
		PreparedStatement ps = arg0.prepareStatement(SAVE_CHECK_LIST, new String[] { "id" });
		int i = 1;
		ps.setLong(i++, pageid);
		return ps;
	    }
	}, keyHolder);
	long id = keyHolder.getKey().intValue();
	return id;
    }

    private static final String FIND_SHAREID = "select * from es_feature_sina_share_check_list where id= ? ";

    public SinaCheckList findShareid(long fid)
    {
	List<SinaCheckList> list = getJdbcTemplate().query(FIND_SHAREID, new Object[] { fid }, new MyRowMapper());
	if (list.size() > 0)
	{
	    return list.get(0);
	}
	return null;
    }

    class MyRowMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int arg1) throws SQLException
	{
	    SinaCheckList scl = new SinaCheckList();
	    scl.setId(rs.getLong("id"));
	    scl.setPageid(rs.getLong("pageid"));
	    scl.setCreatetime(rs.getTimestamp("createtime"));
	    scl.setShareid(rs.getLong("shareid"));
	    scl.setStatus(rs.getString("status"));
	    return scl;
	}
    }

    private static final String UPDATE_SHARE_ID = "update es_feature_sina_share_check_list set shareid=? where id=? ";

    public void updateShareId(long fid, long shareid)
    {
	getJdbcTemplate().update(UPDATE_SHARE_ID, new Object[] { shareid, fid });
    }
}
