package project.chuanmc.mgr;

import com.huiyee.esite.dto.IDto;

public interface IAccountManager {
	
	public IDto login(String ownername,String accountname,String password,String ip);
}
