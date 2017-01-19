package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IVisitDao;
import com.huiyee.esite.dto.VisitNum;
import com.huiyee.esite.dto.VisitUserDto;
import com.huiyee.esite.model.AreaAnalysis;
import com.huiyee.esite.model.CustomVisitReport;
import com.huiyee.esite.model.VisitLog;
import com.huiyee.esite.model.VisitReportDetail;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.DateUtil;

public class VisitDaoImpl extends AbstractDao implements IVisitDao
{

	@Override
	public void insertVisitLog(long siteid, long pageid,VisitUser vu, String ip,String agent,String term,String key)
	{
		Object[] params =
		{ vu.getHyUserId(),vu.getWxuid(), siteid, pageid,ip,agent,term,key };
		getJdbcTemplate().update("insert into esite.es_visit_log (uid,wxuid,siteid,pageid,ip,createtime,userAgent,terminal,source) values (?,?,?,?,?,now(),?,?,?)", params);
	}

	@Override
	public void insertVisitLogAnonymous(long siteid,long pageid, String ip,String agent,String term,String key)
	{
		Object[] params =
		{ siteid, pageid,ip ,agent,term,key};
		getJdbcTemplate().update("insert into esite.es_visit_log_unknown(siteid,pageid,ip,createtime,userAgent,terminal,source) values (?,?,?,now(),?,?,?)", params);
	}

	@Override
	public List<VisitLog> findVistLogBySiteGidAndtype(long siteGid, String type, int page)
	{
		if ("是".equals(type))
		{
			return getJdbcTemplate().query("select vlog.*,userinfo.nickname,userinfo.wbuid wbuid from  esite.es_visit_log vlog join esite.es_site site on site.id=vlog.siteid left outer join esite.es_user_info userinfo on userinfo.id=vlog.uid where site.sitegroupid =? and vlog.uid is null  order by createtime desc limit ?,?", new Object[]
			{ siteGid, (page - 1) * IPageConstants.CONTENT_LIMIT, IPageConstants.CONTENT_LIMIT }, new VisitLogRowMapper2());
		}
		return getJdbcTemplate().query("select vlog.*,userinfo.nickname,userinfo.wbuid wbuid from  esite.es_visit_log vlog,esite.es_site site,esite.es_user_info userinfo where site.id=vlog.siteid and userinfo.id=vlog.uid and site.sitegroupid=? and vlog.uid is not  null  order by createtime desc  limit ?,?", new Object[]
		{ siteGid, (page - 1) * IPageConstants.CONTENT_LIMIT, IPageConstants.CONTENT_LIMIT }, new VisitLogRowMapper2());
	}
	
	@Override
    public List<VisitLog> findVistLogBySiteAndtype(long siteid, String type, int page)
    {
        if ("是".equals(type))
        {
            return getJdbcTemplate().query("select vlog.* from  esite.es_visit_log vlog,esite.es_site site where site.id=vlog.siteid  and site.id=? and vlog.uid is  null  limit ?,?", new Object[]
            { siteid, (page - 1) * IPageConstants.CONTENT_LIMIT, IPageConstants.CONTENT_LIMIT }, new VisitLogRowMapper());
        }
        return getJdbcTemplate().query("select vlog.* from  esite.es_visit_log vlog,esite.es_site site where site.id=vlog.siteid  and site.id=? and vlog.uid is not null limit ?,?", new Object[]
        { siteid, (page - 1) * IPageConstants.CONTENT_LIMIT, IPageConstants.CONTENT_LIMIT }, new VisitLogRowMapper());
    }

