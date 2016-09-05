package com.bigdata.spitter;

import java.io.IOException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bigdata.spitter.featch.PoolManager;
import com.bigdata.spitter.processor.PageResponseHandler;
import com.bigdata.spitter.scheduler.TaskQueue;
import com.bigdata.spitter.storage.Storage;

/**
 * 爬虫实例
 * 
 * @author Cang
 *
 */
public class SpitterInstance implements Runnable {

	private static Logger logger = LoggerFactory
			.getLogger(SpitterInstance.class);

	public static final Object signal = new Object(); // 线程间通信变量
	private int waitCount = 0; // 表示有多少个线程处于wait状态

	// 设置页面响应处理类
	private PageResponseHandler responseHandler = null;
	// 存储方法
	private Storage storage = null;

	public SpitterInstance(PageResponseHandler responseHandler, Storage storage) {
		this.responseHandler = responseHandler;
		this.storage = storage;
	}

	@Override
	public void run() {
		while (true) {
			logger.info("current thread: " + Thread.currentThread().getName());
			String url = TaskQueue.getUrl();
			if (url == null) {
				synchronized (signal) {
					try {
						waitCount++;
						logger.info("当前有" + waitCount + "个线程在等待");
						signal.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				fetchPage(url);
				if (waitCount > 0) { // 如果有等待的线程，则唤醒
					synchronized (signal) {
						waitCount--;
						signal.notify();
					}
				}
			}
		}

	}

	private void fetchPage(String url) {
		logger.info("current thread:" + Thread.currentThread().getName()
				+ " catch url: " + url);
		// 获取连接
		HttpClient httpclient = PoolManager.getHttpClient();
		// 设置URL
		HttpGet httpget = new HttpGet(url);
		try {
			Page page = httpclient.execute(httpget, responseHandler);
			storage.process(page);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
