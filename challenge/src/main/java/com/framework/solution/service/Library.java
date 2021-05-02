package com.framework.solution.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.solution.enums.ContentType;
import com.framework.solution.model.Content;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


import org.springframework.stereotype.Service;

@Service
public class Library implements ILibrary {
  final String filePath = "./src/main/resources/content.json";

  // some way to read a JSON file
  List<Content> allContent = new ArrayList<Content>();
  List<Content> initialContent = new ArrayList<Content>();

  public Library(){
    this.initialContent = seedInitialContent();
    this.allContent.addAll(this.initialContent);
  }

  private List<Content> seedInitialContent(){
    ObjectMapper objectMapper = new ObjectMapper();
    List<Content> initialContent;
    try {
      initialContent = objectMapper.readValue(new File(filePath), new TypeReference<List<Content>>(){});
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
    this.allContent.add(content);
    return content;
  }

  // Deletes Content from the Library, not the JSON file
  @Override
  public void deleteContentById(UUID id) {
    Iterator<Content> iterator = allContent.iterator();
    while(iterator.hasNext())
      if(iterator.next().getContentId().equals(id)){
        iterator.remove();
      }
  }

  @Override
  public int countContentByType(ContentType contentType) {
    return this.getContentsByType(contentType).size();
  }

  @Override
  public List<Content> getAllContents() {
    return this.allContent;
  }

  @Override
  public List<Content> getContentsByType(ContentType contentType) {
    List<Content> contentsByType = new ArrayList<Content>();
    // There's a findAny() but not a findAll()?
    for(Content content : this.allContent)
      if(content.getContentType().equals(contentType)) {
        contentsByType.add(content);
      }
    return contentsByType;
  }
}