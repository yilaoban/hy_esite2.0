package com.huiyee.esite.dto.showdto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.RenQiRecord;
import com.huiyee.interact.renqi.model.RenQi;

public class Show144Dto implements IDto, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long source; //发起者
	private long visit;	//参与者
	private RenQi rq;
	private List<RenQiRecord> record;//记录
	private int isSend;//参与者是否发起过
	private int isJoin;//参与者是否参与过
	private int total;//总分

	public long getSource() {
		return source;
	}

	public void setSource(long source) {
		this.source = source;
	}

	public long getVisit() {
		return visit;
	}

	public void setVisit(long visit) {
		this.visit = visit;
	}

	public List<RenQiRecord> getRecord() {
		return record;
	}

	public void setRecord(List<RenQiRecord> record) {
		this.record = record;
	}

	public int getIsSend() {
		return isSend;
	}

	public void setIsSend(int isSend) {
		this.isSend = isSend;
	}

	public int getIsJoin() {
		return isJoin;
	}

	public void setIsJoin(int isJoin) {
		this.isJoin = isJoin;
	}

	public RenQi getRq() {
		return rq;
	}

	public void setRq(RenQi rq) {
		this.rq = rq;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	
}
