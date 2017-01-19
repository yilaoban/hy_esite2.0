
package project.caidan.dao;

import java.util.List;

import project.caidan.model.CDAccountCpz;
import project.caidan.model.CDAccountDl;
import project.caidan.model.CDAccoutType;
import project.caidan.model.CDChannel;

public interface ICaiDanTeamDao
{

	public CDAccoutType findAccountTypeByWxuid(long wxuid, String type);

	public List<CDChannel> findChannelByAcid(long acid, long owner);

	public CDAccountDl findAccountDlByWxuid(long wxuid);

	public CDAccountCpz findAccountCpzByWxuid(long wxuid);

	public List<CDAccountDl> findAccountDlListByWxuid(long wxuid, String status);

	public List<CDAccountCpz> findAccountCpzByWxuid(long wxuid, String status);

	public CDAccountDl findAccountDlById(long id);

	public CDAccountCpz findAccountCpzById(long id);

	public CDChannel findChannelById(long id, long owner);

	public int updateAccountDlById(long id, long clid, String status);

	public int updateAccountCpzById(long id, long clid, String status);

	public int saveAccountDl(CDAccountDl accountdl);

	public int saveAccountCpz(CDAccountDl accountdl);

	public CDAccountDl findAccountDlByWxuidAndFwxuid(long wxuid, long fawxuid);

	public CDAccountCpz findAccountCpzByWxuidAndFwxuid(long wxuid, long fawxuid);

	public int updateAccountDlByWxuidAndFwxuid(CDAccountDl accountdl);

	public int updateAccountCpzByWxuidAndFwxuid(CDAccountDl accountdl);

	public List<CDAccountDl> findDlByFaWxuid(long wxuid, long wayid, String status, String starttime, String endtime);

	public int findCpzTotalByFather(long wxuid, long wayid, String starttime, String endtime);

	public List<CDAccountCpz> findCpzByFather(long wxuid, long wayid, String starttime, String endtime, int i, int reportCpzLimit);

}
