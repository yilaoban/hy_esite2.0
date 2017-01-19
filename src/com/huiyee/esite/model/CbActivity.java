
package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

import com.huiyee.esite.util.DateUtil;
import com.huiyee.weixin.model.WxPageShow;

public class CbActivity implements Serializable {

	private static final long serialVersionUID = -5173381196103180850L;
	private long id;
	private long owner;
	private String title;
	private String img;
	private String content;
	private Date updatetime;
	private Date createtime;
	private String del_tag;
	private int status;
	private int type;
	private long enid;

	private int zhuanfa;
	private int zhuanfajf;
	private int click;
	private int clickjf;
	private int guanzhu;
	private int guanzhujf;
	private int guanzhudays;
	private int waibu;
	private int waibujf;
	private int hudong;
	private int hudongjf;
	private Date starttime;
	private Date endtime;
	private Date proday;
	private Date protime;

	private String starttimeStr;
	private String endtimeStr;
	private String m_title;
	private WxPageShow wps;
	private boolean active;
	private String a_status;
	private int shared_num;
	private int sucainum;
	private float zhuanfa_jf;
	private float click_jf;
	private float guanzhu_jf;
	private float waibu_jf;
	private float hudong_jf;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOwner() {
		return owner;
	}

	public void setOwner(long owner) {
		this.owner = owner;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getDel_tag() {
		return del_tag;
	}

	public void setDel_tag(String del_tag) {
		this.del_tag = del_tag;
	}

	public int getZhuanfa() {
		return zhuanfa;
	}

	public void setZhuanfa(int zhuanfa) {
		if (zhuanfa != 0) {
			setZhuanfa_jf((float) zhuanfa / 100);
		}
		this.zhuanfa = zhuanfa;
	}

	public int getClick() {
		return click;
	}

	public void setClick(int click) {
		if (click != 0) {
			setClick_jf((float) click / 100);
		}
		this.click = click;
	}

	public int getGuanzhu() {
		return guanzhu;
	}

	public void setGuanzhu(int guanzhu) {
		if (guanzhu != 0) {
			setGuanzhu_jf((float) guanzhu / 100);
		}
		this.guanzhu = guanzhu;
	}

	public int getWaibu() {
		return waibu;
	}

	public void setWaibu(int waibu) {
		if (waibu != 0) {
			setWaibu_jf((float) waibu / 100);
		}
		this.waibu = waibu;
	}

	public int getHudong() {
		return hudong;
	}

	public void setHudong(int hudong) {
		if (hudong != 0) {
			setHudong_jf((float) hudong / 100);
		}
		this.hudong = hudong;
	}

	public int getGuanzhudays() {
		return guanzhudays;
	}

	public void setGuanzhudays(int guanzhudays) {
		this.guanzhudays = guanzhudays;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		if (starttime != null) {
			starttimeStr = DateUtil.getDateTimeString(starttime);
		}
		this.starttime = starttime;
	}

	public void setEndtime(Date endtime) {
		if (endtime != null) {
			endtimeStr = DateUtil.getDateTimeString(endtime);
		}
		this.endtime = endtime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public Date getProday() {
		return proday;
	}

	public void setProday(Date proday) {
		this.proday = proday;
	}

	public Date getProtime() {
		return protime;
	}

	public void setProtime(Date protime) {
		this.protime = protime;
	}

	public String getStarttimeStr() {
		return starttimeStr;
	}

	public void setStarttimeStr(String starttimeStr) {
		starttime = DateUtil.getDateTime(starttimeStr);
		this.starttimeStr = starttimeStr;
	}

	public String getEndtimeStr() {
		return endtimeStr;
	}

	public void setEndtimeStr(String endtimeStr) {
		endtime = DateUtil.getDateTime(endtimeStr);
		this.endtimeStr = endtimeStr;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSucainum() {
		return sucainum;
	}

	public void setSucainum(int sucainum) {
		this.sucainum = sucainum;
	}

	public int getZhuanfajf() {
		return zhuanfajf;
	}

	public void setZhuanfajf(int zhuanfajf) {
		this.zhuanfajf = zhuanfajf;
	}

	public int getClickjf() {
		return clickjf;
	}

	public void setClickjf(int clickjf) {
		this.clickjf = clickjf;
	}

	public int getGuanzhujf() {
		return guanzhujf;
	}

	public void setGuanzhujf(int guanzhujf) {
		this.guanzhujf = guanzhujf;
	}

	public int getWaibujf() {
		return waibujf;
	}

	public void setWaibujf(int waibujf) {
		this.waibujf = waibujf;
	}

	public int getHudongjf() {
		return hudongjf;
	}

	public void setHudongjf(int hudongjf) {
		this.hudongjf = hudongjf;
	}

	public float getZhuanfa_jf() {
		return zhuanfa_jf;
	}

	public void setZhuanfa_jf(float zhuanfa_jf) {
		this.zhuanfa_jf = zhuanfa_jf;
	}

	public float getClick_jf() {
		return click_jf;
	}

	public void setClick_jf(float click_jf) {
		this.click_jf = click_jf;
	}

	public float getGuanzhu_jf() {
		return guanzhu_jf;
	}

	public void setGuanzhu_jf(float guanzhu_jf) {
		this.guanzhu_jf = guanzhu_jf;
	}

	public float getWaibu_jf() {
		return waibu_jf;
	}

	public void setWaibu_jf(float waibu_jf) {
		this.waibu_jf = waibu_jf;
	}

	public float getHudong_jf() {
		return hudong_jf;
	}

	public void setHudong_jf(float hudong_jf) {
		this.hudong_jf = hudong_jf;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getEnid() {
		return enid;
	}

	public void setEnid(long enid) {
		this.enid = enid;
	}

	public String getM_title() {
		return m_title;
	}

	public void setM_title(String m_title) {
		this.m_title = m_title;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getA_status() {
		return a_status;
	}

	public void setA_status(String a_status) {
		this.a_status = a_status;
	}

	public int getShared_num() {
		return shared_num;
	}

	public void setShared_num(int shared_num) {
		this.shared_num = shared_num;
	}

	public WxPageShow getWps() {
		return wps;
	}

	public void setWps(WxPageShow wps) {
		this.wps = wps;
	}

}
