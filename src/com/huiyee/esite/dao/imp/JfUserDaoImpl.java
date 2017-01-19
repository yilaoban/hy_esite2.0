package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.IJfUserDao;
import com.huiyee.esite.model.UserBalance;


public class JfUserDaoImpl implements IJfUserDao
{
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findUserBalanceTotal(long owner,long mpid,long hyuid,String nickname,Date startTime,Date endTime,int type)
	{
		String sql = "SELECT COUNT(hu.id) FROM es_hy_user hu join es_wx_user wx on hu.wxuid = wx.id LEFT JOIN es_hy_user_balance b  on b.hyuid = hu.id WHERE wx.nickname is not null and hu.`owner` = ? AND wx.mpid = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(owner);
		list.add(mpid);
		if(hyuid>0){
			sql +=" and hu.id like ?";
			list.add("%" + hyuid + "%");
		}
		if(StringUtils.isNotBlank(nickname)){
			nickname = "%" + nickname + "%";
			sql +=" and (hu.nickname like ? or wx.nickname like ? )";
			list.add(nickname);list.add(nickname);
		}
		if(type == 1){//增加积分
			if(endTime == null && startTime == null){
				sql = sql + " and b.lastaddtime is not null ";
			}else{
				if(endTime != null){
					sql = sql + " and b.lastaddtime < ? ";
					list.add(endTime);
				}
				if(startTime != null){
					sql = sql + " and b.lastaddtime > ?";
					list.add(startTime);
				}
			} 
		}else if(type == 2){//消耗积分
			if(endTime == null && startTime == null){
				sql = sql + " and b.lastusetime is not null ";
			}else{
				if(endTime != null){
					sql = sql + " and b.lastusetime < ? ";
					list.add(endTime);
				} 
				if(startTime != null){
					sql = sql + " and b.lastusetime > ?";
					list.add(startTime);
				}
			} 
		}else if(type == 3){//没有增加积分
			if(endTime == null && startTime == null){
				sql = sql + " and b.lastaddtime is null";
			}else if(endTime != null && startTime != null){
				sql = sql + " and (b.lastaddtime < ? or b.lastaddtime > ? or b.lastaddtime is null) ";
				list.add(startTime);list.add(endTime);
			}else if(endTime == null && startTime != null){
				sql = sql + " and (b.lastaddtime < ? or b.lastaddtime is null) ";
				list.add(startTime);
			}else if(endTime != null && startTime == null){
				sql = sql + " and (b.lastaddtime > ? or b.lastaddtime is null) ";
				list.add(endTime);
			}
		}else if(type == 4){//没有消耗积分
			if(endTime == null && startTime == null){
				sql = sql + " and b.lastusetime is null";
			}else if(endTime != null && startTime != null){
				sql = sql + " and (b.lastusetime < ? or b.lastusetime > ? or b.lastusetime is null) ";
				list.add(startTime);list.add(endTime);
			}else if(endTime == null && startTime != null){
				sql = sql + " and (b.lastusetime < ? or b.lastusetime is null) ";
				list.add(startTime);
			}else if(endTime != null && startTime == null){
				sql = sql + " and (b.lastusetime > ? or b.lastusetime is null) ";
				list.add(endTime);
			}
		}else if(type == 5){//充值
			if(endTime == null && startTime == null){
				sql = sql + " and b.rmblastaddtime is not null ";
			}else{
				if(endTime != null){
					sql = sql + " and b.rmblastaddtime < ? ";
					list.add(endTime);
				}
				if(startTime != null){
					sql = sql + " and b.rmblastaddtime > ?";
					list.add(startTime);
				}
			} 
		}else if(type == 6){//消费
			if(endTime == null && startTime == null){
				sql = sql + " and b.rmblastusetime is not null ";
			}else{
				if(endTime != null){
					sql = sql + " and b.rmblastusetime < ? ";
					list.add(endTime);
				}
				if(startTime != null){
					sql = sql + " and b.rmblastusetime > ?";
					list.add(startTime);
				}
			}
		}else if(type == 7){//未充值
			if(endTime == null && startTime == null){
				sql = sql + " and b.rmblastaddtime is null";
			}else if(endTime != null && startTime != null){
				sql = sql + " and (b.rmblastaddtime < ? or b.rmblastaddtime > ? or b.rmblastaddtime is null) ";
				list.add(startTime);list.add(endTime);
			}else if(endTime == null && startTime != null){
				sql = sql + " and (b.rmblastaddtime < ? or b.rmblastaddtime is null) ";
				list.add(startTime);
			}else if(endTime != null && startTime == null){
				sql = sql + " and (b.rmblastaddtime > ? or b.rmblastaddtime is null) ";
				list.add(endTime);
			}
		}else if(type == 8){//未消费
			if(endTime == null && startTime == null){
				sql = sql + " and b.rmblastusetime is null";
			}else if(endTime != null && startTime != null){
				sql = sql + " and (b.rmblastusetime < ? or b.rmblastusetime > ? or b.rmblastusetime is null) ";
				list.add(startTime);list.add(endTime);
			}else if(endTime == null && startTime != null){
				sql = sql + " and (b.rmblastusetime < ? or b.rmblastusetime is null) ";
				list.add(startTime);
			}else if(endTime != null && startTime == null){
				sql = sql + " and (b.rmblastusetime > ? or b.rmblastusetime is null) ";
				list.add(endTime);
			}
		}
		return jdbcTemplate.queryForInt(sql,list.toArray());
	}

