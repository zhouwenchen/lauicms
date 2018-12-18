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
import com.alibaba.fastjson.JSON;
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
		DemoMycrawler.getAllVideos(null,"mnw");
	}
	private static String[][] urls = {
		{"http://www.mnw.cn/news/ent/ylxw/","娱乐","","mnw"},//娱乐视频
	};
	
	public static void getAllVideos(NewsInfoMapper newsInfoMapper,String urlfrom1) {
		arr.clear();
		for (int i = 0; i < urls.length; i++) {
			String urlfrom = urls[i][3];
			List<PAGE> pages = null;
			switch(urlfrom){
			case "mnw":
				 pages = getmnwPages(urlfrom,i);
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
			newsInfo.setNewsAuthor(page.getSource());
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
			System.out.println(newsInfo);
			int i = newsInfoMapper.insertNewsInfo(newsInfo);
			System.out.println("插入成功数据：" + i);
		}
	}

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
			PAGE page = new PAGE();
			page.setUrl(pageUrl);
			page.setContenttitle(title);
			page.setTopic(title);
			page.setPagedate(time);
			page.setSource(source);
			page.setAuthor(source);
			page.setContent(content);
			page.setImgs(imglist);
			pages.add(page);
		}
		return pages;
	}
}
