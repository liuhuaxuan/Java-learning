package com.test;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import com.httphelper.HttpClientHelper;
import com.jsoup.JsoupHelper;
import com.xmloperate.XmlOperateHelper;

public class MainTest {

	public static void main(String[] args)
			throws IOException, ParserConfigurationException, TransformerException, SAXException, InterruptedException {

		long startTime = System.currentTimeMillis();

		String baseUrl = "https://movie.douban.com/photos/photo/2407668722/";
		String fistReferer = "https://movie.douban.com/photos/photo/2407668722/";
		String filePath = "C:\\Users\\L\\Desktop\\links.xml";
		String nextReferer = null;
		String linkString = null;
		int imageCount = 1;
		final int loopTime = 918;

		XmlOperateHelper.createXml(filePath, "url");

		String htmlString = HttpClientHelper.getHtmlString(baseUrl, fistReferer);
		// System.out.println(html);
		linkString = JsoupHelper.getImageURL(htmlString);
		linkString = linkString.replaceAll("photo", "raw");
		linkString = linkString.replaceFirst("raw", "photo");
		System.out.println(linkString);
		XmlOperateHelper.addNode(filePath, "url", "item", linkString);
		nextReferer = baseUrl;
		baseUrl = JsoupHelper.getNextPage(htmlString);

		for (int i = 1; i < loopTime; i++) {
			htmlString = HttpClientHelper.getHtmlString(baseUrl, nextReferer);
			linkString = JsoupHelper.getImageURL(htmlString);
			linkString = linkString.replaceAll("photo", "raw");
			linkString = linkString.replaceFirst("raw", "photo");
			System.out.println(linkString);
			XmlOperateHelper.addNode(filePath, "url", "item", linkString);
			nextReferer = baseUrl;
			baseUrl = JsoupHelper.getNextPage(htmlString);
			imageCount++;
			Thread.currentThread();
			int x = (int) (Math.random() * 100) + 500;
			Thread.sleep(x);
		}

		System.out.println(imageCount);

		long endTime = System.currentTimeMillis();
		System.out.println("ºÄÊ±£º" + (endTime - startTime) + " ms");
	}
}
