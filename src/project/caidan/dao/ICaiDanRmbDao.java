package project.caidan.dao;

import java.util.List;

import project.caidan.model.CDRmb;
import project.caidan.model.CDRmbGet;


public interface ICaiDanRmbDao
{
	public int findTotalRmbGet(String starttime, String endtime);
	
	public List<CDRmbGet> findRmbGetList(String starttime, String endtime, int start,int size);
	
	public int updateRmbGetById(CDRmbGet rmbGet);
	
	public CDRmb findCDRmbByWxuid(long wxuid);

	public int addRmbGet(long wxuid, float rmb);
	
	public void updateRmbUsed(long wxuid,int used);

	public List<CDRmbGet> findRmbGetList(long wxuid, int i, int size);
	
	public int updateRmbGetStatusById(CDRmbGet rmbGet);
	
	public CDRmbGet findCDRmbGetById(long id);
	
	public int saveCdRmbget(long wxuid, float rmb);

	public List<CDRmbGet> findRmbGetList(String starttime, String endtime);
}
