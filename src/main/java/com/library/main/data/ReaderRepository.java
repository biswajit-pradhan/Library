package com.library.main.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.main.model.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {
}
