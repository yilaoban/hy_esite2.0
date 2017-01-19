package com.huiyee.interact.appointment.dao;

import java.util.List;

import com.huiyee.bdmap.dto.BDAddress;
import com.huiyee.esite.model.AppointmentRecord;
import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.appointment.model.AptCode;
import com.huiyee.interact.appointment.model.AptMapping;
import com.huiyee.interact.appointment.model.OrderMappingModel;
import com.huiyee.interact.lottery.model.Lottery;

public interface IInteractAptDao {

	/**
	 * 预约订单查询
	 * @param ownerid
	 * @param omid 
	 * @param start
	 * @param size
	 * @return
	 */
	public List<AppointmentModel> findOrderMesById(long ownerid,long omid, int start,int size);
	
    public int findUserJoinTotal(long aptid, long entity, int userTypeWb);
	
	public List<AppointmentRecord> findAptRecord(long aptid, long pageid, int start, int size);
	
	public void saveCustomCommentRepotr(final long entityid,final AppointmentRecord apt,final String ip,final String terminal,final String source,final int type);
	
	public void saveCustomCommentRepotrXY(final long entityid,final AppointmentRecord apt,final String ip,final String terminal,final String source,final int type,final BDAddress bda);
	
	public int findOrderMesTotal(long ownerid, long omid);
	
	public long addOrderMes(AppointmentModel apt,long ownerid,long mid, long omid);
	
	public AppointmentModel findOrderMes(long id);
	
    public int findCode(String code,long aptid,int coded);
	
	public int updateCode(String code,long aptid);
	
	public int updateOrderMes(AppointmentModel apt,long id);
	
	public int deleteOrderMes(long id);
	
	public int addName(long aptid,String name,String mapping,String coltype,String stype,String defaultvalue,String isshow,int row,String tag,String req);
	
	public List<OrderMappingModel> findMappingPre(long aptid);
	
	public List<OrderMappingModel> findMappingPreNew(long aptid);
	
	public int updateMappingMes(long id,String name,String defaultvalue,String isshow,int row,String tag,String req);
	
	public List<String> findAptMapping(long aptid);
	
	public long saveAptMapping(long aptid,String mapping, String tag);
	
	public int deleteMapping(long aptid);
	
	public List<AppointmentDataModel> checkOrderRecord(long aptid,long ownerid, int type, int start, int size);
	
	public AppointmentDataModel findAppointmentDataModelById(long rid,long ownerid);
	
	public List<Lottery> findLotteryByType(long ownerid,String type);
	
	public Lottery findLotteryById(long lotteryid);
	
	public int updateRuletypeByLotteryid(long lotteryid);
	
	public List<AptMapping> findShowColum(long aptid, int limit);

	public int findAptTotal(long aptid, long ownerid, int type);
	
	public long addAppointment(long mid,long ownerid,String title);

	public List<AptMapping> findUsedMapping(long aptid, long ownerid);

	public void updateDataShowClean(long aptid);

	public int updataDataShow(long aptid, List<String> mapping);

	public List<AppointmentRecord> findAptRecordByUser(long aptid, long pageid, long uid, int cd, int start, int size);

	public int updateAptClean(long aptid, long owner);

	public List<AptMapping> findAllColum(long aptid);

	public List<AppointmentDataModel> findExportData(long aptid, long owmer, int utype, String starttime, String endtime);
	
	public int delAptRecord(long rid);

	public long saveSimpleApt(long ownerid, String aptname);

	public int updateCoded(long id, int i);

	public int findAptCodeTotal(long aptid, String code, int status);

	public List<AptCode> findAptCodeList(long aptid, String code, int status, int i, int j);

	public int saveAptCode(long aptid, List<String> insertList);

	public List<OrderMappingModel> findUserDefine(long l);

	public int delAptRecordByuid(long aptid, long uid);

	/**
	 * 微现场用到的 根据uid和aptid查找recordid
	 * @param id
	 * @param uid
	 * @return
	 */
	public long findRecordIdByUid(long id, long uid);

	/**
	 * 当totallimit=1时查看的此人aptrecord
	 * @param id
	 * @param uid
	 * @param type
	 */
	public AppointmentDataModel findAptRecordByUser(long id, long uid, int type);

	/**
	 * 当totallimit=1时
	 * @param uid
	 * @param aptc
	 * @param ip
	 * @param terminal
	 * @param source
	 * @param type
	 */
	public void updateAptRecord(long uid, AppointmentRecord aptc, String ip, String terminal, String source, int type);

	public AppointmentDataModel findAppointmentDataByWxuid(long wxuid, long aptid, long ownerid);

	public int updateAptRecord(AppointmentDataModel record);
}
