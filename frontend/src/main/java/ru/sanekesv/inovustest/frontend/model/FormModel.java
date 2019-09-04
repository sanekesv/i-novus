package ru.sanekesv.inovustest.frontend.model;

import org.springframework.web.multipart.MultipartFile;

public class FormModel {

  private MultipartFile[] files;

  public MultipartFile[] getFiles() {
    return files;
  }

  public void setFiles(MultipartFile[] files) {
    this.files = files;
  }
}
