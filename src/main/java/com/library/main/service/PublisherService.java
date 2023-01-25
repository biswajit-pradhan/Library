package com.library.main.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.main.data.PublisherRepository;
import com.library.main.model.Publisher;
@Service
public class PublisherService {
	
	@Autowired
	private PublisherRepository publisherRepository;
	

	public List<Publisher> getAllPublishers() {
		
		return publisherRepository.findAll();
	}

	public void insertPublisher(Publisher publisher) {

     publisherRepository.save(publisher);
		
	}

	public Optional getPublisherById(int id) {
		Optional<Publisher> optional = publisherRepository.findById(id);		
		return optional;
	}

	public void updatePublisher(Publisher publisher) {
		publisherRepository.save(publisher);
		
		
	}

	public void deletePublisher(int id) {
		publisherRepository.deleteById(id);
		
		
		
	}

	

	public Object getTotalRentByPublisherId(double bookprice, int totalDays) {

			if (totalDays <= 7 && totalDays > 0) {
				return (bookprice / 100) * 10;
			} else if (totalDays > 7 && totalDays <= 14) {
				return (bookprice / 100) * 15;
			} else if (totalDays > 14 && totalDays <= 21) {
				return (bookprice / 100) * 20;
			} else if (totalDays > 21 && totalDays <= 30) {
				return (bookprice / 100) * 25;
			} else {
				return "You can take book rent for maximum 30 days only..";
			}
		}

	
}