	@Override
	public int findTotalVistLogBySiteidAndtype(long siteGid, String type)
	{
		if ("是".equals(type))
		{
			return getJdbcTemplate().queryForInt("select count(vlog.id) from  esite.es_visit_log vlog,esite.es_site site where site.id=vlog.siteid  and site.sitegroupid=? and vlog.uid is  null", new Object[]
			{ siteGid });
		}
		return getJdbcTemplate().queryForInt("select count(vlog.id) from  esite.es_visit_log vlog,esite.es_site site where site.id=vlog.siteid  and site.sitegroupid=? and vlog.uid is not null", new Object[]
		{ siteGid });
	}
	@Override
    public int findTotalVistLogBySiteAndtype(long siteid, String type)
    {
        if ("是".equals(type))
        {
            return getJdbcTemplate().queryForInt("select count(vlog.id) from  esite.es_visit_log vlog,esite.es_site site where site.id=vlog.siteid  and site.id=? and vlog.uid is  null", new Object[]
            { siteid });
        }
        return getJdbcTemplate().queryForInt("select count(vlog.id) from  esite.es_visit_log vlog,esite.es_site site where site.id=vlog.siteid  and site.id=? and vlog.uid is not null", new Object[]
        { siteid });
    }
	
	
	@Override
	public List<VisitLog> findVistLogExport(long siteGid, String type )
	{
		if ("Y".equals(type))
		{
			return getJdbcTemplate().query("select vlog.*,userinfo.nickname,userinfo.wbuid wbuid from  esite.es_visit_log vlog join esite.es_site site on site.id=vlog.siteid left outer join esite.es_user_info userinfo on userinfo.id=vlog.uid where site.sitegroupid =? and vlog.uid is null  order by createtime desc", new Object[]
			{ siteGid}, new VisitLogRowMapper2());
		}
		return getJdbcTemplate().query("select vlog.*,userinfo.nickname,userinfo.wbuid wbuid from  esite.es_visit_log vlog,esite.es_site site,esite.es_user_info userinfo where site.id=vlog.siteid and userinfo.id=vlog.uid and site.sitegroupid=? and vlog.uid is not  null  order by createtime desc", new Object[]
		{ siteGid, }, new VisitLogRowMapper2());
	}
	private static final String FIND_VISIT_COUNT_BY_SITE_GROUP="select count(distinct(uid)) from es_visit_log v left join es_site s on s.id=v.siteid where v.uid is not null and s.sitegroupid=? and DATE(v.createtime) =? ";
    @Override
    public int findVisitNumBySiteGroup(long sgid,Date date) {
        return getJdbcTemplate().queryForInt(FIND_VISIT_COUNT_BY_SITE_GROUP, new Object[]{sgid,date});
    }
    
    private static final String FIND_VISIT_COUNT_BY_HOUR="select count(v.id) from es_visit_log v left join es_site s on s.id=v.siteid where s.sitegroupid=? and HOUR(v.createtime) =? ";
    @Override
    public int findVisitNumByHour(long sgid,int hour) {
        return getJdbcTemplate().queryForInt(FIND_VISIT_COUNT_BY_HOUR, new Object[]{sgid,hour});
    }
    
    private static final String FIND_VISIT_COUNT_BEFORE_DATE="select count(distinct(uid)) from es_visit_log v left join es_site s on s.id=v.siteid where v.uid is not null and s.sitegroupid=? and v.createtime <? ";
    @Override
    public int findVisitNumBeforeDate(long sgid,String date) {
        return getJdbcTemplate().queryForInt(FIND_VISIT_COUNT_BEFORE_DATE, new Object[]{sgid,date});
    }
    
    private static final String FIND_NOLOGIN_VISIT_COUNT_BY_SITE_GROUP="select count(v.id) from es_visit_log_unknown v left join es_site s on s.id=v.siteid where s.sitegroupid=? and DATE(v.createtime) =? ";
    @Override
    public int findNoLoginVisitNumBySiteGroup(long sgid,Date date) {
        return getJdbcTemplate().queryForInt(FIND_NOLOGIN_VISIT_COUNT_BY_SITE_GROUP, new Object[]{sgid,date});
    }
    
    private static final String FIND_NOLOG_TOTAL="select count(v.id) from es_visit_log_unknown v left join es_site s on s.id=v.siteid where s.sitegroupid=? ";
    @Override
    public int findNoLoginVisitTotalBySiteGroup(long sgid) {
        return getJdbcTemplate().queryForInt(FIND_NOLOG_TOTAL, new Object[]{sgid});
    }
	
    
    private static final String FIND_VISIT_BY_SITE_GROUP_GENDER = "select count(distinct(v.uid)) from es_visit_log v  left join es_site s on s.id=v.siteid left join es_user_info u on u.id=v.uid and v.siteid=u.siteid where v.uid is not null and s.sitegroupid=? and u.gender=?";

    @Override
    public int findVisitBySiteGroupGender(long sgid,String gender) {
        return getJdbcTemplate().queryForInt(FIND_VISIT_BY_SITE_GROUP_GENDER, new Object[] { sgid,gender });
    }
    
    private static final String FIND_NO_LOG_VISIT_BY_SITE_GROUP_GENDER = "select count(v.id) from es_visit_log v left join es_site s on s.id=v.siteid left join es_user_info u on u.id=v.uid and v.siteid=u.siteid where v.uid is null and s.sitegroupid=? and u.gender=?";

    @Override
    public int findNoLogVisitBySiteGroupGender(long sgid,String gender) {
        return getJdbcTemplate().queryForInt(FIND_NO_LOG_VISIT_BY_SITE_GROUP_GENDER, new Object[] { sgid,gender });
    }

    


