package com.huiyee.esite.handler;


public class HandlerRunner implements Runnable
{
	private Handler handler;
	private Object o;

	@Override
	public void run()
	{
		handler.work(o);
	}

	public void setData(Handler h, Object o)
	{
		this.handler = h;
		this.o = o;
	}
}
