package org.zwc.cms.service.impl;


import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zwc.cms.bean.CmsResult;
import org.zwc.cms.bean.NewsInfo;
import org.zwc.cms.bean.Param;
import org.zwc.cms.constant.CmsEnum;
import org.zwc.cms.mapper.NewsInfoMapper;
import org.zwc.cms.service.NewsInfoService;
import org.zwc.cms.utils.CommentUtils;

import com.alibaba.fastjson.JSONObject;

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
	
	/**
	 * 插入新闻数据
	 * @param newsInfo
	 * @return
	 */
	public int insertNewsInfo(String params){
		NewsInfo newsInfo = JSONObject.parseObject(params,NewsInfo.class);
		if(newsInfo == null){
			return 0;
		}
		newsInfo.setId(null);
		newsInfo.setNewsId(String.valueOf(Long.MAX_VALUE-Long.parseLong(CommentUtils.builderRowKey(String.valueOf(System.currentTimeMillis()), "999999"))));
		newsInfo.setNewsSource(newsInfo.getNewsAuthor());
		newsInfo.setImgUrls("");
		newsInfo.setNewsUrl(""); // 目前没有生成的数据
		newsInfo.setCreatetime(new Date());
		newsInfo.setUpdatetime(newsInfo.getCreatetime());
		newsInfo.setIsCrawler(CmsEnum.ISCRAWLER_NO);
		newsInfo.setIsCrawler(CmsEnum.ISDETELED_NO);
		int result = newsInfoMapper.insertNewsInfo(newsInfo);
		return result;
	}

	@Override
	public NewsInfo getNewsInfoByUrl(String newsUrl) {
		return null;
	}

	@Override
	public List<NewsInfo> getNewsInfosByWhere(NewsInfo newsInfo) {
		// 查询未删除的数据
		if(StringUtils.isEmpty(newsInfo.getIsDeteled())||"1".equals(newsInfo.getIsDeteled())){
			newsInfo.setIsDeteled(CmsEnum.ISDETELED_NO);
		}else {
			newsInfo.setIsDeteled(CmsEnum.ISDETELED_YES);
		}
		return newsInfoMapper.getNewsInfosByWhere(newsInfo);
	}

	/**
	 * 更新是否显示
	 */
	@Override
	public CmsResult updateNewsInfo(NewsInfo newsInfo) {
		CmsResult result = new CmsResult();
		// 1. 验证参数的合法性
		if(newsInfo.getId() == null){
			return result.errorResult("请求参数不合法，id为空");
		}
		// 2.根据id，查询数据
		NewsInfo queryNesInfo = new NewsInfo();
		queryNesInfo.setId(newsInfo.getId());
		List<NewsInfo> lists = newsInfoMapper.getNewsInfosByWhere(queryNesInfo);
		if(lists.size() != 1){
			return result.errorResult("根据id，查询newsInfo的信息不唯一");
		}
		// 3.更新是否显示的状态
		NewsInfo updateNewsInfo = lists.get(0);
		if("true".equals(newsInfo.getIsShow())){
			updateNewsInfo.setIsShow("checked");
		}else if("false".equals(newsInfo.getIsShow()) || "".equals(newsInfo.getIsShow())){
			updateNewsInfo.setIsShow("");
		}
		
		// 3.1审核通过
		updateNewsInfo.setNewsStatus(newsInfo.getNewsStatus());
		updateNewsInfo.setNewsContent(newsInfo.getNewsContent());
		updateNewsInfo.setNewsLook(newsInfo.getNewsLook());
		updateNewsInfo.setNewsName(newsInfo.getNewsName());
		updateNewsInfo.setNewsAuthor(newsInfo.getNewsAuthor());
		updateNewsInfo.setNewsSource(updateNewsInfo.getNewsAuthor());
		updateNewsInfo.setCreatetime(newsInfo.getCreatetime());
		updateNewsInfo.setIsDeteled(newsInfo.getIsDeteled());
		updateNewsInfo.setUpdatetime(new Date());
		
		int resultInt = newsInfoMapper.updateNewsInfo(updateNewsInfo);
		if(resultInt!=0){
			return result.successResult("更新成功");
		}
		
		return result.errorResult("更新失败");
	}

	@Override
	public List<NewsInfo> getNewInfoByStartEnd(Param param) {
		return newsInfoMapper.getNewInfoByStartEnd(param);
	}
}