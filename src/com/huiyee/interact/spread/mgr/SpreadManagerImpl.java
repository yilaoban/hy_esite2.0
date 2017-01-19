package com.huiyee.interact.spread.mgr;

import java.util.List;
import java.util.Random;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.ToolUtils;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.spread.dao.ISpreadDao;
import com.huiyee.interact.spread.dto.IDto;
import com.huiyee.interact.spread.dto.SpreadDto;
import com.huiyee.interact.spread.model.SpreadModel;
import com.huiyee.interact.spread.model.SpreadOption;

public class SpreadManagerImpl extends AbstractMgr implements ISpreadManager {

	private ISpreadDao spreadDao;

	public ISpreadDao getSpreadDao() {
		return spreadDao;
	}

	public void setSpreadDao(ISpreadDao spreadDao) {
		this.spreadDao = spreadDao;
	}

	@Override
	public int findSpreadListTotal(long ownerid, long omid) {
		return spreadDao.findSpreadListTotal(ownerid, omid);
	}

	@Override
	public IDto findSpreadList(long ownerid, int start, int size, long omid) {
		SpreadDto dto = new SpreadDto();
		List<SpreadModel> spreadList = spreadDao.findSpreadList(ownerid, start,
				size, omid);
		dto.setSpreadList(spreadList);
		return dto;
	}

	@Override
	public List<Lottery> findLotteryByType(long ownerid, String type) {
		return spreadDao.findLotteryByType(ownerid, type);
	}

	@Override
	public long saveSpreadDesign(long ownerid, SpreadDto dto, long omid) {
		return spreadDao.saveSpreadDesign(ownerid, dto, omid);
	}

	@Override
	public IDto findSpreadModelById(long spreadid,long ownerid) {
		SpreadDto dto = new SpreadDto();
		SpreadModel spread = spreadDao.findSpreadModelById(spreadid,ownerid);
		dto.setSpread(spread);
		return dto;
	}

	@Override
	public Lottery findLotteryById(long lotteryid) {
		return spreadDao.findLotteryById(lotteryid);
	}

	@Override
	public long updateSpreadDesign(long ownerid, SpreadDto dto, long spreadid) {
		return spreadDao.updateSpreadDesign(ownerid, dto, spreadid);
	}

	@Override
	public int deleteSpread(long id) {
		return spreadDao.deleteSpread(id);
	}

