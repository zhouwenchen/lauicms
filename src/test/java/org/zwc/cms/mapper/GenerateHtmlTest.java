package org.zwc.cms.mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zwc.cms.bean.NewsInfo;
import org.zwc.cms.utils.GenerateHtml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.sh.crawlerbese.bean.IMG;
import cn.com.sh.crawlerbese.bean.PAGE;

/**  
 * 生成页面的测试
 * @author zhouwenchen
 * @date 2018年12月26日 下午6:10:10 
 */  
@SpringBootTest
@RunWith(SpringRunner.class)
public class GenerateHtmlTest {
	
	@Autowired
	private NewsInfoMapper newsInfoMapper;
	
	@Before
	public void Before(){
		System.out.println("提前执行了。。。。。");
	}
	
	@Test
	public void test() {
		List<NewsInfo> lists = newsInfoMapper.findAll();
		for (NewsInfo newsInfo : lists) {
			System.out.println(newsInfo);
		}
	}
	
	@Test
	public void generateHtmlOneByid(){
		GenerateHtml html = new GenerateHtml();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		NewsInfo newsInfo = new NewsInfo();
		newsInfo.setId(144L);
		List<NewsInfo> newsInfos = newsInfoMapper.getNewsInfosByWhere(newsInfo);
		for (NewsInfo news : newsInfos) {
			PAGE page = new PAGE();
			page.setContenttitle(news.getNewsName());
			page.setPagedate(dateFormat.format(news.getCreatetime()));
			page.setSource(news.getNewsSource());
			page.setUrl(news.getNewsUrl());
			page.setContent(news.getNewsContent());
			
			String imgUrls = news.getImgUrls();
			List<IMG> imgs = new ArrayList<IMG>();
			if(StringUtils.isNotEmpty(imgUrls)){
				JSONObject imgUrlsObj = JSON.parseObject(imgUrls);
				JSONArray imgUrlsJArrs = imgUrlsObj.getJSONArray("imgUrls");
				for (int j = 0; j < imgUrlsJArrs.size(); j++) {
					IMG img = new IMG();
					JSONObject JO = imgUrlsJArrs.getJSONObject(j);
					String imgUrl = JO.getString("imgUrl");
					String idx = JO.getString("idx");
					
					img.setSrc(imgUrl);
					img.setIdx(Integer.parseInt(idx));
					imgs.add(img);
				}
			}
			page.setImgs(imgs);
			String newsId = news.getNewsId();
			html.generateHTML(newsId, page);
		}
	}
}
