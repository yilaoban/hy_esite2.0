package com.huiyee.esite.dao.imp;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IDynamicActionRecordDao;
import com.huiyee.esite.dao.imp.VisitDaoImpl.AreaReport1;
import com.huiyee.esite.dto.DanymicActionRecord;
import com.huiyee.esite.dto.DanymicAton;
import com.huiyee.esite.dto.DanymicUserDetail;
import com.huiyee.esite.dto.DanymicUserRecord;
import com.huiyee.esite.dto.DanymicUserSiftDto;
import com.huiyee.esite.dto.ReportArea;
import com.huiyee.esite.dto.ReportHdNum;
import com.huiyee.esite.dto.ReportSource;
import com.huiyee.esite.dto.ReportTerminalAnalyse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import com.huiyee.esite.model.AreaAnalysis;
import com.huiyee.esite.model.HourAnalyse;
import com.huiyee.esite.model.ReportGenderAnalyse;
import com.huiyee.esite.model.UserInfo;
import com.huiyee.esite.model.VisitAnalyse;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.esite.util.ToolUtils;

public class DynamicActionRecordDaoImpl extends AbstractDao implements
		IDynamicActionRecordDao {
	private static final String FIND_DYNAMIC_COUNT_BY_SITE_GROUP = "select count(distinct(r.wbuid)) from es_feature_dynamic_action_record r left join es_page p on r.pageid=p.id left join es_site s on s.id=p.siteid where s.sitegroupid=? and DATE(r.createtime) =?";

	@Override
	public int findDynamicNumBySiteGroup(long sgid, Date date) {
		return getJdbcTemplate().queryForInt(FIND_DYNAMIC_COUNT_BY_SITE_GROUP,
				new Object[] { sgid, date });
	}
    
    private static final String FIND_DYNAMIC_COUNT_BY_SITE_GROUP_HOUR = "select count(r.wbuid) from es_feature_dynamic_action_record r left join es_page p on r.pageid=p.id left join es_site s on s.id=p.siteid where s.sitegroupid=? and HOUR(r.createtime) =?";

    @Override
    public int findDynamicNumByHour(long sgid, int hour) {
        return getJdbcTemplate().queryForInt(FIND_DYNAMIC_COUNT_BY_SITE_GROUP_HOUR, new Object[] { sgid, hour });
    }

    private static final String FIND_DYNAMIC_COUNT_BY_SITE_GROUP_PAD = "select count(r.id) from es_feature_dynamic_action_record r left join es_page p on r.pageid=p.id left join es_site s on s.id=p.siteid where s.sitegroupid=? and r.terminal='A'";
	@Override
	public int findDynamicNumBySiteGroupPad(long sgid) {
		return getJdbcTemplate().queryForInt(
				FIND_DYNAMIC_COUNT_BY_SITE_GROUP_PAD, new Object[] { sgid });
	}

	private static final String FIND_DYNAMIC_COUNT_BY_SITE_GROUP_PC = "select count(r.id) from es_feature_dynamic_action_record r left join es_page p on r.pageid=p.id left join es_site s on s.id=p.siteid where s.sitegroupid=? and r.terminal='C'";

	@Override
	public int findDynamicNumBySiteGroupPC(long sgid) {
		return getJdbcTemplate().queryForInt(
				FIND_DYNAMIC_COUNT_BY_SITE_GROUP_PC, new Object[] { sgid });
	}

	private static final String FIND_DYNAMIC_COUNT_BY_SITE_GROUP_PHONE = "select count(r.id) from es_feature_dynamic_action_record r left join es_page p on r.pageid=p.id left join es_site s on s.id=p.siteid where s.sitegroupid=? and r.terminal='P'";

	@Override
	public int findDynamicNumBySiteGroupPhone(long sgid) {
		return getJdbcTemplate().queryForInt(
				FIND_DYNAMIC_COUNT_BY_SITE_GROUP_PHONE, new Object[] { sgid });
	}
	
	
	@Override
	public int findHdPerNumByNum(long sgid,int num) {
		StringBuffer buffer=new StringBuffer();
		if(num!=5){
			buffer.append("select count(id) from es_report_wbhdnum_analyse where hdnum=? and siteid=? ");
		}else{
			buffer.append("select count(id) from es_report_wbhdnum_analyse where hdnum >=? and siteid=? ");
		}
		return getJdbcTemplate().queryForInt(
				buffer.toString(), new Object[] {num,sgid});
	}
	@Override
	public int findHdNumTotalOwner(long owner) {
//		String sql="select count(a.id) from es_report_wbhdnum_analyse a join es_site_group g on a.sitegroupid=g.id where g.status!='DEL' and g.ownerid=? ";
		String sql="select count(a.id) from es_report_wbhdnum_analyse a join es_site g on a.siteid=g.id where g.status!='DEL' and g.ownerid=? ";
		return getJdbcTemplate().queryForInt(sql, new Object[] {owner});
	}
	@Override
	public int findHdPerNumByNumOwner(long owner,int num) {
		StringBuffer buffer=new StringBuffer();
		if(num!=5){
//			buffer.append("select count(a.id) from es_report_wbhdnum_analyse a join es_site_group g on a.sitegroupid=g.id where g.status!='DEL' and hdnum=? and g.ownerid=? ");
			buffer.append("select count(a.id) from es_report_wbhdnum_analyse a join es_site g on a.siteid=g.id where g.status!='DEL' and hdnum=? and g.ownerid=? ");
		}else{
//			buffer.append("select count(a.id) from es_report_wbhdnum_analyse a join es_site_group g on a.sitegroupid=g.id where g.status!='DEL' and hdnum >=? and g.ownerid=? ");
			buffer.append("select count(a.id) from es_report_wbhdnum_analyse a join es_site g on a.siteid=g.id where g.status!='DEL' and hdnum >=? and g.ownerid=? ");
		}
		return getJdbcTemplate().queryForInt(
				buffer.toString(), new Object[] {num,owner});
	}

	private static final String FIND_DYNAMIC_AREA_BYSGID = "select area,hdnum from es_report_area_analyse where siteid=? and hdnum>0 order by hdnum desc limit 10 ";

	@Override
	public List<ReportArea> findDymamicAreaBySiteGroup(long sgid) {
		return getJdbcTemplate().query(FIND_DYNAMIC_AREA_BYSGID,
				new Object[] { sgid }, new HdAreaReportRowMaper());
	}
//	private static final String FIND_DYNAMIC_AREA_BYOWNER = "select area,sum(hdnum)as hdnum from es_report_area_analyse a join es_site_group g on a.sitegroupid=g.id where g.status!='DEL' and g.ownerid=? and hdnum>0 group by aid order by hdnum desc limit 10 ";
	private static final String FIND_DYNAMIC_AREA_BYOWNER = "select area,sum(hdnum)as hdnum from es_report_area_analyse a join es_site g on a.siteid=g.id where g.status!='DEL' and g.ownerid=? and hdnum>0 group by aid order by hdnum desc limit 10 ";
	@Override
	public List<ReportArea> findDymamicAreaBySiteOwner(long owner) {
		return getJdbcTemplate().query(FIND_DYNAMIC_AREA_BYOWNER,
				new Object[] { owner }, new HdAreaReportRowMaper());
	}
	
	private static final String FIND_DYNAMIC_HD_NUM_BYSGID = "select nickname,hdnum,lasthdtime from es_report_wbhdnum_analyse where sitegroupid=? order by hdnum desc limit 10 ";

	@Override
	public List<ReportHdNum> findDymamicHdNumBySiteGroup(long sgid) {
		return getJdbcTemplate().query(FIND_DYNAMIC_HD_NUM_BYSGID,
				new Object[] { sgid }, new HdNumReportRowMaper());
	}
	
	private static final String FIND_DYNAMIC_HD_NUM_LIST_BYSGID = "select nickname,hdnum,lasthdtime from es_report_wbhdnum_analyse where sitegroupid=? order by hdnum desc limit ?,? ";

	@Override
	public List<ReportHdNum> findDymamicHdNumListBySiteGroup(long sgid,int start,int size) {
		return getJdbcTemplate().query(FIND_DYNAMIC_HD_NUM_LIST_BYSGID,
				new Object[] { sgid,start,size }, new HdNumReportRowMaper());
	}
	
	private static final String FIND_DYNAMIC_HD_NUM_TOTAL_BYSGID = "select count(id) from es_report_wbhdnum_analyse where siteid=? and hdnum!=0 ";

	@Override
	public int findDymamicHdNumTotalBySiteGroup(long sgid) {
		return getJdbcTemplate().queryForInt(FIND_DYNAMIC_HD_NUM_TOTAL_BYSGID,
				new Object[] { sgid});
	}
	
	private static final String FIND_DYNAMIC_HD_NUM_TOTAL = "select sum(hdnum) from es_report_wbhdnum_analyse where siteid=? ";

	@Override
	public int findDymamicHdNumTotal(long sgid) {
		return getJdbcTemplate().queryForInt(FIND_DYNAMIC_HD_NUM_TOTAL,
				new Object[] { sgid});
	}
	
	private static final String FIND_VISIT_AREA_BYSGID = "select area,visitnum from es_report_area_analyse where sitegroupid=? and visitnum>0 order by visitnum desc limit 10 ";

    @Override
    public List<ReportArea> findVisitAreaBySiteGroup(long sgid) {
        return getJdbcTemplate().query(FIND_VISIT_AREA_BYSGID,
                new Object[] { sgid }, new VisitAreaReportRowMaper());
    }

	private static final String FIND_DYNAMIC_SOURCE_BYSGID = "select count(r.id) num,source from es_feature_dynamic_action_record r left join es_page p on r.pageid=p.id left join es_site s on s.id=p.siteid  where s.sitegroupid=? group by r.source order by r.id desc limit 10 ";

	@Override
	public List<ReportSource> findDymamicSourceBySiteGroup(long sgid) {
		return getJdbcTemplate().query(FIND_DYNAMIC_SOURCE_BYSGID,
				new Object[] { sgid }, new ReportSourceRowMaper());
	}

	private static final String FIND_DYNAMIC_TOTAL_BY_SITE_GROUP = "select count(distinct(r.wbuid)) from es_feature_dynamic_action_record r left join es_page p on r.pageid=p.id left join es_site s on s.id=p.siteid where s.sitegroupid=?";

	@Override
	public int findDynamicTotalBySiteGroup(long sgid) {
		return getJdbcTemplate().queryForInt(FIND_DYNAMIC_TOTAL_BY_SITE_GROUP,
				new Object[] { sgid });
	}

	class HdAreaReportRowMaper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			ReportArea area = new ReportArea();
			area.setArea(rs.getString("area"));
			area.setNum(rs.getInt("hdnum"));
			return area;
		}
	}
	class HdNumReportRowMaper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			ReportHdNum hdnum = new ReportHdNum();
			hdnum.setHdnum(rs.getInt("hdnum"));
			hdnum.setLasthdtime(rs.getTimestamp("lasthdtime"));
			hdnum.setNickname(rs.getString("nickname"));
			return hdnum;
		}
	}
	class VisitAreaReportRowMaper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            ReportArea area = new ReportArea();
            area.setArea(rs.getString("area"));
            area.setNum(rs.getInt("visitnum"));
            return area;
        }
    }

	class ReportSourceRowMaper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			ReportSource source = new ReportSource();
			source.setSource(rs.getString("source"));
			source.setNum(rs.getInt("num"));
			return source;
		}
	}
    private static final String SHOW_AREA_BY_IP = "select area from es_ips where ip1<=? and ip2>=?";
	@Override
	public String findAreaByIp(String ip) {
		long iplong = ToolUtils.getIpByStr(ip);
		if (ip != null) {
			return (String) getJdbcTemplate().queryForObject(SHOW_AREA_BY_IP,
					new Object[] { iplong, iplong }, String.class);
		}
		return null;
	}

	// 互动
	private static final String SHOW_DYNAMIC_BY_ID = "select area,hdnum,lasthdtime from es_report_area_analyse where sitegroupid=? and hdnum>0 group by id order by hdnum desc limit ?,?";

	private static final String FIND_DYNAMIC_COUNT_BY_SITE_GROUP_GENDER = "select count(distinct(r.wbuid)) from es_feature_dynamic_action_record r left join es_page p on r.pageid=p.id left join es_site s on s.id=p.siteid left join es_user_info u on (u.wbuid=r.wbuid and s.id=u.siteid) where s.sitegroupid=? and u.gender=?";

	@Override
	public int findDynamicNumBySiteGroupGender(long sgid, String gender) {
		return getJdbcTemplate().queryForInt(
				FIND_DYNAMIC_COUNT_BY_SITE_GROUP_GENDER,
				new Object[] { sgid, gender });
	}

	@Override
	public List<DanymicUserRecord> findDanymicRecordList(long sgid,
			DanymicUserSiftDto siftDto, int start, int size) {
		String sql = "select * from es_report_wbhdnum_analyse where sitegroupid=? ";
		StringBuffer buffer = new StringBuffer();
		buffer.append(sql);
		List<Object> list = new ArrayList<Object>();
		list.add(sgid);
		if (siftDto != null) {
			if (siftDto.getAction() > 0) {
                buffer.append(" and action=? ");
                list.add(siftDto.getAction());
			}
			if (siftDto.getStartTime() != null
					&& !"".equals(siftDto.getStartTime().trim())) {
				buffer.append(" and createtime>= ? ");
				list.add(siftDto.getStartTime());
			}
			if (siftDto.getEndTime() != null
					&& !"".equals(siftDto.getEndTime().trim())) {
				buffer.append(" and createtime<= ? ");
				list.add(siftDto.getEndTime());
			}
			if (siftDto.getDcount()!=null&&!"".equals(siftDto.getDcount())) {
				buffer.append(" and hdnum=? ");
				list.add(siftDto.getDcount());
			}
		}
		buffer.append("order by lasthdtime desc limit ?,? ");
		list.add(start);
		list.add(size);
		return getJdbcTemplate().query(buffer.toString(), list.toArray(),
				new DanymicRecordRowMaper());
	}

	@Override
	public List<DanymicUserDetail> findDanymicRecordDetailList(long sgid,
			String wbuid, DanymicUserSiftDto siftDto, int start, int size) {
		String sql = "select r.action, f.name as actiontype,r.entityid,r.createtime,r.terminal from es_feature_dynamic_action_record r left join es_feature f on r.action=f.id left join es_page p on r.pageid=p.id left join es_site s on s.id=p.siteid  where s.sitegroupid=? and r.wbuid=? ";
		StringBuffer buffer = new StringBuffer();
		buffer.append(sql);
		List<Object> list = new ArrayList<Object>();
		list.add(sgid);
		list.add(wbuid);
		if (siftDto != null) {
		    if (siftDto.getAction() > 0) {
                buffer.append(" and r.action=? ");
                list.add(siftDto.getAction());
            }
			if (siftDto.getStartTime() != null
					&& !"".equals(siftDto.getStartTime().trim())) {
				buffer.append(" and r.createtime>= ? ");
				list.add(siftDto.getStartTime());
			}
			if (siftDto.getEndTime() != null
					&& !"".equals(siftDto.getEndTime().trim())) {
				buffer.append(" and r.createtime<= ? ");
				list.add(siftDto.getEndTime());
			}
		}
		buffer.append("order by r.createtime desc limit ?,? ");
		list.add(start);
		list.add(size);
		return getJdbcTemplate().query(buffer.toString(), list.toArray(),
				new DanymicRecordDetailRowMaper());
	}

	@Override
	public int findDanymicRecordDetailTotal(long sgid, String wbuid,
			DanymicUserSiftDto siftDto) {
		String sql = "select count(*) from (select r.id from es_feature_dynamic_action_record r left join es_page p on r.pageid=p.id left join es_site s on s.id=p.siteid where s.sitegroupid=? and r.wbuid=? ";
		StringBuffer buffer = new StringBuffer();
		buffer.append(sql);
		List<Object> list = new ArrayList<Object>();
		list.add(sgid);
		list.add(wbuid);
		if (siftDto != null) {
		    if (siftDto.getAction() > 0) {
                buffer.append(" and r.action=? ");
                list.add(siftDto.getAction());
            }
			if (siftDto.getStartTime() != null
					&& !"".equals(siftDto.getStartTime().trim())) {
				buffer.append(" and r.createtime>= ? ");
				list.add(siftDto.getStartTime());
			}
			if (siftDto.getEndTime() != null
					&& !"".equals(siftDto.getEndTime().trim())) {
				buffer.append(" and r.createtime<= ? ");
				list.add(siftDto.getEndTime());
			}
		}
		buffer.append(")T");
		return getJdbcTemplate().queryForInt(buffer.toString(), list.toArray());
	}

	@Override
	public int findDanymicRecordListTotal(long sgid, DanymicUserSiftDto siftDto) {
		String sql = "select count(id) from es_report_wbhdnum_analyse where sitegroupid=? ";
		StringBuffer buffer = new StringBuffer();
		buffer.append(sql);
		List<Object> list = new ArrayList<Object>();
		list.add(sgid);
		if (siftDto != null) {
			if (siftDto.getAction() > 0) {
			    buffer.append(" and action=? ");
				list.add(siftDto.getAction());
			}
			if (siftDto.getStartTime() != null
					&& !"".equals(siftDto.getStartTime().trim())) {
				buffer.append(" and createtime>= ? ");
				list.add(siftDto.getStartTime());
			}
			if (siftDto.getEndTime() != null
					&& !"".equals(siftDto.getEndTime().trim())) {
				buffer.append(" and createtime<= ? ");
				list.add(siftDto.getEndTime());
			}
			if (siftDto.getDcount()!=null&&!"".equals(siftDto.getDcount())) {
				buffer.append(" and hdnum=?");
				list.add(siftDto.getDcount());
			}
		}
		return getJdbcTemplate().queryForInt(buffer.toString(), list.toArray());
	}

	@Override
	public List<DanymicUserRecord> findDanymicRecordListByNickName(long sgid,
			String nickname, int start, int size) {
		String sql = "select * from es_report_wbhdnum_analyse where sitegroupid=? and nickname like '%"+ nickname+ "%' order by lasthdtime desc limit ?,? ";
		return getJdbcTemplate().query(sql, new Object[] { sgid, start, size },
				new DanymicRecordRowMaper());

	}

	@Override
	public int findDanymicRecordListTotalByNickName(long sgid, String nickname) {
		String sql = "select count(id) from es_report_wbhdnum_analyse where sitegroupid=? and nickname like '%"+ nickname + "%'";
		return getJdbcTemplate().queryForInt(sql, new Object[] { sgid });
	}

	class DanymicRecordRowMaper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			DanymicUserRecord record = new DanymicUserRecord();
			record.setActiontype(rs.getString("actiontype"));
			record.setAction(rs.getLong("action"));
			record.setIp(rs.getString("ip"));
			record.setLastactiontime(rs.getTimestamp("lasthdtime"));
			record.setNickname(rs.getString("nickname"));
			record.setDcount(rs.getInt("hdnum"));
			record.setWbuid(rs.getString("wbuid"));
			record.setSiteid(rs.getLong("siteid"));
			record.setTerminal(rs.getString("terminal"));
			return record;
		}
	}

	private static final String ADD_DYNAMIC_ACTION_RECORD = "insert into esite.es_feature_dynamic_action_record(pageid,wbuid,action,entityid,source,terminal,ip,createtime)values(?,?,?,?,?,?,?,now())";

	@Override
	public int addDynamicActionRecord(long pageid, long wbuid, long action,
			long entityid, String source, String ip, String terminal) {
		Object[] params = { pageid, wbuid, action, entityid, source, terminal,
				ip };
		return getJdbcTemplate().update(ADD_DYNAMIC_ACTION_RECORD, params);
	}

	class DanymicRecordDetailRowMaper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			DanymicUserDetail detail = new DanymicUserDetail();
			detail.setActiontype(rs.getString("actiontype"));
			detail.setAction(rs.getLong("action"));
			String cratetime = rs.getString("createtime");
			if (cratetime != null && !"".equals(cratetime)) {
				detail.setCreatetime(cratetime.substring(0, 19));
			} else {
				detail.setCreatetime("");
			}
			detail.setEntityid(rs.getLong("entityid"));
			detail.setTerminal(rs.getString("terminal"));
			return detail;
		}
	}

	@Override
	public UserInfo findUserInofByUid(long uid) {
		List<UserInfo> list = getJdbcTemplate().query(
				"select * from esite.es_user_info where id = ?",
				new Object[] { uid }, new UserInfoRowMapper());
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	class UserInfoRowMapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			UserInfo userinfo = new UserInfo();
			userinfo.setId(rs.getLong("id"));
			userinfo.setSiteid(rs.getLong("siteid"));
			userinfo.setEmail(rs.getString("email"));
			userinfo.setPhone(rs.getString("phone"));
			userinfo.setWbuid(rs.getString("wbuid"));
			userinfo.setNickname(rs.getString("nickname"));
			userinfo.setUrl(rs.getString("url"));
			userinfo.setBiaoqian(rs.getString("biaoqian"));
			userinfo.setFensishu(rs.getInt("fensishu"));
			userinfo.setWeiboshu(rs.getInt("weiboshu"));
			userinfo.setCreatetime(DateUtil.getDateTimeString(rs
					.getTimestamp("createtime")));
			return userinfo;
		}
	}
	private static final String FIND_VISIT_ANALYSE = "select * from esite.es_report_visit_analyse where siteid=? limit 14 ";
	 @Override
    public List<VisitAnalyse> findVisitAnalyseByDate(long sgid) {
        return getJdbcTemplate().query(FIND_VISIT_ANALYSE, new Object[] { sgid}, new VisitAnalyseRowMapper());
    }
	 
	private static final String FIND_VISIT_ANALYSE_BY_DATE = "select * from esite.es_report_visit_analyse where sitegroupid=? and date=? ";
     @Override
    public VisitAnalyse findVisitAnalyse(long sgid,String date) {
        List<VisitAnalyse> list= getJdbcTemplate().query(FIND_VISIT_ANALYSE_BY_DATE, new Object[] { sgid, date }, new VisitAnalyseRowMapper());
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
     
    private static final String FIND_HOUR_ANALYSE_BY_HOUR = "select * from esite.es_report_hour_analyse where sitegroupid=? and hour=? ";
     @Override
    public HourAnalyse findHourAnalyse(long sgid,int hour) {
        List<HourAnalyse> list= getJdbcTemplate().query(FIND_HOUR_ANALYSE_BY_HOUR, new Object[] { sgid, hour }, new HourAnalyseRowMapper());
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
	
    //互动
    class HourAnalyseRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            HourAnalyse analyse = new HourAnalyse();
            analyse.setHdNum(rs.getInt("hdnum"));
            analyse.setHour(rs.getInt("hour"));
            analyse.setVisitNum(rs.getInt("visitnum"));
            return analyse;
        }
    }
    
    private static final String FIND_HOUR_ANALYSE_LIST= "select * from esite.es_report_hour_analyse where siteid=? ";

    @Override
    public List<HourAnalyse> findHourAnalyseBySgid(long sgid) {
        return getJdbcTemplate().query(FIND_HOUR_ANALYSE_LIST, new Object[] { sgid},
                new HourAnalyseRowMapper());
    }

	// 互动
	@Override
	public List<AreaAnalysis> showDynamicBySiteGroupId(long sgid, int start,
			int size) {

		return getJdbcTemplate().query(SHOW_DYNAMIC_BY_ID,
				new Object[] { sgid, start, size }, new AreaReport());
	}
	 private static final String SHOW_DYNAMICAREA_BY_OWNER = "select area,sum(hdnum) hdnum,lasthdtime from es_report_area_analyse a join es_site_group g on g.id=a.sitegroupid where g.ownerid=? and hdnum>0 group by a.aid order by hdnum desc limit ?,?";

	@Override
	public List<AreaAnalysis> findHdAreaOwner(long owner, int start, int size)
	{
		return getJdbcTemplate().query(SHOW_DYNAMICAREA_BY_OWNER, new Object[]
		{ owner, start, size }, new AreaReport());
	}


	class AreaReport implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			AreaAnalysis areais = new AreaAnalysis();
			DateUtil util = new DateUtil();
			areais.setArea(rs.getString("area"));
            areais.setNum(rs.getInt("hdnum"));
            areais.setLasttime(util.getDateTimeString(rs.getTimestamp("lasthdtime")));

			return areais;
		}
	}
	class VisitAnalyseRowMapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			VisitAnalyse analyse = new VisitAnalyse();
			analyse.setAddVisitNum(rs.getInt("addVisitNum"));
			analyse.setDate(rs.getString("date"));
			analyse.setHdnum(rs.getInt("hdnum"));
			analyse.setVisitTotalNum(rs.getInt("visitTotalNum"));
			analyse.setVisitNum(rs.getInt("visitNum"));
			return analyse;
		}
	}

	//查询互动总数
	public int sumHdNum(long sgid){
		String sql="select sum(hdnum) from es_report_hour_analyse where sitegroupid=?";
		return getJdbcTemplate().queryForInt(sql,new Object[]{sgid});
	}
	
	  @Override
	public int findDynamicTerminalReportTotal(long sgid)
	{
		String sql = "select sum(hdnum) from  es_report_terminal_analyse where siteid=?";
		return getJdbcTemplate().queryForInt(sql, new Object[]
		{ sgid });
	}
	  
	  @Override
	public int findDynamicTerminalReportTotalByOwner(long owner)
	{
//		String sql = "select sum(hdnum) from  es_report_terminal_analyse a join es_site_group g on a.sitegroupid=g.id where g.status!='DEL' and g.ownerid =?";
		  String sql = "select sum(hdnum) from  es_report_terminal_analyse a join es_site g on a.siteid=g.id where g.status!='DEL' and g.ownerid =?";
		  return getJdbcTemplate().queryForInt(sql, new Object[]
		{ owner });
	}
	  
     @Override
	public int findDAreaReportTotal(long sgid)
	{
		String sql = "select sum(hdnum) from  es_report_area_analyse where siteid=?";
		return getJdbcTemplate().queryForInt(sql, new Object[]
		{ sgid });
	}
     @Override
 	public int findDAreaReportTotalByOwner(long owner)
 	{
// 		String sql = "select sum(hdnum) from  es_report_area_analyse a join es_site_group g on a.sitegroupid=g.id where g.status!='DEL' and g.ownerid=?";
    	 String sql = "select sum(hdnum) from  es_report_area_analyse a join es_site g on a.siteid=g.id where g.status!='DEL' and g.ownerid=?";
    	 return getJdbcTemplate().queryForInt(sql, new Object[]
 		{ owner });
 	}
	
	@Override
	public List<AreaAnalysis> findDynamicAreaReport(long sgid) {
		String sql="select max(a.createtime) as lasttime,a.ip,count(distinct(a.wbuid)) as num from es_feature_dynamic_action_record a join es_page b on a.pageid=b.id join es_site c on b.siteid=c.id where c.sitegroupid=? group by a.ip order by num desc limit 10";
		return getJdbcTemplate().query(sql, new Object[]{sgid},new AreaReport());
	}
	// 得到互动的总记录数用于分页
	@Override
	public int findDynamicAreaReportTotal(long sgid) {
		String sql = "select count(*) from (select area,hdnum,lasthdtime from es_report_area_analyse where sitegroupid=? and hdnum>0 group by id) t;";
		return getJdbcTemplate().queryForInt(sql, new Object[] { sgid });
	}
	@Override
    public List<DanymicAton> findAtionBySgid(long sgid) {
        String sql="select distinct(r.action ) action,(select name from es_feature where id=action ) as name from es_feature_dynamic_action_record r left join es_page p on r.pageid=p.id left join es_site s on s.id=p.siteid  where s.sitegroupid=?";
        return getJdbcTemplate().query(sql, new Object[]{sgid},new DanymicAtonRowMaper());
    }
	class DanymicAtonRowMaper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            DanymicAton action=new DanymicAton();
            action.setAction(rs.getInt("action"));
            action.setName(rs.getString("name"));
            return action;
        }
    }
	
    private static final String FIND_DANUMIC_RECORD_HOUR_LIST = "select r.id,r.createtime,s.sitegroupid from es_feature_dynamic_action_record r left join es_page p on p.id=r.pageid left join es_site s on p.siteid=s.id where r.hourprocessstatus='N' limit ? ";

    @Override
    public List<DanymicActionRecord> findNoProcessHourStatusDanymicRecordList(int size) {
        Object[] params = { size };
        return getJdbcTemplate().query(FIND_DANUMIC_RECORD_HOUR_LIST, params, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                DanymicActionRecord vl = new DanymicActionRecord();
                vl.setId(rs.getLong("id"));
                vl.setSitegroupid(rs.getLong("sitegroupid"));
                vl.setHour(rs.getString("createtime").substring(11,13));
                return vl;
            }

        });
    }
    
    private static final String FIND_DANUMIC_RECORD_AREA_LIST = "select r.id,r.ip,r.createtime,s.sitegroupid from es_feature_dynamic_action_record r left join es_page p on p.id=r.pageid left join es_site s on p.siteid=s.id where r.areaproceestatus='N' limit ? ";

    @Override
    public List<DanymicActionRecord> findNoProcessAreaStatusDanymicRecordList(int size) {
        Object[] params = { size };
        return getJdbcTemplate().query(FIND_DANUMIC_RECORD_AREA_LIST, params, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                DanymicActionRecord vl = new DanymicActionRecord();
                vl.setId(rs.getLong("id"));
                vl.setSitegroupid(rs.getLong("sitegroupid"));
                vl.setIp(rs.getString("ip"));
                vl.setCreatetime(rs.getTimestamp("createtime"));
                return vl;
            }

        });
    }
    
    private static final String FIND_DANUMIC_RECORD_GENDER_LIST = "select r.id,u.gender,s.sitegroupid from es_feature_dynamic_action_record r left join es_page p on p.id=r.pageid left join es_site s on p.siteid=s.id left join es_sina_user u on u.wbuid=r.wbuid where r.genderprocessstatus='N' limit ? ";

    @Override
    public List<DanymicActionRecord> findNoProcessGenderStatusDanymicRecordList(int size) {
        Object[] params = { size };
        return getJdbcTemplate().query(FIND_DANUMIC_RECORD_GENDER_LIST, params, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                DanymicActionRecord vl = new DanymicActionRecord();
                vl.setId(rs.getLong("id"));
                vl.setSitegroupid(rs.getLong("sitegroupid"));
                vl.setGender(rs.getString("gender"));
                return vl;
            }

        });
    }

	
    @Override
    public int updateDynamicActionRecordAreaStatus(long id) {
        Object[] params = {id};
        return getJdbcTemplate().update("update es_feature_dynamic_action_record set areaproceestatus='C' where id=?", params);
    }
    
    @Override
    public int updateDynamicActionRecordGenderStatus(long id) {
        Object[] params = {id};
        return getJdbcTemplate().update("update es_feature_dynamic_action_record set genderprocessstatus='C' where id=?", params);
    }
    
    @Override
    public int updateDynamicActionRecordHourStatus(long id) {
        Object[] params = {id};
        return getJdbcTemplate().update("update es_feature_dynamic_action_record set hourprocessstatus='C' where id=?", params);
    }
    
	private static final String FIND_DYNAMIC_AREA_LIST = "select r.id,r.ip,r.createtime from es_feature_dynamic_action_record r left join es_page p on r.pageid=p.id left join es_site s on s.id=p.siteid  where r.areaproceestatus='N' and s.sitegroupid=? order by r.createtime";

    @Override
    public List<DanymicUserRecord> findDymamicListAreaBySgid(long sgid) {
        return getJdbcTemplate().query(FIND_DYNAMIC_AREA_LIST,
                new Object[] { sgid }, new DanymicRecordAreaRowMaper());
    }
    class DanymicRecordAreaRowMaper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            DanymicUserRecord record = new DanymicUserRecord();
            record.setId(rs.getLong("id"));
            record.setIp(rs.getString("ip"));
            record.setLastactiontime(rs.getTimestamp("createtime"));
            return record;
        }
    }
    class ReportAreaRowMaper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            ReportArea area=new ReportArea();
            area.setAid(rs.getLong("aid"));
            area.setArea(rs.getString("area"));
            return area;
        }
    }
    private static final String FIND_AREA_REPORT_BY_IP = "select area,aid from es_ips where ip1<=? and ip2>=?";

    @Override
    public ReportArea findReportAreaByIp(String ip) {
        long iplong = ToolUtils.getIpByStr(ip);
        List<ReportArea> list= getJdbcTemplate().query(FIND_AREA_REPORT_BY_IP,
                new Object[] { iplong,iplong}, new ReportAreaRowMaper());
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
    
    private static final String FIND_REPORT_GENDER_ANALYSE_BY_GSID = "select * from es_report_gender_analyse where siteid=? ";

    @Override
    public ReportGenderAnalyse findReportGenderAnalyseByGsid(long sgid) {
        Object[] params = { sgid };
        List<ReportGenderAnalyse> list= getJdbcTemplate().query(FIND_REPORT_GENDER_ANALYSE_BY_GSID, params, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                ReportGenderAnalyse vl = new ReportGenderAnalyse();
                vl.setId(rs.getLong("id"));
                vl.setHfnum(rs.getInt("hfnum"));
                vl.setHmnum(rs.getInt("hmnum"));
                vl.setHothernum(rs.getInt("hothernum"));
                vl.setVfnum(rs.getInt("vfnum"));
                vl.setVmnum(rs.getInt("vmnum"));
                vl.setVothernum(rs.getInt("vothernum"));
                return vl;
            }

        });
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
    
    private static final String FIND_REPORT_TERMINAL_ANALYSE_BY_GSID = "select * from es_report_terminal_analyse where siteid=? ";

    @Override
    public List<ReportTerminalAnalyse> findReportTerminalAnalyseByGsid(long sgid) {
        Object[] params = { sgid };
        List<ReportTerminalAnalyse> list= getJdbcTemplate().query(FIND_REPORT_TERMINAL_ANALYSE_BY_GSID, params, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            	ReportTerminalAnalyse vl = new ReportTerminalAnalyse();
                vl.setSgid(rs.getLong("siteid"));
                vl.setTerminal(rs.getString("terminal"));
                vl.setHnum(rs.getInt("hdnum"));
                vl.setVnum(rs.getInt("visitnum"));
                vl.setLasthdtime(rs.getTimestamp("lasthdtime"));
                vl.setLastvtime(rs.getTimestamp("lastvisittime"));
                return vl;
            }

        });
        return list;
    }
    
