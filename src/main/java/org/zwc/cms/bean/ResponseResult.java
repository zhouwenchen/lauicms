package org.zwc.cms.bean;

import java.util.Map;

/**
 * 返回一个结果类
 * @author zhouwenchen
 */
public class ResponseResult {
	private Map<String, Object> map;
	private String msg;

	public ResponseResult() {
		super();
	}

	public ResponseResult(Map<String, Object> map, String msg) {
		super();
		this.map = map;
		this.msg = msg;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ResponseResult [map=" + map + ", msg=" + msg + "]";
	}

}
