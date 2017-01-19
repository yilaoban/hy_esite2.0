package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.IWeiKaQuanDao;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.PayApt;
import com.huiyee.esite.model.WxUser;
import com.huiyee.weixin.model.Wkq;

@SuppressWarnings("unchecked")
public class WeiKaQuanDaoImpl implements IWeiKaQuanDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int saveWkqShopOwner(long wxuid, long owner) {
		String sql = "insert into es_wkq_shop_owner(wxuid,createtime,owner) values(?,now(),?) on duplicate key update createtime = now()";
		Object[] params = { wxuid, owner };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int findTotalContentProduct(long owner) {
		String sql = "select count(id) from es_content_product where ownerid = ? and subtype = 'K' and status != 'DEL'";
		Object[] params = { owner };
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<ContentProduct> findContentProductList(long owner, int start, int size) {
		String sql = "select * from es_content_product where ownerid = ? and subtype = 'K' and status != 'DEL' limit ?,?";
		Object[] params = { owner, start, size };
		return jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ContentProduct p = new ContentProduct();
				p.setId(rs.getLong("id"));
				p.setOwner(rs.getLong("ownerid"));
				p.setCatid(rs.getLong("catid"));
				p.setName(rs.getString("name"));
				p.setSimgurl(rs.getString("simgurl"));
				p.setBimgurl(rs.getString("bimgurl"));
				p.setLinkurl(rs.getString("linkurl"));
				p.setPrice(rs.getDouble("price"));
				p.setSalesprice(rs.getDouble("salesprice"));
				p.setDetail(rs.getString("detail"));
				p.setStatus(rs.getString("status"));
				p.setCreatetime(rs.getTimestamp("createtime"));
				p.setUpdatetime(rs.getDate("updatetime"));
				p.setTag(rs.getString("tag"));
				p.setType(rs.getString("type"));
				p.setSubtype(rs.getString("subtype"));
				p.setTotal(rs.getInt("total"));
				p.setUsed(rs.getInt("used"));
				return p;
			}
		});

	}

	@Override
	public int findTotalPayOrderListByProductid(long productid) {
		String sql = "select count(g.id) from es_pay_order_goods g join es_pay_order o on g.orderid = o.id where g.productid = ?";
		Object[] params = { productid };
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<OrderGoods> findPayOrderListByProductid(long productid, int start, int size) {
		String sql = "select * from es_pay_order_goods g join es_pay_order o on g.orderid = o.id where g.productid = ? limit ?,?";
		Object[] params = { productid, start, size };
		return jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				OrderGoods cp = new OrderGoods();
				cp.setId(rs.getLong("g.id"));
				cp.setOrderid(rs.getLong("g.orderid"));
				cp.setPid(rs.getLong("g.productid"));
				cp.setProductname(rs.getString("g.productname"));
				cp.setProductsubtype(rs.getString("g.productsubtype"));
				cp.setProductsimg(rs.getString("g.productsimg"));
				cp.setPrice(rs.getInt("g.price"));
				cp.setType(rs.getInt("g.type"));
				cp.setUuid(rs.getString("g.uuid"));
				cp.setNum(rs.getInt("g.num"));
				cp.setPaid(rs.getLong("g.paid"));
				cp.setUsed(rs.getString("g.used"));
				cp.setShoppingcartid(rs.getLong("g.shoppingcartid"));
				cp.setStatus(rs.getString("o.status"));
				return cp;
			}

		});

	}

	@Override
	public OrderGoods findPayOrderGoods(long id) {
		String sql = "SELECT * FROM es_pay_order_goods g LEFT JOIN es_pay_order o ON o.id=g.orderid WHERE g.id=?";
		Object[] args = { id };
		List<OrderGoods> list = jdbcTemplate.query(sql, args, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				OrderGoods og = new OrderGoods();
				og.setId(rs.getLong("g.id"));
				og.setProductname(rs.getString("g.productname"));
				og.setProductsimg(rs.getString("g.productsimg"));
				og.setPrice(rs.getInt("g.price"));

				PayApt payApt = new PayApt();
				payApt.setHyuid(rs.getLong("o.hyuid"));
				payApt.setWxuid(rs.getLong("o.wxuid"));
				og.setPayApt(payApt);

				return og;
			}

		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int saveWKQ(long pageid, long owner) {
		String sql = "insert into es_wkq(owner,yzpage) values(?,?) on duplicate key update yzpage = ?";
		Object[] params = { owner, pageid, pageid };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int findTotalWKQShopOwnerByOwner(long owner) {
		String sql = "select count(ws.id) from es_wkq_shop_owner ws join es_wx_user wx on ws.wxuid = wx.id where ws.owner = ?";
		Object[] params = { owner };
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<WxUser> findWKQShopOwnerByOwner(long owner, int start, int size) {
		String sql = "select * from es_wkq_shop_owner ws join es_wx_user wx on ws.wxuid = wx.id where ws.owner = ? order by ws.createtime desc limit ?,?";
		Object[] params = { owner, start, size };
		return jdbcTemplate.query(sql, params, new RowMapper() {
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
	public int delShopOwner(long wxuid, long owner) {
		String sql = "delete from es_wkq_shop_owner where wxuid = ? and owner = ?";
		Object[] params = { wxuid, owner };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public Wkq findWKQByOwner(long owner) {
		String sql = "select * from es_wkq where owner = ?";
		Object[] params = { owner };
		List<Wkq> list = jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Wkq q = new Wkq();
				q.setYzpage(rs.getLong("yzpage"));
				q.setId(rs.getLong("id"));
				return q;
			}

		});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
