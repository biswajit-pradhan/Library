package com.library.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.main.data.BookRepository;
import com.library.main.data.PublisherRepository;
import com.library.main.model.Book;
@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	
	public void postBook(Book book) {
		
		bookRepository.save(book);
	}

	public List<Book> getAllBook() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

	public Optional<Book> getBookByID(int bid) {
		Optional optional =  bookRepository.findById(bid);
		return optional;
	}

	public void deleteBookById(int bid) {
		 bookRepository.deleteById(bid);
		
	}

	public void updateBook(Book book) {
		bookRepository.save(book);
		
	}

	public Optional<Book> getBookByPublisherId(int id) {
		Optional<Book> optional = bookRepository.findByPublisherId(id);
		return optional;
	}


}
