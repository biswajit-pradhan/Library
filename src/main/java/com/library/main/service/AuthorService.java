package com.library.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.library.main.data.AuthorRepository;
import com.library.main.data.ReaderBookRepository;
import com.library.main.data.ReaderRepository;
import com.library.main.model.Author;
import com.library.main.model.Book;
import com.library.main.model.Reader;
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

}
