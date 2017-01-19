package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.IWxUserDao;
import com.huiyee.esite.model.WxUser;

@SuppressWarnings("unchecked")
public class WxUserDaoImpl implements IWxUserDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public WxUser findWxUserByOpenid(String openid) {
		String sql = "select * from esite.es_wx_user where openid=?";
		Object[] params = { openid };
		List<WxUser> list = jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxUser wx = new WxUser();
				wx.setId(rs.getLong("id"));
				wx.setNickname(rs.getString("nickname"));
				wx.setSex(rs.getInt("sex"));
				wx.setMpid(rs.getLong("mpid"));
				wx.setCity(rs.getString("city"));
				wx.setCountry(rs.getString("country"));
				wx.setHeadimgurl(rs.getString("headimgurl"));
				wx.setOpenid(rs.getString("openid"));
				wx.setPrivilege(rs.getString("privilege"));
				wx.setSubscribe(rs.getInt("subscribe"));
				wx.setProvince(rs.getString("province"));
				wx.setUpdatetime(rs.getTimestamp("updatetime"));
				return wx;
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public WxUser findWxUserByid(long wxuid)
	{
		String sql = "select * from esite.es_wx_user where id=?";
		Object[] params = { wxuid };
		List<WxUser> list = jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxUser wx = new WxUser();
				wx.setId(rs.getLong("id"));
				wx.setNickname(rs.getString("nickname"));
				wx.setSex(rs.getInt("sex"));
				wx.setMpid(rs.getLong("mpid"));
				wx.setCity(rs.getString("city"));
				wx.setCountry(rs.getString("country"));
				wx.setHeadimgurl(rs.getString("headimgurl"));
				wx.setOpenid(rs.getString("openid"));
				wx.setPrivilege(rs.getString("privilege"));
				wx.setSubscribe(rs.getInt("subscribe"));
				wx.setProvince(rs.getString("province"));
				wx.setUpdatetime(rs.getTimestamp("updatetime"));
				return wx;
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public WxUser findWxUserByHyuid(long hyuid) {
		String sql = "select * from esite.es_hy_user h left join esite.es_wx_user w on w.id=h.wxuid where h.id=?";
		Object[] params = { hyuid };
		List<WxUser> list = jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxUser wx = new WxUser();
				wx.setId(rs.getLong("w.id"));
				wx.setNickname(rs.getString("w.nickname"));
				wx.setSex(rs.getInt("w.sex"));
				wx.setMpid(rs.getLong("w.mpid"));
				wx.setCity(rs.getString("w.city"));
				wx.setCountry(rs.getString("w.country"));
				wx.setHeadimgurl(rs.getString("w.headimgurl"));
				wx.setOpenid(rs.getString("w.openid"));
				wx.setPrivilege(rs.getString("w.privilege"));
				wx.setSubscribe(rs.getInt("w.subscribe"));
				wx.setProvince(rs.getString("w.province"));
				wx.setUpdatetime(rs.getTimestamp("w.updatetime"));
				return wx;
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<WxUser> findWxUserByOwner(long owner,String nickname,int start,int size)
	{
		String sql = "";
		List<Object> list = new ArrayList<Object>();
		list.add(owner);
		if(StringUtils.isNotBlank(nickname)){
			nickname = "%" + nickname + "%";
			sql = "select * from es_hy_user hy join es_wx_user wx on hy.wxuid = wx.id where hy.owner = ? and wx.nickname like ? and wx.subscribe = 1 limit ?,?";
			list.add(nickname);
		}else{
			sql = "select * from es_hy_user hy join es_wx_user wx on hy.wxuid = wx.id where hy.owner = ? and wx.subscribe = 1 limit ?,?";
		}
		list.add(start);list.add(size);
		return jdbcTemplate.query(sql, list.toArray(), new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxUser wx = new WxUser();
				wx.setId(rs.getLong("wx.id"));
				wx.setNickname(rs.getString("wx.nickname"));
				wx.setSex(rs.getInt("wx.sex"));
				wx.setMpid(rs.getLong("wx.mpid"));
				wx.setCity(rs.getString("wx.city"));
				wx.setCountry(rs.getString("wx.country"));
				wx.setHeadimgurl(rs.getString("wx.headimgurl"));
				wx.setOpenid(rs.getString("wx.openid"));
				wx.setPrivilege(rs.getString("wx.privilege"));
				wx.setSubscribe(rs.getInt("wx.subscribe"));
				wx.setProvince(rs.getString("wx.province"));
				wx.setUpdatetime(rs.getTimestamp("wx.updatetime"));
				return wx;
			}
		});
	}

	@Override
	public int findTotalWxUserByOwner(long owner,String nickname)
	{
		String sql = "";
		List<Object> list = new ArrayList<Object>();
		list.add(owner);
		if(StringUtils.isNotBlank(nickname)){
			nickname = "%" + nickname + "%";
			sql = "select count(wx.id) from es_hy_user hy join es_wx_user wx on hy.wxuid = wx.id where hy.owner = ? and wx.nickname like ? and wx.subscribe = 1";
			list.add(nickname);
		}else{
			sql = "select count(wx.id) from es_hy_user hy join es_wx_user wx on hy.wxuid = wx.id where hy.owner = ? and wx.subscribe = 1";
		}
		return jdbcTemplate.queryForInt(sql,list.toArray());
	}

}
