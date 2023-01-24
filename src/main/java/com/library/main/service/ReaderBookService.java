package com.library.main.service;

import org.springframework.stereotype.Service;

import com.library.main.data.ReaderBookRepository;
import com.library.main.model.ReaderBook;
@Service
public class ReaderBookService {
	
	private ReaderBookRepository readerBookRepository;

	public void assign(ReaderBook readerBook) {
		
		readerBookRepository.save(readerBook);
	}

}
