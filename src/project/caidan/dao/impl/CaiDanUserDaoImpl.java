package project.caidan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.WxUser;

import project.caidan.dao.ICaiDanUserDao;

@SuppressWarnings("unchecked")
public class CaiDanUserDaoImpl implements ICaiDanUserDao {
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findHyUserCount(long owner, HyUser filter) {
		String nickname = null;
		String telphone = null;
		if (filter != null) {
			nickname = filter.getNickname();
			telphone = filter.getTelphone();
		}

		String sql = "SELECT COUNT(*) FROM esite.es_hy_user h,esite.es_wx_user w WHERE h.wxuid=w.id AND h.owner=" + owner;
		if (StringUtils.isNotBlank(nickname)) {
			sql += " AND w.nickname LIKE '%" + nickname + "%'";
		} else {
			sql += " AND w.nickname IS NOT NULL";
		}
		if (StringUtils.isNotBlank(telphone)) {
			sql += " AND h.telphone LIKE '%" + telphone + "%'";
		}
		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public List<HyUser> findHyUserList(long owner, HyUser filter) {
		String nickname = null;
		String telphone = null;
		if (filter != null) {
			nickname = filter.getNickname();
			telphone = filter.getTelphone();
		}

		String sql = "SELECT * FROM esite.es_hy_user h JOIN esite.es_wx_user w ON w.id=h.wxuid";
		if (StringUtils.isNotBlank(nickname)) {
			sql += " AND w.nickname LIKE '%" + nickname + "%'";
		} else {
			sql += " AND w.nickname IS NOT NULL";
		}
		sql += " LEFT JOIN esite.es_hy_user_balance b on b.hyuid=h.id WHERE h.owner=" + owner;
		if (StringUtils.isNotBlank(telphone)) {
			sql += " AND h.telphone LIKE '%" + telphone + "%'";
		}
		return jdbcTemplate.query(sql, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				HyUser hu = new HyUser();
				hu.setId(rs.getLong("h.id"));
				hu.setUsername(rs.getString("h.username"));
				hu.setPassword(rs.getString("h.password"));
				hu.setWxuid(rs.getLong("h.wxuid"));
				hu.setWbuid(rs.getLong("h.wbuid"));
				hu.setOwner(rs.getLong("h.owner"));
				hu.setNickname(rs.getString("h.nickname"));
				hu.setTelphone(rs.getString("h.telphone"));
				hu.setEmail(rs.getString("h.email"));
				hu.setImg(rs.getString("h.img"));

				WxUser wu = new WxUser();
				wu.setId(rs.getLong("w.id"));
				wu.setHeadimgurl(rs.getString("w.headimgurl"));
				wu.setNickname(rs.getString("w.nickname"));
				wu.setSex(rs.getInt("w.sex"));
				wu.setCountry(rs.getString("w.country"));
				wu.setProvince(rs.getString("w.province"));
				wu.setCity(rs.getString("w.city"));
				long subscribe_time = rs.getLong("w.subscribe_time");
				if (subscribe_time > 0) {
					wu.setSubscribe_time(new Date(subscribe_time * 1000));
				}
				hu.setWxUser(wu);

				hu.setBalance_total(rs.getInt("b.total"));
				hu.setBalance_used(rs.getInt("b.used"));
				return hu;
			}

		});
	}

	@Override
	public List<HyUser> findHyUserList(long owner, HyUser filter, int start, int rows) {
		String nickname = null;
		String telphone = null;
		if (filter != null) {
			nickname = filter.getNickname();
			telphone = filter.getTelphone();
		}
		String sql = "SELECT * FROM esite.es_hy_user h JOIN esite.es_wx_user w ON w.id=h.wxuid";
		if (StringUtils.isNotBlank(nickname)) {
			sql += " AND w.nickname LIKE '%" + nickname + "%'";
		} else {
			sql += " AND w.nickname IS NOT NULL";
		}
		sql += " LEFT JOIN esite.es_hy_user_balance b on b.hyuid=h.id WHERE h.owner=" + owner;
		if (StringUtils.isNotBlank(telphone)) {
			sql += " AND h.telphone LIKE '%" + telphone + "%'";
		}
		sql += " ORDER BY w.subscribe_time DESC LIMIT " + start + "," + rows;

		return jdbcTemplate.query(sql, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				HyUser hu = new HyUser();
				hu.setId(rs.getLong("h.id"));
				hu.setUsername(rs.getString("h.username"));
				hu.setPassword(rs.getString("h.password"));
				hu.setWxuid(rs.getLong("h.wxuid"));
				hu.setWbuid(rs.getLong("h.wbuid"));
				hu.setOwner(rs.getLong("h.owner"));
				hu.setNickname(rs.getString("h.nickname"));
				hu.setTelphone(rs.getString("h.telphone"));
				hu.setEmail(rs.getString("h.email"));
				hu.setImg(rs.getString("h.img"));

				WxUser wu = new WxUser();
				wu.setId(rs.getLong("w.id"));
				wu.setHeadimgurl(rs.getString("w.headimgurl"));
				wu.setNickname(rs.getString("w.nickname"));
				wu.setSex(rs.getInt("w.sex"));
				wu.setCountry(rs.getString("w.country"));
				wu.setProvince(rs.getString("w.province"));
				wu.setCity(rs.getString("w.city"));
				long subscribe_time = rs.getLong("w.subscribe_time");
				if (subscribe_time > 0) {
					wu.setSubscribe_time(new Date(subscribe_time * 1000));
				}
				hu.setWxUser(wu);

				hu.setBalance_total(rs.getInt("b.total"));
				hu.setBalance_used(rs.getInt("b.used"));
				return hu;
			}

		});
	}

	@Override
	public int findOrderGoodsCount(long hyuid) {
		String sql = "SELECT COUNT(*) FROM esite.es_pay_order WHERE hyuid=?";
		Object[] param = { hyuid };
		return jdbcTemplate.queryForInt(sql, param);
	}

	@Override
	public List<OrderGoods> findOrderGoodsList(long hyuid, int start, int rows) {
		String sql = "SELECT * FROM esite.es_pay_order o,esite.es_pay_order_goods g WHERE o.id=g.orderid AND o.hyuid=? ORDER BY g.id DESC LIMIT ?,?";
		Object[] params = { hyuid, start, rows };
		return jdbcTemplate.query(sql, params, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				OrderGoods og = new OrderGoods();
				og.setId(rs.getLong("g.id"));
				og.setOrderid(rs.getLong("g.orderid"));
				og.setPid(rs.getLong("g.productid"));
				og.setProductname(rs.getString("g.productname"));
				og.setProductsimg(rs.getString("g.productsimg"));
				og.setPrice(rs.getInt("g.price"));
				og.setUsed(rs.getString("g.used"));
				return og;
			}

		});
	}

	@Override
	public HyUser findHyUser(long owner, long wxuid) {
		String sql = "select * from es_hy_user where owner = ? and wxuid = ? and telphone is not null";
		Object[] params = { owner, wxuid };
		List<HyUser> list = jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				HyUser hu = new HyUser();
				hu.setId(rs.getLong("id"));
				hu.setOwner(rs.getLong("owner"));
				hu.setPassword(rs.getString("password"));
				hu.setUsername(rs.getString("username"));
				hu.setWxuid(rs.getLong("wxuid"));
				hu.setEmail(rs.getString("email"));
				hu.setImg(rs.getString("img"));
				hu.setNickname(rs.getString("nickname"));
				hu.setTelphone(rs.getString("telphone"));
				return hu;
			}

		});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public HyUser findHyUserByWxuid(long owner, long wxuid) {
		String sql = "select * from es_hy_user where owner = ? and wxuid = ?";
		Object[] params = { owner, wxuid };
		List<HyUser> list = jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				HyUser hu = new HyUser();
				hu.setId(rs.getLong("id"));
				hu.setOwner(rs.getLong("owner"));
				hu.setPassword(rs.getString("password"));
				hu.setUsername(rs.getString("username"));
				hu.setWxuid(rs.getLong("wxuid"));
				hu.setEmail(rs.getString("email"));
				hu.setImg(rs.getString("img"));
				hu.setNickname(rs.getString("nickname"));
				hu.setTelphone(rs.getString("telphone"));
				return hu;
			}

		});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateTelByWxuid(long owner, long wxuid, String telphone) {
		String sql = "update es_hy_user set telphone = ? where owner = ? and wxuid = ?";
		Object[] params = { telphone, owner, wxuid };
		return jdbcTemplate.update(sql, params);
	}

}
