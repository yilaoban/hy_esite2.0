package project.caidan.mgr;

import java.util.List;

import project.caidan.model.CDRmb;
import project.caidan.model.CDRmbGet;

import com.huiyee.esite.dto.IDto;


public interface ICaiDanRmbMgr
{
	public IDto findRmbGetList(int pageId, String starttime, String endtime);
	
	public int updateRmbGetById(CDRmbGet rmbGet);
	
	public int updateRmbGetStatusById(CDRmbGet rmbGet);

	public CDRmb findRmbByWxuid(long wxuid);

	public int addRmbGet(long wxuid, float rmb);

	public List<CDRmbGet> findRmbGetList(long wxuid, int pageId, int size);

	public List<CDRmbGet> findRmbGetList(String starttime, String endtime);
}
