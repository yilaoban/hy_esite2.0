package com.huiyee.interact.xc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.google.gson.Gson;
import com.huiyee.esite.model.Site;
import com.huiyee.interact.xc.dto.XcSiftDto;
import com.huiyee.interact.xc.model.CheckinConfig;
import com.huiyee.interact.xc.model.CommentConfig;
import com.huiyee.interact.xc.model.InviteConfig;
import com.huiyee.interact.xc.model.LotteryConfig;
import com.huiyee.interact.xc.model.Xc;
import com.huiyee.interact.xc.model.XcCheckinRecord;
import com.huiyee.interact.xc.model.XcCjRecord;
import com.huiyee.interact.xc.model.XcCommentRecord;
import com.huiyee.interact.xc.model.XcExport;
import com.huiyee.interact.xc.model.XcInviteRecord;
import com.huiyee.interact.xc.model.XcSite;

public class XcLotteryDao implements IXcLotteryDao
{
	private JdbcTemplate jdbcTemplate;
	Gson gson = new Gson();

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findTotalByOwnerId(long ownerid)
	{
		return jdbcTemplate.queryForInt("select count(*) from es_interact_xc where owner=? and status !='DEL'", new Object[]
		{ ownerid });
	}

	@Override
	public List<Xc> findXcList(long ownerid, int start, int size)
	{
		String sql = "select * from  es_interact_xc where owner=? and status !='DEL' order by id desc limit ?,?";
		Object[] params =
		{ ownerid, start, size };
		return jdbcTemplate.query(sql, params, new XcRowMapper());
	}

