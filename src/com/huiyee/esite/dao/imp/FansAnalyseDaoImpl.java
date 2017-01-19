package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IFansAnalyseDao;
import com.huiyee.esite.model.FansAnalyse;
import com.huiyee.esite.model.FansAnalyseRecord;
import com.huiyee.esite.model.FansLevelAnalyse;

public class FansAnalyseDaoImpl extends AbstractDao implements IFansAnalyseDao {

	private static final String FIND_HD_FANS_TOTAL="select count(r.id) from es_report_fans_analyse r join es_site s on r.siteid = s.id where r.type='D' and s.sitegroupid = ? and r.followed = ? "; 
	@Override
	public int findHdFansTotal(long sitegroupid,String type) {
		Object[] params={sitegroupid,type};
		return getJdbcTemplate().queryForInt(FIND_HD_FANS_TOTAL, params);
	}

	private static final String FIND_VISIT_FANS_TOTAL="select count(r.id) from es_report_fans_analyse_record r join es_site s on r.siteid = s.id where r.type='V' and s.sitegroupid = ? and r.followed = ? "; 
	@Override
	public int findVisitFansTotal(long sitegroupid,String type) {
		Object[] params={sitegroupid,type};
		return getJdbcTemplate().queryForInt(FIND_VISIT_FANS_TOTAL, params);
	}
	
	private static final String FIND_VISIT_TOTAL="select count(r.id) from es_report_fans_analyse r join es_site s on r.siteid = s.id where s.sitegroupid = ? ";
	@Override
	public int findVisitTotal(long sitegroupid) {
		Object[] params={sitegroupid};
		return getJdbcTemplate().queryForInt(FIND_VISIT_TOTAL, params);
	}
	
