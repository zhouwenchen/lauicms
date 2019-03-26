package org.zwc.cms.service;

import java.util.List;

import org.zwc.cms.bean.CmsResult;
import org.zwc.cms.bean.NewsInfo;
import org.zwc.cms.bean.Param;

/**
 * 
 * @author zhouwenchen
 * @date 2018年12月18日 下午5:05:34
 */
public interface NewsInfoService {
	/**
	 * 查询所有的数据
	 * @return 所有的数据
	 */
    public List<NewsInfo> findAll();
    
    /**
     * 根据newsId查询新闻的具体信息
     * @param id
     * @return
     */
	public NewsInfo getNewsInfoByNewsId(int newsId);
	
	/**
	 * 插入新闻数据
	 * @param newsInfo
	 * @return
	 */
	public int insertNewsInfo(NewsInfo newsInfo);
	/**
	 * 插入新闻数据
	 * @param newsInfo
	 * @return
	 */
	public int insertNewsInfo(String params);
	
	/**
	 * 根据url查询数据
	 * @param url
	 * @return
	 */
	public NewsInfo getNewsInfoByUrl(String newsUrl);
	
	/**
     * 根据条件查询满足条件的数据
     * @param id
     * @return
     */
	public List<NewsInfo> getNewsInfosByWhere(NewsInfo newsInfo);
	
	/**
	 * 更新操作
	 * @param newsInfo
	 */
	public CmsResult updateNewsInfo(NewsInfo newsInfo);
	
	/**
	 * 根据来源，开始和结束时间查询
	 * @param param
	 */
	public List<NewsInfo> getNewInfoByStartEnd(Param param);
}