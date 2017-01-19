package com.huiyee.esite.mgr;

import com.huiyee.esite.model.BalanceSet;
import com.huiyee.interact.checkin.model.Checkin;


public interface IJfDesignMgr
{
	public int savezfjfDesign(long owner, BalanceSet balanceSet);
	
	public int savesqjfDesign(long owner, BalanceSet balanceSet);
	
	public int savezsjfDesign(long owner, BalanceSet balanceSet);
	
	public int savexqjfDesign(long owner, BalanceSet balanceSet);
	
	public int saveyyjfDesign(long owner, BalanceSet balanceSet);
	
	public int savepjjfDesign(long owner, BalanceSet balanceSet);
	
	public int saveczDesign(long owner,BalanceSet balanceSet);
	
	public int saveqdjfDesign(long owner,Checkin checkin);
	
	public BalanceSet findBalanceSetByOwner(long owner);

	public int saveJfPageSet(long owner, long sitegroupid);
	
}
