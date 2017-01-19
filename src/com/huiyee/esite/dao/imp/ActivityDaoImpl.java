package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IActivityDao;
import com.huiyee.esite.model.Activity;
import com.huiyee.esite.model.ActivityLog;
import com.huiyee.esite.model.ActivityReportAnalyse;
import com.huiyee.esite.model.Feature;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.Source;
import com.huiyee.esite.model.Terminal;
import com.huiyee.esite.model.UserInfo;
import com.huiyee.esite.model.VisitLog;

public class ActivityDaoImpl extends AbstractDao implements IActivityDao {

	private static final String FIND_ACTIVITYALL = "select count(a.name) from esite.es_activity a join esite.es_site s on a.siteid = s.id where s.id = ? and a.status !='DEL'";
	@Override
	public int findActivityAll(long id) {
		Object[] params = {id};
		return getJdbcTemplate().queryForInt(FIND_ACTIVITYALL,params);
	}
	
	private static final String SAVE_ACTIVITY = "insert into esite.es_activity(name,siteid,ownerid,createtime) values (?,?,?,now())";
	@Override
	public long saveActivity(final String name,final long siteid,final long ownerid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_ACTIVITY,
						new String[] { "id" });
				ps.setString(1, name);
				ps.setLong(2, siteid);
				ps.setLong(3,ownerid );
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
	
	private static final String SAVE_MODULE_ACTIVITY = "insert into esite.es_activity_module(activityid,mmid,createtime) values (?,?,now())";
	@Override
	public int savaActivityModule(long activityid, long mmid) {
		Object[] params={activityid,mmid};
		return getJdbcTemplate().update(SAVE_MODULE_ACTIVITY,params);
	}
	
	private static final String FIND_ACTIVITY_NAMES = "select * from esite.es_activity s where siteid = ? and status!='DEL'";
	@Override
	public List<Activity> findActivityName(long siteid) {
		Object[] params={siteid};
		return getJdbcTemplate().query(FIND_ACTIVITY_NAMES, params, new ActivityRowmapper());
	}
	
