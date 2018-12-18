package org.zwc.cms.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zwc.cms.bean.NewsInfo;
import org.zwc.cms.mapper.NewsInfoMapper;
import org.zwc.cms.service.NewsInfoService;

@Service
public class NewInfoServiceImpl implements NewsInfoService{
    
	@Autowired
	private NewsInfoMapper newsInfoMapper;

	@Override
	public List<NewsInfo> findAll() {
		return null;
	}

	@Override
	public NewsInfo getNewsInfoByNewsId(int newsId) {
		return null;
	}

	@Override
	public int insertNewsInfo(NewsInfo newsInfo) {
		return 0;
	}

	@Override
	public NewsInfo getNewsInfoByUrl(String newsUrl) {
		return null;
	}

	@Override
	public List<NewsInfo> getNewsInfosByWhere(NewsInfo newsInfo) {
		return newsInfoMapper.getNewsInfosByWhere(newsInfo);
	}
	
	
}