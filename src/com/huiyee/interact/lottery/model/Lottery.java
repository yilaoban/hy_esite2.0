package com.huiyee.interact.lottery.model;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.model.SuperHdModel;

public class Lottery extends SuperHdModel implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6607529554829292004L;
	private String name;
	private long owner;
	private long wbuid;
	private String type;
	private int zjl;// ÷–Ω±¬  ÕÚ∑÷÷Æº∏
	private int drawnum;
	private int winnum;
	private String detail;
	private String endimg;
	private String userName ="Y";// –’√˚ «∑Òœ‘ æ
	private String userNameValue;
	private String userPhone ="Y";
	private String userPhoneValue;
	private String userEmail ="Y";
	private String userEmailValue;
	private String userLocation ="Y";
	private String userLocationValue;
	private String ruletype="D";
	private String usertype ="A";
	private String assignuser;
	private int usertotal;
	private int userdaynum;
	private int userzjtotal;
	private List<LotteryPrize> lotteryPrizes;
	private long gzeid;
	
	private int idx;
	private String img;//≤ µ∞≥ÈΩ±Õº∆¨
	private long pageid;//≤ µ∞≥ÈΩ±ƒ£∞Â“≥id
	
	public String getImg()
	{
		return img;
	}



	
	public void setImg(String img)
	{
		this.img = img;
	}



	
	public long getPageid()
	{
		return pageid;
	}



	
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}



	public int getIdx()
	{
		return idx;
	}


	
	public void setIdx(int idx)
	{
		this.idx = idx;
	}


	public int getUserzjtotal()
	{
		return userzjtotal;
	}

	
	public void setUserzjtotal(int userzjtotal)
	{
		this.userzjtotal = userzjtotal;
	}

	public List<LotteryPrize> getLotteryPrizes()
	{
		return lotteryPrizes;
	}
	
	public void setLotteryPrizes(List<LotteryPrize> lotteryPrizes)
	{
		this.lotteryPrizes = lotteryPrizes;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public long getOwner()
	{
		return owner;
	}

	public void setOwner(long owner)
	{
		this.owner = owner;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		if("Z".equals(type)){
			super.setFeatureid(IInteractConstants.INTERACT_ZLOTTERY);
		}else if("G".equals(type)){
			super.setFeatureid(IInteractConstants.INTERACT_GLOTTERY);
		}else if("L".equals(type)){
			super.setFeatureid(IInteractConstants.INTERACT_LLOTTERY);
		}
		this.type = type;
	}

	public int getZjl()
	{
		return zjl;
	}

	public void setZjl(int zjl)
	{
		this.zjl = zjl;
	}

	public String getDetail()
	{
		return detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

	public String getEndimg()
	{
		return endimg;
	}

	public void setEndimg(String endimg)
	{
		this.endimg = endimg;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserNameValue()
	{
		return userNameValue;
	}

	public void setUserNameValue(String userNameValue)
	{
		this.userNameValue = userNameValue;
	}

	public String getUserPhone()
	{
		return userPhone;
	}

	public void setUserPhone(String userPhone)
	{
		this.userPhone = userPhone;
	}

	public String getUserPhoneValue()
	{
		return userPhoneValue;
	}

	public void setUserPhoneValue(String userPhoneValue)
	{
		this.userPhoneValue = userPhoneValue;
	}

	public String getUserEmail()
	{
		return userEmail;
	}

	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}

	public String getUserEmailValue()
	{
		return userEmailValue;
	}

	public void setUserEmailValue(String userEmailValue)
	{
		this.userEmailValue = userEmailValue;
	}

	public String getUserLocation()
	{
		return userLocation;
	}

	public void setUserLocation(String userLocation)
	{
		this.userLocation = userLocation;
	}

	public String getUserLocationValue()
	{
		return userLocationValue;
	}

	public void setUserLocationValue(String userLocationValue)
	{
		this.userLocationValue = userLocationValue;
	}

	public String getRuletype()
	{
		return ruletype;
	}

	public void setRuletype(String ruletype)
	{
		this.ruletype = ruletype;
	}

	public String getUsertype()
	{
		return usertype;
	}

	public void setUsertype(String usertype)
	{
		this.usertype = usertype;
	}

	public String getAssignuser()
	{
		return assignuser;
	}

	public void setAssignuser(String assignuser)
	{
		this.assignuser = assignuser;
	}

	public int getUsertotal()
	{
		return usertotal;
	}

	public void setUsertotal(int usertotal)
	{
		this.usertotal = usertotal;
	}

	public int getUserdaynum()
	{
		return userdaynum;
	}

	public void setUserdaynum(int userdaynum)
	{
		this.userdaynum = userdaynum;
	}


	
	public long getGzeid()
	{
		return gzeid;
	}


	
	public void setGzeid(long gzeid)
	{
		this.gzeid = gzeid;
	}




	
	public int getDrawnum()
	{
		return drawnum;
	}




	
	public void setDrawnum(int drawnum)
	{
		this.drawnum = drawnum;
	}




	
	public int getWinnum()
	{
		return winnum;
	}




	
	public void setWinnum(int winnum)
	{
		this.winnum = winnum;
	}
}
