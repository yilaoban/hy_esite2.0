package com.huiyee.esite.dao;

import java.util.List;
import java.util.Map;

import com.huiyee.esite.model.InteractModel;

public interface IInteractModelDao {
	
	/**
	 * 所有互动模块
	 * @return
	 */
	public List<InteractModel> findInteractModel();

	/**
	 * 用户选中的展示模块
	 * @param owner
	 * @return
	 */
	public List<InteractModel> findInteractModel(long owner);

	public void removeInteract(long owner);

	/**
	 * 所有用户模块 除了已删除的
	 * @param owner
	 * @return
	 */
	public List<InteractModel> findAllInteractModel(long owner);

	/**
	 * 添加ownerinteract
	 * @param title
	 * @param mid
	 * @param owner
	 * @return
	 */
	public int addOwnerInteract(String title, long mid, long owner);

	public int deleteOwnerInteract(long lid, long ownerid);

	public int updateOwnerInteract(long lid, String title, String status, long ownerid);

	public int findTotalByMid(long mid, long owner);

	public InteractModel findInteractModelById(long omid);

	public int updateOwnerInteractStatus(long lid, long owner, String status);

	public int updateOwnerInteractTitle(long lid, long owner, String title);

	public int findCountByTitle(String title, long owner);
	
}
