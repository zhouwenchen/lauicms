package org.zwc.cms.bean;

import java.util.Date;

/**  
* @author zhouwenchen
* @date 2018年12月17日 上午10:47:55 
*/  
public class NewsInfo {
	
	/** 主键Id*/
	private Long id;
	/** 新闻Id*/
	private String newsId;
	/** 标题*/
	private String newsName;
	/** Url*/
	private String newsUrl;
	/** 来源*/
	private String newsSource;
	/** 作者*/
	private String newsAuthor;
	/** 审核状态(审核通过)(待审核)*/
	private String newsStatus;
	/** 权限*/
	private String newsLook;
	/** 图片地址*/
	private String imgUrls;
	/** 是否显示*/
	private String isShow;
	/** 创建时间*/
	private Date createtime;
	/** 更新时间*/
	private Date updatetime;
	/** 新闻内容*/
	private String newsContent;
	/** 是否是爬取*/
	private String isCrawler;
	
	public NewsInfo() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getNewsUrl() {
		return newsUrl;
	}

	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}

	public String getNewsName() {
		return newsName;
	}

	public void setNewsName(String newsName) {
		this.newsName = newsName;
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

	public String getNewsStatus() {
		return newsStatus;
	}

	public void setNewsStatus(String newsStatus) {
		this.newsStatus = newsStatus;
	}

	public String getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}

	public String getNewsLook() {
		return newsLook;
	}

	public void setNewsLook(String newsLook) {
		this.newsLook = newsLook;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
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

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	
	public String getIsCrawler() {
		return isCrawler;
	}

	public void setIsCrawler(String isCrawler) {
		this.isCrawler = isCrawler;
	}

	@Override
	public String toString() {
		return "NewsInfo [id=" + id + ", newsId=" + newsId + ", newsName=" + newsName + ", newsUrl=" + newsUrl
				+ ", newsSource=" + newsSource + ", newsAuthor=" + newsAuthor + ", newsStatus=" + newsStatus
				+ ", newsLook=" + newsLook + ", imgUrls=" + imgUrls + ", isShow=" + isShow + ", createtime="
				+ createtime + ", updatetime=" + updatetime + ", newsContent=" + newsContent + ", isCrawler="
				+ isCrawler + "]";
	}
}
