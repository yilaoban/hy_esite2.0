package com.huiyee.esite.fmgr.imp;

import com.google.gson.Gson;
import com.huiyee.esite.dto.Feature109Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.IPictureDao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.Content;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.Picture;
import java.util.List;

import net.sf.json.JSONObject;

public class PictureManagerImpl extends AbstractFeatureManager {
    private IPictureDao pictureDao;

    public void setPictureDao(IPictureDao pictureDao) {
        this.pictureDao = pictureDao;
    }

    @Override
    public long add(long pageid, long featureid,String featurename) {
        long fid = pictureDao.saveFeturePicture(pageid);
        return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
    }

    @Override
    public IDto config(long fid) {
        Feature109Dto d = new Feature109Dto();
        d.setFlist(pictureDao.findPictureById(fid));
        return d;
    }

    @Override
    public String configSub(long featureid, IDto dto, Account account) {
        String result = "N";
        Feature109Dto d = (Feature109Dto) dto;
        List<Long> list = d.getPids();
        List<Long> oldlist = d.getOldpids();
        if (list != null && oldlist != null) {
            pictureDao.deleteFeaturePictureList(d.getVlpids());
            for (int i = 0; i < list.size(); i++) {
                long pid=list.get(i);
                if (pid > 0) {
                    int idx=d.getIdidxs().get(i);
                    pictureDao.saveFeturePicListPic(d.getFid(), pid,idx);
                }
            }
        } else {
            if (oldlist != null && oldlist.size() > 0) {
                pictureDao.deleteFeaturePictureList(d.getVlpids());
            }
            if (list != null && list.size() > 0) {
               for (int i = 0; i < list.size(); i++) {
                   long pid=list.get(i);
                   if (pid > 0) {
                       int idx=d.getIdidxs().get(i);
                       pictureDao.saveFeturePicListPic(d.getFid(), pid,idx);
                   }
                   
               }
            }

        }
        result = "Y";
        return result;
    }

    @Override
    public IDto config(long fid, Account account) {
        Feature109Dto dto = new Feature109Dto();
        List<Picture> flist = pictureDao.findPictureById(fid);
        List<Picture> list = pictureDao.findPictureByOwner(account.getOwner().getId());
        dto.setList(list);
        dto.setFlist(flist);
        return dto;
    }

    @Override
	public IDto configNew(long fid, Account account, long relationid) {
    	Feature109Dto dto = new Feature109Dto();
		dto.setFid(fid);
		List<CategoryTree> categoryTreeList = pictureDao.findTreeByType("P", account.getOwner().getId());
		dto.setCategoryTreeList(categoryTreeList);
		PageBlockRelation pbr = pictureDao.findPageBlockRelationByRelationid(relationid);
		if(pbr != null){
			JSONObject jb = JSONObject.fromObject(pbr.getJson());
			String hd = jb.get("obj").toString();
			jb = JSONObject.fromObject(hd);
			Object obj = jb.get("ccid");
			if(obj != null){
				long ccid = jb.getLong("ccid");
				dto.setCcid(ccid);
			}
		}
		return dto;
	}
	
	@Override
	public String configSubNew(long featureid, IDto dto, Account account) {
		Feature109Dto d = (Feature109Dto) dto;
		Content content = new Content();
		content.setCcid(d.getCcid());
		content.setFeatureid(featureid);
		configSub(featureid, dto, account);
		long ownerid = account.getOwner().getId();
		content.setOwnerid(ownerid);
		content.setHyType("P");
		Gson gson = new Gson();
		String str1 = gson.toJson(content);
		PageBlockRelation pbr = pictureDao.findPageBlockRelationByRelationid(d.getRelationid());
		String str = pbr.getJson();
		JSONObject jo = JSONObject.fromObject(str);
		jo.element("obj", str1);
		pictureDao.updatePageBlockRelationByRelationid(d.getRelationid(),jo.toString());
		return "Y";
	}
    
}
