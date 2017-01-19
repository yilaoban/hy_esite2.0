package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class SinaUserApp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5517513722205354692L;
	private long wbuid;
	private String nickname;
	private Date createtime;
	private String ip;
	private String terminal;

	public long getWbuid() {
		return wbuid;
	}

	public void setWbuid(long wbuid) {
		this.wbuid = wbuid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

}
