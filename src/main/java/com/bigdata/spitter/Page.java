package com.bigdata.spitter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.bigdata.spitter.scheduler.TaskQueue;

/**
 * 抓取的页面对象
 * 
 * @author Cang
 *
 */
public class Page {

	private String url = null;
	// 要抓取的信息
	private Map<String, Object> fields = new LinkedHashMap<String, Object>();

	public void putField(String string, String text) {
		fields.put(string, text);
	}

	public Map<String, Object> getFields() {
		return fields;
	}

	public void setFields(Map<String, Object> fields) {
		this.fields = fields;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
