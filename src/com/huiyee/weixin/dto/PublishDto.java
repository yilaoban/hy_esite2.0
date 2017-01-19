package com.huiyee.weixin.dto;

import java.util.ArrayList;
import java.util.List;

import com.huiyee.esite.model.Page;
import com.huiyee.weixin.model.WxMp;
import com.huiyee.weixin.model.WxPageShow;

public class PublishDto {

	private List<Page> pageList = new ArrayList<Page>();
	private List<WxPageShow> wxPageShowlist;
	private WxMp wxMp;
	private WxPageShow wxPageShow;
	private List<Long> ppList;
	private List<Page> editPlist;

	public List<Page> getEditPlist() {
		return editPlist;
	}

	public void setEditPlist(List<Page> editPlist) {
		this.editPlist = editPlist;
	}

	public List<Long> getPpList() {
		return ppList;
	}

	public void setPpList(List<Long> ppList) {
		this.ppList = ppList;
	}

	public WxMp getWxMp() {
		return wxMp;
	}

	public void setWxMp(WxMp wxMp) {
		this.wxMp = wxMp;
	}

	public List<WxPageShow> getWxPageShowlist() {
		return wxPageShowlist;
	}

	public void setWxPageShowlist(List<WxPageShow> wxPageShowlist) {
		this.wxPageShowlist = wxPageShowlist;
	}

	public List<Page> getPageList() {
		return pageList;
	}

	public void setPageList(List<Page> pageList) {
		this.pageList = pageList;
	}

	public WxPageShow getWxPageShow() {
		return wxPageShow;
	}

	public void setWxPageShow(WxPageShow wxPageShow) {
		this.wxPageShow = wxPageShow;
	}

}
