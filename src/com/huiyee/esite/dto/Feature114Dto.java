package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.model.SinaShare;

public class Feature114Dto implements IDto, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1569717302961926705L;
    private List<SinaShare> checklist;
    private List<SinaChecklistRecord> checkListRecord;
    private long shareid;
    private long fid;

    public List<SinaShare> getChecklist()
    {
	return checklist;
    }

    public void setChecklist(List<SinaShare> checklist)
    {
	this.checklist = checklist;
    }

    public List<SinaChecklistRecord> getCheckListRecord()
    {
	return checkListRecord;
    }

    public void setCheckListRecord(List<SinaChecklistRecord> checkListRecord)
    {
	this.checkListRecord = checkListRecord;
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
