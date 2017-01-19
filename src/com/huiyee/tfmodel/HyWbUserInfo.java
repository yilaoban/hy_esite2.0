package com.huiyee.tfmodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HyWbUserInfo extends HyWbUser {
	
	private static final long serialVersionUID = 1L;
	private int wbRank;
	private int userRank;
	private String birthDay;
	private String education;
	private String daren;
	private String experience;
	private List<String> tag;
	
	public HyWbUserInfo() {
		
	}
	
	public HyWbUserInfo(String jsonstr) {
		JSONObject json = JSONObject.fromObject(jsonstr);
		this.setId(HyJsonUtils.pasString(json,"id"));
		this.setScreenName(HyJsonUtils.pasString(json,"screenName"));
		this.setName(HyJsonUtils.pasString(json,"name"));
		this.setProvince(HyJsonUtils.pasInt(json,"province"));
		this.setCity(HyJsonUtils.pasInt(json,"city"));
		this.setLocation(HyJsonUtils.pasString(json,"location"));
		this.setDescription(HyJsonUtils.pasString(json,"description"));
		this.setUrl(HyJsonUtils.pasString(json,"url"));
		this.setProfileImageUrl(HyJsonUtils.pasString(json,"profileImageUrl"));
		this.setUserDomain(HyJsonUtils.pasString(json,"userDomain"));
		this.setGender(HyJsonUtils.pasString(json,"gender"));
		this.setFollowersCount(HyJsonUtils.pasInt(json,"followersCount"));
		this.setFriendsCount(HyJsonUtils.pasInt(json,"friendsCount"));
		this.setStatusesCount(HyJsonUtils.pasInt(json,"statusesCount"));
		this.setFavouritesCount(HyJsonUtils.pasInt(json,"favouritesCount"));
		JSONObject createdAtObj = HyJsonUtils.pasJsonobj(json,"createdAt");
		if (!createdAtObj.isEmpty()) {
			this.setCreatedAt(new Date(createdAtObj.getLong("time")));
		}
		this.setFollowing(HyJsonUtils.pasBoolean(json,"following"));
		this.setVerified(HyJsonUtils.pasBoolean(json,"verified"));
		this.setVerifiedType(HyJsonUtils.pasInt(json,"verifiedType"));
		this.setAllowAllActMsg(HyJsonUtils.pasBoolean(json,"allowAllActMsg"));
		this.setAllowAllComment(HyJsonUtils.pasBoolean(json,"allowAllComment"));
		this.setFollowMe(HyJsonUtils.pasBoolean(json,"followMe"));
		this.setAvatarLarge(HyJsonUtils.pasString(json,"avatarLarge"));
		this.setOnlineStatus(HyJsonUtils.pasInt(json,"onlineStatus"));
		String weiboStr = HyJsonUtils.pasString(json,"weibo");
		if (weiboStr != null && !"null".equals(weiboStr)) {
			this.setWeibo(new HyWbSrc(weiboStr));
		}
		this.setBiFollowersCount(HyJsonUtils.pasInt(json,"biFollowersCount"));
		this.setRemark(HyJsonUtils.pasString(json,"remark"));
		this.setLang(HyJsonUtils.pasString(json,"lang"));
		this.setVerifiedReason(HyJsonUtils.pasString(json,"verifiedReason"));
		this.setWeihao(HyJsonUtils.pasString(json,"weihao"));
		this.setStatusId(HyJsonUtils.pasString(json,"statusId"));
		this.wbRank = HyJsonUtils.pasInt(json,"wbRank");
		this.userRank = HyJsonUtils.pasInt(json,"userRank");
		this.birthDay = HyJsonUtils.pasString(json,"birthDay");
		this.education = HyJsonUtils.pasString(json,"education");
		this.daren = HyJsonUtils.pasString(json,"daren");
		this.experience = HyJsonUtils.pasString(json,"experience");
		JSONArray tagArr =  HyJsonUtils.pasArray(json,"tag");
		if (tagArr!=null&&!tagArr.isEmpty()) {
			List<String> taglist = new ArrayList<String>();
			for (int i = 0; i < tagArr.size(); i++) {
				taglist.add(tagArr.getString(i));
			}
			this.tag = taglist;
		}
	}

	public int getWbRank()
	{
		return wbRank;
	}
	public void setWbRank(int wbRank)
	{
		this.wbRank = wbRank;
	}
	public int getUserRank()
	{
		return userRank;
	}
	public void setUserRank(int userRank)
	{
		this.userRank = userRank;
	}
	public String getBirthDay()
	{
		return birthDay;
	}
	public void setBirthDay(String birthDay)
	{
		this.birthDay = birthDay;
	}
	public String getEducation()
	{
		return education;
	}
	public void setEducation(String education)
	{
		this.education = education;
	}
	public String getDaren()
	{
		return daren;
	}
	public void setDaren(String daren)
	{
		this.daren = daren;
	}
	public List<String> getTag()
	{
		return tag;
	}
	public void setTag(List<String> tag)
	{
		this.tag = tag;
	}

	public String getExperience()
	{
		return experience;
	}

	public void setExperience(String experience)
	{
		this.experience = experience;
	}
}
