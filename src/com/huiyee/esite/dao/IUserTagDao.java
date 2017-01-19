
package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.UserPkvTag;
import com.huiyee.esite.model.UserTag;

public interface IUserTagDao
{

	public List<UserTag> findOwnerTag(long owner);

	public UserPkvTag findUserPkvTag(String k_s, String v_s);

	public UserTag findUserTagById(long tagid);

	public long findTagByName(long owner, String name);

	public long saveTag(long owner, String name);

	public void savePkvTag(UserPkvTag t);

	public UserPkvTag findUserPkvTag(long pageid);
}
