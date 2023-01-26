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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.main.model.Book;
import com.library.main.model.Reader;
import com.library.main.model.ReaderBook;
import com.library.main.service.BookService;
import com.library.main.service.ReaderBookService;
import com.library.main.service.ReaderService;

@RestController
@RequestMapping("/api/readerbook")
public class ReaderBookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private ReaderService readerService;
	@Autowired
	private ReaderBookService readerBookService;
	
	
	@PostMapping("/add/{readerId}/{bookId}")
	public ResponseEntity<String> assignBookToReader(@RequestBody ReaderBook readerBook,
									@PathVariable ("readerId") int readerId,
									@PathVariable ("bookId") int bookId) {
		
		Optional<Reader> optionalR = readerService.getReaderById(readerId);
		if(!optionalR.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Entered invalid readerId");
		Optional<Book> optionalB = bookService.getBookByID(bookId);
		if(!optionalR.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Entered invalid bookId");
		
		Reader reader = optionalR.get();
		Book book = optionalB.get();
		
		readerBook.setReader(reader);
		readerBook.setBook(book);
		
		readerBookService.assign(readerBook);
		return ResponseEntity.status(HttpStatus.OK).body("book is assigned to reader");
	}
	
	@GetMapping("/allReaderBook")
	public List<ReaderBook> getAllReaderBook() {
		List<ReaderBook> list = readerBookService.getAllReaderBook();
		return list;
		
	}
	
	@GetMapping("/one/{rbid}")
	public ResponseEntity<Object> getReaderBookById(@PathVariable("rbid") int rbid) {
		Optional<ReaderBook> optional = readerBookService.getReaderBookByID(rbid);
		if (!optional.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid IDs Given");
		ReaderBook readerBook = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(readerBook);
	}

	@GetMapping("/readers/{rbid}")
	public List<Reader> getReadersByBookId(@PathVariable("rbid") int rbid){
		List<Reader> list =readerBookService.getReadersByBookId(rbid);
		return list;
	}
	
	@DeleteMapping("/delete/{rbid}")
	public ResponseEntity<String> deleteReader(@PathVariable("rbid") int rbid) {
		readerBookService.deleteReaderBookById(rbid);
		return ResponseEntity.status(HttpStatus.OK).body("ReaderBook deleted");
	}
	

	@GetMapping("/totalrent/reader/{rid}")
	public Double getTotalRentByReaderId(@PathVariable("rid") int rid) {
		Double totalprice = readerBookService.getTotalRentByReaderId(rid);
		return totalprice;
	}

	/*Get book by Reader Id*/
	@GetMapping("/reader/{rid}")
	public List<Book> getBookbyReaderId(@PathVariable("rid")int rid){
		List<Book>list= readerBookService.getBookByReaderId(rid);
		return list;

	}

}
