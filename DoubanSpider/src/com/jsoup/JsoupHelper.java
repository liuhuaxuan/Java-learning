package com.jsoup;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JsoupHelper {

	/**
	 * 直接得到包含图片链接的页面
	 * 
	 * @param htmlString
	 * @return String
	 */
	public static String getNextPage(String htmlString) {

		String nextPageUrl = null;

		Document htmlDocument = Jsoup.parse(htmlString); /* 解析HTML文档 */

		Element spanElement = htmlDocument.select("span.opt-mid").first(); /* 得到元素 */
		Element linkElement = spanElement.select("a").last();

		if (linkElement == null) {
			nextPageUrl = null; // 如果不存在link这个标签，就返回null
		} else {
			nextPageUrl = linkElement.attr("href"); // 如果存在link这个标签，就返回url
		}
		return nextPageUrl;
	}

	/**
	 * 得到每个页面中的图片连接
	 * 
	 * @param htmlString
	 * @return String
	 * @throws IOException
	 */
	public static String getImageURL(String htmlString) throws IOException {

		Document htmlDocument = Jsoup.parse(htmlString);

		Element divElement = htmlDocument.select("div.photo-wp").first();
		Element linkElement = divElement.getElementsByTag("img").first();

		String imageUrl = linkElement.attr("src");

		return imageUrl;
	}
}
