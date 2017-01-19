package com.huiyee.interact.research.mgr;

import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dao.ITemplateDao;
import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.model.BlockContext;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageCard;
import com.huiyee.esite.model.TemplateBlock;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HttpTookit;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.research.dao.IResearchDao;
import com.huiyee.interact.research.dto.IDto;
import com.huiyee.interact.research.dto.ResearceRecordDto;
import com.huiyee.interact.research.dto.ResearchDto;
import com.huiyee.interact.research.dto.ResearchSubDto;
import com.huiyee.interact.research.model.ResearchModel;
import com.huiyee.interact.research.model.ResearchQuestionModel;
import com.huiyee.interact.research.model.ResearchRecord;
import com.huiyee.interact.vote.model.Pager;

import net.sf.json.JSONObject;
public class ResearchManagerImpl extends AbstractMgr implements IResearchManager{

	private IResearchDao researchDao;
	private ITemplateDao templateDao;
	
	
	public void setTemplateDao(ITemplateDao templateDao)
	{
		this.templateDao = templateDao;
	}

	public IResearchDao getResearchDao(){
		return researchDao;
	}

	public void setResearchDao(IResearchDao researchDao){
		this.researchDao = researchDao;
	}
	
	@Override
	public IDto findResearchList(long ownerid, int start, int size, long omid){
		ResearchDto dto = new ResearchDto();
		List<ResearchModel>  researchList = researchDao.findVoteList(ownerid, start, size, omid);
		dto.setResearchList(researchList);
		return dto;
	}

	@Override
	public int findResearchListTotal(long ownerid, long omid){
		return researchDao.findResearchListTotal(ownerid, omid);
	}

	@Override
	public ResearchModel findResearchModelById(long id, long ownerid){
		ResearchModel m = researchDao.findResearchModelById(id);
		List<ResearchQuestionModel> list = researchDao.findQuestionsByResearchid(id);
		for(ResearchQuestionModel q : list){
			q.setOptions(researchDao.findOptionsByQuestionid(q.getId()));
		}
		m.setQuestions(list);
		return m;
	}
	
	@Override
	public List<Lottery> findLotteryByType(long ownerid, String type){
		return researchDao.findLotteryByType(ownerid, type);
	}

	@Override
	public long saveResearchDesign(long ownerid, ResearchDto dto, long omid){
		return researchDao.saveResearchDesign(ownerid, dto, omid);
	}

	@Override
	public IDto findResearchModelById(long researchid){
		ResearchDto dto = new ResearchDto();
		ResearchModel research = researchDao.findResearchModelById(researchid);
		dto.setResearch(research);
		return dto;
	}

	@Override
	public Lottery findLotteryById(long lotteryid){
		return researchDao.findLotteryById(lotteryid);
	}

	@Override
	public long updateResearchDesign(long ownerid, ResearchDto dto, long researchid){
		return researchDao.updateResearchDesign(ownerid,dto,researchid);
	}
	
	@Override
	public IDto findResearchRecordList(long searchid, int type, String nickname, String source, int pageId,long owner)
	{
		
		ResearceRecordDto dto = new ResearceRecordDto();
		int start = (pageId - 1) * IInteractConstants.VOTE_LIMIT;
		if(type==0){
			int total = researchDao.findResearchRecordTotal(searchid,owner);
			if (total > 0){
				List<ResearchRecord> list = researchDao.findResearchRecordList(searchid, start, IInteractConstants.VOTE_LIMIT,owner);
				dto.setList(list);
			}
			Pager pager = new Pager(pageId, total, IInteractConstants.VOTE_LIMIT);
			dto.setPager(pager); 
		}else if(type==1){
			int total=researchDao.findWxResearchRecordListTotal(searchid,source,owner);
			List<ResearchRecord> sourceList = researchDao.findWxSourceByResearchRecord(searchid);
			dto.setSourceList(sourceList);
			if (total > 0){
				List<ResearchRecord> list = researchDao.findWxResearchRecordList(searchid, start, IInteractConstants.VOTE_LIMIT,source,owner);
				dto.setList(list);
			}
			Pager pager = new Pager(pageId, total, IInteractConstants.VOTE_LIMIT);
			dto.setPager(pager);
		}else{
			int total=researchDao.findNiResearchRecordTotal(searchid,type,owner);
			if (total > 0){
				List<ResearchRecord> list = researchDao.findNiResearchRecordList(searchid,type,start, IInteractConstants.VOTE_LIMIT,owner);
				dto.setList(list);
			}
			Pager pager = new Pager(pageId, total, IInteractConstants.VOTE_LIMIT);
			dto.setPager(pager);
		}
		return dto;
	}

