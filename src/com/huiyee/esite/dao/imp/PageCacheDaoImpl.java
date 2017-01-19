package com.huiyee.esite.dao.imp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IPageCacheDao;
import com.huiyee.esite.model.Activity;
import com.huiyee.esite.model.PageCache;

public class PageCacheDaoImpl extends AbstractDao implements IPageCacheDao {
	private String SAVE_MAP = "insert into es_page_cache (pageid,cache,updatetime) values (?,?,now()) ON DUPLICATE KEY UPDATE cache = ? ,updatetime=now()";

	public int saveMap(long pageId, byte[] bs) {
		return getJdbcTemplate().update(SAVE_MAP, new Object[] { pageId, bs, bs });
	}

	private static final String GET_MAP = "select cache from es_page_cache where pageid= ? ";

	public Map<String, Object> getMap(long pageid) {
		List<Map<String, Object>> list = getJdbcTemplate().query(GET_MAP, new Object[] { pageid }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Blob blob = rs.getBlob("cache");
				ByteArrayInputStream bai = (ByteArrayInputStream) blob.getBinaryStream();
				ObjectInputStream ois = null;
				try {
					ois = new ObjectInputStream(bai);
					Map<String, Object> result = (Map<String, Object>) ois.readObject();
					return result;
				} catch (Exception e) {
					e.printStackTrace();
				}
				finally{
					if(bai != null ){
						try {
							bai.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(ois != null ){
						try {
							ois.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				return null;
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	private static final String SAVE_PAGE_HTML = "insert into es_page_cache(pageid,html) values(?,?) ON DUPLICATE KEY UPDATE html = ?";
	@Override
	public int savePageHtml(long pageid,String sTotalString) {
		return getJdbcTemplate().update(SAVE_PAGE_HTML, new Object[] { pageid,sTotalString,sTotalString});
	}
	
	private static final String FIND_PAGE_CACHE_BY_PAGEID = "select html from es_page_cache where pageid = ?";
	@Override
	public PageCache findPageCacheByPageid(long pageid) {
		Object[] params={pageid};
		List<PageCache> list = getJdbcTemplate().query(FIND_PAGE_CACHE_BY_PAGEID,params , new PageCacheRowMapper());
		if(list != null &&list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	class PageCacheRowMapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			PageCache p = new PageCache();
			p.setHtml(rs.getString("html"));
			return p;
		}
		
	}
}
