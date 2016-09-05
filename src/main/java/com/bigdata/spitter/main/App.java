package com.bigdata.spitter.main;

import com.bigdata.spitter.Spitter;
import com.bigdata.spitter.processor.SimpleHtmlHandler;
import com.bigdata.spitter.storage.AppendFileStorage;

/**
 * 程序入口
 *
 */
public class App {

	public static void main(String[] args) {
		Spitter spitter = new Spitter(new SimpleHtmlHandler());
		spitter.setStorage(new AppendFileStorage());
		spitter.start();
	}
}
