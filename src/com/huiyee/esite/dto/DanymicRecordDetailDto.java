package com.huiyee.esite.dto;

import com.huiyee.esite.model.UserInfo;
import java.util.List;
public class DanymicRecordDetailDto implements IDto {
    private List<DanymicUserDetail> list;
    private int total;
    private UserInfo user;

    public List<DanymicUserDetail> getList() {
        return list;
    }

    public void setList(List<DanymicUserDetail> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

}
