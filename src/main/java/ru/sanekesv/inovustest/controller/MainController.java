package ru.sanekesv.inovustest.controller;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.sanekesv.inovustest.service.impl.DocumentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class MainController {

  private DocumentServiceImpl documentService;

  @Autowired
  public MainController(DocumentServiceImpl documentService) {
    this.documentService = documentService;
  }

  @RequestMapping(value = "/")
  public String defaultPage() {
    return "welcome";
  }


  @RequestMapping(method = RequestMethod.POST, value = "/post/xml-to-json", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<String> convertXmlToJson(@RequestParam("file") MultipartFile uploadfile,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) {
    try {
      log.info("File has been received, filename = {}", uploadfile.getName());
      String content = new String(uploadfile.getBytes(), "UTF-8");
      JSONObject jsonObject = documentService.convertToJson(content);
      return ResponseEntity.ok(jsonObject.toString());

    } catch (IOException e) {
      log.error("IOExcetion with converting xml to json", e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
  }
}
