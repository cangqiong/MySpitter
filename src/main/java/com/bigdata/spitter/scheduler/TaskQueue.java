package com.bigdata.spitter.scheduler;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bigdata.spitter.Request;

/**
 * 任务队列
 * 
 * @author Cang
 *
 */
public class TaskQueue {

	private static Logger logger = LoggerFactory.getLogger(TaskQueue.class);
	// 链接队列,FIFO
	private static LinkedBlockingQueue<Request> requestQueue = new LinkedBlockingQueue<Request>();
	private static SimpleBloomFilter bloomFilter = new SimpleBloomFilter();

	public synchronized static void addRequest(Request req) {
		requestQueue.add(req);
	}

	public synchronized static Request getRequest() {
		return requestQueue.poll();
	}

	public synchronized static String getUrl() {
		logger.info("Get url, the queue size is " + size());
		if (requestQueue.isEmpty()) {
			return null;
		}
		return requestQueue.poll().getUrl();
	}

	public synchronized static boolean isEmpty() {
		return requestQueue.isEmpty();
	}

	public synchronized static int size() {
		return requestQueue.size();
	}

	public synchronized static LinkedBlockingQueue<Request> getRequestQueue() {
		return requestQueue;
	}

	/**
	 * 添加请求
	 * 
	 * @param links
	 */
	public synchronized static void addRequest(List<String> links) {
		for (String link : links) {
			if (bloomFilter.contains(link)) {
				continue;
			}
			addRequest(new Request(link));
		}
		logger.info("Add request, the queue size is " + size());
		return;
	}

}
