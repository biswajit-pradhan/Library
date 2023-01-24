package com.library.main.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping("/readers/{bid}")
	public List<ReaderBook> getReadersByBookId(@PathVariable("bid") int bid){
		List<ReaderBook> list =readerBookService.getReadersByBookId(bid);
		return list;
	}
}
