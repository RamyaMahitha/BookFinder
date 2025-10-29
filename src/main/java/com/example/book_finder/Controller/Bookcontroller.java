package com.example.book_finder.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/books")
//@CrossOrigin(origins="http://localhost:5173")
@CrossOrigin(origins="${FRONTEND_URL:https://localhost:5173}")
public class Bookcontroller {
  
	@GetMapping
	public ResponseEntity<String> getBooks(
			@RequestParam(required=false) String title,
			@RequestParam(required=false) String author,
			@RequestParam(required=false) String subject) {
		String baseUrl="https://openlibrary.org/search.json?";
		StringBuilder url=new StringBuilder(baseUrl);
		
		boolean firstParam=true;
		if(title!=null && !title.isEmpty()) {
			url.append("title=").append(title.replace(" ", "+"));
			firstParam=false;
		}
		if(author!=null && !author.isEmpty()) {
			if(!firstParam) 
				url.append("&");
			url.append("author=").append(author.replace(" ", "+"));
			firstParam=false;
		}
		if(subject!=null && !subject.isEmpty()) {
			if(!firstParam)
				 url.append("&");
			url.append("subject=").append(subject.replace(" ", "+"));
		}
		RestTemplate rest=new RestTemplate();
		String result=rest.getForObject(url.toString(), String.class);
		return ResponseEntity.ok(result);
	}
	
}
