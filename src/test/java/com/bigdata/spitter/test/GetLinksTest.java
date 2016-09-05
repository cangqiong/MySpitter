package com.bigdata.spitter.test;

import com.bigdata.spitter.Spitter;
import com.bigdata.spitter.processor.SimpleHtmlHandler;
import com.bigdata.spitter.storage.ConsoleStorage;

/**
 * Hello world!
 *
 */
public class GetLinksTest {

	public static void main(String[] args) {
		Spitter spitter = new Spitter(new SimpleHtmlHandler());
		spitter.setStorage(new ConsoleStorage());
		spitter.setStartUrl("http://jobs.zhaopin.com/141087207250569.htm");
		spitter.start();
	}
}
