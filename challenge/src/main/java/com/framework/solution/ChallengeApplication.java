package com.framework.solution;

import com.framework.solution.enums.ContentType;
import com.framework.solution.model.Content;
import com.framework.solution.service.Library;
import java.util.List;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class ChallengeApplication {

	private static final Logger logger = LoggerFactory.getLogger(ChallengeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);

		Library library = new Library();

		ObjectMapper mapper = new ObjectMapper();
	
		try {
			List<Content> content = Arrays.asList(mapper.readValue(new File("c:\\Users\\tskey\\Documents\\Taqiyya\\Framework Challenge\\candidate-challenges\\challenge\\src\\main\\resources\\content.json"), Content[].class));
			for (Content newEntry : content) {
				library.addContent(newEntry);
			}
		}  catch (IOException e) {
			e.printStackTrace();
		}
		List<Content> contentList = library.getAllContents();
		List<Content> contentsByType = library.getContentsByType(ContentType.FANTASY);

		for (Content conEntry : contentList) {
			logger.info("Library content includes: " + conEntry.toString());	
		}
		
		for (Content typEntry : contentsByType) {
			logger.info("Items of type Fantasy are: " + typEntry.toString());	
		}
		
		System.out.println(library.toString());
		

	}

}
