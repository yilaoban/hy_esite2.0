package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.ITemplateDao;
import com.huiyee.esite.model.Block2page;
import com.huiyee.esite.model.BlockContext;
import com.huiyee.esite.model.CardBackGround;
import com.huiyee.esite.model.CardBolck;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.MyTempalte;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageCard;
import com.huiyee.esite.model.PageHtml;
import com.huiyee.esite.model.PageTemplate;
import com.huiyee.esite.model.TemplateBlock;
import com.huiyee.esite.model.TemplateCard;
import com.huiyee.esite.model.TemplateCategory;
import com.huiyee.esite.model.TemplateModel;

public class TemplateDaoImpl extends AbstractDao implements ITemplateDao {

	private static final String FIND_TEMPLATE="select ca.* from esite.es_template_category ca order by idx";
	@Override
	public List<TemplateCategory> findTemplateCategory() {
		return getJdbcTemplate().query(FIND_TEMPLATE, new TemplateCategoryRowmapper());
	}
	
	class TemplateCategoryRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TemplateCategory tc = new TemplateCategory();
			tc.setId(rs.getLong("id"));
			tc.setName(rs.getString("name"));
			tc.setIdx(rs.getInt("idx"));
			return tc;
		}
		
	}

	private static final String FIND_TEMPLATE_MODEL_LIST="select * from esite.es_template where categoryid=? and status != 'DEL' and type=?";
	@Override
	public List<TemplateModel> findTemplateModelList(long categoryid,String type) {
		Object[] params={categoryid,type};
		return getJdbcTemplate().query(FIND_TEMPLATE_MODEL_LIST, params, new TemplateModelRowmapper());
	}
	
	class TemplateModelRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TemplateModel model = new TemplateModel();
			model.setId(rs.getLong("id"));
			model.setCategoryid(rs.getLong("categoryid"));
			model.setName(rs.getString("name"));
			model.setCpath(rs.getString("cpath"));
			model.setCimg(rs.getString("cimg"));
			model.setPpath(rs.getString("ppath"));
			model.setPimg(rs.getString("pimg"));
			model.setType(rs.getString("type"));
			return model;
		}
		
	}

	private static final String SAVE_OWNER_TEMPATE="insert into esite.es_owner_template(ownerid,tempid,createtime,name) value(?,?,now(),(select name from esite.es_template where id = ?))";
	@Override
	public int saveOwnerTemplate(long ownerid, long templateid) {
		Object[] params={ownerid,templateid,templateid};
		return getJdbcTemplate().update(SAVE_OWNER_TEMPATE, params);
	}
	
	private static final String FIND_MY_TEMPLATE_BY_OWNERID="select ot.*,t.ppath,t.cpath,t.pimg,t.cimg,t.type,t.categoryid,tc.name categoryName from esite.es_owner_template ot join esite.es_template t on ot.tempid = t.id join esite.es_template_category tc on tc.id = t.categoryid where ot.ownerid = ? and ot.status!='DEL'";
	@Override
	public List<MyTempalte> findMyTemplateByOwnerid(long ownerid) {
		Object[] params={ownerid};
		return getJdbcTemplate().query(FIND_MY_TEMPLATE_BY_OWNERID, params, new MyTemplateRowmapper());
	}
	
	class MyTemplateRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			MyTempalte mt = new MyTempalte();
			mt.setId(rs.getLong("id"));
			mt.setOwnerid(rs.getLong("ownerid"));
			mt.setTempid(rs.getLong("tempid"));
			mt.setName(rs.getString("name"));
			mt.setType(rs.getString("type"));
			mt.setCategoryid(rs.getLong("categoryid"));
			mt.setCategoryName(rs.getString("categoryName"));
			mt.setPpath(rs.getString("ppath"));
			mt.setCpath(rs.getString("cpath"));
			mt.setCimg(rs.getString("cimg"));
			mt.setPimg(rs.getString("pimg"));
			return mt;
		}
		
	}

	@Override
	public MyTempalte findOneTemplate(long id)
	{
		String sql="select ot.*,t.ppath,t.cpath,t.pimg,t.cimg,t.type,t.categoryid,tc.name categoryName from esite.es_owner_template ot join esite.es_template t on ot.tempid = t.id join esite.es_template_category tc on tc.id = t.categoryid where ot.id = ? and ot.status!='DEL'";
		List<MyTempalte> list= getJdbcTemplate().query(sql, new Object[]{id},new MyTemplateRowmapper1());
		if(list.size()>0){
			MyTempalte mt=list.get(0);
			return mt;
		}
		return null;
	}
	class MyTemplateRowmapper1 implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			MyTempalte mt = new MyTempalte();
			mt.setId(rs.getLong("id"));
			mt.setOwnerid(rs.getLong("ownerid"));
			mt.setTempid(rs.getLong("tempid"));
			mt.setName(rs.getString("name"));
			mt.setStyle(rs.getString("style"));
			mt.setPpath(rs.getString("ppath"));
			mt.setCpath(rs.getString("cpath"));
			mt.setCimg(rs.getString("cimg"));
			mt.setPimg(rs.getString("pimg"));
			return mt;
		}
		
	}

	@Override
	public int deleteTemplate(long id)
	{
		String sql="update es_owner_template set status='DEL' where id=?";
		return getJdbcTemplate().update(sql,new Object[]{id});
	}
	
	@Override
	public int updateNameTemplate(long id,String name)
	{
		String sql="update esite.es_owner_template set name=? where id=?";
		return getJdbcTemplate().update(sql,new Object[]{name,id});
	}
	
	@Override
	public int updateTemplate(long id,String style)
	{
		String sql="update esite.es_owner_template set style=? where id=?";
		return getJdbcTemplate().update(sql,new Object[]{style,id});
	}

	private static final String FIND_CARD_COUNT="select count(id) from es_template_card where categoryid =? and type= ? and child='N' and del_tag='N'";
	@Override
	public int findCardCount(long categoryid, String type) {
		Object[] param={categoryid,type};
		return getJdbcTemplate().queryForInt(FIND_CARD_COUNT, param);
	}
	
	@Override
	public int findCardCount(long categoryid, String type,String ptype) {
		String sql="select count(id) from es_template_card where categoryid =? and type= ? and child=? and del_tag='N'";
		Object[] param={categoryid,type,ptype};
		return getJdbcTemplate().queryForInt(sql, param);
	}
	@Override
	public List<TemplateCard> findCardList(long categoryid, String type) {
		StringBuffer buffer=new StringBuffer();
		List<Object> pra=new ArrayList<Object>();
		pra.add(categoryid);
		if(type!=null&&!"".equals(type)){
			buffer.append("select * from es_template_card c where categoryid=? and type=? and child='N' and del_tag='N' order by idx asc,id desc");
			pra.add(type);
		}else{
			buffer.append("select * from es_template_card c where categoryid=? and child='N' and del_tag='N' order by idx asc,id desc");
		}
		return getJdbcTemplate().query(buffer.toString(), pra.toArray(), new TemplateCardRowmapper());
	}
	
	class TemplateCardRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TemplateCard tc = new TemplateCard();
			tc.setId(rs.getLong("id"));
			tc.setName(rs.getString("name"));
			tc.setCategory(rs.getLong("categoryid"));
			tc.setPath(rs.getString("path"));
			tc.setCss(rs.getString("css"));
			tc.setType(rs.getString("type"));
			tc.setSimg(rs.getString("simg"));
			tc.setBimg(rs.getString("bimg"));
			tc.setDesc(rs.getString("desc"));
			tc.setUrl(rs.getString("url"));
			return tc;
		}
		
	}

	private static final String FIND_CARD_CATEGORY="select c.id from es_page p join es_template_category c on p.stype=c.value where p.id = ?";
	@Override
	public long findCardCategoryid(long pageid) {
		Object[] params={pageid};
		try {
			return getJdbcTemplate().queryForLong(FIND_CARD_CATEGORY, params);
		} catch (DataAccessException e) {
			return 0;
		}
	}

	private static final String FIND_POSITION="select count(id) from es_page_card where pageid = ?";
	private static final String SAVE_PAGE_CARD="insert into es_page_card(pageid,cid,position,name,createtime)values(?,?,?,(select name from es_template_card where id = ?),now())";
	@Override
	public long savePageCard(final long pageid,final long cardid) {
		final int position =getJdbcTemplate().queryForInt(FIND_POSITION,new Object[]{pageid});
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_PAGE_CARD,
						new String[] { "id" });
				ps.setLong(1, pageid);
				ps.setLong(2, cardid);
				ps.setLong(3, position+1);
