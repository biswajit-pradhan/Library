package com.library.main.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.main.data.AuthorRepository;
import com.library.main.data.ReaderBookRepository;
import com.library.main.data.ReaderRepository;
import com.library.main.model.Author;
import com.library.main.model.Book;
import com.library.main.model.ReaderBook;

@Service
public class AuthorService {
	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private ReaderRepository readerRepository;

	@Autowired
	private ReaderBookRepository readerBookRepository;

	public void insertAuthor(Author author) {

		authorRepository.save(author);

	}

	public List<Author> getAllAuthor() {
		return authorRepository.findAll();
	}

	public Optional<Author> getAuthorById(int id) {
		Optional<Author> optional = authorRepository.findById(id);
		return optional;
	}

	public void updateAuthor(Author author) {
		authorRepository.save(author);

	}

	public void deleteAuhorById(int id) {
		authorRepository.deleteById(id);

	}

	public Object getTotalRentByAuthorId(List<Book> book) {
		List<ReaderBook> list = readerBookRepository.findAll();
		double totalPrice = 0;
		int totalDays = 0;
		List<Double> priceList = book.stream().map(b -> b.getPrice()).collect(Collectors.toList());
		for (Double p : priceList) {
			totalPrice += p;
		}
		List<Integer> daySum = list.stream().map(rb -> rb.getDays()).collect(Collectors.toList());
		for(Integer d : daySum) {
			totalDays += d;
		}
		if (totalDays <= 7 && totalDays > 0) {
			return (totalPrice / 100) * 10;
		} else if (totalDays > 7 && totalDays <= 14) {
			return (totalPrice / 100) * 15;
		} else if (totalDays > 14 && totalDays <= 21) {
			return (totalPrice / 100) * 20;
		} else if (totalDays > 21 && totalDays <= 30) {
			return (totalPrice / 100) * 25;
		} else {
			return "You can take book rent for maximum 30 days only..";
		}
	}

}