//    private static final String FIND_REPORT_TERMINAL_ANALYSE_BY_OWNER = "select sitegroupid,terminal,sum(hdnum)as hdnum,sum(visitnum) as visitnum ,lastvisittime,lasthdtime from es_report_terminal_analyse a join es_site_group g on g.id=a.sitegroupid where g.status!='DEL' and g.ownerid=? group by terminal";
    private static final String FIND_REPORT_TERMINAL_ANALYSE_BY_OWNER = "select sitegroupid,terminal,sum(hdnum)as hdnum,sum(visitnum) as visitnum ,lastvisittime,lasthdtime from es_report_terminal_analyse a join es_site g on g.id=a.siteid where g.status!='DEL' and g.ownerid=? group by terminal";
    @Override
    public List<ReportTerminalAnalyse> findReportTerminalAnalyseByOwner(long ownerid) {
        Object[] params = { ownerid };
        List<ReportTerminalAnalyse> list= getJdbcTemplate().query(FIND_REPORT_TERMINAL_ANALYSE_BY_OWNER, params, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            	ReportTerminalAnalyse vl = new ReportTerminalAnalyse();
                vl.setSgid(rs.getLong("sitegroupid"));
                vl.setTerminal(rs.getString("terminal"));
                vl.setHnum(rs.getInt("hdnum"));
                vl.setVnum(rs.getInt("visitnum"));
                vl.setLasthdtime(rs.getTimestamp("lasthdtime"));
                vl.setLastvtime(rs.getTimestamp("lastvisittime"));
                return vl;
            }

        });
        return list;
    }
    
}