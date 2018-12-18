package org.zwc.cms.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.zwc.cms.bean.NewsContent;

/**  
 * 新闻内容和图片的详细信息
* @author zhouwenchen
* @date 2018年12月17日 上午10:53:51 
*/  
@Mapper
public interface NewsContentMapper {
    
	/**
	 * 查询所有的数据
	 * @return 所有的数据
	 */
    public List<NewsContent> findAll();
    
    /**
     * 根据newsId查询新闻的具体信息
     * @param id
     * @return
     */
	public NewsContent getNewsContentByNewsId(int id);
	
	/**
	 * 插入新闻数据
	 * @param newsInfo
	 * @return
	 */
	public int insertNewsContent(NewsContent newsContent);
}