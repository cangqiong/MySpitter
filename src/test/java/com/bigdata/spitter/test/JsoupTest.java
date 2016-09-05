package com.bigdata.spitter.test;

import java.io.File;

import org.jsoup.select.Elements;

import com.bigdata.spitter.util.FileUtils;
import com.bigdata.spitter.util.HtmlUtils;

/**
 * Hello world!
 *
 */
public class JsoupTest {

	public static void main(String[] args) {
		File file = new File("D:/test.html");
		String html = FileUtils.file2String(file, "utf-8");
		// Document doc = Jsoup.parse(html);
//		System.out.println(html);
		String jobname = HtmlUtils.getElementTextBySelector(html,
				"div.fixed-inner-box > div.fl > h1");
		Elements jobname2 = HtmlUtils.getElementBySelector(html,
				"div.fixed-inner-box > div.fl > h1");
		String tags = HtmlUtils.getElementTextBySelector(html,
				"div.fixed-inner-box > div.fl > div.welfare-tab-box");
		String jobInfo = HtmlUtils.getElementTextBySelector(html, "ul.terminal-ul");
		System.out.println("jobname : "+jobname);
//		System.out.println("Elements : "+jobname2);
		System.out.println("tags : "+tags);
		System.out.println("jobInfo : "+jobInfo);
	}
}
