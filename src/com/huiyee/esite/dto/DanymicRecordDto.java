package com.huiyee.esite.dto;

import java.util.List;
public class DanymicRecordDto implements IDto{
	private Pager pager;
	private List<DanymicUserRecord> list;
	private List<DanymicAton> alist;
	private String groupname;
    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public List<DanymicUserRecord> getList() {
        return list;
    }

    public void setList(List<DanymicUserRecord> list) {
        this.list = list;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public List<DanymicAton> getAlist() {
        return alist;
    }

    public void setAlist(List<DanymicAton> alist) {
        this.alist = alist;
    }

}
