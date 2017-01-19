
package com.huiyee.esite.mgr;

import java.util.List;

import net.sf.json.JSONObject;

import com.huiyee.esite.model.UserPkvTag;
import com.huiyee.esite.model.UserTag;

public interface IUserTagMgr
{

	public List<UserTag> findOwnerTag(long ownerid);

	public UserPkvTag findContentTag(long ccid, long pid);
	
	public UserPkvTag findContentTag(String type, long pid);
	
	public void updateContentTag(long owner, long ccid, long pid, JSONObject tags);

	public UserPkvTag findPageTag(long pageid);

	public void updatePageTag(long id, long pageid, JSONObject tagJson);
}
