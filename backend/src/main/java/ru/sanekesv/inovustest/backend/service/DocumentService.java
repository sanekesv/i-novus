package ru.sanekesv.inovustest.backend.service;

import org.json.JSONObject;

import java.io.IOException;

public interface DocumentService {
  JSONObject convertNodesFromXml(String xml) throws IOException;
}
