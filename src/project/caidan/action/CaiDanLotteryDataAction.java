package project.caidan.action;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.interact.lottery.dto.LotteryDataDto;
import com.huiyee.interact.lottery.mgr.ILotteryPrizeMgr;
import com.huiyee.interact.lottery.mgr.ILotteryUserMgr;


public class CaiDanLotteryDataAction extends InteractModelAction
{
	
	private static final long serialVersionUID = 7590447416388783630L;
	private LotteryDataDto dto;
	private ILotteryUserMgr lotteryUserMgr;
	private ILotteryPrizeMgr lotteryPrizeMgr;
	private String htype;
	private String type;
	private long mid;
	private int media;
	private int pageId = 1;
	private long lid;
	private String nickName;
	
	public void setLotteryPrizeMgr(ILotteryPrizeMgr lotteryPrizeMgr)
	{
		this.lotteryPrizeMgr = lotteryPrizeMgr;
	}
	public void setLotteryUserMgr(ILotteryUserMgr lotteryUserMgr)
	{
		this.lotteryUserMgr = lotteryUserMgr;
	}
	/**
	 * wbÊý¾Ý
	 */
	@Override
	public String execute() throws Exception
	{
		dto = (LotteryDataDto) lotteryUserMgr.findLotteryData(pageId, nickName, lid, this.getOwner(), media);
		return SUCCESS;
	}
	
	public String winList() throws Exception
	{
		dto = (LotteryDataDto) lotteryPrizeMgr.findLotteryPrize(lid, pageId);
		return SUCCESS;
	}

	
	public LotteryDataDto getDto()
	{
		return dto;
	}

	
	public void setDto(LotteryDataDto dto)
	{
		this.dto = dto;
	}

	public String getHtype()
	{
		return htype;
	}
	
	public void setHtype(String htype)
	{
		this.htype = htype;
	}
	public long getMid()
	{
		return mid;
	}

	
	public void setMid(long mid)
	{
		this.mid = mid;
	}

	
	public int getMedia()
	{
		return media;
	}

	
	public void setMedia(int media)
	{
		this.media = media;
	}
	
	public int getPageId()
	{
		return pageId;
	}
	
	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}
	
	public long getLid()
	{
		return lid;
	}
	
	public void setLid(long lid)
	{
		this.lid = lid;
	}
	
	public String getNickName()
	{
		return nickName;
	}
	
	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
		htype = type;
	}

	
	
	
	
	
}
