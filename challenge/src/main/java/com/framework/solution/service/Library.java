package com.framework.solution.service;

import com.framework.solution.enums.ContentType;
import com.framework.solution.model.Content;
import java.util.List;
import java.util.*;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class Library implements ILibrary {
  List<Content> taqLibrary = new LinkedList<>();
  // Adds Content to the Library, not the JSON file
  @Override
  public Content addContent(Content content) {
    taqLibrary.add(content);
    return content;
  }

  // Deletes Content from the Library, not the JSON file
  @Override
  public void deleteContentById(UUID id) { 
    for(Content libEntry : taqLibrary){
      if (!libEntry.getContentId().equals(id))  
        continue;  
      
      taqLibrary.remove(libEntry);       
      break;
    }
  }

  @Override
  public int countContentByType(ContentType contentType) {
    int count = 0;
    for(Content libEntry : taqLibrary){
     if (libEntry.getContentType().equals(contentType)){
      count++;
     }
    }
    return count;
  }

  @Override
  public List<Content> getAllContents() {
    return taqLibrary;
  }

  @Override
  public List<Content> getContentsByType(ContentType contentType) {
    List<Content> newContentList = new LinkedList<>();
    for(Content libEntry : taqLibrary) {
      if (libEntry.getContentType().equals(contentType))
        newContentList.add(libEntry);
    }
    return newContentList;
  }
}