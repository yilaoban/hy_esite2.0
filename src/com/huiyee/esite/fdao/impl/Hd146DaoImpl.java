package com.huiyee.esite.fdao.impl;

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

import com.google.gson.Gson;
import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.IHd146Dao;
import com.huiyee.interact.xc.model.CheckinConfig;
import com.huiyee.interact.xc.model.CommentConfig;
import com.huiyee.interact.xc.model.InviteConfig;
import com.huiyee.interact.xc.model.LotteryConfig;
import com.huiyee.interact.xc.model.Xc;

public class Hd146DaoImpl extends AbstractDao implements IHd146Dao {
	Gson gson = new Gson();

	private static final String SAVE_XC="insert into es_feature_interact_xc(pageid,createtime) values(?,now())";
	@Override
	public long saveXc(final long pageid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
                PreparedStatement ps = arg0.prepareStatement(SAVE_XC, new String[] { "id" });
                int i = 1;
                ps.setLong(i++, pageid);
                return ps;
            }
        }, keyHolder);
        long id = keyHolder.getKey().intValue();
        return id;
	}
	
	private static final String FIND_XC_LIST="select id,title from es_interact_xc where owner = ? order by id desc";
	@Override
	public List<Xc> findXcList(long owner) {
		Object[] params={owner};
		return getJdbcTemplate().query(FIND_XC_LIST,params,new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Xc xc = new Xc();
				xc.setId(rs.getLong("id"));
				xc.setTitle(rs.getString("title"));
				return xc;
			}
			
		});
	}
	
	private static final String FIND_XCID_BY_FID="select xcid from es_feature_interact_xc where id = ?";
	@Override
	public long findXcidByFid(long fid) {
		Object[] params={fid};
		try {
			return getJdbcTemplate().queryForLong(FIND_XCID_BY_FID,params);
		} catch (DataAccessException e) {
			return 0;
		}
	}
	
	private static final String UPDATE_XCID="update es_feature_interact_xc set xcid = ? where id = ?";
	@Override
	public int updateXcid(long xcid, long fid) {
		Object[] params={xcid,fid};
		return getJdbcTemplate().update(UPDATE_XCID, params);
	}
	
	private static final String FIND_XC_BY_ID="select t2.* from es_feature_interact_xc t1 join es_interact_xc t2 on t1.xcid = t2.id where t1.id = ?";
	@Override
	public Xc findXcById(long fid) {
		Object[] params={fid};
		List<Xc> list = getJdbcTemplate().query(FIND_XC_BY_ID, params,new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Xc xc = new Xc();
				xc.setId(rs.getLong("id"));
				xc.setTitle(rs.getString("title"));
				xc.setOwner(rs.getLong("owner"));
				xc.setNeedcheckin(rs.getString("needcheckin"));
				xc.setNeedcomment(rs.getString("needcomment"));
				xc.setNeedinvite(rs.getString("needinvite"));
				xc.setNeedlottery(rs.getString("needlottery"));
				xc.setCheckinconfig(gson.fromJson(rs.getString("checkinconfig"), CheckinConfig.class));
				xc.setCommentconfig(gson.fromJson(rs.getString("commentconfig"), CommentConfig.class));
				xc.setInviteconfig(gson.fromJson(rs.getString("inviteconfig"), InviteConfig.class));
				xc.setLotteryconfig(gson.fromJson(rs.getString("lotteryconfig"), LotteryConfig.class));
				xc.setCreatetime(rs.getTimestamp("createtime"));
				return xc;
			}
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
}
