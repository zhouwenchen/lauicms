package org.zwc.cms.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetDoc {
	
	/**
	 * 默认调用的方法
	 */
	public static Document getbaseDoc(String url) {
		return getdoc(url, 3000, 0, 2);
	}
	
	public static Document getdoc(String url, int timeout, int waittime,int retrytime) {
		Document Doc = null;
		for (int i = 0; i < retrytime; i++) {
			try {
				// 如果2次连接间隔不为0,则等待
				if (waittime > 0) {
					Thread.sleep(waittime);
				}
				HttpClientUtils http = new HttpClientUtils();
				String html = http.get(url);
				Doc = Jsoup.parse(html);
				if (Doc != null) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		return Doc;
	}
	
	
	public static Document getdoc4Chrome(String url, int timeout, int waittime, int retrytime) {
		Document Doc = null;
		for (int i = 0; i < retrytime; i++) {
			try {
				// 如果2次连接间隔不为0,则等待
				if (waittime > 0) {
					Thread.sleep(waittime);
				}
				Doc =	Jsoup.connect(url)
				.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36")
				.header("Upgrade-Insecure-Requests", "1")
				.header("Host", "")
				.header("Connection", "keep-alive")
				.header("Cache-Control", "max-age=0")
				.header("Accept-Language", "zh-CN,zh;q=0.8")
				.header("Accept-Encoding", "gzip, deflate, sdch")
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
				.get();
				if (Doc != null) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		return Doc;
	}
	
	public static Document getdoc2h5(String url, int timeout, int waittime, int retrytime) {
		Document Doc = null;
		for (int i = 0; i < retrytime; i++) {
			try {
				if (waittime > 0) {
					Thread.sleep(waittime);
				}
				HttpClientUtils http = new HttpClientUtils();
				String html = http.get4h5(url);
				Doc = Jsoup.parse(html);
				if (Doc != null) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		return Doc;
	}

}
