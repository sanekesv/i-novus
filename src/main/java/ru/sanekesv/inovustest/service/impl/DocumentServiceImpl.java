package ru.sanekesv.inovustest.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import ru.sanekesv.inovustest.service.DocumentService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class DocumentServiceImpl implements DocumentService {


  private static Document convertXMLFileToXMLDocument(String fileContext) {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = null;
    try {
      builder = factory.newDocumentBuilder();
      return builder.parse(new InputSource(new StringReader(fileContext)));
    } catch (Exception e) {
      log.error("Exception", e);
    }
    return null;
  }

  public JSONObject convertToJson(String xml) {
    log.info("starting convert process xml to json");
    log.info("convert XML to org.w3c.dom.Document");
    log.debug("XML data = '{}'", xml);
    Document document = convertXMLFileToXMLDocument(xml);
    if (document != null) {
      Node documentElement = document.getDocumentElement();
      JSONObject jsonFromXML = getJsonFromXML(documentElement);
      log.info("convert process ended without errors");
      log.debug("JSON data = '{}'", jsonFromXML.toString());
      return jsonFromXML;
    }
    return null;
  }

  private JSONObject getJsonFromXML(Node node) {
    JSONObject jsonObject = new JSONObject();
    String nodeName = node.getNodeName();
    try {
      JSONObject jsonObject2 = new JSONObject();
      convertNode(node, jsonObject2);
      jsonObject.put(nodeName, jsonObject2);
    } catch (JSONException e) {
      log.error("Exception", e);
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
            log.error("Exception", e);
          }
        }
        jsonObject.put("value", value);
      }
    }
    return value;
  }

}
