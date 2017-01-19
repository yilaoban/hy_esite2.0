
package project.caidan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import project.caidan.dao.ICaiDanNewsDao;
import project.caidan.model.CDNews;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.model.ContentNew;

public class CDNewsDaoImpl extends AbstractDao implements ICaiDanNewsDao
{

	@Override
	public List<CDNews> findList(long owner, String accountType, int start, int size)
	{
		return getJdbcTemplate().query("select * from caidan.cd_news r join esite.es_content_news n on r.newsid=n.id where r.owner=? and r.type=? and n.status!='DEL' order by r.type desc,r.idx desc limit ?,?", new Object[]
		{ owner, accountType2int(accountType), start, size }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDNews cdn = new CDNews();
				cdn.setId(rs.getLong("r.id"));
				cdn.setNewsid(rs.getLong("r.newsid"));
				cdn.setIdx(rs.getInt("r.idx"));
				cdn.setOwner(rs.getLong("r.owner"));
				
				
				
				ContentNew cn = new ContentNew();
				cn.setId(rs.getLong("n.id"));
				cn.setOwnerid(rs.getLong("n.ownerid"));
				cn.setCatid(rs.getLong("n.catid"));
				cn.setTitle(rs.getString("n.title"));
				cn.setContent(rs.getString("n.content"));
				cn.setStatus(rs.getString("n.status"));
				cn.setBimgurl(rs.getString("n.bimgurl"));
				cn.setSimgurl(rs.getString("n.simgurl"));
				cn.setLinkurl(rs.getString("n.linkurl"));
				cn.setIdx(rs.getInt("n.idx"));
				cn.setShortdesc(rs.getString("n.shortdesc"));
				cn.setCreatetime(rs.getTimestamp("n.createtime"));
				cn.setUpdatetime(rs.getTimestamp("n.updatetime"));
				cn.setIslink(rs.getString("n.islink"));
				cn.setStartTime(rs.getTimestamp("n.startTime"));
				cn.setEndTime(rs.getTimestamp("n.endTime"));
				cn.setAuthor(rs.getString("n.author"));
				cn.setPublishtime(rs.getTimestamp("n.publishtime"));
				cn.setSource(rs.getString("n.source"));
				cn.setFatie(rs.getString("n.fatie"));
				cn.setInitialZan(rs.getInt("n.initialZan"));
				cn.setInitialRead(rs.getInt("n.initialRead"));
				cn.setFatherid(rs.getLong("n.fatherid"));
				
				cdn.setNews(cn);
				return cdn;
			}
		});
	}

	@Override
	public int findTotal(long owner, String accountType)
	{
		return getJdbcTemplate().queryForInt("select count(id) from caidan.cd_news where owner=? and type=?", new Object[]
		{ owner, accountType2int(accountType) });
	}

	@Override
	public void saveNews(long ownerid, String accountType, long pid)
	{
		final int idx = findMaxIdx(ownerid, accountType);
		getJdbcTemplate().update("insert into caidan.cd_news (type,newsid,owner,idx) values(?,?,?,?)", new Object[]
		{ accountType2int(accountType), pid, ownerid, idx+1 });
	}

	@Override
	public int findMaxIdx(long owner, String accountType)
	{
		String sql = "select Max(idx) from caidan.cd_news where owner=? and type=? ";
		return getJdbcTemplate().queryForInt(sql, new Object[]
		{ owner, accountType2int(accountType) });
	}
	
	
	@Override
	public CDNews findcdNews(long contentid, long owner)
	{
		List<CDNews> list=getJdbcTemplate().query("select * from caidan.cd_news where owner=? and newsid=? limit 1", new Object[]{owner,contentid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDNews cdn = new CDNews();
				cdn.setId(rs.getLong("id"));
				cdn.setNewsid(rs.getLong("newsid"));
				cdn.setIdx(rs.getInt("idx"));
				cdn.setOwner(rs.getLong("owner"));
				cdn.setType(rs.getInt("type"));
				return cdn;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public CDNews findFrontcdNews(int idx, int type, long owner)
	{
		List<CDNews> list=getJdbcTemplate().query("select * from caidan.cd_news r join esite.es_content_news n on r.newsid=n.id where r.idx>? and r.type=? and r.owner=? and n.status!='DEL' order by r.idx asc limit 1", new Object[]{idx,type,owner}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDNews cdn = new CDNews();
				cdn.setId(rs.getLong("id"));
				cdn.setNewsid(rs.getLong("newsid"));
				cdn.setIdx(rs.getInt("idx"));
				cdn.setOwner(rs.getLong("owner"));
				cdn.setType(rs.getInt("type"));
				return cdn;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public CDNews findNextcdNews(int idx, int type, long owner)
	{
		List<CDNews> list=getJdbcTemplate().query("select * from caidan.cd_news r join esite.es_content_news n on r.newsid=n.id where r.idx<? and r.type=? and r.owner=? and n.status!='DEL' order by r.idx desc limit 1", new Object[]{idx,type,owner}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CDNews cdn = new CDNews();
				cdn.setId(rs.getLong("id"));
				cdn.setNewsid(rs.getLong("newsid"));
				cdn.setIdx(rs.getInt("idx"));
				cdn.setOwner(rs.getLong("owner"));
				cdn.setType(rs.getInt("type"));
				return cdn;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	
	@Override
	public int updatecdNews(CDNews current)
	{
		return getJdbcTemplate().update("update caidan.cd_news set idx=? where id=? ", new Object[]{current.getIdx(),current.getId()});
	}
	
	
	@Override
	public List<CDNews> findForCaiMinPage(long owner, int start, int size)
	{
		return getJdbcTemplate().query("select * from caidan.cd_news r join esite.es_content_news n on r.newsid=n.id where r.owner=?  and n.status!='DEL' and r.type=0 order by r.idx desc limit ?,?", new Object[]
		{ owner, start, size }, new NewsForPage());
	}
	
	
	@Override
	public List<CDNews> findForStation(long owner,int level, int start, int size)
	{
		return getJdbcTemplate().query("select * from caidan.cd_news r join esite.es_content_news n on r.newsid=n.id where r.owner=?  and n.status!='DEL' and r.type>=? order by r.type desc,r.idx desc limit ?,?", new Object[]
		{ owner, level, start, size }, new NewsForPage());
	}
	
	class NewsForPage implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			CDNews cdn = new CDNews();
			cdn.setId(rs.getLong("r.id"));
			cdn.setNewsid(rs.getLong("r.newsid"));
			cdn.setIdx(rs.getInt("r.idx"));
			cdn.setOwner(rs.getLong("r.owner"));
			
			
			
			ContentNew cn = new ContentNew();
			cn.setId(rs.getLong("n.id"));
			cn.setOwnerid(rs.getLong("n.ownerid"));
			cn.setCatid(rs.getLong("n.catid"));
			cn.setTitle(rs.getString("n.title"));
			cn.setContent(rs.getString("n.content"));
			cn.setStatus(rs.getString("n.status"));
			cn.setBimgurl(rs.getString("n.bimgurl"));
			cn.setSimgurl(rs.getString("n.simgurl"));
			cn.setLinkurl(rs.getString("n.linkurl"));
			cn.setIdx(rs.getInt("n.idx"));
			cn.setShortdesc(rs.getString("n.shortdesc"));
			cn.setCreatetime(rs.getTimestamp("n.createtime"));
			cn.setUpdatetime(rs.getTimestamp("n.updatetime"));
			cn.setIslink(rs.getString("n.islink"));
			cn.setStartTime(rs.getTimestamp("n.startTime"));
			cn.setEndTime(rs.getTimestamp("n.endTime"));
			cn.setAuthor(rs.getString("n.author"));
			cn.setPublishtime(rs.getTimestamp("n.publishtime"));
			cn.setSource(rs.getString("n.source"));
			cn.setFatie(rs.getString("n.fatie"));
			cn.setInitialZan(rs.getInt("n.initialZan"));
			cn.setInitialRead(rs.getInt("n.initialRead"));
			cn.setFatherid(rs.getLong("n.fatherid"));
			
			cdn.setNews(cn);
			return cdn;
		}
	}
	
	private int accountType2int(String accounttype)
	{
		if ("ALZ".equals(accounttype))
		{
			return 2;
		} else if ("PRZ".equals(accounttype))
		{
			return 1;
		} else
		{
			return 0;
		}
	}

}
