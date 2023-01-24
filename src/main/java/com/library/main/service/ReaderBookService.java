package com.library.main.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.main.data.ReaderBookRepository;
import com.library.main.model.Reader;
import com.library.main.model.ReaderBook;
@Service
public class ReaderBookService {
	
	@Autowired
	private ReaderBookRepository readerBookRepository;

	public void assign(ReaderBook readerBook) {
		
		readerBookRepository.save(readerBook);
	}

	public List<ReaderBook> getReadersByBookId(int bid) {
		List<ReaderBook> list = readerBookRepository.findAll();
		List<ReaderBook> filteredlist =	list.stream()
											.filter(e->e.getBook().getId()==bid)
											.collect(Collectors.toList());
		return filteredlist;
	}



}
