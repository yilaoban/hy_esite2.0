package com.huiyee.esite.util;

/**
 * 线程池,维护线程
 * autor :hzm
 */
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.huiyee.esite.handler.Handler;
import com.huiyee.esite.handler.HandlerRunner;



public class ThreadPool
{
	private int corePoolSize = 100;// 线程池维护线程的最少数量
	private int maximumPoolSize = 1000;// 线程池维护线程的最大数量
	private long keepAliveTime = 0;// 线程池维护线程所允许的空闲时间
	private TimeUnit unit = TimeUnit.SECONDS;// 线程池维护线程所允许的空闲时间的单位
	private BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);// 线程池所使用的缓冲队列
	private RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();// 线程池对拒绝任务的处理策略
	private int sleeptime = 5000;// 当线程提交任务失败的时候重复提交任务的时间间隔
	private ThreadPoolExecutor pool = null;

	public synchronized void dowork(Handler whandler, Object taskData)
	{
		HandlerRunner hr = new HandlerRunner();
		hr.setData(whandler, taskData);
		if (pool == null)
		{
			pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);// 创建线程池
		}
		try
		{
			pool.execute(hr);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				Thread.sleep(sleeptime);
			}
			catch (InterruptedException e1)
			{

				e1.printStackTrace();
			}
			dowork(whandler, taskData);
		}
	}

	public int getCorePoolSize()
	{
		return corePoolSize;
	}

	public void setCorePoolSize(int corePoolSize)
	{
		this.corePoolSize = corePoolSize;
	}

	public int getMaximumPoolSize()
	{
		return maximumPoolSize;
	}

	public void setMaximumPoolSize(int maximumPoolSize)
	{
		this.maximumPoolSize = maximumPoolSize;
	}

	public long getKeepAliveTime()
	{
		return keepAliveTime;
	}

	public void setKeepAliveTime(long keepAliveTime)
	{
		this.keepAliveTime = keepAliveTime;
	}

	public TimeUnit getUnit()
	{
		return unit;
	}

	public void setUnit(TimeUnit unit)
	{
		this.unit = unit;
	}

	public BlockingQueue<Runnable> getWorkQueue()
	{
		return workQueue;
	}

	public void setWorkQueue(BlockingQueue<Runnable> workQueue)
	{
		this.workQueue = workQueue;
	}

	public RejectedExecutionHandler getHandler()
	{
		return handler;
	}

	public void setHandler(RejectedExecutionHandler handler)
	{
		this.handler = handler;
	}

	public int getSleeptime()
	{
		return sleeptime;
	}

	public void setSleeptime(int sleeptime)
	{
		this.sleeptime = sleeptime;
	}

	public ThreadPoolExecutor getPool()
	{
		return pool;
	}

	public void setPool(ThreadPoolExecutor pool)
	{
		this.pool = pool;
	}

}
