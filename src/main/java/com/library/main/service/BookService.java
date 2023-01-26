package com.library.main.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.main.data.AuthorRepository;
import com.library.main.data.BookRepository;
import com.library.main.model.Author;
import com.library.main.model.Book;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;

	public Book postBook(Book book) {

		bookRepository.save(book);
		return book;
	}

	public List<Book> getAllBook() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

	public Optional<Book> getBookByID(int bid) {
		Optional optional = bookRepository.findById(bid);
		return optional;
	}

	public String deleteBookById(int bid) {
		bookRepository.deleteById(bid);
		return "null";

	}

	public void updateBook(Book book) {
		bookRepository.save(book);

	}

	public List<Book> getBookByPublisherId(int id) {
		List<Book> list =bookRepository.findAll();
				
		List<Book> filteredList= list.stream()
				        .filter(e -> e.getPublisher().getId()== id)
                        .collect(Collectors.toList());
		
		
		return filteredList;
	}

	public Object getTotalRentByBookId(double bookPrice, int totalDays) {

		if (totalDays <= 7 && totalDays > 0) {
			return (bookPrice / 100) * 10;
		} else if (totalDays > 7 && totalDays <= 14) {
			return (bookPrice / 100) * 15;
		} else if (totalDays > 14 && totalDays <= 21) {
			return (bookPrice / 100) * 20;
		} else if (totalDays > 21 && totalDays <= 30) {
			return (bookPrice / 100) * 25;
		} else {
			return "You can take book rent for maximum 30 days only..";
		}
	}
	

	
}
