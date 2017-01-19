
package project.caidan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import project.caidan.dao.ICouponDao;
import project.caidan.model.CDWkqRecord;
import project.caidan.model.Coupon;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.model.ContentProduct;

public class CouponDaoImpl extends AbstractDao implements ICouponDao
{

	private static final String FIND_LIST = "select * from es_content_product p left outer join caidan.cd_product_rmb r on p.id=r.pid where ownerid=? and p.status!='DEL' order by p.idx desc limit ?,?";

	@Override
	public List<Coupon> findList(long ownerid, int start, int size)
	{
		return getJdbcTemplate().query(FIND_LIST, new Object[]
		{ ownerid, start, size }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Coupon c = new Coupon();
				c.setId(rs.getLong("r.id"));
				c.setPid(rs.getLong("r.pid"));
				c.setZdrmb(rs.getInt("r.zdrmb"));
				c.setXjrmb(rs.getInt("r.xjrmb"));
				c.setRmb(rs.getInt("r.rmb"));

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
				p.setIdx(rs.getInt("p.idx"));

				c.setProduct(p);
				return c;
			}
		});
	}

	@Override
	public int findTotal(long ownerid)
	{
		return getJdbcTemplate().queryForInt(
				"select count(*) from es_content_product p left outer join caidan.cd_product_rmb r on p.id=r.pid where ownerid=? and status!='DEL'", new Object[]
				{ ownerid });
	}

	@Override
	public void saveCoupon(Coupon coupon)
	{
		getJdbcTemplate().update("insert into caidan.cd_product_rmb (pid,zdrmb,xjrmb,rmb,endtime) values(?,?,?,?,?)", new Object[]
		{ coupon.getPid(), coupon.getZdrmb(), coupon.getXjrmb(), coupon.getRmb(), coupon.getEndtime() });
	}

	@Override
	public Coupon findCouponByPid(long pid, long owner)
	{
		List<Coupon> list = getJdbcTemplate().query("select * from caidan.cd_product_rmb r join esite.es_content_product p where r.pid=p.id and p.id=? and ownerid=?", new Object[]
		{ pid, owner }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Coupon c = new Coupon();
				c.setId(rs.getLong("r.id"));
				c.setPid(rs.getLong("r.pid"));
				c.setXjrmb(rs.getInt("r.xjrmb"));
				c.setZdrmb(rs.getInt("r.zdrmb"));
				c.setRmb(rs.getInt("r.rmb"));
				c.setEndtime(rs.getString("r.endtime"));

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
				c.setProduct(p);

				return c;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public void updateCoupon(Coupon coupon, long pid)
	{
		getJdbcTemplate().update("update caidan.cd_product_rmb set zdrmb=?,xjrmb=?,rmb=?,endtime=? where pid=?", new Object[]
		{ coupon.getZdrmb(), coupon.getXjrmb(), coupon.getRmb(), coupon.getEndtime(), pid });
	}

	@Override
	public List<CDWkqRecord> findWkqRecord(long wxuid, int start, int size)
	{
		return getJdbcTemplate().query("select * from es_wkq_record w join es_pay_order_goods o on w.pogid=o.id join es_pay_order p on p.id=o.orderid where w.wxuid=? order by w.createtime desc limit ?,?", new Object[]
		{ wxuid, start, size }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDWkqRecord c = new CDWkqRecord();
				c.setCreatetime(rs.getTimestamp("createtime"));
				c.setPid(rs.getLong("o.productid"));
				c.setHyuid(rs.getLong("hyuid"));
				return c;
			}
		});
	}
	
	@Override
	public ContentProduct findProductByCodeid(long codeid, long owner)
	{
		List<ContentProduct> list=getJdbcTemplate().query("select * from es_content_product_code a join es_content_product b on a.pid=b.id where a.id=? and b.ownerid=?", new Object[]{codeid,owner}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ContentProduct p=new ContentProduct();
				p.setId(rs.getLong("b.id"));
				return p;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public int findCodeUsed(long codeid)
	{
		return getJdbcTemplate().queryForInt("select count(id) from es_pay_order_goods where pcodeid=?", new Object[]{codeid});
	}
	
	@Override
	public int delCouponCode(long codeid)
	{
		return getJdbcTemplate().update("delete from es_content_product_code where id=?", new Object[]{codeid});
	}
	
	@Override
	public int updateCodeClean(long productid)
	{
		return getJdbcTemplate().update("delete from es_content_product_code where pid=? and used=0", new Object[]{productid});
	}
}
