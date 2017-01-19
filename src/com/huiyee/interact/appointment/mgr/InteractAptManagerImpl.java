
package com.huiyee.interact.appointment.mgr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.huiyee.bdmap.dto.BDAddress;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dao.IContentCategoryDao;
import com.huiyee.esite.dao.IContentFormDao;
import com.huiyee.esite.dao.IHyUserDao;
import com.huiyee.esite.dao.ISinaUserDao;
import com.huiyee.esite.dao.ITemplateDao;
import com.huiyee.esite.dao.IWeiXinDao;
import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.model.AppointmentRecord;
import com.huiyee.esite.model.BlockContext;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentForm;
import com.huiyee.esite.model.ContentFormRecord;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageCard;
import com.huiyee.esite.model.TemplateBlock;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.esite.util.HttpTookit;
import com.huiyee.interact.appointment.action.AddOrderAction;
import com.huiyee.interact.appointment.dao.IInteractAptDao;
import com.huiyee.interact.appointment.dto.AppointmentDataDto;
import com.huiyee.interact.appointment.dto.AptCodeDto;
import com.huiyee.interact.appointment.dto.IDto;
import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.appointment.model.AppointmentMappingModel;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.appointment.model.AptCode;
import com.huiyee.interact.appointment.model.AptMapping;
import com.huiyee.interact.appointment.model.OrderMappingModel;
import com.huiyee.interact.lottery.model.Lottery;

public class InteractAptManagerImpl extends AbstractMgr implements IInteractAptManager
{

	private IInteractAptDao interactAptDao;
	private ITemplateDao templateDao;

	public void setTemplateDao(ITemplateDao templateDao)
	{
		this.templateDao = templateDao;
	}

	private ISinaUserDao sinaUserDao;
	private IWeiXinDao weiXinDao;
	private IHyUserDao hyUserDao;
	private IContentCategoryDao categoryDao;
	private IContentFormDao contentFormDao;

	
	public void setWeiXinDao(IWeiXinDao weiXinDao)
	{
		this.weiXinDao = weiXinDao;
	}

	
	public void setHyUserDao(IHyUserDao hyUserDao)
	{
		this.hyUserDao = hyUserDao;
	}

	public void setCategoryDao(IContentCategoryDao categoryDao)
	{
		this.categoryDao = categoryDao;
	}

	public void setContentFormDao(IContentFormDao contentFormDao)
	{
		this.contentFormDao = contentFormDao;
	}

	public void setInteractAptDao(IInteractAptDao interactAptDao)
	{
		this.interactAptDao = interactAptDao;
	}

	@Override
	public List<AppointmentModel> findOrderMesById(long ownerid, long omid, int start, int size)
	{
		return interactAptDao.findOrderMesById(ownerid, omid, start, size);
	}

	@Override
	public int findOrderMesTotal(long ownerid, long omid)
	{
		return interactAptDao.findOrderMesTotal(ownerid, omid);
	}

	@Override
	public long addOrderMes(AppointmentModel apt, long ownerid, long mid, List<OrderMappingModel> subSys, List<OrderMappingModel> subUdf, long aptid, long omid)
	{
		long result = 0;
		long id = interactAptDao.addOrderMes(apt, ownerid, mid, omid);
		int coded = 0;
		List<OrderMappingModel> list=new ArrayList<OrderMappingModel>();
		list.addAll(subSys);
		if(subUdf!=null){
			list.addAll(subUdf);
		}
		for (OrderMappingModel m : list)
		{	
			if(m!=null&&StringUtils.isNotEmpty(m.getMapping())){
				
				if (m.getName() == null || "".equals(m.getName()))
				{
					m.setIsshow("N");
				}
				if(StringUtils.isEmpty(m.getIsshow())){
					m.setIsshow("N");
				}
				if(StringUtils.isEmpty(m.getReq())){
					m.setReq("N");
				}
				Pattern p=Pattern.compile("f\\d+");
				if(p.matcher(m.getMapping()).matches()){
					m.setTag("N");
				}else{
					m.setTag("Y");
				}
				interactAptDao.addName(id, m.getName(), m.getMapping(), m.getColtype(), m.getStype(), m.getDefaultvalue(), m.getIsshow(), m.getRow(), StringUtils.isNotEmpty(m.getTag())?m.getTag():"N", m.getReq());
				/**
				 * 当验证码字段修改是需修改APT表中的coded字段
				 */
				if ("code".equals(m.getMapping()) && "Y".equals(m.getIsshow()))
				{
					coded = 1;
				}
				
			}
		}
		
		
		
		interactAptDao.updateCoded(id, coded);
		if ("Y".equals(apt.getIslottery()))
		{

		}
		if (id > 0)
		{
			result = id;
		}
		return result;
	}

