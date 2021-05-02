package com.framework.solution;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.UUID;

import com.framework.solution.enums.ContentType;
import com.framework.solution.model.Content;
import com.framework.solution.service.Library;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChallengeApplicationTests {
	
	Library getSeededLibrary(){
		Library library = new Library();
		List<Content> libraryContent = library.getAllContents();
		assert((libraryContent.size())== 6);
		return library;
	}

	@Test
	void contextLoads() {
	}

	@Test
	void getAllContents(){
		getSeededLibrary();
	}

	@Test
	void addContent(){
		String title = "The Return of the King";
		Library library = getSeededLibrary();

		library.addContent(new Content(new UUID(0, 100), title, ContentType.FANTASY));
		List<Content> updatedContent = library.getAllContents();
		assert(updatedContent.size() == 7);
		assert(updatedContent.stream().anyMatch(c -> c.getTitle().equals(title)));
	}

	@Test
	void deleteContent(){
		Library library = getSeededLibrary();
		UUID testUUID = UUID.fromString("5a33d150-81eb-11eb-8dcd-0242ac130003");
		library.deleteContentById(testUUID);

		assert(library.getAllContents().size() == 5);
		for(Content content : library.getAllContents()){
			if(content.getContentId().equals(testUUID)){
				fail("testUUID " + testUUID + " found despite delete");
			}
		}
	}

	@Test
	void countContentByType(){
		Library library = getSeededLibrary();
		int poetryCount = library.countContentByType(ContentType.POETRY);
		assert(poetryCount == 1);
	}

	@Test
	void getContentsByType(){
		Library library = getSeededLibrary();
		List<Content> fantasyContent = library.getContentsByType(ContentType.FANTASY);
		assert(fantasyContent.size() == 3);
	}
}
