package com.huiyee.esite.mgr.imp;

import java.util.List;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IMaterialDao;
import com.huiyee.esite.dto.MaterialDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.mgr.IMaterialManager;
import com.huiyee.esite.model.MaterialCategory;
import com.huiyee.esite.model.MaterialPic;
public class MaterialManagerImpl implements IMaterialManager {
	
	private IMaterialDao materialDao;
	@Override
	public MaterialDto findMaterialIndex(long fcategory,long scategory,int pageId) {
		int size=0;
		List<MaterialCategory> catetgorylist=materialDao.findFCategoryList();
		if(catetgorylist!=null&&catetgorylist.size()>0){
			if (fcategory == 0)
			{
				fcategory = catetgorylist.get(0).getId();
			}
			List<MaterialCategory> scatetgorylist = materialDao.findSCategoryList(fcategory);
				if(scatetgorylist!=null&&scatetgorylist.size()>0){
					if (scategory == 0){
						scategory = scatetgorylist.get(0).getId();
					}
					size=materialDao.findCountById(scategory);
				}
		}
		return findMaterialList(fcategory, scategory, pageId,size);
	}
	
	@Override
	public MaterialDto findMaterialList(long fcategory,long scategory,int pageId, int size) {
		MaterialDto dto=new MaterialDto();
		List<MaterialCategory> catetgorylist=materialDao.findFCategoryList();
		if(catetgorylist!=null&&catetgorylist.size()>0){
			List<MaterialCategory> scatetgorylist = materialDao.findSCategoryList(fcategory);
				int start = (pageId - 1) * size;
				if(scatetgorylist!=null&&scatetgorylist.size()>0){
					if(scategory ==0){
						scategory = scatetgorylist.get(0).getId();
					}
						List<MaterialPic> materiallist = materialDao.findPicList(fcategory, scategory, start, size);
						dto.setMateriallist(materiallist);
						int total = materialDao.findMaterialCount(fcategory, scategory);
						dto.setTotal(total);
						dto.setScategoryList(scatetgorylist);
						dto.setPager(new Pager(pageId,total,size));
				}else{
					List<MaterialPic> materiallist = materialDao.findPicList(fcategory, 0, start, size);
					dto.setMateriallist(materiallist);
					int total = materialDao.findMaterialCount(fcategory, 0);
					dto.setPager(new Pager(pageId,total,size));
				}
			dto.setFid(fcategory);
			dto.setSid(scategory);
			dto.setFcategoryList(catetgorylist);
		}
		return dto;
	}
	
	public IMaterialDao getMaterialDao()
	{
		return materialDao;
	}
	public void setMaterialDao(IMaterialDao materialDao)
	{
		this.materialDao = materialDao;
	}
	

}
