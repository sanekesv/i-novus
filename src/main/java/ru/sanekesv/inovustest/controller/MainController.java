package ru.sanekesv.inovustest.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sanekesv.inovustest.model.FormModel;
import ru.sanekesv.inovustest.service.DocumentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class MainController {

  private DocumentService documentService;

  @Autowired
  public MainController(DocumentService documentService) {
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
//    System.out.println(uploadfiles);
    try {
      String content = new String(uploadfile.getBytes(), "UTF-8");
      JSONObject jsonObject = documentService.convertNodesFromXml(content);
      return ResponseEntity.ok(jsonObject.toString());

    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
  }
}
