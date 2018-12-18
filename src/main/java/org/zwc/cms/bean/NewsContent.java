package org.zwc.cms.bean;

import java.util.Date;

/**  
 * 新闻的内容和图片的信息
* @author zhouwenchen
* @date 2018年12月17日 下午5:24:42 
*/  
public class NewsContent {
	/** 主键Id*/
	private Long id;
	/** 新闻Id*/
	private Long newsId;
	/** 标题*/
	private String newsName;
	/** url*/
	private String newsUrl;
	/** 来源*/
	private String newsSource;
	/** 作者*/
	private String newsAuthor;
	/** 新闻内容*/
	private String newsContent;
	/** 新闻图片*/
	private String imgUrls;
	/** 创建时间*/
	private Date createtime;
	/** 更新时间*/
	private Date updatetime;
	
	public NewsContent() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}

	public String getNewsName() {
		return newsName;
	}

	public void setNewsName(String newsName) {
		this.newsName = newsName;
	}

	public String getNewsUrl() {
		return newsUrl;
	}

	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}

	public String getNewsSource() {
		return newsSource;
	}

	public void setNewsSource(String newsSource) {
		this.newsSource = newsSource;
	}

	public String getNewsAuthor() {
		return newsAuthor;
	}

	public void setNewsAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	@Override
	public String toString() {
		return "NewsContent [id=" + id + ", newsId=" + newsId + ", newsName=" + newsName + ", newsUrl=" + newsUrl
				+ ", newsSource=" + newsSource + ", newsAuthor=" + newsAuthor + ", newsContent=" + newsContent
				+ ", imgUrls=" + imgUrls + ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}
}
