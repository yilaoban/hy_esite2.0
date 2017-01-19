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

import com.huiyee.esite.dao.IHyUserDao;
import com.huiyee.esite.dto.HuDetail;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.JsonStringUtil;
import com.huiyee.interact.bbs.model.BBSUser;
import com.huiyee.interact.bbs.model.SMS;

public class HyUserDao implements IHyUserDao
{
private JdbcTemplate jdbcTemplate;

public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
{
	this.jdbcTemplate = jdbcTemplate;
}


@Override
public long findIdByCookie(String cookie,long owner)
{
	List<Long> ls=jdbcTemplate.query("select id from esite.es_hy_cookie where cookie=? and owner=?", new Object[]{cookie,owner},new RowMapper(){

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			return rs.getLong("id");
		}
		
	});
	if(ls!=null&& ls.size()>0){
		return ls.get(0);
	}
	return 0;
}

@Override
public long saveCookieUserByCookie(final String cookie,final long owner)
{
	KeyHolder keyholder = new GeneratedKeyHolder();
	jdbcTemplate.update(new PreparedStatementCreator()
	{
		@Override
		public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
		{
			PreparedStatement ps = arg0.prepareStatement("insert into esite.es_hy_cookie (cookie,owner) values(?,?)", new String[]
			{ "id" });
			int i = 1;
			ps.setString(i++, cookie);
			ps.setLong(i++, owner);
			return ps;
		}

	}, keyholder);
	return keyholder.getKey().longValue();
}

@Override
public HyUser findHyuserByUP(String username,String password, long owner)
{
	List<HyUser> ls=jdbcTemplate.query("select * from esite.es_hy_user where username=? and password=? and owner=?", new Object[]{username,password,owner},new RowMapper(){

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			HyUser hu=new HyUser();
			hu.setId(rs.getLong("id"));
			hu.setOwner(rs.getLong("owner"));
			hu.setPassword(rs.getString("password"));
			hu.setUsername(rs.getString("username"));
			hu.setWxuid(rs.getLong("wxuid"));
			hu.setEmail(rs.getString("email"));
			hu.setHuDetail((HuDetail) JsonStringUtil.String2Obj(rs.getString("hudetail"), HuDetail.class));
			hu.setImg(rs.getString("img"));
			hu.setNickname(rs.getString("nickname"));
			hu.setTelphone(rs.getString("telphone"));
			hu.setLevelid(rs.getLong("levelid"));
			return hu;
		}
		
	});
	if(ls!=null&& ls.size()>0){
		return ls.get(0);
	}
	return null;
}

@Override
public long saveHyUserByUP(final String username,final String password,final long owner,final String telphone,final String email,final String nickname,final String hudetail,final String img,final VisitUser vu)
{
	KeyHolder keyholder = new GeneratedKeyHolder();
	jdbcTemplate.update(new PreparedStatementCreator()
	{
		@Override
		public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
		{
			PreparedStatement ps = arg0.prepareStatement("insert into esite.es_hy_user (username,password,owner,wxuid,wbuid,telphone,email,nickname,hudetail,img) values(?,?,?,?,?,?,?,?,?,?)", new String[]
			{ "id" });
			int i = 1;
			ps.setString(i++, username);
			ps.setString(i++, password);
			ps.setLong(i++, owner);
			ps.setLong(i++,vu.getWxuid());
			ps.setLong(i++,vu.getWbuid());
			ps.setString(i++, telphone);
			ps.setString(i++, email);
			ps.setString(i++, nickname);
			ps.setString(i++, hudetail);
			ps.setString(i++, img);
			return ps;
		}

	}, keyholder);
	return keyholder.getKey().longValue();
}

@Override
public void updateHyUserByHyuid(String username, String password,String telphone,String email,String nickname,String hudetail,String img, long hyuid)
{
	jdbcTemplate.update("update esite.es_hy_user set username=?,password=?,telphone=?,email=?,nickname=?,hudetail=?,img=? where id=?",new Object[]{username,password,telphone,email,nickname,hudetail,img,hyuid} );
}

@Override
public long findIdByWx(long wxuid, long owner)
{
	List<Long> ls=jdbcTemplate.query("select id from esite.es_hy_user where  wxuid=? and owner=?", new Object[]{wxuid,owner},new RowMapper(){

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			return rs.getLong("id");
		}
		
	});
	if(ls!=null&& ls.size()>0){
		return ls.get(0);
	}
	return 0;
}

@Override
public void saveHyUserByWxuid( long wxuid, long owner)
{
	String sql = "insert into esite.es_hy_user (wxuid,owner) values (?,?)";
	Object[] params = {wxuid,owner};
	jdbcTemplate.update(sql, params);
}


@Override
public int findBBSUserByName(String username, long owner)
{
	return jdbcTemplate.queryForInt("select count(id) from esite.es_hy_user where username=? and owner=?", new Object[]{username,owner});
}


@Override
public long findOwnerByForumid(long forumid)
{
	String sql = "select c.owner from hybbs.bbs_category c join hybbs.bbs_forum f on c.id = f.cateid where f.id = ? and f.status != 'DEL'";
	Object[] params = {forumid};
	List<Long> ls = jdbcTemplate.query(sql,params,new RowMapper(){
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			return rs.getLong("c.owner");
		}
	});
	if (ls != null && ls.size() > 0)
	{
		return ls.get(0);
	}
	return 0;
}


