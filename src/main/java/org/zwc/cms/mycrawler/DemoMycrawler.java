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
import org.zwc.cms.utils.GetMainType;
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
<<<<<<< HEAD
		{"https://newsapi.yiche.com/appnews/news/list/original?page=1&publishtime=&size=20","汽车","","yiche"},		//花边娱乐	http://www.huabian.com/mingxing
=======
		{"https://weixin.sogou.com/","资讯","","sogou"},		//搜狗微信，资讯分类
>>>>>>> deee6947a4a91c4427bb854ef07f40649a1bd7bf
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
<<<<<<< HEAD
			case "yiche":
				 pages = getyichePages(urlfrom,i);
=======
			case "sogou":
				 pages = getsogouPages(urlfrom,i);
>>>>>>> deee6947a4a91c4427bb854ef07f40649a1bd7bf
				break;
			default:
				break;
			}
			
			// 过滤和入库操作
			checkVisitedAndInsert(pages,newsInfoMapper);
		}
		
		try {
			System.out.println("=========睡眠时间==========");
			Thread.sleep(100);
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
<<<<<<< HEAD
				newsInfo.setNewsAuthor(page.getSource());
				newsInfo.setNewsStatus(CmsEnum.NEWSSTATUS_WAIT_PASS);// 待审核
				newsInfo.setNewsLook(CmsEnum.NEWSLOOK_OPEN); 		// 开放浏览
				newsInfo.setIsShow(CmsEnum.ISSHOW_YES); 			// 是否显示
				newsInfo.setNewsType(GetMainType.getMain(page.getTp1st())); // 主分类
=======
				newsInfo.setNewsAuthor(page.getAuthor());
				newsInfo.setNewsStatus(CmsEnum.NEWSSTATUS_PASS);
				newsInfo.setNewsLook(CmsEnum.NEWSLOOK_OPEN);
				newsInfo.setIsShow(CmsEnum.ISSHOW_YES);
>>>>>>> deee6947a4a91c4427bb854ef07f40649a1bd7bf

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
	 * 易车APP
	 */
	private static List<PAGE> getyichePages(String urlfrom, int i) {
		String baseyicheUrl = "http://news.m.yiche.com";
		String basePageDetailUrl = "https://newsapi.yiche.com/appnews/news/show?";
		List<PAGE> pages = new ArrayList<PAGE>();
		String jsonStr = HttpClientUtilsForAppV2.get(urls[i][0]);
		JSONObject jsonObject = JSON.parseObject(jsonStr);
		System.out.println(jsonStr);
		JSONArray jsonObjectJArrs = jsonObject.getJSONObject("data").getJSONArray("list");
		for(int j = 0; j < jsonObjectJArrs.size();j++){
			JSONObject JO = (JSONObject) jsonObjectJArrs.get(j);
			String pageUrl = baseyicheUrl + JO.getString("url");
			String title = JO.getString("title");
			String source = JO.getJSONObject("user").getString("showname");
			String time = JO.getString("publishTime");
			
			String type = JO.getString("type");
			String id = JO.getString("id");
			
			
			
			// 获取正文内容
			String pageDetailUrl = basePageDetailUrl + "type="+ type +"&id=" + id;
			String jsonDetailStr = HttpClientUtilsForAppV2.get(pageDetailUrl);
			System.out.println(jsonDetailStr);
			JSONObject jsonDetailJO = JSON.parseObject(jsonDetailStr);
			JSONArray contents = jsonDetailJO.getJSONObject("data").getJSONArray("content");
			StringBuffer sb = new StringBuffer();
			
			String contentTag = "!@#!@";
			// 1. 开始标签
			sb.append(contentTag+"\t");
			ArrayList<IMG> imglist = new ArrayList<IMG>();
			int idx = 1;
			for(int m = 0; m < contents.size();m++){
				JSONObject content = contents.getJSONObject(m);
				String content_t = content.getString("type");
				String content_c = content.getString("content");
				if("9".equals(content_t)){
					continue;
				}
				// 2.正文内容
				if("1".equals(content_t)){
					sb.append(content_c).append(contentTag);
				}
				
				// 3.图片
				if("2".equals(content_t)){
					IMG img = new IMG();
					String imgidex = "000"+String.valueOf(idx);
					sb.append("$#imgidx="+imgidex+"#$").append(contentTag+"\t");
					img.setSrc(content_c);
					img.setIdx(idx);
					imglist.add(img);
					idx++;
				}
			}
			
			if(pageUrl == null || title == null|| source == null|| time == null|| sb.toString() == null){
				System.out.println("有数据为空："+pageUrl+"\t"+title+"\t"+source+"\t"+time+"\t"+sb.toString());
				continue;
			}
			setPage(pages, pageUrl, title, source, time, imglist, sb.toString(),urls[i][1]);
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
<<<<<<< HEAD
			setPage(pages, pageUrl, title, source, time, imglist, content,urls[i][1]);
=======
			setPage(pages, pageUrl, title, urls[i][3], time, imglist, content,source);
>>>>>>> deee6947a4a91c4427bb854ef07f40649a1bd7bf
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
			
<<<<<<< HEAD
			setPage(pages, pageUrl, title, source, time, imglist, content,urls[i][1]);
=======
			setPage(pages, pageUrl, title, urls[i][3], time, imglist, content,source);
>>>>>>> deee6947a4a91c4427bb854ef07f40649a1bd7bf
		}
		return pages;
	}
	
	/**
	 * 设置page的值
	 */
<<<<<<< HEAD
	private static PAGE setPage(List<PAGE> pages, String pageUrl, String title, String source, String time,
			ArrayList<IMG> imglist, String content,String mainType) {
=======
	private static PAGE setPage(List<PAGE> pages, String pageUrl, String title, String urlfrom, String time,
			ArrayList<IMG> imglist, String content,String source) {
>>>>>>> deee6947a4a91c4427bb854ef07f40649a1bd7bf
		PAGE page = new PAGE();
		page.setUrl(pageUrl);
		page.setContenttitle(title);
		page.setTopic(title);
		page.setPagedate(time);
		page.setSource(urlfrom);
		page.setAuthor(source);
		page.setContent(content);
		page.setImgs(imglist);
		page.setTp1st(mainType);
		pages.add(page);
		return page;
	}
}
