package com.framework.solution.model;

import com.framework.solution.enums.ContentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class Content {

  private UUID contentId;
  private String title;
  private ContentType contentType;

  @Override
  public String toString() {
    return "Content{" +
        "contentId=" + contentId +
        ", title='" + title + '\'' +
        ", contentType=" + contentType +
        '}';
  }
}
