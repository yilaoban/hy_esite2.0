
package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IContentProductDao;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.ContentTab;
import com.huiyee.esite.model.ProductCode;
import com.huiyee.weixin.model.ProductLevel;

public class ContentProductDaoImpl extends AbstractDao implements IContentProductDao
{

	public List<ContentProduct> findProductByCcid(long ccid, long owner, int start, int size, String name)
	{
		String sql = "select * from es_content_product product join es_content_category category  on product.catid=category.id where catid =? and product.ownerid=? and product.status!='DEL' ";
		List<Object> list = new ArrayList<Object>();
		list.add(ccid);
		list.add(owner);
		if (StringUtils.isNotEmpty(name))
		{
			sql += " and product.name like ? ";
			list.add("%" + name + "%");
		}
		sql += " order by product.idx desc limit ?,? ";
		list.add(start);
		list.add(size);
		return getJdbcTemplate().query(sql, list.toArray(), new myRowMapper());
	}

	class myRowMapper implements RowMapper
	{

		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			ContentProduct p = new ContentProduct();
			p.setId(rs.getLong("product.id"));
			p.setOwner(rs.getLong("product.ownerid"));
			p.setCatid(rs.getLong("product.catid"));
			p.setCatname(rs.getString("category.name"));
			p.setName(rs.getString("product.name"));
			p.setSimgurl(rs.getString("product.simgurl"));
			p.setBimgurl(rs.getString("product.bimgurl"));
			p.setLinkurl(rs.getString("product.linkurl"));
			p.setPrice(rs.getDouble("product.price"));
			p.setSalesprice(rs.getDouble("product.salesprice"));
			p.setDetail(rs.getString("product.detail"));
			p.setStatus(rs.getString("product.status"));
			p.setCreatetime(rs.getTimestamp("product.createtime"));
			p.setUpdatetime(rs.getTimestamp("product.updatetime"));
			p.setTag(rs.getString("product.tag"));
			p.setIdx(rs.getInt("product.idx"));
			p.setTotal(rs.getInt("product.total"));
			p.setUsed(rs.getInt("product.used"));
			p.setVip(rs.getInt("vip"));
			p.setPersonlimit(rs.getInt("personlimit"));
			p.setF1(rs.getString("f1"));
			p.setF2(rs.getString("f2"));
			p.setF3(rs.getString("f3"));
			p.setF4(rs.getString("f4"));
			p.setF5(rs.getString("f5"));
			return p;
		}
	}

	private static final String SAVE_PRODUCT = "insert into es_content_product (ownerid,catid,name,simgurl,bimgurl,linkurl,price,salesprice,detail,status,createtime,updatetime,tag,idx,type,subtype,total,used,maxjf,fatherid,shownum,personlimit) values(?,?,?,?,?,?,?,?,?,?,now(),now(),?,?,?,?,?,?,?,?,?,?)";

	public long saveProduct(final ContentProduct product)
	{
		final int idx = findMaxIndx(product.getCatid());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(SAVE_PRODUCT, new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, product.getOwner());
				ps.setLong(i++, product.getCatid());
				ps.setString(i++, product.getName());
				ps.setString(i++, product.getSimgurl());
				ps.setString(i++, product.getBimgurl());
				ps.setString(i++, product.getLinkurl());
				ps.setDouble(i++, product.getPrice());
				ps.setDouble(i++, product.getSalesprice());
				ps.setString(i++, product.getDetail());
				ps.setString(i++, product.getStatus());
				ps.setString(i++, product.getTag());
				ps.setInt(i++, idx + 1);
				ps.setString(i++, product.getType());
				ps.setString(i++, product.getSubtype());
				ps.setInt(i++, product.getTotal());
				ps.setInt(i++, product.getUsed());
				ps.setInt(i++, product.getMaxjf());
				ps.setLong(i++, product.getFatherid());
				ps.setInt(i++, product.getShownum());
				ps.setInt(i++, product.getPersonlimit());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	public ContentProduct findProductById(long contentId)
	{
		String sql = "select * from es_content_product where id= ? ";
		List<ContentProduct> cp = getJdbcTemplate().query(sql, new Object[]
		{ contentId }, new ContentProductRowMapper());
		if (cp.size() > 0)
		{
			return cp.get(0);
		}
		return null;
	}

	public ContentProduct findBeforeProductById(ContentProduct product)
	{
		String sql = "select * from es_content_product where catid = ? and status != 'DEL' and idx > ? order by idx desc";
		List<ContentProduct> cp = getJdbcTemplate().query(sql, new Object[]
		{ product.getCatid(), product.getIdx() }, new ContentProductRowMapper());
		if (cp.size() >= 1)
		{
			return cp.get(cp.size() - 1);
		}
		return null;
	}

	public ContentProduct findNextProductById(ContentProduct product)
	{
		String sql = "select * from es_content_product where catid = ? and status != 'DEL' and idx < ? order by idx desc";
		List<ContentProduct> cp = getJdbcTemplate().query(sql, new Object[]
		{ product.getCatid(), product.getIdx() }, new ContentProductRowMapper());
		if (cp.size() >= 1)
		{
			return cp.get(0);
		}
		return null;
	}

	class ContentProductRowMapper implements RowMapper
	{

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
			p.setMaxjf(rs.getInt("maxjf"));
			p.setFatherid(rs.getLong("fatherid"));
			p.setShownum(rs.getInt("shownum"));
			p.setIdx(rs.getInt("idx"));
			p.setPersonlimit(rs.getInt("personlimit"));
			p.setVip(rs.getInt("vip"));
			p.setF1(rs.getString("f1"));
			p.setF2(rs.getString("f2"));
			p.setF3(rs.getString("f3"));
			p.setF4(rs.getString("f4"));
			p.setF5(rs.getString("f5"));
			return p;
		}
	}

	private static final String EDIT_PRODUCT = "update es_content_product set ownerid=?,name=?,simgurl=?,bimgurl=?,linkurl=?,price=?,salesprice=?,detail=?,status=?,updatetime=now(),tag=?,total=?,used=?,subtype= ?,maxjf=?,shownum=?,personlimit=? where id= ? and ownerid =? ";

	public int updateProduct(ContentProduct product, long ownerid)
	{
		Object[] params =
		{ product.getOwner(), product.getName(), product.getSimgurl(), product.getBimgurl(), product.getLinkurl(), product.getPrice(), product.getSalesprice(),
				product.getDetail(), product.getStatus(), product.getTag(), product.getTotal(), product.getUsed(), product.getSubtype(), product.getMaxjf(), product.getShownum(),product.getPersonlimit(),product.getId(), ownerid };
		return getJdbcTemplate().update(EDIT_PRODUCT, params);
	}
	
	private static final String EDIT_PRODUCT_BY_FATHERID = "update es_content_product set ownerid=?,name=?,simgurl=?,bimgurl=?,linkurl=?,price=?,salesprice=?,detail=?,status=?,updatetime=now(),tag=?,total=?,used=?,subtype= ?,maxjf=?,shownum=?,personlimit=? where fatherid= ? and ownerid =? and status!='DEL'";
	@Override
	public void updateProductByFatherid(long fatherid,long onwerid, ContentProduct product)
	{
		Object[] params =
		{ product.getOwner(),product.getName(), product.getSimgurl(), product.getBimgurl(), product.getLinkurl(), product.getPrice(), product.getSalesprice(),
				product.getDetail(), product.getStatus(), product.getTag(), product.getTotal(), product.getUsed(), product.getSubtype(), product.getMaxjf(),product.getShownum(),product.getPersonlimit(), fatherid, onwerid };
		getJdbcTemplate().update(EDIT_PRODUCT_BY_FATHERID, params);
	}

	private static final String UPDATE_PRODUCT_STATUS = "update es_content_product set status=? where id=? and ownerid=?";

	public int updateProduct(long contentId, String contentDel, long owner)
	{
		return getJdbcTemplate().update(UPDATE_PRODUCT_STATUS, new Object[]
		{ contentDel, contentId, owner });
	}

	private static final String FIND_PRODUCT_BY_OWNER = "select * from es_content_product where ownerid=? and status!='DEL' order by id desc ";

	public List<ContentProduct> findProductByOwner(long ownerId)
	{
		return getJdbcTemplate().query(FIND_PRODUCT_BY_OWNER, new Object[]
		{ ownerId }, new RowMapper()
		{

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
				p.setDetail(rs.getString("detail"));
				p.setStatus(rs.getString("status"));
				p.setCreatetime(rs.getTimestamp("createtime"));
				p.setUpdatetime(rs.getDate("updatetime"));
				p.setTag(rs.getString("tag"));
				p.setVip(rs.getInt("vip"));
				p.setPersonlimit(rs.getInt("personlimit"));
				p.setF1(rs.getString("f1"));
				p.setF2(rs.getString("f2"));
				p.setF3(rs.getString("f3"));
				p.setF4(rs.getString("f4"));
				p.setF5(rs.getString("f5"));
				return p;
			}
		});
	}

	public int findProductTotal(long ccid, long ownerid, String name)
	{
		String sql = "select count(*) from es_content_product where catid =? and status !='DEL' and ownerid= ? ";
		List<Object> list = new ArrayList<Object>();
		list.add(ccid);
		list.add(ownerid);
		if (StringUtils.isNotEmpty(name))
		{
			sql += " and name like ?";
			list.add("%" + name + "%");
		}
		return getJdbcTemplate().queryForInt(sql, list.toArray());
	}

	@Override
	public int deleteIndx(int idx, long catid)
	{
		String sql = "update es_content_product set idx=idx-1 where idx>? and catid=?";
		return getJdbcTemplate().update(sql, new Object[]
		{ idx, catid });
	}

	@Override
	public Map findIndx(long id)
	{
		String sql = "select catid,idx from es_content_product where id=?";
		try
		{
			return getJdbcTemplate().queryForMap(sql, new Object[]
			{ id });
		} catch (DataAccessException e)
		{
			return null;
		}
	}

	@Override
	public int findMaxIndx(long catid)
	{
		String sql = "select Max(idx) from es_content_product where catid=?";
		return getJdbcTemplate().queryForInt(sql, new Object[]
		{ catid });
	}

	@Override
	public int updateProductIdx(ContentProduct p)
	{
		return getJdbcTemplate().update("update es_content_product set idx=? where id=?", new Object[]
		{ p.getIdx(), p.getId() });
	}

	@Override
	public List<ContentProduct> findProductByCcid(long ccid, long ownerid)
	{
		String sql = "select n.*,c.name categoryname  from es_content_product n join es_content_category c on n.catid=c.id where n.catid=? and n.ownerid=? and n.status='CMP' order by n.idx desc, n.id desc";
		return getJdbcTemplate().query(sql, new Object[]
		{ ccid, ownerid }, new RowMapper()
		{

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
				p.setDetail(rs.getString("detail"));
				p.setStatus(rs.getString("status"));
				p.setCreatetime(rs.getTimestamp("createtime"));
				p.setUpdatetime(rs.getTimestamp("updatetime"));
				p.setTag(rs.getString("tag"));
				p.setCatname(rs.getString("categoryname"));
				p.setSalesprice(rs.getDouble("salesprice"));
				p.setFatie(rs.getString("fatie"));
				p.setTopicid(rs.getLong("topicid"));
				p.setVip(rs.getInt("vip"));
				p.setPersonlimit(rs.getInt("personlimit"));
				p.setF1(rs.getString("f1"));
				p.setF2(rs.getString("f2"));
				p.setF3(rs.getString("f3"));
				p.setF4(rs.getString("f4"));
				p.setF5(rs.getString("f5"));
				return p;
			}
		});
	}

	@Override
	public void updateProductPost(long entityid, long topicid)
	{
		getJdbcTemplate().update("update esite.es_content_product set fatie='Y',topicid=? where id=?", new Object[]
		{ topicid, entityid });
	}

	@Override
	public void updateUsed(long id, int quantity)
	{
		getJdbcTemplate().update("update esite.es_content_product set used=used+? where id=?", new Object[]
		{ quantity, id });
	}
	
	@Override
	public List<ContentProduct> findProductBySubtype(long owner, String subtype)
	{
		return getJdbcTemplate().query("select * from es_content_product product join es_content_category category on product.catid=category.id where product.subtype=? and product.ownerid=? and product.status!='DEL' and category.status!='DEL'", new Object[]{subtype,owner}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ContentProduct p = new ContentProduct();
				p.setId(rs.getLong("product.id"));
				p.setOwner(rs.getLong("product.ownerid"));
				p.setCatid(rs.getLong("product.catid"));
				p.setCatname(rs.getString("category.name"));
				p.setName(rs.getString("product.name"));
				p.setSimgurl(rs.getString("product.simgurl"));
				p.setBimgurl(rs.getString("product.bimgurl"));
				p.setLinkurl(rs.getString("product.linkurl"));
				p.setPrice(rs.getDouble("product.price"));
				p.setSalesprice(rs.getDouble("product.salesprice"));
				p.setDetail(rs.getString("product.detail"));
				p.setStatus(rs.getString("product.status"));
				p.setCreatetime(rs.getTimestamp("product.createtime"));
				p.setUpdatetime(rs.getTimestamp("product.updatetime"));
				p.setTag(rs.getString("product.tag"));
				p.setIdx(rs.getInt("product.idx"));
				p.setVip(rs.getInt("vip"));
				p.setPersonlimit(rs.getInt("personlimit"));
				p.setF1(rs.getString("f1"));
				p.setF2(rs.getString("f2"));
				p.setF3(rs.getString("f3"));
				p.setF4(rs.getString("f4"));
				p.setF5(rs.getString("f5"));
				return p;
			}
		});
	}

	@Override
	public ContentProduct findEasyProductById(long id,long owner)
	{
		String sql = "select id,type,total,used,personlimit from es_content_product where id= ? and ownerid= ? and status ='CMP'";
		List<ContentProduct> cp = getJdbcTemplate().query(sql, new Object[]
		{id,owner}, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ContentProduct p = new ContentProduct();
				p.setId(rs.getLong("id"));
				p.setType(rs.getString("type"));
				p.setTotal(rs.getInt("total"));
				p.setUsed(rs.getInt("used"));
				p.setPersonlimit(rs.getInt("personlimit"));
				return p;
			}
			
		});
		if (cp.size() > 0)
		{
			return cp.get(0);
		}
		return null;
	}
	
	
	private final static String ADD_COUPONSCODE = "insert into es_content_product_code(pid,code,password) values(?,?,?)";
	@Override
	public int addCouponsCode(final List<ProductCode> list,final long id)
	{
		String sql = ADD_COUPONSCODE;
		int[] rs = getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter()
		{
			public int getBatchSize()
			{
				return list.size();
			}

			public void setValues(PreparedStatement ps, int index) throws SQLException
			{
				ProductCode c = list.get(index);
				int i = 1;
				ps.setLong(i++, id);
				ps.setString(i++, c.getCode());
				ps.setString(i++, c.getPassword());
			}
		});
		if (rs.length > 0)
		{
			return rs.length;
		}
		return 0;
	}

	@Override
	public int findCodeTotal(long pid, String code)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(id) from es_content_product_code  where pid=? ";
		list.add(pid);
		if (code != null && code.trim().length() > 0)
		{
			sql += " and code like ?";
			list.add("%" + code + "%");
		}
		return getJdbcTemplate().queryForInt(sql, list.toArray());
	}

	@Override
	public List<ProductCode> findCodeList(long pid, String code, int s, int j)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from es_content_product_code  where pid=? ";
		list.add(pid);
		if (code != null && code.trim().length() > 0)
		{
			sql += " and code like ?";
			list.add("%" + code + "%");
		}
		sql += "order by id desc limit ?,? ";
		list.add(s);
		list.add(j);
		return getJdbcTemplate().query(sql, list.toArray(), new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ProductCode pc = new ProductCode();
				pc.setId(rs.getLong("id"));
				pc.setPid(rs.getLong("pid"));
				pc.setCode(rs.getString("code"));
				pc.setPassword(rs.getString("password"));
				pc.setStatus(rs.getString("status"));
				pc.setUsed(rs.getInt("used"));
				return pc;
			}
		});
	}
	
	@Override
	public ProductCode findCodeByPid(long id)
	{
		List<ProductCode> list=getJdbcTemplate().query("select * from es_content_product_code where total-used>0 and status='N' and pid=?", new Object[]{id}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ProductCode code=new ProductCode();
				code.setId(rs.getLong("id"));
				code.setTotal(rs.getInt("total"));
				code.setUsed(rs.getInt("used"));
				return code;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public void updateProductCodeUsed(ProductCode pcode)
	{
		
		if(pcode.getTotal()-pcode.getUsed()==1){
			getJdbcTemplate().update("update es_content_product_code set used=used+1,status='Y' where id=?", new Object[]{pcode.getId()});
		}else if(pcode.getTotal()-pcode.getUsed()>0){
			getJdbcTemplate().update("update es_content_product_code set used=used+1 where id=?", new Object[]{pcode.getId()});
		}
	}

	@Override
	public int saveCode(long pid,String code,String password,long total)
	{
		String sql="insert into es_content_product_code(pid,code,password,total) values(?,?,?,?)";
		return getJdbcTemplate().update(sql, new Object[]{pid,code,password,total});
	}

	@Override
	public long addProductLevel(long productid,String id, String price)
	{
		long levelid = Long.parseLong(id);
		double Price = Double.parseDouble(price);
		String sql = "insert into es_content_product_level (productid,levelid,price,createtime) values(?,?,?,now()) on duplicate key update price=?";
		return  getJdbcTemplate().update(sql, new Object[]{productid,levelid,Price,Price});
	}

	@Override
	public Map<Long, Object> findProductLevel(long productid) {
		String sql = "select pl.levelid,pl.price,l.name from es_content_product_level pl join es_hy_user_level l on pl.levelid = l.id where productid = ?";
		Object[] params = {productid};
		List<ProductLevel> list = getJdbcTemplate().query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ProductLevel pl = new ProductLevel();
				pl.setLevelid(rs.getLong("pl.levelid"));
				pl.setPrice(rs.getInt("pl.price"));
				pl.setName(rs.getString("l.name"));
				return pl;
			}
		});
		Map<Long, Object> map = new HashMap<Long, Object>();
		if(list.size() > 0){
			for(ProductLevel pl : list){
				map.put(pl.getLevelid(), pl);
			}
			return map;
		}
		return null;
	}

	@Override
	public long updateProductVip(long vip,long productid)
	{
		return getJdbcTemplate().update("update esite.es_content_product set vip=? where id=?", new Object[]{vip,productid });
	}

	@Override
	public Map findProductTabs(long contentId, long owner)
	{
		try
		{
			return getJdbcTemplate().queryForMap("select f1,f2,f3,f4,f5 from es_content_product where id=? and ownerid=?", new Object[]{contentId,owner});
		} catch (DataAccessException e)
		{
			return null;
		}
	}
	
	@Override
	public int updateTabIndex(long pid, String tabid, String json)
	{
		return getJdbcTemplate().update("update es_content_product set "+tabid+"=? where id=?", new Object[]{json,pid});
	}
}
