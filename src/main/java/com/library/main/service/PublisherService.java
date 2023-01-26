package com.library.main.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.main.data.BookRepository;
import com.library.main.data.PublisherRepository;
import com.library.main.data.ReaderBookRepository;
import com.library.main.model.Book;
import com.library.main.model.Publisher;
import com.library.main.model.ReaderBook;

@Service
public class PublisherService {

	@Autowired
	private PublisherRepository publisherRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ReaderBookRepository readerBookRepository;

	@Autowired
	private ReaderBookService readerBookService;

	public List<Publisher> getAllPublishers() {

		return publisherRepository.findAll();
	}

	public void insertPublisher(Publisher publisher) {

		publisherRepository.save(publisher);

	}

	public Optional getPublisherById(int id) {
		Optional<Publisher> optional = publisherRepository.findById(id);
		return optional;
	}

	public void updatePublisher(Publisher publisher) {
		publisherRepository.save(publisher);

	}

	public void deletePublisher(int id) {
		publisherRepository.deleteById(id);
	}

	public Object getTotalRentByPublisherId(int pid, List<Book> book) {
		List<ReaderBook> list = readerBookRepository.findAll();
		double totalPrice = 0;
		int totalDays = 0;
		List<Double> priceList = book.stream().filter(b -> b.getPublisher().getId() == pid).map(b -> b.getPrice())
				.collect(Collectors.toList());
		for (Double p : priceList) {
			totalPrice += p;
		}
		List<Integer> bookTest=book.stream().filter(b->b.getPublisher().getId()==pid).map(b->b.getId()).collect(Collectors.toList());
		List<Integer> daySum = list.stream().filter(e -> bookTest.contains(e.getBook().getId())).map(e -> e.getDays())
				.collect(Collectors.toList());
		
		for(Integer d:daySum) {
			totalDays+=d;
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
