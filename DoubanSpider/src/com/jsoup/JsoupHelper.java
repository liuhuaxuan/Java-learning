package com.jsoup;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JsoupHelper {

	/**
	 * ֱ�ӵõ�����ͼƬ���ӵ�ҳ��
	 * 
	 * @param htmlString
	 * @return String
	 */
	public static String getNextPage(String htmlString) {

		String nextPageUrl = null;

		Document htmlDocument = Jsoup.parse(htmlString); /* ����HTML�ĵ� */

		Element spanElement = htmlDocument.select("span.opt-mid").first(); /* �õ�Ԫ�� */
		Element linkElement = spanElement.select("a").last();

		if (linkElement == null) {
			nextPageUrl = null; // ���������link�����ǩ���ͷ���null
		} else {
			nextPageUrl = linkElement.attr("href"); // �������link�����ǩ���ͷ���url
		}
		return nextPageUrl;
	}

	/**
	 * �õ�ÿ��ҳ���е�ͼƬ����
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
