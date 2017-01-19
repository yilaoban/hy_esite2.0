package com.huiyee.esite.model;

import java.io.Serializable;
public class FansAnalyseRecord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3394002847040273890L;
	
	private long sitegroupid;
	private long wbuid;
	private String nickname;
	private String type;
	private String followed;
	private int level;
    public long getSitegroupid() {
        return sitegroupid;
    }
    public void setSitegroupid(long sitegroupid) {
        this.sitegroupid = sitegroupid;
    }
    public long getWbuid() {
        return wbuid;
    }
    public void setWbuid(long wbuid) {
        this.wbuid = wbuid;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getFollowed() {
        return followed;
    }
    public void setFollowed(String followed) {
        this.followed = followed;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

}
