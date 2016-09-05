package com.bigdata.spitter.storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.bigdata.spitter.Page;
import com.bigdata.spitter.util.FileUtils;

public class ConsoleStorage implements Storage {

	private static Logger logger = LoggerFactory
			.getLogger(ConsoleStorage.class);

	@Override
	public void process(Page page) {
		if (page == null) {
			System.out.println("page is null");
			return;
		}
		for (Entry<String, Object> map : page.getFields().entrySet()) {
			System.out.println(map);
		}
	}

}