	/**
	 * 查询得到访问表
	 */
	private static final String SHOW_DYNAMICAREA_BY_GROUPID="select area,visitnum,lastvisittime from es_report_area_analyse where siteid=? and visitnum>0  order by visitnum desc limit ?,?";
    
	@Override
	public List<AreaAnalysis> findVisitArea(long sgid,int start,int size) {
		
		
		return getJdbcTemplate().query(SHOW_DYNAMICAREA_BY_GROUPID, new Object[]{sgid,start,size},new AreaReport1());
		
	}
	
   private static final String SHOW_DYNAMICAREA_BY_OWNER="select area,sum(visitnum) visitnum,lastvisittime from es_report_area_analyse a join es_site_group g on g.id=a.sitegroupid where g.ownerid=? and visitnum>0 group by a.aid order by visitnum desc limit ?,?";
    
	@Override
	public List<AreaAnalysis> findVisitAreaOwner(long owner,int start,int size) {
		
		
		return getJdbcTemplate().query(SHOW_DYNAMICAREA_BY_OWNER, new Object[]{owner,start,size},new AreaReport1());
		
	}
	class AreaReport1 implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int arg1) throws SQLException { 
            AreaAnalysis areais=new AreaAnalysis();
            DateUtil util=new DateUtil();
            areais.setArea(rs.getString("area"));
            areais.setNum(rs.getInt("visitnum"));
            areais.setLasttime(util.getDateTimeString(rs.getTimestamp("lastvisittime")));
            return areais;
        }
    }
	
	@Override
	public List<AreaAnalysis> findVisitAreaReport(long sgid){
		String sql="select  max(a.createtime) as lasttime,a.ip,count(distinct(a.uid)) as num from es_visit_log a join es_site b on a.siteid=b.id where b.sitegroupid=? group by a.ip  order by num desc  limit 10";
		return getJdbcTemplate().query(sql, new Object[]{sgid},new AreaReport1());
	}
	
	//得到访问的总记录数用于分页
	@Override
	public int findVisitAreaReportTotal(long sgid) {
		String sql=" select count(*) from (select area,visitnum,lastvisittime from es_report_area_analyse where siteid=? and visitnum>0 group by id) t";
		return getJdbcTemplate().queryForInt(sql,new Object[]{sgid});
	}
	
	//查询访问总数
	public int sumVisitNum(long sgid){
		String sql="select sum(visitnum) from es_report_hour_analyse where siteid=?";
		return getJdbcTemplate().queryForInt(sql,new Object[]{sgid});
	}

	
	class CustomVisitMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
        	CustomVisitReport report=new CustomVisitReport();
        	DateUtil util=new DateUtil();
        	report.setSiteid(rs.getLong("siteid"));
        	report.setWbuid(rs.getString("wbuid"));
        	report.setName(rs.getString("nickname"));
        	report.setLasttime(util.getDateTimeString(rs.getTimestamp("lastvisittime")));
        	report.setNum(rs.getInt("visitnum"));
        	report.setIp(rs.getString("ip"));
        	report.setTerminal(rs.getString("terminal"));
            return report;
        }
    }
	class CustomVisitUnkownMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            CustomVisitReport report=new CustomVisitReport();
            report.setCreatetime(rs.getTimestamp("createtime"));
            report.setIp(rs.getString("ip"));
            return report;
        }
    }
	
	/**
	 * 表单查询
	 */
	@Override
	public List<CustomVisitReport> searchVisitReport(long sgid,VisitUserDto visitdto,int start,int size) {
		
		String query="select * from es_report_visitnum_analyse where siteid=? ";
		StringBuffer buffer=new StringBuffer();
		buffer.append(query);
		List<Object> list=new ArrayList<Object>();
		list.add(sgid);
		if(visitdto!=null){
			if(visitdto.getStartdate()!=null&&!"".equals(visitdto.getStartdate().trim())){
				buffer.append(" and lastvisittime>=?");
				list.add(visitdto.getStartdate());
			}
			if(visitdto.getEnddate()!=null&&!"".equals(visitdto.getEnddate().trim())){
				buffer.append(" and lastvisittime<=?");
				list.add(visitdto.getEnddate());  
			}
			if(visitdto.getNum()!=null&&!"".equals(visitdto.getNum().trim())){
				buffer.append(" and visitnum =?");
				list.add(visitdto.getNum());
			}
		}
		buffer.append(" order by lastvisittime desc limit ?,?");
		list.add(start);
		list.add(size);
			return getJdbcTemplate().query(buffer.toString(),list.toArray(),new CustomVisitMapper());
	}
	
	@Override
    public List<CustomVisitReport> findVisitLogUnknow(long sgid,int start,int size) {
	    String sql="select v.createtime,v.ip from es_visit_log_unknown v left join es_site s on v.siteid=s.id where s.siteid=? order by v.createtime desc limit ?,?";
	    return getJdbcTemplate().query(sql,new Object[]{sgid,start,size},new CustomVisitUnkownMapper());
	}
	@Override
    public List<CustomVisitReport> findVisitRecordListByNickName(long sgid,
            String nickname, int start, int size) {
        String sql = "select * from es_report_visitnum_analyse where siteid=? and nickname like '%" + nickname + "%' order by lastvisittime desc limit ?,? ";
        return getJdbcTemplate().query(sql, new Object[] { sgid, start, size },
                new CustomVisitMapper());

    }

    @Override
    public int findVisitRecordListTotalByNickName(long sgid, String nickname) {
        String sql = "select count(id) from es_report_visitnum_analyse where siteid=? and nickname like '%"+ nickname + "%'";
        return getJdbcTemplate().queryForInt(sql, new Object[] { sgid });
    }

	
	//查询得出总数，用于分页
	@Override
	public int searchVisitReportTotal(long sgid, VisitUserDto visitdto) {
		String query="select count(id) from es_report_visitnum_analyse where siteid=?";
		StringBuffer buffer=new StringBuffer();
		buffer.append(query);
		List<Object> list=new ArrayList<Object>();
		list.add(sgid);
		if(visitdto!=null){
			if(visitdto.getStartdate()!=null&&!"".equals(visitdto.getStartdate().trim())){
				buffer.append(" and createtime>=?");
				list.add(visitdto.getStartdate());
			}
			if(visitdto.getEnddate()!=null&&!"".equals(visitdto.getEnddate().trim())){
				buffer.append(" and createtime<=? ");
				list.add(visitdto.getEnddate());  
			}
			if(visitdto.getNum()!=null&&!"".equals(visitdto.getNum().trim())){
				buffer.append(" and visitnum =?");
				list.add(visitdto.getNum());
			}
		}
		return getJdbcTemplate().queryForInt(buffer.toString(), list.toArray());
	}
	 @Override
	public int findVisitTerminalReportTotal(long sgid)
	{
		String sql = "select sum(visitnum) from es_report_terminal_analyse where siteid=?";
		return getJdbcTemplate().queryForInt(sql, new Object[]
		{ sgid });
	}
	
	@Override
    public void insertVisitAnalyse(long sgid,int visitTotalNum,int visitNum,int hdnum,int addVisitNum,String date)
    {
        Object[] params =
        { sgid, visitTotalNum,visitNum,hdnum,addVisitNum,date,visitTotalNum,visitNum,hdnum,addVisitNum};
        getJdbcTemplate().update("insert into esite.es_report_visit_analyse (siteid,visitTotalNum,visitNum,hdnum,addVisitNum,date,createtime) values (?,?,?,?,?,?,now()) ON DUPLICATE KEY UPDATE visitTotalNum=?,visitNum=?,hdnum=?,addVisitNum=?,createtime=now()", params);
    }
	
	@Override
    public void insertHourAnalyse(long sgid,int hour,int visitnum,int hdnum)
    {
        Object[] params =
        { sgid,hour,visitnum,hdnum,visitnum,hdnum};
        getJdbcTemplate().update("insert into esite.es_report_hour_analyse (siteid,hour,visitnum,hdnum,createtime) values (?,?,?,?,now())ON DUPLICATE KEY UPDATE visitnum=?,hdnum=?,createtime=now()", params);
    }
	@Override
    public void insertAreaAnalyse(long sgid,long aid,String area,int vnum,int hnum,Date lasttime)
    {
	    StringBuffer buffer=new StringBuffer();
	    if(vnum==1){
	        buffer.append("insert into esite.es_report_area_analyse (siteid,aid,area,visitnum,hdnum,lastvisittime,createtime) values (?,?,?,?,?,?,now())ON DUPLICATE KEY UPDATE visitnum=visitnum+1,createtime=now(),lastvisittime=?");
	    }else{
	        buffer.append("insert into esite.es_report_area_analyse (siteid,aid,area,visitnum,hdnum,lasthdtime,createtime) values (?,?,?,?,?,?,now())ON DUPLICATE KEY UPDATE hdnum=hdnum+1,createtime=now(),lasthdtime=?");
	    }
        Object[] params =
        { sgid,aid,area,vnum,hnum,lasttime,lasttime};
        getJdbcTemplate().update(buffer.toString(), params);
    }
	
	@Override
    public void insertGenderAnalyse(long sgid,int vnum,int hnum,String gender)
    {
        StringBuffer buffer=new StringBuffer();
        if(vnum==1&&"m".equalsIgnoreCase(gender)){
            buffer.append("insert into esite.es_report_gender_analyse (siteid,vmnum,createtime) values (?,1,now())ON DUPLICATE KEY UPDATE vmnum=vmnum+1,createtime=now()");
        }else if(vnum==1&&"f".equalsIgnoreCase(gender)){
            buffer.append("insert into esite.es_report_gender_analyse (siteid,vfnum,createtime) values (?,1,now())ON DUPLICATE KEY UPDATE vfnum=vfnum+1,createtime=now()");
        }else if(vnum==1&&(gender==null||"".equals(gender))){
            buffer.append("insert into esite.es_report_gender_analyse (siteid,vothernum,createtime) values (?,1,now())ON DUPLICATE KEY UPDATE vothernum=vothernum+1,createtime=now()");
        }else if(hnum==1&&"m".equalsIgnoreCase(gender)){
            buffer.append("insert into esite.es_report_gender_analyse (siteid,hmnum,createtime) values (?,1,now())ON DUPLICATE KEY UPDATE hmnum=hmnum+1,createtime=now()");
        }else if(hnum==1&&"f".equalsIgnoreCase(gender)){
            buffer.append("insert into esite.es_report_gender_analyse (siteid,hfnum,createtime) values (?,1,now())ON DUPLICATE KEY UPDATE hfnum=hfnum+1,createtime=now()");
        }else if(hnum==1&&(gender==null||"".equals(gender))){
            buffer.append("insert into esite.es_report_gender_analyse (siteid,hothernum,createtime) values (?,1,now())ON DUPLICATE KEY UPDATE hothernum=hothernum+1,createtime=now()");
        }
        String sql=buffer.toString();
        if(sql!=null&&!"".equals(sql)){
            Object[] params =
            { sgid};
            getJdbcTemplate().update(sql, params);
        }
    }
	
	@Override
    public void insertHourAnalyse(long sgid,String hour,int vnum,int hnum)
    {
        StringBuffer buffer=new StringBuffer();
        if(vnum==1){
            buffer.append("insert into esite.es_report_hour_analyse (siteid,hour,visitnum,hdnum,createtime) values (?,?,?,?,now())ON DUPLICATE KEY UPDATE visitnum=visitnum+1,createtime=now()");
        }else{
            buffer.append("insert into esite.es_report_hour_analyse (siteid,hour,visitnum,hdnum,createtime) values (?,?,?,?,now())ON DUPLICATE KEY UPDATE hdnum=hdnum+1,createtime=now()");
        }
        Object[] params =
        { sgid,hour,vnum,hnum};
        getJdbcTemplate().update(buffer.toString(), params);
    }

	/**
	 * 查询结果集总数
	 */
	@Override
	public int findVisitReportTotal(long sgid) {
		String sql="select count(*) from (select u.nickname as name,max(v.createtime) as lasttime,max(v.ip) as ip,count(v.uid) num from es_visit_log v join es_user_info u on (v.uid=u.id and v.siteid = u.siteid) join es_site s on u.siteid=s.id join es_site_group g on s.siteid=g.id where 1=1 and g.id=? and s.status != 'DEL' group by u.wbuid) t";
		
		return getJdbcTemplate().queryForInt(sql,new Object[]{sgid});
	}
	
	private static final String FIND_VISIT_LOG_AREA_LIST = "select r.id,r.ip,r.createtime from es_visit_log r left join es_site s on s.id=r.siteid  where r.areaproceestatus='N' and s.siteid=? order by r.createtime";


    @Override
    public List<VisitLog> findVisitLogListAreaBySgid(long sgid) {
        return getJdbcTemplate().query(FIND_VISIT_LOG_AREA_LIST,
                new Object[] { sgid }, new VisitLogAreaRowMapper());
    }
    
    
    
    @Override
    public int updateVisitLogAreaStatus(long id) {
        Object[] params = {id};
        return getJdbcTemplate().update("update es_visit_log set areaproceestatus='C' where id=?", params);
    }
    
    @Override
    public int updateVisitLogGenderStatus(long id) {
        Object[] params = {id};
        return getJdbcTemplate().update("update es_visit_log set genderprocessstatus='C' where id=?", params);
    }
    @Override
    public int updateVisitLogHourStatus(long id) {
        Object[] params = {id};
        return getJdbcTemplate().update("update es_visit_log set hourprocessstatus='C' where id=?", params);
    }
    class VisitLogAreaRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            VisitLog log=new VisitLog();
            log.setId(rs.getLong("id"));
            log.setIp(rs.getString("ip"));
            log.setCreateTime(rs.getTimestamp("createtime"));
            return log;
        }
    }
    private static final String FIND_VISIT_LOG_HOUR_LIST = "select v.id,v.createtime,s.siteid from es_visit_log v left join es_site s on v.siteid=s.id where v.hourprocessstatus='N' limit ? ";


    @Override
    public List<VisitLog> findNoProcessHourStatusVisitLogList(int size) {
        Object[] params={size};
        return getJdbcTemplate().query(FIND_VISIT_LOG_HOUR_LIST, params,new RowMapper(){
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                VisitLog vl = new VisitLog();
                vl.setId(rs.getLong("id"));
                vl.setSiteid(rs.getLong("siteid"));
                vl.setHour(rs.getString("createtime").substring(11, 13));
                return vl;
            }
            
        });
    }
    
    private static final String FIND_VISIT_LOG_LIST_AREA_LIMIT = "select v.id,v.createtime,v.ip,s.siteid from es_visit_log v left join es_site s on v.siteid=s.id where v.areaproceestatus='N' limit ?";


    @Override
    public List<VisitLog> findNoProcessAreaStatusVisitLogList(int size) {
        Object[] params={size};
        return getJdbcTemplate().query(FIND_VISIT_LOG_LIST_AREA_LIMIT, params,new RowMapper(){
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                VisitLog vl = new VisitLog();
                vl.setId(rs.getLong("id"));
                vl.setSiteid(rs.getLong("siteid"));
                vl.setIp(rs.getString("ip"));
                vl.setCreateTime(rs.getTimestamp("createtime"));
                return vl;
            }
            
        });
    }
    
    private static final String FIND_VISIT_LOG_LIST_GENDER_LIMIT = "select v.id,user.gender,s.siteid from es_visit_log v left join es_site s on v.siteid=s.id left join es_user_info u on (v.uid=u.id and v.siteid = u.siteid) left join es_sina_user user on user.wbuid=u.wbuid where v.genderprocessstatus='N' limit ?";


    @Override
    public List<VisitLog> findNoProcessGenderStatusVisitLogList(int size) {
        Object[] params={size};
        return getJdbcTemplate().query(FIND_VISIT_LOG_LIST_GENDER_LIMIT, params,new RowMapper(){
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                VisitLog vl = new VisitLog();
                vl.setId(rs.getLong("id"));
                vl.setSiteid(rs.getLong("siteid"));
                vl.setGender(rs.getString("gender"));
                return vl;
            }
            
        });
    }

	private static final String FIND_VISIT_LOG_NOT_PROCESS="select l.id,l.createtime,s.id,user.wbuid,t.cid,user.fensishu from esite.es_visit_log l left join es_site s on l.siteid=s.id join esite.es_user_info i on (l.uid = i.id and i.siteid=s.id) join esite.es_sina_token t on l.siteid = t.siteid left join es_sina_user user on user.wbuid=i.wbuid where l.fansprocessstatus ='N' and t.type='Z' and i.status='NOR' limit ?";
	@Override
	public List<VisitLog> findVisitLogNotProcess(int size) {
		Object[] params={size};
		return getJdbcTemplate().query(FIND_VISIT_LOG_NOT_PROCESS, params,new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				VisitLog vl = new VisitLog();
				vl.setId(rs.getLong("id"));
				vl.setSiteid(rs.getLong("s.id"));
				vl.setWbuid(rs.getLong("wbuid"));
				vl.setCid(rs.getLong("cid"));
				vl.setFansnum(rs.getInt("fensishu"));
				vl.setCreateTime(rs.getTimestamp("createtime"));
				return vl;
			}
			
		});
	}

	private static final String UPDATE_VISIT_LOG_FANS_PROCESS="update esite.es_visit_log set fansprocessstatus = ? where id = ?";
	@Override
	public int updateVisitLogFansprocessstatus(long id, String type) {
		Object[] params={type,id};
		return getJdbcTemplate().update(UPDATE_VISIT_LOG_FANS_PROCESS, params);
	}

	/**
	 * 访问用户表查看详情
	 */
	@Override
	public List<VisitReportDetail> findVisitReportDetail(long sgid,
			String wbuid, int start, int size) {
		String query="select su.nickname name,v.uid uid,v.terminal terminal,v.createtime visittime from es_visit_log v join es_user_info u on (v.uid=u.id and v.siteid = u.siteid)join es_sina_user su on su.wbuid=u.wbuid join es_site s on u.siteid=s.id where s.siteid=? and u.wbuid=? limit ?,?  ";
		return getJdbcTemplate().query(query, new Object[]{sgid,wbuid,start,size},new VisitReportDetailRowMapper());
	}
	class VisitReportDetailRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
        	VisitReportDetail detail=new VisitReportDetail();
        	DateUtil util=new DateUtil();
        	detail.setNickname(rs.getString("name"));
        	detail.setUid(rs.getLong("uid"));
        	detail.setTerminal(rs.getString("terminal"));
        	detail.setVisittime(util.getDateTimeString(rs.getTimestamp("visittime")));
        	return detail;
        }
    }
	@Override
	public List<VisitNum> findVisitNumListByOwner(long ownerid) {
//		String query="select count(wbuid) pernum,visitnum as num from es_report_visitnum_analyse a join es_site_group g on a.siteid=g.id where g.status!='DEL' and g.ownerid=? and visitnum<5 group by visitnum limit 4 ";
		String query="select count(wbuid) pernum,visitnum as num from es_report_visitnum_analyse a join es_site g on a.siteid=g.id where g.status!='DEL' and g.ownerid=? and visitnum<5 group by visitnum limit 4 ";
		return getJdbcTemplate().query(query, new Object[]{ownerid},new VisitNumRowMapper());
	}
	@Override
	public List<VisitNum> findVisitNumListBySgid(long sgid) {
		String query="select count(wbuid) pernum,visitnum as num from es_report_visitnum_analyse where siteid =? and visitnum<5 group by visitnum limit 4 ";
		return getJdbcTemplate().query(query, new Object[]{sgid},new VisitNumRowMapper());
	}
	@Override
	public List<VisitNum> findHdNumListByOwner(long ownerid) {
//		String query="select count(wbuid) pernum,hdnum as num from es_report_wbhdnum_analyse a join es_site_group g on a.siteid=g.id where g.status!='DEL' and g.ownerid=? and hdnum<5 group by hdnum limit 4 ";
		String query="select count(wbuid) pernum,hdnum as num from es_report_wbhdnum_analyse a join es_site g on a.siteid=g.id where g.status!='DEL' and g.ownerid=? and hdnum<5 group by hdnum limit 4 ";
		return getJdbcTemplate().query(query, new Object[]{ownerid},new VisitNumRowMapper());
	}
	@Override
	public List<VisitNum> findHdNumListBySgid(long sgid) {
		String query="select count(wbuid) pernum,hdnum as num from es_report_wbhdnum_analyse where siteid=? and hdnum<5 group by hdnum limit 4 ";
		return getJdbcTemplate().query(query, new Object[]{sgid},new VisitNumRowMapper());
	}
	class VisitNumRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
        	VisitNum num=new VisitNum();
        	num.setNum(rs.getString("num"));
        	num.setPernum(rs.getInt("pernum"));
        	return num;
        }
    }

	/**
	 * 统计出访问的次数
	 * @param sgid
	 * @param wbuid
	 * @return
	 */
	public int countVisitNum(long sgid,String wbuid){
		String sql="select count(v.uid) from es_visit_log v join es_user_info u on (v.uid=u.id and v.siteid = u.siteid) join es_site s on u.siteid=s.id where s.siteid=? and u.wbuid=? ";
		return getJdbcTemplate().queryForInt(sql,new Object[]{sgid,wbuid});
	}
	/**
	 * 访问用户表查看详情总数，用于分页
	 */
	@Override
	public int findVisitReportDetailTotal(long sgid, String wbuid) {
		String sql="select count(*) from(select u.nickname name,v.uid,v.terminal terminal,v.createtime visittime from es_visit_log v join es_user_info u on (v.uid=u.id and v.siteid = u.siteid) join es_site s on u.siteid=s.id where s.siteid=? and u.wbuid=?) t";
		return getJdbcTemplate().queryForInt(sql,new Object[]{sgid,wbuid});
	}
	
	public int findVisitLogTotalNum(long sgid){
        String sql="select sum(visitTotalNum) from es_report_visit_analyse where siteid=? ";
        return getJdbcTemplate().queryForInt(sql,new Object[]{sgid});
    }
	
	public int findLoginVisitLogTotalNum(long sgid){
        String sql="select sum(visitNum) from es_report_visit_analyse where siteid=? ";
        return getJdbcTemplate().queryForInt(sql,new Object[]{sgid});
    }
	
	public int findHdTotalNum(long sgid){
        String sql="select sum(hdnum) from es_report_visit_analyse where siteid=? ";
        return getJdbcTemplate().queryForInt(sql,new Object[]{sgid});
    }
	public int findTodayDddNum(long sgid){
        String sql="select sum(addVisitNum) from es_report_visit_analyse where siteid=? and date=date(now()) ";
        return getJdbcTemplate().queryForInt(sql,new Object[]{sgid});
    }
	
	@Override
    public int findJoinNum(long sgid) {
        String sql="select sum(joinnum) from esite.es_report_activity_analyse a join esite.es_activity t on t.id=a.activityid join esite.es_site s on s.id=t.siteid where s.siteid=? ";
        return getJdbcTemplate().queryForInt(sql,new Object[]{sgid});
    }
	@Override
    public int findJoinSucNum(long sgid) {
	    String sql="select sum(sucjoinnum) from esite.es_report_activity_analyse a join esite.es_activity t on t.id=a.activityid join esite.es_site s on s.id=t.siteid where s.siteid=? ";
        return getJdbcTemplate().queryForInt(sql,new Object[]{sgid});
    }
	
	@Override
    public int findVisitNumBySgid(long sgid) {
//	    String sql="select sum(visitnum) from esite.es_report_visitnum_analyse where siteid=? ";
		String sql="select sum(visitnum) from esite.es_report_visitnum_analyse where siteid=? ";
        return getJdbcTemplate().queryForInt(sql,new Object[]{sgid});
    }
	@Override
    public int findVisitPerNumBySgid(long sgid) {
//	    String sql="select count(wbuid) from esite.es_report_visitnum_analyse where siteid=? ";
		String sql="select count(wbuid) from esite.es_report_visitnum_analyse where siteid=? ";
        return getJdbcTemplate().queryForInt(sql,new Object[]{sgid});
    }
	@Override
	public int findVisitPerNumByNumOwner(long owner,int num) {
		StringBuffer buffer=new StringBuffer();
		if(num!=5){
//			buffer.append("select count(a.id) from es_report_visitnum_analyse a join es_site_group g on a.siteid=g.id where g.status!='DEL' and visitnum=? and g.ownerid=? ");
			buffer.append("select count(a.id) from es_report_visitnum_analyse a join es_site g on a.siteid=g.id where g.status!='DEL' and visitnum=? and g.ownerid=? ");
		}else{
//			buffer.append("select count(a.id) from es_report_visitnum_analyse a join es_site_group g on a.siteid=g.id where g.status!='DEL' and visitnum >=? and g.ownerid=? ");
			buffer.append("select count(a.id) from es_report_visitnum_analyse a join es_site g on a.siteid=g.id where g.status!='DEL' and visitnum >=? and g.ownerid=? ");
		}
		return getJdbcTemplate().queryForInt(
				buffer.toString(), new Object[] {num,owner});
	}
	@Override
	public int findVisitNumTotalOwner(long owner) {
//		String sql="select count(a.id) from es_report_visitnum_analyse a join es_site_group g on a.siteid=g.id where g.status!='DEL' and g.ownerid=? ";
		String sql="select count(a.id) from es_report_visitnum_analyse a join es_site g on a.siteid=g.id where g.status!='DEL' and g.ownerid=? ";
		return getJdbcTemplate().queryForInt(sql, new Object[] {owner});
	}
	@Override
	public int findVisitPerNumByNum(long sgid,int num) {
		StringBuffer buffer=new StringBuffer();
		if(num!=5){
			buffer.append("select count(id) from es_report_visitnum_analyse where visitnum=? and siteid=? ");
		}else{
			buffer.append("select count(id) from es_report_visitnum_analyse where visitnum >=? and siteid=? ");
		}
		return getJdbcTemplate().queryForInt(
				buffer.toString(), new Object[] {num,sgid});
	}
}

class VisitLogRowMapper implements RowMapper
{

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException
	{
		VisitLog vl = new VisitLog();
		vl.setId(rs.getLong("id"));
		vl.setUid(rs.getString("uid"));
		vl.setNickname(rs.getString("nickname"));
		vl.setSiteid(rs.getLong("siteid"));
		vl.setCreatetime(DateUtil.getDateTimeString(rs.getTimestamp("createtime")));
		vl.setIp(rs.getString("ip"));
		vl.setUserAgent(rs.getString("userAgent"));
		return vl;
	}

}


class VisitLogRowMapper2 implements RowMapper
{

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException
	{
		VisitLog vl = new VisitLog();
		vl.setId(rs.getLong("id"));
		vl.setUid(rs.getString("wbuid"));
		vl.setNickname(rs.getString("nickname"));
		vl.setSiteid(rs.getLong("siteid"));
		vl.setCreatetime(DateUtil.getDateTimeString(rs.getTimestamp("createtime")));
		vl.setIp(rs.getString("ip"));
		vl.setUserAgent(rs.getString("userAgent"));
		return vl;
	}

}


