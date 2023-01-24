package com.library.main.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.main.model.Reader;
import com.library.main.model.ReaderBook;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {


}
