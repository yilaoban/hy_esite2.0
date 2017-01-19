package project.caidan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import project.caidan.dao.ICaiDanProductDao;
import project.caidan.dto.CdReportSift;
import project.caidan.model.CDProductRmb;
import project.caidan.model.CDWay;
import project.caidan.model.CdWkqRmb;

import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.ProductCode;
import com.huiyee.weixin.model.PayOrder;


public class CaiDanProductDaoImpl implements ICaiDanProductDao
{
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<CDProductRmb> findProductList(long owner)
	{
		String sql = "select * from caidan.cd_product_rmb r join es_content_product p on r.pid = p.id where p.ownerid = ? and r.endtime > now() and p.status = 'CMP' order by p.idx desc";
		Object[] params = {owner};
		return jdbcTemplate.query(sql, params, new ContentProductRowMapper());
	}

	class ContentProductRowMapper implements RowMapper
	{

		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			CDProductRmb rmb = new CDProductRmb();
			rmb.setId(rs.getLong("r.id"));
			rmb.setPid(rs.getLong("r.pid"));
			rmb.setRmb(rs.getInt("r.rmb"));
			rmb.setXjrmb(rs.getInt("r.xjrmb"));
			rmb.setZdrmb(rs.getInt("r.zdrmb"));
			rmb.setEndtime(rs.getTimestamp("r.endtime"));
			ContentProduct p = new ContentProduct();
			p.setId(rs.getLong("p.id"));
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
			p.setMaxjf(rs.getInt("p.maxjf"));
			p.setFatherid(rs.getLong("p.fatherid"));
			p.setShownum(rs.getInt("p.shownum"));
			rmb.setProduct(p);
			return rmb;
		}
	}
	
	
	@Override
	public List<CDProductRmb> findProductList(int start, int size, long owner)
	{
		String sql = "select * from caidan.cd_product_rmb r join es_content_product p on r.pid = p.id where p.ownerid = ? and r.endtime > now() and p.status = 'CMP' order by p.idx desc limit ?,?";
		Object[] params = {owner,start,size};
		return jdbcTemplate.query(sql, params, new ContentProductRowMapper());
	}


	@Override
	public CDProductRmb findProductById(long id)
	{
		String sql = "select * from caidan.cd_product_rmb r join es_content_product p on r.pid = p.id where r.id = ?";
		Object[] params = {id};
		List<CDProductRmb> list = jdbcTemplate.query(sql, params, new ContentProductRowMapper());
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public CDProductRmb findProductByProductid(long productid)
	{
		String sql = "select * from caidan.cd_product_rmb r join es_content_product p on r.pid = p.id where p.id = ?";
		Object[] params = {productid};
		List<CDProductRmb> list = jdbcTemplate.query(sql, params, new ContentProductRowMapper());
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public CDProductRmb findProductByPid(long id)
	{
		String sql = "select * from caidan.cd_product_rmb r join es_content_product p on r.pid = p.id where r.pid = ?";
		Object[] params = {id};
		List<CDProductRmb> list = jdbcTemplate.query(sql, params, new ContentProductRowMapper());
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	


	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public List<CDProductRmb> findOrderList(long hyuid, String status, int type, int start, int size)
	{
		String sql = "";
		List<Object> list = new ArrayList<Object>();
		list.add(hyuid);list.add(type);
		/*if(StringUtils.isNotBlank(status)){
			sql = "select * from es_pay_order o join es_content_product p on o.productid = p.id join caidan.cd_product_rmb r on p.id = r.pid where o.hyuid = ? and p.type = ? and o.status =? and o.del_tag != 'Y' order by o.createtime desc limit ?,?";
			list.add(status);
		}else{
			sql = "select * from es_pay_order o join es_content_product p on o.productid = p.id join caidan.cd_product_rmb r on p.id = r.pid where o.hyuid = ? and p.type = ? and o.del_tag != 'Y' order by o.createtime desc limit ?,?";
		}*/
		if(StringUtils.isNotBlank(status)){
			sql = "select * from es_pay_order_goods g join caidan.cd_product_rmb r on g.productid = r.pid join es_pay_order o on g.orderid = o.id where o.hyuid = ? and g.type = ? and g.productsubtype != 'S' and o.del_tag != 'Y' and o.status = ? order by o.createtime desc limit ?,?";
			list.add(status);
		}else{
			sql = "select * from es_pay_order_goods g join caidan.cd_product_rmb r on g.productid = r.pid join es_pay_order o on g.orderid = o.id where o.hyuid = ? and g.type = ? and g.productsubtype != 'S' and o.del_tag != 'Y' order by o.createtime desc limit ?,?";
		}
		list.add(start);list.add(size);
		return jdbcTemplate.query(sql, list.toArray(), new MyRowMapper());
	}


	@Override
	public List<CDProductRmb> findOrderList(long hyuid, String status, int type)
	{
		String sql = "";
		List<Object> list = new ArrayList<Object>();
		list.add(hyuid);list.add(type);
		if(StringUtils.isNotBlank(status)){
			sql = "select * from es_pay_order_goods g join caidan.cd_product_rmb r on g.productid = r.pid join es_pay_order o on g.orderid = o.id where o.hyuid = ? and g.type = ? and g.productsubtype != 'S' and o.del_tag != 'Y' and o.status = ? order by o.createtime desc ";
			list.add(status);
		}else{
			sql = "select * from es_pay_order_goods g join caidan.cd_product_rmb r on g.productid = r.pid join es_pay_order o on g.orderid = o.id where o.hyuid = ? and g.type = ? and g.productsubtype != 'S' and o.del_tag != 'Y' order by o.createtime desc ";
		}
		return jdbcTemplate.query(sql, list.toArray(), new MyRowMapper());
	}
	
	class MyRowMapper implements RowMapper
	{

		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			CDProductRmb rmb = new CDProductRmb();
			rmb.setId(rs.getLong("r.id"));
			rmb.setPid(rs.getLong("r.pid"));
			rmb.setRmb(rs.getInt("r.rmb"));
			rmb.setXjrmb(rs.getInt("r.xjrmb"));
			rmb.setZdrmb(rs.getInt("r.zdrmb"));
			rmb.setEndtime(rs.getTimestamp("r.endtime"));
			
			OrderGoods goods  = new OrderGoods();
			goods.setId(rs.getLong("g.id"));
			goods.setProductname(rs.getString("g.productname"));
			goods.setProductsimg(rs.getString("g.productsimg"));
			goods.setUsed(rs.getString("g.used"));
			goods.setProductsubtype(rs.getString("g.productsubtype"));
			
			Date date = rs.getTimestamp("r.endtime");
			Date now = new Date();
			String sta = rs.getString("g.used");
			if(date.before(now) && !"Y".equals(sta)){
				goods.setUsed("G");
			}
			rmb.setGoods(goods);
			return rmb;
		}
	}


	@Override
	public CDProductRmb findCdOrderById(long hyuid, String status, String type, long id)
	{
		String sql = "";
		List<Object> list = new ArrayList<Object>();
		list.add(type);list.add(id);
		sql = "select * from es_pay_order_goods g join caidan.cd_product_rmb r on g.productid = r.pid join es_content_product p on r.pid = p.id where p.type = ? and g.id = ? order by g.createtime desc ";
		List<CDProductRmb> cdProductlist = jdbcTemplate.query(sql, list.toArray(), new ContentProductRowMapper());
		if(cdProductlist != null && cdProductlist.size()>0){
			return cdProductlist.get(0);
		}
		return null;
	}
	
	@Override
	public int findProductCodeLess(long id)
	{
		String sql="select sum(total) from es_content_product_code where pid=? and total-used>0";
		int rs=jdbcTemplate.queryForInt(sql, new Object[]{id});
		return rs;
	}
	
	@Override
	public int findChannelPerformanceTotal(long owner, CdReportSift sift)
	{
		List<Object> list=new ArrayList<Object>();
		String sql="select count(e.id) from caidan.cd_product_channel c join es_pay_order_goods d on d.productid=c.pid join es_wkq_record e on d.id=e.pogid where 1=1 ";
		if(StringUtils.isNotEmpty(sift.getStarttime())){
			sql+=" and e.createtime >=? ";
			list.add(sift.getStarttime());
		}
		if(StringUtils.isNotEmpty(sift.getEndtime())){
			sql+=" and e.createtime <=? ";
			list.add(sift.getEndtime());
		}
		if(sift.getCatid()>0){
			sql+=" and c.ccname = ? ";
			list.add(sift.getCatid());
		}
		if(StringUtils.isNotEmpty(sift.getWayname())){
			sql+=" and c.cname like ? ";
			list.add("%"+sift.getWayname()+"%");
		}
		return jdbcTemplate.queryForInt(sql, list.toArray());
	}
	
	@Override
	public List<CdWkqRmb> findChannelPerformance(long owner, CdReportSift sift, int start, int size)
	{
		List<Object> list=new ArrayList<Object>();
		String sql="select * from caidan.cd_product_channel c join es_pay_order_goods d on d.productid=c.pid join es_wkq_record e on d.id=e.pogid where 1=1 ";
		if(StringUtils.isNotEmpty(sift.getStarttime())){
			sql+=" and e.createtime >=? ";
			list.add(sift.getStarttime());
		}
		if(StringUtils.isNotEmpty(sift.getEndtime())){
			sql+=" and e.createtime <=? ";
			list.add(sift.getEndtime());
		}
		if(sift.getCatid()>0){
			sql+=" and c.ccname = ? ";
			list.add(sift.getCatid());
		}
		if(StringUtils.isNotEmpty(sift.getWayname())){
			sql+=" and c.cname like ? ";
			list.add("%"+sift.getWayname()+"%");
		}
		sql+=" order by e.createtime desc limit ?,?";
		list.add(start);
		list.add(size);
		return jdbcTemplate.query(sql, list.toArray(),new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CdWkqRmb c=new CdWkqRmb();
				c.setCreatetime(rs.getTimestamp("e.createtime"));
				c.setCpzid(rs.getLong("e.wxuid"));
				c.setPogid(rs.getLong("e.pogid"));
				c.setPid(rs.getLong("c.pid"));
				c.setWayid(rs.getLong("c.clid"));
				
				CDWay way=new CDWay();
				way.setName(rs.getString("c.cname"));
				way.setId(rs.getLong("c.clid"));
				c.setWay(way);
				return c;
			}
		});
	}
	
	@Override
	public List<CdWkqRmb> findChannelPerformance(CdReportSift sift)
	{
		List<Object> list=new ArrayList<Object>();
		String sql="select * from caidan.cd_product_channel c join es_pay_order_goods d on d.productid=c.pid join es_wkq_record e on d.id=e.pogid where 1=1 ";
		if(StringUtils.isNotEmpty(sift.getStarttime())){
			sql+=" and e.createtime >=? ";
			list.add(sift.getStarttime());
		}
		if(StringUtils.isNotEmpty(sift.getEndtime())){
			sql+=" and e.createtime <=? ";
			list.add(sift.getEndtime());
		}
		if(sift.getCatid()>0){
			sql+=" and c.ccname = ? ";
			list.add(sift.getCatid());
		}
		if(StringUtils.isNotEmpty(sift.getWayname())){
			sql+=" and c.cname like ? ";
			list.add("%"+sift.getWayname()+"%");
		}
		sql+=" order by e.createtime desc";
		return jdbcTemplate.query(sql, list.toArray(),new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CdWkqRmb c=new CdWkqRmb();
				c.setCreatetime(rs.getTimestamp("e.createtime"));
				c.setCpzid(rs.getLong("e.wxuid"));
				c.setPogid(rs.getLong("e.pogid"));
				c.setPid(rs.getLong("c.pid"));
				c.setWayid(rs.getLong("c.clid"));
				
				CDWay way=new CDWay();
				way.setName(rs.getString("c.cname"));
				c.setWay(way);
				return c;
			}
		});
	}
	
	
	@Override
	public long findWxuidByPogid(long pogid)
	{
		try
		{
			return jdbcTemplate.queryForLong("select b.wxuid from es_pay_order_goods a join es_pay_order b on a.orderid=b.id where a.id=?",new Object[]{pogid});
		} catch (DataAccessException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	
	@Override
	public ProductCode findCodeByPogid(long id)
	{
		List<ProductCode> list=jdbcTemplate.query("select * from es_pay_order_goods a join es_content_product_code b on a.pcodeid=b.id where a.id=?", new Object[]{id}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ProductCode code=new ProductCode();
				code.setId(rs.getLong("b.id"));
				code.setCode(rs.getString("b.code"));
				code.setPassword(rs.getString("b.password"));
				return code;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	
}
