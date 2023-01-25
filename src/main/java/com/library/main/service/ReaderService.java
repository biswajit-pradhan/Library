package com.library.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.main.data.AuthorRepository;
import com.library.main.data.ReaderBookRepository;
import com.library.main.data.ReaderRepository;
import com.library.main.model.Reader;
@Service
public class ReaderService {
	@Autowired
	private ReaderRepository readerRepository;
	@Autowired
	private AuthorRepository authorRepository;
    
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




	public List<Reader>  getReadersByAuthorId(int aid) {
		//List<Reader> list=readerRepository.findAll();
		//List<Reader> filteredList= list.stream().filter(e->e.getId()== aid).collect(Collectors.toList());
		
		  return  getReadersByAuthorId(aid);
	}


}