	@Override
	public AppointmentModel findOrderMes(long ownerid, long id)
	{
		AppointmentModel m = interactAptDao.findOrderMes(id);
		if (m != null)
		{
			List<OrderMappingModel> list = interactAptDao.findMappingPre(id);
			if (list != null && list.size() > 0)
			{
				m.setList(list);
			}
		}
		return m;
	}

	@Override
	public AppointmentModel findOrderMesNew(long ownerid, long id)
	{
		AppointmentModel m = interactAptDao.findOrderMes(id);
		if (m != null)
		{
			List<OrderMappingModel> list = interactAptDao.findMappingPreNew(id);
			if (list != null && list.size() > 0)
			{
				m.setList(list);
			}
		}
		return m;
	}

	@Override
	public int updateOrderMes(AppointmentModel apt, long aptid, List<OrderMappingModel> subSys, List<OrderMappingModel> subUdf)
	{
		int result = 0;
		long id=apt.getId();
		interactAptDao.deleteMapping(id);
		interactAptDao.updateOrderMes(apt, id);
		int coded = 0;
		List<OrderMappingModel> list=new ArrayList<OrderMappingModel>();
		list.addAll(subSys);
		if(subUdf!=null){
			list.addAll(subUdf);
		}
		for (OrderMappingModel m : list)
		{	
			if(m!=null&&StringUtils.isNotEmpty(m.getMapping())){
				
				if (m.getName() == null || "".equals(m.getName()))
				{
					m.setIsshow("N");
				}
				if(StringUtils.isEmpty(m.getIsshow())){
					m.setIsshow("N");
				}
				if(StringUtils.isEmpty(m.getReq())){
					m.setReq("N");
				}
				Pattern p=Pattern.compile("f\\d+");
				if(p.matcher(m.getMapping()).matches()){
					m.setTag("N");
				}else{
					m.setTag("Y");
				}
				interactAptDao.addName(id, m.getName(), m.getMapping(), m.getColtype(), m.getStype(), m.getDefaultvalue(), m.getIsshow(), m.getRow(), StringUtils.isNotEmpty(m.getTag())?m.getTag():"N", m.getReq());
				/**
				 * 当验证码字段修改是需修改APT表中的coded字段
				 */
				if ("code".equals(m.getMapping()) && "Y".equals(m.getIsshow()))
				{
					AppointmentModel am=interactAptDao.findOrderMes(id);
					coded=am.getCoded()==0?1:am.getCoded();
				}
				
			}
		}
		interactAptDao.updateCoded(id, coded);
		if (id > 0)
		{
			result = 1;
		}
		return result;
	}

	@Override
	public int deleteOrderMes(long id)
	{
		return interactAptDao.deleteOrderMes(id);
	}

	@Override
	public String findAptMapping(long aptid, String newMappingStr)
	{
		List<String> mapping = interactAptDao.findAptMapping(aptid);
		List<String> allMapping = AddOrderAction.mappingList;
		allMapping.removeAll(mapping);
		if (allMapping.size() >= 15)
		{
			if (StringUtils.isEmpty(newMappingStr))
			{
				return allMapping.get(0);
			}
			List<String> alreadyMapping = Arrays.asList(newMappingStr.split(","));
			allMapping.removeAll(alreadyMapping);
			if (allMapping.size() > 0)
			{
				return allMapping.get(0);
			}
		}
		return null;
	}

