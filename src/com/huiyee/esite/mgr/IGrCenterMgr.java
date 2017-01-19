package com.huiyee.esite.mgr;

import com.huiyee.esite.dto.BalancePageDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.BalancePay;
import com.huiyee.esite.model.BalanceRule;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.VisitUser;

public interface IGrCenterMgr {
	
	public IDto findGrCenterData(long hyuid,long owner,HyUser hyUser);
	
	public IDto findMemberData(long owner,HyUser hyUser);
	
	public IDto findMyBalanceByHyUid(long hyuid,long owner);
	
	public long saveBalancePay(String ip,long hyuid,long owner,long ruleid);
	
	public BalancePay findBalancePayById(long payid);
	
	public BalanceRule findBalanceRuleById(long id,long owner);
	
	public void saveBalanceRecord(long ruleid,String total_fee,String mediaorder,long payid);
	
	public BalancePageDto findBalanceSetByOwner(long owner);
	
	public void updateHyUserLevel(long owner,long hyuid,int total_fee,VisitUser vu);
	
	public int updateSession(VisitUser vu);
	
	public int updateLevelBycheckCode(long owner,long hyuid,String code,VisitUser vu);
	
}