	class ActivityRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Activity activity = new Activity();
			activity.setCreatetime(rs.getTimestamp("createtime"));
			activity.setId(rs.getLong("id"));
			activity.setName(rs.getString("name"));
			activity.setSiteid(rs.getLong("siteid"));
			activity.setStatus(rs.getString("status"));
			return activity;
		}
		
	}
	/**
	 * 根据siteid以及name查找activityid
	 */
	private static final String FIND_ACTIVITYID = "select s.id from esite.es_activity s where siteid = ? and name = ? ";
	@Override
	public List<Activity> findActivityId(String name, long siteid) {
		Object[] params={siteid,name};
		return getJdbcTemplate().query(FIND_ACTIVITYID, params, new ActivityRowmapper());
	}
	
	private static final String FIND_ACTIVITYID_BY_SITEID = "select * from esite.es_activity where siteid = ? and status !='DEL'";
	@Override
	public List<Activity> findActivityId(long siteid) {
		Object[] params={siteid};
		return getJdbcTemplate().query(FIND_ACTIVITYID_BY_SITEID, params, new ActivityRowmapper());
	}
	
	/**
	 * 根据mmid查找对应的模块名字
	 */
	private static final String FIND_MODULE_LIST = "select mm.id,mm.name from esite.es_activity a join esite.es_activity_module am on a.id = am.activityid join esite.es_module_model mm on am.mmid = mm.id where a.id = ?;";
	@Override
	public List<Module> findModuleList(long mmid) {
		Object[] params={mmid};
		return getJdbcTemplate().query(FIND_MODULE_LIST, params, new ModuleRowmapper() );
	}
	
	class ModuleRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Module modules = new Module();
			modules.setId(rs.getLong("id"));
			modules.setName(rs.getString("name"));
			return modules;
		}
	}

	private static final String DELETE_ACTIVITY = "update esite.es_activity set status= 'DEL' where id = ?";
	@Override
	public int deleteActivity(long activityid) {
		Object[] params={activityid};
		return getJdbcTemplate().update(DELETE_ACTIVITY,params);
	}
	
	private static final String UPDATE_ACTIVITY = "update esite.es_activity set name = ? where id = ? ";
	@Override
	public int updateActivity(String name, long activityid) {
		Object[] params={name,activityid};
		return getJdbcTemplate().update(UPDATE_ACTIVITY,params);
	}
	
	private static final String FIND_ACTIVITY_FEATURE="select f.id id,f.name name from esite.es_activity a join esite.es_activity_module am on a.id = am.activityid join esite.es_feature f on am.mmid = f.mmid where a.id = ? and f.type='S'";
	@Override
	public List<Feature> findActivityFeature(long activityid) {
		Object[] params={activityid};
		return getJdbcTemplate().query(FIND_ACTIVITY_FEATURE, params, new FeatureActivityRowmapper());
	}
	
	private static final String FIND_ACTIVITY_NAME = "select * from esite.es_activity where id = ?";
	@Override
	public Activity findActivityNameById(long activityid) {
		Object[] params = { activityid };
		List<Activity> list = getJdbcTemplate().query(FIND_ACTIVITY_NAME, params, new ActivityRowmapper());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	class FeatureActivityRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Feature f = new Feature();
			f.setId(rs.getLong("id"));
			f.setName(rs.getString("name"));
			return f;
		}
		
	}
	
	private static final String FIND_MODULEID_BY_ACTIVITYID="select mmid from esite.es_activity_module where activityid = ?";
	@Override
	public List<Long> findModuleIdByActivityid(long activityid) {
		try {
			Object[] params={activityid};
			return getJdbcTemplate().queryForList(FIND_MODULEID_BY_ACTIVITYID,params,Long.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	private static final String DELETE_ACTIVITY_MODULE ="delete from esite.es_activity_module where activityid = ?";
	@Override
	public int deleteActivityModule(long activityid) {
		Object[] params={activityid};
		return getJdbcTemplate().update(DELETE_ACTIVITY_MODULE, params);
	}
	
	class VisitLogTimeRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			VisitLog visitlog = new VisitLog();
			visitlog.setCreateTime(rs.getTimestamp("createtime"));
			visitlog.setCountSum(rs.getLong("sum"));
			return visitlog;
		}
		
	}
	
	private static final String FIND_ACTIVITYCOUNT_KNOWN = "select count(distinct uid) sum,max(vl.createtime) createtime from esite.es_activity a join es_visit_log vl on a.siteid = vl.siteid where a.id = ? and a.status != 'DEL' and vl.uid is not null";
	@Override
	public VisitLog findUserCount(long activityid) {
		Object[] params={activityid};
		List<VisitLog> list =  getJdbcTemplate().query(FIND_ACTIVITYCOUNT_KNOWN, params,new VisitLogTimeRowmapper());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
		
	private static final String FIND_ACTIVITYCOUNT_UNKNOWN = "select count(vl.id) sum, max(vl.createtime) createtime from esite.es_activity a join es_visit_log_unknown vl on a.siteid = vl.siteid where a.id = ? and a.status != 'DEL' ";
	@Override
	public VisitLog findUnkonwnUserCount(long activityid) {
		Object[] params={activityid};
		List<VisitLog> list = getJdbcTemplate().query(FIND_ACTIVITYCOUNT_UNKNOWN, params,new VisitLogTimeRowmapper());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	private static final String FIND_ACTIVITYLIST_BY_SITEGROUPID = "select a.* from esite.es_site s join esite.es_activity a on s.id = a.siteid and a.status != 'DEL' where s.sitegroupid = ?";
	@Override
	public List<Activity> findActivityListBysitegrouid(long activityid) {
		Object[] params = {activityid};
		return getJdbcTemplate().query(FIND_ACTIVITYLIST_BY_SITEGROUPID, params, new ActivityRowmapper());
	}
	
	class VisitLogRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			VisitLog visitlog = new VisitLog();
			visitlog.setCreateTime(rs.getTimestamp("createtime"));
			visitlog.setIp(rs.getString("ip"));
			return visitlog;
		}
		
	}
	
	class TerminalRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Terminal terminal = new Terminal();
			terminal.setName(rs.getString("terminal"));
			terminal.setCount(rs.getLong("num"));
			return terminal;
		}
	}
	
	private static final String FIND_VISITCOUNT_BY_TERMINAL = "select vl.terminal,count(distinct uid) num from esite.es_activity a join es_visit_log vl on a.siteid = vl.siteid where a.id = ? and vl.uid is not null group by vl.terminal ";
	@Override
	public List<Terminal> findVisitCountByTerminal(long activityid) {
		Object[] params={activityid};
		return getJdbcTemplate().query(FIND_VISITCOUNT_BY_TERMINAL, params,new TerminalRowmapper());
	}
	
	private static final String FIND_ANONVISITCOUNT_BY_TERMINAL = "select vl.terminal,count(vl.id) num from esite.es_activity a join es_visit_log vl on a.siteid = vl.siteid where a.id = ? and vl.uid is null group by vl.terminal";
	@Override
	public List<Terminal> findAnonVisitCountByTerminal(long activityid) {
		Object[] params={activityid};
		return getJdbcTemplate().query(FIND_ANONVISITCOUNT_BY_TERMINAL, params,new TerminalRowmapper());
	}
	
	private static final String FIND_ANONVISIT_BY_TERMINAL = "select count(vl.id) from esite.es_activity a join es_visit_log_unknown vl on a.siteid = vl.siteid where a.id = ? and vl.terminal = ?";
	@Override
	public int findAnonVisitCountByTerminal(String name, long activityid) {
		Object[] params={activityid,name};
		return getJdbcTemplate().queryForInt(FIND_ANONVISIT_BY_TERMINAL, params);
	}

	class SourceRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Source source = new Source();
			source.setName(rs.getString("source"));
			source.setCount(rs.getLong("num"));
			return source;
		}
	}
	
	class ParticipateSourceRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Source source = new Source();
			source.setName(rs.getString("source"));
			source.setCount(rs.getLong("num"));
			source.setCountSum(rs.getLong("mounts"));
			return source;
		}
	}
	
	private static final String FIND_VISITCOUNT_BY_SOURCE = "select vl.source,count(distinct uid) num from esite.es_activity a join es_visit_log vl on a.siteid = vl.siteid where a.id = ? and vl.uid is not null group by vl.source";
	@Override
	public List<Source> findVistitCountBySource(long activityid) {
		Object[] params={activityid};
		return getJdbcTemplate().query(FIND_VISITCOUNT_BY_SOURCE, params,new SourceRowmapper());
	}
	
	private static final String FIND_ANONVISITCOUNT_BY_SOURCE = " select count(vl.id) from esite.es_activity a join es_visit_log_unknown vl on a.siteid = vl.siteid where a.id = ? and vl.source = ?";
	@Override
	public int findAnonVisitCountBySource(String name, long activityid) {
		Object[] params={activityid,name};
		return getJdbcTemplate().queryForInt(FIND_ANONVISITCOUNT_BY_SOURCE, params);
	}
	
	private static final String FIND_PARTICIPATECOUNT_BY_SOURCE = "select al.source,count(distinct al.wbuid) num , count(al.wbuid) mounts from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.wbuid is not null and al.type = 'R' group by al.source";
	@Override
	public List<Source> findParticipateCountBySource(long activityid) {
		Object[] params={activityid};
		return getJdbcTemplate().query(FIND_PARTICIPATECOUNT_BY_SOURCE, params,new ParticipateSourceRowmapper());
	}
	
	private static final String FIND_ANONPARTICIPATECOUNT_BY_SOURCE = "select count(al.wbuid) from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.wbuid is not null and al.type = 'R' and al.source = ? ";
	@Override
	public int findAnonParticipateCountBySource(String name, long activityid) {
		Object[] params={activityid,name};
		return getJdbcTemplate().queryForInt(FIND_ANONPARTICIPATECOUNT_BY_SOURCE, params);
	}
	
	class ParticipateTerminalRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Terminal terminal = new Terminal();
			terminal.setName(rs.getString("terminal"));
			terminal.setCount(rs.getLong("num"));
			terminal.setCountSum(rs.getLong("mounts"));
			return terminal;
		}
	}
	
	private static final String FIND_PARTICIPATECOUNT_BY_TERMINAL = "select al.terminal,count(distinct al.wbuid) num ,count(al.wbuid) mounts from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.wbuid is not null and al.type = 'R' group by al.terminal";
	@Override
	public List<Terminal> findParticipateCountByTerminal(long activityid) {
		Object[] params={activityid};
		return getJdbcTemplate().query(FIND_PARTICIPATECOUNT_BY_TERMINAL, params,new ParticipateTerminalRowmapper());
	}
	
	private static final String FIND_ANONPARTICIPATECOUNT_BY_TERMINAL = "select count(al.wbuid) from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.wbuid is not null and al.type = 'R' and al.terminal = ?";
	@Override
	public int findAnonParticipateCountByTerminal(String name, long activityid) {
		Object[] params={activityid,name};
		return getJdbcTemplate().queryForInt(FIND_ANONPARTICIPATECOUNT_BY_TERMINAL, params);
	}
	
	class ParticipateActiviytRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			ActivityLog activitylog = new ActivityLog();
			activitylog.setWbuid(rs.getLong("wbuid"));
			activitylog.setIp(rs.getString("ip"));
			activitylog.setSource(rs.getString("source"));
			activitylog.setCreatetime(rs.getTimestamp("createtime"));
			return activitylog;
		}
	}
	
	private static final String FIND_ACTIVITY_PARTICIPATE_DETAILES = "select al.wbuid,al.ip,al.source,al.createtime from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.wbuid is not null and al.type = 'R' and al.terminal = ? order by al.createtime desc limit ?,?";
	@Override
	public List<ActivityLog> findActivityParticipateDetails(long activityid,String name, int start, int pageSize) {
		Object[] params={activityid,name,start,pageSize};
		return getJdbcTemplate().query(FIND_ACTIVITY_PARTICIPATE_DETAILES, params,new ParticipateActiviytRowmapper());
	}
	
	private static final String FIND_PARTICIPATE_SUCCESS_COUNT_BY_TERMINAL = "select al.terminal,count(distinct al.wbuid) num ,count(al.wbuid) mounts from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.wbuid is not null and al.type = 'C' group by al.terminal";
	@Override
	public List<Terminal> findParticipateSuccessCountByTerminal(long activityid) {
		Object[] params={activityid};
		return getJdbcTemplate().query(FIND_PARTICIPATE_SUCCESS_COUNT_BY_TERMINAL, params,new ParticipateTerminalRowmapper());
	}
	
	private static final String FIND_ANONPARTICIPATE_SUCCESS_COUNT_BY_TERMINAL = "select count(al.wbuid) from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.type = 'C' and al.terminal = ?";
	@Override
	public int findAnonParticipateSuccessCountByTerminal(String name,
			long activityid) {
		Object[] params={activityid,name};
		return getJdbcTemplate().queryForInt(FIND_ANONPARTICIPATE_SUCCESS_COUNT_BY_TERMINAL, params);
	}
	
	private static final String FIND_PARTICIPATECOUNT_SUCCESS_BY_SOURCE = "select al.source,count(distinct al.wbuid) num ,count(al.wbuid) mounts from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.wbuid is not null and al.type = 'C' group by al.source";
	@Override
	public List<Source> findParticipateSuccessCountBySource(long activityid) {
		Object[] params={activityid};
		return getJdbcTemplate().query(FIND_PARTICIPATECOUNT_SUCCESS_BY_SOURCE, params,new ParticipateSourceRowmapper());
	}
	
	private static final String FIND_ANONPARTICIPATE_SUCCESS_COUNT_BY_SOURCE = "select count(al.wbuid) from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.wbuid is not null and al.type = 'C' and al.source = ?";
	@Override
	public int findAnonParticipateSuccessCountBySource(String name,
			long activityid) {
		Object[] params={activityid,name};
		return getJdbcTemplate().queryForInt(FIND_ANONPARTICIPATE_SUCCESS_COUNT_BY_SOURCE, params);
	}
	
	class ActiviytReportRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			ActivityReportAnalyse activityReport = new ActivityReportAnalyse();
			activityReport.setJoinnum(rs.getInt("joinnum"));
			activityReport.setSucjoinnum(rs.getInt("sucjoinnum"));
			return activityReport;
		}
	}
	
	private static final String FIND_PARTICIPATE_COUNT ="select count(distinct wbuid) num from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.type = 'R'";
	@Override
	public int findParticipateCount(long activityid) {
		Object[] params={activityid};
		return getJdbcTemplate().queryForInt(FIND_PARTICIPATE_COUNT, params);
	}
	
	private static final String FIND_PARTICIPATE_SUCCESS_COUNT = "select count(distinct wbuid) num from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.type = 'C'";
	@Override
	public int findParticipateSuccessCount(long activityid) {
		Object[] params={activityid};
		return getJdbcTemplate().queryForInt(FIND_PARTICIPATE_SUCCESS_COUNT, params);
	}
	
	private static final String FIND_ANONVISIT_DATA = " select vl.createtime,vl.ip from esite.es_activity a join es_visit_log_unknown vl on a.siteid = vl.siteid where a.id = ? limit ?,?";
	@Override
	public List<VisitLog> findAnonVisitData(long activityid,int start,int pageSize) {
		Object[] params={activityid,start,pageSize};
		return getJdbcTemplate().query(FIND_ANONVISIT_DATA, params,new VisitLogRowmapper());
	}
	
	private static final String FIND_ANONVISIT_DATA_COUNT = "select count(vl.id) from esite.es_activity a join es_visit_log_unknown vl on a.siteid = vl.siteid where a.id = ? ";
	@Override
	public int findAnonVisitDataCount(long activityid) {
		Object[] params={activityid};
		return getJdbcTemplate().queryForInt(FIND_ANONVISIT_DATA_COUNT, params);
	}

	private static final String FIND_NONANONVISIT_DATA = "select vl.createtime,vl.ip,vl.terminal,vl.source,su.nickname from esite.es_activity a join esite.es_visit_log vl on a.siteid = vl.siteid join esite.es_user_info ui on vl.uid = ui.id join es_sina_user su on ui.wbuid = su.wbuid where a.id = ? order by vl.createtime desc limit ?,?";
	@Override
	public List<VisitLog> findnonAnonVisitData(long activityid,int start,int pageSize) {
		Object[] params={activityid,start,pageSize};
		return getJdbcTemplate().query(FIND_NONANONVISIT_DATA, params,new VisitLogDataRowmapper());
	}
	
	private static final String FIND_NONANONVISITCOUNT = "select count(vl.id) from esite.es_activity a join esite.es_visit_log vl on a.siteid = vl.siteid join esite.es_user_info ui on vl.uid = ui.id where a.id = ? and vl.uid is not null ";
	@Override
	public int findnonAnonVisitCount(long activityid) {
		Object[] params={activityid};
		return getJdbcTemplate().queryForInt(FIND_NONANONVISITCOUNT, params);
	}
	
	class VisitLogDataRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			VisitLog visitlog = new VisitLog();
			visitlog.setCreateTime(rs.getTimestamp("createtime"));
			visitlog.setIp(rs.getString("ip"));
			visitlog.setNickname(rs.getString("nickname"));
			visitlog.setTerminal(rs.getString("terminal"));
			visitlog.setSource(rs.getString("source"));
			return visitlog;
		}
		
	}

	@Override
	public List<VisitLog> findnonAnonVisitDataDetails(long activityid,String visitTime1,String visitTime2, String terminal, String source,int page,int pageSize) {
		List<Object> paramsList = new ArrayList<Object>();
		String sql = createFindSQL(activityid,visitTime1,visitTime2,terminal,source,paramsList,page,pageSize);
		Object[] params = paramsList.toArray();
		return getJdbcTemplate().query(sql, params,new VisitLogDataRowmapper());
	}
	
	private String createFindSQL(long activityid,String visitTime1,String visitTime2, String terminal, String source,List<Object> params,int page,int pageSize) {
		StringBuffer sb = new StringBuffer();
		sb.append("select vl.createtime,vl.ip,vl.terminal,vl.source,su.nickname from esite.es_activity a join esite.es_visit_log vl on a.siteid = vl.siteid join esite.es_user_info ui on vl.uid = ui.id join esite.es_sina_user su on ui.wbuid = su.wbuid where a.id = ? ");
		params.add(activityid);
		int lastMax = (page-1)*pageSize;
		if (visitTime1 != null 
				&& visitTime1.length() > 0) {
			sb.append("and vl.createtime>? ");
			params.add(visitTime1);
		}
		if(visitTime2 != null 
				&& visitTime2.length() > 0) {
			sb.append("and vl.createtime<? ");
			params.add(visitTime2);
		}
		if(terminal != null 
				&& !terminal.equals("0")) {
			sb.append("and vl.terminal=? ");
			params.add(terminal);
		}
		if(source != null 
				&& !source.equals("0")) {
			sb.append("and vl.source=? ");
			params.add(source);
		}
		sb.append("order by vl.createtime desc limit ?,? ");
		params.add(lastMax);
		params.add(pageSize);
		String sql = sb.toString() ;
		return sql;
	}
	
	@Override
	public int findnonAnonVisitDataDetailsCount(long activityid,String visitTime1, String visitTime2, String terminal, String source) {
		List<Object> paramsList = new ArrayList<Object>();
		String sql = createFindSQLSum(activityid,visitTime1,visitTime2,terminal,source,paramsList);
		Object[] params = paramsList.toArray();
		return getJdbcTemplate().queryForInt(sql, params);
	}
	
	private String createFindSQLSum(long activityid,String visitTime1,String visitTime2, String terminal, String source,List<Object> params) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(vl.id) from esite.es_activity a join esite.es_visit_log vl on a.siteid = vl.siteid join esite.es_user_info ui on vl.uid = ui.id where a.id = ? and vl.uid is not null ");
		params.add(activityid);
		if (visitTime1 != null 
				&& visitTime1.length() > 0) {
			sb.append("and vl.createtime>? ");
			params.add(visitTime1);
		}
		if(visitTime2 != null 
				&& visitTime2.length() > 0) {
			sb.append("and vl.createtime<? ");
			params.add(visitTime2);
		}
		if(terminal != null 
				&& !terminal.equals("0")) {
			sb.append("and vl.terminal=? ");
			params.add(terminal);
		}
		if(source != null 
				&& !source.equals("0")) {
			sb.append("and vl.source=? ");
			params.add(source);
		}
		String sql = sb.toString() ;
		return sql;
	}
	
	private static final String FIND_TERMINAL_NAMES = "select distinct vl.terminal from esite.es_activity a join es_visit_log vl on a.siteid = vl.siteid where a.id = ? and vl.uid is not null group by vl.terminal ";
	@Override
	public List<Terminal> findTerminalNames(long activityid) {
		Object[] params={activityid};
		return getJdbcTemplate().query(FIND_TERMINAL_NAMES, params,new VisitLogTerminalRowmapper());
	}

	class VisitLogTerminalRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Terminal terminal = new Terminal();
			terminal.setName(rs.getString("terminal"));
			return terminal;
		}
		
	}

	private static final String FIND_SOURCE_NAMES = "select distinct vl.source from esite.es_activity a join es_visit_log vl on a.siteid = vl.siteid where a.id = ? and vl.uid is not null and vl.source is not null group by vl.source ";
	@Override
	public List<Source> findSourceNames(long activityid) {
		Object[] params={activityid};
		return getJdbcTemplate().query(FIND_SOURCE_NAMES, params,new VisitLogSourceRowmapper());
	}
	
	class VisitLogSourceRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Source source = new Source();
			source.setName(rs.getString("source"));
			return source;
		}
		
	}

	private static final String FIND_NONANONVISIT_DETAILES = " select vl.createtime,vl.ip,vl.terminal,vl.source,su.nickname from esite.es_activity a join esite.es_visit_log vl on a.siteid = vl.siteid join esite.es_user_info ui on vl.uid = ui.id join es_sina_user su on ui.wbuid = su.wbuid where a.id = ? and su.nickname like ? limit ?,?";
	@Override
	public List<VisitLog> findnonAnonVisitDataDetails(long activityid,
			String nickname,int start,int pageSize) {
		nickname = "%" + nickname + "%";
		Object[] params={activityid,nickname,start,pageSize};
		return getJdbcTemplate().query(FIND_NONANONVISIT_DETAILES, params,new VisitLogDataRowmapper());
	}

	private static final String FIND_NONANONVISIT_DATA_DETAILS_COUNT = "select count(vl.id) from esite.es_activity a join esite.es_visit_log vl on a.siteid = vl.siteid join esite.es_user_info ui on vl.uid = ui.id where a.id = ? and vl.uid is not null and ui.nickname like ?";
	@Override
	public int findnonAnonVisitDataDetailsCount(long activityid, String nickname) {
		nickname = "%" + nickname + "%";
		Object[] params={activityid,nickname};
		return getJdbcTemplate().queryForInt(FIND_NONANONVISIT_DATA_DETAILS_COUNT, params);
	}

	class UserInfoRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserInfo userinfo = new UserInfo();
			userinfo.setNickname(rs.getString("nickname"));
			userinfo.setUserid(rs.getLong("wbuid"));
			return userinfo;
		}
	}
	
	private static final String FIND_NICK_NAMES = "select su.nickname,ui.wbuid from esite.es_user_info ui join es_sina_user su on ui.wbuid = su.wbuid where ui.siteid = ? and ui.wbuid = ?";
	@Override
	public UserInfo findNickNames(long siteid, long wbuid) {
		Object[] params={siteid,wbuid};
		List<UserInfo> list = getJdbcTemplate().query(FIND_NICK_NAMES, params,new UserInfoRowmapper());
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	
	private static final String FIND_ACTIVITY_BY_ACTIVITYID = "select a.* from esite.es_activity a where a.id = ?";
	@Override
	public Activity findActivityByActivityid(long activityid) {
		Object[] params={activityid};
		List<Activity> list = getJdbcTemplate().query(FIND_ACTIVITY_BY_ACTIVITYID, params,new ActivityRowmapper());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int findActivityParticipateDetailsCount(String terminalName,long activityid, String visitTime1, String visitTime2, String source) {
		List<Object> paramsList = new ArrayList<Object>();
		String sql = createFindActivityParticipateSQL(terminalName,activityid,visitTime1,visitTime2,source,paramsList);
		Object[] params = paramsList.toArray();
		return getJdbcTemplate().queryForInt(sql, params);
	}
	
	private String createFindActivityParticipateSQL(String terminalName,long activityid,String visitTime1,String visitTime2, String source,List<Object> params) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(al.wbuid) from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.type = 'R' and al.terminal = ? ");
		params.add(activityid);
		params.add(terminalName);
		if (visitTime1 != null 
				&& visitTime1.length() > 0) {
			sb.append("and al.createtime>? ");
			params.add(visitTime1);
		}
		if(visitTime2 != null 
				&& visitTime2.length() > 0) {
			sb.append("and al.createtime<? ");
			params.add(visitTime2);
		}
		if(source != null 
				&& !source.equals("0")) {
			sb.append("and al.source=? ");
			params.add(source);
		}
		String sql = sb.toString() ;
		return sql;
	}

	@Override
	public List<ActivityLog> findActivityParticipateDetails(long activityid,String terminalName, String visitTime1, String visitTime2,
			String source, int start, int pageSize) {
		List<Object> paramsList = new ArrayList<Object>();
		String sql = createFindActivityParticipateSQL(terminalName,activityid,visitTime1,visitTime2,source,paramsList,start,pageSize);
		Object[] params = paramsList.toArray();
		return getJdbcTemplate().query(sql, params,new ParticipateActiviytRowmapper());
	}
	
	private String createFindActivityParticipateSQL(String terminalName,long activityid,String visitTime1,String visitTime2, String source,List<Object> params,int start,int pageSize) {
		StringBuffer sb = new StringBuffer();
		sb.append("select al.wbuid,al.ip,al.source,al.createtime from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.type = 'R' and al.terminal = ? ");
		params.add(activityid);
		params.add(terminalName);
		if (visitTime1 != null 
				&& visitTime1.length() > 0) {
			sb.append("and al.createtime>? ");
			params.add(visitTime1);
		}
		if(visitTime2 != null 
				&& visitTime2.length() > 0) {
			sb.append("and al.createtime<? ");
			params.add(visitTime2);
		}
		if(source != null 
				&& !source.equals("0")) {
			sb.append("and al.source=? ");
			params.add(source);
		}
		sb.append("order by al.createtime desc limit ?,? ");
		params.add(start);
		params.add(pageSize);
		String sql = sb.toString() ;
		return sql;
	}

	private String FIND_ACTIVITY_PARTICIPATE_SOURCENAMES = "select distinct al.source from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.type = 'R' and al.terminal = ?";
	@Override
	public List<Source> findActivityParticipateSourceNames(String terminalName,long activityid) {
		Object[] params={activityid,terminalName};
		return getJdbcTemplate().query(FIND_ACTIVITY_PARTICIPATE_SOURCENAMES, params,new VisitLogSourceRowmapper());
	}
	
	private String FIND_WBUID = "select su.nickname,ui.wbuid from esite.es_user_info ui join es_sina_user su on ui.wbuid = su.wbuid where ui.siteid = ? and su.nickname like ? ";
	@Override
	public List<UserInfo> findWbuid(long siteid, String nickname) {
		nickname = "%" + nickname + "%";
		Object[] params={siteid,nickname};
		return getJdbcTemplate().query(FIND_WBUID, params,new UserInfoRowmapper());
	}
	
	private String FIND_ACTIVITY_PARTICIPATE_DETAILES_BY_NAME = "select count(al.wbuid) from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.type = 'R' and al.terminal = ? and al.wbuid = ? ";
	@Override
	public int findActivityParticipateDetailsByName(long activityid,String terminalName, long wbuid) {
		Object[] params={activityid,terminalName,wbuid};
		return getJdbcTemplate().queryForInt(FIND_ACTIVITY_PARTICIPATE_DETAILES_BY_NAME, params);
	}

	private String FIND_ACTIVITYLOG_PARTICIPATE_DETAILS_BY_NAME = "select al.wbuid,al.ip,al.source,al.createtime from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.type = 'R' and al.terminal = ? and al.wbuid = ? limit ?,?";
	@Override
	public List<ActivityLog> findActivityParticipateDetailsByName(long activityid, String terminalName, long wbuid, int start,int pageSize) {
		Object[] params={activityid,terminalName,wbuid,start,pageSize};
		return getJdbcTemplate().query(FIND_ACTIVITYLOG_PARTICIPATE_DETAILS_BY_NAME, params,new ParticipateActiviytRowmapper());
	}

	private static final String FIND_ACTIVITY_PARTICIPATE_SUCCESS_DETAILES = "select al.wbuid,al.ip,al.source,al.createtime from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.type = 'C' and al.terminal = ? order by al.createtime desc limit ?,?";
	@Override
	public List<ActivityLog> findActivityParticipateSuccessDetails(
			long activityid, String name, int start, int pageSize) {
		Object[] params={activityid,name,start,pageSize};
		return getJdbcTemplate().query(FIND_ACTIVITY_PARTICIPATE_SUCCESS_DETAILES, params,new ParticipateActiviytRowmapper());
	}
	
	@Override
	public int findActivityParticipateSuccessDetailsCount(String terminalName,long activityid, String visitTime1, String visitTime2, String source) {
		List<Object> paramsList = new ArrayList<Object>();
		String sql = createFindActivityParticipateSuccessSQL(terminalName,activityid,visitTime1,visitTime2,source,paramsList);
		Object[] params = paramsList.toArray();
		return getJdbcTemplate().queryForInt(sql, params);
	}
	
	private String createFindActivityParticipateSuccessSQL(String terminalName,long activityid,String visitTime1,String visitTime2, String source,List<Object> params) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(al.wbuid) from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.type = 'C' and al.terminal = ? ");
		params.add(activityid);
		params.add(terminalName);
		if (visitTime1 != null 
				&& visitTime1.length() > 0) {
			sb.append("and al.createtime>? ");
			params.add(visitTime1);
		}
		if(visitTime2 != null 
				&& visitTime2.length() > 0) {
			sb.append("and al.createtime<? ");
			params.add(visitTime2);
		}
		if(source != null 
				&& !source.equals("0")) {
			sb.append("and al.source=? ");
			params.add(source);
		}
		String sql = sb.toString() ;
		return sql;
	}

	@Override
	public List<ActivityLog> findActivityParticipateSuccessDetails(
			long activityid, String terminalName, String visitTime1,
			String visitTime2, String source, int start, int pageSize) {
		List<Object> paramsList = new ArrayList<Object>();
		String sql = createFindActivityParticipateSuccessSQL(terminalName,activityid,visitTime1,visitTime2,source,paramsList,start,pageSize);
		Object[] params = paramsList.toArray();
		return getJdbcTemplate().query(sql, params,new ParticipateActiviytRowmapper());
	}
	
	private String createFindActivityParticipateSuccessSQL(String terminalName,long activityid,String visitTime1,String visitTime2, String source,List<Object> params,int start,int pageSize) {
		StringBuffer sb = new StringBuffer();
		sb.append("select al.wbuid,al.ip,al.source,al.createtime from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.type = 'C' and al.terminal = ? ");
		params.add(activityid);
		params.add(terminalName);
		if (visitTime1 != null 
				&& visitTime1.length() > 0) {
			sb.append("and al.createtime>? ");
			params.add(visitTime1);
		}
		if(visitTime2 != null 
				&& visitTime2.length() > 0) {
			sb.append("and al.createtime<? ");
			params.add(visitTime2);
		}
		if(source != null 
				&& !source.equals("0")) {
			sb.append("and al.source=? ");
			params.add(source);
		}
		sb.append("order by al.createtime desc limit ?,? ");
		params.add(start);
		params.add(pageSize);
		String sql = sb.toString() ;
		return sql;
	}

	private String FIND_ACTIVITY_PARTICIPATE_SUCCESS_SOURCENAMES = "select distinct al.source from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.type = 'C' and al.terminal = ?";
	@Override
	public List<Source> findActivityParticipateSuccessSourceNames(
			String terminalName, long activityid) {
		Object[] params={activityid,terminalName};
		return getJdbcTemplate().query(FIND_ACTIVITY_PARTICIPATE_SUCCESS_SOURCENAMES, params,new VisitLogSourceRowmapper());
	}
	
	private String FIND_ACTIVITY_PARTICIPATE_SUCCESS_DETAILES_BY_NAME = "select count(al.wbuid) from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.type = 'C' and al.terminal = ? and al.wbuid = ? ";
	@Override
	public int findActivityParticipateSuccessDetailsByName(long activityid,String terminalName, long wbuid) {
		Object[] params={activityid,terminalName,wbuid};
		return getJdbcTemplate().queryForInt(FIND_ACTIVITY_PARTICIPATE_SUCCESS_DETAILES_BY_NAME, params);
	}
	
	private String FIND_ACTIVITYLOG_PARTICIPATE_SUCCESS_DETAILS_BY_NAME = "select al.wbuid,al.ip,al.source,al.createtime from esite.es_activity a join esite.es_activity_log al on a.id = al.activityid where a.id = ? and al.type = 'C' and al.terminal = ? and al.wbuid = ? limit ?,?";
	@Override
	public List<ActivityLog> findActivityParticipateSuccessDetailsByName(long activityid, String terminalName, long wbuid, int start,int pageSize) {
		Object[] params={activityid,terminalName,wbuid,start,pageSize};
		return getJdbcTemplate().query(FIND_ACTIVITYLOG_PARTICIPATE_SUCCESS_DETAILS_BY_NAME, params,new ParticipateActiviytRowmapper());
	}

	private String FIND_PARTICIPATE_FAIL_COUNT_BY_TERMINAL = "select count(t1.wbuid) from ((select distinct wbuid,type type1,ip,terminal,source from es_activity_log where type='R' and activityid = ? and terminal = ?) t1 left outer join (select distinct wbuid,type type2 from es_activity_log where type='C' and activityid = ?) t2 on t1.wbuid= t2.wbuid) where type2 is null ";
	@Override
	public int findParticipateFailCountByTerminal(String name, long activityid) {
		Object[] params={activityid,name,activityid};
		return getJdbcTemplate().queryForInt(FIND_PARTICIPATE_FAIL_COUNT_BY_TERMINAL, params);
	}

	private String FINA_ACTIVITY_PARTICIPATE_FAIL_DETAILS = "select t1.* from ((select distinct wbuid,createtime,type type1,ip,terminal,source from es_activity_log where type='R' and activityid = ? and terminal = ?) t1 left outer join (select distinct wbuid,type type2 from es_activity_log where type='C' and activityid = ?) t2 on t1.wbuid= t2.wbuid) where type2 is null limit ?,? ";
	@Override
	public List<ActivityLog> findActivityParticipateFailDetails(long activityid, String name, int start, int pageSize) {
		Object[] params={activityid,name,activityid,start,pageSize};
		return getJdbcTemplate().query(FINA_ACTIVITY_PARTICIPATE_FAIL_DETAILS, params,new ParticipateActiviytRowmapper());
	}
	
	private String FINA_ACTIVITY_PARTICIPATE_FAIL_SOURCENAMES = " select distinct source from ((select distinct wbuid,createtime,type type1,ip,terminal,source from es_activity_log where type='R' and activityid = ? and terminal = ?) t1 left outer join (select distinct wbuid,type type2 from es_activity_log where type='C' and activityid = ?) t2 on t1.wbuid= t2.wbuid) where type2 is null ";
	@Override
	public List<Source> findActivityParticipateFailSourceNames(String terminalName, long activityid) {
		Object[] params={activityid,terminalName,activityid};
		return getJdbcTemplate().query(FINA_ACTIVITY_PARTICIPATE_FAIL_SOURCENAMES, params,new VisitLogSourceRowmapper());
	}
	
	public int findActivityParticipateFailDetailsCount(String terminalName,long activityid, String visitTime1, String visitTime2, String source) {
		List<Object> paramsList = new ArrayList<Object>();
		String sql = createFindActivityParticipateFailSQL(terminalName,activityid,visitTime1,visitTime2,source,paramsList);
		Object[] params = paramsList.toArray();
		return getJdbcTemplate().queryForInt(sql, params);
	}
	
	private String createFindActivityParticipateFailSQL(String terminalName,long activityid,String visitTime1,String visitTime2, String source,List<Object> params) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(t1.wbuid) from ((select distinct wbuid,createtime,type type1,ip,terminal,source from es_activity_log where type='R' and activityid = ? and terminal = ?) t1 left outer join (select distinct wbuid,type type2 from es_activity_log where type='C' and activityid = ?) t2 on t1.wbuid= t2.wbuid) where type2 is null ");
		params.add(activityid);
		params.add(terminalName);
		params.add(activityid);
		if (visitTime1 != null 
				&& visitTime1.length() > 0) {
			sb.append("and t1.createtime>? ");
			params.add(visitTime1);
		}
		if(visitTime2 != null 
				&& visitTime2.length() > 0) {
			sb.append("and t1.createtime<? ");
			params.add(visitTime2);
		}
		if(source != null 
				&& !source.equals("0")) {
			sb.append("and t1.source=? ");
			params.add(source);
		}
		String sql = sb.toString() ;
		return sql;
	}
	
	public List<ActivityLog> findActivityParticipateFailDetails(
			long activityid, String terminalName, String visitTime1,
			String visitTime2, String source, int start, int pageSize) {
		List<Object> paramsList = new ArrayList<Object>();
		String sql = createFindActivityParticipateFailSQL(terminalName,activityid,visitTime1,visitTime2,source,paramsList,start,pageSize);
		Object[] params = paramsList.toArray();
		return getJdbcTemplate().query(sql, params,new ParticipateActiviytRowmapper());
	}
	
	private String createFindActivityParticipateFailSQL(String terminalName,long activityid,String visitTime1,String visitTime2, String source,List<Object> params,int start,int pageSize) {
		StringBuffer sb = new StringBuffer();
		sb.append("select t1.* from ((select distinct wbuid,createtime,type type1,ip,terminal,source from es_activity_log where type='R' and activityid = ? and terminal = ?) t1 left outer join (select distinct wbuid,type type2 from es_activity_log where type='C' and activityid = ?) t2 on t1.wbuid= t2.wbuid) where type2 is null ");
		params.add(activityid);
		params.add(terminalName);
		params.add(activityid);
		if (visitTime1 != null 
				&& visitTime1.length() > 0) {
			sb.append("and t1.createtime>? ");
			params.add(visitTime1);
		}
		if(visitTime2 != null 
				&& visitTime2.length() > 0) {
			sb.append("and t1.createtime<? ");
			params.add(visitTime2);
		}
		if(source != null 
				&& !source.equals("0")) {
			sb.append("and t1.source=? ");
			params.add(source);
		}
		sb.append("limit ?,? ");
		params.add(start);
		params.add(pageSize);
		String sql = sb.toString() ;
		return sql;
	}
	
	private String FIND_ACTIVITY_PARTICIPATE_FAIL_DETAILES_BY_NAME = "select count(t1.wbuid) from ((select distinct wbuid,createtime,type type1,ip,terminal,source from es_activity_log where type='R' and activityid = ? and terminal = ?) t1 left outer join (select distinct wbuid,type type2 from es_activity_log where type='C' and activityid = ?) t2 on t1.wbuid= t2.wbuid) where type2 is null and t1.wbuid = ? ";
	@Override
	public int findActivityParticipateFailDetailsByName(long activityid,String terminalName, long wbuid) {
		Object[] params={activityid,terminalName,activityid,wbuid};
		return getJdbcTemplate().queryForInt(FIND_ACTIVITY_PARTICIPATE_FAIL_DETAILES_BY_NAME, params);
	}

	private String FIND_ACTIVITYLOG_PARTICIPATE_FAIL_DETAILS_BY_NAME = "select t1.* from ((select distinct wbuid,createtime,type type1,ip,terminal,source from es_activity_log where type='R' and activityid = ? and terminal = ?) t1 left outer join (select distinct wbuid,type type2 from es_activity_log where type='C' and activityid = ?) t2 on t1.wbuid= t2.wbuid) where type2 is null and t1.wbuid = ? limit ?,?";
	@Override
	public List<ActivityLog> findActivityParticipateFailDetailsByName(long activityid, String terminalName, long wbuid, int start,int pageSize) {
		Object[] params={activityid,terminalName,activityid,wbuid,start,pageSize};
		return getJdbcTemplate().query(FIND_ACTIVITYLOG_PARTICIPATE_FAIL_DETAILS_BY_NAME,params,new ParticipateActiviytRowmapper());
	}
	
	private static final String FIND_PARTICIPATE_FAIL_COUNT = "select t1.terminal,count(distinct t1.wbuid) num,count(t1.wbuid) mounts from ((select distinct wbuid,type type1,ip,terminal,source from es_activity_log where type='R' and activityid = ?) t1 left outer join (select distinct wbuid,type type2 from es_activity_log where type='C' and activityid = ?) t2 on t1.wbuid= t2.wbuid) where type2 is null group by terminal";
	@Override
	public List<Terminal> findParticipateFailCountByTerminal(long activityid) {
		Object[] params={activityid,activityid};
		return getJdbcTemplate().query(FIND_PARTICIPATE_FAIL_COUNT, params,new ParticipateTerminalRowmapper());
	}

	private static final String FIND_PARTICIPATE_FAIL_COUNT_BY_SOURCE = "select t1.source,count(distinct t1.wbuid) num,count(t1.wbuid) mounts from ((select distinct wbuid,type type1,ip,terminal,source from es_activity_log where type='R' and activityid = ?) t1 left outer join (select distinct wbuid,type type2 from es_activity_log where type='C' and activityid = ?) t2 on t1.wbuid= t2.wbuid) where type2 is null group by source";
	@Override
	public List<Source> findParticipateFailCountBySource(long activityid) {
		Object[] params={activityid,activityid};
		return getJdbcTemplate().query(FIND_PARTICIPATE_FAIL_COUNT_BY_SOURCE, params,new ParticipateSourceRowmapper());
	}

	private static final String FIND_WBUID_BY_UID = " select ui.nickname,ui.wbuid from esite.es_user_info ui where ui.id = ? ";
	@Override
	public UserInfo findwbuidByUid(long uid) {
		Object[] params={uid};
		List<UserInfo> list = getJdbcTemplate().query(FIND_WBUID_BY_UID, params,new UserInfoRowmapper());
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	private static final String SAVE_ACTIVITY_LOG = "insert into esite.es_activity_log (activityid,wbuid,type,ip,terminal,source,createtime) values(?,?,?,?,?,?,now()) ";
	@Override
	public long saveActivityLog(final long activityid, final long wbuid, final String type,
			final String ip, final String terminal, final String source) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_ACTIVITY_LOG,new String[] { "id" });
				int i = 1;
				ps.setLong(i++, activityid);
				ps.setLong(i++, wbuid);
				ps.setString(i++, type);
				ps.setString(i++, ip);
				ps.setString(i++, terminal);
				ps.setString(i++, source);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
}
