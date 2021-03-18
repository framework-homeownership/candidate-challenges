package com.framework.solution;

import com.framework.solution.enums.ContentType;
import com.framework.solution.model.Content;
import com.framework.solution.service.Library;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeApplication {

	private static final Logger logger = LoggerFactory.getLogger(ChallengeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);

		Library library = new Library();

		List<Content> contentList = library.getAllContents();
		List<Content> contentsByType = library.getContentsByType(ContentType.FANTASY);

		System.out.println(library.toString());
	}

}
