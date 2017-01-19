package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Redirect;
import com.huiyee.interact.vote.model.InteractVote;

public class Feature123Dto implements IDto , Serializable{

	private static final long serialVersionUID = -2470071164121283719L;
	
	private List<InteractVote> interactVote;
	private long voteid;
	private long fid;

	private long relationid;
	private String type;
	private long pageid;
	
	private int start;
	private int end;
	
	private Redirect redirect;
	private String url;
	private String urlShow;
	private String urlPre;
	private List<Page> pageList;
	private long toPageid;
	
	public Redirect getRedirect()
	{
		return redirect;
	}

	
	public void setRedirect(Redirect redirect)
	{
		this.redirect = redirect;
	}

	
	public String getUrl()
	{
		return url;
	}

	
	public void setUrl(String url)
	{
		this.url = url;
	}

	
	public String getUrlShow()
	{
		return urlShow;
	}

	
	public void setUrlShow(String urlShow)
	{
		this.urlShow = urlShow;
	}

	
	public String getUrlPre()
	{
		return urlPre;
	}

	
	public void setUrlPre(String urlPre)
	{
		this.urlPre = urlPre;
	}

	
	public List<Page> getPageList()
	{
		return pageList;
	}

	
	public void setPageList(List<Page> pageList)
	{
		this.pageList = pageList;
	}

	
	public long getToPageid()
	{
		return toPageid;
	}

	
	public void setToPageid(long toPageid)
	{
		this.toPageid = toPageid;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public long getRelationid()
	{
		return relationid;
	}

	public void setRelationid(long relationid)
	{
		this.relationid = relationid;
	}

	public List<InteractVote> getInteractVote() {
		return interactVote;
	}

	public void setInteractVote(List<InteractVote> interactVote) {
		this.interactVote = interactVote;
	}

	public long getVoteid() {
		return voteid;
	}

	public void setVoteid(long voteid) {
		this.voteid = voteid;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}
	
	
}
