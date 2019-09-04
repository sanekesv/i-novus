package ru.sanekesv.inovustest;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sanekesv.inovustest.service.impl.DocumentServiceImpl;

import java.nio.file.Files;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class INovusTestApplicationTests {

  @Autowired
  private DocumentServiceImpl documentService;

  @Test
  public void tryConvertXmlToHashMap() {
    final String xmlFilePath = "src/test/resources/example.xml";
    log.info("Test convert xml to JSON started");
    try {
      String xml = new String(Files.readAllBytes(Paths.get(xmlFilePath)));
      JSONObject jsonObject = documentService.convertToJson(xml);
      assert jsonObject.length() == 1;
      assert jsonObject.get("root") != null;
      assert jsonObject.get("root") instanceof JSONObject;
      assert ((JSONObject) jsonObject.get("root")).length() == 3;
      assert ((JSONObject) jsonObject.get("root")).get("value").equals(6);
      log.info("Test ended without errors");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


}
