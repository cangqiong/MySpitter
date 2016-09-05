# MySpitter

## 说明文档：
	抓取招聘网站的特定信息，如智联招聘
### 技术选型
信息的抓取：	httpclient-4.4.1.jar
信息的抽取：	jsoup-1.8.3.jar
JSON的处理：	fastjson-1.2.6.jar
日志：	    logback-classic-1.1.3.jar

### 各包说明：
com.bigdata.spitter.main		程序入口，程序运行入口
com.bigdata.spitter				一些基础类
com.bigdata.spitter.featch		负责从互联网抓取页面
com.bigdata.spitter.processor	负责页面的解析
com.bigdata.spitter.scheduler   负责管理待抓取的URL，防止重复抓取
com.bigdata.spitter.storage	            解析信息的存储

### 配置：配置是通过resource文件夹下的config.properties文件配置，具体含义看其注解。logback.xml为日志配置

### 抓取信息：智联招聘的职位信息，例子：http://jobs.zhaopin.com/136251293251822.htm
具体信息：
	职位名称
	职位标签
	职位薪水
	职位月薪
	工作地点
	发布日期
	工作性质
	工作经验
	最低学历
	招聘人数
	 职位类别
             职位描述
            工作地址
             公司：公司名称、公司规模、公司性质、公司行业：互联网/电子商务公司、主页、公司地址、公司介绍
            
### 进度（持续更新）：暂时完成链接的抽取，下一步进行实时抓取



