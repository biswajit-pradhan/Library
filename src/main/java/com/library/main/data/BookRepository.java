package com.library.main.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.main.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
