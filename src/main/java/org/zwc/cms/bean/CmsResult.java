package org.zwc.cms.bean;

import java.io.Serializable;

/**
 * 封装cms的结果类
 * @author zhouwenchen
 */
public class CmsResult implements Serializable{
	private static final long serialVersionUID = 1L;
	private String status;
	private Object data;
	
	public CmsResult() {
	}
	
	public CmsResult(String status, Object data) {
		this.status = status;
		this.data = data;
	}

	public CmsResult successResult(Object data){
		return new CmsResult("success",data);
	}
	
	public CmsResult errorResult(Object data){
		return new CmsResult("error",data);
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "CmsResult [status=" + status + ", data=" + data + "]";
	}
}
