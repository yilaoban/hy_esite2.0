package com.huiyee.esite.action;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.CustomVisitDto;
import com.huiyee.esite.dto.DanymicRecordDto;
import com.huiyee.esite.dto.DanymicUserSiftDto;
import com.huiyee.esite.dto.VisitReportDetailDto;
import com.huiyee.esite.dto.VisitUserDto;
import com.huiyee.esite.model.Account;

public class CustomVisitReportAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2961546756365126378L;
	
	private CustomVisitDto dto;
	private long sgid;
	private int pageId=1;
	private String wbuid;
    private long siteid;
    private VisitUserDto visitdto;
    private VisitReportDetailDto detaildto;
    private DanymicUserSiftDto siftDto;
    private DanymicRecordDto dydto;
    private String nickname;
    

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

	public CustomVisitDto getDto() {
		return dto;
	}

	public void setDto(CustomVisitDto dto) {
		this.dto = dto;
	}

	public long getSgid() {
		return sgid;
	}

	public void setSgid(long sgid) {
		this.sgid = sgid;
	}
	
	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public VisitUserDto getVisitdto() {
		return visitdto;
	}

	public void setVisitdto(VisitUserDto visitdto) {
		this.visitdto = visitdto;
	}

	public String execute() throws Exception{
		Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid=account.getOwner().getId();
		dto=(CustomVisitDto) pageCompose.composeSearchVisitReport(ownerid,sgid, visitdto, pageId);
		return SUCCESS;
	}
	public String getCustomVisitReportListByNickName() throws Exception{
        Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        if(nickname!=null&&!"".equals(nickname)){
            dto=(CustomVisitDto) pageCompose.findVisitRecordByNickname(sgid, account, nickname, pageId);
        }
        return SUCCESS;
    }
	public String getVisitLogsUnkown() throws Exception{
        Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        dto=(CustomVisitDto) pageCompose.findVisitLogUnkownList(sgid,pageId,account);
        return SUCCESS;
    }
	

	public DanymicUserSiftDto getSiftDto() {
		return siftDto;
	}

	public void setSiftDto(DanymicUserSiftDto siftDto) {
		this.siftDto = siftDto;
	}

	public DanymicRecordDto getDydto() {
		return dydto;
	}

	public void setDydto(DanymicRecordDto dydto) {
		this.dydto = dydto;
	}

	public VisitReportDetailDto getDetaildto() {
		return detaildto;
	}

	public void setDetaildto(VisitReportDetailDto detaildto) {
		this.detaildto = detaildto;
	}
	
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
} 