	@Override
	public IDto checkOrderRecord(long aptid, long ownerid, int type, int pageId)
	{
		AppointmentDataDto dto = new AppointmentDataDto();
		List<AptMapping> cloum = interactAptDao.findShowColum(aptid, 4);
		dto.setMapping(cloum);
		List<AppointmentDataModel> list = interactAptDao.checkOrderRecord(aptid, ownerid, type, (pageId - 1) * IInteractConstants.APT_RECORD_LIMIT,
				IInteractConstants.APT_RECORD_LIMIT);
		for (AppointmentDataModel model : list)
		{
			long entityid=model.getWbuid();
			if (type == 0)
			{
				model.setTel(hyUserDao.findNickname(0,entityid,0));
				model.setNickname(sinaUserDao.findNickNameById(entityid));
			} else if (type == 1)
			{
				model.setTel(hyUserDao.findNickname(0,0,entityid));
				model.setNickname(weiXinDao.findNickNameById(entityid));
			} else if (type == -1)
			{
				model.setTel(hyUserDao.findNickname(entityid,0,0));
			}  else if (type == 2)
			{
				model.setTel(hyUserDao.findNickname(entityid,0,0));
				model.setNickname(sinaUserDao.findNickNameById(entityid));
				model.setWxnickname(weiXinDao.findNickNameById(entityid));
			}
			model.composeList(cloum);
		}
		dto.setList(list);
		int total = interactAptDao.findAptTotal(aptid, ownerid, type);
		dto.setPager(new Pager(pageId, total, IInteractConstants.APT_RECORD_LIMIT));
		return dto;
	}

	@Override
	public AppointmentDataModel findAppointmentDataModelById(long rid, long ownerid)
	{
		AppointmentDataModel model = interactAptDao.findAppointmentDataModelById(rid, ownerid);
		if ("0".equals(model.getType()))
		{
			model.setNickname(sinaUserDao.findNickNameById(model.getWbuid()));
		}
		model.setMaps(interactAptDao.findAllColum(model.getAptid()));
		return model;
	}

	@Override
	public List<Lottery> findLotteryByType(long ownerid, String type)
	{
		return interactAptDao.findLotteryByType(ownerid, type);
	}

	@Override
	public Lottery findLotteryById(long lotteryid)
	{
		return interactAptDao.findLotteryById(lotteryid);
	}

	@Override
	public int updateRuletypeByLotteryid(long lotteryid)
	{
		return interactAptDao.updateRuletypeByLotteryid(lotteryid);
	}

	public void setSinaUserDao(ISinaUserDao sinaUserDao)
	{
		this.sinaUserDao = sinaUserDao;
	}

	@Override
	public long addAppointment(long mid, long ownerid, String title)
	{
		long result = 0;
		AppointmentMappingModel mappingModel = new AppointmentMappingModel();
		long id = interactAptDao.addAppointment(mid, ownerid, title);
		for (int i = 0; i < mappingModel.getList().size(); i++)
		{
			OrderMappingModel om = mappingModel.getList().get(i);
			String name = om.getName();
			String mapping = om.getMapping();
			String defaultvalue = om.getDefaultvalue();
			String coltype = om.getColtype();
			String stype = om.getStype();
			String isshow = om.getIsshow();
			int row = om.getRow();
			String tag = om.getTag();
			String req = om.getReq();
			interactAptDao.addName(id, name, mapping, coltype, stype, defaultvalue, isshow, row, tag, req);
		}
		if (id > 0)
		{
			result = id;
		}
		return result;
	}

	@Override
	public IDto findUsedMapping(long aptid, long ownerid)
	{
		AppointmentDataDto dto = new AppointmentDataDto();
		dto.setMapping(interactAptDao.findUsedMapping(aptid, ownerid));
		return dto;
	}

