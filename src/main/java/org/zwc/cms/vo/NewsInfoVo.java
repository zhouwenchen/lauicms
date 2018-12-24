package org.zwc.cms.vo;

import java.io.Serializable;
import java.util.Date;

import org.zwc.cms.bean.NewsInfo;

import com.fasterxml.jackson.annotation.JsonFormat;

public class NewsInfoVo extends NewsInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date newsTime;
	public Date getNewsTime() {
		return newsTime;
	}
	public void setNewsTime(Date newsTime) {
		this.newsTime = newsTime;
	}
}
