package com.huiyee.esite.mgr;

import java.util.List;
import java.util.Map;

import com.huiyee.esite.model.InteractModel;

public interface IInteractModelManager {
	
	public List<InteractModel> findInteractModel(long owner);

	public List<InteractModel> findAllInteractModel(long id);

	public List<InteractModel> findInteractModel();

	public String addOwnerInteract(String title, long mid, long owner);

	public int deleteOwnerInteract(long lid, long ownerid);

	public int updateOwnerInteract(long lid, String title, String status, long ownerid);

	public InteractModel findInteractModelById(long omid);

	public int updateOwnerInteractTitle(long lid, long owner, String title);

	public int updateOwnerInteractStatus(long lid, long owner, String status);

}
