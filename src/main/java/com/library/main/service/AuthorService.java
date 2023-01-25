package com.library.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.main.data.AuthorRepository;
import com.library.main.data.ReaderRepository;
import com.library.main.model.Author;
import com.library.main.model.Reader;

@Service
public class AuthorService {
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private ReaderRepository readerRepository;
	
	public void insertAuthor(Author author) {

		authorRepository.save(author);
			
		}

	public List<Author> getAllAuthor() {
		return authorRepository.findAll();
	}
	

	public Optional<Author> getAuthorById(int id) {
		Optional<Author> optional = authorRepository.findById(id);
		return optional ;
	}

	public void updateAuthor(Author author) {
		authorRepository.save(author);
		
	}

	public void deleteAuhorById(int id) {
		authorRepository.deleteById(id);
		
	}

//	public List<Reader> getReadersByAuthorId(int aid) {
//		return readerRepository.findByAuthorId(aid);
//	}
	

}