@Override
public HyUser findHyUserById(long hyuserid)
{
	String sql = "select * from esite.es_hy_user u left join esite.es_hy_user_level l on l.id=u.levelid where u.id = ?";
	Object[] params = {hyuserid};
	List<HyUser> ls = jdbcTemplate.query(sql,params,new RowMapper(){
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			HyUser u = new HyUser();
			u.setId(rs.getLong("u.id"));
			u.setOwner(rs.getLong("u.owner"));
			u.setPassword(rs.getString("u.password"));
			u.setUsername(rs.getString("u.username"));
			u.setWbuid(rs.getLong("u.wbuid"));
			u.setWxuid(rs.getLong("u.wxuid"));
			u.setNickname(rs.getString("u.nickname"));
			u.setTelphone(rs.getString("u.telphone"));
			u.setBirthday(rs.getDate("u.birthday"));
			u.setGender(rs.getInt("u.gender"));
			u.setLevelid(rs.getLong("u.levelid"));
			u.setLevelname(rs.getString("l.name"));
			return u;
		}
	});
	if (ls != null && ls.size() > 0)
	{
		return ls.get(0);
	}
	return null;
}
@Override
public HyUser findHyUserBywxuid(long wxuid,long owner)
{
	String sql = "select * from esite.es_hy_user  where wxuid=? and owner=?";
	Object[] params =
	{ wxuid ,owner};
	List<HyUser> list = jdbcTemplate.query(sql, params, new RowMapper()
	{
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			HyUser hu=new HyUser();
			hu.setId(rs.getLong("id"));
			hu.setOwner(rs.getLong("owner"));
			hu.setPassword(rs.getString("password"));
			hu.setUsername(rs.getString("username"));
			hu.setWxuid(rs.getLong("wxuid"));
			hu.setEmail(rs.getString("email"));
		//	hu.setHuDetail((HuDetail) JsonStringUtil.String2Obj(rs.getString("hudetail"), HuDetail.class));
			hu.setImg(rs.getString("img"));
			hu.setNickname(rs.getString("nickname"));
			hu.setTelphone(rs.getString("telphone"));
			hu.setBirthday(rs.getDate("birthday"));
			hu.setGender(rs.getInt("gender"));
			hu.setLevelid(rs.getLong("levelid"));
			return hu;
		}

	});
	if (list != null && list.size() > 0)
	{
		return list.get(0);
		
	}
	return null;
}

@Override
public int updateHyUserWbuidById(long wbuid,long hyuserid)
{
	String sql = "update esite.es_hy_user set wbuid = ? where id = ?";
	Object[] params = {wbuid,hyuserid};
	return jdbcTemplate.update(sql, params);
}


@Override
public int updateHyUserWxuidById(long wxuid, long hyuserid)
{
	String sql = "update esite.es_hy_user set wxuid = ? where id = ?";
	Object[] params = {wxuid,hyuserid};
	return jdbcTemplate.update(sql, params);
}


@Override
	public String findNickname(long entityid, long wbuid, long wxuid)
	{
		String sql="";
		Object[] param=null;
		if(entityid!=0){
			sql="select username from es_hy_user where id=?";
			param=new Object[]{entityid};
		}else if(wbuid!=0){
			sql="select username from es_hy_user where wbuid=?";
			param=new Object[]{wbuid};
		}else if(wxuid!=0){
			sql="select username from es_hy_user where wxuid=?";
			param=new Object[]{wxuid};
		}else{
			return null;
		}
		List<String> list=jdbcTemplate.query(sql, param, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getString("username");
			}
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

@Override
public SMS findPPSmsbyOwner(long owner) {
	String sql = "select * from esite.es_sms where owner = ?";
	Object[] params = {owner};
	List<SMS> list = jdbcTemplate.query(sql, params, new RowMapper(){
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			SMS sms = new SMS();
			sms.setId(rs.getLong("id"));
			sms.setOwner(rs.getLong("owner"));
			sms.setUsername(rs.getString("username"));
			sms.setPassword(rs.getString("password"));
			sms.setProductid(rs.getLong("productid"));
			sms.setHydesc(rs.getString("hydesc"));
			return sms;
		}
	});
	if(list != null && list.size()>0){
		return list.get(0);
	}
	return null;
}


@Override
public long saveHyUser(final String username,final String password,final long owner,final BBSUser bs)
{
	KeyHolder keyholder = new GeneratedKeyHolder();
	jdbcTemplate.update(new PreparedStatementCreator()
	{
		@Override
		public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
		{
			PreparedStatement ps = arg0.prepareStatement("insert into esite.es_hy_user (username,password,owner,nickname,telphone,email,img) values(?,?,?,?,?,?,?)", new String[]
			{ "id" });
			int i = 1;
			ps.setString(i++, username);
			ps.setString(i++, password);
			ps.setLong(i++, owner);
			ps.setString(i++, bs.getNickname());
			ps.setString(i++, bs.getTelphone());
			ps.setString(i++, bs.getEmail());
			ps.setString(i++, bs.getImg());
			return ps;
		}

	}, keyholder);
	return keyholder.getKey().longValue();
}


	@Override
	public int updateVipInfo(HyUser hyUser)
	{
		return jdbcTemplate.update("update es_hy_user set username=?,telphone=?,birthday=?,gender=? where wxuid=? and owner=?", new Object[]{hyUser.getUsername(),hyUser.getTelphone(),hyUser.getBirthday(),hyUser.getGender(),hyUser.getWxuid(),hyUser.getOwner()});
	}


	@Override
	public int updateHyUserLevelidById(long levelid, long hyuid) {
		String sql = "update es_hy_user set levelid = ? where id = ?";
		Object[] params = {levelid,hyuid};
		return jdbcTemplate.update(sql, params);
	}
}
