package com.framework.solution.service;

import com.framework.solution.enums.ContentType;
import com.framework.solution.model.Content;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class Library implements ILibrary {

  List<Content> initialContent; // some way to read a JSON file
  List<Content> allContent;
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