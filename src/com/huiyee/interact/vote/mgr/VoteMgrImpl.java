package com.huiyee.interact.vote.mgr;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dao.ITemplateDao;
import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.BlockContext;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageCard;
import com.huiyee.esite.model.TemplateBlock;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.service.FileUploadService;
import com.huiyee.esite.util.HttpTookit;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.vote.dao.IVoteDao;
import com.huiyee.interact.vote.dao.IVoteOptionDao;
import com.huiyee.interact.vote.dto.IDto;
import com.huiyee.interact.vote.dto.VoteDto;
import com.huiyee.interact.vote.dto.VoteOptionDto;
import com.huiyee.interact.vote.dto.VoteRecordDto;
import com.huiyee.interact.vote.dto.VoteRecordQueryDto;
import com.huiyee.interact.vote.model.InteractVote;
import com.huiyee.interact.vote.model.Pager;
import com.huiyee.interact.vote.model.VoteOption;
import com.huiyee.interact.vote.model.VoteOptionModel;
import com.huiyee.interact.vote.model.VoteRecord;
import com.huiyee.interact.xc.dao.IXcfeatureDao;

public class VoteMgrImpl extends AbstractMgr implements IVoteMgr{

	private IVoteDao voteDao;
	private IVoteOptionDao optionDao;
	private IXcfeatureDao xcfeatureDao;
	private ITemplateDao templateDao;

	
	public void setTemplateDao(ITemplateDao templateDao)
	{
		this.templateDao = templateDao;
	}

	public IVoteDao getVoteDao() {
		return voteDao;
	}

	public void setVoteDao(IVoteDao voteDao) {
		this.voteDao = voteDao;
	} 

	@Override
	public List<InteractVote> findVoteList(long ownerid, int start, int size, long omid) {
		return voteDao.findVoteList(ownerid, start, size, omid);
	}

	@Override
	public int findVoteListTotal(long ownerid, long omid) {
		return voteDao.findVoteListTotal(ownerid, omid);
	}

	@Override
	public long saveVoteDesign(long ownerid,VoteDto dto,long omid) {
		return voteDao.saveVoteDesign(ownerid,dto,omid);
	}

	@Override
	public IDto findVoteManageModelById(long voteid,long ownerid) {
		VoteDto dto = new VoteDto();
		InteractVote vote = voteDao.findVoteManageModelById(voteid);
		dto.setVote(vote);
		return dto;
	}

	@Override
	public long updateVoteDesign(long ownerid, VoteDto dto,long voteid) {
		return voteDao.updateVoteDesign(ownerid,dto,voteid);
	}
    public VoteOptionDto findVoteData(long voteid) {
        VoteOptionDto dto = new VoteOptionDto();
        List<List<Object>> optionobj = new ArrayList<List<Object>>();
        List<VoteOption> list = voteDao.findVoteOptionList(voteid);
        DecimalFormat df2 = new DecimalFormat("##.##%");
        int total = voteDao.findVoteOptionTotalByVoteid(voteid);
        if (list != null && list.size() > 0) {
            for (VoteOption option : list) {
                int num = option.getCount();
                String content = option.getContent();
                List<Object> votecontents = new ArrayList<Object>();
                if (total != 0) {
                    double optionnum = (num * 1.00) / (total * 1.00);
                    String percent=optionnum != 0 ? df2.format(optionnum) : "0%";
                    votecontents.add("'"+content + "=" + percent+"'");
                    option.setPercent(percent);
                } else {
                    votecontents.add("'"+content + "=0%'");
                    option.setPercent("0%");
                }
                votecontents.add(num);
                optionobj.add(votecontents);
            }
        }
        dto.setVotedata(optionobj);
        dto.setTotal(total);
        dto.setList(list);
        return dto;
    }
    
    @Override
    public IDto findVoteRecordData(long voteid, VoteRecordQueryDto queryDto,int pageId,int type,long owner) {
        VoteRecordDto dto=new VoteRecordDto();
        int start = (pageId - 1) * IInteractConstants.VOTE_LIMIT;
        int total = voteDao.findVoteRecordTotal(voteid, queryDto,type,owner);
        if(total>0){
            List<VoteRecord> list= voteDao.findVoteRecordList(voteid, queryDto, start, IInteractConstants.VOTE_LIMIT,type,owner);
            dto.setList(list);
        }
        Pager pager = new Pager(pageId, total, IInteractConstants.VOTE_LIMIT);
        dto.setPager(pager);
        return dto;
    }

    private String createFileName(String fileName,String type) {
		int index = fileName.lastIndexOf('.');
		// 判断上传文件名是否有扩展名
		if (index != -1) {
			return FileUploadService.getNow()+type+ fileName.substring(index);
		}
		return FileUploadService.getNow()+type;
	}
    
	@Override
	public int saveVoteContent(VoteOptionModel vom, long voteid) {
		int result=0;
		long mid=10002;
		Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid=account.getOwner().getId();
			String content=vom.getContent();
			if(vom.getImg()==null){
				String pic=vom.getPic();
				result=voteDao.saveVoteContent(voteid, content,vom.getDescription(), pic,vom.getVediourl(),vom.getTag(),vom.getLinked(),vom.getLinkurl());
			}else{	
				String name=createFileName(vom.getImgFileName(),"_m");
				String path = FileUploadService.getFilePath(IInteractConstants.TYPE_INTERACT, ownerid, mid+"");
				String saveResult = FileUploadService.saveFile(vom.getImg(),path,name);
				result=voteDao.saveVoteContent(voteid, content,vom.getDescription(), saveResult,vom.getVediourl(),vom.getTag(),vom.getLinked(),vom.getLinkurl());
			}
			if(result>0){
				result=1;
			}
			return result;
	}

