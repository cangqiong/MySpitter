package com.bigdata.spitter.featch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * 工作爬虫线程池
 * 
 * @author Cang
 *
 */
public class WorkerThreadPool {

	private int threadNum;

	private ExecutorService executorService;

	public WorkerThreadPool(int threadNum) {
		this.threadNum = threadNum;
		this.executorService = Executors.newFixedThreadPool(threadNum);
	}

	public void execute(final Runnable runnable) {
		executorService.execute(runnable);
	}

	public void submit(final Runnable runnable) {
		executorService.submit(runnable);
	}
}