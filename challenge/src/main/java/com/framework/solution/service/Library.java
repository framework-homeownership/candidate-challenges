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
    // There was no instruction on whether or not to replace an existing record/object for a given ID.
    //
    // Decision/design criteria
    //
    // (1) Based on a Content object being immutable by design, I stayed with the logic to only add a
    //     Content object if the key (ID) does not exist in the Library Repo already.
    //
    // (2) Initially, I wanted the logic to check if that key already exists in the repo, and if it does,
    //     I wanted to throw an exception. However, because the method signature/contract (ILibrary)/ interface does
    //     not declare throwing an exception, I couldn't change the interface signature, due to instructions.
    //     If the ILibrary was my own code, I would switch and go that route on throwing a runtime exception
    //     which is good practice, eliminating the need to if checks.

    repository.putIfAbsent(content.getContentId(), content);
    return content;
  }

  // Deletes Content from the Library, not the JSON file
  @Override
  public void deleteContentById(UUID id) {
    // Does not fail if the id does not exist in the repo.
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
