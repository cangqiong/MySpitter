package com.bigdata.spitter.processor;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import com.bigdata.spitter.Page;

public class PageResponseHandler implements ResponseHandler<Page> {

	private static HtmlHandler htmlHandler = null;

	@Override
	public Page handleResponse(HttpResponse response)
			throws ClientProtocolException, IOException {
		StatusLine statusLine = response.getStatusLine();
		HttpEntity entity = response.getEntity();
		if (statusLine.getStatusCode() >= 300) {
			EntityUtils.consume(entity);
			throw new HttpResponseException(statusLine.getStatusCode(),
					statusLine.getReasonPhrase());
		}
		if (entity == null)
			return null;

		// 利用HTTPClient自带的EntityUtils把当前HttpResponse中的HttpEntity转化成HTML代码
		String html = EntityUtils.toString(entity);
		return htmlHandler.process(html);
	}

	// 设置处理页面抽取对象
	public static void setHtmlHandler(HtmlHandler htmlHandler) {
		PageResponseHandler.htmlHandler = htmlHandler;
	}

}