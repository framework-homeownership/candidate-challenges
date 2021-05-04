package com.framework.solution.service;

import com.framework.solution.enums.ContentType;
import com.framework.solution.model.Content;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Service
public class Library implements ILibrary {

  private final ConcurrentMap<UUID, Content> repository = new ConcurrentHashMap<>();

  // Decided to use Java's Optional NPE Handling here.
  public Optional<Content> findById(UUID id) {
    return Optional.ofNullable(repository.get(id));
  }

  // Adds Content to the Library, not the JSON file
  @Override
  public Content addContent(Content content) {
    // TODO: There's no instruction on whether or not to replace an existing record/object for a given ID. Maybe we can ask this questions or just implement something from a design decision.
    repository.putIfAbsent(content.getContentId(), content);
    return content;
  }

  // Deletes Content from the Library, not the JSON file
  @Override
  public void deleteContentById(UUID id) {
    repository.remove(id);
  }

  @Override
  public int countContentByType(ContentType contentType) {
    return this.getContentsByType(contentType).size();
  }

  @Override
  public List<Content> getAllContents() {
    return new ArrayList<>(repository.values());
  }

  @Override
  public List<Content> getContentsByType(ContentType contentType) {
    return repository.values()
        .stream()
        .filter(content -> content.getContentType().equals(contentType))
        .collect(Collectors.toList());
  }
}
