package org.zwc.cms.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zwc.cms.mycrawler.DemoMycrawler;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoMycrawlerTest {
	
	@Autowired
	private NewsInfoMapper newsInfoMapper;
	
	/**
	 * 启动爬取数据的入口
	 */
	@Test
	public void mycrawler() {
		DemoMycrawler.getAllVideos(newsInfoMapper,"mnw");
	}
}
