package ru.sanekesv.inovustest.client.wrapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.sanekesv.inovustest.client.WorkerClient;

@Component
@RequiredArgsConstructor
public class WorkerWrapper {

    private final WorkerClient worker;

    public String wrap (MultipartFile file){
        return worker.format(file);
    }
}
