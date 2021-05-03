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
    }

    @Test
    void deleteContentById() {
    }

    @Test
    void countContentByType() {
    }

    @Test
    void getAllContents() {
    }

    @Test
    void getContentsByType() {
    }
}
