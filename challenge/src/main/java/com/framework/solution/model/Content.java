package com.framework.solution.model;

import com.framework.solution.enums.ContentType;
import java.util.UUID;

// Testing fork process
public class Content {

  private UUID contentId;
  private String title;
  private ContentType contentType;

  public Content() {

  }

  public Content(UUID contentId, String title, ContentType contentType) {
    this.contentId = contentId;
    this.title = title;
    this.contentType = contentType;
  }

  public UUID getContentId() {
    return contentId;
  }

  public String getTitle() {
    return title;
  }

  public ContentType getContentType() {
    return contentType;
  }

  @Override
  public String toString() {
    return "Content{" +
        "contentId=" + contentId +
        ", title='" + title + '\'' +
        ", contentType=" + contentType +
        '}';
  }
}
