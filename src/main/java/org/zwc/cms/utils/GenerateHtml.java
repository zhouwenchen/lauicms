package org.zwc.cms.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.sh.crawlerbese.bean.PAGE;

/**
 * 生成一个html的页面
 * @author zwc
 */
public class GenerateHtml {
	  static String htmlStart = "<html>";
	  static String htmlEnd = "</html>";
	
	 public static void generateHTML(String rowkey, PAGE page) {
		    String title = "<h2>"+page.getContenttitle()+"</h2>";//文章标题
		    String date = page.getPagedate();//获取到文章日期
		    if(date==null||date.equals("")) {
		      date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//如果没有日期，则用当前日期
//		      date = page.getPagedate();
		    } 
		    
		    String msg = "<div>"+date+"&nbsp"+page.getSource()+"</div>";
		    StringBuilder tempContent = new StringBuilder(page.getContent().replaceFirst("!@#!@", "<p>").replaceAll("!@#!@", "</p><p>"));
		    if(tempContent.lastIndexOf("<p>")==-1) {
		      return;
		    }
		    String contentNoImg = tempContent.replace(tempContent.lastIndexOf("<p>"), tempContent.lastIndexOf("<p>")+3, "").toString();
		    //加入图片标签
		    Pattern p = Pattern.compile("\\$#imgidx=0{0,3}(\\d+)#\\$");
		    Matcher m = p.matcher(contentNoImg);
		    while(m.find()) {
		      contentNoImg = contentNoImg.replace(m.group(0), "<img src=\""+page.getImgs().get(Integer.parseInt(m.group(1))-1).getSrc()+"\"></img>");
		    }
		    String htmlString = htmlStart+"<a href=\""+page.getUrl()+"\" target=_blank>源网站</a>"+title+msg+contentNoImg+htmlEnd;
		    System.out.println(htmlString);
		    System.out.println(page.getContenttitle());
		    String title1 = page.getContenttitle().trim();
		    if(page.getContenttitle().trim().contains("|")){
		        title1 = page.getContenttitle().trim().replace("|", "");
		      }
		      if(page.getContenttitle().trim().contains("/")){
		        title1 = page.getContenttitle().trim().replace("/", "");
		      }
		      if(page.getContenttitle().trim().contains(": ")){
		        title1 = page.getContenttitle().trim().replace(": ", "");
		      }
		      if(page.getContenttitle().trim().contains(":")){
		        title1 = page.getContenttitle().trim().replace(":", "");
		      }
		      if(page.getContenttitle().trim().contains("|")&&page.getContenttitle().trim().contains(": ")){
		        title1 = page.getContenttitle().trim().replace("|", "").replace(": ", "");
		      }
		      if(page.getContenttitle().trim().contains("|")&&page.getContenttitle().trim().contains("？")){
		        title1 = page.getContenttitle().trim().replace("|", "").replace("？", "");
		      }
		      if(page.getContenttitle().trim().contains("|")&&page.getContenttitle().trim().contains("：")){
		        title1 = page.getContenttitle().trim().replace("|", "").replace("：", "");
		      }
		      if(page.getContenttitle().trim().contains("|")&&page.getContenttitle().trim().contains("\"")){
		        title1 = page.getContenttitle().trim().replace("|", "").replace("\"", "");
		      }
		      if(page.getContenttitle().trim().contains("！")&&page.getContenttitle().trim().contains("\"")){
		        title1 = page.getContenttitle().trim().replace("！", "").replace("\"", "");
		      }
		      if(page.getContenttitle().trim().contains(" | ")&&page.getContenttitle().trim().contains("，")){
		        title1 = page.getContenttitle().trim().replace(" | ", "").replace("，", "");
		      }
		      if(page.getContenttitle().trim().contains(",")&&page.getContenttitle().trim().contains("?")){
		        title1 = page.getContenttitle().trim().replace(",", "").replace("?", "");
		      }
		      if(page.getContenttitle().trim().contains("\"")&&page.getContenttitle().trim().contains("（")
		          &&page.getContenttitle().trim().contains("）")){
		        title1 = page.getContenttitle().trim().replace("\"", "").replace("）", "").replace("（", "");
		      }
		      if("".equals(title1)){
		        title1 = page.getContenttitle().trim();
		      }
		    String path = "E:/video/html/"+rowkey+"&"+title1+".html";
		    try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path)), "utf-8"))) {
		      br.write(htmlString);
		      br.flush();
		    } catch (FileNotFoundException e) {
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		    
		  }
}

