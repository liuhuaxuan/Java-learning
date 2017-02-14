
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
	 * ����HTTP GET��������Ӧ���ĵ�ʵ�岿��
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
		String responseContent = null; /* ���ص����� */

		/**
		 * HTTP�����ײ����ֶ�
		 */
		final String ACCEPT = "Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";
		final String ACCEPT_LANGUAGE = "Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";
		final String CONNECTION = "Connection:keep-alive";
		final String UPGRADE_INSECURE_REQUESTS = "Upgrade-Insecure-Requests:1";
		final String USER_AGENT = "User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";

		httpClient = HttpClients.createDefault(); /* ���ȴ���HttpClient���� */

		HttpGet httpGet = new HttpGet(url); /* ����HTTP������� */

		/* α��HTTP HEADER */
		httpGet.setHeader(ACCEPT, url);
		httpGet.setHeader(ACCEPT_LANGUAGE, url);
		httpGet.setHeader(CONNECTION, url);
		httpGet.setHeader(Referer, url); /* ��ǰ��ҳ�ĵ�ַ����ǰ������ҳ�������,����· */
		httpGet.setHeader(UPGRADE_INSECURE_REQUESTS,
				url);/* Upgrade-Insecure-Requests�ײ���Ϊ�ȸ���������� */
		httpGet.setHeader(USER_AGENT, url);

		/*
		 * ���ó�ʱ ���ӳ�ʱʱ��Ϊ5000���� ���ô�connect Manager��ȡConnection��ʱʱ��Ϊ1000����
		 * �����ȡ���ݵĳ�ʱʱ��Ϊ6000����
		 */

		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000)
				.setSocketTimeout(6000).build();
		httpGet.setConfig(requestConfig);

		try {
			htttpResponse = httpClient.execute(httpGet); /* HTTP��Ӧ */
		} catch (ConnectTimeoutException e) {
			System.out.println("request time out!");
			return null;
		}

		/* �õ�״̬�� ���״̬�벻��200���ͷ���null */
		if (htttpResponse.getStatusLine().getStatusCode() != 200) {
			System.out.println(htttpResponse.getStatusLine());
			responseContent = null; // ���HTTP GET�������󲻳ɹ�����������Ϊnull
		} else {

			HttpEntity httpEntity = htttpResponse.getEntity(); /* ���HTTP��Ӧ���ĵ�ʵ�岿�� */
			responseContent = EntityUtils.toString(httpEntity, "UTF-8");
			// ���HTTP GET��������ɹ�����������Ϊ��Ӧ���ĵ�ʵ�岿��
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
