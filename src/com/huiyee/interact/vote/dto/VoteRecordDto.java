package com.huiyee.interact.vote.dto;

import java.util.List;
import com.huiyee.interact.vote.model.Pager;
import com.huiyee.interact.vote.model.VoteRecord;

public class VoteRecordDto implements IDto {

	private List<VoteRecord> list;
	private Pager pager;

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

    public List<VoteRecord> getList() {
        return list;
    }

    public void setList(List<VoteRecord> list) {
        this.list = list;
    }
}
