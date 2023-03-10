package com.library.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.main.data.UserRepository;
import com.library.main.model.Author;
import com.library.main.model.Book;
import com.library.main.model.User;
import com.library.main.service.AuthorService;
import com.library.main.service.BookService;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
	
	@Autowired
	private AuthorService  authorService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/* Post API for Author */
	@PostMapping("/add/{bid}")
	public ResponseEntity<String> insertAuthor(@RequestBody Author author,@PathVariable("bid") int bid){
		Book book=bookService.getBookByID(bid).get();
		List<Book> books=new ArrayList<Book>();
		books.add(book);
		author.setBook(books);
		//Fetch User info from employee input and save it in DB 
		User user = author.getUser(); //I have username and password 
		//I will assign the role
		user.setRole("AUTHER");
		//Converting plain text password into encoded text
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		//attach encoded password to user
		user.setPassword(encodedPassword);
		user  = userRepository.save(user);
		//Attach user object to employee
		 author.setUser(user);
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

	@GetMapping("/gettotalrentbyauthorid/{aid}")
	public ResponseEntity<Object> getTotalRentByAuthorId(@PathVariable("aid") int aid) {
		Optional<Author> optional = authorService.getAuthorById(aid);
		if (!optional.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID Given");
		Author author=optional.get();
		List<Book> book=author.getBook();
		Object totalCost=authorService.getTotalRentByAuthorId(book);
		return ResponseEntity.status(HttpStatus.OK).body(totalCost);
	}


}