package com.huiyee.esite.dto;
public class DanymicUserDetail {
	private String createtime;//互动时间
	private long action;//活动类型
    private String target;//互动对象
    private long entityid;
    private String actiontype;
    private String terminal;
    public long getAction() {
        return action;
    }
    public void setAction(long action) {
        this.action = action;
    }
    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }
    public String getCreatetime() {
        return createtime;
    }
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    public long getEntityid() {
        return entityid;
    }
    public void setEntityid(long entityid) {
        this.entityid = entityid;
    }
    public String getActiontype() {
        return actiontype;
    }
    public void setActiontype(String actiontype) {
        this.actiontype = actiontype;
    }
    public String getTerminal() {
        return terminal;
    }
    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
}
