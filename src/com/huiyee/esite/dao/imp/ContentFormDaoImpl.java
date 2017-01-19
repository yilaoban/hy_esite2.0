package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IContentFormDao;
import com.huiyee.esite.model.ContentForm;
import com.huiyee.esite.model.ContentFormMapping;
import com.huiyee.esite.model.ContentFormRecord;
import com.huiyee.interact.appointment.model.OrderMappingModel;

public class ContentFormDaoImpl extends AbstractDao implements IContentFormDao {
	@Override
	public long addForm(final long owner, final String title, final long catid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement("insert into es_content_form (ownerid,title,catid) values(?,?,?)", new String[] { "id" });
				ps.setLong(1, owner);
				ps.setString(2, title);
				ps.setLong(3, catid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public long addFormMapping(final long formid) {
		final List<ContentFormMapping> list = getBaseMapping();
		int[] arr = getJdbcTemplate().batchUpdate("insert into es_content_form_mapping (formid,name,mapping,coltype,stype,row) values(?,?,?,?,?,?)",
				new BatchPreparedStatementSetter() {
					@Override
					public int getBatchSize() {
						return list.size();
					}

					@Override
					public void setValues(PreparedStatement ps, int index) throws SQLException {
						int i = 1;
						ContentFormMapping cfm = list.get(index);
						ps.setLong(i++, formid);
						ps.setString(i++, cfm.getName());
						ps.setString(i++, cfm.getMapping());
						ps.setString(i++, cfm.getColtype());
						ps.setString(i++, cfm.getStype());
						ps.setInt(i++, cfm.getRow());
					}
				});
		return arr.length;
	}

	@Override
	public ContentForm findContentFormByCcid(long ccid) {
		List<ContentForm> list = getJdbcTemplate().query("select * from esite.es_content_form where catid=?", new Object[] { ccid }, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ContentForm cf = new ContentForm();
				cf.setId(rs.getLong("id"));
				cf.setOwnerid(rs.getLong("ownerid"));
				cf.setTitle(rs.getString("title"));
				cf.setCatid(rs.getLong("catid"));
				return cf;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	private List<ContentFormMapping> getBaseMapping() {
		List<ContentFormMapping> list = new ArrayList<ContentFormMapping>();
		ContentFormMapping c1 = new ContentFormMapping();
		c1.setColtype("S");
		c1.setMapping("title");
		c1.setName("title");
		c1.setRow(0);
		c1.setStype("T");
		list.add(c1);
		ContentFormMapping c2 = new ContentFormMapping();
		c2.setColtype("S");
		c2.setMapping("detail");
		c2.setName("content");
		c2.setRow(0);
		c2.setStype("A");
		list.add(c2);
		return list;
	}

	@Override
	public ContentForm findContentFormById(long formid) {
		List<ContentForm> list = getJdbcTemplate().query("select * from esite.es_content_form where id=?", new Object[] { formid }, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ContentForm cf = new ContentForm();
				cf.setId(rs.getLong("id"));
				cf.setOwnerid(rs.getLong("ownerid"));
				cf.setTitle(rs.getString("title"));
				cf.setCatid(rs.getLong("catid"));
				return cf;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public List<ContentFormMapping> findMappingById(long formid) {
		List<ContentFormMapping> list = getJdbcTemplate().query("select * from esite.es_content_form_mapping where formid=? order by id asc", new Object[] { formid },
				new RowMapper() {
					@Override
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						ContentFormMapping model = new ContentFormMapping();
						model.setId(rs.getLong("id"));
						model.setFormid(rs.getLong("formid"));
						model.setRow(rs.getInt("row"));
						model.setName(rs.getString("name"));
						model.setMapping(rs.getString("mapping"));
						model.setColtype(rs.getString("coltype"));
						model.setStype(rs.getString("stype"));
						model.setDefaultvalue(rs.getString("defaultvalue"));
						return model;

					}
				});
		return list;
	}

	@Override
	public int addFormMapping(final long formid, final List<ContentFormMapping> list) {
		int[] arr = getJdbcTemplate().batchUpdate("insert into es_content_form_mapping (formid,name,mapping,coltype,stype,row,defaultvalue) values(?,?,?,?,?,?,?)",
				new BatchPreparedStatementSetter() {
					@Override
					public int getBatchSize() {
						return list.size();
					}

					@Override
					public void setValues(PreparedStatement ps, int index) throws SQLException {
						int i = 1;
						ContentFormMapping cfm = list.get(index);
						ps.setLong(i++, formid);
						ps.setString(i++, cfm.getName());
						ps.setString(i++, cfm.getMapping());
						ps.setString(i++, cfm.getColtype());
						ps.setString(i++, cfm.getStype());
						ps.setInt(i++, cfm.getRow());
						ps.setString(i++, cfm.getDefaultvalue());
					}
				});
		return arr.length;
	}

	@Override
	public int updateMappingClean(long formid) {
		return getJdbcTemplate().update("delete from es_content_form_mapping where formid=?", new Object[] { formid });
	}

	@Override
	public List<ContentFormMapping> findMappingOrderByRow(long formid) {
		List<ContentFormMapping> list = getJdbcTemplate().query("select * from esite.es_content_form_mapping where formid=? order by row=0,row asc", new Object[] { formid },
				new RowMapper() {
					@Override
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						ContentFormMapping model = new ContentFormMapping();
						model.setId(rs.getLong("id"));
						model.setFormid(rs.getLong("formid"));
						model.setRow(rs.getInt("row"));
						model.setName(rs.getString("name"));
						model.setMapping(rs.getString("mapping"));
						model.setColtype(rs.getString("coltype"));
						model.setStype(rs.getString("stype"));
						model.setDefaultvalue(rs.getString("defaultvalue"));
						return model;

					}
				});
		return list;
	}

	@Override
	public long addContentFormSub(final ContentFormRecord record) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement("insert into esite.es_content_form_record (formid,title,detail,f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14,f15,f16,f17,f18,f19,f20,createtime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())", new String[]
				{ "id" });
				int i=1;
				ps.setLong(i++, record.getFormid());
				ps.setString(i++, record.getTitle());
				ps.setString(i++, record.getDetail());
				ps.setString(i++, record.getF1());
				ps.setString(i++, record.getF2());
				ps.setString(i++, record.getF3());
				ps.setString(i++, record.getF4());
				ps.setString(i++, record.getF5());
				ps.setString(i++, record.getF6());
				ps.setString(i++, record.getF7());
				ps.setString(i++, record.getF8());
				ps.setString(i++, record.getF9());
				ps.setString(i++, record.getF10());
				ps.setString(i++, record.getF11());
				ps.setString(i++, record.getF12());
				ps.setString(i++, record.getF13());
				ps.setString(i++, record.getF14());
				ps.setString(i++, record.getF15());
				ps.setString(i++, record.getF16());
				ps.setString(i++, record.getF17());
				ps.setString(i++, record.getF18());
				ps.setString(i++, record.getF19());
				ps.setString(i++, record.getF20());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
	   return id;
	}

	@Override
	public List<ContentFormRecord> findRecordByFormid(long formid) {
		return getJdbcTemplate().query("select * from esite.es_content_form_record where formid=? order by id desc", new Object[] { formid }, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ContentFormRecord r = new ContentFormRecord();
				r.setId(rs.getLong("id"));
				r.setFormid(rs.getLong("formid"));
				r.setTitle(rs.getString("title"));
				r.setDetail(rs.getString("detail"));
				r.setF1(rs.getString("f1"));
				r.setF2(rs.getString("f2"));
				r.setF3(rs.getString("f3"));
				r.setF4(rs.getString("f4"));
				r.setF5(rs.getString("f5"));
				r.setF6(rs.getString("f6"));
				r.setF7(rs.getString("f7"));
				r.setF8(rs.getString("f8"));
				r.setF9(rs.getString("f9"));
				r.setF10(rs.getString("f10"));
				r.setF11(rs.getString("f11"));
				r.setF12(rs.getString("f12"));
				r.setF13(rs.getString("f13"));
				r.setF14(rs.getString("f14"));
				r.setF15(rs.getString("f15"));
				r.setF16(rs.getString("f16"));
				r.setF17(rs.getString("f17"));
				r.setF18(rs.getString("f18"));
				r.setF19(rs.getString("f19"));
				r.setF20(rs.getString("f20"));
				return r;
			}
		});
	}

	@Override
	public int deletFormRecord(long recordid) {
		return getJdbcTemplate().update("delete from esite.es_content_form_record where id=?", new Object[] { recordid });
	}

	@Override
	public ContentFormRecord findRecordById(long recordid) {
		List<ContentFormRecord> list = getJdbcTemplate().query("select * from esite.es_content_form_record where id=?", new Object[] { recordid }, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ContentFormRecord r = new ContentFormRecord();
				r.setId(rs.getLong("id"));
				r.setFormid(rs.getLong("formid"));
				r.setTitle(rs.getString("title"));
				r.setDetail(rs.getString("detail"));
				r.setF1(rs.getString("f1"));
				r.setF2(rs.getString("f2"));
				r.setF3(rs.getString("f3"));
				r.setF4(rs.getString("f4"));
				r.setF5(rs.getString("f5"));
				r.setF6(rs.getString("f6"));
				r.setF7(rs.getString("f7"));
				r.setF8(rs.getString("f8"));
				r.setF9(rs.getString("f9"));
				r.setF10(rs.getString("f10"));
				r.setF11(rs.getString("f11"));
				r.setF12(rs.getString("f12"));
				r.setF13(rs.getString("f13"));
				r.setF14(rs.getString("f14"));
				r.setF15(rs.getString("f15"));
				r.setF16(rs.getString("f16"));
				r.setF17(rs.getString("f17"));
				r.setF18(rs.getString("f18"));
				r.setF19(rs.getString("f19"));
				r.setF20(rs.getString("f20"));
				r.setLbsid(rs.getLong("lbsid"));
				return r;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public int updateFormRecord(ContentFormRecord record) {
		return getJdbcTemplate()
				.update("update esite.es_content_form_record set formid=?,title=?,detail=?,f1=?,f2=?,f3=?,f4=?,f5=?,f6=?,f7=?,f8=?,f9=?,f10=?,f11=?,f12=?,f13=?,f14=?,f15=?,f16=?,f17=?,f18=?,f19=?,f20=? where id=?",
						new Object[] { record.getFormid(), record.getTitle(), record.getDetail(), record.getF1(), record.getF2(), record.getF3(), record.getF4(), record.getF5(),
								record.getF6(), record.getF7(), record.getF8(), record.getF9(), record.getF10(), record.getF11(), record.getF12(), record.getF13(),
								record.getF14(), record.getF15(), record.getF16(), record.getF17(), record.getF18(), record.getF19(), record.getF20(),record.getId() });
	}
	
	@Override
	public void updateLbsid(long lbsid, long recordid)
	{
		getJdbcTemplate().update("update esite.es_content_form_record set lbsid=? where id=?", new Object[]{lbsid,recordid});
	}
}
