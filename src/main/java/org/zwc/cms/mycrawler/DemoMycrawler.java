package org.zwc.cms.mycrawler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.zwc.cms.bean.NewsInfo;
import org.zwc.cms.constant.CmsEnum;
import org.zwc.cms.mapper.NewsInfoMapper;
import org.zwc.cms.utils.CommentUtils;
import org.zwc.cms.utils.DocUtils;
import org.zwc.cms.utils.GetDoc;
import org.zwc.cms.utils.HttpClientUtilsForAppV2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.sh.crawlerbese.bean.IMG;
import cn.com.sh.crawlerbese.bean.PAGE;

/**
 * 爬虫，写入数据库中
 */
@Service
public class DemoMycrawler {
	
	public DemoMycrawler() {
	}
	
	private static ArrayList<String> arr = new ArrayList<String>();
	public static void main(String[] args) {
		DemoMycrawler.getAllVideos(null);
	}
	private static String[][] urls = {
//		{"http://www.mnw.cn/news/ent/ylxw/","娱乐","","mnw"},		//娱乐视频
//		{"https://www.huabian.com/api/photo/123/5/1/bto95hjvs","娱乐","","huabian"},		//花边娱乐	http://www.huabian.com/mingxing
		{"https://weixin.sogou.com/","资讯","","sogou"},		//搜狗微信，资讯分类
	};
	
