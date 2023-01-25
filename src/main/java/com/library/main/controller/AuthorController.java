package com.library.main.controller;

import java.util.ArrayList;
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

import com.library.main.model.Author;
import com.library.main.model.Book;
import com.library.main.model.Reader;
import com.library.main.service.AuthorService;
import com.library.main.service.BookService;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
	
	@Autowired
	private AuthorService  authorService;
	
	@Autowired
	private BookService bookService;
	
	/* Post API for Author */
	@PostMapping("/add/{bid}")
	public ResponseEntity<String> insertAuthor(@RequestBody Author author,@PathVariable("bid") int bid){
		Book book=bookService.getBookByID(bid).get();
		List<Book> books=new ArrayList<Book>();
		books.add(book);
		author.setBook(books);
		authorService.insertAuthor(author);
		return ResponseEntity.status(HttpStatus.OK).body("Author posted in DB");
	}
	
	// Get All API 
	@GetMapping("/allAuthor")
	public List<Author> getAllAuthor(){
		List<Author> list =authorService.getAllAuthor();
		return list;
	}
	/* Get by ID*/
	@GetMapping("/one/author/{id}")
	public ResponseEntity<Object> getById(@PathVariable("id")int id){
		Optional<Author> optional = authorService.getAuthorById(id);
		if(!optional.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID Given");
		
		Author author = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(author);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<String> updateAuthor(@RequestBody Author author){
		authorService.updateAuthor(author);
		return ResponseEntity.status(HttpStatus.OK).body("Author is updated");
	}
	/* Delete api for Author */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePublisher(@PathVariable("id") int id){
		authorService.deleteAuhorById(id);
		return ResponseEntity.status(HttpStatus.OK).body("author deleted from database");
	}
}