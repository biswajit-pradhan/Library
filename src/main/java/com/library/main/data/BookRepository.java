package com.library.main.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.library.main.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	Optional<Book> findByPublisherId(@PathVariable("id")int id);
	

}
