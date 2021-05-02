package com.framework.solution.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.solution.enums.ContentType;
import com.framework.solution.model.Content;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class Library implements ILibrary {
  final String filePath = "../candidate-challenges/challenge/src/main/resources/content.json";
  // some way to read a JSON file
  List<Content> allContent = new ArrayList<Content>();
  List<Content> initialContent = new ArrayList<Content>();

  public Library(){
    this.initialContent = seedInitialContent();
    allContent.addAll(this.initialContent);
  }

  private List<Content> seedInitialContent(){
    ObjectMapper objectMapper = new ObjectMapper();
    List<Content> initialContent;
    try {
      initialContent = objectMapper.readValue(filePath, new TypeReference<List<Content>>(){});
      return initialContent;
    } catch (JsonParseException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  // Adds Content to the Library, not the JSON file
  @Override
  public Content addContent(Content content) {
    allContent.add(content);
    return content;
  }

  // Deletes Content from the Library, not the JSON file
  @Override
  public void deleteContentById(UUID id) {
    for(Content content : allContent)
      if(content.getContentId() == id){
        allContent.remove(content);
      }
  }

  @Override
  public int countContentByType(ContentType contentType) {
    return this.getContentsByType(contentType).size();
  }

  @Override
  public List<Content> getAllContents() {
    return allContent;
  }

  @Override
  public List<Content> getContentsByType(ContentType contentType) {
    List<Content> contentsByType = new ArrayList<Content>();
    // There's a findAny() but not a findAll()?
    for(Content content : allContent)
      if(content.getContentType().equals(contentType)) {
        contentsByType.add(content);
      }
    return contentsByType;
  }
}