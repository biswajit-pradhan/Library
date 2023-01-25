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
@RequestMapping(value = "/api/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private PublisherService publisherService;
	
	@Autowired
	private ReaderBookService readerBookService;

	@PostMapping("/add/{pid}")
	public ResponseEntity<String> postBook(@RequestBody Book book, @PathVariable("pid") int pid) {
		Publisher publisher = (Publisher) publisherService.getPublisherById(pid).get();
		book.setPublisher(publisher);
		bookService.postBook(book);
		return ResponseEntity.status(HttpStatus.OK).body("Book added");
	}

	@GetMapping("/getall")
	public List<Book> getAllBook() {
		List<Book> list = bookService.getAllBook();
		return list;
	}

	@GetMapping("/one/{bid}")
	public ResponseEntity<Object> getBookById(@PathVariable("bid") int bid) {
		Optional<Book> optional = bookService.getBookByID(bid);
		if (!optional.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid book id...");
		Book book = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(book);
	}
	
	@PutMapping("/update/{pid}/{bid}")
	public ResponseEntity<String> updateBook(@RequestBody Book bookToUpdate, @PathVariable("pid") int pid,@PathVariable("bid") int bid) {
		Publisher publisher = (Publisher) publisherService.getPublisherById(pid).get();
		Book book=(Book) bookService.getBookByID(bid).get();
		book.setPublisher(publisher);
		book.setName(bookToUpdate.getName());
		book.setPrice(bookToUpdate.getPrice());
		bookService.updateBook(book);
		return ResponseEntity.status(HttpStatus.OK).body("Book updated");
	}

	@DeleteMapping("/delete/{bid}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("bid") int bid) {
		bookService.deleteBookById(bid);
		return ResponseEntity.status(HttpStatus.OK).body("Book deleted");
	}
	/* GetBook by PublisherId */
	@GetMapping("/publisher/{id}")
	public ResponseEntity<Object> getBookByPublisherId(@PathVariable("id") int id) {
		Optional<Book> optional = bookService.getBookByPublisherId(id);
		if(!optional.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid publisher Id Given");
		
		Book book = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(book);	
	}
	/* get book by author id */
	
	@GetMapping("/gettotalrentbybookid/{bid}")
	public ResponseEntity<Object> getTotalRentByBookId(@PathVariable("bid") int bid) {
		Optional<Book> optional = bookService.getBookByID(bid);
		if (!optional.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid book id...");
		Book book = optional.get();
		List<ReaderBook> readerBook=readerBookService.getReaderBookById(bid);
		int totalDays=readerBook.get(0).getDays();
		Object totalCost=bookService.getTotalRentByBookId(book.getPrice(),totalDays);
		return ResponseEntity.status(HttpStatus.OK).body(totalCost);
	}
	
	
}
