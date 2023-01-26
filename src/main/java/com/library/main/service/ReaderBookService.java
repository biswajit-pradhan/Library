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
	





	public List<ReaderBook> getReaderBookById(int bid) {
		
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


	public List<Book> getBookByReaderId(int rid) {
		List<ReaderBook>list=readerBookRepository.findAll();
		
		List<Book>filteredList=list.stream().filter(e->e.getReader().getId()==rid)
													.map(e->e.getBook()).collect(Collectors.toList());
		return filteredList;
	}


	public Optional<ReaderBook> getReaderBookId(int rbid) {


		return readerBookRepository.findById(rbid);
	}      
	
	
	


}
