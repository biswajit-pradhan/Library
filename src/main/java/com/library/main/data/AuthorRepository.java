package com.library.main.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.main.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{

}
