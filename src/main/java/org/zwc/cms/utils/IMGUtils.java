package org.zwc.cms.utils;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;

import cn.com.sh.crawlerbese.bean.IMG;


public class IMGUtils {

	public static IMG getvalidIMG(Element _imgele, int _imgtypefilter, String _purl, int _imgidx) {
		IMG img = new IMG();
		// 获取图片的url
		String absUrl = _imgele.absUrl("src");
		String data_src = _imgele.absUrl("data-src");
		String alt_src = _imgele.absUrl("alt_src");
		String srcstr = _imgele.attr("src");
		String imgsrc = "";
		if(StringUtils.isNotEmpty(absUrl)){
			imgsrc = UrlFilter.geturls(absUrl);
		} else if(StringUtils.isNotEmpty(data_src)){
			imgsrc = UrlFilter.geturls(data_src);
		}else if(StringUtils.isNotEmpty(alt_src)){
			imgsrc = UrlFilter.geturls(alt_src);
		}else if(StringUtils.isNotEmpty(srcstr)){
			imgsrc = UrlFilter.geturls(srcstr);
		}

		// 根据图片名后缀获得图片的类型
		String pictype = "";
		int idx = imgsrc.lastIndexOf(".");
		if (idx > 0) {
			pictype = imgsrc.substring(idx + 1);
		}

		// 过滤非正常的图片url
		if (imgsrc.endsWith(".html") || imgsrc.endsWith(".htm") || imgsrc.endsWith(".shtml") || imgsrc.contains(".dll?")
				|| imgsrc.contains(".jsp?") || imgsrc.contains(".aspx?")
		// || imgsrc.contains(".php?")
		) {

		} else {
			// 过滤搜狐有问题图片
			if (imgsrc.endsWith("!article.foil")) {
				imgsrc = imgsrc.replace("!article.foil", "");
			}

			// 图片过滤类型为0,仅过滤gif图片,否则过滤gif和png图片
			if (_imgtypefilter == 1) {
				if (pictype.equals("gif") || pictype.equals("png")) {
				} else {
					if (!_imgele.attr("alt").isEmpty()) {
						img.setAlt(_imgele.attr("alt"));
					}

					if (imgsrc != null && imgsrc.length() > 0) {
						img.setSrc(imgsrc);
					}

					img.setPurl(_purl);
					img.setIdx(_imgidx);
				}
			}

			// 不进行任何过滤
			else if (_imgtypefilter == 2) {
				if (!_imgele.attr("alt").isEmpty()) {
					img.setAlt(_imgele.attr("alt"));
				}

				if (imgsrc != null && imgsrc.length() > 0) {
					img.setSrc(imgsrc);
				}

				img.setPurl(_purl);
				img.setIdx(_imgidx);
			}
			else {
				if (pictype.equals("gif")) {
				} else {

					if (!_imgele.attr("alt").isEmpty()) {
						img.setAlt(_imgele.attr("alt"));
					}

					if (imgsrc != null && imgsrc.length() > 0) {
						img.setSrc(imgsrc);
					}

					img.setPurl(_purl);
					img.setIdx(_imgidx);
				}
			}

		}
		return img;
	}

}
