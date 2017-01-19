
package project.caidan.model;

import java.util.Date;

import com.huiyee.esite.model.Account;

public class CDWay
{

	private long id;
	private String name;
	private String hydesc;
	private long owner;
	private Date createtime;
	private long caid;
	private CDWayCatalog catalog;
	private long acid;
	private CDAccoutType account;// ÔËÎ¬¾­Àí
	private String idStr;
	private String realname;
	private String email;
	private String telphone;
	private String address;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		if(id > 0){
			idStr = String.format("A%07d", id);
		}
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getHydesc()
	{
		return hydesc;
	}

	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}

	public long getOwner()
	{
		return owner;
	}

	public void setOwner(long owner)
	{
		this.owner = owner;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public long getCaid()
	{
		return caid;
	}

	public void setCaid(long caid)
	{
		this.caid = caid;
	}

	public CDWayCatalog getCatalog()
	{
		return catalog;
	}

	public void setCatalog(CDWayCatalog catalog)
	{
		this.catalog = catalog;
	}

	public long getAcid()
	{
		return acid;
	}

	public void setAcid(long acid)
	{
		this.acid = acid;
	}

	public CDAccoutType getAccount()
	{
		return account;
	}

	public void setAccount(CDAccoutType account)
	{
		this.account = account;
	}

	
	public String getIdStr()
	{
		return idStr;
	}

	
	public void setIdStr(String idStr)
	{
		this.idStr = idStr;
	}

	
	public String getRealname()
	{
		return realname;
	}

	
	public void setRealname(String realname)
	{
		this.realname = realname;
	}

	
	public String getEmail()
	{
		return email;
	}

	
	public void setEmail(String email)
	{
		this.email = email;
	}

	
	public String getTelphone()
	{
		return telphone;
	}

	
	public void setTelphone(String telphone)
	{
		this.telphone = telphone;
	}

	
	public String getAddress()
	{
		return address;
	}

	
	public void setAddress(String address)
	{
		this.address = address;
	}

}