	@Override
	public List<VoteOption> searchVoteOptionList(long id,int start,int size)  {
		return voteDao.searchVoteOptionList(id,start,size);
	}

	@Override
	public List<Lottery> findLotteryByType(long ownerid, String type) {
		return voteDao.findLotteryByType(ownerid, type);
	}

	@Override
	public InteractVote findVoteModelWithOptionsById(long voteid, long ownerid) {
		InteractVote v = voteDao.findVoteManageModelById(voteid);
		v.setOptions(optionDao.findOptionsByVoteid(voteid));
		return v;
	}

	public void setOptionDao(IVoteOptionDao optionDao) {
		this.optionDao = optionDao;
	}

	@Override
	public Lottery findLotteryById(long lotteryid) {
		return voteDao.findLotteryById(lotteryid);
	}

	@Override
	public HdRsDto saveVoteRecord(VisitUser visit, long cho,
			String ip, String terminal, long pageid,long relationid, InteractVote vote) {
		
		    HdRsDto rs=new HdRsDto();
		    if(cho==0)
		    {
		    	   rs.setStatus(-1);
			       rs.setHydesc("没有选项！");	
			       return rs;
		    }
	       	
			long entityid=visit.getUid();
			int type=visit.getCd();
			int mr=voteDao.findUserDaySub(entityid, type, cho);	
			if(mr>0){
				rs.setStatus(-2);
			    rs.setHydesc("每人每天对于一个选择只有一次机会；");
			    return rs;
			}
			 rs.setStatus(1);
		     rs.setHydesc("投票成功！");	
			long id = voteDao.saveVoteRecord(entityid,type, vote.getId(), ip, terminal, visit.getSource(),pageid);
			this.updateInteractAction(vote,entityid,type);
			if(vote.getBalance()!=0&&visit.getHyUser()!=null)
			{
				this.updateBalance(visit.getHyUser().getId(), vote.getBalance(), "投票获得积分", "HUD" ,"VOT", vote.getId());
			}
			if (id > 0 )
			{
					voteDao.saveVoteOptionRecord(id, cho,entityid,type);
					voteDao.addOptionCount(cho);
			}
		
		if (relationid>0)
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
	public int delVoteOption(long id)
	{
		Map map = voteDao.findIndx(id);
		long voteid = (Long) map.get("voteid");
		int idx = (Integer) map.get("idx");
		voteDao.deleteIndx(idx, voteid);
		return voteDao.delVoteOption(id);
	}

	@Override
	public int updateVoteOption(long id,VoteOptionModel vom,long voteid)
	{
		long mid=10002;
		Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid=account.getOwner().getId();
			String content=vom.getContent();
			if(vom.getImg()!=null){
				String name=createFileName(vom.getImgFileName(),"_m");
				String path = FileUploadService.getFilePath(IInteractConstants.TYPE_INTERACT, ownerid, mid+"");
				String saveResult = FileUploadService.saveFile(vom.getImg(),path,name);
				int result=voteDao.updateVoteOption(id, content, saveResult,vom.getVediourl(),vom.getTag(),vom.getDescription(),vom.getLinked(),vom.getLinkurl());
				if(result>0){
					result=1;
				}
				return result;
			}else if(vom.getImg()==null){
				String Tpic=vom.getPic();
				int result=voteDao.updateVoteOption(id, content, Tpic,vom.getVediourl(),vom.getTag(),vom.getDescription(),vom.getLinked(),vom.getLinkurl());
				if(result>0){
					result=1;
				}
				return result;
			}
			return 0;
	}

	@Override
	public VoteOption findOneOption(long id)
	{
		return voteDao.findOneOption(id);
	}

	@Override
	public int deleteVote(long id)
	{
		xcfeatureDao.deletfeature(10002, id);
		return voteDao.deleteVote(id);
	}

	@Override
	public int findVoteOptionTotal(long id)
	{
		return voteDao.findVoteOptionTotal(id);
	}
	
	@Override
	public int updateRuletypeByLotteryid(long lotteryid) {
		return voteDao.updateRuletypeByLotteryid(lotteryid);
	}
	
	@Override
	public InteractVote findVoteByIdAndPageid(long voteid)
	{
		InteractVote vote = voteDao.findVoteManageModelById(voteid);
		return vote;
	}

	@Override
	public long addVote(long ownerid, String title)
	{
		return voteDao.addVote(ownerid, title);
	}

	public void setXcfeatureDao(IXcfeatureDao xcfeatureDao)
	{
		this.xcfeatureDao = xcfeatureDao;
	}
	
	@Override
	public List<VoteOption> findVoteOptionCount(long voteid,VisitUser visit)
	{
		List<VoteOption> list= voteDao.findVoteOptionListByVoteid(voteid);
		for (VoteOption voteOption : list)
		{
			if(!HyConfig.isRun()){
				int count=optionDao.findUserOptionCount(voteid,voteOption.getId(),visit.getCookieUser().getId(),-1);
				count += optionDao.findUserOptionCount(voteid,voteOption.getId(),visit.getWxuid(),1);
				voteOption.setUserCount(count);
			}
		}
		return list;
	}
	
	@Override
	public List<VoteOption> findVoteOptionCountPC(long espageid)
	{
		return voteDao.findVoteOptionCountPC(espageid);
	}

	@Override
	public int updateContnetIdx(long optionid, long voteid, int oldIdx, int newIdx) {
		return voteDao.updateContnetIdx(optionid, voteid, oldIdx, newIdx);
	}
	
	@Override
	public int updateVoteClean(long voteid, long owner)
	{
		InteractVote m=voteDao.findVoteManageModelById(voteid);
		if (m!=null)
		{
			return xcfeatureDao.updateVoteClean(voteid);
		}
		return 0;
	}
}
