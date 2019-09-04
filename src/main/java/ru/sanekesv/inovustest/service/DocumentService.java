package ru.sanekesv.inovustest.service;

import org.json.JSONObject;

import java.io.IOException;

public interface DocumentService {
  JSONObject convertToJson(String xml) throws IOException;
}