	@Override
	public int updateDataShow(long aptid, List<String> mapping)
	{
		interactAptDao.updateDataShowClean(aptid);
		return interactAptDao.updataDataShow(aptid, mapping);
	}

	@Override
	public AppointmentModel findAppointById(long aptid)
	{
		return interactAptDao.findOrderMes(aptid);
	}

	@Override
	public HdRsDto saveCustomCommentRepotr(VisitUser vu, AppointmentRecord aptc, String ip, String terminal, String source, long relationid, AppointmentModel apt)
	{
		HdRsDto rs = new HdRsDto();
		rs.setStatus(1);
		rs.setHydesc("提交成功！");
		long uid = vu.getUid();
		int type = vu.getCd();
		
		boolean needUpdate=false;//当申请总数为1的时候,还能继续提交.只是replace原来的记录
		if("UPD".equals(apt.getStatus())){
			AppointmentDataModel ar=interactAptDao.findAptRecordByUser(apt.getId(),uid,type);
			if(ar!=null){
				aptc.setId(ar.getId());
				needUpdate=true;
			}
		}
		
		if(needUpdate){
			interactAptDao.updateAptRecord(uid, aptc, ip, terminal, source, type);
		}else{
			interactAptDao.saveCustomCommentRepotr(uid, aptc, ip, terminal, source, type);
		}
		
		
		
		this.updateInteractAction(apt, uid, type);
		if(apt.getBalance()!=0&&vu.getHyUser()!=null)
		{
			this.updateBalance(vu.getHyUser().getId(), apt.getBalance(), "申请获得积分","HUD" ,"APT", apt.getId());
		}
		if (relationid > 0)
		{
			TemplateBlock tb = templateDao.findTemplateBlockByRelationid(relationid);
			if (tb != null)
			{
				PageBlockRelation r = templateDao.findPageBlockRelationByRelationid(relationid);
				String jtype = "N";
				String url = null;
				if (r != null)
				{
					String json = r.getJson();
					JSONObject jo = JSONObject.fromObject(json);
					try
					{
						jo = jo.getJSONObject("obj");
						jo = jo.getJSONObject("redirect");
						jtype = jo.getString("type");
						url = jo.getString("url");
					} catch (Exception e)
					{
						jtype = "N";
					}
				}
				if ("N".equals(jtype))
				{ // 默认跳转
					BlockContext bc = templateDao.findBlockContextid(tb.getId(), "Y");
					if (bc != null)
					{
						Page page = templateDao.findPageByRelationidAndContextid(relationid, bc.getId());
						if (page != null)
						{
							List<PageCard> cardList = templateDao.findTemplateCardByPageid(page.getId());
							if (cardList != null && cardList.size() > 0)
							{ // 有子页面且页面上有卡片则需要跳转
								rs.setId(page.getId());
							}
						}
					}
				} else if ("R".equals(jtype))
				{// 通过http获取跳转地址
					url = HttpTookit.doGet(url, null, "utf-8", true);
				}
				rs.setUrl(url);
			}
		}
		return rs;
	}

