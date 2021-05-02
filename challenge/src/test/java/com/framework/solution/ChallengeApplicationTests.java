package com.framework.solution;

import java.util.List;
import java.util.UUID;

import com.framework.solution.enums.ContentType;
import com.framework.solution.model.Content;
import com.framework.solution.service.Library;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChallengeApplicationTests {
	
	@Test
	void contextLoads() {
	}

	@Test
	void getAllContents(){
		//String evictedId = "338e2efc-8749-11eb-8dcd-0242ac130003"
		Library library = new Library();
		List<Content> libraryContent = library.getAllContents();
		assert((libraryContent.size())== 6);
	}

	@Test
	void addContent(){
		Library library = new Library();
		assert(library.getAllContents().size() == 6);

		library.addContent(new Content(new UUID(0, 100), "The Return of the King", ContentType.FANTASY));
		assert(library.getAllContents().size() == 7);
	}

}
