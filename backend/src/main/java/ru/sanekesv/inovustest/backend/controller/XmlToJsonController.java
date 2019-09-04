package ru.sanekesv.inovustest.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sanekesv.inovustest.backend.service.DocumentService;

@RestController
public class XmlToJsonController {

    @Autowired
    private DocumentService documentService;


    @GetMapping("/")
    public String hello () {
        return "Hello, man!";
    }

    @PostMapping("/xml-to-json")
    public String calculate (@RequestParam("file") MultipartFile uploadfile){
        try {
            return documentService.convertNodesFromXml(new String(uploadfile.getBytes(), "UTF-8")).toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
