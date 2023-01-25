package com.library.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.main.data.ReaderBookRepository;
import com.library.main.data.ReaderRepository;
import com.library.main.model.Reader;
@Service
public class ReaderService {
	@Autowired
	private ReaderRepository readerRepository;
    
	@Autowired
	private ReaderBookRepository readerBookRepository;

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
