package com.huiyee.esite.action;

import com.google.gson.Gson;
import com.huiyee.esite.dto.DanymicRecordDetailDto;
import com.huiyee.esite.dto.DanymicRecordDto;
import com.huiyee.esite.dto.DanymicUserDetail;
import com.huiyee.esite.dto.DanymicUserSiftDto;
import com.huiyee.esite.model.Account;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class DanymicUserReportAction extends AbstractAction {

    /**
     * 
     */
    private static final long serialVersionUID = -2961546756365126378L;
    private DanymicRecordDto dto;
    private long sgid;
    private DanymicUserSiftDto siftDto;
    private int pageId = 1;
    private String nickname;
    private DanymicRecordDetailDto detailDto;
    private String wbuid;
    private long siteid;

    public String getDanymicRecordList() throws Exception {
        Account account=(Account)ServletActionContext.getRequest().getSession().getAttribute("account");
        dto = (DanymicRecordDto) pageCompose.findDanymicUserRecord(sgid,account, siftDto, pageId);
        return SUCCESS;
    }

    public String getDanymicRecordListNickName() throws Exception {
        Account account=(Account)ServletActionContext.getRequest().getSession().getAttribute("account");
        if(nickname!=null&&!"".equals(nickname)){
            dto = (DanymicRecordDto) pageCompose.findDanymicUserRecordByNickname(sgid,account, nickname, pageId);
        }
        return SUCCESS;
    }

    public String getDanymicRecordDetailList() throws Exception {
        detailDto = (DanymicRecordDetailDto) pageCompose.findDanymicRecordDetailList(sgid, siteid, wbuid, siftDto,
                pageId);
        return SUCCESS;
    }

    public String getDanymicRecordDetailData() throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setContentType("text/html; charset=utf-8");
        List<DanymicUserDetail> list = pageCompose.findDanymicRecordDetailListData(sgid, wbuid, siftDto, pageId);
        Gson gson = new Gson();
        String gsonComments = gson.toJson(list);
        PrintWriter pw = response.getWriter();
        pw.print(gsonComments);
        pw.flush();
        pw.close();
        return null;
    }

    public int getReportPoint() {
        return 3;
    }

    public long getSgid() {
        return sgid;
    }

    public void setSgid(long sgid) {
        this.sgid = sgid;
    }

    public DanymicUserSiftDto getSiftDto() {
        return siftDto;
    }

    public void setSiftDto(DanymicUserSiftDto siftDto) {
        this.siftDto = siftDto;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getWbuid() {
        return wbuid;
    }

    public void setWbuid(String wbuid) {
        this.wbuid = wbuid;
    }

    public long getSiteid() {
        return siteid;
    }

    public void setSiteid(long siteid) {
        this.siteid = siteid;
    }

    public DanymicRecordDto getDto() {
        return dto;
    }

    public DanymicRecordDetailDto getDetailDto() {
        return detailDto;
    }

}
