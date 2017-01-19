package com.huiyee.interact.vote.model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.model.SuperHdModel;
import com.huiyee.esite.util.DateUtil;

public class InteractVote extends SuperHdModel implements Serializable{

	private static final long serialVersionUID = 5273056390994903340L;
	private String starttimeStr;
	private String endtimeStr;
	private List<VoteOption> voteOption;
	private int totalOption;
	
	private List<VoteOption> options;
	private List<VoteOptionModel> list;
	
	public InteractVote(){
		setFeatureid(IInteractConstants.INTERACT_VOTE);
	}
	
	public List<VoteOption> getVoteOption()
	{
		return voteOption;
	}
	public void setVoteOption(List<VoteOption> voteOption)
	{
		this.voteOption = voteOption;
	}
	public long getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(long ownerid) {
		this.ownerid = ownerid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		/*if(starttime !=null){
			super.setStart(starttime.getTime());
		}*/
		this.starttime = starttime;
		setStarttimeStr(DateUtil.getDateString(starttime));
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		/*if(endtime != null){
			super.setEnd(endtime.getTime());
		}*/
		this.endtime = endtime;
		setEndtimeStr(DateUtil.getDateString(endtime));
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/*public long getStart() {
		return getStarttime().getTime();
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getEnd() {
		return getEndtime().getTime();
	}
	public void setEnd(long end) {
		this.end = end;
	}*/
	public String getStarttimeStr()
	{
		return starttimeStr;
	}
	public void setStarttimeStr(String starttimeStr)
	{
		this.starttimeStr = starttimeStr;
	}
	public String getEndtimeStr()
	{
		return endtimeStr;
	}
	public void setEndtimeStr(String endtimeStr)
	{
		this.endtimeStr = endtimeStr;
	}

	public long getOmid() {
		return omid;
	}

	public void setOmid(long omid) {
		this.omid = omid;
	}

	public int getTotalOption() {
		return totalOption;
	}

	public void setTotalOption(int totalOption) {
		this.totalOption = totalOption;
	}

	
	public List<VoteOption> getOptions()
	{
		return options;
	}

	
	public void setOptions(List<VoteOption> options)
	{
		this.options = options;
	}

	
	public List<VoteOptionModel> getList()
	{
		return list;
	}

	
	public void setList(List<VoteOptionModel> list)
	{
		this.list = list;
	}
	
}