	@Override
	public List<XcSite> findXcSite(long xcid){
		String sql = "select * from es_interact_xc_site xc join es_site s on xc.siteid = s.id where xc.xcid = ?";
		Object[] params = { xcid };
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				XcSite xcs = new XcSite();
				xcs.setId(rs.getLong("xc.id"));
				xcs.setName(rs.getString("s.name"));
				xcs.setSiteid(rs.getLong("xc.siteid"));
				xcs.setXcid(rs.getLong("xc.xcid"));
				xcs.setType(rs.getString("xc.type"));
				return xcs;
			}
			
		});
	}
	
	class XcRowMapper implements RowMapper
	{
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException
		{
			Xc xc = new Xc();
			xc.setId(rs.getLong("id"));
			xc.setTitle(rs.getString("title"));
			xc.setOwner(rs.getLong("owner"));
			xc.setNeedcheckin(rs.getString("needcheckin"));
			xc.setNeedcomment(rs.getString("needcomment"));
			xc.setNeedinvite(rs.getString("needinvite"));
			xc.setNeedlottery(rs.getString("needlottery"));
			xc.setCheckinconfig(gson.fromJson(rs.getString("checkinconfig"), CheckinConfig.class));
			xc.setCommentconfig(gson.fromJson(rs.getString("commentconfig"), CommentConfig.class));
			xc.setInviteconfig(gson.fromJson(rs.getString("inviteconfig"), InviteConfig.class));
			xc.setLotteryconfig(gson.fromJson(rs.getString("lotteryconfig"), LotteryConfig.class));
			xc.setCreatetime(rs.getTimestamp("createtime"));
			xc.setAptid(rs.getLong("aptid"));
			return xc;
		}
	}

	private static final String SAVE_XC_LOTTERY = "insert into esite.es_interact_xc(title,owner,createtime,inviteconfig,checkinconfig,commentconfig,lotteryconfig) values(?,?,now(),?,?,?,?)";

	@Override
	public long saveXcLottery(final Xc xc, final long ownerid)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(SAVE_XC_LOTTERY, new String[]
				{ "id" });
				int i = 1;
				ps.setString(i++, xc.getTitle());
				ps.setLong(i++, ownerid);
				ps.setString(i++, xc.getInviteconfig()!=null?gson.toJson(xc.getInviteconfig()):null);
				ps.setString(i++, xc.getCheckinconfig()!=null?gson.toJson(xc.getCheckinconfig()):null);
				ps.setString(i++, xc.getCommentconfig()!=null?gson.toJson(xc.getCheckinconfig()):null);
				ps.setString(i++, xc.getLotteryconfig()!=null?gson.toJson(xc.getLotteryconfig()):null);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	private static final String UPDATE_XC_LOTTERY = "update es_interact_xc set lotteryconfig = ? where id = ?";

	@Override
	public int updateXcLottery(long id, String lotteryconfig)
	{
		Object[] params =
		{ lotteryconfig, id };
		return jdbcTemplate.update(UPDATE_XC_LOTTERY, params);
	}

	private static final String FIND_XC_BY_ID = "select * from es_interact_xc where id = ?";

	@Override
	public Xc findXcById(long id)
	{
		Object[] params =
		{ id };
		List<Xc> list = jdbcTemplate.query(FIND_XC_BY_ID, params, new XcRowMapper());
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateXcLottery(long id, Xc xc)
	{
		String sql = "update es_interact_xc set needinvite=?,needcheckin=?,needcomment=?,needlottery=?,inviteconfig=?,checkinconfig=?,commentconfig=?,lotteryconfig=? where id = ?";
		Object[] params =
		{ xc.getNeedinvite(),xc.getNeedcheckin(), xc.getNeedcomment(), xc.getNeedlottery(), gson.toJson(xc.getInviteconfig()), gson.toJson(xc.getCheckinconfig()), gson.toJson(xc.getCommentconfig()), gson.toJson(xc.getLotteryconfig()), id };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int delXcLottery(long id)
	{
		String sql = "update es_interact_xc set status = 'DEL' where id = ? ";
		Object[] params =
		{ id };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int findTotalInviteRecord(long id, String name, XcSiftDto siftDto)
	{
		List<Object> list=new ArrayList<Object>();
		String sql = "select count(r.id) from es_interact_xc_invite_record r join es_wx_user u on r.uid = u.id where r.xcid = ? and r.utype = 1";
		list.add(id);
		if(!"A".equals(siftDto.getIsChecked())){
			sql+=" and r.checked=? ";
			list.add(siftDto.getIsChecked());
		}
		if(StringUtils.isNotEmpty(name)){
			sql+=" and u.nickname like ? ";
			list.add("%"+name+"%");
		}
		return jdbcTemplate.queryForInt(sql, list.toArray());
	}

	@Override
	public List<XcInviteRecord> findInviteRecordList(long id,int start, int size, String name, XcSiftDto siftDto)
	{
		List<Object> list=new ArrayList<Object>();
		String sql = "select r.*,nickname from es_interact_xc_invite_record r join es_wx_user u on r.uid = u.id where r.xcid = ? and r.utype = 1 ";
		list.add(id);
		if(!"A".equals(siftDto.getIsChecked())){
			sql+=" and r.checked=?";
			list.add(siftDto.getIsChecked());
		}
		if(StringUtils.isNotEmpty(name)){
			sql+=" and u.nickname like ? ";
			list.add("%"+name+"%");
		}
		sql+=" order by r.createtime desc limit ?,?";
		list.add(start);
		list.add(size);
		return jdbcTemplate.query(sql, list.toArray(), new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				XcInviteRecord ir = new XcInviteRecord();
				ir.setId(rs.getLong("r.id"));
				ir.setNickname(rs.getString("nickname"));
				ir.setPageid(rs.getLong("r.pageid"));
				ir.setXcid(rs.getLong("r.xcid"));
				ir.setUid(rs.getLong("r.uid"));
				ir.setUtype(rs.getInt("r.utype"));
				ir.setCreatetime(rs.getTimestamp("r.createtime"));
				ir.setIp(rs.getString("r.ip"));
				ir.setTerminal(rs.getString("r.terminal"));
				ir.setSource(rs.getString("r.source"));
				ir.setChecked(rs.getString("checked"));
				return ir;
			}

		});
	}

	@Override
	public int findTotalCheckinRecord(long id, int utype)
	{
		String sql = "";
		if (utype == 1)
		{
			sql = "select count(r.id) from es_interact_xc_checkin_record r join es_wx_user u on r.uid = u.id where r.xcid = ? and r.utype = ? and r.status=1";
		}
		else if (utype == 0)
		{
			sql = "select count(r.id) from es_interact_xc_checkin_record r join es_sina_user u on r.uid = u.wbuid where r.xcid = ? and r.utype = ? and r.status=1";
		}
		Object[] params =
		{ id, utype };
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<XcCheckinRecord> findCheckinRecordList(long id, int utype, int start, int size)
	{
		String sql = "";
		if (utype == 1)
		{
			sql = "select r.*,nickname from es_interact_xc_checkin_record r join es_wx_user u on r.uid = u.id where r.xcid = ? and r.utype = ? and r.status=1 order by r.id desc limit ?,?";
		}
		else if (utype == 0)
		{
			sql = "select r.*,nickname from es_interact_xc_checkin_record r join es_sina_user u on r.uid = u.wbuid where r.xcid = ? and r.utype = ? and  r.status=1 order by r.id desc limit ?,?";
		}
		Object[] params =
		{ id, utype, start, size };
		return jdbcTemplate.query(sql, params, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				XcCheckinRecord ir = new XcCheckinRecord();
				ir.setId(rs.getLong("r.id"));
				ir.setNickname(rs.getString("nickname"));
				ir.setPageid(rs.getLong("r.pageid"));
				ir.setXcid(rs.getLong("r.xcid"));
				ir.setCreatetime(rs.getTimestamp("r.createtime"));
				ir.setUid(rs.getLong("r.uid"));
				ir.setUtype(rs.getInt("r.utype"));
				ir.setIp(rs.getString("r.ip"));
				ir.setTerminal(rs.getString("r.terminal"));
				ir.setSource(rs.getString("r.source"));
				return ir;
			}

		});
	}

	@Override
	public int findTotalCommentRecord(long id, int utype)
	{
		String sql = "";
		if (utype == 1)
		{
			sql = "select count(r.id) from es_interact_xc_comment_record r join es_wx_user u on r.uid = u.id where r.xcid = ? and r.utype = ?";
		}
		else if (utype == 0)
		{
			sql = "select count(r.id) from es_interact_xc_comment_record r join es_sina_user u on r.uid = u.wbuid where r.xcid = ? and r.utype = ?";
		}
		Object[] params =
		{ id, utype };
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<XcCommentRecord> findCommentRecordList(long id, int utype, int start, int size)
	{
		String sql = "";
		if (utype == 1)
		{
			sql = "select r.*,nickname from es_interact_xc_comment_record r join es_wx_user u on r.uid = u.id where r.xcid = ? and r.utype = ? order by r.createtime desc limit ?,?";
		}
		else if (utype == 0)
		{
			sql = "select r.*,nickname from es_interact_xc_comment_record r join es_sina_user u on r.uid = u.wbuid where r.xcid = ? and r.utype = ? order by r.createtime desc limit ?,?";
		}
		Object[] params =
		{ id, utype, start, size };
		return jdbcTemplate.query(sql, params, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				XcCommentRecord ir = new XcCommentRecord();
				ir.setId(rs.getLong("r.id"));
				ir.setNickname(rs.getString("nickname"));
				ir.setPageid(rs.getLong("r.pageid"));
				ir.setXcid(rs.getLong("r.xcid"));
				ir.setUid(rs.getLong("r.uid"));
				ir.setUtype(rs.getInt("r.utype"));
				ir.setContent(rs.getString("r.content"));
				ir.setImg(rs.getString("r.img"));
				ir.setStatus(rs.getString("r.status"));
				ir.setCreatetime(rs.getTimestamp("r.createtime"));
				ir.setIp(rs.getString("r.ip"));
				ir.setTerminal(rs.getString("r.terminal"));
				ir.setSource(rs.getString("r.source"));
				return ir;
			}

		});
	}

	@Override
	public int findTotalLotteryRecord(long id, int utype,String nickname,String top)
	{
		String sql = "";
		List<Object> list=new ArrayList<Object>();
		list.add(id);
		list.add(utype);
		if (utype == 1)
		{
			sql = "select count(r.id) from es_interact_xc_cj_record r join es_wx_user u on r.uid = u.id where r.xcid = ? and r.utype = ?";
		}
		else if (utype == 0)
		{
			sql = "select count(r.id) from es_interact_xc_cj_record r join es_sina_user u on r.uid = u.wbuid where r.xcid = ? and r.utype = ?";
		}
		if(nickname!=null&&nickname.trim().length()>0){
			sql += " and u.nickname like ? ";
			list.add("%"+nickname+"%");
		}
		if(top!=null&&!"A".equals(top)){
			sql += " and top=? ";
			list.add(top);
		}
		return jdbcTemplate.queryForInt(sql, list.toArray());
	}

	@Override
	public List<XcCjRecord> findLotteryRecordList(long id, int utype, int start, int size,String nickname,String top)
	{
		String sql = "";
		List<Object> list=new ArrayList<Object>();
		list.add(id);
		list.add(utype);
		if (utype == 1)
		{
			sql = "select r.*,nickname from es_interact_xc_cj_record r join es_wx_user u on r.uid = u.id where r.xcid = ? and r.utype = ? ";
			if(nickname!=null&&nickname.trim().length()>0){
				sql += " and u.nickname like ? ";
				list.add("%"+nickname+"%");
			}
			if(top!=null&&!"A".equals(top)){
				sql += " and top=? ";
				list.add(top);
			}
			sql +=" order by r.createtime desc limit ?,?";
		}
		else if (utype == 0)
		{
			sql = "select r.*,nickname from es_interact_xc_cj_record r join es_sina_user u on r.uid = u.wbuid where r.xcid = ? and r.utype = ? ";
			if(nickname!=null&&nickname.trim().length()>0){
				sql += " and u.nickname like ? ";
				list.add("%"+nickname+"%");
			}
			if(top!=null&&!"A".equals(top)){
				sql += " and top=? ";
				list.add(top);
			}
			sql +=" order by r.createtime desc limit ?,?";
		}
		list.add(start);
		list.add(size);
		return jdbcTemplate.query(sql, list.toArray(), new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				XcCjRecord ir = new XcCjRecord();
				ir.setId(rs.getLong("r.id"));
				ir.setNickname(rs.getString("nickname"));
				ir.setPageid(rs.getLong("r.pageid"));
				ir.setXcid(rs.getLong("r.xcid"));
				ir.setStartnum(rs.getInt("r.startnum"));
				ir.setUid(rs.getLong("r.uid"));
				ir.setUtype(rs.getInt("r.utype"));
				ir.setJoinnum(rs.getInt("r.joinnum"));
				ir.setTop(rs.getString("r.top"));
				ir.setCreatetime(rs.getTimestamp("r.createtime"));
				ir.setIp(rs.getString("r.ip"));
				ir.setTerminal(rs.getString("r.terminal"));
				ir.setSource(rs.getString("r.source"));
				return ir;
			}

		});
	}

	@Override
	public long updateCommentRecordStatus(long id, String status)
	{
		String sql = "update es_interact_xc_comment_record r set r.status = ? where r.id = ?";
		Object[] params =
		{ status, id };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int findTotalAudit(long id, int utype)
	{
		String sql = "";
		if (utype == 1)
		{
			sql = "select count(r.id) from es_interact_xc_comment_record r join es_wx_user u on r.uid = u.id where r.xcid = ? and r.utype = ? and r.status = 'EDT' order by r.createtime desc";
		}
		else if (utype == 0)
		{
			sql = "select count(r.id) from es_interact_xc_comment_record r join es_sina_user u on r.uid = u.wbuid where r.xcid = ? and r.utype = ? and r.status = 'EDT' order by r.createtime desc";
		}
		Object[] params =
		{ id, utype };
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<XcCommentRecord> findAuditList(long id, int utype, int start, int size)
	{
		String sql = "";
		if (utype == 1)
		{
			sql = "select r.*,nickname from es_interact_xc_comment_record r join es_wx_user u on r.uid = u.id where r.xcid = ? and r.utype = ? and r.status = 'EDT' order by r.createtime desc limit ?,?";
		}
		else if (utype == 0)
		{
			sql = "select r.*,nickname from es_interact_xc_comment_record r join es_sina_user u on r.uid = u.wbuid where r.xcid = ? and r.utype = ? and r.status = 'EDT' order by r.createtime desc limit ?,?";
		}
		Object[] params =
		{ id, utype, start, size };
		return jdbcTemplate.query(sql, params, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				XcCommentRecord ir = new XcCommentRecord();
				ir.setId(rs.getLong("r.id"));
				ir.setNickname(rs.getString("nickname"));
				ir.setPageid(rs.getLong("r.pageid"));
				ir.setXcid(rs.getLong("r.xcid"));
				ir.setUid(rs.getLong("r.uid"));
				ir.setUtype(rs.getInt("r.utype"));
				ir.setContent(rs.getString("r.content"));
				ir.setImg(rs.getString("r.img"));
				ir.setStatus(rs.getString("r.status"));
				ir.setCreatetime(rs.getTimestamp("r.createtime"));
				ir.setIp(rs.getString("r.ip"));
				ir.setTerminal(rs.getString("r.terminal"));
				ir.setSource(rs.getString("r.source"));
				return ir;
			}

		});
	}

	@Override
	public List<XcExport> findWinner(long id)
	{
		return jdbcTemplate.query("select * from es_interact_xc_cj_record where xcid=? and top='Y' order by id desc", new Object[]
		{ id }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				XcExport xc = new XcExport();
				xc.setCreatetime(rs.getTimestamp("createtime"));
				xc.setXcid(rs.getLong("xcid"));
				xc.setJoinnum(rs.getInt("joinnum"));
				xc.setStartnum(rs.getInt("startnum"));
				xc.setUid(rs.getLong("uid"));
				xc.setUtype(rs.getInt("utype"));
				return xc;
			}
		});
	}

	@Override
	public List<Site> findSiteList(long ownerid)
	{
		String sql = "select id,name from es_site where ownerid = ? and status != 'DEL'";
		Object[] params = {ownerid};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Site s = new Site();
				s.setId(rs.getLong("id"));
				s.setName(rs.getString("name"));
				return s;
			}
		});
	}

	@Override
	public long saveXcSite(long xcid, long siteid, String type)
	{
		String sql = "insert into es_interact_xc_site(siteid,xcid,type) values(?,?,?)";
		Object[] params = {siteid,xcid,type};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateXcName(Xc xc)
	{
		String sql = "update es_interact_xc set title = ? where id = ?";
		Object[] params = {xc.getTitle(),xc.getId()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateBigScreenSite(long id, long siteid)
	{
		String sql = "update es_interact_xc_site set siteid = ? where id = ?";
		Object[] params = {siteid,id};
		return jdbcTemplate.update(sql, params);
	}
	
	@Override
	public long findXcFidByPage(long pageId)
	{
		List<Long> list=jdbcTemplate.query("select id from es_feature_interact_xc where pageid=?", new Object[]{pageId},new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("id");
			}
		});
		return list.size()==1?list.get(0):0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Xc getXcById(long xcid) {
		Object[] param = { xcid };
		List<Xc> list = jdbcTemplate.query("SELECT * FROM esite.es_interact_xc WHERE id=?", param, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Xc xc = new Xc();
				xc.setId(rs.getLong("id"));
				xc.setTitle(rs.getString("title"));
				xc.setOwner(rs.getLong("owner"));
				xc.setCreatetime(rs.getTimestamp("createtime"));
				xc.setCheckinconfig(gson.fromJson(rs.getString("checkinconfig"), CheckinConfig.class));
				xc.setCommentconfig(gson.fromJson(rs.getString("commentconfig"), CommentConfig.class));
				xc.setInviteconfig(gson.fromJson(rs.getString("inviteconfig"), InviteConfig.class));
				xc.setLotteryconfig(gson.fromJson(rs.getString("lotteryconfig"), LotteryConfig.class));
				xc.setNeedcheckin(rs.getString("needcheckin"));
				xc.setNeedcomment(rs.getString("needcomment"));
				xc.setNeedinvite(rs.getString("needinvite"));
				xc.setNeedlottery(rs.getString("needlottery"));
				return xc;
			}
		});

		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public void updateXcWithApt(long newXc, long newApt)
	{
		jdbcTemplate.update("update es_interact_xc set aptid=? where id=?", new Object[]{newApt,newXc});
	}

}
