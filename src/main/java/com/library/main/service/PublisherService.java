package com.library.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.main.data.PublisherRepository;
import com.library.main.model.Publisher;

@Service
public class PublisherService {
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	public Publisher getPublisherById(int pid) {
		Optional<Publisher> optional = publisherRepository.findById(pid);
		if(optional != null)
			 return optional.get(); 
		return null;
	}
}
