package com.framework.solution.model;

import com.framework.solution.enums.ContentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@ToString
public class Content {
  private UUID contentId;
  private String title;
  private ContentType contentType;
}
