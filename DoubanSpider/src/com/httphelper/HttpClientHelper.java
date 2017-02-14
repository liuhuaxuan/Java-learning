
package com.httphelper;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientHelper {

	/**
	 * 发送HTTP GET请求获得响应报文的实体部分
	 * 
	 * @param url
	 * @param Referer
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String getHtmlString(String url, String Referer) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse htttpResponse = null;
		String responseContent = null; /* 返回的内容 */

		/**
		 * HTTP请求首部行字段
		 */
		final String ACCEPT = "Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";
		final String ACCEPT_LANGUAGE = "Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";
		final String CONNECTION = "Connection:keep-alive";
		final String UPGRADE_INSECURE_REQUESTS = "Upgrade-Insecure-Requests:1";
		final String USER_AGENT = "User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";

		httpClient = HttpClients.createDefault(); /* 首先创建HttpClient对象 */

		HttpGet httpGet = new HttpGet(url); /* 创建HTTP请求对象 */

		/* 伪造HTTP HEADER */
		httpGet.setHeader(ACCEPT, url);
		httpGet.setHeader(ACCEPT_LANGUAGE, url);
		httpGet.setHeader(CONNECTION, url);
		httpGet.setHeader(Referer, url); /* 先前网页的地址，当前请求网页紧随其后,即来路 */
		httpGet.setHeader(UPGRADE_INSECURE_REQUESTS,
				url);/* Upgrade-Insecure-Requests首部行为谷歌浏览器特有 */
		httpGet.setHeader(USER_AGENT, url);

		/*
		 * 设置超时 连接超时时间为5000毫秒 设置从connect Manager获取Connection超时时间为1000毫秒
		 * 请求获取数据的超时时间为6000毫秒
		 */

		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000)
				.setSocketTimeout(6000).build();
		httpGet.setConfig(requestConfig);

		try {
			htttpResponse = httpClient.execute(httpGet); /* HTTP响应 */
		} catch (ConnectTimeoutException e) {
			System.out.println("request time out!");
			return null;
		}

		/* 得到状态码 如果状态码不是200，就返回null */
		if (htttpResponse.getStatusLine().getStatusCode() != 200) {
			System.out.println(htttpResponse.getStatusLine());
			responseContent = null; // 如果HTTP GET请求请求不成功，返回内容为null
		} else {

			HttpEntity httpEntity = htttpResponse.getEntity(); /* 获得HTTP响应报文的实体部分 */
			responseContent = EntityUtils.toString(httpEntity, "UTF-8");
			// 如果HTTP GET请求请求成功，返回内容为响应报文的实体部分
		}

		try {
			httpClient.close();
			htttpResponse.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return responseContent;

	}
}
