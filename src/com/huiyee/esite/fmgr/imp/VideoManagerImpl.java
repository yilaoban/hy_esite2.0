package com.huiyee.esite.fmgr.imp;

import com.huiyee.esite.dto.Feature101Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.IVideoDao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.FeatureVideoList;
import com.huiyee.esite.model.Video;
import java.util.Collections;
import java.util.List;

public class VideoManagerImpl extends AbstractFeatureManager {
    private IVideoDao videoDao;

    public void setVideoDao(IVideoDao videoDao) {
        this.videoDao = videoDao;
    }

    @Override
    public long add(long pageid, long featureid,String featurename) {
        FeatureVideoList video = new FeatureVideoList();
        video.setPageid(pageid);
        long fid = videoDao.saveFetureVideo(video);
        return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
    }

    @Override
    public IDto config(long fid) {
        Feature101Dto d = new Feature101Dto();
        d.setFlist(videoDao.findVideoById(fid));
        return d;
    }

    @Override
    public String configSub(long featureid, IDto dto, Account account) {
        String result = "N";
        Feature101Dto d = (Feature101Dto) dto;
        List<Long> list = d.getVids();
        List<Long> oldlist = d.getOldvids();
        if (list != null && oldlist != null) {
            videoDao.deleteFeatureVideoList(d.getVlvids());
            for (int i = 0; i < list.size(); i++) {
                long vid = list.get(i);
                int idx = d.getIdidxs().get(i);
                if (vid > 0) {
                    videoDao.saveFetureVideoListVideo(d.getFid(), vid, idx);
                }
            }
        } else {
            if (oldlist != null && oldlist.size() > 0) {
                videoDao.deleteFeatureVideoList(d.getVlvids());
            }
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    long vid = list.get(i);
                    int idx = d.getIdidxs().get(i);
                    if (vid > 0) {
                        videoDao.saveFetureVideoListVideo(d.getFid(), vid, idx);
                    }
                }
            }

        }
        result = "Y";
        return result;
    }

    @Override
    public IDto config(long fid, Account account) {
        Feature101Dto dto = new Feature101Dto();
        List<Video> flist = videoDao.findVideoById(fid);
        List<Video> list = videoDao.findVideoByOwner(account.getOwner().getId());
        dto.setList(list);
        dto.setFlist(flist);
        return dto;
    }

}
