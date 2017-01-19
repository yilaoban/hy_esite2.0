package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * »î¶¯
 * @author hy
 *
 */
public class Activity implements Serializable{
	private long id;
	private String name;
	private Date createtime;
	private String status;
	private long siteid;
	private long ownerid;
	private List<Module> modules;
	private List<Feature> features;
	private List<Long> moduleids;
	
	public long getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(long ownerid) {
		this.ownerid = ownerid;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getSiteid() {
		return siteid;
	}
	public void setSiteid(long siteid) {
		this.siteid = siteid;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	public List<Feature> getFeatures() {
		return features;
	}
	public void setFeatures(List<Feature> features) {
		this.features = features;
	}
	public List<Long> getModuleids() {
		return moduleids;
	}
	public void setModuleids(List<Long> moduleids) {
		this.moduleids = moduleids;
	}
}
