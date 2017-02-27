package org.climer.xmloperator;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XmlOperateHelper {

	/**
	 * ����XML�ļ�
	 * 
	 * @param filename
	 *            �ļ�·��
	 * @param rootname
	 *            ���ڵ�����
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public static void createXml(String filename, String rootname)
			throws ParserConfigurationException, TransformerException {

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();/* ����DocumentBuilderFactory */
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();/* ����DocumentBuilder */
		Document xmlDocument = documentBuilder.newDocument();/* ����Document */
		Element urlElement = xmlDocument.createElement(rootname); /* �������ڵ� */
		xmlDocument.appendChild(urlElement);/* �����ڵ���ӵ�Document�� */
		TransformerFactory transformerFactory = TransformerFactory.newInstance(); /* ����TransformerFactory���� */
		Transformer tf = transformerFactory.newTransformer(); /* ����Transformer���� */
		tf.setOutputProperty(OutputKeys.INDENT, "yes"); /* �����������ʱ���� */
		tf.transform(new DOMSource(xmlDocument), new StreamResult(
				filename));/* ʹ��Transformer��transform()������DOM��ת����XML */
	}

	/**
	 * ��ָ���ڵ�������µĽڵ�
	 * 
	 * @param filePath
	 *            �ļ�·��
	 * @param fatherNode
	 *            ���ڵ�����
	 * @param newNode
	 *            ��ӵĽڵ�����
	 * @param textContent
	 *            ��ӵĽڵ������
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 */
	public static void addNode(String filePath, String fatherNode, String newNode, String textContent)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
		Document xmlDocument = XmlOperateHelper.readXML(filePath);
		Element itemElement = xmlDocument.createElement(newNode);
		itemElement.setTextContent(textContent);
		Node fNode = xmlDocument.getElementsByTagName(fatherNode).item(0);
		fNode.appendChild(itemElement);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(new DOMSource(xmlDocument), new StreamResult(filePath));
	}

	/**
	 * ��ȡXML�ļ�
	 * 
	 * @param filePath
	 * @return Document
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document readXML(String filePath) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();/* ����DocumentBuilderFactory */
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();/* ����DocumentBuilder */
		Document urlXml = documentBuilder.parse(filePath);

		return urlXml;
	}
}
