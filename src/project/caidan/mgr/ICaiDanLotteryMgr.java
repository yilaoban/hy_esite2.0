package project.caidan.mgr;

import java.util.List;

import com.huiyee.interact.lottery.dto.IDto;

import project.caidan.model.CDLottery;
import project.caidan.model.CDSet;



public interface ICaiDanLotteryMgr
{
	public IDto findAllLottery(int pageId, long owner, String type, long omid);
	
	public IDto findAllLottery(long owner, long omid,int size);
	
	public List<CDSet> findCdSetByType(String type);
	
	public long saveCdLottery(long lid,String img,long enid,long owner,String htype);
	
	public int updateCdLottery(long lid,String img,long enid);
	
	public CDLottery findCdLotteryById(long lid);
	
	public int updatelotteryUp(long lid,long owner, String type);
	
	public int updatelotteryDown(long lid,long owner, String type);
}
