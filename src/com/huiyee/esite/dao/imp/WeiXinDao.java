package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import com.huiyee.esite.dao.IWeiXinDao;
import com.huiyee.esite.model.WeiXinPage;
import com.huiyee.esite.model.WeixinApp;
import com.huiyee.esite.model.WxOA;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.EmojiFilter;
import com.huiyee.weixin.model.WxMp;
import com.huiyee.weixin.model.WxPageShow;

@SuppressWarnings("unchecked")
public class WeiXinDao implements IWeiXinDao 
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public WeiXinPage findWXPage( long id) {
		
		List<WeiXinPage> ls = jdbcTemplate.query("select * from esite.es_wx_page_show where id=?", new Object[] { id }, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WeiXinPage wx = new WeiXinPage();
				wx.setId(rs.getLong("id"));
				wx.setPageid(rs.getLong("pageid"));
				wx.setInfoed(rs.getString("infoed"));
				wx.setOwnerid(rs.getLong("ownerid"));
				wx.setMpid(rs.getLong("mpid"));
				wx.setUpdateseconds(rs.getInt("updateseconds"));
				return wx;
			}

		});
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}
	
	@Override
	public WeiXinPage findWXPageByPageid( long pageid) {
		
		List<WeiXinPage> ls = jdbcTemplate.query("select * from esite.es_wx_page_show where pageid=?", new Object[] { pageid }, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WeiXinPage wx = new WeiXinPage();
				wx.setId(rs.getLong("id"));
				wx.setPageid(rs.getLong("pageid"));
				wx.setInfoed(rs.getString("infoed"));
				wx.setOwnerid(rs.getLong("ownerid"));
				wx.setMpid(rs.getLong("mpid"));
				wx.setUpdateseconds(rs.getInt("updateseconds"));
				return wx;
			}

		});
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}
	
	
	@Override
	public WeiXinPage findWXPageByPS( long pageid) {
		
		List<WeiXinPage> ls = jdbcTemplate.query("SELECT s.* FROM esite.es_wx_page_show s,esite.es_wx_ps ps,esite.es_page p WHERE p.id=? and s.id=ps.wxpid and p.siteid=ps.siteid", new Object[] { pageid }, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WeiXinPage wx = new WeiXinPage();
				wx.setId(rs.getLong("id"));
				wx.setPageid(rs.getLong("pageid"));
				wx.setInfoed(rs.getString("infoed"));
				wx.setOwnerid(rs.getLong("ownerid"));
				wx.setMpid(rs.getLong("mpid"));
				wx.setUpdateseconds(rs.getInt("updateseconds"));
				return wx;
			}

		});
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}
	
	
	@Override
	public WeiXinPage findWXPageByPP( long pageid) {
		
		List<WeiXinPage> ls = jdbcTemplate.query("SELECT s.* FROM esite.es_wx_page_show s,esite.es_wx_pp p WHERE p.pageid=? and s.id=p.wxpid", new Object[] { pageid }, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WeiXinPage wx = new WeiXinPage();
				wx.setId(rs.getLong("id"));
				wx.setPageid(rs.getLong("pageid"));
				wx.setInfoed(rs.getString("infoed"));
				wx.setOwnerid(rs.getLong("ownerid"));
				wx.setMpid(rs.getLong("mpid"));
				wx.setUpdateseconds(rs.getInt("updateseconds"));
				return wx;
			}

		});
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	@Override
	public void addWXUser(final WxUser wp) {
		try {
			jdbcTemplate.update("insert ignore into esite.es_wx_user(subscribe,mpid,openid,nickname,sex,province,city,country,headimgurl,privilege,updatetime) values(?,?,?,?,?,?,?,?,?,?,?)", new Object[] {
					wp.getSubscribe(),wp.getMpid(), wp.getOpenid(), wp.getNickname(), wp.getSex(), wp.getProvince(), wp.getCity(), wp.getCountry(), wp.getHeadimgurl(), wp.getPrivilege(), wp.getUpdatetime() });
		} catch (Exception e) {
			wp.setNickname(EmojiFilter.getCutString(wp.getNickname()));
			jdbcTemplate.update("insert ignore into esite.es_wx_user(subscribe,mpid,openid,nickname,sex,province,city,country,headimgurl,privilege,updatetime) values(?,?,?,?,?,?,?,?,?,?,?)", new Object[] {
					wp.getSubscribe(),wp.getMpid(), wp.getOpenid(), wp.getNickname(), wp.getSex(), wp.getProvince(), wp.getCity(), wp.getCountry(), wp.getHeadimgurl(), wp.getPrivilege(), wp.getUpdatetime() });
		}

	}

	@Override
	public long findWXUserId( String openid) {
		List<Long> ls = jdbcTemplate.query("select * from esite.es_wx_user where  openid=?", new Object[] { openid }, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getLong("id");
			}

		});
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return 0;
	}

	private static final String SAVE_WX_PAGE_SHOW = "insert into es_wx_page_show(appid,pageid,pic,infoed,createtime) values(?,?,?,?,now())";

	@Override
	public long saveWxPageShow(final long pageid, final long appid, final String pic, final String infoed) {
		KeyHolder keyholder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_WX_PAGE_SHOW, new String[] { "id" });
				int i = 1;
				ps.setLong(i++, appid);
				ps.setLong(i++, pageid);
				ps.setString(i++, pic);
				ps.setString(i++, infoed);
				return ps;
			}
		}, keyholder);
		return keyholder.getKey().longValue();
	}

	private static final String UPDATE_PAGE_PIC = "update es_page set pic = ? where id = ?";

	@Override
	public int updatePagePic(long pageid, String pic) {
		Object[] params = { pic, pageid };
		return jdbcTemplate.update(UPDATE_PAGE_PIC, params);
	}

	private static final String UPDATE_PAGE_SUB_WX = "update es_page set subweixin = ? where id = ?";

	@Override
	public int updatePagesubWx(long pageid, String status) {
		Object[] params = { status, pageid };
		return jdbcTemplate.update(UPDATE_PAGE_SUB_WX, params);
	}

	private static final String FIND_PAGE_PIC = "select pic from es_wx_page_show where id = ?";

	@Override
	public String findPagePic(long wxapageid) {
		try {
			return (String) jdbcTemplate.queryForObject(FIND_PAGE_PIC, new Object[] { wxapageid }, String.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public long updateWxPageShow(long showid, String pic, String infoed) {
		String sql = "update es_wx_page_show set pic=?,infoed=? where id=? ";
		return jdbcTemplate.update(sql, new Object[] { pic, infoed, showid });
	}

	@Override
	public int findWeixinappByOwneridTotal(long ownerid) {
		String sql = "select count(id) from es_wx_open_auth where ownerid = ? ";
		return jdbcTemplate.queryForInt(sql, new Object[] { ownerid });
	}

	public List<WeixinApp> findWeixinappByOwnerid(final long ownerid, int start, int size) {
		String sql = "select o.*,m.nick_name,m.appid as authorizer_appid from es_wx_open_auth o,es_wx_mp m where o.mp_id=m.id and o.ownerid = ? limit ?,?";
		Object[] params = { ownerid, start, size };
		return jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				WeixinApp app = new WeixinApp();
				app.setId(rs.getLong("id"));
				app.setName(rs.getString("nick_name"));
				app.setOwnerid(ownerid);
				app.setAppkey(rs.getString("authorizer_appid"));
				app.setAppsecret(rs.getString("authorizer_access_token"));
				return app;
			}

		});

	}

	@Override
	public String findNickNameById(long wbuid) {
		List<String> list = jdbcTemplate.query("select nickname from esite.es_wx_user where id=?", new Object[] { wbuid }, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString("nickname");
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updatePageAddressStatus(long pageid) {
		String sql = "update es_page_address set status='DEL' where pageid=? and name='н╒пе'";
		return jdbcTemplate.update(sql, new Object[] { pageid });
	}

	@Override
	public int findWeixinCount(long pageid) {
		String sql = "select count(id) from es_wx_page_show where pageid=?";
		return jdbcTemplate.queryForInt(sql, new Object[] { pageid });
	}

	@Override
	public List<WeiXinPage> findWeiXinPageList(long pageid) {
		String sql = "select id from es_wx_page_show where pageid=?";
		return jdbcTemplate.query(sql, new Object[] { pageid }, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WeiXinPage wx = new WeiXinPage();
				wx.setId(rs.getLong("id"));
				return wx;
			}
		});
	}

	@Override
	public void updateWXUser(long id, WxUser wp) {
		String sql = "update esite.es_wx_user set subscribe=?,nickname=?,sex=?,province=?,city=?,country=?,headimgurl=?,privilege=?,updatetime=now() where id=?";
		try {
			jdbcTemplate.update(sql, new Object[] { wp.getSubscribe(),wp.getNickname(), wp.getSex(), wp.getProvince(), wp.getCity(), wp.getCountry(), wp.getHeadimgurl(), wp.getPrivilege(), id });
		} catch (Exception e) {
			wp.setNickname(EmojiFilter.getCutString(wp.getNickname()));
			jdbcTemplate.update(sql, new Object[] { wp.getSubscribe(),wp.getNickname(), wp.getSex(), wp.getProvince(), wp.getCity(), wp.getCountry(), wp.getHeadimgurl(), wp.getPrivilege(), id });

		}
	}

	@Override
	public WxUser findWxUserByOpenid(String openid) {
		List<WxUser> list = jdbcTemplate.query("select * from esite.es_wx_user where openid=?", new Object[] { openid }, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxUser wx = new WxUser();
				wx.setId(rs.getLong("id"));
				wx.setNickname(rs.getString("nickname"));
				wx.setSex(rs.getInt("sex"));
				wx.setMpid(rs.getLong("mpid"));
				wx.setCity(rs.getString("city"));
				wx.setCountry(rs.getString("country"));
				wx.setHeadimgurl(rs.getString("headimgurl"));
				wx.setOpenid(rs.getString("openid"));
				wx.setPrivilege(rs.getString("privilege"));
				wx.setSubscribe(rs.getInt("subscribe"));
				wx.setProvince(rs.getString("province"));
				wx.setUpdatetime(rs.getTimestamp("updatetime"));
				return wx;
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public WxUser getWxUserById(long id) {
		List<WxUser> ls = jdbcTemplate.query("select * from esite.es_wx_user where  id=?", new Object[] { id }, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxUser wx = new WxUser();
				wx.setId(rs.getLong("id"));
				wx.setNickname(rs.getString("nickname"));
				wx.setMpid(rs.getLong("mpid"));
				wx.setCity(rs.getString("city"));
				wx.setCountry(rs.getString("country"));
				wx.setHeadimgurl(rs.getString("headimgurl"));
				wx.setOpenid(rs.getString("openid"));
				wx.setPrivilege(rs.getString("privilege"));
				wx.setProvince(rs.getString("province"));
				wx.setSex(rs.getInt("sex"));
				wx.setUpdatetime(rs.getTimestamp("updatetime"));
				return wx;
			}

		});
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	private static final String FIND_WX_PAGE_SHOW_BY_SITEGROUPID="select * from es_wx_page_show where sitegroupid = ? limit 1";
	@Override
	public WxPageShow findWxPageShowBySitegroupId(long sgid)
	{
		Object[] param={sgid};
		List<WxPageShow> list = jdbcTemplate.query(FIND_WX_PAGE_SHOW_BY_SITEGROUPID, param, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				WxPageShow wps = new WxPageShow();
				wps.setId(rs.getLong("id"));
				wps.setTitle(rs.getString("title"));
				wps.setDescription(rs.getString("description"));
				wps.setPic(rs.getString("pic"));
				return wps;
			}
			
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public WxPageShow findWxPageShowBySiteId(long siteid)
	{
		List<WxPageShow> list=jdbcTemplate.query("SELECT s.* FROM esite.es_wx_page_show s,esite.es_wx_ps ps WHERE ps.wxpid=s.id and ps.siteid=?", new Object[]{siteid}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				WxPageShow wps = new WxPageShow();
				wps.setId(rs.getLong("id"));
				wps.setTitle(rs.getString("title"));
				wps.setDescription(rs.getString("description"));
				wps.setPic(rs.getString("pic"));
				return wps;
			}
		});
		return list.size()>0?list.get(0):null;
	}

	@Override
	public WeiXinPage findWXPageBySitegroup(long pageid)
	{
		Object[] params = { pageid };
		String sql = "SELECT s.* FROM esite.es_wx_page_show s,esite.es_page p,esite.es_site t WHERE s.sitegroupid=t.sitegroupid and p.siteid=t.id and p.id=? ORDER BY s.id DESC";
		List<WeiXinPage> list = jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WeiXinPage wx = new WeiXinPage();
				wx.setId(rs.getLong("id"));
				wx.setPageid(rs.getLong("pageid"));
				wx.setInfoed(rs.getString("infoed"));
				wx.setOwnerid(rs.getLong("ownerid"));
				wx.setUpdateseconds(rs.getInt("updateseconds"));
				return wx;
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public WxMp findWxMpById(long id)
	{
		List<WxMp> ls = jdbcTemplate.query("select * from esite.es_wx_mp where id=?", new Object[] { id }, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxMp wx = new WxMp();
				wx.setAccess_token(rs.getString("access_token"));
				wx.setAlias(rs.getString("alias"));
				wx.setAppid(rs.getString("appid"));
				wx.setCert_path(rs.getString("cert_path"));
				wx.setExpires_in(rs.getLong("expires_in"));
				wx.setHead_img(rs.getString("head_img"));
				wx.setId(rs.getLong("id"));
				wx.setKey(rs.getString("key"));
				wx.setMch_id(rs.getString("mch_id"));
				wx.setNick_name(rs.getString("nick_name"));
				wx.setQrcode_url(rs.getString("qrcode_url"));
				wx.setService_type_info(rs.getInt("service_type_info"));
				wx.setUser_name(rs.getString("user_name"));
				wx.setVerify_type_info(rs.getInt("verify_type_info"));
				wx.setAppsecret(rs.getString("appsecret"));
				wx.setToken(rs.getString("token"));
				return wx;
			}

		});
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	@Override
	public WxMp findWxMpByOwner(long owner)
	{
		List<WxMp> ls = jdbcTemplate.query("select * from esite.es_wx_mp where owner=? and for_esite=1 order by id desc limit 1", new Object[] { owner }, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxMp wx = new WxMp();
				wx.setOwner(rs.getLong("owner"));
				wx.setAccess_token(rs.getString("access_token"));
				wx.setAlias(rs.getString("alias"));
				wx.setAppid(rs.getString("appid"));
				wx.setCert_path(rs.getString("cert_path"));
				wx.setExpires_in(rs.getLong("expires_in"));
				wx.setHead_img(rs.getString("head_img"));
				wx.setId(rs.getLong("id"));
				wx.setKey(rs.getString("key"));
				wx.setMch_id(rs.getString("mch_id"));
				wx.setNick_name(rs.getString("nick_name"));
				wx.setQrcode_url(rs.getString("qrcode_url"));
				wx.setService_type_info(rs.getInt("service_type_info"));
				wx.setUser_name(rs.getString("user_name"));
				wx.setVerify_type_info(rs.getInt("verify_type_info"));
				wx.setAppsecret(rs.getString("appsecret"));
				wx.setToken(rs.getString("token"));
				return wx;
			}

		});
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	@Override
	public WxOA findWxOA(long mpid)
	{
		List<WxOA> ls = jdbcTemplate.query("select au.*,ap.appid,ap.access_token,ap.expires from esite.es_wx_open_auth au,esite.es_wx_app ap where au.mp_id=? and au.app_id=ap.id order by ap.type desc limit 1", new Object[] { mpid }, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxOA wx = new WxOA();
				wx.setId(rs.getLong("au.id"));
				wx.setAccess_token(rs.getString("au.authorizer_access_token"));
				wx.setExpires_in(rs.getLong("au.expires_in"));
				wx.setRefresh_token(rs.getString("au.authorizer_refresh_token"));
				wx.setThird_appid(rs.getString("ap.appid"));
				wx.setThird_access_token(rs.getString("ap.access_token"));
				wx.setThird_expires_in(rs.getLong("ap.expires"));
				return wx;
			}

		});
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}
	
	@Override
	public int saveToken(long mpid, String access_token, long expires_in) {
		Object[] params = { access_token, expires_in,mpid};
		String sql = "UPDATE esite.es_wx_mp SET access_token=?,expires_in=? WHERE id=?";
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int saveAuthToken(WxOA woa) 
	{
		Object[] params = { woa.getAccess_token(), woa.getExpires_in(), woa.getRefresh_token(), woa.getId() };
		String sql = "UPDATE esite.es_wx_open_auth SET authorizer_access_token=?,expires_in=?,authorizer_refresh_token=? WHERE id=?";
		return jdbcTemplate.update(sql, params);
	}
}