	private static final String FIND_VISIT_ANASYLE="select * from es_report_fans_analyse_record where siteid = ? and type=? order by createtime desc limit ?,?";
	@Override
	public List<FansAnalyseRecord> findFansAnalyselist(long sitegroupid,String type, int start,
			int size) {
		Object[] params={sitegroupid,type,start,size};
		return getJdbcTemplate().query(FIND_VISIT_ANASYLE, params, new FansAnalyseRecordRowmapper());
	}
	class FansAnalyseRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			FansAnalyse fa = new FansAnalyse();
			fa.setSitegroupid(rs.getLong("sitegroupid"));
			fa.setDfansnum(rs.getInt("dfansnum"));
			fa.setDunfansnum(rs.getInt("dunfansnum"));
			fa.setVfansnum(rs.getInt("vfansnum"));
			fa.setVunfansnum(rs.getInt("vunfansnum"));
			fa.setCreatetime(rs.getTimestamp("createtime"));
			return fa;
		}
	}

	private static final String SAVE_FANS_ANALYSE="insert into esite.es_report_fans_analyse_record(wbuid,sitegroupid,nickname,type,followed,level,createtime)values(?,?,?,?,?,?,now()) on duplicate key update nickname = ?,followed = ?";
	@Override
	public int saveFansAnalyse(long sitegroupid, long wbuid, String nickname,
			String type, String followed,int level) {
		Object[] params={wbuid,sitegroupid,nickname,type,followed,level,nickname,followed};
		return getJdbcTemplate().update(SAVE_FANS_ANALYSE, params);
	}
	private static final String SAVE_DANYMIC_LEVEL_ANALYSE="insert into esite.es_report_level_analyse(sitegroupid,level,dnum,createtime)values(?,?,?,now()) on duplicate key update dnum = dnum+1";
    @Override
    public int saveDanymicLevelFansAnalyse(long sitegroupid, int level,int dnum) {
        Object[] params={sitegroupid,level,dnum};
        return getJdbcTemplate().update(SAVE_DANYMIC_LEVEL_ANALYSE, params);
    }
    private static final String SAVE_VISIT_LEVEL_ANALYSE="insert into esite.es_report_level_analyse(sitegroupid,level,vnum,createtime)values(?,?,?,now()) on duplicate key update vnum = vnum+1";
    @Override
    public int saveVistiLevelFansAnalyse(long sitegroupid, int level,int vnum) {
        Object[] params={sitegroupid,level,vnum};
        return getJdbcTemplate().update(SAVE_VISIT_LEVEL_ANALYSE, params);
    }
    
    private static final String SAVE_VISIT_FANS_ANALYSE="insert into esite.es_report_fans_analyse(sitegroupid,vfansnum,createtime)values(?,?,now()) on duplicate key update vfansnum = vfansnum+1";
    @Override
    public int saveVistiFansAnalyse(long sitegroupid, int vfansnum) {
        Object[] params={sitegroupid,vfansnum};
        return getJdbcTemplate().update(SAVE_VISIT_FANS_ANALYSE, params);
    }
    
    private static final String SAVE_VISIT_UNFANS_ANALYSE="insert into esite.es_report_fans_analyse(sitegroupid,vunfansnum,createtime)values(?,?,now()) on duplicate key update vunfansnum = vunfansnum+1";
    @Override
    public int saveVistiUnFansAnalyse(long sitegroupid, int vunfansnum) {
        Object[] params={sitegroupid,vunfansnum};
        return getJdbcTemplate().update(SAVE_VISIT_UNFANS_ANALYSE, params);
    }
    
    private static final String SAVE_DANYMIC_FANS_ANALYSE="insert into esite.es_report_fans_analyse(sitegroupid,dfansnum,createtime)values(?,?,now()) on duplicate key update dfansnum = dfansnum+1";
    @Override
    public int saveDanymicFansAnalyse(long sitegroupid, int dfansnum) {
        Object[] params={sitegroupid,dfansnum};
        return getJdbcTemplate().update(SAVE_DANYMIC_FANS_ANALYSE, params);
    }
    
    @Override
    public int updateDanymicFansAnalyse(long sitegroupid,String type) {
    	String sql="";
    	if("Y".equals(type)){
    		sql="update esite.es_report_fans_analyse set dfansnum = dfansnum+1,dunfansnum=dunfansnum-1 where sitegroupid=?";
    	}else if("N".equals(type)){
    		sql="update esite.es_report_fans_analyse set dfansnum = dfansnum-1 dunfansnum=dunfansnum+1 where sitegroupid=?";
    	}
    	
        Object[] params={sitegroupid};
        return getJdbcTemplate().update(sql, params);
    }
    @Override
    public int updateVisitFansAnalyse(long sitegroupid,String type) {
    	String sql="";
    	if("Y".equals(type)){
    		sql="update esite.es_report_fans_analyse set vfansnum = vfansnum+1,vunfansnum=vunfansnum-1 where sitegroupid=?";
    	}else if("N".equals(type)){
    		sql="update esite.es_report_fans_analyse set vfansnum = vfansnum-1 vunfansnum=vunfansnum+1 where sitegroupid=?";
    	}
    	
        Object[] params={sitegroupid};
        return getJdbcTemplate().update(sql, params);
    }
    @Override
    public int updateAVisitLevelAnalyse(long sitegroupid,int level) {
    	String sql="update esite.es_report_level_analyse set vnum = vnum+1 where sitegroupid=? and level=?";
        Object[] params={sitegroupid,level};
        return getJdbcTemplate().update(sql, params);
    }
    @Override
    public int updateDVisitLevelAnalyse(long sitegroupid,int level) {
    	String sql="update esite.es_report_level_analyse set vnum = vnum-1 where sitegroupid=? and level=?";
        Object[] params={sitegroupid,level};
        return getJdbcTemplate().update(sql, params);
    }
    @Override
    public int updateADanymicLevelAnalyse(long sitegroupid,int level) {
    	String sql="update esite.es_report_level_analyse set dnum = dnum+1 where sitegroupid=? and level=?";
        Object[] params={sitegroupid,level};
        return getJdbcTemplate().update(sql, params);
    }
    @Override
    public int updateDDanymicLevelAnalyse(long sitegroupid,int level) {
    	String sql="update esite.es_report_level_analyse set dnum = dnum-1 where sitegroupid=? and level=?";
        Object[] params={sitegroupid,level};
        return getJdbcTemplate().update(sql, params);
    }
    
    
    private static final String SAVE_DANYMIC_UNFANS_ANALYSE="insert into esite.es_report_fans_analyse(sitegroupid,dunfansnum,createtime)values(?,?,now()) on duplicate key update dunfansnum = dunfansnum+1";
    @Override
    public int saveDanymicUnFansAnalyse(long sitegroupid, int dunfansnum) {
        Object[] params={sitegroupid,dunfansnum};
        return getJdbcTemplate().update(SAVE_DANYMIC_UNFANS_ANALYSE, params);
    }
    class FansAnalyseRecordRowmapper implements RowMapper{
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            FansAnalyseRecord fa = new FansAnalyseRecord();
            fa.setWbuid(rs.getLong("wbuid"));
            fa.setNickname(rs.getString("nickname"));
            fa.setSitegroupid(rs.getLong("sitegroupid"));
            fa.setType(rs.getString("type"));
            fa.setFollowed(rs.getString("followed"));
            fa.setLevel(rs.getInt("level"));
            return fa;
        }
    }
    private static final String FIND_FANS_ANASYLE_RECORD="select * from es_report_fans_analyse_record where sitegroupid=? and wbuid=?";
    @Override
    public FansAnalyseRecord findFansAnalyseRecord(long sitegroupid,long wbuid) {
        Object[] params={sitegroupid,wbuid};
        List<FansAnalyseRecord> list= getJdbcTemplate().query(FIND_FANS_ANASYLE_RECORD, params, new FansAnalyseRecordRowmapper());
        if(list!=null&&list.size()>0)
            return list.get(0);
        return null;
    }
    private static final String FIND_FANS_ANASYLE="select * from es_report_fans_analyse where siteid=? ";
    @Override
    public FansAnalyse findFansAnalyse(long sitegroupid) {
        Object[] params={sitegroupid};
        List<FansAnalyse> list= getJdbcTemplate().query(FIND_FANS_ANASYLE, params, new FansAnalyseRowmapper());
        if(list!=null&&list.size()>0)
            return list.get(0);
        return null;
    }
    
    private static final String FIND_FANS_LEVEL_ANASYLE="select vnum,dnum,level from es_report_level_analyse where siteid=? group by level order by level";
    @Override
    public List<FansLevelAnalyse> findFansLevelAnalyseList(long sitegroupid) {
        Object[] params={sitegroupid};
        List<FansLevelAnalyse> list= getJdbcTemplate().query(FIND_FANS_LEVEL_ANASYLE, params, new FansLevelAnalyseRecordRowmapper());
        return list;
    }
    
    private static final String FIND_FANS_LEVEL_ANASYLE_OWNER="select sum(vnum) vnum,sum(dnum) dnum,level from es_report_level_analyse l join es_site_group g on g.id=l.sitegroupid where g.ownerid=? group by level order by level";
    @Override
    public List<FansLevelAnalyse> findFansLevelAnalyseListByOwner(long ownerid) {
        Object[] params={ownerid};
        List<FansLevelAnalyse> list= getJdbcTemplate().query(FIND_FANS_LEVEL_ANASYLE_OWNER, params, new FansLevelAnalyseRecordRowmapper());
        return list;
    }
    
    class FansLevelAnalyseRecordRowmapper implements RowMapper{
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            FansLevelAnalyse fa=new FansLevelAnalyse();
            fa.setLevel(rs.getInt("level"));
            fa.setVnum(rs.getInt("vnum"));
            fa.setDnum(rs.getInt("dnum"));
            return fa;
        }
    }
    
    private static final String FIND_LEVEL_DNUM_TOTAL="select sum(dnum) from es_report_level_analyse where sitegroupid=? "; 
    @Override
    public int findLevelDnumTotal(long sitegroupid) {
        Object[] params={sitegroupid};
        return getJdbcTemplate().queryForInt(FIND_LEVEL_DNUM_TOTAL, params);
    }
    
    private static final String FIND_LEVEL_VNUM_TOTAL="select sum(vnum) from es_report_level_analyse where siteid=? "; 
    @Override
    public int findLevelVnumTotal(long sitegroupid) {
        Object[] params={sitegroupid};
        return getJdbcTemplate().queryForInt(FIND_LEVEL_VNUM_TOTAL, params);
    }

}
