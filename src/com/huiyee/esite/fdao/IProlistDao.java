package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.Prolist;

public interface IProlistDao {

	public List<Prolist> findProlistByfid(long fid);

	public void deleteById(long id);

	public long addProlist(String title, long fid, String content, int idx);

	public void updateProlistTitle(String title, long l, String content, int idx);

}
