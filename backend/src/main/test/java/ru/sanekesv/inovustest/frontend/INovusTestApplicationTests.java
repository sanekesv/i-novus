package ru.sanekesv.inovustest.frontend;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.sanekesv.inovustest.backend.service.DocumentService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class INovusTestApplicationTests {

  @Autowired
  private DocumentService documentService;

  @Test
  public void tryConvertXmlToHashMap() {
    final String xmlFilePath = "src/test/resources/example.xml";

    try {
      JSONObject jsonObject = documentService.convertNodesFromXml(new String(Files.readAllBytes(Paths.get(xmlFilePath))));
      assert jsonObject.length() == 1;
      assert jsonObject.get("root") != null;
      assert jsonObject.get("root") instanceof JSONObject;
      assert ((JSONObject) jsonObject.get("root")).length() == 3;
      assert ((JSONObject) jsonObject.get("root")).get("value").equals(6);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


}
