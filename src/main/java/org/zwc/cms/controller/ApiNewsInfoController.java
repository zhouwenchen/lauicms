package org.zwc.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zwc.cms.bean.NewsInfo;
import org.zwc.cms.bean.Param;
import org.zwc.cms.service.NewsInfoService;

/** 
 * 对外提供接口的api
 * @author zhouwenchen
 * @date 2019年3月22日 下午8:19:18 
 */  
@RestController
@RequestMapping("/apiNewsInfo")
public class ApiNewsInfoController {
	@Autowired
	private NewsInfoService newsInfoService;
	
	@RequestMapping("/getNewInfoByStartEnd")
	public List<NewsInfo> getNewInfoByStartEnd(String startdate,String enddate) {
		Param param = new Param();
		param.setpStr01("sogou");
		param.setpStr02(startdate);
		param.setpStr03(enddate);
		List<NewsInfo> newsInfos = newsInfoService.getNewInfoByStartEnd(param);
		return newsInfos;
	}
}
