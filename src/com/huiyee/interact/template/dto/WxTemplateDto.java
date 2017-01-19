package com.huiyee.interact.template.dto;

import java.util.List;

import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.template.model.WxTemplate;
import com.huiyee.interact.template.model.WxTemplateIndustry;
import com.huiyee.interact.template.model.WxTemplateJob;
import com.huiyee.interact.template.model.WxTemplateStore;

public class WxTemplateDto {

	private List<WxTemplate> list;
	private List<WxTemplateStore> store_list;
	private List<WxTemplateJob> job_list;
	private Pager pager;

	private String industry; // 行业字符串
	private long industry_id1;
	private long industry_id2;

	private List<WxTemplateIndustry> primary_first_list;// 主一级行业列表
	private List<WxTemplateIndustry> secondary_first_list;// 副一级行业列表
	private List<WxTemplateIndustry> primary_second_list;// 主二级行业列表
	private List<WxTemplateIndustry> secondary_second_list;// 副二级行业列表

	public List<WxTemplate> getList() {
		return list;
	}

	public void setList(List<WxTemplate> list) {
		this.list = list;
	}

	public List<WxTemplateStore> getStore_list() {
		return store_list;
	}

	public void setStore_list(List<WxTemplateStore> store_list) {
		this.store_list = store_list;
	}

	public List<WxTemplateJob> getJob_list() {
		return job_list;
	}

	public void setJob_list(List<WxTemplateJob> job_list) {
		this.job_list = job_list;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public long getIndustry_id1() {
		return industry_id1;
	}

	public void setIndustry_id1(long industry_id1) {
		this.industry_id1 = industry_id1;
	}

	public long getIndustry_id2() {
		return industry_id2;
	}

	public void setIndustry_id2(long industry_id2) {
		this.industry_id2 = industry_id2;
	}

	public List<WxTemplateIndustry> getPrimary_first_list() {
		return primary_first_list;
	}

	public void setPrimary_first_list(List<WxTemplateIndustry> primary_first_list) {
		this.primary_first_list = primary_first_list;
	}

	public List<WxTemplateIndustry> getSecondary_first_list() {
		return secondary_first_list;
	}

	public void setSecondary_first_list(List<WxTemplateIndustry> secondary_first_list) {
		this.secondary_first_list = secondary_first_list;
	}

	public List<WxTemplateIndustry> getPrimary_second_list() {
		return primary_second_list;
	}

	public void setPrimary_second_list(List<WxTemplateIndustry> primary_second_list) {
		this.primary_second_list = primary_second_list;
	}

	public List<WxTemplateIndustry> getSecondary_second_list() {
		return secondary_second_list;
	}

	public void setSecondary_second_list(List<WxTemplateIndustry> secondary_second_list) {
		this.secondary_second_list = secondary_second_list;
	}

}
