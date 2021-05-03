package com.framework.solution.service;

import com.framework.solution.enums.ContentType;
import com.framework.solution.model.Content;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LibraryTest {

  private Library libraryService;

  @BeforeEach
  void setUp() {
    // Keeping the Unit test as small and efficient as possible so we'll not use the
    // @SpringBootTest, which is why we won't use Dependency Injection
    libraryService = new Library();
  }

  // TODO: Need a test/business decision for the scenario when we call add content and the ID already exists in the repo.

  @Test
  void findById() {
    // given
    UUID uuid = UUID.randomUUID();
    Content unsavedContent = Content.builder()
        .contentId(uuid)
        .contentType(ContentType.AUDIO)
        .title("Drake - Thank Me Later")
        .build();

    Content savedContent = libraryService.addContent(unsavedContent);

    // when
    Optional<Content> queriedContent = libraryService.findById(uuid);

    // then
    assertTrue(queriedContent.isPresent());
    assertEquals(savedContent, queriedContent.get());
    assertFalse(libraryService.findById(UUID.randomUUID()).isPresent());
  }

  @Test
  void addContent() {
    // given
    UUID uuid = UUID.randomUUID();
    Content unsavedContent = Content.builder()
        .contentId(uuid)
        .contentType(ContentType.AUDIO)
        .title("Drake - Thank Me Later")
        .build();

    // when
    Content savedContent = libraryService.addContent(unsavedContent);

    // then
    assertEquals(unsavedContent, savedContent);
    assertEquals(1, libraryService.getAllContents().size());
  }

  @Test
  void deleteContentById() {
    // given
    UUID uuid = UUID.randomUUID();
    Content unsavedContent = Content.builder()
        .contentId(uuid)
        .contentType(ContentType.AUDIO)
        .title("Drake - Thank Me Later")
        .build();
    libraryService.addContent(unsavedContent);
    assertEquals(1, libraryService.getAllContents().size());

    // when
    libraryService.deleteContentById(uuid);

    // then
    assertEquals(0, libraryService.getAllContents().size());
  }

  @Test
  void deleteContentsByIdShouldNotFailWhenObjectDoesNotExist() {
    // given
    UUID uuid = UUID.randomUUID();
    assertEquals(0, libraryService.getAllContents().size());

    // when
    libraryService.deleteContentById(uuid);

    // then
    assertEquals(0, libraryService.getAllContents().size());
  }

  @Test
  void countContentByType() {
// given
    Content audioContent1 = Content.builder()
        .contentId(UUID.randomUUID())
        .contentType(ContentType.AUDIO)
        .title("Drake - Thank Me Later")
        .build();
    libraryService.addContent(audioContent1);

    Content audioContent2 = Content.builder()
        .contentId(UUID.randomUUID())
        .contentType(ContentType.AUDIO)
        .title("Drake - Take Care")
        .build();
    libraryService.addContent(audioContent2);

    Content content3 = Content.builder()
        .contentId(UUID.randomUUID())
        .contentType(ContentType.VIDEO)
        .title("The Hangover")
        .build();
    libraryService.addContent(content3);

    Content content4 = Content.builder()
        .contentId(UUID.randomUUID())
        .contentType(ContentType.POETRY)
        .title("The Raven - Edgar Allan Poe")
        .build();
    libraryService.addContent(content4);

    // when
    // then
    assertEquals(2, libraryService.countContentByType(ContentType.AUDIO));
    assertEquals(1, libraryService.countContentByType(ContentType.POETRY));
    assertEquals(1, libraryService.countContentByType(ContentType.VIDEO));
  }

  @Test
  void getAllContents() {
    // given
    Content audioContent1 = Content.builder()
        .contentId(UUID.randomUUID())
        .contentType(ContentType.AUDIO)
        .title("Drake - Thank Me Later")
        .build();
    libraryService.addContent(audioContent1);

    Content audioContent2 = Content.builder()
        .contentId(UUID.randomUUID())
        .contentType(ContentType.AUDIO)
        .title("Drake - Take Care")
        .build();
    libraryService.addContent(audioContent2);

    Content content3 = Content.builder()
        .contentId(UUID.randomUUID())
        .contentType(ContentType.VIDEO)
        .title("The Hangover")
        .build();
    libraryService.addContent(content3);

    Content content4 = Content.builder()
        .contentId(UUID.randomUUID())
        .contentType(ContentType.POETRY)
        .title("The Raven - Edgar Allan Poe")
        .build();
    libraryService.addContent(content4);

    // when
    List<Content> allContentsList = libraryService.getAllContents();

    // then
    assertEquals(4, allContentsList.size());
  }

  @Test
  void getContentsByType() {
    // given
    Content audioContent1 = Content.builder()
        .contentId(UUID.randomUUID())
        .contentType(ContentType.AUDIO)
        .title("Drake - Thank Me Later")
        .build();
    libraryService.addContent(audioContent1);

    Content audioContent2 = Content.builder()
        .contentId(UUID.randomUUID())
        .contentType(ContentType.AUDIO)
        .title("Drake - Take Care")
        .build();
    libraryService.addContent(audioContent2);

    Content content3 = Content.builder()
        .contentId(UUID.randomUUID())
        .contentType(ContentType.VIDEO)
        .title("The Hangover")
        .build();
    libraryService.addContent(content3);

    Content content4 = Content.builder()
        .contentId(UUID.randomUUID())
        .contentType(ContentType.POETRY)
        .title("The Raven - Edgar Allan Poe")
        .build();
    libraryService.addContent(content4);

    // when
    List<Content> audioContentList = libraryService.getContentsByType(ContentType.AUDIO);

    // then
    assertEquals(4, libraryService.getAllContents().size());
    assertEquals(2, audioContentList.size());
  }
}
