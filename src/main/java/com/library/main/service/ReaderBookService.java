package com.library.main.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.main.data.ReaderBookRepository;
import com.library.main.model.Book;
import com.library.main.model.Reader;
import com.library.main.model.ReaderBook;

@Service
public class ReaderBookService {

	@Autowired
	private ReaderBookRepository readerBookRepository;
	
	public void assign(ReaderBook readerBook) {

		readerBookRepository.save(readerBook);
	}


	public List<Reader> getReadersByBookId(int bid) {
		List<ReaderBook> list = readerBookRepository.findAll();
		List<Reader> filteredlist =	list.stream()
											.filter(e->e.getBook().getId()==bid)
											.map(e->e.getReader())
											.collect(Collectors.toList());
		return filteredlist;
	}

	public List<ReaderBook> getReaderBookById_book(int bid) {
		
		return readerBookRepository.findAll().stream().filter(r->r.getBook().getId()==bid).collect(Collectors.toList());
	}
	
	public Optional<ReaderBook> getReaderBookByID(int rbid) {
		Optional<ReaderBook> optional = readerBookRepository.findById(rbid);
		return optional;
	}


	public List<ReaderBook> getAllReaderBook() {
		
		return readerBookRepository.findAll();
	}
	
	public void deleteReaderBookById(int rbid) {
		readerBookRepository.deleteById(rbid);

	}



	public Double getTotalRentByReaderId(int rid) {
		List<ReaderBook> list = readerBookRepository.findAll();
		
		double bookSum=0;
		double daysSum=0;
		
		
		List<Double> bookPrices = list.stream()
						.filter(e->e.getReader().getId()==rid)
						.map(e->e.getBook().getPrice())
						.collect(Collectors.toList());
		
		for(Double p:bookPrices) {
			bookSum +=p;
		}
		List<Integer> noOfDays = list.stream()
				.filter(e->e.getReader().getId()==rid)
				.map(e->e.getDays())
				.collect(Collectors.toList());
		
		
		
		for(Integer i:noOfDays) {
			daysSum+=i;
		}
		double res = (bookSum)*(daysSum)*2/100;
		return res;
	}

	public List<Book> getBookByReaderId(int rid) {
		List<ReaderBook>list=readerBookRepository.findAll();
		
		List<Book>filteredList=list.stream().filter(e->e.getReader().getId()==rid)
													.map(e->e.getBook()).collect(Collectors.toList());
		return filteredList;
	}      
	
	
	



}
