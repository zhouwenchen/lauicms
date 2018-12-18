package org.zwc.cms.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zwc.cms.bean.NewsInfo;
import org.zwc.cms.service.NewsInfoService;
import org.zwc.cms.vo.NewsInfoVo;

/**
 * 新闻信息的Controller
 * 
 * @author zhouwenchen
 * @date 2018年12月18日 下午5:03:20
 */
@RestController
@RequestMapping("/newsInfo")
public class NewsInfoController {

	@Autowired
	private NewsInfoService newsInfoService;
	
	@RequestMapping("/{newsId}")
	public List<NewsInfo> getNewsInfosByWhere(@PathVariable(name = "newsId") String newsId) {
		NewsInfo newsInfo = new NewsInfo();
		// 1.验证参数的合法性
		if(StringUtils.isEmpty(newsId)){
			return null;
		}
		newsInfo.setNewsId(newsId);
		return newsInfoService.getNewsInfosByWhere(newsInfo);
	}
	
	@RequestMapping("/getAllNewsInfo")
	public List<NewsInfoVo> getNewsInfosAll(@RequestParam(name="keys",defaultValue="") String keys){
		System.out.println("keys:\t"+keys);
		NewsInfo queryNewsInfo = new NewsInfo();
		queryNewsInfo.setNewsName(keys);
		List<NewsInfo> lists = newsInfoService.getNewsInfosByWhere(queryNewsInfo);
		List<NewsInfoVo> newsInfoVos = new ArrayList<NewsInfoVo>();
		for (NewsInfo  newsInfo: lists) {
			NewsInfoVo newsInfoVo = new NewsInfoVo();
			BeanUtils.copyProperties(newsInfo, newsInfoVo);
			newsInfoVo.setNewsTime(newsInfo.getCreatetime());
			newsInfoVos.add(newsInfoVo);
		}
		return newsInfoVos;
	}
	
}