	@Override
	public HdRsDto saveCustomCommentRepotrXY(VisitUser vu, AppointmentRecord aptc, String ip, String terminal, String source, long relationid, AppointmentModel apt, BDAddress bda)
	{
		HdRsDto rs = new HdRsDto();
		rs.setStatus(1);
		rs.setHydesc("提交成功！");
		long uid = vu.getUid();
		int type = vu.getCd();
		interactAptDao.saveCustomCommentRepotrXY(uid, aptc, ip, terminal, source, type, bda);
		this.updateInteractAction(apt, uid, type);
		if(apt.getBalance()!=0&&vu.getHyUser()!=null)
		{
			this.updateBalance(vu.getHyUser().getId(), apt.getBalance(), "申请获得积分", "HUD" ,"APT", apt.getId());
		}
		if (relationid > 0)
		{
			TemplateBlock tb = templateDao.findTemplateBlockByRelationid(relationid);
			if (tb != null)
			{
				PageBlockRelation r = templateDao.findPageBlockRelationByRelationid(relationid);
				String jtype = "N";
				String url = null;
				if (r != null)
				{
					String json = r.getJson();
					JSONObject jo = JSONObject.fromObject(json);
					try
					{
						jo = jo.getJSONObject("obj");
						jo = jo.getJSONObject("redirect");
						jtype = jo.getString("type");
						url = jo.getString("url");
					} catch (Exception e)
					{
						jtype = "N";
					}
				}
				if ("N".equals(jtype))
				{ // 默认跳转
					BlockContext bc = templateDao.findBlockContextid(tb.getId(), "Y");
					if (bc != null)
					{
						Page page = templateDao.findPageByRelationidAndContextid(relationid, bc.getId());
						if (page != null)
						{
							List<PageCard> cardList = templateDao.findTemplateCardByPageid(page.getId());
							if (cardList != null && cardList.size() > 0)
							{ // 有子页面且页面上有卡片则需要跳转
								rs.setId(page.getId());
							}
						}
					}
				} else if ("R".equals(jtype))
				{// 通过http获取跳转地址
					url = HttpTookit.doGet(url, null, "utf-8", true);
				}
				rs.setUrl(url);
			}
		}
		return rs;
	}

	@Override
	public int findUserJoinTotal(long aptid, long entity, int userTypeWb)
	{
		return interactAptDao.findUserJoinTotal(aptid, entity, userTypeWb);
	}

	@Override
	public List<AppointmentRecord> findAptRecord(long aptid, long pageid, int start, int size, VisitUser vu)
	{
		if (vu == null)
		{
			return interactAptDao.findAptRecord(aptid, pageid, start, size);
		} else
		{
			return interactAptDao.findAptRecordByUser(aptid, pageid, vu.getUid(), vu.getCd(), start, size);
		}
	}

	@Override
	public List<ContentCategory> findNextCategoryByCcid(long catid, long owner)
	{
		return categoryDao.findNextCategoryByCcid(catid, owner);
	}

	@Override
	public List<ContentFormRecord> findFormRecordByCatid(long catid)
	{
		ContentForm cf = contentFormDao.findContentFormByCcid(catid);
		if (cf != null)
		{
			return contentFormDao.findRecordByFormid(cf.getId());
		} else
		{
			return null;
		}
	}

	@Override
	public int updateAptClean(long aptid, long owner)
	{
		return interactAptDao.updateAptClean(aptid, owner);
	}

	@Override
	public List<String> findExportData(long owmer, int utype, long aptid, String starttime, String endtime)
	{
		List<String> rs = new ArrayList<String>();
		List<AptMapping> cloum = interactAptDao.findAllColum(aptid);
		String columStr = "";
		switch (utype)
		{
		case 0:
			columStr = "用户名,微博昵称,";
			break;
		case 1:
			columStr = "用户名,微信昵称,";
			break;
		case 2:
			columStr = "用户名,微博昵称,微信昵称,";
			break;
		}
		for (int i = 0; i < cloum.size(); i++)
		{
			AptMapping c = cloum.get(i);
			columStr += cvsStr(c.getName()) + ",";
		}
		columStr += "提交时间";
		rs.add(columStr);
		List<AppointmentDataModel> list = interactAptDao.findExportData(aptid, owmer, utype, starttime, endtime);
		for (AppointmentDataModel model : list)
		{
			long entityid=model.getWbuid();
			if (utype == 0)
			{
				model.setTel(hyUserDao.findNickname(0,entityid,0));
				model.setNickname(sinaUserDao.findNickNameById(entityid));
			} else if (utype == 1)
			{
				model.setTel(hyUserDao.findNickname(0,0,entityid));
				model.setNickname(weiXinDao.findNickNameById(entityid));
			} else if (utype == -1)
			{
				model.setTel(hyUserDao.findNickname(entityid,0,0));
			}  else if (utype == 2)
			{
				model.setTel(hyUserDao.findNickname(entityid,0,0));
				model.setNickname(sinaUserDao.findNickNameById(entityid));
				model.setWxnickname(weiXinDao.findNickNameById(entityid));
			}
			model.composeList(cloum);
			if (model.getList() != null && model.getList().size() > 0)
			{
				String recordStr = "";
				switch (utype)
				{
				case 0:
					recordStr = cvsStr(model.getTel()) + "," + cvsStr(model.getNickname()) + ",";
					break;
				case 1:
					recordStr = cvsStr(model.getTel()) + "," + cvsStr(model.getNickname()) + ",";
					break;
				case 2:
					recordStr = cvsStr(model.getTel()) + "," + cvsStr(model.getNickname()) + "," + cvsStr(model.getWxnickname()) + ",";
					break;
				}
				for (int i = 0; i < model.getList().size(); i++)
				{
					String r = model.getList().get(i);
					recordStr += cvsStr(r) + ",";
				}
				Date createtime = model.getCreatetime();
				String timeStr = " ";
				if (createtime != null)
				{
					timeStr = DateUtil.getDateTimeString(createtime);
				}
				recordStr += cvsStr(timeStr);
				rs.add(recordStr);
			}
		}

		return rs;
	}

