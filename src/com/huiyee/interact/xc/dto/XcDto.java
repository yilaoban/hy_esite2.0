package com.huiyee.interact.xc.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huiyee.interact.xc.model.LotteryConfig;
import com.huiyee.interact.xc.model.XcRecord;

public class XcDto {

	private LotteryConfig lc;
	private Map<String, XcRecord> map = new HashMap<String, XcRecord>();
	private List<String> keys = new ArrayList<String>();
	private List<Long> uids = new ArrayList<Long>();

	public LotteryConfig getLc() {
		return lc;
	}

	public void setLc(LotteryConfig lc) {
		this.lc = lc;
	}

	public Map<String, XcRecord> getMap() {
		return map;
	}

	public void setMap(Map<String, XcRecord> map) {
		this.map = map;
	}

	public List<String> getKeys() {
		return keys;
	}

	public void setKeys(List<String> keys) {
		this.keys = keys;
	}

	public List<Long> getUids() {
		return uids;
	}

	public void setUids(List<Long> uids) {
		this.uids = uids;
	}

}
