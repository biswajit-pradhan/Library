package com.library.main.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.main.model.Book;
import com.library.main.model.Reader;
import com.library.main.model.ReaderBook;

public interface ReaderBookRepository extends JpaRepository<ReaderBook, Integer> {
   @Query("select  rb.reader from ReaderBook rb where rb.book.id=?1")
	List<Reader> getByBookId(int id);

   
}
