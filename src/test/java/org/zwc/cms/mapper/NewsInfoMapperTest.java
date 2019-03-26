package org.zwc.cms.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zwc.cms.bean.NewsInfo;
import org.zwc.cms.bean.Param;
import org.zwc.cms.constant.CmsEnum;
import org.zwc.cms.service.NewsInfoService;
import org.zwc.cms.utils.CommentUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NewsInfoMapperTest {
	
	@Autowired
	private NewsInfoMapper newsInfoMapper;
	
	@Autowired
	private NewsInfoService newsInfoService;
	
	@Before
	public void Before(){
		System.out.println("提前执行了。。。。。");
	}
	
	@Test
	public void test() {
		List<NewsInfo> lists = newsInfoMapper.findAll();
		for (NewsInfo newsInfo : lists) {
			System.out.println(newsInfo);
		}
	}
	
	@Test
	public void insertNewsInfo(){
		NewsInfo newsInfo = new NewsInfo();
		newsInfo.setId(null);
		newsInfo.setNewsId(String.valueOf(Long.MAX_VALUE-Long.parseLong(CommentUtils.builderRowKey(String.valueOf(System.currentTimeMillis()), "999999"))));
		newsInfo.setNewsName("文臣新的一天");
		newsInfo.setNewsUrl("http://www.mnw.cn/news/shehui/2099764.html");
		newsInfo.setNewsSource("zhouwenchen");
		newsInfo.setNewsAuthor("zhouwenchen");
		newsInfo.setNewsStatus(CmsEnum.NEWSSTATUS_PASS);
		newsInfo.setNewsLook(CmsEnum.NEWSLOOK_OPEN);
		newsInfo.setIsShow(CmsEnum.ISSHOW_YES);

		Map<String, List<String>> map = new HashMap<String,List<String>>();
		List<String> lists = new ArrayList<>();
		lists.add("http://moviepic.manmankan.com/yybpic/yanyuan/pic/5600/300044_s.jpg");
		lists.add("http://moviepic.manmankan.com/yybpic/yanyuan/pic/5600/117863_s.jpg");
		map.put("imgUrls", lists);
		String imgUrls = JSON.toJSONString(map);
		
		newsInfo.setImgUrls(imgUrls);
		newsInfo.setCreatetime(new Date());
		newsInfo.setUpdatetime(new Date());
		newsInfo.setNewsContent("!@#!@　　2018年流行的网络用语“小奶狗”、“小狼狗”大火，这两类男生也成了大多数女生的择偶标准。小奶狗，是指年纪小、单纯、好调教、粘人的男生；小狼狗，是形容那些长相帅气、年纪小、很酷且控制欲强的男生。近日，女生的择偶标准似乎又发生了新的变化。随着歌曲《小叔叔》的上线，歌曲中提到的小叔叔：温柔细腻随叫随到，不矫情不玻璃心不大男子主义，浑身散发成熟男人的味道。拥有此类特征的“小叔叔”迅速俘获了大批90后、00后女生的芳心，成为她们最新的择偶标准。!@#!@$#imgidx=0001#$!@#!@《小叔叔》原唱在线征婚首唱会趣味打歌引关注!@#!@　　近日，北京现代音乐研修学院战队选手王欣茹、地里亚尔在酷狗直播间为《小叔叔》打歌，这首歌是导师张杰亲自指定、由酷狗官方为王欣茹量身打造的歌曲。高甜的歌词加上王欣茹的完美诠释使得整首歌曲甜而不腻，少女心满满。直播中，同战队的选手地里亚尔成为本首歌的rap担当，他独特的民族唱腔也为歌曲平添一丝别样的韵味。!@#!@　　唱歌间隙，王欣茹与大家亲密地聊天互动，甚至把妈妈发给自己的“催婚”语音放给大家听。很多观众感同身受，纷纷留言：“同一个世界同一个妈”、“你这么小就被催找对象了，原来我不是一个人”等等。乖巧听话的王欣茹也借首唱会的机会在线征婚，希望自己能够找到像“小叔叔”这样的男朋友，年龄20-25岁，出得厅堂入得厨房，温柔的钢铁直男。她还坦言自己是“婆婆都喜欢的类型”，很多观众被她诚恳又可爱的样子逗乐了，直播间的气氛也变得更为活跃。!@#!@$#imgidx=0002#$!@#!@“送命题”标准答案奉上 “小叔叔”甜暖开嗓!@#!@　　“当你的女朋友和你一起玩游戏，因操作太烂而将游戏手柄摔在地上时，你会？”“上去把游戏柄踩烂，这破游戏柄怎么能影响我宝贝儿的发挥！”王欣茹准备的“送命题”被地里亚尔求生欲满满的回答完美破解，直播间观众也忍不住狂刷礼物，纷纷留言“学到了”“请地里亚尔出份教程吧！”看来要成为小叔叔并没有那么简单，还得多向温暖系“小叔叔”-地里亚尔多多学习！!@#!@　　地里亚尔在直播中用吉他弹唱的《一见钟情》给人留下了深刻的印象。吉他的清脆音色搭配地里亚尔动人的嗓音透出丝丝温暖。独特的西域唱腔、深情干净歌声令人十分惊艳，他不经意间流露出的少年感与成熟感兼具的气质更是吸粉无数。!@#!@　　一直以来，北京现代音乐研修学院战队都凭借着自己超强的专业素养为大家不断地带来惊喜。1月5日，酷狗校际音超联赛巅峰争霸赛将在北京开战，五强战队：北京现代音乐研修学院、浙江音乐学院、四川音乐学院、武汉音乐学院、重庆大学也在为了最终的角逐紧张备战中。实力强劲，发挥稳定的北京现代音乐研修学院战队究竟能否成为本届酷狗校际音超联赛最终赢家，夺得总冠军的桂冠呢？让我们一起拭目以待！原标题：95后大学生直播被催婚 “小叔叔”成最新择偶标准责任编辑：柯金定");
		int i = newsInfoMapper.insertNewsInfo(newsInfo);
		System.out.println("插入成功数据：" + i);
	}
	
	@Test
	public void getNewsInfoByUrlTest(){
		NewsInfo newsInfo = newsInfoMapper.getNewsInfoByUrl("http://www.mnw.cn/news/shehui/2099764.html");
		if(newsInfo!=null){
			System.out.println(newsInfo);
		}
	}
	
	@Test
	public void jsonTest(){
		Map<String, List<Map<String, Object>>> map = new HashMap<String,List<Map<String, Object>>>();
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		Map<String, Object> imgMap1 = new HashMap<String,Object>();
		imgMap1.put("idx", 1);
		imgMap1.put("imgUrl", "http://moviepic.manmankan.com/yybpic/yanyuan/pic/5600/300044_s.jpg");
		
		
		Map<String, Object> imgMap2 = new HashMap<String,Object>();
		imgMap2.put("idx", 2);
		imgMap2.put("imgUrl", "http://moviepic.manmankan.com/yybpic/yanyuan/pic/5600/117863_s.jpg");
		lists.add(imgMap1);
		lists.add(imgMap2);
		map.put("imgUrls", lists);
		String string = JSON.toJSONString(map);
		System.out.println(string);
//		System.out.println(System.currentTimeMillis());
//		System.out.println(Long.MAX_VALUE-Long.parseLong(CommentUtils.builderRowKey(String.valueOf(System.currentTimeMillis()), "999999")));
	}
	
	/**
	 * 根据条件查询
	 */
	@Test
	public void getNewsInfosByWhereTest(){
		NewsInfo queryNewInfo = new NewsInfo();
//		queryNewInfo.setNewsLook("开放浏览");
//		queryNewInfo.setIsShow("否");
//		queryNewInfo.setNewsName("我不是药神");
		List<NewsInfo> newsInfos = newsInfoMapper.getNewsInfosByWhere(queryNewInfo);
		for (NewsInfo newsInfo : newsInfos) {
			System.out.println(newsInfo);
		}
	}
	
	@Test
	public void updateNewsInfoTest(){
		NewsInfo newsInfo = new NewsInfo();
		newsInfo.setId(1L);
		newsInfo.setIsShow(CmsEnum.ISSHOW_YES);
		newsInfo.setNewsStatus(CmsEnum.NEWSSTATUS_REFUSE);
		newsInfo.setUpdatetime(new Date());
		int rows = newsInfoMapper.updateNewsInfo(newsInfo);
		System.out.println(rows);
	}
	
	@Test
	public void addNewsInfo_JSON2Obj(){
		String str = "{'newsName':'惊人！是我的没有错','newsLook':'会员浏览','newsTime':'2018-12-19','newsAuthor':'周文臣','newsContent':'<p>Fastjson是一个Java语言编写的JSON处理器,由阿里巴巴公司开发。<br><br>1、遵循http://json.org标准，为其官方网站收录的参考实现之一。<br><br>2、功能qiang打，支持JDK的各种类型，包括基本的JavaBean、Collection、Map、Date、Enum、泛型。<br><br>3、无依赖，不需要例外额外的jar，能够直接跑在JDK上。<br><br>4、开源，使用Apache License 2.0协议开源。http://code.alibabatech.com/wiki/display/FastJSON/Home<br>--------------------- <br>作者：jilongliang <br>来源：CSDN <br>原文：https://blog.csdn.net/jilongliang/article/details/42870951 <br>版权声明：本文为博主原创文章，转载请附上博文链接！<br></p>','isShow':'checked','newsStatus':'审核通过'}";
		NewsInfo newsInfo = JSONObject.parseObject(str,NewsInfo.class);
		System.out.println(newsInfo);
//		JSONArray jsonArray = JSONObject.parseArray(str);
//		for(int i = 0; i < jsonArray.size();i++){
//			JSONObject object = jsonArray.getJSONObject(i);
//			System.out.println(object);
//		}
		int info = newsInfoService.insertNewsInfo(str);
		System.out.println("添加文章"+info);
	}
	
	/**
	 * 根据source
	 * 开始和结束时间查询数据
	 */
	@Test
	public void getNewInfoByStartEnd(){
		Param param = new Param();
		param.setpStr01("sogou");
		param.setpStr02("20190301");
		param.setpStr03("20190320");
		List<NewsInfo> newsInfos = newsInfoService.getNewInfoByStartEnd(param);
		for (NewsInfo newsInfo : newsInfos) {
			System.out.println(newsInfo);
		}
	}
}
