package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IContentCategoryDao;
import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.OwnerContentType;

public class ContentCategoryDaoImpl extends AbstractDao implements IContentCategoryDao {

	private static final String FIND_CONETNT_CATEGORY_WINT_TYPE_AND_NO_FATHERCATID = "select * from es_content_category where type=? and ownerid=? and isnull(fartherCatId) and status !='DEL' order by id asc";

	public List<ContentCategory> findContentCategoryWithTypeAndNoFathercatid(String type, long owner) {
		return getJdbcTemplate().query(FIND_CONETNT_CATEGORY_WINT_TYPE_AND_NO_FATHERCATID, new Object[] { type, owner }, new MyRowMapper());
	}

	private static final String FIND_NEXT_CATEGORY_BY_CCID = "select * from es_content_category where ownerid=? and fartherCatId=? and status !='DEL' order by idx,CONVERT(name USING gbk) asc";

	public List<ContentCategory> findNextCategoryByCcid(long ccid, long owner) {
		return getJdbcTemplate().query(FIND_NEXT_CATEGORY_BY_CCID, new Object[] { owner, ccid }, new MyRowMapper());
	}

	@Override
	public List<CategoryTree> findNextTreeByCcid(long ccid, long ownerid) {
		return getJdbcTemplate().query("select * from es_content_category where fartherCatId=? and status !='DEL' and ownerid=? order by id desc", new Object[] { ccid, ownerid },
				new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						CategoryTree t = new CategoryTree();
						t.setId(rs.getLong("id"));
						t.setPId(rs.getLong("fartherCatId"));
						t.setName(rs.getString("name"));
						return t;
					}
				});
	}

	class MyRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			ContentCategory cc = new ContentCategory();
			cc.setId(rs.getLong("id"));
			cc.setOwnerid(rs.getLong("ownerid"));
			cc.setName(rs.getString("name"));
			cc.setType(rs.getString("type"));
			long fartherCatId = rs.getLong("fartherCatId");
			if (fartherCatId > 0) {
				cc.setFartherCatId(fartherCatId);
			}
			cc.setIdx(rs.getInt("idx"));
			cc.setTypeid(rs.getLong("typeid"));
			cc.setDesc(rs.getString("content"));
			cc.setPic(rs.getString("pic"));
			cc.setBread(rs.getString("bread"));
			cc.setPageid(rs.getLong("pageid"));
			cc.setSubtype(rs.getString("subtype"));
			return cc;
		}
	}

	private static final String FIND_CONTENT_CATEGORY_TYPE = "select type from es_content_type  where id = ? and (ownerid=? or ownerid is null) and del_tag='N'";

	public String findContentCategoryType(long typeid, long owner) {
		List<String> list = getJdbcTemplate().queryForList(FIND_CONTENT_CATEGORY_TYPE, new Object[] { typeid, owner }, String.class);
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	private static final String FIND_CONTENT_CATEGORY = "select * from es_content_category a where farthercatid=(select farthercatid from es_content_category b where id=?) and a.id!=? and a.status !='DEL' order by a.id desc;";

	public List<ContentCategory> findContentCategory(long ccid) {
		return getJdbcTemplate().query(FIND_CONTENT_CATEGORY, new Object[] { ccid, ccid }, new MyRowMapper());
	}

	private static final String FIND_PRODUCT_CATEGORY = "select * from es_content_category where type=? and ownerid=? and status !='DEL'";

	public List<ContentCategory> findProductCategory(long owner, String type) {
		return getJdbcTemplate().query(FIND_PRODUCT_CATEGORY, new Object[] { type, owner }, new MyRowMapper());
	}

	private static final String FIND_CONTENT_CATEGORY_BY_ID = "select * from es_content_category where id = ? and status !='DEL'";

	public ContentCategory findContentCategoryById(long ccid) {
		List<ContentCategory> list = getJdbcTemplate().query(FIND_CONTENT_CATEGORY_BY_ID, new Object[] { ccid }, new MyRowMapper());
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	private static final String DEL_CATEGORY = "update es_content_category set status='DEL' where id= ?";

	public int delCategory(long ccid) {
		return getJdbcTemplate().update(DEL_CATEGORY, new Object[] { ccid });
	}

	private static final String ADD_CATEGORY = "insert into es_content_category (ownerid,name,type,fartherCatId,idx,typeid,content,pic,subtype) values (?,?,?,?,?,?,?,?,?)";

	public long addCategory(final long owner, final ContentCategory cc) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(ADD_CATEGORY, new String[] { "id" });
				int i = 1;
				ps.setLong(i++, owner);
				ps.setString(i++, cc.getName());
				ps.setString(i++, cc.getType());
				ps.setObject(i++, cc.getFartherCatId() > 0 ? cc.getFartherCatId() : null);
				ps.setInt(i++, cc.getIdx());
				ps.setLong(i++, cc.getTypeid());
				ps.setString(i++, cc.getDesc());
				ps.setString(i++, cc.getPic());
				ps.setString(i++, cc.getSubtype());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
	
	@Override
	public ContentCategory findByOwner(long owner, String type)
	{
		List<ContentCategory> list=getJdbcTemplate().query("select * from es_content_category where ownerid=? and type=? limit 1", new Object[]{owner,type},new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ContentCategory cc=new ContentCategory();
				cc.setId(rs.getLong("id"));
				return cc;
			}
		});
		return list.size()>0?list.get(0):null;
	}

	private static final String FIND_CONTENT_CATEGORY_BY_NAME = "select count(*) from es_content_category where name=? and ownerid=? and fartherCatId=? and status!='DEL' and typeid=? ";

	public int findContentCategoryByName(String ccname, long owner, long ccid, long typeid) {
		return getJdbcTemplate().queryForInt(FIND_CONTENT_CATEGORY_BY_NAME, new Object[] { ccname, owner, ccid, typeid });
	}

	private static final String FIND_FATHER_CATEGORY_BY_CCID = "select fartherCatId,type from es_content_category where id= ? ";

	public long findFatherCategoryByCcid(long ccid) {
		List<Long> list = getJdbcTemplate().query(FIND_FATHER_CATEGORY_BY_CCID, new Object[] { ccid }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				long fatherid = rs.getLong("fartherCatId");
				if (fatherid > 0) {
					return fatherid;
				} else {
					return -1L;
				}
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return 0;
	}

	private static final String FIND_TREE_BY_TYPE = "select * from es_content_category where type=? and status !='DEL' and (ownerid =? or ownerid is null) and typeid=? and fartherCatId is null  order by id asc";

	public List<CategoryTree> findTreeByType(String type, long owner, long typeid) {
		return getJdbcTemplate().query(FIND_TREE_BY_TYPE, new Object[] { type, owner, typeid }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				CategoryTree t = new CategoryTree();
				t.setId(rs.getLong("id"));
				t.setPId(rs.getLong("fartherCatId"));
				t.setName(rs.getString("name"));
				return t;
			}
		});
	}

	@Override
	public int updateCategoryName(long id, ContentCategory cc) {
		String sql = "update es_content_category set name=?,content=?,pic=? where id=?";
		return getJdbcTemplate().update(sql, new Object[] { cc.getName(), cc.getDesc(), cc.getPic(), id });
	}

	@Override
	public List<OwnerContentType> findTypeList(long owner) {
		return getJdbcTemplate().query("select * from es_content_type where (ownerid is null or ownerid=? ) and del_tag='N'", new Object[] { owner }, new TypeRowMapper());
	}

	class TypeRowMapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			OwnerContentType ct = new OwnerContentType();
			ct.setId(rs.getLong("id"));
			ct.setName(rs.getString("name"));
			ct.setOwnerid(rs.getLong("ownerid"));
			ct.setType(rs.getString("type"));
			return ct;
		}
	}

	@Override
	public long findDefaultCcid(long typeid, long owner) {
		List<Long> list= getJdbcTemplate().query("select id from es_content_category where typeid=? and ownerid is null and fartherCatId is null and status!='DEL'  order by id asc ",
				new Object[] { typeid }, new RowMapper() {
					@Override
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						return rs.getLong("id");
					}
				});
		if (list.size() == 0) {
			list = getJdbcTemplate().query("select id from es_content_category where typeid=? and ownerid=? and fartherCatId is null and status!='DEL' order by id asc ",
					new Object[] { typeid, owner }, new RowMapper() {
						@Override
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							return rs.getLong("id");
						}
					});
		}
		return list.size() > 0 ? list.get(0) : 0;
	}

	@Override
	public long addContentType(final String ccname, final long owner, final String type) {
		final String sql = "insert into es_content_type (ownerid,name,type,del_tag) values(?,?,?,'N')";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql, new String[] { "id" });
				int i = 1;
				ps.setLong(i++, owner);
				ps.setString(i++, ccname);
				ps.setString(i++, type);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public int deleteContentType(long typeid, long owner) {
		return getJdbcTemplate().update("update es_content_type set del_tag='Y' where id=? and ownerid=?", new Object[] { typeid, owner });
	}

	@Override
	public void delCategoryByTypeid(long typeid, long ownerid) {
		getJdbcTemplate().update("update es_content_category set status='DEL' where typeid=? and ownerid=?", new Object[] { typeid, ownerid });

	}

	@Override
	public void addOwnerType(long typeid, long owner, String string) {
		getJdbcTemplate().update("insert into es_owner_type(typeid,owner,status) values(?,?,?) on duplicate key update status=?", new Object[] { typeid, owner, string, string });
	}

	@Override
	public int addOwnerType(final List<OwnerContentType> updateList, final long owner) {
		getJdbcTemplate().update("delete from es_owner_type where owner=?", new Object[] { owner });
		int[] result = getJdbcTemplate().batchUpdate("insert into  es_owner_type (owner,typeid,status) values(?,?,?)", new BatchPreparedStatementSetter() {
			@Override
			public int getBatchSize() {
				return updateList.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				int i = 1;
				OwnerContentType oct = updateList.get(index);
				ps.setLong(i++, owner);
				ps.setLong(i++, oct.getId());
				ps.setString(i++, oct.getStatus());
			}
		});
		return result.length;
	}

	@Override
	public List<OwnerContentType> findAllContentType(long owner)
	{
		List<OwnerContentType> result=new ArrayList<OwnerContentType>();
		String sql1="select t.*,o.id useid,o.status status from es_content_type t left outer join es_owner_type o on t.id =o.typeid and o.owner=?  where  t.ownerid is null and del_tag!='Y' order by t.id desc";
		List<OwnerContentType> list1=getJdbcTemplate().query(sql1, new Object[]	{ owner },new ContentTypeRowMapper());
		String sql2="select t.*,o.id useid,o.status status from es_content_type t left outer join es_owner_type o on t.id =o.typeid and o.owner=? where  t.ownerid =? and del_tag!='Y'  order by t.id desc";
		List<OwnerContentType> list2=getJdbcTemplate().query(sql2, new Object[]	{ owner,owner },new ContentTypeRowMapper());
		result.addAll(list2);
		result.addAll(list1);
		return result;
	}

	class ContentTypeRowMapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			OwnerContentType t = new OwnerContentType();
			t.setOwnerid(rs.getLong("ownerid"));
			t.setName(rs.getString("name"));
			t.setId(rs.getLong("id"));
			t.setUseid(rs.getLong("useid"));
			t.setStatus(rs.getString("status"));
			t.setType(rs.getString("type"));
			return t;
		}
	}

	@Override
	public List<OwnerContentType> findContentType(long owner) {
		List<OwnerContentType> list = getJdbcTemplate().query(
				"select a.* from es_content_type a join es_owner_type b on a.id=b.typeid where b.owner=? and del_tag!='Y' and b.status='CMP'", new Object[] { owner },
				new TypeRowMapper());
		if (list.size() == 0) {
			list = getJdbcTemplate().query("select * from es_content_type where ownerid is null and del_tag!='Y'", new TypeRowMapper());
		}
		return list;
	}

	@Override
	public void updateCategoryName(final List<OwnerContentType> renameList) {
		getJdbcTemplate().batchUpdate("update es_content_type set name=? where id=?", new BatchPreparedStatementSetter() {
			@Override
			public int getBatchSize() {
				return renameList.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				int i = 1;
				OwnerContentType oct = renameList.get(index);
				ps.setString(i++, oct.getName());
				ps.setLong(i++, oct.getId());
			}
		});
	}

	private static final String FIND_BREAD="select bread from es_content_category where id = ?";
	@Override
	public String findBread(long catid)
	{
		Object[] param={catid};
		try
		{
			return (String) getJdbcTemplate().queryForObject(FIND_BREAD, param, String.class);
		} catch (DataAccessException e)
		{
			return null;
		}
	}

	private static final String SAVE_BREAD="update es_content_category set bread = ? where id = ?";
	@Override
	public int saveBread(long catid, String json)
	{
		Object[] param={json,catid};
		return getJdbcTemplate().update(SAVE_BREAD, param);
	}
	
	@Override
	public List<ContentCategory> findCcHasPageId(long ownerid)
	{
		return getJdbcTemplate().query("select * from es_content_category where ownerid=? and  type='N' and pageid is not null and status!='DEL'", new Object[]{ownerid}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ContentCategory cc = new ContentCategory();
				cc.setId(rs.getLong("id"));
				cc.setOwnerid(rs.getLong("ownerid"));
				cc.setName(rs.getString("name"));
				cc.setType(rs.getString("type"));
				long fartherCatId = rs.getLong("fartherCatId");
				String type = rs.getString("type");
				cc.setIdx(rs.getInt("idx"));
				cc.setTypeid(rs.getLong("typeid"));
				cc.setDesc(rs.getString("content"));
				cc.setPic(rs.getString("pic"));
				return cc;
			}
		});
	}

	
	@Override
	public long findPageidByNewsid(long nid, long owner)
	{
		return getJdbcTemplate().queryForLong("select c.pageid from es_content_news n join es_content_category c on n.catid=c.id  where n.id=?", new Object[]{nid});
	}
	
	@Override
	public List<ContentCategory> findBangDingCategory(long ownerid)
	{
		return getJdbcTemplate().query("select * from esite.es_content_category c join esite.es_content_type t on c.typeid=t.id where c.ownerid=? and c.status!='DEL' and pageid > 0", new Object[]{ ownerid }, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ContentCategory c = new ContentCategory();
				c.setId(rs.getLong("c.id"));
				c.setType(rs.getString("t.type"));
				c.setName(rs.getString("c.name"));
				c.setFartherCatId(rs.getLong("c.fartherCatId"));
				c.setPageid(rs.getLong("pageid"));
				return c;
			}
		});
	}
	
	@Override
	public List<ContentCategory> findBangDingShopCategory(long ownerid,String subtype)
	{
		return getJdbcTemplate().query("select * from esite.es_content_category c  where c.ownerid=? and c.subtype=? and c.status!='DEL' and pageid > 0", new Object[]{ ownerid,subtype }, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ContentCategory c = new ContentCategory();
				c.setId(rs.getLong("c.id"));
				c.setType(rs.getString("c.type"));
				c.setName(rs.getString("c.name"));
				c.setFartherCatId(rs.getLong("c.fartherCatId"));
				c.setPageid(rs.getLong("pageid"));
				return c;
			}
		});
	}

	private static final String FIND_FATHRER_CATEGORY_BY_CCID = "select * from es_content_category where ownerid=? and fartherCatId=? and status !='DEL' order by idx asc,id desc";
	@Override
	public List<ContentCategory> findCategoryByFatherCcid(long catid, long owner)
	{
		return getJdbcTemplate().query(FIND_FATHRER_CATEGORY_BY_CCID, new Object[] { owner, catid }, new MyRowMapper());
	}
}
