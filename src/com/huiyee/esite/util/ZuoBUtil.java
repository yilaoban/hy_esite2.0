package com.huiyee.esite.util;

public class ZuoBUtil
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

      MZb(0, 0, 6, 9);
     
	}

	public static ZuoB MZb(double x, double y, double sr,double er)
	{
		ZuoB zb = new ZuoB();
		double r=Math.random()*(er-sr)+sr;
	    double ai=Math.random()*360*Math.PI/180;
	    double ad=Math.cos(ai)*r;
	    double bd=Math.sin(ai)*r;
	    zb.setX(x-ad);
	    zb.setY(y-bd);
		return zb;
	}
}
