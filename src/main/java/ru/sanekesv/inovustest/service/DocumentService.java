package ru.sanekesv.inovustest.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DocumentService {


  private static Document convertXMLFileToXMLDocument(String fileContext) {
    //Parser that produces DOM object trees from XML content
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    //API to obtain DOM Document instance
    DocumentBuilder builder = null;
    try {
      //Create DocumentBuilder with default configuration
      builder = factory.newDocumentBuilder();

      //Parse the content to Document object
      Document doc = builder.parse(new InputSource(new StringReader(fileContext)));
      return doc;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public JSONObject convertNodesFromXml(String xml) throws IOException {
    Document document = convertXMLFileToXMLDocument(xml);
    Node documentElement = document.getDocumentElement();
    return getJsonFromXML(documentElement);
  }

  private JSONObject getJsonFromXML(Node node) {
    JSONObject jsonObject = new JSONObject();
    String nodeName = node.getNodeName();
    try {
      JSONObject jsonObject2 = new JSONObject();
      convertNode(node, jsonObject2);
      jsonObject.put(nodeName, jsonObject2);
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return jsonObject;
  }

  private int convertNode(Node node, JSONObject jsonObject) throws JSONException {
    int value = 0;
    if (node.getNodeType() == Node.ELEMENT_NODE) {
      NodeList childNodes = node.getChildNodes();
      if (childNodes.getLength() == 1 && node.getFirstChild().getNodeType() == Node.TEXT_NODE) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(node.getFirstChild().getTextContent());
        while (m.find()) {
          value += Integer.parseInt(m.group());
        }
        jsonObject.put("value", value);
        return value;
      } else {
        for (int i = 0; i < childNodes.getLength(); i++) {
          try {
            JSONObject jsonObject2 = new JSONObject();
            Node child = childNodes.item(i);
            String childName = child.getNodeName();
            if (!childName.equals("#text")) {
              value = value + convertNode(child, jsonObject2);
              jsonObject.put(childName, jsonObject2);
            }
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
        jsonObject.put("value", value);
      }
    }
    return value;
  }

}
