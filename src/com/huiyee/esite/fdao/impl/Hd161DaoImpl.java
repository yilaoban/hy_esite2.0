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

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.IHd161Dao;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.HD154Model;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.interact.lottery.model.Lottery;

public class Hd161DaoImpl extends AbstractDao implements IHd161Dao {

	private static final String SAVE_FEATRUE_INTERACT = "insert into esite.es_feature_interact_lottery (pageid,type,createtime,lotteryid) values(?,?,now(),?)";
	@Override
	public long saveFeatureInteractLottery(final long pageid,final String type,final long lotteryid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
                PreparedStatement ps = arg0.prepareStatement(SAVE_FEATRUE_INTERACT, new String[] { "id" });
                int i = 1;
                ps.setLong(i++, pageid);
                ps.setString(i++, type);
                ps.setLong(i++, lotteryid);
                return ps;
            }
        }, keyHolder);
        long id = keyHolder.getKey().intValue();
        return id;
	}
	
	private static final String FIND_LOTTERY_ID_BY_FID="select lotteryid from es_feature_interact_lottery where id = ?";
	@Override
	public long findLotteryIdByFid(long fid)
	{
		Object[] params={fid};
		try
		{
			return getJdbcTemplate().queryForLong(FIND_LOTTERY_ID_BY_FID,params);
		} catch (Exception e)
		{
			return 0;
		}
	}
	
	private static final String UPDATE_LOTTERY="update es_interact_lottery set name = ?,starttime = ?,endtime = ?,usertotal=?,userdaynum=?,zjl=? where id = ?";
	@Override
	public int updateLottery(Lottery lottery)
	{
		Object[] param =
		{ lottery.getName(), lottery.getStarttime(), lottery.getEndtime(), lottery.getUsertotal(), lottery.getUserdaynum(), lottery.getZjl(), lottery.getId() };
		return getJdbcTemplate().update(UPDATE_LOTTERY, param);
	}
}
