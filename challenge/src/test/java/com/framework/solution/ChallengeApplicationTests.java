package com.framework.solution;

import java.util.List;
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

}
