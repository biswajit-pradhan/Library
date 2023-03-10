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

import com.library.main.data.ReaderBookRepository;
import com.library.main.data.UserRepository;
import com.library.main.model.Author;
import com.library.main.model.Book;
import com.library.main.model.Reader;
import com.library.main.model.ReaderBook;
import com.library.main.model.User;
import com.library.main.service.AuthorService;
import com.library.main.service.ReaderBookService;
import com.library.main.service.ReaderService;

@RestController
@RequestMapping("/api/reader")
public class ReaderController {
	
	@Autowired
	private ReaderService readerService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ReaderBookService readerBookService;
	
	private AuthorService authorService;
	
	@Autowired
	private ReaderBookRepository readerBookRepository;
	
	@PostMapping("/add")
	public ResponseEntity<String> insertReader(@RequestBody Reader reader){
		//Fetch User info from employee input and save it in DB 
				User user = reader.getUser(); //I have username and password 
				//I will assign the role
				user.setRole("READER");
				//Converting plain text password into encoded text
				String encodedPassword = passwordEncoder.encode(user.getPassword());
				//attach encoded password to user
				user.setPassword(encodedPassword);
				user  = userRepository.save(user);
				//Attach user object to employee
				reader.setUser(user);
		
		readerService.insertReader(reader);
		return ResponseEntity.status(HttpStatus.OK).body("Reader posted in DB");
	}
	
	@GetMapping("/allreader")
	public List<Reader> getAllPublishers(){
		List<Reader> list = readerService.getAllReader();
		return list;
	}
	
	
	@GetMapping("/one/reader/{rid}")
	public ResponseEntity<Object> getById(@PathVariable("rid")int rid){
		Optional<Reader> optional = readerService.getReaderById(rid);
		if(!optional.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Reader ID Given");
		
		Reader reader = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(reader);
	}
	@PutMapping("/update/{rid}")
	public ResponseEntity<String> updateReader(@RequestBody Reader readertoupdate, @PathVariable("rid") int rid ) {
		Reader reader = (Reader)readerService.getReaderById(rid).get();
		reader.setName(readertoupdate.getName());
		readerService.updateReader(reader);
		return ResponseEntity.status(HttpStatus.OK).body("Reader updated");
	}
	@DeleteMapping("/delete/{rid}")
	public ResponseEntity<String> deleteReader(@PathVariable("rid")int rid) {
		readerService.deleteReader(rid);
		return ResponseEntity.status(HttpStatus.OK).body("Reader deleted");
	}

	

	//Get reader by Author Id
	@GetMapping("/author/{aid}")
	public ResponseEntity<Object> getReadersByAuthorId(@PathVariable("aid") int aid){
		List<Reader> list=new ArrayList<>();
		Optional<Author> optional = authorService.getAuthorById(aid);
		if(!optional.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Author ID Given");
		
		Author author = optional.get();
		List<Book> bookList=author.getBook();
		bookList.stream().forEach(b->{
		List<Reader> rList=	readerBookRepository.getByBookId(b.getId());
		list.addAll(rList);
		});
		//List<Reader> list=readerService.getReadersByAuthorId(aid);
		return ResponseEntity.status(HttpStatus.OK).body(list);
		
	}
	



}
