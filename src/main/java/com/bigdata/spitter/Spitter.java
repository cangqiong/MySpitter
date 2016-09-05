package com.bigdata.spitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bigdata.spitter.featch.WorkerThreadPool;
import com.bigdata.spitter.processor.HtmlHandler;
import com.bigdata.spitter.processor.PageResponseHandler;
import com.bigdata.spitter.processor.SimpleHtmlHandler;
import com.bigdata.spitter.scheduler.TaskQueue;
import com.bigdata.spitter.storage.ConsoleStorage;
import com.bigdata.spitter.storage.Storage;
import com.bigdata.spitter.util.GetProperties;

/**
 * 爬虫主类
 * 
 * @author Cang
 *
 */
public class Spitter {

	private static Logger logger = LoggerFactory.getLogger(Spitter.class);

	private int threadNum = GetProperties.getThreadNum();
	private String startUrl = GetProperties.getStartUrl();

	// 线程池
	private WorkerThreadPool workerThreadPool = null;
	// 设置页面响应处理对象
	private PageResponseHandler responseHandler = null;
	// 设置页处理对象
	private HtmlHandler htmlHandler = null;
	// 存储方法
	private Storage storage = null;

	public Spitter(HtmlHandler handler) {
		setHtmlHandler(handler);
	}

	/**
	 * 爬虫状态初始化
	 */
	private void init() {
		workerThreadPool = new WorkerThreadPool(threadNum);
		Request request = new Request(startUrl);
		TaskQueue.addRequest(request);
		if (htmlHandler == null) {
			// 设置页面响应对象
			setResponseHandler(new PageResponseHandler());
			// 设置页面处理对象
			setHtmlHandler(new SimpleHtmlHandler());
		}
		if (storage == null) {
			// 默认存储方法
			setStorage(new ConsoleStorage());
		}
	}

	/**
	 * 开始运行爬虫
	 */
	public void start() {
		// 初始化爬虫配置
		init();
		logger.info("Spider started!");
		for (int i = 0; i < threadNum; i++) {
			workerThreadPool.execute(new SpitterInstance(responseHandler,
					storage));
		}
	}

	/**
	 * 设置存储方式
	 */
	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	/**
	 * 设置线程数目
	 * 
	 * @param threadNum
	 */
	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	/**
	 * 设置页面响应处理类
	 * 
	 * @param responseHandler
	 */
	public void setResponseHandler(PageResponseHandler responseHandler) {
		this.responseHandler = responseHandler;
	}

	/**
	 * 设置页面处理对象
	 * 
	 * @param htmlHandler
	 */
	public void setHtmlHandler(HtmlHandler htmlHandler) {
		PageResponseHandler.setHtmlHandler(htmlHandler);
	}

	/**
	 * 设置起始url
	 * 
	 * @param startUrl
	 */
	public void setStartUrl(String startUrl) {
		this.startUrl = startUrl;
	}

}
