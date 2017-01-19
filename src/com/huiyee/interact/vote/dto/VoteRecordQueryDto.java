package com.huiyee.interact.vote.dto;
public class VoteRecordQueryDto implements IDto {

	//开始时间
	private String startTime;
	//结束时间
	private String endTime;
	
	private String nickname;
	
    public String getStartTime()
	{
		return startTime;
	}
	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}
	public String getEndTime()
	{
		return endTime;
	}
	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}
	public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
