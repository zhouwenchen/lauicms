package org.zwc.cms.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zwc.cms.bean.CmsResult;
import org.zwc.cms.bean.NewsInfo;
import org.zwc.cms.service.NewsInfoService;
import org.zwc.cms.vo.NewsInfoVo;

import com.alibaba.fastjson.JSONObject;

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
	
	/**
	 * 根据newId查询数据
	 * @param newsId
	 * @return
	 */
	@RequestMapping("/getNewsInfoByWhere")
	public List<NewsInfo> getNewsInfosByWhere(NewsInfo newsInfo) {
		// 1.验证参数的合法性
		if(newsInfo == null){
			return null;
		}
		System.out.println(newsInfo.getId());
//		newsInfo.setNewsId(newsId);
//		newsInfo.setId(id);
		return newsInfoService.getNewsInfosByWhere(newsInfo);
	}
	
	/**
	 * 查询所有的新闻列表的数据
	 */
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
	
	/**
	 * 根据条件更新操作(是否展示)
	 * @param newsInfo
	 * @return
	 */
	@RequestMapping("/update")
	public CmsResult updateNewsInfoIsShow(NewsInfo newsInfo){
		System.out.println(newsInfo);
		return newsInfoService.updateNewsInfo(newsInfo);
	}
	
	/**
	 * 添加文章
	 */
	@RequestMapping("/addNewsInfo")
	public CmsResult addNewsInfo(@RequestParam("params")String params){
		System.out.println(params);
		int result = newsInfoService.insertNewsInfo(params);
		CmsResult cmsResult = new CmsResult();
		if(result != 0){
			return cmsResult.successResult(result);
		}
		return cmsResult.errorResult("添加文章出现异常");
	}
	
	/**
	 * 更新NewsInfo的数据
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/updateNewsInfo",method = RequestMethod.POST)
	public CmsResult updateNewsInfo(@RequestParam("params")String params){
		CmsResult cmsResult = new CmsResult();
		try {
			NewsInfo newsInfo = JSONObject.parseObject(params,NewsInfo.class);
			cmsResult = newsInfoService.updateNewsInfo(newsInfo);
			if(cmsResult.getStatus()=="success"){
				return cmsResult;
			}
			return cmsResult.errorResult("添加文章出现异常");
		} catch (Exception e) {
			e.printStackTrace();
			return cmsResult.errorResult("添加文章出现异常");
		}
	}
	
}
