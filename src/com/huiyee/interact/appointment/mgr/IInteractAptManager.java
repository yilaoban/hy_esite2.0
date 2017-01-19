package com.huiyee.interact.appointment.mgr;

import java.util.List;

import com.huiyee.bdmap.dto.BDAddress;
import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.model.AppointmentRecord;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentFormRecord;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.appointment.dto.IDto;
import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.appointment.model.AppointmentMappingModel;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.appointment.model.OrderMappingModel;
import com.huiyee.interact.lottery.model.Lottery;

public interface IInteractAptManager {

	public List<AppointmentModel> findOrderMesById(long ownerid,long omid, int start,int size);
	
    public int findUserJoinTotal(long aptid, long entity, int userTypeWb);
	
	public List<AppointmentRecord> findAptRecord(long aptid, long pageid, int start, int size, VisitUser visitUser);
	
	public int findOrderMesTotal(long ownerid, long omid);
	
	public AppointmentModel findOrderMes(long ownerid,long id);
	
	public AppointmentModel findAppointById(long aptid);
	
	public int findCode(String code,long aptid,int coded);
	
	public void updateCode(String code,long aptid);
	
	public HdRsDto saveCustomCommentRepotr(VisitUser visit,AppointmentRecord apt,String ip,String terminal,String source,long relationid, AppointmentModel apt2);
	
	public HdRsDto saveCustomCommentRepotrXY(VisitUser visit,AppointmentRecord apt,String ip,String terminal,String source,long relationid, AppointmentModel apt2,BDAddress bda);
	
	public AppointmentModel findOrderMesNew(long ownerid,long id);
	
	public int deleteOrderMes(long id);
	
	public String findAptMapping(long aptid,String newMappingStr);
	
	public IDto checkOrderRecord(long aptid,long ownerid, int type, int pageId);
	
	public AppointmentDataModel findAppointmentDataModelById(long rid,long ownerid);
	
	public int delAptRecord(long rid);
	
	public List<Lottery> findLotteryByType(long ownerid,String type);
	
	public Lottery findLotteryById(long lotteryid);
	
	public int updateRuletypeByLotteryid(long lotteryid);
	
	public long addAppointment(long mid,long ownerid,String title);

	public IDto findUsedMapping(long aptid, long ownerid);

	public int updateDataShow(long aptid, List<String> mapping);

	public List<ContentCategory> findNextCategoryByCcid(long catid, long owner);

	public List<ContentFormRecord> findFormRecordByCatid(long catid);

	/**
	 * 清空表单详情
	 * @param aptid
	 * @param id
	 * @return
	 */
	public int updateAptClean(long aptid, long id);

	/**
	 * 申请数据导出
	 * @param id
	 * @param aptid
	 * @param endtime 
	 * @param starttime 
	 * @param aptid2 
	 * @return
	 */
	public List<String> findExportData(long id, int utype, long aptid, String starttime, String endtime);

	public IDto findAptCodeList(long aptid, int pageId, String code, int status);

	public int saveAptCode(long aptid, List<String> insertList);

	public long addOrderMes(AppointmentModel apt, long ownerid, long mid, List<OrderMappingModel> subSys, List<OrderMappingModel> subUdf, long aptid, long omid);

	public int updateOrderMes(AppointmentModel apt, long aptid, List<OrderMappingModel> subSys, List<OrderMappingModel> subUdf);

	/**
	 * 更新验证码是否可重复使用
	 * @param status
	 * @param aptid
	 * @return
	 */
	public int updateAptCode(int status, long aptid);

	public AppointmentDataModel findAppointmentDataByWxuid(long wxuid, long aptid, long ownerid);

	public int updateAptRecord(AppointmentDataModel record);

	public AppointmentDataModel findAppointMaps(long aptid);
	
}
