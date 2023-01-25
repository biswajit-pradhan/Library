package com.library.main.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.main.data.BookRepository;
import com.library.main.data.ReaderRepository;
import com.library.main.model.Book;
import com.library.main.model.Reader;
@Service
public class ReaderService {
	@Autowired
	private ReaderRepository readerRepository;
	


	public void insertReader(Reader reader) {
		readerRepository.save(reader);
	}

	public List<Reader> getAllReader() {
		
		return readerRepository.findAll();
	}

	public Optional<Reader> getReaderById(int readerId) {
		Optional optional = readerRepository.findById(readerId);
		return optional;
	}

	public void updateReader(Reader reader) {
		readerRepository.save(reader);
		
	}

	public void deleteReader(int rid) {
		readerRepository.deleteById(rid);
		
	}

}
