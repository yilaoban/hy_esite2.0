package com.huiyee.esite.dao.imp;

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
import com.huiyee.esite.dao.IXuantuDao;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.weixin.model.ShoppingCartRecord;

import net.sf.json.JSONObject;

public class XuantuDaoImpl extends AbstractDao implements IXuantuDao {

	private static final String ADD_COLLECTION="insert ignore into  es_xuantu_product_collection(ownerid,productid,hyuid,createtime) values(?,?,?,now())";
	@Override
	public int addCollection(long productid, long ownerid, long hyuid)
	{
		Object[] params={ownerid,productid,hyuid};
		return getJdbcTemplate().update(ADD_COLLECTION,params);
	}

	private static final String CHECK_COLLECTION="select count(id) from es_xuantu_product_collection where ownerid= ? and productid = ? and hyuid=?";
	@Override
	public int checkCollection(long productid, long ownerid, long hyuid)
	{
		Object[] params={ownerid,productid,hyuid};
		return getJdbcTemplate().queryForInt(CHECK_COLLECTION,params);
	}
	
	private static final String MY_COLLECTION="select * from es_xuantu_product_collection t1 join es_content_product t2 on t1.productid = t2.id where t1.ownerid= ? and t1.hyuid=? order by t1.id desc limit ?,?";
	@Override
	public List myCollection(long ownerid, long hyuid, int start, int size)
	{
		Object[] params = {ownerid,hyuid,start,size};
		return getJdbcTemplate().query(MY_COLLECTION, params, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				JSONObject jo = new JSONObject();
				jo.put("id", rs.getLong("t1.id"));
				jo.put("productid", rs.getString("t1.productid"));
				jo.put("simgurl", rs.getString("t2.simgurl"));
				jo.put("bimgurl", rs.getString("t2.bimgurl"));
				jo.put("catid", rs.getLong("t2.catid"));
				jo.put("name", rs.getString("t2.name"));
				return jo;
			}
			
		});
	}
	
	private static final String MY_TOTAL = "select count(t1.id) from es_xuantu_product_collection t1 join es_content_product t2 on t1.productid = t2.id where t1.ownerid= ? and t1.hyuid=? ";
	@Override
	public int mytotal(long ownerid, long hyuid)
	{
		Object[] params={ownerid,hyuid};
		return getJdbcTemplate().queryForInt(MY_TOTAL, params);
	}
	
	private static final String REMOVE_COLLECTION="delete from es_xuantu_product_collection where ownerid = ? and hyuid = ? and productid = ?";
	@Override
	public int removeCollection(long ownerid, long hyuid, long productid)
	{
		Object[] params={ownerid,hyuid,productid};
		return getJdbcTemplate().update(REMOVE_COLLECTION, params);
	}
	
	@Override
	public ContentProduct findEasyProductById(long id,long owner)
	{
		String sql = "select id,type,name from es_content_product where id= ? and ownerid= ? and status ='CMP'";
		List<ContentProduct> cp = getJdbcTemplate().query(sql, new Object[]
		{id,owner}, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ContentProduct p = new ContentProduct();
				p.setId(rs.getLong("id"));
				p.setType(rs.getString("type"));
				p.setName(rs.getString("name"));
				return p;
			}
			
		});
		if (cp.size() > 0)
		{
			return cp.get(0);
		}
		return null;
	}
	
	@Override
	public ShoppingCartRecord findShoppingCartProduct(long productid, long hyuid,String status)
	{
		String sql = "select c.id,c.num,c.productid,c.paid from es_pay_shopping_cart c where c.hyuid = ? and c.productid = ? and c.status = ?";
		Object[] params = {hyuid,productid,status};
		List<ShoppingCartRecord> list = getJdbcTemplate().query(sql, params, new RowMapper(){
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
	public int updatePayShoppingCartNum(long id, long hyuid)
	{
		String sql = "update es_pay_shopping_cart set num = num + 1 where hyuid = ? and id = ?";
		Object[] params = {hyuid,id};
		return getJdbcTemplate().update(sql, params);
	}
	
	
	@Override
	public long savePayShoppingCart(final long productid,final long hyuid,final String status)
	{
		final String sql = "insert into es_pay_shopping_cart(hyuid,productid,num,createtime,status) values(?,?,?,now(),?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
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
				ps.setString(i++, status);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
		
		
	}

	private static final String FIND_MY_WANT="select * from es_pay_shopping_cart t1 join es_content_product t2 on t1.productid = t2.id where t1.hyuid = ? and t2.ownerid = ? and t1.status = ? order by t1.id desc limit ?,?";
	@Override
	public List findShoppingCartProductByHyuid(long hyuid, long owner, String type, int start, int size)
	{
		Object[] params = {hyuid,owner,type,start,size};
		return getJdbcTemplate().query(FIND_MY_WANT, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				JSONObject jo = new JSONObject();
				jo.put("id", rs.getLong("t1.id"));
				jo.put("productid", rs.getString("t1.productid"));
				jo.put("simgurl", rs.getString("t2.simgurl"));
				jo.put("bimgurl", rs.getString("t2.bimgurl"));
				jo.put("catid", rs.getLong("t2.catid"));
				jo.put("name", rs.getString("t2.name"));
				jo.put("number", rs.getInt("t1.num"));
				return jo;
			}
			
		});
	}

	private static final String DEL_MY_WANT="delete from es_pay_shopping_cart where hyuid = ? and id = ?";
	@Override
	public int delShopCartProduct(long shopCartid, long hyuid)
	{
		Object[] params = {hyuid,shopCartid};
		return getJdbcTemplate().update(DEL_MY_WANT, params);
	}

	private static final String DEL_SHOPCART="delete from es_pay_shopping_cart where hyuid = ?";
	@Override
	public int delShopCart(long hyuid)
	{
		Object[] params = {hyuid};
		return getJdbcTemplate().update(DEL_SHOPCART, params);
	}
	
}