	private String cvsStr(String str)
	{
		if (str != null)
		{
			if (str.indexOf(",") != -1)
			{
				return "\"" + str + "\"";
			} else
			{
				return str;
			}
		} else
		{
			return " ";
		}
	}

	@Override
	public int delAptRecord(long rid)
	{
		return interactAptDao.delAptRecord(rid);
	}

	@Override
	public IDto findAptCodeList(long aptid, int pageId, String code, int status)
	{
		AptCodeDto dto = new AptCodeDto();
		int total = interactAptDao.findAptCodeTotal(aptid, code, status);
		int used=interactAptDao.findAptCodeTotal(aptid, null, 1);
		int least=interactAptDao.findAptCodeTotal(aptid, null, 0);
		dto.setLeast(least);
		dto.setUsed(used);
		if (total > 0)
		{
			List<AptCode> list = interactAptDao.findAptCodeList(aptid, code, status, (pageId - 1) * 20, 20);
			dto.setList(list);
			dto.setPager(new Pager(pageId, total, 20));
		}
		AppointmentModel am=interactAptDao.findOrderMes(aptid);
		dto.setApt(am);
		return dto;
	}
	
	@Override
	public int saveAptCode(long aptid, List<String> insertList)
	{
		return interactAptDao.saveAptCode(aptid,insertList);
	}
	
	private String getMappingTag(String mapping){
		Pattern p=Pattern.compile("^f[1-9]+$");
		Matcher m=p.matcher(mapping);
		if(m.matches()){
			return "N";
		}else{
			return "Y";
		}
	}

	@Override
	public int findCode(String code, long aptid,int coded)
	{
		return interactAptDao.findCode(code, aptid,coded);
	}

	@Override
	public void updateCode(String code, long aptid)
	{
		interactAptDao.updateCode(code, aptid);		
	}

	@Override
	public int updateAptCode(int status, long aptid)
	{
		return interactAptDao.updateCoded(aptid,status);
	}
	@Override
	public AppointmentDataModel findAppointmentDataByWxuid(long wxuid, long aptid, long ownerid)
	{
		AppointmentDataModel model = interactAptDao.findAppointmentDataByWxuid(wxuid, aptid, ownerid);
		if(model!=null){
			if ("0".equals(model.getType()))
			{
				model.setNickname(sinaUserDao.findNickNameById(model.getWbuid()));
			}
			model.setMaps(interactAptDao.findAllColum(model.getAptid()));
		}
		return model;
	}
	
	@Override
	public AppointmentDataModel findAppointMaps(long aptid)
	{
		AppointmentDataModel model =new AppointmentDataModel();
		model.setMaps(interactAptDao.findAllColum(aptid));
		return model;
	}
	
	@Override
	public int updateAptRecord(AppointmentDataModel record)
	{
			return interactAptDao.updateAptRecord(record);
	}
}
