package com.huiyee.interact.vote.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.huiyee.interact.vote.model.InteractVote;
import com.huiyee.interact.vote.model.Pager;

public class VoteDto implements IDto {

	private List<InteractVote> list;
	private Pager pager;

	private String title;
	private Date starttime;
	private Date endtime;
	private String islottery;
	private String content;
	private long lotteryid;
	private String type;
	private long zlotteryid;
	private long llotteryid;
	private long glotteryid;
	private long ylotteryid;
	private InteractVote vote;
	private String votetype;
	private int balance;
	private int totallimit;
	private int daylimit;
	private int maxlotterychance;
	private int lotterychance;
	private String lotterytype;//抽奖类型
	private String startnote;// 开始提示语
	private String endnote;// 结束提示语
	
	public String getLotterytype()
	{
		return lotterytype;
	}

	
	public List<InteractVote> getList()
	{
		return list;
	}

	public void setList(List<InteractVote> list)
	{
		this.list = list;
	}
	
	public InteractVote getVote()
	{
		return vote;
	}
	
	public void setVote(InteractVote vote)
	{
		this.vote = vote;
	}

	public void setLotterytype(String lotterytype)
	{
		this.lotterytype = lotterytype;
	}


	public int getLotterychance()
	{
		return lotterychance;
	}

	
	public void setLotterychance(int lotterychance)
	{
		this.lotterychance = lotterychance;
	}

	public String getStartnote() {
		return startnote;
	}

	public void setStartnote(String startnote) {
		this.startnote = startnote;
	}

	public String getEndnote() {
		return endnote;
	}

	public void setEndnote(String endnote) {
		this.endnote = endnote;
	}

	public int getTotallimit() {
		return totallimit;
	}

	public void setTotallimit(int totallimit) {
		this.totallimit = totallimit;
	}

	public int getMaxlotterychance() {
		return maxlotterychance;
	}

	public void setMaxlotterychance(int maxlotterychance) {
		this.maxlotterychance = maxlotterychance;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		if (StringUtils.isNotBlank(starttime)) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date start = sdf.parse(starttime);
				this.starttime = start;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		if (StringUtils.isNotBlank(endtime)) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date end = sdf.parse(endtime);
				this.endtime = end;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getIslottery() {
		return islottery;
	}

	public void setIslottery(String islottery) {
		this.islottery = islottery;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getLotteryid() {
		return lotteryid;
	}

	public void setLotteryid(long lotteryid) {
		this.lotteryid = lotteryid;
	}

	public long getZlotteryid() {
		return zlotteryid;
	}

	public void setZlotteryid(long zlotteryid) {
		this.zlotteryid = zlotteryid;
	}

	public long getLlotteryid() {
		return llotteryid;
	}

	public void setLlotteryid(long llotteryid) {
		this.llotteryid = llotteryid;
	}

	public long getGlotteryid() {
		return glotteryid;
	}

	public void setGlotteryid(long glotteryid) {
		this.glotteryid = glotteryid;
	}

	public String getVotetype() {
		return votetype;
	}

	public void setVotetype(String votetype) {
		this.votetype = votetype;
	}

	public long getYlotteryid() {
		return ylotteryid;
	}

	public void setYlotteryid(long ylotteryid) {
		this.ylotteryid = ylotteryid;
	}

	public int getDaylimit() {
		return daylimit;
	}

	public void setDaylimit(int daylimit) {
		this.daylimit = daylimit;
	}

	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}

	
	public void setStarttime(Date starttime)
	{
		this.starttime = starttime;
	}
	
	public void setEndtime(Date endtime)
	{
		this.endtime = endtime;
	}

}
