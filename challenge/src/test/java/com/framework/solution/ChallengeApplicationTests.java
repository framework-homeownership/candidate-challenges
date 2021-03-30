package com.framework.solution;
import com.framework.solution.enums.ContentType;
import com.framework.solution.model.Content;
import com.framework.solution.service.Library;
import java.util.List;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class ChallengeApplicationTests {
	
	 Library library;
	 List<Content> content;
	 ObjectMapper mapper;

	@BeforeEach
	void init() {
		library = new Library();
		mapper = new ObjectMapper();
	
		try {
			content = Arrays.asList(mapper.readValue(new File("c:\\Users\\tskey\\Documents\\Taqiyya\\Framework Challenge\\candidate-challenges\\challenge\\src\\main\\resources\\content.json"), Content[].class));
			for (Content newEntry : content) {
				library.addContent(newEntry);
			}
		}  catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void getAllContentsTest() {
		List<Content> testLib = library.getAllContents();
				
		assertNotNull(testLib);
		assertEquals(content.size(), testLib.size());
		assertEquals(content, testLib);
	}

	@Test
	void countContentByTypeTest() {
		int count = library.countContentByType(ContentType.FANTASY);
		assertEquals(3, count);
	}

	@Test
	void getContentsByType() {
		List<Content> contLib = library.getContentsByType(ContentType.NONFICTION);
		assertNotNull(contLib);
		assertEquals(2, contLib.size());
		assertEquals(contLib.get(0).getContentType(), ContentType.NONFICTION);
		assertEquals(contLib.get(1).getContentType(), ContentType.NONFICTION);
	}

	@Test
	void deleteContentByIdTest() {
		int libSize = library.getAllContents().size();
		
		UUID newId = UUID.fromString("5a33d150-81eb-11eb-8dcd-0242ac130003");
		library.deleteContentById(newId);
		int newLibSize = library.getAllContents().size();
		assertEquals(libSize - 1, newLibSize);

	}

	@Test
	void addContentTest() {
		Content myNewEntry = new Content(UUID.randomUUID(), "Poetic Justice", ContentType.POETRY);

		int libSize = library.getAllContents().size();
		library.addContent(myNewEntry);
		int newLibSize = library.getAllContents().size();
		assertEquals(libSize + 1, newLibSize);
	}

}
