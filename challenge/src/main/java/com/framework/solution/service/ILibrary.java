package com.framework.solution.service;

import com.framework.solution.enums.ContentType;
import com.framework.solution.model.Content;
import java.util.List;
import java.util.UUID;

public interface ILibrary {

  Content addContent(Content content);

  void deleteContentById(UUID id);

  int countContentByType(ContentType contentType);

  List<Content> getAllContents();

  List<Content> getContentsByType(ContentType contentType);

}
