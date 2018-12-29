package org.zwc.cms.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.zwc.cms.bean.NewsInfo;

/**  
 * 获取到新闻列表的数据
* @author zhouwenchen
* @date 2018年12月17日 上午10:53:51 
*/  
@Mapper
public interface NewsInfoMapper {
    
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
	public int updateNewsInfo(NewsInfo newsInfo);
}