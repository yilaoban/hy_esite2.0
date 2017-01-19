package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.model.OwnerSetting;

public interface IHyUtilMgr
{
	 public long findOwnerByName(String oname);
	 
	 /**
	  * 本来是让用 客户自己创建第三方平台，然后可以用自己的域名，现在不用了。
	  * @return
	  */
	 public List<OwnerSetting> findOwnerSetting();
}
