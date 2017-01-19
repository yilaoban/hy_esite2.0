package com.huiyee.esite.dto;

import java.util.Date;
import java.util.List;

import com.huiyee.esite.model.Activity;
import com.huiyee.esite.model.ActivityLog;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Source;
import com.huiyee.esite.model.Terminal;
import com.huiyee.esite.model.VisitLog;

public class SiteVistLogDto implements IDto
{
	private List<VisitLog> vistLogList;
	private List<Activity> activityList;
	private List<ActivityLog> activityLogList;
	private Pager pager;
	private long userCount;
	private long unKnownCount;
	private Date createtime;//非匿名最新创建时间
	private Date unknowncreatetime;//匿名最新创建时间
	private long countSum;
	private long participateCount;
	private long participateSuccessCount;
	private long participateFailCount;
	
	private List<Terminal> terminal;
	private List<Source> source;
	private List<List<Object>> terminalList;
	private List<List<Object>> sourceList;
	private Site site;
	private long visitCount;
	
	public List<Activity> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}

	public List<ActivityLog> getActivityLogList() {
		return activityLogList;
	}

	public void setActivityLogList(List<ActivityLog> activityLogList) {
		this.activityLogList = activityLogList;
	}

	public long getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(long visitCount) {
		this.visitCount = visitCount;
	}



	public Site getSite()
	{
		return site;
	}

	public void setSite(Site site)
	{
		this.site = site;
	}

	public long getParticipateCount() {
		return participateCount;
	}

	public void setParticipateCount(long participateCount) {
		this.participateCount = participateCount;
	}

	public long getParticipateSuccessCount() {
		return participateSuccessCount;
	}

	public void setParticipateSuccessCount(long participateSuccessCount) {
		this.participateSuccessCount = participateSuccessCount;
	}

	public long getParticipateFailCount() {
		return participateFailCount;
	}

	public void setParticipateFailCount(long participateFailCount) {
		this.participateFailCount = participateFailCount;
	}

	public List<Source> getSource() {
		return source;
	}

	public void setSource(List<Source> source) {
		this.source = source;
	}

	public List<List<Object>> getSourceList() {
		return sourceList;
	}

	public void setSourceList(List<List<Object>> sourceList) {
		this.sourceList = sourceList;
	}

	public List<List<Object>> getTerminalList() {
		return terminalList;
	}

	public void setTerminalList(List<List<Object>> terminalList) {
		this.terminalList = terminalList;
	}

	public List<VisitLog> getVistLogList()
	{
		return vistLogList;
	}

	public void setVistLogList(List<VisitLog> vistLogList)
	{
		this.vistLogList = vistLogList;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public long getUserCount() {
		return userCount;
	}

	public void setUserCount(long userCount) {
		this.userCount = userCount;
	}

	public long getUnKnownCount() {
		return unKnownCount;
	}

	public void setUnKnownCount(long unKnownCount) {
		this.unKnownCount = unKnownCount;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUnknowncreatetime() {
		return unknowncreatetime;
	}

	public void setUnknowncreatetime(Date unknowncreatetime) {
		this.unknowncreatetime = unknowncreatetime;
	}

	public long getCountSum() {
		return countSum;
	}

	public void setCountSum(long countSum) {
		this.countSum = countSum;
	}

	public List<Terminal> getTerminal() {
		return terminal;
	}

	public void setTerminal(List<Terminal> terminal) {
		this.terminal = terminal;
	}

}
