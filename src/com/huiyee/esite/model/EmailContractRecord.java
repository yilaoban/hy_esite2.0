package com.huiyee.esite.model;

import java.io.Serializable;
public class EmailContractRecord implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 8672084167045373033L;
    private long id;
	private String email;
	private long contractid;
	private long pageid;
	private String ip;
	private String terminal;
	private String source;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public long getContractid() {
        return contractid;
    }
    public void setContractid(long contractid) {
        this.contractid = contractid;
    }
    public long getPageid() {
        return pageid;
    }
    public void setPageid(long pageid) {
        this.pageid = pageid;
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
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    
}
