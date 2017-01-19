package com.huiyee.esite.dto;

import com.huiyee.esite.model.UserUpload;
import com.huiyee.esite.model.UserUploadRecord;

import java.io.Serializable;
import java.util.List;

public class Feature108Dto implements IDto,Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long uploadid;
    private List<UserUpload> uploadlist;
    private List<Long> recordids;
    private long fid;
    private UserUpload upload;
    private List<UserUploadRecord> recordlist;
    private List<Integer> ididxs;
    public List<UserUploadRecord> getRecordlist() {
        return recordlist;
    }
    public void setRecordlist(List<UserUploadRecord> recordlist) {
        this.recordlist = recordlist;
    }
    public long getFid() {
        return fid;
    }
    public void setFid(long fid) {
        this.fid = fid;
    }
    public List<UserUpload> getUploadlist() {
        return uploadlist;
    }
    public void setUploadlist(List<UserUpload> uploadlist) {
        this.uploadlist = uploadlist;
    }
    public long getUploadid() {
        return uploadid;
    }
    public void setUploadid(long uploadid) {
        this.uploadid = uploadid;
    }
    public List<Long> getRecordids() {
        return recordids;
    }
    public void setRecordids(List<Long> recordids) {
        this.recordids = recordids;
    }
    public UserUpload getUpload() {
        return upload;
    }
    public void setUpload(UserUpload upload) {
        this.upload = upload;
    }
    public List<Integer> getIdidxs() {
        return ididxs;
    }
    public void setIdidxs(List<Integer> ididxs) {
        this.ididxs = ididxs;
    }
}
