package com.huiyee.esite.fmgr.imp;

import com.huiyee.esite.dto.Feature108Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.IUserUploadRecommentListDao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.FeatureUploadRecommentList;
import com.huiyee.esite.model.UserUpload;
import java.util.Collections;
import java.util.List;

public class FeatureUploadRecommentManagerImpl extends AbstractFeatureManager {
    private IUserUploadRecommentListDao userUploadRecommentListDao;

    public void setUserUploadRecommentListDao(IUserUploadRecommentListDao userUploadRecommentListDao) {
        this.userUploadRecommentListDao = userUploadRecommentListDao;
    }

    @Override
    public long add(long pageid, long featureid,String featurename) {
        FeatureUploadRecommentList recommentList = new FeatureUploadRecommentList();
        recommentList.setPageid(pageid);
        long fid = userUploadRecommentListDao.saveFetureUserUploadRecommentList(recommentList);
        return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
    }

    @Override
    public IDto config(long fid) {
        Feature108Dto dto = new Feature108Dto();
        dto.setRecordlist(userUploadRecommentListDao.findUploadRecordByFid(fid));
        return dto;
    }

    @Override
    public String configSub(long featureid, IDto dto, Account account) {
        String result = "Y";
        Feature108Dto featuredto = (Feature108Dto) dto;
        long fid = featuredto.getFid();
        UserUpload upload = userUploadRecommentListDao.findUserUploadListByFid(fid);
        long uploadid = featuredto.getUploadid();
        if (upload == null || (upload.getId() != uploadid)) {
            userUploadRecommentListDao.updateFeatureUserUploadRecomment(fid, uploadid);
        } else {
            List<Long> selrdids = userUploadRecommentListDao.findRecordids(fid);
            List<Long> recordids = featuredto.getRecordids();
            if (selrdids != null && recordids != null) {
                String temp = selrdids.toString();
                String oldrdids = temp.substring(1, temp.length() - 1);
                if (oldrdids != null && !"".equals(oldrdids.trim())) {
                    userUploadRecommentListDao.deleteUpLoadListRecord(oldrdids, fid);
                }
                for (int i = 0; i < recordids.size(); i++) {
                    long recordid = recordids.get(i);
                    int idx = featuredto.getIdidxs().get(i);
                    if (recordid > 0) {
                        userUploadRecommentListDao.saveFeatureUserUploadlistRecord(fid, recordid, idx);
                    }
                }
            } else {
                if (selrdids != null) {
                    String temp = selrdids.toString();
                    String oldrdids = temp.substring(1, temp.length() - 1);
                    if (oldrdids != null && !"".equals(oldrdids.trim())) {
                        userUploadRecommentListDao.deleteUpLoadListRecord(oldrdids, fid);
                    }
                }
                if (recordids != null) {
                    for (int i = 0; i < recordids.size(); i++) {
                        long recordid = recordids.get(i);
                        int idx = featuredto.getIdidxs().get(i);
                        if (recordid > 0) {
                            userUploadRecommentListDao.saveFeatureUserUploadlistRecord(fid, recordid, idx);
                        }
                    }
                }
            }

        }
        return result;
    }

    @Override
    public IDto config(long fid, Account account) {
        Feature108Dto dto = new Feature108Dto();
        dto.setUploadlist(userUploadRecommentListDao.findUserUploadList());
        dto.setUpload(userUploadRecommentListDao.findUserUploadListByFid(fid));
        UserUpload upload = dto.getUpload();
        if (upload != null) {
            dto.setRecordlist(userUploadRecommentListDao.findUploadRecordCheckRecomment(fid, upload.getId()));
        }
        return dto;
    }
}
