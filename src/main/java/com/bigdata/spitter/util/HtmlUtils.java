package com.bigdata.spitter.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * HTML解析抽取工具类
 * 
 * @author Cang
 *
 */
public class HtmlUtils {

	/**
	 * 获取选择器选择的页面元素的文本信息
	 * 
	 * @param html
	 *            HTML字符串
	 * @param selector
	 *            css选择器
	 * @return
	 */
	public static String getElementTextBySelector(String html, String selector) {
		Document document = Jsoup.parse(html);
		Elements e = document.select(selector);
		return Jsoup.parse(e.toString()).text();
	}

	/**
	 * 获取选择器选择的页面元素
	 * 
	 * @param html
	 *            HTML字符串
	 * @param selector
	 *            css选择器
	 * @return
	 */
	public static Elements getElementBySelector(String html, String selector) {
		Document document = Jsoup.parse(html);
		return document.select(selector);
	}

	/**
	 * 获取a标签的链接
	 * 
	 * @param html
	 *            HTML字符串
	 * @return
	 */
	public static List<String> getALinks(String html) {
		List<String> linkList = new ArrayList<String>();
		Document document = Jsoup.parse(html);
		// 获取链接
		Elements links = document.select("a[href]");
		for (Element link : links) {
			linkList.add(link.attr("abs:href"));
		}
		return linkList;
	}

	/**
	 * 获取固定格式a标签的链接
	 * 
	 * @param html
	 *            HTML字符串
	 * @param pattern
	 *            要匹配的模式
	 * @return
	 */
	public static List<String> getALinksByregex(String html, Pattern pattern) {
		List<String> linkList = new ArrayList<String>();
		Document document = Jsoup.parse(html);
		// 获取链接
		Elements links = document.select("a[href]");
		String temp = "";
		for (Element link : links) {
			temp = link.attr("abs:href");
			if (pattern.matcher(temp).find()) {
//				if (temp.contains("?")) {
//					linkList.add(temp.substring(0, temp.indexOf("?")));
//				} else {
					linkList.add(temp);
//				}
			}
		}
		return linkList;
	}
}
