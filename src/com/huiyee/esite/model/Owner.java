package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Owner implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3279416389800177711L;
	private long id;
	private String entity;//会员名
	private String domain;
	private Date endtime;
	private String sup;//是否是超级用户 N：普通用户 Y：超级用户
	private List<OwnerPrivilege> privileges;
	private Map<Long, Integer> permission;
	
	public Map<Long, Integer> getPermission()
	{
		return permission;
	}

	
	public void setPermission(Map<Long, Integer> permission)
	{
		this.permission = permission;
	}

	public List<OwnerPrivilege> getPrivileges()
	{
		return privileges;
	}
	
	public void setPrivileges(List<OwnerPrivilege> privileges)
	{
		this.privileges = privileges;
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		if(privileges != null){
			for(OwnerPrivilege op : privileges){
				map.put(op.getPid(), op.getLevel());
			}
		}
		setPermission(map);
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public Date getEndtime()
	{
		return endtime;
	}
	public void setEndtime(Date endtime)
	{
		this.endtime = endtime;
	}
	public String getSup() {
		return sup;
	}
	public void setSup(String sup) {
		this.sup = sup;
	}
	
	public Boolean contains(Long id){
		return permission.containsKey(id);
	}
}
