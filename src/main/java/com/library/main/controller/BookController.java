package com.library.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.main.model.Book;
import com.library.main.model.Publisher;
import com.library.main.service.BookService;
import com.library.main.service.PublisherService;

@RestController
@RequestMapping(value="/api/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private PublisherService publisherService;
	
	@PostMapping("/add")
	public ResponseEntity<String> postBook(@RequestBody Book book,@PathVariable("pid") int pid) {
		Publisher publisher=publisherService.getPublisherById(pid);
		book.setPublisher(publisher);
		bookService.postBook(book);
		return  ResponseEntity.status(HttpStatus.OK).body("Book added");
	}
	
}
