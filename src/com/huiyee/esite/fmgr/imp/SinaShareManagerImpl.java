package com.huiyee.esite.fmgr.imp;

import java.util.ArrayList;
import java.util.List;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.ISinaShareRecordDao;
import com.huiyee.esite.dto.ExportDto;
import com.huiyee.esite.dto.Feature112Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show112Dto;
import com.huiyee.esite.fdao.ISinaShareDao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.util.Base62Util;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.esite.util.HyConfig;

public class SinaShareManagerImpl extends AbstractFeatureManager {

	private ISinaShareDao sinaShareDao;
	private ISinaShareRecordDao sinaShareRecordDao;

	public void setSinaShareRecordDao(ISinaShareRecordDao sinaShareRecordDao) {
		this.sinaShareRecordDao = sinaShareRecordDao;
	}

	@Override
	public long add(long pageid, long featureid,String featurename) {
		long fid = sinaShareDao.addFeatureSinaShare(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}

	@Override
	public IDto config(long fid, Account account) {
		Feature112Dto dto = new Feature112Dto();
		dto.setShare(sinaShareDao.findFeatureSinaShareById(fid));
		return dto;
	}

	@Override
	public IDto config(long fid) {
		Show112Dto dto = new Show112Dto();
		dto.setShare(sinaShareDao.findFeatureSinaShareById(fid));
		return dto;
	}

	@Override
	public String configSub(long featureid, IDto dto, Account account) {
		Feature112Dto d = (Feature112Dto) dto;
		int result = sinaShareDao.updateFeatureSinaShare(d.getShare());
		if (result > 0) {
			return IPageConstants.FEATURE_MGR_RESULT_SUCCESS;
		}
		return "系统出错!";
	}

	public List<String> export(long featureid, long sitegroupid, long ownerid,ExportDto exportDto) {
		List<String> list = new ArrayList<String>();
		list.add("昵称,微博链接,内容,图片地址,发布时间");
		List<SinaChecklistRecord> record = new ArrayList<SinaChecklistRecord>();
		record = sinaShareRecordDao.findExport(sitegroupid, ownerid);
		if (record != null && record.size() > 0) {
			for (SinaChecklistRecord sclr : record) {
				StringBuilder sb = new StringBuilder();
				sb.append(sclr.getUser().getNickname() + ",");
				sb.append("http://www.weibo.com/" + sclr.getUser().getWbuid() + "/" + Base62Util.MidToUrl(String.valueOf(sclr.getWbid())) + ",");
				sb.append(sclr.getContent().replaceAll(",", "，") + ",");
				sb.append(HyConfig.getImgDomain() + "/" + sclr.getImgPath() + ",");
				sb.append(DateUtil.getDateTimeString(sclr.getCreatetime())+",");
				list.add(sb.toString());
			}
		}
		return list;
	}

	public void setSinaShareDao(ISinaShareDao sinaShareDao) {
		this.sinaShareDao = sinaShareDao;
	}
}
