package com.huiyee.tfmodel.param;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TfTagSource implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
    private long wbuid;
	private String nickName;
	private String tag;
	private String description;
	private String verifiedReason;
	private String education;
	private String experience;
	private Date updatetime;

	public Map<String, String> getSourceMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("nicknametotal", nickName);
		map.put("tagtotal", tag);
		map.put("descriptiontotal", description);
		map.put("verifiedtotal", verifiedReason);
		map.put("educationtotal", education);
		map.put("experiencetotal", experience);

		return map;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVerifiedReason() {
		return verifiedReason;
	}

	public void setVerifiedReason(String verifiedReason) {
		this.verifiedReason = verifiedReason;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public Date getUpdatetime()
	{
		return updatetime;
	}

	public void setUpdatetime(Date updatetime)
	{
		this.updatetime = updatetime;
	}

}
