package org.zwc.cms.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import cn.com.sh.crawlerbese.bean.IMG;

/**
 * Doc的工具类
* @author zhouwenchen
* @date 2018年12月18日 上午10:22:34
 */
public class DocUtils {
	static final String imgtaghead = "$#imgidx=";
	static final String imgtagtail = "#$";
	static final String linetag = "!@#!@";
//	static ArrayList<IMG> imglist = new ArrayList<IMG>();
	static HashSet<String> imgsrcs = new HashSet<String>();
	static String tmpcontentstr = "";
	static int imgidx = 1;
	
	public DocUtils() {
		imgidx = 1;
	}



	/***
	 * 获取文章内容
	 */
	public String getcontent(Element element, String _purl, int _imgtypefilter, int imgidx_temp, ArrayList<IMG> imglist) {
		StringBuilder ret = new StringBuilder();
		if (element != null) {
			List<Node> nodes = element.childNodes();
			int nodesize = nodes.size();
			for (int i = 0; i < nodesize; i++) {
				Node n = nodes.get(i);
				if (n.nodeName().equals("br") || n.nodeName().equals("p") || n.nodeName().equalsIgnoreCase("h1") || n.nodeName().equalsIgnoreCase("h2")) {
					if (!ret.toString().endsWith(linetag)) {
						ret.append(linetag);
					}
				}
				if (n.childNodes().size() > 0) {
					ret.append(getcontent((Element) n, _purl, _imgtypefilter, imgidx_temp, imglist));
				} else {
					// 将node转换为element,报错则跳过
					Element tmp = null;
					try {
						tmp = (Element) nodes.get(i);
					} catch (Exception e) {
						if (e instanceof ClassCastException) {
							String nodeStr = nodes.get(i).toString();
							if (nodeStr.indexOf("<!--") != -1) {
								continue;
							}
							tmpcontentstr = nodeStr;
							if (!tmpcontentstr.trim().equals("")) {
								ret.append(tmpcontentstr);
							}
						}
					}
					if (tmp != null) {
						// 获取图片
						if (!tmp.select("img").isEmpty()) {
							Elements imgeles = tmp.select("img");
							for (Element tmpimgele : imgeles) {
								IMG tmpimg = IMGUtils.getvalidIMG(tmpimgele, _imgtypefilter, _purl, imgidx);
								if (tmpimg.getSrc() != null && tmpimg.getSrc().length() > 0 && !imgsrcs.contains(tmpimg.getSrc())) {
									imglist.add(tmpimg);
									imgsrcs.add(tmpimg.getSrc());
									if (!ret.toString().endsWith(linetag)) {
										ret.append(linetag);
									}
									ret.append(imgtaghead);
									ret.append(String.format("%04d", imgidx));
									ret.append(imgtagtail);
									ret.append(linetag);
									imgidx++;
								}
							}
						} else {
							// 文本信息
							tmpcontentstr = tmp.text();
							if (!tmpcontentstr.trim().equals("")) {
								ret.append(tmp.text());
							}
						}
					}
				}
			}
		}
		// if (ret.toString().startsWith(linetag))
		// return ret.toString().replaceFirst(linetag, "");
		// else

		String ret_str = ret.toString();
		while (ret_str.indexOf(linetag + linetag) != -1) {
			ret_str = ret_str.replaceAll(linetag + linetag, linetag);
		}
		ret_str = ret_str.replaceAll("\n", "").replaceAll("\r", "");
		return ret_str;
	}
}
