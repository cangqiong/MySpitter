package com.bigdata.spitter.storage;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bigdata.spitter.Page;
import com.bigdata.spitter.util.FileUtils;

public class AppendFileStorage implements Storage {

	private Logger logger = LoggerFactory.getLogger(AppendFileStorage.class);
	private String fileName = "jobinfo.txt";

	@Override
	public void process(Page page) {
		if (page == null) {
			return;
		}
		String fullname = this.path + fileName;
		if (page.getFields().get("jobname") == null) {
			return;
		}
		try {
			PrintWriter  out = new PrintWriter(new FileWriter(FileUtils.getFile(fullname), true));

			for (Map.Entry<String, Object> entry : page.getFields().entrySet()) {
				if (entry.getValue() instanceof Iterable) {

					Iterable value = (Iterable) entry.getValue();
					out.write(entry.getKey() + ":");
					for (Object o : value) {
						out.write(o.toString());
					}
				} else {
					out.write(entry.getKey() + ":" + entry.getValue());
				}
				out.write("\t");
			}
			out.write("\n");
			out.close();
		} catch (IOException e) {
			logger.warn("write file error", e);
		}
	}

}
