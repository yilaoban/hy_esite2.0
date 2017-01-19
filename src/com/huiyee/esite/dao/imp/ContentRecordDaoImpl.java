package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.IContentRecordDao;
import com.huiyee.esite.model.ContentRecord;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.xc.model.XcInviteRecord;


public class ContentRecordDaoImpl implements IContentRecordDao
{
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int saveContentRecord(VisitUser vu, ContentRecord contentRecord, String ip, String terminal)
	{
		String sql = "insert into esite.es_content_record(title,hydesc,longdesc,enid,type,uid,utype,createtime,ip,terminal,source) values(?,?,?,?,?,?,?,now(),?,?,?)";
		Object[] params = {contentRecord.getTitle(),contentRecord.getHydesc(),contentRecord.getLongdesc(),contentRecord.getEnid(),contentRecord.getType(),contentRecord.getUid(),contentRecord.getUtype(),ip,terminal,contentRecord.getSource()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int findTotalContentReport(String type,int utype,long uid,String title)
	{//0-sina 1-weixin
		String sql = "";
		if (utype == 1)
		{
			sql = "select count(r.id) from esite.es_content_record r join es_wx_user u on r.uid = u.id where r.utype = ? and r.type = ?";
		}
		else if (utype == 0)
		{
			sql = "select count(r.id) from es_content_record r join es_sina_user u on r.uid = u.wbuid where r.utype = ? and r.type = ?";
		}
		List<Object> list = new ArrayList<Object>();
		list.add(utype);list.add(type);
		if(uid > 0){
			sql = sql + " and r.uid = ?";
			list.add(uid);
		}
		if(StringUtils.isNotBlank(title)){
			sql = sql + " and r.title = ?";
			list.add(title);
		}
		return jdbcTemplate.queryForInt(sql, list.toArray());
		
	}

	@Override
	public List<ContentRecord> findContentRecordList(String type,int utype,long uid,String title, int start, int size)
	{
		String sql = "";
		if (utype == 1)
		{
			sql = "select r.*,u.nickname from es_content_record r join es_wx_user u on r.uid = u.id where r.utype = ? and r.type = ? ";
		}
		else if (utype == 0)
		{
			sql = "select r.*,u.nickname from es_content_record r join es_sina_user u on r.uid = u.wbuid where r.utype = ? and r.type = ? ";
		}
		List<Object> list = new ArrayList<Object>();
		list.add(utype);list.add(type);
		if(uid > 0){
			sql = sql + " and r.uid = ?";
			list.add(uid);
		}
		if(StringUtils.isNotBlank(title)){
			sql = sql + " and r.title = ?";
			list.add(title);
		}
		sql = sql + " order by r.createtime desc limit ?,?";
		list.add(start);list.add(size);
		return jdbcTemplate.query(sql, list.toArray(), new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ContentRecord ir = new ContentRecord();
				ir.setId(rs.getLong("r.id"));
				ir.setNickname(rs.getString("u.nickname"));
				ir.setTitle(rs.getString("r.title"));
				ir.setHydesc(rs.getString("r.hydesc"));
				ir.setLongdesc(rs.getString("r.longdesc"));
				ir.setEnid(rs.getLong("r.enid"));
				ir.setType(rs.getString("type"));
				ir.setUid(rs.getLong("r.uid"));
				ir.setUtype(rs.getInt("r.utype"));
				ir.setCreatetime(rs.getTimestamp("r.createtime"));
				ir.setIp(rs.getString("r.ip"));
				ir.setTerminal(rs.getString("r.terminal"));
				ir.setSource(rs.getString("r.source"));
				return ir;
			}

		});
	}

	@Override
	public ContentRecord findContentRecordById(long id)
	{
		String sql = "select r.* from es_content_record r where id = ?";
		Object[] params = {id};
		List<ContentRecord> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ContentRecord ir = new ContentRecord();
				ir.setId(rs.getLong("r.id"));
				ir.setTitle(rs.getString("r.title"));
				ir.setHydesc(rs.getString("r.hydesc"));
				ir.setLongdesc(rs.getString("r.longdesc"));
				ir.setEnid(rs.getLong("r.enid"));
				ir.setType(rs.getString("type"));
				ir.setUid(rs.getLong("r.uid"));
				ir.setUtype(rs.getInt("r.utype"));
				ir.setCreatetime(rs.getTimestamp("r.createtime"));
				ir.setIp(rs.getString("r.ip"));
				ir.setTerminal(rs.getString("r.terminal"));
				ir.setSource(rs.getString("r.source"));
				ir.setRemark(rs.getString("r.remark"));
				ir.setStatus(rs.getString("r.status"));
				return ir;
			}
			
		});
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateMicroRecord(long id, ContentRecord cr)
	{
		String sql = "update es_content_record set updatetime = now(),status=?,remark=? where id = ?";
		Object[] params = {cr.getStatus(),cr.getRemark(),id};
		return jdbcTemplate.update(sql, params);
	}

}
