package com.huiyee.esite.dto.showdto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.SinaCheckList;
import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.model.SinaShare;

public class Show114Dto implements IDto, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5018812924307783397L;
    private List<SinaShare> checklist;
    private List<SinaChecklistRecord> checkListRecord;
    private long shareid;
    private long fid;

    public List<SinaChecklistRecord> getCheckListRecord()
    {
	return checkListRecord;
    }

    public void setCheckListRecord(List<SinaChecklistRecord> checkListRecord)
    {
	this.checkListRecord = checkListRecord;
    }

    public List<SinaShare> getChecklist()
    {
	return checklist;
    }

    public void setChecklist(List<SinaShare> checklist)
    {
	this.checklist = checklist;
    }

    public long getShareid()
    {
	return shareid;
    }

    public void setShareid(long shareid)
    {
	this.shareid = shareid;
    }

    public long getFid()
    {
	return fid;
    }

    public void setFid(long fid)
    {
	this.fid = fid;
    }
}
