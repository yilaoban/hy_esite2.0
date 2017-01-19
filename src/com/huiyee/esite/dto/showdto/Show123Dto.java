package com.huiyee.esite.dto.showdto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.interact.vote.model.InteractVote;
import com.huiyee.interact.vote.model.VoteOption;

public class Show123Dto implements IDto, Serializable{
	private static final long serialVersionUID = 8311376812737525210L;
	
	private long fid;
	private InteractVote interactVote;
	private List<VoteOption> voteOption;
	private int start;
	private int end;
	
	public long getFid() {
		return fid;
	}
	public void setFid(long fid) {
		this.fid = fid;
	}
	public InteractVote getInteractVote() {
		return interactVote;
	}
	public void setInteractVote(InteractVote interactVote) {
		this.interactVote = interactVote;
	}
	public List<VoteOption> getVoteOption() {
		return voteOption;
	}
	public void setVoteOption(List<VoteOption> voteOption) {
		this.voteOption = voteOption;
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
	
}
