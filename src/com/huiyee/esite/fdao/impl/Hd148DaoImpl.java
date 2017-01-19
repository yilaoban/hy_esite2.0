package com.huiyee.esite.fdao.impl;

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
import com.huiyee.esite.fdao.IHd148Dao;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentPicture;
import com.huiyee.esite.model.FeaturePiclist;
import com.huiyee.esite.model.Picture;

public class Hd148DaoImpl extends AbstractDao implements IHd148Dao
{

	private static final String SAVE_FETRUE_PICLIST = "insert into esite.es_feature_piclist (pageid) values(?)";
    public long saveFeturePicture(final long pageid) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
                PreparedStatement ps = arg0.prepareStatement(SAVE_FETRUE_PICLIST, new String[] { "id" });
                int i = 1;
                ps.setLong(i++, pageid);
                return ps;
            }
        }, keyHolder);
        long id = keyHolder.getKey().intValue();
        return id;
    }
    
	@Override
	public FeaturePiclist findCatidByFid(long fid)
	{
		String sql = "select * from es_feature_piclist where id = ?";
		Object[] params = {fid};
		List<FeaturePiclist> list  =getJdbcTemplate().query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				FeaturePiclist l = new FeaturePiclist();
				l.setCategoryid(rs.getLong("categoryid"));
				return l;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<ContentCategory> findContentCategoryListByOwner(long ownerid)
	{		String sql = "select * from es_content_category cc where cc.ownerid= ? and cc.type = 'P' and cc.status != 'DEL'";
	Object[] params = {ownerid};
	return getJdbcTemplate().query(sql, params, new RowMapper(){
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			ContentCategory cc= new ContentCategory();
			cc.setId(rs.getLong("cc.id"));
			cc.setName(rs.getString("cc.name"));
			return cc;
		}
	});}

	@Override
	public List<ContentPicture> findPicListByCatid(long catid)
	{
		String sql = "select * from es_content_category cc join es_content_pic n on cc.id = n.catid where cc.id = ? and n.status != 'DEL'";
		Object[] params = {catid};
		return getJdbcTemplate().query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ContentPicture cn = new ContentPicture();
				cn.setId(rs.getLong("n.id"));
				cn.setOwnerid(rs.getLong("n.ownerid"));
				cn.setCatid(rs.getLong("n.catid"));
				cn.setTitle(rs.getString("n.title"));
				cn.setStatus(rs.getString("n.status"));
				cn.setLinkurl(rs.getString("n.linkurl"));
				cn.setTag(rs.getString("n.tag"));
				cn.setImgurl(rs.getString("n.imgurl"));
				cn.setCreatetime(rs.getTimestamp("n.createtime"));
				cn.setUpdatetime(rs.getTimestamp("n.updatetime"));
				cn.setIdx(rs.getInt("n.idx"));
				return cn;
			}
			
		});
	}

	@Override
	public void updateFeaturePiclistCatidByFid(long fid, long catid)
	{
		String sql = "update es_feature_piclist set categoryid = ? where id = ?";
		Object[] parms = {catid,fid};
		getJdbcTemplate().update(sql, parms);
	}

	private static final String DELETE_ALL_RELATION = "update es_feature_piclist_pic set status='DEL' where plid= ? ";
	@Override
	public void deleteAllRelation(long fid)
	{
		getJdbcTemplate().update(DELETE_ALL_RELATION, new Object[] { fid });
	}
	
	private static final String FIND_IDX_BY_FID = "select max(idx) from es_feature_piclist_pic where plid = ? and status != 'DEL' ";
	@Override
	public int findIdxByfid(long fid)
	{
		return getJdbcTemplate().queryForInt(FIND_IDX_BY_FID, new Object[] { fid });
	}

	private static final String ADD_RELATION = "insert into es_feature_piclist_pic(plid,pid,idx,status) values (?,?,?,'CMP') on duplicate key update status ='CMP',idx=? ";
	@Override
	public void addReation(long fid, long pid, int idx)
	{
		Object[] parms = {fid,pid,idx,idx};
		getJdbcTemplate().update(ADD_RELATION, parms);
	}

	private static final String FIND_PIC_BY_ID="select * from esite.es_feature_piclist f join esite.es_content_pic cp on f.categoryid=cp.catid where f.id=? and cp.status = 'CMP' ";
	@Override
	public List<Picture> findPictureById(long id) {
		Object[] params={id};
		List<Picture> list = getJdbcTemplate().query(FIND_PIC_BY_ID, params, new PictureListRowmapper());
		return list;
	}
	
	class PictureListRowmapper implements RowMapper{

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Picture p=new Picture();
            p.setId(rs.getLong("cp.id"));
            p.setTitle(rs.getString("cp.title"));
            p.setImgurl(rs.getString("cp.imgurl"));
            p.setIdx(rs.getInt("cp.idx"));
            p.setLinkurl(rs.getString("cp.linkurl"));
            p.setTag(rs.getString("cp.tag"));
            return p;
        }
    }

}
