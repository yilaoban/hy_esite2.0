package com.huiyee.interact.vote.dto;
import java.util.List;

import com.huiyee.interact.vote.model.Pager;
import com.huiyee.interact.vote.model.VoteOption;
public class VoteOptionDto implements IDto {
    private List<List<Object>>  votedata;
    private List<VoteOption> list;
    private int total;
    private Pager pager;
    public List<List<Object>> getVotedata() {
        return votedata;
    }

    public void setVotedata(List<List<Object>> votedata) {
        this.votedata = votedata;
    }

    public List<VoteOption> getList() {
        return list;
    }

    public void setList(List<VoteOption> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

}
