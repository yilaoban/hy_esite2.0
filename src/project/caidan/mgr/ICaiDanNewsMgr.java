package project.caidan.mgr;

import java.util.List;

import project.caidan.model.CDNews;
import net.sf.json.JSONObject;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentNew;


public interface ICaiDanNewsMgr
{

	public IDto findList(Account account, String accountType, int pageId);

	public long saveNews(ContentNew news, JSONObject tagJson, Account account, String accountType);

	public ContentNew findNewById(long contentid, long id);

	public int updateNews(ContentNew news, JSONObject tagJson, Account account, String accountType);

	public int deletecdNews(long contentid, long id);

	public int updateNewsUp(long contentid, long id);

	public int updateNewsDown(long contentid, long id);

	public List<CDNews> findListForCaiMin(long owner, int pageid, int size);

	public List<CDNews> findListForStation(long owner, String accountType, int pageid, int size);

}
