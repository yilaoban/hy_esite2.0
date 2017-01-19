package com.huiyee.esite.fdao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.IProListProductDao;
import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.ProlistProduct;

public class ProListProductDaoImpl extends AbstractDao implements IProListProductDao {
	private static final String FIND_PRODUCT = "select product.*,relation.zantotal,relation.status,relation.id ppid ,relation.idx  idx from es_feature_prolist_product relation  join es_content_product product  on relation.productid=product.id where prolistid=? and  relation.status!='DEL' and product.status!='DEL' order by relation.idx asc ";

	public List<ProlistProduct> findProduct(long key) {
		return getJdbcTemplate().query(FIND_PRODUCT, new Object[] { key }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ProlistProduct pp = new ProlistProduct();
				ContentProduct cp = new ContentProduct();
				cp.setId(rs.getLong("product.id"));
				cp.setOwner(rs.getLong("product.ownerid"));
				cp.setCatid(rs.getLong("product.catid"));
				cp.setName(rs.getString("product.name"));
				cp.setSimgurl(rs.getString("product.simgurl"));
				cp.setBimgurl(rs.getString("product.bimgurl"));
				cp.setLinkurl(rs.getString("product.linkurl"));
				cp.setPrice(rs.getDouble("product.price"));
				cp.setDetail(rs.getString("product.detail"));
				cp.setStatus(rs.getString("product.status"));
				cp.setCreatetime(rs.getTimestamp("product.createtime"));
				cp.setUpdatetime(rs.getDate("product.updatetime"));
				cp.setTag(rs.getString("product.tag"));
				pp.setProduct(cp);
				pp.setId(rs.getLong("ppid"));
				pp.setZantotal(rs.getInt("relation.zantotal"));
				pp.setStatus(rs.getString("relation.status"));
				pp.setIdx(rs.getInt("idx"));
				return pp;
			}
		});
	}

	private static final String DELETE_BY_PROLISTID = "update es_feature_prolist_product set status='DEL' where prolistid = ? ";

	public void deleteByprolistId(long id) {
		getJdbcTemplate().update(DELETE_BY_PROLISTID, new Object[] { id });
	}

	private static final String ADD_PROLIST_PRODUCT = "insert into es_feature_prolist_product (prolistid,productid,zantotal,status,idx) values(?,?,?,'CMP',?) on duplicate key update status ='CMP',idx=? ";

	public void addProlistProduct(long newprolistid, long productid, int zantotal,int idx) {
		getJdbcTemplate().update(ADD_PROLIST_PRODUCT, new Object[] { newprolistid, productid, zantotal,idx,idx });
	}

	private static final String ADD_PRODUCT_ZAN = "insert into esite.es_feature_product_zan(zanproid,uid,createtime,ip,terminal,source,pageid) values (?,?,now(),?,?,?,?)";

	@Override
	public long addProductZan(final long productid,final long uid,final String ip,final String terminal,final String source,final long pageid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(ADD_PRODUCT_ZAN,
						new String[] { "id" });
				ps.setLong(1, productid);
				ps.setLong(2, uid);
				ps.setString(3, ip);
				ps.setString(4, terminal);
				ps.setString(5, source);
				ps.setLong(6, pageid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
		
	}

	private static final String ADD_ZAN = "update esite.es_feature_prolist_product set zantotal = zantotal +1 where id = ?";

	@Override
	public int addZan(long fppid) {
		return getJdbcTemplate().update(ADD_ZAN, new Object[] { fppid });
	}

	private static final String FIND_MAX_IDX = "select max(idx) from esite.es_feature_prolist_product where prolistid = ?";

	public int findMaxIdx(long newprolistid) {
		return getJdbcTemplate().queryForInt(FIND_MAX_IDX, new Object[]{newprolistid});
	}

	private static final String FIND_PRODUCT_ZAN="select id from esite.es_feature_product_zan where zanproid = ? and uid = ? ";
	@Override
	public long findProductZan(long productid, long uid) {
		Object[] params={productid,uid};
		try {
			return getJdbcTemplate().queryForLong(FIND_PRODUCT_ZAN, params);
		} catch (DataAccessException e) {
			return 0;
		}
	}
	
	private static final String FIND_TREE_BY_TYPE = "select * from es_content_category where type=? and status !='DEL' and (ownerid =? or ownerid is null) order by id asc";
	@Override
	public List<CategoryTree> findTreeByType(String type,long owner) {
		return getJdbcTemplate().query(FIND_TREE_BY_TYPE, new Object[] { type,owner }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				CategoryTree t = new CategoryTree();
				t.setId(rs.getLong("id"));
				t.setPId(rs.getLong("fartherCatId"));
				t.setName(rs.getString("name"));
				return t;
			}
		});
	}
	
	private static final String FIND_PAGE_BLOCK_RELATION_BY_BLOCKID="select r.*,b.cid,pf.fid from es_page_block_relation r join es_template_card_block b on r.cbid = b.id join es_page_feature pf on r.pfid = pf.id  where r.id = ?";
	@Override
	public PageBlockRelation findPageBlockRelationByRelationid(long relationid) {
		Object[] params={relationid};
		List<PageBlockRelation> list = getJdbcTemplate().query(FIND_PAGE_BLOCK_RELATION_BY_BLOCKID, params, new RowMapper(){
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				PageBlockRelation pbr = new PageBlockRelation();
				pbr.setId(rs.getLong("id"));
				pbr.setCbid(rs.getLong("cbid"));
				pbr.setPcid(rs.getLong("pcid"));
				pbr.setJson(rs.getString("json"));
				pbr.setCid(rs.getLong("cid"));
				pbr.setPfid(rs.getLong("pfid"));
				pbr.setFid(rs.getLong("fid"));
				return pbr;
			}
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public int updatePageBlockRelationByRelationid(long relationid, String json) {
		String sql = "update es_page_block_relation r set r.json = ? where r.id = ?";
		Object[] params = { json,relationid};
		return getJdbcTemplate().update(sql, params);
	}
}
