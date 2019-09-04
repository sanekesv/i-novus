package ru.sanekesv.inovustest.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "backend")
@RibbonClient(name = "backend")
public interface WorkerClient {

    @GetMapping("/xml-to-json")
    String format (@RequestParam("file") MultipartFile uploadfile);
}