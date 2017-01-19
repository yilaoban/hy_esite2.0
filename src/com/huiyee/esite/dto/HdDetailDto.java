package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.HD103Model;
import com.huiyee.esite.model.HD106Model;
import com.huiyee.esite.model.HD111Model;
import com.huiyee.esite.model.HD113Model;
import com.huiyee.esite.model.HD117Model;
import com.huiyee.esite.model.HD120Model;
import com.huiyee.esite.model.HD121Model;
import com.huiyee.esite.model.HD131Model;
import com.huiyee.esite.model.HD133Model;
import com.huiyee.esite.model.HD135Model;
import com.huiyee.esite.model.Hd103QueryObject;
import com.huiyee.esite.model.Hd106QueryObject;
import com.huiyee.esite.model.Hd111QueryObject;
import com.huiyee.esite.model.Hd113QueryObject;
import com.huiyee.esite.model.Hd117QueryObject;
import com.huiyee.esite.model.Hd120QueryObject;
import com.huiyee.esite.model.Hd131QueryObject;
import com.huiyee.esite.model.Hd133QueryObject;
import com.huiyee.esite.model.Hd135QueryObject;
import com.huiyee.esite.model.QueryObject;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;

public class HdDetailDto implements IDto{
	
	private Site site;
	private Pager pager;
	private List<HD103Model> hd103Model;//赞
	private List<HD106Model> hd106Model;//上传图片
	private List<HD111Model> hd111Model;//授权
	private List<HD113Model> hd113Model;//原创分享
	private List<HD117Model> hd117Model;//转发分享
	private List<HD121Model> hd121Model;//邮件预定
	private List<HD120Model> hd120Model;
	private List<HD131Model> hd131Model;//邮件预定
	private List<HD133Model> hd133Model;//邮件预定
	private List<HD135Model> hd135Model;//邮件预定
	private Hd113QueryObject hd113Query;
	private Hd103QueryObject hd103Query;
	private Hd106QueryObject hd106Query;
	private Hd111QueryObject hd111Query;
	private Hd117QueryObject hd117Query;
	private Hd120QueryObject hd120Query;
	private Hd131QueryObject hd131Query;
	private Hd133QueryObject hd133Query;
	private Hd135QueryObject hd135Query;
	
	public List<HD111Model> getHd111Model() {
		return hd111Model;
	}

	public void setHd111Model(List<HD111Model> hd111Model) {
		this.hd111Model = hd111Model;
	}

	public List<HD113Model> getHd113Model() {
		return hd113Model;
	}

	public void setHd113Model(List<HD113Model> hd113Model) {
		this.hd113Model = hd113Model;
	}

	public List<HD103Model> getHd103Model() {
		return hd103Model;
	}

	public void setHd103Model(List<HD103Model> hd103Model) {
		this.hd103Model = hd103Model;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}


	public Site getSite()
	{
		return site;
	}

	public void setSite(Site site)
	{
		this.site = site;
	}

	public List<HD106Model> getHd106Model() {
		return hd106Model;
	}

	public void setHd106Model(List<HD106Model> hd106Model) {
		this.hd106Model = hd106Model;
	}

	public Hd113QueryObject getHd113Query() {
		return hd113Query;
	}

	public void setHd113Query(Hd113QueryObject hd113Query) {
		this.hd113Query = hd113Query;
	}
	
	public QueryObject getQueryObject(long hdid) {
		if (hdid == 103L) {
			return hd103Query;
		} else if (hdid == 106L) {
			return hd106Query;
		} else if (hdid == 111L) {
			return hd111Query;
		} else if (hdid == 113L) {
			return hd113Query;
		} else if(hdid == 117L){
			return hd117Query;
		} else if(hdid == 120L){
			return hd120Query;
		}else {
			return null;
		}
	}

	public Hd103QueryObject getHd103Query() {
		return hd103Query;
	}

	public void setHd103Query(Hd103QueryObject hd103Query) {
		this.hd103Query = hd103Query;
	}

	public Hd106QueryObject getHd106Query() {
		return hd106Query;
	}

	public void setHd106Query(Hd106QueryObject hd106Query) {
		this.hd106Query = hd106Query;
	}

	public Hd111QueryObject getHd111Query() {
		return hd111Query;
	}

	public void setHd111Query(Hd111QueryObject hd111Query) {
		this.hd111Query = hd111Query;
	}

	public List<HD117Model> getHd117Model() {
		return hd117Model;
	}

	public void setHd117Model(List<HD117Model> hd117Model) {
		this.hd117Model = hd117Model;
	}

	public Hd117QueryObject getHd117Query() {
		return hd117Query;
	}

	public void setHd117Query(Hd117QueryObject hd117Query) {
		this.hd117Query = hd117Query;
	}

    public List<HD121Model> getHd121Model() {
        return hd121Model;
    }

    public void setHd121Model(List<HD121Model> hd121Model) {
        this.hd121Model = hd121Model;
    }

	public List<HD120Model> getHd120Model() {
		return hd120Model;
	}

	public void setHd120Model(List<HD120Model> hd120Model) {
		this.hd120Model = hd120Model;
	}

	public Hd120QueryObject getHd120Query() {
		return hd120Query;
	}

	public void setHd120Query(Hd120QueryObject hd120Query) {
		this.hd120Query = hd120Query;
	}

	public List<HD131Model> getHd131Model()
	{
		return hd131Model;
	}

	public void setHd131Model(List<HD131Model> hd131Model)
	{
		this.hd131Model = hd131Model;
	}

	public List<HD133Model> getHd133Model()
	{
		return hd133Model;
	}

	public void setHd133Model(List<HD133Model> hd133Model)
	{
		this.hd133Model = hd133Model;
	}

	public List<HD135Model> getHd135Model()
	{
		return hd135Model;
	}

	public void setHd135Model(List<HD135Model> hd135Model)
	{
		this.hd135Model = hd135Model;
	}

	public Hd131QueryObject getHd131Query()
	{
		return hd131Query;
	}

	public void setHd131Query(Hd131QueryObject hd131Query)
	{
		this.hd131Query = hd131Query;
	}

	public Hd133QueryObject getHd133Query()
	{
		return hd133Query;
	}

	public void setHd133Query(Hd133QueryObject hd133Query)
	{
		this.hd133Query = hd133Query;
	}

	public Hd135QueryObject getHd135Query()
	{
		return hd135Query;
	}

	public void setHd135Query(Hd135QueryObject hd135Query)
	{
		this.hd135Query = hd135Query;
	}

    
}