//				ps.setString(4, "¿¨Æ¬"+(position+1));
				ps.setLong(4, cardid);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	private static final String SAVE_PAGE_BLOCK_RELATION="insert into es_page_block_relation(pcid,cbid,json,createtime,pfid,display) values (?,?,?,now(),?,?)";
	@Override
	public long savePageBlockRelation(final long pcid,final long cbid,final String json,final long pfid,final String display) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_PAGE_BLOCK_RELATION,new String[] { "id" });
				int i = 1;
				ps.setLong(i++, pcid);
				ps.setLong(i++, cbid);
				ps.setString(i++, json);
				ps.setLong(i++, pfid);
				ps.setString(i++, display);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}

	private static final String FINDRELATION = "SELECT p.* FROM esite.es_page_block_relation r, esite.es_page_card c, esite.es_block_2_page p WHERE r.pcid=c.id AND r.id=p.relationid AND c.pageid=?";
	@SuppressWarnings("unchecked")
	@Override
	public List<Block2page> findBlock2pageByPageid(long pageid)
	{
		Object[] params =
		{ pageid };
		return getJdbcTemplate().query(FINDRELATION, params, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Block2page b2p = new Block2page();
				b2p.setRelationid(rs.getLong("relationid"));
				b2p.setPageid(rs.getLong("pageid"));
				return b2p;
			}

		});
	}
	@SuppressWarnings("unchecked")
	@Override
	public PageBlockRelation findPageBlockRelationById(long id)
	{
		Object[] params =
		{ id };
		List<PageBlockRelation> list = getJdbcTemplate().query("SELECT * FROM esite.es_page_block_relation WHERE id=?", params, new PageBlockRelationRowmapper());
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}
	@Override
	public int updatePageBlockRelation(long id, String json)
	{
		Object[] params =
		{ json, id };
		return getJdbcTemplate().update("UPDATE esite.es_page_block_relation SET json=? WHERE id=?", params);
	}
	
	private static final String FIND_CARD_BLOCK="select cb.*,b.featureid from es_template_card_block cb join es_template_block b on b.id = cb.bid where cid = ? and cb.alone = 'N' order by position ";
	@Override
	public List<CardBolck> findCardBlock(long cardid) {
		Object[] params={cardid};
		return getJdbcTemplate().query(FIND_CARD_BLOCK, params, new CardBlockRowmapper());
	}
	
	class CardBlockRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			CardBolck cb = new CardBolck();
			cb.setId(rs.getLong("id"));
			cb.setCid(rs.getLong("cid"));
			cb.setBid(rs.getLong("bid"));
			cb.setJson(rs.getString("json"));
			cb.setPosition(rs.getInt("position"));
			cb.setFeatureid(rs.getLong("featureid"));
			return cb;
		}
		
	}

	private static final String FIND_CARD_BY_ID="select c.*,pc.bg ubg,pc.pageid from es_template_card c join es_page_card pc on c.id = pc.cid where pc.id = ?";
	@Override
	public TemplateCard findCardByPcid(long pcid) {
		Object[] params={pcid};
		List<TemplateCard> list = getJdbcTemplate().query(FIND_CARD_BY_ID, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				TemplateCard tc = new TemplateCard();
				tc.setId(rs.getLong("id"));
				tc.setName(rs.getString("name"));
				tc.setCategory(rs.getLong("categoryid"));
				tc.setPath(rs.getString("path"));
				tc.setCss(rs.getString("css"));
				tc.setType(rs.getString("type"));
				tc.setSimg(rs.getString("simg"));
				tc.setBimg(rs.getString("bimg"));
				tc.setDesc(rs.getString("desc"));
				tc.setUrl(rs.getString("url"));
				String bg = rs.getString("bg");
				String ubg = rs.getString("ubg");
				if(!StringUtils.isEmpty(ubg)){
					tc.setBg(ubg);
				}else{
					tc.setBg(bg);
				}
				tc.setPageid(rs.getLong("pageid"));
				return tc;
			}
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	private static final String FIND_CARD_BY_ID2="select c.*,pc.bg ubg,pc.pageid from es_template_card c join es_page_card_2 pc on c.id = pc.cid where pc.id = ?";
	@Override
	public TemplateCard findCardByPcid2(long pcid) {
		Object[] params={pcid};
		List<TemplateCard> list = getJdbcTemplate().query(FIND_CARD_BY_ID2, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				TemplateCard tc = new TemplateCard();
				tc.setId(rs.getLong("id"));
				tc.setName(rs.getString("name"));
				tc.setCategory(rs.getLong("categoryid"));
				tc.setPath(rs.getString("path"));
				tc.setCss(rs.getString("css"));
				tc.setType(rs.getString("type"));
				tc.setSimg(rs.getString("simg"));
				tc.setBimg(rs.getString("bimg"));
				tc.setDesc(rs.getString("desc"));
				tc.setUrl(rs.getString("url"));
				String bg = rs.getString("bg");
				String ubg = rs.getString("ubg");
				if(!StringUtils.isEmpty(ubg)){
					tc.setBg(ubg);
				}else{
					tc.setBg(bg);
				}
				tc.setPageid(rs.getLong("pageid"));
				return tc;
			}
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	private static final String FIND_PAGE_BLOCK_RELATION_BY_CARDID="select r.* from es_template_block b join es_template_card_block cb on b.id = cb.bid  join es_page_block_relation r on r.cbid = cb.id where r.id = ?";
	@Override
	public List<PageBlockRelation> findPageBlockRelationBycardid(long cardid) {
		Object[] params={cardid};
		return getJdbcTemplate().query(FIND_PAGE_BLOCK_RELATION_BY_CARDID, params, new PageBlockRelationRowmapper());
	}
	
	class PageBlockRelationRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			PageBlockRelation pbr = new PageBlockRelation();
			pbr.setId(rs.getLong("id"));
			pbr.setCbid(rs.getLong("cbid"));
			pbr.setPcid(rs.getLong("pcid"));
			pbr.setJson(rs.getString("json"));
			pbr.setPfid(rs.getLong("pfid"));
			pbr.setDisplay(rs.getString("display"));
			return pbr;
		}
		
	}

	private static final String FIND_BLOCK_BY_CARDID="select b.*,r.id relationid,r.json ujson,r.display display from es_template_block b join es_template_card_block cb on b.id = cb.bid join es_page_block_relation r on cb.id= r.cbid join es_page_card pc on r.pcid = pc.id where pc.id = ?";
	@Override
	public List<TemplateBlock> findBlocksByPcid(long pcid) {
		Object[] params={pcid};
		return getJdbcTemplate().query(FIND_BLOCK_BY_CARDID, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				TemplateBlock tb = new TemplateBlock();
				tb.setId(rs.getLong("id"));
				tb.setName(rs.getString("name"));
				tb.setJson(rs.getString("json"));
				tb.setType(rs.getString("type"));
				tb.setFeatureid(rs.getLong("featureid"));
				tb.setRelationid(rs.getLong("relationid"));
				tb.setUjson(rs.getString("ujson"));
				tb.setDisplay(rs.getString("display"));
				tb.setVjson(rs.getString("vjson"));
				tb.setAlone(rs.getString("alone"));
				tb.setImg(rs.getString("img"));
				tb.setDesc(rs.getString("desc"));
				return tb;
			}
		});
	}
	
	private static final String FIND_BLOCK_BY_CARDID2="select b.*,r.id relationid,r.json ujson,r.display from es_template_block b join es_template_card_block cb on b.id = cb.bid join es_page_block_relation_2 r on cb.id= r.cbid join es_page_card pc on r.pcid = pc.id where pc.id = ?";
	@Override
	public List<TemplateBlock> findBlocksByPcid2(long pcid) {
		Object[] params={pcid};
		return getJdbcTemplate().query(FIND_BLOCK_BY_CARDID2, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				TemplateBlock tb = new TemplateBlock();
				tb.setId(rs.getLong("id"));
				tb.setName(rs.getString("name"));
				tb.setJson(rs.getString("json"));
				tb.setType(rs.getString("type"));
				tb.setFeatureid(rs.getLong("featureid"));
				tb.setRelationid(rs.getLong("relationid"));
				tb.setUjson(rs.getString("ujson"));
				tb.setDisplay(rs.getString("display"));
				tb.setVjson(rs.getString("vjson"));
				tb.setAlone(rs.getString("alone"));
				tb.setImg(rs.getString("img"));
				tb.setDesc(rs.getString("desc"));
				return tb;
			}
		});
	}

	private static final String FIND_TEMPLATE_BLOCK_BY_ID="select b.* from es_page_block_relation r join es_template_card_block cb on r.cbid = cb.id join es_template_block b on cb.bid = b.id where r.id = ?";
	@Override
	public TemplateBlock findTemplateBlockByRelationid(long relationid) {
		Object[] params={relationid};
		List<TemplateBlock> list = getJdbcTemplate().query(FIND_TEMPLATE_BLOCK_BY_ID, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				TemplateBlock tb = new TemplateBlock();
				tb.setId(rs.getLong("id"));
				tb.setName(rs.getString("name"));
				tb.setJson(rs.getString("json"));
				tb.setType(rs.getString("type"));
				tb.setFid(rs.getLong("featureid"));
				tb.setImg(rs.getString("img"));
				tb.setDesc(rs.getString("desc"));
				return tb;
			}
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	private static final String FIND_PAGE_BLOCK_RELATION_BY_BLOCKID="select r.*,b.cid,pf.fid from es_page_block_relation r join es_template_card_block b on r.cbid = b.id left outer join es_page_feature pf on r.pfid = pf.id  where r.id = ?";
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

	private static final String SAVE_RELATION_JSON="update es_page_block_relation set json = ? where id = ?";
	@Override
	public int saveRelationJson(long relationid, String json) {
		Object[] params={json,relationid};
		return getJdbcTemplate().update(SAVE_RELATION_JSON,params);
	}

	private static final String FIND_TEMPLATE_CARD_BY_PAGEID="select pc.eventname,pc.css,pc.isshow, pc.id ,pc.pageid,pc.position,pc.name cardname,pc.bg bg,c.id cardid,c.bimg cardbimg,c.simg cardsimg,c.path,c.desc,c.type from es_page_card pc join es_template_card c on pc.cid = c.id where pc.pageid = ? order by pc.position";
	@Override
	public List<PageCard> findTemplateCardByPageid(long pageid) {
		Object[] param={pageid};
		return getJdbcTemplate().query(FIND_TEMPLATE_CARD_BY_PAGEID, param, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				PageCard pc = new PageCard();
				pc.setId(rs.getLong("id"));
				pc.setPageid(rs.getLong("pageid"));
				pc.setPosition(rs.getInt("position"));
				pc.setCardid(rs.getLong("cardid"));
				pc.setCardname(rs.getString("cardname"));
				pc.setCardbimg(rs.getString("cardbimg"));
				pc.setCardsimg(rs.getString("cardsimg"));
				pc.setBg(rs.getString("bg"));
				pc.setIsshow(rs.getString("isshow"));
				pc.setPath(rs.getString("path"));
				pc.setDesc(rs.getString("desc"));
				pc.setCss(rs.getString("pc.css"));
				pc.setType(rs.getString("c.type"));
				pc.setEventName(rs.getString("eventname"));
				return pc;
			}
		});
	}
	
	private static final String FIND_TEMPLATE_CARD_BY_PAGEID2="select pc.eventname,pc.isshow, pc.id ,pc.pageid,pc.position,pc.name cardname,pc.bg bg,c.id cardid,c.bimg cardbimg,c.simg cardsimg,c.path from es_page_card_2 pc join es_template_card c on pc.cid = c.id where pc.pageid = ? order by pc.position";
	@Override
	public List<PageCard> findTemplateCardByPageid2(long pageid) {
		Object[] param={pageid};
		return getJdbcTemplate().query(FIND_TEMPLATE_CARD_BY_PAGEID2, param, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				PageCard pc = new PageCard();
				pc.setId(rs.getLong("id"));
				pc.setPageid(rs.getLong("pageid"));
				pc.setPosition(rs.getInt("position"));
				pc.setCardid(rs.getLong("cardid"));
				pc.setCardname(rs.getString("cardname"));
				pc.setCardbimg(rs.getString("cardbimg"));
				pc.setCardsimg(rs.getString("cardsimg"));
				pc.setBg(rs.getString("bg"));
				pc.setIsshow(rs.getString("isshow"));
				pc.setPath(rs.getString("path"));
				pc.setEventName(rs.getString("eventname"));
				return pc;
			}
		});
	}

	private static final String UPDATE_CARD_POSITION = "update es_page_card set position = ? where id = ?";
	@Override
	public int updateCardPosition(long cardid, long position) {
		Object[] params={position,cardid};
		return getJdbcTemplate().update(UPDATE_CARD_POSITION,params);
	}

	private static final String DELETE_CARD = "delete from es_page_card where id = ?";
	@Override
	public int deleteCard(long pcid) {
		Object[] params={pcid};
		return getJdbcTemplate().update(DELETE_CARD, params);
	}

	private static final String DELETE_PAGE_BLOCK_RELATION = "delete from es_page_block_relation where pcid = ?";
	@Override
	public int deletePageBlockRelation(long pcid){
		Object[] params={pcid};
		return getJdbcTemplate().update(DELETE_PAGE_BLOCK_RELATION, params);
	}

	private static final String UPDATE_CARD_NAME = "update es_page_card set name = ? where id = ?";
	@Override
	public int updateCardName(long pcid, String name) {
		Object[] params={name,pcid};
		return getJdbcTemplate().update(UPDATE_CARD_NAME,params);
	}

	@Override
	public long saveNewPageCard(final PageCard pagecard)
	{
		final String sql="insert into es_page_card(pageid,cid,position,name,createtime,bg,isshow,css,eventname)values(?,?,?,?,now(),?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql,
						new String[] { "id" });
				ps.setLong(1,pagecard.getPageid());
				ps.setLong(2,pagecard.getCardid());
				ps.setInt(3,pagecard.getPosition());
				ps.setString(4, pagecard.getCardname());
				ps.setString(5, pagecard.getBg());
				ps.setString(6, pagecard.getIsshow());
				ps.setString(7, pagecard.getCss());
				ps.setString(8, pagecard.getEventName());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}

	@Override
	public List<PageBlockRelation> findPageBlockRelationBycardid1(long pcid)
	{
		String sql="select r.* , cb.alone from  es_page_block_relation r join es_template_card_block cb on r.cbid = cb.id where pcid = ?";
		return getJdbcTemplate().query(sql, new Object[]{pcid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				PageBlockRelation pbr = new PageBlockRelation();
				pbr.setId(rs.getLong("id"));
				pbr.setCbid(rs.getLong("cbid"));
				pbr.setPcid(rs.getLong("pcid"));
				pbr.setJson(rs.getString("json"));
				pbr.setPfid(rs.getLong("pfid"));
				pbr.setDisplay(rs.getString("display"));
				pbr.setAlone(rs.getString("alone"));
				return pbr;
			}
		});
	}

	@Override
	public int updatePageBlockRelation(long relationid, long pfid) {
		String sql = "update es_page_block_relation set pfid = ? where id = ?";
		Object[] params={pfid,relationid};
		return getJdbcTemplate().update(sql,params);
	}

	@Override
	public List<BlockContext> findesBlockContext(long relationid) {
		String sql = "select tbc.* from es_page_block_relation r join es_template_card_block cb on r.cbid = cb.id join es_template_block b on cb.bid = b.id join es_template_block_context tbc on b.id = tbc.blockid where r.id = ?";
		return getJdbcTemplate().query(sql, new Object[]{relationid}, new BlockContextRowmapper());
	}
	
	class BlockContextRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			BlockContext bc = new BlockContext();
			bc.setId(rs.getLong("id"));
			bc.setBlockid(rs.getLong("blockid"));
			bc.setContext(rs.getString("context"));
			bc.setType(rs.getString("type"));
			bc.setCardid(rs.getLong("cardid"));
			return bc;
		}
	}

	@Override
	public long saveNewPage(final long relationid,final long siteid,final String stype, final long contextid,final String name) {
		final String sql = "insert into es_page(name,jspname,siteid,stype,createtime,updatetime,relationid,contextid,isonline,onlinetime) values(?,?,?,?,now(),now(),?,?,'Y',now())";
		final String jspname = "template_blank.jsp";
		/*Object[] params={name,jspname,siteid,stype,relationid,contextid};
		return getJdbcTemplate().update(sql,params);*/
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql,
						new String[] { "id" });
				ps.setString(1, name);
				ps.setString(2,jspname);
				ps.setLong(3,siteid);
				ps.setString(4, stype);
				ps.setLong(5, relationid);
				ps.setLong(6, contextid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
		
	}

	@Override
	public Page findSiteidByPageid(long pageid) {
		String sql = "select id,name,siteid,stype,bg,jspstyle from es_page where id = ?";
		List<Page> list = getJdbcTemplate().query(sql, new Object[]{pageid}, new PageRowmapper());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	class PageRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Page p = new Page();
			p.setId(rs.getLong("id"));
			p.setName(rs.getString("name"));
			p.setSiteid(rs.getInt("siteid"));
			p.setJspstyle(rs.getString("jspstyle"));
			p.setStype(rs.getString("stype"));
			p.setBg(rs.getString("bg"));
			return p;
		}
	}

	@Override
	public List<Page> findChildPageByPageid(long pageid) {
		String sql = "select r.id relationid,tbc.id contextid from es_page_block_relation r join es_template_card_block cb on r.cbid = cb.id join es_template_block b on cb.bid = b.id join es_template_block_context tbc on b.id = tbc.blockid join es_page_card pc on r.pcid = pc.id where pc.pageid = ?";
		return getJdbcTemplate().query(sql, new Object[]{pageid}, new ChildPageRowmapper());
	}
	
	class ChildPageRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Page p = new Page();
			p.setRelationid(rs.getLong("relationid"));
			p.setContextid(rs.getLong("contextid"));
			return p;
		}
	}
	
	@Override
	public List<TemplateCard> findCardList(long categoryid, String type,String ptype) {
		StringBuffer buffer=new StringBuffer();
		Object[] params;
		if(type!=null){
			buffer.append( "select * from es_template_card c where categoryid=? and type=? and child = ? and del_tag='N' order by id desc");
			 params=new Object[]{categoryid,type,ptype};
		}else{
			buffer.append( "select * from es_template_card c where categoryid=? and child = ? and del_tag='N' order by id desc");
			params=new Object[]{categoryid,ptype};
		}
		return getJdbcTemplate().query(buffer.toString(), params, new TemplateCardRowmapper());
	}
	
	@Override
	public List<TemplateCard> findCardList(long categoryid, String type,String ptype,int start,int size) {
		StringBuffer buffer=new StringBuffer();
		Object[] params;
		if(type!=null){
			buffer.append( "select * from es_template_card c where categoryid=? and type=? and child = ? and del_tag='N' order by id desc limit ?,?");
			 params=new Object[]{categoryid,type,ptype,start,size};
		}else{
			buffer.append( "select * from es_template_card c where categoryid=? and child = ? and del_tag='N' order by id desc limit ?,?");
			params=new Object[]{categoryid,ptype,start,size};
		}
		return getJdbcTemplate().query(buffer.toString(), params, new TemplateCardRowmapper());
	}

	@Override
	public List<Page> findTemplateCardByRelationidAndContextid(long relationid, long contextid) {
		String sql = "select id from es_page p where p.relationid = ? and p.contextid = ? ";
		Object[] params={relationid,contextid};
		return getJdbcTemplate().query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Page p = new Page();
				p.setId(rs.getLong("id"));
				return p;
			}
		});
	}

	@Override
	public List<Page> findResultPageByPageid(long pageid) {
		String sql = "select p.id from es_page_block_relation r join es_page_card pc on r.pcid = pc.id join es_template_card_block cb on r.cbid = cb.id join es_template_block b on cb.bid = b.id join es_template_block_context bc on bc.blockid = b.id  join es_page p on (p.relationid = r.id and p.contextid = bc.id) where pageid = ? and p.contextid = 2";
		Object[] params={pageid};
		return getJdbcTemplate().query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Page p = new Page();
				p.setId(rs.getLong("id"));
				return p;
			}
		});
	}

	@Override
	public List<Page> findResultSuccessPageByPageid(long pageid) {
		String sql = "select p.id,p.contextid,p.name,p.relationid,p.jspstyle,bc.type from es_page_block_relation r join es_page_card pc on r.pcid = pc.id join es_template_card_block cb on r.cbid = cb.id join es_template_block b on cb.bid = b.id join es_template_block_context bc on bc.blockid = b.id  join es_page p on (p.relationid = r.id and p.contextid = bc.id) where pageid = ? order by p.id";
		Object[] params={pageid};
		return getJdbcTemplate().query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Page p = new Page();
				p.setId(rs.getLong("id"));
				p.setContextid(rs.getLong("contextid"));
				p.setName(rs.getString("name"));
				p.setBctype(rs.getString("type"));
				p.setRelationid(rs.getLong("relationid"));
				p.setJspstyle(rs.getString("jspstyle"));
				return p;
			}
		});
	}

	@Override
	public Page findPageByRelationidAndContextid(long relationid, long contextid) {
		String sql = "select id,name,siteid,stype,bg,jspstyle from es_page where relationid = ? and contextid = ? ";
		Object[] params = {relationid,contextid};
		List<Page> list = getJdbcTemplate().query(sql, params, new PageRowmapper());
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public BlockContext findBlockContextid(long blockid, String type) {
		String sql = "select bc.* from es_template_block_context bc where bc.blockid = ? and bc.type = ?";
		Object[] params = {blockid,type};
		List<BlockContext> list = getJdbcTemplate().query(sql, params, new BlockContextRowmapper());
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	
	private static final String FIND_PAGEID_BY_PCID="select pageid from es_page_card where id = ?";
	@Override
	public long findPageidByPcid(long pcid) {
		return getJdbcTemplate().queryForLong(FIND_PAGEID_BY_PCID, new Object[]{pcid});
	}

	private static final String FIND_RELATIONID_BY_PAGEID = "select relationid from es_page where id = ?";
	@Override
	public long findRelationidByPageid(long pageid) {
		return getJdbcTemplate().queryForLong(FIND_RELATIONID_BY_PAGEID, new Object[]{pageid});
	}

	private static final String FIND_PAGEID_BY_RELATIONID = "select pageid from es_page_block_relation r join es_page_card pc on r.pcid = pc.id where r.id = ?";
	@Override
	public long findPageidByRelationid(long relationid) {
		try {
			return getJdbcTemplate().queryForLong(FIND_PAGEID_BY_RELATIONID, new Object[]{relationid});
		} catch (DataAccessException e) {
			System.out.println("relation:"+relationid);
			return 0;
		}
	}

	private static final String FIND_PAGE_BY_PAGEID = "select p.* from es_page p join es_site s on p.siteid = s. id where p.id = ? and s.ownerid = ? and p.status != 'DEL'";
	@Override
	public Page findPageByPageid(long pageid,long ownerid) {
		List<Page> list = getJdbcTemplate().query(FIND_PAGE_BY_PAGEID, new Object[]{pageid,ownerid}, new PageRowmapper());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public PageHtml findHtmlByPageid(long pageid,String type){
		String sql = "select * from es_page_html where pageid = ? and type = ?";
		List<PageHtml>  list = getJdbcTemplate().query(sql, new Object[]{pageid,type},new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				PageHtml ph = new PageHtml();
				ph.setId(rs.getLong("id"));
				ph.setPageid(rs.getLong("pageid"));
				ph.setHtml(rs.getString("html"));
				ph.setCss(rs.getString("css"));
				ph.setJs(rs.getString("js"));
				ph.setType(rs.getString("type"));
				return ph;
			}
		});
		if(list!= null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	
	@Override
	public List<PageCard> findPageCardAllByPageid(long pageid)
	{
		String sql="select * from es_page_card where pageid=?";
		return getJdbcTemplate().query(sql, new Object[]{pageid},new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				PageCard pc = new PageCard();
				pc.setId(rs.getLong("id"));
				pc.setPageid(rs.getLong("pageid"));
				pc.setCardid(rs.getLong("cid"));
				pc.setPosition(rs.getInt("position"));
				pc.setCardname(rs.getString("name"));
				pc.setBg(rs.getString("bg"));
				pc.setIsshow(rs.getString("isshow"));
				pc.setCss(rs.getString("css"));
				pc.setEventName(rs.getString("eventName"));
				return pc;
			}
		});
	}


	@Override
	public int deletePageBolckRelation2(long pcid)
	{
		String sql="delete from es_page_block_relation_2 where pcid=?";
		return getJdbcTemplate().update(sql,new Object[]{pcid});
	}

	@Override
	public int deletePageCard2(long pageid)
	{
		String sql="delete from es_page_card_2 where pageid=?";
		return getJdbcTemplate().update(sql,new Object[]{pageid});
	}

	@Override
	public int savePageBolckRelation2(long id,long pcid, long cbid, String json, long pfid,String display)
	{
		String sql="insert into es_page_block_relation_2 (id,pcid,cbid,json,createtime,pfid,display) values(?,?,?,?,now(),?,?)";
		return getJdbcTemplate().update(sql,new Object[]{id,pcid,cbid,json,pfid,display});
	}

	@Override
	public int savePageCard2(long id,long pageid, long cid, int position, String name,String bg,String isshow,String css,String eventName)
	{
		String sql="insert into es_page_card_2 (id,pageid,cid,position,name,createtime,bg,isshow,css,eventname) values(?,?,?,?,?,now(),?,?,?,?)";
		return getJdbcTemplate().update(sql,new Object[]{id,pageid,cid,position,name,bg,isshow,css,eventName});
	}

	@Override
	public int updatePageJspName(String jspname,String jspstyle, long pageid)
	{
		String sql = "update es_page set jspname = ?,jspstyle=? where id = ?";
		Object[] params={jspname,jspstyle,pageid};
		return getJdbcTemplate().update(sql,params);
	}

	@Override
	public List<TemplateBlock> findRelationidByPcid(long pcid) {
		String sql="select b.*,r.id relationid,r.json ujson from es_template_block b join es_template_card_block cb on b.id = cb.bid join es_page_block_relation r on cb.id= r.cbid join es_page_card pc on r.pcid = pc.id where pc.id = ? and b.featureid is not null";
		Object[] params={pcid};
		return getJdbcTemplate().query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				TemplateBlock tb = new TemplateBlock();
				tb.setId(rs.getLong("id"));
				tb.setName(rs.getString("name"));
				tb.setJson(rs.getString("json"));
				tb.setType(rs.getString("type"));
				tb.setRelationid(rs.getLong("relationid"));
				tb.setUjson(rs.getString("ujson"));
				tb.setImg(rs.getString("img"));
				tb.setDesc(rs.getString("desc"));
				return tb;
			}
		});
	
	}

	@Override
	public List<Block2page> findAnotherPageidByPageid(long pageid)
	{
		String sql="select bp.relationid,bp.pageid FROM es_block_2_page AS bp Join es_page_block_relation AS pbr ON bp.relationid = pbr.id Join es_page_card AS pc ON pbr.pcid = pc.id Join es_page AS p ON pc.pageid = p.id where p.id=?";
		return getJdbcTemplate().query(sql, new Object[]{pageid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Block2page bp=new Block2page();
				bp.setRelationid(rs.getLong("relationid"));
				bp.setPageid(rs.getLong("pageid"));
				return bp;
			}
		});
	}

	private static final String SAVE_BLOCK_2_PAGE="insert into es_block_2_page(relationid,pageid,fapageid,type) values (?,?,?,?) on duplicate key update pageid = ?";
	@Override
	public int saveBlock2Page(long relationid, long pageid,long fapageid,int type) {
		Object[] params={relationid,pageid,fapageid,type,pageid};
		return getJdbcTemplate().update(SAVE_BLOCK_2_PAGE,params);
	}

	private static final String DELETE_BLOCK_2_PAGE="delete from es_block_2_page where relationid = ?";
	@Override
	public int deleteBlock2Page(long relationid) {
		Object[] params={relationid};
		return getJdbcTemplate().update(DELETE_BLOCK_2_PAGE,params);
	}

	@Override
	public ContentNew findNewsByNid(long id) {
		String sql = "select * from es_content_news where id = ?";
		Object[] params = { id};
		List<ContentNew> list = getJdbcTemplate().query(sql, params, new MyRowMapper());
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	class MyRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			ContentNew cn = new ContentNew();
			cn.setId(rs.getLong("id"));
			cn.setOwnerid(rs.getLong("ownerid"));
			cn.setCatid(rs.getLong("catid"));
			cn.setTitle(rs.getString("title"));
			cn.setContent(rs.getString("content"));
			cn.setStatus(rs.getString("status"));
			cn.setBimgurl(rs.getString("bimgurl"));
			cn.setSimgurl(rs.getString("simgurl"));
			cn.setLinkurl(rs.getString("linkurl"));
			cn.setIslink(rs.getString("islink"));
			cn.setShortdesc(rs.getString("shortdesc"));
			cn.setCreatetime(rs.getTimestamp("createtime"));
			cn.setUpdatetime(rs.getTimestamp("updatetime"));
			cn.setTopicid(rs.getLong("topicid"));
			cn.setFatie(rs.getString("fatie"));
			return cn;
		}
	}

	@Override
	public int savePageCardStyle(long cardid, String bg)
	{
		String sql="update es_page_card set bg=? where id=? ";
		return getJdbcTemplate().update(sql,new Object[]{bg,cardid});
	}
	
	@Override
	public int savePageCardCss(long cardid, String css)
	{
		String sql="update es_page_card set css=? where id=? ";
		return getJdbcTemplate().update(sql,new Object[]{css,cardid});
	}

	@Override
	public CardBackGround findBgByCardid(long cardid)
	{
		String sql="select pc.bg bg1,tc.bg bg2 from es_page_card pc join es_template_card tc on pc.cid=tc.id where pc.id=?";
		List<CardBackGround> list=getJdbcTemplate().query(sql, new Object[]{cardid},new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				CardBackGround bg=new CardBackGround();
				bg.setBg1(rs.getString("bg1"));
				bg.setBg2(rs.getString("bg2"));
				return bg;
			}
		});
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public PageCard findcssBgByCardid(long cardid)
	{
		String sql="select css from es_page_card where id=?";
		List<PageCard> list=getJdbcTemplate().query(sql, new Object[]{cardid},new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				PageCard pc=new PageCard();
				pc.setCss(rs.getString("css"));
				return pc;
			}
		});
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public String findPageStype(long pageid)
	{
		String sql="select stype from es_page where id=?";
		List<String> list=getJdbcTemplate().queryForList(sql, new Object[]{pageid},String.class);
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<PageTemplate> findPageTemplateList()
	{
		String sql="select * from es_page_template ";
		return getJdbcTemplate().query(sql, new PageTemplateRowmapper());
	}
	class PageTemplateRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			PageTemplate pt = new PageTemplate();
			pt.setId(rs.getLong("id"));
			pt.setName(rs.getString("name"));
			pt.setPageid(rs.getLong("pageid"));
			pt.setImg(rs.getString("img"));
			return pt;
		}
		
	}

	private static final String FIND_JSON_BY_RELATION="select json from es_page_block_relation where id = ?";
	@Override
	public String findJsonByRelationid(long relationid) {
		try {
			return (String) getJdbcTemplate().queryForObject(FIND_JSON_BY_RELATION,new Object[]{relationid}, String.class);
		} catch (DataAccessException e) {
			return null;
		}
	}

	private static final String SAVE_JSON_BY_RELATIONID="update es_page_block_relation set json = ? where id = ?";
	@Override
	public int saveJsonByRelationid(long relationid, String json) {
		Object[] params={json,relationid};
		return getJdbcTemplate().update(SAVE_JSON_BY_RELATIONID, params);
	}

	@Override
	public String findSubTypeByPageid(long pageid)
	{
		String sql="select subtype from es_template_card c join es_page_card pc where c.id=pc.cid and pc.pageid=?";
		List<String> list=getJdbcTemplate().queryForList(sql, new Object[]{pageid},String.class);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<TemplateCard> findTemplateCardList(long categoryid, String subtype)
	{
		StringBuffer buffer=new StringBuffer();
		List<Object> pra=new ArrayList<Object>();
		pra.add(categoryid);
		if(subtype!=null&&!"".equals(subtype)){
			buffer.append("select * from es_template_card c where categoryid=? and subtype=? and child='N' and del_tag='N' order by idx asc,id desc");
			pra.add(subtype);
		}else{
			buffer.append("select * from es_template_card c where categoryid=? and child='N' and del_tag='N' order by idx asc,id desc");
		}
		return getJdbcTemplate().query(buffer.toString(), pra.toArray(), new TemplateCardRowmapper());
	}

	@Override
	public String findCssByPcid(long pcid)
	{
		String sql="select css from es_page_card where id=?";
		try
		{
			return (String) getJdbcTemplate().queryForObject(sql, new Object[]{pcid},String.class);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	private static final String FIND_ZUJIAN="select * from es_template_block where alone = 'Y'";
	@Override
	public List<TemplateBlock> findZujian() {
		return getJdbcTemplate().query(FIND_ZUJIAN, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				TemplateBlock tb = new TemplateBlock();
				tb.setId(rs.getLong("id"));
				tb.setName(rs.getString("name"));
				tb.setJson(rs.getString("json"));
				tb.setType(rs.getString("type"));
				tb.setAlone(rs.getString("alone"));
				tb.setVjson(rs.getString("vjson"));
				tb.setImg(rs.getString("img"));
				tb.setDesc(rs.getString("desc"));
				return tb;
			}
			
		});
	}

	private static final String FIND_CARDID = "select cid from es_page_card where id = ?";
	private static final String FIND_BLOCK_POSITITON="select max(position) from es_template_card_block where cid = ?";
	private static final String ADD_CARD_BLOCK="insert into es_template_card_block(cid,bid,json,createtime,position,alone) values (?,?,?,now(),?,'Y')";
	@Override
	public long addCardBlock(final long pcid,final long bid,final String vjson) {
		final long cardid = getJdbcTemplate().queryForLong(FIND_CARDID,new Object[]{pcid});
		final int position = getJdbcTemplate().queryForInt(FIND_BLOCK_POSITITON,new Object[]{cardid});
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(ADD_CARD_BLOCK,
						new String[] { "id" });
				ps.setLong(1, cardid);
				ps.setLong(2, bid);
				ps.setString(3, vjson);
				ps.setInt(4, (position+1));
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	@Override
	public long findCbidByPcidBid(long pcid, long bid)
	{
		long cardid = getJdbcTemplate().queryForLong(FIND_CARDID,new Object[]{pcid});
		try
		{
			return getJdbcTemplate().queryForLong("select id from es_template_card_block where cid=? and bid=?", new Object[]{cardid,bid});
		} catch (Exception e)
		{
			return 0;
		}
	}

	private static final String FIND_TEMPLATE_BLOCK_BY_PCID="select * from es_template_block where id = ?";
	@Override
	public TemplateBlock findTemplateBlockByBid(long bid) {
		Object[] params={bid};
		List<TemplateBlock> list = getJdbcTemplate().query(FIND_TEMPLATE_BLOCK_BY_PCID,params,new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				TemplateBlock tb = new TemplateBlock();
				tb.setId(rs.getLong("id"));
				tb.setName(rs.getString("name"));
				tb.setJson(rs.getString("json"));
				tb.setType(rs.getString("type"));
				tb.setAlone(rs.getString("alone"));
				tb.setVjson(rs.getString("vjson"));
				tb.setImg(rs.getString("img"));
				tb.setDesc(rs.getString("desc"));
				return tb;
			}
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	private static final String UPDATE_EVENT="update es_page_card set eventname = ? where id = ?";
	@Override
	public int updateEvent(long pcid, String eventName) {
		Object[] params={eventName,pcid};
		return getJdbcTemplate().update(UPDATE_EVENT, params);
	}

	
	private static final String ADD_COPY_CBID="insert into es_template_card_block(cid,bid,json,createtime,position,alone)(select cid,bid,json,now(),position,alone from es_template_card_block where id = ?);";
	@Override
	public long addCopyCbid(final long cbid){
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(ADD_COPY_CBID,
						new String[] { "id" });
				ps.setLong(1, cbid);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	private static final String FIND_PAGE_BLOCK_RELATION_BY_PAGE="select r.* from es_page_block_relation r join es_page_card c on r.pcid=c.id where c.pageid=? ";
	@Override
	public List<PageBlockRelation> findPageBlockRelationByPageId(long pageid)
	{
		return getJdbcTemplate().query(FIND_PAGE_BLOCK_RELATION_BY_PAGE, new Object[]{pageid}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				PageBlockRelation pbr = new PageBlockRelation();
				pbr.setId(rs.getLong("id"));
				pbr.setJson(rs.getString("json"));
				return pbr;
			}
		});
	}

	private static final String FIND_PAGE_BLOCK_BY_ID="select pc.eventname,pc.css,pc.isshow, pc.id ,pc.pageid,pc.position,pc.name cardname,pc.bg bg,c.id cardid,c.bimg cardbimg,c.simg cardsimg,c.path,c.desc,c.type from es_page_card pc join es_template_card c on pc.cid = c.id where pc.id = ? order by pc.position";
	@Override
	public PageCard findPageBlockById(long pcid){
		Object[] param={pcid};
		List<PageCard> list= getJdbcTemplate().query(FIND_PAGE_BLOCK_BY_ID, param, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				PageCard pc = new PageCard();
				pc.setId(rs.getLong("id"));
				pc.setPageid(rs.getLong("pageid"));
				pc.setPosition(rs.getInt("position"));
				pc.setCardid(rs.getLong("cardid"));
				pc.setCardname(rs.getString("cardname"));
				pc.setCardbimg(rs.getString("cardbimg"));
				pc.setCardsimg(rs.getString("cardsimg"));
				pc.setBg(rs.getString("bg"));
				pc.setIsshow(rs.getString("isshow"));
				pc.setPath(rs.getString("path"));
				pc.setDesc(rs.getString("desc"));
				pc.setCss(rs.getString("pc.css"));
				pc.setType(rs.getString("c.type"));
				pc.setEventName(rs.getString("eventname"));
				return pc;
			}
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public ContentProduct findProductByNid(long id){
		String sql = "select * from es_content_product where id = ?";
		Object[] params = { id};
		List<ContentProduct> list = getJdbcTemplate().query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException{
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
				p.setIdx(rs.getInt("idx"));
				p.setTopicid(rs.getLong("topicid"));
				p.setFatie(rs.getString("fatie"));
				return p;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	private static final String SAVE_PAGE_BLOCK_RELATION_2="insert into es_page_block_relation_2 (select * from es_page_block_relation where pcid = ?)";
	@Override
	public int savePageBolckRelation2(long pcid)
	{
		Object[] param={pcid};
		return getJdbcTemplate().update(SAVE_PAGE_BLOCK_RELATION_2,param);
	}

}
