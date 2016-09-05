package com.bigdata.spitter;

/**
 * 站点信息
 * 
 * @author Cang
 *
 */
public class Site {

	private String domain = null;
	private int featchDelayTime = 0;

	public int getFeatchDelayTime() {
		return featchDelayTime;
	}

	public void setFeatchDelayTime(int featchDelayTime) {
		this.featchDelayTime = featchDelayTime;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

}
