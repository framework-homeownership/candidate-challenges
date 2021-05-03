package com.framework.solution.service;

import com.framework.solution.enums.ContentType;
import com.framework.solution.model.Content;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    private Library libraryService;

    @BeforeEach
    void setUp() {
        // Keeping the Unit test as small and efficient as possible so we'll not use the
        // @SpringBootTest, which is why we won't use Dependency Injection
        libraryService = new Library();
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
        assertEquals(0,libraryService.getAllContents().size());
    }

    @Test
    void countContentByType() {
        // given

        // when

        // then
    }

    @Test
    void getAllContents() {
        // given

        // when

        // then
    }

    @Test
    void getContentsByType() {
        // given

        // when

        // then
    }
}
