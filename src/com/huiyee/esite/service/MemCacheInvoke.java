package com.huiyee.esite.service;

import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.huiyee.esite.util.HyConfig;

public class MemCacheInvoke {
	public static MemCacheInvoke getInstance() {
		return mci;
	}

	public Object get(String key) {
		return mcc.get(key);
	}

	public void set(String key, Object value) {
		mcc.set(key, value);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @param validTime 有效期，毫秒值
	 */
	public void set(String key, Object value, long validTime) {
		mcc.set(key, value, new Date(System.currentTimeMillis() + validTime));
	}

	public void set(String key, Object value, Date d) {
		mcc.set(key, value, d);
	}

	private static MemCacheInvoke mci = new MemCacheInvoke();
	private MemCachedClient mcc;

	private static final String POOL_NAME = "MCC_POOL";

	private MemCacheInvoke() {
		init();
	}

	private void init() {
		// 设置缓存服务器列表，当使用分布式缓存的时，可以指定多个缓存服务器
		String[] servers = { HyConfig.getMemcachedServer() };
		// 与服务器列表中对应的各服务器的权重
		Integer[] weights = { 3 };
		// 创建Socked连接池
		SockIOPool pool = SockIOPool.getInstance(POOL_NAME);
		// 向连接池设定服务器和权重
		pool.setServers(servers);
		pool.setWeights(weights);

		// 连接池参数
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 6);
		// set the sleep for the maint thread
		// it will wake up every x seconds and
		// maintain the pool size
		pool.setMaintSleep(30);
		// set some TCP settings
		// disable nagle
		// set the read timeout to 3 secs
		// and don't set a connect timeout
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);
		// initialize the connection pool
		pool.initialize();

		// lets set some compression on for the client
		// compress anything larger than 64k
		mcc = new MemCachedClient(POOL_NAME);
		// mcc.setCompressEnable(true);
		// mcc.setCompressThreshold(64 * 1024);
	}
}