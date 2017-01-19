package com.huiyee.esite.dto;

import java.util.ArrayList;
import java.util.List;

import com.huiyee.esite.model.Activity;
import com.huiyee.esite.model.Feature;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.Page;

public class FeatureMgrDto implements IDto{

	private Page page;
	private List<Feature> moduleFeatures;
	private ArrayList<Long> featureids;
	private List<Activity> activity;
	private List<String> activityFeatures;
	private List<Module> modules;
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<Feature> getModuleFeatures() {
		return moduleFeatures;
	}
	public void setModuleFeatures(List<Feature> moduleFeatures) {
		this.moduleFeatures = moduleFeatures;
	}
	public ArrayList<Long> getFeatureids() {
		return featureids;
	}
	public void setFeatureids(ArrayList<Long> featureids) {
		this.featureids = featureids;
	}
	public List<Activity> getActivity() {
		return activity;
	}
	public void setActivity(List<Activity> activity) {
		this.activity = activity;
	}
	public List<String> getActivityFeatures() {
		return activityFeatures;
	}
	public void setActivityFeatures(List<String> activityFeatures) {
		this.activityFeatures = activityFeatures;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

}
