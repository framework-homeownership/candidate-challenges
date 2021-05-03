package com.framework.solution.service;

import com.framework.solution.enums.ContentType;
import com.framework.solution.model.Content;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Service;

@Service
public class Library implements ILibrary {

  private final ConcurrentMap<UUID, Content> repository = new ConcurrentHashMap<>();

  // Adds Content to the Library, not the JSON file
  @Override
  public Content addContent(Content content) {
    repository.putIfAbsent(content.getContentId(), content);
    return content;
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
    return new ArrayList<>(repository.values());
  }

  @Override
  public List<Content> getContentsByType(ContentType contentType) {
    return null;
  }
}