	@Override
	public HdRsDto saveSpreadRecord(long pageid,  long spreadid,
			VisitUser u,String content,String pic,long wbid, String ip, String terminal, String source,long oid,String nicknames, SpreadModel s) {
		HdRsDto rs=new HdRsDto();
		rs.setHydesc("传播成功！");
		rs.setStatus(1);
		if(s.getSharelimit() >0){
			int num = 0;
			if(u.getWbuid() != 0 && u.getWxuid() == 0){
				num = spreadDao.findSpreadRecordCountByWbuid(u.getWbuid(), spreadid);//查看wbuid 参加几次调研
			}else if(u.getWxuid() != 0 && u.getWbuid() == 0){
				num = spreadDao.findSpreadRecordCountByWxuid(u.getWxuid(), spreadid);//查看wbuid 参加几次调研
			}
			if(num >= s.getSharelimit()){
				rs.setStatus(0);
				rs.setHydesc("超过次数限制");
			}			
		}
		long entityid=u.getUid();
		int type=u.getCd();
		if(u.getWbuid() >0){
			String token = spreadDao.findTokenByPageidAndWbuid(u.getWbuid(), pageid);
			if(token == null){
				rs.setHydesc("新浪token失效");
				rs.setStatus(-2);
			}
			SpreadOption so = spreadDao.findSpreadOptionByOid(oid);
			String cont="";
			if (IInteractConstants.SPREAD_TYPE_SRE.equals(s.getType())) {
				//随机选择直发
				List<SpreadOption> optionList = spreadDao.findSpreadOptionsBySpreadid(spreadid);
				if(optionList != null && optionList.size()>0){
					Random r = new Random();  
					int index = r.nextInt(optionList.size());
					so = optionList.get(index);
				}
				if(so != null){
					cont = processConten(so.getContent(),nicknames,content);					
				}else{
					cont = processConten1(nicknames,content);
				}
				if(!StringUtils.isEmpty(pic)){
					try {
						this.getJustWS().weiboByPic(token, cont, pic);
					} catch (Exception e) {
						rs.setHydesc("新浪接口繁忙！");
						rs.setStatus(-3);
					}	
				}else{
					try {
						this.getJustWS().weibo(token, cont);
					} catch (Exception e) {
						rs.setHydesc("新浪接口繁忙！");
						rs.setStatus(-3);
					}
				}
				spreadDao.saveSpreadRecord(pageid, u.getWbuid(), spreadid, cont, pic, null, ip, terminal, u.getSource(),oid,nicknames);
				this.updateInteractAction(s,entityid,type);
				if(s.getBalance()!=0&&u.getHyUser()!=null)
				{
					this.updateBalance(u.getHyUser().getId(), s.getBalance(), "传散获得积分", "HUD" ,"SPR", s.getId());
				}
			} else if (IInteractConstants.SPREAD_TYPE_FWD.equals(s.getType())) {
				//选择内容直发
				if(so != null){
					cont = processConten(so.getContent(),nicknames,content);					
				}else{
					cont = processConten1(nicknames,content);
				}
				if(!StringUtils.isEmpty(pic)){
					try {
						this.getJustWS().weiboByPic(token, cont, pic);
					} catch (Exception e) {
						rs.setHydesc("新浪接口繁忙！");
						rs.setStatus(-3);
					}	
				}else{
					try {
						this.getJustWS().weibo(token, cont);
					} catch (Exception e) {
						rs.setHydesc("新浪接口繁忙！");
						rs.setStatus(-3);
					}
				}
				spreadDao.saveSpreadRecord(pageid, u.getWbuid(), spreadid, cont, pic, null, ip, terminal, u.getSource(),oid,nicknames);
				this.updateInteractAction(s,entityid,type);
				if(s.getBalance()!=0&&u.getHyUser()!=null)
				{
					this.updateBalance(u.getHyUser().getId(), s.getBalance(), "传散获得积分", "HUD" ,"SPR", s.getId());
				}
			} else if (IInteractConstants.SPREAD_TYPE_FAC.equals(s.getType())) {
				if(so == null){
					List<SpreadOption> optionList = spreadDao.findOptionsBySpreadid(spreadid);
					if(optionList != null && optionList.size()>0){
						so = optionList.get(0);
					}else{
						rs.setHydesc("次数上限");
						rs.setStatus(-2);
					}
				}
				cont = content;
				this.getJustWS().repost( so.getWbid(), content, token, 3);
				
				spreadDao.saveSpreadRecord(pageid, u.getWbuid(), spreadid, cont, pic, so.getWbid(), ip, terminal, u.getSource(),oid,nicknames);
				this.updateInteractAction(s,entityid,type);
				if(s.getBalance()!=0&&u.getHyUser()!=null)
				{
					this.updateBalance(u.getHyUser().getId(), s.getBalance(), "传散获得积分", "HUD" ,"SPR", s.getId());
				}
			}else{
				rs.setHydesc("需要转发并评论！");
				rs.setStatus(-4);
			}
		}else if(u.getWxuid() >0){
		
			spreadDao.saveWxSpreadRecord(pageid,spreadid,content,ip,terminal,u.getSource(),oid,u.getWxuid(),nicknames);
			this.updateInteractAction(s,entityid,type);
			if(s.getBalance()!=0&&u.getHyUser()!=null)
			{
				this.updateBalance(u.getHyUser().getId(), s.getBalance(), "传散获得积分", "HUD" ,"SPR", s.getId());
			}
		}
		return rs;
	}
	
	private String processConten(String content,String nicknames,String ucontent){
		if(content.contains("$nickname")){
			int count = ToolUtils.countStr(0,content,"$nickname");
			JSONArray ja = JSONArray.fromObject(nicknames);
			for(int i = 0 ;i<count ;i++){				
				content = content.replace("$nickname"+(i+1), ja.getString(i));
			}
		}
		return content;
	}
	
	private String processConten1(String nicknames,String content){
		JSONArray ja = JSONArray.fromObject(nicknames);
		for(int i=0;i<ja.size();i++){
			if(i < ja.size() - 1){
				content = content + "@" + ja.getString(i) + ",";
			}else{
				content = content + "@" + ja.getString(i);
			}
		}
		return content;
	}
	
	@Override
	public SpreadModel previewSpread(long spreadid, long ownerid) {
		SpreadModel spread= spreadDao.findSpreadModelById(spreadid, ownerid);
		spread.setOptions(spreadDao.findOptionsBySpreadid(spreadid));
		return spread;
	}

	public int updateRuletypeByLotteryid(long lotteryid){
		return spreadDao.updateRuletypeByLotteryid(lotteryid);
	}
	
	@Override
	public SpreadModel findSpreadByIdAndPageId(long spreadid, long pageid)
	{
		SpreadModel s = spreadDao.findSpreadModelById(spreadid,0);
		return s;
	}

	@Override
	public long addSpread(long ownerid, String title)
	{
		return spreadDao.addSpread(ownerid, title);
	}
}
