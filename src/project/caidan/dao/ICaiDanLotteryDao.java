package project.caidan.dao;

import java.util.List;

import com.huiyee.interact.lottery.model.Lottery;

import project.caidan.model.CDLottery;
import project.caidan.model.CDSet;


public interface ICaiDanLotteryDao
{
	public List<CDSet> findCdSetByType(String type);
	
	public int findMaxIdx(long owner,String type);
	
	public long saveCdLottery(long lid, String img, long enid,int idx);
	
	public int findLotteryTotal(long owner, String type, long omid);

	public List<Lottery> findAllLottery(int start, int size, long owner, String type, long omid);
	
	public List<Lottery> findAllLottery(long owner, long omid);
	
	public int updateCdLottery(long lid, String img, long enid);
	
	public CDLottery findCdLotteryById(long lid);
	
	public Lottery findLotteryById(long lid,long owner,String type);
	
	public Lottery findFrontLottery(long lid,long owner,String type);
	
	public Lottery findNextLottery(long lid,long owner,String type);
	
	public int updateLotteryIdx(long lid,long idx);
}
