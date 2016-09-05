package com.bigdata.spitter.processor;

import static com.bigdata.spitter.util.GetProperties.getLinkPattern;

import java.util.List;

import com.bigdata.spitter.Page;
import com.bigdata.spitter.scheduler.TaskQueue;
import com.bigdata.spitter.util.HtmlUtils;

public class SimpleHtmlHandler implements HtmlHandler{

	@Override
	public Page process(String html) {
		Page page = null;
		List<String> links = HtmlUtils.getALinksByregex(html, getLinkPattern());
		TaskQueue.addRequest(links);
		String jobname = HtmlUtils.getElementTextBySelector(html,
				"div.fixed-inner-box > div.fl > h1");
		String tags =  HtmlUtils.getElementTextBySelector(html,
				"div.fixed-inner-box > div.fl > div.welfare-tab-box");
		String jobInfo = HtmlUtils.getElementTextBySelector(html, "ul.terminal-ul");
		if (jobname != null) {
			page = new Page();
			page.putField("jobname", jobname);
			page.putField("tags", tags);
			page.putField("jobInfo", jobInfo);
		}
		return page;
	}

}
