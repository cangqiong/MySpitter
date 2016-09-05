package com.bigdata.spitter;

/** 
 * 封装请求信息
 * @author Cang
 *
 */
public class Request {
	
	// 请求链接
	private String url;
	
	// 是否需要模拟浏览器行为
	private boolean neeadSimulation = false;

	public Request(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isNeeadSimulation() {
		return neeadSimulation;
	}

	public void setNeeadSimulation(boolean neeadSimulation) {
		this.neeadSimulation = neeadSimulation;
	}
	
}
