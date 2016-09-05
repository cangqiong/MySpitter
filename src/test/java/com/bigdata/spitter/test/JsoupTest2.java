package com.bigdata.spitter.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.bigdata.spitter.util.HtmlUtils;

/**
 * Hello world!
 *
 */
public class JsoupTest2 {

	public static void main(String[] args) throws IOException {
		String url = "http://jobs.zhaopin.com/141087207250569.htm";
		Document doc = Jsoup.connect(url).get();
		// 获取链接
		Elements links = doc.select("a[href]");
		System.out.println(links.size());
		for (Element link : links) {
			System.out.println(link.attr("abs:href"));
		}
	}
}