	@Override
	public List<UserBalance> findUserBalance(long owner,long mpid,long hyuid,String nickname,Date startTime,Date endTime,int type,int start, int size)
	{
		String sql = "SELECT hu.nickname huname, wx.nickname wxname,hu.id, b.* FROM  es_hy_user hu join es_wx_user wx on hu.wxuid = wx.id LEFT JOIN es_hy_user_balance b  on b.hyuid = hu.id WHERE wx.nickname is not null and hu.`owner` = ? AND wx.mpid = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(owner);
		list.add(mpid);
		if(hyuid>0){
			sql +=" and hu.id like ?";
			list.add("%" + hyuid + "%");
		}
		if(StringUtils.isNotBlank(nickname)){
			nickname = "%" + nickname + "%";
			sql +=" and (hu.nickname like ? or wx.nickname like ?)";
			list.add(nickname);list.add(nickname);
		}
		if(type == 1){//增加积分
			if(endTime == null && startTime == null){
				sql = sql + " and b.lastaddtime is not null ";
			}else{
				if(endTime != null){
					sql = sql + " and b.lastaddtime < ? ";
					list.add(endTime);
				}
				if(startTime != null){
					sql = sql + " and b.lastaddtime > ?";
					list.add(startTime);
				}
			} 
		}else if(type == 2){//消耗积分
			if(endTime == null && startTime == null){
				sql = sql + " and b.lastusetime is not null ";
			}else{
				if(endTime != null){
					sql = sql + " and b.lastusetime < ? ";
					list.add(endTime);
				} 
				if(startTime != null){
					sql = sql + " and b.lastusetime > ?";
					list.add(startTime);
				}
			} 
		}else if(type == 3){//没有增加积分
			if(endTime == null && startTime == null){
				sql = sql + " and b.lastaddtime is null";
			}else if(endTime != null && startTime != null){
				sql = sql + " and (b.lastaddtime < ? or b.lastaddtime > ? or b.lastaddtime is null) ";
				list.add(startTime);list.add(endTime);
			}else if(endTime == null && startTime != null){
				sql = sql + " and (b.lastaddtime < ? or b.lastaddtime is null) ";
				list.add(startTime);
			}else if(endTime != null && startTime == null){
				sql = sql + " and (b.lastaddtime > ? or b.lastaddtime is null) ";
				list.add(endTime);
			}
		}else if(type == 4){//没有消耗积分
			if(endTime == null && startTime == null){
				sql = sql + " and b.lastusetime is null";
			}else if(endTime != null && startTime != null){
				sql = sql + " and (b.lastusetime < ? or b.lastusetime > ? or b.lastusetime is null) ";
				list.add(startTime);list.add(endTime);
			}else if(endTime == null && startTime != null){
				sql = sql + " and (b.lastusetime < ? or b.lastusetime is null) ";
				list.add(startTime);
			}else if(endTime != null && startTime == null){
				sql = sql + " and (b.lastusetime > ? or b.lastusetime is null) ";
				list.add(endTime);
			}
		}else if(type == 5){//充值
			if(endTime == null && startTime == null){
				sql = sql + " and b.rmblastaddtime is not null ";
			}else{
				if(endTime != null){
					sql = sql + " and b.rmblastaddtime < ? ";
					list.add(endTime);
				}
				if(startTime != null){
					sql = sql + " and b.rmblastaddtime > ?";
					list.add(startTime);
				}
			} 
		}else if(type == 6){//消费
			if(endTime == null && startTime == null){
				sql = sql + " and b.rmblastusetime is not null ";
			}else{
				if(endTime != null){
					sql = sql + " and b.rmblastusetime < ? ";
					list.add(endTime);
				}
				if(startTime != null){
					sql = sql + " and b.rmblastusetime > ?";
					list.add(startTime);
				}
			}
		}else if(type == 7){//未充值
			if(endTime == null && startTime == null){
				sql = sql + " and b.rmblastaddtime is null";
			}else if(endTime != null && startTime != null){
				sql = sql + " and (b.rmblastaddtime < ? or b.rmblastaddtime > ? or b.rmblastaddtime is null) ";
				list.add(startTime);list.add(endTime);
			}else if(endTime == null && startTime != null){
				sql = sql + " and (b.rmblastaddtime < ? or b.rmblastaddtime is null) ";
				list.add(startTime);
			}else if(endTime != null && startTime == null){
				sql = sql + " and (b.rmblastaddtime > ? or b.rmblastaddtime is null) ";
				list.add(endTime);
			}
		}else if(type == 8){//未消费
			if(endTime == null && startTime == null){
				sql = sql + " and b.rmblastusetime is null";
			}else if(endTime != null && startTime != null){
				sql = sql + " and (b.rmblastusetime < ? or b.rmblastusetime > ? or b.rmblastusetime is null) ";
				list.add(startTime);list.add(endTime);
			}else if(endTime == null && startTime != null){
				sql = sql + " and (b.rmblastusetime < ? or b.rmblastusetime is null) ";
				list.add(startTime);
			}else if(endTime != null && startTime == null){
				sql = sql + " and (b.rmblastusetime > ? or b.rmblastusetime is null) ";
				list.add(endTime);
			}
		}
		sql += " order by b.total desc limit ?,?";
		list.add(start);list.add(size);
		return jdbcTemplate.query(sql, list.toArray(), new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				UserBalance b = new UserBalance();
				b.setId(rs.getLong("b.id"));
				b.setHyuid(rs.getLong("hu.id"));
				b.setTotal(rs.getInt("b.total"));
				b.setUsed(rs.getInt("b.used"));
				b.setPreused(rs.getInt("b.preused"));
				b.setLastaddtime(rs.getTimestamp("b.lastaddtime"));
				b.setLastusetime(rs.getTimestamp("b.lastusetime"));
				if(StringUtils.isNotBlank(rs.getString("huname"))){
					b.setNickname(rs.getString("huname"));
				}else{
					b.setNickname(rs.getString("wxname"));
				}
				b.setRmbtotal(rs.getInt("b.rmbtotal"));
				b.setRmbused(rs.getInt("b.rmbused"));
				b.setRmbpreused(rs.getInt("b.rmbpreused"));
				b.setRmblastaddtime(rs.getTimestamp("b.rmblastaddtime"));
				b.setRmblastusetime(rs.getTimestamp("b.rmblastusetime"));
				return b;
			}
			
		});
	}

	@Override
	public UserBalance findUserBalanceByHyUid(long hyuid)
	{
		String sql = "select b.*,hu.nickname huname,wx.nickname wxname,hu.img,wx.headimgurl from es_hy_user_balance b left join es_hy_user hu on b.hyuid = hu.id left join es_wx_user wx on hu.wxuid = wx.id where hu.id = ?";
		Object[] params = {hyuid};
		List<UserBalance> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				UserBalance b = new UserBalance();
				b.setId(rs.getLong("b.id"));
				b.setHyuid(rs.getLong("b.hyuid"));
				b.setTotal(rs.getInt("b.total"));
				b.setUsed(rs.getInt("b.used"));
				b.setPreused(rs.getInt("b.preused"));
				b.setLastaddtime(rs.getTimestamp("b.lastaddtime"));
				b.setLastusetime(rs.getTimestamp("b.lastusetime"));
				if(StringUtils.isNotBlank(rs.getString("huname"))){
					b.setNickname(rs.getString("huname"));
				}else{
					b.setNickname(rs.getString("wxname"));
				}
				if(StringUtils.isNotBlank(rs.getString("hu.img"))){
					b.setImg(rs.getString("hu.img"));
				}else{
					b.setImg(rs.getString("wx.headimgurl"));
				}
				return b;
			}
			
		}); 
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	
}
