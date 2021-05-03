package com.framework.solution;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.solution.enums.ContentType;
import com.framework.solution.model.Content;
import com.framework.solution.service.Library;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class ChallengeApplication {

	@Bean
	CommandLineRunner runner() {
		return args -> {
			ObjectMapper objectMapper = new ObjectMapper();
			TypeReference<List<Content>> typeReference = new TypeReference<List<Content>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/content.json");
			try {
				List<Content> contentList = objectMapper.readValue(inputStream, typeReference);
				System.out.println(contentList);
			} catch(IOException e) {
				log.error("@CommandLineRunner hti an IOException: {}", e.getMessage());
				e.printStackTrace();
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);

		Library library = new Library();

		List<Content> contentList = library.getAllContents();
		List<Content> contentsByType = library.getContentsByType(ContentType.FANTASY);

		System.out.println(library.toString());
	}

}
