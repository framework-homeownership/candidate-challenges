package com.framework.solution.service;

import com.framework.solution.enums.ContentType;
import com.framework.solution.model.Content;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Integration Tests with the Full Spring Context & Data From Content.json
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LibraryIntegrationTest {

  @Autowired
  private Library libraryService;

  @BeforeEach
  void setUp() {
  }

  @Test
  @Order(1)
  void findById() {
    // given
    UUID uuid = UUID.fromString("338e2efc-8749-11eb-8dcd-0242ac130003");

    Content expected = Content.builder()
        .contentId(uuid)
        .contentType(ContentType.NONFICTION)
        .title("Evicted")
        .build();
    // when
    Optional<Content> queriedContent = libraryService.findById(uuid);

    // then
    assertTrue(queriedContent.isPresent());
    assertEquals(expected, queriedContent.get());
    assertFalse(libraryService.findById(UUID.randomUUID()).isPresent());
  }

  @Test
  void addContent() {
//    // given
//    UUID uuid = UUID.randomUUID();
//    Content unsavedContent = Content.builder()
//        .contentId(uuid)
//        .contentType(ContentType.AUDIO)
//        .title("Drake - Thank Me Later")
//        .build();
//
//    // when
//    Content savedContent = libraryService.addContent(unsavedContent);
//
//    // then
//    assertEquals(unsavedContent, savedContent);
//    assertEquals(1, libraryService.getAllContents().size());
  }

  @Test
  @Order(2)
  void countContentByType() {
    // given

    // when

    // then
    assertEquals(3, libraryService.countContentByType(ContentType.FANTASY));
    assertEquals(1, libraryService.countContentByType(ContentType.POETRY));
    assertEquals(2, libraryService.countContentByType(ContentType.NONFICTION));
    assertEquals(0, libraryService.countContentByType(ContentType.AUDIO));
    assertEquals(0, libraryService.countContentByType(ContentType.FICTION));
    assertEquals(0, libraryService.countContentByType(ContentType.HISTORY));
    assertEquals(0, libraryService.countContentByType(ContentType.SCHOLARLY_JOURNAL));
    assertEquals(0, libraryService.countContentByType(ContentType.VIDEO));
  }

  @Test
  @Order(3)
  void getAllContents() {
    // given

    // when
    List<Content> allContentsList = libraryService.getAllContents();
    allContentsList.forEach(System.out::println);

    // then
    assertEquals(6, allContentsList.size());
  }

  @Test
  @Order(4)
  void getContentsByType() {
    // given

    // when
    List<Content> audioContentList = libraryService.getContentsByType(ContentType.AUDIO);
    List<Content> poetryContentList = libraryService.getContentsByType(ContentType.POETRY);

    // then
    assertEquals(6, libraryService.getAllContents().size());
    assertEquals(0, audioContentList.size());
    assertEquals(1, poetryContentList.size());
  }

  @Test
  @Order(5)
  void deleteContentById() {
    // given
    UUID uuid = UUID.fromString("338e2efc-8749-11eb-8dcd-0242ac130003");
    assertTrue(libraryService.findById(uuid).isPresent());

    // when
    libraryService.deleteContentById(uuid);

    // then
    assertFalse(libraryService.findById(uuid).isPresent());
  }

  @Test
  @Order(6)
  void deleteContentsByIdShouldNotFailWhenObjectDoesNotExist() {
    // given
    UUID uuid = UUID.fromString("338e2efc-8749-11eb-8dcd-0242ac130009");
    assertFalse(libraryService.findById(uuid).isPresent());

    // when
    libraryService.deleteContentById(uuid);

    // then
    assertFalse(libraryService.findById(uuid).isPresent());
  }
}
