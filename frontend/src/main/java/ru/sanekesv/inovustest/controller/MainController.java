package ru.sanekesv.inovustest.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sanekesv.inovustest.client.wrapper.WorkerWrapper;

@Controller
public class MainController {

  private WorkerWrapper workerWrapper;

  @Autowired
  public MainController(WorkerWrapper workerWrapper) {
    this.workerWrapper = workerWrapper;
  }

  @RequestMapping(value = "/")
  public String defaultPage() {
    System.out.println("WTF");
    return "welcome";
  }


  @RequestMapping(method = RequestMethod.POST, value = "/post/xml-to-json", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<String> convertXmlToJson(@RequestParam("file") MultipartFile uploadfile) {
//    System.out.println(uploadfiles);
    try {
      return ResponseEntity.ok(workerWrapper.wrap(uploadfile));

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
    }
  }
}
