package com.huiyee.esite.fdao;

import com.huiyee.esite.model.Notice;

public interface INoticeDao {
	
	public Notice findNoticeById(long id);
	
	public int updateNotice(Notice notice);
	
	public long addNotice();
	
}
