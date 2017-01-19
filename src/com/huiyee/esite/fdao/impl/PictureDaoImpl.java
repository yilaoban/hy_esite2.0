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
import com.huiyee.esite.fdao.IPictureDao;
import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.Picture;
public class PictureDaoImpl extends AbstractDao implements IPictureDao {

	private static final String FIND_PIC_BY_ID="select pp.id vlpid, pp.idx, cp.id,cp.title,cp.imgurl from esite.es_feature_piclist_pic pp join esite.es_content_pic cp on pp.pid=cp.id where pp.status!='DEL' and pp.plid = ? order by pp.idx,cp.id asc";
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
            p.setId(rs.getLong("id"));
            p.setTitle(rs.getString("title"));
            p.setImgurl(rs.getString("imgurl"));
            p.setVlpid(rs.getLong("vlpid"));
            p.setIdx(rs.getInt("idx"));
            return p;
        }
    }
	
	class PictureRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		    Picture p=new Picture();
		    p.setId(rs.getLong("id"));
		    p.setTitle(rs.getString("title"));
		    p.setImgurl(rs.getString("imgurl"));
			return p;
		}
	}
	private static final String FIND_PIC_BY_OWNER="select id, imgurl,title from esite.es_content_pic  where ownerid = ? and status!='DEL' order by id desc";
	    @Override
	public List<Picture> findPictureByOwner(long owner) {
	        Object[] params={owner};
	        List<Picture> list = getJdbcTemplate().query(FIND_PIC_BY_OWNER, params, new PictureRowmapper());
	        return list;
	}

//	private static final String UPDATE_NOTICE="update esite.es_feature_notice set title = ?,content = ? ,imgurl = ?,CountdownTime = ?,CountdownWeekday = ? where id = ?";
//	@Override
//	public int updateVideo(Video video) {
//		Object[] params = {
//				notice.getTitle(),
//				notice.getContent(),
//				notice.getImgurl(),
//				notice.getHour() + ":" + notice.getMinute() + ":"
//						+ notice.getSecond(), notice.getCountdownWeekday(),
//				notice.getId() };
//		return getJdbcTemplate().update(UPDATE_NOTICE, params);
//	}
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
    private static final String SAVE_FETRUE_PIC_LIST = "insert into esite.es_feature_piclist_pic (plid,pid,status,idx) values(?,?,'NOR',?) on duplicate key update status='NOR',idx=?";

    public int saveFeturePicListPic(final long pvid,final long pid,final long idx) {
        Object[] params ={ pvid, pid,idx,idx};
        return getJdbcTemplate().update(SAVE_FETRUE_PIC_LIST, params);
    }

//    @Override
//    public int findFetureVideoListVideo(long lvid, long vid) {
//        Object[] args={lvid,vid};
//        String sql = "select count(id) esite.es_feature_videolist_video where lvid=? and vid=?";
//        return getJdbcTemplate().queryForInt(sql,args);
//    }
    
    
    @Override
    public int deleteFeaturePictureList(String ids) {
        String sql="update esite.es_feature_piclist_pic set status='DEL' where id in ("+ids+")";
        return getJdbcTemplate().update(sql);
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
	

	@Override
	public int updatePageBlockRelationByRelationid(long relationid, String json) {
		String sql = "update es_page_block_relation r set r.json = ? where r.id = ?";
		Object[] params = { json,relationid};
		return getJdbcTemplate().update(sql, params);
	}
}
