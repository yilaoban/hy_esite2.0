package com.huiyee.weixin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.MarketingSet;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.PayApt;
import com.huiyee.esite.model.PayRecord;
import com.huiyee.weixin.dao.IWxBuyProductDao;
import com.huiyee.weixin.dto.WkqCmp;
import com.huiyee.weixin.model.PayAddress;
import com.huiyee.weixin.model.PayOrder;
import com.huiyee.weixin.model.PayOrderTemplate;
import com.huiyee.weixin.model.ProductLevel;
import com.huiyee.weixin.model.ShoppingCartRecord;


public class WxBuyProductDaoImpl implements IWxBuyProductDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<PayAddress> findPayAddressListByHyUid(long hyuid)
	{
		String sql = "select * from es_pay_address where hyuid = ? order by id desc";
		Object[] params = {hyuid};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				PayAddress pa = new PayAddress();
				pa.setId(rs.getLong("id"));
				pa.setHyuid(rs.getLong("hyuid"));
				pa.setAddress(rs.getString("address"));
				pa.setCity(rs.getString("city"));
				pa.setName(rs.getString("name"));
				pa.setProvince(rs.getString("province"));
				pa.setTelphone(rs.getString("telphone"));
				pa.setIsdefault(rs.getString("isdefault"));
				return pa;
			}
			
		});
	}

	@Override
	public PayAddress findPayAddressById(long aid)
	{
		String sql = "select * from es_pay_address where id = ?";
		Object[] params = {aid};
		List<PayAddress> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				PayAddress pa = new PayAddress();
				pa.setId(rs.getLong("id"));
				pa.setHyuid(rs.getLong("hyuid"));
				pa.setAddress(rs.getString("address"));
				pa.setCity(rs.getString("city"));
				pa.setName(rs.getString("name"));
				pa.setProvince(rs.getString("province"));
				pa.setTelphone(rs.getString("telphone"));
				pa.setIsdefault(rs.getString("isdefault"));
				return pa;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public PayAddress findDefaultPayAddress(long aid)
	{
		String sql = "select * from es_pay_address where hyuid = ? and isdefault = 'Y' limit 0,1";
		Object[] params = {aid};
		List<PayAddress> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				PayAddress pa = new PayAddress();
				pa.setId(rs.getLong("id"));
				pa.setHyuid(rs.getLong("hyuid"));
				pa.setAddress(rs.getString("address"));
				pa.setCity(rs.getString("city"));
				pa.setName(rs.getString("name"));
				pa.setProvince(rs.getString("province"));
				pa.setTelphone(rs.getString("telphone"));
				return pa;
			}
			
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public long savePayOrder(final long hyuid,final long wxuid,final long owner,final int type,final String ip,final String status)
	{
		final String sql = "insert into es_pay_order(hyuid,wxuid,ownerid,createtime,status,ip,subtype) values(?,?,?,now(),?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i=1;
				ps.setLong(i++, hyuid);
				ps.setLong(i++, wxuid);
				ps.setLong(i++, owner);
				ps.setString(i++, status);
				ps.setString(i++, ip);
				ps.setInt(i++, type);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}

	@Override
	public int updatepayOrderStatus(long out_trade_no,String prestatus,String status)
	{
		String sql = "";
		List<Object> list = new ArrayList<Object>();
		list.add(status);
		list.add(out_trade_no);
		if(StringUtils.isNotBlank(prestatus)){
			sql = "update es_pay_order set status = ? where id = ? and status = ?";
			list.add(prestatus);
		}else{
			sql = "update es_pay_order set status = ? where id = ?";
		}
		return jdbcTemplate.update(sql, list.toArray());
	}

	@Override
	public long savePayRecord(final String mediaorder,final String ip)
	{
		final String sql = "insert into es_pay_record(mediaorder,createtime,ip) values(?,now(),?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i=1;
				ps.setString(i++, mediaorder);
				ps.setString(i++, ip);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public int savePayAddress(long hyuid, PayAddress address)
	{
		String sql = "insert into es_pay_address(hyuid,name,province,city,address,telphone,isdefault) values(?,?,?,?,?,?,?)";
		Object[] params = {hyuid,address.getName(),address.getProvince(),address.getCity(),address.getAddress(),address.getTelphone(),address.getIsdefault()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updatePayAddress(long aid, PayAddress address)
	{
		String sql = "update es_pay_address set name=?,province=?,city=?,address=?,telphone=?,isdefault=? where id = ?";
		Object[] params = {address.getName(),address.getProvince(),address.getCity(),address.getAddress(),address.getTelphone(),address.getIsdefault(),aid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int delPayAddress(long aid)
	{
		String sql = "delete from es_pay_address where id = ?";
		Object[] params = {aid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public PayOrder findPayOrderById(long payOrderid)
	{
		String sql = "select * from es_pay_order o where o.id = ? ";
		Object[] params = {payOrderid};
		List<PayOrder> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				PayOrder order = new PayOrder();
				order.setId(rs.getLong("o.id"));
				order.setHyuid(rs.getLong("o.hyuid"));
				order.setWxuid(rs.getLong("o.wxuid"));
				order.setOwnerid(rs.getLong("o.ownerid"));
				order.setCreatetime(rs.getTimestamp("o.createtime"));
				order.setAddressid(rs.getLong("o.addressid"));
				order.setSendtime(rs.getTimestamp("o.sendtime"));
				order.setTotalprice(rs.getInt("o.totalprice"));
				order.setDiscountprice(rs.getInt("o.discountprice"));
				order.setRealprice(rs.getInt("o.realprice"));
				order.setExpressid(rs.getString("o.expressid"));
				order.setStatus(rs.getString("o.status"));
				order.setIp(rs.getString("o.ip"));
				order.setSubtype(rs.getInt("o.subtype"));
				order.setFatherorderid(rs.getLong("o.fatherorderid"));
				order.setUsejf(rs.getInt("o.usejf"));
				order.setGoodscount(rs.getInt("o.goodscount"));
				return order;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateWkqYzStatus(String uuid, long id,String status)
	{
		return jdbcTemplate.update("update es_pay_order_goods set used=? where id=? and uuid=?", new Object[]{status,id,uuid});
	}

	@Override
	public String findWkqOrder(String uuid, long id)
	{
		List<String> ls=jdbcTemplate.query("select used from  es_pay_order_goods where id=? and uuid=?", new Object[]{id,uuid},new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getString("used");
			}
			
		});
		if(ls!=null&&ls.size()>0){
			return ls.get(0);
		}
		return null;
	}

	@Override
	public ShoppingCartRecord findShoppingCartProduct(long productid, long hyuid,long paid,String status)
	{
		String sql = "select c.id,c.num,c.productid,c.paid from es_pay_shopping_cart c where c.hyuid = ? and c.productid = ? and c.status = ? and c.paid = ?";
		Object[] params = {hyuid,productid,status,paid};
		List<ShoppingCartRecord> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ShoppingCartRecord p = new ShoppingCartRecord();
				p.setId(rs.getLong("c.id"));
				p.setNum(rs.getInt("c.num"));
				p.setProductid(rs.getLong("c.productid"));
				p.setPaid(rs.getLong("c.paid"));
				return p;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateShoppingCartProductNum(long shopCartid, long hyuid,int num)
	{
		String sql = "update es_pay_shopping_cart set num = ? where hyuid = ? and id = ?";
		Object[] params = {num,hyuid,shopCartid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public long savePayShoppingCart(final long productid,final long hyuid,final long paid,final String status)
	{
		final String sql = "insert into es_pay_shopping_cart(hyuid,productid,num,createtime,paid,status) values(?,?,?,now(),?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i=1;
				ps.setLong(i++, hyuid);
				ps.setLong(i++, productid);
				ps.setInt(i++, 1);
				ps.setLong(i++, paid);
				ps.setString(i++, status);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
		
		
	}

	@Override
	public List<ShoppingCartRecord> findShoppingCartProductByHyuid(long hyuid,long owner,String type)
	{
		String sql = "select * from es_pay_shopping_cart c join es_content_product p on c.productid = p.id where c.hyuid = ? and c.status = 'EDT' and p.ownerid = ? and p.type = ?";
		Object[] params = {hyuid,owner,type};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ShoppingCartRecord r = new ShoppingCartRecord();
				r.setId(rs.getLong("c.id"));
				r.setNum(rs.getInt("c.num"));
				r.setPaid(rs.getLong("c.paid"));
				r.setProductid(rs.getLong("c.productid"));
				
				ContentProduct p = new ContentProduct();
				p.setId(rs.getLong("p.id"));
				p.setOwner(rs.getLong("p.ownerid"));
				p.setCatid(rs.getLong("p.catid"));
				p.setName(rs.getString("p.name"));
				p.setSimgurl(rs.getString("p.simgurl"));
				p.setLinkurl(rs.getString("p.linkurl"));
				p.setPrice(rs.getDouble("p.price"));
				p.setSalesprice(rs.getDouble("p.salesprice"));
				p.setStatus(rs.getString("p.status"));
				p.setCreatetime(rs.getTimestamp("p.createtime"));
				p.setType(rs.getString("p.type"));
				p.setSubtype(rs.getString("p.subtype"));
				p.setTotal(rs.getInt("p.total"));
				p.setUsed(rs.getInt("p.used"));
				p.setMaxjf(rs.getInt("p.maxjf"));
				p.setShownum(rs.getInt("p.shownum"));
				
				r.setProduct(p);
				
				return r;
			}
			
		});
	}

	@Override
	public int updateShoppingCartStatus(long shopCartid, long hyuid)
	{
		String sql = "update es_pay_shopping_cart set status = 'DEL' where hyuid = ? and id = ?";
		Object[] params = {hyuid,shopCartid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updatePayRecord(long payrecordid, String mediaorder, int status,int price)
	{
		String sql = "update es_pay_record set mediaorder = ?,status=?,price=? where id = ?";
		Object[] params = {mediaorder,status,price,payrecordid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public List<PayOrder> findOrderList(long hyuid, String status,int type,int start,int size)
	{
		String sql = "";
		List<Object> list = new ArrayList<Object>();
		list.add(hyuid);list.add(type);
		if(StringUtils.isNotBlank(status)){
			sql = "select * from es_pay_order o where o.hyuid = ? and o.subtype = ? and o.status =? and o.del_tag != 'Y' and o.fatherorderid is null order by o.createtime desc limit ?,?";
			list.add(status);
		}else{
			sql = "select * from es_pay_order o where o.hyuid = ? and o.subtype = ? and o.del_tag != 'Y' and o.fatherorderid is null order by o.createtime desc limit ?,?";
		}
		list.add(start);list.add(size);
		return jdbcTemplate.query(sql, list.toArray(), new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				PayOrder order = new PayOrder();
				order.setId(rs.getLong("o.id"));
				order.setHyuid(rs.getLong("o.hyuid"));
				order.setWxuid(rs.getLong("o.wxuid"));
				order.setOwnerid(rs.getLong("o.ownerid"));
				order.setCreatetime(rs.getTimestamp("o.createtime"));
				order.setAddressid(rs.getLong("o.addressid"));
				order.setSendtime(rs.getTimestamp("o.sendtime"));
				order.setTotalprice(rs.getInt("o.totalprice"));
				order.setDiscountprice(rs.getInt("o.discountprice"));
				order.setRealprice(rs.getInt("o.realprice"));
				order.setExpressid(rs.getString("o.expressid"));
				order.setStatus(rs.getString("o.status"));
				order.setIp(rs.getString("o.ip"));
				order.setSubtype(rs.getInt("o.subtype"));
				order.setGoodscount(rs.getInt("o.goodscount"));
				order.setFatherorderid(rs.getLong("o.fatherorderid"));
				return order;
			}
		});
	}
	
	@Override
	public List<PayOrder> findKQOrderList(long hyuid,String status,int type,int start,int size){
		String sql = "";
		List<Object> list = new ArrayList<Object>();
		list.add(hyuid);list.add(type);
		if(StringUtils.isNotBlank(status)){
			sql = "select * from es_pay_order o where o.hyuid = ? and o.subtype = ? and o.status =? and o.del_tag != 'Y' and o.fatherorderid > 0 order by o.createtime desc limit ?,?";
			list.add(status);
		}else{
			sql = "select * from es_pay_order o where o.hyuid = ? and o.subtype = ? and o.del_tag != 'Y' and o.fatherorderid > 0 order by o.createtime desc limit ?,?";
		}
		list.add(start);list.add(size);
		return jdbcTemplate.query(sql, list.toArray(), new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				PayOrder order = new PayOrder();
				order.setId(rs.getLong("o.id"));
				order.setHyuid(rs.getLong("o.hyuid"));
				order.setWxuid(rs.getLong("o.wxuid"));
				order.setOwnerid(rs.getLong("o.ownerid"));
				order.setCreatetime(rs.getTimestamp("o.createtime"));
				order.setAddressid(rs.getLong("o.addressid"));
				order.setSendtime(rs.getTimestamp("o.sendtime"));
				order.setTotalprice(rs.getInt("o.totalprice"));
				order.setDiscountprice(rs.getInt("o.discountprice"));
				order.setRealprice(rs.getInt("o.realprice"));
				order.setExpressid(rs.getString("o.expressid"));
				order.setStatus(rs.getString("o.status"));
				order.setIp(rs.getString("o.ip"));
				order.setSubtype(rs.getInt("o.subtype"));
				order.setFatherorderid(rs.getLong("o.fatherorderid"));
				return order;
			}
		});
	}
	
	@Override
	public List<PayOrder> findKQOrderList(long hyuid,String status,int start,int size){
		String sql = "select * from es_pay_order o where o.hyuid = ? and o.status =? and o.del_tag != 'Y' and o.fatherorderid > 0 order by o.createtime desc limit ?,?";
		Object[] params = {hyuid,status,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				PayOrder order = new PayOrder();
				order.setId(rs.getLong("o.id"));
				order.setHyuid(rs.getLong("o.hyuid"));
				order.setWxuid(rs.getLong("o.wxuid"));
				order.setOwnerid(rs.getLong("o.ownerid"));
				order.setCreatetime(rs.getTimestamp("o.createtime"));
				order.setAddressid(rs.getLong("o.addressid"));
				order.setSendtime(rs.getTimestamp("o.sendtime"));
				order.setTotalprice(rs.getInt("o.totalprice"));
				order.setDiscountprice(rs.getInt("o.discountprice"));
				order.setRealprice(rs.getInt("o.realprice"));
				order.setExpressid(rs.getString("o.expressid"));
				order.setStatus(rs.getString("o.status"));
				order.setIp(rs.getString("o.ip"));
				order.setSubtype(rs.getInt("o.subtype"));
				order.setFatherorderid(rs.getLong("o.fatherorderid"));
				return order;
			}
		});
	
	}

	
	public int findTotalOrderList(long hyuid, String status,int type)
	{
		String sql = "";
		List<Object> list = new ArrayList<Object>();
		list.add(hyuid);list.add(type);
		if(StringUtils.isNotBlank(status)){
			sql = "select count(o.id) from es_pay_order o where o.hyuid = ? and o.subtype = ? and o.status =? and o.del_tag != 'Y' and o.fatherorderid is null";
			list.add(status);
		}else{
			sql = "select count(o.id) from es_pay_order o where o.hyuid = ? and o.subtype = ? and o.del_tag != 'Y' and o.fatherorderid is null";
		}
		return jdbcTemplate.queryForInt(sql, list.toArray());
	}

	@Override
	public int findTotalKQOrderList(long hyuid,String status,int type){
		String sql = "";
		List<Object> list = new ArrayList<Object>();
		list.add(hyuid);list.add(type);
		if(StringUtils.isNotBlank(status)){
			sql = "select count(o.id) from es_pay_order o where o.hyuid = ? and o.subtype = ? and o.status =? and o.del_tag != 'Y' and o.fatherorderid > 0";
			list.add(status);
		}else{
			sql = "select count(o.id) from es_pay_order o where o.hyuid = ? and o.subtype = ? and o.del_tag != 'Y' and o.fatherorderid > 0";
		}
		return jdbcTemplate.queryForInt(sql, list.toArray());
	}
	
	@Override
	public int updateShoppingCartJf(long productid, long hyuid, int usejf)
	{
		String sql = "update es_pay_shopping_cart set usejf = ? where hyuid = ? and productid = ? and status != 'DEL'";
		Object[] params = {usejf,hyuid,productid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updatePayShoppingCartNum(long id, long hyuid,long paid)
	{
		String sql = "update es_pay_shopping_cart set num = num + 1 where hyuid = ? and id = ? and paid = ?";
		Object[] params = {hyuid,id,paid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public ContentProduct findShoppingCartByProductid(long hyuid, long productid)
	{
		String sql = "select * from es_pay_shopping_cart c join es_content_product p on c.productid = p.id where c.hyuid = ? and c.productid = ? and c.status != 'DEL'";
		Object[] params = {hyuid,productid};
		List<ContentProduct> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ContentProduct p = new ContentProduct();
				p.setId(rs.getLong("p.id"));
//				p.setNum(rs.getInt("c.num"));
//				p.setUsejf(rs.getInt("c.usejf"));
				p.setOwner(rs.getLong("p.ownerid"));
				p.setCatid(rs.getLong("p.catid"));
				p.setName(rs.getString("p.name"));
				p.setSimgurl(rs.getString("p.simgurl"));
				p.setBimgurl(rs.getString("p.bimgurl"));
				p.setLinkurl(rs.getString("p.linkurl"));
				p.setPrice(rs.getDouble("p.price"));
				p.setSalesprice(rs.getDouble("p.salesprice"));
				p.setDetail(rs.getString("p.detail"));
				p.setStatus(rs.getString("p.status"));
				p.setCreatetime(rs.getTimestamp("p.createtime"));
				p.setUpdatetime(rs.getDate("p.updatetime"));
				p.setTag(rs.getString("p.tag"));
				p.setType(rs.getString("p.type"));
				p.setSubtype(rs.getString("p.subtype"));
				p.setTotal(rs.getInt("p.total"));
				p.setUsed(rs.getInt("p.used"));
				p.setMaxjf(rs.getInt("maxjf"));
				return p;
			}
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public long findPayHome(long owner)
	{
		List<Long> ls=jdbcTemplate.query("select homepage from  es_pay_shop where owner=?", new Object[]{owner},new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("homepage");
			}
			
		});
		if(ls!=null&&ls.size()>0){
			return ls.get(0);
		}
		return 0;
	}

	@Override
	public long findPayJfHome(long owner)
	{
		List<Long> ls=jdbcTemplate.query("select homepage from  es_pay_jf_shop where owner=?", new Object[]{owner},new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("homepage");
			}
			
		});
		if(ls!=null&&ls.size()>0){
			return ls.get(0);
		}
		return 0;
	}

	@Override
	public int findShoppingCartProductNumByHyuid(long hyuid)
	{
		String sql = "select count(c.id) from es_pay_shopping_cart c join es_content_product p on c.productid = p.id where c.hyuid = ? and c.status != 'DEL'";
		Object[] params = {hyuid};
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public long findPayJfUserPage(long owner)
	{
		List<Long> ls=jdbcTemplate.query("select userpage from  es_pay_jf_shop where owner=?", new Object[]{owner},new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("userpage");
			}
			
		});
		if(ls!=null&&ls.size()>0){
			return ls.get(0);
		}
		return 0;
	}

	@Override
	public PayRecord findPayRecordById(long id)
	{
		String sql = "select * from es_pay_record where id = ?";
		Object[] params = {id};
		List<PayRecord> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				PayRecord r = new PayRecord();
				r.setId(rs.getLong("id"));
				r.setCreatetime(rs.getTimestamp("createtime"));
				r.setMediaorder(rs.getString("mediaorder"));
				r.setPrice(rs.getInt("price"));
				return r;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int delPayOrder(long payOrderid)
	{
		String sql = "update es_pay_order set del_tag = 'Y' where id = ?";
		Object[] params = {payOrderid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public PayOrderTemplate findPayOrderNameById(long payOrderid)
	{
		String  sql = "select g.productname,u.openid from es_pay_order o join es_pay_order_goods g on o.id = g.orderid join es_hy_user hu on hu.id=o.hyuid join es_wx_user u on u.id=hu.wxuid  where o.id=?";
		
		List<PayOrderTemplate> list= jdbcTemplate.query(sql, new Object[]{payOrderid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				PayOrderTemplate p = new PayOrderTemplate();
				p.setName(rs.getString("g.productname"));
                p.setOpenid(rs.getString("u.openid"));
				return p;
			}
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	
	}

	@Override
	public WkqCmp findWkqCmp(long poid)
	{
		String sql = "select po.createtime,u.nickname,u.openid from es_pay_order po,es_wx_user u,es_hy_user hu where po.id = ? and po.hyuid=hu.id and hu.wxuid=u.id";
		Object[] params = {poid};
		List<WkqCmp> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				WkqCmp pa = new WkqCmp();
				pa.setNickname(rs.getString("u.nickname"));
				pa.setOpenid(rs.getString("u.openid"));
				pa.setTime(rs.getTimestamp("po.createtime"));
				return pa;
			}
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public void updateWkqRecord(long wxuid, long pogid)
	{
		Object[] params = {wxuid,pogid};
		String sql = "INSERT INTO esite.es_wkq_record (wxuid,pogid,createtime) VALUES (?,?,now())";
		 jdbcTemplate.update(sql, params);
	}

	@Override
	public PayApt findPayAptById(long id)
	{
		String sql = "select * from es_pay_apt where id = ?";
		Object [] params = {id};
		List<PayApt> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				PayApt payApt = new PayApt();
				payApt.setId(rs.getLong("id"));
				payApt.setHyuid(rs.getLong("hyuid"));
				payApt.setImg(rs.getString("img"));
				payApt.setWxuid(rs.getLong("wxuid"));
				return payApt;
			}
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public long savePayOrderGoods(final long orderid,final long productid,final String productname,final String productSubtype,final String productsimg,final int price,final int type,final String uuid,final int quantity,final long paid,final long shoppingcartid)
	{
		final String sql = "insert into es_pay_order_goods(orderid,productid,productname,productSubtype,productsimg,createtime,price,type,uuid,num,paid,shoppingcartid) values(?,?,?,?,?,now(),?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i=1;
				ps.setLong(i++, orderid);
				ps.setLong(i++, productid);
				ps.setString(i++, productname);
				ps.setString(i++, productSubtype);
				ps.setString(i++, productsimg);
				ps.setInt(i++, price);
				ps.setInt(i++, type);
				ps.setString(i++, uuid);
				ps.setInt(i++, quantity);
				ps.setLong(i++, paid);
				ps.setLong(i++, shoppingcartid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}

	private static final String FIND_SHOPPING_PRDUCT_BY_ID="select sc.*,p.id pid,p.name,p.simgurl,p.linkurl,p.type,p.subtype,p.maxjf,p.salesprice,p.personlimit from es_pay_shopping_cart sc join es_content_product p on sc.productid = p.id where sc.id = ? and sc.hyuid = ? and sc.status !='DEL'";
	@Override
	public ShoppingCartRecord findShoppingProductById(long id,long hyuid)
	{
		Object[] param={id,hyuid};
		List<ShoppingCartRecord> list = jdbcTemplate.query(FIND_SHOPPING_PRDUCT_BY_ID, param, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				ShoppingCartRecord cr=new ShoppingCartRecord();
				cr.setId(rs.getLong("id"));
				cr.setHyuid(rs.getLong("hyuid"));
				cr.setProductid(rs.getLong("productid"));
				cr.setNum(rs.getInt("num"));
				cr.setStatus(rs.getString("status"));
				cr.setCreatetime(rs.getTimestamp("createtime"));
				cr.setPaid(rs.getLong("paid"));
				ContentProduct cp = new ContentProduct();
				cp.setId(rs.getLong("pid"));
				cp.setName(rs.getString("name"));
				cp.setSimgurl(rs.getString("simgurl"));
				cp.setLinkurl(rs.getString("linkurl"));
				cp.setType(rs.getString("type"));
				cp.setSubtype(rs.getString("subtype"));
				cp.setMaxjf(rs.getInt("maxjf"));
				cp.setSalesprice(rs.getDouble("salesprice"));
				cp.setPersonlimit(rs.getInt("personlimit"));
				cr.setProduct(cp);
				return cr;
			}
			
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public void removeOtherAddressDefault(long hyuid)
	{
		jdbcTemplate.update("update es_pay_address set isdefault='N' where hyuid=?", new Object[]{hyuid});
	}

	@Override
	public long saveNewOrder(final long hyuid,final long wxuid,final long owner,final int type,final long addressid,final String ip)
	{
		final String sql = "insert into es_pay_order(hyuid,wxuid,ownerid,createtime,addressid,ip,subtype) values(?,?,?,now(),?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i=1;
				ps.setLong(i++, hyuid);
				ps.setLong(i++, wxuid);
				ps.setLong(i++, owner);
				ps.setLong(i++, addressid);
				ps.setString(i++, ip);
				ps.setInt(i++, type);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}
	
	@Override
	public long saveChildOrder(final long hyuid,final long wxuid,final long owner,final int type,final long addressid,final String ip,final long fatherorderid,final String status)
	{
		final String sql = "insert into es_pay_order(hyuid,wxuid,ownerid,createtime,addressid,ip,subtype,fatherorderid,status) values(?,?,?,now(),?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i=1;
				ps.setLong(i++, hyuid);
				ps.setLong(i++, wxuid);
				ps.setLong(i++, owner);
				ps.setLong(i++, addressid);
				ps.setString(i++, ip);
				ps.setInt(i++, type);
				ps.setLong(i++, fatherorderid);
				ps.setString(i++, status);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}

	@Override
	public List<OrderGoods> findPayOrderGoods(long payOrderid)
	{
		String sql = "select * from es_pay_order_goods where orderid = ? ";
		Object[] params = {payOrderid};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				OrderGoods cp = new OrderGoods();
				cp.setId(rs.getLong("id"));
				cp.setOrderid(rs.getLong("orderid"));
				cp.setPid(rs.getLong("productid"));
				cp.setProductname(rs.getString("productname"));
				cp.setProductsubtype(rs.getString("productsubtype"));
				cp.setProductsimg(rs.getString("productsimg"));
				cp.setPrice(rs.getInt("price"));
				cp.setType(rs.getInt("type"));
				cp.setUuid(rs.getString("uuid"));
				cp.setNum(rs.getInt("num"));
				cp.setPaid(rs.getLong("paid"));
				cp.setUsed(rs.getString("used"));
				cp.setShoppingcartid(rs.getLong("shoppingcartid"));
				return cp;
			}
			
		});
	}

	@Override
	public long savePayRecord(final PayRecord payRecord)
	{
		final String sql = "insert into es_pay_record(mediaorder,createtime,ip,status,price,hyuid,orderid) values(?,now(),?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i=1;
				ps.setString(i++, payRecord.getMediaorder());
				ps.setString(i++, payRecord.getIp());
				ps.setInt(i++, 1);
				ps.setInt(i++, payRecord.getPrice());
				ps.setLong(i++, payRecord.getHyuid());
				ps.setLong(i++, payRecord.getOrderid());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public int updatePayorderStatusByFid(long out_trade_no,String status)
	{
		String sql = "update es_pay_order set status = ? where fatherorderid = ?";
		Object[] params = {status,out_trade_no};
		return jdbcTemplate.update(sql, params);
	}

	private static final String UPDATE_ORDER_PRICE_BY_ID="update es_pay_order set totalprice = ?,discountprice=?,realprice=? where id = ?";
	@Override
	public int updateOrderPriceById(long orderid, int totalprice, int discountprice, int realprice)
	{
		Object[] params={totalprice,discountprice,realprice,orderid};
		return jdbcTemplate.update(UPDATE_ORDER_PRICE_BY_ID, params);
	}

	private static final String UPDATE_USE_Jf="update es_pay_order set usejf=? where id =? and hyuid = ?";
	@Override
	public int updateUseJf(long orderid, long hyuid, int usejf)
	{
		Object[] params={usejf,orderid,hyuid};
		return jdbcTemplate.update(UPDATE_USE_Jf, params);
	}

	private static final String DELETE_SHOPPING_CART="update es_pay_shopping_cart set status='DEL' where id = ? and hyuid = ?";
	@Override
	public int deleteShoppingCart(long id, long hyuid)
	{
		Object[] params={id,hyuid};
		return jdbcTemplate.update(DELETE_SHOPPING_CART,params);
	}

	@Override
	public int updatePayorderByFid(long payOrderid)
	{
		String sql = "update es_pay_order set del_tag = 'Y' where fatherorderid = ?";
		Object[] params = {payOrderid};
		return jdbcTemplate.update(sql, params);	
	}

	@Override
	public OrderGoods findPayOrderGoodsById(long id)
	{
		String sql = "select * from es_pay_order_goods where id = ? ";
		Object[] params = {id};
		List<OrderGoods> ls= jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				OrderGoods cp = new OrderGoods();
				cp.setId(rs.getLong("id"));
				cp.setOrderid(rs.getLong("orderid"));
				cp.setPid(rs.getLong("productid"));
				cp.setProductname(rs.getString("productname"));
				cp.setProductsubtype(rs.getString("productsubtype"));
				cp.setProductsimg(rs.getString("productsimg"));
				cp.setPrice(rs.getInt("price"));
				cp.setType(rs.getInt("type"));
				cp.setUuid(rs.getString("uuid"));
				cp.setNum(rs.getInt("num"));
				cp.setPaid(rs.getLong("paid"));
				cp.setUsed(rs.getString("used"));
				cp.setShoppingcartid(rs.getLong("shoppingcartid"));
				return cp;
			}
		});
		if(ls!=null&&ls.size()>0){
			return ls.get(0);
		}
		return null;
	}

	@Override
	public int updatePayorderStatusById(long orderid,String status)
	{
		String sql = "update es_pay_order set status = ? where id = ?";
		Object[] params = {status,orderid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public long savePayApt(final PayApt apt)
	{
		final String sql = "insert into es_pay_apt(hyuid,wxuid,img) values(?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i=1;
				ps.setLong(i++, apt.getHyuid());
				ps.setLong(i++, apt.getWxuid());
				ps.setString(i++, apt.getImg());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public long findChildOrder(long payOrderid)
	{
		String sql = "select id from es_pay_order where fatherorderid = ?";
		Object[] params = {payOrderid};
		try
		{
			return jdbcTemplate.queryForLong(sql, params);
		} catch (DataAccessException e){
		}
		return 0; 
	}

	@Override
	public PayOrderTemplate findPayOrderNameByFid(long payOrderid)
	{
		String  sql = "select g.productname,u.openid from es_pay_order o join es_pay_order_goods g on o.id = g.orderid join es_hy_user hu on hu.id=o.hyuid join es_wx_user u on u.id=hu.wxuid  where o.fatherorderid=?";
		List<PayOrderTemplate> list= jdbcTemplate.query(sql, new Object[]{payOrderid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				PayOrderTemplate p = new PayOrderTemplate();
				p.setName(rs.getString("g.productname"));
                p.setOpenid(rs.getString("u.openid"));
				return p;
			}
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	
	}

	@Override
	public PayOrder findChildOrderById(long payOrderid)
	{
		String sql = "select * from es_pay_order o where o.fatherorderid = ?";
		Object[] params = {payOrderid};
		List<PayOrder> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				PayOrder order = new PayOrder();
				order.setId(rs.getLong("o.id"));
				order.setHyuid(rs.getLong("o.hyuid"));
				order.setWxuid(rs.getLong("o.wxuid"));
				order.setOwnerid(rs.getLong("o.ownerid"));
				order.setCreatetime(rs.getTimestamp("o.createtime"));
				order.setAddressid(rs.getLong("o.addressid"));
				order.setSendtime(rs.getTimestamp("o.sendtime"));
				order.setTotalprice(rs.getInt("o.totalprice"));
				order.setDiscountprice(rs.getInt("o.discountprice"));
				order.setRealprice(rs.getInt("o.realprice"));
				order.setExpressid(rs.getString("o.expressid"));
				order.setStatus(rs.getString("o.status"));
				order.setIp(rs.getString("o.ip"));
				order.setSubtype(rs.getInt("o.subtype"));
				order.setGoodscount(rs.getInt("o.goodscount"));
				order.setFatherorderid(rs.getLong("o.fatherorderid"));
				return order;
			}
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public MarketingSet findPayShopByOwner(long owner)
	{
		List<MarketingSet> list=jdbcTemplate.query("select * from es_pay_shop where owner=?", new Object[]{owner}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				MarketingSet set=new MarketingSet();
				set.setDzpage(rs.getLong("dzpage"));
				set.setHomepage(rs.getLong("homepage"));
				set.setYzpage(rs.getLong("yzpage"));
				set.setSpage(rs.getLong("spage"));
				set.setFpage(rs.getLong("fpage"));
				set.setLpage(rs.getLong("lpage"));
				return set;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	
	@Override
	public MarketingSet findPayJfShopByOwner(long owner)
	{
		List<MarketingSet> list=jdbcTemplate.query("select * from es_pay_jf_shop where owner=?", new Object[]{owner}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				MarketingSet set=new MarketingSet();
				set.setDzpage(rs.getLong("dzpage"));
				set.setHomepage(rs.getLong("homepage"));
				set.setYzpage(rs.getLong("yzpage"));
				set.setSpage(rs.getLong("spage"));
				set.setFpage(rs.getLong("fpage"));
				set.setLpage(rs.getLong("lpage"));
				return set;
			}
		});
		return list.size()>0?list.get(0):null;
	}

	@Override
	public PayRecord findPayRecordByPoid(long payOrderid)
	{
		String sql = "select * from es_pay_record where orderid = ?";
		Object[] params = {payOrderid};
		List<PayRecord> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				PayRecord r = new PayRecord();
				r.setId(rs.getLong("id"));
				r.setCreatetime(rs.getTimestamp("createtime"));
				r.setMediaorder(rs.getString("mediaorder"));
				r.setPrice(rs.getInt("price"));
				return r;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int finfTotalUnusedKQOrderList(long hyuid, String status, int type)
	{
		String sql = "";
		List<Object> list = new ArrayList<Object>();
		list.add(hyuid);list.add(type);
		if(StringUtils.isNotBlank(status)){
			sql = "select count(o.id) from es_pay_order o join es_pay_order_goods g on g.orderid = o.id where o.hyuid = ? and o.subtype = ? and o.status =? and o.del_tag != 'Y' and o.fatherorderid > 0 and g.used = 'N'";
			list.add(status);
		}else{
			sql = "select count(o.id) from es_pay_order o join es_pay_order_goods g on g.orderid = o.id where o.hyuid = ? and o.subtype = ? and o.del_tag != 'Y' and o.fatherorderid > 0 and g.used = 'N'";
		}
		return jdbcTemplate.queryForInt(sql, list.toArray());
	}

	private static final String SELECT_ORDER_PRODUCT_BY_ORDERID="select hyuid,productid,num from es_pay_order o join es_pay_order_goods g on o.id = g.orderid where  o.id = ? or o.fatherorderid = ?";
	@Override
	public int updateAddPersonBuyProduct(long orderid)
	{
		Object[] param={orderid,orderid};
		List<String> list = jdbcTemplate.query(SELECT_ORDER_PRODUCT_BY_ORDERID, param, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				StringBuffer sb = new StringBuffer();
				sb.append("(").append(rs.getLong("hyuid")).append(",").append(rs.getLong("productid")).append(",").append(rs.getInt("num")).append(")");
				return sb.toString();
			}
			
		});
		if(list.size()==0){
			return 0;
		}
		String sql="insert into es_user_buy_product_count(hyuid,productid,count) values ";
		String end = " on duplicate key update count = count+VALUES(count);";
		for (String str : list)
		{
			sql += str + ",";
		}
		sql = sql.substring(0,sql.length()-1);
		sql += end;
		return jdbcTemplate.update(sql);
	}
	
	@Override
	public void updateOrderPcode(long id, long codeid)
	{
		jdbcTemplate.update("update es_pay_order_goods set pcodeid=? where id=?", new Object[]{codeid,id});
	}

	@Override
	public int updateCancelPersonBuyProduct(long orderid)
	{
		Object[] param={orderid,orderid};
		List<String> list = jdbcTemplate.query(SELECT_ORDER_PRODUCT_BY_ORDERID, param, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				StringBuffer sb = new StringBuffer();
				sb.append("(").append(rs.getLong("hyuid")).append(",").append(rs.getLong("productid")).append(",").append(rs.getInt("num")).append(")");
				return sb.toString();
			}
			
		});
		if(list.size()==0){
			return 0;
		}
		String sql="insert into es_user_buy_product_count(hyuid,productid,count) values ";
		String end = " on duplicate key update count = count - VALUES(count);";
		for (String str : list)
		{
			sql += str + ",";
		}
		sql = sql.substring(0,sql.length()-1);
		sql += end;
		return jdbcTemplate.update(sql);
	}

	@Override
	public ProductLevel findProductLevel(long productid, long levelid) {
		String sql = "select price from es_content_product_level where productid = ? and levelid = ?";
		Object[] params = {productid,levelid};
		List<ProductLevel> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ProductLevel pl = new ProductLevel();
				pl.setPrice(rs.getInt("price"));
				return pl;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int findBuyProductNumByProductid(long productid,long hyuid) {
		String sql = "select count from es_user_buy_product_count where productid = ? and hyuid = ?";
		Object[] params = {productid,hyuid};
		try {
			return jdbcTemplate.queryForInt(sql, params);
		} catch (Exception e) {
		}
		return 0;
	}
	
}
