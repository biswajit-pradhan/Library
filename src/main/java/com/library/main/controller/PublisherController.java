package com.library.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.main.model.Book;
import com.library.main.model.Publisher;
import com.library.main.model.ReaderBook;
import com.library.main.service.BookService;
import com.library.main.service.PublisherService;
import com.library.main.service.ReaderBookService;

@RestController
@RequestMapping("/api/publisher")
public class PublisherController {
	
	@Autowired
	private PublisherService publisherService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ReaderBookService readerBookService;
	
	/* Post API for Publisher */
	@PostMapping("/add")
	public ResponseEntity<String> insertPublisher(@RequestBody Publisher publisher){
		publisherService.insertPublisher(publisher);
		return ResponseEntity.status(HttpStatus.OK).body("Publisher posted in DB");
	}
	
	// Get All API 
	@GetMapping("/allpublisher")
	public List<Publisher> getAllPublishers(){
		List<Publisher> list = publisherService.getAllPublishers();
		return list;
	}
	/* Get by ID*/
	@GetMapping("/one/publisher/{id}")
	public ResponseEntity<Object> getPublisherById(@PathVariable("id")int id){
		Optional<Publisher> optional = publisherService.getPublisherById(id);
		if(!optional.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID Given");
		
		Publisher publisher = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(publisher);
	}
	
	/* PUT Api for Publisher */
	
	@PutMapping("/update")
	public ResponseEntity<String> updatePublisher(@RequestBody Publisher publisher){
		publisherService.updatePublisher(publisher);
		return ResponseEntity.status(HttpStatus.OK).body("Publisher is updated");
	}
	/* Delete api for Publisher */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePublisher(@PathVariable("id") int id){
	publisherService.deletePublisher(id);
		return ResponseEntity.status(HttpStatus.OK).body("publisher deleted from database");
	}
	
	/* Get TotalRent By PublisherId */
	
	
}