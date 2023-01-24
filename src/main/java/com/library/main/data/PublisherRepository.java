package com.library.main.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.main.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

}
