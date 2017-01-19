package com.huiyee.esite.mgr;

import com.huiyee.esite.dto.MaterialDto;
public interface IMaterialManager {
	
	public MaterialDto findMaterialIndex(long fcategory,long scategory,int pageId) ;
	
	public MaterialDto findMaterialList(long fcategory,long scategory,int pageId, int size) ;
}