	@Override
	public HdRsDto saveResearchReocrd(ResearchSubDto dto,long pageid,VisitUser visit, String ip, String terminal,long relationid, ResearchModel research){
	        HdRsDto rs=new HdRsDto();
	        rs.setStatus(1);
	        rs.setHydesc("调研成功！");
			long entityid=visit.getUid();
			int type=visit.getCd();
			long recordid = researchDao.saveResearchReocrd(pageid, entityid, research.getId(), ip, terminal, visit.getSource(),type);
			if(recordid > 0){
				this.updateInteractAction(research, entityid, type);
				if(research.getBalance()!=0&&visit.getHyUser()!=null)
				{
					this.updateBalance(visit.getHyUser().getId(), research.getBalance(), "调研获得积分","HUD" , "RSH", research.getId());
				}
				
				if(dto.getAnswerStr()!=null){
					String[] str=dto.getAnswerStr().split(";");
					for(int i=0;i<str.length;i++){
						String[] str1=str[i].split(":");
						String typeStr=researchDao.findResearchType(Long.parseLong(str1[0]));
						//排序
						if("ORD".equals(typeStr)){
							//类如160：170-1再次分解
							String[] str2=str1[1].split("-");
							researchDao.saveResearchAnswerPx(recordid, Long.parseLong(str1[0]), Long.parseLong(str2[0]), Integer.parseInt(str2[1]));
							researchDao.updateResearchQuestionOption(Long.parseLong(str2[0]));
						}
						//多选
						if("MUP".equals(typeStr)){
							researchDao.saveResearchAnswerXZ(recordid, Long.parseLong(str1[0]), Long.parseLong(str1[1]));
							researchDao.updateResearchQuestionOption(Long.parseLong(str1[1]));
						}
						//单选
						if("SGL".equals(typeStr)){
							researchDao.saveResearchAnswerXZ(recordid, Long.parseLong(str1[0]), Long.parseLong(str1[1]));
							researchDao.updateResearchQuestionOption(Long.parseLong(str1[1]));
						}
						//打分
						if("GAD".equals(typeStr)){
							if(str1.length >1){
								researchDao.saveResearchAnswerTK(recordid, Long.parseLong(str1[0]), str1[1]);								
							}else{
								researchDao.saveResearchAnswerTK(recordid, Long.parseLong(str1[0]), "");
							}
						}
						//填空
						if("FIL".equals(typeStr)){
							if(str1.length >1){
								researchDao.saveResearchAnswerTK(recordid, Long.parseLong(str1[0]), str1[1]);								
							}else{
								researchDao.saveResearchAnswerTK(recordid, Long.parseLong(str1[0]), "");
							}
						}
					}
				}
			}
		if(relationid>0)
		{
			TemplateBlock tb = templateDao.findTemplateBlockByRelationid(relationid);
			if(tb != null){
				PageBlockRelation r = templateDao.findPageBlockRelationByRelationid(relationid);
				String jtype = "N";
				String url = null;
				if(r != null){
					String json = r.getJson();
					JSONObject jo = JSONObject.fromObject(json);
					try {
						jo = jo.getJSONObject("obj");
						jo = jo.getJSONObject("redirect");
						jtype = jo.getString("type");
						url = jo.getString("url");
					}catch (Exception e) {
						jtype = "N";
					}
				}
				if("N".equals(jtype) ){ //默认跳转
					BlockContext bc = templateDao.findBlockContextid(tb.getId(), "Y");
					if(bc != null){
						Page page = templateDao.findPageByRelationidAndContextid(relationid, bc.getId());
						if (page != null) {
							List<PageCard> cardList = templateDao.findTemplateCardByPageid(page.getId());
							if (cardList != null && cardList.size() > 0) { // 有子页面且页面上有卡片则需要跳转
								rs.setId(page.getId());
							}
						}					
					}
				}else if("R".equals(jtype)){//通过http获取跳转地址
					url = HttpTookit.doGet(url, null, "utf-8", true);
				}
				rs.setUrl(url);
			}
		}
		return rs;
	}

	@Override
	public int deleteResearch(long id)
	{
		researchDao.deleteResearch(id);
		return 0;
	}
	
	
	@Override
	public int updateRuletypeByLotteryid(long lotteryid) {
		return researchDao.updateRuletypeByLotteryid(lotteryid);
	}
	
	@Override
	public ResearchModel findModelByIdAndPageId(long researchid, long pageid)
	{
		ResearchModel research=researchDao.findResearchModelById(researchid);
		return research;
	}

	@Override
	public long addResearch(long ownerid, String title)
	{
		return researchDao.addResearch(ownerid, title);
	}
	
	@Override
	public int updateResearchClean(long searchid, long owner)
	{
		ResearchModel m=researchDao.findResearchModelById(searchid);
		if(m!=null&&m.getOwnerid()==owner){
			return researchDao.updateResearchClean(searchid);
		}
		return 0;
	}
}
