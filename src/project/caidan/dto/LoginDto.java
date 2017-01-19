package project.caidan.dto;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Account;

public class LoginDto implements IDto{
	private Account account;
	private String type;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	
	public String getType()
	{
		return type;
	}

	
	public void setType(String type)
	{
		this.type = type;
	}
	
}