	public static void getAllVideos(NewsInfoMapper newsInfoMapper) {
		arr.clear();
		for (int i = 0; i < urls.length; i++) {
			String urlfrom = urls[i][3];
			List<PAGE> pages = null;
			switch(urlfrom){
			case "mnw":
				 pages = getmnwPages(urlfrom,i);
				break;
			case "huabian":
				 pages = gethuabianPages(urlfrom,i);
				break;
			case "sogou":
				 pages = getsogouPages(urlfrom,i);
				break;
			default:
				break;
			}
			
			// 过滤和入库操作
			checkVisitedAndInsert(pages,newsInfoMapper);
		}
		
		try {
			System.out.println("=========睡眠时间==========");
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 检查过滤和准备入库操作
	 */
	private static void checkVisitedAndInsert(List<PAGE> pages,NewsInfoMapper newsInfoMapper) {
		for (PAGE page : pages) {
			try {
				String pageUrl = page.getUrl();
				NewsInfo newsInfo = newsInfoMapper.getNewsInfoByUrl(pageUrl);
				if(newsInfo!=null){
					System.out.println("======visited====="+pageUrl+"\t"+page.getTopic());
					continue;
				} else {
					newsInfo = new NewsInfo();
				}
				
				// 入库操作
				newsInfo.setId(null);
				newsInfo.setNewsId(String.valueOf(Long.MAX_VALUE-Long.parseLong(CommentUtils.builderRowKey(String.valueOf(System.currentTimeMillis()), "999999"))));
				newsInfo.setNewsName(page.getTopic());
				newsInfo.setNewsUrl(page.getUrl());
				newsInfo.setNewsSource(page.getSource());
				newsInfo.setNewsAuthor(page.getAuthor());
				newsInfo.setNewsStatus(CmsEnum.NEWSSTATUS_PASS);
				newsInfo.setNewsLook(CmsEnum.NEWSLOOK_OPEN);
				newsInfo.setIsShow(CmsEnum.ISSHOW_YES);

				Map<String, List<Map<String, Object>>> map = new HashMap<String,List<Map<String, Object>>>();
				List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
				List<IMG> imgs = page.getImgs();
				for (int i = 0; i < imgs.size(); i++) {
					Map<String, Object> imgMap = new HashMap<String,Object>();
					IMG img = imgs.get(i);
					System.out.println(img.getIdx()+"\t"+img.getSrc()+"\t"+img.getAlt());
					imgMap.put("idx", img.getIdx());
					imgMap.put("imgUrl", img.getSrc());
					imgMap.put("alt", img.getAlt());
					lists.add(imgMap);
				}
				map.put("imgUrls", lists);
				String imgUrls = JSON.toJSONString(map);
				
				newsInfo.setImgUrls(imgUrls);
				newsInfo.setCreatetime(new Date());
				newsInfo.setUpdatetime(new Date());
				newsInfo.setNewsContent(page.getContent());
				newsInfo.setIsCrawler(CmsEnum.ISCRAWLER_YES); // 爬取
				newsInfo.setIsDeteled(CmsEnum.ISDETELED_NO);  // 正常
				System.out.println(newsInfo);
				int i = newsInfoMapper.insertNewsInfo(newsInfo);
				System.out.println("插入成功数据：" + i);
			} catch (Exception e) {
				System.err.println("出现异常了===============");
				continue;
			}
		}
	}
	
	/**
	 * 搜狗微信
	 */
	private static List<PAGE> getsogouPages(String urlfrom, int i) {
		List<PAGE> pages = new ArrayList<PAGE>();
		Document doc = GetDoc.getdoc(urls[i][0], 3000, 0, 2);
		Elements elements_list = doc.select("#pc_0_0 > li");
		for (Element element : elements_list) {
			String pageUrl = element.select("div[class=txt-box] > h3 > a").attr("href");
			String title = element.select("div[class=txt-box] > h3").text();
			String source = element.select("a.account").text();
			String time = String.valueOf(System.currentTimeMillis());
			
			Document document = GetDoc.getbaseDoc(pageUrl);
			Elements elements = document.select("#js_content");
			// 3.正文内容
			ArrayList<IMG> imglist = new ArrayList<IMG>();
			DocUtils docUtils = new DocUtils();
			String content = docUtils.getcontent(elements.first(), pageUrl, 2, 1, imglist);
			
			if(pageUrl == null || title == null|| source == null|| time == null|| content == null){
				System.out.println("有数据为空："+pageUrl+"\t"+title+"\t"+source+"\t"+time+"\t"+content);
				continue;
			}
			setPage(pages, pageUrl, title, urls[i][3], time, imglist, content,source);
		}
		return pages;
	}

	/**
	 * 花边娱乐
	 */
	private static List<PAGE> gethuabianPages(String urlfrom, int i) {
		List<PAGE> pages = new ArrayList<PAGE>();
		String jsonStr = HttpClientUtilsForAppV2.get(urls[i][0]);
		JSONObject jsonObject = JSON.parseObject(jsonStr);
		JSONArray jsonObjectJArrs = jsonObject.getJSONObject("data").getJSONObject("info").getJSONArray("data");
		for(int j = 0; j < jsonObjectJArrs.size();j++){
			JSONObject JO = (JSONObject) jsonObjectJArrs.get(j);
			String pageUrl = JO.getString("url");
			String title = JO.getString("title");
			String source = JO.getString("username");
			String time = JO.getString("inputtime");
			if(pageUrl.indexOf("http:")== -1){
				pageUrl = "http:" + pageUrl;
			}
			Document document = GetDoc.getbaseDoc(pageUrl);
			Elements elements = document.select("body > div.hb_main > div.hb_main_l > div.hb_neirong > div.hb_content");
//			System.out.println(document);
			
			// 3.正文内容
			ArrayList<IMG> imglist = new ArrayList<IMG>();
			DocUtils docUtils = new DocUtils();
			String content = docUtils.getcontent(elements.first(), pageUrl, 2, 1, imglist);
			
			if(pageUrl == null || title == null|| source == null|| time == null|| content == null){
				System.out.println("有数据为空："+pageUrl+"\t"+title+"\t"+source+"\t"+time+"\t"+content);
				continue;
			}
			setPage(pages, pageUrl, title, urls[i][3], time, imglist, content,source);
		}
		return pages;
	}

	/**
	 * 
	 */
	private static List<PAGE> getmnwPages(String urlfrom, int i) {
		List<PAGE> pages = new ArrayList<PAGE>();
		Document doc = GetDoc.getdoc(urls[i][0], 3000, 0, 2);
		Elements elements2 = doc.select("body > div.iw.ic > div.l > dl > dd > div");
		for (Element element : elements2) {
			String pageUrl = element.select("a").attr("href");
			String title = element.select("a").text();
			
			Document document = GetDoc.getbaseDoc(pageUrl);
			Elements elements = document.select("body > div.iw.ic > div.l > div.ii > div.il");
			
			// 1.来源
			String source = elements.select("div > span:nth-child(1)").text();
			source = source.replace("来源:", "");
			// 2.时间
			String time = elements.select("div > span:nth-child(2)").text();
			
			// 3.正文内容
			Elements ContentEles = document.select("body > div.iw.ic > div.l > div.icontent");
			ArrayList<IMG> imglist = new ArrayList<IMG>();
			DocUtils docUtils = new DocUtils();
			String content = docUtils.getcontent(ContentEles.first(), pageUrl, 2, 1, imglist);
			
			if(pageUrl == null || title == null|| source == null|| time == null|| content == null){
				System.out.println("有数据为空："+pageUrl+"\t"+title+"\t"+source+"\t"+time+"\t"+content);
				continue;
			}
			
			setPage(pages, pageUrl, title, urls[i][3], time, imglist, content,source);
		}
		return pages;
	}
	
	/**
	 * 设置page的值
	 */
	private static PAGE setPage(List<PAGE> pages, String pageUrl, String title, String urlfrom, String time,
			ArrayList<IMG> imglist, String content,String source) {
		PAGE page = new PAGE();
		page.setUrl(pageUrl);
		page.setContenttitle(title);
		page.setTopic(title);
		page.setPagedate(time);
		page.setSource(urlfrom);
		page.setAuthor(source);
		page.setContent(content);
		page.setImgs(imglist);
		pages.add(page);
		return page;
	}
}
