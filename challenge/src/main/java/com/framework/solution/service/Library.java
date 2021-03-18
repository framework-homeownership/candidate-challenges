package com.framework.solution.service;

import com.framework.solution.enums.ContentType;
import com.framework.solution.model.Content;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class Library implements ILibrary {

  // Adds Content to the Library, not the JSON file
  @Override
  public Content addContent(Content content) {
    return null;
  }

  // Deletes Content from the Library, not the JSON file
  @Override
  public void deleteContentById(UUID id) {

  }

  @Override
  public int countContentByType(ContentType contentType) {
    return 0;
  }

  @Override
  public List<Content> getAllContents() {
    return null;
  }

  @Override
  public List<Content> getContentsByType(ContentType contentType) {
    return null;
  }
}