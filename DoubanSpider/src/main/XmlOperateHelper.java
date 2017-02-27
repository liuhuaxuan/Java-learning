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
	 * 创建XML文件
	 * 
	 * @param filename
	 *            文件路径
	 * @param rootname
	 *            根节点名称
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public static void createXml(String filename, String rootname)
			throws ParserConfigurationException, TransformerException {

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();/* 创建DocumentBuilderFactory */
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();/* 创建DocumentBuilder */
		Document xmlDocument = documentBuilder.newDocument();/* 创建Document */
		Element urlElement = xmlDocument.createElement(rootname); /* 创建根节点 */
		xmlDocument.appendChild(urlElement);/* 将根节点添加到Document下 */
		TransformerFactory transformerFactory = TransformerFactory.newInstance(); /* 创建TransformerFactory对象 */
		Transformer tf = transformerFactory.newTransformer(); /* 创建Transformer对象 */
		tf.setOutputProperty(OutputKeys.INDENT, "yes"); /* 设置输出数据时换行 */
		tf.transform(new DOMSource(xmlDocument), new StreamResult(
				filename));/* 使用Transformer的transform()方法将DOM树转换成XML */
	}

	/**
	 * 向指定节点下添加新的节点
	 * 
	 * @param filePath
	 *            文件路径
	 * @param fatherNode
	 *            父节点名称
	 * @param newNode
	 *            添加的节点名称
	 * @param textContent
	 *            添加的节点的内容
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
	 * 读取XML文件
	 * 
	 * @param filePath
	 * @return Document
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document readXML(String filePath) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();/* 创建DocumentBuilderFactory */
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();/* 创建DocumentBuilder */
		Document urlXml = documentBuilder.parse(filePath);

		return urlXml;
	}
}
