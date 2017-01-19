package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IMaterialDao;
import com.huiyee.esite.model.MaterialCategory;
import com.huiyee.esite.model.MaterialPic;
import com.huiyee.interact.research.model.ResearchRecord;
public class MaterialDaoImpl extends AbstractDao implements IMaterialDao {

	
	class MaterialPicRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			MaterialPic mp=new MaterialPic();
			mp.setId(rs.getLong("id"));
			mp.setName(rs.getString("name"));
			mp.setPath(rs.getString("path"));
			mp.setFid(rs.getLong("fid"));
			mp.setSid(rs.getLong("sid"));
			return mp;
		}
		
	}
	class MaterialCategoryRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			MaterialCategory mp=new MaterialCategory();
			mp.setName(rs.getString("name"));
			mp.setId(rs.getLong("id"));
			return mp;
		}
		
	}

	@Override
	public int findMaterialCount(long fid, long sid)
	{
		StringBuffer buffer=new StringBuffer();
		List<Object> plist=new ArrayList<Object>();
		plist.add(fid);
		if(sid>0){
			buffer.append("select count(id) from es_material_pic where fid=? and sid=?");
			plist.add(sid);
		}else{
			buffer.append("select count(id) from es_material_pic where fid=?");
		}
		return getJdbcTemplate().queryForInt(buffer.toString(),plist.toArray());
	}

	@Override
	public List<MaterialPic> findPicList(long fid,long sid,int start,int size)
	{
		StringBuffer buffer=new StringBuffer();
		List<Object> plist=new ArrayList<Object>();
		plist.add(fid);
		if(sid>0){
			buffer.append("select * from es_material_pic where fid=? and sid=? order by id limit ?,? ");
			plist.add(sid);
		}else{
			buffer.append("select * from es_material_pic where fid=? order by id limit ?,?");
		}
		plist.add(start);
		plist.add(size);
		List<MaterialPic> list= getJdbcTemplate().query(buffer.toString(), plist.toArray(), new MaterialPicRowmapper());
		return list;
	}
	
	@Override
	public List<MaterialCategory> findFCategoryList()
	{
		String sql="select * from es_material_category order by idx  ";
		List<MaterialCategory> list= getJdbcTemplate().query(sql, new MaterialCategoryRowmapper());
		return list;
	}
	@Override
	public List<MaterialCategory> findSCategoryList(long fid)
	{
		String sql="select * from es_material_second_category where fid=? order by idx ";
		Object[] objects=new Object[]{fid};
		List<MaterialCategory> list= getJdbcTemplate().query(sql,objects,new RowMapper(){

			@Override 
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				MaterialCategory mc = new MaterialCategory();
				mc.setCount(rs.getInt("count"));
				mc.setId(rs.getLong("id"));
				mc.setName(rs.getString("name"));
		        return mc;
			}
		});
		return list;
	}

	@Override
	public int findCountById(long id)
	{
		String sql="select count from es_material_second_category where id=?";
		return getJdbcTemplate().queryForInt(sql,new Object[]{id});
	}

	

}
