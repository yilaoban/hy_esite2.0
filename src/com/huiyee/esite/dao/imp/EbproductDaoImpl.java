
package com.huiyee.esite.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IEbproductDao;
import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.MarketingOrder;
import com.huiyee.esite.model.MarketingSet;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.ProductCode;
import com.huiyee.esite.model.UserLevel;
import com.huiyee.weixin.model.PayAddress;

public class EbproductDaoImpl extends AbstractDao implements IEbproductDao
{

	public List<CategoryTree> findCategory(long fatherCatid, String type, String subtype, long owner)
	{
		String sql = "";
		Object[] param = null;
		if (fatherCatid == 0)
		{
			sql = "select * from es_content_category where type=? and subtype=? and fartherCatId is null and status!='DEL' and ownerid=?";
			param = new Object[]
			{ type, subtype, owner };
		} else
		{
			sql = "select * from es_content_category where type=? and subtype=? and fartherCatId = ?  and status!='DEL' and ownerid=?";
			param = new Object[]
			{ type, subtype, fatherCatid, owner };
		}
		return getJdbcTemplate().query(sql, param, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CategoryTree t = new CategoryTree();
				t.setId(rs.getLong("id"));
				t.setPId(rs.getLong("fartherCatId"));
				t.setName(rs.getString("name"));
				return t;
			}
		});
	}

	@Override
	public int findListTotal(long ccid, long owner)
	{
		return getJdbcTemplate().queryForInt("select count(id) from es_content_product where catid=? and ownerid=? and status!='DEL'", new Object[]
		{ ccid, owner });
	}

	@Override
	public List<ContentProduct> findListByccid(long ccid, long owner, int start, int size)
	{
		return getJdbcTemplate().query("select * from es_content_product where catid=? and ownerid=? and status!='DEL' order by idx desc limit ?,? ", new Object[]
		{ ccid, owner, start, size }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ContentProduct p = new ContentProduct();
				p.setId(rs.getLong("id"));
				p.setOwner(rs.getLong("ownerid"));
				p.setCatid(rs.getLong("catid"));
				p.setName(rs.getString("name"));
				p.setSimgurl(rs.getString("simgurl"));
				p.setBimgurl(rs.getString("bimgurl"));
				p.setLinkurl(rs.getString("linkurl"));
				p.setPrice(rs.getDouble("price"));
				p.setStatus(rs.getString("status"));
				p.setCreatetime(rs.getTimestamp("createtime"));
				p.setUpdatetime(rs.getTimestamp("updatetime"));
				p.setIdx(rs.getInt("idx"));
				p.setType(rs.getString("type"));
				p.setSubtype(rs.getString("subtype"));
				p.setVip(rs.getInt("vip"));
				p.setPersonlimit(rs.getInt("personlimit"));
				return p;
			}
		});
	}

	@Override
	public int findCodeTotal(long pid, String code, String status)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(id) from es_content_product_code  where pid=? ";
		list.add(pid);
		if (code != null && code.trim().length() > 0)
		{
			sql += " and code like ?";
			list.add("%" + code + "%");
		}
		if (status != null && status.trim().length() > 0)
		{
			sql += " and status = ?";
			list.add(status);
		}
		return getJdbcTemplate().queryForInt(sql, list.toArray());
	}

	@Override
	public List<ProductCode> findCodeList(long pid, String code, String status, int i, int j)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from es_content_product_code  where pid=? ";
		list.add(pid);
		if (code != null && code.trim().length() > 0)
		{
			sql += " and code like ?";
			list.add("%" + code + "%");
		}
		if (status != null && status.trim().length() > 0)
		{
			sql += " and status = ?";
			list.add(status);
		}
		return getJdbcTemplate().query(sql, list.toArray(), new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ProductCode pc = new ProductCode();
				pc.setId(rs.getLong("id"));
				pc.setPid(rs.getLong("pid"));
				pc.setCode(rs.getString("code"));
				pc.setStatus(rs.getString("status"));
				return pc;
			}
		});
	}

	@Override
	public int saveProductCode(final long pid, final List<String> insertList)
	{
		int[] i = getJdbcTemplate().batchUpdate("insert ignore into es_content_product_code(pid,code) values(?,?)", new BatchPreparedStatementSetter()
		{

			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException
			{
				int i = 1;
				ps.setLong(i++, pid);
				ps.setString(i++, insertList.get(index));
			}

			@Override
			public int getBatchSize()
			{
				return insertList.size();
			}
		});
		return i.length;
	}

	@Override
	public List<MarketingOrder> findOrderList(long owner, JSONObject sift, int subtype, int start, int size, Date startTime, Date endTime)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from es_pay_order o left outer join es_pay_address a on o.addressid=a.id where o.ownerid=? and o.subtype=? and o.fatherorderid is null";
		list.add(owner);
		list.add(subtype);
		if (sift.has("status"))
		{
			if (sift.getJSONArray("status").size() < 5)
			{
				sql += " and ( ";
				JSONArray ja = sift.getJSONArray("status");
				String statusStr = "";
				for (Object object : ja)
				{
					statusStr += "or o.status = ? ";
					list.add(object);
				}
				statusStr = statusStr.replaceFirst("or", "");
				sql += statusStr + " ) ";
			}
		}
		if(sift.has("orderid")&&sift.getJSONArray("orderid").size()>0){
			String str=(String) sift.getJSONArray("orderid").get(0);
			if(StringUtils.isNotEmpty(str)){
				sql+=" and o.id like ?";
				list.add("%"+str+"%");
			}
		}
		if (startTime != null) {
			sql += " and o.createtime > ? ";
			list.add(startTime);
		}
		if (endTime != null) {
			sql += " and o.createtime < ? ";
			list.add(endTime);
		}
		
		sql+=" order by o.id desc limit ?,?";
		list.add(start);list.add(size);
		return getJdbcTemplate().query(sql, list.toArray(), new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				MarketingOrder order = new MarketingOrder();
				order.setId(rs.getLong("o.id"));
				order.setHyuid(rs.getLong("o.hyuid"));
				order.setWxuid(rs.getLong("o.wxuid"));
				order.setStatus(rs.getString("o.status"));
				order.setCreatetime(rs.getTimestamp("o.createtime"));
				order.setExpressid(rs.getString("o.expressid"));
				order.setTotalprice(rs.getInt("totalprice"));
				order.setDiscountprice(rs.getInt("discountprice"));
				order.setRealprice(rs.getInt("realprice"));
				order.setSubtype(rs.getInt("subtype"));
				
				
				PayAddress pa = new PayAddress();
				pa.setId(rs.getLong("a.id"));
				pa.setHyuid(rs.getLong("a.hyuid"));
				pa.setAddress(rs.getString("a.address"));
				pa.setCity(rs.getString("a.city"));
				pa.setName(rs.getString("a.name"));
				pa.setProvince(rs.getString("a.province"));
				pa.setTelphone(rs.getString("a.telphone"));
				order.setAddress(pa);
				
				return order;
			}
		});
	}

	@Override
	public int findOrderTotal(long owner, JSONObject sift, int subtype,Date startTime,Date endTime)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(*) from es_pay_order o where o.ownerid=? and o.subtype=? and o.fatherorderid is null";
		list.add(owner);
		list.add(subtype);
		if (sift.has("status"))
		{
			if (sift.getJSONArray("status").size() < 5)
			{
				sql += " and ( ";
				JSONArray ja = sift.getJSONArray("status");
				String statusStr = "";
				for (Object object : ja)
				{
					statusStr += "or o.status = ? ";
					list.add(object);
				}
				statusStr = statusStr.replaceFirst("or", "");
				sql += statusStr + " ) ";
			}
		}
		if(sift.has("orderid")){
			sql+=" and o.id like ?";
			list.add("%"+sift.getJSONArray("orderid").get(0)+"%");
		}
		if (startTime != null) {
			sql += " and o.createtime > ? ";
			list.add(startTime);
		}
		if (endTime != null) {
			sql += " and o.createtime < ? ";
			list.add(endTime);
		}
		return getJdbcTemplate().queryForInt(sql, list.toArray());
	}
	
	@Override
	public int updateOrderExpress(long orderid, String express)
	{
		return getJdbcTemplate().update("update es_pay_order set expressid=?,status='DSH' where id=?", new Object[]{express,orderid});
	}
	
	
	@Override
	public MarketingSet findjifenSetting(long owner)
	{
		List<MarketingSet> list=getJdbcTemplate().query("select * from es_pay_jf_shop where owner=?", new Object[]{owner}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				MarketingSet m=new MarketingSet();
				m.setId(rs.getLong("id"));
				m.setOwner(rs.getLong("owner"));
				m.setDzpage(rs.getLong("dzpage"));
				m.setHomepage(rs.getLong("homepage"));
				m.setUserpage(rs.getLong("userpage"));
				m.setYzpage(rs.getLong("yzpage"));
				m.setSpage(rs.getLong("spage"));
				m.setFpage(rs.getLong("fpage"));
				return m;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public MarketingSet findwdsSetting(long owner)
	{
		List<MarketingSet> list=getJdbcTemplate().query("select * from es_pay_shop where owner=?", new Object[]{owner}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				MarketingSet m=new MarketingSet();
				m.setId(rs.getLong("id"));
				m.setOwner(rs.getLong("owner"));
				m.setDzpage(rs.getLong("dzpage"));
				m.setHomepage(rs.getLong("homepage"));
				m.setYzpage(rs.getLong("yzpage"));
				m.setSpage(rs.getLong("spage"));
				m.setFpage(rs.getLong("fpage"));
				m.setBili(rs.getInt("bili"));
				return m;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public int updateHomepage(long id, MarketingSet marketingSet)
	{
		return getJdbcTemplate().update("replace into es_pay_shop (owner,dzpage,homepage,yzpage,spage,fpage,lpage) values(?,?,?,?,?,?,?)", new Object[]{id,marketingSet.getDzpage(),marketingSet.getHomepage(),marketingSet.getYzpage(),marketingSet.getSpage(),marketingSet.getFpage(),marketingSet.getLpage()});
	}
	
	@Override
	public int updateJifenHomepage(long id, MarketingSet marketingSet)
	{
		return getJdbcTemplate().update("replace into es_pay_jf_shop (owner,dzpage,homepage,userpage,yzpage,spage,fpage,lpage) values(?,?,?,?,?,?,?,?)", new Object[]{id,marketingSet.getDzpage(),marketingSet.getHomepage(),marketingSet.getUserpage(),marketingSet.getYzpage(),marketingSet.getSpage(),marketingSet.getFpage(),marketingSet.getLpage()});
	}
	@Override
	public ProductCode findCodeById(long pcid)
	{
		List<ProductCode> list = new ArrayList<ProductCode>();
		String sql = "select * from es_content_product_code  where id=? ";
		list=getJdbcTemplate().query(sql, new Object[]{pcid}, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ProductCode pc = new ProductCode();
				pc.setId(rs.getLong("id"));
				pc.setPid(rs.getLong("pid"));
				pc.setCode(rs.getString("code"));
				pc.setStatus(rs.getString("status"));
				return pc;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public List<OrderGoods> findOrderGoodsByOrid(long orderid)
	{
		return getJdbcTemplate().query("select * from es_pay_order_goods  where orderid=?", new Object[]{orderid}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				OrderGoods og=new OrderGoods();
				og.setId(rs.getLong("id"));
				og.setOrderid(rs.getLong("orderid"));
				og.setPid(rs.getLong("productid"));
				og.setPcid(rs.getLong("pcodeid"));
				og.setCreatetime(rs.getTimestamp("createtime"));
				og.setPrice(rs.getInt("price"));
				og.setType(rs.getInt("type"));
				og.setUuid(rs.getString("uuid"));
				og.setNum(rs.getInt("num"));
				og.setProductname(rs.getString("productname"));
				og.setProductsubtype(rs.getString("productsubtype"));
				return og;
			}
		});
	}
	
	@Override
	public List<OrderGoods> findOrderGoodsByfoid(long orderid)
	{
		return getJdbcTemplate().query("select g.* from es_pay_order_goods g join es_pay_order o on g.orderid=o.id  where o.fatherorderid=?", new Object[]{orderid}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				OrderGoods og=new OrderGoods();
				og.setId(rs.getLong("id"));
				og.setOrderid(rs.getLong("orderid"));
				og.setPid(rs.getLong("productid"));
				og.setPcid(rs.getLong("pcodeid"));
				og.setCreatetime(rs.getTimestamp("createtime"));
				og.setPrice(rs.getInt("price"));
				og.setType(rs.getInt("type"));
				og.setUuid(rs.getString("uuid"));
				og.setNum(rs.getInt("num"));
				og.setProductname(rs.getString("productname"));
				og.setProductsubtype(rs.getString("productsubtype"));
				return og;
			}
		});
	}
	
	@Override
	public int updateJifenToPrice(long id, int jftoprice)
	{
		return getJdbcTemplate().update("update es_pay_shop set bili=? where owner=?", new Object[]{jftoprice,id});
	}

	@Override
	public List<UserLevel> findpriceByowner(long owner,long pid)
	{
		String sql ="select distinct ul.id,ul.name,cpl.price from es_hy_user_level ul left join es_content_product_level cpl on ul.id=cpl.levelid where owner =? and productid = ?";
		return getJdbcTemplate().query(sql, new Object[]{owner,pid}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				UserLevel ul=new UserLevel();
				ul.setId(rs.getLong("ul.id"));
				ul.setName(rs.getString("ul.name"));
				ul.setPrice(rs.getDouble("cpl.price"));
				return ul;
			}
		});
	}
	
	@Override
	public List<UserLevel> findLevelNameByowner(long owner)
	{
		String sql ="select * from es_hy_user_level where owner =?";
		return getJdbcTemplate().query(sql, new Object[]{owner}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				UserLevel ul=new UserLevel();
				ul.setId(rs.getLong("id"));
				ul.setName(rs.getString("name"));
				return ul;
			}
		});
	}

	@Override
	public ContentProduct findListBypid(long pid)
	{
		
		List<ContentProduct> list=getJdbcTemplate().query("select * from es_content_product where id=?", new Object[]{pid}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ContentProduct p = new ContentProduct();
				p.setId(rs.getLong("id"));
				p.setVip(rs.getInt("vip"));
				return p;
			}
		});
		return list.size()>0?list.get(0):null;
	}
}
